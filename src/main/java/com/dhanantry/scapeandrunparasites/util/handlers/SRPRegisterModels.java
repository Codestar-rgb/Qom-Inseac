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
public class SRPRegisterModels {
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        SRPRegisterModels.registerItemModels();
    }

    private static void registerItemModels() {
        for (Item item : SRPItems.SRP_ITEMS) {
            if (!(item instanceof IModelSRP)) continue;
            ((IModelSRP)item).registerModels();
        }
        for (Block block : SRPBlocks.SRP_BLOCKS) {
            if (!(block instanceof IModelSRP)) continue;
            ((IModelSRP)block).registerModels();
        }
        SRPRegisterModels.registerItemModel(SRPBlocks.InfestPurify);
        SRPRegisterModels.registerItemModel(SRPBlocks.EscaBulb);
        SRPRegisterModels.registerItemModel(SRPBlocks.EscaBulbWhite);
        SRPRegisterModels.registerItemModel(SRPBlocks.EscaBulbOrange);
        SRPRegisterModels.registerItemModel(SRPBlocks.EscaBulbMagenta);
        SRPRegisterModels.registerItemModel(SRPBlocks.EscaBulbLightBlue);
        SRPRegisterModels.registerItemModel(SRPBlocks.EscaBulbYellow);
        SRPRegisterModels.registerItemModel(SRPBlocks.EscaBulbLime);
        SRPRegisterModels.registerItemModel(SRPBlocks.EscaBulbPink);
        SRPRegisterModels.registerItemModel(SRPBlocks.EscaBulbGray);
        SRPRegisterModels.registerItemModel(SRPBlocks.EscaBulbLightGray);
        SRPRegisterModels.registerItemModel(SRPBlocks.EscaBulbCyan);
        SRPRegisterModels.registerItemModel(SRPBlocks.EscaBulbPurple);
        SRPRegisterModels.registerItemModel(SRPBlocks.EscaBulbBlue);
        SRPRegisterModels.registerItemModel(SRPBlocks.EscaBulbBrown);
        SRPRegisterModels.registerItemModel(SRPBlocks.EscaBulbGreen);
        SRPRegisterModels.registerItemModel(SRPBlocks.EscaBulbRed);
        SRPRegisterModels.registerItemModel(SRPBlocks.EscaBulbBlack);
        SRPRegisterModels.registerItemModel(SRPBlocks.HarlequinnGrass);
        SRPRegisterModels.registerItemModel(SRPBlocks.HarleskinnBlock);
        SRPRegisterModels.registerItemModel(SRPBlocks.PolandSkinBlock);
        SRPRegisterModels.registerItemModel(SRPBlocks.HairFollicleBlock);
        SRPRegisterModels.registerItemModel(SRPBlocks.LocsBlock);
        SRPRegisterModels.registerItemModel(SRPBlocks.HirsuteHair);
        SRPRegisterModels.registerItemModel(SRPBlocks.TressesHair);
        SRPRegisterModels.registerItemModel(SRPBlocks.LipomaMass);
        SRPRegisterModels.registerItemModel(SRPBlocks.HarleskinnStairs);
        SRPRegisterModels.registerItemModel(SRPBlocks.FleshStairs);
        SRPRegisterModels.registerItemModel(SRPBlocks.CookedFleshStairs);
        SRPRegisterModels.registerItemModel(SRPBlocks.BruisewoodPlankStairs);
        SRPRegisterModels.registerItemModel(SRPBlocks.InfestedSandstoneStairs);
        SRPRegisterModels.registerItemModel(SRPBlocks.GothPlanksStairs);
        SRPRegisterModels.registerItemModel(SRPBlocks.DeadheadPlankStairs);
        SRPRegisterModels.registerItemModel(SRPBlocks.ResidueStairs);
        SRPRegisterModels.registerItemModel(SRPBlocks.InfestedPlanksStairs);
        SRPRegisterModels.registerItemModel(SRPBlocks.ConsumedPlanksStairs);
        SRPRegisterModels.registerItemModel(SRPBlocks.InfestedStoneBricksStairs);
        SRPRegisterModels.registerItemModel(SRPBlocks.InfestedPolishedStoneBricksStairs);
        SRPRegisterModels.registerItemModel(SRPBlocks.InfestedStoneStairs);
        SRPRegisterModels.registerItemModel(SRPBlocks.WheatheredBricksStairs);
        SRPRegisterModels.registerItemModel(SRPBlocks.WheatheredCobblestoneStairs);
        SRPRegisterModels.registerItemModel(SRPBlocks.FrostWeatheredStoneStairs);
        SRPRegisterModels.registerItemModel(SRPItems.infestedbonemeal);
        SRPRegisterModels.registerItemModel(SRPItems.BLOODY_IRON_INGOT);
        SRPRegisterModels.registerItemModel(SRPItems.BLOODY_ROD);
        SRPRegisterModels.registerItemModel(SRPItems.BLOODY_BONE);
        SRPRegisterModels.registerItemModel(SRPItems.ada_vermin_drop);
        SRPRegisterModels.registerItemModel(SRPItems.ada_viscera_drop);
        SRPRegisterModels.registerItemModel(SRPItems.hijacked_drop);
        SRPRegisterModels.registerItemModel(SRPItems.hive_scrap);
        SRPRegisterModels.registerItemModel(SRPBlocks.HarleskinnFence);
        SRPRegisterModels.registerItemModel(SRPBlocks.InfestedFence);
        SRPRegisterModels.registerItemModel(SRPBlocks.DeadheadFence);
        SRPRegisterModels.registerItemModel(SRPBlocks.GothFence);
        SRPRegisterModels.registerItemModel(SRPBlocks.ConsumedFence);
        SRPRegisterModels.registerItemModel(SRPBlocks.BrusewoodFence);
        SRPRegisterModels.registerItemModel(SRPBlocks.FleshFence);
        SRPRegisterModels.registerItemModel(SRPBlocks.CookedFleshFence);
        SRPRegisterModels.registerItemModel(SRPBlocks.HarleskinnSlab);
        SRPRegisterModels.registerItemModel(SRPBlocks.InfestedCobblestoneSlab);
        SRPRegisterModels.registerItemModel(SRPBlocks.InfestedStoneSlab);
        SRPRegisterModels.registerItemModel(SRPBlocks.InfestedDirtSlab);
        SRPRegisterModels.registerItemModel(SRPBlocks.InfestedPlankSlab);
        SRPRegisterModels.registerItemModel(SRPBlocks.ReinforcedHivestoneSlab);
        SRPRegisterModels.registerItemModel(SRPBlocks.ParasiticColonyCoreSlab);
        SRPRegisterModels.registerItemModel(SRPBlocks.SacOfFleshSlab);
        SRPRegisterModels.registerItemModel(SRPBlocks.DeadHeadPlankSlab);
        SRPRegisterModels.registerItemModel(SRPBlocks.WeatheredBricksSlab);
        SRPRegisterModels.registerItemModel(SRPBlocks.ParasiticCompressedColonyStoneSlab);
        SRPRegisterModels.registerItemModel(SRPBlocks.WeatheredCobblestoneSlab);
        SRPRegisterModels.registerItemModel(SRPBlocks.FrostWeatheredStoneSlab);
        SRPRegisterModels.registerItemModel(SRPBlocks.InfestedStoneBrickSlab);
        SRPRegisterModels.registerItemModel(SRPBlocks.InfestedTerracottaSlab);
        SRPRegisterModels.registerItemModel(SRPBlocks.PolishedInfestedStoneSlab);
        SRPRegisterModels.registerItemModel(SRPBlocks.ResidueBrickSlab);
        SRPRegisterModels.registerItemModel(SRPBlocks.InfestedSandstoneSlab);
        SRPRegisterModels.registerItemModel(SRPBlocks.GothPlankSlab);
        SRPRegisterModels.registerItemModel(SRPBlocks.BruisewoodPlankSlab);
        SRPRegisterModels.registerItemModel(SRPBlocks.ConsumedPlankSlab);
        SRPRegisterModels.registerItemModel(SRPBlocks.ConsumedPlankSlab);
        SRPRegisterModels.registerItemModel(SRPBlocks.PolandSkinSlab);
        SRPRegisterModels.registerItemModel(SRPBlocks.LocsBlockSlab);
        SRPRegisterModels.registerItemModel(SRPBlocks.FleshSlab);
        SRPRegisterModels.registerItemModel(SRPBlocks.CookedFleshSlab);
        SRPRegisterModels.registerItemModel(SRPBlocks.InfestedLeaves);
        SRPRegisterModels.registerItemModel(SRPBlocks.InfestedLeavesFast);
        SRPRegisterModels.registerItemModel(SRPBlocks.InfestedPlanks);
        SRPRegisterModels.registerItemModel(SRPBlocks.ParasiteCactus);
        SRPRegisterModels.registerItemModel(SRPBlocks.CookedFleshPlanks);
        SRPRegisterModels.registerItemModel(SRPBlocks.CookedFlesh);
        SRPRegisterModels.registerItemModel(SRPBlocks.FleshPlanks);
        SRPRegisterModels.registerItemModel(SRPBlocks.InfestedStoneBricks);
        SRPRegisterModels.registerItemModel(SRPBlocks.InfestedTerracotta);
        SRPRegisterModels.registerItemModel(SRPBlocks.PolishedInfestedStone);
        SRPRegisterModels.registerItemModel(SRPBlocks.ResidueBricks);
        SRPRegisterModels.registerItemModel(SRPBlocks.InfestedColumn);
        SRPRegisterModels.registerItemModel(SRPBlocks.InfestedSandstone);
        SRPRegisterModels.registerItemModel(SRPBlocks.InfestedSandstoneChiseled);
        SRPRegisterModels.registerItemModel(SRPBlocks.InfestedSandstoneCut);
        SRPRegisterModels.registerItemModel((Block)SRPBlocks.ASHEN_GLASS_PANE);
        SRPRegisterModels.registerItemModel((Block)SRPBlocks.SHROUDED_GLASS_PANE);
        SRPRegisterModels.registerItemModel((Block)SRPBlocks.HARLEQUINN_GLASS_PANE);
        SRPRegisterModels.registerItemModel((Block)SRPBlocks.BLOODY_GLASS_PANE);
        SRPRegisterModels.registerItemModel((Block)SRPBlocks.INFESTED_GLASS_PANE);
        SRPRegisterModels.registerItemModel((Block)SRPBlocks.SHADE_GLASS_PANE);
        SRPRegisterModels.registerItemModel((Block)SRPBlocks.SEPIA_GLASS_PANE);
        SRPRegisterModels.registerItemModel((Block)SRPBlocks.MOODY_GLASS_PANE);
        SRPRegisterModels.registerItemModel(SRPBlocks.ShroudedGlass);
        SRPRegisterModels.registerItemModel(SRPBlocks.HarlequinnGlass);
        SRPRegisterModels.registerItemModel(SRPBlocks.BloodyGlass);
        SRPRegisterModels.registerItemModel(SRPBlocks.InfestedGlass);
        SRPRegisterModels.registerItemModel(SRPBlocks.AshenGlass);
        SRPRegisterModels.registerItemModel(SRPBlocks.ShadeGlass);
        SRPRegisterModels.registerItemModel(SRPBlocks.SepiaGlass);
        SRPRegisterModels.registerItemModel(SRPBlocks.MoodyGlass);
        SRPRegisterModels.registerItemModel(SRPBlocks.BiomassBlock);
        SRPRegisterModels.registerItemModel(SRPBlocks.TrophyVoidOrb);
        SRPRegisterModels.registerItemModel(SRPBlocks.TrophyBoomOrb);
        SRPRegisterModels.registerItemModel(SRPBlocks.ResidueBlock);
        SRPRegisterModels.registerItemModel(SRPBlocks.ResiduePlants);
        SRPRegisterModels.registerItemModel(SRPBlocks.FogNullifier);
        SRPRegisterModels.registerItemModel(SRPBlocks.InfuserFurnace);
        SRPMain.proxy.modelReg(Item.func_150898_a((Block)SRPBlocks.InfuserFurnace), 0, "inventory");
        SRPRegisterModels.registerItemModel(SRPBlocks.diseasedSponge);
        SRPRegisterModels.registerItemModel(SRPBlocks.SemiorganicBlock);
        SRPRegisterModels.registerItemModel(SRPBlocks.AssimilatedPumpkin);
        SRPRegisterModels.registerItemModel(SRPBlocks.AssimilatedJackOLantern);
        SRPRegisterModels.registerItemModel(SRPBlocks.AssimilatedSugarCane);
        SRPRegisterModels.registerItemModel(SRPBlocks.GothStem);
        SRPRegisterModels.registerItemModel(SRPBlocks.GothPlanks);
        SRPRegisterModels.registerItemModel(SRPBlocks.BrusewoodPlanks);
        SRPRegisterModels.registerItemModel(SRPBlocks.BrusewoodTrapdoor);
        SRPRegisterModels.registerItemModel(SRPBlocks.ConsumedPlanks);
        SRPRegisterModels.registerItemModel(SRPBlocks.ConsumedWorkbench);
        SRPRegisterModels.registerItemModel(SRPBlocks.InfestedWorkbench);
        SRPRegisterModels.registerItemModel(SRPBlocks.ConsumedTrapdoor);
        SRPRegisterModels.registerItemModel(SRPItems.itemThornshadeBerry);
        SRPRegisterModels.registerItemModel(SRPItems.bookofvengeance);
        SRPRegisterModels.registerItemModel(((Item)new ItemDoor((Block)SRPBlocks.GothDoor).setRegistryName(SRPBlocks.GothDoor.getRegistryName())).func_77655_b("srparasites." + SRPBlocks.GothDoor.getRegistryName()));
        SRPRegisterModels.registerItemModel(((Item)new ItemDoor((Block)SRPBlocks.BrusewoodDoor).setRegistryName(SRPBlocks.BrusewoodDoor.getRegistryName())).func_77655_b("srparasites." + SRPBlocks.BrusewoodDoor.getRegistryName()));
        SRPRegisterModels.registerItemModel(((Item)new ItemDoor((Block)SRPBlocks.ConsumedDoor).setRegistryName(SRPBlocks.ConsumedDoor.getRegistryName())).func_77655_b("srparasites." + SRPBlocks.ConsumedDoor.getRegistryName()));
        SRPRegisterModels.registerItemModel(SRPBlocks.Alveoli);
        SRPRegisterModels.registerItemModel(SRPBlocks.SickAlveoli);
        SRPRegisterModels.registerItemModel(SRPBlocks.AlveoliGrowth);
        SRPRegisterModels.registerItemModel(SRPBlocks.SolidAlveoliBlock);
        SRPRegisterModels.registerItemModel(SRPBlocks.InfestedCobblestone);
        SRPRegisterModels.registerItemModel(SRPItems.ALVEOLAR_FLUID);
        SRPRegisterModels.registerItemModel(SRPItems.DEADBLOOD_FLUID);
        SRPRegisterModels.registerItemModel(SRPItems.VENKROL_BOOTS);
        SRPRegisterModels.registerItemModel(SRPItems.MOD_ADAPTED);
        SRPRegisterModels.registerItemModel(SRPItems.MOD_ANCIENT);
        SRPRegisterModels.registerItemModel(SRPItems.MOD_ASSIMILATED);
        SRPRegisterModels.registerItemModel(SRPItems.MOD_CRUDE);
        SRPRegisterModels.registerItemModel(SRPItems.MOD_DESMOID);
        SRPRegisterModels.registerItemModel(SRPItems.MOD_DETERRENT);
        SRPRegisterModels.registerItemModel(SRPItems.MOD_ESCHAR);
        SRPRegisterModels.registerItemModel(SRPItems.MOD_FERAL);
        SRPRegisterModels.registerItemModel(SRPItems.MOD_HIJACKED);
        SRPRegisterModels.registerItemModel(SRPItems.MOD_IDEAL);
        SRPRegisterModels.registerItemModel(SRPItems.MOD_INBORN);
        SRPRegisterModels.registerItemModel(SRPItems.MOD_NEXUS);
        SRPRegisterModels.registerItemModel(SRPItems.MOD_ORIGIN);
        SRPRegisterModels.registerItemModel(SRPItems.MOD_PREEMINENT);
        SRPRegisterModels.registerItemModel(SRPItems.MOD_PRIMITIVE);
        SRPRegisterModels.registerItemModel(SRPItems.MOD_PURE);
        SRPRegisterModels.registerItemModel(SRPItems.MOD_RESISTANCE);
        SRPRegisterModels.registerItemModel(SRPItems.MOD_ASSIMARA);
        SRPRegisterModels.registerItemModel(SRPItems.MOD_DERIVED);
        SRPRegisterModels.registerItemModel(SRPItems.MOD_VECTORS);
        SRPRegisterModels.registerItemModel(SRPItems.MOD_PHASE);
        SRPRegisterModels.registerItemModel(SRPItems.MOD_DISLODGEMENT);
        SRPRegisterModels.registerItemModel(SRPItems.acanra_drop);
        SRPRegisterModels.registerItemModel(SRPItems.aemana_drop);
        SRPRegisterModels.registerItemModel(SRPItems.ahull_drop);
        SRPRegisterModels.registerItemModel(SRPItems.anogla_drop);
        SRPRegisterModels.registerItemModel(SRPItems.ashyco_drop);
        SRPRegisterModels.registerItemModel(SRPItems.abano_drop);
        SRPRegisterModels.registerItemModel(SRPItems.aranrac_drop);
        SRPRegisterModels.registerItemModel(SRPItems.alum_drop);
        SRPRegisterModels.registerItemModel(SRPItems.SRP_FIELD_GUIDE);
        SRPRegisterModels.registerItemModel(SRPItems.THORNSHADE_DECANTER);
        SRPRegisterModels.registerItemModel(SRPItems.modulebase);
        SRPRegisterModels.registerItemModel(SRPItems.tissuespike);
        SRPRegisterModels.registerItemModel(SRPItems.organsynth);
        SRPRegisterModels.registerItemModel(SRPItems.infected_drop);
        SRPRegisterModels.registerItemModel(SRPItems.venkrol_drop);
        SRPRegisterModels.registerItemModel(SRPItems.dod_drop);
        SRPRegisterModels.registerItemModel(SRPItems.phase_report);
        SRPRegisterModels.registerItemModel(SRPItems.itembase);
        SRPRegisterModels.registerItemModel(SRPItems.itemEvolve);
        SRPRegisterModels.registerItemModel(SRPItems.itemAssimilate);
        SRPRegisterModels.registerItemModel(SRPItems.itemDevolve);
        SRPRegisterModels.registerItemModel(SRPItems.itemVariant);
        SRPRegisterModels.registerItemModel(SRPItems.itemthrow);
        SRPRegisterModels.registerItemModel(SRPItems.itemEVClock);
        SRPRegisterModels.registerItemModel(SRPItems.itemLevelClock);
        SRPRegisterModels.registerItemModel(SRPItems.biomeCompass);
        SRPRegisterModels.registerItemModel(SRPItems.colonyCompass);
        SRPRegisterModels.registerItemModel(SRPItems.originCompass);
        SRPRegisterModels.registerItemModel(SRPItems.DISLODGEMENT_REPORT);
        SRPRegisterModels.registerItemModel(SRPItems.BOUGH);
        SRPRegisterModels.registerItemModel(SRPItems.GREEK_FIRE);
        SRPRegisterModels.registerItemModel(SRPItems.shrimp);
        SRPRegisterModels.registerItemModel(Item.func_150898_a((Block)SRPBlocks.INFESTED_POT));
        SRPRegisterModels.registerItemModel(Item.func_150898_a((Block)SRPBlocks.CONSUMED_POT));
        SRPRegisterModels.registerItemModel(Item.func_150898_a((Block)SRPBlocks.POTTED_ASSIMILATED_BLOSSOM));
        SRPRegisterModels.registerItemModel(Item.func_150898_a((Block)SRPBlocks.POTTED_CONSUMED_ASSIMILATED_BLOSSOM));
        SRPRegisterModels.registerItemModel(Item.func_150898_a((Block)SRPBlocks.POTTED_CONSUMED_ASSIMILATED_BLOSSOM));
        SRPRegisterModels.registerItemModel(Item.func_150898_a((Block)SRPBlocks.ASSIMILATED_BLOSSOM));
        SRPRegisterModels.registerItemModel(SRPItems.tendrons);
        SRPRegisterModels.registerItemModel(SRPItems.hardbone);
        SRPRegisterModels.registerItemModel(SRPItems.infblade);
        SRPRegisterModels.registerItemModel(SRPItems.livingcore);
        SRPRegisterModels.registerItemModel(SRPItems.vileshell);
        SRPRegisterModels.registerItemModel(SRPItems.bone);
        SRPRegisterModels.registerItemModel(SRPItems.pearl);
        SRPRegisterModels.registerItemModel(SRPItems.falseapple);
        SRPRegisterModels.registerItemModel(SRPItems.semiorganicingot);
        SRPRegisterModels.registerItemModel(SRPItems.fishlin);
        SRPRegisterModels.registerItemModel(SRPItems.alveoligrowth);
        SRPRegisterModels.registerItemModel(SRPItems.VECTOR_MAP);
        SRPRegisterModels.registerItemModel(SRPItems.itemlurecomponent1);
        SRPRegisterModels.registerItemModel(SRPItems.itemlurecomponent2);
        SRPRegisterModels.registerItemModel(SRPItems.itemlurecomponent3);
        SRPRegisterModels.registerItemModel(SRPItems.itemlurecomponent4);
        SRPRegisterModels.registerItemModel(SRPItems.itemlurecomponent5);
        SRPRegisterModels.registerItemModel(SRPItems.itemlurecomponent6);
        SRPRegisterModels.registerItemModel(SRPItems.itemlurecomponent7);
        SRPRegisterModels.registerItemModel(SRPItems.itemlurecomponent8);
        SRPRegisterModels.registerItemModel(SRPItems.itemlurecomponent9);
        SRPRegisterModels.registerItemModel(SRPItems.itemlurecomponent10);
        SRPRegisterModels.registerItemModel(SRPItems.itemlurecomponent10);
        SRPRegisterModels.registerItemModel(SRPItems.itemlurecomponent10);
        SRPRegisterModels.registerItemModel(SRPItems.itemlurecomponent10);
        SRPRegisterModels.registerItemModel(SRPItems.weapon_scythe);
        SRPRegisterModels.registerItemModel(SRPItems.weapon_scytheSentient);
        SRPRegisterModels.registerItemModel(SRPItems.weapon_axe);
        SRPRegisterModels.registerItemModel(SRPItems.weapon_axeSentient);
        SRPRegisterModels.registerItemModel(SRPItems.weapon_sword);
        SRPRegisterModels.registerItemModel(SRPItems.weapon_swordSentient);
        SRPRegisterModels.registerItemModel(SRPItems.weapon_cleaver);
        SRPRegisterModels.registerItemModel(SRPItems.weapon_cleaverSentient);
        SRPRegisterModels.registerItemModel(SRPItems.weapon_bow);
        SRPRegisterModels.registerItemModel(SRPItems.weapon_bow_sentient);
        SRPRegisterModels.registerItemModel(SRPItems.weapon_maul);
        SRPRegisterModels.registerItemModel(SRPItems.weapon_maulSentient);
        SRPRegisterModels.registerItemModel(SRPItems.weapon_lance);
        SRPRegisterModels.registerItemModel(SRPItems.weapon_lanceSentient);
        SRPRegisterModels.registerItemModel(SRPItems.armor_helmet);
        SRPRegisterModels.registerItemModel(SRPItems.armor_chest);
        SRPRegisterModels.registerItemModel(SRPItems.armor_pants);
        SRPRegisterModels.registerItemModel(SRPItems.armor_boots);
        SRPRegisterModels.registerItemModel(SRPItems.armor_helmetSentient);
        SRPRegisterModels.registerItemModel(SRPItems.armor_chestSentient);
        SRPRegisterModels.registerItemModel(SRPItems.armor_pantsSentient);
        SRPRegisterModels.registerItemModel(SRPItems.armor_bootsSentient);
        SRPRegisterModels.registerItemModel(SRPItems.hijacked_iron_boots);
        SRPRegisterModels.registerItemModel(SRPItems.hijacked_iron_helmet);
        SRPRegisterModels.registerItemModel(SRPItems.hijacked_iron_chestpiece);
        SRPRegisterModels.registerItemModel(SRPItems.hijacked_iron_leggings);
        SRPRegisterModels.registerItemModel(SRPItems.hijacked_iron_hoe);
        SRPRegisterModels.registerItemModel(SRPItems.hijacked_iron_axe);
        SRPRegisterModels.registerItemModel(SRPItems.hijacked_iron_shovel);
        SRPRegisterModels.registerItemModel(SRPItems.hijacked_iron_sword);
        SRPRegisterModels.registerItemModel(SRPItems.hijacked_iron_pickaxe);
        SRPRegisterModels.registerItemModel(SRPItems.disc1);
        SRPRegisterModels.registerItemModel(SRPItems.disc2);
        SRPRegisterModels.registerItemModel(SRPItems.disc3);
        SRPRegisterModels.registerItemModel(SRPBlocks.InfestedStain);
        SRPRegisterModels.registerItemModel(SRPBlocks.InfestedRubble);
        SRPRegisterModels.registerItemModel(SRPBlocks.InfestedTrunk);
        SRPRegisterModels.registerItemModel(SRPBlocks.InfestRemain);
        SRPRegisterModels.registerItemModel(SRPBlocks.BiomeHeart);
        SRPRegisterModels.registerItemModel(SRPBlocks.ColonyHeart);
        SRPRegisterModels.registerItemModel(SRPBlocks.ColonyOutpost);
        SRPRegisterModels.registerItemModel(SRPBlocks.BiomePurifier);
        SRPRegisterModels.registerItemModel(SRPBlocks.SRPWeb);
        SRPRegisterModels.registerItemModel(SRPItems.signstatus);
        SRPRegisterModels.registerItemModel(SRPBlocks.ParasiteStructure);
        SRPRegisterModels.registerItemModel(SRPBlocks.buglin);
        SRPRegisterModels.registerItemModel(SRPBlocks.ParasiteThin);
        SRPRegisterModels.registerItemModel((Block)SRPBlocks.ParasiteRubbleSlabHalf);
        SRPRegisterModels.registerItemModel((Block)SRPBlocks.ParasiteStainSlabHalf);
        SRPRegisterModels.registerItemModel(SRPBlocks.ParasiteVine);
        SRPRegisterModels.registerItemModel(SRPBlocks.ParasiteFog);
        SRPRegisterModels.registerItemModel(SRPBlocks.ParasiteMouth);
        SRPRegisterModels.registerItemModel((Block)SRPBlocks.ParasiteCanisterActive);
        SRPRegisterModels.registerItemModel(SRPBlocks.ParasiteRubbleFleshWall);
        SRPRegisterModels.registerItemModel(SRPBlocks.dodN);
        SRPRegisterModels.registerItemModel(SRPBlocks.InfestedSand);
        SRPRegisterModels.registerItemModel(SRPBlocks.BloodyIce);
        SRPRegisterModels.registerItemModel(SRPBlocks.gothShroom);
        SRPRegisterModels.registerItemModel(SRPBlocks.NODE_LAMP);
        SRPRegisterModels.registerItemModel(SRPBlocks.RELAY_CONTROLLER);
        SRPRegisterModels.registerItemModel(SRPBlocks.PARASITE_BARRIER);
        SRPRegisterModels.registerItemModel(SRPBlocks.DermoidCyst);
        SRPRegisterModels.registerItemModel(SRPBlocks.RelayBase);
        SRPRegisterModels.registerItemModel(SRPBlocks.RelayMiddle);
        SRPRegisterModels.registerItemModel(SRPBlocks.RelayRoof);
        SRPRegisterModels.registerItemModel(SRPBlocks.INFESTED_FURNACE);
        SRPRegisterModels.registerItemModel(SRPBlocks.ParasitePlankDeadheadWall);
        SRPRegisterModels.registerItemModel(SRPBlocks.ParasiteRubbleWeathbWall);
        SRPRegisterModels.registerItemModel(SRPBlocks.ParasiteRubbleDenseColonyWall);
        SRPRegisterModels.registerItemModel(SRPBlocks.ParasiteRubbleWeathfsWall);
        SRPRegisterModels.registerItemModel(SRPBlocks.ParasiteRubbleDenseBiomeWall);
        SRPRegisterModels.registerItemModel(SRPBlocks.ParasiteRubbleWeathbcWall);
        SRPRegisterModels.registerItemModel(SRPBlocks.ParasiteRubbleBricksWall);
        SRPRegisterModels.registerItemModel(SRPBlocks.ParasiteCanisterBagWall);
        SRPRegisterModels.registerItemModel(SRPBlocks.InfestedRubbleWall);
        SRPRegisterModels.registerItemModel(SRPBlocks.InfestedStainWall);
        SRPRegisterModels.registerItemModel(SRPBlocks.ParasiteStainFleshWall);
        SRPRegisterModels.registerItemModel(SRPBlocks.ParasiteRubbleMetalWall);
        SRPRegisterModels.registerItemModel(SRPBlocks.InfestedPlankWall);
        SRPRegisterModels.registerItemModel(SRPBlocks.ResidueBrickWall);
        SRPRegisterModels.registerItemModel(SRPBlocks.BruisewoodPlankWall);
        SRPRegisterModels.registerItemModel(SRPBlocks.PolishedInfestedStoneWall);
        SRPRegisterModels.registerItemModel(SRPBlocks.InfestedStoneBrickWall);
        SRPRegisterModels.registerItemModel(SRPBlocks.GothPlankWall);
        SRPRegisterModels.registerItemModel(SRPBlocks.InfestedSandstoneWall);
        SRPRegisterModels.registerItemModel(SRPBlocks.ConsumedPlankWall);
        SRPRegisterModels.registerBlockVariants();
        SRPRegisterModels.RegisterCustomMashesAndStates();
        SRPMain.logger.info("Models registered");
    }

    private static void registerItemModel(Item item) {
        if (item == null) {
            SRPMain.logger.error("registerItemModel(Item): item is NULL");
            return;
        }
        if (item.getRegistryName() == null) {
            SRPMain.logger.error("registerItemModel(Item): item has NULL registry name: {} ({})", (Object)item, (Object)item.getClass().getName());
            return;
        }
        ModelResourceLocation location = new ModelResourceLocation(item.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation((Item)item, (int)0, (ModelResourceLocation)location);
    }

    private static void registerItemModel(Block block) {
        if (block == null) {
            SRPMain.logger.error("registerItemModel(Block): block is NULL");
            return;
        }
        Item item = Item.func_150898_a((Block)block);
        if (item == null || item == Items.field_190931_a) {
            SRPMain.logger.error("registerItemModel(Block): no ItemBlock for block {} reg={}", (Object)block, (Object)block.getRegistryName());
            return;
        }
        SRPRegisterModels.registerItemModel(item);
    }

    private static void registerItemModel(Block block, int meta, String fileName) {
        if (block == null) {
            SRPMain.logger.error("registerItemModel(Block,meta,file): block is NULL file={}", (Object)fileName);
            return;
        }
        Item item = Item.func_150898_a((Block)block);
        if (item == null || item == Items.field_190931_a) {
            SRPMain.logger.error("registerItemModel(Block,meta,file): no ItemBlock for block {} reg={} meta={} file={}", (Object)block, (Object)block.getRegistryName(), (Object)meta, (Object)fileName);
            return;
        }
        ModelResourceLocation location = new ModelResourceLocation(new ResourceLocation("srparasites", fileName), "inventory");
        ModelLoader.setCustomModelResourceLocation((Item)item, (int)meta, (ModelResourceLocation)location);
    }

    private static void registerBlockVariants() {
        int i;
        for (i = 0; i < BlockWebBase.EnumType.values().length; ++i) {
            SRPRegisterModels.registerItemModel(SRPBlocks.SRPWeb, i, "web_" + BlockWebBase.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockGore.EnumType.values().length; ++i) {
            SRPRegisterModels.registerItemModel((Block)SRPBlocks.goreSim, i, "gore_sim_" + BlockGore.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockGore.EnumType.values().length; ++i) {
            SRPRegisterModels.registerItemModel((Block)SRPBlocks.gorePri, i, "gore_pri_" + BlockGore.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockGore.EnumType.values().length; ++i) {
            SRPRegisterModels.registerItemModel((Block)SRPBlocks.goreAda, i, "gore_ada_" + BlockGore.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockGore.EnumType.values().length; ++i) {
            SRPRegisterModels.registerItemModel((Block)SRPBlocks.gorePur, i, "gore_pur_" + BlockGore.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockGore.EnumType.values().length; ++i) {
            SRPRegisterModels.registerItemModel((Block)SRPBlocks.goreFer, i, "gore_fer_" + BlockGore.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockGore.EnumType.values().length; ++i) {
            SRPRegisterModels.registerItemModel((Block)SRPBlocks.goreMar, i, "gore_mar_" + BlockGore.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockEvolutionLure.EnumType.values().length; ++i) {
            SRPRegisterModels.registerItemModel(SRPBlocks.evolutionLure, i, "lure_" + BlockEvolutionLure.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockInfestedBush.EnumType.values().length; ++i) {
            SRPRegisterModels.registerItemModel((Block)SRPBlocks.InfestedBush, i, "infestedbush_" + BlockInfestedBush.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockParasiteCanister.EnumType.values().length; ++i) {
            SRPRegisterModels.registerItemModel(SRPBlocks.ParasiteCanister, i, "canister_" + BlockParasiteCanister.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockParasiteRubble.EnumType.values().length; ++i) {
            SRPRegisterModels.registerItemModel(SRPBlocks.ParasiteRubble, i, "rubble_" + BlockParasiteRubble.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockParasiteStain.EnumType.values().length; ++i) {
            SRPRegisterModels.registerItemModel(SRPBlocks.ParasiteStain, i, "stain_" + BlockParasiteStain.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockParasiteTrunk.EnumType.values().length; ++i) {
            SRPRegisterModels.registerItemModel(SRPBlocks.ParasiteTrunk, i, "trunk_" + BlockParasiteTrunk.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockParasitePlank.EnumType.values().length; ++i) {
            SRPRegisterModels.registerItemModel(SRPBlocks.ParasitePlank, i, "plank_" + BlockParasitePlank.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockParasiteBush.EnumType.values().length; ++i) {
            SRPRegisterModels.registerItemModel((Block)SRPBlocks.ParasiteBush, i, "parasitebush_" + BlockParasiteBush.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockParasiteSapling.EnumType.values().length; ++i) {
            SRPRegisterModels.registerItemModel(SRPBlocks.ParasiteSapling, i, "sapling_" + BlockParasiteSapling.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockParasiteRubbleDense.EnumType.values().length; ++i) {
            SRPRegisterModels.registerItemModel(SRPBlocks.ParasiteRubbleDense, i, "rubbledense_" + BlockParasiteRubbleDense.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockInfestedOre.EnumType.values().length; ++i) {
            SRPRegisterModels.registerItemModel(SRPBlocks.InfestedOre, i, "infestedore_" + BlockInfestedOre.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockParasiteLoot.EnumType.values().length; ++i) {
            SRPRegisterModels.registerItemModel(SRPBlocks.ParasiteLoot, i, "loot_" + BlockParasiteLoot.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockSlabRubble.EnumType.values().length; ++i) {
            SRPRegisterModels.registerItemModel((Block)SRPBlocks.ParasiteRubbleSlabHalf, i, "slab_" + BlockSlabRubble.EnumType.values()[i].func_176610_l());
        }
        for (i = 0; i < BlockSlabStain.EnumType.values().length; ++i) {
            SRPRegisterModels.registerItemModel((Block)SRPBlocks.ParasiteStainSlabHalf, i, "slab_" + BlockSlabStain.EnumType.values()[i].func_176610_l());
        }
        SRPRegisterModels.registerItemModel(SRPBlocks.BloodyIce, 0, "bloodyice");
    }

    public static void RegisterCustomMashesAndStates() {
        ModelLoader.setCustomMeshDefinition((Item)Item.func_150898_a((Block)SRPBlocks.DeadBlood), (ItemMeshDefinition)new ItemMeshDefinition(){

            public ModelResourceLocation func_178113_a(ItemStack stack) {
                return new ModelResourceLocation("srparasites:deadblood", "fluid");
            }
        });
        ModelLoader.setCustomStateMapper((Block)SRPBlocks.DeadBlood, (IStateMapper)new StateMapperBase(){

            protected ModelResourceLocation func_178132_a(IBlockState state) {
                return new ModelResourceLocation("srparasites:deadblood", "fluid");
            }
        });
    }

    @Mod.EventBusSubscriber(modid="srparasites", value={Side.CLIENT})
    public static final class SRPClientParticles {
        public static TextureAtlasSprite INFESTED_LEAF_SPRITE;
        public static final TextureAtlasSprite[] INFESTED_LEAF;

        @SubscribeEvent
        public static void onTextureStitch(TextureStitchEvent.Pre e) {
            TextureMap map = e.getMap();
            SRPClientParticles.INFESTED_LEAF[0] = INFESTED_LEAF_SPRITE = map.func_174942_a(new ResourceLocation("srparasites:particle/infested_leaves"));
            SRPClientParticles.INFESTED_LEAF[1] = map.func_174942_a(new ResourceLocation("srparasites:particle/infested_leaves2"));
            SRPClientParticles.INFESTED_LEAF[2] = map.func_174942_a(new ResourceLocation("srparasites:particle/infested_leaves3"));
            SRPClientParticles.INFESTED_LEAF[3] = map.func_174942_a(new ResourceLocation("srparasites:particle/infested_leaves4"));
        }

        public static TextureAtlasSprite randomInfestedLeaf(Random rand) {
            return INFESTED_LEAF[rand.nextInt(INFESTED_LEAF.length)];
        }

        static {
            INFESTED_LEAF = new TextureAtlasSprite[4];
        }
    }
}

