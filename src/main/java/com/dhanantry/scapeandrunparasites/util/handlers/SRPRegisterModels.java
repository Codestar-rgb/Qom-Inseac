package com.dhanantry.scapeandrunparasites.util.handlers;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.block.BlockEvolutionLure;
import com.dhanantry.scapeandrunparasites.block.BlockGore;
import com.dhanantry.scapeandrunparasites.block.BlockInfestedBush;
import com.dhanantry.scapeandrunparasites.block.BlockInfestedOre;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteBush;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteCanister;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteLoot;
import com.dhanantry.scapeandrunparasites.block.BlockParasitePlank;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteRubble;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteRubbleDense;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteSapling;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteStain;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteTrunk;
import com.dhanantry.scapeandrunparasites.block.BlockWebBase;
import com.dhanantry.scapeandrunparasites.block.slabs.BlockSlabRubble;
import com.dhanantry.scapeandrunparasites.block.slabs.BlockSlabStain;
import com.dhanantry.scapeandrunparasites.client.IModelSRP;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent.Pre;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(Side.CLIENT)
public class SRPRegisterModels {
   @SubscribeEvent
   public static void registerModels(ModelRegistryEvent event) {
      registerItemModels();
   }

   private static void registerItemModels() {
      for (Item item : SRPItems.SRP_ITEMS) {
         if (item instanceof IModelSRP) {
            ((IModelSRP)item).registerModels();
         }
      }

      for (Block block : SRPBlocks.SRP_BLOCKS) {
         if (block instanceof IModelSRP) {
            ((IModelSRP)block).registerModels();
         }
      }

      registerItemModel(SRPBlocks.InfestPurify);
      registerItemModel(SRPBlocks.EscaBulb);
      registerItemModel(SRPBlocks.EscaBulbWhite);
      registerItemModel(SRPBlocks.EscaBulbOrange);
      registerItemModel(SRPBlocks.EscaBulbMagenta);
      registerItemModel(SRPBlocks.EscaBulbLightBlue);
      registerItemModel(SRPBlocks.EscaBulbYellow);
      registerItemModel(SRPBlocks.EscaBulbLime);
      registerItemModel(SRPBlocks.EscaBulbPink);
      registerItemModel(SRPBlocks.EscaBulbGray);
      registerItemModel(SRPBlocks.EscaBulbLightGray);
      registerItemModel(SRPBlocks.EscaBulbCyan);
      registerItemModel(SRPBlocks.EscaBulbPurple);
      registerItemModel(SRPBlocks.EscaBulbBlue);
      registerItemModel(SRPBlocks.EscaBulbBrown);
      registerItemModel(SRPBlocks.EscaBulbGreen);
      registerItemModel(SRPBlocks.EscaBulbRed);
      registerItemModel(SRPBlocks.EscaBulbBlack);
      registerItemModel(SRPBlocks.HarlequinnGrass);
      registerItemModel(SRPBlocks.HarleskinnBlock);
      registerItemModel(SRPBlocks.PolandSkinBlock);
      registerItemModel(SRPBlocks.HairFollicleBlock);
      registerItemModel(SRPBlocks.LocsBlock);
      registerItemModel(SRPBlocks.HirsuteHair);
      registerItemModel(SRPBlocks.TressesHair);
      registerItemModel(SRPBlocks.LipomaMass);
      registerItemModel(SRPBlocks.HarleskinnStairs);
      registerItemModel(SRPBlocks.FleshStairs);
      registerItemModel(SRPBlocks.CookedFleshStairs);
      registerItemModel(SRPBlocks.BruisewoodPlankStairs);
      registerItemModel(SRPBlocks.InfestedSandstoneStairs);
      registerItemModel(SRPBlocks.GothPlanksStairs);
      registerItemModel(SRPBlocks.DeadheadPlankStairs);
      registerItemModel(SRPBlocks.ResidueStairs);
      registerItemModel(SRPBlocks.InfestedPlanksStairs);
      registerItemModel(SRPBlocks.ConsumedPlanksStairs);
      registerItemModel(SRPBlocks.InfestedStoneBricksStairs);
      registerItemModel(SRPBlocks.InfestedPolishedStoneBricksStairs);
      registerItemModel(SRPBlocks.InfestedStoneStairs);
      registerItemModel(SRPBlocks.WheatheredBricksStairs);
      registerItemModel(SRPBlocks.WheatheredCobblestoneStairs);
      registerItemModel(SRPBlocks.FrostWeatheredStoneStairs);
      registerItemModel(SRPItems.infestedbonemeal);
      registerItemModel(SRPItems.BLOODY_IRON_INGOT);
      registerItemModel(SRPItems.BLOODY_ROD);
      registerItemModel(SRPItems.BLOODY_BONE);
      registerItemModel(SRPItems.ada_vermin_drop);
      registerItemModel(SRPItems.ada_viscera_drop);
      registerItemModel(SRPItems.hijacked_drop);
      registerItemModel(SRPItems.hive_scrap);
      registerItemModel(SRPBlocks.HarleskinnFence);
      registerItemModel(SRPBlocks.InfestedFence);
      registerItemModel(SRPBlocks.DeadheadFence);
      registerItemModel(SRPBlocks.GothFence);
      registerItemModel(SRPBlocks.ConsumedFence);
      registerItemModel(SRPBlocks.BrusewoodFence);
      registerItemModel(SRPBlocks.FleshFence);
      registerItemModel(SRPBlocks.CookedFleshFence);
      registerItemModel(SRPBlocks.HarleskinnSlab);
      registerItemModel(SRPBlocks.InfestedCobblestoneSlab);
      registerItemModel(SRPBlocks.InfestedStoneSlab);
      registerItemModel(SRPBlocks.InfestedDirtSlab);
      registerItemModel(SRPBlocks.InfestedPlankSlab);
      registerItemModel(SRPBlocks.ReinforcedHivestoneSlab);
      registerItemModel(SRPBlocks.ParasiticColonyCoreSlab);
      registerItemModel(SRPBlocks.SacOfFleshSlab);
      registerItemModel(SRPBlocks.DeadHeadPlankSlab);
      registerItemModel(SRPBlocks.WeatheredBricksSlab);
      registerItemModel(SRPBlocks.ParasiticCompressedColonyStoneSlab);
      registerItemModel(SRPBlocks.WeatheredCobblestoneSlab);
      registerItemModel(SRPBlocks.FrostWeatheredStoneSlab);
      registerItemModel(SRPBlocks.InfestedStoneBrickSlab);
      registerItemModel(SRPBlocks.InfestedTerracottaSlab);
      registerItemModel(SRPBlocks.PolishedInfestedStoneSlab);
      registerItemModel(SRPBlocks.ResidueBrickSlab);
      registerItemModel(SRPBlocks.InfestedSandstoneSlab);
      registerItemModel(SRPBlocks.GothPlankSlab);
      registerItemModel(SRPBlocks.BruisewoodPlankSlab);
      registerItemModel(SRPBlocks.ConsumedPlankSlab);
      registerItemModel(SRPBlocks.ConsumedPlankSlab);
      registerItemModel(SRPBlocks.PolandSkinSlab);
      registerItemModel(SRPBlocks.LocsBlockSlab);
      registerItemModel(SRPBlocks.FleshSlab);
      registerItemModel(SRPBlocks.CookedFleshSlab);
      registerItemModel(SRPBlocks.InfestedLeaves);
      registerItemModel(SRPBlocks.InfestedLeavesFast);
      registerItemModel(SRPBlocks.InfestedPlanks);
      registerItemModel(SRPBlocks.ParasiteCactus);
      registerItemModel(SRPBlocks.CookedFleshPlanks);
      registerItemModel(SRPBlocks.CookedFlesh);
      registerItemModel(SRPBlocks.FleshPlanks);
      registerItemModel(SRPBlocks.InfestedStoneBricks);
      registerItemModel(SRPBlocks.InfestedTerracotta);
      registerItemModel(SRPBlocks.PolishedInfestedStone);
      registerItemModel(SRPBlocks.ResidueBricks);
      registerItemModel(SRPBlocks.InfestedColumn);
      registerItemModel(SRPBlocks.InfestedSandstone);
      registerItemModel(SRPBlocks.InfestedSandstoneChiseled);
      registerItemModel(SRPBlocks.InfestedSandstoneCut);
      registerItemModel(SRPBlocks.ASHEN_GLASS_PANE);
      registerItemModel(SRPBlocks.SHROUDED_GLASS_PANE);
      registerItemModel(SRPBlocks.HARLEQUINN_GLASS_PANE);
      registerItemModel(SRPBlocks.BLOODY_GLASS_PANE);
      registerItemModel(SRPBlocks.INFESTED_GLASS_PANE);
      registerItemModel(SRPBlocks.SHADE_GLASS_PANE);
      registerItemModel(SRPBlocks.SEPIA_GLASS_PANE);
      registerItemModel(SRPBlocks.MOODY_GLASS_PANE);
      registerItemModel(SRPBlocks.ShroudedGlass);
      registerItemModel(SRPBlocks.HarlequinnGlass);
      registerItemModel(SRPBlocks.BloodyGlass);
      registerItemModel(SRPBlocks.InfestedGlass);
      registerItemModel(SRPBlocks.AshenGlass);
      registerItemModel(SRPBlocks.ShadeGlass);
      registerItemModel(SRPBlocks.SepiaGlass);
      registerItemModel(SRPBlocks.MoodyGlass);
      registerItemModel(SRPBlocks.BiomassBlock);
      registerItemModel(SRPBlocks.TrophyVoidOrb);
      registerItemModel(SRPBlocks.TrophyBoomOrb);
      registerItemModel(SRPBlocks.ResidueBlock);
      registerItemModel(SRPBlocks.ResiduePlants);
      registerItemModel(SRPBlocks.FogNullifier);
      registerItemModel(SRPBlocks.InfuserFurnace);
      SRPMain.proxy.modelReg(Item.func_150898_a(SRPBlocks.InfuserFurnace), 0, "inventory");
      registerItemModel(SRPBlocks.diseasedSponge);
      registerItemModel(SRPBlocks.SemiorganicBlock);
      registerItemModel(SRPBlocks.EpitomeInfestationWarpDiffuser);
      registerItemModel(SRPBlocks.AssimilatedPumpkin);
      registerItemModel(SRPBlocks.AssimilatedJackOLantern);
      registerItemModel(SRPBlocks.AssimilatedSugarCane);
      registerItemModel(SRPBlocks.GothStem);
      registerItemModel(SRPBlocks.GothPlanks);
      registerItemModel(SRPBlocks.BrusewoodPlanks);
      registerItemModel(SRPBlocks.BrusewoodTrapdoor);
      registerItemModel(SRPBlocks.ConsumedPlanks);
      registerItemModel(SRPBlocks.ConsumedWorkbench);
      registerItemModel(SRPBlocks.InfestedWorkbench);
      registerItemModel(SRPBlocks.ConsumedTrapdoor);
      registerItemModel(SRPItems.itemThornshadeBerry);
      registerItemModel(SRPItems.bookofvengeance);
      registerItemModel(
         ((Item)new ItemDoor(SRPBlocks.GothDoor).setRegistryName(SRPBlocks.GothDoor.getRegistryName()))
            .func_77655_b("srparasites." + SRPBlocks.GothDoor.getRegistryName())
      );
      registerItemModel(
         ((Item)new ItemDoor(SRPBlocks.BrusewoodDoor).setRegistryName(SRPBlocks.BrusewoodDoor.getRegistryName()))
            .func_77655_b("srparasites." + SRPBlocks.BrusewoodDoor.getRegistryName())
      );
      registerItemModel(
         ((Item)new ItemDoor(SRPBlocks.ConsumedDoor).setRegistryName(SRPBlocks.ConsumedDoor.getRegistryName()))
            .func_77655_b("srparasites." + SRPBlocks.ConsumedDoor.getRegistryName())
      );
      registerItemModel(SRPBlocks.Alveoli);
      registerItemModel(SRPBlocks.SickAlveoli);
      registerItemModel(SRPBlocks.AlveoliGrowth);
      registerItemModel(SRPBlocks.SolidAlveoliBlock);
      registerItemModel(SRPBlocks.InfestedCobblestone);
      registerItemModel(SRPItems.ALVEOLAR_FLUID);
      registerItemModel(SRPItems.DEADBLOOD_FLUID);
      registerItemModel(SRPItems.VENKROL_BOOTS);
      registerItemModel(SRPItems.MOD_ADAPTED);
      registerItemModel(SRPItems.MOD_ANCIENT);
      registerItemModel(SRPItems.MOD_ASSIMILATED);
      registerItemModel(SRPItems.MOD_CRUDE);
      registerItemModel(SRPItems.MOD_DESMOID);
      registerItemModel(SRPItems.MOD_DETERRENT);
      registerItemModel(SRPItems.MOD_ESCHAR);
      registerItemModel(SRPItems.MOD_FERAL);
      registerItemModel(SRPItems.MOD_HIJACKED);
      registerItemModel(SRPItems.MOD_IDEAL);
      registerItemModel(SRPItems.MOD_INBORN);
      registerItemModel(SRPItems.MOD_NEXUS);
      registerItemModel(SRPItems.MOD_ORIGIN);
      registerItemModel(SRPItems.MOD_PREEMINENT);
      registerItemModel(SRPItems.MOD_PRIMITIVE);
      registerItemModel(SRPItems.MOD_PURE);
      registerItemModel(SRPItems.MOD_RESISTANCE);
      registerItemModel(SRPItems.MOD_ASSIMARA);
      registerItemModel(SRPItems.MOD_DERIVED);
      registerItemModel(SRPItems.MOD_VECTORS);
      registerItemModel(SRPItems.MOD_PHASE);
      registerItemModel(SRPItems.MOD_DISLODGEMENT);
      registerItemModel(SRPItems.acanra_drop);
      registerItemModel(SRPItems.aemana_drop);
      registerItemModel(SRPItems.ahull_drop);
      registerItemModel(SRPItems.anogla_drop);
      registerItemModel(SRPItems.ashyco_drop);
      registerItemModel(SRPItems.abano_drop);
      registerItemModel(SRPItems.aranrac_drop);
      registerItemModel(SRPItems.alum_drop);
      registerItemModel(SRPItems.SRP_FIELD_GUIDE);
      registerItemModel(SRPItems.THORNSHADE_DECANTER);
      registerItemModel(SRPItems.modulebase);
      registerItemModel(SRPItems.tissuespike);
      registerItemModel(SRPItems.organsynth);
      registerItemModel(SRPItems.infected_drop);
      registerItemModel(SRPItems.venkrol_drop);
      registerItemModel(SRPItems.dod_drop);
      registerItemModel(SRPItems.phase_report);
      registerItemModel(SRPItems.DARK_DAYS_ICON);
      registerItemModel(SRPItems.ADAPTED_ICON);
      registerItemModel(SRPItems.PRIMITIVE_ICON);
      registerItemModel(SRPItems.CRUDE_ICON);
      registerItemModel(SRPItems.PURE_ICON);
      registerItemModel(SRPItems.HUNT_SEASON_ICON);
      registerItemModel(SRPItems.GUERILLA_ICON);
      registerItemModel(SRPItems.ECSTASY_ICON);
      registerItemModel(SRPItems.ENEMY_OF_ENEMY_ICON);
      registerItemModel(SRPItems.FOG_NULLIFIER_ICON);
      registerItemModel(SRPItems.SELF_DESTRUCT_ICON);
      registerItemModel(SRPItems.POTION_COLUMBUS_ICON);
      registerItemModel(SRPItems.POTION_STOLAS_ICON);
      registerItemModel(SRPItems.HELLFIRE_CHECMICAL_WARFARE_ICON);
      registerItemModel(SRPItems.COSMIC_STRUCTUREAL_FAILURE_ICON);
      registerItemModel(SRPItems.ROOTS_ICON);
      registerItemModel(SRPItems.itembase);
      registerItemModel(SRPItems.itemEvolve);
      registerItemModel(SRPItems.itemAssimilate);
      registerItemModel(SRPItems.itemDevolve);
      registerItemModel(SRPItems.itemVariant);
      registerItemModel(SRPItems.itemthrow);
      registerItemModel(SRPItems.itemEVClock);
      registerItemModel(SRPItems.itemLevelClock);
      registerItemModel(SRPItems.biomeCompass);
      registerItemModel(SRPItems.colonyCompass);
      registerItemModel(SRPItems.originCompass);
      registerItemModel(SRPItems.DISLODGEMENT_REPORT);
      registerItemModel(SRPItems.BOUGH);
      registerItemModel(SRPItems.GREEK_FIRE);
      registerItemModel(SRPItems.shrimp);
      registerItemModel(Item.func_150898_a(SRPBlocks.INFESTED_POT));
      registerItemModel(Item.func_150898_a(SRPBlocks.CONSUMED_POT));
      registerItemModel(Item.func_150898_a(SRPBlocks.POTTED_ASSIMILATED_BLOSSOM));
      registerItemModel(Item.func_150898_a(SRPBlocks.POTTED_CONSUMED_ASSIMILATED_BLOSSOM));
      registerItemModel(Item.func_150898_a(SRPBlocks.POTTED_CONSUMED_ASSIMILATED_BLOSSOM));
      registerItemModel(Item.func_150898_a(SRPBlocks.ASSIMILATED_BLOSSOM));
      registerItemModel(SRPItems.tendrons);
      registerItemModel(SRPItems.hardbone);
      registerItemModel(SRPItems.infblade);
      registerItemModel(SRPItems.livingcore);
      registerItemModel(SRPItems.vileshell);
      registerItemModel(SRPItems.bone);
      registerItemModel(SRPItems.pearl);
      registerItemModel(SRPItems.falseapple);
      registerItemModel(SRPItems.semiorganicingot);
      registerItemModel(SRPItems.fishlin);
      registerItemModel(SRPItems.alveoligrowth);
      registerItemModel(SRPItems.VECTOR_MAP);
      registerItemModel(SRPItems.itemlurecomponent1);
      registerItemModel(SRPItems.itemlurecomponent2);
      registerItemModel(SRPItems.itemlurecomponent3);
      registerItemModel(SRPItems.itemlurecomponent4);
      registerItemModel(SRPItems.itemlurecomponent5);
      registerItemModel(SRPItems.itemlurecomponent6);
      registerItemModel(SRPItems.itemlurecomponent7);
      registerItemModel(SRPItems.itemlurecomponent8);
      registerItemModel(SRPItems.itemlurecomponent9);
      registerItemModel(SRPItems.itemlurecomponent10);
      registerItemModel(SRPItems.itemlurecomponent10);
      registerItemModel(SRPItems.itemlurecomponent10);
      registerItemModel(SRPItems.itemlurecomponent10);
      registerItemModel(SRPItems.weapon_scythe);
      registerItemModel(SRPItems.weapon_scytheSentient);
      registerItemModel(SRPItems.weapon_axe);
      registerItemModel(SRPItems.weapon_axeSentient);
      registerItemModel(SRPItems.weapon_sword);
      registerItemModel(SRPItems.weapon_swordSentient);
      registerItemModel(SRPItems.weapon_cleaver);
      registerItemModel(SRPItems.weapon_cleaverSentient);
      registerItemModel(SRPItems.weapon_bow);
      registerItemModel(SRPItems.weapon_bow_sentient);
      registerItemModel(SRPItems.weapon_maul);
      registerItemModel(SRPItems.weapon_maulSentient);
      registerItemModel(SRPItems.weapon_lance);
      registerItemModel(SRPItems.weapon_lanceSentient);
      registerItemModel(SRPItems.armor_helmet);
      registerItemModel(SRPItems.armor_chest);
      registerItemModel(SRPItems.armor_pants);
      registerItemModel(SRPItems.armor_boots);
      registerItemModel(SRPItems.armor_helmetSentient);
      registerItemModel(SRPItems.armor_chestSentient);
      registerItemModel(SRPItems.armor_pantsSentient);
      registerItemModel(SRPItems.armor_bootsSentient);
      registerItemModel(SRPItems.hijacked_iron_boots);
      registerItemModel(SRPItems.hijacked_iron_helmet);
      registerItemModel(SRPItems.hijacked_iron_chestpiece);
      registerItemModel(SRPItems.hijacked_iron_leggings);
      registerItemModel(SRPItems.hijacked_iron_hoe);
      registerItemModel(SRPItems.hijacked_iron_axe);
      registerItemModel(SRPItems.hijacked_iron_shovel);
      registerItemModel(SRPItems.hijacked_iron_sword);
      registerItemModel(SRPItems.hijacked_iron_pickaxe);
      registerItemModel(SRPItems.disc1);
      registerItemModel(SRPItems.disc2);
      registerItemModel(SRPItems.disc3);
      registerItemModel(SRPBlocks.InfestedStain);
      registerItemModel(SRPBlocks.InfestedRubble);
      registerItemModel(SRPBlocks.InfestedTrunk);
      registerItemModel(SRPBlocks.InfestRemain);
      registerItemModel(SRPBlocks.BiomeHeart);
      registerItemModel(SRPBlocks.ColonyHeart);
      registerItemModel(SRPBlocks.ColonyOutpost);
      registerItemModel(SRPBlocks.BiomePurifier);
      registerItemModel(SRPBlocks.SRPWeb);
      registerItemModel(SRPItems.signstatus);
      registerItemModel(SRPBlocks.ParasiteStructure);
      registerItemModel(SRPBlocks.buglin);
      registerItemModel(SRPBlocks.ParasiteThin);
      registerItemModel(SRPBlocks.ParasiteRubbleSlabHalf);
      registerItemModel(SRPBlocks.ParasiteStainSlabHalf);
      registerItemModel(SRPBlocks.ParasiteVine);
      registerItemModel(SRPBlocks.ParasiteFog);
      registerItemModel(SRPBlocks.ParasiteMouth);
      registerItemModel(SRPBlocks.ParasiteCanisterActive);
      registerItemModel(SRPBlocks.ParasiteRubbleFleshWall);
      registerItemModel(SRPBlocks.dodN);
      registerItemModel(SRPBlocks.InfestedSand);
      registerItemModel(SRPBlocks.BloodyIce);
      registerItemModel(SRPBlocks.gothShroom);
      registerItemModel(SRPBlocks.NODE_LAMP);
      registerItemModel(SRPBlocks.RELAY_CONTROLLER);
      registerItemModel(SRPBlocks.PARASITE_BARRIER);
      registerItemModel(SRPBlocks.DermoidCyst);
      registerItemModel(SRPBlocks.RelayBase);
      registerItemModel(SRPBlocks.RelayMiddle);
      registerItemModel(SRPBlocks.RelayRoof);
      registerItemModel(SRPBlocks.INFESTED_FURNACE);
      registerItemModel(SRPBlocks.ParasitePlankDeadheadWall);
      registerItemModel(SRPBlocks.ParasiteRubbleWeathbWall);
      registerItemModel(SRPBlocks.ParasiteRubbleDenseColonyWall);
      registerItemModel(SRPBlocks.ParasiteRubbleWeathfsWall);
      registerItemModel(SRPBlocks.ParasiteRubbleDenseBiomeWall);
      registerItemModel(SRPBlocks.ParasiteRubbleWeathbcWall);
      registerItemModel(SRPBlocks.ParasiteRubbleBricksWall);
      registerItemModel(SRPBlocks.ParasiteCanisterBagWall);
      registerItemModel(SRPBlocks.InfestedRubbleWall);
      registerItemModel(SRPBlocks.InfestedStainWall);
      registerItemModel(SRPBlocks.ParasiteStainFleshWall);
      registerItemModel(SRPBlocks.ParasiteRubbleMetalWall);
      registerItemModel(SRPBlocks.InfestedPlankWall);
      registerItemModel(SRPBlocks.ResidueBrickWall);
      registerItemModel(SRPBlocks.BruisewoodPlankWall);
      registerItemModel(SRPBlocks.PolishedInfestedStoneWall);
      registerItemModel(SRPBlocks.InfestedStoneBrickWall);
      registerItemModel(SRPBlocks.GothPlankWall);
      registerItemModel(SRPBlocks.InfestedSandstoneWall);
      registerItemModel(SRPBlocks.ConsumedPlankWall);
      registerBlockVariants();
      RegisterCustomMashesAndStates();
      SRPMain.logger.info("Models registered");
   }

