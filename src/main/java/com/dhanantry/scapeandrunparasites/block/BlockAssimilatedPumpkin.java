package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockAssimilatedPumpkin extends Block {
   public static final PropertyDirection FACING = BlockHorizontal.field_185512_D;

   public BlockAssimilatedPumpkin(String regName, boolean glowing) {
      super(Material.field_151572_C);
      this.setRegistryName(regName);
      this.func_149663_c("srparasites." + regName);
      this.func_149711_c(1.0F);
      this.func_149672_a(SoundType.field_185848_a);
      this.func_149647_a(SRPMain.SRP_CREATIVETAB);
      if (glowing) {
         this.func_149715_a(1.0F);
      }

      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(FACING, EnumFacing.NORTH));
      SRPBlocks.SRP_BLOCKS.add(this);
      SRPItems.SRP_ITEMS.add(new BlockAssimilatedPumpkin.ItemAssimilatedPumpkin(this).setRegistryName(this.getRegistryName()));
   }

   public IBlockState func_180642_a(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
      EnumFacing dir = placer.func_174811_aO().func_176734_d();
      return this.func_176223_P().func_177226_a(FACING, dir);
   }

   public int func_176201_c(IBlockState state) {
      return ((EnumFacing)state.func_177229_b(FACING)).func_176736_b();
   }

   public IBlockState func_176203_a(int meta) {
      return this.func_176223_P().func_177226_a(FACING, EnumFacing.func_176731_b(meta & 3));
   }

   public IBlockState func_185499_a(IBlockState state, Rotation rot) {
      return state.func_177226_a(FACING, rot.func_185831_a((EnumFacing)state.func_177229_b(FACING)));
   }

   public IBlockState func_185471_a(IBlockState state, Mirror mirror) {
      return state.func_185907_a(mirror.func_185800_a((EnumFacing)state.func_177229_b(FACING)));
   }

   protected BlockStateContainer func_180661_e() {
      return new BlockStateContainer(this, new IProperty[]{FACING});
   }

   public boolean func_149662_c(IBlockState state) {
      return false;
   }

   public boolean func_149686_d(IBlockState state) {
      return false;
   }

   public BlockRenderLayer func_180664_k() {
      return BlockRenderLayer.CUTOUT_MIPPED;
   }

   public static class ItemAssimilatedPumpkin extends ItemBlock {
      public ItemAssimilatedPumpkin(Block block) {
         super(block);
      }

      public boolean isValidArmor(ItemStack stack, EntityEquipmentSlot slot, Entity entity) {
         return slot == EntityEquipmentSlot.HEAD;
      }

      public EntityEquipmentSlot getEquipmentSlot(ItemStack stack) {
         return EntityEquipmentSlot.HEAD;
      }

      public ActionResult<ItemStack> func_77659_a(World world, EntityPlayer player, EnumHand hand) {
         ItemStack held = player.func_184586_b(hand);
         ItemStack head = player.func_184582_a(EntityEquipmentSlot.HEAD);
         if (head.func_190926_b()) {
            ItemStack one = held.func_77946_l();
            one.func_190920_e(1);
            player.func_184201_a(EntityEquipmentSlot.HEAD, one);
            held.func_190918_g(1);
            return new ActionResult(EnumActionResult.SUCCESS, held);
         } else {
            return super.func_77659_a(world, player, hand);
         }
      }
   }
}
