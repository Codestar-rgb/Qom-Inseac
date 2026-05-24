/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.properties.PropertyInteger
 *  net.minecraft.block.state.BlockFaceShape
 *  net.minecraft.block.state.BlockStateContainer
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.util.BlockRenderLayer
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.block;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.init.SPItems;
import java.util.Objects;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockParasiteFog
extends Block {
    public static final PropertyInteger STAGE = PropertyInteger.func_177719_a((String)"air", (int)0, (int)2);

    public BlockParasiteFog(String name) {
        super(Material.field_151579_a);
        this.setRegistryName(name);
        this.func_149663_c("subspaceparasite." + name);
        this.func_149647_a(SPMain.SP_CREATIVETAB);
        this.func_149675_a(true);
        SPBlocks.SP_BLOCKS.add(this);
        SPItems.SP_ITEMS.add((Item)new ItemBlock((Block)this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
        this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a((IProperty)STAGE, (Comparable)Integer.valueOf(0)));
    }

    public boolean func_149662_c(IBlockState state) {
        return false;
    }

    public boolean func_149686_d(IBlockState state) {
        return false;
    }

    public boolean func_176200_f(IBlockAccess worldIn, BlockPos pos) {
        return true;
    }

    public boolean func_176209_a(IBlockState state, boolean hitIfLiquid) {
        return false;
    }

    public void func_180653_a(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
    }

    public Item func_180660_a(IBlockState state, Random rand, int fortune) {
        return null;
    }

    protected BlockStateContainer func_180661_e() {
        return new BlockStateContainer((Block)this, new IProperty[]{STAGE});
    }

    public int func_176201_c(IBlockState state) {
        return (Integer)state.func_177229_b((IProperty)STAGE);
    }

    public IBlockState func_176203_a(int meta) {
        return this.func_176223_P().func_177226_a((IProperty)STAGE, (Comparable)Integer.valueOf(meta & 2));
    }

    public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        super.func_180650_b(worldIn, pos, state, rand);
        int meta = worldIn.func_180495_p(pos).func_177230_c().func_176201_c(worldIn.func_180495_p(pos));
        if (meta == 0) {
            worldIn.func_175656_a(pos, SPBlocks.ParasiteFog.func_176223_P().func_177226_a((IProperty)STAGE, (Comparable)Integer.valueOf(1)));
        }
        if (meta == 2) {
            int i1 = pos.func_177956_o();
            double l1 = pos.func_177958_n();
            double i2 = pos.func_177952_p();
            int BGrange = 2;
            for (int k2 = -1 * BGrange; k2 <= 1 * BGrange; ++k2) {
                for (int l2 = -1 * BGrange; l2 <= 1 * BGrange; ++l2) {
                    for (int j = -1 * BGrange; j <= 1 * BGrange; ++j) {
                        int meta2;
                        double i3 = l1 + (double)k2;
                        double k = i1 + j;
                        double l = i2 + (double)l2;
                        BlockPos blockpos = new BlockPos(i3, k, l);
                        IBlockState iblockstate = worldIn.func_180495_p(blockpos);
                        Block block = iblockstate.func_177230_c();
                        if (block != SPBlocks.ParasiteFog || (meta2 = block.func_176201_c(iblockstate)) == 2) continue;
                        worldIn.func_175656_a(blockpos, SPBlocks.ParasiteFog.func_176223_P().func_177226_a((IProperty)STAGE, (Comparable)Integer.valueOf(2)));
                    }
                }
            }
            worldIn.func_175656_a(pos, Blocks.field_150350_a.func_176223_P());
        }
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_176225_a(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        IBlockState iblockstate = blockAccess.func_180495_p(pos.func_177972_a(side));
        Block block = iblockstate.func_177230_c();
        if (this == SPBlocks.ParasiteFog) {
            if (blockState != iblockstate) {
                return true;
            }
            if (block == this) {
                return side != EnumFacing.UP && side != EnumFacing.DOWN;
            }
        }
        return block != this && super.func_176225_a(blockState, blockAccess, pos, side);
    }

    @SideOnly(value=Side.CLIENT)
    public BlockRenderLayer func_180664_k() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Nullable
    public AxisAlignedBB func_180646_a(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return field_185506_k;
    }

    public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }
}

