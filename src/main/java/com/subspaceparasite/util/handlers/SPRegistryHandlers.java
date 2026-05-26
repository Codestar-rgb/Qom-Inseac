/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.IWorldGenerator
 *  net.minecraftforge.fml.common.registry.GameRegistry
 */
package com.subspaceparasite.util.handlers;

import com.subspaceparasite.client.renderer.ScreenOverlayRenderer;
import com.subspaceparasite.util.handlers.BiomeBatchSender;
import com.subspaceparasite.util.handlers.SPDisloEventHandler;
import com.subspaceparasite.util.handlers.SPEventHandlerBus;
import com.subspaceparasite.util.handlers.SPEventHandlerTerrainGen;
import com.subspaceparasite.util.handlers.SPRenderHandler;
import com.subspaceparasite.world.gen.WorldGenCustomStructures;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SPRegistryHandlers {
    public static void initRenders() {
        SPRenderHandler.registryEntityRenders();
        MinecraftForge.EVENT_BUS.register((Object)new ScreenOverlayRenderer());
    }

    public static void initEvents() {
        MinecraftForge.EVENT_BUS.register((Object)new SPEventHandlerBus());
        MinecraftForge.EVENT_BUS.register((Object)new SPDisloEventHandler());
        MinecraftForge.EVENT_BUS.register((Object)new BiomeBatchSender());
        MinecraftForge.TERRAIN_GEN_BUS.register((Object)new SPEventHandlerTerrainGen());
        GameRegistry.registerWorldGenerator((IWorldGenerator)new WorldGenCustomStructures(), (int)0);
    }
}

