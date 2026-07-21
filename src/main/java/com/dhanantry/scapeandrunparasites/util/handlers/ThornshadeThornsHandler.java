package com.dhanantry.scapeandrunparasites.util.handlers;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import net.minecraft.advancements.Advancement;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.PotionEvent.PotionApplicableEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;

@EventBusSubscriber(modid = "srparasites")
public class ThornshadeThornsHandler {
   private static final String TAG_ROOT = "srp_thornshade_thorns";
   private static final String TAG_USES = "Uses";
   private static final String TAG_COOLDOWN_UNTIL = "CooldownUntil";
   private static final String TAG_EXPLODE_DELAY = "ExplodeDelay";
   private static final float MAX_HP_ALLOWED = 120.0F;
   private static final String TAG_HAS_EXPLODED = "HasExplodedOnce";

   @SubscribeEvent
   public static void onPotionApplicable(PotionApplicableEvent event) {
      EntityLivingBase living = event.getEntityLiving();
      if (living != null && !living.field_70170_p.field_72995_K) {
         PotionEffect incoming = event.getPotionEffect();
         if (incoming != null && incoming.func_188419_a() == SRPPotions.THORNSHADE_THORNS_E) {
            if (living instanceof EntityParasiteBase) {
               event.setResult(Result.DENY);
            } else if (living.func_110138_aP() > 120.0F) {
               event.setResult(Result.DENY);
            } else if (living.func_70644_a(SRPPotions.THORNSHADE_THORNS_E)) {
               event.setResult(Result.DENY);
            } else if (isInfiniteDuration(incoming)) {
               event.setResult(Result.DENY);
            } else {
               World world = living.field_70170_p;
               long now = world.func_82737_E();
               NBTTagCompound data = getThornshadeData(living);
               int uses = data.func_74762_e("Uses");
               if (uses >= 2) {
                  event.setResult(Result.DENY);
                  if (!data.func_74764_b("ExplodeDelay")) {
                     scheduleExplosion(living, data);
                  }

                  setThornshadeData(living, data);
               } else {
                  long cooldownUntil = data.func_74763_f("CooldownUntil");
                  if (cooldownUntil > now) {
                     event.setResult(Result.DENY);
                  } else {
                     data.func_74768_a("Uses", ++uses);
                     int durationTicks = incoming.func_76459_b();
                     long extraCooldownTicks = durationTicks / 2L;
                     data.func_74772_a("CooldownUntil", now + extraCooldownTicks);
                     setThornshadeData(living, data);
                     event.setResult(Result.DEFAULT);
                  }
               }
            }
         }
      }
   }

   @SubscribeEvent
   public static void onLivingUpdate(LivingUpdateEvent event) {
      EntityLivingBase living = event.getEntityLiving();
      if (living != null && !living.field_70170_p.field_72995_K) {
         NBTTagCompound data = getThornshadeData(living);
         if (data.func_74764_b("ExplodeDelay")) {
            int delay = data.func_74762_e("ExplodeDelay");
            if (delay > 0) {
               spawnBloodParticles(living.field_70170_p, living, 15);
               data.func_74768_a("ExplodeDelay", --delay);
               setThornshadeData(living, data);
            } else {
               data.func_82580_o("ExplodeDelay");
               setThornshadeData(living, data);
               doExplosion(living);
            }
         }
      }
   }

   private static void spawnBloodParticles(World world, EntityLivingBase entity, int count) {
      if (world instanceof WorldServer) {
         WorldServer ws = (WorldServer)world;
         double x = entity.field_70165_t;
         double y = entity.field_70163_u + entity.field_70131_O * 0.5;
         double z = entity.field_70161_v;
         int redDustId = Block.func_176210_f(Blocks.field_150451_bX.func_176223_P());

         for (int i = 0; i < count; i++) {
            double offsetX = (world.field_73012_v.nextDouble() - 0.5) * 0.6;
            double offsetY = world.field_73012_v.nextDouble() * 0.8;
            double offsetZ = (world.field_73012_v.nextDouble() - 0.5) * 0.6;
            double motionX = (world.field_73012_v.nextDouble() - 0.5) * 0.3;
            double motionY = world.field_73012_v.nextDouble() * 0.4 + 0.1;
            double motionZ = (world.field_73012_v.nextDouble() - 0.5) * 0.3;
            ws.func_175739_a(EnumParticleTypes.BLOCK_DUST, x + offsetX, y + offsetY, z + offsetZ, 0, motionX, motionY, motionZ, 0.0, new int[]{redDustId});
         }
      }
   }

