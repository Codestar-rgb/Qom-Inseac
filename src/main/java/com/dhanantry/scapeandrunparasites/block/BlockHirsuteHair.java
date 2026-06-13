/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBush
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.util.BlockRenderLayer
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.IBlockAccess
 */
package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.init.SRPSoundTypes;
import java.util.Objects;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockHirsuteHair
extends BlockBush {
    private static final AxisAlignedBB AABB = new AxisAlignedBB(0.1, 0.0, 0.1, 0.9, 0.9, 0.9);

    public BlockHirsuteHair(String name) {
        this.setRegistryName(name);
        this.func_149663_c("srparasites." + name);
        this.func_149647_a(SRPMain.SRP_CREATIVETAB);
        this.func_149672_a(SRPSoundTypes.FLESH);
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

    public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess src, BlockPos pos) {
        return AABB;
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
}