   private static void registerItemModel(Item item) {
      if (item == null) {
         SRPMain.logger.error("registerItemModel(Item): item is NULL");
      } else if (item.getRegistryName() == null) {
         SRPMain.logger.error("registerItemModel(Item): item has NULL registry name: {} ({})", item, item.getClass().getName());
      } else {
         ModelResourceLocation location = new ModelResourceLocation(item.getRegistryName(), "inventory");
         ModelLoader.setCustomModelResourceLocation(item, 0, location);
      }
   }

   private static void registerItemModel(Block block) {
      if (block == null) {
         SRPMain.logger.error("registerItemModel(Block): block is NULL");
      } else {
         Item item = Item.func_150898_a(block);
         if (item != null && item != Items.field_190931_a) {
            registerItemModel(item);
         } else {
            SRPMain.logger.error("registerItemModel(Block): no ItemBlock for block {} reg={}", block, block.getRegistryName());
         }
      }
   }

   private static void registerItemModel(Block block, int meta, String fileName) {
      if (block == null) {
         SRPMain.logger.error("registerItemModel(Block,meta,file): block is NULL file={}", fileName);
      } else {
         Item item = Item.func_150898_a(block);
         if (item != null && item != Items.field_190931_a) {
            ModelResourceLocation location = new ModelResourceLocation(new ResourceLocation("srparasites", fileName), "inventory");
            ModelLoader.setCustomModelResourceLocation(item, meta, location);
         } else {
            SRPMain.logger
               .error("registerItemModel(Block,meta,file): no ItemBlock for block {} reg={} meta={} file={}", block, block.getRegistryName(), meta, fileName);
         }
      }
   }

