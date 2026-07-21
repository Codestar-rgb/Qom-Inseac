package com.dhanantry.scapeandrunparasites.init;

import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import com.dhanantry.scapeandrunparasites.world.biome.BiomeParasiteHarlequin;
import com.dhanantry.scapeandrunparasites.world.biome.BiomeParasiteShrouded;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SRPBiomes {
   public static final BiomeParasiteShrouded biomeShrouded = new BiomeParasiteShrouded();
   public static final BiomeParasiteHarlequin biomeHarlequin = new BiomeParasiteHarlequin();

   private static void register(Register<Biome> event, Biome biome, BiomeType type, String name, int weight, Type... biomeDictTypes) {
      biome.setRegistryName(new ResourceLocation("srparasites", name));
      event.getRegistry().register(biome);

      for (Type biomeDictType : biomeDictTypes) {
         BiomeDictionary.addTypes(biome, new Type[]{biomeDictType});
      }

      BiomeManager.addBiome(type, new BiomeEntry(biome, weight));
   }

   public static void clearMobSpawnList() {
      biomeShrouded.mobListClear();
      biomeShrouded.setBlocks();
      biomeHarlequin.mobListClear();
      biomeHarlequin.setBlocks();
   }

   @EventBusSubscriber(modid = "srparasites")
   public static class RegistrationHandler {
      @SubscribeEvent
      public static void onEvent(Register<Biome> event) {
         if (SRPConfigWorld.biomeRegster) {
            SRPBiomes.register(event, SRPBiomes.biomeShrouded, BiomeType.COOL, "biomeparasite_shrouded", SRPConfigWorld.biomeOneWeight, Type.SPOOKY);
            SRPBiomes.register(event, SRPBiomes.biomeHarlequin, BiomeType.COOL, "biomeparasite_harlequin", SRPConfigWorld.biomeThreeWeight, Type.SPOOKY);
         }
      }
   }
}
