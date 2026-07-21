package com.dhanantry.scapeandrunparasites.item;

import com.dhanantry.scapeandrunparasites.bestiary.net.BestiaryNetwork;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.network.PacketVengeanceFX;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public final class VengeanceGrappleHandler {
   private static final VengeanceGrappleHandler INSTANCE = new VengeanceGrappleHandler();
   private static final Map<UUID, VengeanceGrappleHandler.GrappleData> ACTIVE = new HashMap<>();

   public static VengeanceGrappleHandler get() {
      return INSTANCE;
   }

   private VengeanceGrappleHandler() {
   }

   private static SimpleNetworkWrapper net() {
      return BestiaryNetwork.CH;
   }

   private static void fxAroundTracking(WorldServer ws, Vec3d pos, PacketVengeanceFX pkt) {
      for (EntityPlayerMP p : ws.func_73046_m().func_184103_al().func_181057_v()) {
         if (p.field_71093_bK == ws.field_73011_w.getDimension() && !(p.func_70092_e(pos.field_72450_a, pos.field_72448_b, pos.field_72449_c) > 4096.0)) {
            net().sendTo(pkt, p);
         }
      }
   }

   public static void start(EntityPlayer player, EntityLivingBase target) {
      if (player != null && target != null) {
         if (!player.field_70128_L && !target.field_70128_L) {
            if (player.field_70170_p instanceof WorldServer) {
               VengeanceGrappleHandler.GrappleData d = new VengeanceGrappleHandler.GrappleData(
                  player.func_110124_au(), target.func_110124_au(), player.field_71093_bK
               );
               d.stage = VengeanceGrappleHandler.Stage.WINDUP;
               d.timer = 20;
               ACTIVE.put(player.func_110124_au(), d);
               WorldServer ws = (WorldServer)player.field_70170_p;
               ws.func_184148_a(
                  null, player.field_70165_t, player.field_70163_u, player.field_70161_v, SRPSounds.VENGEANCE_PAPER, SoundCategory.PLAYERS, 2.0F, 1.0F
               );
            }
         }
      }
   }

   @SubscribeEvent
   public void onPlayerAttacked(LivingAttackEvent e) {
      if (e.getEntityLiving() instanceof EntityPlayer) {
         EntityPlayer p = (EntityPlayer)e.getEntityLiving();
         VengeanceGrappleHandler.GrappleData d = ACTIVE.get(p.func_110124_au());
         if (d != null) {
            Entity src = e.getSource().func_76346_g();
            if (src != null && src.func_110124_au().equals(d.targetId)) {
               e.setCanceled(true);
               p.field_70172_ad = 20;
            }
         }
      }
   }

   @SubscribeEvent
   public void onPlayerHurt(LivingHurtEvent e) {
      if (e.getEntityLiving() instanceof EntityPlayer) {
         EntityPlayer p = (EntityPlayer)e.getEntityLiving();
         VengeanceGrappleHandler.GrappleData d = ACTIVE.get(p.func_110124_au());
         if (d != null) {
            if (e.getSource() == DamageSource.field_180137_b) {
               e.setCanceled(true);
            } else {
               String dt = e.getSource().field_76373_n;
               if ("explosion".equals(dt) || "explosion.player".equals(dt)) {
                  e.setCanceled(true);
               }
            }
         }
      }
   }

   private static void applyVengeanceDebuffs(EntityLivingBase ent) {
      if (ent != null && !ent.field_70128_L) {
         int dur = 100;
         if (SRPPotions.DOD_SMOKE_TRAIL_E != null) {
            ent.func_70690_d(new PotionEffect(SRPPotions.DOD_SMOKE_TRAIL_E, 100, 0, false, true));
         }

         if (SRPPotions.BLEED_E != null) {
            ent.func_70690_d(new PotionEffect(SRPPotions.BLEED_E, 100, 0, false, true));
         }

         if (SRPPotions.DEBAR_E != null) {
            ent.func_70690_d(new PotionEffect(SRPPotions.DEBAR_E, 100, 0, false, true));
         }
      }
   }

   @SubscribeEvent
   public void onServerTick(ServerTickEvent e) {
      if (e.phase == Phase.END) {
         if (!ACTIVE.isEmpty()) {
            Iterator<Entry<UUID, VengeanceGrappleHandler.GrappleData>> it = ACTIVE.entrySet().iterator();

            while (it.hasNext()) {
               Entry<UUID, VengeanceGrappleHandler.GrappleData> en = it.next();
               VengeanceGrappleHandler.GrappleData d = en.getValue();
               EntityPlayer player = d.getPlayer();
               EntityLivingBase target = d.getTarget();
               if (player == null || target == null) {
                  it.remove();
               } else if (player.field_70128_L || target.field_70128_L) {
                  it.remove();
               } else if (player.field_71093_bK != d.dimension) {
                  it.remove();
               } else if (!(player.field_70170_p instanceof WorldServer)) {
                  it.remove();
               } else {
                  WorldServer ws = (WorldServer)player.field_70170_p;
                  if (!player.func_70685_l(target)) {
                     ws.func_184148_a(
                        null, player.field_70165_t, player.field_70163_u, player.field_70161_v, SoundEvents.field_187646_bt, SoundCategory.PLAYERS, 0.6F, 1.4F
                     );
                     it.remove();
                  } else {
                     switch (d.stage) {
                        case WINDUP:
                           spawnChainParticles(ws, player, target, EnumParticleTypes.SPELL_WITCH);
                           if (d.timer % 4 == 0) {
                              Vec3d c = new Vec3d(player.field_70165_t, player.field_70163_u + 0.2, player.field_70161_v);
                              fxAroundTracking(ws, c, new PacketVengeanceFX((byte)0, c, 1.2F, 16));
                           }

                           if (d.timer % 6 == 0) {
                              ws.func_184148_a(
                                 null,
                                 player.field_70165_t,
                                 player.field_70163_u,
                                 player.field_70161_v,
                                 SoundEvents.field_190021_aL,
                                 SoundCategory.PLAYERS,
                                 0.25F,
                                 1.8F
                              );
                           }

                           d.timer--;
                           if (d.timer <= 0) {
                              ws.func_184148_a(
                                 null,
                                 target.field_70165_t,
                                 target.field_70163_u + target.field_70131_O * 0.5,
                                 target.field_70161_v,
                                 SRPSounds.VENGEANCE_CHAIN_IMPACT,
                                 SoundCategory.PLAYERS,
                                 1.0F,
                                 1.0F
                              );
                              d.stage = VengeanceGrappleHandler.Stage.PULL;
                              d.ticks = 0;
                           }
                           break;
                        case PULL:
                           d.ticks++;
                           spawnChainParticles(ws, player, target, EnumParticleTypes.CRIT_MAGIC);
                           pullPlayerToward(player, target, 1.8);
                           double dist = player.func_70032_d(target);
                           if (dist <= 2.2 || d.ticks > 40) {
                              impact1(ws, player, target, d);
                           }
                           break;
                        case BOUNCE:
                           if (d.timer % 4 == 0) {
                              for (UUID id : d.affected) {
                                 Entity e2 = ws.func_175733_a(id);
                                 if (e2 instanceof EntityLivingBase) {
                                    EntityLivingBase ent2 = (EntityLivingBase)e2;
                                    if (!ent2.field_70128_L) {
                                       Vec3d c = new Vec3d(ent2.field_70165_t, ent2.field_70163_u + ent2.field_70131_O * 0.5, ent2.field_70161_v);
                                       fxAroundTracking(ws, c, new PacketVengeanceFX((byte)2, c, 0.0F, 14));
                                    }
                                 }
                              }
                           }

                           int t = d.timer;
                           if (t >= 12) {
                              player.field_70181_x = Math.max(player.field_70181_x, 0.55);
                              player.field_70159_w *= 0.4;
                              player.field_70179_y *= 0.4;
                              player.field_70133_I = true;
                           } else if (t >= 6) {
                              player.field_70181_x = Math.max(player.field_70181_x, -0.02);
                              player.field_70159_w *= 0.65;
                              player.field_70179_y *= 0.65;
                              player.field_70133_I = true;
                           } else {
                              pullPlayerToward(player, target, 2.35);
                           }

                           player.field_70143_R = 0.0F;
                           d.timer--;
                           if (d.timer <= 0) {
                              impact2AndLightning(ws, player, target, d);
                              it.remove();
                           }
                     }
                  }
               }
            }
         }
      }
   }

   private static void pullPlayerToward(EntityPlayer player, EntityLivingBase target, double speed) {
      Vec3d to = new Vec3d(
         target.field_70165_t - player.field_70165_t,
         target.field_70163_u + target.func_70047_e() * 0.5 - (player.field_70163_u + player.func_70047_e()),
         target.field_70161_v - player.field_70161_v
      );
      double len = to.func_72433_c();
      if (!(len < 1.0E-4)) {
         Vec3d dir = to.func_186678_a(1.0 / len);
         double vx = MathHelper.func_151237_a(dir.field_72450_a * speed, -2.8, 2.8);
         double vy = MathHelper.func_151237_a(dir.field_72448_b * speed, -2.2, 2.2);
         double vz = MathHelper.func_151237_a(dir.field_72449_c * speed, -2.8, 2.8);
         player.field_70159_w = vx;
         player.field_70181_x = vy;
         player.field_70179_y = vz;
         player.field_70133_I = true;
         player.field_70143_R = 0.0F;
      }
   }

   private static void impact1(WorldServer ws, EntityPlayer player, EntityLivingBase target, VengeanceGrappleHandler.GrappleData d) {
      ws.func_184148_a(null, target.field_70165_t, target.field_70163_u, target.field_70161_v, SRPSounds.VENGEANCE_IMPACT, SoundCategory.PLAYERS, 1.0F, 1.0F);
      ws.func_184148_a(null, target.field_70165_t, target.field_70163_u, target.field_70161_v, SRPSounds.VENGEANCE_ROCK, SoundCategory.PLAYERS, 0.9F, 0.9F);
      Vec3d hitPos = new Vec3d(target.field_70165_t, target.field_70163_u, target.field_70161_v);
      fxAroundTracking(ws, hitPos, new PacketVengeanceFX((byte)1, hitPos, 0.0F, 28));
      ws.func_175739_a(
         EnumParticleTypes.EXPLOSION_HUGE, target.field_70165_t, target.field_70163_u + 0.2, target.field_70161_v, 1, 0.0, 0.0, 0.0, 0.0, new int[0]
      );
      float blastDamage = 14.0F;
      float radius = 5.0F;
      AxisAlignedBB aabb = new AxisAlignedBB(
         target.field_70165_t - radius,
         target.field_70163_u - 2.0,
         target.field_70161_v - radius,
         target.field_70165_t + radius,
         target.field_70163_u + 4.0,
         target.field_70161_v + radius
      );
      List<EntityLivingBase> list = ws.func_72872_a(EntityLivingBase.class, aabb);
      d.affected.clear();

      for (EntityLivingBase ent : list) {
         if (ent != null && !ent.field_70128_L && ent != player) {
            double dist = ent.func_70032_d(target);
            if (!(dist > radius)) {
               d.affected.add(ent.func_110124_au());
               applyVengeanceDebuffs(ent);
               if (SRPSounds.VENGEANCE_WHOOSH != null) {
                  ws.func_184148_a(null, ent.field_70165_t, ent.field_70163_u, ent.field_70161_v, SRPSounds.VENGEANCE_WHOOSH, SoundCategory.PLAYERS, 0.8F, 1.2F);
               }

               ent.func_70097_a(DamageSource.func_76365_a(player), blastDamage);
               Vec3d look = player.func_70040_Z();
               double l = look.func_72433_c();
               if (l > 1.0E-4) {
                  look = look.func_186678_a(1.0 / l);
               }

               double forward = 1.9;
               double upward = 0.65;
               ent.field_70159_w = look.field_72450_a * forward;
               ent.field_70179_y = look.field_72449_c * forward;
               ent.field_70181_x = Math.max(ent.field_70181_x, look.field_72448_b * forward + upward);
               ent.field_70133_I = true;
            }
         }
      }

      player.field_70159_w *= 0.15;
      player.field_70179_y *= 0.15;
      player.field_70143_R = 0.0F;
      player.field_70133_I = true;
      d.stage = VengeanceGrappleHandler.Stage.BOUNCE;
      d.timer = 14;
   }

   private static void impact2AndLightning(WorldServer ws, EntityPlayer player, EntityLivingBase target, VengeanceGrappleHandler.GrappleData d) {
      ws.func_184148_a(null, target.field_70165_t, target.field_70163_u, target.field_70161_v, SoundEvents.field_187539_bB, SoundCategory.PLAYERS, 0.7F, 1.2F);
      Vec3d p = new Vec3d(target.field_70165_t, target.field_70163_u + 0.1, target.field_70161_v);
      fxAroundTracking(ws, p, new PacketVengeanceFX((byte)3, p, 0.0F, 0));
      ws.func_184148_a(null, target.field_70165_t, target.field_70163_u, target.field_70161_v, SRPSounds.VENGEANCE_ROCK, SoundCategory.PLAYERS, 0.9F, 0.9F);
      ws.func_175739_a(
         EnumParticleTypes.EXPLOSION_LARGE, target.field_70165_t, target.field_70163_u + 0.15, target.field_70161_v, 3, 0.15, 0.1, 0.15, 0.0, new int[0]
      );
      if (!target.field_70128_L) {
         target.func_70097_a(DamageSource.func_76365_a(player), 6.0F);
         target.field_70181_x = Math.max(target.field_70181_x, 0.35);
         target.field_70133_I = true;
      }

      float radius = 3.5F;
      float maxDmg = 10.0F;
      AxisAlignedBB aabb = new AxisAlignedBB(
         target.field_70165_t - 3.5,
         target.field_70163_u - 1.5,
         target.field_70161_v - 3.5,
         target.field_70165_t + 3.5,
         target.field_70163_u + 2.5,
         target.field_70161_v + 3.5
      );

      for (EntityLivingBase ent : ws.func_72872_a(EntityLivingBase.class, aabb)) {
         if (ent != null && !ent.field_70128_L && ent != player) {
            double dist = ent.func_70032_d(target);
            if (!(dist > 3.5)) {
               float scale = 1.0F - (float)(dist / 3.5);
               float dmg = 10.0F * scale;
               ent.func_70097_a(DamageSource.func_188405_b(player), dmg);
               Vec3d push = new Vec3d(ent.field_70165_t - target.field_70165_t, 0.0, ent.field_70161_v - target.field_70161_v);
               double len = push.func_72433_c();
               if (len > 1.0E-4) {
                  push = push.func_186678_a(1.0 / len);
                  double kb = 0.85 * scale;
                  ent.field_70159_w = ent.field_70159_w + push.field_72450_a * kb;
                  ent.field_70179_y = ent.field_70179_y + push.field_72449_c * kb;
                  ent.field_70181_x = Math.max(ent.field_70181_x, 0.25 + 0.2 * scale);
                  ent.field_70133_I = true;
               }
            }
         }
      }

      player.field_70143_R = 0.0F;
      lightningStrike(ws, player, d);
   }

   private static void lightningStrike(WorldServer ws, EntityPlayer player, VengeanceGrappleHandler.GrappleData d) {
      ws.func_184148_a(null, player.field_70165_t, player.field_70163_u, player.field_70161_v, SoundEvents.field_187754_de, SoundCategory.PLAYERS, 0.9F, 1.0F);

      for (UUID id : d.affected) {
         Entity e = ws.func_175733_a(id);
         if (e instanceof EntityLivingBase) {
            EntityLivingBase ent = (EntityLivingBase)e;
            if (!ent.field_70128_L) {
               ws.func_72942_c(new EntityLightningBolt(ws, ent.field_70165_t, ent.field_70163_u, ent.field_70161_v, true));
               Vec3d p = new Vec3d(ent.field_70165_t, ent.field_70163_u, ent.field_70161_v);
               fxAroundTracking(ws, p, new PacketVengeanceFX((byte)3, p, 0.0F, 0));
               ent.func_70097_a(DamageSource.field_180137_b, 16.0F);
               ws.func_184148_a(null, ent.field_70165_t, ent.field_70163_u, ent.field_70161_v, SoundEvents.field_187752_dd, SoundCategory.HOSTILE, 0.8F, 1.0F);
            }
         }
      }
   }

   private static void spawnChainParticles(WorldServer ws, EntityPlayer player, EntityLivingBase target, EnumParticleTypes type) {
      Vec3d start = new Vec3d(player.field_70165_t, player.field_70163_u + player.func_70047_e() - 0.15, player.field_70161_v);
      Vec3d end = new Vec3d(target.field_70165_t, target.field_70163_u + target.func_70047_e() * 0.5, target.field_70161_v);
      Vec3d delta = end.func_178788_d(start);
      double len = delta.func_72433_c();
      if (!(len < 1.0E-4)) {
         Vec3d step = delta.func_186678_a(1.0 / len);
         int count = MathHelper.func_76125_a((int)(len / 0.6), 6, 60);

         for (int i = 0; i < count; i++) {
            Vec3d p = start.func_178787_e(step.func_186678_a(i * (len / count)));
            ws.func_175739_a(type, p.field_72450_a, p.field_72448_b, p.field_72449_c, 1, 0.02, 0.02, 0.02, 0.0, new int[0]);
         }
      }
   }

   private static final class GrappleData {
      final UUID playerId;
      final UUID targetId;
      final int dimension;
      VengeanceGrappleHandler.Stage stage = VengeanceGrappleHandler.Stage.WINDUP;
      int ticks = 0;
      int timer = 0;
      final List<UUID> affected = new ArrayList<>();

      GrappleData(UUID playerId, UUID targetId, int dimension) {
         this.playerId = playerId;
         this.targetId = targetId;
         this.dimension = dimension;
      }

      EntityPlayer getPlayer() {
         return FMLCommonHandler.instance().getMinecraftServerInstance() == null
            ? null
            : FMLCommonHandler.instance().getMinecraftServerInstance().func_184103_al().func_177451_a(this.playerId);
      }

      EntityLivingBase getTarget() {
         EntityPlayer p = this.getPlayer();
         if (p != null && p.field_70170_p instanceof WorldServer) {
            Entity e = ((WorldServer)p.field_70170_p).func_175733_a(this.targetId);
            return e instanceof EntityLivingBase ? (EntityLivingBase)e : null;
         } else {
            return null;
         }
      }
   }

   private static enum Stage {
      WINDUP,
      PULL,
      BOUNCE;
   }
}
