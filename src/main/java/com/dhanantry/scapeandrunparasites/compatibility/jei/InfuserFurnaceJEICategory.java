package com.dhanantry.scapeandrunparasites.compatibility.jei;

import javax.annotation.Nonnull;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.IDrawableAnimated.StartDirection;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class InfuserFurnaceJEICategory implements IRecipeCategory<InfuserFurnaceJEIRecipe> {
   public static final String UID = "srparasites.infuser_furnace";
   private static final ResourceLocation BG = new ResourceLocation("srparasites", "textures/gui/infuser_furnace.png");
   private static final ResourceLocation VANILLA_FURNACE = new ResourceLocation("minecraft", "textures/gui/container/furnace.png");
   private final IDrawable background;
   private final IDrawableAnimated arrow;

   public InfuserFurnaceJEICategory(IGuiHelper guiHelper) {
      this.background = guiHelper.createDrawable(BG, 0, 0, 176, 82);
      IDrawableStatic arrowStatic = guiHelper.createDrawable(VANILLA_FURNACE, 176, 14, 24, 17);
      this.arrow = guiHelper.createAnimatedDrawable(arrowStatic, 200, StartDirection.LEFT, false);
   }

   @Nonnull
   public String getUid() {
      return "srparasites.infuser_furnace";
   }

   @Nonnull
   public String getTitle() {
      return "Infuser Furnace";
   }

   @Nonnull
   public String getModName() {
      return "Scape and Run: Parasites";
   }

   @Nonnull
   public IDrawable getBackground() {
      return this.background;
   }

   public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull InfuserFurnaceJEIRecipe recipeWrapper, @Nonnull IIngredients ingredients) {
      IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
      stacks.init(0, true, 55, 16);
      stacks.init(1, true, 29, 34);
      stacks.init(2, false, 115, 34);
      stacks.init(3, false, 139, 52);
      stacks.set(ingredients);
   }

   public void drawExtras(@Nonnull Minecraft minecraft) {
      this.arrow.draw(minecraft, 79, 34);
   }
}
