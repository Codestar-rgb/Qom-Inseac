/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.dhanantry.scapeandrunparasites.client.celestial;

import com.dhanantry.scapeandrunparasites.bestiary.cap.BestiaryCapability;
import com.dhanantry.scapeandrunparasites.bestiary.cap.IBestiaryProgress;
import com.dhanantry.scapeandrunparasites.bestiary.net.BestiaryNetwork;
import com.dhanantry.scapeandrunparasites.bestiary.net.PacketBestiarySeenCelestial;
import com.dhanantry.scapeandrunparasites.client.celestial.CelestialObjectDefinition;
import com.dhanantry.scapeandrunparasites.client.celestial.CelestialObjectRegistry;
import com.dhanantry.scapeandrunparasites.client.celestial.CelestialPhaseClient;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
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
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid="srparasites", value={Side.CLIENT})
public final class CelestialSkyRenderer {
    private static final double SKY_RADIUS = 180.0;
    private static final float ORBIT_PERIOD_TICKS = 12000.0f;
    private static final boolean DEBUG = false;
    private static final Map<String, Long> ORBIT_STARTS = new HashMap<String, Long>();
    private static long lastDayTime = 0L;
    private static final Set<String> SENT = new HashSet<String>();

    private CelestialSkyRenderer() {
    }

    private static boolean hasFieldGuide(EntityPlayer p) {
        if (p == null) {
            return false;
        }
        Item guide = SRPItems.SRP_FIELD_GUIDE;
        for (ItemStack st : p.field_71071_by.field_70462_a) {
            if (st.func_190926_b() || st.func_77973_b() != guide) continue;
            return true;
        }
        for (ItemStack st : p.field_71071_by.field_184439_c) {
            if (st.func_190926_b() || st.func_77973_b() != guide) continue;
            return true;
        }
        return false;
    }

    private static void recordSeenOnce(Minecraft mc, String id) {
        if (id == null) {
            return;
        }
        if (mc == null || mc.field_71439_g == null) {
            return;
        }
        if (!CelestialSkyRenderer.hasFieldGuide((EntityPlayer)mc.field_71439_g)) {
            return;
        }
        IBestiaryProgress prog = (IBestiaryProgress)mc.field_71439_g.getCapability(BestiaryCapability.CAP, null);
        if (prog != null && prog.hasSeenCelestial(id)) {
            return;
        }
        if (SENT.contains(id)) {
            return;
        }
        SENT.add(id);
        BestiaryNetwork.CH.sendToServer((IMessage)new PacketBestiarySeenCelestial(id));
    }

    public static void clearSeenCacheClient() {
        SENT.clear();
    }

