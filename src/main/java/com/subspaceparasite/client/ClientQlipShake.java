/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraftforge.client.event.EntityViewRenderEvent$CameraSetup
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Post
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 */
package com.subspaceparasite.client;

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

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
    public void onClientTick(TickEvent.ClientTickEvent e) {
        if (e.phase != TickEvent.Phase.END) {
            return;
        }
        if (Minecraft.func_71410_x().field_71439_g == null) {
            return;
        }
        if (this.delayLeft > 0) {
            --this.delayLeft;
        } else if (this.elapsed < 40) {
            ++this.elapsed;
        }
    }

    private float intensity() {
        --this.durationShake;
        if (this.delayLeft > 0 || this.elapsed <= 0 || this.elapsed > 40) {
            return 0.0f;
        }
        if (this.elapsed <= 10) {
            return (float)this.elapsed / 10.0f;
        }
        int afterRamp = this.elapsed - 10;
        if (afterRamp <= 10) {
            return 1.0f;
        }
        if (this.durationShake > 0) {
            this.elapsed = 30;
            return 1.0f;
        }
        int afterHold = afterRamp - 10;
        return Math.max(0.0f, 1.0f - (float)afterHold / 20.0f);
    }

    @SubscribeEvent
    public void onCameraSetup(EntityViewRenderEvent.CameraSetup e) {
        float k = this.intensity();
        if (k <= 0.0f || !this.shakeScreen) {
            return;
        }
        float strengthDeg = this.shakeScreenValue * k;
        e.setYaw(e.getYaw() + (this.rng.nextFloat() - 0.5f) * 2.0f * strengthDeg);
        e.setPitch(e.getPitch() + (this.rng.nextFloat() - 0.5f) * 2.0f * strengthDeg);
        e.setRoll(e.getRoll() + (this.rng.nextFloat() - 0.5f) * 2.0f * (strengthDeg * 0.7f));
    }

    @SubscribeEvent
    public void onOverlayPost(RenderGameOverlayEvent.Post e) {
        if (!this.darkScreen) {
            return;
        }
        if (e.getType() != RenderGameOverlayEvent.ElementType.ALL) {
            return;
        }
        float k = this.intensity();
        if (k <= 0.0f) {
            return;
        }
        int w = e.getResolution().func_78326_a();
        int h = e.getResolution().func_78328_b();
        float maxAlpha = 0.6f;
        int a = (int)(maxAlpha * k * 255.0f) << 24;
        Gui.func_73734_a((int)0, (int)0, (int)w, (int)h, (int)(0 | a));
    }
}

