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
package com.dhanantry.scapeandrunparasites.init;

import com.dhanantry.scapeandrunparasites.entity.EntityOrbBoom;
import com.dhanantry.scapeandrunparasites.entity.EntityOrbScary;
import com.dhanantry.scapeandrunparasites.entity.EntityOrbVoid;
import com.dhanantry.scapeandrunparasites.entity.EntityParasiticScent;
import com.dhanantry.scapeandrunparasites.entity.EntityRemain;
import com.dhanantry.scapeandrunparasites.entity.EntitySource;
import com.dhanantry.scapeandrunparasites.entity.EntityToxicCloud;
import com.dhanantry.scapeandrunparasites.entity.monster.EntityBiomass;
import com.dhanantry.scapeandrunparasites.entity.monster.EntityTendril;
import com.dhanantry.scapeandrunparasites.entity.monster.EntityWave;
import com.dhanantry.scapeandrunparasites.entity.monster.EntityWaveShock;
import com.dhanantry.scapeandrunparasites.entity.monster.abomination.EntityAboBodies;
import com.dhanantry.scapeandrunparasites.entity.monster.abomination.EntityAboHead;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityBanoAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityCanraAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityEmanaAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityGimAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityHullAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityIkiAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityLumAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityNoglaAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityRanracAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityShycoAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityWymoAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityZaaAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.ancient.EntityOronco;
import com.dhanantry.scapeandrunparasites.entity.monster.ancient.EntityOroncoTen;
import com.dhanantry.scapeandrunparasites.entity.monster.ancient.EntityTerla;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityCruxA;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityCruxB;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityDone;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityHeed;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityHost;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityHostII;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityInhooM;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityInhooS;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityLeer;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityLesh;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityMes;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityQuac;
import com.dhanantry.scapeandrunparasites.entity.monster.derived.EntityHeblu;
import com.dhanantry.scapeandrunparasites.entity.monster.derived.EntityKirin;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.EntityDodT;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.EntityLeemB;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.EntityNak;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.EntityRof;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.EntityTonro;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.EntityUnvo;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityDod;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityDodSII;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityDodSIII;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityDodSIV;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityLeem;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityLeemSII;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityLeemSIII;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityLeemSIV;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityVenkrol;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityVenkrolSII;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityVenkrolSIII;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityVenkrolSIV;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerBear;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerCow;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerEnderman;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerHorse;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerHuman;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerPig;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerSheep;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerVillager;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerWolf;
import com.dhanantry.scapeandrunparasites.entity.monster.hijacked.EntityHiBlaze;
import com.dhanantry.scapeandrunparasites.entity.monster.hijacked.EntityHiGolem;
import com.dhanantry.scapeandrunparasites.entity.monster.hijacked.EntityHiSkeleton;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityAta;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityButhol;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityGothol;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityKol;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityLodo;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityMudo;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityNuuh;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityRathol;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityViin;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityDorpa;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfBear;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfCow;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfDragonE;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfEnderman;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfHorse;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfHuman;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfPig;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfPlayer;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfSheep;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfSquid;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfVillager;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfWolf;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfCowHead;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfDragonEHead;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfEndermanHead;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfHorseHead;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfHumanHead;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfPigHead;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfPlayerHead;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfSheepHead;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfVillagerHead;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfWolfHead;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.special.EntitySpeBear;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.special.EntitySpeCow;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.special.EntitySpeEnderman;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.special.EntitySpeHuman;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.special.EntitySpeSheep;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.special.EntitySpeVillager;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityBano;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityCanra;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityEmana;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityGim;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityHull;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityIki;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityLum;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityNogla;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityRanrac;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityShyco;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityWymo;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityZaa;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityAlafha;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityAnged;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityEsor;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityFlog;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityGanro;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityOmboo;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityOrch;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntitySoo;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent.EntityElvia;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent.EntityFlam;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent.EntityJinjo;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent.EntityLencia;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent.EntityPheon;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent.EntityTenn;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent.EntityVesta;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityBomb;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityDropPod;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityGore;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityMeteor;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityNade;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileAlafhaBall;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileAncientball;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileAngedball;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileBiomass;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileDragonE;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileEffects;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileElviaBall;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileHomming;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileLenciaBall;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileNade;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectilePullball;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileSpineball;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileWebball;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityThrowableAntiInfestedBlock;
import com.dhanantry.scapeandrunparasites.entity.tile.TileEntityCanister;
import com.dhanantry.scapeandrunparasites.entity.tile.TileEntityDod;
import com.dhanantry.scapeandrunparasites.tileentity.TileEntityParasiteLoot;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
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

