package com.dhanantry.scapeandrunparasites.compatibility.jei;

import com.dhanantry.scapeandrunparasites.recipes.InfuserFurnaceRecipe;
import com.dhanantry.scapeandrunparasites.recipes.InfuserFurnaceRecipes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

@JEIPlugin
public class SRPJEIPlugin implements IModPlugin {
   public void registerCategories(IRecipeCategoryRegistration registry) {
      registry.addRecipeCategories(new IRecipeCategory[]{new InfuserFurnaceJEICategory(registry.getJeiHelpers().getGuiHelper())});
      registry.addRecipeCategories(new IRecipeCategory[]{new SRPBrewingCategory(registry.getJeiHelpers().getGuiHelper())});
   }

   public void register(IModRegistry registry) {
      List<InfuserFurnaceJEIRecipe> jeiRecipes = new ArrayList<>();

      for (InfuserFurnaceRecipe r : InfuserFurnaceRecipes.all()) {
         jeiRecipes.add(new InfuserFurnaceJEIRecipe(r));
      }

      registry.addRecipes(jeiRecipes, "srparasites.infuser_furnace");
      Block infuser = (Block)ForgeRegistries.BLOCKS.getValue(new ResourceLocation("srparasites", "infuser_furnace"));
      if (infuser != null && infuser != Blocks.field_150350_a) {
         ItemStack infuserStack = new ItemStack(infuser);
         if (!infuserStack.func_190926_b()) {
            registry.addRecipeCatalyst(infuserStack, new String[]{"srparasites.infuser_furnace"});
         }
      }

      String MODID = "srparasites";
      Item alveolar = (Item)ForgeRegistries.ITEMS.getValue(new ResourceLocation("srparasites", "alveolar_fluid"));
      Item diseasedSponge = (Item)ForgeRegistries.ITEMS.getValue(new ResourceLocation("srparasites", "diseased_sponge"));
      Item deadblood = (Item)ForgeRegistries.ITEMS.getValue(new ResourceLocation("srparasites", "deadblood_fluid"));
      Item thornshadeBerry = (Item)ForgeRegistries.ITEMS.getValue(new ResourceLocation("srparasites", "thornshade_berry"));
      Item thornshadeDecanter = (Item)ForgeRegistries.ITEMS.getValue(new ResourceLocation("srparasites", "thornshade_decanter"));
      PotionType FEAR = (PotionType)ForgeRegistries.POTION_TYPES.getValue(new ResourceLocation("srparasites", "fear"));
      PotionType WATER = (PotionType)ForgeRegistries.POTION_TYPES.getValue(new ResourceLocation("minecraft", "water"));
      PotionType AWKWARD = (PotionType)ForgeRegistries.POTION_TYPES.getValue(new ResourceLocation("minecraft", "awkward"));
      List<SRPBrewingJEIRecipe> brewing = new ArrayList<>();
      if (alveolar != null && FEAR != null) {
         brewing.add(
            new SRPBrewingJEIRecipe(
               Arrays.asList(new ItemStack(alveolar)),
               Arrays.asList(new ItemStack(Items.field_151145_ak)),
               PotionUtils.func_185188_a(new ItemStack(Items.field_151068_bn), FEAR)
            )
         );
         brewing.add(
            new SRPBrewingJEIRecipe(
               Arrays.asList(PotionUtils.func_185188_a(new ItemStack(Items.field_151068_bn), FEAR)),
               Arrays.asList(new ItemStack(Items.field_151016_H)),
               PotionUtils.func_185188_a(new ItemStack(Items.field_185155_bH), FEAR)
            )
         );
         brewing.add(
            new SRPBrewingJEIRecipe(
               Arrays.asList(PotionUtils.func_185188_a(new ItemStack(Items.field_185155_bH), FEAR)),
               Arrays.asList(new ItemStack(Items.field_185157_bK)),
               PotionUtils.func_185188_a(new ItemStack(Items.field_185156_bI), FEAR)
            )
         );
      }

      if (diseasedSponge != null && deadblood != null && WATER != null && AWKWARD != null) {
         ItemStack waterPotion = PotionUtils.func_185188_a(new ItemStack(Items.field_151068_bn), WATER);
         ItemStack awkwardPotion = PotionUtils.func_185188_a(new ItemStack(Items.field_151068_bn), AWKWARD);
         brewing.add(new SRPBrewingJEIRecipe(Arrays.asList(waterPotion, awkwardPotion), Arrays.asList(new ItemStack(diseasedSponge)), new ItemStack(deadblood)));
      }

      if (thornshadeBerry != null && thornshadeDecanter != null && WATER != null && AWKWARD != null) {
         ItemStack waterPotion = PotionUtils.func_185188_a(new ItemStack(Items.field_151068_bn), WATER);
         ItemStack awkwardPotion = PotionUtils.func_185188_a(new ItemStack(Items.field_151068_bn), AWKWARD);
         brewing.add(
            new SRPBrewingJEIRecipe(Arrays.asList(waterPotion, awkwardPotion), Arrays.asList(new ItemStack(thornshadeBerry)), new ItemStack(thornshadeDecanter))
         );
      }

      if (!brewing.isEmpty()) {
         registry.addRecipes(brewing, "srparasites.srp_brewing");
         ItemStack brewingStandStack = new ItemStack(Items.field_151067_bt);
         if (!brewingStandStack.func_190926_b()) {
            registry.addRecipeCatalyst(brewingStandStack, new String[]{"srparasites.srp_brewing"});
         }
      }

      blacklistAdvancementIcon(registry, "dark_days_icon");
      blacklistAdvancementIcon(registry, "adapted_icon");
      blacklistAdvancementIcon(registry, "primitive_icon");
      blacklistAdvancementIcon(registry, "crude_icon");
      blacklistAdvancementIcon(registry, "pure_icon");
      blacklistAdvancementIcon(registry, "hunt_season_icon");
      blacklistAdvancementIcon(registry, "guerilla_icon");
      blacklistAdvancementIcon(registry, "ecstasy_icon");
      blacklistAdvancementIcon(registry, "enemy_of_enemy_icon");
      blacklistAdvancementIcon(registry, "fog_nullifier_icon");
      blacklistAdvancementIcon(registry, "self_destruct_icon");
      blacklistAdvancementIcon(registry, "potion_columbus_icon");
      blacklistAdvancementIcon(registry, "potion_stolas_icon");
      blacklistAdvancementIcon(registry, "hellfire_chemical_warfare_icon");
      blacklistAdvancementIcon(registry, "cosmic_structural_failure_icon");
      blacklistAdvancementIcon(registry, "roots_icon");
   }

   private static void blacklistAdvancementIcon(IModRegistry registry, String itemName) {
      ResourceLocation id = new ResourceLocation("srparasites", itemName);
      Item item = (Item)ForgeRegistries.ITEMS.getValue(id);
      if (item != null && item != Items.field_190931_a) {
         ItemStack stack = new ItemStack(item);
         if (stack.func_190926_b()) {
            System.out.println("[SRP][JEI] Empty advancement icon stack: " + id);
         } else {
            try {
               registry.getJeiHelpers().getIngredientBlacklist().addIngredientToBlacklist(stack);
               System.out.println("[SRP][JEI] Blacklisted advancement icon: " + id);
            } catch (Throwable var6) {
               System.out.println("[SRP][JEI] Failed to blacklist advancement icon: " + id);
               var6.printStackTrace();
            }
         }
      } else {
         System.out.println("[SRP][JEI] Missing advancement icon item: " + id);
      }
   }
}
