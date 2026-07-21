package com.dhanantry.scapeandrunparasites.client.celestial;

import com.dhanantry.scapeandrunparasites.bestiary.cap.BestiaryCapability;
import com.dhanantry.scapeandrunparasites.bestiary.cap.IBestiaryProgress;
import com.dhanantry.scapeandrunparasites.bestiary.net.BestiaryNetwork;
import com.dhanantry.scapeandrunparasites.bestiary.net.PacketBestiarySeenCelestial;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(modid = "srparasites", value = Side.CLIENT)
public final class CelestialSkyRenderer {
   private static final double SKY_RADIUS = 180.0;
   private static final float ORBIT_PERIOD_TICKS = 12000.0F;
   private static final boolean DEBUG = false;
   private static boolean warnedOptifineDarkDays = false;
   private static final Map<String, Long> ORBIT_STARTS = new HashMap<>();
   private static long lastDayTime = 0L;
   private static final Set<String> SENT = new HashSet<>();

   private CelestialSkyRenderer() {
   }

   private static boolean hasFieldGuide(EntityPlayer p) {
      if (p == null) {
         return false;
      } else {
         Item guide = SRPItems.SRP_FIELD_GUIDE;

         for (ItemStack st : p.field_71071_by.field_70462_a) {
            if (!st.func_190926_b() && st.func_77973_b() == guide) {
               return true;
            }
         }

         for (ItemStack stx : p.field_71071_by.field_184439_c) {
            if (!stx.func_190926_b() && stx.func_77973_b() == guide) {
               return true;
            }
         }

         return false;
      }
   }

   private static void recordSeenOnce(Minecraft mc, String id) {
      if (id != null) {
         if (mc != null && mc.field_71439_g != null) {
            if (hasFieldGuide(mc.field_71439_g)) {
               IBestiaryProgress prog = (IBestiaryProgress)mc.field_71439_g.getCapability(BestiaryCapability.CAP, null);
               if (prog == null || !prog.hasSeenCelestial(id)) {
                  if (!SENT.contains(id)) {
                     SENT.add(id);
                     BestiaryNetwork.CH.sendToServer(new PacketBestiarySeenCelestial(id));
                  }
               }
            }
         }
      }
   }

   public static void clearSeenCacheClient() {
      SENT.clear();
   }

   private static Vec3d dirFromYawPitch(float yawDeg, float pitchDeg) {
      float yaw = (float)Math.toRadians(yawDeg);
      float pitch = (float)Math.toRadians(pitchDeg);
      float x = -MathHelper.func_76126_a(yaw) * MathHelper.func_76134_b(pitch);
      float y = MathHelper.func_76126_a(pitch);
      float z = MathHelper.func_76134_b(yaw) * MathHelper.func_76134_b(pitch);
      return new Vec3d(x, y, z);
   }

   private static float getCelestialMovementSpeedMultiplier() {
      return MathHelper.func_76131_a((float)SRPConfigWorld.celestialMovementSpeedMultiplier, 0.01F, 100.0F);
   }

