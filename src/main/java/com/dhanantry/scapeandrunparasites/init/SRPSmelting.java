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
      } else {
         return new ItemStack(Item.func_150898_a(blk), count, meta);
      }
   }

   private static ItemStack i(String path, int meta, int count) {
      Item it = (Item)ForgeRegistries.ITEMS.getValue(new ResourceLocation("srparasites", path));
      if (it == null) {
         throw new IllegalStateException("Missing item: srparasites:" + path);
      } else {
         return new ItemStack(it, count, meta);
      }
   }

   public static void register() {
      GameRegistry.addSmelting(b("parasiterubble", 11, 1), b("parasiterubble", 13, 1), 0.1F);
      GameRegistry.addSmelting(b("infestedore", 0, 1), new ItemStack(Items.field_151044_h, 4), 0.1F);
      GameRegistry.addSmelting(b("infestedore", 1, 1), new ItemStack(Items.field_151045_i, 2), 1.0F);
      GameRegistry.addSmelting(b("infestedore", 2, 1), new ItemStack(Items.field_151166_bC, 2), 1.0F);
      GameRegistry.addSmelting(b("infestedore", 3, 1), new ItemStack(Items.field_151043_k, 2), 0.7F);
      GameRegistry.addSmelting(b("infestedore", 4, 1), new ItemStack(Items.field_151042_j, 2), 0.7F);
      GameRegistry.addSmelting(b("infestedore", 5, 1), new ItemStack(Items.field_151100_aR, 9, 4), 0.2F);
      GameRegistry.addSmelting(b("infestedore", 6, 1), new ItemStack(Items.field_151137_ax, 7), 0.3F);
      GameRegistry.addSmelting(b("infestedore", 7, 1), i("lurecomponent6", 0, 1), 1.0F);
      GameRegistry.addSmelting(i("bloody_rod", 0, 1), new ItemStack(Items.field_151072_bj), 0.1F);
      GameRegistry.addSmelting(i("bloody_bone", 0, 1), new ItemStack(Items.field_151103_aS), 0.1F);
      GameRegistry.addSmelting(i("bloody_iron_ingot", 0, 1), new ItemStack(Items.field_151042_j), 0.1F);
      GameRegistry.addSmelting(b("infested_cobblestone", 0, 1), b("infestedrubble", 0, 1), 0.1F);
      GameRegistry.addSmelting(i("parasitestain", 2, 1), i("cooked_flesh", 0, 1), 0.35F);
      GameRegistry.addSmelting(b("parasiterubble", 3, 2), i("hive_scrap", 0, 1), 0.1F);
   }
}
