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
 */
package com.subspaceparasite.client;

import com.subspaceparasite.client.particle.ParticleKirinWarning;
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

@Mod.EventBusSubscriber(value={Side.CLIENT})
public final class SPClientParticles {
    public static final TextureAtlasSprite[] INFESTED_LEAF = new TextureAtlasSprite[4];
    public static TextureAtlasSprite KIRIN_WARNING;

    private SPClientParticles() {
    }

    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre e) {
        TextureMap map = e.getMap();
        SPClientParticles.INFESTED_LEAF[0] = map.func_174942_a(new ResourceLocation("subspaceparasite:particle/infested_leaves"));
        SPClientParticles.INFESTED_LEAF[1] = map.func_174942_a(new ResourceLocation("subspaceparasite:particle/infested_leaves2"));
        SPClientParticles.INFESTED_LEAF[2] = map.func_174942_a(new ResourceLocation("subspaceparasite:particle/infested_leaves3"));
        SPClientParticles.INFESTED_LEAF[3] = map.func_174942_a(new ResourceLocation("subspaceparasite:particle/infested_leaves4"));
        KIRIN_WARNING = map.func_174942_a(new ResourceLocation("subspaceparasite:particle/kirin_warning"));
    }

    public static TextureAtlasSprite randomInfestedLeaf(Random rand) {
        return INFESTED_LEAF[rand.nextInt(INFESTED_LEAF.length)];
    }

    public static void spawnKirinWarning(World world, double x, double y, double z, float sizeBlocks, float yawRad, int maxAgeTicks) {
        if (!world.field_72995_K) {
            return;
        }
        ParticleKirinWarning p = new ParticleKirinWarning(world, x, y, z, sizeBlocks, yawRad, maxAgeTicks);
        Minecraft.func_71410_x().field_71452_i.func_78873_a((Particle)p);
    }
}

