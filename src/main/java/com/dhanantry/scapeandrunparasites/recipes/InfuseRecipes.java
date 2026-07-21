package com.dhanantry.scapeandrunparasites.recipes;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.item.ItemStack;

public class InfuseRecipes {
   private static final InfuseRecipes INSTANCE = new InfuseRecipes();
   private final Map<InfuseRecipes.Key, ItemStack> map = new HashMap<>();

   public static InfuseRecipes instance() {
      return INSTANCE;
   }

   public void add(ItemStack base, ItemStack reagent, ItemStack result) {
      this.map.put(new InfuseRecipes.Key(base, reagent), result);
   }

   public ItemStack getResult(ItemStack base, ItemStack reagent) {
      for (Entry<InfuseRecipes.Key, ItemStack> e : this.map.entrySet()) {
         InfuseRecipes.Key k = e.getKey();
         if (ItemStack.func_179545_c(k.a, base) && ItemStack.func_179545_c(k.b, reagent)) {
            return e.getValue();
         }
      }

      return ItemStack.field_190927_a;
   }

   private static class Key {
      private final ItemStack a;
      private final ItemStack b;

      Key(ItemStack a, ItemStack b) {
         this.a = a;
         this.b = b;
      }

      @Override
      public boolean equals(Object o) {
         if (!(o instanceof InfuseRecipes.Key)) {
            return false;
         } else {
            InfuseRecipes.Key k = (InfuseRecipes.Key)o;
            return ItemStack.func_179545_c(this.a, k.a) && ItemStack.func_179545_c(this.b, k.b);
         }
      }

      @Override
      public int hashCode() {
         return (this.a.func_77973_b().hashCode() * 31 + this.b.func_77973_b().hashCode()) * 31 + this.a.func_77960_j() * 7 + this.b.func_77960_j();
      }
   }
}