public class SRPEntities {
    public static EntityEntry[] SRPENTITIES;

    private static <T extends Entity> EntityEntry CreateEntityMob(String name, Class<T> cls, int primaryColorIn, int secondaryColorIn, int id, boolean active) {
        if (active) {
            EntityEntryBuilder builder = EntityEntryBuilder.create();
            builder.entity(cls);
            builder.name("srparasites." + name);
            builder.id(new ResourceLocation("srparasites", name), id);
            builder.tracker(64, 3, true);
            boolean test = false;
            if (SRPConfig.vanillaEggs && !name.equals("damage") && !name.equals("ancientpod") || test) {
                builder.egg(primaryColorIn, secondaryColorIn);
            }
            return builder.build();
        }
        return null;
    }

    private static <T extends Entity> EntityEntry CreateEntityProjectile(String name, Class<T> cls, int moid) {
        EntityEntryBuilder builder = EntityEntryBuilder.create();
        builder.entity(cls);
        builder.name("srparasites." + name);
        builder.id(new ResourceLocation("srparasites", name), moid);
        builder.tracker(64, 3, true);
        return builder.build();
    }

    private static <T extends Entity> EntityEntry CreateEntityProjectile(String name, Class<T> cls, int tracking, int moid) {
        EntityEntryBuilder builder = EntityEntryBuilder.create();
        builder.entity(cls);
        builder.name("srparasites." + name);
        builder.id(new ResourceLocation("srparasites", name), moid);
        builder.tracker(tracking, 1, true);
        return builder.build();
    }

    private static <T extends Entity> EntityEntry CreateEntityNoEgg(String name, Class<T> cls, int id, int trackRange, int updateFreq, boolean sendVelocity, boolean active) {
        if (!active) {
            return null;
        }
        String MODID = "srparasites";
        EntityEntryBuilder builder = EntityEntryBuilder.create();
        builder.entity(cls);
        builder.name("srparasites." + name);
        builder.id(new ResourceLocation("srparasites", name), id);
        builder.tracker(trackRange, updateFreq, sendVelocity);
        return builder.build();
    }

