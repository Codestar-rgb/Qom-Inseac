/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.command.ICommand
 *  net.minecraft.entity.EntityLiving$SpawnPlacementType
 *  net.minecraft.entity.EntitySpawnPlacementRegistry
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.config.Configuration
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.IWorldGenerator
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPostInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLServerStartingEvent
 *  net.minecraftforge.fml.common.network.IGuiHandler
 *  net.minecraftforge.fml.common.network.NetworkRegistry
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 *  net.minecraftforge.fml.common.registry.GameRegistry
 */
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
import net.minecraft.command.ICommand;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
    public static Configuration config;
    public static Configuration configMobs;
    public static Configuration configSystems;
    public static Configuration configWorld;

    public void preInit(FMLPreInitializationEvent e) {
        GameRegistry.registerTileEntity(TileEntityRelayController.class, (ResourceLocation)SRPMain.rl("relaycontroller"));
        GameRegistry.registerTileEntity(TileEntityInfestationPurifier.class, (ResourceLocation)SRPMain.rl("infestation_purifier_te"));
        GameRegistry.registerTileEntity(TileEntityParasiteBarrier.class, (ResourceLocation)SRPMain.rl("parasite_barrier_te"));
        GameRegistry.registerTileEntity(TileEntityFogNullifier.class, (ResourceLocation)SRPMain.rl("fog_nullifier_te"));
        MinecraftForge.EVENT_BUS.register((Object)new CelestialNightJoinSync());
        GameRegistry.registerTileEntity(TileEntityInfuserFurnace.class, (ResourceLocation)SRPMain.rl("infuser_furnace"));
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
        NetworkRegistry.INSTANCE.registerGuiHandler((Object)SRPMain.instance, (IGuiHandler)new SRPGuiHandler());
        MinecraftForge.EVENT_BUS.register((Object)new BestiaryCombatStatsHandler());
        MinecraftForge.EVENT_BUS.register((Object)VengeanceGrappleHandler.get());
        MinecraftForge.EVENT_BUS.register((Object)new DeadBloodBottleHandler());
        BestiaryCapability.register();
        MinecraftForge.EVENT_BUS.register((Object)new BestiaryCapEvents());
        MinecraftForge.EVENT_BUS.register((Object)new InfestedBonemealHandler());
        MinecraftForge.EVENT_BUS.register((Object)new BestiaryEvents());
        MinecraftForge.EVENT_BUS.register((Object)new BlockDiscoveryHandler());
        MinecraftForge.EVENT_BUS.register((Object)new ShrimpDropHandler());
        FishingHooksSRP.register();
        MinecraftForge.EVENT_BUS.register((Object)new CelestialPhaseSyncHandler());
        CelestialEffectRegistry.register("twenty_seven", new EffectTwentySeven());
        MinecraftForge.EVENT_BUS.register((Object)new CelestialEffectHooks());
        if (FMLCommonHandler.instance().getSide().isClient()) {
            MinecraftForge.EVENT_BUS.register((Object)ClientQlipShake.INSTANCE);
            MinecraftForge.EVENT_BUS.register((Object)new DeadBloodSwimHandler());
            MinecraftForge.EVENT_BUS.register((Object)new DeadBloodOverlayHandler());
        }
    }

    public void init(FMLInitializationEvent e) {
        GameRegistry.registerWorldGenerator((IWorldGenerator)new HarlequinRockBushGen(), (int)18);
        SRPSmelting.register();
        Recipe.init();
        CelestialObjectRegistry.init();
        MinecraftForge.EVENT_BUS.register((Object)new AssimilatedPumpkinOverlayHandler());
        MinecraftForge.EVENT_BUS.register((Object)new EscapeOnDeathHandler());
        MinecraftForge.EVENT_BUS.register((Object)new EscapeRespawnHandler());
        SRPInfuserFurnaceRecipeInit.init();
        MinecraftForge.EVENT_BUS.register((Object)new PotionDiscoveryHandler());
        BeckonStage4DetectedAdvancement.register();
        RupterKillMilestoneAdvancement.register();
        EnemyOfMyEnemyAdvancement.register();
        HuntSeasonAdvancement.register();
        DispatcherStage4DetectedAdvancement.register();
        BestiaryNetwork.register();
        Blocks.field_150480_ab.func_180686_a(SRPBlocks.InfestRemain, 60, 200);
        InfuseRecipes.instance().add(new ItemStack(Items.field_151042_j), new ItemStack(SRPItems.DEADBLOOD_FLUID), new ItemStack(SRPItems.semiorganicingot));
        for (Block b : SRPBlocks.SRP_BLOCKS) {
            if (!(b instanceof BlockLeafLike)) continue;
            Blocks.field_150480_ab.func_180686_a(b, 30, 60);
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
        EntitySpawnPlacementRegistry.setPlacementType(EntityButhol.class, (EntityLiving.SpawnPlacementType)EntityLiving.SpawnPlacementType.IN_AIR);
        EntitySpawnPlacementRegistry.setPlacementType(EntityViin.class, (EntityLiving.SpawnPlacementType)EntityLiving.SpawnPlacementType.IN_AIR);
        EntitySpawnPlacementRegistry.setPlacementType(EntityInfDragonE.class, (EntityLiving.SpawnPlacementType)EntityLiving.SpawnPlacementType.IN_AIR);
        EntitySpawnPlacementRegistry.setPlacementType(EntityInfSquid.class, (EntityLiving.SpawnPlacementType)EntityLiving.SpawnPlacementType.IN_WATER);
        EntitySpawnPlacementRegistry.setPlacementType(EntityHiBlaze.class, (EntityLiving.SpawnPlacementType)EntityLiving.SpawnPlacementType.IN_AIR);
        EntitySpawnPlacementRegistry.setPlacementType(EntityLum.class, (EntityLiving.SpawnPlacementType)EntityLiving.SpawnPlacementType.IN_WATER);
        EntitySpawnPlacementRegistry.setPlacementType(EntityLumAdapted.class, (EntityLiving.SpawnPlacementType)EntityLiving.SpawnPlacementType.IN_WATER);
        EntitySpawnPlacementRegistry.setPlacementType(EntityEmana.class, (EntityLiving.SpawnPlacementType)EntityLiving.SpawnPlacementType.IN_AIR);
        EntitySpawnPlacementRegistry.setPlacementType(EntityEmanaAdapted.class, (EntityLiving.SpawnPlacementType)EntityLiving.SpawnPlacementType.IN_AIR);
        EntitySpawnPlacementRegistry.setPlacementType(EntityIki.class, (EntityLiving.SpawnPlacementType)EntityLiving.SpawnPlacementType.IN_AIR);
        EntitySpawnPlacementRegistry.setPlacementType(EntityLeer.class, (EntityLiving.SpawnPlacementType)EntityLiving.SpawnPlacementType.IN_AIR);
        EntitySpawnPlacementRegistry.setPlacementType(EntityAlafha.class, (EntityLiving.SpawnPlacementType)EntityLiving.SpawnPlacementType.IN_AIR);
        EntitySpawnPlacementRegistry.setPlacementType(EntityJinjo.class, (EntityLiving.SpawnPlacementType)EntityLiving.SpawnPlacementType.IN_AIR);
        EntitySpawnPlacementRegistry.setPlacementType(EntityLencia.class, (EntityLiving.SpawnPlacementType)EntityLiving.SpawnPlacementType.IN_AIR);
        EntitySpawnPlacementRegistry.setPlacementType(EntityElvia.class, (EntityLiving.SpawnPlacementType)EntityLiving.SpawnPlacementType.IN_AIR);
        EntitySpawnPlacementRegistry.setPlacementType(EntityTenn.class, (EntityLiving.SpawnPlacementType)EntityLiving.SpawnPlacementType.IN_AIR);
        EntitySpawnPlacementRegistry.setPlacementType(EntityOmboo.class, (EntityLiving.SpawnPlacementType)EntityLiving.SpawnPlacementType.IN_AIR);
        EntitySpawnPlacementRegistry.setPlacementType(EntityHeblu.class, (EntityLiving.SpawnPlacementType)EntityLiving.SpawnPlacementType.IN_AIR);
    }

    public void serverInit(FMLServerStartingEvent e) {
        e.registerServerCommand((ICommand)new SRPCommandEvolution());
        e.registerServerCommand((ICommand)new SRPCommandOrigin());
        e.registerServerCommand((ICommand)new SRPCommandNode());
        e.registerServerCommand((ICommand)new SRPCommandColony());
        e.registerServerCommand((ICommand)new SRPCommandRoot());
        e.registerServerCommand((ICommand)new SRPCommandDislodgment());
        e.registerServerCommand((ICommand)new SRPCommandGeneration());
        e.registerServerCommand((ICommand)new SRPCommandUDevelopment());
        e.registerServerCommand((ICommand)new CommandHarlequinHere());
        e.registerServerCommand((ICommand)new CommandHarlequinConvert());
        e.registerServerCommand((ICommand)new CommandHarlequinScatter());
        e.registerServerCommand((ICommand)new CommandSRPHelp());
        e.registerServerCommand((ICommand)new CommandSRPGuide());
        e.registerServerCommand((ICommand)new CommandSRPGuideClear());
        e.registerServerCommand((ICommand)new CommandForceCelestial());
        e.registerServerCommand((ICommand)new SRPCommandGuiDistortion());
        e.registerServerCommand((ICommand)new SRPCommandBestiaryStats());
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
        world.func_152344_a(() -> message.serverSide(FMLCommonHandler.instance().getMinecraftServerInstance(), message, messageContext.getServerHandler().field_147369_b, messageContext));
    }
}

