package com.dhanantry.scapeandrunparasites.client.shader;

import java.lang.reflect.Field;
import java.util.List;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SideOnly(Side.CLIENT)
public final class BreatheShaderManager {
   private static final Logger LOG = LogManager.getLogger("SRP-Shader");
   private static final ResourceLocation POST = new ResourceLocation("srparasites", "shaders/post/notch_tweaked.json");
   private static final BreatheShaderManager INSTANCE = new BreatheShaderManager();
   private boolean wantEnabled = false;
   private boolean applied = false;
   private boolean registered = false;
   private int lastW = -1;
   private int lastH = -1;
   private long nextRetryMs = 0L;
   private long startNs = 0L;
   private int ticksRemaining = 0;
   private static Field F_LIST_SHADERS;

   public static BreatheShaderManager get() {
      return INSTANCE;
   }

   private BreatheShaderManager() {
   }

   public void enable() {
      this.wantEnabled = true;
      if (this.ticksRemaining <= 0) {
         this.startNs = System.nanoTime();
      }

      if (!this.registered) {
         MinecraftForge.EVENT_BUS.register(this);
         this.registered = true;
      }

      LOG.info("[SRP Breathe] enable()");
   }

   public void enableFor(int ticks) {
      if (ticks > 0) {
         if (!this.wantEnabled || this.ticksRemaining <= 0) {
            this.startNs = System.nanoTime();
         }

         this.wantEnabled = true;
         if (ticks > this.ticksRemaining) {
            this.ticksRemaining = ticks;
         }

         if (!this.registered) {
            MinecraftForge.EVENT_BUS.register(this);
            this.registered = true;
         }

         LOG.info("[SRP Breathe] enableFor({})", ticks);
      }
   }

   public void disable() {
      this.wantEnabled = false;
      this.ticksRemaining = 0;
      LOG.info("[SRP Breathe] disable()");
   }

   @SubscribeEvent
   public void onClientTick(ClientTickEvent e) {
      if (e.phase == Phase.END) {
         Minecraft mc = Minecraft.func_71410_x();
         if (mc.field_71441_e != null && mc.field_71439_g != null) {
            if (this.wantEnabled && this.ticksRemaining > 0) {
               this.ticksRemaining--;
               if (this.ticksRemaining <= 0) {
                  this.wantEnabled = false;
               }
            }

            EntityRenderer er = mc.field_71460_t;
            ShaderGroup current = er.func_147706_e();
            if (!this.wantEnabled) {
               if (current != null) {
                  er.func_181022_b();
                  this.applied = false;
                  LOG.info("[SRP Breathe] stopUseShader()");
               }

               if (this.registered) {
                  MinecraftForge.EVENT_BUS.unregister(this);
                  this.registered = false;
               }
            } else if (this.applied && current != null) {
               int w = mc.field_71443_c;
               int h = mc.field_71440_d;
               if ((w != this.lastW || h != this.lastH) && er.func_147706_e() != null) {
                  er.func_147706_e().func_148026_a(w, h);
                  this.lastW = w;
                  this.lastH = h;
               }

               ShaderGroup sg = er.func_147706_e();
               if (sg != null) {
                  float timeSec = (float)((System.nanoTime() - this.startNs) / 1.0E9);
                  setUniformAll(sg, "SRP_Time", timeSec);
                  setUniformAll(sg, "TintStrength", 0.1F);
               }
            } else {
               try {
                  LOG.info("[SRP Breathe] loadShader {}", POST);
                  long now = System.currentTimeMillis();
                  if (now < this.nextRetryMs) {
                     return;
                  }

                  er.func_175069_a(POST);
                  ShaderGroup sg = er.func_147706_e();
                  if (sg != null) {
                     int wx = mc.field_71443_c;
                     int hx = mc.field_71440_d;
                     sg.func_148026_a(wx, hx);
                     this.lastW = wx;
                     this.lastH = hx;
                     this.applied = true;
                     LOG.info("[SRP Breathe] loaded {}x{}", wx, hx);
                  } else {
                     LOG.error("[SRP Breathe] ShaderGroup is null after loadShader");
                     this.applied = false;
                  }
               } catch (Throwable var10) {
                  this.nextRetryMs = System.currentTimeMillis() + 2000L;
                  LOG.error("[SRP Breathe] loadShader failed", var10);
                  this.applied = false;
               }
            }
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

   private static void setUniformAll(ShaderGroup sg, String name, float v) {
      try {
         List<Shader> shaders = getShaders(sg);
         if (shaders == null) {
            return;
         }

         for (Shader s : shaders) {
            if (s != null) {
               ShaderUniform u = s.func_148043_c().func_147991_a(name);
               if (u != null) {
                  u.func_148090_a(v);
               }
            }
         }
      } catch (Throwable var7) {
      }
   }
}
