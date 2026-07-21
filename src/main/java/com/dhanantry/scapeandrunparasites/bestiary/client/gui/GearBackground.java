package com.dhanantry.scapeandrunparasites.bestiary.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.MathHelper;

public class GearBackground extends Gui {
   private final Minecraft mc;

   public GearBackground(Minecraft mc) {
      this.mc = mc;
   }

   public void render(float partialTicks, boolean jumbled) {
      ScaledResolution sr = new ScaledResolution(this.mc);
      int width = sr.func_78326_a();
      int height = sr.func_78328_b();
      this.drawBackgroundGradient(width, height);
      this.drawBackdropGrid(width, height);
      GlStateManager.func_179147_l();
      GlStateManager.func_179090_x();
      this.drawMovingCorridor(width, height);
      this.drawSideMasks(width, height);
      this.drawFloatingSquares(width, height, jumbled);
      int baseX = width / 2 + 5;
      int baseY = height / 2 - 6;
      float fastSpeed = jumbled ? 2.2F : 1.0F;
      float slowSpeed = jumbled ? 2.0F : 1.0F;
      float tFastCCW = this.getAngleSeconds(3.0F / fastSpeed, false);
      float tFastCW = this.getAngleSeconds(3.0F / fastSpeed, true);
      float tSlowCCW = this.getAngleSeconds(6.0F / slowSpeed, false);
      float tSlowCW = this.getAngleSeconds(7.5F / slowSpeed, true);
      this.drawGear(baseX - 205, baseY + 6, 46, tSlowCW, false, 0.18F);
      this.drawGear(baseX - 125, baseY - 68, 56, tFastCCW, false, 0.22F);
      this.drawGear(baseX - 52, baseY + 2, 56, tFastCW, false, 0.22F);
      this.drawGear(baseX - 125, baseY + 74, 56, tFastCCW, false, 0.22F);
      this.drawGear(baseX + 84, baseY - 60, 102, tSlowCCW, true, 0.2F);
      this.drawGear(width - 78, height - 36, 66, this.getAngleSeconds(8.0F / slowSpeed, true), true, 0.1F);
      this.drawGear(width - 28, 48, 48, this.getAngleSeconds(7.0F / slowSpeed, false), false, 0.08F);
      this.drawGear(width - 170, 30, 38, this.getAngleSeconds(9.0F / slowSpeed, true), false, 0.07F);
      this.drawRotationalSparks(baseX - 205, baseY + 6, 46, tSlowCW, true, jumbled ? 0.95F : 0.75F, 0.2F, jumbled);
      this.drawRotationalSparks(baseX - 125, baseY - 68, 56, tFastCCW, false, jumbled ? 1.0F : 0.82F, 1.1F, jumbled);
      this.drawRotationalSparks(baseX - 52, baseY + 2, 56, tFastCW, true, jumbled ? 1.0F : 0.82F, 2.0F, jumbled);
      this.drawRotationalSparks(baseX - 125, baseY + 74, 56, tFastCCW, false, jumbled ? 0.96F : 0.78F, 2.8F, jumbled);
      this.drawRotationalSparks(baseX + 84, baseY - 60, 102, tSlowCCW, false, jumbled ? 0.88F : 0.7F, 3.4F, jumbled);
      GlStateManager.func_179098_w();
      GlStateManager.func_179084_k();
      this.drawVignette(width, height);
   }

   private float getTimeSeconds() {
      return (float)(Minecraft.func_71386_F() % 1000000L) / 1000.0F;
   }

   private float getAngleSeconds(float secondsPerRotation, boolean clockwise) {
      float time = this.getTimeSeconds();
      float turns = time / secondsPerRotation;
      float angle = turns * 360.0F % 360.0F;
      return clockwise ? angle : -angle;
   }

   private void drawSideMasks(int width, int height) {
      int maskW = 54;
      this.func_73733_a(0, 0, maskW, height, -872086267, 570754309);
      this.func_73733_a(maskW, 0, maskW + 22, height, 570754309, 0);
      this.func_73733_a(width - maskW, 0, width, height, 570754309, -872086267);
      this.func_73733_a(width - maskW - 22, 0, width - maskW, height, 0, 570754309);
   }

