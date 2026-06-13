/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.util.ResourceLocation
 */
package com.dhanantry.scapeandrunparasites.bestiary.blocks;

import com.dhanantry.scapeandrunparasites.bestiary.blocks.BlockBestiaryEntry;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

public final class SRPBlockCompendiumRegistry {
    private static final Map<ResourceLocation, BlockBestiaryEntry> ENTRIES = new LinkedHashMap<ResourceLocation, BlockBestiaryEntry>();

    private SRPBlockCompendiumRegistry() {
    }

    public static BlockBestiaryEntry register(Block block, String nameKey, String loreKey) {
        if (block == null) {
            System.out.println("[BlockCompendium] Skipping entry '" + nameKey + "' because block is null");
            return null;
        }
        ResourceLocation id = block.getRegistryName();
        if (id == null) {
            System.out.println("[BlockCompendium] Skipping entry '" + nameKey + "' because registryName is null for " + block);
            return null;
        }
        BlockBestiaryEntry entry = new BlockBestiaryEntry(block, nameKey, loreKey);
        ENTRIES.put(id, entry);
        return entry;
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
        SRPBlockCompendiumRegistry.clear();
        SRPBlockCompendiumRegistry.register(SRPBlocks.HarleskinnBlock, "tile.srparasites.harleskinn_block.name", "bestiary.block.srparasites.harleskinn_block.desc");
        SRPBlockCompendiumRegistry.register(SRPBlocks.LocsBlock, "tile.srparasites.locs_block.name", "bestiary.block.srparasites.locs_block.desc");
        SRPBlockCompendiumRegistry.register(SRPBlocks.FogNullifier, "tile.srparasites.fog_nullifier.name", "bestiary.block.srparasites.fog_nullifier.desc");
        SRPBlockCompendiumRegistry.register(SRPBlocks.dodN, "tile.srparasites.dispatchern.name", "bestiary.block.srparasites.dispatchern.desc");
        SRPBlockCompendiumRegistry.register(SRPBlocks.AssimilatedSugarCane, "tile.srparasites.assimilated_reed.name", "bestiary.block.srparasites.assimilated_reed.desc");
        SRPBlockCompendiumRegistry.register(SRPBlocks.INFESTED_FURNACE, "tile.srparasites.infested_furnace.name", "bestiary.block.srparasites.infested_furnace.desc");
        SRPBlockCompendiumRegistry.register(SRPBlocks.BiomassBlock, "tile.srparasites.biomass_block.name", "bestiary.block.srparasites.biomass_block.desc");
        SRPBlockCompendiumRegistry.register(SRPBlocks.ResidueBlock, "tile.srparasites.residue_block.name", "bestiary.block.srparasites.residue_block.desc");
        SRPBlockCompendiumRegistry.register(SRPBlocks.Alveoli, "tile.srparasites.alveoli.name", "bestiary.block.srparasites.alveoli.desc");
        SRPBlockCompendiumRegistry.register(SRPBlocks.SickAlveoli, "tile.srparasites.sick_alveoli.name", "bestiary.block.srparasites.sick_alveoli.desc");
        SRPBlockCompendiumRegistry.register(SRPBlocks.HairFollicleBlock, "tile.srparasites.hair_follicle_block.name", "bestiary.block.srparasites.hair_follicle_block.desc");
        SRPBlockCompendiumRegistry.register(SRPBlocks.PARASITE_BARRIER, "tile.srparasites.parasite_barrier.name", "bestiary.block.srparasites.parasite_barrier.desc");
        SRPBlockCompendiumRegistry.register(SRPBlocks.AssimilatedPumpkin, "tile.srparasites.assimilated_pumpkin.name", "bestiary.block.srparasites.assimilated_pumpkin.desc");
        SRPBlockCompendiumRegistry.register(SRPBlocks.EscaBulb, "tile.srparasites.esca_bulb.name", "bestiary.block.srparasites.esca_bulb.desc");
        SRPBlockCompendiumRegistry.register(SRPBlocks.NODE_LAMP, "tile.srparasites.node_redstone_lamp.name", "bestiary.block.srparasites.node_redstone_lamp.desc");
        SRPBlockCompendiumRegistry.register(SRPBlocks.ParasiteLoot, "tile.srparasites.parasiteloot_common.name", "bestiary.block.srparasites.parasiteloot_common.desc");
        SRPBlockCompendiumRegistry.register(SRPBlocks.InfestPurify, "tile.srparasites.infestation_purifier.name", "bestiary.block.srparasites.infestation_purifier.desc");
        SRPBlockCompendiumRegistry.register(SRPBlocks.ParasiteMouth, "tile.srparasites.parasitemouth.name", "bestiary.block.srparasites.parasitemouth.desc");
        SRPBlockCompendiumRegistry.register(SRPBlocks.RelayBase, "tile.srparasites.relay_base.name", "bestiary.block.srparasites.relay_base.desc");
        SRPBlockCompendiumRegistry.register(SRPBlocks.RelayMiddle, "tile.srparasites.relay_middle.name", "bestiary.block.srparasites.relay_middle.desc");
        SRPBlockCompendiumRegistry.register(SRPBlocks.RelayRoof, "tile.srparasites.relay_roof.name", "bestiary.block.srparasites.relay_roof.desc");
        SRPBlockCompendiumRegistry.register(SRPBlocks.BiomePurifier, "tile.srparasites.biomepurifier.name", "bestiary.block.srparasites.biomepurifier.desc");
        SRPBlockCompendiumRegistry.register(SRPBlocks.InfestRemain, "tile.srparasites.infestremain.name", "bestiary.block.srparasites.infested_remain.desc");
        SRPBlockCompendiumRegistry.register(SRPBlocks.diseasedSponge, "tile.srparasites.diseased_sponge.name", "bestiary.block.srparasites.diseased_sponge.desc");
    }
}

