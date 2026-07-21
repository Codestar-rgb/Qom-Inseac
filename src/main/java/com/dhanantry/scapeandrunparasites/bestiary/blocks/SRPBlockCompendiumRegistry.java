package com.dhanantry.scapeandrunparasites.bestiary.blocks;

import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

public final class SRPBlockCompendiumRegistry {
   private static final Map<ResourceLocation, BlockBestiaryEntry> ENTRIES = new LinkedHashMap<>();

   private SRPBlockCompendiumRegistry() {
   }

   public static BlockBestiaryEntry register(Block block, String nameKey, String loreKey) {
      if (block == null) {
         System.out.println("[BlockCompendium] Skipping entry '" + nameKey + "' because block is null");
         return null;
      } else {
         ResourceLocation id = block.getRegistryName();
         if (id == null) {
            System.out.println("[BlockCompendium] Skipping entry '" + nameKey + "' because registryName is null for " + block);
            return null;
         } else {
            BlockBestiaryEntry entry = new BlockBestiaryEntry(block, nameKey, loreKey);
            ENTRIES.put(id, entry);
            return entry;
         }
      }
   }

   public static BlockBestiaryEntry get(ResourceLocation id) {
      return ENTRIES.get(id);
   }

   public static Collection<BlockBestiaryEntry> all() {
      return ENTRIES.values();
   }

   public static void clear() {
      ENTRIES.clear();
   }

   public static void registerDefaults() {
      clear();
      register(SRPBlocks.HarleskinnBlock, "tile.srparasites.harleskinn_block.name", "bestiary.block.srparasites.harleskinn_block.desc");
      register(SRPBlocks.LocsBlock, "tile.srparasites.locs_block.name", "bestiary.block.srparasites.locs_block.desc");
      register(SRPBlocks.FogNullifier, "tile.srparasites.fog_nullifier.name", "bestiary.block.srparasites.fog_nullifier.desc");
      register(SRPBlocks.dodN, "tile.srparasites.dispatchern.name", "bestiary.block.srparasites.dispatchern.desc");
      register(SRPBlocks.AssimilatedSugarCane, "tile.srparasites.assimilated_reed.name", "bestiary.block.srparasites.assimilated_reed.desc");
      register(SRPBlocks.INFESTED_FURNACE, "tile.srparasites.infested_furnace.name", "bestiary.block.srparasites.infested_furnace.desc");
      register(SRPBlocks.BiomassBlock, "tile.srparasites.biomass_block.name", "bestiary.block.srparasites.biomass_block.desc");
      register(SRPBlocks.ResidueBlock, "tile.srparasites.residue_block.name", "bestiary.block.srparasites.residue_block.desc");
      register(SRPBlocks.Alveoli, "tile.srparasites.alveoli.name", "bestiary.block.srparasites.alveoli.desc");
      register(SRPBlocks.SickAlveoli, "tile.srparasites.sick_alveoli.name", "bestiary.block.srparasites.sick_alveoli.desc");
      register(SRPBlocks.HairFollicleBlock, "tile.srparasites.hair_follicle_block.name", "bestiary.block.srparasites.hair_follicle_block.desc");
      register(SRPBlocks.PARASITE_BARRIER, "tile.srparasites.parasite_barrier.name", "bestiary.block.srparasites.parasite_barrier.desc");
      register(SRPBlocks.AssimilatedPumpkin, "tile.srparasites.assimilated_pumpkin.name", "bestiary.block.srparasites.assimilated_pumpkin.desc");
      register(SRPBlocks.EscaBulb, "tile.srparasites.esca_bulb.name", "bestiary.block.srparasites.esca_bulb.desc");
      register(SRPBlocks.NODE_LAMP, "tile.srparasites.node_redstone_lamp.name", "bestiary.block.srparasites.node_redstone_lamp.desc");
      register(SRPBlocks.ParasiteLoot, "tile.srparasites.parasiteloot_common.name", "bestiary.block.srparasites.parasiteloot_common.desc");
      register(SRPBlocks.InfestPurify, "tile.srparasites.infestation_purifier.name", "bestiary.block.srparasites.infestation_purifier.desc");
      register(SRPBlocks.ParasiteMouth, "tile.srparasites.parasitemouth.name", "bestiary.block.srparasites.parasitemouth.desc");
      register(SRPBlocks.RelayBase, "tile.srparasites.relay_base.name", "bestiary.block.srparasites.relay_base.desc");
      register(SRPBlocks.RelayMiddle, "tile.srparasites.relay_middle.name", "bestiary.block.srparasites.relay_middle.desc");
      register(SRPBlocks.RelayRoof, "tile.srparasites.relay_roof.name", "bestiary.block.srparasites.relay_roof.desc");
      register(SRPBlocks.BiomePurifier, "tile.srparasites.biomepurifier.name", "bestiary.block.srparasites.biomepurifier.desc");
      register(SRPBlocks.InfestRemain, "tile.srparasites.infestremain.name", "bestiary.block.srparasites.infested_remain.desc");
      register(SRPBlocks.diseasedSponge, "tile.srparasites.diseased_sponge.name", "bestiary.block.srparasites.diseased_sponge.desc");
   }
}
