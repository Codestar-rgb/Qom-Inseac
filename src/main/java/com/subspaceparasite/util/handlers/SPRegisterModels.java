/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.renderer.ItemMeshDefinition
 *  net.minecraft.client.renderer.block.model.ModelResourceLocation
 *  net.minecraft.client.renderer.block.statemap.IStateMapper
 *  net.minecraft.client.renderer.block.statemap.StateMapperBase
 *  net.minecraft.client.renderer.texture.TextureAtlasSprite
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemDoor
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.event.ModelRegistryEvent
 *  net.minecraftforge.client.event.TextureStitchEvent$Pre
 *  net.minecraftforge.client.model.ModelLoader
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.subspaceparasite.util.handlers;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.block.BlockEvolutionLure;
import com.subspaceparasite.block.BlockGore;
import com.subspaceparasite.block.BlockInfestedBush;
import com.subspaceparasite.block.BlockInfestedOre;
import com.subspaceparasite.block.BlockParasiteBush;
import com.subspaceparasite.block.BlockParasiteCanister;
import com.subspaceparasite.block.BlockParasiteLoot;
import com.subspaceparasite.block.BlockParasitePlank;
import com.subspaceparasite.block.BlockParasiteRubble;
import com.subspaceparasite.block.BlockParasiteRubbleDense;
import com.subspaceparasite.block.BlockParasiteSapling;
import com.subspaceparasite.block.BlockParasiteStain;
import com.subspaceparasite.block.BlockParasiteTrunk;
import com.subspaceparasite.block.BlockWebBase;
import com.subspaceparasite.block.slabs.BlockSlabRubble;
import com.subspaceparasite.block.slabs.BlockSlabStain;
import com.subspaceparasite.client.IModelSP;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.init.SPItems;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value={Side.CLIENT})
public class SPRegisterModels {
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        SPRegisterModels.registerItemModels();
    }

    private static void registerItemModels() {
        for (Item item : SPItems.SP_ITEMS) {
            if (!(item instanceof IModelSP)) continue;
            ((IModelSP)item).registerModels();
        }
        for (Block block : SPBlocks.SP_BLOCKS) {
            if (!(block instanceof IModelSP)) continue;
            ((IModelSP)block).registerModels();
        }
        SPRegisterModels.registerItemModel(SPBlocks.InfestPurify);
        SPRegisterModels.registerItemModel(SPBlocks.EscaBulb);
        SPRegisterModels.registerItemModel(SPBlocks.EscaBulbWhite);
        SPRegisterModels.registerItemModel(SPBlocks.EscaBulbOrange);
        SPRegisterModels.registerItemModel(SPBlocks.EscaBulbMagenta);
        SPRegisterModels.registerItemModel(SPBlocks.EscaBulbLightBlue);
        SPRegisterModels.registerItemModel(SPBlocks.EscaBulbYellow);
        SPRegisterModels.registerItemModel(SPBlocks.EscaBulbLime);
        SPRegisterModels.registerItemModel(SPBlocks.EscaBulbPink);
        SPRegisterModels.registerItemModel(SPBlocks.EscaBulbGray);
        SPRegisterModels.registerItemModel(SPBlocks.EscaBulbLightGray);
        SPRegisterModels.registerItemModel(SPBlocks.EscaBulbCyan);
        SPRegisterModels.registerItemModel(SPBlocks.EscaBulbPurple);
        SPRegisterModels.registerItemModel(SPBlocks.EscaBulbBlue);
        SPRegisterModels.registerItemModel(SPBlocks.EscaBulbBrown);
        SPRegisterModels.registerItemModel(SPBlocks.EscaBulbGreen);
        SPRegisterModels.registerItemModel(SPBlocks.EscaBulbRed);
        SPRegisterModels.registerItemModel(SPBlocks.EscaBulbBlack);
        SPRegisterModels.registerItemModel(SPBlocks.HarlequinnGrass);
        SPRegisterModels.registerItemModel(SPBlocks.HarleskinnBlock);
        SPRegisterModels.registerItemModel(SPBlocks.PolandSkinBlock);
        SPRegisterModels.registerItemModel(SPBlocks.HairFollicleBlock);
        SPRegisterModels.registerItemModel(SPBlocks.LocsBlock);
        SPRegisterModels.registerItemModel(SPBlocks.HirsuteHair);
        SPRegisterModels.registerItemModel(SPBlocks.TressesHair);
        SPRegisterModels.registerItemModel(SPBlocks.LipomaMass);
        SPRegisterModels.registerItemModel(SPBlocks.HarleskinnStairs);
        SPRegisterModels.registerItemModel(SPBlocks.FleshStairs);
        SPRegisterModels.registerItemModel(SPBlocks.CookedFleshStairs);
        SPRegisterModels.registerItemModel(SPBlocks.BruisewoodPlankStairs);
        SPRegisterModels.registerItemModel(SPBlocks.InfestedSandstoneStairs);
        SPRegisterModels.registerItemModel(SPBlocks.GothPlanksStairs);
        SPRegisterModels.registerItemModel(SPBlocks.DeadheadPlankStairs);
        SPRegisterModels.registerItemModel(SPBlocks.ResidueStairs);
        SPRegisterModels.registerItemModel(SPBlocks.InfestedPlanksStairs);
        SPRegisterModels.registerItemModel(SPBlocks.ConsumedPlanksStairs);
        SPRegisterModels.registerItemModel(SPBlocks.InfestedStoneBricksStairs);
        SPRegisterModels.registerItemModel(SPBlocks.InfestedPolishedStoneBricksStairs);
        SPRegisterModels.registerItemModel(SPBlocks.InfestedStoneStairs);
        SPRegisterModels.registerItemModel(SPBlocks.WheatheredBricksStairs);
        SPRegisterModels.registerItemModel(SPBlocks.WheatheredCobblestoneStairs);
        SPRegisterModels.registerItemModel(SPBlocks.FrostWeatheredStoneStairs);
        SPRegisterModels.registerItemModel(SPItems.infestedbonemeal);
        SPRegisterModels.registerItemModel(SPItems.BLOODY_IRON_INGOT);
        SPRegisterModels.registerItemModel(SPItems.BLOODY_ROD);
        SPRegisterModels.registerItemModel(SPItems.BLOODY_BONE);
        SPRegisterModels.registerItemModel(SPItems.ada_vermin_drop);
        SPRegisterModels.registerItemModel(SPItems.ada_viscera_drop);
        SPRegisterModels.registerItemModel(SPItems.hijacked_drop);
        SPRegisterModels.registerItemModel(SPItems.hive_scrap);
        SPRegisterModels.registerItemModel(SPBlocks.HarleskinnFence);
        SPRegisterModels.registerItemModel(SPBlocks.InfestedFence);
        SPRegisterModels.registerItemModel(SPBlocks.DeadheadFence);
        SPRegisterModels.registerItemModel(SPBlocks.GothFence);
        SPRegisterModels.registerItemModel(SPBlocks.ConsumedFence);
        SPRegisterModels.registerItemModel(SPBlocks.BrusewoodFence);
        SPRegisterModels.registerItemModel(SPBlocks.FleshFence);
        SPRegisterModels.registerItemModel(SPBlocks.CookedFleshFence);
        SPRegisterModels.registerItemModel(SPBlocks.HarleskinnSlab);
        SPRegisterModels.registerItemModel(SPBlocks.InfestedCobblestoneSlab);
        SPRegisterModels.registerItemModel(SPBlocks.InfestedStoneSlab);
        SPRegisterModels.registerItemModel(SPBlocks.InfestedDirtSlab);
        SPRegisterModels.registerItemModel(SPBlocks.InfestedPlankSlab);
        SPRegisterModels.registerItemModel(SPBlocks.ReinforcedHivestoneSlab);
        SPRegisterModels.registerItemModel(SPBlocks.ParasiticColonyCoreSlab);
        SPRegisterModels.registerItemModel(SPBlocks.SacOfFleshSlab);
        SPRegisterModels.registerItemModel(SPBlocks.DeadHeadPlankSlab);
        SPRegisterModels.registerItemModel(SPBlocks.WeatheredBricksSlab);
        SPRegisterModels.registerItemModel(SPBlocks.ParasiticCompressedColonyStoneSlab);
        SPRegisterModels.registerItemModel(SPBlocks.WeatheredCobblestoneSlab);
        SPRegisterModels.registerItemModel(SPBlocks.FrostWeatheredStoneSlab);
        SPRegisterModels.registerItemModel(SPBlocks.InfestedStoneBrickSlab);
        SPRegisterModels.registerItemModel(SPBlocks.InfestedTerracottaSlab);
        SPRegisterModels.registerItemModel(SPBlocks.PolishedInfestedStoneSlab);
        SPRegisterModels.registerItemModel(SPBlocks.ResidueBrickSlab);
        SPRegisterModels.registerItemModel(SPBlocks.InfestedSandstoneSlab);
        SPRegisterModels.registerItemModel(SPBlocks.GothPlankSlab);
        SPRegisterModels.registerItemModel(SPBlocks.BruisewoodPlankSlab);
        SPRegisterModels.registerItemModel(SPBlocks.ConsumedPlankSlab);
        SPRegisterModels.registerItemModel(SPBlocks.ConsumedPlankSlab);
        SPRegisterModels.registerItemModel(SPBlocks.PolandSkinSlab);
        SPRegisterModels.registerItemModel(SPBlocks.LocsBlockSlab);
        SPRegisterModels.registerItemModel(SPBlocks.FleshSlab);
        SPRegisterModels.registerItemModel(SPBlocks.CookedFleshSlab);
        SPRegisterModels.registerItemModel(SPBlocks.InfestedLeaves);
        SPRegisterModels.registerItemModel(SPBlocks.InfestedLeavesFast);
        SPRegisterModels.registerItemModel(SPBlocks.InfestedPlanks);
        SPRegisterModels.registerItemModel(SPBlocks.ParasiteCactus);
        SPRegisterModels.registerItemModel(SPBlocks.CookedFleshPlanks);
        SPRegisterModels.registerItemModel(SPBlocks.CookedFlesh);
        SPRegisterModels.registerItemModel(SPBlocks.FleshPlanks);
        SPRegisterModels.registerItemModel(SPBlocks.InfestedStoneBricks);
        SPRegisterModels.registerItemModel(SPBlocks.InfestedTerracotta);
        SPRegisterModels.registerItemModel(SPBlocks.PolishedInfestedStone);
        SPRegisterModels.registerItemModel(SPBlocks.ResidueBricks);
        SPRegisterModels.registerItemModel(SPBlocks.InfestedColumn);
        SPRegisterModels.registerItemModel(SPBlocks.InfestedSandstone);
        SPRegisterModels.registerItemModel(SPBlocks.InfestedSandstoneChiseled);
        SPRegisterModels.registerItemModel(SPBlocks.InfestedSandstoneCut);
        SPRegisterModels.registerItemModel((Block)SPBlocks.ASHEN_GLASS_PANE);
        SPRegisterModels.registerItemModel((Block)SPBlocks.SHROUDED_GLASS_PANE);
        SPRegisterModels.registerItemModel((Block)SPBlocks.HARLEQUINN_GLASS_PANE);
        SPRegisterModels.registerItemModel((Block)SPBlocks.BLOODY_GLASS_PANE);
        SPRegisterModels.registerItemModel((Block)SPBlocks.INFESTED_GLASS_PANE);
        SPRegisterModels.registerItemModel((Block)SPBlocks.SHADE_GLASS_PANE);
        SPRegisterModels.registerItemModel((Block)SPBlocks.SEPIA_GLASS_PANE);
        SPRegisterModels.registerItemModel((Block)SPBlocks.MOODY_GLASS_PANE);
        SPRegisterModels.registerItemModel(SPBlocks.ShroudedGlass);
        SPRegisterModels.registerItemModel(SPBlocks.HarlequinnGlass);
        SPRegisterModels.registerItemModel(SPBlocks.BloodyGlass);
        SPRegisterModels.registerItemModel(SPBlocks.InfestedGlass);
        SPRegisterModels.registerItemModel(SPBlocks.AshenGlass);
        SPRegisterModels.registerItemModel(SPBlocks.ShadeGlass);
        SPRegisterModels.registerItemModel(SPBlocks.SepiaGlass);
        SPRegisterModels.registerItemModel(SPBlocks.MoodyGlass);
        SPRegisterModels.registerItemModel(SPBlocks.BiomassBlock);
        SPRegisterModels.registerItemModel(SPBlocks.TrophyVoidOrb);
        SPRegisterModels.registerItemModel(SPBlocks.TrophyBoomOrb);
        SPRegisterModels.registerItemModel(SPBlocks.ResidueBlock);
        SPRegisterModels.registerItemModel(SPBlocks.ResiduePlants);
        SPRegisterModels.registerItemModel(SPBlocks.FogNullifier);
        SPRegisterModels.registerItemModel(SPBlocks.InfuserFurnace);
        SPMain.proxy.modelReg(Item.func_150898_a((Block)SPBlocks.InfuserFurnace), 0, "inventory");
        SPRegisterModels.registerItemModel(SPBlocks.diseasedSponge);
        SPRegisterModels.registerItemModel(SPBlocks.SemiorganicBlock);
        SPRegisterModels.registerItemModel(SPBlocks.AssimilatedPumpkin);
        SPRegisterModels.registerItemModel(SPBlocks.AssimilatedJackOLantern);
        SPRegisterModels.registerItemModel(SPBlocks.AssimilatedSugarCane);
        SPRegisterModels.registerItemModel(SPBlocks.GothStem);
        SPRegisterModels.registerItemModel(SPBlocks.GothPlanks);
        SPRegisterModels.registerItemModel(SPBlocks.BrusewoodPlanks);
        SPRegisterModels.registerItemModel(SPBlocks.BrusewoodTrapdoor);
        SPRegisterModels.registerItemModel(SPBlocks.ConsumedPlanks);
        SPRegisterModels.registerItemModel(SPBlocks.ConsumedWorkbench);
        SPRegisterModels.registerItemModel(SPBlocks.InfestedWorkbench);
        SPRegisterModels.registerItemModel(SPBlocks.ConsumedTrapdoor);
        SPRegisterModels.registerItemModel(SPItems.itemThornshadeBerry);
        SPRegisterModels.registerItemModel(SPItems.bookofvengeance);
        SPRegisterModels.registerItemModel(((Item)new ItemDoor((Block)SPBlocks.GothDoor).setRegistryName(SPBlocks.GothDoor.getRegistryName())).func_77655_b("subspaceparasite." + SPBlocks.GothDoor.getRegistryName()));
        SPRegisterModels.registerItemModel(((Item)new ItemDoor((Block)SPBlocks.BrusewoodDoor).setRegistryName(SPBlocks.BrusewoodDoor.getRegistryName())).func_77655_b("subspaceparasite." + SPBlocks.BrusewoodDoor.getRegistryName()));
        SPRegisterModels.registerItemModel(((Item)new ItemDoor((Block)SPBlocks.ConsumedDoor).setRegistryName(SPBlocks.ConsumedDoor.getRegistryName())).func_77655_b("subspaceparasite." + SPBlocks.ConsumedDoor.getRegistryName()));
        SPRegisterModels.registerItemModel(SPBlocks.Alveoli);
        SPRegisterModels.registerItemModel(SPBlocks.SickAlveoli);
        SPRegisterModels.registerItemModel(SPBlocks.AlveoliGrowth);
        SPRegisterModels.registerItemModel(SPBlocks.SolidAlveoliBlock);
        SPRegisterModels.registerItemModel(SPBlocks.InfestedCobblestone);
        SPRegisterModels.registerItemModel(SPItems.ALVEOLAR_FLUID);
        SPRegisterModels.registerItemModel(SPItems.DEADBLOOD_FLUID);
        SPRegisterModels.registerItemModel(SPItems.VENKROL_BOOTS);
        SPRegisterModels.registerItemModel(SPItems.MOD_ADAPTED);
        SPRegisterModels.registerItemModel(SPItems.MOD_ANCIENT);
        SPRegisterModels.registerItemModel(SPItems.MOD_ASSIMILATED);
        SPRegisterModels.registerItemModel(SPItems.MOD_CRUDE);
        SPRegisterModels.registerItemModel(SPItems.MOD_DESMOID);
        SPRegisterModels.registerItemModel(SPItems.MOD_DETERRENT);
        SPRegisterModels.registerItemModel(SPItems.MOD_ESCHAR);
        SPRegisterModels.registerItemModel(SPItems.MOD_FERAL);
        SPRegisterModels.registerItemModel(SPItems.MOD_HIJACKED);
        SPRegisterModels.registerItemModel(SPItems.MOD_IDEAL);
        SPRegisterModels.registerItemModel(SPItems.MOD_INBORN);
        SPRegisterModels.registerItemModel(SPItems.MOD_NEXUS);
        SPRegisterModels.registerItemModel(SPItems.MOD_ORIGIN);
        SPRegisterModels.registerItemModel(SPItems.MOD_PREEMINENT);
        SPRegisterModels.registerItemModel(SPItems.MOD_PRIMITIVE);
        SPRegisterModels.registerItemModel(SPItems.MOD_PURE);
        SPRegisterModels.registerItemModel(SPItems.MOD_RESISTANCE);
        SPRegisterModels.registerItemModel(SPItems.MOD_ASSIMARA);
        SPRegisterModels.registerItemModel(SPItems.MOD_DERIVED);
        SPRegisterModels.registerItemModel(SPItems.MOD_VECTORS);
        SPRegisterModels.registerItemModel(SPItems.MOD_PHASE);
        SPRegisterModels.registerItemModel(SPItems.MOD_DISLODGEMENT);
        SPRegisterModels.registerItemModel(SPItems.acanra_drop);
        SPRegisterModels.registerItemModel(SPItems.aemana_drop);
        SPRegisterModels.registerItemModel(SPItems.ahull_drop);
        SPRegisterModels.registerItemModel(SPItems.anogla_drop);
        SPRegisterModels.registerItemModel(SPItems.ashyco_drop);
        SPRegisterModels.registerItemModel(SPItems.abano_drop);
        SPRegisterModels.registerItemModel(SPItems.aranrac_drop);
        SPRegisterModels.registerItemModel(SPItems.alum_drop);
        SPRegisterModels.registerItemModel(SPItems.SP_FIELD_GUIDE);
        SPRegisterModels.registerItemModel(SPItems.THORNSHADE_DECANTER);
        SPRegisterModels.registerItemModel(SPItems.modulebase);
        SPRegisterModels.registerItemModel(SPItems.tissuespike);
        SPRegisterModels.registerItemModel(SPItems.organsynth);
        SPRegisterModels.registerItemModel(SPItems.infected_drop);
        SPRegisterModels.registerItemModel(SPItems.venkrol_drop);
        SPRegisterModels.registerItemModel(SPItems.dod_drop);
        SPRegisterModels.registerItemModel(SPItems.phase_report);
        SPRegisterModels.registerItemModel(SPItems.itembase);
        SPRegisterModels.registerItemModel(SPItems.itemEvolve);
        SPRegisterModels.registerItemModel(SPItems.itemAssimilate);
        SPRegisterModels.registerItemModel(SPItems.itemDevolve);
        SPRegisterModels.registerItemModel(SPItems.itemVariant);
        SPRegisterModels.registerItemModel(SPItems.itemthrow);
        SPRegisterModels.registerItemModel(SPItems.itemEVClock);
        SPRegisterModels.registerItemModel(SPItems.itemLevelClock);
        SPRegisterModels.registerItemModel(SPItems.biomeCompass);
        SPRegisterModels.registerItemModel(SPItems.colonyCompass);
        SPRegisterModels.registerItemModel(SPItems.originCompass);
        SPRegisterModels.registerItemModel(SPItems.DISLODGEMENT_REPORT);
        SPRegisterModels.registerItemModel(SPItems.BOUGH);
        SPRegisterModels.registerItemModel(SPItems.GREEK_FIRE);
        SPRegisterModels.registerItemModel(SPItems.shrimp);
        SPRegisterModels.registerItemModel(Item.func_150898_a((Block)SPBlocks.INFESTED_POT));
        SPRegisterModels.registerItemModel(Item.func_150898_a((Block)SPBlocks.CONSUMED_POT));
        SPRegisterModels.registerItemModel(Item.func_150898_a((Block)SPBlocks.POTTED_ASSIMILATED_BLOSSOM));
        SPRegisterModels.registerItemModel(Item.func_150898_a((Block)SPBlocks.POTTED_CONSUMED_ASSIMILATED_BLOSSOM));
        SPRegisterModels.registerItemModel(Item.func_150898_a((Block)SPBlocks.POTTED_CONSUMED_ASSIMILATED_BLOSSOM));
        SPRegisterModels.registerItemModel(Item.func_150898_a((Block)SPBlocks.ASSIMILATED_BLOSSOM));
        SPRegisterModels.registerItemModel(SPItems.tendrons);
        SPRegisterModels.registerItemModel(SPItems.hardbone);
        SPRegisterModels.registerItemModel(SPItems.infblade);
        SPRegisterModels.registerItemModel(SPItems.livingcore);
        SPRegisterModels.registerItemModel(SPItems.vileshell);
        SPRegisterModels.registerItemModel(SPItems.bone);
        SPRegisterModels.registerItemModel(SPItems.pearl);
        SPRegisterModels.registerItemModel(SPItems.falseapple);
        SPRegisterModels.registerItemModel(SPItems.semiorganicingot);
        SPRegisterModels.registerItemModel(SPItems.fishlin);
        SPRegisterModels.registerItemModel(SPItems.alveoligrowth);
        SPRegisterModels.registerItemModel(SPItems.VECTOR_MAP);
        SPRegisterModels.registerItemModel(SPItems.itemlurecomponent1);
        SPRegisterModels.registerItemModel(SPItems.itemlurecomponent2);
        SPRegisterModels.registerItemModel(SPItems.itemlurecomponent3);
        SPRegisterModels.registerItemModel(SPItems.itemlurecomponent4);
        SPRegisterModels.registerItemModel(SPItems.itemlurecomponent5);
        SPRegisterModels.registerItemModel(SPItems.itemlurecomponent6);
        SPRegisterModels.registerItemModel(SPItems.itemlurecomponent7);
        SPRegisterModels.registerItemModel(SPItems.itemlurecomponent8);
        SPRegisterModels.registerItemModel(SPItems.itemlurecomponent9);
        SPRegisterModels.registerItemModel(SPItems.itemlurecomponent10);
        SPRegisterModels.registerItemModel(SPItems.itemlurecomponent10);
        SPRegisterModels.registerItemModel(SPItems.itemlurecomponent10);
        SPRegisterModels.registerItemModel(SPItems.itemlurecomponent10);
        SPRegisterModels.registerItemModel(SPItems.weapon_scythe);
        SPRegisterModels.registerItemModel(SPItems.weapon_scytheSentient);
        SPRegisterModels.registerItemModel(SPItems.weapon_axe);
        SPRegisterModels.registerItemModel(SPItems.weapon_axeSentient);
        SPRegisterModels.registerItemModel(SPItems.weapon_sword);
        SPRegisterModels.registerItemModel(SPItems.weapon_swordSentient);
        SPRegisterModels.registerItemModel(SPItems.weapon_cleaver);
        SPRegisterModels.registerItemModel(SPItems.weapon_cleaverSentient);
        SPRegisterModels.registerItemModel(SPItems.weapon_bow);
        SPRegisterModels.registerItemModel(SPItems.weapon_bow_sentient);
        SPRegisterModels.registerItemModel(SPItems.weapon_maul);
        SPRegisterModels.registerItemModel(SPItems.weapon_maulSentient);
        SPRegisterModels.registerItemModel(SPItems.weapon_lance);
        SPRegisterModels.registerItemModel(SPItems.weapon_lanceSentient);
        SPRegisterModels.registerItemModel(SPItems.armor_helmet);
        SPRegisterModels.registerItemModel(SPItems.armor_chest);
        SPRegisterModels.registerItemModel(SPItems.armor_pants);
        SPRegisterModels.registerItemModel(SPItems.armor_boots);
        SPRegisterModels.registerItemModel(SPItems.armor_helmetSentient);
        SPRegisterModels.registerItemModel(SPItems.armor_chestSentient);
        SPRegisterModels.registerItemModel(SPItems.armor_pantsSentient);
        SPRegisterModels.registerItemModel(SPItems.armor_bootsSentient);
        SPRegisterModels.registerItemModel(SPItems.hijacked_iron_boots);
        SPRegisterModels.registerItemModel(SPItems.hijacked_iron_helmet);
        SPRegisterModels.registerItemModel(SPItems.hijacked_iron_chestpiece);
        SPRegisterModels.registerItemModel(SPItems.hijacked_iron_leggings);
        SPRegisterModels.registerItemModel(SPItems.hijacked_iron_hoe);
        SPRegisterModels.registerItemModel(SPItems.hijacked_iron_axe);
        SPRegisterModels.registerItemModel(SPItems.hijacked_iron_shovel);
        SPRegisterModels.registerItemModel(SPItems.hijacked_iron_sword);
        SPRegisterModels.registerItemModel(SPItems.hijacked_iron_pickaxe);
        SPRegisterModels.registerItemModel(SPItems.disc1);
        SPRegisterModels.registerItemModel(SPItems.disc2);
        SPRegisterModels.registerItemModel(SPItems.disc3);
        SPRegisterModels.registerItemModel(SPBlocks.InfestedStain);
        SPRegisterModels.registerItemModel(SPBlocks.InfestedRubble);
        SPRegisterModels.registerItemModel(SPBlocks.InfestedTrunk);
        SPRegisterModels.registerItemModel(SPBlocks.InfestRemain);
        SPRegisterModels.registerItemModel(SPBlocks.BiomeHeart);
        SPRegisterModels.registerItemModel(SPBlocks.ColonyHeart);
        SPRegisterModels.registerItemModel(SPBlocks.ColonyOutpost);
        SPRegisterModels.registerItemModel(SPBlocks.BiomePurifier);
        SPRegisterModels.registerItemModel(SPBlocks.SPWeb);
        SPRegisterModels.registerItemModel(SPItems.signstatus);
        SPRegisterModels.registerItemModel(SPBlocks.ParasiteStructure);
        SPRegisterModels.registerItemModel(SPBlocks.buglin);
        SPRegisterModels.registerItemModel(SPBlocks.ParasiteThin);
        SPRegisterModels.registerItemModel((Block)SPBlocks.ParasiteRubbleSlabHalf);
        SPRegisterModels.registerItemModel((Block)SPBlocks.ParasiteStainSlabHalf);
        SPRegisterModels.registerItemModel(SPBlocks.ParasiteVine);
        SPRegisterModels.registerItemModel(SPBlocks.ParasiteFog);
        SPRegisterModels.registerItemModel(SPBlocks.ParasiteMouth);
        SPRegisterModels.registerItemModel((Block)SPBlocks.ParasiteCanisterActive);
        SPRegisterModels.registerItemModel(SPBlocks.ParasiteRubbleFleshWall);
        SPRegisterModels.registerItemModel(SPBlocks.dodN);
        SPRegisterModels.registerItemModel(SPBlocks.InfestedSand);
        SPRegisterModels.registerItemModel(SPBlocks.BloodyIce);
        SPRegisterModels.registerItemModel(SPBlocks.gothShroom);
        SPRegisterModels.registerItemModel(SPBlocks.NODE_LAMP);
        SPRegisterModels.registerItemModel(SPBlocks.RELAY_CONTROLLER);
        SPRegisterModels.registerItemModel(SPBlocks.PARASITE_BARRIER);
        SPRegisterModels.registerItemModel(SPBlocks.DermoidCyst);
        SPRegisterModels.registerItemModel(SPBlocks.RelayBase);
        SPRegisterModels.registerItemModel(SPBlocks.RelayMiddle);
        SPRegisterModels.registerItemModel(SPBlocks.RelayRoof);
        SPRegisterModels.registerItemModel(SPBlocks.INFESTED_FURNACE);
        SPRegisterModels.registerItemModel(SPBlocks.ParasitePlankDeadheadWall);
        SPRegisterModels.registerItemModel(SPBlocks.ParasiteRubbleWeathbWall);
        SPRegisterModels.registerItemModel(SPBlocks.ParasiteRubbleDenseColonyWall);
        SPRegisterModels.registerItemModel(SPBlocks.ParasiteRubbleWeathfsWall);
        SPRegisterModels.registerItemModel(SPBlocks.ParasiteRubbleDenseBiomeWall);
        SPRegisterModels.registerItemModel(SPBlocks.ParasiteRubbleWeathbcWall);
        SPRegisterModels.registerItemModel(SPBlocks.ParasiteRubbleBricksWall);
        SPRegisterModels.registerItemModel(SPBlocks.ParasiteCanisterBagWall);
        SPRegisterModels.registerItemModel(SPBlocks.InfestedRubbleWall);
        SPRegisterModels.registerItemModel(SPBlocks.InfestedStainWall);
        SPRegisterModels.registerItemModel(SPBlocks.ParasiteStainFleshWall);
        SPRegisterModels.registerItemModel(SPBlocks.ParasiteRubbleMetalWall);
        SPRegisterModels.registerItemModel(SPBlocks.InfestedPlankWall);
        SPRegisterModels.registerItemModel(SPBlocks.ResidueBrickWall);
        SPRegisterModels.registerItemModel(SPBlocks.BruisewoodPlankWall);
        SPRegisterModels.registerItemModel(SPBlocks.PolishedInfestedStoneWall);
        SPRegisterModels.registerItemModel(SPBlocks.InfestedStoneBrickWall);
        SPRegisterModels.registerItemModel(SPBlocks.GothPlankWall);
        SPRegisterModels.registerItemModel(SPBlocks.InfestedSandstoneWall);
        SPRegisterModels.registerItemModel(SPBlocks.ConsumedPlankWall);
        SPRegisterModels.registerBlockVariants();
        SPRegisterModels.RegisterCustomMashesAndStates();
        SPMain.logger.info("Models registered");
    }

    private static void registerItemModel(Item item) {
        if (item == null) {
            SPMain.logger.error("registerItemModel(Item): item is NULL");
            return;
        }
        if (item.getRegistryName() == null) {
            SPMain.logger.error("registerItemModel(Item): item has NULL registry name: {} ({})", (Object)item, (Object)item.getClass().getName());
            return;
        }
        ModelResourceLocation location = new ModelResourceLocation(item.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation((Item)item, (int)0, (ModelResourceLocation)location);
    }

    private static void registerItemModel(Block block) {
        if (block == null) {
            SPMain.logger.error("registerItemModel(Block): block is NULL");
            return;
        }
        Item item = Item.func_150898_a((Block)block);
        if (item == null || item == Items.field_190931_a) {
            SPMain.logger.error("registerItemModel(Block): no ItemBlock for block {} reg={}", (Object)block, (Object)block.getRegistryName());
            return;
        }
        SPRegisterModels.registerItemModel(item);
    }

    private static void registerItemModel(Block block, int meta, String fileName) {
        if (block == null) {
            SPMain.logger.error("registerItemModel(Block,meta,file): block is NULL file={}", (Object)fileName);
            return;
        }
        Item item = Item.func_150898_a((Block)block);
        if (item == null || item == Items.field_190931_a) {
            SPMain.logger.error("registerItemModel(Block,meta,file): no ItemBlock for block {} reg={} meta={} file={}", (Object)block, (Object)block.getRegistryName(), (Object)meta, (Object)fileName);
            return;
        }
        ModelResourceLocation location = new ModelResourceLocation(new ResourceLocation("subspaceparasite", fileName), "inventory");
        ModelLoader.setCustomModelResourceLocation((Item)item, (int)meta, (ModelResourceLocation)location);
    }

    private static void registerBlockVariants() {
        int i;
        for (i = 0; i < BlockWebBase.EnumType.values().length; ++i) {
            SPRegisterModels.registerItemModel(SPBlocks.SPWeb, i, "web_" + BlockWebBase.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockGore.EnumType.values().length; ++i) {
            SPRegisterModels.registerItemModel((Block)SPBlocks.goreSim, i, "gore_sim_" + BlockGore.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockGore.EnumType.values().length; ++i) {
            SPRegisterModels.registerItemModel((Block)SPBlocks.gorePri, i, "gore_pri_" + BlockGore.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockGore.EnumType.values().length; ++i) {
            SPRegisterModels.registerItemModel((Block)SPBlocks.goreAda, i, "gore_ada_" + BlockGore.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockGore.EnumType.values().length; ++i) {
            SPRegisterModels.registerItemModel((Block)SPBlocks.gorePur, i, "gore_pur_" + BlockGore.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockGore.EnumType.values().length; ++i) {
            SPRegisterModels.registerItemModel((Block)SPBlocks.goreFer, i, "gore_fer_" + BlockGore.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockGore.EnumType.values().length; ++i) {
            SPRegisterModels.registerItemModel((Block)SPBlocks.goreMar, i, "gore_mar_" + BlockGore.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockEvolutionLure.EnumType.values().length; ++i) {
            SPRegisterModels.registerItemModel(SPBlocks.evolutionLure, i, "lure_" + BlockEvolutionLure.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockInfestedBush.EnumType.values().length; ++i) {
            SPRegisterModels.registerItemModel((Block)SPBlocks.InfestedBush, i, "infestedbush_" + BlockInfestedBush.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockParasiteCanister.EnumType.values().length; ++i) {
            SPRegisterModels.registerItemModel(SPBlocks.ParasiteCanister, i, "canister_" + BlockParasiteCanister.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockParasiteRubble.EnumType.values().length; ++i) {
            SPRegisterModels.registerItemModel(SPBlocks.ParasiteRubble, i, "rubble_" + BlockParasiteRubble.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockParasiteStain.EnumType.values().length; ++i) {
            SPRegisterModels.registerItemModel(SPBlocks.ParasiteStain, i, "stain_" + BlockParasiteStain.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockParasiteTrunk.EnumType.values().length; ++i) {
            SPRegisterModels.registerItemModel(SPBlocks.ParasiteTrunk, i, "trunk_" + BlockParasiteTrunk.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockParasitePlank.EnumType.values().length; ++i) {
            SPRegisterModels.registerItemModel(SPBlocks.ParasitePlank, i, "plank_" + BlockParasitePlank.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockParasiteBush.EnumType.values().length; ++i) {
            SPRegisterModels.registerItemModel((Block)SPBlocks.ParasiteBush, i, "parasitebush_" + BlockParasiteBush.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockParasiteSapling.EnumType.values().length; ++i) {
            SPRegisterModels.registerItemModel(SPBlocks.ParasiteSapling, i, "sapling_" + BlockParasiteSapling.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockParasiteRubbleDense.EnumType.values().length; ++i) {
            SPRegisterModels.registerItemModel(SPBlocks.ParasiteRubbleDense, i, "rubbledense_" + BlockParasiteRubbleDense.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockInfestedOre.EnumType.values().length; ++i) {
            SPRegisterModels.registerItemModel(SPBlocks.InfestedOre, i, "infestedore_" + BlockInfestedOre.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockParasiteLoot.EnumType.values().length; ++i) {
            SPRegisterModels.registerItemModel(SPBlocks.ParasiteLoot, i, "loot_" + BlockParasiteLoot.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockSlabRubble.EnumType.values().length; ++i) {
            SPRegisterModels.registerItemModel((Block)SPBlocks.ParasiteRubbleSlabHalf, i, "slab_" + BlockSlabRubble.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockSlabStain.EnumType.values().length; ++i) {
            SPRegisterModels.registerItemModel((Block)SPBlocks.ParasiteStainSlabHalf, i, "slab_" + BlockSlabStain.EnumType.values()[i].func_176610_l());
        }
        SPRegisterModels.registerItemModel(SPBlocks.BloodyIce, 0, "bloodyice");
    }

    public static void RegisterCustomMashesAndStates() {
        ModelLoader.setCustomMeshDefinition((Item)Item.func_150898_a((Block)SPBlocks.DeadBlood), (ItemMeshDefinition)new ItemMeshDefinition(){

            public ModelResourceLocation func_178113_a(ItemStack stack) {
                return new ModelResourceLocation("subspaceparasite:deadblood", "fluid");
            }
        });
        ModelLoader.setCustomStateMapper((Block)SPBlocks.DeadBlood, (IStateMapper)new StateMapperBase(){

            protected ModelResourceLocation func_178132_a(IBlockState state) {
                return new ModelResourceLocation("subspaceparasite:deadblood", "fluid");
            }
        });
    }

    @Mod.EventBusSubscriber(modid="subspaceparasite", value={Side.CLIENT})
    public static final class SPClientParticles {
        public static TextureAtlasSprite INFESTED_LEAF_SPRITE;
        public static final TextureAtlasSprite[] INFESTED_LEAF;

        @SubscribeEvent
        public static void onTextureStitch(TextureStitchEvent.Pre e) {
            TextureMap map = e.getMap();
            SPClientParticles.INFESTED_LEAF[0] = INFESTED_LEAF_SPRITE = map.func_174942_a(new ResourceLocation("subspaceparasite:particle/infested_leaves"));
            SPClientParticles.INFESTED_LEAF[1] = map.func_174942_a(new ResourceLocation("subspaceparasite:particle/infested_leaves2"));
            SPClientParticles.INFESTED_LEAF[2] = map.func_174942_a(new ResourceLocation("subspaceparasite:particle/infested_leaves3"));
            SPClientParticles.INFESTED_LEAF[3] = map.func_174942_a(new ResourceLocation("subspaceparasite:particle/infested_leaves4"));
        }

        public static TextureAtlasSprite randomInfestedLeaf(Random rand) {
            return INFESTED_LEAF[rand.nextInt(INFESTED_LEAF.length)];
        }

        static {
            INFESTED_LEAF = new TextureAtlasSprite[4];
        }
    }
}

