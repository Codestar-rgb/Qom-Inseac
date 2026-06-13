/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.properties.PropertyDirection
 *  net.minecraft.block.state.BlockStateContainer
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.InventoryHelper
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumFacing$Plane
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.Mirror
 *  net.minecraft.util.Rotation
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.block.BlockBase;
import com.dhanantry.scapeandrunparasites.block.TileEntityDermoidCyst;
import com.dhanantry.scapeandrunparasites.init.SRPSoundTypes;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockDermoidCyst
extends BlockBase {
    public static final PropertyDirection FACING = PropertyDirection.func_177712_a((String)"facing", (Predicate)EnumFacing.Plane.HORIZONTAL);

    public BlockDermoidCyst() {
        super(Material.field_151575_d, "dermoid_cyst", 2.5f, true, true);
        this.func_149672_a(SRPSoundTypes.FLESH);
        this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a((IProperty)FACING, (Comparable)EnumFacing.NORTH));
    }

    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityDermoidCyst();
    }

    public IBlockState func_180642_a(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.func_176223_P().func_177226_a((IProperty)FACING, (Comparable)placer.func_174811_aO());
    }

    public void func_180633_a(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        TileEntity te = world.func_175625_s(pos);
        if (te instanceof TileEntityDermoidCyst && stack.func_82837_s()) {
            ((TileEntityDermoidCyst)te).func_190575_a(stack.func_82833_r());
        }
    }

    public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (world.field_72995_K) {
            return true;
        }
        TileEntity te = world.func_175625_s(pos);
        if (te instanceof TileEntityDermoidCyst) {
            player.func_71007_a((IInventory)((TileEntityDermoidCyst)te));
        }
        return true;
    }

    public void func_180663_b(World world, BlockPos pos, IBlockState state) {
        TileEntity te;
        if (!world.field_72995_K) {
            world.func_184133_a(null, pos, SRPSounds.FLESH_GROWL, SoundCategory.BLOCKS, 1.0f, 1.0f);
        }
        if ((te = world.func_175625_s(pos)) instanceof TileEntityDermoidCyst) {
            InventoryHelper.func_180175_a((World)world, (BlockPos)pos, (IInventory)((TileEntityDermoidCyst)te));
            world.func_175666_e(pos, (Block)this);
        }
        super.func_180663_b(world, pos, state);
    }

    public boolean func_149740_M(IBlockState state) {
        return true;
    }

    public int func_180641_l(IBlockState state, World world, BlockPos pos) {
        TileEntity te = world.func_175625_s(pos);
        if (te instanceof TileEntityDermoidCyst) {
            return Container.func_94526_b((IInventory)((TileEntityDermoidCyst)te));
        }
        return 0;
    }

    public IBlockState func_185499_a(IBlockState state, Rotation rot) {
        return state.func_177226_a((IProperty)FACING, (Comparable)rot.func_185831_a((EnumFacing)state.func_177229_b((IProperty)FACING)));
    }

    public IBlockState func_185471_a(IBlockState state, Mirror mirror) {
        return state.func_185907_a(mirror.func_185800_a((EnumFacing)state.func_177229_b((IProperty)FACING)));
    }

    public int func_176201_c(IBlockState state) {
        return ((EnumFacing)state.func_177229_b((IProperty)FACING)).func_176736_b();
    }

    public IBlockState func_176203_a(int meta) {
        return this.func_176223_P().func_177226_a((IProperty)FACING, (Comparable)EnumFacing.func_176731_b((int)(meta & 3)));
    }

    protected BlockStateContainer func_180661_e() {
        return new BlockStateContainer((Block)this, new IProperty[]{FACING});
    }

    public boolean func_189539_a(IBlockState state, World world, BlockPos pos, int id, int param) {
        super.func_189539_a(state, world, pos, id, param);
        TileEntity te = world.func_175625_s(pos);
        return te != null && te.func_145842_c(id, param);
    }
}

