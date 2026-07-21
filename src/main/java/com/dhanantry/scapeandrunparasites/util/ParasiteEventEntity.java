package com.dhanantry.scapeandrunparasites.util;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.block.BlockGore;
import com.dhanantry.scapeandrunparasites.entity.EntityParasiticScent;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanSummon;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPBeckon;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPFeral;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPHijacked;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPInfected;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPMalleable;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.monster.EntityBiomass;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityBanoAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityCanraAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityEmanaAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityGimAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityHullAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityNoglaAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityRanracAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityShycoAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityWymoAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityZaaAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityInhooM;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityInhooS;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.EntityNak;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.EntityRof;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.EntityTonro;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.EntityUnvo;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerBear;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerCow;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerEnderman;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerHorse;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerHuman;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerPig;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerSheep;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerVillager;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerWolf;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityKol;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfBear;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfCow;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfEnderman;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfHorse;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfHuman;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfPig;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfSheep;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfVillager;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfWolf;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.special.EntitySpeCow;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.special.EntitySpeEnderman;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.special.EntitySpeHuman;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.special.EntitySpeVillager;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityBano;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityCanra;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityEmana;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityGim;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityHull;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityIki;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityNogla;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityRanrac;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityShyco;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityWymo;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityZaa;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityAlafha;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityAnged;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityEsor;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityFlog;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityGanro;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityOmboo;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityOrch;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent.EntityTenn;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityBomb;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileBiomass;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.network.SRPPacketMovingSound;
import com.dhanantry.scapeandrunparasites.network.SRPPacketParticle;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import com.dhanantry.scapeandrunparasites.util.spawn.ParasiteSummon;
import com.dhanantry.scapeandrunparasites.world.SRPExplosion;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import com.dhanantry.scapeandrunparasites.world.SRPWorldData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ClassInheritanceMultiMap;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import org.apache.logging.log4j.Level;

public class ParasiteEventEntity {
   public static boolean canSpawnNext = true;

   public static int entityChunkCount(World world, BlockPos pos, Class<? extends EntityLivingBase> mobC) {
      ClassInheritanceMultiMap<Entity>[] arrayE = world.func_175726_f(pos).func_177429_s();
      int v = 0;

      for (ClassInheritanceMultiMap<Entity> entities : arrayE) {
         if (entities != null) {
            Object[] arrayO = entities.toArray();

            for (Object o : arrayO) {
               if (o != null && mobC.isInstance(o)) {
                  v++;
               }
            }
         }
      }

      return v;
   }

   public static boolean checkEntity(EntityLivingBase entity, String[] list, boolean inverted) {
      ResourceLocation enti = EntityList.func_191301_a(entity);
      return enti != null ? checkName(enti.toString(), list, inverted) : false;
   }

   public static boolean checkName(String potentialElement, String[] blacklist, boolean isWhitelist) {
      return potentialElement == null ? false : Arrays.stream(blacklist).anyMatch(potentialElement::contains) != isWhitelist;
   }

   public static void orbApplyEffects(EntityLivingBase target, EntityParasiteBase in, String[] effects, int mobs) {
      for (String i : effects) {
         String[] here = i.split(";");

         try {
            if (here[5] == null) {
               return;
            }
         } catch (Exception var15) {
            return;
         }

         Potion potionE = Potion.func_180142_b(here[3]);
         if (potionE != null) {
            int self = Integer.parseInt(here[0]);
            int duration = Integer.parseInt(here[1]) * 20;
            int amp = Integer.parseInt(here[2]);
            int enemiesA = Integer.parseInt(here[4]);
            int enemiesD = Integer.parseInt(here[5]);
            if (enemiesA != 0) {
               amp += mobs / enemiesA;
            }

            if (enemiesD != 0) {
               duration += mobs / enemiesD * 20;
            }

            if (self == 1) {
               in.func_70690_d(new PotionEffect(potionE, duration, amp, false, false));
            } else if (self == 2) {
               if (target instanceof EntityParasiteBase) {
                  SRPPotions.applyStackPotion(potionE, target, duration, amp);
               }
            } else if (!(target instanceof EntityParasiteBase)) {
               SRPPotions.applyStackPotion(potionE, target, duration, amp);
            }
         }
      }
   }

   public static void spawnNext(EntityParasiteBase entityin, EntityParasiteBase entityout, boolean effects, boolean thunder) {
      if (!entityin.field_70128_L) {
         if (entityout != null) {
            boolean flag = entityin.func_70027_ad();
            entityin.func_70106_y();
            entityout.func_70012_b(entityin.field_70165_t, entityin.field_70163_u, entityin.field_70161_v, entityin.field_70177_z, entityin.field_70125_A);
            entityout.func_180482_a(entityin.field_70170_p.func_175649_E(new BlockPos(entityout)), null);
            entityout.cannotDespawn(entityin.func_70692_ba());
            if (entityin.func_145818_k_()) {
               entityout.func_96094_a(entityin.func_95999_t());
               entityout.func_174805_g(entityin.func_174833_aM());
            }

            entityin.field_70170_p.func_72838_d(entityout);
            if (entityin instanceof EntityPMalleable && entityout instanceof EntityPMalleable) {
               ((EntityPMalleable)entityout).copyResistancesFrom((EntityPMalleable)entityin);
            }

            if (effects) {
               entityout.particleStatus((byte)7);
            }

            if (thunder && SRPConfig.thunderEnable) {
               entityout.field_70170_p
                  .func_72942_c(
                     new EntityLightningBolt(entityout.field_70170_p, entityout.field_70165_t, entityout.field_70163_u, entityout.field_70161_v, true)
                  );
            }

            if (flag) {
               entityout.func_70606_j(entityout.func_110138_aP() * 0.5F);
               entityout.func_70015_d(8);
            }
         }
      }
   }

   public static void spawnFromList(Entity entityin, String[] out, @Nullable EntityLivingBase target) {
      EntityLiving entityout = (EntityLiving)EntityList.func_188429_b(
         new ResourceLocation(out[entityin.field_70170_p.field_73012_v.nextInt(out.length)]), entityin.field_70170_p
      );
      if (entityout != null) {
         entityout.func_82149_j(entityin);
         entityout.func_180482_a(entityin.field_70170_p.func_175649_E(new BlockPos(entityout)), null);
         entityin.field_70170_p.func_72838_d(entityout);
         if (target != null) {
            entityout.func_70624_b(target);
         }
      }
   }

   public static boolean spawnBiomassFromProjectile(EntityParasiteBase entityin, String[] out, @Nullable EntityLivingBase target) {
      if (!entityin.field_70170_p.field_72995_K) {
         Random rand = new Random();
         int index = rand.nextInt(out.length);
         int limit = 0;
         boolean flag = true;

         while (flag) {
            if (index >= out.length) {
               index = 0;
               limit++;
            }

            if (limit == 2) {
               return false;
            }

            if (out[index] != null) {
               String[] entityC = out[index].split(";");
               double chance = Double.parseDouble(entityC[1]);
               if (rand.nextDouble() <= chance) {
                  EntityCanSummon father = (EntityCanSummon)entityin;
                  int points = Integer.parseInt(entityC[2]);
                  if (father.getTotalParasites() - father.getActualParasites() >= points) {
                     if (target == null) {
                        return false;
                     }

                     Vec3d vec3d = entityin.func_70676_i(1.0F);
                     double d2 = target.field_70165_t - (entityin.field_70165_t + vec3d.field_72450_a);
                     double d3 = target.func_174813_aQ().field_72338_b
                        + target.field_70131_O / 2.0F
                        - (0.5 + entityin.field_70163_u + entityin.field_70131_O / 2.0F);
                     double d4 = target.field_70161_v - (entityin.field_70161_v + vec3d.field_72449_c);
                     EntityProjectileBiomass entityout = new EntityProjectileBiomass(entityin.field_70170_p, entityin, d2, d3, d4);
                     entityout.field_70165_t = entityin.field_70165_t + vec3d.field_72450_a;
                     entityout.field_70163_u = entityin.field_70163_u + entityin.func_70047_e() - 0.2;
                     entityout.field_70161_v = entityin.field_70161_v + vec3d.field_72449_c;
                     entityout.setParasite(entityC[0], points, 4);
                     father.setActualParasites(points);
                     father.addID(entityout.func_145782_y(), points);
                     entityin.field_70170_p.func_72838_d(entityout);
                     flag = false;
                     return true;
                  }

                  index++;
                  continue;
               }
            }

            index++;
         }
      }

      return false;
   }

