/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.dhanantry.scapeandrunparasites.client.sky;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid="srparasites", value={Side.CLIENT})
public final class SkyFlashRenderer {
    private static final ResourceLocation TEX = new ResourceLocation("srparasites:textures/entity/monster/sky_flash.png");
    private static final List<Flash> FLASHES = new ArrayList<Flash>();

    public static void trigger(int durationTicks, double span, double altitude) {
        SkyFlashRenderer.trigger(durationTicks, span, altitude, -15.0f);
    }

    public static void trigger(int durationTicks, double span, double altitude, float bankDeg) {
        SkyFlashRenderer.triggerOffset(durationTicks, span, altitude, bankDeg, 0.0, 0.0, 0.0);
    }

    public static void triggerOffset(int durationTicks, double span, double altitude, float bankDeg, double yawOffsetDeg, double forwardOffset, double lateralOffset) {
        Minecraft mc = Minecraft.func_71410_x();
        if (mc.field_71441_e == null || mc.field_71439_g == null) {
            return;
        }
        Vec3d cam = new Vec3d(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u, mc.field_71439_g.field_70161_v);
        Vec3d upW = new Vec3d(0.0, 1.0, 0.0);
        Vec3d look = mc.field_71439_g.func_70676_i(1.0f).func_72432_b();
        Vec3d right = upW.func_72431_c(look).func_72432_b();
        if (right.func_189985_c() < 1.0E-6) {
            right = new Vec3d(1.0, 0.0, 0.0);
        }
        Vec3d lookR = SkyFlashRenderer.rotateYaw(look, yawOffsetDeg);
        Vec3d rightR = SkyFlashRenderer.rotateYaw(right, yawOffsetDeg);
        double forwardDist = 260.0 + forwardOffset;
        Vec3d center = cam.func_178787_e(lookR.func_186678_a(forwardDist)).func_72441_c(0.0, altitude, 0.0).func_178787_e(rightR.func_186678_a(lateralOffset));
        Vec3d start = center.func_178788_d(rightR.func_186678_a(span * 0.5));
        Vec3d end = center.func_178787_e(rightR.func_186678_a(span * 0.5));
        FLASHES.add(new Flash(mc.field_71441_e.func_82737_E(), durationTicks, start, end, bankDeg));
    }

    private static Vec3d rotateYaw(Vec3d v, double deg) {
        double r = Math.toRadians(deg);
        double c = Math.cos(r);
        double s = Math.sin(r);
        return new Vec3d(v.field_72450_a * c - v.field_72449_c * s, v.field_72448_b, v.field_72450_a * s + v.field_72449_c * c);
    }

    @SubscribeEvent
    public static void onRenderWorld(RenderWorldLastEvent e) {
        if (FLASHES.isEmpty()) {
            return;
        }
        Minecraft mc = Minecraft.func_71410_x();
        if (mc.field_71441_e == null) {
            FLASHES.clear();
            return;
        }
        double camX = mc.func_175598_ae().field_78730_l;
        double camY = mc.func_175598_ae().field_78731_m;
        double camZ = mc.func_175598_ae().field_78728_n;
        GlStateManager.func_179094_E();
        GlStateManager.func_179140_f();
        GlStateManager.func_179129_p();
        GlStateManager.func_179147_l();
        GlStateManager.func_179112_b((int)770, (int)1);
        mc.func_110434_K().func_110577_a(TEX);
        float pt = e.getPartialTicks();
        Iterator<Flash> it = FLASHES.iterator();
        while (it.hasNext()) {
            Flash f = it.next();
            double now = (float)mc.field_71441_e.func_82737_E() + pt;
            float t = (float)((now - f.startTick) / f.durationTicks);
            if (t >= 1.0f) {
                it.remove();
                continue;
            }
            if (t < 0.0f) continue;
            Vec3d pos = SkyFlashRenderer.lerp(f.start, f.end, t);
            Vec3d upW = new Vec3d(0.0, 1.0, 0.0);
            Vec3d tangent = f.end.func_178788_d(f.start).func_72432_b();
            Vec3d n = upW.func_72431_c(tangent);
            if (n.func_189985_c() < 1.0E-6) {
                n = new Vec3d(1.0, 0.0, 0.0).func_72431_c(tangent);
            }
            n = n.func_72432_b();
            Vec3d b = tangent.func_72431_c(n).func_72432_b();
            if (Math.abs(f.bankDeg) > 0.001f) {
                double r = Math.toRadians(f.bankDeg);
                double cs = Math.cos(r);
                double sn = Math.sin(r);
                Vec3d nR = n.func_186678_a(cs).func_178787_e(b.func_186678_a(sn));
                Vec3d bR = b.func_186678_a(cs).func_178788_d(n.func_186678_a(sn));
                n = nR;
                b = bR;
            }
            float halfLen = 24.0f;
            float halfThick = 6.0f;
            int alpha = Math.max(0, Math.min(255, (int)(255.0f * (1.0f - t))));
            SkyFlashRenderer.drawRibbonQuad(pos, tangent, n, b, 0.0, halfLen, halfThick, alpha, camX, camY, camZ);
            SkyFlashRenderer.drawRibbonQuad(pos, tangent, n, b, 60.0, halfLen, halfThick, alpha, camX, camY, camZ);
            SkyFlashRenderer.drawRibbonQuad(pos, tangent, n, b, 120.0, halfLen, halfThick, alpha, camX, camY, camZ);
        }
        GlStateManager.func_179132_a((boolean)true);
        GlStateManager.func_179084_k();
        GlStateManager.func_179089_o();
        GlStateManager.func_179145_e();
        GlStateManager.func_179126_j();
        GlStateManager.func_179121_F();
    }