    public static void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityCanister.class, (ResourceLocation)new ResourceLocation("srparasites", "tileentitycanister"));
        GameRegistry.registerTileEntity(TileEntityDod.class, (ResourceLocation)new ResourceLocation("srparasites", "tileentitydod"));
        GameRegistry.registerTileEntity(TileEntityParasiteLoot.class, (ResourceLocation)new ResourceLocation("srparasites", "parasite_loot"));
    }

    @Mod.EventBusSubscriber(modid="srparasites")
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void onEvent(RegistryEvent.Register<EntityEntry> event) {
            IForgeRegistry registry = event.getRegistry();
            SRPENTITIES = new EntityEntry[]{SRPEntities.CreateEntityMob("sim_bigspider", EntityDorpa.class, 8611072, 16711900, 2, SRPConfigMobs.dorpaEnabled), SRPEntities.CreateEntityMob("sim_squid", EntityInfSquid.class, 8611072, 16711900, 307, SRPConfigMobs.infsquidEnabled), SRPEntities.CreateEntityMob("sim_human", EntityInfHuman.class, 8611072, 16711900, 6, SRPConfigMobs.infhumanEnabled), SRPEntities.CreateEntityMob("sim_cow", EntityInfCow.class, 8611072, 16711900, 13, SRPConfigMobs.infcowEnabled), SRPEntities.CreateEntityMob("sim_sheep", EntityInfSheep.class, 8611072, 16711900, 14, SRPConfigMobs.infsheepEnabled), SRPEntities.CreateEntityMob("sim_wolf", EntityInfWolf.class, 8611072, 16711900, 15, SRPConfigMobs.infwolfEnabled), SRPEntities.CreateEntityMob("sim_pig", EntityInfPig.class, 8611072, 16711900, 26, SRPConfigMobs.infpigEnabled), SRPEntities.CreateEntityMob("sim_villager", EntityInfVillager.class, 8611072, 16711900, 27, SRPConfigMobs.infvillagerEnabled), SRPEntities.CreateEntityMob("sim_adventurer", EntityInfPlayer.class, 8611072, 16711900, 40, SRPConfigMobs.infadventurerEnabled), SRPEntities.CreateEntityMob("sim_horse", EntityInfHorse.class, 8611072, 16711900, 44, SRPConfigMobs.infhorseEnabled), SRPEntities.CreateEntityMob("sim_bear", EntityInfBear.class, 8611072, 16711900, 49, SRPConfigMobs.infbearEnabled), SRPEntities.CreateEntityMob("sim_enderman", EntityInfEnderman.class, 8611072, 16711900, 59, SRPConfigMobs.infendermanEnabled), SRPEntities.CreateEntityMob("sim_dragone", EntityInfDragonE.class, 8611072, 16711900, 64, SRPConfigMobs.infdragoneEnabled), SRPEntities.CreateEntityMob("sim_sheephead", EntityInfSheepHead.class, 8611072, 16711900, 22, SRPConfigMobs.infsheepEnabled), SRPEntities.CreateEntityMob("sim_wolfhead", EntityInfWolfHead.class, 8611072, 16711900, 21, SRPConfigMobs.infwolfEnabled), SRPEntities.CreateEntityMob("sim_cowhead", EntityInfCowHead.class, 8611072, 16711900, 28, SRPConfigMobs.infcowEnabled), SRPEntities.CreateEntityMob("sim_pighead", EntityInfPigHead.class, 8611072, 16711900, 31, SRPConfigMobs.infpigEnabled), SRPEntities.CreateEntityMob("sim_villagerhead", EntityInfVillagerHead.class, 8611072, 16711900, 32, SRPConfigMobs.infvillagerEnabled), SRPEntities.CreateEntityMob("sim_horsehead", EntityInfHorseHead.class, 8611072, 16711900, 45, SRPConfigMobs.infhorseEnabled), SRPEntities.CreateEntityMob("sim_humanhead", EntityInfHumanHead.class, 8611072, 16711900, 46, SRPConfigMobs.infhumanEnabled), SRPEntities.CreateEntityMob("sim_endermanhead", EntityInfEndermanHead.class, 8611072, 16711900, 69, SRPConfigMobs.infendermanEnabled), SRPEntities.CreateEntityMob("sim_dragonehead", EntityInfDragonEHead.class, 8611072, 16711900, 70, SRPConfigMobs.infdragoneEnabled), SRPEntities.CreateEntityMob("sim_adventurerhead", EntityInfPlayerHead.class, 8611072, 16711900, 71, SRPConfigMobs.infadventurerEnabled), SRPEntities.CreateEntityMob("mar_enderman", EntitySpeEnderman.class, 8611072, 16711900, 321, true), SRPEntities.CreateEntityMob("mar_cow", EntitySpeCow.class, 8611072, 16711900, 322, true), SRPEntities.CreateEntityMob("mar_villager", EntitySpeVillager.class, 8611072, 16711900, 323, true), SRPEntities.CreateEntityMob("mar_human", EntitySpeHuman.class, 8611072, 16711900, 324, true), SRPEntities.CreateEntityMob("mar_sheep", EntitySpeSheep.class, 8611072, 16711900, 329, true), SRPEntities.CreateEntityMob("mar_bear", EntitySpeBear.class, 8611072, 16711900, 330, true), SRPEntities.CreateEntityMob("fer_bear", EntityFerBear.class, 8611072, 16711900, 306, SRPConfigMobs.ferbearEnabled), SRPEntities.CreateEntityMob("fer_cow", EntityFerCow.class, 8611072, 16711900, 93, SRPConfigMobs.fercowEnabled), SRPEntities.CreateEntityMob("fer_enderman", EntityFerEnderman.class, 8611072, 16711900, 94, SRPConfigMobs.ferendermanEnabled), SRPEntities.CreateEntityMob("fer_horse", EntityFerHorse.class, 8611072, 16711900, 95, SRPConfigMobs.ferhorseEnabled), SRPEntities.CreateEntityMob("fer_human", EntityFerHuman.class, 8611072, 16711900, 96, SRPConfigMobs.ferhumanEnabled), SRPEntities.CreateEntityMob("fer_pig", EntityFerPig.class, 8611072, 16711900, 97, SRPConfigMobs.ferpigEnabled), SRPEntities.CreateEntityMob("fer_sheep", EntityFerSheep.class, 8611072, 16711900, 98, SRPConfigMobs.fersheepEnabled), SRPEntities.CreateEntityMob("fer_villager", EntityFerVillager.class, 8611072, 16711900, 99, SRPConfigMobs.fervillagerEnabled), SRPEntities.CreateEntityMob("fer_wolf", EntityFerWolf.class, 8611072, 16711900, 300, SRPConfigMobs.ferwolfEnabled), SRPEntities.CreateEntityMob("abo_bodies", EntityAboBodies.class, 8611072, 16711900, 325, true), SRPEntities.CreateEntityMob("abo_head", EntityAboHead.class, 8611072, 16711900, 326, true), SRPEntities.CreateEntityMob("hi_blaze", EntityHiBlaze.class, 8611072, 16711900, 302, SRPConfigMobs.hiblazeEnabled), SRPEntities.CreateEntityMob("hi_golem", EntityHiGolem.class, 8611072, 16711900, 301, SRPConfigMobs.higolemEnabled), SRPEntities.CreateEntityMob("hi_skeleton", EntityHiSkeleton.class, 8611072, 16711900, 303, SRPConfigMobs.hiskeletonEnabled), SRPEntities.CreateEntityMob("carrier_heavy", EntityRathol.class, 3224855, 3224855, 3, SRPConfigMobs.ratholEnabled), SRPEntities.CreateEntityMob("carrier_light", EntityGothol.class, 3224855, 3224855, 304, SRPConfigMobs.gotholEnabled), SRPEntities.CreateEntityMob("buglin", EntityLodo.class, 3224855, 3224855, 5, SRPConfigMobs.lodoEnabled), SRPEntities.CreateEntityMob("carrier_flying", EntityButhol.class, 3224855, 3224855, 11, SRPConfigMobs.butholEnabled), SRPEntities.CreateEntityMob("rupter", EntityMudo.class, 3224855, 3224855, 12, SRPConfigMobs.mudoEnabled), SRPEntities.CreateEntityMob("movingflesh", EntityLesh.class, 3224855, 3224855, 23, true), SRPEntities.CreateEntityMob("worker", EntityKol.class, 3224855, 3224855, 36, SRPConfigMobs.kolEnabled), SRPEntities.CreateEntityMob("mangler", EntityNuuh.class, 3224855, 3224855, 76, SRPConfigMobs.nuuhEnabled), SRPEntities.CreateEntityMob("gnat", EntityAta.class, 3224855, 3224855, 91, true), SRPEntities.CreateEntityMob("lice", EntityViin.class, 3224855, 3224855, 334, true), SRPEntities.CreateEntityMob("beckon_si", EntityVenkrol.class, 3224855, 3224855, 16, true), SRPEntities.CreateEntityMob("beckon_sii", EntityVenkrolSII.class, 3224855, 3224855, 18, true), SRPEntities.CreateEntityMob("beckon_siii", EntityVenkrolSIII.class, 3224855, 3224855, 19, true), SRPEntities.CreateEntityMob("beckon_siv", EntityVenkrolSIV.class, 3224855, 3224855, 41, true), SRPEntities.CreateEntityMob("dispatcherten", EntityDodT.class, 3224855, 3224855, 74, true), SRPEntities.CreateEntityMob("dispatcher_si", EntityDod.class, 3224855, 3224855, 73, true), SRPEntities.CreateEntityMob("dispatcher_sii", EntityDodSII.class, 3224855, 3224855, 77, true), SRPEntities.CreateEntityMob("dispatcher_siii", EntityDodSIII.class, 3224855, 3224855, 78, true), SRPEntities.CreateEntityMob("dispatcher_siv", EntityDodSIV.class, 3224855, 3224855, 79, true), SRPEntities.CreateEntityMob("rooterball", EntityLeemB.class, 3224855, 3224855, 314, true), SRPEntities.CreateEntityMob("rooter_si", EntityLeem.class, 3224855, 3224855, 310, true), SRPEntities.CreateEntityMob("rooter_sii", EntityLeemSII.class, 3224855, 3224855, 311, true), SRPEntities.CreateEntityMob("rooter_siii", EntityLeemSIII.class, 3224855, 3224855, 312, true), SRPEntities.CreateEntityMob("rooter_siv", EntityLeemSIV.class, 3224855, 3224855, 313, true), SRPEntities.CreateEntityMob("kyphosis", EntityTonro.class, 3224855, 3224855, 29, SRPConfigMobs.tonroEnabled), SRPEntities.CreateEntityMob("sentry", EntityUnvo.class, 3224855, 3224855, 30, SRPConfigMobs.unvoEnabled), SRPEntities.CreateEntityMob("seizer", EntityNak.class, 3224855, 3224855, 72, true), SRPEntities.CreateEntityMob("worm", EntityRof.class, 3224855, 3224855, 308, true), SRPEntities.CreateEntityMob("incompleteform_small", EntityInhooS.class, 8611072, 16711900, 39, SRPConfigMobs.inhooSEnabled), SRPEntities.CreateEntityMob("incompleteform_medium", EntityInhooM.class, 8611072, 16711900, 43, SRPConfigMobs.inhooMEnabled), SRPEntities.CreateEntityMob("host", EntityHost.class, 8611072, 16711900, 48, SRPConfigMobs.hostEnabled), SRPEntities.CreateEntityMob("hostii", EntityHostII.class, 8611072, 16711900, 75, SRPConfigMobs.herdEnabled), SRPEntities.CreateEntityMob("heed", EntityHeed.class, 8350208, 0x404040, 63, SRPConfigMobs.heedEnabled), SRPEntities.CreateEntityMob("crux", EntityCruxA.class, 8339200, 11992832, 62, SRPConfigMobs.cruxaEnabled), SRPEntities.CreateEntityMob("crux_incomplete", EntityCruxB.class, 8339200, 11992832, 320, SRPConfigMobs.cruxaEnabled), SRPEntities.CreateEntityMob("thrall", EntityMes.class, 8339200, 11992832, 80, SRPConfigMobs.thrallEnabled), SRPEntities.CreateEntityMob("dredge", EntityDone.class, 8339200, 11992832, 319, SRPConfigMobs.doneEnabled), SRPEntities.CreateEntityMob("airscrew", EntityLeer.class, 8339200, 11992832, 328, true), SRPEntities.CreateEntityMob("carrier_worm", EntityQuac.class, 8339200, 11992832, 327, true), SRPEntities.CreateEntityMob("pri_longarms", EntityShyco.class, 8350208, 0x404040, 1, SRPConfigMobs.shycoEnabled), SRPEntities.CreateEntityMob("pri_manducater", EntityHull.class, 8350208, 0x404040, 7, SRPConfigMobs.hullEnabled), SRPEntities.CreateEntityMob("pri_reeker", EntityNogla.class, 8350208, 0x404040, 10, SRPConfigMobs.noglaEnabled), SRPEntities.CreateEntityMob("pri_yelloweye", EntityEmana.class, 8350208, 0x404040, 4, SRPConfigMobs.emanaEnabled), SRPEntities.CreateEntityMob("pri_summoner", EntityCanra.class, 8350208, 0x404040, 8, SRPConfigMobs.canraEnabled), SRPEntities.CreateEntityMob("pri_bolster", EntityBano.class, 8350208, 0x404040, 17, SRPConfigMobs.zetmoEnabled), SRPEntities.CreateEntityMob("pri_tozoon", EntityWymo.class, 8350208, 0x404040, 37, SRPConfigMobs.wymoEnabled), SRPEntities.CreateEntityMob("pri_arachnida", EntityRanrac.class, 8350208, 0x404040, 38, SRPConfigMobs.arachnidaEnabled), SRPEntities.CreateEntityMob("pri_devourer", EntityLum.class, 8350208, 0x404040, 66, SRPConfigMobs.lumEnabled), SRPEntities.CreateEntityMob("pri_vermin", EntityIki.class, 8350208, 0x404040, 92, SRPConfigMobs.ikiEnabled), SRPEntities.CreateEntityMob("pri_viscera", EntityGim.class, 8350208, 0x404040, 317, SRPConfigMobs.gimEnabled), SRPEntities.CreateEntityMob("pri_burrower", EntityZaa.class, 8350208, 0x404040, 318, SRPConfigMobs.zaaEnabled), SRPEntities.CreateEntityMob("ada_longarms", EntityShycoAdapted.class, 8339200, 11992832, 51, SRPConfigMobs.shycoEnabled), SRPEntities.CreateEntityMob("ada_manducater", EntityHullAdapted.class, 8339200, 11992832, 52, SRPConfigMobs.hullEnabled), SRPEntities.CreateEntityMob("ada_reeker", EntityNoglaAdapted.class, 8339200, 11992832, 54, SRPConfigMobs.noglaEnabled), SRPEntities.CreateEntityMob("ada_yelloweye", EntityEmanaAdapted.class, 8339200, 11992832, 55, SRPConfigMobs.emanaEnabled), SRPEntities.CreateEntityMob("ada_summoner", EntityCanraAdapted.class, 8339200, 11992832, 53, SRPConfigMobs.canraEnabled), SRPEntities.CreateEntityMob("ada_bolster", EntityBanoAdapted.class, 8339200, 11992832, 56, SRPConfigMobs.zetmoEnabled), SRPEntities.CreateEntityMob("ada_tozoon", EntityWymoAdapted.class, 8339200, 11992832, 57, SRPConfigMobs.wymoEnabled), SRPEntities.CreateEntityMob("ada_arachnida", EntityRanracAdapted.class, 8339200, 11992832, 58, SRPConfigMobs.arachnidaEnabled), SRPEntities.CreateEntityMob("ada_devourer", EntityLumAdapted.class, 8350208, 0x404040, 81, SRPConfigMobs.lumEnabled), SRPEntities.CreateEntityMob("ada_vermin", EntityIkiAdapted.class, 8350208, 0x404040, 333, SRPConfigMobs.ikiEnabled), SRPEntities.CreateEntityMob("ada_viscera", EntityGimAdapted.class, 8350208, 0x404040, 315, SRPConfigMobs.gimEnabled), SRPEntities.CreateEntityMob("ada_burrower", EntityZaaAdapted.class, 8350208, 0x404040, 316, SRPConfigMobs.zaaEnabled), SRPEntities.CreateEntityMob("overseer", EntityAlafha.class, 8611072, 16711900, 9, SRPConfigMobs.alafhaEnabled), SRPEntities.CreateEntityMob("vigilante", EntityAnged.class, 3224855, 3224855, 25, SRPConfigMobs.angedEnabled), SRPEntities.CreateEntityMob("warden", EntityGanro.class, 3224855, 3224855, 33, SRPConfigMobs.ganroEnabled), SRPEntities.CreateEntityMob("bomber_light", EntityOmboo.class, 3224855, 3224855, 47, SRPConfigMobs.ombooEnabled), SRPEntities.CreateEntityMob("marauder", EntityEsor.class, 3224855, 3224855, 50, SRPConfigMobs.esorEnabled), SRPEntities.CreateEntityMob("monarch", EntityOrch.class, 3224855, 3224855, 84, SRPConfigMobs.orchEnabled), SRPEntities.CreateEntityMob("grunt", EntityFlog.class, 3224855, 3224855, 60, SRPConfigMobs.flogEnabled), SRPEntities.CreateEntityMob("bomber_heavy", EntityJinjo.class, 3224855, 3224855, 65, SRPConfigMobs.jinjoEnabled), SRPEntities.CreateEntityMob("wraith", EntityElvia.class, 3224855, 3224855, 85, SRPConfigMobs.elviaEnabled), SRPEntities.CreateEntityMob("bogle", EntityLencia.class, 3224855, 3224855, 86, SRPConfigMobs.lenciaEnabled), SRPEntities.CreateEntityMob("haunter", EntityPheon.class, 3224855, 3224855, 87, SRPConfigMobs.pheonEnabled), SRPEntities.CreateEntityMob("carrier_colony", EntityVesta.class, 3224855, 3224855, 88, SRPConfigMobs.vestaEnabled), SRPEntities.CreateEntityMob("succor", EntityFlam.class, 3224855, 3224855, 89, true), SRPEntities.CreateEntityMob("seeker", EntitySoo.class, 3224855, 3224855, 82, true), SRPEntities.CreateEntityMob("architect", EntityTenn.class, 3224855, 3224855, 90, true), SRPEntities.CreateEntityMob("anc_dreadnaut", EntityOronco.class, 4272252, 4272252, 24, SRPConfigMobs.oroncoEnabled), SRPEntities.CreateEntityMob("anc_overlord", EntityTerla.class, 4272252, 4272252, 20, SRPConfigMobs.terlaEnabled), SRPEntities.CreateEntityMob("anc_pod", EntityDropPod.class, 4272252, 4272252, 34, true), SRPEntities.CreateEntityMob("anc_dreadnaut_ten", EntityOroncoTen.class, 4272252, 4272252, 35, SRPConfigMobs.oroncoEnabled), SRPEntities.CreateEntityProjectile("pullingball", EntityProjectilePullball.class, 111), SRPEntities.CreateEntityProjectile("webball", EntityProjectileWebball.class, 101), SRPEntities.CreateEntityProjectile("spineball", EntityProjectileSpineball.class, 102), SRPEntities.CreateEntityProjectile("nadeball", EntityProjectileNade.class, 110), SRPEntities.CreateEntityProjectile("salivaball", EntityProjectileAlafhaBall.class, 103), SRPEntities.CreateEntityProjectile("ballball", EntityProjectileAngedball.class, 104), SRPEntities.CreateEntityProjectile("ancientball", EntityProjectileAncientball.class, 105), SRPEntities.CreateEntityProjectile("homming", EntityProjectileHomming.class, 106), SRPEntities.CreateEntityProjectile("antiinfestedblock", EntityThrowableAntiInfestedBlock.class, 107), SRPEntities.CreateEntityProjectile("biomassball", EntityProjectileBiomass.class, 108), SRPEntities.CreateEntityProjectile("missile", EntityProjectileDragonE.class, 109), SRPEntities.CreateEntityProjectile("balltall", EntityProjectileElviaBall.class, 112), SRPEntities.CreateEntityProjectile("ballmall", EntityProjectileLenciaBall.class, 113), SRPEntities.CreateEntityProjectile("salivaeff", EntityProjectileEffects.class, 331), SRPEntities.CreateEntityMob("kirin", EntityKirin.class, 4272252, 4272252, 67, true), SRPEntities.CreateEntityMob("draconite", EntityHeblu.class, 4272252, 4272252, 309, true), SRPEntities.CreateEntityProjectile("orbscary", EntityOrbScary.class, 210), SRPEntities.CreateEntityProjectile("orbvoid", EntityOrbVoid.class, 256, 9501), SRPEntities.CreateEntityProjectile("orbboom", EntityOrbBoom.class, 256, 9502), SRPEntities.CreateEntityProjectile("source", EntitySource.class, 208), SRPEntities.CreateEntityProjectile("remain", EntityRemain.class, 209), SRPEntities.CreateEntityProjectile("bomb", EntityBomb.class, 203), SRPEntities.CreateEntityProjectile("cloudtoxic", EntityToxicCloud.class, 207), SRPEntities.CreateEntityProjectile("biomass", EntityBiomass.class, 205), SRPEntities.CreateEntityProjectile("gore", EntityGore.class, 204), SRPEntities.CreateEntityProjectile("tendril", EntityTendril.class, 202), SRPEntities.CreateEntityProjectile("scent", EntityParasiticScent.class, 206), SRPEntities.CreateEntityProjectile("wave", EntityWave.class, 211), SRPEntities.CreateEntityProjectile("waveshock", EntityWaveShock.class, 213), SRPEntities.CreateEntityProjectile("nade", EntityNade.class, 212), SRPEntities.CreateEntityProjectile("meteor", EntityMeteor.class, 256, 9500)};
            for (int i = 0; i < SRPENTITIES.length; ++i) {
                if (SRPENTITIES[i] == null) continue;
                registry.register((IForgeRegistryEntry)SRPENTITIES[i]);
            }
        }
    }
}

