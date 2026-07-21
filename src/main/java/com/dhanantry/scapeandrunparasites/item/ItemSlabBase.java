package com.dhanantry.scapeandrunparasites.item;

import com.dhanantry.scapeandrunparasites.block.IMetaName;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockSlab.EnumBlockHalf;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSlabBase extends ItemBlock {
   private final BlockSlab singleSlab;
   private final BlockSlab doubleSlab;
   private IMetaName helper;
   private Enum[] variants;

   public ItemSlabBase(Block block, IMetaName block2, BlockSlab singleSlab, BlockSlab doubleSlab) {
      super(block);
      this.helper = block2;
      this.singleSlab = singleSlab;
      this.doubleSlab = doubleSlab;
      this.func_77656_e(0);
      this.func_77627_a(true);
      this.variants = ((IMetaName)block).getVariants();
   }

   public int func_77647_b(int damage) {
      return damage;
   }

   public String func_77667_c(ItemStack stack) {
      return stack.func_77960_j() > this.variants.length - 1
         ? this.singleSlab.func_149739_a() + "_" + this.variants[stack.func_77960_j() & this.variants.length - 1]
         : this.singleSlab.func_149739_a() + "_" + this.variants[stack.func_77960_j()];
   }

   public EnumActionResult func_180614_a(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      ItemStack itemstack = player.func_184586_b(hand);
      if (!itemstack.func_190926_b() && player.func_175151_a(pos.func_177972_a(facing), facing, itemstack)) {
         Comparable<?> comparable = this.singleSlab.func_185674_a(itemstack);
         IBlockState iblockstate = worldIn.func_180495_p(pos);
         if (iblockstate.func_177230_c() == this.singleSlab) {
            IProperty<?> iproperty = this.singleSlab.func_176551_l();
            Comparable<?> comparable1 = iblockstate.func_177229_b(iproperty);
            EnumBlockHalf blockslab$enumblockhalf = (EnumBlockHalf)iblockstate.func_177229_b(BlockSlab.field_176554_a);
            if ((
                  facing == EnumFacing.UP && blockslab$enumblockhalf == EnumBlockHalf.BOTTOM
                     || facing == EnumFacing.DOWN && blockslab$enumblockhalf == EnumBlockHalf.TOP
               )
               && comparable1 == comparable) {
               IBlockState iblockstate1 = this.makeState(iproperty, comparable1);
               AxisAlignedBB axisalignedbb = iblockstate1.func_185890_d(worldIn, pos);
               if (axisalignedbb != Block.field_185506_k
                  && worldIn.func_72855_b(axisalignedbb.func_186670_a(pos))
                  && worldIn.func_180501_a(pos, iblockstate1, 11)) {
                  SoundType soundtype = this.doubleSlab.getSoundType(iblockstate1, worldIn, pos, player);
                  worldIn.func_184133_a(
                     player, pos, soundtype.func_185841_e(), SoundCategory.BLOCKS, (soundtype.func_185843_a() + 1.0F) / 2.0F, soundtype.func_185847_b() * 0.8F
                  );
                  itemstack.func_190918_g(1);
                  if (player instanceof EntityPlayerMP) {
                     CriteriaTriggers.field_193137_x.func_193173_a((EntityPlayerMP)player, pos, itemstack);
                  }
               }

               return EnumActionResult.SUCCESS;
            }
         }

         return this.tryPlace(player, itemstack, worldIn, pos.func_177972_a(facing), comparable)
            ? EnumActionResult.SUCCESS
            : super.func_180614_a(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
      } else {
         return EnumActionResult.FAIL;
      }
   }

   @SideOnly(Side.CLIENT)
   public boolean func_179222_a(World worldIn, BlockPos pos, EnumFacing side, EntityPlayer player, ItemStack stack) {
      IProperty<?> iproperty = this.singleSlab.func_176551_l();
      Comparable<?> comparable = this.singleSlab.func_185674_a(stack);
      IBlockState iblockstate = worldIn.func_180495_p(pos);
      if (iblockstate.func_177230_c() == this.singleSlab) {
         boolean flag = iblockstate.func_177229_b(BlockSlab.field_176554_a) == EnumBlockHalf.TOP;
         if ((side == EnumFacing.UP && !flag || side == EnumFacing.DOWN && flag) && comparable == iblockstate.func_177229_b(iproperty)) {
            return true;
         }
      }

      BlockPos var11 = pos.func_177972_a(side);
      IBlockState iblockstate1 = worldIn.func_180495_p(var11);
      return iblockstate1.func_177230_c() == this.singleSlab && comparable == iblockstate1.func_177229_b(iproperty)
         ? true
         : super.func_179222_a(worldIn, pos, side, player, stack);
   }

   private boolean tryPlace(EntityPlayer player, ItemStack stack, World worldIn, BlockPos pos, Object itemSlabType) {
      IBlockState iblockstate = worldIn.func_180495_p(pos);
      if (iblockstate.func_177230_c() == this.singleSlab) {
         Comparable<?> comparable = iblockstate.func_177229_b(this.singleSlab.func_176551_l());
         if (comparable == itemSlabType) {
            IBlockState iblockstate1 = this.makeState(this.singleSlab.func_176551_l(), comparable);
            AxisAlignedBB axisalignedbb = iblockstate1.func_185890_d(worldIn, pos);
            if (axisalignedbb != Block.field_185506_k && worldIn.func_72855_b(axisalignedbb.func_186670_a(pos)) && worldIn.func_180501_a(pos, iblockstate1, 11)
               )
             {
               SoundType soundtype = this.doubleSlab.getSoundType(iblockstate1, worldIn, pos, player);
               worldIn.func_184133_a(
                  player, pos, soundtype.func_185841_e(), SoundCategory.BLOCKS, (soundtype.func_185843_a() + 1.0F) / 2.0F, soundtype.func_185847_b() * 0.8F
               );
               stack.func_190918_g(1);
            }

            return true;
         }
      }

      return false;
   }

   protected <T extends Comparable<T>> IBlockState makeState(IProperty<T> p_185055_1_, Comparable<?> p_185055_2_) {
      return this.doubleSlab.func_176223_P().func_177226_a(p_185055_1_, p_185055_2_);
   }

   public ItemBlock getItemBlock() {
      return this.helper.getItemBlock();
   }
}
