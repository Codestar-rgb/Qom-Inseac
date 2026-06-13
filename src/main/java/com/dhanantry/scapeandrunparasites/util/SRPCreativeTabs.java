/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.ItemStack
 */
package com.dhanantry.scapeandrunparasites.util;

import com.dhanantry.scapeandrunparasites.init.SRPItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public final class SRPCreativeTabs
extends CreativeTabs {
    public SRPCreativeTabs(String label) {
        super(label);
    }

    public ItemStack func_78016_d() {
        return new ItemStack(SRPItems.itembase);
    }
}

