/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.properties.PropertyEnum
 *  net.minecraft.block.state.BlockStateContainer
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IStringSerializable
 *  net.minecraft.util.NonNullList
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.block.BlockBase;
import com.dhanantry.scapeandrunparasites.block.IMetaName;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.item.ItemBlockVariant;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockParasiteCanister
extends BlockBase
implements IMetaName {
    public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.func_177709_a((String)"variant", EnumType.class);
    protected static final AxisAlignedBB TALL_GRASS_AABB = new AxisAlignedBB(0.1, 0.0, 0.1, 0.9, 0.55, 0.9);

    public BlockParasiteCanister(Material material, String name, float hardness, boolean creative, boolean tickRandom) {
        super(material, name, hardness, creative, tickRandom);
        this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(VARIANT, (Comparable)((Object)EnumType.SAC)));
    }

    public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) {
        if (state.func_177229_b(VARIANT) == EnumType.CYST) {
            return TALL_GRASS_AABB;
        }
        return super.func_185496_a(state, source, pos);
    }

    public boolean func_149662_c(IBlockState state) {
        return false;
    }

    public boolean func_149686_d(IBlockState state) {
        return false;
    }

    public int func_180651_a(IBlockState state) {
        return this.func_176201_c(state);
    }

    public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.func_175697_a(pos, 3)) {
            return;
        }
        if (!worldIn.field_72995_K && state.func_177229_b(VARIANT) == EnumType.CYST && worldIn.func_180495_p(pos.func_177977_b()).func_177230_c() == Blocks.field_150350_a) {
            worldIn.func_175656_a(pos, Blocks.field_150350_a.func_176223_P());
        }
    }

    public Item func_180660_a(IBlockState state, Random rand, int fortune) {
        switch ((EnumType)((Object)state.func_177229_b(VARIANT))) {
            case SAC: {
                return SRPItems.itemlurecomponent6;
            }
        }
        return super.func_180660_a(state, rand, fortune);
    }

    public int quantityDropped(IBlockState state, int fortune, Random random) {
        if (state.func_177229_b(VARIANT) == EnumType.SAC) {
            if (random.nextDouble() < 0.05) {
                return 1;
            }
            return 0;
        }
        return super.quantityDropped(state, fortune, random);
    }

    public void func_149666_a(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for (EnumType variant : EnumType.values()) {
            items.add((Object)new ItemStack((Block)this, 1, variant.ordinal()));
        }
    }

    public IBlockState func_176203_a(int meta) {
        return this.func_176223_P().func_177226_a(VARIANT, (Comparable)((Object)EnumType.values()[meta]));
    }

    public int func_176201_c(IBlockState state) {
        return ((EnumType)((Object)state.func_177229_b(VARIANT))).ordinal();
    }

    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(Item.func_150898_a((Block)this), 1, this.func_176201_c(world.func_180495_p(pos)));
    }

    protected BlockStateContainer func_180661_e() {
        return new BlockStateContainer((Block)this, new IProperty[]{VARIANT});
    }

    @Override
    public Enum[] getVariants() {
        return EnumType.values();
    }

    @Override
    public ItemBlock getItemBlock() {
        return new ItemBlockVariant(this);
    }

    public static enum EnumType implements IStringSerializable
    {
        SAC,
        CYST,
        LUMP,
        BAG;


        public String func_176610_l() {
            return this.name().toLowerCase();
        }

        public String toString() {
            return this.func_176610_l();
        }
    }
}

