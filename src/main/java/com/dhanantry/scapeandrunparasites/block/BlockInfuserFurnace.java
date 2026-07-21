package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.tileentity.TileEntityInfuserFurnace;
import java.util.Objects;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockInfuserFurnace extends BlockContainer {
   public static final PropertyDirection FACING = BlockHorizontal.field_185512_D;

   public BlockInfuserFurnace(String name) {
      super(Material.field_151576_e);
      this.setRegistryName(name);
      this.func_149663_c("srparasites." + name);
      this.func_149711_c(3.5F);
      this.func_149752_b(17.5F);
      this.func_149672_a(SoundType.field_185851_d);
      this.func_149647_a(SRPMain.SRP_CREATIVETAB);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(FACING, EnumFacing.NORTH));
      SRPBlocks.SRP_BLOCKS.add(this);
      Item itemBlock = new ItemBlock(this);
      SRPItems.SRP_ITEMS.add(itemBlock.setRegistryName(Objects.requireNonNull(this.getRegistryName())));
   }

   public TileEntity func_149915_a(World worldIn, int meta) {
      return new TileEntityInfuserFurnace();
   }

   public boolean func_180639_a(
      World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ
   ) {
      if (worldIn.field_72995_K) {
         return true;
      } else {
         playerIn.openGui(SRPMain.instance, 7, worldIn, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
         return true;
      }
   }

   public IBlockState getStateForPlacement(
      World worldIn, BlockPos pos, EnumFacing clickedSide, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand
   ) {
      return this.func_176223_P().func_177226_a(FACING, placer.func_174811_aO().func_176734_d());
   }

   public IBlockState func_176203_a(int meta) {
      EnumFacing f = EnumFacing.func_82600_a(meta);
      if (f.func_176740_k() == Axis.Y) {
         f = EnumFacing.NORTH;
      }

      return this.func_176223_P().func_177226_a(FACING, f);
   }

   public int func_176201_c(IBlockState state) {
      return ((EnumFacing)state.func_177229_b(FACING)).func_176745_a();
   }

   public IBlockState func_176221_a(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      return state;
   }

   protected BlockStateContainer func_180661_e() {
      return new BlockStateContainer(this, new IProperty[]{FACING});
   }

   public IBlockState func_185499_a(IBlockState state, Rotation rot) {
      return state.func_177226_a(FACING, rot.func_185831_a((EnumFacing)state.func_177229_b(FACING)));
   }

   public IBlockState func_185471_a(IBlockState state, Mirror mirrorIn) {
      return state.func_185907_a(mirrorIn.func_185800_a((EnumFacing)state.func_177229_b(FACING)));
   }

   public EnumBlockRenderType func_149645_b(IBlockState state) {
      return EnumBlockRenderType.MODEL;
   }

   @SideOnly(Side.CLIENT)
   public BlockRenderLayer func_180664_k() {
      return BlockRenderLayer.SOLID;
   }

   public boolean func_149662_c(IBlockState state) {
      return true;
   }

   public boolean func_149686_d(IBlockState state) {
      return true;
   }

   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state) {
      TileEntity te = worldIn.func_175625_s(pos);
      if (te instanceof TileEntityInfuserFurnace) {
         InventoryHelper.func_180175_a(worldIn, pos, (TileEntityInfuserFurnace)te);
         worldIn.func_175666_e(pos, this);
      }

      super.func_180663_b(worldIn, pos, state);
   }
}
