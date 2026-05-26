/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockRotatedPillar
 *  net.minecraft.block.material.Material
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 */
package com.subspaceparasite.block;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.init.SPItems;
import java.util.Objects;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockHairFolliclePillar
extends BlockRotatedPillar {
    public BlockHairFolliclePillar(String name, Material mat) {
        super(mat);
        this.setRegistryName(name);
        this.func_149663_c("subspaceparasite." + name);
        this.func_149647_a(SPMain.SP_CREATIVETAB);
        this.func_149711_c(1.0f);
        SPBlocks.SP_BLOCKS.add((Block)this);
        SPItems.SP_ITEMS.add((Item)new ItemBlock((Block)this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
    }
}