   public static boolean spawnBiomassFromVomit(EntityParasiteBase entityin, String[] out, @Nullable EntityLivingBase target) {
      if (!entityin.field_70170_p.field_72995_K) {
         Random rand = new Random();
         int index = rand.nextInt(out.length);
         int limit = 0;
         boolean flag = true;

         while (flag) {
            if (index >= out.length) {
               index = 0;
               limit++;
            }

            if (limit == 2) {
               return false;
            }

            if (out[index] != null) {
               String[] entityC = out[index].split(";");
               double chance = Double.parseDouble(entityC[1]);
               if (rand.nextDouble() <= chance) {
                  EntityCanSummon father = (EntityCanSummon)entityin;
                  int points = Integer.parseInt(entityC[2]);
                  if (father.getTotalParasites() - father.getActualParasites() >= points) {
                     EntityBiomass entityout = new EntityBiomass(entityin.field_70170_p, entityin, target);
                     entityout.func_70012_b(
                        entityin.field_70165_t, entityin.field_70163_u, entityin.field_70161_v, entityin.field_70177_z, entityin.field_70125_A
                     );
                     float f19 = MathHelper.func_76126_a(entityin.field_70177_z * (float) (Math.PI / 180.0) - entityin.field_70704_bt * 0.01F);
                     float f14 = (float) (Math.PI / 18);
                     float f16 = MathHelper.func_76134_b(f14);
                     float f4 = MathHelper.func_76134_b(entityin.field_70177_z * (float) (Math.PI / 180.0) - entityin.field_70704_bt * 0.01F);
                     entityout.field_70177_z = entityin.field_70177_z;
                     if (entityout.field_70170_p
                           .func_180495_p(
                              new BlockPos(
                                 entityin.field_70165_t + -1.0 * (f19 * 3.0F * f16),
                                 entityin.field_70163_u + entityin.func_70047_e(),
                                 entityin.field_70161_v - -1.0 * (f4 * 3.0F * f16)
                              )
                           )
                           .func_177230_c()
                        != Blocks.field_150350_a) {
                        entityout.func_70106_y();
                        return false;
                     }

                     entityout.func_70107_b(
                        entityin.field_70165_t + -1.0 * (f19 * 3.0F * f16),
                        entityin.field_70163_u + entityin.func_70047_e(),
                        entityin.field_70161_v - -1.0 * (f4 * 3.0F * f16)
                     );
                     entityout.setFuse(80);
                     entityout.setParasite(entityC[0], points);
                     if (entityin instanceof EntityCanra) {
                        entityout.setSkin(5);
                     } else {
                        entityout.setSkin(6);
                     }

                     father.setActualParasites(points);
                     father.addID(entityout.func_145782_y(), points);
                     if (entityin.func_70027_ad()) {
                        entityout.func_70015_d(8);
                     }

                     entityin.field_70170_p.func_72838_d(entityout);
                     flag = false;
                     return true;
                  }

                  index++;
                  continue;
               }
            }

            index++;
         }
      }

      return false;
   }

   private static boolean getWorldBeckonSpawnLimit(EntityParasiteBase entityin) {
      int count = 0;

      for (Entity entity : entityin.func_130014_f_().field_72996_f) {
         if (entity instanceof EntityParasiteBase) {
            count++;
         }
      }

      int players = entityin.field_70170_p.field_73010_i.size();
      return count < SRPConfig.worldMobCap + players * SRPConfig.worldMobCapPlusPlayer + SRPConfig.worldBeckonSpawnsCap;
   }

   public static boolean spawnBiomassFromBeckon(
      EntityParasiteBase entityin, int stage, EntityLivingBase target, boolean payfather, String[] ground, String[] air
   ) {
      if (!entityin.field_70170_p.field_72995_K) {
         String[] mobListG = ground;
         if (entityin.field_70163_u + 3.0 <= target.field_70163_u) {
            if (stage == 1) {
               return false;
            }

            mobListG = air;
         }

         Random rand = new Random();
         int index = rand.nextInt(mobListG.length);
         int limit = 0;

         for (boolean flag = true; flag; index++) {
            if (index >= mobListG.length) {
               index = 0;
               limit++;
            }

            if (limit == 2) {
               return false;
            }

            if (mobListG[index] != null) {
               String[] entityC = mobListG[index].split(";");
               if (entityC.length != 3) {
                  SRPMain.logger
                     .error(
                        "Malformed string: "
                           + mobListG[index].toString()
                           + " in the beckon spawn pool configuration, safely exiting loop. Did you forget a semicolon?"
                     );
                  return false;
               }

               double chance = Double.parseDouble(entityC[1]);
               EntityCanSummon father = (EntityCanSummon)entityin;
               int points = Integer.parseInt(entityC[2]);
               if (father.getTotalParasites() - father.getActualParasites() < points && payfather) {
                  index++;
               }

               double b = 0.0;
               if (stage == 3) {
                  b = 0.5;
               }

               if (getWorldBeckonSpawnLimit(entityin)) {
                  EntityBiomass entityout = new EntityBiomass(entityin.field_70170_p, entityin, stage, target, payfather);
                  entityout.func_70012_b(
                     entityin.field_70165_t,
                     entityin.field_70163_u + (entityin.func_70047_e() + b),
                     entityin.field_70161_v,
                     entityin.field_70177_z,
                     entityin.field_70125_A
                  );
                  double d0 = (float)entityin.field_70165_t + entityin.field_70170_p.field_73012_v.nextFloat();
                  double d1 = (float)entityin.field_70163_u + entityin.func_70047_e() + entityin.field_70170_p.field_73012_v.nextFloat();
                  double d2 = (float)entityin.field_70161_v + entityin.field_70170_p.field_73012_v.nextFloat();
                  double d3 = d0 - entityin.field_70165_t;
                  double d4 = d1 - entityin.field_70163_u;
                  double d5 = d2 - entityin.field_70161_v;
                  double d6 = MathHelper.func_76133_a(d3 * d3 + d4 * d4 + d5 * d5);
                  d3 /= d6;
                  d4 /= d6;
                  d5 /= d6;
                  double d7 = 0.5 / (d6 / 4.0 + 0.1);
                  d7 *= entityin.field_70170_p.field_73012_v.nextFloat() * entityin.field_70170_p.field_73012_v.nextFloat() + 1.7F;
                  double k = 3.0;
                  if (stage == 3) {
                     k = 5.0;
                  }

                  d3 *= d7 * k;
                  d4 *= d7 * 2.0;
                  d5 = d7 * k;
                  entityout.setMotion(d3, d4, d5, 0.4, 0.5);
                  entityout.setFuse(80);
                  entityout.setParasite(entityC[0], points);
                  entityout.setSkin(stage);
                  if (entityin.func_70027_ad()) {
                     entityout.func_70015_d(8);
                  }

                  entityin.field_70170_p.func_72838_d(entityout);
                  if (payfather) {
                     father.setActualParasites(points);
                     father.addID(entityout.func_145782_y(), points);
                  }

                  flag = false;
                  return true;
               }

               EntityBomb entityAlt = new EntityBomb(entityin.field_70170_p, entityin, false);
               if (entityin.func_130014_f_().field_73012_v.nextInt() < 40) {
                  return false;
               }

               if (entityin.func_70638_az() != null) {
                  entityAlt.func_70012_b(
                     entityin.field_70165_t,
                     entityin.field_70163_u + (entityin.func_70047_e() + b),
                     entityin.field_70161_v,
                     entityin.field_70177_z,
                     entityin.field_70125_A
                  );
                  double d0x = (float)entityin.field_70165_t + entityin.field_70170_p.field_73012_v.nextFloat();
                  double d1x = (float)entityin.field_70163_u + entityin.func_70047_e() + entityin.field_70170_p.field_73012_v.nextFloat();
                  double d2x = (float)entityin.field_70161_v + entityin.field_70170_p.field_73012_v.nextFloat();
                  double d3x = d0x - entityin.field_70165_t;
                  double d4x = d1x - entityin.field_70163_u;
                  double d5x = d2x - entityin.field_70161_v;
                  double d6x = MathHelper.func_76133_a(d3x * d3x + d4x * d4x + d5x * d5x);
                  d3x /= d6x;
                  d4x /= d6x;
                  d5x /= d6x;
                  double d7x = 0.5 / (d6x / 4.0 + 0.1);
                  d7x *= entityin.field_70170_p.field_73012_v.nextFloat() * entityin.field_70170_p.field_73012_v.nextFloat() + 1.7F;
                  double kx = 3.0;
                  if (stage == 3) {
                     kx = 5.0;
                  }

                  d3x *= d7x * kx;
                  d4x *= d7x * 2.0;
                  d5x = d7x * kx;
                  entityAlt.setMotion(d3x, d4x, d5x, 0.4, 0.5);
                  entityAlt.setFuse(60);
                  entityAlt.setStren(0.0F);
                  entityAlt.setSkin(1);
                  entityAlt.setDamage((float)entityin.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111125_b(), 2);
                  entityAlt.field_70125_A -= -20.0F;
                  entityin.field_70170_p.func_72838_d(entityAlt);
                  entityAlt.updateSTR();
                  SRPMain.network
                     .sendToAll(new SRPPacketParticle(entityin.field_70165_t, entityin.field_70163_u, entityin.field_70161_v, 0.5F, 0.5F, (byte)10));
               }
            }
         }
      }

      return false;
   }

