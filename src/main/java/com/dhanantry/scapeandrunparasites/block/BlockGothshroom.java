/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.properties.PropertyBool
 *  net.minecraft.block.properties.PropertyDirection
 *  net.minecraft.block.state.BlockFaceShape
 *  net.minecraft.block.state.BlockStateContainer
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.BlockRenderLayer
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.NonNullList
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.SoundCategory
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
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGothshroom
extends Block {
    public static final PropertyDirection FACING = PropertyDirection.func_177713_a((String)"facing", Arrays.asList(EnumFacing.values()));
    public static final PropertyBool GROUP = PropertyBool.func_177716_a((String)"group");
    private static final AxisAlignedBB AABB_FLOOR = new AxisAlignedBB(0.25, 0.0, 0.25, 0.75, 0.65, 0.75);
    private static final AxisAlignedBB AABB_CEILING = new AxisAlignedBB(0.25, 0.35, 0.25, 0.75, 1.0, 0.75);
    private static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(0.25, 0.25, 0.0, 0.75, 0.75, 0.6);
    private static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(0.25, 0.25, 0.4, 0.75, 0.75, 1.0);
    private static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(0.0, 0.25, 0.25, 0.6, 0.75, 0.75);
    private static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(0.4, 0.25, 0.25, 1.0, 0.75, 0.75);

    public BlockGothshroom(String name, boolean creative) {
        super(Material.field_151585_k);
        this.func_149672_a(SoundType.field_185850_c);
        this.func_149711_c(0.0f);
        this.func_149752_b(0.0f);
        this.func_149713_g(0);
        this.func_149675_a(false);
        this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a((IProperty)FACING, (Comparable)EnumFacing.UP).func_177226_a((IProperty)GROUP, (Comparable)Boolean.FALSE));
        this.setRegistryName("srparasites", name);
        this.func_149663_c("srparasites." + name);
        if (creative) {
            this.func_149647_a(SRPMain.SRP_CREATIVETAB);
        }
        SRPBlocks.SRP_BLOCKS.add(this);
        ItemBlock itemBlock = new ItemBlock((Block)this);
        SRPItems.SRP_ITEMS.add((Item)itemBlock.setRegistryName(Objects.requireNonNull(this.getRegistryName())));
    }

    public boolean func_149662_c(IBlockState state) {
        return false;
    }

    public boolean func_149686_d(IBlockState state) {
        return false;
    }

    public AxisAlignedBB func_180646_a(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return field_185506_k;
    }

    public boolean func_176205_b(IBlockAccess worldIn, BlockPos pos) {
        return true;
    }

    public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }

    @SideOnly(value=Side.CLIENT)
    public BlockRenderLayer func_180664_k() {
        return BlockRenderLayer.CUTOUT;
    }

    public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        switch ((EnumFacing)state.func_177229_b((IProperty)FACING)) {
            case DOWN: {
                return AABB_CEILING;
            }
            case NORTH: {
                return AABB_NORTH;
            }
            case SOUTH: {
                return AABB_SOUTH;
            }
            case WEST: {
                return AABB_WEST;
            }
            case EAST: {
                return AABB_EAST;
            }
        }
        return AABB_FLOOR;
    }

    private static boolean isSRP(Block b) {
        ResourceLocation id = b.getRegistryName();
        return id != null && "srparasites".equals(id.func_110624_b());
    }

    private static boolean canAttachTo(World world, BlockPos pos, EnumFacing attachSide) {
        BlockPos supportPos = pos.func_177972_a(attachSide.func_176734_d());
        IBlockState support = world.func_180495_p(supportPos);
        return BlockGothshroom.isSRP(support.func_177230_c()) && support.isSideSolid((IBlockAccess)world, supportPos, attachSide);
    }

    public boolean func_176198_a(World worldIn, BlockPos pos, EnumFacing side) {
        return BlockGothshroom.canAttachTo(worldIn, pos, side);
    }

    public boolean func_176196_c(World worldIn, BlockPos pos) {
        for (EnumFacing f : EnumFacing.values()) {
            if (!BlockGothshroom.canAttachTo(worldIn, pos, f)) continue;
            return true;
        }
        return false;
    }

    public IBlockState func_180642_a(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        if (BlockGothshroom.canAttachTo(world, pos, facing)) {
            EnumFacing outward = BlockGothshroom.outwardFromAttach(facing);
            return this.func_176223_P().func_177226_a((IProperty)FACING, (Comparable)outward);
        }
        return this.func_176223_P().func_177226_a((IProperty)FACING, (Comparable)EnumFacing.UP);
    }

    public void func_176213_c(World worldIn, BlockPos pos, IBlockState state) {
        super.func_176213_c(worldIn, pos, state);
        this.checkAndDrop(worldIn, pos, state);
    }

    public void func_189540_a(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        this.checkAndDrop(worldIn, pos, state);
    }

    private void checkAndDrop(World world, BlockPos pos, IBlockState state) {
        EnumFacing attachSide = BlockGothshroom.attachFromOutward((EnumFacing)state.func_177229_b((IProperty)FACING));
        if (!BlockGothshroom.canAttachTo(world, pos, attachSide)) {
            world.func_175655_b(pos, true);
        }
    }

    private static EnumFacing attachFromOutward(EnumFacing outward) {
        return outward.func_176740_k().func_176720_b() ? outward : outward.func_176734_d();
    }

    private static EnumFacing outwardFromAttach(EnumFacing attach) {
        return attach.func_176740_k().func_176720_b() ? attach : attach.func_176734_d();
    }

    public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack held = player.func_184586_b(hand);
        if (held.func_190926_b()) {
            return false;
        }
        if (held.func_77973_b() == Item.func_150898_a((Block)this) && !((Boolean)state.func_177229_b((IProperty)GROUP)).booleanValue()) {
            if (!world.field_72995_K) {
                world.func_180501_a(pos, state.func_177226_a((IProperty)GROUP, (Comparable)Boolean.TRUE), 2);
                if (!player.field_71075_bZ.field_75098_d) {
                    held.func_190918_g(1);
                }
                world.func_184133_a(null, pos, SoundEvents.field_187577_bU, SoundCategory.BLOCKS, 0.6f, 1.0f);
            }
            return true;
        }
        return false;
    }

    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        int count = (Boolean)state.func_177229_b((IProperty)GROUP) != false ? 2 : 1;
        for (int i = 0; i < count; ++i) {
            drops.add((Object)new ItemStack(Item.func_150898_a((Block)this)));
        }
    }

    protected BlockStateContainer func_180661_e() {
        return new BlockStateContainer((Block)this, new IProperty[]{FACING, GROUP});
    }

    public int func_176201_c(IBlockState state) {
        return ((EnumFacing)state.func_177229_b((IProperty)FACING)).func_176745_a() & 7 | ((Boolean)state.func_177229_b((IProperty)GROUP) != false ? 8 : 0);
    }

    public IBlockState func_176203_a(int meta) {
        EnumFacing f = EnumFacing.func_82600_a((int)(meta & 7));
        if (f == null) {
            f = EnumFacing.UP;
        }
        boolean grouped = (meta & 8) != 0;
        return this.func_176223_P().func_177226_a((IProperty)FACING, (Comparable)f).func_177226_a((IProperty)GROUP, (Comparable)Boolean.valueOf(grouped));
    }

    public Item func_180660_a(IBlockState state, Random rand, int fortune) {
        return Item.func_150898_a((Block)this);
    }
}

