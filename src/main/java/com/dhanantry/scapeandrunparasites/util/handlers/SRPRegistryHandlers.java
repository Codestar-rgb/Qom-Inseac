package com.dhanantry.scapeandrunparasites.util.handlers;

import com.dhanantry.scapeandrunparasites.client.renderer.ScreenOverlayRenderer;
import com.dhanantry.scapeandrunparasites.world.gen.WorldGenCustomStructures;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SRPRegistryHandlers {
   public static void initRenders() {
      SRPRenderHandler.registryEntityRenders();
      MinecraftForge.EVENT_BUS.register(new ScreenOverlayRenderer());
   }

   public static void initEvents() {
      MinecraftForge.EVENT_BUS.register(new SRPEventHandlerBus());
      MinecraftForge.EVENT_BUS.register(new SRPDisloEventHandler());
      MinecraftForge.EVENT_BUS.register(new BiomeBatchSender());
      MinecraftForge.TERRAIN_GEN_BUS.register(new SRPEventHandlerTerrainGen());
      GameRegistry.registerWorldGenerator(new WorldGenCustomStructures(), 0);
   }
}
