/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.util.math.MathHelper
 */
package com.dhanantry.scapeandrunparasites.bestiary.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.MathHelper;

public class GearBackground
extends Gui {
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
        float fastSpeed = jumbled ? 2.2f : 1.0f;
        float slowSpeed = jumbled ? 2.0f : 1.0f;
        float tFastCCW = this.getAngleSeconds(3.0f / fastSpeed, false);
        float tFastCW = this.getAngleSeconds(3.0f / fastSpeed, true);
        float tSlowCCW = this.getAngleSeconds(6.0f / slowSpeed, false);
        float tSlowCW = this.getAngleSeconds(7.5f / slowSpeed, true);
        this.drawGear(baseX - 205, baseY + 6, 46, tSlowCW, false, 0.18f);
        this.drawGear(baseX - 125, baseY - 68, 56, tFastCCW, false, 0.22f);
        this.drawGear(baseX - 52, baseY + 2, 56, tFastCW, false, 0.22f);
        this.drawGear(baseX - 125, baseY + 74, 56, tFastCCW, false, 0.22f);
        this.drawGear(baseX + 84, baseY - 60, 102, tSlowCCW, true, 0.2f);
        this.drawGear(width - 78, height - 36, 66, this.getAngleSeconds(8.0f / slowSpeed, true), true, 0.1f);
        this.drawGear(width - 28, 48, 48, this.getAngleSeconds(7.0f / slowSpeed, false), false, 0.08f);
        this.drawGear(width - 170, 30, 38, this.getAngleSeconds(9.0f / slowSpeed, true), false, 0.07f);
        this.drawRotationalSparks(baseX - 205, baseY + 6, 46, tSlowCW, true, jumbled ? 0.95f : 0.75f, 0.2f, jumbled);
        this.drawRotationalSparks(baseX - 125, baseY - 68, 56, tFastCCW, false, jumbled ? 1.0f : 0.82f, 1.1f, jumbled);
        this.drawRotationalSparks(baseX - 52, baseY + 2, 56, tFastCW, true, jumbled ? 1.0f : 0.82f, 2.0f, jumbled);
        this.drawRotationalSparks(baseX - 125, baseY + 74, 56, tFastCCW, false, jumbled ? 0.96f : 0.78f, 2.8f, jumbled);
        this.drawRotationalSparks(baseX + 84, baseY - 60, 102, tSlowCCW, false, jumbled ? 0.88f : 0.7f, 3.4f, jumbled);
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
        this.drawVignette(width, height);
    }

    private float getTimeSeconds() {
        GearBackground gearBackground = this;
        return (float)(gearBackground.mc.func_71386_F() % 1000000L) / 1000.0f;
    }

    private float getAngleSeconds(float secondsPerRotation, boolean clockwise) {
        float time = this.getTimeSeconds();
        float turns = time / secondsPerRotation;
        float angle = turns * 360.0f % 360.0f;
        return clockwise ? angle : -angle;
    }

    private void drawSideMasks(int width, int height) {
        int maskW = 54;
        this.func_73733_a(0, 0, maskW, height, -872086267, 0x22050505);
        this.func_73733_a(maskW, 0, maskW + 22, height, 0x22050505, 0);
        this.func_73733_a(width - maskW, 0, width, height, 0x22050505, -872086267);
        this.func_73733_a(width - maskW - 22, 0, width - maskW, height, 0, 0x22050505);
    }

    private void drawBackgroundGradient(int width, int height) {
        int top = -14869993;
        int mid = -15462130;
        int bottom = -15988215;
        this.func_73733_a(0, 0, width, (int)((float)height * 0.6f), top, mid);
        this.func_73733_a(0, (int)((float)height * 0.6f), width, height, mid, bottom);
    }

    private void drawBackdropGrid(int width, int height) {
        int gridColor = 176977744;
        int spacing = 24;
        for (int x = 0; x < width; x += spacing) {
            GearBackground.func_73734_a((int)x, (int)0, (int)(x + 1), (int)height, (int)gridColor);
        }
        for (int y = 0; y < height; y += spacing) {
            GearBackground.func_73734_a((int)0, (int)y, (int)width, (int)(y + 1), (int)gridColor);
        }
    }

    private void drawMovingCorridor(int width, int height) {
        float time = this.getTimeSeconds();
        int spacing = 78;
        int pillarW = 18;
        int offset = (int)(time * 22.0f % (float)spacing);
        for (int x = -spacing; x < width + spacing; x += spacing) {
            int px = x + offset;
            GearBackground.func_73734_a((int)px, (int)0, (int)(px + pillarW), (int)height, (int)243954768);
            GearBackground.func_73734_a((int)(px + pillarW / 2 - 1), (int)0, (int)(px + pillarW / 2 + 1), (int)height, (int)312378200);
            for (int y = -20; y < height + 40; y += 48) {
                GearBackground.func_73734_a((int)(px - 2), (int)y, (int)(px + pillarW + 2), (int)(y + 18), (int)137111067);
                GearBackground.func_73734_a((int)px, (int)(y + 2), (int)(px + pillarW), (int)(y + 16), (int)222969387);
            }
        }
        int spacing2 = 132;
        int pillarW2 = 28;
        int offset2 = (int)(time * 11.0f % (float)spacing2);
        for (int x = -spacing2; x < width + spacing2; x += spacing2) {
            int px = x + offset2;
            GearBackground.func_73734_a((int)px, (int)0, (int)(px + pillarW2), (int)height, (int)136519192);
            GearBackground.func_73734_a((int)(px + 2), (int)0, (int)(px + 4), (int)height, (int)224021298);
            GearBackground.func_73734_a((int)(px + pillarW2 - 4), (int)0, (int)(px + pillarW2 - 2), (int)height, (int)224021298);
        }
    }

    private void drawVignette(int width, int height) {
        this.func_73733_a(0, 0, width, 28, 0x66000000, 0);
        this.func_73733_a(0, height - 28, width, height, 0, 0x7A000000);
        this.func_73733_a(0, 0, 28, height, 0x4A000000, 0);
        this.func_73733_a(width - 28, 0, width, height, 0, 0x66000000);
    }

    private void drawGear(int cx, int cy, int radius, float angleDeg, boolean large, float alphaMul) {
        GlStateManager.func_179094_E();
        GlStateManager.func_179109_b((float)cx, (float)cy, (float)0.0f);
        GlStateManager.func_179114_b((float)angleDeg, (float)0.0f, (float)0.0f, (float)1.0f);
        int bodyColor = this.applyAlpha(6183508, alphaMul);
        int toothColor = this.applyAlpha(6841180, alphaMul);
        int rimLightColor = this.applyAlpha(8551024, alphaMul * 0.85f);
        int rimDarkColor = this.applyAlpha(3420461, alphaMul * 0.85f);
        int innerHoleColor = this.applyAlpha(0x121110, alphaMul * 0.95f);
        int spokeColor = this.applyAlpha(6051665, alphaMul * 0.95f);
        int toothCount = large ? 12 : 10;
        int toothLen = large ? 12 : 9;
        int toothW = Math.max(8, radius / 4);
        for (int i = 0; i < toothCount; ++i) {
            GlStateManager.func_179094_E();
            GlStateManager.func_179114_b((float)((float)i * (360.0f / (float)toothCount)), (float)0.0f, (float)0.0f, (float)1.0f);
            this.drawCenteredRect(0, -radius - toothLen / 2 + 2, toothW, toothLen, toothColor);
            GlStateManager.func_179121_F();
        }
        this.fillCircle(0, 0, radius, bodyColor);
        this.drawCircleRing(0, 0, radius - 1, 1, rimLightColor);
        this.drawCircleRing(0, 0, radius - 5, 1, rimDarkColor);
        int spokeCount = 6;
        int spokeWidth = radius * 2 + (large ? 18 : 12);
        int spokeHeight = Math.max(8, radius / 5);
        for (int i = 0; i < spokeCount; ++i) {
            GlStateManager.func_179094_E();
            GlStateManager.func_179114_b((float)((float)i * (360.0f / (float)spokeCount)), (float)0.0f, (float)0.0f, (float)1.0f);
            this.drawCenteredRect(0, 0, spokeWidth, spokeHeight, spokeColor);
            GlStateManager.func_179121_F();
        }
        this.fillCircle(0, 0, radius - 7, bodyColor);
        int innerR = large ? radius - 20 : radius - 14;
        this.fillCircle(0, 0, Math.max(10, innerR), innerHoleColor);
        this.fillCircle(0, 0, Math.max(4, innerR / 3), this.applyAlpha(2828582, alphaMul * 0.9f));
        GlStateManager.func_179121_F();
    }

    private void drawRotationalSparks(int cx, int cy, int radius, float gearAngleDeg, boolean clockwise, float alphaMul, float phaseOffset, boolean jumbled) {
        float time = this.getTimeSeconds();
        int sparkCount = jumbled ? 7 : 4;
        float timeMul = jumbled ? 13.5f : 8.5f;
        float burstMul = jumbled ? 3.2f : 2.4f;
        float threshold = jumbled ? 0.56f : 0.72f;
        for (int i = 0; i < sparkCount; ++i) {
            float tangentY;
            float dirY;
            float localTime = time * timeMul + phaseOffset + (float)i * 1.37f;
            float burst = 0.5f + 0.5f * MathHelper.func_76126_a((float)(localTime * burstMul));
            if (burst < threshold) continue;
            float attachAngle = gearAngleDeg + phaseOffset * 50.0f + (float)i * 73.0f;
            float attachRad = (float)Math.toRadians(attachAngle);
            float rimDist = (float)radius * (0.82f + 0.06f * (float)(i % 2));
            int px = cx + (int)(MathHelper.func_76134_b((float)attachRad) * rimDist);
            int py = cy + (int)(MathHelper.func_76126_a((float)attachRad) * rimDist);
            float tangentDeg = attachAngle + (clockwise ? 90.0f : -90.0f);
            float tangentRad = (float)Math.toRadians(tangentDeg);
            float outwardX = MathHelper.func_76134_b((float)attachRad);
            float outwardY = MathHelper.func_76126_a((float)attachRad);
            float tangentX = MathHelper.func_76134_b((float)tangentRad);
            float dirX = tangentX * 0.82f + outwardX * 0.38f;
            float len = MathHelper.func_76129_c((float)(dirX * dirX + (dirY = (tangentY = MathHelper.func_76126_a((float)tangentRad)) * 0.82f + outwardY * 0.38f) * dirY));
            if (len > 1.0E-4f) {
                dirX /= len;
                dirY /= len;
            }
            int streakLen = jumbled ? 12 + i % 3 * 4 : 8 + i % 3 * 3;
            this.drawSparkStreak(px, py, dirX, dirY, streakLen, alphaMul * burst, jumbled);
        }
    }

    private void drawSparkStreak(int x, int y, float dirX, float dirY, int length, float alphaMul, boolean jumbled) {
        float spacing = jumbled ? 2.5f : 2.0f;
        for (int i = 0; i < length; ++i) {
            int color;
            float t = (float)i / (float)Math.max(1, length - 1);
            int sx = x + Math.round(dirX * (float)i * spacing);
            int sy = y + Math.round(dirY * (float)i * spacing);
            float fade = 1.0f - t;
            float brightMul = alphaMul * fade;
            if (jumbled) {
                if (i == 0) {
                    color = this.applyAlpha(14264319, brightMul);
                    GearBackground.func_73734_a((int)sx, (int)sy, (int)(sx + 2), (int)(sy + 2), (int)color);
                    continue;
                }
                if (i < 3) {
                    color = this.applyAlpha(11032055, brightMul * 0.9f);
                    GearBackground.func_73734_a((int)sx, (int)sy, (int)(sx + 2), (int)(sy + 1), (int)color);
                    continue;
                }
                color = this.applyAlpha(5974662, brightMul * 0.75f);
                GearBackground.func_73734_a((int)sx, (int)sy, (int)(sx + 1), (int)(sy + 1), (int)color);
                continue;
            }
            if (i == 0) {
                color = this.applyAlpha(16770979, brightMul);
                GearBackground.func_73734_a((int)sx, (int)sy, (int)(sx + 2), (int)(sy + 2), (int)color);
                continue;
            }
            if (i < 3) {
                color = this.applyAlpha(16761946, brightMul * 0.9f);
                GearBackground.func_73734_a((int)sx, (int)sy, (int)(sx + 2), (int)(sy + 1), (int)color);
                continue;
            }
            color = this.applyAlpha(10771480, brightMul * 0.75f);
            GearBackground.func_73734_a((int)sx, (int)sy, (int)(sx + 1), (int)(sy + 1), (int)color);
        }
    }

    private void drawCenteredRect(int cx, int cy, int w, int h, int color) {
        GearBackground.func_73734_a((int)(cx - w / 2), (int)(cy - h / 2), (int)(cx + w / 2), (int)(cy + h / 2), (int)color);
    }

    private void fillCircle(int cx, int cy, int r, int color) {
        for (int y = -r; y <= r; ++y) {
            int dx = (int)MathHelper.func_76129_c((float)(r * r - y * y));
            GearBackground.func_73734_a((int)(cx - dx), (int)(cy + y), (int)(cx + dx + 1), (int)(cy + y + 1), (int)color);
        }
    }

    private void drawCircleRing(int cx, int cy, int r, int thickness, int color) {
        for (int y = -r; y <= r; ++y) {
            int outerDx = (int)MathHelper.func_76129_c((float)(r * r - y * y));
            int innerR = Math.max(0, r - thickness);
            int innerDx = innerR > 0 && innerR * innerR - y * y >= 0 ? (int)MathHelper.func_76129_c((float)(innerR * innerR - y * y)) : 0;
            GearBackground.func_73734_a((int)(cx - outerDx), (int)(cy + y), (int)(cx - innerDx), (int)(cy + y + 1), (int)color);
            GearBackground.func_73734_a((int)(cx + innerDx + 1), (int)(cy + y), (int)(cx + outerDx + 1), (int)(cy + y + 1), (int)color);
        }
    }

    private int applyAlpha(int rgb, float alphaMul) {
        int a = MathHelper.func_76125_a((int)((int)(255.0f * alphaMul)), (int)0, (int)255);
        return a << 24 | rgb & 0xFFFFFF;
    }

    private void drawFloatingSquares(int width, int height, boolean jumbled) {
        float time = this.getTimeSeconds();
        int count = jumbled ? 18 : 10;
        for (int i = 0; i < count; ++i) {
            int y;
            float seed = 17.0f + (float)i * 23.713f;
            float baseXNorm = 0.3f + 0.58f * this.frac(seed * 0.173f);
            int x = (int)((float)width * baseXNorm);
            int size = jumbled ? 8 + i % 4 * 4 : 10 + i % 3 * 4;
            float phase = seed * 1.37f;
            if (jumbled) {
                float speed = 18.0f + (float)(i % 5) * 5.0f;
                float start = this.frac(seed * 0.417f) * ((float)height + 120.0f) - 60.0f;
                y = (i & 1) == 0 ? (int)((start - time * speed) % ((float)height + 120.0f)) : (int)((start + time * speed) % ((float)height + 120.0f));
                y -= 60;
            } else {
                float baseYNorm = 0.18f + 0.64f * this.frac(seed * 0.291f);
                int baseY = (int)((float)height * baseYNorm);
                float sway = MathHelper.func_76126_a((float)(time * (0.7f + (float)(i % 4) * 0.18f) + phase)) * (8.0f + (float)(i % 3) * 3.0f);
                y = baseY + (int)sway;
            }
            this.drawFloatingSquare(x, y, size, jumbled, seed, time);
        }
    }

    private void drawFloatingSquare(int x, int y, int size, boolean jumbled, float seed, float time) {
        int highlight;
        int inner;
        int outer;
        float pulse = 0.65f + 0.35f * (0.5f + 0.5f * MathHelper.func_76126_a((float)(time * 2.2f + seed)));
        if (jumbled) {
            outer = this.applyAlpha(5974662, 0.16f * pulse);
            inner = this.applyAlpha(11032055, 0.11f * pulse);
            highlight = this.applyAlpha(14264319, 0.16f * pulse);
        } else {
            outer = this.applyAlpha(6183508, 0.1f * pulse);
            inner = this.applyAlpha(9075299, 0.07f * pulse);
            highlight = this.applyAlpha(12167824, 0.1f * pulse);
        }
        GearBackground.func_73734_a((int)x, (int)y, (int)(x + size), (int)(y + size), (int)outer);
        if (size > 4) {
            GearBackground.func_73734_a((int)(x + 1), (int)(y + 1), (int)(x + size - 1), (int)(y + size - 1), (int)inner);
        }
        GearBackground.func_73734_a((int)x, (int)y, (int)(x + size), (int)(y + 1), (int)highlight);
        GearBackground.func_73734_a((int)x, (int)y, (int)(x + 1), (int)(y + size), (int)highlight);
        if (jumbled && (int)seed % 3 == 0) {
            int s2 = Math.max(3, size / 3);
            int ox = x + size + 3;
            int oy = y - 2 + (int)(MathHelper.func_76126_a((float)(time * 5.0f + seed)) * 2.0f);
            int c2 = this.applyAlpha(12088575, 0.14f * pulse);
            GearBackground.func_73734_a((int)ox, (int)oy, (int)(ox + s2), (int)(oy + s2), (int)c2);
        }
    }

    private float frac(float v) {
        return v - (float)Math.floor(v);
    }
}

