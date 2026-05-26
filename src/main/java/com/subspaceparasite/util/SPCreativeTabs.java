/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.ItemStack
 */
package com.subspaceparasite.util;

import com.subspaceparasite.init.SPItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public final class SPCreativeTabs
extends CreativeTabs {
    public SPCreativeTabs(String label) {
        super(label);
    }

    public ItemStack func_78016_d() {
        return new ItemStack(SPItems.itembase);
    }
}

