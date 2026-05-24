/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.block.BlockSlab$EnumBlockHalf
 *  net.minecraft.block.SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.properties.PropertyEnum
 *  net.minecraft.block.state.BlockStateContainer
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IStringSerializable
 *  net.minecraft.util.NonNullList
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.world.World
 */
package com.subspaceparasite.block.slabs;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.block.slabs.BlockSlabBase;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.init.SPItems;
import com.subspaceparasite.init.SPSounds;
import com.subspaceparasite.item.ItemBlockVariant;
import java.util.Objects;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public abstract class BlockSlabStain
extends BlockSlabBase {
    public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.func_177709_a((String)"variant", EnumType.class);

    public BlockSlabStain(Material materialIn, String name, float hardness, boolean creative, boolean tickRandom, BlockSlab half) {
        super(materialIn, name, hardness, tickRandom, tickRandom, half);
        this.setHarvestLevel("shovel", 0);
        this.func_149672_a(new SoundType(1.0f, 0.5f, SPSounds.BLOCKINFEST_BREAK, SPSounds.BLOCKINFEST_STEP, SPSounds.BLOCKINFEST_PLACE, SPSounds.BLOCKINFEST_HIT, SoundEvents.field_187876_fn));
        if (creative) {
            this.func_149647_a(SPMain.SP_CREATIVETAB);
        }
        this.field_149783_u = !this.func_176552_j();
        IBlockState state = this.field_176227_L.func_177621_b().func_177226_a(VARIANT, (Comparable)((Object)EnumType.DIRT));
        if (!this.func_176552_j()) {
            state = state.func_177226_a((IProperty)field_176554_a, (Comparable)BlockSlab.EnumBlockHalf.BOTTOM);
        }
    }

    public ItemStack func_185473_a(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack((Block)this, 1, ((EnumType)((Object)state.func_177229_b(VARIANT))).ordinal());
    }

    public String func_150002_b(int meta) {
        return super.func_149739_a();
    }

    public IProperty<?> func_176551_l() {
        return VARIANT;
    }

    public Comparable<?> func_185674_a(ItemStack stack) {
        return EnumType.values()[stack.func_77960_j() & 7];
    }

    public void func_149666_a(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for (EnumType variant : EnumType.values()) {
            items.add((Object)new ItemStack((Block)this, 1, variant.ordinal()));
        }
    }

    public IBlockState func_176203_a(int meta) {
        IBlockState state = this.func_176223_P().func_177226_a(VARIANT, (Comparable)((Object)EnumType.values()[meta & 7]));
        if (!this.func_176552_j()) {
            state = state.func_177226_a((IProperty)field_176554_a, (Comparable)((meta & 8) != 0 ? BlockSlab.EnumBlockHalf.TOP : BlockSlab.EnumBlockHalf.BOTTOM));
        }
        return state;
    }

    public int func_176201_c(IBlockState state) {
        int meta = ((EnumType)((Object)state.func_177229_b(VARIANT))).ordinal();
        if (!this.func_176552_j() && state.func_177229_b((IProperty)field_176554_a) == BlockSlab.EnumBlockHalf.TOP) {
            meta |= 8;
        }
        return meta;
    }

    protected BlockStateContainer func_180661_e() {
        return new BlockStateContainer((Block)this, new IProperty[]{field_176554_a, VARIANT});
    }

    public int func_180651_a(IBlockState state) {
        return this.func_176201_c(state);
    }

    @Override
    public ItemBlock getItemBlock() {
        return new ItemBlockVariant((Block)this);
    }

    @Override
    public Enum[] getVariants() {
        return EnumType.values();
    }

    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(Item.func_150898_a((Block)this.getHalfBlock()), 1, state.func_177230_c().func_176201_c(state.func_177226_a((IProperty)field_176554_a, (Comparable)BlockSlab.EnumBlockHalf.BOTTOM)));
    }

    @Override
    public BlockSlab getHalfBlock() {
        return SPBlocks.ParasiteStainSlabHalf;
    }

    @Override
    public BlockSlab getDoubleBlock() {
        return SPBlocks.ParasiteStainSlabDouble;
    }

    public static class BlockSlabStainHalf
    extends BlockSlabStain {
        public BlockSlabStainHalf(Material materialIn, String name, float hardness, boolean creative, boolean tickRandom, BlockSlab half, BlockSlab doubleSlab) {
            super(materialIn, name + "slabhalf", hardness, creative, tickRandom, half);
            SPBlocks.SP_BLOCKS.add((Block)this);
        }

        public boolean func_176552_j() {
            return false;
        }
    }

    public static class BlockSlabStainDouble
    extends BlockSlabStain {
        public BlockSlabStainDouble(Material materialIn, String name, float hardness, boolean creative, boolean tickRandom, BlockSlab half) {
            super(materialIn, name + "slabdouble", hardness, creative, tickRandom, half);
            SPBlocks.SP_BLOCKS.add((Block)this);
            SPItems.SP_ITEMS.add((Item)new ItemBlock((Block)this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
        }

        public boolean func_176552_j() {
            return true;
        }
    }

    public static enum EnumType implements IStringSerializable
    {
        DIRT,
        MUD,
        SFLESH,
        FEELER,
        SPORE,
        RED,
        SACKFLESH;


        public String func_176610_l() {
            return this.name().toLowerCase();
        }

        public String toString() {
            return this.func_176610_l();
        }
    }
}