   @SubscribeEvent
   public static void onLivingHurt(LivingHurtEvent event) {
      EntityLivingBase target = event.getEntityLiving();
      if (target != null && !target.field_70170_p.field_72995_K) {
         PotionEffect eff = target.func_70660_b(SRPPotions.THORNSHADE_THORNS_E);
         if (eff != null) {
            if (!isInfiniteDuration(eff)) {
               Entity trueSourceEntity = event.getSource().func_76346_g();
               if (trueSourceEntity instanceof EntityLivingBase) {
                  EntityLivingBase attacker = (EntityLivingBase)trueSourceEntity;
                  float incoming = event.getAmount();
                  if (!(incoming <= 0.0F)) {
                     NBTTagCompound data = getThornshadeData(target);
                     int uses = data.func_74762_e("Uses");
                     float reflectFactor;
                     if (uses <= 1) {
                        reflectFactor = 0.25F;
                     } else {
                        reflectFactor = 0.5F;
                     }

                     float reflected = incoming * reflectFactor;
                     if (!(reflected <= 0.0F)) {
                        attacker.func_70097_a(DamageSource.func_92087_a(target), reflected);
                     }
                  }
               }
            }
         }
      }
   }

   private static void scheduleExplosion(EntityLivingBase living, NBTTagCompound data) {
      if (!data.func_74767_n("HasExplodedOnce")) {
         data.func_74768_a("ExplodeDelay", 20);
         World world = living.field_70170_p;
         world.func_184148_a(
            null,
            living.field_70165_t,
            living.field_70163_u,
            living.field_70161_v,
            SRPSounds.ADAPTATION_P,
            SoundCategory.PLAYERS,
            1.5F,
            0.8F + world.field_73012_v.nextFloat() * 0.4F
         );
      }
   }

