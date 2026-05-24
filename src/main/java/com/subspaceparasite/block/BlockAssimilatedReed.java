/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.properties.PropertyInteger
 *  net.minecraft.block.state.BlockStateContainer
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.item.Item
 *  net.minecraft.util.BlockRenderLayer
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.fluids.Fluid
 *  net.minecraftforge.fluids.FluidRegistry
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.block;

import com.subspaceparasite.block.BlockBushBase;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockAssimilatedReed
extends BlockBushBase {
    private static final String MODID = "subspaceparasite";
    private static final String DEADBLOOD_NAME = "deadblood";
    private static final Fluid DEADBLOOD = FluidRegistry.getFluid((String)"deadblood");
    public static final PropertyInteger AGE = PropertyInteger.func_177719_a((String)"age", (int)0, (int)15);
    private static final AxisAlignedBB AABB = new AxisAlignedBB(0.125, 0.0, 0.125, 0.875, 1.0, 0.875);

    public BlockAssimilatedReed() {
        this("assimilated_reed", 0.0f, true, true);
    }

    public BlockAssimilatedReed(String name, float hardness, boolean creative, boolean tickRandom) {
        super(name, hardness, creative, tickRandom);
        this.func_149672_a(SoundType.field_185850_c);
        this.func_149675_a(true);
        this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a((IProperty)AGE, (Comparable)Integer.valueOf(0)));
    }

    public boolean func_149662_c(IBlockState state) {
        return false;
    }

    public boolean func_149686_d(IBlockState state) {
        return false;
    }

    public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return AABB;
    }

    @SideOnly(value=Side.CLIENT)
    public BlockRenderLayer func_180664_k() {
        return BlockRenderLayer.CUTOUT;
    }

    public boolean func_176196_c(World world, BlockPos pos) {
        IBlockState below = world.func_180495_p(pos.func_177977_b());
        if (below.func_177230_c() == this) {
            return true;
        }
        return BlockAssimilatedReed.isSPBlock(below) && BlockAssimilatedReed.hasAdjacentGrowthFluid(world, pos.func_177977_b());
    }

    public void func_176213_c(World world, BlockPos pos, IBlockState state) {
        super.func_176213_c(world, pos, state);
        this.checkAndDrop(world, pos, state);
    }

    public void func_189540_a(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
        this.checkAndDrop(world, pos, state);
    }

    public boolean func_180671_f(World world, BlockPos pos, IBlockState state) {
        IBlockState below = world.func_180495_p(pos.func_177977_b());
        if (below.func_177230_c() == this) {
            return true;
        }
        return BlockAssimilatedReed.isSPBlock(below) && BlockAssimilatedReed.hasAdjacentGrowthFluid(world, pos.func_177977_b());
    }

    private void checkAndDrop(World world, BlockPos pos, IBlockState state) {
        if (!this.func_180671_f(world, pos, state)) {
            world.func_175655_b(pos, true);
        }
    }

    private static boolean isSPBlock(IBlockState state) {
        ResourceLocation id = state.func_177230_c().getRegistryName();
        return id != null && MODID.equals(id.func_110624_b());
    }

    private static boolean hasAdjacentGrowthFluid(World world, BlockPos base) {
        for (EnumFacing f : EnumFacing.field_176754_o) {
            IBlockState st = world.func_180495_p(base.func_177972_a(f));
            if (st.func_185904_a() == Material.field_151586_h) {
                return true;
            }
            Fluid neighbor = FluidRegistry.lookupFluidForBlock((Block)st.func_177230_c());
            if (neighbor == null || (DEADBLOOD == null || neighbor != DEADBLOOD) && !DEADBLOOD_NAME.equals(neighbor.getName()) && !"subspaceparasite:deadblood".equals(neighbor.getName())) continue;
            return true;
        }
        return false;
    }

    public void func_180650_b(World world, BlockPos pos, IBlockState state, Random rand) {
        if (!world.func_175697_a(pos, 1)) {
            return;
        }
        if (!this.func_180671_f(world, pos, state)) {
            this.checkAndDrop(world, pos, state);
            return;
        }
        if (world.func_175623_d(pos.func_177984_a())) {
            int height = 1;
            while (world.func_180495_p(pos.func_177979_c(height)).func_177230_c() == this) {
                ++height;
            }
            if (height < 7) {
                int age = (Integer)state.func_177229_b((IProperty)AGE);
                if (age >= 15) {
                    world.func_175656_a(pos.func_177984_a(), this.func_176223_P());
                    world.func_180501_a(pos, state.func_177226_a((IProperty)AGE, (Comparable)Integer.valueOf(0)), 4);
                } else {
                    world.func_180501_a(pos, state.func_177226_a((IProperty)AGE, (Comparable)Integer.valueOf(age + 1)), 4);
                }
            }
        }
    }

    protected BlockStateContainer func_180661_e() {
        return new BlockStateContainer((Block)this, new IProperty[]{AGE});
    }

    public int func_176201_c(IBlockState state) {
        return (Integer)state.func_177229_b((IProperty)AGE);
    }

    public IBlockState func_176203_a(int meta) {
        if (meta < 0) {
            meta = 0;
        }
        if (meta > 15) {
            meta = 15;
        }
        return this.func_176223_P().func_177226_a((IProperty)AGE, (Comparable)Integer.valueOf(meta));
    }

    public Item func_180660_a(IBlockState state, Random rand, int fortune) {
        return Item.func_150898_a((Block)this);
    }
}