   private static void registerBlockVariants() {
      for (int i = 0; i < BlockWebBase.EnumType.values().length; i++) {
         registerItemModel(SRPBlocks.SRPWeb, i, "web_" + BlockWebBase.EnumType.values()[i].func_176610_l());
      }

      for (int i = 0; i < BlockGore.EnumType.values().length; i++) {
         registerItemModel(SRPBlocks.goreSim, i, "gore_sim_" + BlockGore.EnumType.values()[i].func_176610_l());
      }

      for (int i = 0; i < BlockGore.EnumType.values().length; i++) {
         registerItemModel(SRPBlocks.gorePri, i, "gore_pri_" + BlockGore.EnumType.values()[i].func_176610_l());
      }

      for (int i = 0; i < BlockGore.EnumType.values().length; i++) {
         registerItemModel(SRPBlocks.goreAda, i, "gore_ada_" + BlockGore.EnumType.values()[i].func_176610_l());
      }

      for (int i = 0; i < BlockGore.EnumType.values().length; i++) {
         registerItemModel(SRPBlocks.gorePur, i, "gore_pur_" + BlockGore.EnumType.values()[i].func_176610_l());
      }

      for (int i = 0; i < BlockGore.EnumType.values().length; i++) {
         registerItemModel(SRPBlocks.goreFer, i, "gore_fer_" + BlockGore.EnumType.values()[i].func_176610_l());
      }

      for (int i = 0; i < BlockGore.EnumType.values().length; i++) {
         registerItemModel(SRPBlocks.goreMar, i, "gore_mar_" + BlockGore.EnumType.values()[i].func_176610_l());
      }

      for (int i = 0; i < BlockEvolutionLure.EnumType.values().length; i++) {
         registerItemModel(SRPBlocks.evolutionLure, i, "lure_" + BlockEvolutionLure.EnumType.values()[i].func_176610_l());
      }

      for (int i = 0; i < BlockInfestedBush.EnumType.values().length; i++) {
         registerItemModel(SRPBlocks.InfestedBush, i, "infestedbush_" + BlockInfestedBush.EnumType.values()[i].func_176610_l());
      }

      for (int i = 0; i < BlockParasiteCanister.EnumType.values().length; i++) {
         registerItemModel(SRPBlocks.ParasiteCanister, i, "canister_" + BlockParasiteCanister.EnumType.values()[i].func_176610_l());
      }

      for (int i = 0; i < BlockParasiteRubble.EnumType.values().length; i++) {
         registerItemModel(SRPBlocks.ParasiteRubble, i, "rubble_" + BlockParasiteRubble.EnumType.values()[i].func_176610_l());
      }

      for (int i = 0; i < BlockParasiteStain.EnumType.values().length; i++) {
         registerItemModel(SRPBlocks.ParasiteStain, i, "stain_" + BlockParasiteStain.EnumType.values()[i].func_176610_l());
      }

      for (int i = 0; i < BlockParasiteTrunk.EnumType.values().length; i++) {
         registerItemModel(SRPBlocks.ParasiteTrunk, i, "trunk_" + BlockParasiteTrunk.EnumType.values()[i].func_176610_l());
      }

      for (int i = 0; i < BlockParasitePlank.EnumType.values().length; i++) {
         registerItemModel(SRPBlocks.ParasitePlank, i, "plank_" + BlockParasitePlank.EnumType.values()[i].func_176610_l());
      }

      for (int i = 0; i < BlockParasiteBush.EnumType.values().length; i++) {
         registerItemModel(SRPBlocks.ParasiteBush, i, "parasitebush_" + BlockParasiteBush.EnumType.values()[i].func_176610_l());
      }

      for (int i = 0; i < BlockParasiteSapling.EnumType.values().length; i++) {
         registerItemModel(SRPBlocks.ParasiteSapling, i, "sapling_" + BlockParasiteSapling.EnumType.values()[i].func_176610_l());
      }

      for (int i = 0; i < BlockParasiteRubbleDense.EnumType.values().length; i++) {
         registerItemModel(SRPBlocks.ParasiteRubbleDense, i, "rubbledense_" + BlockParasiteRubbleDense.EnumType.values()[i].func_176610_l());
      }

      for (int i = 0; i < BlockInfestedOre.EnumType.values().length; i++) {
         registerItemModel(SRPBlocks.InfestedOre, i, "infestedore_" + BlockInfestedOre.EnumType.values()[i].func_176610_l());
      }

      for (int i = 0; i < BlockParasiteLoot.EnumType.values().length; i++) {
         registerItemModel(SRPBlocks.ParasiteLoot, i, "loot_" + BlockParasiteLoot.EnumType.values()[i].func_176610_l());
      }

      for (int i = 0; i < BlockSlabRubble.EnumType.values().length; i++) {
         registerItemModel(SRPBlocks.ParasiteRubbleSlabHalf, i, "slab_" + BlockSlabRubble.EnumType.values()[i].func_176610_l());
      }

      for (int i = 0; i < BlockSlabStain.EnumType.values().length; i++) {
         registerItemModel(SRPBlocks.ParasiteStainSlabHalf, i, "slab_" + BlockSlabStain.EnumType.values()[i].func_176610_l());
      }

      registerItemModel(SRPBlocks.BloodyIce, 0, "bloodyice");
   }

