/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 */
package com.dhanantry.scapeandrunparasites.bestiary.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class BlockBestiaryEntry {
    public final ResourceLocation id;
    public final Block block;
    public final ItemStack icon;
    public final String nameKey;
    public final String loreKey;

    public BlockBestiaryEntry(Block block, String nameKey, String loreKey) {
        this.block = block;
        this.id = block.getRegistryName();
        this.icon = new ItemStack(block);
        this.nameKey = nameKey;
        this.loreKey = loreKey;
    }
}

