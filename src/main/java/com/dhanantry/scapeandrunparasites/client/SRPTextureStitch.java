/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.event.TextureStitchEvent$Pre
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.dhanantry.scapeandrunparasites.client;

import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
@Mod.EventBusSubscriber(modid="srparasites", value={Side.CLIENT})
public final class SRPTextureStitch {
    private SRPTextureStitch() {
    }

    @SubscribeEvent
    public static void onTextureStitchPre(TextureStitchEvent.Pre event) {
        TextureMap map = event.getMap();
        map.func_174942_a(new ResourceLocation("srparasites", "blocks/arraytowertexture"));
        map.func_174942_a(new ResourceLocation("srparasites", "blocks/arraytowertextureon"));
    }
}

