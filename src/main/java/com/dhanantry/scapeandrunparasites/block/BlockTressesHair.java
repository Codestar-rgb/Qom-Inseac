/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockDoublePlant
 *  net.minecraft.block.SoundType
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.BlockRenderLayer
 *  net.minecraft.util.NonNullList
 */
package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import java.util.Objects;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;

public class BlockTressesHair
extends BlockDoublePlant {
    public BlockTressesHair(String name) {
        this.setRegistryName(name);
        this.func_149663_c("srparasites." + name);
        this.func_149647_a(SRPMain.SRP_CREATIVETAB);
        this.func_149672_a(SoundType.field_185850_c);
        this.func_149711_c(0.0f);
        SRPBlocks.SRP_BLOCKS.add((Block)this);
        SRPItems.SRP_ITEMS.add((Item)new ItemBlock((Block)this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
    }

    public boolean func_149662_c(IBlockState s) {
        return false;
    }

    public boolean func_149686_d(IBlockState s) {
        return false;
    }

    public BlockRenderLayer func_180664_k() {
        return BlockRenderLayer.CUTOUT;
    }

    protected boolean func_185514_i(IBlockState stateBelow) {
        return this.isSRPBlock(stateBelow);
    }

    private boolean isSRPBlock(IBlockState s) {
        if (s == null) {
            return false;
        }
        Block b = s.func_177230_c();
        return b.getRegistryName() != null && "srparasites".equals(b.getRegistryName().func_110624_b());
    }

    public void func_149666_a(CreativeTabs tab, NonNullList<ItemStack> items) {
        CreativeTabs selfTab = this.func_149708_J();
        if (selfTab != null && (tab == selfTab || tab == CreativeTabs.field_78027_g)) {
            items.add((Object)new ItemStack((Block)this, 1, 0));
        }
    }

    public int func_180651_a(IBlockState state) {
        return 0;
    }
}

