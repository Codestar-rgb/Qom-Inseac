/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.Ingredient
 */
package com.subspaceparasite.recipes;

import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.init.SPItems;
import com.subspaceparasite.recipes.InfuserFurnaceRecipe;
import com.subspaceparasite.recipes.InfuserFurnaceRecipes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public final class SPInfuserFurnaceRecipeInit {
    private SPInfuserFurnaceRecipeInit() {
    }

    private static void addGlassSwap(ItemStack y16, ItemStack x32) {
        InfuserFurnaceRecipes.add(new InfuserFurnaceRecipe(Ingredient.func_193369_a((ItemStack[])new ItemStack[]{new ItemStack(SPBlocks.InfestedGlass, 32)}), Ingredient.func_193369_a((ItemStack[])new ItemStack[]{y16}), x32, ItemStack.field_190927_a, 200));
    }

    public static void init() {
        InfuserFurnaceRecipes.add(new InfuserFurnaceRecipe(Ingredient.func_193369_a((ItemStack[])new ItemStack[]{new ItemStack(Items.field_151042_j)}), Ingredient.func_193369_a((ItemStack[])new ItemStack[]{new ItemStack(SPItems.DEADBLOOD_FLUID)}), new ItemStack(SPItems.semiorganicingot, 1), new ItemStack(Items.field_151069_bo, 1), 200));
        InfuserFurnaceRecipes.add(new InfuserFurnaceRecipe(Ingredient.func_193369_a((ItemStack[])new ItemStack[]{new ItemStack(SPBlocks.InfestedSand)}), Ingredient.func_193369_a((ItemStack[])new ItemStack[]{new ItemStack(SPItems.DEADBLOOD_FLUID)}), new ItemStack(SPBlocks.InfestedGlass, 1), new ItemStack(Items.field_151069_bo, 1), 200));
        SPInfuserFurnaceRecipeInit.addGlassSwap(new ItemStack(SPBlocks.CookedFlesh, 16), new ItemStack(SPBlocks.BloodyGlass, 2));
        SPInfuserFurnaceRecipeInit.addGlassSwap(new ItemStack(SPBlocks.InfestedTerracotta, 16), new ItemStack(SPBlocks.AshenGlass, 2));
        SPInfuserFurnaceRecipeInit.addGlassSwap(new ItemStack(SPBlocks.PolandSkinBlock, 16), new ItemStack(SPBlocks.SepiaGlass, 2));
        SPInfuserFurnaceRecipeInit.addGlassSwap(new ItemStack(SPBlocks.HarleskinnBlock, 16), new ItemStack(SPBlocks.HarlequinnGlass, 2));
        SPInfuserFurnaceRecipeInit.addGlassSwap(new ItemStack(Blocks.field_150432_aD, 16), new ItemStack(SPBlocks.ShroudedGlass, 2));
        SPInfuserFurnaceRecipeInit.addGlassSwap(new ItemStack(SPBlocks.ResidueBlock, 16), new ItemStack(SPBlocks.MoodyGlass, 2));
        SPInfuserFurnaceRecipeInit.addGlassSwap(new ItemStack(SPBlocks.gothShroom, 16), new ItemStack(SPBlocks.ShadeGlass, 2));
    }
}

