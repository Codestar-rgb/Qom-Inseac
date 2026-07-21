package com.dhanantry.scapeandrunparasites.init;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.block.BlockAlveoli;
import com.dhanantry.scapeandrunparasites.block.BlockAlveoliGrowth;
import com.dhanantry.scapeandrunparasites.block.BlockAshenGlass;
import com.dhanantry.scapeandrunparasites.block.BlockAssimilatedPumpkin;
import com.dhanantry.scapeandrunparasites.block.BlockAssimilatedReed;
import com.dhanantry.scapeandrunparasites.block.BlockBase;
import com.dhanantry.scapeandrunparasites.block.BlockBiomassBlock;
import com.dhanantry.scapeandrunparasites.block.BlockBiomeCore;
import com.dhanantry.scapeandrunparasites.block.BlockBiomePurifier;
import com.dhanantry.scapeandrunparasites.block.BlockBloodyIce;
import com.dhanantry.scapeandrunparasites.block.BlockBuglin;
import com.dhanantry.scapeandrunparasites.block.BlockColonyCore;
import com.dhanantry.scapeandrunparasites.block.BlockColonyStructure;
import com.dhanantry.scapeandrunparasites.block.BlockDermoidCyst;
import com.dhanantry.scapeandrunparasites.block.BlockDiseasedSponge;
import com.dhanantry.scapeandrunparasites.block.BlockDod;
import com.dhanantry.scapeandrunparasites.block.BlockEntityTrophy;
import com.dhanantry.scapeandrunparasites.block.BlockEpitomeInfestationWarpDiffuser;
import com.dhanantry.scapeandrunparasites.block.BlockEscaBulb;
import com.dhanantry.scapeandrunparasites.block.BlockEvolutionLure;
import com.dhanantry.scapeandrunparasites.block.BlockFallingInfestedStain;
import com.dhanantry.scapeandrunparasites.block.BlockFluid;
import com.dhanantry.scapeandrunparasites.block.BlockFogNullifier;
import com.dhanantry.scapeandrunparasites.block.BlockGore;
import com.dhanantry.scapeandrunparasites.block.BlockGothshroom;
import com.dhanantry.scapeandrunparasites.block.BlockHairFolliclePillar;
import com.dhanantry.scapeandrunparasites.block.BlockHarleskinnFence;
import com.dhanantry.scapeandrunparasites.block.BlockHarleskinnSlab;
import com.dhanantry.scapeandrunparasites.block.BlockHarleskinnStairs;
import com.dhanantry.scapeandrunparasites.block.BlockHirsuteHair;
import com.dhanantry.scapeandrunparasites.block.BlockInfestationPurifier;
import com.dhanantry.scapeandrunparasites.block.BlockInfestedBush;
import com.dhanantry.scapeandrunparasites.block.BlockInfestedColumn;
import com.dhanantry.scapeandrunparasites.block.BlockInfestedFurnace;
import com.dhanantry.scapeandrunparasites.block.BlockInfestedOre;
import com.dhanantry.scapeandrunparasites.block.BlockInfestedRemain;
import com.dhanantry.scapeandrunparasites.block.BlockInfestedRubble;
import com.dhanantry.scapeandrunparasites.block.BlockInfestedStain;
import com.dhanantry.scapeandrunparasites.block.BlockInfestedTrunk;
import com.dhanantry.scapeandrunparasites.block.BlockInfuserFurnace;
import com.dhanantry.scapeandrunparasites.block.BlockLeafLike;
import com.dhanantry.scapeandrunparasites.block.BlockLipomaMass;
import com.dhanantry.scapeandrunparasites.block.BlockLocs;
import com.dhanantry.scapeandrunparasites.block.BlockNodeLamp;
import com.dhanantry.scapeandrunparasites.block.BlockNodeRelay;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteBarrier;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteBush;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteCactus;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteCanister;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteCanisterC;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteFog;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteLoot;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteMouth;
import com.dhanantry.scapeandrunparasites.block.BlockParasitePlank;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteRubble;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteRubbleDense;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteSapling;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteSpreading;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteStain;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteThin;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteTrunk;
import com.dhanantry.scapeandrunparasites.block.BlockPottedSRPFlower;
import com.dhanantry.scapeandrunparasites.block.BlockRelay;
import com.dhanantry.scapeandrunparasites.block.BlockRelayController;
import com.dhanantry.scapeandrunparasites.block.BlockResidue;
import com.dhanantry.scapeandrunparasites.block.BlockResiduePlants;
import com.dhanantry.scapeandrunparasites.block.BlockSRPFlower;
import com.dhanantry.scapeandrunparasites.block.BlockSRPWorkbench;
import com.dhanantry.scapeandrunparasites.block.BlockSickAlveoli;
import com.dhanantry.scapeandrunparasites.block.BlockSolidAlveoli;
import com.dhanantry.scapeandrunparasites.block.BlockStairBase;
import com.dhanantry.scapeandrunparasites.block.BlockThornshade;
import com.dhanantry.scapeandrunparasites.block.BlockTressesHair;
import com.dhanantry.scapeandrunparasites.block.BlockVineBase;
import com.dhanantry.scapeandrunparasites.block.BlockWallBase;
import com.dhanantry.scapeandrunparasites.block.BlockWebBase;
import com.dhanantry.scapeandrunparasites.block.IMetaName;
import com.dhanantry.scapeandrunparasites.block.SRPDoor;
import com.dhanantry.scapeandrunparasites.block.SRPGlassPane;
import com.dhanantry.scapeandrunparasites.block.SRPTrapDoor;
import com.dhanantry.scapeandrunparasites.block.TileEntityDermoidCyst;
import com.dhanantry.scapeandrunparasites.block.slabs.BlockSlabBase;
import com.dhanantry.scapeandrunparasites.block.slabs.BlockSlabRubble;
import com.dhanantry.scapeandrunparasites.block.slabs.BlockSlabStain;
import com.dhanantry.scapeandrunparasites.entity.tile.TileEntityTrophy;
import com.dhanantry.scapeandrunparasites.item.ItemBlockWithTooltip;
import com.dhanantry.scapeandrunparasites.item.ItemSlabBase;
import com.dhanantry.scapeandrunparasites.tileentity.TileEntityInfestedFurnace;
import com.dhanantry.scapeandrunparasites.tileentity.TileEntityNodeRelay;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.Level;

