package com.dhanantry.scapeandrunparasites.client.shader;

import com.dhanantry.scapeandrunparasites.client.celestial.BlackSkyClient;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.shader.Shader;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.client.shader.ShaderUniform;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public final class BlackSkyShaderManager {
   private static final ResourceLocation POST = new ResourceLocation("srparasites", "shaders/post/black_sky_darkness.json");
   private static final BlackSkyShaderManager INSTANCE = new BlackSkyShaderManager();
   private boolean registered = false;
   private boolean applied = false;
   private int lastW = -1;
   private int lastH = -1;
   private long startNs = 0L;
   private long nextRetryMs = 0L;
   private static Field F_LIST_SHADERS;

   public static BlackSkyShaderManager get() {
      return INSTANCE;
   }

   private BlackSkyShaderManager() {
   }

   public void register() {
      if (!this.registered) {
         MinecraftForge.EVENT_BUS.register(this);
         this.registered = true;
      }
   }

   @SubscribeEvent
   public void onClientTick(ClientTickEvent e) {
      if (e.phase == Phase.END) {
         Minecraft mc = Minecraft.func_71410_x();
         if (mc != null && mc.field_71441_e != null && mc.field_71439_g != null) {
            EntityRenderer er = mc.field_71460_t;
            ShaderGroup current = er.func_147706_e();
            boolean shouldEnable = BlackSkyClient.isBlackSkyActive();
            if (!shouldEnable) {
               if (current != null && this.applied) {
                  er.func_181022_b();
               }

               this.applied = false;
            } else if (this.applied && current != null) {
               ShaderGroup sg = er.func_147706_e();
               if (sg == null) {
                  this.applied = false;
               } else {
                  int w = mc.field_71443_c;
                  int h = mc.field_71440_d;
                  if (w != this.lastW || h != this.lastH) {
                     sg.func_148026_a(w, h);
                     this.lastW = w;
                     this.lastH = h;
                  }

                  float timeSec = (float)((System.nanoTime() - this.startNs) / 1.0E9);
                  setUniformAll(sg, "SRP_Time", timeSec);
                  setUniformAll(sg, "Darkness", 0.85F);
               }
            } else {
               long now = System.currentTimeMillis();
               if (now >= this.nextRetryMs) {
                  try {
                     this.startNs = System.nanoTime();
                     er.func_175069_a(POST);
                     ShaderGroup sg = er.func_147706_e();
                     if (sg != null) {
                        int w = mc.field_71443_c;
                        int h = mc.field_71440_d;
                        sg.func_148026_a(w, h);
                        this.lastW = w;
                        this.lastH = h;
                        this.applied = true;
                     } else {
                        this.applied = false;
                     }
                  } catch (Throwable var11) {
                     this.nextRetryMs = System.currentTimeMillis() + 2000L;
                     this.applied = false;
                  }
               }
            }
         } else {
            this.applied = false;
         }
      }
   }

   private static List<Shader> getShaders(ShaderGroup sg) {
      try {
         if (F_LIST_SHADERS == null) {
            F_LIST_SHADERS = ReflectionHelper.findField(ShaderGroup.class, "listShaders", "field_148031_d");
            F_LIST_SHADERS.setAccessible(true);
         }

         return (List<Shader>)F_LIST_SHADERS.get(sg);
      } catch (Throwable var2) {
         return null;
      }
   }

   public static boolean isSRPDarkDaysShaderPackActive() {
      try {
         Class<?> shaders = Class.forName("net.optifine.shaders.Shaders");

         try {
            Object v = shaders.getField("currentShaderName").get(null);
            if (v instanceof String) {
               String s = ((String)v).toLowerCase(Locale.ROOT);
               return s.contains("srp_darkdays_lite")
                  || s.contains("srp darkdays lite")
                  || s.contains("srp_dark_days_lite")
                  || s.contains("srp dark days lite");
            }
         } catch (Throwable var3) {
         }
      } catch (Throwable var4) {
      }

      return false;
   }

   private static void setUniformAll(ShaderGroup sg, String name, float value) {
      try {
         List<Shader> shaders = getShaders(sg);
         if (shaders == null) {
            return;
         }

         for (Shader shader : shaders) {
            if (shader != null) {
               ShaderUniform uniform = shader.func_148043_c().func_147991_a(name);
               if (uniform != null) {
                  uniform.func_148090_a(value);
               }
            }
         }
      } catch (Throwable var7) {
      }
   }
}
