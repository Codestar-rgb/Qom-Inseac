package com.dhanantry.scapeandrunparasites.client;

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.EntityViewRenderEvent.CameraSetup;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Post;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

public final class ClientQlipShake {
   public static final ClientQlipShake INSTANCE = new ClientQlipShake();
   private static final int RAMP_UP_TICKS = 10;
   private static final int HOLD_TICKS = 10;
   private static final int FADE_TICKS = 20;
   private static final int TOTAL_TICKS = 40;
   private int ticksLeft = 0;
   private final Random rng = new Random();
   private int delayLeft = 0;
   private int elapsed = 40;
   private boolean darkScreen;
   private boolean shakeScreen;
   private float shakeScreenValue;
   private int durationShake;

   private ClientQlipShake() {
   }

   public void triggerDelayed(int durationTicks, int delayTicks, boolean dark, boolean shake, float value) {
      this.shakeScreen = shake;
      this.darkScreen = dark;
      this.durationShake = durationTicks;
      this.shakeScreenValue = value;
      this.delayLeft = Math.max(this.delayLeft, Math.max(0, delayTicks));
      if (this.elapsed == 40) {
         this.elapsed = 0;
      }

      if (this.elapsed >= 10) {
         this.elapsed = 10;
      }
   }

   @SubscribeEvent
   public void onClientTick(ClientTickEvent e) {
      if (e.phase == Phase.END) {
         if (Minecraft.func_71410_x().field_71439_g != null) {
            if (this.delayLeft > 0) {
               this.delayLeft--;
            } else if (this.elapsed < 40) {
               this.elapsed++;
            }
         }
      }
   }

   private float intensity() {
      this.durationShake--;
      if (this.delayLeft > 0 || this.elapsed <= 0 || this.elapsed > 40) {
         return 0.0F;
      } else if (this.elapsed <= 10) {
         return this.elapsed / 10.0F;
      } else {
         int afterRamp = this.elapsed - 10;
         if (afterRamp <= 10) {
            return 1.0F;
         } else if (this.durationShake > 0) {
            this.elapsed = 30;
            return 1.0F;
         } else {
            int afterHold = afterRamp - 10;
            return Math.max(0.0F, 1.0F - afterHold / 20.0F);
         }
      }
   }

   @SubscribeEvent
   public void onCameraSetup(CameraSetup e) {
      float k = this.intensity();
      if (!(k <= 0.0F) && this.shakeScreen) {
         float strengthDeg = this.shakeScreenValue * k;
         e.setYaw(e.getYaw() + (this.rng.nextFloat() - 0.5F) * 2.0F * strengthDeg);
         e.setPitch(e.getPitch() + (this.rng.nextFloat() - 0.5F) * 2.0F * strengthDeg);
         e.setRoll(e.getRoll() + (this.rng.nextFloat() - 0.5F) * 2.0F * (strengthDeg * 0.7F));
      }
   }

   @SubscribeEvent
   public void onOverlayPost(Post e) {
      if (this.darkScreen) {
         if (e.getType() == ElementType.ALL) {
            float k = this.intensity();
            if (!(k <= 0.0F)) {
               int w = e.getResolution().func_78326_a();
               int h = e.getResolution().func_78328_b();
               float maxAlpha = 0.6F;
               int a = (int)(maxAlpha * k * 255.0F) << 24;
               Gui.func_73734_a(0, 0, w, h, 0 | a);
            }
         }
      }
   }
}