   private void drawBackgroundGradient(int width, int height) {
      int top = -14869993;
      int mid = -15462130;
      int bottom = -15988215;
      this.func_73733_a(0, 0, width, (int)(height * 0.6F), top, mid);
      this.func_73733_a(0, (int)(height * 0.6F), width, height, mid, bottom);
   }

   private void drawBackdropGrid(int width, int height) {
      int gridColor = 176977744;
      int spacing = 24;

      for (int x = 0; x < width; x += spacing) {
         func_73734_a(x, 0, x + 1, height, gridColor);
      }

      for (int y = 0; y < height; y += spacing) {
         func_73734_a(0, y, width, y + 1, gridColor);
      }
   }

   private void drawMovingCorridor(int width, int height) {
      float time = this.getTimeSeconds();
      int spacing = 78;
      int pillarW = 18;
      int offset = (int)(time * 22.0F % spacing);

      for (int x = -spacing; x < width + spacing; x += spacing) {
         int px = x + offset;
         func_73734_a(px, 0, px + pillarW, height, 243954768);
         func_73734_a(px + pillarW / 2 - 1, 0, px + pillarW / 2 + 1, height, 312378200);

         for (int y = -20; y < height + 40; y += 48) {
            func_73734_a(px - 2, y, px + pillarW + 2, y + 18, 137111067);
            func_73734_a(px, y + 2, px + pillarW, y + 16, 222969387);
         }
      }

      int spacing2 = 132;
      int pillarW2 = 28;
      int offset2 = (int)(time * 11.0F % spacing2);

      for (int x = -spacing2; x < width + spacing2; x += spacing2) {
         int px = x + offset2;
         func_73734_a(px, 0, px + pillarW2, height, 136519192);
         func_73734_a(px + 2, 0, px + 4, height, 224021298);
         func_73734_a(px + pillarW2 - 4, 0, px + pillarW2 - 2, height, 224021298);
      }
   }

   private void drawVignette(int width, int height) {
      this.func_73733_a(0, 0, width, 28, 1711276032, 0);
      this.func_73733_a(0, height - 28, width, height, 0, 2046820352);
      this.func_73733_a(0, 0, 28, height, 1241513984, 0);
      this.func_73733_a(width - 28, 0, width, height, 0, 1711276032);
   }

   private void drawGear(int cx, int cy, int radius, float angleDeg, boolean large, float alphaMul) {
      GlStateManager.func_179094_E();
      GlStateManager.func_179109_b(cx, cy, 0.0F);
      GlStateManager.func_179114_b(angleDeg, 0.0F, 0.0F, 1.0F);
      int bodyColor = this.applyAlpha(6183508, alphaMul);
      int toothColor = this.applyAlpha(6841180, alphaMul);
      int rimLightColor = this.applyAlpha(8551024, alphaMul * 0.85F);
      int rimDarkColor = this.applyAlpha(3420461, alphaMul * 0.85F);
      int innerHoleColor = this.applyAlpha(1184016, alphaMul * 0.95F);
      int spokeColor = this.applyAlpha(6051665, alphaMul * 0.95F);
      int toothCount = large ? 12 : 10;
      int toothLen = large ? 12 : 9;
      int toothW = Math.max(8, radius / 4);

      for (int i = 0; i < toothCount; i++) {
         GlStateManager.func_179094_E();
         GlStateManager.func_179114_b(i * (360.0F / toothCount), 0.0F, 0.0F, 1.0F);
         this.drawCenteredRect(0, -radius - toothLen / 2 + 2, toothW, toothLen, toothColor);
         GlStateManager.func_179121_F();
      }

      this.fillCircle(0, 0, radius, bodyColor);
      this.drawCircleRing(0, 0, radius - 1, 1, rimLightColor);
      this.drawCircleRing(0, 0, radius - 5, 1, rimDarkColor);
      int spokeCount = 6;
      int spokeWidth = radius * 2 + (large ? 18 : 12);
      int spokeHeight = Math.max(8, radius / 5);

      for (int i = 0; i < spokeCount; i++) {
         GlStateManager.func_179094_E();
         GlStateManager.func_179114_b(i * (360.0F / spokeCount), 0.0F, 0.0F, 1.0F);
         this.drawCenteredRect(0, 0, spokeWidth, spokeHeight, spokeColor);
         GlStateManager.func_179121_F();
      }

      this.fillCircle(0, 0, radius - 7, bodyColor);
      int innerR = large ? radius - 20 : radius - 14;
      this.fillCircle(0, 0, Math.max(10, innerR), innerHoleColor);
      this.fillCircle(0, 0, Math.max(4, innerR / 3), this.applyAlpha(2828582, alphaMul * 0.9F));
      GlStateManager.func_179121_F();
   }

