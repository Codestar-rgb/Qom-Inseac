/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.EntityRenderer
 *  net.minecraft.client.shader.Shader
 *  net.minecraft.client.shader.ShaderGroup
 *  net.minecraft.client.shader.ShaderUniform
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.relauncher.ReflectionHelper
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package com.subspaceparasite.client.shader;

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
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SideOnly(value=Side.CLIENT)
public final class BreatheShaderManager {
    private static final Logger LOG = LogManager.getLogger((String)"SRP-Shader");
    private static final ResourceLocation POST = new ResourceLocation("subspaceparasite", "shaders/post/notch_tweaked.json");
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
            MinecraftForge.EVENT_BUS.register((Object)this);
            this.registered = true;
        }
        LOG.info("[SRP Breathe] enable()");
    }

    public void enableFor(int ticks) {
        if (ticks <= 0) {
            return;
        }
        if (!this.wantEnabled || this.ticksRemaining <= 0) {
            this.startNs = System.nanoTime();
        }
        this.wantEnabled = true;
        if (ticks > this.ticksRemaining) {
            this.ticksRemaining = ticks;
        }
        if (!this.registered) {
            MinecraftForge.EVENT_BUS.register((Object)this);
            this.registered = true;
        }
        LOG.info("[SRP Breathe] enableFor({})", (Object)ticks);
    }

    public void disable() {
        this.wantEnabled = false;
        this.ticksRemaining = 0;
        LOG.info("[SRP Breathe] disable()");
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent e) {
        ShaderGroup sg;
        if (e.phase != TickEvent.Phase.END) {
            return;
        }
        Minecraft mc = Minecraft.func_71410_x();
        if (mc.field_71441_e == null || mc.field_71439_g == null) {
            return;
        }
        if (this.wantEnabled && this.ticksRemaining > 0) {
            --this.ticksRemaining;
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
                MinecraftForge.EVENT_BUS.unregister((Object)this);
                this.registered = false;
            }
            return;
        }
        if (!this.applied || current == null) {
            try {
                LOG.info("[SRP Breathe] loadShader {}", (Object)POST);
                long now = System.currentTimeMillis();
                if (now < this.nextRetryMs) {
                    return;
                }
                er.func_175069_a(POST);
                ShaderGroup sg2 = er.func_147706_e();
                if (sg2 != null) {
                    int w = mc.field_71443_c;
                    int h = mc.field_71440_d;
                    sg2.func_148026_a(w, h);
                    this.lastW = w;
                    this.lastH = h;
                    this.applied = true;
                    LOG.info("[SRP Breathe] loaded {}x{}", (Object)w, (Object)h);
                } else {
                    LOG.error("[SRP Breathe] ShaderGroup is null after loadShader");
                    this.applied = false;
                }
            }
            catch (Throwable t) {
                this.nextRetryMs = System.currentTimeMillis() + 2000L;
                LOG.error("[SRP Breathe] loadShader failed", t);
                this.applied = false;
            }
            return;
        }
        int w = mc.field_71443_c;
        int h = mc.field_71440_d;
        if ((w != this.lastW || h != this.lastH) && er.func_147706_e() != null) {
            er.func_147706_e().func_148026_a(w, h);
            this.lastW = w;
            this.lastH = h;
        }
        if ((sg = er.func_147706_e()) != null) {
            float timeSec = (float)((double)(System.nanoTime() - this.startNs) / 1.0E9);
            BreatheShaderManager.setUniformAll(sg, "SP_Time", timeSec);
            BreatheShaderManager.setUniformAll(sg, "TintStrength", 0.1f);
        }
    }

    private static List<Shader> getShaders(ShaderGroup sg) {
        try {
            if (F_LIST_SHADERS == null) {
                F_LIST_SHADERS = ReflectionHelper.findField(ShaderGroup.class, (String)"listShaders", (String)"field_148031_d");
                F_LIST_SHADERS.setAccessible(true);
            }
            return (List)F_LIST_SHADERS.get(sg);
        }
        catch (Throwable t) {
            return null;
        }
    }

    private static void setUniformAll(ShaderGroup sg, String name, float v) {
        try {
            List<Shader> shaders = BreatheShaderManager.getShaders(sg);
            if (shaders == null) {
                return;
            }
            for (Shader s : shaders) {
                ShaderUniform u;
                if (s == null || (u = s.func_148043_c().func_147991_a(name)) == null) continue;
                u.func_148090_a(v);
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }
}

