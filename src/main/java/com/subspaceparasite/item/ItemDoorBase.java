/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.advancements.CriteriaTriggers
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockDoor
 *  net.minecraft.block.BlockDoor$EnumDoorHalf
 *  net.minecraft.block.BlockDoor$EnumHingePosition
 *  net.minecraft.block.SoundType
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.util.ITooltipFlag
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemDoor
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.EnumActionResult
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.NonNullList
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.item;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDoorBase
extends ItemDoor {
    private final Block block;

    public ItemDoorBase(Block block) {
        super(block);
        this.block = block;
    }

    public EnumActionResult func_180614_a(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemstack;
        if (facing != EnumFacing.UP) {
            return EnumActionResult.FAIL;
        }
        IBlockState iblockstate = worldIn.func_180495_p(pos);
        Block block = iblockstate.func_177230_c();
        if (!block.func_176200_f((IBlockAccess)worldIn, pos)) {
            pos = pos.func_177972_a(facing);
        }
        if (player.func_175151_a(pos, facing, itemstack = player.func_184586_b(hand)) && this.block.func_176196_c(worldIn, pos)) {
            EnumFacing enumfacing = EnumFacing.func_176733_a((double)player.field_70177_z);
            int i = enumfacing.func_82601_c();
            int j = enumfacing.func_82599_e();
            boolean flag = i < 0 && hitZ < 0.5f || i > 0 && hitZ > 0.5f || j < 0 && hitX > 0.5f || j > 0 && hitX < 0.5f;
            ItemDoorBase.placeDoor(worldIn, pos, enumfacing, this.block, flag);
            SoundType soundtype = worldIn.func_180495_p(pos).func_177230_c().getSoundType(worldIn.func_180495_p(pos), worldIn, pos, (Entity)player);
            worldIn.func_184133_a(player, pos, soundtype.func_185841_e(), SoundCategory.BLOCKS, (soundtype.func_185843_a() + 1.0f) / 2.0f, soundtype.func_185847_b() * 0.8f);
            itemstack.func_190918_g(1);
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.FAIL;
    }

    public static void placeDoor(World worldIn, BlockPos pos, EnumFacing facing, Block door, boolean isRightHinge) {
        boolean flag1;
        BlockPos blockpos = pos.func_177972_a(facing.func_176746_e());
        BlockPos blockpos1 = pos.func_177972_a(facing.func_176735_f());
        int i = (worldIn.func_180495_p(blockpos1).func_185915_l() ? 1 : 0) + (worldIn.func_180495_p(blockpos1.func_177984_a()).func_185915_l() ? 1 : 0);
        int j = (worldIn.func_180495_p(blockpos).func_185915_l() ? 1 : 0) + (worldIn.func_180495_p(blockpos.func_177984_a()).func_185915_l() ? 1 : 0);
        boolean flag = worldIn.func_180495_p(blockpos1).func_177230_c() == door || worldIn.func_180495_p(blockpos1.func_177984_a()).func_177230_c() == door;
        boolean bl = flag1 = worldIn.func_180495_p(blockpos).func_177230_c() == door || worldIn.func_180495_p(blockpos.func_177984_a()).func_177230_c() == door;
        if ((!flag || flag1) && j <= i) {
            if (flag1 && !flag || j < i) {
                isRightHinge = false;
            }
        } else {
            isRightHinge = true;
        }
        BlockPos blockpos2 = pos.func_177984_a();
        boolean flag2 = worldIn.func_175640_z(pos) || worldIn.func_175640_z(blockpos2);
        IBlockState iblockstate = door.func_176223_P().func_177226_a((IProperty)BlockDoor.field_176520_a, (Comparable)facing).func_177226_a((IProperty)BlockDoor.field_176521_M, (Comparable)(isRightHinge ? BlockDoor.EnumHingePosition.RIGHT : BlockDoor.EnumHingePosition.LEFT)).func_177226_a((IProperty)BlockDoor.field_176522_N, (Comparable)Boolean.valueOf(flag2)).func_177226_a((IProperty)BlockDoor.field_176519_b, (Comparable)Boolean.valueOf(flag2));
        worldIn.func_180501_a(pos, iblockstate.func_177226_a((IProperty)BlockDoor.field_176523_O, (Comparable)BlockDoor.EnumDoorHalf.LOWER), 2);
        worldIn.func_180501_a(blockpos2, iblockstate.func_177226_a((IProperty)BlockDoor.field_176523_O, (Comparable)BlockDoor.EnumDoorHalf.UPPER), 2);
        worldIn.func_175685_c(pos, door, false);
        worldIn.func_175685_c(blockpos2, door, false);
    }

    public static boolean setTileEntityNBT(World worldIn, @Nullable EntityPlayer player, BlockPos pos, ItemStack stackIn) {
        TileEntity tileentity;
        MinecraftServer minecraftserver = worldIn.func_73046_m();
        if (minecraftserver == null) {
            return false;
        }
        NBTTagCompound nbttagcompound = stackIn.func_179543_a("BlockEntityTag");
        if (nbttagcompound != null && (tileentity = worldIn.func_175625_s(pos)) != null) {
            if (!(worldIn.field_72995_K || !tileentity.func_183000_F() || player != null && player.func_189808_dh())) {
                return false;
            }
            NBTTagCompound nbttagcompound1 = tileentity.func_189515_b(new NBTTagCompound());
            NBTTagCompound nbttagcompound2 = nbttagcompound1.func_74737_b();
            nbttagcompound1.func_179237_a(nbttagcompound);
            nbttagcompound1.func_74768_a("x", pos.func_177958_n());
            nbttagcompound1.func_74768_a("y", pos.func_177956_o());
            nbttagcompound1.func_74768_a("z", pos.func_177952_p());
            if (!nbttagcompound1.equals((Object)nbttagcompound2)) {
                tileentity.func_145839_a(nbttagcompound1);
                tileentity.func_70296_d();
                return true;
            }
        }
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side, EntityPlayer player, ItemStack stack) {
        Block block = worldIn.func_180495_p(pos).func_177230_c();
        if (block == Blocks.field_150431_aC && block.func_176200_f((IBlockAccess)worldIn, pos)) {
            side = EnumFacing.UP;
        } else if (!block.func_176200_f((IBlockAccess)worldIn, pos)) {
            pos = pos.func_177972_a(side);
        }
        return worldIn.func_190527_a(this.block, pos, false, side, (Entity)null);
    }

    public String func_77667_c(ItemStack stack) {
        return this.block.func_149739_a();
    }

    public String func_77658_a() {
        return this.block.func_149739_a();
    }

    public CreativeTabs func_77640_w() {
        return this.block.func_149708_J();
    }

    public void func_150895_a(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.func_194125_a(tab)) {
            this.block.func_149666_a(tab, items);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.func_77624_a(stack, worldIn, tooltip, flagIn);
        this.block.func_190948_a(stack, worldIn, tooltip, flagIn);
    }

    public Block getBlock() {
        return this.getBlockRaw() == null ? null : (Block)this.getBlockRaw().delegate.get();
    }

    private Block getBlockRaw() {
        return this.block;
    }

    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState) {
        if (!world.func_180501_a(pos, newState, 11)) {
            return false;
        }
        IBlockState state = world.func_180495_p(pos);
        if (state.func_177230_c() == this.block) {
            ItemDoorBase.setTileEntityNBT(world, player, pos, stack);
            this.block.func_180633_a(world, pos, state, (EntityLivingBase)player, stack);
            if (player instanceof EntityPlayerMP) {
                CriteriaTriggers.field_193137_x.func_193173_a((EntityPlayerMP)player, pos, stack);
            }
        }
        return true;
    }
}