public class SRPBlocks {
   public static List<Block> SRP_BLOCKS = new ArrayList<>();
   public static Block INFESTED_FURNACE;
   public static Block InfestedStain = new BlockInfestedStain(Material.field_151578_c, "infestedstain", 0.7F, true, true);
   public static Block InfestRemain = new BlockInfestedRemain("infestremain");
   public static Block InfestedTrunk = new BlockInfestedTrunk(Material.field_151575_d, "infestedtrunk", 2.1F, true, true);
   public static Block InfestedRubble = new BlockInfestedRubble(Material.field_151576_e, "infestedrubble", 2.2F, true, true, 11.0F);
   public static BlockInfestedBush InfestedBush = new BlockInfestedBush("infestedbush", 0.4F);
   public static Block BiomeHeart;
   public static Block ColonyHeart;
   public static Block ColonyOutpost;
   public static Block BiomePurifier;
   public static Block SRPWeb;
   public static Block evolutionLure;
   public static Block buglin;
   public static Block RELAY_CONTROLLER;
   public static Block PARASITE_BARRIER;
   public static Block NODE_RELAY;
   public static IBlockState optionalDirt;
   public static IBlockState optionalRub;
   public static Block InfestedLeaves;
   public static Block InfestedLeavesFast;
   public static Block InfestedPlanks;
   public static Block InfestedStoneBricks;
   public static Block InfestedTerracotta;
   public static Block PolishedInfestedStone;
   public static Block ResidueBricks;
   public static Block ResidueBlock;
   public static Block InfestedColumn;
   public static Block InfestedSandstone;
   public static Block InfestedSandstoneChiseled;
   public static Block InfestedSandstoneCut;
   public static Block AshenGlass;
   public static Block ShroudedGlass;
   public static Block HarlequinnGlass;
   public static Block BloodyGlass;
   public static Block InfestedGlass;
   public static Block ShadeGlass;
   public static Block SepiaGlass;
   public static Block MoodyGlass;
   public static SRPGlassPane ASHEN_GLASS_PANE;
   public static SRPGlassPane INFESTED_GLASS_PANE;
   public static SRPGlassPane BLOODY_GLASS_PANE;
   public static SRPGlassPane HARLEQUINN_GLASS_PANE;
   public static SRPGlassPane SHROUDED_GLASS_PANE;
   public static SRPGlassPane SHADE_GLASS_PANE;
   public static SRPGlassPane SEPIA_GLASS_PANE;
   public static SRPGlassPane MOODY_GLASS_PANE;
   public static Block AssimilatedPumpkin;
   public static Block AssimilatedJackOLantern;
   public static Block AssimilatedSugarCane;
   public static Block GothStem;
   public static Block GothPlanks;
   public static Block ParasiteCactus;
   public static Block FleshPlanks;
   public static Block CookedFleshPlanks;
   public static Block CookedFlesh;
   public static Block BrusewoodPlanks;
   public static Block BrusewoodTrapdoor;
   public static Block ConsumedPlanks;
   public static Block ConsumedTrapdoor;
   public static Block ConsumedWorkbench;
   public static Block InfestedWorkbench;
   public static BlockSRPFlower ASSIMILATED_BLOSSOM;
   public static BlockPottedSRPFlower INFESTED_POT;
   public static BlockPottedSRPFlower POTTED_ASSIMILATED_BLOSSOM;
   public static BlockPottedSRPFlower CONSUMED_POT;
   public static BlockPottedSRPFlower POTTED_CONSUMED_ASSIMILATED_BLOSSOM;
   public static SRPDoor GothDoor;
   public static SRPDoor BrusewoodDoor;
   public static SRPDoor ConsumedDoor;
   public static Block Thornshade;
   public static Block diseasedSponge;
   public static Block SemiorganicBlock;
   public static Block HarlequinnGrass;
   public static Block HarleskinnBlock;
   public static Block PolandSkinBlock;
   public static Block HairFollicleBlock;
   public static Block LocsBlock;
   public static Block HirsuteHair;
   public static Block TressesHair;
   public static Block LipomaMass;
   public static BlockParasiteBush ParasiteBush;
   public static Block InfestedCobblestone;
   public static Block RelayBase;
   public static Block RelayMiddle;
   public static Block RelayRoof;
   public static Block relaycontroller_dummy;
   public static Block NODE_LAMP;
   public static Block ParasiteCanister;
   public static Block ParasiteTrunk;
   public static Block ParasitePlank;
   public static Block ParasiteStain;
   public static Block ParasiteLoot;
   public static Block ParasiteRubble;
   public static Block ParasiteStructure;
   public static Block ParasiteThin;
   public static Block ParasiteSapling;
   public static Block ParasiteMouth;
   public static Block ParasiteRubbleDense;
   public static Block ParasiteRubbleFleshWall;
   public static Block InfestedStainStair;
   public static Block InfestedRubbleStair;
   public static Block InfestedTrunkStair;
   public static Block ParasiteRubbleBoneStair;
   public static Block ParasiteRubbleFleshStair;
   public static Block ParasiteRubbleStoneStair;
   public static Block ParasiteRubbleStoneDebrisStair;
   public static Block ParasiteRubbleWoodStair;
   public static Block ParasiteRubbleBrickStair;
   public static Block ParasiteRubbleMetalStair;
   public static Block ParasiteRubbleObsidianStair;
   public static Block ParasiteRubbleFungusStair;
   public static Block ParasiteStainFleshStair;
   public static Block ParasiteStainDirtStair;
   public static Block ParasiteStainMudStair;
   public static Block ParasiteStainFeelerStair;
   public static Block ParasiteRubbleDenseWallStair;
   public static Block ParasiteRubbleDenseNodeStair;
   public static Block ParasiteRubbleDenseColonyStair;
   public static Block ParasiteTrunkBallStair;
   public static Block ParasiteTrunkTreeStair;
   public static Block ParasiteTrunkPlantStair;
   public static BlockSlabBase ParasiteRubbleSlabHalf;
   public static BlockSlabBase ParasiteRubbleSlabDouble;
   public static BlockSlabBase ParasiteStainSlabHalf;
   public static BlockSlabBase ParasiteStainSlabDouble;
   public static Block Alveoli;
   public static Block SickAlveoli;
   public static Block AlveoliGrowth;
   public static Block SolidAlveoliBlock;
   public static Block HarleskinnStairs;
   public static Block HarleskinnFence;
   public static Block InfestedFence;
   public static Block DeadheadFence;
   public static Block GothFence;
   public static Block ConsumedFence;
   public static Block BrusewoodFence;
   public static Block CookedFleshFence;
   public static Block FleshFence;
   public static Block HarleskinnSlabDouble;
   public static Block HarleskinnSlab;
   public static Block BruisewoodPlankStairs;
   public static Block InfestedSandstoneStairs;
   public static Block GothPlanksStairs;
   public static Block DeadheadPlankStairs;
   public static Block ResidueStairs;
   public static Block InfestedPlanksStairs;
   public static Block ConsumedPlanksStairs;
   public static Block InfestedStoneBricksStairs;
   public static Block InfestedPolishedStoneBricksStairs;
   public static Block InfestedStoneStairs;
   public static Block WheatheredBricksStairs;
   public static Block WheatheredCobblestoneStairs;
   public static Block CookedFleshStairs;
   public static Block FleshStairs;
   public static Block FrostWeatheredStoneStairs;
   public static Block EscaBulb;
   public static Block EscaBulbWhite;
   public static Block EscaBulbOrange;
   public static Block EscaBulbMagenta;
   public static Block EscaBulbLightBlue;
   public static Block EscaBulbYellow;
   public static Block EscaBulbLime;
   public static Block EscaBulbPink;
   public static Block EscaBulbGray;
   public static Block EscaBulbLightGray;
   public static Block EscaBulbCyan;
   public static Block EscaBulbPurple;
   public static Block EscaBulbBlue;
   public static Block EscaBulbBrown;
   public static Block EscaBulbGreen;
   public static Block EscaBulbRed;
   public static Block EscaBulbBlack;
   public static Block ParasiteVine;
   public static Block ParasiteFog;
   public static Block DeadBlood;
   public static Block InfestedSand;
   public static Block BiomassBlock;
   public static Block TrophyVoidOrb;
   public static Block TrophyBoomOrb;
   public static Block ResiduePlants;
   public static Block InfuserFurnace;
   public static Block FogNullifier;
   public static Block InfestedCobblestoneSlab;
   public static Block InfestedCobblestoneSlabDouble;
   public static Block InfestedStoneSlab;
   public static Block InfestedStoneSlabDouble;
   public static Block InfestedDirtSlab;
   public static Block InfestedDirtSlabDouble;
   public static Block ReinforcedHivestoneSlab;
   public static Block ReinforcedHivestoneSlabDouble;
   public static Block ParasiticColonyCoreSlab;
   public static Block ParasiticColonyCoreSlabDouble;
   public static Block SacOfFleshSlab;
   public static Block SacOfFleshSlabDouble;
   public static Block DeadHeadPlankSlab;
   public static Block DeadHeadPlankSlabDouble;
   public static Block WeatheredBricksSlab;
   public static Block WeatheredBricksSlabDouble;
   public static Block ParasiticCompressedColonyStoneSlab;
   public static Block ParasiticCompressedColonyStoneSlabDouble;
   public static Block WeatheredCobblestoneSlab;
   public static Block WeatheredCobblestoneSlabDouble;
   public static Block FrostWeatheredStoneSlab;
   public static Block FrostWeatheredStoneSlabDouble;
   public static Block InfestedStoneBrickSlab;
   public static Block InfestedStoneBrickSlabDouble;
   public static Block InfestedTerracottaSlab;
   public static Block InfestedTerracottaSlabDouble;
   public static Block PolishedInfestedStoneSlab;
   public static Block PolishedInfestedStoneSlabDouble;
   public static Block ResidueBrickSlab;
   public static Block ResidueBrickSlabDouble;
   public static Block InfestedSandstoneSlab;
   public static Block InfestedSandstoneSlabDouble;
   public static Block GothPlankSlab;
   public static Block GothPlankSlabDouble;
   public static Block BruisewoodPlankSlab;
   public static Block BruisewoodPlankSlabDouble;
   public static Block ConsumedPlankSlab;
   public static Block ConsumedPlankSlabDouble;
   public static Block InfestedPlankSlabDouble;
   public static Block InfestedPlankSlab;
   public static Block PolandSkinSlab;
   public static Block PolandSkinSlabDouble;
   public static Block LocsBlockSlab;
   public static Block LocsBlockSlabDouble;
   public static Block FleshSlab;
   public static Block FleshSlabDouble;
   public static Block CookedFleshSlab;
   public static Block CookedFleshSlabDouble;
   public static Block DermoidCyst;
   public static Block EpitomeInfestationWarpDiffuser;
   public static Block ParasitePlankDeadheadWall;
   public static Block ParasiteRubbleWeathbWall;
   public static Block ParasiteRubbleWeathfsWall;
   public static Block ParasiteRubbleWeathbcWall;
   public static Block ParasiteRubbleBricksWall;
   public static Block ParasiteRubbleMetalWall;
   public static Block ParasiteRubbleDenseColonyWall;
   public static Block ParasiteRubbleDenseBiomeWall;
   public static Block ParasiteCanisterBagWall;
   public static Block InfestedRubbleWall;
   public static Block InfestedStainWall;
   public static Block ParasiteStainFleshWall;
   public static Block InfestedPlankWall;
   public static Block ResidueBrickWall;
   public static Block BruisewoodPlankWall;
   public static Block PolishedInfestedStoneWall;
   public static Block InfestedStoneBrickWall;
   public static Block GothPlankWall;
   public static Block InfestedSandstoneWall;
   public static Block ConsumedPlankWall;
   public static BlockGore goreSim;
   public static BlockGore gorePri;
   public static BlockGore goreAda;
   public static BlockGore gorePur;
   public static BlockGore goreFer;
   public static BlockGore goreMar;
   public static BlockParasiteCanisterC ParasiteCanisterActive;
   public static BlockInfestationPurifier InfestPurify;
   public static Block dodN;
   public static Block InfestedOre;
   public static Block BloodyIce = new BlockBloodyIce(Material.field_151588_w, "bloodyice", 0.7F, true, true);
   public static Block gothShroom = new BlockGothshroom("gothshroom", true);
   public static Material PARASITEBLOOD = new MaterialLiquid(MapColor.field_151662_n);

