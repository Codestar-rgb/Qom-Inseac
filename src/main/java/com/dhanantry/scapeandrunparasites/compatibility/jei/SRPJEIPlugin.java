/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  mezz.jei.api.IModPlugin
 *  mezz.jei.api.IModRegistry
 *  mezz.jei.api.JEIPlugin
 *  mezz.jei.api.recipe.IRecipeCategory
 *  mezz.jei.api.recipe.IRecipeCategoryRegistration
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionType
 *  net.minecraft.potion.PotionUtils
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.common.registry.ForgeRegistries
 */
package com.dhanantry.scapeandrunparasites.compatibility.jei;

import com.dhanantry.scapeandrunparasites.compatibility.jei.InfuserFurnaceJEICategory;
import com.dhanantry.scapeandrunparasites.compatibility.jei.InfuserFurnaceJEIRecipe;
import com.dhanantry.scapeandrunparasites.compatibility.jei.SRPBrewingCategory;
import com.dhanantry.scapeandrunparasites.compatibility.jei.SRPBrewingJEIRecipe;
import com.dhanantry.scapeandrunparasites.recipes.InfuserFurnaceRecipe;
import com.dhanantry.scapeandrunparasites.recipes.InfuserFurnaceRecipes;
import java.util.ArrayList;
import java.util.Arrays;
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
public class SRPJEIPlugin
implements IModPlugin {
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new IRecipeCategory[]{new InfuserFurnaceJEICategory(registry.getJeiHelpers().getGuiHelper())});
        registry.addRecipeCategories(new IRecipeCategory[]{new SRPBrewingCategory(registry.getJeiHelpers().getGuiHelper())});
    }

    public void register(IModRegistry registry) {
        ItemStack awkwardPotion;
        ItemStack waterPotion;
        ArrayList<InfuserFurnaceJEIRecipe> jeiRecipes = new ArrayList<InfuserFurnaceJEIRecipe>();
        for (InfuserFurnaceRecipe r : InfuserFurnaceRecipes.all()) {
            jeiRecipes.add(new InfuserFurnaceJEIRecipe(r));
        }
        registry.addRecipes(jeiRecipes, "srparasites.infuser_furnace");
        Block infuser = (Block)ForgeRegistries.BLOCKS.getValue(new ResourceLocation("srparasites", "infuser_furnace"));
        if (infuser != null) {
            registry.addRecipeCatalyst((Object)new ItemStack(infuser), new String[]{"srparasites.infuser_furnace"});
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
        ArrayList<SRPBrewingJEIRecipe> brewing = new ArrayList<SRPBrewingJEIRecipe>();
        if (alveolar != null && FEAR != null) {
            brewing.add(new SRPBrewingJEIRecipe(Arrays.asList(new ItemStack(alveolar)), Arrays.asList(new ItemStack(Items.field_151145_ak)), PotionUtils.func_185188_a((ItemStack)new ItemStack((Item)Items.field_151068_bn), (PotionType)FEAR)));
            brewing.add(new SRPBrewingJEIRecipe(Arrays.asList(PotionUtils.func_185188_a((ItemStack)new ItemStack((Item)Items.field_151068_bn), (PotionType)FEAR)), Arrays.asList(new ItemStack(Items.field_151016_H)), PotionUtils.func_185188_a((ItemStack)new ItemStack((Item)Items.field_185155_bH), (PotionType)FEAR)));
            brewing.add(new SRPBrewingJEIRecipe(Arrays.asList(PotionUtils.func_185188_a((ItemStack)new ItemStack((Item)Items.field_185155_bH), (PotionType)FEAR)), Arrays.asList(new ItemStack(Items.field_185157_bK)), PotionUtils.func_185188_a((ItemStack)new ItemStack((Item)Items.field_185156_bI), (PotionType)FEAR)));
        }
        if (diseasedSponge != null && deadblood != null && WATER != null && AWKWARD != null) {
            waterPotion = PotionUtils.func_185188_a((ItemStack)new ItemStack((Item)Items.field_151068_bn), (PotionType)WATER);
            awkwardPotion = PotionUtils.func_185188_a((ItemStack)new ItemStack((Item)Items.field_151068_bn), (PotionType)AWKWARD);
            brewing.add(new SRPBrewingJEIRecipe(Arrays.asList(waterPotion, awkwardPotion), Arrays.asList(new ItemStack(diseasedSponge)), new ItemStack(deadblood)));
        }
        if (thornshadeBerry != null && thornshadeDecanter != null && WATER != null && AWKWARD != null) {
            waterPotion = PotionUtils.func_185188_a((ItemStack)new ItemStack((Item)Items.field_151068_bn), (PotionType)WATER);
            awkwardPotion = PotionUtils.func_185188_a((ItemStack)new ItemStack((Item)Items.field_151068_bn), (PotionType)AWKWARD);
            brewing.add(new SRPBrewingJEIRecipe(Arrays.asList(waterPotion, awkwardPotion), Arrays.asList(new ItemStack(thornshadeBerry)), new ItemStack(thornshadeDecanter)));
        }
        if (!brewing.isEmpty()) {
            registry.addRecipes(brewing, "srparasites.srp_brewing");
            registry.addRecipeCatalyst((Object)new ItemStack(Blocks.field_150382_bo), new String[]{"srparasites.srp_brewing"});
        }
    }
}

