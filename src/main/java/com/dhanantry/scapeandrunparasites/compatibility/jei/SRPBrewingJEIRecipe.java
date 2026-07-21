package com.dhanantry.scapeandrunparasites.compatibility.jei;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Nonnull;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

public class SRPBrewingJEIRecipe implements IRecipeWrapper {
   private final List<ItemStack> inputs;
   private final List<ItemStack> reagents;
   private final ItemStack output;

   public SRPBrewingJEIRecipe(List<ItemStack> inputs, List<ItemStack> reagents, ItemStack output) {
      this.inputs = inputs;
      this.reagents = reagents;
      this.output = output;
   }

   public void getIngredients(@Nonnull IIngredients ingredients) {
      ingredients.setInputLists(VanillaTypes.ITEM, Arrays.asList(this.inputs, this.reagents));
      ingredients.setOutput(VanillaTypes.ITEM, this.output);
   }
}
