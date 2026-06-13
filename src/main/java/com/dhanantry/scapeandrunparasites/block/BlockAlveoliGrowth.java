/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.BlockFaceShape
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.util.BlockRenderLayer
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.block.BlockBase;
import com.dhanantry.scapeandrunparasites.init.SRPSoundTypes;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockAlveoliGrowth
extends BlockBase {
    private static final AxisAlignedBB SHAPE = new AxisAlignedBB(0.2, 0.8, 0.2, 0.8, 1.0, 0.8);

    public BlockAlveoliGrowth() {
        super(Material.field_151585_k, "alveoli_growth", 0.0f, true, true);
        this.func_149713_g(0);
        this.func_149672_a(SRPSoundTypes.FLESH);
    }

    private boolean isSRPBlock(World w, BlockPos p) {
        Block b = w.func_180495_p(p).func_177230_c();
        ResourceLocation rl = b.getRegistryName();
        return rl != null && "srparasites".equals(rl.func_110624_b());
    }

    public boolean func_176196_c(World w, BlockPos pos) {
        return this.isSRPBlock(w, pos.func_177984_a());
    }

    public boolean func_176198_a(World w, BlockPos pos, EnumFacing side) {
        return side == EnumFacing.DOWN && this.isSRPBlock(w, pos.func_177984_a());
    }

    public void func_189540_a(IBlockState state, World w, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!this.isSRPBlock(w, pos.func_177984_a())) {
            w.func_175655_b(pos, true);
        }
    }

    public boolean func_149662_c(IBlockState s) {
        return false;
    }

    public boolean func_149686_d(IBlockState s) {
        return false;
    }

    public boolean func_149730_j(IBlockState s) {
        return false;
    }

    public boolean isNormalCube(IBlockState s, IBlockAccess w, BlockPos p) {
        return false;
    }

    public boolean func_185481_k(IBlockState s) {
        return false;
    }

    public boolean func_176205_b(IBlockAccess world, BlockPos pos) {
        return true;
    }

    public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess world, BlockPos pos) {
        return SHAPE;
    }

    public AxisAlignedBB func_180646_a(IBlockState state, IBlockAccess world, BlockPos pos) {
        return field_185506_k;
    }

    public BlockFaceShape func_193383_a(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB func_180640_a(IBlockState state, World world, BlockPos pos) {
        return SHAPE.func_186670_a(pos);
    }

    public boolean func_176200_f(IBlockAccess world, BlockPos pos) {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public BlockRenderLayer func_180664_k() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }
}