   public static void init() {
      optionalDirt = parseBlockState(SRPConfigSystems.optionalBlockDirt, Blocks.field_150351_n.func_176223_P());
      optionalRub = parseBlockState(SRPConfigSystems.optionalBlockRubble, Blocks.field_150341_Y.func_176223_P());
   }

   private static IBlockState parseBlockState(String cfg, IBlockState fallback) {
      if (cfg != null && !cfg.isEmpty()) {
         String[] parts = cfg.split(":");
         if (parts.length < 3) {
            SRPMain.logger.log(Level.WARN, "[SRPBlocks] Invalid block format '" + cfg + "' (need domain:block:meta), using fallback: " + fallback);
            return fallback;
         } else {
            String name = parts[0] + ":" + parts[1];
            Block block = Block.func_149684_b(name);
            if (block == null) {
               SRPMain.logger.log(Level.WARN, "[SRPBlocks] Unknown block '" + name + "', using fallback: " + fallback);
               return fallback;
            } else {
               try {
                  int meta = Integer.parseInt(parts[2]);
                  return block.func_176203_a(meta);
               } catch (NumberFormatException var6) {
                  SRPMain.logger.log(Level.WARN, "[SRPBlocks] Bad meta '" + parts[2] + "' for block '" + name + "', using fallback: " + fallback);
                  return fallback;
               }
            }
         }
      } else {
         SRPMain.logger.log(Level.WARN, "[SRPBlocks] Empty config string for block, using fallback: " + fallback);
         return fallback;
      }
   }

   @EventBusSubscriber(modid = "srparasites")
   public static class RegistrationHandler {
      private static final String MODID = "srparasites";
      private static IForgeRegistry<Item> itemRegistry;

