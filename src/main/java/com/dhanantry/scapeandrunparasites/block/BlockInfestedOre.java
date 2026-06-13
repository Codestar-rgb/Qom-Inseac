/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.properties.PropertyEnum
 *  net.minecraft.block.state.BlockStateContainer
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IStringSerializable
 *  net.minecraft.util.NonNullList
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.block.BlockBase;
import com.dhanantry.scapeandrunparasites.block.IMetaName;
import com.dhanantry.scapeandrunparasites.client.particle.ParticleSpawner;
import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.item.ItemBlockVariant;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockInfestedOre
extends BlockBase
implements IMetaName {
    public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.func_177709_a((String)"variant", EnumType.class);

    public BlockInfestedOre(Material material, String name, float hardness, boolean creative, boolean tickRandom) {
        super(material, name, hardness, creative, tickRandom);
        this.setHarvestLevel("pickaxe", 1);
        this.func_149672_a(new SoundType(1.0f, 0.5f, SRPSounds.BLOCKINFEST_BREAK, SRPSounds.BLOCKINFEST_STEP, SRPSounds.BLOCKINFEST_PLACE, SRPSounds.BLOCKINFEST_HIT, SoundEvents.field_187876_fn));
        this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(VARIANT, (Comparable)((Object)EnumType.CO)));
    }

    public int func_180651_a(IBlockState state) {
        return this.func_176201_c(state);
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
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        this.func_176208_a(world, pos, state, player);
        return world.func_180501_a(pos, Blocks.field_150350_a.func_176223_P(), world.field_72995_K ? 11 : 3);
    }

    public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
    }

    public void func_176199_a(World worldIn, BlockPos pos, Entity entityIn) {
    }

    public Item func_180660_a(IBlockState state, Random rand, int fortune) {
        switch ((EnumType)((Object)state.func_177229_b(VARIANT))) {
            case CO: {
                break;
            }
            case DIA: {
                break;
            }
            case EME: {
                break;
            }
            case GOL: {
                break;
            }
            case IRO: {
                break;
            }
            case LAP: {
                break;
            }
            case RED: {
                break;
            }
            case UN: {
                break;
            }
        }
        return this == Blocks.field_150449_bY ? Items.field_151128_bU : Item.func_150898_a((Block)this);
    }

    @Override
    public ItemBlock getItemBlock() {
        return new ItemBlockVariant(this);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_180655_c(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (worldIn.func_180495_p(pos.func_177984_a()).func_177230_c() != Blocks.field_150350_a && worldIn.func_180495_p(pos.func_177984_a()).func_177230_c() != SRPBlocks.ParasiteBush) {
            return;
        }
        if (rand.nextDouble() <= (double)SRPConfigSystems.rsBlockParticleS) {
            double d0 = (double)pos.func_177958_n() + rand.nextDouble();
            double d1 = (double)pos.func_177956_o() + 2.5;
            double d2 = (double)pos.func_177952_p() + rand.nextDouble();
            ParticleSpawner.spawnParticle(SRPEnumParticle.SPORE, d0, d1, d2, 0.0, 0.0, 0.0, 0, 0, 0);
        }
    }

    public static enum EnumType implements IStringSerializable
    {
        CO,
        DIA,
        EME,
        GOL,
        IRO,
        LAP,
        RED,
        UN;


        public String func_176610_l() {
            return this.name().toLowerCase();
        }

        public String toString() {
            return this.func_176610_l();
        }
    }
}

