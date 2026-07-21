package com.dhanantry.scapeandrunparasites.potion;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public final class Recipe {
   private Recipe() {
   }

   public static void init() {
      String MODID = "srparasites";
      Item alveolar = (Item)ForgeRegistries.ITEMS.getValue(new ResourceLocation("srparasites", "alveolar_fluid"));
      Item diseasedSponge = (Item)ForgeRegistries.ITEMS.getValue(new ResourceLocation("srparasites", "diseased_sponge"));
      Item deadblood = (Item)ForgeRegistries.ITEMS.getValue(new ResourceLocation("srparasites", "deadblood_fluid"));
      PotionType FEAR = (PotionType)ForgeRegistries.POTION_TYPES.getValue(new ResourceLocation("srparasites", "fear"));
      PotionType WATER = (PotionType)ForgeRegistries.POTION_TYPES.getValue(new ResourceLocation("minecraft", "water"));
      PotionType AWKWARD = (PotionType)ForgeRegistries.POTION_TYPES.getValue(new ResourceLocation("minecraft", "awkward"));
      Item thornshadeBerry = (Item)ForgeRegistries.ITEMS.getValue(new ResourceLocation("srparasites", "thornshade_berry"));
      Item thornshadeDecanter = (Item)ForgeRegistries.ITEMS.getValue(new ResourceLocation("srparasites", "thornshade_decanter"));
      if (alveolar == null) {
         System.err.println("[SRPBrewing] Missing item: srparasites:alveolar_fluid");
      } else if (FEAR == null) {
         System.err.println("[SRPBrewing] Missing potion type: srparasites:fear");
      } else if (WATER == null || AWKWARD == null) {
         System.err.println("[SRPBrewing] Missing vanilla potion types: minecraft:water or minecraft:awkward");
      } else if (diseasedSponge == null) {
         System.err.println("[SRPBrewing] Missing item: srparasites:diseased_sponge");
      } else if (deadblood == null) {
         System.err.println("[SRPBrewing] Missing item: srparasites:deadblood_fluid");
      } else if (thornshadeBerry == null) {
         System.err.println("[SRPBrewing] Missing item: srparasites:thornshade_berry");
      } else if (thornshadeDecanter == null) {
         System.err.println("[SRPBrewing] Missing item: srparasites:thornshade_decanter");
      } else {
         BrewingRecipeRegistry.addRecipe(new Recipe.BaseToFearRecipe(alveolar, FEAR));
         BrewingRecipeRegistry.addRecipe(new Recipe.FearToSplashRecipe(FEAR));
         BrewingRecipeRegistry.addRecipe(new Recipe.FearSplashToLingeringRecipe(FEAR));
         BrewingRecipeRegistry.addRecipe(new Recipe.SpongeToDeadbloodRecipe(diseasedSponge, deadblood, WATER, AWKWARD));
         BrewingRecipeRegistry.addRecipe(new Recipe.BerryToThornshadeDecanterRecipe(thornshadeBerry, thornshadeDecanter, WATER, AWKWARD));
      }
   }

   private static class BaseToFearRecipe implements IBrewingRecipe {
      private final Item baseItem;
      private final PotionType fear;

      private BaseToFearRecipe(Item baseItem, PotionType fear) {
         this.baseItem = baseItem;
         this.fear = fear;
      }

      public boolean isInput(ItemStack input) {
         return !input.func_190926_b() && input.func_77973_b() == this.baseItem;
      }

      public boolean isIngredient(ItemStack ingredient) {
         return !ingredient.func_190926_b() && ingredient.func_77973_b() == Items.field_151145_ak;
      }

      public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
         return this.isInput(input) && this.isIngredient(ingredient)
            ? PotionUtils.func_185188_a(new ItemStack(Items.field_151068_bn), this.fear)
            : ItemStack.field_190927_a;
      }
   }

   private static class BerryToThornshadeDecanterRecipe implements IBrewingRecipe {
      private final Item ingredientItem;
      private final Item outItem;
      private final PotionType water;
      private final PotionType awkward;

      private BerryToThornshadeDecanterRecipe(Item ingredientItem, Item outItem, PotionType water, PotionType awkward) {
         this.ingredientItem = ingredientItem;
         this.outItem = outItem;
         this.water = water;
         this.awkward = awkward;
      }

      public boolean isInput(ItemStack input) {
         if (input.func_190926_b()) {
            return false;
         } else if (input.func_77973_b() != Items.field_151068_bn) {
            return false;
         } else {
            PotionType p = PotionUtils.func_185191_c(input);
            return p == this.water || p == this.awkward;
         }
      }

      public boolean isIngredient(ItemStack ingredient) {
         return !ingredient.func_190926_b() && ingredient.func_77973_b() == this.ingredientItem;
      }

      public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
         return this.isInput(input) && this.isIngredient(ingredient) ? new ItemStack(this.outItem, 1) : ItemStack.field_190927_a;
      }
   }

   private static class FearSplashToLingeringRecipe implements IBrewingRecipe {
      private final PotionType fear;

      private FearSplashToLingeringRecipe(PotionType fear) {
         this.fear = fear;
      }

      public boolean isInput(ItemStack input) {
         return !input.func_190926_b() && input.func_77973_b() == Items.field_185155_bH && PotionUtils.func_185191_c(input) == this.fear;
      }

      public boolean isIngredient(ItemStack ingredient) {
         return !ingredient.func_190926_b() && ingredient.func_77973_b() == Items.field_185157_bK;
      }

      public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
         return this.isInput(input) && this.isIngredient(ingredient)
            ? PotionUtils.func_185188_a(new ItemStack(Items.field_185156_bI), this.fear)
            : ItemStack.field_190927_a;
      }
   }

   private static class FearToSplashRecipe implements IBrewingRecipe {
      private final PotionType fear;

      private FearToSplashRecipe(PotionType fear) {
         this.fear = fear;
      }

      public boolean isInput(ItemStack input) {
         return !input.func_190926_b() && input.func_77973_b() == Items.field_151068_bn && PotionUtils.func_185191_c(input) == this.fear;
      }

      public boolean isIngredient(ItemStack ingredient) {
         return !ingredient.func_190926_b() && ingredient.func_77973_b() == Items.field_151016_H;
      }

      public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
         return this.isInput(input) && this.isIngredient(ingredient)
            ? PotionUtils.func_185188_a(new ItemStack(Items.field_185155_bH), this.fear)
            : ItemStack.field_190927_a;
      }
   }

   private static class SpongeToDeadbloodRecipe implements IBrewingRecipe {
      private final Item ingredientItem;
      private final Item outItem;
      private final PotionType water;
      private final PotionType awkward;

      private SpongeToDeadbloodRecipe(Item ingredientItem, Item outItem, PotionType water, PotionType awkward) {
         this.ingredientItem = ingredientItem;
         this.outItem = outItem;
         this.water = water;
         this.awkward = awkward;
      }

      public boolean isInput(ItemStack input) {
         if (input.func_190926_b()) {
            return false;
         } else if (input.func_77973_b() != Items.field_151068_bn) {
            return false;
         } else {
            PotionType p = PotionUtils.func_185191_c(input);
            return p == this.water || p == this.awkward;
         }
      }

      public boolean isIngredient(ItemStack ingredient) {
         return !ingredient.func_190926_b() && ingredient.func_77973_b() == this.ingredientItem;
      }

      public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
         return this.isInput(input) && this.isIngredient(ingredient) ? new ItemStack(this.outItem, 1) : ItemStack.field_190927_a;
      }
   }
}
