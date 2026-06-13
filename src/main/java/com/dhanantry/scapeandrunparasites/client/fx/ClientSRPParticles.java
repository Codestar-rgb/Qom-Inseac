/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.particle.ParticleManager
 *  net.minecraft.client.renderer.texture.TextureAtlasSprite
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.event.TextureStitchEvent$Pre
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.dhanantry.scapeandrunparasites.client.fx;

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid="srparasites", value={Side.CLIENT})
public final class ClientSRPParticles {
    public static TextureAtlasSprite INFESTED_LEAF_SPRITE;
    public static final TextureAtlasSprite[] INFESTED_LEAF_SPRITES;
    private static int spawnedThisTick;
    private static final int MAX_PER_TICK = 68;

    public static boolean canSpawn() {
        return spawnedThisTick < 68;
    }

    public static void onSpawn() {
        ++spawnedThisTick;
    }

    public static ParticleManager fx() {
        return Minecraft.func_71410_x().field_71452_i;
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent e) {
        if (e.phase == TickEvent.Phase.END) {
            spawnedThisTick = 0;
        }
    }

    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre e) {
        ClientSRPParticles.INFESTED_LEAF_SPRITES[0] = INFESTED_LEAF_SPRITE = e.getMap().func_174942_a(new ResourceLocation("srparasites:particle/infested_leaves"));
        ClientSRPParticles.INFESTED_LEAF_SPRITES[1] = e.getMap().func_174942_a(new ResourceLocation("srparasites:particle/infested_leaves2"));
        ClientSRPParticles.INFESTED_LEAF_SPRITES[2] = e.getMap().func_174942_a(new ResourceLocation("srparasites:particle/infested_leaves3"));
        ClientSRPParticles.INFESTED_LEAF_SPRITES[3] = e.getMap().func_174942_a(new ResourceLocation("srparasites:particle/infested_leaves4"));
    }

    public static TextureAtlasSprite randomInfestedLeaf(Random rand) {
        return INFESTED_LEAF_SPRITES[rand.nextInt(INFESTED_LEAF_SPRITES.length)];
    }

    static {
        INFESTED_LEAF_SPRITES = new TextureAtlasSprite[4];
        spawnedThisTick = 0;
    }
}

