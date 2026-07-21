package com.dhanantry.scapeandrunparasites.item;

import com.dhanantry.scapeandrunparasites.block.IMetaName;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockVariant extends ItemBlock {
   private Enum[] variants;

   public ItemBlockVariant(Block block) {
      super(block);
      this.func_77627_a(true);
      this.func_77656_e(0);
      this.variants = ((IMetaName)block).getVariants();
   }

   public String func_77667_c(ItemStack stack) {
      return stack.func_77960_j() > this.variants.length - 1
         ? super.func_77658_a() + "_" + this.variants[stack.func_77960_j() & this.variants.length - 1]
         : super.func_77658_a() + "_" + this.variants[stack.func_77960_j()];
   }

   public int func_77647_b(int damage) {
      return damage;
   }
}