   private void drawRotationalSparks(int cx, int cy, int radius, float gearAngleDeg, boolean clockwise, float alphaMul, float phaseOffset, boolean jumbled) {
      float time = this.getTimeSeconds();
      int sparkCount = jumbled ? 7 : 4;
      float timeMul = jumbled ? 13.5F : 8.5F;
      float burstMul = jumbled ? 3.2F : 2.4F;
      float threshold = jumbled ? 0.56F : 0.72F;

      for (int i = 0; i < sparkCount; i++) {
         float localTime = time * timeMul + phaseOffset + i * 1.37F;
         float burst = 0.5F + 0.5F * MathHelper.func_76126_a(localTime * burstMul);
         if (!(burst < threshold)) {
            float attachAngle = gearAngleDeg + phaseOffset * 50.0F + i * 73.0F;
            float attachRad = (float)Math.toRadians(attachAngle);
            float rimDist = radius * (0.82F + 0.06F * (i % 2));
            int px = cx + (int)(MathHelper.func_76134_b(attachRad) * rimDist);
            int py = cy + (int)(MathHelper.func_76126_a(attachRad) * rimDist);
            float tangentDeg = attachAngle + (clockwise ? 90.0F : -90.0F);
            float tangentRad = (float)Math.toRadians(tangentDeg);
            float outwardX = MathHelper.func_76134_b(attachRad);
            float outwardY = MathHelper.func_76126_a(attachRad);
            float tangentX = MathHelper.func_76134_b(tangentRad);
            float tangentY = MathHelper.func_76126_a(tangentRad);
            float dirX = tangentX * 0.82F + outwardX * 0.38F;
            float dirY = tangentY * 0.82F + outwardY * 0.38F;
            float len = MathHelper.func_76129_c(dirX * dirX + dirY * dirY);
            if (len > 1.0E-4F) {
               dirX /= len;
               dirY /= len;
            }

            int streakLen = jumbled ? 12 + i % 3 * 4 : 8 + i % 3 * 3;
            this.drawSparkStreak(px, py, dirX, dirY, streakLen, alphaMul * burst, jumbled);
         }
      }
   }

   private void drawSparkStreak(int x, int y, float dirX, float dirY, int length, float alphaMul, boolean jumbled) {
      float spacing = jumbled ? 2.5F : 2.0F;

      for (int i = 0; i < length; i++) {
         float t = (float)i / Math.max(1, length - 1);
         int sx = x + Math.round(dirX * i * spacing);
         int sy = y + Math.round(dirY * i * spacing);
         float fade = 1.0F - t;
         float brightMul = alphaMul * fade;
         if (jumbled) {
            if (i == 0) {
               int color = this.applyAlpha(14264319, brightMul);
               func_73734_a(sx, sy, sx + 2, sy + 2, color);
            } else if (i < 3) {
               int color = this.applyAlpha(11032055, brightMul * 0.9F);
               func_73734_a(sx, sy, sx + 2, sy + 1, color);
            } else {
               int color = this.applyAlpha(5974662, brightMul * 0.75F);
               func_73734_a(sx, sy, sx + 1, sy + 1, color);
            }
         } else if (i == 0) {
            int color = this.applyAlpha(16770979, brightMul);
            func_73734_a(sx, sy, sx + 2, sy + 2, color);
         } else if (i < 3) {
            int color = this.applyAlpha(16761946, brightMul * 0.9F);
            func_73734_a(sx, sy, sx + 2, sy + 1, color);
         } else {
            int color = this.applyAlpha(10771480, brightMul * 0.75F);
            func_73734_a(sx, sy, sx + 1, sy + 1, color);
         }
      }
   }