   public static void convertEntity(EntityLivingBase entityin, NBTTagCompound tags, boolean ignoreKey, String[] list) {
      if (entityin != null) {
         World world = entityin.field_70170_p;
         if (!world.field_72995_K) {
            if (SRPSaveData.get(entityin.field_70170_p, 105).getEvolutionPhase(entityin.field_71093_bK) < SRPConfigSystems.evolutionFeralNoSim
               || !convertEntityFeral(entityin, tags, true, list)) {
               if (tags.func_74764_b("srpcothimmunity")) {
                  int key = tags.func_74762_e("srpcothimmunity");
                  if (key == 0 && !ignoreKey) {
                     entityin.func_184596_c(SRPPotions.COTH_E);
                     return;
                  }

                  entityin.func_70690_d(new PotionEffect(MobEffects.field_76421_d, 100, 3, false, false));
                  SRPMain.network
                     .sendToAll(
                        new SRPPacketParticle(
                           entityin.field_70165_t, entityin.field_70163_u, entityin.field_70161_v, entityin.field_70130_N, entityin.field_70131_O, (byte)1
                        )
                     );
                  tags.func_74768_a("srpcothimmunity", ++key);
                  if (key < 3 && !ignoreKey) {
                     return;
                  }

                  SRPSaveData dataLol = SRPSaveData.get(entityin.field_70170_p, 103);
                  int goo = SRPConfigSystems.disloCOTHTiers ? dataLol.getCurrentCode(entityin.field_70170_p.field_73011_w.getDimension(), 1) : 0;
                  if (goo != 0) {
                     EntityParasiteBase halo = getRandomFeral(entityin.field_70170_p);
                     if (goo >= SRPConfigSystems.disloCOTHTiersValue1) {
                        halo = getRandomPrimitive(entityin.field_70170_p);
                     }

                     if (goo >= SRPConfigSystems.disloCOTHTiersValue2) {
                        halo = getRandomAdapted(entityin.field_70170_p);
                     }

                     if (goo >= SRPConfigSystems.disloCOTHTiersValue3) {
                        halo = getRandomPure(entityin.field_70170_p);
                     }

                     halo.func_82149_j(entityin);
                     world.func_72900_e(entityin);
                     halo.func_180482_a(world.func_175649_E(new BlockPos(halo)), null);
                     if (entityin.func_145818_k_()) {
                        halo.func_96094_a(entityin.func_95999_t());
                        halo.func_174805_g(entityin.func_174833_aM());
                     }

                     world.func_72838_d(halo);
                     world.func_180498_a(null, 1026, new BlockPos(halo), 0);
                     halo.particleStatus((byte)7);
                     halo.cannotDespawn(SRPConfig.convertedDespawn);
                     if (key >= 10) {
                        halo.func_70690_d(new PotionEffect(SRPPotions.EPEL_E, 600, 0, false, false));
                     }

                     return;
                  }

                  String mobname;
                  try {
                     mobname = EntityList.func_191301_a(entityin).toString();
                  } catch (Exception var21) {
                     SRPMain.logger.log(Level.ERROR, "Problem while converting entity", var21);
                     spawnInsider(entityin, world, tags);
                     return;
                  }

                  boolean flag = true;

                  for (String s : list) {
                     String[] here = s.split(";");

                     try {
                        if (here[0] == null || here[1] == null) {
                           spawnInsider(entityin, world, tags);
                           return;
                        }
                     } catch (Exception var22) {
                        SRPMain.logger.log(Level.ERROR, "Problem while converting entity", var22);
                        spawnInsider(entityin, world, tags);
                        return;
                     }

                     if (here[0].equals(mobname)) {
                        Entity outOne = EntityList.func_188429_b(new ResourceLocation(here[1]), world);
                        if (outOne == null) {
                           spawnInsider(entityin, world, tags);
                           return;
                        }

                        if (outOne instanceof EntityPInfected) {
                           EntityPInfected entityout = (EntityPInfected)outOne;
                           SRPSaveData.get(world, 104).addNumberIDDataSpawn(entityout.getParasiteIDRegister());
                           entityout.func_82149_j(entityin);
                           world.func_72900_e(entityin);
                           entityout.setHost(mobname);
                           entityout.func_180482_a(world.func_175649_E(new BlockPos(entityout)), null);
                           if (entityin.func_145818_k_()) {
                              entityout.func_96094_a(entityin.func_95999_t());
                              entityout.func_174805_g(entityin.func_174833_aM());
                           }

                           world.func_72838_d(entityout);
                           world.func_180498_a(null, 1026, new BlockPos(entityout), 0);
                           if (SRPConfigSystems.generationUse) {
                              entityout.func_70606_j(entityout.func_110143_aJ() * getSimCOTHMod(dataLol, world));
                           }

                           entityout.particleStatus((byte)7);
                           entityout.cannotDespawn(SRPConfig.convertedDespawn);
                           if (key >= 10) {
                              entityout.func_70690_d(new PotionEffect(SRPPotions.EPEL_E, 600, 0, false, false));
                           }

                           AxisAlignedBB axisalignedbb = new AxisAlignedBB(entityout.func_180425_c()).func_186662_g(14.0);

                           for (EntityLivingBase mob : entityout.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb)) {
                              if (mob.func_70644_a(SRPPotions.COTH_E)) {
                                 tags = mob.getEntityData();
                                 if (tags.func_74764_b("srpcothimmunity")) {
                                    key = tags.func_74762_e("srpcothimmunity");
                                    if (key == 1 && mob.func_70660_b(SRPPotions.COTH_E).func_76458_c() > 1) {
                                       tags.func_74768_a("srpcothimmunity", ++key);
                                    }
                                 }
                              }
                           }
                        } else if (outOne instanceof EntityLiving) {
                           EntityLiving entityoutx = (EntityLiving)outOne;
                           entityoutx.func_82149_j(entityin);
                           world.func_72900_e(entityin);
                           entityoutx.func_180482_a(world.func_175649_E(new BlockPos(entityoutx)), null);
                           if (entityin.func_145818_k_()) {
                              entityoutx.func_96094_a(entityin.func_95999_t());
                              entityoutx.func_174805_g(entityin.func_174833_aM());
                           }

                           world.func_72838_d(entityoutx);
                           world.func_180498_a(null, 1026, new BlockPos(entityoutx), 0);
                        }

                        flag = false;
                     }
                  }

                  if (flag && !ignoreKey) {
                     spawnInsider(entityin, world, tags);
                  }
               }
            }
         }
      }
   }

   public static void spawnInsider(EntityLivingBase entity, World world, NBTTagCompound tags) {
      if (SRPConfigMobs.inhooSEnabled && SRPConfigMobs.inhooMEnabled) {
         List<Entity> serverList = world.field_72996_f;
         int count = 0;

         for (Entity value : serverList) {
            if (value instanceof EntityInhooM || value instanceof EntityInhooS) {
               count++;
            }
         }

         if (count > SRPConfig.incompleteCap) {
            world.func_72900_e(entity);
         } else {
            if (tags.func_74764_b("srpcothimmunity")) {
               int key = tags.func_74762_e("srpcothimmunity");
               if (key == 0) {
                  entity.func_184596_c(SRPPotions.COTH_E);
                  return;
               }

               entity.func_70690_d(new PotionEffect(MobEffects.field_76421_d, 100, 3, false, false));
               SRPMain.network
                  .sendToAll(
                     new SRPPacketParticle(
                        entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, entity.field_70130_N, entity.field_70131_O, (byte)1
                     )
                  );
               tags.func_74768_a("srpcothimmunity", ++key);
               if (key < 3) {
                  return;
               }

               EntityParasiteBase out = new EntityInhooS(world);
               float mass = getEntityArea(entity);
               if (mass > 0.517) {
                  out = new EntityInhooM(world);
               }

               out.func_82149_j(entity);
               world.func_72900_e(entity);
               world.func_72838_d(out);
               world.func_180498_a(null, 1026, new BlockPos(out), 0);
               out.particleStatus((byte)7);
               out.cannotDespawn(SRPConfig.convertedDespawn);
               if (SRPConfigSystems.generationUse) {
                  out.func_70606_j(out.func_110143_aJ() * getSimCOTHMod(SRPSaveData.get(world, 102), world));
               }

               int range = 1;
               double i1 = MathHelper.func_76128_c(out.field_70163_u + 0.1);
               double l1 = out.field_70165_t;
               double i2 = out.field_70161_v;
               int counttt = 0;
               int var25 = 2;

               for (int k2 = -1 * range; k2 <= 1 * range && SRPConfig.paraGore; k2++) {
                  for (int l2 = -1 * range; l2 <= 1 * range; l2++) {
                     double i3 = l1 + k2;
                     double l = i2 + l2;
                     BlockPos blockpos = new BlockPos(i3, i1, l);
                     Block block = out.field_70170_p.func_180495_p(blockpos).func_177230_c();
                     Block blockDown = out.field_70170_p.func_180495_p(blockpos.func_177977_b()).func_177230_c();
                     if (block == Blocks.field_150350_a
                        && blockDown != Blocks.field_150350_a
                        && world.func_180495_p(blockpos.func_177977_b()).func_185913_b()
                        && blockDown != SRPBlocks.InfestedStain
                        && out.field_70170_p.field_73012_v.nextInt(4) == 0) {
                        out.field_70170_p.func_175656_a(blockpos, SRPBlocks.goreSim.func_176223_P().func_177226_a(BlockGore.VARIANT, BlockGore.EnumType.FLAT));
                        if (++counttt >= var25) {
                           return;
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private static float getEntityArea(EntityLivingBase entity) {
      return entity.field_70130_N * entity.field_70130_N * entity.field_70131_O;
   }

   private static float getSimCOTHMod(SRPSaveData data, World world) {
      switch (data.getGeneration(world.field_73011_w.getDimension())) {
         case 0:
            return SRPConfigSystems.generationCOTH0;
         case 1:
            return SRPConfigSystems.generationCOTH1;
         case 2:
            return SRPConfigSystems.generationCOTH2;
         case 3:
            return SRPConfigSystems.generationCOTH3;
         case 4:
            return SRPConfigSystems.generationCOTH4;
         case 5:
            return SRPConfigSystems.generationCOTH5;
         default:
            return 1.0F;
      }
   }

   public static boolean convertEntityFeral(EntityLivingBase entityin, NBTTagCompound tags, boolean ignoreKey, String[] list) {
      World world = entityin.field_70170_p;
      if (world.field_72995_K) {
         return false;
      } else if (entityin == null) {
         return false;
      } else {
         if (tags.func_74764_b("srpcothimmunity")) {
            int key = tags.func_74762_e("srpcothimmunity");
            if (key == 0 && !ignoreKey) {
               entityin.func_184596_c(SRPPotions.COTH_E);
               return false;
            }

            entityin.func_70690_d(new PotionEffect(MobEffects.field_76421_d, 100, 3, false, false));
            SRPMain.network
               .sendToAll(
                  new SRPPacketParticle(
                     entityin.field_70165_t, entityin.field_70163_u, entityin.field_70161_v, entityin.field_70130_N, entityin.field_70131_O, (byte)1
                  )
               );
            tags.func_74768_a("srpcothimmunity", ++key);
            if (key < 3 && !ignoreKey) {
               return false;
            }

            String mobname;
            try {
               mobname = EntityList.func_191301_a(entityin).toString();
            } catch (Exception var20) {
               SRPMain.logger.log(Level.ERROR, "Problem while converting entity", var20);
               spawnInsider(entityin, world, tags);
               return false;
            }

            boolean flag = true;

            for (String s : list) {
               String[] here = s.split(";");

               try {
                  if (here[0] == null || here[1] == null) {
                     spawnInsider(entityin, world, tags);
                     return false;
                  }
               } catch (Exception var21) {
                  SRPMain.logger.log(Level.ERROR, "Problem while converting entity", var21);
                  spawnInsider(entityin, world, tags);
                  return false;
               }

               if (here[0].equals(mobname)) {
                  Entity outOne = EntityList.func_188429_b(new ResourceLocation(here[1]), world);
                  if (outOne == null) {
                     spawnInsider(entityin, world, tags);
                     return false;
                  }

                  if (outOne instanceof EntityPInfected) {
                     EntityPFeral gaa = ((EntityPInfected)outOne).getFeral(world);
                     if (gaa != null) {
                        outOne.func_70106_y();
                        gaa.func_82149_j(entityin);
                        world.func_72900_e(entityin);
                        gaa.func_180482_a(world.func_175649_E(new BlockPos(gaa)), null);
                        if (entityin.func_145818_k_()) {
                           gaa.func_96094_a(entityin.func_95999_t());
                           gaa.func_174805_g(entityin.func_174833_aM());
                        }

                        world.func_72838_d(gaa);
                        world.func_180498_a(null, 1026, new BlockPos(gaa), 0);
                        gaa.particleStatus((byte)7);
                        gaa.cannotDespawn(SRPConfig.convertedDespawn);
                     } else {
                        EntityPInfected entityout = (EntityPInfected)outOne;
                        SRPSaveData.get(world, 101).addNumberIDDataSpawn(entityout.getParasiteIDRegister());
                        entityout.func_82149_j(entityin);
                        world.func_72900_e(entityin);
                        entityout.setHost(mobname);
                        entityout.func_180482_a(world.func_175649_E(new BlockPos(entityout)), null);
                        if (entityin.func_145818_k_()) {
                           entityout.func_96094_a(entityin.func_95999_t());
                           entityout.func_174805_g(entityin.func_174833_aM());
                        }

                        world.func_72838_d(entityout);
                        world.func_180498_a(null, 1026, new BlockPos(entityout), 0);
                        entityout.particleStatus((byte)7);
                        entityout.cannotDespawn(SRPConfig.convertedDespawn);
                        if (key >= 10) {
                           entityout.func_70690_d(new PotionEffect(SRPPotions.EPEL_E, 600, 0, false, false));
                        }

                        AxisAlignedBB axisalignedbb = new AxisAlignedBB(entityout.func_180425_c()).func_186662_g(14.0);

                        for (EntityLivingBase mob : entityout.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb)) {
                           if (mob.func_70644_a(SRPPotions.COTH_E)) {
                              tags = mob.getEntityData();
                              if (tags.func_74764_b("srpcothimmunity")) {
                                 key = tags.func_74762_e("srpcothimmunity");
                                 if (key == 1 && mob.func_70660_b(SRPPotions.COTH_E).func_76458_c() > 1) {
                                    tags.func_74768_a("srpcothimmunity", ++key);
                                 }
                              }
                           }
                        }
                     }

                     return true;
                  }

                  if (outOne instanceof EntityLiving) {
                     EntityLiving entityoutx = (EntityLiving)outOne;
                     entityoutx.func_82149_j(entityin);
                     world.func_72900_e(entityin);
                     entityoutx.func_180482_a(world.func_175649_E(new BlockPos(entityoutx)), null);
                     if (entityin.func_145818_k_()) {
                        entityoutx.func_96094_a(entityin.func_95999_t());
                        entityoutx.func_174805_g(entityin.func_174833_aM());
                     }

                     world.func_72838_d(entityoutx);
                     world.func_180498_a(null, 1026, new BlockPos(entityoutx), 0);
                     return true;
                  }

                  flag = false;
               }
            }

            if (flag && !ignoreKey) {
               spawnInsider(entityin, world, tags);
            }
         }

         return false;
      }
   }

   public static boolean hijackEntity(EntityLivingBase entityin, String[] list) {
      if (entityin == null) {
         return false;
      } else {
         World world = entityin.field_70170_p;
         if (world.field_72995_K) {
            return false;
         } else {
            String mobname;
            try {
               mobname = EntityList.func_191301_a(entityin).toString();
            } catch (Exception var12) {
               SRPMain.logger.log(Level.ERROR, "Problem while converting entity", var12);
               return false;
            }

            boolean flag = true;

            for (String s : list) {
               String[] here = s.split(";");

               try {
                  if (here[0] == null || here[1] == null) {
                     return false;
                  }
               } catch (Exception var13) {
                  SRPMain.logger.log(Level.ERROR, "Problem while converting entity", var13);
                  return false;
               }

               if (here[0].equals(mobname)) {
                  Entity outOne = EntityList.func_188429_b(new ResourceLocation(here[1]), world);
                  if (outOne == null) {
                     return false;
                  }

                  if (outOne instanceof EntityPHijacked) {
                     EntityPHijacked entityout = (EntityPHijacked)outOne;
                     SRPSaveData.get(world, 98).addNumberIDDataSpawn(entityout.getParasiteIDRegister());
                     entityout.func_82149_j(entityin);
                     world.func_72900_e(entityin);
                     entityout.func_180482_a(world.func_175649_E(new BlockPos(entityout)), null);
                     if (entityin.func_145818_k_()) {
                        entityout.func_96094_a(entityin.func_95999_t());
                        entityout.func_174805_g(entityin.func_174833_aM());
                     }

                     world.func_72838_d(entityout);
                     world.func_180498_a(null, 1026, new BlockPos(entityout), 0);
                     entityout.particleStatus((byte)7);
                     entityout.cannotDespawn(SRPConfig.convertedDespawn);
                  } else if (outOne instanceof EntityLiving) {
                     EntityLiving entityout = (EntityLiving)outOne;
                     entityout.func_82149_j(entityin);
                     world.func_72900_e(entityin);
                     entityout.func_180482_a(world.func_175649_E(new BlockPos(entityout)), null);
                     if (entityin.func_145818_k_()) {
                        entityout.func_96094_a(entityin.func_95999_t());
                        entityout.func_174805_g(entityin.func_174833_aM());
                     }

                     world.func_72838_d(entityout);
                     world.func_180498_a(null, 1026, new BlockPos(entityout), 0);
                  }

                  flag = false;
               }
            }

            return false;
         }
      }
   }

   public static BlockPos getFloor(World worldIn, BlockPos pos, int loop) {
      if (loop <= 0) {
         return null;
      } else {
         loop--;
         if (worldIn.func_180495_p(pos).func_177230_c() == Blocks.field_150350_a) {
            return worldIn.func_180495_p(pos.func_177977_b()).func_177230_c() != Blocks.field_150350_a ? pos : getFloor(worldIn, pos.func_177977_b(), loop);
         } else {
            return getFloor(worldIn, pos.func_177984_a(), loop);
         }
      }
   }

   public static BlockPos getFloorBuilding(World worldIn, BlockPos pos, int loop) {
      if (loop <= 0) {
         return null;
      } else {
         loop--;
         if (!worldIn.func_180495_p(pos).func_185913_b()) {
            return worldIn.func_180495_p(pos.func_177977_b()).func_185913_b()
                  && !(worldIn.func_180495_p(pos.func_177977_b()).func_177230_c() instanceof BlockBush)
                  && !(worldIn.func_180495_p(pos.func_177977_b()).func_177230_c() instanceof BlockLeaves)
                  && !(worldIn.func_180495_p(pos.func_177977_b()).func_177230_c() instanceof BlockLog)
               ? pos
               : getFloorBuilding(worldIn, pos.func_177977_b(), loop);
         } else {
            return !(worldIn.func_180495_p(pos).func_177230_c() instanceof BlockBush)
                  && !(worldIn.func_180495_p(pos).func_177230_c() instanceof BlockLeaves)
                  && !(worldIn.func_180495_p(pos).func_177230_c() instanceof BlockLog)
               ? getFloorBuilding(worldIn, pos.func_177984_a(), loop)
               : getFloorBuilding(worldIn, pos.func_177979_c(1), loop);
         }
      }
   }

   public static boolean spawnTurrets(EntityLivingBase entityin, int range, byte type, int stage) {
      if (stage <= 2) {
         return false;
      } else if (entityin.field_70170_p.func_175659_aa() == EnumDifficulty.PEACEFUL) {
         return false;
      } else {
         Random rand = new Random();
         World world = entityin.field_70170_p;
         double randomx = rand.nextInt(range);
         double randomz = rand.nextInt(range);
         double negative = rand.nextInt(2);
         if (negative == 0.0) {
            randomx *= -1.0;
         }

         negative = rand.nextInt(2);
         if (negative == 0.0) {
            randomz *= -1.0;
         }

         int index = 5;
         int limit = 0;

         for (boolean flag = true; flag; limit++) {
            if (limit >= 5) {
               return false;
            }

            BlockPos floor = getFloor(world, new BlockPos(entityin.field_70165_t + randomx, entityin.field_70163_u, entityin.field_70161_v + randomz), 5);
            if (floor != null && world.func_180495_p(floor.func_177977_b()).func_177230_c() == SRPBlocks.InfestedStain) {
               int flag2 = 0;
               AxisAlignedBB axisalignedbb = new AxisAlignedBB(
                     floor.func_177958_n(),
                     floor.func_177956_o(),
                     floor.func_177952_p(),
                     floor.func_177958_n() + 1,
                     floor.func_177956_o() + 1,
                     floor.func_177952_p() + 1
                  )
                  .func_72314_b(42.0, 5.0, 42.0);

               for (EntityParasiteBase mob : world.func_72872_a(EntityParasiteBase.class, axisalignedbb)) {
                  if (mob.func_70089_S() && mob.getParasiteType() == 40) {
                     flag2++;
                  }
               }

               if (flag2 >= 3) {
                  return false;
               }

               if (type == 1) {
                  if (!SRPConfigMobs.unvoEnabled) {
                     return false;
                  }

                  EntityUnvo out = new EntityUnvo(world);
                  out.func_70012_b(floor.func_177958_n(), floor.func_177956_o(), floor.func_177952_p(), 0.0F, 0.0F);
                  world.func_72838_d(out);
                  out.func_70624_b(entityin);
                  return true;
               }

               if (type == 2) {
                  if (!SRPConfigMobs.tonroEnabled) {
                     return false;
                  }

                  EntityTonro out = new EntityTonro(world);
                  out.func_70012_b(floor.func_177958_n(), floor.func_177956_o(), floor.func_177952_p(), 0.0F, 0.0F);
                  world.func_72838_d(out);
                  out.func_70624_b(entityin);
                  return true;
               }
            }

            randomx = rand.nextInt(range);
            randomz = rand.nextInt(range);
            negative = rand.nextInt(2);
            if (negative == 0.0) {
               randomx *= -1.0;
            }

            negative = rand.nextInt(2);
            if (negative == 0.0) {
               randomz *= -1.0;
            }
         }

         return false;
      }
   }

   public static void alertAllPlayerDim(World worldIn, String message, int warning) {
      if (worldIn != null) {
         List<EntityPlayer> playerEntityList = worldIn.field_73010_i;
         SRPMain.network.sendToDimension(new SRPPacketMovingSound(warning), worldIn.field_73011_w.getDimension());
         if (!message.equals("")) {
            for (EntityPlayer entityPlayer : playerEntityList) {
               entityPlayer.func_145747_a(new TextComponentString(message));
            }
         }

         if (warning == -7 && message.equals("Phase decreased")) {
            for (Entity entity : worldIn.field_72996_f) {
               if (entity instanceof EntityParasiteBase) {
                  ((EntityParasiteBase)entity).func_70690_d(new PotionEffect(SRPPotions.RAGE_E, 2400, 1, false, false));
               }
            }
         }
      }
   }

   public static void alertAllPlayerSer(World w, String message) {
      if (w != null) {
         for (EntityPlayerMP entityPlayerMP : w.func_73046_m().func_184103_al().func_181057_v()) {
            entityPlayerMP.func_145747_a(new TextComponentString(message));
         }
      }
   }

   public static void alertAllPlayerSer(World w, String message, int warning) {
      if (w != null) {
         List<EntityPlayerMP> playerEntityList = w.func_73046_m().func_184103_al().func_181057_v();
         SRPMain.network.sendToServer(new SRPPacketMovingSound(warning));

         for (EntityPlayerMP entityPlayerMP : playerEntityList) {
            entityPlayerMP.func_145747_a(new TextComponentString(message));
         }
      }
   }

   public static boolean spawnFromBlock(World world, String[] out, int range, BlockPos pos) {
      if (!world.field_72995_K) {
         List<Entity> serverList = world.field_72996_f;
         int count = 0;
         int tenn = 0;

         for (int x = 0; x < serverList.size(); x++) {
            if (serverList.get(x) instanceof EntityParasiteBase) {
               count++;
               if (serverList.get(x) instanceof EntityTenn) {
                  tenn++;
               }

               if (count > SRPConfig.worldMobCap) {
                  return false;
               }

               if (tenn > 5) {
                  return false;
               }
            }
         }

         Random rand = new Random();
         double xx = pos.func_177958_n();
         double y = pos.func_177956_o();
         double z = pos.func_177952_p();
         double randomx = rand.nextInt(range);
         double randomz = rand.nextInt(range);
         double negative = rand.nextInt(2);
         if (negative == 0.0) {
            randomx *= -1.0;
         }

         negative = rand.nextInt(2);
         if (negative == 0.0) {
            randomz *= -1.0;
         }

         int index = rand.nextInt(out.length);
         int limit = 0;

         for (boolean flag = true; flag; index++) {
            if (index >= out.length) {
               index = 0;
               limit++;
            }

            if (limit == 2) {
               return false;
            }

            if (out[index] != null) {
               String[] entityC = out[index].split(";");
               double chance = Double.parseDouble(entityC[1]);
               if (rand.nextDouble() <= chance) {
                  BlockPos helper = getFloor(world, new BlockPos(xx + randomx, y, z + randomz), 3);
                  if (helper != null) {
                     EntityLiving entityout = (EntityLiving)EntityList.func_188429_b(new ResourceLocation(entityC[0]), world);
                     if (entityout == null) {
                        return false;
                     }

                     entityout.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(16.0);
                     entityout.func_70012_b(helper.func_177958_n(), helper.func_177956_o(), helper.func_177952_p(), 0.0F, 0.0F);
                     entityout.func_180482_a(world.func_175649_E(helper), null);
                     if (entityout instanceof EntityKol) {
                        EntityKol kol = (EntityKol)entityout;
                        SRPWorldData data = SRPWorldData.get(world);
                        BlockPos origin = data.nearestColonyPosition(helper, false);
                        if (origin == null) {
                           return false;
                        }

                        kol.setTask(origin, data.getColonyDistanceSpreadByPosition(origin, false));
                     }

                     world.func_72838_d(entityout);
                     flag = false;
                     return true;
                  }

                  randomx = rand.nextInt(range);
                  randomz = rand.nextInt(range);
                  negative = rand.nextInt(2);
                  if (negative == 0.0) {
                     randomx *= -1.0;
                  }

                  negative = rand.nextInt(2);
                  if (negative == 0.0) {
                     randomz *= -1.0;
                  }
               }
            }
         }
      }

      return false;
   }

   public static void spawnBeckon(World world, DamageSource cause, EntityParasiteBase in) {
      if (!(in.field_70130_N <= 1.0F) || !(in.field_70131_O <= 1.0F)) {
         if (SRPConfigSystems.rsEnabled) {
            List<Entity> serverList = world.field_72996_f;
            int count = 0;

            for (Entity entity : serverList) {
               if (entity instanceof EntityPBeckon) {
                  if (++count > SRPConfig.nexusVenkrolCap || in.func_70068_e(entity) < SRPConfig.nexusVenkrolDis * SRPConfig.nexusVenkrolDis) {
                     return;
                  }
               }
            }

            SRPWorldData data = SRPWorldData.get(world);
            if (SRPConfigSystems.rsPlayer) {
               if (cause.func_76346_g() instanceof EntityPlayer) {
                  if (SRPConfigSystems.useEvolution) {
                     spawnBeckonE(data, world, in);
                  } else {
                     spawnBeckonNE(data, SRPConfigSystems.rschance, world, in);
                  }
               }
            } else if (SRPConfigSystems.useEvolution) {
               spawnBeckonE(data, world, in);
            } else {
               spawnBeckonNE(data, SRPConfigSystems.rschance, world, in);
            }
         }
      }
   }

   public static void spawnBeckonNE(SRPWorldData data, double chance, World world, EntityParasiteBase in) {
      long worldT = world.func_82737_E();
      long seconds = (worldT - SRPAttributes.lastTimeD1) / 20L;
      Random rand = new Random();
      if (rand.nextDouble() < chance && SRPConfigSystems.rsCooldown < Math.abs(seconds)) {
         if (SRPConfigWorld.originActivated && data.nearestInfectionValue(in.func_180425_c(), false) == -1) {
            return;
         }

         if (ParasiteSummon.SummonM(in, new String[]{getRSColony(data)}, 5, 10, in.func_70638_az())) {
            if (SRPConfigSystems.rsSounds) {
               if (SRPConfigSystems.disloGrowlNoise) {
                  if (SRPSaveData.get(world, 94).getCurrentCode(world.field_73011_w.getDimension(), 15) == 0) {
                     in.func_184185_a(SRPSounds.VENKROLSI, 4.0F, 1.0F);
                  }
               } else {
                  in.func_184185_a(SRPSounds.VENKROLSI, 4.0F, 1.0F);
               }
            }

            SRPAttributes.lastTimeD1 = worldT;
         }
      }
   }

   public static String getRSColony(SRPWorldData data) {
      if (SRPConfigWorld.coloniesActivated) {
         int totalColonyPoints = data.totalColonyPoints(0);
         double bonus = totalColonyPoints / SRPConfigWorld.colonyExtraRSChancePoint * SRPConfigWorld.colonyExtraRSChanceValue;
         if (bonus > 2.0) {
            return "srparasites:beckon_siii;1;1";
         }

         if (bonus > 1.0) {
            return "srparasites:beckon_sii;1;1";
         }
      }

      return "srparasites:beckon_si;1;1";
   }

   public static void spawnBeckonE(SRPWorldData data, World world, EntityParasiteBase in) {
      switch (SRPSaveData.get(world, 93).getEvolutionPhase(world.field_73011_w.getDimension())) {
         case 1:
            spawnBeckonNE(data, SRPConfigSystems.reinforcementSystemChanceOne, world, in);
            break;
         case 2:
            spawnBeckonNE(data, SRPConfigSystems.reinforcementSystemChanceTwo, world, in);
            break;
         case 3:
            spawnBeckonNE(data, SRPConfigSystems.reinforcementSystemChanceThree, world, in);
            break;
         case 4:
            spawnBeckonNE(data, SRPConfigSystems.reinforcementSystemChanceFour, world, in);
            break;
         case 5:
            spawnBeckonNE(data, SRPConfigSystems.reinforcementSystemChanceFive, world, in);
            break;
         case 6:
            spawnBeckonNE(data, SRPConfigSystems.reinforcementSystemChanceSix, world, in);
            break;
         case 7:
            spawnBeckonNE(data, SRPConfigSystems.reinforcementSystemChanceSeven, world, in);
            break;
         case 8:
            spawnBeckonNE(data, SRPConfigSystems.reinforcementSystemChanceEight, world, in);
            break;
         case 9:
            spawnBeckonNE(data, SRPConfigSystems.reinforcementSystemChanceNine, world, in);
            break;
         case 10:
            spawnBeckonNE(data, SRPConfigSystems.reinforcementSystemChanceTen, world, in);
      }
   }

   public static double getRSchance(World world) {
      switch (SRPSaveData.get(world, 92).getEvolutionPhase(world.field_73011_w.getDimension())) {
         case 1:
            return SRPConfigSystems.reinforcementSystemChanceOne;
         case 2:
            return SRPConfigSystems.reinforcementSystemChanceTwo;
         case 3:
            return SRPConfigSystems.reinforcementSystemChanceThree;
         case 4:
            return SRPConfigSystems.reinforcementSystemChanceFour;
         case 5:
            return SRPConfigSystems.reinforcementSystemChanceFive;
         case 6:
            return SRPConfigSystems.reinforcementSystemChanceSix;
         case 7:
            return SRPConfigSystems.reinforcementSystemChanceSeven;
         case 8:
            return SRPConfigSystems.reinforcementSystemChanceEight;
         case 9:
            return SRPConfigSystems.reinforcementSystemChanceNine;
         case 10:
            return SRPConfigSystems.reinforcementSystemChanceTen;
         default:
            return 0.0;
      }
   }

   public static boolean alertOthers(EntityParasiteBase pin, EntityLivingBase target, World world, int loop) {
      return false;
   }

   public static void leaveScent(World world, DamageSource cause, EntityParasiteBase in) {
      if (SRPConfigSystems.useScent) {
         if (SRPConfigSystems.scentPlayer) {
            if (!(cause.func_76346_g() instanceof EntityPlayer)) {
               return;
            }
         } else if (!(cause.func_76346_g() instanceof EntityLivingBase)) {
            return;
         }

         if (!(world.field_73012_v.nextDouble() < SRPConfigSystems.scentDeathSpawning)) {
            if (!SRPConfigSystems.useEvolution
               || in.getPhaseCreated() >= SRPConfigSystems.evolutionOneMind
               || in.getLevelCreated() >= SRPConfigSystems.deveOnemindUse) {
               List<Entity> serverList = world.field_72996_f;
               int count = 0;

               for (Entity entity : serverList) {
                  if (entity instanceof EntityParasiticScent) {
                     count++;
                  }
               }

               if (count <= SRPConfigSystems.scentCap) {
                  AxisAlignedBB axisalignedbb = new AxisAlignedBB(
                        in.field_70165_t, in.field_70163_u, in.field_70161_v, in.field_70165_t + 1.0, in.field_70163_u + 1.0, in.field_70161_v + 1.0
                     )
                     .func_186662_g(64.0);

                  for (EntityParasiticScent mob : world.func_72872_a(EntityParasiticScent.class, axisalignedbb)) {
                     if (in.func_70685_l(mob)) {
                        mob.increaseDanger(in.getCCDeathValue(), true);
                        mob.increaseActivity(1, true);
                        mob.setScentLife(mob.getScentLife() + 20 * SRPConfigSystems.scentLifeDeath);
                        mob.setScentReaction(getScentReactionBonus(in.getPhaseCreated()), false);
                        mob.setTargetToKill((EntityLivingBase)cause.func_76346_g(), true);
                        if (cause.func_76346_g() instanceof EntityPlayer) {
                           Entity source = cause.func_76346_g();
                           if (source instanceof EntityPlayer) {
                              ((EntityPlayer)source).func_146105_b(new TextComponentTranslation("srp.msg.scent.closest_notified", new Object[0]), true);
                           }
                        }

                        return;
                     }
                  }

                  axisalignedbb = new AxisAlignedBB(
                        in.field_70165_t, in.field_70163_u, in.field_70161_v, in.field_70165_t + 1.0, in.field_70163_u + 1.0, in.field_70161_v + 1.0
                     )
                     .func_186662_g(64.0);
                  List<Entity> moblist2 = world.func_72872_a(EntityParasiteBase.class, axisalignedbb);
                  int dangerValue = in.getCCDeathValue() + getScentBonus(in.getPhaseCreated());
                  if (moblist2.size() <= 3 && in.getPhaseCreated() >= 0 && in.getCCDeathValue() > 2) {
                     EntityParasiticScent nut = new EntityParasiticScent(world, 0, (EntityLivingBase)cause.func_76346_g());
                     nut.func_82149_j(cause.func_76346_g());
                     nut.setScentLife(SRPConfigSystems.scentLifeObserver * 20);
                     nut.increaseDanger(dangerValue, true);
                     nut.setScentReaction(getScentReactionBonus(in.getPhaseCreated()), false);
                     world.func_72838_d(nut);
                     nut.warnPlayers(I18n.func_74838_a("srp.msg.scent.deployed_area"));
                  }
               }
            }
         }
      }
   }

   public static int getScentBonus(byte in) {
      int q = 1;
      if (SRPConfigSystems.useEvolution) {
         switch (in) {
            case 0:
               q = SRPConfigSystems.phaseScentBonusZero;
               break;
            case 1:
               q = SRPConfigSystems.phaseScentBonusOne;
               break;
            case 2:
               q = SRPConfigSystems.phaseScentBonusTwo;
               break;
            case 3:
               q = SRPConfigSystems.phaseScentBonusThree;
               break;
            case 4:
               q = SRPConfigSystems.phaseScentBonusFour;
               break;
            case 5:
               q = SRPConfigSystems.phaseScentBonusFive;
               break;
            case 6:
               q = SRPConfigSystems.phaseScentBonusSix;
               break;
            case 7:
               q = SRPConfigSystems.phaseScentBonusSeven;
               break;
            case 8:
               q = SRPConfigSystems.phaseScentBonusEight;
         }
      }

      return q;
   }

   public static byte getScentReactionBonus(byte in) {
      byte q = SRPConfigSystems.scentGoActive;
      if (SRPConfigSystems.useEvolution) {
         switch (in) {
            case 0:
               q = SRPConfigSystems.phaseScentReactionZero;
               break;
            case 1:
               q = SRPConfigSystems.phaseScentReactionOne;
               break;
            case 2:
               q = SRPConfigSystems.phaseScentReactionTwo;
               break;
            case 3:
               q = SRPConfigSystems.phaseScentReactionThree;
               break;
            case 4:
               q = SRPConfigSystems.phaseScentReactionFour;
               break;
            case 5:
               q = SRPConfigSystems.phaseScentReactionFive;
               break;
            case 6:
               q = SRPConfigSystems.phaseScentReactionSix;
               break;
            case 7:
               q = SRPConfigSystems.phaseScentReactionSeven;
               break;
            case 8:
               q = SRPConfigSystems.phaseScentReactionEight;
         }
      }

      return q;
   }

   public static void checkColony(World world, DamageSource cause, EntityPMalleable in) {
      if (!in.func_70027_ad() && SRPConfigWorld.coloniesActivated) {
         if (ParasiteEventWorld.numberofColonies(world) > 0) {
            double chance = 0.0;
            if (in.func_70644_a(SRPPotions.LINK_E)) {
               chance = (in.func_70660_b(SRPPotions.LINK_E).func_76458_c() + 1) * SRPConfigSystems.adapsChance;
            }

            if (ParasiteEventWorld.rangeOfColony(world, in.func_180425_c(), true) != null || world.field_73012_v.nextDouble() < chance) {
               SRPWorldData data = SRPWorldData.get(world);
               if (in.colonySpawned) {
                  in.removeCommonDamage(data.getMostCommonDamageS(), data.getMostCommonDamageI());
               }

               String da = in.getMostCommonDamage();
               if (da == null) {
                  return;
               }

               data.addGlobalResistance(da);
               SRPMain.network
                  .sendToAll(new SRPPacketParticle(in.field_70165_t, in.field_70163_u, in.field_70161_v, in.field_70130_N, in.field_70131_O, (byte)4));
            }
         }
      }
   }

   public static SRPExplosion createExplosion(World worldIn, @Nullable Entity entityIn, double x, double y, double z, float strength, boolean isSmoking) {
      SRPExplosion explosion = new SRPExplosion(worldIn, entityIn, x, y, z, strength, false, isSmoking);
      if (ForgeEventFactory.onExplosionStart(worldIn, explosion)) {
         return explosion;
      } else {
         explosion.func_77278_a();
         explosion.func_77279_a(false);
         return explosion;
      }
   }

   public static boolean teleportDigging(EntityParasiteBase in, float maxHardness, BlockPos posIn, int range, int mini) {
      if (!in.func_70644_a(MobEffects.field_76421_d)) {
         in.func_70690_d(new PotionEffect(MobEffects.field_76421_d, 80, 4, false, false));
         return false;
      } else {
         Random rand = new Random();
         double x = posIn.func_177958_n();
         double y = posIn.func_177956_o();
         double z = posIn.func_177952_p();
         double randomx = rand.nextInt(range) + mini;
         double randomz = rand.nextInt(range) + mini;
         double negative = rand.nextInt(2);
         if (negative == 0.0) {
            randomx *= -1.0;
         }

         negative = rand.nextInt(2);
         if (negative == 0.0) {
            randomz *= -1.0;
         }

         int limit = 0;

         for (boolean flag = true; flag; limit++) {
            if (limit >= 5) {
               return false;
            }

            BlockPos pos = new BlockPos(x + randomx, y, z + randomz);
            pos = getFloor(in.field_70170_p, pos, 5);
            if (pos != null && in.field_70170_p.func_180495_p(pos.func_177977_b()).func_185917_h()) {
               float bHard = 0.0F;

               for (int i = 1; i < 4; i++) {
                  IBlockState state = in.field_70170_p.func_180495_p(pos.func_177979_c(i));
                  float atm = state.func_185887_b(in.field_70170_p, pos.func_177979_c(i));
                  if (atm <= 0.0F) {
                     return false;
                  }

                  bHard += atm;
               }

               if (bHard >= maxHardness) {
                  return false;
               }

               AxisAlignedBB axisalignedbb = new AxisAlignedBB(
                     pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), pos.func_177958_n() + 1, pos.func_177956_o() + 1, pos.func_177952_p() + 1
                  )
                  .func_186662_g(1.0);
               List<EntityLivingBase> moblist = in.field_70170_p.func_72872_a(EntityNak.class, axisalignedbb);
               if (moblist.isEmpty()) {
                  in.func_70012_b(pos.func_177958_n() + 0.5, pos.func_177956_o(), pos.func_177952_p() + 0.5, in.field_70177_z, in.field_70125_A);
                  flag = false;
                  return true;
               }
            }

            randomx = rand.nextInt(range) + mini;
            randomz = rand.nextInt(range) + mini;
            negative = rand.nextInt(2);
            if (negative == 0.0) {
               randomx *= -1.0;
            }

            negative = rand.nextInt(2);
            if (negative == 0.0) {
               randomz *= -1.0;
            }
         }

         return false;
      }
   }

   public static EntityParasiteBase getRandomAssimilated(World world) {
      switch (world.field_73012_v.nextInt(9)) {
         case 0:
            if (SRPConfigMobs.infbearEnabled) {
               return new EntityInfBear(world);
            }
            break;
         case 1:
            if (SRPConfigMobs.infcowEnabled) {
               return new EntityInfCow(world);
            }
            break;
         case 2:
            if (SRPConfigMobs.infendermanEnabled) {
               return new EntityInfEnderman(world);
            }
            break;
         case 3:
            if (SRPConfigMobs.infhorseEnabled) {
               return new EntityInfHorse(world);
            }
            break;
         case 4:
            if (SRPConfigMobs.infhumanEnabled) {
               return new EntityInfHuman(world);
            }
            break;
         case 5:
            if (SRPConfigMobs.infpigEnabled) {
               return new EntityInfPig(world);
            }
            break;
         case 6:
            if (SRPConfigMobs.infsheepEnabled) {
               return new EntityInfSheep(world);
            }
            break;
         case 7:
            if (SRPConfigMobs.infvillagerEnabled) {
               return new EntityInfVillager(world);
            }
            break;
         case 8:
            if (SRPConfigMobs.infwolfEnabled) {
               return new EntityInfWolf(world);
            }
      }

      return null;
   }

   public static EntityParasiteBase getRandomFeral(World world) {
      switch (world.field_73012_v.nextInt(9)) {
         case 0:
            if (SRPConfigMobs.ferbearEnabled) {
               return new EntityFerBear(world);
            }
            break;
         case 1:
            if (SRPConfigMobs.fercowEnabled) {
               return new EntityFerCow(world);
            }
            break;
         case 2:
            if (SRPConfigMobs.ferendermanEnabled) {
               return new EntityFerEnderman(world);
            }
            break;
         case 3:
            if (SRPConfigMobs.ferhorseEnabled) {
               return new EntityFerHorse(world);
            }
            break;
         case 4:
            if (SRPConfigMobs.ferhumanEnabled) {
               return new EntityFerHuman(world);
            }
            break;
         case 5:
            if (SRPConfigMobs.ferpigEnabled) {
               return new EntityFerPig(world);
            }
            break;
         case 6:
            if (SRPConfigMobs.fersheepEnabled) {
               return new EntityFerSheep(world);
            }
            break;
         case 7:
            if (SRPConfigMobs.fervillagerEnabled) {
               return new EntityFerVillager(world);
            }
            break;
         case 8:
            if (SRPConfigMobs.ferwolfEnabled) {
               return new EntityFerWolf(world);
            }
      }

      return null;
   }

   public static EntityParasiteBase getRandomAssimara(World world) {
      switch (world.field_73012_v.nextInt(9)) {
         case 1:
            if (SRPConfigMobs.fercowEnabled) {
               return new EntitySpeCow(world);
            }
            break;
         case 2:
            if (SRPConfigMobs.ferendermanEnabled) {
               return new EntitySpeEnderman(world);
            }
         case 3:
         case 5:
         case 6:
         default:
            break;
         case 4:
            if (SRPConfigMobs.ferhumanEnabled) {
               return new EntitySpeHuman(world);
            }
            break;
         case 7:
            if (SRPConfigMobs.fervillagerEnabled) {
               return new EntitySpeVillager(world);
            }
      }

      return null;
   }

   public static EntityParasiteBase getRandomPrimitive(World world) {
      switch (world.field_73012_v.nextInt(11)) {
         case 0:
            if (SRPConfigMobs.emanaEnabled) {
               return new EntityEmana(world);
            }
            break;
         case 1:
            if (SRPConfigMobs.canraEnabled) {
               return new EntityCanra(world);
            }
            break;
         case 2:
            if (SRPConfigMobs.zetmoEnabled) {
               return new EntityBano(world);
            }
            break;
         case 3:
            if (SRPConfigMobs.shycoEnabled) {
               return new EntityShyco(world);
            }
            break;
         case 4:
            if (SRPConfigMobs.arachnidaEnabled) {
               return new EntityRanrac(world);
            }
            break;
         case 5:
            if (SRPConfigMobs.noglaEnabled) {
               return new EntityNogla(world);
            }
            break;
         case 6:
            if (SRPConfigMobs.hullEnabled) {
               return new EntityHull(world);
            }
            break;
         case 7:
            if (SRPConfigMobs.ikiEnabled) {
               return new EntityIki(world);
            }
            break;
         case 8:
            if (SRPConfigMobs.wymoEnabled) {
               return new EntityWymo(world);
            }
            break;
         case 9:
            if (SRPConfigMobs.zaaEnabled) {
               return new EntityZaa(world);
            }
            break;
         case 10:
            if (SRPConfigMobs.gimEnabled) {
               return new EntityGim(world);
            }
      }

      return null;
   }

   public static EntityParasiteBase getRandomAdapted(World world) {
      switch (world.field_73012_v.nextInt(10)) {
         case 0:
            if (SRPConfigMobs.emanaEnabled) {
               return new EntityEmanaAdapted(world);
            }
            break;
         case 1:
            if (SRPConfigMobs.canraEnabled) {
               return new EntityCanraAdapted(world);
            }
            break;
         case 2:
            if (SRPConfigMobs.zetmoEnabled) {
               return new EntityBanoAdapted(world);
            }
            break;
         case 3:
            if (SRPConfigMobs.shycoEnabled) {
               return new EntityShycoAdapted(world);
            }
            break;
         case 4:
            if (SRPConfigMobs.arachnidaEnabled) {
               return new EntityRanracAdapted(world);
            }
            break;
         case 5:
            if (SRPConfigMobs.noglaEnabled) {
               return new EntityNoglaAdapted(world);
            }
            break;
         case 6:
            if (SRPConfigMobs.hullEnabled) {
               return new EntityHullAdapted(world);
            }
            break;
         case 7:
            if (SRPConfigMobs.wymoEnabled) {
               return new EntityWymoAdapted(world);
            }
            break;
         case 8:
            if (SRPConfigMobs.zaaEnabled) {
               return new EntityZaaAdapted(world);
            }
            break;
         case 9:
            if (SRPConfigMobs.gimEnabled) {
               return new EntityGimAdapted(world);
            }
      }

      return null;
   }

   public static EntityParasiteBase getRandomPure(World world) {
      switch (world.field_73012_v.nextInt(7)) {
         case 0:
            if (SRPConfigMobs.alafhaEnabled) {
               return new EntityAlafha(world);
            }
            break;
         case 1:
            if (SRPConfigMobs.angedEnabled) {
               return new EntityAnged(world);
            }
            break;
         case 2:
            if (SRPConfigMobs.esorEnabled) {
               return new EntityEsor(world);
            }
            break;
         case 3:
            if (SRPConfigMobs.flogEnabled) {
               return new EntityFlog(world);
            }
            break;
         case 4:
            if (SRPConfigMobs.ganroEnabled) {
               return new EntityGanro(world);
            }
            break;
         case 5:
            if (SRPConfigMobs.ombooEnabled) {
               return new EntityOmboo(world);
            }
            break;
         case 6:
            if (SRPConfigMobs.orchEnabled) {
               return new EntityOrch(world);
            }
      }

      return null;
   }

   public static boolean spawnUnitFromRof(World world, EntityLivingBase target, BlockPos poss, String[] out, int min, int max) {
      poss = getFloor(world, poss, 10);
      if (poss == null) {
         return false;
      } else {
         EntityRof samuel = new EntityRof(world);
         samuel.func_70107_b(poss.func_177958_n() + 0.5, poss.func_177956_o(), poss.func_177952_p() + 0.5);
         if (!samuel.field_70170_p.func_184144_a(samuel, samuel.func_174813_aQ().func_72321_a(1.0, 7.0, 1.0)).isEmpty()) {
            samuel.func_70106_y();
            return false;
         } else {
            samuel.setMob(out);
            samuel.maxmob = max;
            samuel.minmob = min;
            samuel.setPeek(true);
            samuel.setBuried();
            samuel.func_70624_b(target);
            world.func_72838_d(samuel);
            world.func_184133_a(null, samuel.func_180425_c(), SRPSounds.ROF_EMERGE, SoundCategory.HOSTILE, 2.0F, 1.0F);
            world.func_72960_a(samuel, (byte)50);
            samuel.targetScent = target;
            if (target == null) {
               samuel.targetScent = samuel;
            }

            return true;
         }
      }
   }

   public static boolean disloNumber2(World world) {
      List<Entity> serverList = world.field_72996_f;
      int count = 0;
      EntityLivingBase in = null;

      for (int i = 0; i < serverList.size(); i++) {
         if (serverList.get(i) instanceof EntityLivingBase && ((EntityLivingBase)serverList.get(i)).func_70644_a(SRPPotions.JUGG_E)) {
            int atm = ((EntityLivingBase)serverList.get(i)).func_70660_b(SRPPotions.JUGG_E).func_76458_c();
            if (count <= atm) {
               count = atm;
               in = (EntityLivingBase)serverList.get(i);
            }
         }
      }

      if (in != null && count >= SRPConfigSystems.disloSummonByDeathKilling) {
         int loop = 0;
         Random rand = new Random();

         for (int count2 = SRPSaveData.get(world, 99).getCurrentCode(world.field_73011_w.getDimension(), 2); loop < 10; loop++) {
            double randomx = rand.nextInt(4);
            double randomz = rand.nextInt(4);
            if (rand.nextBoolean()) {
               randomx *= -1.0;
            }

            if (rand.nextBoolean()) {
               randomz *= -1.0;
            }

            if (spawnUnitFromRof(
               world,
               in,
               new BlockPos(in.field_70165_t + randomx, in.field_70163_u, in.field_70161_v + randomz),
               new String[]{disloNumber2A(count2, world.field_73012_v)},
               0,
               0
            )) {
               SRPSaveData.get(world, 91).setCurrentCode(world.field_73011_w.getDimension(), 2, 0, 0, world, false, 0);
               return true;
            }
         }
      }

      return false;
   }

   private static String disloNumber2A(int dama, Random rand) {
      int cc = 0;

      for (int i = 0; i < SRPConfigSystems.disloSummonByDeathMobs.length; i++) {
         if (SRPConfigSystems.disloSummonByDeathMobs[i] != null) {
            String[] here = SRPConfigSystems.disloSummonByDeathMobs[i].split(";");
            int min = Integer.parseInt(here[0]);
            if (min < dama && min > cc) {
               cc = min;
            }
         }
      }

      ArrayList<String> mobList = new ArrayList<>();

      for (int ix = 0; ix < SRPConfigSystems.disloSummonByDeathMobs.length; ix++) {
         if (SRPConfigSystems.disloSummonByDeathMobs[ix] != null) {
            String[] here = SRPConfigSystems.disloSummonByDeathMobs[ix].split(";");
            if (cc == Integer.parseInt(here[0])) {
               mobList.add(here[1]);
            }
         }
      }

      if (mobList.isEmpty()) {
         for (int ixx = 0; ixx < SRPConfigSystems.disloSummonByDeathMobs.length; ixx++) {
            if (SRPConfigSystems.disloSummonByDeathMobs[ixx] != null) {
               String[] here = SRPConfigSystems.disloSummonByDeathMobs[ixx].split(";");
               if (here.length > 1) {
                  return here[1];
               }
            }
         }

         return "srparasites:warden";
      } else {
         return mobList.get(rand.nextInt(mobList.size()));
      }
   }

   public static boolean disloNumber5(EntityLivingBase target, World world) {
      return false;
   }
}
