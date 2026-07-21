package com.dhanantry.scapeandrunparasites.item;

import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemThornshadeBerry extends ItemBase {
   public ItemThornshadeBerry() {
      super("thornshade_berry", 64, (byte)30);
   }

   public EnumActionResult func_180614_a(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      ItemStack stack = player.func_184586_b(hand);
      if (facing != EnumFacing.UP) {
         return EnumActionResult.FAIL;
      } else {
         IBlockState clickedState = worldIn.func_180495_p(pos);
         Block clickedBlock = clickedState.func_177230_c();
         BlockPos placePos = clickedBlock.func_176200_f(worldIn, pos) ? pos : pos.func_177984_a();
         if (!player.func_175151_a(placePos, facing, stack)) {
            return EnumActionResult.FAIL;
         } else if (!SRPBlocks.Thornshade.func_176196_c(worldIn, placePos)) {
            return EnumActionResult.FAIL;
         } else {
            IBlockState placeState = SRPBlocks.Thornshade.getStateForPlacement(worldIn, placePos, facing, hitX, hitY, hitZ, 0, player, hand);
            if (!worldIn.field_72995_K) {
               if (!worldIn.func_180501_a(placePos, placeState, 11)) {
                  return EnumActionResult.FAIL;
               }

               if (!player.field_71075_bZ.field_75098_d) {
                  stack.func_190918_g(1);
               }

               worldIn.func_184133_a(null, placePos, SRPSounds.BLOCKINFEST_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }

            return EnumActionResult.SUCCESS;
         }
      }
   }

   public int func_77626_a(ItemStack stack) {
      return 10;
   }

   public EnumAction func_77661_b(ItemStack stack) {
      return EnumAction.EAT;
   }

   public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
      ItemStack stack = playerIn.func_184586_b(handIn);
      if (playerIn.func_71043_e(true)) {
         playerIn.func_184598_c(handIn);
         return new ActionResult(EnumActionResult.SUCCESS, stack);
      } else {
         return new ActionResult(EnumActionResult.FAIL, stack);
      }
   }

   public ItemStack func_77654_b(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
      if (entityLiving instanceof EntityPlayer) {
         EntityPlayer player = (EntityPlayer)entityLiving;
         if (!worldIn.field_72995_K) {
            player.func_71024_bL().func_75122_a(2, 0.1F);
            player.func_70097_a(DamageSource.field_76376_m, 4.0F);
            if (!player.field_71075_bZ.field_75098_d) {
               stack.func_190918_g(1);
            }
         }

         worldIn.func_184148_a(
            null, player.field_70165_t, player.field_70163_u, player.field_70161_v, SoundEvents.field_187800_eb, player.func_184176_by(), 1.0F, 1.0F
         );
         player.func_184185_a(SRPSounds.FLESH_GROW, 10.0F, 1.0F);
      }

      return stack;
   }
}