   public static void RegisterCustomMashesAndStates() {
      ModelLoader.setCustomMeshDefinition(Item.func_150898_a(SRPBlocks.DeadBlood), new ItemMeshDefinition() {
         public ModelResourceLocation func_178113_a(ItemStack stack) {
            return new ModelResourceLocation("srparasites:deadblood", "fluid");
         }
      });
      ModelLoader.setCustomStateMapper(SRPBlocks.DeadBlood, new StateMapperBase() {
         protected ModelResourceLocation func_178132_a(IBlockState state) {
            return new ModelResourceLocation("srparasites:deadblood", "fluid");
         }
      });
   }

   @EventBusSubscriber(modid = "srparasites", value = Side.CLIENT)
   public static final class SRPClientParticles {
      public static TextureAtlasSprite INFESTED_LEAF_SPRITE;
      public static final TextureAtlasSprite[] INFESTED_LEAF = new TextureAtlasSprite[4];

      @SubscribeEvent
      public static void onTextureStitch(Pre e) {
         TextureMap map = e.getMap();
         INFESTED_LEAF_SPRITE = map.func_174942_a(new ResourceLocation("srparasites:particle/infested_leaves"));
         INFESTED_LEAF[0] = INFESTED_LEAF_SPRITE;
         INFESTED_LEAF[1] = map.func_174942_a(new ResourceLocation("srparasites:particle/infested_leaves2"));
         INFESTED_LEAF[2] = map.func_174942_a(new ResourceLocation("srparasites:particle/infested_leaves3"));
         INFESTED_LEAF[3] = map.func_174942_a(new ResourceLocation("srparasites:particle/infested_leaves4"));
      }

      public static TextureAtlasSprite randomInfestedLeaf(Random rand) {
         return INFESTED_LEAF[rand.nextInt(INFESTED_LEAF.length)];
      }
   }
}
