/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.particle.Particle
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$FogMode
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.EntityViewRenderEvent$FogColors
 *  net.minecraftforge.client.event.EntityViewRenderEvent$FogDensity
 *  net.minecraftforge.client.event.EntityViewRenderEvent$RenderFogEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.subspaceparasite.client.fx;

import com.subspaceparasite.client.fx.ParticleBlizzard;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid="subspaceparasite", value={Side.CLIENT})
public final class ClientExtremeSnow {
    private static boolean enabled = false;
    private static float intensity = 1.0f;
    private static boolean forceAnywhere = true;
    private static float windDeg = 30.0f;
    private static float windSpeed = 0.5f;
    private static double windX = 0.0;
    private static double windZ = 0.0;

    private ClientExtremeSnow() {
    }

    public static void setState(boolean on, float i, boolean any, float deg, float spd) {
        enabled = on;
        intensity = MathHelper.func_76131_a((float)i, (float)0.0f, (float)1.0f);
        forceAnywhere = any;
        windDeg = deg % 360.0f;
        windSpeed = MathHelper.func_76131_a((float)spd, (float)0.0f, (float)1.0f);
        double rad = Math.toRadians(windDeg);
        double base = 0.15 + 0.55 * (double)windSpeed;
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
    public static void onFogDensity(EntityViewRenderEvent.FogDensity e) {
        if (!enabled) {
            return;
        }
        Minecraft mc = Minecraft.func_71410_x();
        if (mc.field_71441_e == null || mc.field_71439_g == null) {
            return;
        }
        if (!ClientExtremeSnow.isOutdoors((World)mc.field_71441_e, mc.field_71439_g)) {
            return;
        }
        float d = ClientExtremeSnow.exp2DensityFor(10.0);
        e.setDensity(d);
        e.setCanceled(true);
    }

    @SubscribeEvent
    public static void onFogColors(EntityViewRenderEvent.FogColors e) {
        if (!enabled) {
            return;
        }
        Minecraft mc = Minecraft.func_71410_x();
        if (mc.field_71441_e == null || mc.field_71439_g == null) {
            return;
        }
        if (!ClientExtremeSnow.isOutdoors((World)mc.field_71441_e, mc.field_71439_g)) {
            return;
        }
        float push = 0.75f;
        e.setRed(e.getRed() * (1.0f - push) + push);
        e.setGreen(e.getGreen() * (1.0f - push) + push);
        e.setBlue(e.getBlue() * (1.0f - push) + push);
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent e) {
        if (!enabled || e.phase != TickEvent.Phase.END) {
            return;
        }
        Minecraft mc = Minecraft.func_71410_x();
        WorldClient w = mc.field_71441_e;
        if (w == null) {
            return;
        }
        EntityPlayerSP p = mc.field_71439_g;
        if (p == null) {
            return;
        }
        double radius = 12.0;
        int setting = mc.field_71474_y.field_74362_aa;
        double budget = setting == 2 ? 0.25 : (setting == 1 ? 0.55 : 1.0);
        int base = 140 + (int)(360.0f * intensity);
        int count = (int)((double)base * 1.8 * budget);
        for (int i = 0; i < count; ++i) {
            int z;
            int x = (int)(p.field_70165_t + w.field_73012_v.nextGaussian() * radius);
            BlockPos ground = w.func_175725_q(new BlockPos(x, (int)p.field_70163_u, z = (int)(p.field_70161_v + w.field_73012_v.nextGaussian() * radius)));
            if (!w.func_175678_i(ground) || !forceAnywhere && !w.func_175708_f(ground, false)) continue;
            double spawnY = Math.max((double)(ground.func_177956_o() + 16 + w.field_73012_v.nextInt(8)), p.field_70163_u + 16.0 + (double)w.field_73012_v.nextInt(8));
            double sx = (double)x + 0.5 + (w.field_73012_v.nextDouble() - 0.5);
            double sz = (double)z + 0.5 + (w.field_73012_v.nextDouble() - 0.5);
            double jitter = 0.03;
            double vx = windX + (w.field_73012_v.nextDouble() - 0.5) * jitter;
            double vz = windZ + (w.field_73012_v.nextDouble() - 0.5) * jitter;
            double vy = -0.22 - 0.06 * (double)intensity - w.field_73012_v.nextDouble() * 0.04;
            mc.field_71452_i.func_78873_a((Particle)new ParticleBlizzard((World)w, sx, spawnY, sz, vx, vy, vz, p));
        }
    }

    private static float densityForVisibility(double targetBlocks) {
        double THRESHOLD = 0.02;
        double D = Math.sqrt(Math.log(50.0)) / Math.max(1.0E-6, targetBlocks);
        return (float)MathHelper.func_151237_a((double)D, (double)0.0, (double)1.0);
    }

    @SubscribeEvent
    public static void onRenderFog(EntityViewRenderEvent.RenderFogEvent e) {
        if (!enabled) {
            return;
        }
        Minecraft mc = Minecraft.func_71410_x();
        if (mc.field_71441_e == null || mc.field_71439_g == null) {
            return;
        }
        if (!ClientExtremeSnow.isOutdoors((World)mc.field_71441_e, mc.field_71439_g)) {
            return;
        }
        float d = ClientExtremeSnow.exp2DensityFor(10.0);
        GlStateManager.func_187430_a((GlStateManager.FogMode)GlStateManager.FogMode.EXP2);
        GlStateManager.func_179095_a((float)d);
    }

    private static boolean isOutdoors(World w, EntityPlayerSP p) {
        BlockPos[] samples;
        for (BlockPos s : samples = new BlockPos[]{new BlockPos(p.field_70165_t, p.field_70163_u, p.field_70161_v), new BlockPos(p.field_70165_t + 4.0, p.field_70163_u, p.field_70161_v), new BlockPos(p.field_70165_t - 4.0, p.field_70163_u, p.field_70161_v), new BlockPos(p.field_70165_t, p.field_70163_u, p.field_70161_v + 4.0), new BlockPos(p.field_70165_t, p.field_70163_u, p.field_70161_v - 4.0)}) {
            BlockPos h = w.func_175725_q(s);
            if (!w.func_175678_i(h)) continue;
            return true;
        }
        return false;
    }
}

