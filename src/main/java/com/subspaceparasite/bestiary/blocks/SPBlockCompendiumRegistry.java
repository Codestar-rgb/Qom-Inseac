/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.util.ResourceLocation
 */
package com.subspaceparasite.bestiary.blocks;

import com.subspaceparasite.bestiary.blocks.BlockBestiaryEntry;
import com.subspaceparasite.init.SPBlocks;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

public final class SPBlockCompendiumRegistry {
    private static final Map<ResourceLocation, BlockBestiaryEntry> ENTRIES = new LinkedHashMap<ResourceLocation, BlockBestiaryEntry>();

    private SPBlockCompendiumRegistry() {
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
        SPBlockCompendiumRegistry.clear();
        SPBlockCompendiumRegistry.register(SPBlocks.HarleskinnBlock, "tile.subspaceparasite.harleskinn_block.name", "bestiary.block.subspaceparasite.harleskinn_block.desc");
        SPBlockCompendiumRegistry.register(SPBlocks.LocsBlock, "tile.subspaceparasite.locs_block.name", "bestiary.block.subspaceparasite.locs_block.desc");
        SPBlockCompendiumRegistry.register(SPBlocks.FogNullifier, "tile.subspaceparasite.fog_nullifier.name", "bestiary.block.subspaceparasite.fog_nullifier.desc");
        SPBlockCompendiumRegistry.register(SPBlocks.dodN, "tile.subspaceparasite.dispatchern.name", "bestiary.block.subspaceparasite.dispatchern.desc");
        SPBlockCompendiumRegistry.register(SPBlocks.AssimilatedSugarCane, "tile.subspaceparasite.assimilated_reed.name", "bestiary.block.subspaceparasite.assimilated_reed.desc");
        SPBlockCompendiumRegistry.register(SPBlocks.INFESTED_FURNACE, "tile.subspaceparasite.infested_furnace.name", "bestiary.block.subspaceparasite.infested_furnace.desc");
        SPBlockCompendiumRegistry.register(SPBlocks.BiomassBlock, "tile.subspaceparasite.biomass_block.name", "bestiary.block.subspaceparasite.biomass_block.desc");
        SPBlockCompendiumRegistry.register(SPBlocks.ResidueBlock, "tile.subspaceparasite.residue_block.name", "bestiary.block.subspaceparasite.residue_block.desc");
        SPBlockCompendiumRegistry.register(SPBlocks.Alveoli, "tile.subspaceparasite.alveoli.name", "bestiary.block.subspaceparasite.alveoli.desc");
        SPBlockCompendiumRegistry.register(SPBlocks.SickAlveoli, "tile.subspaceparasite.sick_alveoli.name", "bestiary.block.subspaceparasite.sick_alveoli.desc");
        SPBlockCompendiumRegistry.register(SPBlocks.HairFollicleBlock, "tile.subspaceparasite.hair_follicle_block.name", "bestiary.block.subspaceparasite.hair_follicle_block.desc");
        SPBlockCompendiumRegistry.register(SPBlocks.PARASITE_BARRIER, "tile.subspaceparasite.parasite_barrier.name", "bestiary.block.subspaceparasite.parasite_barrier.desc");
        SPBlockCompendiumRegistry.register(SPBlocks.AssimilatedPumpkin, "tile.subspaceparasite.assimilated_pumpkin.name", "bestiary.block.subspaceparasite.assimilated_pumpkin.desc");
        SPBlockCompendiumRegistry.register(SPBlocks.EscaBulb, "tile.subspaceparasite.esca_bulb.name", "bestiary.block.subspaceparasite.esca_bulb.desc");
        SPBlockCompendiumRegistry.register(SPBlocks.NODE_LAMP, "tile.subspaceparasite.node_redstone_lamp.name", "bestiary.block.subspaceparasite.node_redstone_lamp.desc");
        SPBlockCompendiumRegistry.register(SPBlocks.ParasiteLoot, "tile.subspaceparasite.parasiteloot_common.name", "bestiary.block.subspaceparasite.parasiteloot_common.desc");
        SPBlockCompendiumRegistry.register(SPBlocks.InfestPurify, "tile.subspaceparasite.infestation_purifier.name", "bestiary.block.subspaceparasite.infestation_purifier.desc");
        SPBlockCompendiumRegistry.register(SPBlocks.ParasiteMouth, "tile.subspaceparasite.parasitemouth.name", "bestiary.block.subspaceparasite.parasitemouth.desc");
        SPBlockCompendiumRegistry.register(SPBlocks.RelayBase, "tile.subspaceparasite.relay_base.name", "bestiary.block.subspaceparasite.relay_base.desc");
        SPBlockCompendiumRegistry.register(SPBlocks.RelayMiddle, "tile.subspaceparasite.relay_middle.name", "bestiary.block.subspaceparasite.relay_middle.desc");
        SPBlockCompendiumRegistry.register(SPBlocks.RelayRoof, "tile.subspaceparasite.relay_roof.name", "bestiary.block.subspaceparasite.relay_roof.desc");
        SPBlockCompendiumRegistry.register(SPBlocks.BiomePurifier, "tile.subspaceparasite.biomepurifier.name", "bestiary.block.subspaceparasite.biomepurifier.desc");
        SPBlockCompendiumRegistry.register(SPBlocks.InfestRemain, "tile.subspaceparasite.infestremain.name", "bestiary.block.subspaceparasite.infested_remain.desc");
        SPBlockCompendiumRegistry.register(SPBlocks.diseasedSponge, "tile.subspaceparasite.diseased_sponge.name", "bestiary.block.subspaceparasite.diseased_sponge.desc");
    }
}