    private static void drawRibbonQuad(Vec3d pos, Vec3d tangent, Vec3d n, Vec3d b, double phiDeg, float halfLen, float halfThick, int alpha, double camX, double camY, double camZ) {
        double r = Math.toRadians(phiDeg);
        double cs = Math.cos(r);
        double sn = Math.sin(r);
        Vec3d u = n.func_186678_a(cs).func_178787_e(b.func_186678_a(sn));
        Vec3d p0 = pos.func_178787_e(tangent.func_186678_a((double)(-halfLen))).func_178787_e(u.func_186678_a((double)(-halfThick)));
        Vec3d p1 = pos.func_178787_e(tangent.func_186678_a((double)halfLen)).func_178787_e(u.func_186678_a((double)(-halfThick)));
        Vec3d p2 = pos.func_178787_e(tangent.func_186678_a((double)halfLen)).func_178787_e(u.func_186678_a((double)halfThick));
        Vec3d p3 = pos.func_178787_e(tangent.func_186678_a((double)(-halfLen))).func_178787_e(u.func_186678_a((double)halfThick));
        Tessellator tess = Tessellator.func_178181_a();
        BufferBuilder buf = tess.func_178180_c();
        buf.func_181668_a(7, DefaultVertexFormats.field_181709_i);
        buf.func_181662_b(p0.field_72450_a - camX, p0.field_72448_b - camY, p0.field_72449_c - camZ).func_187315_a(0.0, 1.0).func_181669_b(255, 255, 255, alpha).func_181675_d();
        buf.func_181662_b(p1.field_72450_a - camX, p1.field_72448_b - camY, p1.field_72449_c - camZ).func_187315_a(1.0, 1.0).func_181669_b(255, 255, 255, alpha).func_181675_d();
        buf.func_181662_b(p2.field_72450_a - camX, p2.field_72448_b - camY, p2.field_72449_c - camZ).func_187315_a(1.0, 0.0).func_181669_b(255, 255, 255, alpha).func_181675_d();
        buf.func_181662_b(p3.field_72450_a - camX, p3.field_72448_b - camY, p3.field_72449_c - camZ).func_187315_a(0.0, 0.0).func_181669_b(255, 255, 255, alpha).func_181675_d();
        tess.func_78381_a();
    }

    private static Vec3d lerp(Vec3d a, Vec3d b, float t) {
        return a.func_186678_a((double)(1.0f - t)).func_178787_e(b.func_186678_a((double)t));
    }

    private SkyFlashRenderer() {
    }

    private static final class Flash {
        final double startTick;
        final double durationTicks;
        final Vec3d start;
        final Vec3d end;
        final float bankDeg;

        Flash(double startTick, double durationTicks, Vec3d start, Vec3d end, float bankDeg) {
            this.startTick = startTick;
            this.durationTicks = durationTicks;
            this.start = start;
            this.end = end;
            this.bankDeg = bankDeg;
        }
    }
}

