package com.dhanantry.scapeandrunparasites.client.fx;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.FogMode;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogColors;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogDensity;
import net.minecraftforge.client.event.EntityViewRenderEvent.RenderFogEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(modid = "srparasites", value = Side.CLIENT)
public final class ClientExtremeSnow {
   private static boolean enabled = false;
   private static float intensity = 1.0F;
   private static boolean forceAnywhere = true;
   private static float windDeg = 30.0F;
   private static float windSpeed = 0.5F;
   private static double windX = 0.0;
   private static double windZ = 0.0;

   private ClientExtremeSnow() {
   }

   public static void setState(boolean on, float i, boolean any, float deg, float spd) {
      enabled = on;
      intensity = MathHelper.func_76131_a(i, 0.0F, 1.0F);
      forceAnywhere = any;
      windDeg = deg % 360.0F;
      windSpeed = MathHelper.func_76131_a(spd, 0.0F, 1.0F);
      double rad = Math.toRadians(windDeg);
      double base = 0.15 + 0.55 * windSpeed;
      windX = Math.cos(rad) * base;
      windZ = Math.sin(rad) * base;
      Minecraft mc = Minecraft.func_71410_x();
      if (mc != null) {
         if (mc.field_71438_f != null) {
            mc.field_71438_f.func_72712_a();
         }

         if (mc.field_71460_t != null) {
            mc.field_71460_t.func_78464_a();
         }
      }
   }

   private static float exp2DensityFor(double targetBlocks) {
      double TH = 0.02;
      return (float)Math.min(0.999, Math.sqrt(Math.log(50.0)) / Math.max(1.0E-6, targetBlocks));
   }

   @SubscribeEvent
   public static void onFogDensity(FogDensity e) {
      if (enabled) {
         Minecraft mc = Minecraft.func_71410_x();
         if (mc.field_71441_e != null && mc.field_71439_g != null) {
            if (isOutdoors(mc.field_71441_e, mc.field_71439_g)) {
               float d = exp2DensityFor(10.0);
               e.setDensity(d);
               e.setCanceled(true);
            }
         }
      }
   }

   @SubscribeEvent
   public static void onFogColors(FogColors e) {
      if (enabled) {
         Minecraft mc = Minecraft.func_71410_x();
         if (mc.field_71441_e != null && mc.field_71439_g != null) {
            if (isOutdoors(mc.field_71441_e, mc.field_71439_g)) {
               float push = 0.75F;
               e.setRed(e.getRed() * (1.0F - push) + push);
               e.setGreen(e.getGreen() * (1.0F - push) + push);
               e.setBlue(e.getBlue() * (1.0F - push) + push);
            }
         }
      }
   }

   @SubscribeEvent
   public static void onClientTick(ClientTickEvent e) {
      if (enabled && e.phase == Phase.END) {
         Minecraft mc = Minecraft.func_71410_x();
         World w = mc.field_71441_e;
         if (w != null) {
            EntityPlayerSP p = mc.field_71439_g;
            if (p != null) {
               double radius = 12.0;
               int setting = mc.field_71474_y.field_74362_aa;
               double budget = setting == 2 ? 0.25 : (setting == 1 ? 0.55 : 1.0);
               int base = 140 + (int)(360.0F * intensity);
               int count = (int)(base * 1.8 * budget);

               for (int i = 0; i < count; i++) {
                  int x = (int)(p.field_70165_t + w.field_73012_v.nextGaussian() * radius);
                  int z = (int)(p.field_70161_v + w.field_73012_v.nextGaussian() * radius);
                  BlockPos ground = w.func_175725_q(new BlockPos(x, (int)p.field_70163_u, z));
                  if (w.func_175678_i(ground) && (forceAnywhere || w.func_175708_f(ground, false))) {
                     double spawnY = Math.max(
                        (double)(ground.func_177956_o() + 16 + w.field_73012_v.nextInt(8)), p.field_70163_u + 16.0 + w.field_73012_v.nextInt(8)
                     );
                     double sx = x + 0.5 + (w.field_73012_v.nextDouble() - 0.5);
                     double sz = z + 0.5 + (w.field_73012_v.nextDouble() - 0.5);
                     double jitter = 0.03;
                     double vx = windX + (w.field_73012_v.nextDouble() - 0.5) * jitter;
                     double vz = windZ + (w.field_73012_v.nextDouble() - 0.5) * jitter;
                     double vy = -0.22 - 0.06 * intensity - w.field_73012_v.nextDouble() * 0.04;
                     mc.field_71452_i.func_78873_a(new ParticleBlizzard(w, sx, spawnY, sz, vx, vy, vz, p));
                  }
               }
            }
         }
      }
   }

   private static float densityForVisibility(double targetBlocks) {
      double THRESHOLD = 0.02;
      double D = Math.sqrt(Math.log(50.0)) / Math.max(1.0E-6, targetBlocks);
      return (float)MathHelper.func_151237_a(D, 0.0, 1.0);
   }

   @SubscribeEvent
   public static void onRenderFog(RenderFogEvent e) {
      if (enabled) {
         Minecraft mc = Minecraft.func_71410_x();
         if (mc.field_71441_e != null && mc.field_71439_g != null) {
            if (isOutdoors(mc.field_71441_e, mc.field_71439_g)) {
               float d = exp2DensityFor(10.0);
               GlStateManager.func_187430_a(FogMode.EXP2);
               GlStateManager.func_179095_a(d);
            }
         }
      }
   }

   private static boolean isOutdoors(World w, EntityPlayerSP p) {
      BlockPos[] samples = new BlockPos[]{
         new BlockPos(p.field_70165_t, p.field_70163_u, p.field_70161_v),
         new BlockPos(p.field_70165_t + 4.0, p.field_70163_u, p.field_70161_v),
         new BlockPos(p.field_70165_t - 4.0, p.field_70163_u, p.field_70161_v),
         new BlockPos(p.field_70165_t, p.field_70163_u, p.field_70161_v + 4.0),
         new BlockPos(p.field_70165_t, p.field_70163_u, p.field_70161_v - 4.0)
      };

      for (BlockPos s : samples) {
         BlockPos h = w.func_175725_q(s);
         if (w.func_175678_i(h)) {
            return true;
         }
      }

      return false;
   }
}
