/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package com.dhanantry.scapeandrunparasites.recipes;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.item.ItemStack;

public class InfuseRecipes {
    private static final InfuseRecipes INSTANCE = new InfuseRecipes();
    private final Map<Key, ItemStack> map = new HashMap<Key, ItemStack>();

    public static InfuseRecipes instance() {
        return INSTANCE;
    }

    public void add(ItemStack base, ItemStack reagent, ItemStack result) {
        this.map.put(new Key(base, reagent), result);
    }

    public ItemStack getResult(ItemStack base, ItemStack reagent) {
        for (Map.Entry<Key, ItemStack> e : this.map.entrySet()) {
            Key k = e.getKey();
            if (!ItemStack.func_179545_c((ItemStack)k.a, (ItemStack)base) || !ItemStack.func_179545_c((ItemStack)k.b, (ItemStack)reagent)) continue;
            return e.getValue();
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

        public boolean equals(Object o) {
            if (!(o instanceof Key)) {
                return false;
            }
            Key k = (Key)o;
            return ItemStack.func_179545_c((ItemStack)this.a, (ItemStack)k.a) && ItemStack.func_179545_c((ItemStack)this.b, (ItemStack)k.b);
        }

        public int hashCode() {
            return (this.a.func_77973_b().hashCode() * 31 + this.b.func_77973_b().hashCode()) * 31 + (this.a.func_77960_j() * 7 + this.b.func_77960_j());
        }
    }
}

