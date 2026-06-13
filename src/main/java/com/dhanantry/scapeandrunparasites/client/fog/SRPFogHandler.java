/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$FogMode
 *  net.minecraftforge.client.event.EntityViewRenderEvent$FogColors
 *  net.minecraftforge.client.event.EntityViewRenderEvent$RenderFogEvent
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.dhanantry.scapeandrunparasites.client.fog;

import com.dhanantry.scapeandrunparasites.client.fog.SRPFogManager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public final class SRPFogHandler {
    public static void register() {
        MinecraftForge.EVENT_BUS.register((Object)new SRPFogHandler());
    }

    private boolean shouldApply() {
        return SRPFogManager.get().isEnabled();
    }

    @SubscribeEvent
    public void onFogColors(EntityViewRenderEvent.FogColors e) {
        if (!SRPFogManager.get().isEnabled()) {
            return;
        }
        float r = e.getRed();
        float g = e.getGreen();
        float b = e.getBlue();
        float gray = (r + g + b) / 3.0f;
        r = r * 0.75f + gray * 0.25f;
        g = g * 0.75f + gray * 0.25f;
        b = b * 0.75f + gray * 0.25f;
        e.setRed(r *= 0.9f);
        e.setGreen(g *= 0.9f);
        e.setBlue(b *= 0.9f);
    }

    @SubscribeEvent
    public void onFogRender(EntityViewRenderEvent.RenderFogEvent e) {
        if (!this.shouldApply()) {
            return;
        }
        GlStateManager.func_187430_a((GlStateManager.FogMode)GlStateManager.FogMode.LINEAR);
        GlStateManager.func_179102_b((float)12.0f);
        GlStateManager.func_179153_c((float)64.0f);
    }
}

