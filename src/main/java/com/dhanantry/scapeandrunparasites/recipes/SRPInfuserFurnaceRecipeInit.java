/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.Ingredient
 */
package com.dhanantry.scapeandrunparasites.recipes;

import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.recipes.InfuserFurnaceRecipe;
import com.dhanantry.scapeandrunparasites.recipes.InfuserFurnaceRecipes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public final class SRPInfuserFurnaceRecipeInit {
    private SRPInfuserFurnaceRecipeInit() {
    }

    private static void addGlassSwap(ItemStack y16, ItemStack x32) {
        InfuserFurnaceRecipes.add(new InfuserFurnaceRecipe(Ingredient.func_193369_a((ItemStack[])new ItemStack[]{new ItemStack(SRPBlocks.InfestedGlass, 32)}), Ingredient.func_193369_a((ItemStack[])new ItemStack[]{y16}), x32, ItemStack.field_190927_a, 200));
    }

    public static void init() {
        InfuserFurnaceRecipes.add(new InfuserFurnaceRecipe(Ingredient.func_193369_a((ItemStack[])new ItemStack[]{new ItemStack(Items.field_151042_j)}), Ingredient.func_193369_a((ItemStack[])new ItemStack[]{new ItemStack(SRPItems.DEADBLOOD_FLUID)}), new ItemStack(SRPItems.semiorganicingot, 1), new ItemStack(Items.field_151069_bo, 1), 200));
        InfuserFurnaceRecipes.add(new InfuserFurnaceRecipe(Ingredient.func_193369_a((ItemStack[])new ItemStack[]{new ItemStack(SRPBlocks.InfestedSand)}), Ingredient.func_193369_a((ItemStack[])new ItemStack[]{new ItemStack(SRPItems.DEADBLOOD_FLUID)}), new ItemStack(SRPBlocks.InfestedGlass, 1), new ItemStack(Items.field_151069_bo, 1), 200));
        SRPInfuserFurnaceRecipeInit.addGlassSwap(new ItemStack(SRPBlocks.CookedFlesh, 16), new ItemStack(SRPBlocks.BloodyGlass, 2));
        SRPInfuserFurnaceRecipeInit.addGlassSwap(new ItemStack(SRPBlocks.InfestedTerracotta, 16), new ItemStack(SRPBlocks.AshenGlass, 2));
        SRPInfuserFurnaceRecipeInit.addGlassSwap(new ItemStack(SRPBlocks.PolandSkinBlock, 16), new ItemStack(SRPBlocks.SepiaGlass, 2));
        SRPInfuserFurnaceRecipeInit.addGlassSwap(new ItemStack(SRPBlocks.HarleskinnBlock, 16), new ItemStack(SRPBlocks.HarlequinnGlass, 2));
        SRPInfuserFurnaceRecipeInit.addGlassSwap(new ItemStack(Blocks.field_150432_aD, 16), new ItemStack(SRPBlocks.ShroudedGlass, 2));
        SRPInfuserFurnaceRecipeInit.addGlassSwap(new ItemStack(SRPBlocks.ResidueBlock, 16), new ItemStack(SRPBlocks.MoodyGlass, 2));
        SRPInfuserFurnaceRecipeInit.addGlassSwap(new ItemStack(SRPBlocks.gothShroom, 16), new ItemStack(SRPBlocks.ShadeGlass, 2));
    }
}

