package com.dhanantry.scapeandrunparasites.client.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;

public class StarfieldBackground extends Gui {
   private final Minecraft mc;
   private final Random rand = new Random();
   private final List<StarfieldBackground.Star> stars = new ArrayList<>();
   private final List<StarfieldBackground.ShootingStar> shootingStars = new ArrayList<>();
   private float scrollX = 0.0F;
   private float scrollY = 0.0F;
   private long lastFrameTime = System.currentTimeMillis();
   private static final float STAR_SCROLL_SPEED_X = -4.0F;
   private static final float STAR_SCROLL_SPEED_Y = -2.0F;
   private int lastWidth = -1;
   private int lastHeight = -1;
   private long initTime = System.currentTimeMillis();

   public StarfieldBackground(Minecraft mc) {
      this.mc = mc;
   }

   public void render(int mouseX, int mouseY, float partialTicks, boolean jumbled) {
      this.render(mouseX, mouseY, partialTicks, jumbled, false);
   }

   public void render(int mouseX, int mouseY, float partialTicks, boolean jumbled, boolean darkDays) {
      ScaledResolution sr = new ScaledResolution(this.mc);
      int width = sr.func_78326_a();
      int height = sr.func_78328_b();
      if (width != this.lastWidth || height != this.lastHeight || this.stars.isEmpty()) {
         this.rebuild(width, height, jumbled);
         this.lastWidth = width;
         this.lastHeight = height;
      }

      long nowMs = System.currentTimeMillis();
      float delta = (float)(nowMs - this.lastFrameTime) / 1000.0F;
      this.lastFrameTime = nowMs;
      float scrollMul = jumbled ? 1.35F : 1.0F;
      this.scrollX += -4.0F * delta * scrollMul;
      this.scrollY += -2.0F * delta * scrollMul;
      long now = System.currentTimeMillis();
      float time = (float)(now - this.initTime) / 1000.0F;
      this.drawGradientSky(width, height, jumbled, darkDays);
      if (!darkDays) {
         GlStateManager.func_179090_x();
         GlStateManager.func_179147_l();
         GlStateManager.func_179118_c();
         GlStateManager.func_187428_a(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
         this.renderStars(width, height, time, jumbled);
         this.updateAndRenderShootingStars(width, height, time, partialTicks, jumbled);
         GlStateManager.func_179141_d();
         GlStateManager.func_179098_w();
         GlStateManager.func_179084_k();
      }
   }

   private void rebuild(int width, int height, boolean jumbled) {
      this.stars.clear();
      this.shootingStars.clear();
      int starCount = jumbled ? Math.max(85, width * height / 5200) : Math.max(45, width * height / 9000);

      for (int i = 0; i < starCount; i++) {
         StarfieldBackground.Star s = new StarfieldBackground.Star();
         s.x = this.rand.nextFloat() * Math.max(1, width);
         s.y = this.rand.nextFloat() * Math.max(1, height);
         s.size = this.rand.nextBoolean() ? 1 : 2;
         s.baseAlpha = 0.35F + this.rand.nextFloat() * 0.45F;
         s.twinkleSpeed = 0.6F + this.rand.nextFloat() * 1.4F;
         s.twinkleOffset = this.rand.nextFloat() * 6.28318F;
         this.stars.add(s);
      }

      int shootingCount = jumbled ? 10 : 3;

      for (int i = 0; i < shootingCount; i++) {
         float stagger = jumbled ? i * 0.22F : i * 0.9F;
         this.shootingStars.add(this.createOffscreenShootingStar(width, height, stagger, jumbled));
      }
   }

   private StarfieldBackground.ShootingStar createOffscreenShootingStar(int width, int height, float extraDelay, boolean jumbled) {
      StarfieldBackground.ShootingStar s = new StarfieldBackground.ShootingStar();
      s.reset(width, height, this.rand, extraDelay, jumbled);
      return s;
   }

   private void drawGradientSky(int width, int height, boolean jumbled, boolean darkDays) {
      int top;
      int mid;
      int bottom;
      if (darkDays) {
         top = -16777216;
         mid = -16777216;
         bottom = -16777216;
      } else if (jumbled) {
         top = -16120560;
         mid = -15595238;
         bottom = -16449017;
      } else {
         top = -16448251;
         mid = -16119284;
         bottom = -16645630;
      }

      this.func_73733_a(0, 0, width, (int)(height * 0.7F), top, mid);
      this.func_73733_a(0, (int)(height * 0.7F), width, height, mid, bottom);
   }

   private void renderStars(int width, int height, float time, boolean jumbled) {
      for (StarfieldBackground.Star s : this.stars) {
         float pulse = 0.5F + 0.5F * (float)Math.sin(time * s.twinkleSpeed + s.twinkleOffset);
         float alpha = s.baseAlpha * (0.55F + pulse * 0.45F);
         int a = this.clamp255((int)(alpha * 255.0F));
         int baseRgb = jumbled ? 12088575 : 16777215;
         int glowRgb = jumbled ? 14264319 : 16777215;
         int color = a << 24 | baseRgb;
         float sx = this.wrap(s.x + this.scrollX, width);
         float sy = this.wrap(s.y + this.scrollY, height);
         int ix = (int)sx;
         int iy = (int)sy;
         Gui.func_73734_a(ix, iy, ix + s.size, iy + s.size, color);
         if (s.size > 1 && a > 60) {
            int glowA = this.clamp255(jumbled ? a / 3 : a / 4);
            int glowColor = glowA << 24 | glowRgb;
            Gui.func_73734_a(ix - 1, iy, ix + s.size + 1, iy + 1, glowColor);
            Gui.func_73734_a(ix, iy - 1, ix + 1, iy + s.size + 1, glowColor);
         }

         if (sx < 2.0F) {
            Gui.func_73734_a(ix + width, iy, ix + width + s.size, iy + s.size, color);
         }

         if (sy < 2.0F) {
            Gui.func_73734_a(ix, iy + height, ix + s.size, iy + height + s.size, color);
         }

         if (sx < 2.0F && sy < 2.0F) {
            Gui.func_73734_a(ix + width, iy + height, ix + width + s.size, iy + height + s.size, color);
         }
      }
   }

   private float wrap(float value, int max) {
      if (max <= 0) {
         return 0.0F;
      } else {
         value %= max;
         if (value < 0.0F) {
            value += max;
         }

         return value;
      }
   }

   private void updateAndRenderShootingStars(int width, int height, float time, float partialTicks, boolean jumbled) {
      for (StarfieldBackground.ShootingStar s : this.shootingStars) {
         s.update(width, height, this.rand, jumbled);
         if (s.active) {
            float progress = s.getProgress();
            if (!(progress <= 0.0F) && !(progress >= 1.0F)) {
               float alphaMul = 1.0F - progress;
               int headAlpha = this.clamp255((int)(alphaMul * (jumbled ? 235.0F : 220.0F)));
               int tailAlpha = this.clamp255((int)(alphaMul * (jumbled ? 120.0F : 90.0F)));
               float headX = s.x;
               float headY = s.y;
               float tailX = s.x - s.dirX * s.length;
               float tailY = s.y - s.dirY * s.length;
               this.drawTrail((int)headX, (int)headY, (int)tailX, (int)tailY, headAlpha, tailAlpha, jumbled);
               int headColor = headAlpha << 24 | (jumbled ? 14264319 : 16777215);
               Gui.func_73734_a((int)headX, (int)headY, (int)headX + 2, (int)headY + 2, headColor);
            }
         }
      }
   }

   private void drawTrail(int x0, int y0, int x1, int y1, int headAlpha, int tailAlpha, boolean jumbled) {
      int dx = x1 - x0;
      int dy = y1 - y0;
      int steps = Math.max(Math.abs(dx), Math.abs(dy));
      if (steps > 0) {
         int rgb = jumbled ? 11032055 : 16777215;

         for (int i = 0; i <= steps; i++) {
            float t = (float)i / steps;
            int x = x0 + Math.round(dx * t);
            int y = y0 + Math.round(dy * t);
            int alpha = (int)(headAlpha + (tailAlpha - headAlpha) * t);
            alpha = this.clamp255(alpha);
            int color = alpha << 24 | rgb;
            Gui.func_73734_a(x, y, x + 1, y + 1, color);
         }
      }
   }

   private int clamp255(int v) {
      if (v < 0) {
         return 0;
      } else {
         return v > 255 ? 255 : v;
      }
   }

   private static class ShootingStar {
      float x;
      float y;
      float startX;
      float startY;
      float endX;
      float endY;
      float dirX;
      float dirY;
      float length;
      long startTime;
      long durationMs;
      long nextSpawnTime;
      boolean active;

      private ShootingStar() {
      }

      void reset(int width, int height, Random rand, float extraDelaySeconds, boolean jumbled) {
         if (jumbled) {
            int side = rand.nextInt(4);
            switch (side) {
               case 0:
                  this.startX = -30.0F - rand.nextInt(80);
                  this.startY = rand.nextFloat() * height;
                  break;
               case 1:
                  this.startX = width + 30.0F + rand.nextInt(80);
                  this.startY = rand.nextFloat() * height;
                  break;
               case 2:
                  this.startX = rand.nextFloat() * width;
                  this.startY = -30.0F - rand.nextInt(80);
                  break;
               default:
                  this.startX = rand.nextFloat() * width;
                  this.startY = height + 30.0F + rand.nextInt(80);
            }

            this.endX = rand.nextFloat() * width;
            this.endY = rand.nextFloat() * height;

            for (float tries = 0.0F; this.distanceSq(this.startX, this.startY, this.endX, this.endY) < 12000.0F && tries < 6.0F; tries++) {
               this.endX = rand.nextFloat() * width;
               this.endY = rand.nextFloat() * height;
            }
         } else {
            int side = rand.nextInt(3);
            switch (side) {
               case 0:
                  this.startX = -40.0F - rand.nextInt(80);
                  this.startY = rand.nextFloat() * height;
                  this.endX = width + 120.0F;
                  this.endY = this.startY + (-40.0F + rand.nextFloat() * (height * 0.35F + 80.0F));
                  break;
               case 1:
                  this.startX = rand.nextFloat() * width;
                  this.startY = -30.0F - rand.nextInt(60);
                  this.endX = this.startX + (width * 0.35F + rand.nextFloat() * (width * 0.45F));
                  this.endY = height + 80.0F;
                  break;
               default:
                  this.startX = width + 40.0F + rand.nextInt(80);
                  this.startY = rand.nextFloat() * height;
                  this.endX = -120.0F;
                  this.endY = this.startY + (-40.0F + rand.nextFloat() * (height * 0.35F + 80.0F));
            }
         }

         float dx = this.endX - this.startX;
         float dy = this.endY - this.startY;
         float len = (float)Math.sqrt(dx * dx + dy * dy);
         if (len < 0.001F) {
            dx = 1.0F;
            dy = 0.0F;
            len = 1.0F;
         }

         this.dirX = dx / len;
         this.dirY = dy / len;
         this.length = jumbled ? 32.0F + rand.nextFloat() * 45.0F : 20.0F + rand.nextFloat() * 35.0F;
         this.durationMs = jumbled ? 450L + rand.nextInt(350) : 900L + rand.nextInt(700);
         long now = System.currentTimeMillis();
         this.startTime = now + (long)(extraDelaySeconds * 1000.0F);
         this.nextSpawnTime = now + (jumbled ? 180L + rand.nextInt(420) : 2500L + rand.nextInt(3000));
         this.x = this.startX;
         this.y = this.startY;
         this.active = false;
      }

      void update(int width, int height, Random rand, boolean jumbled) {
         long now = System.currentTimeMillis();
         if (!this.active) {
            if (now < this.startTime) {
               return;
            }

            this.active = true;
         }

         float progress = this.getProgress();
         if (progress >= 1.0F) {
            this.active = false;
            this.reset(width, height, rand, jumbled ? 0.12F + rand.nextFloat() * 0.25F : 0.7F + rand.nextFloat() * 2.4F, jumbled);
         } else {
            this.x = this.startX + (this.endX - this.startX) * progress;
            this.y = this.startY + (this.endY - this.startY) * progress;
         }
      }

      private float distanceSq(float x1, float y1, float x2, float y2) {
         float dx = x2 - x1;
         float dy = y2 - y1;
         return dx * dx + dy * dy;
      }

      float getProgress() {
         long now = System.currentTimeMillis();
         return (float)(now - this.startTime) / (float)this.durationMs;
      }
   }

   private static class Star {
      float x;
      float y;
      int size;
      float baseAlpha;
      float twinkleSpeed;
      float twinkleOffset;

      private Star() {
      }
   }
}
