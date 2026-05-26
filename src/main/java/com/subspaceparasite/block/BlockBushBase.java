/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBush
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 */
package com.subspaceparasite.block;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.init.SPItems;
import java.util.Objects;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBushBase
extends BlockBush {
    public BlockBushBase(String name, float hardness, boolean creative, boolean tickRandom) {
        this.setRegistryName(name);
        this.func_149663_c("subspaceparasite." + name);
        this.func_149711_c(hardness);
        this.func_149675_a(tickRandom);
        if (creative) {
            this.func_149647_a(SPMain.SP_CREATIVETAB);
        }
        SPBlocks.SP_BLOCKS.add((Block)this);
        ItemBlock itemBlock = new ItemBlock((Block)this);
        SPItems.SP_ITEMS.add((Item)itemBlock.setRegistryName(Objects.requireNonNull(this.getRegistryName())));
    }
}

