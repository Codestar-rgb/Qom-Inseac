/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockRotatedPillar
 *  net.minecraft.block.SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.state.BlockStateContainer
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumFacing$Axis
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.world.World
 */
package com.subspaceparasite.block;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.block.BlockParasiteSpreading;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.init.SPItems;
import com.subspaceparasite.util.ParasiteEventWorld;
import com.subspaceparasite.util.config.SPConfigSystems;
import com.subspaceparasite.world.SPSaveData;
import com.subspaceparasite.world.biome.BiomeParasiteBase;
import java.util.Objects;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockInfestedTrunk
extends BlockRotatedPillar {
    public BlockInfestedTrunk(Material material, String name, float hardness, boolean creative, boolean tickRandom) {
        super(material);
        this.setHarvestLevel("axe", 0);
        this.setRegistryName(name);
        this.func_149663_c("subspaceparasite." + name);
        this.func_149711_c(hardness);
        this.func_149675_a(tickRandom);
        this.func_149672_a(SoundType.field_185848_a);
        if (creative) {
            this.func_149647_a(SPMain.SP_CREATIVETAB);
        }
        this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a((IProperty)field_176298_M, (Comparable)EnumFacing.Axis.Y));
        SPBlocks.SP_BLOCKS.add((Block)this);
        SPItems.SP_ITEMS.add((Item)new ItemBlock((Block)this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
    }

    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        boolean flag = super.removedByPlayer(state, world, pos, player, willHarvest);
        if (world.field_72995_K) {
            return flag;
        }
        if (SPConfigSystems.useEvolution && this.func_176201_c(state) > 0) {
            SPSaveData data = SPSaveData.get(world, 10);
            data.setTotalKills(world.field_73011_w.getDimension(), -SPConfigSystems.valueLossBlockTrunk, true, world, false, 22);
        }
        return flag;
    }

    public boolean func_149662_c(IBlockState state) {
        return false;
    }

    public void func_180645_a(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.func_175697_a(pos, 3)) {
            return;
        }
        if (rand.nextDouble() < 0.5) {
            if (worldIn.func_180494_b(pos) instanceof BiomeParasiteBase) {
                worldIn.func_175656_a(pos, SPBlocks.ParasiteStain.func_176223_P());
            } else {
                int heart = ParasiteEventWorld.canBiomeStillExist(worldIn, pos, true);
                if (heart > 0) {
                    BlockParasiteSpreading.SpreadBiome(worldIn, pos, heart, ParasiteEventWorld.canBiomeStillExistType(worldIn, pos, true));
                }
            }
        } else if (rand.nextDouble() <= 0.05) {
            this.func_180650_b(worldIn, pos, state, rand);
        }
    }

    public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.func_175697_a(pos, 3)) {
            return;
        }
        if (!worldIn.field_72995_K) {
            this.func_176201_c(state);
            BlockParasiteSpreading.canInfestBlock(worldIn, pos, rand, 2, true);
        }
    }

    public IBlockState func_176203_a(int meta) {
        IBlockState state = this.func_176223_P();
        int i = meta & 0xC;
        EnumFacing.Axis enumfacing$axis = EnumFacing.Axis.Y;
        if (i == 4) {
            enumfacing$axis = EnumFacing.Axis.X;
        } else if (i == 8) {
            enumfacing$axis = EnumFacing.Axis.Z;
        }
        return state.func_177226_a((IProperty)field_176298_M, (Comparable)enumfacing$axis);
    }

    public int func_176201_c(IBlockState state) {
        int i = 0;
        EnumFacing.Axis enumfacing$axis = (EnumFacing.Axis)state.func_177229_b((IProperty)field_176298_M);
        if (enumfacing$axis == EnumFacing.Axis.X) {
            i |= 4;
        } else if (enumfacing$axis == EnumFacing.Axis.Z) {
            i |= 8;
        }
        return i;
    }

    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(Item.func_150898_a((Block)this), 1, this.func_176201_c(world.func_180495_p(pos)));
    }

    protected BlockStateContainer func_180661_e() {
        return new BlockStateContainer((Block)this, new IProperty[]{field_176298_M});
    }

    protected ItemStack func_180643_i(IBlockState state) {
        return super.func_180643_i(state);
    }
}