   private void drawCenteredRect(int cx, int cy, int w, int h, int color) {
      func_73734_a(cx - w / 2, cy - h / 2, cx + w / 2, cy + h / 2, color);
   }

   private void fillCircle(int cx, int cy, int r, int color) {
      for (int y = -r; y <= r; y++) {
         int dx = (int)MathHelper.func_76129_c(r * r - y * y);
         func_73734_a(cx - dx, cy + y, cx + dx + 1, cy + y + 1, color);
      }
   }

   private void drawCircleRing(int cx, int cy, int r, int thickness, int color) {
      for (int y = -r; y <= r; y++) {
         int outerDx = (int)MathHelper.func_76129_c(r * r - y * y);
         int innerR = Math.max(0, r - thickness);
         int innerDx = innerR > 0 && innerR * innerR - y * y >= 0 ? (int)MathHelper.func_76129_c(innerR * innerR - y * y) : 0;
         func_73734_a(cx - outerDx, cy + y, cx - innerDx, cy + y + 1, color);
         func_73734_a(cx + innerDx + 1, cy + y, cx + outerDx + 1, cy + y + 1, color);
      }
   }

   private int applyAlpha(int rgb, float alphaMul) {
      int a = MathHelper.func_76125_a((int)(255.0F * alphaMul), 0, 255);
      return a << 24 | rgb & 16777215;
   }

   private void drawFloatingSquares(int width, int height, boolean jumbled) {
      float time = this.getTimeSeconds();
      int count = jumbled ? 18 : 10;

      for (int i = 0; i < count; i++) {
         float seed = 17.0F + i * 23.713F;
         float baseXNorm = 0.3F + 0.58F * this.frac(seed * 0.173F);
         int x = (int)(width * baseXNorm);
         int size = jumbled ? 8 + i % 4 * 4 : 10 + i % 3 * 4;
         float phase = seed * 1.37F;
         int y;
         if (jumbled) {
            float speed = 18.0F + i % 5 * 5.0F;
            float start = this.frac(seed * 0.417F) * (height + 120.0F) - 60.0F;
            if ((i & 1) == 0) {
               y = (int)((start - time * speed) % (height + 120.0F));
            } else {
               y = (int)((start + time * speed) % (height + 120.0F));
            }

            y -= 60;
         } else {
            float baseYNorm = 0.18F + 0.64F * this.frac(seed * 0.291F);
            int baseY = (int)(height * baseYNorm);
            float sway = MathHelper.func_76126_a(time * (0.7F + i % 4 * 0.18F) + phase) * (8.0F + i % 3 * 3.0F);
            y = baseY + (int)sway;
         }

         this.drawFloatingSquare(x, y, size, jumbled, seed, time);
      }
   }

   private void drawFloatingSquare(int x, int y, int size, boolean jumbled, float seed, float time) {
      float pulse = 0.65F + 0.35F * (0.5F + 0.5F * MathHelper.func_76126_a(time * 2.2F + seed));
      int outer;
      int inner;
      int highlight;
      if (jumbled) {
         outer = this.applyAlpha(5974662, 0.16F * pulse);
         inner = this.applyAlpha(11032055, 0.11F * pulse);
         highlight = this.applyAlpha(14264319, 0.16F * pulse);
      } else {
         outer = this.applyAlpha(6183508, 0.1F * pulse);
         inner = this.applyAlpha(9075299, 0.07F * pulse);
         highlight = this.applyAlpha(12167824, 0.1F * pulse);
      }

      func_73734_a(x, y, x + size, y + size, outer);
      if (size > 4) {
         func_73734_a(x + 1, y + 1, x + size - 1, y + size - 1, inner);
      }

      func_73734_a(x, y, x + size, y + 1, highlight);
      func_73734_a(x, y, x + 1, y + size, highlight);
      if (jumbled && (int)seed % 3 == 0) {
         int s2 = Math.max(3, size / 3);
         int ox = x + size + 3;
         int oy = y - 2 + (int)(MathHelper.func_76126_a(time * 5.0F + seed) * 2.0F);
         int c2 = this.applyAlpha(12088575, 0.14F * pulse);
         func_73734_a(ox, oy, ox + s2, oy + s2, c2);
      }
   }

   private float frac(float v) {
      return v - (float)Math.floor(v);
   }
}