      @SubscribeEvent
      public static void onEvent(Register<Block> event) {
         SRPBlocks.RELAY_CONTROLLER = new BlockRelayController("relaycontroller");
         SRPBlocks.PARASITE_BARRIER = new BlockParasiteBarrier();
         SRPBlocks.BiomeHeart = new BlockBiomeCore(Material.field_151576_e, "biomeheart", 60.0F, true, true, 2500.0F);
         SRPBlocks.ColonyHeart = new BlockColonyCore(Material.field_151576_e, "colonyheart", 60.0F, true, true, 2500.0F);
         SRPBlocks.ColonyOutpost = new BlockColonyCore(Material.field_151576_e, "colonyoutpost", 30.0F, true, true, 1200.0F);
         SRPBlocks.BiomePurifier = new BlockBiomePurifier("biomepurifier", 2.0F, true, true, 5.0F);
         SRPBlocks.DermoidCyst = new BlockDermoidCyst();
         SRPBlocks.NODE_LAMP = new BlockNodeLamp();
         SRPBlocks.BiomassBlock = new BlockBiomassBlock("biomass_block");
         SRPBlocks.ResidueBlock = new BlockResidue("residue_block");
         SRPBlocks.NODE_RELAY = new BlockNodeRelay("noderelay");
         SRPBlocks.RelayBase = new BlockRelay("relay_base");
         SRPBlocks.RelayMiddle = new BlockRelay("relay_middle");
         SRPBlocks.RelayRoof = new BlockRelay("relay_roof");
         SRPBlocks.FogNullifier = new BlockFogNullifier("fog_nullifier");
         SRPBlocks.ResiduePlants = new BlockResiduePlants("residue_plants");
         SRPBlocks.InfuserFurnace = new BlockInfuserFurnace("infuser_furnace");
         SRPBlocks.relaycontroller_dummy = new BlockRelay("relay_controller_dummy");
         GameRegistry.registerTileEntity(TileEntityNodeRelay.class, new ResourceLocation("srparasites", "node_relay"));
         GameRegistry.registerTileEntity(TileEntityTrophy.class, new ResourceLocation("srparasites", "trophy_te"));
         GameRegistry.registerTileEntity(TileEntityDermoidCyst.class, new ResourceLocation("srparasites", "dermoid_cyst"));
         GameRegistry.registerTileEntity(TileEntityInfestedFurnace.class, "srparasites:infested_furnace");
         SRPBlocks.TrophyVoidOrb = new BlockEntityTrophy(
            Material.field_151576_e, "trophy_void_orb", "srparasites:orbvoid", true, BlockEntityTrophy.TrophyTextureMode.DEFAULT, true, 3.0F, 5.0F
         );
         SRPBlocks.TrophyBoomOrb = new BlockEntityTrophy(
            Material.field_151576_e, "trophy_boom_orb", "srparasites:orbboom", true, BlockEntityTrophy.TrophyTextureMode.DEFAULT, true, 3.0F, 5.0F
         );
         SRPBlocks.ASSIMILATED_BLOSSOM = new BlockSRPFlower("assimilated_blossom");
         SRPBlocks.POTTED_ASSIMILATED_BLOSSOM = new BlockPottedSRPFlower("potted_assimilated_blossom", Item.func_150898_a(SRPBlocks.ASSIMILATED_BLOSSOM));
         SRPBlocks.POTTED_CONSUMED_ASSIMILATED_BLOSSOM = new BlockPottedSRPFlower(
            "potted_consumed_assimilated_blossom", Item.func_150898_a(SRPBlocks.ASSIMILATED_BLOSSOM)
         );
         SRPBlocks.INFESTED_FURNACE = new BlockInfestedFurnace();
         SRPBlocks.SRP_BLOCKS.add(SRPBlocks.INFESTED_FURNACE);
         SRPBlocks.INFESTED_POT = new BlockPottedSRPFlower("infested_pot", null);
         SRPBlocks.CONSUMED_POT = new BlockPottedSRPFlower("consumed_pot", null);
         SRPBlocks.Alveoli = new BlockAlveoli();
         SRPBlocks.SickAlveoli = new BlockSickAlveoli();
         SRPBlocks.AlveoliGrowth = new BlockAlveoliGrowth();
         SRPBlocks.SolidAlveoliBlock = new BlockSolidAlveoli();
         SRPBlocks.InfestedCobblestone = new BlockInfestedStain(Material.field_151576_e, "infested_cobblestone", 2.0F, true, true);
         SRPBlocks.InfestPurify = new BlockInfestationPurifier("infestation_purifier");
         SRPBlocks.EscaBulbWhite = new BlockEscaBulb("esca_bulb_white");
         SRPBlocks.EscaBulbOrange = new BlockEscaBulb("esca_bulb_orange");
         SRPBlocks.EscaBulbMagenta = new BlockEscaBulb("esca_bulb_magenta");
         SRPBlocks.EscaBulbLightBlue = new BlockEscaBulb("esca_bulb_light_blue");
         SRPBlocks.EscaBulbYellow = new BlockEscaBulb("esca_bulb_yellow");
         SRPBlocks.EscaBulbLime = new BlockEscaBulb("esca_bulb_lime");
         SRPBlocks.EscaBulbPink = new BlockEscaBulb("esca_bulb_pink");
         SRPBlocks.EscaBulbGray = new BlockEscaBulb("esca_bulb_gray");
         SRPBlocks.EscaBulbLightGray = new BlockEscaBulb("esca_bulb_light_gray");
         SRPBlocks.EscaBulbCyan = new BlockEscaBulb("esca_bulb_cyan");
         SRPBlocks.EscaBulbPurple = new BlockEscaBulb("esca_bulb_purple");
         SRPBlocks.EscaBulbBlue = new BlockEscaBulb("esca_bulb_blue");
         SRPBlocks.EscaBulbBrown = new BlockEscaBulb("esca_bulb_brown");
         SRPBlocks.EscaBulbGreen = new BlockEscaBulb("esca_bulb_green");
         SRPBlocks.EscaBulbRed = new BlockEscaBulb("esca_bulb_red");
         SRPBlocks.EscaBulbBlack = new BlockEscaBulb("esca_bulb_black");
         SRPBlocks.EscaBulb = new BlockEscaBulb("esca_bulb");
         SRPBlocks.ParasiteBush = new BlockParasiteBush("parasitebush", 0.5F);
         SRPBlocks.ParasiteCanister = new BlockParasiteCanister(Material.field_151570_A, "parasitecanister", 0.7F, true, true);
         SRPBlocks.ParasiteTrunk = new BlockParasiteTrunk(Material.field_151575_d, "parasitetrunk", 2.2F, true, true);
         SRPBlocks.ParasitePlank = new BlockParasitePlank(Material.field_151575_d, "parasiteplank", 2.2F, true, true);
         SRPBlocks.ParasiteLoot = new BlockParasiteLoot(Material.field_151578_c, "parasiteloot", 3.5F, true, true);
         SRPBlocks.ParasiteStain = new BlockParasiteStain(Material.field_151578_c, "parasitestain", 0.8F, true, false);
         SRPBlocks.ParasiteRubble = new BlockParasiteRubble(Material.field_151576_e, "parasiterubble", 2.3F, true, false);
         SRPBlocks.ParasiteStructure = new BlockColonyStructure(Material.field_151576_e, "parasitestructure", 5.0F, true, true, 20.0F);
         SRPBlocks.ParasiteThin = new BlockParasiteThin("parasitethin", 2.2F);
         SRPBlocks.ParasiteSapling = new BlockParasiteSapling("parasitesapling");
         SRPBlocks.ParasiteMouth = new BlockParasiteMouth(Material.field_151570_A, "parasitemouth", 1.4F, true, true);
         SRPBlocks.ParasiteRubbleDense = new BlockParasiteRubbleDense(Material.field_151576_e, "parasiterubbledense", 3.3F, true, true);
         SRPBlocks.ParasiteRubbleFleshWall = new BlockWallBase("parasiterubble_flesh_wall", true, SRPBlocks.ParasiteRubble);
         SRPBlocks.ParasiteRubbleFleshWall.setHarvestLevel("pickaxe", 1);
         SRPBlocks.InfestedPlanks = new BlockInfestedRubble(Material.field_151575_d, "infested_planks", 2.0F, true, true, 5.0F);
         SRPBlocks.InfestedStoneBricks = new BlockInfestedRubble(Material.field_151576_e, "infested_stone_bricks", 1.5F, true, true, 10.0F);
         SRPBlocks.InfestedTerracotta = new BlockInfestedRubble(Material.field_151576_e, "infested_terracotta", 1.25F, true, true, 4.2F);
         SRPBlocks.PolishedInfestedStone = new BlockInfestedRubble(Material.field_151576_e, "infested_stone_polished", 1.5F, true, true, 10.0F);
         SRPBlocks.ResidueBricks = new BlockInfestedRubble(Material.field_151576_e, "residue_bricks", 1.5F, true, true, 10.0F);
         SRPBlocks.InfestedColumn = new BlockInfestedColumn("infested_column");
         SRPBlocks.InfestedSandstone = new BlockInfestedRubble(Material.field_151576_e, "inf_ss", 0.8F, true, true, 4.0F);
         SRPBlocks.InfestedSandstoneChiseled = new BlockInfestedRubble(Material.field_151576_e, "inf_ss_chiseled", 0.8F, true, true, 4.0F);
         SRPBlocks.InfestedSandstoneCut = new BlockInfestedRubble(Material.field_151576_e, "inf_ss_cut", 0.8F, true, true, 4.0F);
         SRPBlocks.AshenGlass = new BlockAshenGlass("ashen_glass");
         SRPBlocks.ShroudedGlass = new BlockAshenGlass("shrouded_glass");
         SRPBlocks.HarlequinnGlass = new BlockAshenGlass("harlequinn_glass");
         SRPBlocks.BloodyGlass = new BlockAshenGlass("bloody_glass");
         SRPBlocks.InfestedGlass = new BlockAshenGlass("infested_glass");
         SRPBlocks.ShadeGlass = new BlockAshenGlass("shade_glass");
         SRPBlocks.SepiaGlass = new BlockAshenGlass("sepia_glass");
         SRPBlocks.MoodyGlass = new BlockAshenGlass("moody_glass");
         SRPBlocks.ASHEN_GLASS_PANE = new SRPGlassPane("ashen_glass_pane");
         SRPBlocks.INFESTED_GLASS_PANE = new SRPGlassPane("infested_glass_pane");
         SRPBlocks.BLOODY_GLASS_PANE = new SRPGlassPane("bloody_glass_pane");
         SRPBlocks.HARLEQUINN_GLASS_PANE = new SRPGlassPane("harlequinn_glass_pane");
         SRPBlocks.SHROUDED_GLASS_PANE = new SRPGlassPane("shrouded_glass_pane");
         SRPBlocks.SHADE_GLASS_PANE = new SRPGlassPane("shade_glass_pane");
         SRPBlocks.SEPIA_GLASS_PANE = new SRPGlassPane("sepia_glass_pane");
         SRPBlocks.MOODY_GLASS_PANE = new SRPGlassPane("moody_glass_pane");
         SRPBlocks.EpitomeInfestationWarpDiffuser = new BlockEpitomeInfestationWarpDiffuser();
         SRPBlocks.GothStem = new BlockInfestedColumn("goth_stem");
         SRPBlocks.AssimilatedPumpkin = new BlockAssimilatedPumpkin("assimilated_pumpkin", false);
         SRPBlocks.AssimilatedJackOLantern = new BlockAssimilatedPumpkin("assimilated_jack_o_lantern", true);
         SRPBlocks.AssimilatedSugarCane = new BlockAssimilatedReed();
         SRPBlocks.CookedFlesh = new BlockInfestedRubble(Material.field_151575_d, "cooked_flesh", 2.0F, true, false, 5.0F);
         SRPBlocks.CookedFleshPlanks = new BlockInfestedRubble(Material.field_151575_d, "cooked_flesh_planks", 2.0F, true, false, 5.0F);
         SRPBlocks.FleshPlanks = new BlockInfestedRubble(Material.field_151575_d, "flesh_planks", 2.0F, true, false, 5.0F);
         SRPBlocks.GothPlanks = new BlockInfestedRubble(Material.field_151575_d, "goth_planks", 2.0F, true, false, 5.0F);
         SRPBlocks.BrusewoodPlanks = new BlockInfestedRubble(Material.field_151575_d, "brusewood_planks", 2.0F, true, false, 5.0F);
         SRPBlocks.ConsumedPlanks = new BlockInfestedRubble(Material.field_151575_d, "consumed_planks", 2.0F, true, false, 5.0F);
         SRPBlocks.SemiorganicBlock = new BlockBase(Material.field_151573_f, "semiorganic_block", 2.0F, true, false, 5.0F);
         SRPBlocks.HarlequinnGrass = new BlockParasiteSpreading(Material.field_151578_c, "harlequinn_grass", 0.6F, true, false);
         SRPBlocks.HarleskinnBlock = new BlockParasiteSpreading(Material.field_151576_e, "harleskinn_block", 1.5F, true, false).func_149752_b(10.0F);
         SRPBlocks.PolandSkinBlock = new BlockParasiteSpreading(Material.field_151576_e, "poland_skin_block", 1.5F, true, false).func_149752_b(10.0F);
         SRPBlocks.HairFollicleBlock = new BlockHairFolliclePillar("hair_follicle_block", Material.field_151575_d);
         SRPBlocks.LocsBlock = new BlockLocs(Material.field_151580_n, "locs_block", 0.8F, true, true);
         SRPBlocks.InfestedLeaves = new BlockLeafLike("infested_leaves");
         SRPBlocks.InfestedLeavesFast = new BlockLeafLike("infested_leaves_fast");
         SRPBlocks.HirsuteHair = new BlockHirsuteHair("hirsute_hair");
         SRPBlocks.TressesHair = new BlockTressesHair("tresses_hair");
         SRPBlocks.LipomaMass = new BlockLipomaMass("lipoma_mass");
         SRPBlocks.HarleskinnFence = new BlockHarleskinnFence("harleskinn_fence");
         SRPBlocks.InfestedFence = new BlockHarleskinnFence("infested_fence");
         SRPBlocks.DeadheadFence = new BlockHarleskinnFence("deadhead_fence");
         SRPBlocks.GothFence = new BlockHarleskinnFence("goth_fence");
         SRPBlocks.ConsumedFence = new BlockHarleskinnFence("consumed_fence");
         SRPBlocks.BrusewoodFence = new BlockHarleskinnFence("bruisewood_fence");
         SRPBlocks.CookedFleshFence = new BlockHarleskinnFence("cooked_flesh_fence");
         SRPBlocks.FleshFence = new BlockHarleskinnFence("flesh_fence");
         SRPBlocks.ConsumedWorkbench = new BlockSRPWorkbench("consumed_workbench", 2.5F, true, 12.5F);
         SRPBlocks.InfestedWorkbench = new BlockSRPWorkbench("infested_workbench", 2.5F, true, 12.5F);
         SRPBlocks.ParasiteCactus = new BlockParasiteCactus("infested_cactus", 0.4F, true, 0.4F);
         SRPBlocks.HarleskinnSlabDouble = new BlockHarleskinnSlab(true, "harleskinn_slab_double", "axe", 1, SRPSoundTypes.FLESH, 2.0F, 3.0F, true);
         SRPBlocks.HarleskinnSlab = new BlockHarleskinnSlab(
            false, "harleskinn_slab", "axe", 1, SRPSoundTypes.FLESH, 2.0F, 3.0F, true, (BlockSlab)SRPBlocks.HarleskinnSlabDouble
         );
         ((BlockHarleskinnSlab)SRPBlocks.HarleskinnSlabDouble).setHalfPartner((BlockSlab)SRPBlocks.HarleskinnSlab);
         SRPBlocks.InfestedCobblestoneSlabDouble = new BlockHarleskinnSlab(
            true, "infested_cobblestone_slab_double", "pickaxe", 1, SoundType.field_185851_d, 2.0F, 3.0F, true
         );
         SRPBlocks.InfestedCobblestoneSlab = new BlockHarleskinnSlab(
            false, "infested_cobblestone_slab", "pickaxe", 1, SoundType.field_185851_d, 2.0F, 3.0F, true, (BlockSlab)SRPBlocks.InfestedCobblestoneSlabDouble
         );
         ((BlockHarleskinnSlab)SRPBlocks.InfestedCobblestoneSlabDouble).setHalfPartner((BlockSlab)SRPBlocks.InfestedCobblestoneSlab);
         SRPBlocks.InfestedStoneSlabDouble = new BlockHarleskinnSlab(
            true, "infested_stone_slab_double", "pickaxe", 1, SoundType.field_185851_d, 2.0F, 3.0F, true
         );
         SRPBlocks.InfestedStoneSlab = new BlockHarleskinnSlab(
            false, "infested_stone_slab", "pickaxe", 1, SoundType.field_185851_d, 2.0F, 3.0F, true, (BlockSlab)SRPBlocks.InfestedStoneSlabDouble
         );
         ((BlockHarleskinnSlab)SRPBlocks.InfestedStoneSlabDouble).setHalfPartner((BlockSlab)SRPBlocks.InfestedStoneSlab);
         SRPBlocks.InfestedDirtSlabDouble = new BlockHarleskinnSlab(true, "infested_dirt_slab_double", "shovel", 0, SRPSoundTypes.FLESH, 0.5F, 0.5F, true);
         SRPBlocks.InfestedDirtSlab = new BlockHarleskinnSlab(
            false, "infested_dirt_slab", "shovel", 0, SRPSoundTypes.FLESH, 0.5F, 0.5F, true, (BlockSlab)SRPBlocks.InfestedDirtSlabDouble
         );
         ((BlockHarleskinnSlab)SRPBlocks.InfestedDirtSlabDouble).setHalfPartner((BlockSlab)SRPBlocks.InfestedDirtSlab);
         SRPBlocks.ReinforcedHivestoneSlabDouble = new BlockHarleskinnSlab(
            true, "reinforced_hivestone_slab_double", "pickaxe", 1, SoundType.field_185851_d, 3.0F, 9.0F, true
         );
         SRPBlocks.ReinforcedHivestoneSlab = new BlockHarleskinnSlab(
            false, "reinforced_hivestone_slab", "pickaxe", 1, SoundType.field_185851_d, 3.0F, 9.0F, true, (BlockSlab)SRPBlocks.ReinforcedHivestoneSlabDouble
         );
         ((BlockHarleskinnSlab)SRPBlocks.ReinforcedHivestoneSlabDouble).setHalfPartner((BlockSlab)SRPBlocks.ReinforcedHivestoneSlab);
         SRPBlocks.ParasiticColonyCoreSlabDouble = new BlockHarleskinnSlab(
            true, "parasitic_colony_core_slab_double", "pickaxe", 1, SRPSoundTypes.FLESH, 2.0F, 6.0F, true
         );
         SRPBlocks.ParasiticColonyCoreSlab = new BlockHarleskinnSlab(
            false, "parasitic_colony_core_slab", "pickaxe", 1, SRPSoundTypes.FLESH, 2.0F, 6.0F, true, (BlockSlab)SRPBlocks.ParasiticColonyCoreSlabDouble
         );
         ((BlockHarleskinnSlab)SRPBlocks.ParasiticColonyCoreSlabDouble).setHalfPartner((BlockSlab)SRPBlocks.ParasiticColonyCoreSlab);
         SRPBlocks.SacOfFleshSlabDouble = new BlockHarleskinnSlab(true, "sac_of_flesh_slab_double", "axe", 1, SRPSoundTypes.FLESH, 1.0F, 2.0F, true);
         SRPBlocks.SacOfFleshSlab = new BlockHarleskinnSlab(
            false, "sac_of_flesh_slab", "axe", 1, SRPSoundTypes.FLESH, 1.0F, 2.0F, true, (BlockSlab)SRPBlocks.SacOfFleshSlabDouble
         );
         ((BlockHarleskinnSlab)SRPBlocks.SacOfFleshSlabDouble).setHalfPartner((BlockSlab)SRPBlocks.SacOfFleshSlab);
         SRPBlocks.DeadHeadPlankSlabDouble = new BlockHarleskinnSlab(true, "dead_head_plank_slab_double", "axe", 1, SoundType.field_185851_d, 2.0F, 3.0F, true);
         SRPBlocks.DeadHeadPlankSlab = new BlockHarleskinnSlab(
            false, "dead_head_plank_slab", "axe", 1, SoundType.field_185851_d, 2.0F, 3.0F, true, (BlockSlab)SRPBlocks.DeadHeadPlankSlabDouble
         );
         ((BlockHarleskinnSlab)SRPBlocks.DeadHeadPlankSlabDouble).setHalfPartner((BlockSlab)SRPBlocks.DeadHeadPlankSlab);
         SRPBlocks.WeatheredBricksSlabDouble = new BlockHarleskinnSlab(
            true, "weathered_bricks_slab_double", "pickaxe", 1, SoundType.field_185851_d, 2.0F, 6.0F, true
         );
         SRPBlocks.WeatheredBricksSlab = new BlockHarleskinnSlab(
            false, "weathered_bricks_slab", "pickaxe", 1, SoundType.field_185851_d, 2.0F, 6.0F, true, (BlockSlab)SRPBlocks.WeatheredBricksSlabDouble
         );
         ((BlockHarleskinnSlab)SRPBlocks.WeatheredBricksSlabDouble).setHalfPartner((BlockSlab)SRPBlocks.WeatheredBricksSlab);
         SRPBlocks.ParasiticCompressedColonyStoneSlabDouble = new BlockHarleskinnSlab(
            true, "parasitic_compressed_colony_stone_slab_double", "pickaxe", 1, SoundType.field_185851_d, 3.0F, 9.0F, true
         );
         SRPBlocks.ParasiticCompressedColonyStoneSlab = new BlockHarleskinnSlab(
            false,
            "parasitic_compressed_colony_stone_slab",
            "pickaxe",
            1,
            SoundType.field_185851_d,
            3.0F,
            9.0F,
            true,
            (BlockSlab)SRPBlocks.ParasiticCompressedColonyStoneSlabDouble
         );
         ((BlockHarleskinnSlab)SRPBlocks.ParasiticCompressedColonyStoneSlabDouble).setHalfPartner((BlockSlab)SRPBlocks.ParasiticCompressedColonyStoneSlab);
         SRPBlocks.WeatheredCobblestoneSlabDouble = new BlockHarleskinnSlab(
            true, "weathered_cobblestone_slab_double", "pickaxe", 1, SoundType.field_185851_d, 2.0F, 6.0F, true
         );
         SRPBlocks.WeatheredCobblestoneSlab = new BlockHarleskinnSlab(
            false, "weathered_cobblestone_slab", "pickaxe", 1, SoundType.field_185851_d, 2.0F, 6.0F, true, (BlockSlab)SRPBlocks.WeatheredCobblestoneSlabDouble
         );
         ((BlockHarleskinnSlab)SRPBlocks.WeatheredCobblestoneSlabDouble).setHalfPartner((BlockSlab)SRPBlocks.WeatheredCobblestoneSlab);
         SRPBlocks.FrostWeatheredStoneSlabDouble = new BlockHarleskinnSlab(
            true, "frost_weathered_stone_slab_double", "pickaxe", 1, SoundType.field_185851_d, 2.0F, 6.0F, true
         );
         SRPBlocks.FrostWeatheredStoneSlab = new BlockHarleskinnSlab(
            false, "frost_weathered_stone_slab", "pickaxe", 1, SoundType.field_185851_d, 2.0F, 6.0F, true, (BlockSlab)SRPBlocks.FrostWeatheredStoneSlabDouble
         );
         ((BlockHarleskinnSlab)SRPBlocks.FrostWeatheredStoneSlabDouble).setHalfPartner((BlockSlab)SRPBlocks.FrostWeatheredStoneSlab);
         SRPBlocks.InfestedStoneBrickSlabDouble = new BlockHarleskinnSlab(
            true, "infested_stone_brick_slab_double", "pickaxe", 1, SoundType.field_185851_d, 2.0F, 6.0F, true
         );
         SRPBlocks.InfestedStoneBrickSlab = new BlockHarleskinnSlab(
            false, "infested_stone_brick_slab", "pickaxe", 1, SoundType.field_185851_d, 2.0F, 6.0F, true, (BlockSlab)SRPBlocks.InfestedStoneBrickSlabDouble
         );
         ((BlockHarleskinnSlab)SRPBlocks.InfestedStoneBrickSlabDouble).setHalfPartner((BlockSlab)SRPBlocks.InfestedStoneBrickSlab);
         SRPBlocks.InfestedTerracottaSlabDouble = new BlockHarleskinnSlab(
            true, "infested_terracotta_slab_double", "pickaxe", 1, SoundType.field_185851_d, 1.25F, 4.2F, true
         );
         SRPBlocks.InfestedTerracottaSlab = new BlockHarleskinnSlab(
            false, "infested_terracotta_slab", "pickaxe", 1, SoundType.field_185851_d, 1.25F, 4.2F, true, (BlockSlab)SRPBlocks.InfestedTerracottaSlabDouble
         );
         ((BlockHarleskinnSlab)SRPBlocks.InfestedTerracottaSlabDouble).setHalfPartner((BlockSlab)SRPBlocks.InfestedTerracottaSlab);
         SRPBlocks.PolishedInfestedStoneSlabDouble = new BlockHarleskinnSlab(
            true, "polished_infested_stone_slab_double", "pickaxe", 1, SoundType.field_185851_d, 2.0F, 6.0F, true
         );
         SRPBlocks.PolishedInfestedStoneSlab = new BlockHarleskinnSlab(
            false,
            "polished_infested_stone_slab",
            "pickaxe",
            1,
            SoundType.field_185851_d,
            2.0F,
            6.0F,
            true,
            (BlockSlab)SRPBlocks.PolishedInfestedStoneSlabDouble
         );
         ((BlockHarleskinnSlab)SRPBlocks.PolishedInfestedStoneSlabDouble).setHalfPartner((BlockSlab)SRPBlocks.PolishedInfestedStoneSlab);
         SRPBlocks.ResidueBrickSlabDouble = new BlockHarleskinnSlab(true, "residue_brick_slab_double", "pickaxe", 1, SoundType.field_185851_d, 2.0F, 6.0F, true);
         SRPBlocks.ResidueBrickSlab = new BlockHarleskinnSlab(
            false, "residue_brick_slab", "pickaxe", 1, SoundType.field_185851_d, 2.0F, 6.0F, true, (BlockSlab)SRPBlocks.ResidueBrickSlabDouble
         );
         ((BlockHarleskinnSlab)SRPBlocks.ResidueBrickSlabDouble).setHalfPartner((BlockSlab)SRPBlocks.ResidueBrickSlab);
         SRPBlocks.InfestedSandstoneSlabDouble = new BlockHarleskinnSlab(
            true, "infested_sandstone_slab_double", "pickaxe", 1, SoundType.field_185851_d, 0.8F, 4.0F, true
         );
         SRPBlocks.InfestedSandstoneSlab = new BlockHarleskinnSlab(
            false, "infested_sandstone_slab", "pickaxe", 1, SoundType.field_185851_d, 0.8F, 4.0F, true, (BlockSlab)SRPBlocks.InfestedSandstoneSlabDouble
         );
         ((BlockHarleskinnSlab)SRPBlocks.InfestedSandstoneSlabDouble).setHalfPartner((BlockSlab)SRPBlocks.InfestedSandstoneSlab);
         SRPBlocks.GothPlankSlabDouble = new BlockHarleskinnSlab(true, "goth_plank_slab_double", "axe", 1, SoundType.field_185851_d, 2.0F, 3.0F, true);
         SRPBlocks.GothPlankSlab = new BlockHarleskinnSlab(
            false, "goth_plank_slab", "axe", 1, SoundType.field_185851_d, 2.0F, 3.0F, true, (BlockSlab)SRPBlocks.GothPlankSlabDouble
         );
         ((BlockHarleskinnSlab)SRPBlocks.GothPlankSlabDouble).setHalfPartner((BlockSlab)SRPBlocks.GothPlankSlab);
         SRPBlocks.BruisewoodPlankSlabDouble = new BlockHarleskinnSlab(
            true, "bruisewood_plank_slab_double", "axe", 1, SoundType.field_185851_d, 2.0F, 3.0F, true
         );
         SRPBlocks.BruisewoodPlankSlab = new BlockHarleskinnSlab(
            false, "bruisewood_plank_slab", "axe", 1, SoundType.field_185851_d, 2.0F, 3.0F, true, (BlockSlab)SRPBlocks.BruisewoodPlankSlabDouble
         );
         ((BlockHarleskinnSlab)SRPBlocks.BruisewoodPlankSlabDouble).setHalfPartner((BlockSlab)SRPBlocks.BruisewoodPlankSlab);
         SRPBlocks.ConsumedPlankSlabDouble = new BlockHarleskinnSlab(true, "consumed_plank_slab_double", "axe", 1, SoundType.field_185851_d, 2.0F, 3.0F, true);
         SRPBlocks.ConsumedPlankSlab = new BlockHarleskinnSlab(
            false, "consumed_plank_slab", "axe", 1, SoundType.field_185851_d, 2.0F, 3.0F, true, (BlockSlab)SRPBlocks.ConsumedPlankSlabDouble
         );
         ((BlockHarleskinnSlab)SRPBlocks.ConsumedPlankSlabDouble).setHalfPartner((BlockSlab)SRPBlocks.ConsumedPlankSlab);
         SRPBlocks.InfestedPlankSlabDouble = new BlockHarleskinnSlab(true, "infested_plank_slab_double", "axe", 1, SoundType.field_185851_d, 2.0F, 3.0F, true);
         SRPBlocks.InfestedPlankSlab = new BlockHarleskinnSlab(
            false, "infested_plank_slab", "axe", 1, SoundType.field_185851_d, 2.0F, 3.0F, true, (BlockSlab)SRPBlocks.InfestedPlankSlabDouble
         );
         ((BlockHarleskinnSlab)SRPBlocks.InfestedPlankSlabDouble).setHalfPartner((BlockSlab)SRPBlocks.InfestedPlankSlab);
         SRPBlocks.PolandSkinSlabDouble = new BlockHarleskinnSlab(true, "poland_skin_slab_double", "axe", 1, SRPSoundTypes.FLESH, 1.0F, 2.0F, true);
         SRPBlocks.PolandSkinSlab = new BlockHarleskinnSlab(
            false, "poland_skin_slab", "axe", 1, SRPSoundTypes.FLESH, 1.0F, 2.0F, true, (BlockSlab)SRPBlocks.PolandSkinSlabDouble
         );
         ((BlockHarleskinnSlab)SRPBlocks.PolandSkinSlabDouble).setHalfPartner((BlockSlab)SRPBlocks.PolandSkinSlab);
         SRPBlocks.LocsBlockSlabDouble = new BlockHarleskinnSlab(true, "locs_block_slab_double", "axe", 1, SoundType.field_185851_d, 1.5F, 3.0F, true);
         SRPBlocks.LocsBlockSlab = new BlockHarleskinnSlab(
            false, "locs_block_slab", "axe", 1, SoundType.field_185851_d, 1.5F, 3.0F, true, (BlockSlab)SRPBlocks.LocsBlockSlabDouble
         );
         ((BlockHarleskinnSlab)SRPBlocks.LocsBlockSlabDouble).setHalfPartner((BlockSlab)SRPBlocks.LocsBlockSlab);
         SRPBlocks.CookedFleshSlabDouble = new BlockHarleskinnSlab(true, "cooked_flesh_slab_double", "axe", 1, SRPSoundTypes.FLESH, 1.5F, 3.0F, true);
         SRPBlocks.CookedFleshSlab = new BlockHarleskinnSlab(
            false, "cooked_flesh_slab", "axe", 1, SRPSoundTypes.FLESH, 1.5F, 3.0F, true, (BlockSlab)SRPBlocks.CookedFleshSlabDouble
         );
         ((BlockHarleskinnSlab)SRPBlocks.CookedFleshSlabDouble).setHalfPartner((BlockSlab)SRPBlocks.CookedFleshSlab);
         SRPBlocks.FleshSlabDouble = new BlockHarleskinnSlab(true, "flesh_slab_double", "axe", 1, SRPSoundTypes.FLESH, 1.5F, 3.0F, true);
         SRPBlocks.FleshSlab = new BlockHarleskinnSlab(
            false, "flesh_slab", "axe", 1, SRPSoundTypes.FLESH, 1.5F, 3.0F, true, (BlockSlab)SRPBlocks.FleshSlabDouble
         );
         ((BlockHarleskinnSlab)SRPBlocks.FleshSlabDouble).setHalfPartner((BlockSlab)SRPBlocks.FleshSlab);
         SRPBlocks.HarleskinnStairs = new BlockHarleskinnStairs(SRPBlocks.HarleskinnBlock.func_176223_P(), "harleskinn_stairs");
         SRPBlocks.BruisewoodPlankStairs = new BlockHarleskinnStairs(SRPBlocks.HarleskinnBlock.func_176223_P(), "bruisewood_plank_stairs");
         SRPBlocks.InfestedSandstoneStairs = new BlockHarleskinnStairs(SRPBlocks.HarleskinnBlock.func_176223_P(), "infested_sandstone_stairs");
         SRPBlocks.GothPlanksStairs = new BlockHarleskinnStairs(SRPBlocks.HarleskinnBlock.func_176223_P(), "goth_planks_stairs");
         SRPBlocks.DeadheadPlankStairs = new BlockHarleskinnStairs(SRPBlocks.HarleskinnBlock.func_176223_P(), "deadhead_plank_stairs");
         SRPBlocks.ResidueStairs = new BlockHarleskinnStairs(SRPBlocks.HarleskinnBlock.func_176223_P(), "residue_stairs");
         SRPBlocks.InfestedPlanksStairs = new BlockHarleskinnStairs(SRPBlocks.HarleskinnBlock.func_176223_P(), "infested_planks_stairs");
         SRPBlocks.ConsumedPlanksStairs = new BlockHarleskinnStairs(SRPBlocks.HarleskinnBlock.func_176223_P(), "consumed_planks_stairs");
         SRPBlocks.InfestedStoneBricksStairs = new BlockHarleskinnStairs(SRPBlocks.HarleskinnBlock.func_176223_P(), "infested_stone_bricks_stairs");
         SRPBlocks.InfestedPolishedStoneBricksStairs = new BlockHarleskinnStairs(
            SRPBlocks.HarleskinnBlock.func_176223_P(), "infested_polished_stone_bricks_stairs"
         );
         SRPBlocks.WheatheredBricksStairs = new BlockHarleskinnStairs(SRPBlocks.HarleskinnBlock.func_176223_P(), "wheathered_bricks_stairs");
         SRPBlocks.WheatheredCobblestoneStairs = new BlockHarleskinnStairs(SRPBlocks.HarleskinnBlock.func_176223_P(), "wheathered_cobblestone_stairs");
         SRPBlocks.FrostWeatheredStoneStairs = new BlockHarleskinnStairs(SRPBlocks.HarleskinnBlock.func_176223_P(), "frost_weathered_stone_stairs");
         SRPBlocks.FleshStairs = new BlockHarleskinnStairs(SRPBlocks.HarleskinnBlock.func_176223_P(), "flesh_stairs");
         SRPBlocks.CookedFleshStairs = new BlockHarleskinnStairs(SRPBlocks.HarleskinnBlock.func_176223_P(), "cooked_flesh_stairs");
         SRPBlocks.InfestedStoneStairs = new BlockHarleskinnStairs(SRPBlocks.HarleskinnBlock.func_176223_P(), "infested_stone_stairs");
         SRPBlocks.GothDoor = new SRPDoor(Material.field_151575_d, "goth_door");
         SRPBlocks.BrusewoodDoor = new SRPDoor(Material.field_151575_d, "brusewood_door");
         SRPBlocks.ConsumedDoor = new SRPDoor(Material.field_151575_d, "consumed_door");
         SRPBlocks.Thornshade = new BlockThornshade();
         SRPBlocks.diseasedSponge = new BlockDiseasedSponge();
         SRPBlocks.BrusewoodTrapdoor = new SRPTrapDoor("brusewood_trapdoor");
         SRPBlocks.ConsumedTrapdoor = new SRPTrapDoor(Material.field_151573_f, "consumed_trapdoor");
         SRPBlocks.ResidueBrickWall = new BlockWallBase("residue_wall", true, SRPBlocks.ParasitePlank);
         SRPBlocks.InfestedPlankWall = new BlockWallBase("infested_plank_wall", true, SRPBlocks.ParasitePlank);
         SRPBlocks.BruisewoodPlankWall = new BlockWallBase("bruisewood_plank_wall", true, SRPBlocks.ParasitePlank);
         SRPBlocks.PolishedInfestedStoneWall = new BlockWallBase("polished_infested_stone_wall", true, SRPBlocks.ParasiteRubble);
         SRPBlocks.InfestedStoneBrickWall = new BlockWallBase("infested_stone_brick_wall", true, SRPBlocks.ParasiteRubble);
         SRPBlocks.GothPlankWall = new BlockWallBase("goth_plank_wall", true, SRPBlocks.ParasitePlank);
         SRPBlocks.InfestedSandstoneWall = new BlockWallBase("infested_sandstone_wall", true, SRPBlocks.ParasiteRubble);
         SRPBlocks.ConsumedPlankWall = new BlockWallBase("consumed_plank_wall", true, SRPBlocks.ParasitePlank);
         SRPBlocks.ParasitePlankDeadheadWall = new BlockWallBase("parasiteplank_deadhead_wall", true, SRPBlocks.ParasitePlank);
         SRPBlocks.ParasiteRubbleWeathbWall = new BlockWallBase("parasiterubble_weathb_wall", true, SRPBlocks.ParasiteRubble);
         SRPBlocks.ParasiteRubbleDenseColonyWall = new BlockWallBase("parasiterubbledense_colony_wall", true, SRPBlocks.ParasiteRubbleDense);
         SRPBlocks.ParasiteRubbleWeathfsWall = new BlockWallBase("parasiterubble_weathfs_wall", true, SRPBlocks.ParasiteRubble);
         SRPBlocks.ParasiteRubbleDenseBiomeWall = new BlockWallBase("parasiterubbledense_biome_wall", true, SRPBlocks.ParasiteRubbleDense);
         SRPBlocks.ParasiteRubbleWeathbcWall = new BlockWallBase("parasiterubble_weathbc_wall", true, SRPBlocks.ParasiteRubble);
         SRPBlocks.ParasiteRubbleBricksWall = new BlockWallBase("parasiterubble_bricks_wall", true, SRPBlocks.ParasiteRubble);
         SRPBlocks.ParasiteCanisterBagWall = new BlockWallBase("parasitecanister_bag_wall", true, SRPBlocks.ParasiteCanister);
         SRPBlocks.InfestedRubbleWall = new BlockWallBase("infestedrubble_wall", true, SRPBlocks.ParasiteRubble);
         SRPBlocks.InfestedStainWall = new BlockWallBase("infestedstain_wall", true, SRPBlocks.ParasiteStain);
         SRPBlocks.ParasiteStainFleshWall = new BlockWallBase("parasitestain_flesh_wall", true, SRPBlocks.ParasiteStain);
         SRPBlocks.ParasiteRubbleMetalWall = new BlockWallBase("parasiterubble_metal_wall", true, SRPBlocks.ParasiteRubble);
         SRPBlocks.ResidueBrickWall.setHarvestLevel("pickaxe", 1);
         SRPBlocks.InfestedPlankWall.setHarvestLevel("pickaxe", 1);
         SRPBlocks.BruisewoodPlankWall.setHarvestLevel("pickaxe", 1);
         SRPBlocks.InfestedStoneBrickWall.setHarvestLevel("pickaxe", 1);
         SRPBlocks.GothPlankWall.setHarvestLevel("pickaxe", 1);
         SRPBlocks.InfestedSandstoneWall.setHarvestLevel("pickaxe", 1);
         SRPBlocks.ConsumedPlankWall.setHarvestLevel("pickaxe", 1);
         SRPBlocks.ParasitePlankDeadheadWall.setHarvestLevel("pickaxe", 1);
         SRPBlocks.ParasiteRubbleWeathbWall.setHarvestLevel("pickaxe", 1);
         SRPBlocks.ParasiteRubbleDenseColonyWall.setHarvestLevel("pickaxe", 1);
         SRPBlocks.ParasiteRubbleWeathfsWall.setHarvestLevel("pickaxe", 1);
         SRPBlocks.ParasiteRubbleDenseBiomeWall.setHarvestLevel("pickaxe", 1);
         SRPBlocks.ParasiteRubbleWeathbcWall.setHarvestLevel("pickaxe", 1);
         SRPBlocks.ParasiteRubbleBricksWall.setHarvestLevel("pickaxe", 1);
         SRPBlocks.ParasiteCanisterBagWall.setHarvestLevel("pickaxe", 1);
         SRPBlocks.InfestedRubbleWall.setHarvestLevel("pickaxe", 1);
         SRPBlocks.InfestedStainWall.setHarvestLevel("pickaxe", 1);
         SRPBlocks.ParasiteStainFleshWall.setHarvestLevel("pickaxe", 1);
         SRPBlocks.ParasiteRubbleMetalWall.setHarvestLevel("pickaxe", 1);
         SRPBlocks.ParasiteRubbleBoneStair = new BlockStairBase(
            "parasiterubble_bone", true, SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, BlockParasiteRubble.EnumType.BONE)
         );
         SRPBlocks.ParasiteRubbleFleshStair = new BlockStairBase(
            "parasiterubble_flesh",
            true,
            SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, BlockParasiteRubble.EnumType.FLESH)
         );
         SRPBlocks.ParasiteRubbleStoneStair = new BlockStairBase(
            "parasiterubble_stone",
            true,
            SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, BlockParasiteRubble.EnumType.STONE)
         );
         SRPBlocks.ParasiteRubbleStoneDebrisStair = new BlockStairBase(
            "parasiterubble_stonedebris",
            true,
            SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, BlockParasiteRubble.EnumType.STONEDEBRIS)
         );
         SRPBlocks.ParasiteRubbleWoodStair = new BlockStairBase(
            "parasiterubble_wood", true, SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, BlockParasiteRubble.EnumType.WOOD)
         );
         SRPBlocks.ParasiteRubbleBrickStair = new BlockStairBase(
            "parasiterubble_bricks",
            true,
            SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, BlockParasiteRubble.EnumType.BRICKS)
         );
         SRPBlocks.ParasiteRubbleMetalStair = new BlockStairBase(
            "parasiterubble_metal",
            true,
            SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, BlockParasiteRubble.EnumType.METAL)
         );
         SRPBlocks.ParasiteRubbleObsidianStair = new BlockStairBase(
            "parasiterubble_obsidian",
            true,
            SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, BlockParasiteRubble.EnumType.OBSIDIAN)
         );
         SRPBlocks.ParasiteRubbleFungusStair = new BlockStairBase(
            "parasiterubble_fungus",
            true,
            SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, BlockParasiteRubble.EnumType.FUNGUS)
         );
         SRPBlocks.ParasiteStainFleshStair = new BlockStairBase(
            "parasitestain_flesh", true, SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.FLESH)
         );
         SRPBlocks.ParasiteStainDirtStair = new BlockStairBase(
            "parasitestain_dirt", true, SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.DIRT)
         );
         SRPBlocks.ParasiteStainMudStair = new BlockStairBase(
            "parasitestain_mud", true, SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.MUD)
         );
         SRPBlocks.ParasiteStainFeelerStair = new BlockStairBase(
            "parasitestain_feeler", true, SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.FEELER)
         );
         SRPBlocks.ParasiteRubbleDenseWallStair = new BlockStairBase(
            "parasiterubbledense_wall",
            true,
            SRPBlocks.ParasiteRubbleDense.func_176223_P().func_177226_a(BlockParasiteRubbleDense.VARIANT, BlockParasiteRubbleDense.EnumType.WALL)
         );
         SRPBlocks.ParasiteRubbleDenseNodeStair = new BlockStairBase(
            "parasiterubbledense_biome",
            true,
            SRPBlocks.ParasiteRubbleDense.func_176223_P().func_177226_a(BlockParasiteRubbleDense.VARIANT, BlockParasiteRubbleDense.EnumType.BIOME)
         );
         SRPBlocks.ParasiteRubbleDenseColonyStair = new BlockStairBase(
            "parasiterubbledense_colony",
            true,
            SRPBlocks.ParasiteRubbleDense.func_176223_P().func_177226_a(BlockParasiteRubbleDense.VARIANT, BlockParasiteRubbleDense.EnumType.COLONY)
         );
         SRPBlocks.ParasiteTrunkBallStair = new BlockStairBase(
            "parasitetrunk_ball", true, SRPBlocks.ParasiteTrunk.func_176223_P().func_177226_a(BlockParasiteTrunk.VARIANT, BlockParasiteTrunk.EnumType.BALL)
         );
         SRPBlocks.ParasiteTrunkTreeStair = new BlockStairBase(
            "parasitetrunk_tree", true, SRPBlocks.ParasiteTrunk.func_176223_P().func_177226_a(BlockParasiteTrunk.VARIANT, BlockParasiteTrunk.EnumType.TREE)
         );
         SRPBlocks.ParasiteTrunkPlantStair = new BlockStairBase(
            "parasitetrunk_plant", true, SRPBlocks.ParasiteTrunk.func_176223_P().func_177226_a(BlockParasiteTrunk.VARIANT, BlockParasiteTrunk.EnumType.PLANT)
         );
         SRPBlocks.ParasiteRubbleSlabHalf = new BlockSlabRubble.BlockSlabRubbleHalf(
            Material.field_151576_e, "parasiterubble", 2.3F, true, false, SRPBlocks.ParasiteRubbleSlabHalf, SRPBlocks.ParasiteRubbleSlabDouble
         );
         SRPBlocks.ParasiteRubbleSlabDouble = new BlockSlabRubble.BlockSlabRubbleDouble(
            Material.field_151576_e, "parasiterubble", 2.3F, false, false, SRPBlocks.ParasiteRubbleSlabHalf
         );
         SRPBlocks.ParasiteStainSlabHalf = new BlockSlabStain.BlockSlabStainHalf(
            Material.field_151578_c, "parasitestain", 0.8F, true, false, SRPBlocks.ParasiteStainSlabHalf, SRPBlocks.ParasiteStainSlabDouble
         );
         SRPBlocks.ParasiteStainSlabDouble = new BlockSlabStain.BlockSlabStainDouble(
            Material.field_151578_c, "parasitestain", 0.8F, false, false, SRPBlocks.ParasiteStainSlabHalf
         );
         SRPBlocks.ParasiteVine = new BlockVineBase("parasitetendril", 0.5F, true, true);
         SRPBlocks.ParasiteFog = new BlockParasiteFog("parasitefog");
         SRPBlocks.DeadBlood = new BlockFluid("deadblood", SRPFluids.DEADBLOOD_FLUID, SRPBlocks.PARASITEBLOOD, true);
         SRPBlocks.SRPWeb = new BlockWebBase("srpweb");
         SRPBlocks.evolutionLure = new BlockEvolutionLure(Material.field_151576_e, "evolutionlure", 1.0F, true, true);
         SRPBlocks.buglin = new BlockBuglin("tunnel", 0.1F, true, true, 0.1F);
         SRPBlocks.ParasiteCanisterActive = new BlockParasiteCanisterC(Material.field_151576_e, "canisteractive", 1.5F, true, true);
         SRPBlocks.dodN = new BlockDod("dispatchern", 0.1F, true, true, 0.1F);
         SRPBlocks.InfestedOre = new BlockInfestedOre(Material.field_151578_c, "infestedore", 3.5F, true, false);
         SRPBlocks.InfestedSand = new BlockFallingInfestedStain(Material.field_151578_c, "infestedsand", 1.0F, true, true);
         SRPBlocks.goreSim = new BlockGore("goresim");
         SRPBlocks.gorePri = new BlockGore("gorepri");
         SRPBlocks.goreAda = new BlockGore("goreada");
         SRPBlocks.gorePur = new BlockGore("gorepur");
         SRPBlocks.goreFer = new BlockGore("gorefer");
         SRPBlocks.goreMar = new BlockGore("goremar");
         event.getRegistry().registerAll(SRPBlocks.SRP_BLOCKS.toArray(new Block[0]));
      }

      @SubscribeEvent
      public static void registerItemBlocks(Register<Item> event) {
         itemRegistry = event.getRegistry();
         registerItemBlock(SRPBlocks.INFESTED_FURNACE);
         registerItemBlock(SRPBlocks.ParasiteRubbleSlabHalf);
         registerItemBlock(SRPBlocks.ParasiteStainSlabHalf);
         registerItemBlock(SRPBlocks.RelayBase);
         registerItemBlock(SRPBlocks.RelayMiddle);
         registerItemBlock(SRPBlocks.RelayRoof);
         registerItemBlock(SRPBlocks.RELAY_CONTROLLER);
         registerItemBlock(SRPBlocks.FleshStairs);
         registerItemBlock(SRPBlocks.CookedFleshStairs);
         registerItemBlock(SRPBlocks.HarleskinnStairs);
         registerItemBlock(SRPBlocks.BruisewoodPlankStairs);
         registerItemBlock(SRPBlocks.InfestedSandstoneStairs);
         registerItemBlock(SRPBlocks.GothPlanksStairs);
         registerItemBlock(SRPBlocks.InfestedPlanksStairs);
         registerItemBlock(SRPBlocks.FrostWeatheredStoneStairs);
         registerItemBlock(SRPBlocks.ConsumedPlanksStairs);
         registerItemBlock(SRPBlocks.InfestedStoneBricksStairs);
         registerItemBlock(SRPBlocks.InfestedPolishedStoneBricksStairs);
         registerItemBlock(SRPBlocks.InfestedStoneStairs);
         registerItemBlock(SRPBlocks.WheatheredBricksStairs);
         registerItemBlock(SRPBlocks.WheatheredCobblestoneStairs);
         registerItemBlock(SRPBlocks.DeadheadPlankStairs);
         registerItemBlock(SRPBlocks.ResidueStairs);
         registerItemBlock(SRPBlocks.HarleskinnFence);
         registerItemBlock(SRPBlocks.InfestedFence);
         registerItemBlock(SRPBlocks.DeadheadFence);
         registerItemBlock(SRPBlocks.GothFence);
         registerItemBlock(SRPBlocks.ConsumedFence);
         registerItemBlock(SRPBlocks.BrusewoodFence);
         registerItemBlock(SRPBlocks.FleshFence);
         registerItemBlock(SRPBlocks.CookedFleshFence);
      }

      private static void registerItemBlock(Block block) {
         if (!(block instanceof BlockHarleskinnSlab)) {
            Item itemBlock;
            if (block == SRPBlocks.RelayBase || block == SRPBlocks.RelayMiddle || block == SRPBlocks.RelayRoof || block == SRPBlocks.RELAY_CONTROLLER) {
               itemBlock = new ItemBlockWithTooltip(block);
            } else if (block instanceof BlockSlabBase) {
               itemBlock = new ItemSlabBase(block, (IMetaName)block, ((BlockSlabBase)block).getHalfBlock(), ((BlockSlabBase)block).getDoubleBlock());
            } else if (block instanceof IMetaName) {
               itemBlock = ((IMetaName)block).getItemBlock();
            } else {
               itemBlock = new ItemBlock(block);
            }

            ResourceLocation registryName = block.getRegistryName();
            if (itemBlock.getRegistryName() == null) {
               itemBlock.setRegistryName(registryName);
            }

            itemRegistry.register(itemBlock);
         }
      }
   }
}
