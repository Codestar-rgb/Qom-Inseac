package com.dhanantry.scapeandrunparasites.proxy;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.advancements.BeckonStage4DetectedAdvancement;
import com.dhanantry.scapeandrunparasites.advancements.DispatcherStage4DetectedAdvancement;
import com.dhanantry.scapeandrunparasites.advancements.EnemyOfMyEnemyAdvancement;
import com.dhanantry.scapeandrunparasites.advancements.HuntSeasonAdvancement;
import com.dhanantry.scapeandrunparasites.advancements.RupterKillMilestoneAdvancement;
import com.dhanantry.scapeandrunparasites.bestiary.BestiaryCombatStatsHandler;
import com.dhanantry.scapeandrunparasites.bestiary.PotionDiscoveryHandler;
import com.dhanantry.scapeandrunparasites.bestiary.SRPBestiaryRegistry;
import com.dhanantry.scapeandrunparasites.bestiary.blocks.BlockDiscoveryHandler;
import com.dhanantry.scapeandrunparasites.bestiary.blocks.SRPBlockCompendiumRegistry;
import com.dhanantry.scapeandrunparasites.bestiary.cap.BestiaryCapEvents;
import com.dhanantry.scapeandrunparasites.bestiary.cap.BestiaryCapability;
import com.dhanantry.scapeandrunparasites.bestiary.event.BestiaryEvents;
import com.dhanantry.scapeandrunparasites.bestiary.net.BestiaryNetwork;
import com.dhanantry.scapeandrunparasites.block.BlockLeafLike;
import com.dhanantry.scapeandrunparasites.block.TileEntityFogNullifier;
import com.dhanantry.scapeandrunparasites.block.TileEntityInfestationPurifier;
import com.dhanantry.scapeandrunparasites.block.TileEntityParasiteBarrier;
import com.dhanantry.scapeandrunparasites.client.AssimilatedPumpkinOverlayHandler;
import com.dhanantry.scapeandrunparasites.client.ClientQlipShake;
import com.dhanantry.scapeandrunparasites.client.DeadBloodOverlayHandler;
import com.dhanantry.scapeandrunparasites.client.DeadBloodSwimHandler;
import com.dhanantry.scapeandrunparasites.client.celestial.CelestialObjectRegistry;
import com.dhanantry.scapeandrunparasites.compatibility.ModCompatibility;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityEmanaAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityLumAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityLeer;
import com.dhanantry.scapeandrunparasites.entity.monster.derived.EntityHeblu;
import com.dhanantry.scapeandrunparasites.entity.monster.hijacked.EntityHiBlaze;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityButhol;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityViin;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfDragonE;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfSquid;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityEmana;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityIki;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityLum;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityAlafha;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityOmboo;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent.EntityElvia;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent.EntityJinjo;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent.EntityLencia;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent.EntityTenn;
import com.dhanantry.scapeandrunparasites.events.DeadBloodBottleHandler;
import com.dhanantry.scapeandrunparasites.events.FishingHooksSRP;
import com.dhanantry.scapeandrunparasites.feature.EscapeOnDeathHandler;
import com.dhanantry.scapeandrunparasites.feature.EscapeRespawnHandler;
import com.dhanantry.scapeandrunparasites.gui.SRPGuiHandler;
import com.dhanantry.scapeandrunparasites.init.SRPBiomes;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPEntities;
import com.dhanantry.scapeandrunparasites.init.SRPFluids;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.init.SRPSmelting;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.init.SRPSpawning;
import com.dhanantry.scapeandrunparasites.item.VengeanceGrappleHandler;
import com.dhanantry.scapeandrunparasites.network.CelestialNightJoinSync;
import com.dhanantry.scapeandrunparasites.network.CelestialPhaseSyncHandler;
import com.dhanantry.scapeandrunparasites.network.CommandHarlequinConvert;
import com.dhanantry.scapeandrunparasites.network.CommandHarlequinHere;
import com.dhanantry.scapeandrunparasites.network.CommandHarlequinScatter;
import com.dhanantry.scapeandrunparasites.network.CommandSRPGuide;
import com.dhanantry.scapeandrunparasites.network.CommandSRPGuideClear;
import com.dhanantry.scapeandrunparasites.network.CommandSRPHelp;
import com.dhanantry.scapeandrunparasites.network.ExtremeSnowNetwork;
import com.dhanantry.scapeandrunparasites.network.SRPCommandBestiaryStats;
import com.dhanantry.scapeandrunparasites.network.SRPCommandColony;
import com.dhanantry.scapeandrunparasites.network.SRPCommandDislodgment;
import com.dhanantry.scapeandrunparasites.network.SRPCommandEvolution;
import com.dhanantry.scapeandrunparasites.network.SRPCommandGeneration;
import com.dhanantry.scapeandrunparasites.network.SRPCommandGuiDistortion;
import com.dhanantry.scapeandrunparasites.network.SRPCommandNode;
import com.dhanantry.scapeandrunparasites.network.SRPCommandOrigin;
import com.dhanantry.scapeandrunparasites.network.SRPCommandRoot;
import com.dhanantry.scapeandrunparasites.network.SRPCommandUDevelopment;
import com.dhanantry.scapeandrunparasites.network.SRPNetwork;
import com.dhanantry.scapeandrunparasites.network.msg.AbstractPacket;
import com.dhanantry.scapeandrunparasites.potion.Recipe;
import com.dhanantry.scapeandrunparasites.recipes.InfuseRecipes;
import com.dhanantry.scapeandrunparasites.recipes.SRPInfuserFurnaceRecipeInit;
import com.dhanantry.scapeandrunparasites.tileentity.TileEntityInfuserFurnace;
import com.dhanantry.scapeandrunparasites.tileentity.TileEntityRelayController;
import com.dhanantry.scapeandrunparasites.util.CommandForceCelestial;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.SRPCommandSummonNidus;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import com.dhanantry.scapeandrunparasites.util.handlers.InfestedBonemealHandler;
import com.dhanantry.scapeandrunparasites.util.handlers.SRPPacketHandler;
import com.dhanantry.scapeandrunparasites.util.handlers.SRPRegistryHandlers;
import com.dhanantry.scapeandrunparasites.util.handlers.ShrimpDropHandler;
import com.dhanantry.scapeandrunparasites.world.celestial.CelestialEffectHooks;
import com.dhanantry.scapeandrunparasites.world.celestial.CelestialEffectRegistry;
import com.dhanantry.scapeandrunparasites.world.celestial.effects.EffectTwentySeven;
import com.dhanantry.scapeandrunparasites.world.gen.HarlequinRockBushGen;
import net.minecraft.block.Block;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityLiving.SpawnPlacementType;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
   public static Configuration config;
   public static Configuration configMobs;
   public static Configuration configSystems;
   public static Configuration configWorld;

   public void preInit(FMLPreInitializationEvent e) {
      GameRegistry.registerTileEntity(TileEntityRelayController.class, SRPMain.rl("relaycontroller"));
      GameRegistry.registerTileEntity(TileEntityInfestationPurifier.class, SRPMain.rl("infestation_purifier_te"));
      GameRegistry.registerTileEntity(TileEntityParasiteBarrier.class, SRPMain.rl("parasite_barrier_te"));
      GameRegistry.registerTileEntity(TileEntityFogNullifier.class, SRPMain.rl("fog_nullifier_te"));
      MinecraftForge.EVENT_BUS.register(new CelestialNightJoinSync());
      GameRegistry.registerTileEntity(TileEntityInfuserFurnace.class, SRPMain.rl("infuser_furnace"));
      SRPFluids.init();
      SRPRegistryHandlers.initEvents();
      SRPConfig.initConfig(e);
      SRPConfigSystems.initConfig(e);
      SRPConfigMobs.initConfig(e);
      SRPConfigWorld.initConfig(e);
      SRPAttributes.reset();
      SRPAttributes.init();
      SRPSounds.init();
      SRPPacketHandler.init();
      SRPEntities.registerTileEntities();
      ModCompatibility.preInit();
      SRPNetwork.register();
      ExtremeSnowNetwork.init();
      NetworkRegistry.INSTANCE.registerGuiHandler(SRPMain.instance, new SRPGuiHandler());
      MinecraftForge.EVENT_BUS.register(new BestiaryCombatStatsHandler());
      MinecraftForge.EVENT_BUS.register(VengeanceGrappleHandler.get());
      MinecraftForge.EVENT_BUS.register(new DeadBloodBottleHandler());
      BestiaryCapability.register();
      MinecraftForge.EVENT_BUS.register(new BestiaryCapEvents());
      MinecraftForge.EVENT_BUS.register(new InfestedBonemealHandler());
      MinecraftForge.EVENT_BUS.register(new BestiaryEvents());
      MinecraftForge.EVENT_BUS.register(new BlockDiscoveryHandler());
      MinecraftForge.EVENT_BUS.register(new ShrimpDropHandler());
      FishingHooksSRP.register();
      MinecraftForge.EVENT_BUS.register(new CelestialPhaseSyncHandler());
      CelestialEffectRegistry.register("twenty_seven", new EffectTwentySeven());
      MinecraftForge.EVENT_BUS.register(new CelestialEffectHooks());
      if (FMLCommonHandler.instance().getSide().isClient()) {
         MinecraftForge.EVENT_BUS.register(ClientQlipShake.INSTANCE);
         MinecraftForge.EVENT_BUS.register(new DeadBloodSwimHandler());
         MinecraftForge.EVENT_BUS.register(new DeadBloodOverlayHandler());
      }
   }

   public void init(FMLInitializationEvent e) {
      GameRegistry.registerWorldGenerator(new HarlequinRockBushGen(), 18);
      SRPSmelting.register();
      Recipe.init();
      CelestialObjectRegistry.init();
      MinecraftForge.EVENT_BUS.register(new AssimilatedPumpkinOverlayHandler());
      MinecraftForge.EVENT_BUS.register(new EscapeOnDeathHandler());
      MinecraftForge.EVENT_BUS.register(new EscapeRespawnHandler());
      SRPInfuserFurnaceRecipeInit.init();
      MinecraftForge.EVENT_BUS.register(new PotionDiscoveryHandler());
      BeckonStage4DetectedAdvancement.register();
      RupterKillMilestoneAdvancement.register();
      EnemyOfMyEnemyAdvancement.register();
      HuntSeasonAdvancement.register();
      DispatcherStage4DetectedAdvancement.register();
      BestiaryNetwork.register();
      Blocks.field_150480_ab.func_180686_a(SRPBlocks.InfestRemain, 60, 200);
      InfuseRecipes.instance().add(new ItemStack(Items.field_151042_j), new ItemStack(SRPItems.DEADBLOOD_FLUID), new ItemStack(SRPItems.semiorganicingot));

      for (Block b : SRPBlocks.SRP_BLOCKS) {
         if (b instanceof BlockLeafLike) {
            Blocks.field_150480_ab.func_180686_a(b, 30, 60);
         }
      }
   }

   public void postInit(FMLPostInitializationEvent e) {
      config.save();
      configMobs.save();
      configSystems.save();
      configWorld.save();
      SRPSpawning.init();
      SRPBlocks.init();
      SRPBestiaryRegistry.registerDefaults();
      SRPBlockCompendiumRegistry.registerDefaults();
      SRPBiomes.clearMobSpawnList();
      SRPSpawning.initBiome();
      ModCompatibility.postInit();
      EntitySpawnPlacementRegistry.setPlacementType(EntityButhol.class, SpawnPlacementType.IN_AIR);
      EntitySpawnPlacementRegistry.setPlacementType(EntityViin.class, SpawnPlacementType.IN_AIR);
      EntitySpawnPlacementRegistry.setPlacementType(EntityInfDragonE.class, SpawnPlacementType.IN_AIR);
      EntitySpawnPlacementRegistry.setPlacementType(EntityInfSquid.class, SpawnPlacementType.IN_WATER);
      EntitySpawnPlacementRegistry.setPlacementType(EntityHiBlaze.class, SpawnPlacementType.IN_AIR);
      EntitySpawnPlacementRegistry.setPlacementType(EntityLum.class, SpawnPlacementType.IN_WATER);
      EntitySpawnPlacementRegistry.setPlacementType(EntityLumAdapted.class, SpawnPlacementType.IN_WATER);
      EntitySpawnPlacementRegistry.setPlacementType(EntityEmana.class, SpawnPlacementType.IN_AIR);
      EntitySpawnPlacementRegistry.setPlacementType(EntityEmanaAdapted.class, SpawnPlacementType.IN_AIR);
      EntitySpawnPlacementRegistry.setPlacementType(EntityIki.class, SpawnPlacementType.IN_AIR);
      EntitySpawnPlacementRegistry.setPlacementType(EntityLeer.class, SpawnPlacementType.IN_AIR);
      EntitySpawnPlacementRegistry.setPlacementType(EntityAlafha.class, SpawnPlacementType.IN_AIR);
      EntitySpawnPlacementRegistry.setPlacementType(EntityJinjo.class, SpawnPlacementType.IN_AIR);
      EntitySpawnPlacementRegistry.setPlacementType(EntityLencia.class, SpawnPlacementType.IN_AIR);
      EntitySpawnPlacementRegistry.setPlacementType(EntityElvia.class, SpawnPlacementType.IN_AIR);
      EntitySpawnPlacementRegistry.setPlacementType(EntityTenn.class, SpawnPlacementType.IN_AIR);
      EntitySpawnPlacementRegistry.setPlacementType(EntityOmboo.class, SpawnPlacementType.IN_AIR);
      EntitySpawnPlacementRegistry.setPlacementType(EntityHeblu.class, SpawnPlacementType.IN_AIR);
   }

   public void serverInit(FMLServerStartingEvent e) {
      e.registerServerCommand(new SRPCommandEvolution());
      e.registerServerCommand(new SRPCommandOrigin());
      e.registerServerCommand(new SRPCommandNode());
      e.registerServerCommand(new SRPCommandColony());
      e.registerServerCommand(new SRPCommandRoot());
      e.registerServerCommand(new SRPCommandDislodgment());
      e.registerServerCommand(new SRPCommandGeneration());
      e.registerServerCommand(new SRPCommandUDevelopment());
      e.registerServerCommand(new CommandHarlequinHere());
      e.registerServerCommand(new CommandHarlequinConvert());
      e.registerServerCommand(new CommandHarlequinScatter());
      e.registerServerCommand(new CommandSRPHelp());
      e.registerServerCommand(new CommandSRPGuide());
      e.registerServerCommand(new CommandSRPGuideClear());
      e.registerServerCommand(new CommandForceCelestial());
      e.registerServerCommand(new SRPCommandGuiDistortion());
      e.registerServerCommand(new SRPCommandBestiaryStats());
      e.registerServerCommand(new SRPCommandSummonNidus());
   }

   public EntityPlayerMP getPlayerEntityFromContext(MessageContext ctx) {
      return ctx.getServerHandler().field_147369_b;
   }

   public void spreadBiome(BlockPos pos, boolean convert, int type) {
   }

   public void playMovingSound(int sound, float v) {
   }

   public void modelReg(Item item, int meta, String id) {
   }

   public <T extends AbstractPacket<T>> void networkMessage(T message, MessageContext messageContext) {
      WorldServer world = (WorldServer)messageContext.getServerHandler().field_147369_b.field_70170_p;
      world.func_152344_a(
         () -> message.serverSide(
            FMLCommonHandler.instance().getMinecraftServerInstance(), message, messageContext.getServerHandler().field_147369_b, messageContext
         )
      );
   }
}
