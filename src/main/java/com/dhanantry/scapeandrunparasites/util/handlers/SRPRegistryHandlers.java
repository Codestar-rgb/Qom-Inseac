/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.IWorldGenerator
 *  net.minecraftforge.fml.common.registry.GameRegistry
 */
package com.dhanantry.scapeandrunparasites.util.handlers;

import com.dhanantry.scapeandrunparasites.client.renderer.ScreenOverlayRenderer;
import com.dhanantry.scapeandrunparasites.util.handlers.BiomeBatchSender;
import com.dhanantry.scapeandrunparasites.util.handlers.SRPDisloEventHandler;
import com.dhanantry.scapeandrunparasites.util.handlers.SRPEventHandlerBus;
import com.dhanantry.scapeandrunparasites.util.handlers.SRPEventHandlerTerrainGen;
import com.dhanantry.scapeandrunparasites.util.handlers.SRPRenderHandler;
import com.dhanantry.scapeandrunparasites.world.gen.WorldGenCustomStructures;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SRPRegistryHandlers {
    public static void initRenders() {
        SRPRenderHandler.registryEntityRenders();
        MinecraftForge.EVENT_BUS.register((Object)new ScreenOverlayRenderer());
    }

    public static void initEvents() {
        MinecraftForge.EVENT_BUS.register((Object)new SRPEventHandlerBus());
        MinecraftForge.EVENT_BUS.register((Object)new SRPDisloEventHandler());
        MinecraftForge.EVENT_BUS.register((Object)new BiomeBatchSender());
        MinecraftForge.TERRAIN_GEN_BUS.register((Object)new SRPEventHandlerTerrainGen());
        GameRegistry.registerWorldGenerator((IWorldGenerator)new WorldGenCustomStructures(), (int)0);
    }
}

