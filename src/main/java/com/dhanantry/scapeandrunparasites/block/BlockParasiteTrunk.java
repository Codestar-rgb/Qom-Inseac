/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockRotatedPillar
 *  net.minecraft.block.SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.properties.PropertyEnum
 *  net.minecraft.block.state.BlockStateContainer
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumFacing$Axis
 *  net.minecraft.util.IStringSerializable
 *  net.minecraft.util.NonNullList
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteSpreading;
import com.dhanantry.scapeandrunparasites.block.IMetaName;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.item.ItemBlockVariant;
import com.dhanantry.scapeandrunparasites.world.biome.BiomeParasiteBase;
import java.util.Objects;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockParasiteTrunk
extends BlockRotatedPillar
implements IMetaName {
    public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.func_177709_a((String)"variant", EnumType.class);

    public BlockParasiteTrunk(Material material, String name, float hardness, boolean creative, boolean tickRandom) {
        super(material);
        this.setHarvestLevel("axe", 0);
        this.setRegistryName(name);
        this.func_149663_c("srparasites." + name);
        this.func_149711_c(hardness);
        this.func_149675_a(tickRandom);
        this.func_149672_a(SoundType.field_185848_a);
        if (creative) {
            this.func_149647_a(SRPMain.SRP_CREATIVETAB);
        }
        this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(VARIANT, (Comparable)((Object)EnumType.TREE)).func_177226_a((IProperty)field_176298_M, (Comparable)EnumFacing.Axis.Y));
        SRPBlocks.SRP_BLOCKS.add((Block)this);
        ItemBlock itemBlock = this.getItemBlock();
        SRPItems.SRP_ITEMS.add((Item)itemBlock.setRegistryName(Objects.requireNonNull(this.getRegistryName())));
    }

    public int func_180651_a(IBlockState state) {
        return ((EnumType)((Object)state.func_177229_b(VARIANT))).ordinal();
    }

    public void func_149666_a(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for (EnumType variant : EnumType.values()) {
            items.add((Object)new ItemStack((Block)this, 1, variant.ordinal()));
        }
    }

    public IBlockState func_176203_a(int meta) {
        EnumFacing.Axis axis;
        EnumType variant;
        EnumType[] all = EnumType.values();
        if (meta >= 12) {
            int ax = meta - 12;
            variant = all[Math.min(4, all.length - 1)];
            axis = ax == 1 ? EnumFacing.Axis.X : (ax == 2 ? EnumFacing.Axis.Z : EnumFacing.Axis.Y);
        } else {
            variant = all[(meta & 3) % all.length];
            int bits = meta & 0xC;
            axis = bits == 4 ? EnumFacing.Axis.X : (bits == 8 ? EnumFacing.Axis.Z : EnumFacing.Axis.Y);
        }
        return this.func_176223_P().func_177226_a(VARIANT, (Comparable)((Object)variant)).func_177226_a((IProperty)field_176298_M, (Comparable)axis);
    }

    public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        EnumType[] all = EnumType.values();
        int idx = Math.min(meta, all.length - 1);
        return this.func_176223_P().func_177226_a(VARIANT, (Comparable)((Object)all[idx])).func_177226_a((IProperty)field_176298_M, (Comparable)facing.func_176740_k());
    }

    public int func_176201_c(IBlockState state) {
        EnumFacing.Axis ax = (EnumFacing.Axis)state.func_177229_b((IProperty)field_176298_M);
        int ord = ((EnumType)((Object)state.func_177229_b(VARIANT))).ordinal();
        if (ord == 4) {
            int axIdx = ax == EnumFacing.Axis.X ? 1 : (ax == EnumFacing.Axis.Z ? 2 : 0);
            return 12 + axIdx;
        }
        int axisBits = ax == EnumFacing.Axis.X ? 4 : (ax == EnumFacing.Axis.Z ? 8 : 0);
        return ord & 3 | axisBits;
    }

    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(Item.func_150898_a((Block)this), 1, ((EnumType)((Object)state.func_177229_b(VARIANT))).ordinal());
    }

    protected BlockStateContainer func_180661_e() {
        return new BlockStateContainer((Block)this, new IProperty[]{VARIANT, field_176298_M});
    }

    protected ItemStack func_180643_i(IBlockState state) {
        return super.func_180643_i(state);
    }

    @Override
    public Enum[] getVariants() {
        return EnumType.values();
    }

    public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.func_175697_a(pos, 3)) {
            return;
        }
        if (worldIn.func_180494_b(pos) instanceof BiomeParasiteBase) {
            BlockParasiteSpreading.spreadBiomeBlockTrunk(worldIn, pos, rand);
        }
    }

    @Override
    public ItemBlock getItemBlock() {
        return new ItemBlockVariant((Block)this);
    }

    public static enum EnumType implements IStringSerializable
    {
        BALL,
        TREE,
        PLANT,
        CIRCLE,
        DEADHEAD;


        public String func_176610_l() {
            return this.name().toLowerCase();
        }

        public String toString() {
            return this.func_176610_l();
        }
    }
}

