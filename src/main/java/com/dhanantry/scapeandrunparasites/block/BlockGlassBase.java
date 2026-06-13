/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGlass
 *  net.minecraft.block.material.Material
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 */
package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import java.util.Objects;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockGlassBase
extends BlockGlass {
    public BlockGlassBase(String name, float hardness, boolean creative, boolean tickRandom) {
        this(name, hardness, creative, tickRandom, Material.field_151592_s, false);
    }

    public BlockGlassBase(String name, float hardness, boolean creative, boolean tickRandom, Material material, boolean ignoreSimilarity) {
        super(material, ignoreSimilarity);
        this.setRegistryName(name);
        this.func_149663_c("srparasites." + name);
        this.func_149711_c(hardness);
        this.func_149675_a(tickRandom);
        if (creative) {
            this.func_149647_a(SRPMain.SRP_CREATIVETAB);
        }
        SRPBlocks.SRP_BLOCKS.add((Block)this);
        ItemBlock itemBlock = new ItemBlock((Block)this);
        SRPItems.SRP_ITEMS.add((Item)itemBlock.setRegistryName(Objects.requireNonNull(this.getRegistryName())));
    }
}

