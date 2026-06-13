/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.BlockFaceShape
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.BlockRenderLayer
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.block.BlockBase;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityLodo;
import com.dhanantry.scapeandrunparasites.init.SRPSoundTypes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBuglin
extends BlockBase {
    protected static final AxisAlignedBB TALL_GRASS_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.125, 1.0);

    public BlockBuglin(String name, float hardness, boolean creative, boolean tickRandom, float resistance) {
        super(Material.field_151583_m, name, hardness, creative, tickRandom, resistance);
        this.func_149672_a(SRPSoundTypes.FLESH);
        this.func_180632_j(this.field_176227_L.func_177621_b());
    }

    public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (worldIn.field_72995_K) {
            return;
        }
        if (!worldIn.func_175697_a(pos, 3)) {
            return;
        }
        if (worldIn.func_175659_aa() == EnumDifficulty.PEACEFUL) {
            return;
        }
        if (!SRPConfigMobs.lodoEnabled) {
            return;
        }
        AxisAlignedBB axisalignedbb = new AxisAlignedBB((double)pos.func_177958_n(), (double)pos.func_177956_o(), (double)pos.func_177952_p(), (double)(pos.func_177958_n() + 1), (double)(pos.func_177956_o() + 1), (double)(pos.func_177952_p() + 1)).func_72314_b(1.0, 1.0, 1.0);
        List moblist = worldIn.func_72872_a(EntityLodo.class, axisalignedbb);
        if (!moblist.isEmpty()) {
            return;
        }
        axisalignedbb = new AxisAlignedBB((double)pos.func_177958_n(), (double)pos.func_177956_o(), (double)pos.func_177952_p(), (double)(pos.func_177958_n() + 1), (double)(pos.func_177956_o() + 1), (double)(pos.func_177952_p() + 1)).func_72314_b(16.0, 16.0, 16.0);
        moblist = worldIn.func_72872_a(EntityParasiteBase.class, axisalignedbb);
        if (moblist.size() > 10) {
            return;
        }
        EntityLodo l222 = new EntityLodo(worldIn);
        l222.func_70107_b((double)pos.func_177958_n() + 0.5, pos.func_177956_o(), (double)pos.func_177952_p() + 0.5);
        l222.setFloorTimer();
        worldIn.func_72838_d((Entity)l222);
        worldIn.func_72960_a((Entity)l222, (byte)50);
    }

    public boolean func_149659_a(Explosion explosionIn) {
        return false;
    }

    public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) {
        return TALL_GRASS_AABB;
    }

    protected boolean func_149700_E() {
        return false;
    }

    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        return false;
    }

    public boolean func_149662_c(IBlockState state) {
        return false;
    }

    public boolean func_149686_d(IBlockState state) {
        return false;
    }

    public void func_180657_a(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te, ItemStack stack) {
    }

    @Nullable
    public AxisAlignedBB func_180646_a(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return field_185506_k;
    }

    @SideOnly(value=Side.CLIENT)
    public BlockRenderLayer func_180664_k() {
        return BlockRenderLayer.CUTOUT;
    }

    public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }

    protected boolean canBlockStay(World worldIn, BlockPos pos) {
        BlockPos down = pos.func_177977_b();
        IBlockState below = worldIn.func_180495_p(down);
        return below.isSideSolid((IBlockAccess)worldIn, down, EnumFacing.UP);
    }

    public boolean func_176196_c(World worldIn, BlockPos pos) {
        return super.func_176196_c(worldIn, pos) && this.canBlockStay(worldIn, pos);
    }

    public void func_176213_c(World worldIn, BlockPos pos, IBlockState state) {
        super.func_176213_c(worldIn, pos, state);
        if (!worldIn.field_72995_K && !this.canBlockStay(worldIn, pos)) {
            worldIn.func_175655_b(pos, false);
        }
    }

    public void func_189540_a(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        super.func_189540_a(state, worldIn, pos, blockIn, fromPos);
        if (!worldIn.field_72995_K && !this.canBlockStay(worldIn, pos)) {
            worldIn.func_175655_b(pos, false);
        }
    }

    public void func_180663_b(World worldIn, BlockPos pos, IBlockState state) {
        Entity ent;
        if (!worldIn.field_72995_K && worldIn.func_175697_a(pos, 1) && worldIn.func_175659_aa() != EnumDifficulty.PEACEFUL && (ent = EntityList.func_188429_b((ResourceLocation)new ResourceLocation("srparasites", "buglin"), (World)worldIn)) != null) {
            ent.func_70107_b((double)pos.func_177958_n() + 0.5, (double)pos.func_177956_o(), (double)pos.func_177952_p() + 0.5);
            worldIn.func_72838_d(ent);
        }
        super.func_180663_b(worldIn, pos, state);
    }
}

