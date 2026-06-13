/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.BlockHorizontal
 *  net.minecraft.block.SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.properties.PropertyBool
 *  net.minecraft.block.state.BlockStateContainer
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.InventoryHelper
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.EnumBlockRenderType
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumFacing$Axis
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.tileentity.TileEntityInfestedFurnace;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockInfestedFurnace
extends BlockContainer {
    public static final PropertyBool LIT = PropertyBool.func_177716_a((String)"lit");
    public static boolean DEBUG = true;
    public static boolean keepInventory = false;

    public BlockInfestedFurnace() {
        super(Material.field_151576_e);
        this.setRegistryName(new ResourceLocation("srparasites", "infested_furnace"));
        this.func_149663_c("srparasites.infested_furnace");
        this.func_149711_c(3.5f);
        this.func_149672_a(SoundType.field_185851_d);
        this.func_149647_a(SRPMain.SRP_CREATIVETAB);
        this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a((IProperty)BlockHorizontal.field_185512_D, (Comparable)EnumFacing.NORTH).func_177226_a((IProperty)LIT, (Comparable)Boolean.FALSE));
    }

    public static void setLitState(boolean active, World world, BlockPos pos) {
        IBlockState state = world.func_180495_p(pos);
        if (!(state.func_177230_c() instanceof BlockInfestedFurnace)) {
            return;
        }
        boolean currentlyLit = (Boolean)state.func_177229_b((IProperty)LIT);
        if (currentlyLit == active) {
            return;
        }
        TileEntity te = world.func_175625_s(pos);
        keepInventory = true;
        if (DEBUG) {
            System.out.println("[InfestedFurnace] setLitState pos=" + pos + " " + currentlyLit + " -> " + active + " te=" + (te == null ? "null" : te.getClass().getName()));
        }
        world.func_180501_a(pos, state.func_177226_a((IProperty)LIT, (Comparable)Boolean.valueOf(active)), 3);
        if (te != null) {
            te.func_145829_t();
            world.func_175690_a(pos, te);
        }
        keepInventory = false;
    }

    public TileEntity func_149915_a(World worldIn, int meta) {
        return new TileEntityInfestedFurnace();
    }

    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
        return (Boolean)state.func_177229_b((IProperty)LIT) != false ? 14 : 0;
    }

    public EnumBlockRenderType func_149645_b(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (DEBUG) {
            System.out.println("[InfestedFurnace] onBlockActivated side=" + (worldIn.field_72995_K ? "CLIENT" : "SERVER") + " pos=" + pos + " hand=" + hand + " sneaking=" + playerIn.func_70093_af() + " held=" + playerIn.func_184586_b(hand));
        }
        if (worldIn.field_72995_K) {
            return true;
        }
        TileEntity te = worldIn.func_175625_s(pos);
        if (DEBUG) {
            System.out.println("[InfestedFurnace] TE=" + (te == null ? "null" : te.getClass().getName()));
        }
        if (!(te instanceof TileEntityInfestedFurnace)) {
            if (DEBUG) {
                System.out.println("[InfestedFurnace] Not our TE, aborting open.");
            }
            return true;
        }
        if (DEBUG) {
            System.out.println("[InfestedFurnace] Opening GUI via displayGUIChest");
        }
        playerIn.func_71007_a((IInventory)te);
        return true;
    }

    public void func_180663_b(World worldIn, BlockPos pos, IBlockState state) {
        TileEntity te;
        if (!keepInventory && (te = worldIn.func_175625_s(pos)) instanceof IInventory) {
            InventoryHelper.func_180175_a((World)worldIn, (BlockPos)pos, (IInventory)((IInventory)te));
            worldIn.func_175666_e(pos, (Block)this);
        }
        worldIn.func_175713_t(pos);
    }

    public Item func_180660_a(IBlockState state, Random rand, int fortune) {
        return Item.func_150898_a((Block)this);
    }

    public ItemStack func_185473_a(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack((Block)this);
    }

    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack((Block)this);
    }

    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return this.func_176223_P().func_177226_a((IProperty)BlockHorizontal.field_185512_D, (Comparable)placer.func_174811_aO().func_176734_d()).func_177226_a((IProperty)LIT, (Comparable)Boolean.FALSE);
    }

    public void func_180633_a(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        TileEntity te;
        worldIn.func_180501_a(pos, state.func_177226_a((IProperty)BlockHorizontal.field_185512_D, (Comparable)placer.func_174811_aO().func_176734_d()), 2);
        if (stack.func_82837_s() && (te = worldIn.func_175625_s(pos)) instanceof TileEntityInfestedFurnace) {
            ((TileEntityInfestedFurnace)te).setCustomInventoryName(stack.func_82833_r());
        }
    }

    protected BlockStateContainer func_180661_e() {
        return new BlockStateContainer((Block)this, new IProperty[]{BlockHorizontal.field_185512_D, LIT});
    }

    public IBlockState func_176203_a(int meta) {
        EnumFacing enumfacing = EnumFacing.func_82600_a((int)(meta & 7));
        if (enumfacing.func_176740_k() == EnumFacing.Axis.Y) {
            enumfacing = EnumFacing.NORTH;
        }
        boolean lit = (meta & 8) != 0;
        return this.func_176223_P().func_177226_a((IProperty)BlockHorizontal.field_185512_D, (Comparable)enumfacing).func_177226_a((IProperty)LIT, (Comparable)Boolean.valueOf(lit));
    }

    public int func_176201_c(IBlockState state) {
        int meta = ((EnumFacing)state.func_177229_b((IProperty)BlockHorizontal.field_185512_D)).func_176745_a();
        if (((Boolean)state.func_177229_b((IProperty)LIT)).booleanValue()) {
            meta |= 8;
        }
        return meta;
    }
}

