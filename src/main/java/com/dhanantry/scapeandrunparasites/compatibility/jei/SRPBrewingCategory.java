/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  mezz.jei.api.IGuiHelper
 *  mezz.jei.api.gui.IDrawable
 *  mezz.jei.api.gui.IGuiItemStackGroup
 *  mezz.jei.api.gui.IRecipeLayout
 *  mezz.jei.api.ingredients.IIngredients
 *  mezz.jei.api.recipe.IRecipeCategory
 *  net.minecraft.util.ResourceLocation
 */
package com.dhanantry.scapeandrunparasites.compatibility.jei;

import com.dhanantry.scapeandrunparasites.compatibility.jei.SRPBrewingJEIRecipe;
import javax.annotation.Nonnull;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.util.ResourceLocation;

public class SRPBrewingCategory
implements IRecipeCategory<SRPBrewingJEIRecipe> {
    public static final String UID = "srparasites.srp_brewing";
    private static final ResourceLocation VANILLA_BREWING = new ResourceLocation("minecraft", "textures/gui/container/brewing_stand.png");
    private final IDrawable background;

    public SRPBrewingCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(VANILLA_BREWING, 0, 0, 176, 80);
    }

    @Nonnull
    public String getUid() {
        return UID;
    }

    @Nonnull
    public String getTitle() {
        return "SRP Brewing";
    }

    @Nonnull
    public String getModName() {
        return "Scape and Run: Parasites";
    }

    @Nonnull
    public IDrawable getBackground() {
        return this.background;
    }

    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull SRPBrewingJEIRecipe recipeWrapper, @Nonnull IIngredients ingredients) {
        IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
        stacks.init(0, true, 55, 50);
        stacks.init(1, true, 78, 16);
        stacks.init(2, false, 101, 50);
        stacks.set(ingredients);
    }
}

