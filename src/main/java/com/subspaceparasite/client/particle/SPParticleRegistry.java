/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.particle.Particle
 *  net.minecraft.client.renderer.texture.TextureAtlasSprite
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.TextureStitchEvent$Pre
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.client.particle;

import com.subspaceparasite.client.particle.ParticlePure;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid="subspaceparasite", value={Side.CLIENT})
public class SPParticleRegistry {
    private static TextureAtlasSprite PURE1;
    private static TextureAtlasSprite PURE2;

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre e) {
        TextureMap map = e.getMap();
        PURE1 = map.func_174942_a(new ResourceLocation("subspaceparasite", "particle/particle_pure1"));
        PURE2 = map.func_174942_a(new ResourceLocation("subspaceparasite", "particle/particle_pure2"));
    }

    @SideOnly(value=Side.CLIENT)
    public static TextureAtlasSprite randPureSprite(Random r) {
        return r.nextBoolean() ? PURE1 : PURE2;
    }

    @SideOnly(value=Side.CLIENT)
    public static void spawnPureBurst(World world, double x, double y, double z, int count, int kind) {
        if (PURE1 == null || PURE2 == null) {
            return;
        }
        Random r = world.field_73012_v;
        for (int i = 0; i < count; ++i) {
            TextureAtlasSprite sprite = (i & 1) == 0 ? PURE1 : PURE2;
            ParticlePure p = new ParticlePure(world, x, y, z, kind, sprite);
            Minecraft.func_71410_x().field_71452_i.func_78873_a((Particle)p);
        }
    }
}

