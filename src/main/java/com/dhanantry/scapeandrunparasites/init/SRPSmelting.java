/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.common.registry.ForgeRegistries
 *  net.minecraftforge.fml.common.registry.GameRegistry
 */
package com.dhanantry.scapeandrunparasites.init;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class SRPSmelting {
    private SRPSmelting() {
    }

    private static ItemStack b(String path, int meta, int count) {
        Block blk = (Block)ForgeRegistries.BLOCKS.getValue(new ResourceLocation("srparasites", path));
        if (blk == null) {
            throw new IllegalStateException("Missing block: srparasites:" + path);
        }
        return new ItemStack(Item.func_150898_a((Block)blk), count, meta);
    }

    private static ItemStack i(String path, int meta, int count) {
        Item it = (Item)ForgeRegistries.ITEMS.getValue(new ResourceLocation("srparasites", path));
        if (it == null) {
            throw new IllegalStateException("Missing item: srparasites:" + path);
        }
        return new ItemStack(it, count, meta);
    }

    public static void register() {
        GameRegistry.addSmelting((ItemStack)SRPSmelting.b("parasiterubble", 11, 1), (ItemStack)SRPSmelting.b("parasiterubble", 13, 1), (float)0.1f);
        GameRegistry.addSmelting((ItemStack)SRPSmelting.b("infestedore", 0, 1), (ItemStack)new ItemStack(Items.field_151044_h, 4), (float)0.1f);
        GameRegistry.addSmelting((ItemStack)SRPSmelting.b("infestedore", 1, 1), (ItemStack)new ItemStack(Items.field_151045_i, 2), (float)1.0f);
        GameRegistry.addSmelting((ItemStack)SRPSmelting.b("infestedore", 2, 1), (ItemStack)new ItemStack(Items.field_151166_bC, 2), (float)1.0f);
        GameRegistry.addSmelting((ItemStack)SRPSmelting.b("infestedore", 3, 1), (ItemStack)new ItemStack(Items.field_151043_k, 2), (float)0.7f);
        GameRegistry.addSmelting((ItemStack)SRPSmelting.b("infestedore", 4, 1), (ItemStack)new ItemStack(Items.field_151042_j, 2), (float)0.7f);
        GameRegistry.addSmelting((ItemStack)SRPSmelting.b("infestedore", 5, 1), (ItemStack)new ItemStack(Items.field_151100_aR, 9, 4), (float)0.2f);
        GameRegistry.addSmelting((ItemStack)SRPSmelting.b("infestedore", 6, 1), (ItemStack)new ItemStack(Items.field_151137_ax, 7), (float)0.3f);
        GameRegistry.addSmelting((ItemStack)SRPSmelting.b("infestedore", 7, 1), (ItemStack)SRPSmelting.i("lurecomponent6", 0, 1), (float)1.0f);
        GameRegistry.addSmelting((ItemStack)SRPSmelting.i("bloody_rod", 0, 1), (ItemStack)new ItemStack(Items.field_151072_bj), (float)0.1f);
        GameRegistry.addSmelting((ItemStack)SRPSmelting.i("bloody_bone", 0, 1), (ItemStack)new ItemStack(Items.field_151103_aS), (float)0.1f);
        GameRegistry.addSmelting((ItemStack)SRPSmelting.i("bloody_iron_ingot", 0, 1), (ItemStack)new ItemStack(Items.field_151042_j), (float)0.1f);
        GameRegistry.addSmelting((ItemStack)SRPSmelting.b("infested_cobblestone", 0, 1), (ItemStack)SRPSmelting.b("infestedrubble", 0, 1), (float)0.1f);
        GameRegistry.addSmelting((ItemStack)SRPSmelting.i("parasitestain", 2, 1), (ItemStack)SRPSmelting.i("cooked_flesh", 0, 1), (float)0.35f);
        GameRegistry.addSmelting((ItemStack)SRPSmelting.b("parasiterubble", 3, 2), (ItemStack)SRPSmelting.i("hive_scrap", 0, 1), (float)0.1f);
    }
}

