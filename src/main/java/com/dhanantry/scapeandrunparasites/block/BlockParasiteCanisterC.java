package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.entity.tile.TileEntityCanister;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import java.util.Objects;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;

public class BlockParasiteCanisterC extends BlockContainer {
   protected static final AxisAlignedBB TALL_GRASS_AABB = new AxisAlignedBB(0.1, 0.0, 0.1, 0.9, 0.55, 0.9);

   public BlockParasiteCanisterC(Material material, String name, float hardness, boolean creative, boolean tickRandom) {
      super(material);
      this.setRegistryName(name);
      this.func_149663_c("srparasites." + name);
      this.func_149711_c(hardness);
      this.func_149675_a(tickRandom);
      if (creative) {
         this.func_149647_a(SRPMain.SRP_CREATIVETAB);
      }

      SRPBlocks.SRP_BLOCKS.add(this);
      SRPItems.SRP_ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
   }

   public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) {
      return TALL_GRASS_AABB;
   }

   public boolean func_149662_c(IBlockState state) {
      return false;
   }

   public boolean func_149686_d(IBlockState state) {
      return false;
   }

   public void func_180633_a(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
      worldIn.func_180501_a(pos, state, 3);
      if (stack.func_82837_s()) {
         TileEntity tileentity = worldIn.func_175625_s(pos);
         if (tileentity instanceof TileEntityCanister) {
            ((TileEntityCanister)tileentity).func_190575_a(stack.func_82833_r());
         }
      }
   }

   public boolean func_176196_c(World worldIn, BlockPos pos) {
      return true;
   }

   public void func_189540_a(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
      super.func_189540_a(state, worldIn, pos, blockIn, fromPos);
      TileEntity tileentity = worldIn.func_175625_s(pos);
      if (tileentity instanceof TileEntityCanister) {
         tileentity.func_145836_u();
      }
   }

   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state) {
      TileEntity tileentity = worldIn.func_175625_s(pos);
      if (tileentity instanceof IInventory) {
         InventoryHelper.func_180175_a(worldIn, pos, (IInventory)tileentity);
         worldIn.func_175666_e(pos, this);
      }

      super.func_180663_b(worldIn, pos, state);
   }

   public boolean func_180639_a(
      World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ
   ) {
      if (!worldIn.field_72995_K) {
         ILockableContainer ilockablecontainer = this.getLockableContainer(worldIn, pos);
         if (ilockablecontainer != null) {
            playerIn.func_71007_a(ilockablecontainer);
            playerIn.func_71029_a(StatList.field_188063_ac);
         }
      }

      return true;
   }

   @Nullable
   public ILockableContainer getLockableContainer(World worldIn, BlockPos pos) {
      return this.getContainer(worldIn, pos, false);
   }

   @Nullable
   public ILockableContainer getContainer(World worldIn, BlockPos pos, boolean allowBlocking) {
      TileEntity tileentity = worldIn.func_175625_s(pos);
      return !(tileentity instanceof TileEntityCanister) ? null : (ILockableContainer)tileentity;
   }

   public TileEntity func_149915_a(World worldIn, int meta) {
      return new TileEntityCanister();
   }

   public boolean func_149740_M(IBlockState state) {
      return true;
   }

   public int func_180641_l(IBlockState blockState, World worldIn, BlockPos pos) {
      return Container.func_94526_b(this.getLockableContainer(worldIn, pos));
   }

   public EnumBlockRenderType func_149645_b(IBlockState state) {
      return EnumBlockRenderType.MODEL;
   }
}
