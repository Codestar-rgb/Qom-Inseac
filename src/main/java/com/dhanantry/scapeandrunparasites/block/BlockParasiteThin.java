/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.block.SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.properties.PropertyBool
 *  net.minecraft.block.state.BlockFaceShape
 *  net.minecraft.block.state.BlockStateContainer
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemLead
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.Mirror
 *  net.minecraft.util.Rotation
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemLead;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockParasiteThin
extends Block {
    public static final PropertyBool NORTH = PropertyBool.func_177716_a((String)"north");
    public static final PropertyBool EAST = PropertyBool.func_177716_a((String)"east");
    public static final PropertyBool SOUTH = PropertyBool.func_177716_a((String)"south");
    public static final PropertyBool WEST = PropertyBool.func_177716_a((String)"west");
    public static final PropertyBool UP = PropertyBool.func_177716_a((String)"up");
    public static final PropertyBool DOWN = PropertyBool.func_177716_a((String)"down");
    protected static final AxisAlignedBB[] BOUNDING_BOXES = new AxisAlignedBB[]{new AxisAlignedBB(0.375, 0.0, 0.375, 0.625, 1.0, 0.625), new AxisAlignedBB(0.375, 0.0, 0.375, 0.625, 1.0, 1.0), new AxisAlignedBB(0.0, 0.0, 0.375, 0.625, 1.0, 0.625), new AxisAlignedBB(0.0, 0.0, 0.375, 0.625, 1.0, 1.0), new AxisAlignedBB(0.375, 0.0, 0.0, 0.625, 1.0, 0.625), new AxisAlignedBB(0.375, 0.0, 0.0, 0.625, 1.0, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 0.625, 1.0, 0.625), new AxisAlignedBB(0.0, 0.0, 0.0, 0.625, 1.0, 1.0), new AxisAlignedBB(0.375, 0.0, 0.375, 1.0, 1.0, 0.625), new AxisAlignedBB(0.375, 0.0, 0.375, 1.0, 1.0, 1.0), new AxisAlignedBB(0.0, 0.0, 0.375, 1.0, 1.0, 0.625), new AxisAlignedBB(0.0, 0.0, 0.375, 1.0, 1.0, 1.0), new AxisAlignedBB(0.375, 0.0, 0.0, 1.0, 1.0, 0.625), new AxisAlignedBB(0.375, 0.0, 0.0, 1.0, 1.0, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 0.625), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0)};
    public static final AxisAlignedBB PILLAR_AABB = new AxisAlignedBB(0.375, 0.5, 0.375, 0.625, 1.5, 0.625);
    public static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.375, 0.0, 0.625, 0.625, 1.5, 1.0);
    public static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.0, 0.0, 0.375, 0.375, 1.5, 0.625);
    public static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.375, 0.0, 0.0, 0.625, 1.5, 0.375);
    public static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.625, 0.0, 0.375, 1.0, 1.5, 0.625);

    public BlockParasiteThin(String name, float hardness) {
        super(Material.field_151575_d);
        this.setHarvestLevel("axe", 0);
        this.setRegistryName(name);
        this.func_149663_c("srparasites." + name);
        this.func_149647_a(SRPMain.SRP_CREATIVETAB);
        this.func_149711_c(hardness);
        this.func_149672_a(SoundType.field_185848_a);
        SRPBlocks.SRP_BLOCKS.add(this);
        SRPItems.SRP_ITEMS.add((Item)new ItemBlock((Block)this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
    }

    public void func_185477_a(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
        if (!isActualState) {
            state = state.func_185899_b((IBlockAccess)worldIn, pos);
        }
        BlockParasiteThin.func_185492_a((BlockPos)pos, (AxisAlignedBB)entityBox, collidingBoxes, (AxisAlignedBB)PILLAR_AABB);
        if (((Boolean)state.func_177229_b((IProperty)NORTH)).booleanValue()) {
            BlockParasiteThin.func_185492_a((BlockPos)pos, (AxisAlignedBB)entityBox, collidingBoxes, (AxisAlignedBB)NORTH_AABB);
        }
        if (((Boolean)state.func_177229_b((IProperty)EAST)).booleanValue()) {
            BlockParasiteThin.func_185492_a((BlockPos)pos, (AxisAlignedBB)entityBox, collidingBoxes, (AxisAlignedBB)EAST_AABB);
        }
        if (((Boolean)state.func_177229_b((IProperty)SOUTH)).booleanValue()) {
            BlockParasiteThin.func_185492_a((BlockPos)pos, (AxisAlignedBB)entityBox, collidingBoxes, (AxisAlignedBB)SOUTH_AABB);
        }
        if (((Boolean)state.func_177229_b((IProperty)WEST)).booleanValue()) {
            BlockParasiteThin.func_185492_a((BlockPos)pos, (AxisAlignedBB)entityBox, collidingBoxes, (AxisAlignedBB)WEST_AABB);
        }
    }

    public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) {
        state = this.func_176221_a(state, source, pos);
        return BOUNDING_BOXES[BlockParasiteThin.getBoundingBoxIdx(state)];
    }

    private static int getBoundingBoxIdx(IBlockState state) {
        int i = 0;
        if (((Boolean)state.func_177229_b((IProperty)NORTH)).booleanValue()) {
            i |= 1 << EnumFacing.NORTH.func_176736_b();
        }
        if (((Boolean)state.func_177229_b((IProperty)EAST)).booleanValue()) {
            i |= 1 << EnumFacing.EAST.func_176736_b();
        }
        if (((Boolean)state.func_177229_b((IProperty)SOUTH)).booleanValue()) {
            i |= 1 << EnumFacing.SOUTH.func_176736_b();
        }
        if (((Boolean)state.func_177229_b((IProperty)WEST)).booleanValue()) {
            i |= 1 << EnumFacing.WEST.func_176736_b();
        }
        return i;
    }

    public boolean func_149662_c(IBlockState state) {
        return false;
    }

    public boolean func_149686_d(IBlockState state) {
        return false;
    }

    public boolean func_176205_b(IBlockAccess worldIn, BlockPos pos) {
        return false;
    }

    public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos, EnumFacing facing) {
        IBlockState iblockstate = worldIn.func_180495_p(pos);
        BlockFaceShape blockfaceshape = iblockstate.func_193401_d(worldIn, pos, facing);
        Block block = iblockstate.func_177230_c();
        boolean flag = blockfaceshape == BlockFaceShape.MIDDLE_POLE && iblockstate.func_185904_a() == this.field_149764_J;
        boolean flag2 = block == SRPBlocks.ParasiteThin;
        boolean flag3 = blockfaceshape == BlockFaceShape.SOLID && (facing == EnumFacing.UP || iblockstate.func_185917_h());
        return !BlockParasiteThin.isExcepBlockForAttachWithPiston(block) && flag3 || flag || flag2;
    }

    protected static boolean isExcepBlockForAttachWithPiston(Block p_194142_0_) {
        return Block.func_193382_c((Block)p_194142_0_) || p_194142_0_ == Blocks.field_180401_cv || p_194142_0_ == Blocks.field_150440_ba || p_194142_0_ == Blocks.field_150423_aK || p_194142_0_ == Blocks.field_150428_aP;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_176225_a(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return true;
    }

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.field_72995_K) {
            return ItemLead.func_180618_a((EntityPlayer)playerIn, (World)worldIn, (BlockPos)pos);
        }
        ItemStack itemstack = playerIn.func_184586_b(hand);
        return itemstack.func_77973_b() == Items.field_151058_ca || itemstack.func_190926_b();
    }

    public int func_176201_c(IBlockState state) {
        return 0;
    }

    public IBlockState func_176221_a(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return state.func_177226_a((IProperty)NORTH, (Comparable)Boolean.valueOf(this.canFenceConnectTo(worldIn, pos, EnumFacing.NORTH))).func_177226_a((IProperty)EAST, (Comparable)Boolean.valueOf(this.canFenceConnectTo(worldIn, pos, EnumFacing.EAST))).func_177226_a((IProperty)SOUTH, (Comparable)Boolean.valueOf(this.canFenceConnectTo(worldIn, pos, EnumFacing.SOUTH))).func_177226_a((IProperty)WEST, (Comparable)Boolean.valueOf(this.canFenceConnectTo(worldIn, pos, EnumFacing.WEST))).func_177226_a((IProperty)UP, (Comparable)Boolean.valueOf(this.canFenceConnectTo(worldIn, pos, EnumFacing.UP))).func_177226_a((IProperty)DOWN, (Comparable)Boolean.valueOf(this.canFenceConnectTo(worldIn, pos, EnumFacing.DOWN)));
    }

    public IBlockState func_185499_a(IBlockState state, Rotation rot) {
        switch (rot) {
            case CLOCKWISE_180: {
                return state.func_177226_a((IProperty)NORTH, state.func_177229_b((IProperty)SOUTH)).func_177226_a((IProperty)EAST, state.func_177229_b((IProperty)WEST)).func_177226_a((IProperty)SOUTH, state.func_177229_b((IProperty)NORTH)).func_177226_a((IProperty)WEST, state.func_177229_b((IProperty)EAST)).func_177226_a((IProperty)UP, state.func_177229_b((IProperty)UP)).func_177226_a((IProperty)DOWN, state.func_177229_b((IProperty)DOWN));
            }
            case COUNTERCLOCKWISE_90: {
                return state.func_177226_a((IProperty)NORTH, state.func_177229_b((IProperty)EAST)).func_177226_a((IProperty)EAST, state.func_177229_b((IProperty)SOUTH)).func_177226_a((IProperty)SOUTH, state.func_177229_b((IProperty)WEST)).func_177226_a((IProperty)WEST, state.func_177229_b((IProperty)NORTH)).func_177226_a((IProperty)UP, state.func_177229_b((IProperty)UP)).func_177226_a((IProperty)DOWN, state.func_177229_b((IProperty)DOWN));
            }
            case CLOCKWISE_90: {
                return state.func_177226_a((IProperty)NORTH, state.func_177229_b((IProperty)WEST)).func_177226_a((IProperty)EAST, state.func_177229_b((IProperty)NORTH)).func_177226_a((IProperty)SOUTH, state.func_177229_b((IProperty)EAST)).func_177226_a((IProperty)WEST, state.func_177229_b((IProperty)SOUTH)).func_177226_a((IProperty)UP, state.func_177229_b((IProperty)UP)).func_177226_a((IProperty)DOWN, state.func_177229_b((IProperty)DOWN));
            }
        }
        return state;
    }

    public IBlockState func_185471_a(IBlockState state, Mirror mirrorIn) {
        switch (mirrorIn) {
            case LEFT_RIGHT: {
                return state.func_177226_a((IProperty)NORTH, state.func_177229_b((IProperty)SOUTH)).func_177226_a((IProperty)SOUTH, state.func_177229_b((IProperty)NORTH));
            }
            case FRONT_BACK: {
                return state.func_177226_a((IProperty)EAST, state.func_177229_b((IProperty)WEST)).func_177226_a((IProperty)WEST, state.func_177229_b((IProperty)EAST));
            }
        }
        return super.func_185471_a(state, mirrorIn);
    }

    protected BlockStateContainer func_180661_e() {
        return new BlockStateContainer((Block)this, new IProperty[]{NORTH, EAST, WEST, SOUTH, UP, DOWN});
    }

    public boolean canBeConnectedTo(IBlockAccess world, BlockPos pos, EnumFacing facing) {
        return this.canConnectTo(world, pos.func_177972_a(facing), facing.func_176734_d());
    }

    private boolean canFenceConnectTo(IBlockAccess world, BlockPos pos, EnumFacing facing) {
        BlockPos other = pos.func_177972_a(facing);
        Block block = world.func_180495_p(other).func_177230_c();
        return this.canConnectTo(world, other, facing.func_176734_d());
    }

    public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return face != EnumFacing.UP && face != EnumFacing.DOWN ? BlockFaceShape.MIDDLE_POLE : BlockFaceShape.CENTER;
    }
}

