package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.init.SRPSoundTypes;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.EnumFacing.Plane;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockDermoidCyst extends BlockBase {
   public static final PropertyDirection FACING = PropertyDirection.func_177712_a("facing", Plane.HORIZONTAL);

   public BlockDermoidCyst() {
      super(Material.field_151575_d, "dermoid_cyst", 2.5F, true, true);
      this.func_149672_a(SRPSoundTypes.FLESH);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(FACING, EnumFacing.NORTH));
   }

   public boolean hasTileEntity(IBlockState state) {
      return true;
   }

   public TileEntity createTileEntity(World world, IBlockState state) {
      return new TileEntityDermoidCyst();
   }

   public IBlockState func_180642_a(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
      return this.func_176223_P().func_177226_a(FACING, placer.func_174811_aO());
   }

   public void func_180633_a(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
      TileEntity te = world.func_175625_s(pos);
      if (te instanceof TileEntityDermoidCyst && stack.func_82837_s()) {
         ((TileEntityDermoidCyst)te).func_190575_a(stack.func_82833_r());
      }
   }

   public boolean func_180639_a(
      World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ
   ) {
      if (world.field_72995_K) {
         return true;
      } else {
         TileEntity te = world.func_175625_s(pos);
         if (te instanceof TileEntityDermoidCyst) {
            player.func_71007_a((TileEntityDermoidCyst)te);
         }

         return true;
      }
   }

   public void func_180663_b(World world, BlockPos pos, IBlockState state) {
      if (!world.field_72995_K) {
         world.func_184133_a(null, pos, SRPSounds.FLESH_GROWL, SoundCategory.BLOCKS, 1.0F, 1.0F);
      }

      TileEntity te = world.func_175625_s(pos);
      if (te instanceof TileEntityDermoidCyst) {
         InventoryHelper.func_180175_a(world, pos, (TileEntityDermoidCyst)te);
         world.func_175666_e(pos, this);
      }

      super.func_180663_b(world, pos, state);
   }

   public boolean func_149740_M(IBlockState state) {
      return true;
   }

   public int func_180641_l(IBlockState state, World world, BlockPos pos) {
      TileEntity te = world.func_175625_s(pos);
      return te instanceof TileEntityDermoidCyst ? Container.func_94526_b((TileEntityDermoidCyst)te) : 0;
   }

   public IBlockState func_185499_a(IBlockState state, Rotation rot) {
      return state.func_177226_a(FACING, rot.func_185831_a((EnumFacing)state.func_177229_b(FACING)));
   }

   public IBlockState func_185471_a(IBlockState state, Mirror mirror) {
      return state.func_185907_a(mirror.func_185800_a((EnumFacing)state.func_177229_b(FACING)));
   }

   public int func_176201_c(IBlockState state) {
      return ((EnumFacing)state.func_177229_b(FACING)).func_176736_b();
   }

   public IBlockState func_176203_a(int meta) {
      return this.func_176223_P().func_177226_a(FACING, EnumFacing.func_176731_b(meta & 3));
   }

   protected BlockStateContainer func_180661_e() {
      return new BlockStateContainer(this, new IProperty[]{FACING});
   }

   public boolean func_189539_a(IBlockState state, World world, BlockPos pos, int id, int param) {
      super.func_189539_a(state, world, pos, id, param);
      TileEntity te = world.func_175625_s(pos);
      return te != null && te.func_145842_c(id, param);
   }
}