   @SubscribeEvent
   public static void onRenderWorldLast(RenderWorldLastEvent e) {
      if (SRPConfigWorld.enableCelestialObjects) {
         Minecraft mc = Minecraft.func_71410_x();
         World world = mc.field_71441_e;
         EntityPlayer player = mc.field_71439_g;
         if (world != null && player != null) {
            if (world.field_73011_w.func_76569_d()) {
               float partialTicks = e.getPartialTicks();
               long totalTime = world.func_72820_D();
               long dayTime = totalTime % 24000L;
               if (BlackSkyClient.isBlackSkyActive()) {
                  recordSeenOnce(mc, "dark_days");
                  if (!isSRPDarkDaysShaderPackActive()) {
                     if (isOptifineInstalled()) {
                        if (!warnedOptifineDarkDays && mc.field_71439_g != null) {
                           TextComponentTranslation msg = new TextComponentTranslation("message.srparasites.dark_days.optifine_visuals_disabled", new Object[0]);
                           msg.func_150256_b().func_150238_a(TextFormatting.DARK_PURPLE);
                           mc.field_71439_g.func_145747_a(msg);
                           warnedOptifineDarkDays = true;
                        }
                     } else {
                        renderBlackSkyDome(mc);
                     }
                  }
               } else {
                  warnedOptifineDarkDays = false;
                  if (dayTime < lastDayTime) {
                     ORBIT_STARTS.clear();
                  }

                  lastDayTime = dayTime;
                  int dim = world.field_73011_w.getDimension();
                  int phase = CelestialPhaseClient.getPhase(dim);
                  float celestialAngle = world.func_72826_c(partialTicks);
                  float starBrightness = world.func_72880_h(partialTicks);
                  if (!(starBrightness <= 0.0F)) {
                     float rainStrength = world.func_72867_j(partialTicks);
                     if (!(rainStrength > 0.0F) && !world.func_72911_I()) {
                        double camX = mc.func_175598_ae().field_78730_l;
                        double camY = mc.func_175598_ae().field_78731_m;
                        double camZ = mc.func_175598_ae().field_78728_n;
                        long worldTime = world.func_82737_E();
                        float movementSpeed = getCelestialMovementSpeedMultiplier();
                        double motionTime = ((float)worldTime + partialTicks) * movementSpeed;
                        GlStateManager.func_179094_E();

                        try {
                           GlStateManager.func_179140_f();
                           GlStateManager.func_179129_p();
                           GlStateManager.func_179147_l();
                           GlStateManager.func_179120_a(770, 771, 1, 0);
                           GlStateManager.func_179141_d();
                           GlStateManager.func_179092_a(516, 0.1F);
                           GlStateManager.func_179126_j();
                           GlStateManager.func_179132_a(false);
                           GlStateManager.func_179143_c(515);
                           GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);

                           for (CelestialObjectDefinition def : CelestialObjectRegistry.getObjects()) {
                              boolean isForced = CelestialPhaseClient.isForcedTonight(dim, def.id);
                              if ((isForced || def.isPhaseAllowed(phase)) && (isForced || CelestialPhaseClient.isActiveTonight(dim, def.id))) {
                                 recordSeenOnce(mc, def.id);
                                 float yawDeg = def.yawDeg;
                                 float pitchDeg = def.pitchDeg;
                                 float period = def.orbitPeriodTicks > 0.0F ? def.orbitPeriodTicks : 12000.0F;
                                 if (def.orbitPath != CelestialObjectDefinition.OrbitPath.NONE) {
                                    String orbitKey = world.field_73011_w.getDimension() + ":" + def.id;
                                    float t;
                                    if (def.oneShotOrbit) {
                                       long startTime = ORBIT_STARTS.computeIfAbsent(orbitKey, k -> worldTime);
                                       t = (float)((double)(((float)(worldTime - startTime) + partialTicks) * movementSpeed) / period);
                                       if (t >= 1.0F) {
                                          continue;
                                       }
                                    } else {
                                       t = (float)(motionTime % period / period);
                                    }

                                    switch (def.orbitPath) {
                                       case RING:
                                          yawDeg = def.yawDeg + def.orbitYawRangeDeg * t;
                                          pitchDeg = def.orbitPitchMinDeg;
                                          break;
                                       case ARC:
                                          yawDeg = def.yawDeg + def.orbitYawRangeDeg * t;
                                          float arc = (float)Math.sin(Math.PI * t);
                                          pitchDeg = def.orbitPitchMinDeg + (def.orbitPitchMaxDeg - def.orbitPitchMinDeg) * arc;
                                    }
                                 } else if (def.fastStreak) {
                                    float t = (float)(motionTime % 12000.0 / 12000.0);
                                    yawDeg += t * 360.0F;
                                 }

                                 if (def.followsStars) {
                                    float starAngleDeg = celestialAngle * 360.0F;
                                    yawDeg += starAngleDeg;
                                 }

                                 if (def.rotationSpeedDeg != 0.0F) {
                                    float spin = (float)(motionTime / 20.0 * def.rotationSpeedDeg);
                                    yawDeg += spin;
                                 }

                                 Vec3d dir = dirFromYawPitch(yawDeg, pitchDeg);
                                 double wx = camX + dir.field_72450_a * 180.0;
                                 double wy = camY + dir.field_72448_b * 180.0;
                                 double wz = camZ + dir.field_72449_c * 180.0;
                                 mc.func_110434_K().func_110577_a(def.texture);
                                 float u0 = 0.0F;
                                 float u1 = 1.0F;
                                 float v0 = 0.0F;
                                 float v1 = 1.0F;
                                 if (def.animated && def.frameCount > 1) {
                                    int frame = (int)(worldTime / def.frameTimeTicks % def.frameCount);
                                    float frameH = 1.0F / def.frameCount;
                                    v0 = frame * frameH;
                                    v1 = v0 + frameH;
                                 }

                                 float alpha = MathHelper.func_76131_a(def.baseOpacity * starBrightness, 0.0F, 1.0F);
                                 if (!(alpha <= 0.001F)) {
                                    float size = def.size;
                                    Vec3d worldUp = new Vec3d(0.0, 1.0, 0.0);
                                    Vec3d side = worldUp.func_72431_c(dir);
                                    if (side.func_189985_c() < 1.0E-4) {
                                       worldUp = new Vec3d(0.0, 0.0, 1.0);
                                       side = worldUp.func_72431_c(dir);
                                    }

                                    side = side.func_72432_b();
                                    Vec3d upVec = dir.func_72431_c(side).func_72432_b();
                                    double half = size;
                                    Vec3d center = new Vec3d(wx, wy, wz);
                                    Vec3d p0 = center.func_178787_e(side.func_186678_a(-half)).func_178787_e(upVec.func_186678_a(-half));
                                    Vec3d p1 = center.func_178787_e(side.func_186678_a(half)).func_178787_e(upVec.func_186678_a(-half));
                                    Vec3d p2 = center.func_178787_e(side.func_186678_a(half)).func_178787_e(upVec.func_186678_a(half));
                                    Vec3d p3 = center.func_178787_e(side.func_186678_a(-half)).func_178787_e(upVec.func_186678_a(half));
                                    GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, alpha);
                                    Tessellator tess = Tessellator.func_178181_a();
                                    BufferBuilder buf = tess.func_178180_c();
                                    float prevLmX = OpenGlHelper.lastBrightnessX;
                                    float prevLmY = OpenGlHelper.lastBrightnessY;
                                    OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, 240.0F, 240.0F);
                                    GlStateManager.func_179118_c();
                                    buf.func_181668_a(7, DefaultVertexFormats.field_181707_g);
                                    buf.func_181662_b(p0.field_72450_a - camX, p0.field_72448_b - camY, p0.field_72449_c - camZ)
                                       .func_187315_a(u0, v1)
                                       .func_181675_d();
                                    buf.func_181662_b(p1.field_72450_a - camX, p1.field_72448_b - camY, p1.field_72449_c - camZ)
                                       .func_187315_a(u1, v1)
                                       .func_181675_d();
                                    buf.func_181662_b(p2.field_72450_a - camX, p2.field_72448_b - camY, p2.field_72449_c - camZ)
                                       .func_187315_a(u1, v0)
                                       .func_181675_d();
                                    buf.func_181662_b(p3.field_72450_a - camX, p3.field_72448_b - camY, p3.field_72449_c - camZ)
                                       .func_187315_a(u0, v0)
                                       .func_181675_d();
                                    tess.func_78381_a();
                                    GlStateManager.func_179141_d();
                                    GlStateManager.func_179092_a(516, 0.1F);
                                    OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, prevLmX, prevLmY);
                                    GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
                                    GlStateManager.func_179147_l();
                                    GlStateManager.func_179120_a(770, 771, 1, 0);
                                    GlStateManager.func_179126_j();
                                    GlStateManager.func_179132_a(false);
                                    GlStateManager.func_179143_c(515);
                                    GlStateManager.func_179129_p();
                                    GlStateManager.func_179140_f();
                                 }
                              }
                           }
                        } finally {
                           GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
                           GlStateManager.func_179141_d();
                           GlStateManager.func_179092_a(516, 0.1F);
                           GlStateManager.func_179084_k();
                           GlStateManager.func_179120_a(1, 0, 1, 0);
                           GlStateManager.func_179126_j();
                           GlStateManager.func_179132_a(true);
                           GlStateManager.func_179143_c(515);
                           GlStateManager.func_179089_o();
                           GlStateManager.func_179145_e();
                           GlStateManager.func_179121_F();
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private static boolean isOptifineInstalled() {
      try {
         Class.forName("net.optifine.Config");
         return true;
      } catch (Throwable var4) {
         try {
            Class.forName("optifine.Config");
            return true;
         } catch (Throwable var3) {
            try {
               Class.forName("optifine.OptiFineClassTransformer");
               return true;
            } catch (Throwable var2) {
               try {
                  Class.forName("net.optifine.shaders.Shaders");
                  return true;
               } catch (Throwable var1) {
                  return false;
               }
            }
         }
      }
   }

   public static boolean isSRPDarkDaysShaderPackActive() {
      try {
         Class<?> shaders = Class.forName("net.optifine.shaders.Shaders");
         Object v = shaders.getField("currentShaderName").get(null);
         if (v instanceof String) {
            String s = ((String)v).toLowerCase(Locale.ROOT);
            return s.contains("srp_darkdays_lite")
               || s.contains("srp darkdays lite")
               || s.contains("srp_dark_days_lite")
               || s.contains("srp dark days lite")
               || s.contains("srp_darkdays")
               || s.contains("srp darkdays");
         }
      } catch (Throwable var3) {
      }

      return false;
   }

   private static void renderBlackSkyDome(Minecraft mc) {
      GlStateManager.func_179094_E();

      try {
         GlStateManager.func_179090_x();
         GlStateManager.func_179140_f();
         GlStateManager.func_179129_p();
         GlStateManager.func_179126_j();
         GlStateManager.func_179132_a(false);
         GlStateManager.func_179143_c(515);
         GlStateManager.func_179147_l();
         GlStateManager.func_179120_a(770, 771, 1, 0);
         GlStateManager.func_179131_c(0.0F, 0.0F, 0.0F, 0.9F);
         Tessellator tess = Tessellator.func_178181_a();
         BufferBuilder buf = tess.func_178180_c();
         float renderDistanceBlocks = mc.field_71474_y.field_151451_c * 16.0F;
         float r = renderDistanceBlocks - 12.0F;
         if (r < 24.0F) {
            r = 24.0F;
         }

         if (r > 180.0F) {
            r = 180.0F;
         }

         buf.func_181668_a(7, DefaultVertexFormats.field_181705_e);
         buf.func_181662_b(-r, -r, -r).func_181675_d();
         buf.func_181662_b(r, -r, -r).func_181675_d();
         buf.func_181662_b(r, r, -r).func_181675_d();
         buf.func_181662_b(-r, r, -r).func_181675_d();
         buf.func_181662_b(r, -r, r).func_181675_d();
         buf.func_181662_b(-r, -r, r).func_181675_d();
         buf.func_181662_b(-r, r, r).func_181675_d();
         buf.func_181662_b(r, r, r).func_181675_d();
         buf.func_181662_b(-r, -r, r).func_181675_d();
         buf.func_181662_b(-r, -r, -r).func_181675_d();
         buf.func_181662_b(-r, r, -r).func_181675_d();
         buf.func_181662_b(-r, r, r).func_181675_d();
         buf.func_181662_b(r, -r, -r).func_181675_d();
         buf.func_181662_b(r, -r, r).func_181675_d();
         buf.func_181662_b(r, r, r).func_181675_d();
         buf.func_181662_b(r, r, -r).func_181675_d();
         buf.func_181662_b(-r, r, -r).func_181675_d();
         buf.func_181662_b(r, r, -r).func_181675_d();
         buf.func_181662_b(r, r, r).func_181675_d();
         buf.func_181662_b(-r, r, r).func_181675_d();
         tess.func_78381_a();
      } finally {
         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.func_179132_a(true);
         GlStateManager.func_179143_c(515);
         GlStateManager.func_179126_j();
         GlStateManager.func_179084_k();
         GlStateManager.func_179089_o();
         GlStateManager.func_179145_e();
         GlStateManager.func_179098_w();
         GlStateManager.func_179121_F();
      }
   }
}
