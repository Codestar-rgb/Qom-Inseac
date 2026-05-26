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
package com.subspaceparasite.proxy;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.advancements.BeckonStage4DetectedAdvancement;
import com.subspaceparasite.advancements.DispatcherStage4DetectedAdvancement;
import com.subspaceparasite.advancements.EnemyOfMyEnemyAdvancement;
import com.subspaceparasite.advancements.HuntSeasonAdvancement;
import com.subspaceparasite.advancements.RupterKillMilestoneAdvancement;
import com.subspaceparasite.bestiary.BestiaryCombatStatsHandler;
import com.subspaceparasite.bestiary.PotionDiscoveryHandler;
import com.subspaceparasite.bestiary.SPBestiaryRegistry;
import com.subspaceparasite.bestiary.blocks.BlockDiscoveryHandler;
import com.subspaceparasite.bestiary.blocks.SPBlockCompendiumRegistry;
import com.subspaceparasite.bestiary.cap.BestiaryCapEvents;
import com.subspaceparasite.bestiary.cap.BestiaryCapability;
import com.subspaceparasite.bestiary.event.BestiaryEvents;
import com.subspaceparasite.bestiary.net.BestiaryNetwork;
import com.subspaceparasite.block.BlockLeafLike;
import com.subspaceparasite.block.TileEntityFogNullifier;
import com.subspaceparasite.block.TileEntityInfestationPurifier;
import com.subspaceparasite.block.TileEntityParasiteBarrier;
import com.subspaceparasite.client.AssimilatedPumpkinOverlayHandler;
import com.subspaceparasite.client.ClientQlipShake;
import com.subspaceparasite.client.DeadBloodOverlayHandler;
import com.subspaceparasite.client.DeadBloodSwimHandler;
import com.subspaceparasite.client.celestial.CelestialObjectRegistry;
import com.subspaceparasite.compatibility.ModCompatibility;
import com.subspaceparasite.entity.monster.adapted.EntityEmanaAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityLumAdapted;
import com.subspaceparasite.entity.monster.crude.EntityLeer;
import com.subspaceparasite.entity.monster.derived.EntityHeblu;
import com.subspaceparasite.entity.monster.hijacked.EntityHiBlaze;
import com.subspaceparasite.entity.monster.inborn.EntityButhol;
import com.subspaceparasite.entity.monster.inborn.EntityViin;
import com.subspaceparasite.entity.monster.infected.EntityInfDragonE;
import com.subspaceparasite.entity.monster.infected.EntityInfSquid;
import com.subspaceparasite.entity.monster.primitive.EntityEmana;
import com.subspaceparasite.entity.monster.primitive.EntityIki;
import com.subspaceparasite.entity.monster.primitive.EntityLum;
import com.subspaceparasite.entity.monster.pure.EntityAlafha;
import com.subspaceparasite.entity.monster.pure.EntityOmboo;
import com.subspaceparasite.entity.monster.pure.preeminent.EntityElvia;
import com.subspaceparasite.entity.monster.pure.preeminent.EntityJinjo;
import com.subspaceparasite.entity.monster.pure.preeminent.EntityLencia;
import com.subspaceparasite.entity.monster.pure.preeminent.EntityTenn;
import com.subspaceparasite.events.DeadBloodBottleHandler;
import com.subspaceparasite.events.FishingHooksSP;
import com.subspaceparasite.feature.EscapeOnDeathHandler;
import com.subspaceparasite.feature.EscapeRespawnHandler;
import com.subspaceparasite.gui.SPGuiHandler;
import com.subspaceparasite.init.SPBiomes;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.init.SPEntities;
import com.subspaceparasite.init.SPFluids;
import com.subspaceparasite.init.SPItems;
import com.subspaceparasite.init.SPSmelting;
import com.subspaceparasite.init.SPSounds;
import com.subspaceparasite.init.SPSpawning;
import com.subspaceparasite.item.VengeanceGrappleHandler;
import com.subspaceparasite.network.CelestialNightJoinSync;
import com.subspaceparasite.network.CelestialPhaseSyncHandler;
import com.subspaceparasite.network.CommandHarlequinConvert;
import com.subspaceparasite.network.CommandHarlequinHere;
import com.subspaceparasite.network.CommandHarlequinScatter;
import com.subspaceparasite.network.CommandSPGuide;
import com.subspaceparasite.network.CommandSPGuideClear;
import com.subspaceparasite.network.CommandSPHelp;
import com.subspaceparasite.network.ExtremeSnowNetwork;
import com.subspaceparasite.network.SPCommandBestiaryStats;
import com.subspaceparasite.network.SPCommandColony;
import com.subspaceparasite.network.SPCommandDislodgment;
import com.subspaceparasite.network.SPCommandEvolution;
import com.subspaceparasite.network.SPCommandGeneration;
import com.subspaceparasite.network.SPCommandGuiDistortion;
import com.subspaceparasite.network.SPCommandNode;
import com.subspaceparasite.network.SPCommandOrigin;
import com.subspaceparasite.network.SPCommandRoot;
import com.subspaceparasite.network.SPCommandUDevelopment;
import com.subspaceparasite.network.SPNetwork;
import com.subspaceparasite.network.msg.AbstractPacket;
import com.subspaceparasite.potion.Recipe;
import com.subspaceparasite.recipes.InfuseRecipes;
import com.subspaceparasite.recipes.SPInfuserFurnaceRecipeInit;
import com.subspaceparasite.tileentity.TileEntityInfuserFurnace;
import com.subspaceparasite.tileentity.TileEntityRelayController;
import com.subspaceparasite.util.CommandForceCelestial;
import com.subspaceparasite.util.SPAttributes;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigMobs;
import com.subspaceparasite.util.config.SPConfigSystems;
import com.subspaceparasite.util.config.SPConfigWorld;
import com.subspaceparasite.util.handlers.InfestedBonemealHandler;
import com.subspaceparasite.util.handlers.SPPacketHandler;
import com.subspaceparasite.util.handlers.SPRegistryHandlers;
import com.subspaceparasite.util.handlers.ShrimpDropHandler;
import com.subspaceparasite.world.celestial.CelestialEffectHooks;
import com.subspaceparasite.world.celestial.CelestialEffectRegistry;
import com.subspaceparasite.world.celestial.effects.EffectTwentySeven;
import com.subspaceparasite.world.gen.HarlequinRockBushGen;
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
        GameRegistry.registerTileEntity(TileEntityRelayController.class, (ResourceLocation)SPMain.rl("relaycontroller"));
        GameRegistry.registerTileEntity(TileEntityInfestationPurifier.class, (ResourceLocation)SPMain.rl("infestation_purifier_te"));
        GameRegistry.registerTileEntity(TileEntityParasiteBarrier.class, (ResourceLocation)SPMain.rl("parasite_barrier_te"));
        GameRegistry.registerTileEntity(TileEntityFogNullifier.class, (ResourceLocation)SPMain.rl("fog_nullifier_te"));
        MinecraftForge.EVENT_BUS.register((Object)new CelestialNightJoinSync());
        GameRegistry.registerTileEntity(TileEntityInfuserFurnace.class, (ResourceLocation)SPMain.rl("infuser_furnace"));
        SPFluids.init();
        SPRegistryHandlers.initEvents();
        SPConfig.initConfig(e);
        SPConfigSystems.initConfig(e);
        SPConfigMobs.initConfig(e);
        SPConfigWorld.initConfig(e);
        SPAttributes.reset();
        SPAttributes.init();
        SPSounds.init();
        SPPacketHandler.init();
        SPEntities.registerTileEntities();
        ModCompatibility.preInit();
        SPNetwork.register();
        ExtremeSnowNetwork.init();
        NetworkRegistry.INSTANCE.registerGuiHandler((Object)SPMain.instance, (IGuiHandler)new SPGuiHandler());
        MinecraftForge.EVENT_BUS.register((Object)new BestiaryCombatStatsHandler());
        MinecraftForge.EVENT_BUS.register((Object)VengeanceGrappleHandler.get());
        MinecraftForge.EVENT_BUS.register((Object)new DeadBloodBottleHandler());
        BestiaryCapability.register();
        MinecraftForge.EVENT_BUS.register((Object)new BestiaryCapEvents());
        MinecraftForge.EVENT_BUS.register((Object)new InfestedBonemealHandler());
        MinecraftForge.EVENT_BUS.register((Object)new BestiaryEvents());
        MinecraftForge.EVENT_BUS.register((Object)new BlockDiscoveryHandler());
        MinecraftForge.EVENT_BUS.register((Object)new ShrimpDropHandler());
        FishingHooksSP.register();
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
        SPSmelting.register();
        Recipe.init();
        CelestialObjectRegistry.init();
        MinecraftForge.EVENT_BUS.register((Object)new AssimilatedPumpkinOverlayHandler());
        MinecraftForge.EVENT_BUS.register((Object)new EscapeOnDeathHandler());
        MinecraftForge.EVENT_BUS.register((Object)new EscapeRespawnHandler());
        SPInfuserFurnaceRecipeInit.init();
        MinecraftForge.EVENT_BUS.register((Object)new PotionDiscoveryHandler());
        BeckonStage4DetectedAdvancement.register();
        RupterKillMilestoneAdvancement.register();
        EnemyOfMyEnemyAdvancement.register();
        HuntSeasonAdvancement.register();
        DispatcherStage4DetectedAdvancement.register();
        BestiaryNetwork.register();
        Blocks.field_150480_ab.func_180686_a(SPBlocks.InfestRemain, 60, 200);
        InfuseRecipes.instance().add(new ItemStack(Items.field_151042_j), new ItemStack(SPItems.DEADBLOOD_FLUID), new ItemStack(SPItems.semiorganicingot));
        for (Block b : SPBlocks.SP_BLOCKS) {
            if (!(b instanceof BlockLeafLike)) continue;
            Blocks.field_150480_ab.func_180686_a(b, 30, 60);
        }
    }

    public void postInit(FMLPostInitializationEvent e) {
        config.save();
        configMobs.save();
        configSystems.save();
        configWorld.save();
        SPSpawning.init();
        SPBlocks.init();
        SPBestiaryRegistry.registerDefaults();
        SPBlockCompendiumRegistry.registerDefaults();
        SPBiomes.clearMobSpawnList();
        SPSpawning.initBiome();
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
        e.registerServerCommand((ICommand)new SPCommandEvolution());
        e.registerServerCommand((ICommand)new SPCommandOrigin());
        e.registerServerCommand((ICommand)new SPCommandNode());
        e.registerServerCommand((ICommand)new SPCommandColony());
        e.registerServerCommand((ICommand)new SPCommandRoot());
        e.registerServerCommand((ICommand)new SPCommandDislodgment());
        e.registerServerCommand((ICommand)new SPCommandGeneration());
        e.registerServerCommand((ICommand)new SPCommandUDevelopment());
        e.registerServerCommand((ICommand)new CommandHarlequinHere());
        e.registerServerCommand((ICommand)new CommandHarlequinConvert());
        e.registerServerCommand((ICommand)new CommandHarlequinScatter());
        e.registerServerCommand((ICommand)new CommandSPHelp());
        e.registerServerCommand((ICommand)new CommandSPGuide());
        e.registerServerCommand((ICommand)new CommandSPGuideClear());
        e.registerServerCommand((ICommand)new CommandForceCelestial());
        e.registerServerCommand((ICommand)new SPCommandGuiDistortion());
        e.registerServerCommand((ICommand)new SPCommandBestiaryStats());
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

