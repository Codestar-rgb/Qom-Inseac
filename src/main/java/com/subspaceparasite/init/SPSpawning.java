/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.Biome
 *  net.minecraft.world.biome.Biome$SpawnListEntry
 *  net.minecraftforge.event.entity.living.LivingSpawnEvent$CheckSpawn
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.Event$Result
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.subspaceparasite.init;

import com.subspaceparasite.entity.ai.misc.EntityCanColony;
import com.subspaceparasite.entity.ai.misc.EntityCanFly;
import com.subspaceparasite.entity.ai.misc.EntityCanHaveBodies;
import com.subspaceparasite.entity.ai.misc.EntityCanSpawn;
import com.subspaceparasite.entity.ai.misc.EntityCanSwim;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.entity.monster.adapted.EntityBanoAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityCanraAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityEmanaAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityHullAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityNoglaAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityRanracAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityShycoAdapted;
import com.subspaceparasite.entity.monster.ancient.EntityOronco;
import com.subspaceparasite.entity.monster.ancient.EntityTerla;
import com.subspaceparasite.entity.monster.crude.EntityCruxA;
import com.subspaceparasite.entity.monster.crude.EntityHeed;
import com.subspaceparasite.entity.monster.crude.EntityHost;
import com.subspaceparasite.entity.monster.crude.EntityHostII;
import com.subspaceparasite.entity.monster.crude.EntityInhooM;
import com.subspaceparasite.entity.monster.crude.EntityInhooS;
import com.subspaceparasite.entity.monster.derived.EntityHeblu;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityVenkrol;
import com.subspaceparasite.entity.monster.feral.EntityFerCow;
import com.subspaceparasite.entity.monster.feral.EntityFerEnderman;
import com.subspaceparasite.entity.monster.feral.EntityFerHorse;
import com.subspaceparasite.entity.monster.feral.EntityFerHuman;
import com.subspaceparasite.entity.monster.feral.EntityFerPig;
import com.subspaceparasite.entity.monster.feral.EntityFerSheep;
import com.subspaceparasite.entity.monster.feral.EntityFerVillager;
import com.subspaceparasite.entity.monster.feral.EntityFerWolf;
import com.subspaceparasite.entity.monster.hijacked.EntityHiGolem;
import com.subspaceparasite.entity.monster.inborn.EntityAta;
import com.subspaceparasite.entity.monster.inborn.EntityButhol;
import com.subspaceparasite.entity.monster.inborn.EntityKol;
import com.subspaceparasite.entity.monster.inborn.EntityLodo;
import com.subspaceparasite.entity.monster.inborn.EntityMudo;
import com.subspaceparasite.entity.monster.inborn.EntityNuuh;
import com.subspaceparasite.entity.monster.inborn.EntityRathol;
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
import com.subspaceparasite.entity.monster.infected.EntityInfVillager;
import com.subspaceparasite.entity.monster.infected.EntityInfWolf;
import com.subspaceparasite.entity.monster.primitive.EntityBano;
import com.subspaceparasite.entity.monster.primitive.EntityCanra;
import com.subspaceparasite.entity.monster.primitive.EntityEmana;
import com.subspaceparasite.entity.monster.primitive.EntityHull;
import com.subspaceparasite.entity.monster.primitive.EntityIki;
import com.subspaceparasite.entity.monster.primitive.EntityNogla;
import com.subspaceparasite.entity.monster.primitive.EntityRanrac;
import com.subspaceparasite.entity.monster.primitive.EntityShyco;
import com.subspaceparasite.entity.monster.primitive.EntityWymo;
import com.subspaceparasite.entity.monster.pure.EntityAlafha;
import com.subspaceparasite.entity.monster.pure.EntityAnged;
import com.subspaceparasite.entity.monster.pure.EntityEsor;
import com.subspaceparasite.entity.monster.pure.EntityFlog;
import com.subspaceparasite.entity.monster.pure.EntityGanro;
import com.subspaceparasite.entity.monster.pure.EntityOmboo;
import com.subspaceparasite.entity.monster.pure.preeminent.EntityElvia;
import com.subspaceparasite.entity.monster.pure.preeminent.EntityJinjo;
import com.subspaceparasite.entity.monster.pure.preeminent.EntityLencia;
import com.subspaceparasite.entity.monster.pure.preeminent.EntityPheon;
import com.subspaceparasite.entity.monster.pure.preeminent.EntityVesta;
import com.subspaceparasite.init.SPBiomes;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigMobs;
import com.subspaceparasite.util.config.SPConfigSystems;
import com.subspaceparasite.util.config.SPConfigWorld;
import com.subspaceparasite.world.SPSaveData;
import com.subspaceparasite.world.SPWorldData;
import com.subspaceparasite.world.biome.BiomeParasiteBase;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SPSpawning {
    private static List<Biome.SpawnListEntry> PHASEMINUSONE;
    private static List<Biome.SpawnListEntry> PHASEZERO;
    private static List<Biome.SpawnListEntry> PHASEONE;
    private static List<Biome.SpawnListEntry> PHASETWO;
    private static List<Biome.SpawnListEntry> PHASETHREE;
    private static List<Biome.SpawnListEntry> PHASEFOUR;
    private static List<Biome.SpawnListEntry> PHASEFIVE;
    private static List<Biome.SpawnListEntry> PHASESIX;
    private static List<Biome.SpawnListEntry> PHASESEVEN;
    private static List<Biome.SpawnListEntry> PHASEEIGHT;
    private static List<Biome.SpawnListEntry> PHASENINE;
    private static List<Biome.SpawnListEntry> PHASETEN;
    private static List<Biome.SpawnListEntry> LEVELONE;
    private static List<Biome.SpawnListEntry> LEVELTWO;
    private static List<Biome.SpawnListEntry> LEVELTHREE;
    private static List<Biome.SpawnListEntry> LEVELFOUR;
    public static boolean totalParasites;

    public static void init() {
        if (!SPConfig.allowMobs) {
            return;
        }
        if (!SPConfigSystems.useEvolution || SPConfigSystems.useEvolution && !SPConfigSystems.phaseCustomSpawner) {
            for (Biome biome : Biome.field_185377_q) {
                SPSpawning.addSpawn(0, EntityShyco.class, 1, 1, biome, SPConfigMobs.shycoSpawnRate, SPConfigMobs.shycoEnabled);
                SPSpawning.addSpawn(0, EntityShycoAdapted.class, 1, 1, biome, SPConfigMobs.shycoASpawnRate, SPConfigMobs.shycoEnabled);
                SPSpawning.addSpawn(0, EntityEmana.class, 2, 3, biome, SPConfigMobs.emanaSpawnRate, SPConfigMobs.emanaEnabled);
                SPSpawning.addSpawn(0, EntityEmanaAdapted.class, 2, 3, biome, SPConfigMobs.emanaASpawnRate, SPConfigMobs.emanaEnabled);
                SPSpawning.addSpawn(0, EntityHull.class, 4, 6, biome, SPConfigMobs.hullSpawnRate, SPConfigMobs.hullEnabled);
                SPSpawning.addSpawn(0, EntityHullAdapted.class, 4, 6, biome, SPConfigMobs.hullASpawnRate, SPConfigMobs.hullEnabled);
                SPSpawning.addSpawn(0, EntityCanra.class, 1, 1, biome, SPConfigMobs.canraSpawnRate, SPConfigMobs.canraEnabled);
                SPSpawning.addSpawn(0, EntityCanraAdapted.class, 1, 1, biome, SPConfigMobs.canraASpawnRate, SPConfigMobs.canraEnabled);
                SPSpawning.addSpawn(0, EntityNogla.class, 1, 2, biome, SPConfigMobs.noglaSpawnRate, SPConfigMobs.noglaEnabled);
                SPSpawning.addSpawn(0, EntityNoglaAdapted.class, 1, 2, biome, SPConfigMobs.noglaASpawnRate, SPConfigMobs.noglaEnabled);
                SPSpawning.addSpawn(0, EntityBano.class, 1, 1, biome, SPConfigMobs.zetmoSpawnRate, SPConfigMobs.zetmoEnabled);
                SPSpawning.addSpawn(0, EntityBanoAdapted.class, 1, 1, biome, SPConfigMobs.zetmoASpawnRate, SPConfigMobs.zetmoEnabled);
                SPSpawning.addSpawn(0, EntityRanrac.class, 1, 1, biome, SPConfigMobs.arachnidaSpawnRate, SPConfigMobs.arachnidaEnabled);
                SPSpawning.addSpawn(0, EntityRanracAdapted.class, 1, 1, biome, SPConfigMobs.arachnidaASpawnRate, SPConfigMobs.arachnidaEnabled);
                SPSpawning.addSpawn(0, EntityWymo.class, 1, 1, biome, SPConfigMobs.wymoSpawnRate, SPConfigMobs.wymoEnabled);
                SPSpawning.addSpawn(0, EntityIki.class, 1, 1, biome, SPConfigMobs.ikiSpawnRate, SPConfigMobs.ikiEnabled);
                SPSpawning.addSpawn(0, EntityRathol.class, 2, 2, biome, SPConfigMobs.ratholSpawnRate, SPConfigMobs.ratholEnabled);
                SPSpawning.addSpawn(0, EntityButhol.class, 1, 2, biome, SPConfigMobs.butholSpawnRate, SPConfigMobs.butholEnabled);
                SPSpawning.addSpawn(0, EntityMudo.class, 3, 6, biome, SPConfigMobs.mudoSpawnRate, SPConfigMobs.mudoEnabled);
                SPSpawning.addSpawn(0, EntityLodo.class, 2, 5, biome, SPConfigMobs.lodoSpawnRate, SPConfigMobs.lodoEnabled);
                SPSpawning.addSpawn(0, EntityKol.class, 1, 1, biome, SPConfigMobs.kolSpawnRate, SPConfigMobs.kolEnabled);
                SPSpawning.addSpawn(0, EntityNuuh.class, 1, 1, biome, SPConfigMobs.nuuhSpawnRate, SPConfigMobs.nuuhEnabled);
                SPSpawning.addSpawn(0, EntityAta.class, 1, 1, biome, SPConfigMobs.ataSpawnRate, SPConfigMobs.ataEnabled);
                SPSpawning.addSpawn(0, EntityInfBear.class, 1, 1, biome, SPConfigMobs.infbearSpawnRate, SPConfigMobs.infbearEnabled);
                SPSpawning.addSpawn(0, EntityDorpa.class, 1, 1, biome, SPConfigMobs.dorpaSpawnRate, SPConfigMobs.dorpaEnabled);
                SPSpawning.addSpawn(0, EntityInfEnderman.class, 1, 1, biome, SPConfigMobs.infendermanSpawnRate, SPConfigMobs.infendermanEnabled);
                SPSpawning.addSpawn(0, EntityInfHuman.class, 3, 5, biome, SPConfigMobs.infhumanSpawnRate, SPConfigMobs.infhumanEnabled);
                SPSpawning.addSpawn(0, EntityInfCow.class, 1, 3, biome, SPConfigMobs.infcowSpawnRate, SPConfigMobs.infcowEnabled);
                SPSpawning.addSpawn(0, EntityInfSheep.class, 1, 3, biome, SPConfigMobs.infsheepSpawnRate, SPConfigMobs.infsheepEnabled);
                SPSpawning.addSpawn(0, EntityInfWolf.class, 3, 6, biome, SPConfigMobs.infwolfSpawnRate, SPConfigMobs.infwolfEnabled);
                SPSpawning.addSpawn(0, EntityInfPig.class, 3, 6, biome, SPConfigMobs.infpigSpawnRate, SPConfigMobs.infpigEnabled);
                SPSpawning.addSpawn(0, EntityInfVillager.class, 3, 6, biome, SPConfigMobs.infvillagerSpawnRate, SPConfigMobs.infvillagerEnabled);
                SPSpawning.addSpawn(0, EntityInfHorse.class, 3, 6, biome, SPConfigMobs.infhorseSpawnRate, SPConfigMobs.infhorseEnabled);
                SPSpawning.addSpawn(0, EntityInfPlayer.class, 3, 6, biome, SPConfigMobs.infadventurerSpawnRate, SPConfigMobs.infadventurerEnabled);
                SPSpawning.addSpawn(0, EntityInfDragonE.class, 3, 6, biome, SPConfigMobs.infdragoneSpawnRate, SPConfigMobs.infdragoneEnabled);
                SPSpawning.addSpawn(0, EntityFerEnderman.class, 1, 1, biome, SPConfigMobs.ferendermanSpawnRate, SPConfigMobs.ferendermanEnabled);
                SPSpawning.addSpawn(0, EntityFerHuman.class, 3, 5, biome, SPConfigMobs.ferhumanSpawnRate, SPConfigMobs.ferhumanEnabled);
                SPSpawning.addSpawn(0, EntityFerCow.class, 1, 3, biome, SPConfigMobs.fercowSpawnRate, SPConfigMobs.fercowEnabled);
                SPSpawning.addSpawn(0, EntityFerSheep.class, 1, 3, biome, SPConfigMobs.fersheepSpawnRate, SPConfigMobs.fersheepEnabled);
                SPSpawning.addSpawn(0, EntityFerWolf.class, 3, 6, biome, SPConfigMobs.ferwolfSpawnRate, SPConfigMobs.ferwolfEnabled);
                SPSpawning.addSpawn(0, EntityFerPig.class, 3, 6, biome, SPConfigMobs.ferpigSpawnRate, SPConfigMobs.ferpigEnabled);
                SPSpawning.addSpawn(0, EntityFerVillager.class, 3, 6, biome, SPConfigMobs.fervillagerSpawnRate, SPConfigMobs.fervillagerEnabled);
                SPSpawning.addSpawn(0, EntityFerHorse.class, 3, 6, biome, SPConfigMobs.ferhorseSpawnRate, SPConfigMobs.ferhorseEnabled);
                SPSpawning.addSpawn(0, EntityHiGolem.class, 1, 1, biome, SPConfigMobs.higolemSpawnRate, SPConfigMobs.higolemEnabled);
                SPSpawning.addSpawn(0, EntityHost.class, 1, 1, biome, SPConfigMobs.hostSpawnRate, SPConfigMobs.hostEnabled);
                SPSpawning.addSpawn(0, EntityHostII.class, 1, 1, biome, SPConfigMobs.herdSpawnRate, SPConfigMobs.herdEnabled);
                SPSpawning.addSpawn(0, EntityHeed.class, 1, 1, biome, SPConfigMobs.heedSpawnRate, SPConfigMobs.heedEnabled);
                SPSpawning.addSpawn(0, EntityCruxA.class, 1, 1, biome, SPConfigMobs.cruxaSpawnRate, SPConfigMobs.cruxaEnabled);
                SPSpawning.addSpawn(0, EntityInhooS.class, 2, 5, biome, SPConfigMobs.inhooSSpawnRate, SPConfigMobs.inhooSEnabled);
                SPSpawning.addSpawn(0, EntityInhooM.class, 2, 5, biome, SPConfigMobs.inhooMSpawnRate, SPConfigMobs.inhooMEnabled);
                SPSpawning.addSpawn(0, EntityAlafha.class, 1, 1, biome, SPConfigMobs.alafhaSpawnRate, SPConfigMobs.alafhaEnabled);
                SPSpawning.addSpawn(0, EntityGanro.class, 1, 1, biome, SPConfigMobs.ganroSpawnRate, SPConfigMobs.ganroEnabled);
                SPSpawning.addSpawn(0, EntityAnged.class, 2, 2, biome, SPConfigMobs.angedSpawnRate, SPConfigMobs.angedEnabled);
                SPSpawning.addSpawn(0, EntityEsor.class, 1, 1, biome, SPConfigMobs.esorSpawnRate, SPConfigMobs.esorEnabled);
                SPSpawning.addSpawn(0, EntityOmboo.class, 2, 2, biome, SPConfigMobs.ombooSpawnRate, SPConfigMobs.ombooEnabled);
                SPSpawning.addSpawn(0, EntityFlog.class, 3, 6, biome, SPConfigMobs.flogSpawnRate, SPConfigMobs.flogEnabled);
                SPSpawning.addSpawn(0, EntityJinjo.class, 3, 6, biome, SPConfigMobs.jinjoSpawnRate, SPConfigMobs.jinjoEnabled);
                SPSpawning.addSpawn(0, EntityElvia.class, 3, 6, biome, SPConfigMobs.elviaSpawnRate, SPConfigMobs.elviaEnabled);
                SPSpawning.addSpawn(0, EntityLencia.class, 3, 6, biome, SPConfigMobs.lenciaSpawnRate, SPConfigMobs.lenciaEnabled);
                SPSpawning.addSpawn(0, EntityPheon.class, 3, 6, biome, SPConfigMobs.pheonSpawnRate, SPConfigMobs.pheonEnabled);
                SPSpawning.addSpawn(0, EntityVesta.class, 3, 6, biome, SPConfigMobs.vestaSpawnRate, SPConfigMobs.vestaEnabled);
                SPSpawning.addSpawn(0, EntityHeblu.class, 1, 1, biome, SPConfigMobs.hebluSpawnRate, SPConfigMobs.hebluEnabled);
                SPSpawning.addSpawn(0, EntityOronco.class, 1, 1, biome, SPConfigMobs.oroncoSpawnRate, SPConfigMobs.oroncoEnabled);
                SPSpawning.addSpawn(0, EntityTerla.class, 1, 1, biome, SPConfigMobs.terlaSpawnRate, SPConfigMobs.terlaEnabled);
                SPSpawning.addSpawn(1, EntityVenkrol.class, 1, 1, biome, SPConfigMobs.venkrolSpawnrate, SPConfigSystems.rsEnabled);
            }
        } else {
            String[] here = new String[4];
            PHASEMINUSONE = new ArrayList<Biome.SpawnListEntry>();
            PHASEZERO = new ArrayList<Biome.SpawnListEntry>();
            PHASEONE = new ArrayList<Biome.SpawnListEntry>();
            PHASETWO = new ArrayList<Biome.SpawnListEntry>();
            PHASETHREE = new ArrayList<Biome.SpawnListEntry>();
            PHASEFOUR = new ArrayList<Biome.SpawnListEntry>();
            PHASEFIVE = new ArrayList<Biome.SpawnListEntry>();
            PHASESIX = new ArrayList<Biome.SpawnListEntry>();
            PHASESEVEN = new ArrayList<Biome.SpawnListEntry>();
            PHASEEIGHT = new ArrayList<Biome.SpawnListEntry>();
            PHASENINE = new ArrayList<Biome.SpawnListEntry>();
            PHASETEN = new ArrayList<Biome.SpawnListEntry>();
            SPSpawning.listInit(SPConfigSystems.phaseSpawnEntryMinusne, PHASEMINUSONE);
            SPSpawning.listInit(SPConfigSystems.phaseSpawnEntryZero, PHASEZERO);
            SPSpawning.listInit(SPConfigSystems.phaseSpawnEntryOne, PHASEONE);
            SPSpawning.listInit(SPConfigSystems.phaseSpawnEntryTwo, PHASETWO);
            SPSpawning.listInit(SPConfigSystems.phaseSpawnEntryThree, PHASETHREE);
            SPSpawning.listInit(SPConfigSystems.phaseSpawnEntryFour, PHASEFOUR);
            SPSpawning.listInit(SPConfigSystems.phaseSpawnEntryFive, PHASEFIVE);
            SPSpawning.listInit(SPConfigSystems.phaseSpawnEntrySix, PHASESIX);
            SPSpawning.listInit(SPConfigSystems.phaseSpawnEntrySeven, PHASESEVEN);
            SPSpawning.listInit(SPConfigSystems.phaseSpawnEntryEight, PHASEEIGHT);
            SPSpawning.listInit(SPConfigSystems.phaseSpawnEntryNine, PHASENINE);
            SPSpawning.listInit(SPConfigSystems.phaseSpawnEntryTen, PHASETEN);
            LEVELONE = new ArrayList<Biome.SpawnListEntry>();
            LEVELTWO = new ArrayList<Biome.SpawnListEntry>();
            LEVELTHREE = new ArrayList<Biome.SpawnListEntry>();
            LEVELFOUR = new ArrayList<Biome.SpawnListEntry>();
            SPSpawning.listInit(SPConfigSystems.deveSpawnEntryUDOne, LEVELONE);
            SPSpawning.listInit(SPConfigSystems.deveSpawnEntryUDTwo, LEVELTWO);
            SPSpawning.listInit(SPConfigSystems.deveSpawnEntryUDThree, LEVELTHREE);
            SPSpawning.listInit(SPConfigSystems.deveSpawnEntryUDFour, LEVELFOUR);
        }
    }

    private static void listInit(String[] list, List<Biome.SpawnListEntry> in) {
        String[] here = new String[4];
        for (int i = 0; i < list.length; ++i) {
            if (list[i] == null) continue;
            here = list[i].split(";");
            int min = Integer.parseInt(here[1]);
            int max = Integer.parseInt(here[2]);
            int weight = Integer.parseInt(here[3]);
            in.add(new Biome.SpawnListEntry(EntityList.getClass((ResourceLocation)new ResourceLocation(here[0])), weight, min, max));
        }
    }

    public static void removeInit() {
        if (SPConfigSystems.useEvolution) {
            return;
        }
        for (Biome biome : Biome.field_185377_q) {
            boolean rem = true;
            boolean gg = true;
            while (gg) {
                String eee;
                int i;
                for (i = 0; i < biome.func_76747_a(EnumCreatureType.MONSTER).size(); ++i) {
                    eee = ((Biome.SpawnListEntry)biome.func_76747_a((EnumCreatureType)EnumCreatureType.MONSTER).get((int)i)).field_76300_b.toString();
                    if (eee.contains("subspaceparasite") && rem) {
                        gg = true;
                        biome.func_76747_a(EnumCreatureType.MONSTER).remove(i);
                        continue;
                    }
                    gg = false;
                }
                for (i = 0; i < biome.func_76747_a(EnumCreatureType.MONSTER).size(); ++i) {
                    eee = ((Biome.SpawnListEntry)biome.func_76747_a((EnumCreatureType)EnumCreatureType.MONSTER).get((int)i)).field_76300_b.toString();
                    if (!eee.contains("subspaceparasite") || !rem) continue;
                    gg = true;
                    biome.func_76747_a(EnumCreatureType.MONSTER).remove(i);
                }
            }
        }
    }

    public static List<Biome.SpawnListEntry> getSpawns(World world, int id, int phase, SPSaveData data) {
        if (phase == -2) {
            return null;
        }
        if (data != null) {
            int[] vaal;
            if (world.field_73012_v.nextDouble() < SPConfigSystems.deveMobChance && data.getDeveLevel() > 0) {
                switch (data.getDeveLevel()) {
                    case 1: {
                        return LEVELONE;
                    }
                    case 2: {
                        return LEVELTWO;
                    }
                    case 3: {
                        return LEVELTHREE;
                    }
                    case 4: {
                        return LEVELFOUR;
                    }
                }
            }
            if ((vaal = data.getDisloValues(id))[5] > 0) {
                return null;
            }
            if (vaal[14] > 0) {
                phase += vaal[14];
            }
            switch (phase) {
                case -1: {
                    if (data.getEIVArea(id) > 0) {
                        return PHASEMINUSONE;
                    }
                    return null;
                }
                case 0: {
                    return PHASEZERO;
                }
                case 1: {
                    return PHASEONE;
                }
                case 2: {
                    return PHASETWO;
                }
                case 3: {
                    return PHASETHREE;
                }
                case 4: {
                    return PHASEFOUR;
                }
                case 5: {
                    return PHASEFIVE;
                }
                case 6: {
                    return PHASESIX;
                }
                case 7: {
                    return PHASESEVEN;
                }
                case 8: {
                    return PHASEEIGHT;
                }
                case 9: {
                    return PHASENINE;
                }
            }
            return PHASETEN;
        }
        return null;
    }

    public static void initBiome() {
        SPSpawning.setBiomeSpawn(SPConfigWorld.biomeOneSpawnEntry, SPBiomes.biomeShrouded);
        SPSpawning.setBiomeSpawn(SPConfigWorld.biomeThreeSpawnEntry, SPBiomes.biomeHarlequin);
    }

    private static void setBiomeSpawn(String[] list, Biome biome) {
        String[] here = new String[5];
        for (int i = 0; i < list.length; ++i) {
            if (list[i] == null) continue;
            here = list[i].split(";");
            int min = Integer.parseInt(here[1]);
            int max = Integer.parseInt(here[2]);
            int weight = Integer.parseInt(here[3]);
            int type = Integer.parseInt(here[4]);
            Class mob = EntityList.getClass((ResourceLocation)new ResourceLocation(here[0]));
            SPSpawning.addSpawn(type, mob, min, max, biome, weight, true);
        }
    }

    public static void addSpawn(int type, Class<? extends EntityLiving> entity, int groupMin, int groupMax, Biome biome, int weight, boolean addSpawn) {
        if (!addSpawn || weight <= 0) {
            return;
        }
        switch (type) {
            case 0: {
                biome.func_76747_a(EnumCreatureType.MONSTER).add(new Biome.SpawnListEntry(entity, weight, groupMin, groupMax));
                return;
            }
            case 1: {
                biome.func_76747_a(EnumCreatureType.CREATURE).add(new Biome.SpawnListEntry(entity, weight, groupMin, groupMax));
                return;
            }
            case 2: {
                biome.func_76747_a(EnumCreatureType.WATER_CREATURE).add(new Biome.SpawnListEntry(entity, weight, groupMin, groupMax));
                return;
            }
        }
    }

    @Mod.EventBusSubscriber(modid="subspaceparasite")
    public static class DimensionHandler {
        @SubscribeEvent
        public static void onSpawn(LivingSpawnEvent.CheckSpawn event) {
            if (event.getEntity() instanceof EntityParasiteBase) {
                List serverList = event.getEntity().field_70170_p.field_72996_f;
                int count = 0;
                int gnatCount = 0;
                int worker = 0;
                int waterParasites = 0;
                int airParasites = 0;
                for (Object entity : serverList) {
                    if (!(entity instanceof EntityParasiteBase)) continue;
                    if (entity instanceof EntityCanHaveBodies) {
                        EntityCanHaveBodies bodies = (EntityCanHaveBodies)entity;
                        if (bodies.getBodyNumber() != 0) continue;
                        ++count;
                        continue;
                    }
                    ++count;
                    if (entity instanceof EntityAta) {
                        ++gnatCount;
                    }
                    if (entity instanceof EntityCanSwim) {
                        ++waterParasites;
                    }
                    if (entity instanceof EntityCanFly) {
                        ++airParasites;
                    }
                    if (!(entity instanceof EntityKol)) continue;
                    ++worker;
                }
                int players = event.getEntity().field_70170_p.field_73010_i.size() * SPConfig.worldMobCapPlusPlayer;
                if (event.getEntity() instanceof EntityAta && gnatCount > SPConfig.worldGnatCap) {
                    event.setResult(Event.Result.DENY);
                    return;
                }
                if (event.getEntity() instanceof EntityKol && worker > 10) {
                    event.setResult(Event.Result.DENY);
                    return;
                }
                if (count > SPConfig.worldSpawningMobCap + players) {
                    totalParasites = false;
                    event.setResult(Event.Result.DENY);
                    if (count > 200) {
                        System.out.println("200!! SOO MANY PARASITES");
                        for (Entity entity : serverList) {
                            if (!(entity instanceof EntityParasiteBase)) continue;
                            ((EntityParasiteBase)entity).func_70106_y();
                            System.out.println("----- SOO MANY PARASITES");
                            if (--count >= SPConfig.worldSpawningMobCap) continue;
                            return;
                        }
                    }
                    return;
                }
                if (waterParasites > SPConfig.worldWaterCap && event.getEntity() instanceof EntityCanSwim) {
                    event.setResult(Event.Result.DENY);
                    return;
                }
                if (airParasites > SPConfig.worldAirCap && event.getEntity() instanceof EntityCanFly) {
                    event.setResult(Event.Result.DENY);
                    return;
                }
                if (!SPConfigSystems.useEvolution) {
                    boolean inv = false;
                    boolean flagI = false;
                    if (SPConfig.blackListedDimensionsWhite) {
                        inv = true;
                    }
                    for (int i : SPConfig.blackListedDimensions) {
                        if (SPConfig.blackListedDimensionsWhite) {
                            if (i != event.getWorld().field_73011_w.getDimension()) continue;
                            flagI = true;
                            break;
                        }
                        if (i != event.getWorld().field_73011_w.getDimension()) continue;
                        event.setResult(Event.Result.DENY);
                        return;
                    }
                    if (inv && !flagI) {
                        event.setResult(Event.Result.DENY);
                        return;
                    }
                }
                if (SPConfigSystems.useEvolution || SPConfigWorld.coloniesActivated) {
                    EntityCanSpawn parasiteSus;
                    EntityParasiteBase parasite = (EntityParasiteBase)event.getEntity();
                    if (!parasite.canSpawnSpawn) {
                        event.setResult(Event.Result.DENY);
                        return;
                    }
                    SPWorldData data = SPWorldData.get(parasite.field_70170_p);
                    SPSaveData sopa = SPSaveData.get(parasite.field_70170_p, 51);
                    parasite.setCreatedPhase(sopa.getEvolutionPhase(parasite.field_70170_p.field_73011_w.getDimension()), sopa.getDeveLevel());
                    if (SPConfigSystems.useEvolution && !SPConfigSystems.phaseCustomSpawner) {
                        if (sopa.getEvolutionPhase(parasite.field_70170_p.field_73011_w.getDimension()) <= -1) {
                            event.setResult(Event.Result.DENY);
                            return;
                        }
                        if (!DimensionHandler.canSpawninPhase(sopa.getEvolutionPhase(parasite.field_70170_p.field_73011_w.getDimension()), sopa.getDeveLevel(), parasite)) {
                            event.setResult(Event.Result.DENY);
                            return;
                        }
                    }
                    if (DimensionHandler.checkEvoLock(parasite.getParasiteIDRegister(), sopa) || DimensionHandler.checkColoLock(parasite.getParasiteIDRegister(), data, parasite)) {
                        event.setResult(Event.Result.DENY);
                        return;
                    }
                    if (parasite instanceof EntityCanSpawn && sopa.getNumberIDDataSpawn((parasiteSus = (EntityCanSpawn)((Object)parasite)).getIDSpawn()) < parasiteSus.canSpawnByIDData()) {
                        event.setResult(Event.Result.DENY);
                        return;
                    }
                }
            } else if (event.getEntity() instanceof EntityLivingBase) {
                if (SPConfigSystems.useEvolution && SPSaveData.get(event.getWorld(), 52).getEvolutionPhase(event.getWorld().field_73011_w.getDimension()) >= SPConfigSystems.evolutionNoParasiteSpawnDenied) {
                    event.setResult(Event.Result.DENY);
                }
                if (SPConfigWorld.coloniesActivated && SPWorldData.get(event.getWorld()).nearestColonyPosition(event.getEntity().func_180425_c(), false) != null) {
                    event.setResult(Event.Result.DENY);
                }
            }
        }

        private static boolean canSpawninPhase(int evPhase, int levelUD, EntityParasiteBase parasite) {
            byte type = parasite.getParasiteType();
            switch (evPhase) {
                case -1: {
                    return false;
                }
                case 0: {
                    if (type < SPConfigSystems.phaseMaxParasiteIDZero && type > SPConfigSystems.phaseCancelParasiteIDZero) break;
                    return false;
                }
                case 1: {
                    if (type < SPConfigSystems.phaseMaxParasiteIDOne && type > SPConfigSystems.phaseCancelParasiteIDOne) break;
                    return false;
                }
                case 2: {
                    if (type < SPConfigSystems.phaseMaxParasiteIDTwo && type > SPConfigSystems.phaseCancelParasiteIDTwo) break;
                    return false;
                }
                case 3: {
                    if (type < SPConfigSystems.phaseMaxParasiteIDThree && type > SPConfigSystems.phaseCancelParasiteIDThree) break;
                    return false;
                }
                case 4: {
                    if (type < SPConfigSystems.phaseMaxParasiteIDFour && type > SPConfigSystems.phaseCancelParasiteIDFour) break;
                    return false;
                }
                case 5: {
                    if (type < SPConfigSystems.phaseMaxParasiteIDFive && type > SPConfigSystems.phaseCancelParasiteIDFive) break;
                    return false;
                }
                case 6: {
                    if (type < SPConfigSystems.phaseMaxParasiteIDSix && type > SPConfigSystems.phaseCancelParasiteIDSix) break;
                    return false;
                }
                case 7: {
                    if (type < SPConfigSystems.phaseMaxParasiteIDSeven && type > SPConfigSystems.phaseCancelParasiteIDSeven) break;
                    return false;
                }
                case 8: {
                    if (type < SPConfigSystems.phaseMaxParasiteIDEight && type > SPConfigSystems.phaseCancelParasiteIDEight) break;
                    return false;
                }
                case 9: {
                    if (type < SPConfigSystems.phaseMaxParasiteIDNine && type > SPConfigSystems.phaseCancelParasiteIDNine) break;
                    return false;
                }
                case 10: {
                    if (type < SPConfigSystems.phaseMaxParasiteIDTen && type > SPConfigSystems.phaseCancelParasiteIDTen) break;
                    return false;
                }
            }
            return true;
        }

        private static boolean checkEvoLock(int in, SPSaveData data) {
            return data.checkParasiteID(in);
        }

        private static boolean checkColoLock(int in, SPWorldData data, EntityParasiteBase parasite) {
            int points;
            if (parasite instanceof EntityCanColony) {
                if (!SPConfigWorld.coloniesActivated) {
                    return true;
                }
                points = data.totalColonyPoints(0);
                if (points > 0) {
                    BlockPos origin = data.nearestColonyPosition(parasite.func_180425_c(), false);
                    if (origin == null ? ((EntityCanColony)((Object)parasite)).onlySpawnInside() : !((EntityCanColony)((Object)parasite)).onlySpawnInside()) {
                        return true;
                    }
                } else {
                    return true;
                }
            }
            points = data.totalColonyPoints(0);
            String[] here = new String[2];
            int id = 0;
            int req = 0;
            for (int i = 0; i < SPConfigWorld.preeValues.length; ++i) {
                here = SPConfigWorld.preeValues[i].split(";");
                id = Integer.parseInt(here[0]);
                if (id != in) continue;
                if (parasite.field_70170_p.func_180494_b(parasite.func_180425_c()) instanceof BiomeParasiteBase) {
                    return SPConfigWorld.preeValuesBiome;
                }
                req = Integer.parseInt(here[1]);
                return points < req;
            }
            return false;
        }
    }
}

