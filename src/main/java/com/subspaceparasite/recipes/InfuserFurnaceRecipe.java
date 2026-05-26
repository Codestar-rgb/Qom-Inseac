/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.Ingredient
 */
package com.subspaceparasite.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public final class InfuserFurnaceRecipe {
    public final Ingredient smeltIn;
    public final Ingredient infuseIn;
    public final ItemStack infusedOut;
    public final ItemStack bottleOut;
    public final int cookTime;

    public InfuserFurnaceRecipe(Ingredient smeltIn, Ingredient infuseIn, ItemStack infusedOut, ItemStack bottleOut, int cookTime) {
        this.smeltIn = smeltIn;
        this.infuseIn = infuseIn;
        this.infusedOut = infusedOut.func_77946_l();
        this.bottleOut = bottleOut.func_77946_l();
        this.cookTime = cookTime;
    }

    public boolean matches(ItemStack smeltStack, ItemStack infuseStack) {
        return this.smeltIn.apply(smeltStack) && this.infuseIn.apply(infuseStack);
    }
}