   private static void doExplosion(EntityLivingBase center) {
      World world = center.field_70170_p;
      if (!world.field_72995_K) {
         NBTTagCompound centerData = getThornshadeData(center);
         centerData.func_74757_a("HasExplodedOnce", true);
         if (center instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP)center;
            Advancement adv = player.func_71121_q()
               .func_73046_m()
               .func_191949_aK()
               .func_192778_a(new ResourceLocation("srparasites", "thornshade_self_destruct"));
            if (adv != null) {
               player.func_192039_O().func_192750_a(adv, "exploded");
            }
         }

         setThornshadeData(center, centerData);
         double x = center.field_70165_t;
         double y = center.field_70163_u;
         double z = center.field_70161_v;
         float innerRadius = 3.0F;
         float outerRadius = 10.0F;
         AxisAlignedBB outerBox = new AxisAlignedBB(x - outerRadius, y - outerRadius, z - outerRadius, x + outerRadius, y + outerRadius, z + outerRadius);
         Explosion explosion = new Explosion(world, null, x, y, z, 3.0F, false, false);
         explosion.func_77278_a();
         world.func_184148_a(null, x, y, z, SRPSounds.BUTHOL_BOOM, SoundCategory.PLAYERS, 2.0F, 1.0F);
         spawnRadialParticles(world, x, y + center.field_70131_O * 0.5, z, innerRadius, 50, EnumParticleTypes.CLOUD);
         spawnRadialParticles(world, x, y + center.field_70131_O * 0.5, z, outerRadius, 120, EnumParticleTypes.SPELL_WITCH);
         center.func_70097_a(DamageSource.field_76376_m.func_76348_h().func_151518_m(), Float.MAX_VALUE);
         AxisAlignedBB innerBox = new AxisAlignedBB(x - innerRadius, y - innerRadius, z - innerRadius, x + innerRadius, y + innerRadius, z + innerRadius);

         for (EntityLivingBase other : world.func_72872_a(EntityLivingBase.class, innerBox)) {
            if (other != center && other.func_70089_S() && !(other instanceof EntityParasiteBase)) {
               double distSq = other.func_70092_e(x, y, z);
               if (!(distSq > innerRadius * innerRadius) && other.func_70644_a(SRPPotions.THORNSHADE_THORNS_E) && !(other.func_110138_aP() > 120.0F)) {
                  NBTTagCompound data = getThornshadeData(other);
                  if (!data.func_74767_n("HasExplodedOnce") && !data.func_74764_b("ExplodeDelay")) {
                     data.func_74768_a("Uses", Math.max(2, data.func_74762_e("Uses")));
                     scheduleExplosion(other, data);
                     setThornshadeData(other, data);
                  }
               }
            }
         }

         for (EntityLivingBase otherx : world.func_72872_a(EntityLivingBase.class, outerBox)) {
            if (otherx != center && otherx.func_70089_S() && !(otherx instanceof EntityParasiteBase)) {
               double distSq = otherx.func_70092_e(x, y, z);
               if (!(distSq <= innerRadius * innerRadius) && !(otherx.func_110138_aP() > 120.0F) && !otherx.func_70644_a(SRPPotions.THORNSHADE_THORNS_E)) {
                  otherx.func_70690_d(new PotionEffect(SRPPotions.THORNSHADE_THORNS_E, 600, 0, false, true));
                  spawnRadialParticles(
                     world,
                     otherx.field_70165_t,
                     otherx.field_70163_u + otherx.field_70131_O * 0.5,
                     otherx.field_70161_v,
                     1.0F,
                     20,
                     EnumParticleTypes.SPELL_WITCH
                  );
               }
            }
         }
      }
   }

   private static void spawnRadialParticles(World world, double x, double y, double z, float radius, int count, EnumParticleTypes type) {
      if (world instanceof WorldServer) {
         WorldServer ws = (WorldServer)world;
         int bloodStateId = Block.func_176210_f(Blocks.field_150451_bX.func_176223_P());

         for (int i = 0; i < count; i++) {
            double angle = world.field_73012_v.nextDouble() * 2.0 * Math.PI;
            double ringRadius = radius * (0.7 + world.field_73012_v.nextDouble() * 0.3);
            double px = x + ringRadius * Math.cos(angle);
            double pz = z + ringRadius * Math.sin(angle);
            double py = y + (world.field_73012_v.nextDouble() - 0.5) * (radius * 0.2);
            double dirX = px - x;
            double dirZ = pz - z;
            double len = Math.sqrt(dirX * dirX + dirZ * dirZ);
            if (len == 0.0) {
               dirX = 1.0;
               dirZ = 0.0;
               len = 1.0;
            }

            dirX /= len;
            dirZ /= len;
            double baseOut = 0.4 + world.field_73012_v.nextDouble() * 0.5;
            double mistVy = 0.05 + world.field_73012_v.nextDouble() * 0.15;
            if (type == EnumParticleTypes.SPELL_WITCH) {
               ws.func_175739_a(EnumParticleTypes.BLOCK_DUST, px, py, pz, 0, dirX * baseOut * 0.6, mistVy, dirZ * baseOut * 0.6, 0.0, new int[]{bloodStateId});
            } else {
               ws.func_175739_a(type, px, py, pz, 1, dirX * baseOut * 0.3, mistVy, dirZ * baseOut * 0.3, 0.1, new int[0]);
            }

            double chunkScale = 0.6 + world.field_73012_v.nextDouble() * 0.8;
            double gx = dirX * chunkScale;
            double gz = dirZ * chunkScale;
            double gy = 0.25 + world.field_73012_v.nextDouble() * 0.6;
            ws.func_175739_a(EnumParticleTypes.BLOCK_DUST, px, py, pz, 0, gx, gy, gz, 0.0, new int[]{bloodStateId});
         }
      }
   }

   private static NBTTagCompound getThornshadeData(EntityLivingBase entity) {
      NBTTagCompound root = entity.getEntityData();
      if (!root.func_150297_b("srp_thornshade_thorns", 10)) {
         NBTTagCompound data = new NBTTagCompound();
         root.func_74782_a("srp_thornshade_thorns", data);
         return data;
      } else {
         return root.func_74775_l("srp_thornshade_thorns");
      }
   }

   private static void setThornshadeData(EntityLivingBase entity, NBTTagCompound data) {
      entity.getEntityData().func_74782_a("srp_thornshade_thorns", data);
   }

   private static boolean isInfiniteDuration(PotionEffect effect) {
      return effect.func_100011_g() || effect.func_76459_b() >= 72000 || effect.func_76459_b() == Integer.MAX_VALUE;
   }
}
