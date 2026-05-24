/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package com.subspaceparasite.recipes;

import com.subspaceparasite.recipes.InfuserFurnaceRecipe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.item.ItemStack;

public final class InfuserFurnaceRecipes {
    private static final List<InfuserFurnaceRecipe> RECIPES = new ArrayList<InfuserFurnaceRecipe>();

    private InfuserFurnaceRecipes() {
    }

    public static void add(InfuserFurnaceRecipe recipe) {
        RECIPES.add(recipe);
    }

    public static InfuserFurnaceRecipe find(ItemStack smelt, ItemStack infuse) {
        if (smelt == null || smelt.func_190926_b() || infuse == null || infuse.func_190926_b()) {
            return null;
        }
        for (InfuserFurnaceRecipe r : RECIPES) {
            if (!r.matches(smelt, infuse)) continue;
            return r;
        }
        return null;
    }

    public static List<InfuserFurnaceRecipe> all() {
        return Collections.unmodifiableList(RECIPES);
    }

    public static void clear() {
        RECIPES.clear();
    }
}

