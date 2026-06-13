/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBush
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.util.BlockRenderLayer
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.init.SRPSoundTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSRPFlower
extends BlockBush {
    public BlockSRPFlower(String regName) {
        this.setRegistryName(new ResourceLocation("srparasites", regName));
        this.func_149663_c("srparasites." + regName);
        this.func_149647_a(SRPMain.SRP_CREATIVETAB);
        this.func_149672_a(SRPSoundTypes.FLESH);
        SRPBlocks.SRP_BLOCKS.add((Block)this);
        SRPItems.SRP_ITEMS.add((Item)new ItemBlock((Block)this).setRegistryName(this.getRegistryName()));
    }

    protected boolean func_185514_i(IBlockState state) {
        Material m = state.func_185904_a();
        return m == Material.field_151577_b || m == Material.field_151578_c || m == Material.field_151595_p || m == Material.field_151571_B;
    }

    public boolean func_149662_c(IBlockState state) {
        return false;
    }

    public boolean func_149686_d(IBlockState state) {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public BlockRenderLayer func_180664_k() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    public boolean func_176196_c(World worldIn, BlockPos pos) {
        return super.func_176196_c(worldIn, pos) && this.func_185514_i(worldIn.func_180495_p(pos.func_177977_b()));
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_176225_a(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
        return true;
    }
}