    private static Vec3d dirFromYawPitch(float yawDeg, float pitchDeg) {
        float yaw = (float)Math.toRadians(yawDeg);
        float pitch = (float)Math.toRadians(pitchDeg);
        float x = -MathHelper.func_76126_a((float)yaw) * MathHelper.func_76134_b((float)pitch);
        float y = MathHelper.func_76126_a((float)pitch);
        float z = MathHelper.func_76134_b((float)yaw) * MathHelper.func_76134_b((float)pitch);
        return new Vec3d((double)x, (double)y, (double)z);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @SubscribeEvent
    public static void onRenderWorldLast(RenderWorldLastEvent e) {
        if (!SRPConfigWorld.enableCelestialObjects) {
            return;
        }
        Minecraft mc = Minecraft.func_71410_x();
        WorldClient world = mc.field_71441_e;
        EntityPlayerSP player = mc.field_71439_g;
        if (world == null || player == null) {
            return;
        }
        if (!world.field_73011_w.func_76569_d()) {
            return;
        }
        float partialTicks = e.getPartialTicks();
        long totalTime = world.func_72820_D();
        long dayTime = totalTime % 24000L;
        if (dayTime < lastDayTime) {
            ORBIT_STARTS.clear();
        }
        lastDayTime = dayTime;
        int dim = world.field_73011_w.getDimension();
        int phase = CelestialPhaseClient.getPhase(dim);
        float celestialAngle = world.func_72826_c(partialTicks);
        float starBrightness = world.func_72880_h(partialTicks);
        if (starBrightness <= 0.0f) {
            return;
        }
        float rainStrength = world.func_72867_j(partialTicks);
        if (rainStrength > 0.0f || world.func_72911_I()) {
            return;
        }
        double camX = mc.func_175598_ae().field_78730_l;
        double camY = mc.func_175598_ae().field_78731_m;
        double camZ = mc.func_175598_ae().field_78728_n;
        long worldTime = world.func_82737_E();
        GlStateManager.func_179094_E();
        try {
            GlStateManager.func_179140_f();
            GlStateManager.func_179129_p();
            GlStateManager.func_179147_l();
            GlStateManager.func_179120_a((int)770, (int)771, (int)1, (int)0);
            GlStateManager.func_179141_d();
            GlStateManager.func_179092_a((int)516, (float)0.1f);
            GlStateManager.func_179126_j();
            GlStateManager.func_179132_a((boolean)false);
            GlStateManager.func_179143_c((int)515);
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            for (CelestialObjectDefinition def : CelestialObjectRegistry.getObjects()) {
                float alpha;
                float period;
                boolean isForced = CelestialPhaseClient.isForcedTonight(dim, def.id);
                if (!isForced && !def.isPhaseAllowed(phase) || !isForced && !CelestialPhaseClient.isActiveTonight(dim, def.id)) continue;
                CelestialSkyRenderer.recordSeenOnce(mc, def.id);
                float yawDeg = def.yawDeg;
                float pitchDeg = def.pitchDeg;
                float f = period = def.orbitPeriodTicks > 0.0f ? def.orbitPeriodTicks : 12000.0f;
                if (def.orbitPath != CelestialObjectDefinition.OrbitPath.NONE) {
                    float t;
                    String orbitKey = world.field_73011_w.getDimension() + ":" + def.id;
                    if (def.oneShotOrbit) {
                        long startTime = ORBIT_STARTS.computeIfAbsent(orbitKey, k -> worldTime);
                        t = (float)((double)(worldTime - startTime) / (double)period);
                        if (t >= 1.0f) {
                            continue;
                        }
                    } else {
                        t = (float)((double)(worldTime % (long)period) / (double)period);
                    }
                    switch (def.orbitPath) {
                        case RING: {
                            yawDeg = def.yawDeg + def.orbitYawRangeDeg * t;
                            pitchDeg = def.orbitPitchMinDeg;
                            break;
                        }
                        case ARC: {
                            yawDeg = def.yawDeg + def.orbitYawRangeDeg * t;
                            float arc = (float)Math.sin(Math.PI * (double)t);
                            pitchDeg = def.orbitPitchMinDeg + (def.orbitPitchMaxDeg - def.orbitPitchMinDeg) * arc;
                            break;
                        }
                    }
                } else if (def.fastStreak) {
                    float t = (float)(worldTime % 12000L) / 12000.0f;
                    yawDeg += t * 360.0f;
                }
                if (def.followsStars) {
                    float starAngleDeg = celestialAngle * 360.0f;
                    yawDeg += starAngleDeg;
                }
                if (def.rotationSpeedDeg != 0.0f) {
                    float spin = ((float)worldTime + partialTicks) / 20.0f * def.rotationSpeedDeg;
                    yawDeg += spin;
                }
                Vec3d dir = CelestialSkyRenderer.dirFromYawPitch(yawDeg, pitchDeg);
                double wx = camX + dir.field_72450_a * 180.0;
                double wy = camY + dir.field_72448_b * 180.0;
                double wz = camZ + dir.field_72449_c * 180.0;
                mc.func_110434_K().func_110577_a(def.texture);
                float u0 = 0.0f;
                float u1 = 1.0f;
                float v0 = 0.0f;
                float v1 = 1.0f;
                if (def.animated && def.frameCount > 1) {
                    int frame = (int)(worldTime / (long)def.frameTimeTicks % (long)def.frameCount);
                    float frameH = 1.0f / (float)def.frameCount;
                    v0 = (float)frame * frameH;
                    v1 = v0 + frameH;
                }
                if ((alpha = MathHelper.func_76131_a((float)(def.baseOpacity * starBrightness), (float)0.0f, (float)1.0f)) <= 0.001f) continue;
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
                GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)alpha);
                Tessellator tess = Tessellator.func_178181_a();
                BufferBuilder buf = tess.func_178180_c();
                float prevLmX = OpenGlHelper.lastBrightnessX;
                float prevLmY = OpenGlHelper.lastBrightnessY;
                OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)240.0f, (float)240.0f);
                GlStateManager.func_179118_c();
                buf.func_181668_a(7, DefaultVertexFormats.field_181707_g);
                buf.func_181662_b(p0.field_72450_a - camX, p0.field_72448_b - camY, p0.field_72449_c - camZ).func_187315_a((double)u0, (double)v1).func_181675_d();
                buf.func_181662_b(p1.field_72450_a - camX, p1.field_72448_b - camY, p1.field_72449_c - camZ).func_187315_a((double)u1, (double)v1).func_181675_d();
                buf.func_181662_b(p2.field_72450_a - camX, p2.field_72448_b - camY, p2.field_72449_c - camZ).func_187315_a((double)u1, (double)v0).func_181675_d();
                buf.func_181662_b(p3.field_72450_a - camX, p3.field_72448_b - camY, p3.field_72449_c - camZ).func_187315_a((double)u0, (double)v0).func_181675_d();
                tess.func_78381_a();
                GlStateManager.func_179141_d();
                GlStateManager.func_179092_a((int)516, (float)0.1f);
                OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)prevLmX, (float)prevLmY);
                GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GlStateManager.func_179147_l();
                GlStateManager.func_179120_a((int)770, (int)771, (int)1, (int)0);
                GlStateManager.func_179126_j();
                GlStateManager.func_179132_a((boolean)false);
                GlStateManager.func_179143_c((int)515);
                GlStateManager.func_179129_p();
                GlStateManager.func_179140_f();
            }
        }
        finally {
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GlStateManager.func_179141_d();
            GlStateManager.func_179092_a((int)516, (float)0.1f);
            GlStateManager.func_179084_k();
            GlStateManager.func_179120_a((int)1, (int)0, (int)1, (int)0);
            GlStateManager.func_179126_j();
            GlStateManager.func_179132_a((boolean)true);
            GlStateManager.func_179143_c((int)515);
            GlStateManager.func_179089_o();
            GlStateManager.func_179145_e();
            GlStateManager.func_179121_F();
        }
    }
}

