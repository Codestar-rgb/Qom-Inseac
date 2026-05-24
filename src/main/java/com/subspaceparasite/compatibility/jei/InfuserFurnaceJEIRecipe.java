/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  mezz.jei.api.ingredients.IIngredients
 *  mezz.jei.api.ingredients.VanillaTypes
 *  mezz.jei.api.recipe.IRecipeWrapper
 *  net.minecraft.item.ItemStack
 */
package com.subspaceparasite.compatibility.jei;

import com.subspaceparasite.recipes.InfuserFurnaceRecipe;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nonnull;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

public class InfuserFurnaceJEIRecipe
implements IRecipeWrapper {
    private final InfuserFurnaceRecipe recipe;

    public InfuserFurnaceJEIRecipe(InfuserFurnaceRecipe recipe) {
        this.recipe = recipe;
    }

    public InfuserFurnaceRecipe getRecipe() {
        return this.recipe;
    }

    public void getIngredients(@Nonnull IIngredients ingredients) {
        List<ItemStack> smeltInputs = Arrays.asList(this.recipe.smeltIn.func_193365_a());
        List<ItemStack> infuseInputs = Arrays.asList(this.recipe.infuseIn.func_193365_a());
        ingredients.setInputLists(VanillaTypes.ITEM, Arrays.asList(smeltInputs, infuseInputs));
        ingredients.setOutputs(VanillaTypes.ITEM, Arrays.asList(this.recipe.infusedOut, this.recipe.bottleOut));
    }
}

