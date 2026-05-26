/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.event.RegistryEvent$Register
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.registry.EntityEntry
 *  net.minecraftforge.fml.common.registry.EntityEntryBuilder
 *  net.minecraftforge.fml.common.registry.GameRegistry
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.IForgeRegistryEntry
 */
package com.subspaceparasite.init;

import com.subspaceparasite.entity.EntityOrbBoom;
import com.subspaceparasite.entity.EntityOrbScary;
import com.subspaceparasite.entity.EntityOrbVoid;
import com.subspaceparasite.entity.EntityParasiticScent;
import com.subspaceparasite.entity.EntityRemain;
import com.subspaceparasite.entity.EntitySource;
import com.subspaceparasite.entity.EntityToxicCloud;
import com.subspaceparasite.entity.monster.EntityBiomass;
import com.subspaceparasite.entity.monster.EntityTendril;
import com.subspaceparasite.entity.monster.EntityWave;
import com.subspaceparasite.entity.monster.EntityWaveShock;
import com.subspaceparasite.entity.monster.abomination.EntityAboBodies;
import com.subspaceparasite.entity.monster.abomination.EntityAboHead;
import com.subspaceparasite.entity.monster.adapted.EntityBanoAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityCanraAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityEmanaAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityGimAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityHullAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityIkiAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityLumAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityNoglaAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityRanracAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityShycoAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityWymoAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityZaaAdapted;
import com.subspaceparasite.entity.monster.ancient.EntityOronco;
import com.subspaceparasite.entity.monster.ancient.EntityOroncoTen;
import com.subspaceparasite.entity.monster.ancient.EntityTerla;
import com.subspaceparasite.entity.monster.crude.EntityCruxA;
import com.subspaceparasite.entity.monster.crude.EntityCruxB;
import com.subspaceparasite.entity.monster.crude.EntityDone;
import com.subspaceparasite.entity.monster.crude.EntityHeed;
import com.subspaceparasite.entity.monster.crude.EntityHost;
import com.subspaceparasite.entity.monster.crude.EntityHostII;
import com.subspaceparasite.entity.monster.crude.EntityInhooM;
import com.subspaceparasite.entity.monster.crude.EntityInhooS;
import com.subspaceparasite.entity.monster.crude.EntityLeer;
import com.subspaceparasite.entity.monster.crude.EntityLesh;
import com.subspaceparasite.entity.monster.crude.EntityMes;
import com.subspaceparasite.entity.monster.crude.EntityQuac;
import com.subspaceparasite.entity.monster.derived.EntityHeblu;
import com.subspaceparasite.entity.monster.derived.EntityKirin;
import com.subspaceparasite.entity.monster.deterrent.EntityDodT;
import com.subspaceparasite.entity.monster.deterrent.EntityLeemB;
import com.subspaceparasite.entity.monster.deterrent.EntityNak;
import com.subspaceparasite.entity.monster.deterrent.EntityRof;
import com.subspaceparasite.entity.monster.deterrent.EntityTonro;
import com.subspaceparasite.entity.monster.deterrent.EntityUnvo;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityDod;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityDodSII;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityDodSIII;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityDodSIV;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityLeem;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityLeemSII;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityLeemSIII;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityLeemSIV;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityVenkrol;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityVenkrolSII;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityVenkrolSIII;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityVenkrolSIV;
import com.subspaceparasite.entity.monster.feral.EntityFerBear;
import com.subspaceparasite.entity.monster.feral.EntityFerCow;
import com.subspaceparasite.entity.monster.feral.EntityFerEnderman;
import com.subspaceparasite.entity.monster.feral.EntityFerHorse;
import com.subspaceparasite.entity.monster.feral.EntityFerHuman;
import com.subspaceparasite.entity.monster.feral.EntityFerPig;
import com.subspaceparasite.entity.monster.feral.EntityFerSheep;
import com.subspaceparasite.entity.monster.feral.EntityFerVillager;
import com.subspaceparasite.entity.monster.feral.EntityFerWolf;
import com.subspaceparasite.entity.monster.hijacked.EntityHiBlaze;
import com.subspaceparasite.entity.monster.hijacked.EntityHiGolem;
import com.subspaceparasite.entity.monster.hijacked.EntityHiSkeleton;
import com.subspaceparasite.entity.monster.inborn.EntityAta;
import com.subspaceparasite.entity.monster.inborn.EntityButhol;
import com.subspaceparasite.entity.monster.inborn.EntityGothol;
import com.subspaceparasite.entity.monster.inborn.EntityKol;
import com.subspaceparasite.entity.monster.inborn.EntityLodo;
import com.subspaceparasite.entity.monster.inborn.EntityMudo;
import com.subspaceparasite.entity.monster.inborn.EntityNuuh;
import com.subspaceparasite.entity.monster.inborn.EntityRathol;
import com.subspaceparasite.entity.monster.inborn.EntityViin;
import com.subspaceparasite.entity.monster.infected.EntityDorpa;
import com.subspaceparasite.entity.monster.infected.EntityInfBear;
import com.subspaceparasite.entity.monster.infected.EntityInfCow;
import com.subspaceparasite.entity.monster.infected.EntityInfDragonE;
import com.subspaceparasite.entity.monster.infected.EntityInfEnderman;
import com.subspaceparasite.entity.monster.infected.EntityInfHorse;
import com.subspaceparasite.entity.monster.infected.EntityInfHuman;
import com.subspaceparasite.entity.monster.infected.EntityInfPig;
import com.subspaceparasite.entity.monster.infected.EntityInfPlayer;
import com.subspaceparasite.entity.monster.infected.EntityInfSheep;
import com.subspaceparasite.entity.monster.infected.EntityInfSquid;
import com.subspaceparasite.entity.monster.infected.EntityInfVillager;
import com.subspaceparasite.entity.monster.infected.EntityInfWolf;
import com.subspaceparasite.entity.monster.infected.head.EntityInfCowHead;
import com.subspaceparasite.entity.monster.infected.head.EntityInfDragonEHead;
import com.subspaceparasite.entity.monster.infected.head.EntityInfEndermanHead;
import com.subspaceparasite.entity.monster.infected.head.EntityInfHorseHead;
import com.subspaceparasite.entity.monster.infected.head.EntityInfHumanHead;
import com.subspaceparasite.entity.monster.infected.head.EntityInfPigHead;
import com.subspaceparasite.entity.monster.infected.head.EntityInfPlayerHead;
import com.subspaceparasite.entity.monster.infected.head.EntityInfSheepHead;
import com.subspaceparasite.entity.monster.infected.head.EntityInfVillagerHead;
import com.subspaceparasite.entity.monster.infected.head.EntityInfWolfHead;
import com.subspaceparasite.entity.monster.infected.special.EntitySpeBear;
import com.subspaceparasite.entity.monster.infected.special.EntitySpeCow;
import com.subspaceparasite.entity.monster.infected.special.EntitySpeEnderman;
import com.subspaceparasite.entity.monster.infected.special.EntitySpeHuman;
import com.subspaceparasite.entity.monster.infected.special.EntitySpeSheep;
import com.subspaceparasite.entity.monster.infected.special.EntitySpeVillager;
import com.subspaceparasite.entity.monster.primitive.EntityBano;
import com.subspaceparasite.entity.monster.primitive.EntityCanra;
import com.subspaceparasite.entity.monster.primitive.EntityEmana;
import com.subspaceparasite.entity.monster.primitive.EntityGim;
import com.subspaceparasite.entity.monster.primitive.EntityHull;
import com.subspaceparasite.entity.monster.primitive.EntityIki;
import com.subspaceparasite.entity.monster.primitive.EntityLum;
import com.subspaceparasite.entity.monster.primitive.EntityNogla;
import com.subspaceparasite.entity.monster.primitive.EntityRanrac;
import com.subspaceparasite.entity.monster.primitive.EntityShyco;
import com.subspaceparasite.entity.monster.primitive.EntityWymo;
import com.subspaceparasite.entity.monster.primitive.EntityZaa;
import com.subspaceparasite.entity.monster.pure.EntityAlafha;
import com.subspaceparasite.entity.monster.pure.EntityAnged;
import com.subspaceparasite.entity.monster.pure.EntityEsor;
import com.subspaceparasite.entity.monster.pure.EntityFlog;
import com.subspaceparasite.entity.monster.pure.EntityGanro;
import com.subspaceparasite.entity.monster.pure.EntityOmboo;
import com.subspaceparasite.entity.monster.pure.EntityOrch;
import com.subspaceparasite.entity.monster.pure.EntitySoo;
import com.subspaceparasite.entity.monster.pure.preeminent.EntityElvia;
import com.subspaceparasite.entity.monster.pure.preeminent.EntityFlam;
import com.subspaceparasite.entity.monster.pure.preeminent.EntityJinjo;
import com.subspaceparasite.entity.monster.pure.preeminent.EntityLencia;
import com.subspaceparasite.entity.monster.pure.preeminent.EntityPheon;
import com.subspaceparasite.entity.monster.pure.preeminent.EntityTenn;
import com.subspaceparasite.entity.monster.pure.preeminent.EntityVesta;
import com.subspaceparasite.entity.projectile.EntityBomb;
import com.subspaceparasite.entity.projectile.EntityDropPod;
import com.subspaceparasite.entity.projectile.EntityGore;
import com.subspaceparasite.entity.projectile.EntityMeteor;
import com.subspaceparasite.entity.projectile.EntityNade;
import com.subspaceparasite.entity.projectile.EntityProjectileAlafhaBall;
import com.subspaceparasite.entity.projectile.EntityProjectileAncientball;
import com.subspaceparasite.entity.projectile.EntityProjectileAngedball;
import com.subspaceparasite.entity.projectile.EntityProjectileBiomass;
import com.subspaceparasite.entity.projectile.EntityProjectileDragonE;
import com.subspaceparasite.entity.projectile.EntityProjectileEffects;
import com.subspaceparasite.entity.projectile.EntityProjectileElviaBall;
import com.subspaceparasite.entity.projectile.EntityProjectileHomming;
import com.subspaceparasite.entity.projectile.EntityProjectileLenciaBall;
import com.subspaceparasite.entity.projectile.EntityProjectileNade;
import com.subspaceparasite.entity.projectile.EntityProjectilePullball;
import com.subspaceparasite.entity.projectile.EntityProjectileSpineball;
import com.subspaceparasite.entity.projectile.EntityProjectileWebball;
import com.subspaceparasite.entity.projectile.EntityThrowableAntiInfestedBlock;
import com.subspaceparasite.entity.tile.TileEntityCanister;
import com.subspaceparasite.entity.tile.TileEntityDod;
import com.subspaceparasite.tileentity.TileEntityParasiteLoot;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigMobs;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class SPEntities {
    public static EntityEntry[] SPENTITIES;

    private static <T extends Entity> EntityEntry CreateEntityMob(String name, Class<T> cls, int primaryColorIn, int secondaryColorIn, int id, boolean active) {
        if (active) {
            EntityEntryBuilder builder = EntityEntryBuilder.create();
            builder.entity(cls);
            builder.name("subspaceparasite." + name);
            builder.id(new ResourceLocation("subspaceparasite", name), id);
            builder.tracker(64, 3, true);
            boolean test = false;
            if (SPConfig.vanillaEggs && !name.equals("damage") && !name.equals("ancientpod") || test) {
                builder.egg(primaryColorIn, secondaryColorIn);
            }
            return builder.build();
        }
        return null;
    }

    private static <T extends Entity> EntityEntry CreateEntityProjectile(String name, Class<T> cls, int moid) {
        EntityEntryBuilder builder = EntityEntryBuilder.create();
        builder.entity(cls);
        builder.name("subspaceparasite." + name);
        builder.id(new ResourceLocation("subspaceparasite", name), moid);
        builder.tracker(64, 3, true);
        return builder.build();
    }

    private static <T extends Entity> EntityEntry CreateEntityProjectile(String name, Class<T> cls, int tracking, int moid) {
        EntityEntryBuilder builder = EntityEntryBuilder.create();
        builder.entity(cls);
        builder.name("subspaceparasite." + name);
        builder.id(new ResourceLocation("subspaceparasite", name), moid);
        builder.tracker(tracking, 1, true);
        return builder.build();
    }

    private static <T extends Entity> EntityEntry CreateEntityNoEgg(String name, Class<T> cls, int id, int trackRange, int updateFreq, boolean sendVelocity, boolean active) {
        if (!active) {
            return null;
        }
        String MODID = "subspaceparasite";
        EntityEntryBuilder builder = EntityEntryBuilder.create();
        builder.entity(cls);
        builder.name("subspaceparasite." + name);
        builder.id(new ResourceLocation("subspaceparasite", name), id);
        builder.tracker(trackRange, updateFreq, sendVelocity);
        return builder.build();
    }

    public static void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityCanister.class, (ResourceLocation)new ResourceLocation("subspaceparasite", "tileentitycanister"));
        GameRegistry.registerTileEntity(TileEntityDod.class, (ResourceLocation)new ResourceLocation("subspaceparasite", "tileentitydod"));
        GameRegistry.registerTileEntity(TileEntityParasiteLoot.class, (ResourceLocation)new ResourceLocation("subspaceparasite", "parasite_loot"));
    }

    @Mod.EventBusSubscriber(modid="subspaceparasite")
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void onEvent(RegistryEvent.Register<EntityEntry> event) {
            IForgeRegistry registry = event.getRegistry();
            SPENTITIES = new EntityEntry[]{SPEntities.CreateEntityMob("sim_bigspider", EntityDorpa.class, 8611072, 16711900, 2, SPConfigMobs.dorpaEnabled), SPEntities.CreateEntityMob("sim_squid", EntityInfSquid.class, 8611072, 16711900, 307, SPConfigMobs.infsquidEnabled), SPEntities.CreateEntityMob("sim_human", EntityInfHuman.class, 8611072, 16711900, 6, SPConfigMobs.infhumanEnabled), SPEntities.CreateEntityMob("sim_cow", EntityInfCow.class, 8611072, 16711900, 13, SPConfigMobs.infcowEnabled), SPEntities.CreateEntityMob("sim_sheep", EntityInfSheep.class, 8611072, 16711900, 14, SPConfigMobs.infsheepEnabled), SPEntities.CreateEntityMob("sim_wolf", EntityInfWolf.class, 8611072, 16711900, 15, SPConfigMobs.infwolfEnabled), SPEntities.CreateEntityMob("sim_pig", EntityInfPig.class, 8611072, 16711900, 26, SPConfigMobs.infpigEnabled), SPEntities.CreateEntityMob("sim_villager", EntityInfVillager.class, 8611072, 16711900, 27, SPConfigMobs.infvillagerEnabled), SPEntities.CreateEntityMob("sim_adventurer", EntityInfPlayer.class, 8611072, 16711900, 40, SPConfigMobs.infadventurerEnabled), SPEntities.CreateEntityMob("sim_horse", EntityInfHorse.class, 8611072, 16711900, 44, SPConfigMobs.infhorseEnabled), SPEntities.CreateEntityMob("sim_bear", EntityInfBear.class, 8611072, 16711900, 49, SPConfigMobs.infbearEnabled), SPEntities.CreateEntityMob("sim_enderman", EntityInfEnderman.class, 8611072, 16711900, 59, SPConfigMobs.infendermanEnabled), SPEntities.CreateEntityMob("sim_dragone", EntityInfDragonE.class, 8611072, 16711900, 64, SPConfigMobs.infdragoneEnabled), SPEntities.CreateEntityMob("sim_sheephead", EntityInfSheepHead.class, 8611072, 16711900, 22, SPConfigMobs.infsheepEnabled), SPEntities.CreateEntityMob("sim_wolfhead", EntityInfWolfHead.class, 8611072, 16711900, 21, SPConfigMobs.infwolfEnabled), SPEntities.CreateEntityMob("sim_cowhead", EntityInfCowHead.class, 8611072, 16711900, 28, SPConfigMobs.infcowEnabled), SPEntities.CreateEntityMob("sim_pighead", EntityInfPigHead.class, 8611072, 16711900, 31, SPConfigMobs.infpigEnabled), SPEntities.CreateEntityMob("sim_villagerhead", EntityInfVillagerHead.class, 8611072, 16711900, 32, SPConfigMobs.infvillagerEnabled), SPEntities.CreateEntityMob("sim_horsehead", EntityInfHorseHead.class, 8611072, 16711900, 45, SPConfigMobs.infhorseEnabled), SPEntities.CreateEntityMob("sim_humanhead", EntityInfHumanHead.class, 8611072, 16711900, 46, SPConfigMobs.infhumanEnabled), SPEntities.CreateEntityMob("sim_endermanhead", EntityInfEndermanHead.class, 8611072, 16711900, 69, SPConfigMobs.infendermanEnabled), SPEntities.CreateEntityMob("sim_dragonehead", EntityInfDragonEHead.class, 8611072, 16711900, 70, SPConfigMobs.infdragoneEnabled), SPEntities.CreateEntityMob("sim_adventurerhead", EntityInfPlayerHead.class, 8611072, 16711900, 71, SPConfigMobs.infadventurerEnabled), SPEntities.CreateEntityMob("mar_enderman", EntitySpeEnderman.class, 8611072, 16711900, 321, true), SPEntities.CreateEntityMob("mar_cow", EntitySpeCow.class, 8611072, 16711900, 322, true), SPEntities.CreateEntityMob("mar_villager", EntitySpeVillager.class, 8611072, 16711900, 323, true), SPEntities.CreateEntityMob("mar_human", EntitySpeHuman.class, 8611072, 16711900, 324, true), SPEntities.CreateEntityMob("mar_sheep", EntitySpeSheep.class, 8611072, 16711900, 329, true), SPEntities.CreateEntityMob("mar_bear", EntitySpeBear.class, 8611072, 16711900, 330, true), SPEntities.CreateEntityMob("fer_bear", EntityFerBear.class, 8611072, 16711900, 306, SPConfigMobs.ferbearEnabled), SPEntities.CreateEntityMob("fer_cow", EntityFerCow.class, 8611072, 16711900, 93, SPConfigMobs.fercowEnabled), SPEntities.CreateEntityMob("fer_enderman", EntityFerEnderman.class, 8611072, 16711900, 94, SPConfigMobs.ferendermanEnabled), SPEntities.CreateEntityMob("fer_horse", EntityFerHorse.class, 8611072, 16711900, 95, SPConfigMobs.ferhorseEnabled), SPEntities.CreateEntityMob("fer_human", EntityFerHuman.class, 8611072, 16711900, 96, SPConfigMobs.ferhumanEnabled), SPEntities.CreateEntityMob("fer_pig", EntityFerPig.class, 8611072, 16711900, 97, SPConfigMobs.ferpigEnabled), SPEntities.CreateEntityMob("fer_sheep", EntityFerSheep.class, 8611072, 16711900, 98, SPConfigMobs.fersheepEnabled), SPEntities.CreateEntityMob("fer_villager", EntityFerVillager.class, 8611072, 16711900, 99, SPConfigMobs.fervillagerEnabled), SPEntities.CreateEntityMob("fer_wolf", EntityFerWolf.class, 8611072, 16711900, 300, SPConfigMobs.ferwolfEnabled), SPEntities.CreateEntityMob("abo_bodies", EntityAboBodies.class, 8611072, 16711900, 325, true), SPEntities.CreateEntityMob("abo_head", EntityAboHead.class, 8611072, 16711900, 326, true), SPEntities.CreateEntityMob("hi_blaze", EntityHiBlaze.class, 8611072, 16711900, 302, SPConfigMobs.hiblazeEnabled), SPEntities.CreateEntityMob("hi_golem", EntityHiGolem.class, 8611072, 16711900, 301, SPConfigMobs.higolemEnabled), SPEntities.CreateEntityMob("hi_skeleton", EntityHiSkeleton.class, 8611072, 16711900, 303, SPConfigMobs.hiskeletonEnabled), SPEntities.CreateEntityMob("carrier_heavy", EntityRathol.class, 3224855, 3224855, 3, SPConfigMobs.ratholEnabled), SPEntities.CreateEntityMob("carrier_light", EntityGothol.class, 3224855, 3224855, 304, SPConfigMobs.gotholEnabled), SPEntities.CreateEntityMob("buglin", EntityLodo.class, 3224855, 3224855, 5, SPConfigMobs.lodoEnabled), SPEntities.CreateEntityMob("carrier_flying", EntityButhol.class, 3224855, 3224855, 11, SPConfigMobs.butholEnabled), SPEntities.CreateEntityMob("rupter", EntityMudo.class, 3224855, 3224855, 12, SPConfigMobs.mudoEnabled), SPEntities.CreateEntityMob("movingflesh", EntityLesh.class, 3224855, 3224855, 23, true), SPEntities.CreateEntityMob("worker", EntityKol.class, 3224855, 3224855, 36, SPConfigMobs.kolEnabled), SPEntities.CreateEntityMob("mangler", EntityNuuh.class, 3224855, 3224855, 76, SPConfigMobs.nuuhEnabled), SPEntities.CreateEntityMob("gnat", EntityAta.class, 3224855, 3224855, 91, true), SPEntities.CreateEntityMob("lice", EntityViin.class, 3224855, 3224855, 334, true), SPEntities.CreateEntityMob("beckon_si", EntityVenkrol.class, 3224855, 3224855, 16, true), SPEntities.CreateEntityMob("beckon_sii", EntityVenkrolSII.class, 3224855, 3224855, 18, true), SPEntities.CreateEntityMob("beckon_siii", EntityVenkrolSIII.class, 3224855, 3224855, 19, true), SPEntities.CreateEntityMob("beckon_siv", EntityVenkrolSIV.class, 3224855, 3224855, 41, true), SPEntities.CreateEntityMob("dispatcherten", EntityDodT.class, 3224855, 3224855, 74, true), SPEntities.CreateEntityMob("dispatcher_si", EntityDod.class, 3224855, 3224855, 73, true), SPEntities.CreateEntityMob("dispatcher_sii", EntityDodSII.class, 3224855, 3224855, 77, true), SPEntities.CreateEntityMob("dispatcher_siii", EntityDodSIII.class, 3224855, 3224855, 78, true), SPEntities.CreateEntityMob("dispatcher_siv", EntityDodSIV.class, 3224855, 3224855, 79, true), SPEntities.CreateEntityMob("rooterball", EntityLeemB.class, 3224855, 3224855, 314, true), SPEntities.CreateEntityMob("rooter_si", EntityLeem.class, 3224855, 3224855, 310, true), SPEntities.CreateEntityMob("rooter_sii", EntityLeemSII.class, 3224855, 3224855, 311, true), SPEntities.CreateEntityMob("rooter_siii", EntityLeemSIII.class, 3224855, 3224855, 312, true), SPEntities.CreateEntityMob("rooter_siv", EntityLeemSIV.class, 3224855, 3224855, 313, true), SPEntities.CreateEntityMob("kyphosis", EntityTonro.class, 3224855, 3224855, 29, SPConfigMobs.tonroEnabled), SPEntities.CreateEntityMob("sentry", EntityUnvo.class, 3224855, 3224855, 30, SPConfigMobs.unvoEnabled), SPEntities.CreateEntityMob("seizer", EntityNak.class, 3224855, 3224855, 72, true), SPEntities.CreateEntityMob("worm", EntityRof.class, 3224855, 3224855, 308, true), SPEntities.CreateEntityMob("incompleteform_small", EntityInhooS.class, 8611072, 16711900, 39, SPConfigMobs.inhooSEnabled), SPEntities.CreateEntityMob("incompleteform_medium", EntityInhooM.class, 8611072, 16711900, 43, SPConfigMobs.inhooMEnabled), SPEntities.CreateEntityMob("host", EntityHost.class, 8611072, 16711900, 48, SPConfigMobs.hostEnabled), SPEntities.CreateEntityMob("hostii", EntityHostII.class, 8611072, 16711900, 75, SPConfigMobs.herdEnabled), SPEntities.CreateEntityMob("heed", EntityHeed.class, 8350208, 0x404040, 63, SPConfigMobs.heedEnabled), SPEntities.CreateEntityMob("crux", EntityCruxA.class, 8339200, 11992832, 62, SPConfigMobs.cruxaEnabled), SPEntities.CreateEntityMob("crux_incomplete", EntityCruxB.class, 8339200, 11992832, 320, SPConfigMobs.cruxaEnabled), SPEntities.CreateEntityMob("thrall", EntityMes.class, 8339200, 11992832, 80, SPConfigMobs.thrallEnabled), SPEntities.CreateEntityMob("dredge", EntityDone.class, 8339200, 11992832, 319, SPConfigMobs.doneEnabled), SPEntities.CreateEntityMob("airscrew", EntityLeer.class, 8339200, 11992832, 328, true), SPEntities.CreateEntityMob("carrier_worm", EntityQuac.class, 8339200, 11992832, 327, true), SPEntities.CreateEntityMob("pri_longarms", EntityShyco.class, 8350208, 0x404040, 1, SPConfigMobs.shycoEnabled), SPEntities.CreateEntityMob("pri_manducater", EntityHull.class, 8350208, 0x404040, 7, SPConfigMobs.hullEnabled), SPEntities.CreateEntityMob("pri_reeker", EntityNogla.class, 8350208, 0x404040, 10, SPConfigMobs.noglaEnabled), SPEntities.CreateEntityMob("pri_yelloweye", EntityEmana.class, 8350208, 0x404040, 4, SPConfigMobs.emanaEnabled), SPEntities.CreateEntityMob("pri_summoner", EntityCanra.class, 8350208, 0x404040, 8, SPConfigMobs.canraEnabled), SPEntities.CreateEntityMob("pri_bolster", EntityBano.class, 8350208, 0x404040, 17, SPConfigMobs.zetmoEnabled), SPEntities.CreateEntityMob("pri_tozoon", EntityWymo.class, 8350208, 0x404040, 37, SPConfigMobs.wymoEnabled), SPEntities.CreateEntityMob("pri_arachnida", EntityRanrac.class, 8350208, 0x404040, 38, SPConfigMobs.arachnidaEnabled), SPEntities.CreateEntityMob("pri_devourer", EntityLum.class, 8350208, 0x404040, 66, SPConfigMobs.lumEnabled), SPEntities.CreateEntityMob("pri_vermin", EntityIki.class, 8350208, 0x404040, 92, SPConfigMobs.ikiEnabled), SPEntities.CreateEntityMob("pri_viscera", EntityGim.class, 8350208, 0x404040, 317, SPConfigMobs.gimEnabled), SPEntities.CreateEntityMob("pri_burrower", EntityZaa.class, 8350208, 0x404040, 318, SPConfigMobs.zaaEnabled), SPEntities.CreateEntityMob("ada_longarms", EntityShycoAdapted.class, 8339200, 11992832, 51, SPConfigMobs.shycoEnabled), SPEntities.CreateEntityMob("ada_manducater", EntityHullAdapted.class, 8339200, 11992832, 52, SPConfigMobs.hullEnabled), SPEntities.CreateEntityMob("ada_reeker", EntityNoglaAdapted.class, 8339200, 11992832, 54, SPConfigMobs.noglaEnabled), SPEntities.CreateEntityMob("ada_yelloweye", EntityEmanaAdapted.class, 8339200, 11992832, 55, SPConfigMobs.emanaEnabled), SPEntities.CreateEntityMob("ada_summoner", EntityCanraAdapted.class, 8339200, 11992832, 53, SPConfigMobs.canraEnabled), SPEntities.CreateEntityMob("ada_bolster", EntityBanoAdapted.class, 8339200, 11992832, 56, SPConfigMobs.zetmoEnabled), SPEntities.CreateEntityMob("ada_tozoon", EntityWymoAdapted.class, 8339200, 11992832, 57, SPConfigMobs.wymoEnabled), SPEntities.CreateEntityMob("ada_arachnida", EntityRanracAdapted.class, 8339200, 11992832, 58, SPConfigMobs.arachnidaEnabled), SPEntities.CreateEntityMob("ada_devourer", EntityLumAdapted.class, 8350208, 0x404040, 81, SPConfigMobs.lumEnabled), SPEntities.CreateEntityMob("ada_vermin", EntityIkiAdapted.class, 8350208, 0x404040, 333, SPConfigMobs.ikiEnabled), SPEntities.CreateEntityMob("ada_viscera", EntityGimAdapted.class, 8350208, 0x404040, 315, SPConfigMobs.gimEnabled), SPEntities.CreateEntityMob("ada_burrower", EntityZaaAdapted.class, 8350208, 0x404040, 316, SPConfigMobs.zaaEnabled), SPEntities.CreateEntityMob("overseer", EntityAlafha.class, 8611072, 16711900, 9, SPConfigMobs.alafhaEnabled), SPEntities.CreateEntityMob("vigilante", EntityAnged.class, 3224855, 3224855, 25, SPConfigMobs.angedEnabled), SPEntities.CreateEntityMob("warden", EntityGanro.class, 3224855, 3224855, 33, SPConfigMobs.ganroEnabled), SPEntities.CreateEntityMob("bomber_light", EntityOmboo.class, 3224855, 3224855, 47, SPConfigMobs.ombooEnabled), SPEntities.CreateEntityMob("marauder", EntityEsor.class, 3224855, 3224855, 50, SPConfigMobs.esorEnabled), SPEntities.CreateEntityMob("monarch", EntityOrch.class, 3224855, 3224855, 84, SPConfigMobs.orchEnabled), SPEntities.CreateEntityMob("grunt", EntityFlog.class, 3224855, 3224855, 60, SPConfigMobs.flogEnabled), SPEntities.CreateEntityMob("bomber_heavy", EntityJinjo.class, 3224855, 3224855, 65, SPConfigMobs.jinjoEnabled), SPEntities.CreateEntityMob("wraith", EntityElvia.class, 3224855, 3224855, 85, SPConfigMobs.elviaEnabled), SPEntities.CreateEntityMob("bogle", EntityLencia.class, 3224855, 3224855, 86, SPConfigMobs.lenciaEnabled), SPEntities.CreateEntityMob("haunter", EntityPheon.class, 3224855, 3224855, 87, SPConfigMobs.pheonEnabled), SPEntities.CreateEntityMob("carrier_colony", EntityVesta.class, 3224855, 3224855, 88, SPConfigMobs.vestaEnabled), SPEntities.CreateEntityMob("succor", EntityFlam.class, 3224855, 3224855, 89, true), SPEntities.CreateEntityMob("seeker", EntitySoo.class, 3224855, 3224855, 82, true), SPEntities.CreateEntityMob("architect", EntityTenn.class, 3224855, 3224855, 90, true), SPEntities.CreateEntityMob("anc_dreadnaut", EntityOronco.class, 4272252, 4272252, 24, SPConfigMobs.oroncoEnabled), SPEntities.CreateEntityMob("anc_overlord", EntityTerla.class, 4272252, 4272252, 20, SPConfigMobs.terlaEnabled), SPEntities.CreateEntityMob("anc_pod", EntityDropPod.class, 4272252, 4272252, 34, true), SPEntities.CreateEntityMob("anc_dreadnaut_ten", EntityOroncoTen.class, 4272252, 4272252, 35, SPConfigMobs.oroncoEnabled), SPEntities.CreateEntityProjectile("pullingball", EntityProjectilePullball.class, 111), SPEntities.CreateEntityProjectile("webball", EntityProjectileWebball.class, 101), SPEntities.CreateEntityProjectile("spineball", EntityProjectileSpineball.class, 102), SPEntities.CreateEntityProjectile("nadeball", EntityProjectileNade.class, 110), SPEntities.CreateEntityProjectile("salivaball", EntityProjectileAlafhaBall.class, 103), SPEntities.CreateEntityProjectile("ballball", EntityProjectileAngedball.class, 104), SPEntities.CreateEntityProjectile("ancientball", EntityProjectileAncientball.class, 105), SPEntities.CreateEntityProjectile("homming", EntityProjectileHomming.class, 106), SPEntities.CreateEntityProjectile("antiinfestedblock", EntityThrowableAntiInfestedBlock.class, 107), SPEntities.CreateEntityProjectile("biomassball", EntityProjectileBiomass.class, 108), SPEntities.CreateEntityProjectile("missile", EntityProjectileDragonE.class, 109), SPEntities.CreateEntityProjectile("balltall", EntityProjectileElviaBall.class, 112), SPEntities.CreateEntityProjectile("ballmall", EntityProjectileLenciaBall.class, 113), SPEntities.CreateEntityProjectile("salivaeff", EntityProjectileEffects.class, 331), SPEntities.CreateEntityMob("kirin", EntityKirin.class, 4272252, 4272252, 67, true), SPEntities.CreateEntityMob("draconite", EntityHeblu.class, 4272252, 4272252, 309, true), SPEntities.CreateEntityProjectile("orbscary", EntityOrbScary.class, 210), SPEntities.CreateEntityProjectile("orbvoid", EntityOrbVoid.class, 256, 9501), SPEntities.CreateEntityProjectile("orbboom", EntityOrbBoom.class, 256, 9502), SPEntities.CreateEntityProjectile("source", EntitySource.class, 208), SPEntities.CreateEntityProjectile("remain", EntityRemain.class, 209), SPEntities.CreateEntityProjectile("bomb", EntityBomb.class, 203), SPEntities.CreateEntityProjectile("cloudtoxic", EntityToxicCloud.class, 207), SPEntities.CreateEntityProjectile("biomass", EntityBiomass.class, 205), SPEntities.CreateEntityProjectile("gore", EntityGore.class, 204), SPEntities.CreateEntityProjectile("tendril", EntityTendril.class, 202), SPEntities.CreateEntityProjectile("scent", EntityParasiticScent.class, 206), SPEntities.CreateEntityProjectile("wave", EntityWave.class, 211), SPEntities.CreateEntityProjectile("waveshock", EntityWaveShock.class, 213), SPEntities.CreateEntityProjectile("nade", EntityNade.class, 212), SPEntities.CreateEntityProjectile("meteor", EntityMeteor.class, 256, 9500)};
            for (int i = 0; i < SPENTITIES.length; ++i) {
                if (SPENTITIES[i] == null) continue;
                registry.register((IForgeRegistryEntry)SPENTITIES[i]);
            }
        }
    }
}

