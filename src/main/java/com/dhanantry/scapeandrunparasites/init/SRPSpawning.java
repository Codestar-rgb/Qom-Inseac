package com.dhanantry.scapeandrunparasites.init;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanColony;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanFly;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanHaveBodies;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanSpawn;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanSwim;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityBanoAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityCanraAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityEmanaAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityHullAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityNoglaAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityRanracAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityShycoAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.ancient.EntityOronco;
import com.dhanantry.scapeandrunparasites.entity.monster.ancient.EntityTerla;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityCruxA;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityHeed;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityHost;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityHostII;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityInhooM;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityInhooS;
import com.dhanantry.scapeandrunparasites.entity.monster.derived.EntityHeblu;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityVenkrol;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerCow;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerEnderman;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerHorse;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerHuman;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerPig;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerSheep;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerVillager;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerWolf;
import com.dhanantry.scapeandrunparasites.entity.monster.hijacked.EntityHiGolem;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityAta;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityButhol;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityKol;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityLodo;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityMudo;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityNuuh;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityRathol;
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
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfVillager;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfWolf;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityBano;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityCanra;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityEmana;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityHull;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityIki;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityNogla;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityRanrac;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityShyco;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityWymo;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityAlafha;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityAnged;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityEsor;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityFlog;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityGanro;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityOmboo;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent.EntityElvia;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent.EntityJinjo;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent.EntityLencia;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent.EntityPheon;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent.EntityVesta;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import com.dhanantry.scapeandrunparasites.world.SRPWorldData;
import com.dhanantry.scapeandrunparasites.world.biome.BiomeParasiteBase;
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
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SRPSpawning {
   private static List<SpawnListEntry> PHASEMINUSONE;
   private static List<SpawnListEntry> PHASEZERO;
   private static List<SpawnListEntry> PHASEONE;
   private static List<SpawnListEntry> PHASETWO;
   private static List<SpawnListEntry> PHASETHREE;
   private static List<SpawnListEntry> PHASEFOUR;
   private static List<SpawnListEntry> PHASEFIVE;
   private static List<SpawnListEntry> PHASESIX;
   private static List<SpawnListEntry> PHASESEVEN;
   private static List<SpawnListEntry> PHASEEIGHT;
   private static List<SpawnListEntry> PHASENINE;
   private static List<SpawnListEntry> PHASETEN;
   private static List<SpawnListEntry> LEVELONE;
   private static List<SpawnListEntry> LEVELTWO;
   private static List<SpawnListEntry> LEVELTHREE;
   private static List<SpawnListEntry> LEVELFOUR;
   public static boolean totalParasites;
   private static final Logger SRP_LOG = LogManager.getLogger("srparasites");

   public static void init() {
      if (SRPConfig.allowMobs) {
         if (!SRPConfigSystems.useEvolution || SRPConfigSystems.useEvolution && !SRPConfigSystems.phaseCustomSpawner) {
            for (Biome biome : Biome.field_185377_q) {
               addSpawn(0, EntityShyco.class, 1, 1, biome, SRPConfigMobs.shycoSpawnRate, SRPConfigMobs.shycoEnabled);
               addSpawn(0, EntityShycoAdapted.class, 1, 1, biome, SRPConfigMobs.shycoASpawnRate, SRPConfigMobs.shycoEnabled);
               addSpawn(0, EntityEmana.class, 2, 3, biome, SRPConfigMobs.emanaSpawnRate, SRPConfigMobs.emanaEnabled);
               addSpawn(0, EntityEmanaAdapted.class, 2, 3, biome, SRPConfigMobs.emanaASpawnRate, SRPConfigMobs.emanaEnabled);
               addSpawn(0, EntityHull.class, 4, 6, biome, SRPConfigMobs.hullSpawnRate, SRPConfigMobs.hullEnabled);
               addSpawn(0, EntityHullAdapted.class, 4, 6, biome, SRPConfigMobs.hullASpawnRate, SRPConfigMobs.hullEnabled);
               addSpawn(0, EntityCanra.class, 1, 1, biome, SRPConfigMobs.canraSpawnRate, SRPConfigMobs.canraEnabled);
               addSpawn(0, EntityCanraAdapted.class, 1, 1, biome, SRPConfigMobs.canraASpawnRate, SRPConfigMobs.canraEnabled);
               addSpawn(0, EntityNogla.class, 1, 2, biome, SRPConfigMobs.noglaSpawnRate, SRPConfigMobs.noglaEnabled);
               addSpawn(0, EntityNoglaAdapted.class, 1, 2, biome, SRPConfigMobs.noglaASpawnRate, SRPConfigMobs.noglaEnabled);
               addSpawn(0, EntityBano.class, 1, 1, biome, SRPConfigMobs.zetmoSpawnRate, SRPConfigMobs.zetmoEnabled);
               addSpawn(0, EntityBanoAdapted.class, 1, 1, biome, SRPConfigMobs.zetmoASpawnRate, SRPConfigMobs.zetmoEnabled);
               addSpawn(0, EntityRanrac.class, 1, 1, biome, SRPConfigMobs.arachnidaSpawnRate, SRPConfigMobs.arachnidaEnabled);
               addSpawn(0, EntityRanracAdapted.class, 1, 1, biome, SRPConfigMobs.arachnidaASpawnRate, SRPConfigMobs.arachnidaEnabled);
               addSpawn(0, EntityWymo.class, 1, 1, biome, SRPConfigMobs.wymoSpawnRate, SRPConfigMobs.wymoEnabled);
               addSpawn(0, EntityIki.class, 1, 1, biome, SRPConfigMobs.ikiSpawnRate, SRPConfigMobs.ikiEnabled);
               addSpawn(0, EntityRathol.class, 2, 2, biome, SRPConfigMobs.ratholSpawnRate, SRPConfigMobs.ratholEnabled);
               addSpawn(0, EntityButhol.class, 1, 2, biome, SRPConfigMobs.butholSpawnRate, SRPConfigMobs.butholEnabled);
               addSpawn(0, EntityMudo.class, 3, 6, biome, SRPConfigMobs.mudoSpawnRate, SRPConfigMobs.mudoEnabled);
               addSpawn(0, EntityLodo.class, 2, 5, biome, SRPConfigMobs.lodoSpawnRate, SRPConfigMobs.lodoEnabled);
               addSpawn(0, EntityKol.class, 1, 1, biome, SRPConfigMobs.kolSpawnRate, SRPConfigMobs.kolEnabled);
               addSpawn(0, EntityNuuh.class, 1, 1, biome, SRPConfigMobs.nuuhSpawnRate, SRPConfigMobs.nuuhEnabled);
               addSpawn(0, EntityAta.class, 1, 1, biome, SRPConfigMobs.ataSpawnRate, SRPConfigMobs.ataEnabled);
               addSpawn(0, EntityInfBear.class, 1, 1, biome, SRPConfigMobs.infbearSpawnRate, SRPConfigMobs.infbearEnabled);
               addSpawn(0, EntityDorpa.class, 1, 1, biome, SRPConfigMobs.dorpaSpawnRate, SRPConfigMobs.dorpaEnabled);
               addSpawn(0, EntityInfEnderman.class, 1, 1, biome, SRPConfigMobs.infendermanSpawnRate, SRPConfigMobs.infendermanEnabled);
               addSpawn(0, EntityInfHuman.class, 3, 5, biome, SRPConfigMobs.infhumanSpawnRate, SRPConfigMobs.infhumanEnabled);
               addSpawn(0, EntityInfCow.class, 1, 3, biome, SRPConfigMobs.infcowSpawnRate, SRPConfigMobs.infcowEnabled);
               addSpawn(0, EntityInfSheep.class, 1, 3, biome, SRPConfigMobs.infsheepSpawnRate, SRPConfigMobs.infsheepEnabled);
               addSpawn(0, EntityInfWolf.class, 3, 6, biome, SRPConfigMobs.infwolfSpawnRate, SRPConfigMobs.infwolfEnabled);
               addSpawn(0, EntityInfPig.class, 3, 6, biome, SRPConfigMobs.infpigSpawnRate, SRPConfigMobs.infpigEnabled);
               addSpawn(0, EntityInfVillager.class, 3, 6, biome, SRPConfigMobs.infvillagerSpawnRate, SRPConfigMobs.infvillagerEnabled);
               addSpawn(0, EntityInfHorse.class, 3, 6, biome, SRPConfigMobs.infhorseSpawnRate, SRPConfigMobs.infhorseEnabled);
               addSpawn(0, EntityInfPlayer.class, 3, 6, biome, SRPConfigMobs.infadventurerSpawnRate, SRPConfigMobs.infadventurerEnabled);
               addSpawn(0, EntityInfDragonE.class, 3, 6, biome, SRPConfigMobs.infdragoneSpawnRate, SRPConfigMobs.infdragoneEnabled);
               addSpawn(0, EntityFerEnderman.class, 1, 1, biome, SRPConfigMobs.ferendermanSpawnRate, SRPConfigMobs.ferendermanEnabled);
               addSpawn(0, EntityFerHuman.class, 3, 5, biome, SRPConfigMobs.ferhumanSpawnRate, SRPConfigMobs.ferhumanEnabled);
               addSpawn(0, EntityFerCow.class, 1, 3, biome, SRPConfigMobs.fercowSpawnRate, SRPConfigMobs.fercowEnabled);
               addSpawn(0, EntityFerSheep.class, 1, 3, biome, SRPConfigMobs.fersheepSpawnRate, SRPConfigMobs.fersheepEnabled);
               addSpawn(0, EntityFerWolf.class, 3, 6, biome, SRPConfigMobs.ferwolfSpawnRate, SRPConfigMobs.ferwolfEnabled);
               addSpawn(0, EntityFerPig.class, 3, 6, biome, SRPConfigMobs.ferpigSpawnRate, SRPConfigMobs.ferpigEnabled);
               addSpawn(0, EntityFerVillager.class, 3, 6, biome, SRPConfigMobs.fervillagerSpawnRate, SRPConfigMobs.fervillagerEnabled);
               addSpawn(0, EntityFerHorse.class, 3, 6, biome, SRPConfigMobs.ferhorseSpawnRate, SRPConfigMobs.ferhorseEnabled);
               addSpawn(0, EntityHiGolem.class, 1, 1, biome, SRPConfigMobs.higolemSpawnRate, SRPConfigMobs.higolemEnabled);
               addSpawn(0, EntityHost.class, 1, 1, biome, SRPConfigMobs.hostSpawnRate, SRPConfigMobs.hostEnabled);
               addSpawn(0, EntityHostII.class, 1, 1, biome, SRPConfigMobs.herdSpawnRate, SRPConfigMobs.herdEnabled);
               addSpawn(0, EntityHeed.class, 1, 1, biome, SRPConfigMobs.heedSpawnRate, SRPConfigMobs.heedEnabled);
               addSpawn(0, EntityCruxA.class, 1, 1, biome, SRPConfigMobs.cruxaSpawnRate, SRPConfigMobs.cruxaEnabled);
               addSpawn(0, EntityInhooS.class, 2, 5, biome, SRPConfigMobs.inhooSSpawnRate, SRPConfigMobs.inhooSEnabled);
               addSpawn(0, EntityInhooM.class, 2, 5, biome, SRPConfigMobs.inhooMSpawnRate, SRPConfigMobs.inhooMEnabled);
               addSpawn(0, EntityAlafha.class, 1, 1, biome, SRPConfigMobs.alafhaSpawnRate, SRPConfigMobs.alafhaEnabled);
               addSpawn(0, EntityGanro.class, 1, 1, biome, SRPConfigMobs.ganroSpawnRate, SRPConfigMobs.ganroEnabled);
               addSpawn(0, EntityAnged.class, 2, 2, biome, SRPConfigMobs.angedSpawnRate, SRPConfigMobs.angedEnabled);
               addSpawn(0, EntityEsor.class, 1, 1, biome, SRPConfigMobs.esorSpawnRate, SRPConfigMobs.esorEnabled);
               addSpawn(0, EntityOmboo.class, 2, 2, biome, SRPConfigMobs.ombooSpawnRate, SRPConfigMobs.ombooEnabled);
               addSpawn(0, EntityFlog.class, 3, 6, biome, SRPConfigMobs.flogSpawnRate, SRPConfigMobs.flogEnabled);
               addSpawn(0, EntityJinjo.class, 3, 6, biome, SRPConfigMobs.jinjoSpawnRate, SRPConfigMobs.jinjoEnabled);
               addSpawn(0, EntityElvia.class, 3, 6, biome, SRPConfigMobs.elviaSpawnRate, SRPConfigMobs.elviaEnabled);
               addSpawn(0, EntityLencia.class, 3, 6, biome, SRPConfigMobs.lenciaSpawnRate, SRPConfigMobs.lenciaEnabled);
               addSpawn(0, EntityPheon.class, 3, 6, biome, SRPConfigMobs.pheonSpawnRate, SRPConfigMobs.pheonEnabled);
               addSpawn(0, EntityVesta.class, 3, 6, biome, SRPConfigMobs.vestaSpawnRate, SRPConfigMobs.vestaEnabled);
               addSpawn(0, EntityHeblu.class, 1, 1, biome, SRPConfigMobs.hebluSpawnRate, SRPConfigMobs.hebluEnabled);
               addSpawn(0, EntityOronco.class, 1, 1, biome, SRPConfigMobs.oroncoSpawnRate, SRPConfigMobs.oroncoEnabled);
               addSpawn(0, EntityTerla.class, 1, 1, biome, SRPConfigMobs.terlaSpawnRate, SRPConfigMobs.terlaEnabled);
               addSpawn(1, EntityVenkrol.class, 1, 1, biome, SRPConfigMobs.venkrolSpawnrate, SRPConfigSystems.rsEnabled);
            }
         } else {
            String[] here = new String[4];
            PHASEMINUSONE = new ArrayList<>();
            PHASEZERO = new ArrayList<>();
            PHASEONE = new ArrayList<>();
            PHASETWO = new ArrayList<>();
            PHASETHREE = new ArrayList<>();
            PHASEFOUR = new ArrayList<>();
            PHASEFIVE = new ArrayList<>();
            PHASESIX = new ArrayList<>();
            PHASESEVEN = new ArrayList<>();
            PHASEEIGHT = new ArrayList<>();
            PHASENINE = new ArrayList<>();
            PHASETEN = new ArrayList<>();
            listInit(SRPConfigSystems.phaseSpawnEntryMinusne, PHASEMINUSONE);
            listInit(SRPConfigSystems.phaseSpawnEntryZero, PHASEZERO);
            listInit(SRPConfigSystems.phaseSpawnEntryOne, PHASEONE);
            listInit(SRPConfigSystems.phaseSpawnEntryTwo, PHASETWO);
            listInit(SRPConfigSystems.phaseSpawnEntryThree, PHASETHREE);
            listInit(SRPConfigSystems.phaseSpawnEntryFour, PHASEFOUR);
            listInit(SRPConfigSystems.phaseSpawnEntryFive, PHASEFIVE);
            listInit(SRPConfigSystems.phaseSpawnEntrySix, PHASESIX);
            listInit(SRPConfigSystems.phaseSpawnEntrySeven, PHASESEVEN);
            listInit(SRPConfigSystems.phaseSpawnEntryEight, PHASEEIGHT);
            listInit(SRPConfigSystems.phaseSpawnEntryNine, PHASENINE);
            listInit(SRPConfigSystems.phaseSpawnEntryTen, PHASETEN);
            LEVELONE = new ArrayList<>();
            LEVELTWO = new ArrayList<>();
            LEVELTHREE = new ArrayList<>();
            LEVELFOUR = new ArrayList<>();
            listInit(SRPConfigSystems.deveSpawnEntryUDOne, LEVELONE);
            listInit(SRPConfigSystems.deveSpawnEntryUDTwo, LEVELTWO);
            listInit(SRPConfigSystems.deveSpawnEntryUDThree, LEVELTHREE);
            listInit(SRPConfigSystems.deveSpawnEntryUDFour, LEVELFOUR);
         }
      }
   }

   private static void listInit(String[] list, List<SpawnListEntry> in) {
      String[] here = new String[4];

      for (int i = 0; i < list.length; i++) {
         if (list[i] != null) {
            here = list[i].split(";");
            int min = Integer.parseInt(here[1]);
            int max = Integer.parseInt(here[2]);
            int weight = Integer.parseInt(here[3]);
            in.add(new SpawnListEntry(EntityList.getClass(new ResourceLocation(here[0])), weight, min, max));
         }
      }
   }

   public static void removeInit() {
      if (!SRPConfigSystems.useEvolution) {
         for (Biome biome : Biome.field_185377_q) {
            boolean rem = true;
            boolean gg = true;

            while (gg) {
               for (int i = 0; i < biome.func_76747_a(EnumCreatureType.MONSTER).size(); i++) {
                  String eee = ((SpawnListEntry)biome.func_76747_a(EnumCreatureType.MONSTER).get(i)).field_76300_b.toString();
                  if (eee.contains("scapeandrunparasites") && rem) {
                     gg = true;
                     biome.func_76747_a(EnumCreatureType.MONSTER).remove(i);
                  } else {
                     gg = false;
                  }
               }

               for (int ix = 0; ix < biome.func_76747_a(EnumCreatureType.MONSTER).size(); ix++) {
                  String eee = ((SpawnListEntry)biome.func_76747_a(EnumCreatureType.MONSTER).get(ix)).field_76300_b.toString();
                  if (eee.contains("scapeandrunparasites") && rem) {
                     gg = true;
                     biome.func_76747_a(EnumCreatureType.MONSTER).remove(ix);
                  }
               }
            }
         }
      }
   }

   public static List<SpawnListEntry> getSpawns(World world, int id, int phase, SRPSaveData data) {
      if (phase == -2) {
         return null;
      } else if (data != null) {
         if (world.field_73012_v.nextDouble() < SRPConfigSystems.deveMobChance && data.getDeveLevel() > 0) {
            List<SpawnListEntry> spawnList = LEVELONE;
            switch (data.getDeveLevel()) {
               case 1:
                  spawnList = LEVELONE;
                  break;
               case 2:
                  spawnList = LEVELTWO;
                  break;
               case 3:
                  spawnList = LEVELTHREE;
                  break;
               case 4:
                  spawnList = LEVELFOUR;
            }

            if (!spawnList.isEmpty()) {
               return spawnList;
            }
         }

         int[] vaal = data.getDisloValues(id);
         if (vaal[5] > 0) {
            return null;
         } else {
            if (vaal[14] > 0) {
               phase += vaal[14];
            }

            switch (phase) {
               case -1:
                  if (data.getEIVArea(id) > 0) {
                     return PHASEMINUSONE;
                  }

                  return null;
               case 0:
                  return PHASEZERO;
               case 1:
                  return PHASEONE;
               case 2:
                  return PHASETWO;
               case 3:
                  return PHASETHREE;
               case 4:
                  return PHASEFOUR;
               case 5:
                  return PHASEFIVE;
               case 6:
                  return PHASESIX;
               case 7:
                  return PHASESEVEN;
               case 8:
                  return PHASEEIGHT;
               case 9:
                  return PHASENINE;
               default:
                  return PHASETEN;
            }
         }
      } else {
         return null;
      }
   }

   public static void initBiome() {
      setBiomeSpawn(SRPConfigWorld.biomeOneSpawnEntry, SRPBiomes.biomeShrouded);
      setBiomeSpawn(SRPConfigWorld.biomeThreeSpawnEntry, SRPBiomes.biomeHarlequin);
   }

   private static void setBiomeSpawn(String[] list, Biome biome) {
      String[] here = new String[5];

      for (int i = 0; i < list.length; i++) {
         if (list[i] != null) {
            here = list[i].split(";");
            int min = Integer.parseInt(here[1]);
            int max = Integer.parseInt(here[2]);
            int weight = Integer.parseInt(here[3]);
            int type = Integer.parseInt(here[4]);
            Class mob = EntityList.getClass(new ResourceLocation(here[0]));
            addSpawn(type, mob, min, max, biome, weight, true);
         }
      }
   }

   public static void addSpawn(int type, Class<? extends EntityLiving> entity, int groupMin, int groupMax, Biome biome, int weight, boolean addSpawn) {
      if (addSpawn && weight > 0) {
         switch (type) {
            case 0:
               biome.func_76747_a(EnumCreatureType.MONSTER).add(new SpawnListEntry(entity, weight, groupMin, groupMax));
               return;
            case 1:
               biome.func_76747_a(EnumCreatureType.CREATURE).add(new SpawnListEntry(entity, weight, groupMin, groupMax));
               return;
            case 2:
               biome.func_76747_a(EnumCreatureType.WATER_CREATURE).add(new SpawnListEntry(entity, weight, groupMin, groupMax));
               return;
         }
      }
   }

   @EventBusSubscriber(modid = "srparasites")
   public static class DimensionHandler {
      @SubscribeEvent
      public static void onSpawn(CheckSpawn event) {
         if (!(event.getEntity() instanceof EntityParasiteBase)) {
            if (event.getEntity() instanceof EntityLivingBase) {
               if (SRPConfigSystems.useEvolution
                  && SRPSaveData.get(event.getWorld(), 52).getEvolutionPhase(event.getWorld().field_73011_w.getDimension())
                     >= SRPConfigSystems.evolutionNoParasiteSpawnDenied) {
                  event.setResult(Result.DENY);
               }

               if (SRPConfigWorld.coloniesActivated
                  && SRPWorldData.get(event.getWorld()).nearestColonyPosition(event.getEntity().func_180425_c(), false) != null) {
                  event.setResult(Result.DENY);
               }
            }
         } else {
            List<Entity> serverList = event.getEntity().field_70170_p.field_72996_f;
            int count = 0;
            int gnatCount = 0;
            int worker = 0;
            int waterParasites = 0;
            int airParasites = 0;

            for (Entity entity : serverList) {
               if (entity instanceof EntityParasiteBase) {
                  if (entity instanceof EntityCanHaveBodies) {
                     EntityCanHaveBodies bodies = (EntityCanHaveBodies)entity;
                     if (bodies.getBodyNumber() == 0) {
                        count++;
                     }
                  } else {
                     count++;
                     if (entity instanceof EntityAta) {
                        gnatCount++;
                     }

                     if (entity instanceof EntityCanSwim) {
                        waterParasites++;
                     }

                     if (entity instanceof EntityCanFly) {
                        airParasites++;
                     }

                     if (entity instanceof EntityKol) {
                        worker++;
                     }
                  }
               }
            }

            int players = event.getEntity().field_70170_p.field_73010_i.size() * SRPConfig.worldMobCapPlusPlayer;
            if (event.getEntity() instanceof EntityAta && gnatCount > SRPConfig.worldGnatCap) {
               event.setResult(Result.DENY);
               return;
            }

            if (event.getEntity() instanceof EntityKol && worker > 10) {
               event.setResult(Result.DENY);
               return;
            }

            if (count > SRPConfig.worldSpawningMobCap + players) {
               SRPSpawning.totalParasites = false;
               event.setResult(Result.DENY);
               if (count > 200) {
                  SRPSpawning.SRP_LOG.debug("200!! SOO MANY PARASITES");

                  for (Entity entityx : serverList) {
                     if (entityx instanceof EntityParasiteBase) {
                        ((EntityParasiteBase)entityx).func_70106_y();
                        SRPSpawning.SRP_LOG.debug("----- SOO MANY PARASITES");
                        if (--count < SRPConfig.worldSpawningMobCap) {
                           return;
                        }
                     }
                  }
               }

               return;
            }

            if (waterParasites > SRPConfig.worldWaterCap && event.getEntity() instanceof EntityCanSwim) {
               event.setResult(Result.DENY);
               return;
            }

            if (airParasites > SRPConfig.worldAirCap && event.getEntity() instanceof EntityCanFly) {
               event.setResult(Result.DENY);
               return;
            }

            if (!SRPConfigSystems.useEvolution) {
               boolean inv = false;
               boolean flagI = false;
               if (SRPConfig.blackListedDimensionsWhite) {
                  inv = true;
               }

               for (int i : SRPConfig.blackListedDimensions) {
                  if (SRPConfig.blackListedDimensionsWhite) {
                     if (i == event.getWorld().field_73011_w.getDimension()) {
                        flagI = true;
                        break;
                     }
                  } else if (i == event.getWorld().field_73011_w.getDimension()) {
                     event.setResult(Result.DENY);
                     return;
                  }
               }

               if (inv && !flagI) {
                  event.setResult(Result.DENY);
                  return;
               }
            }

            if (SRPConfigSystems.useEvolution || SRPConfigWorld.coloniesActivated) {
               EntityParasiteBase parasite = (EntityParasiteBase)event.getEntity();
               if (!parasite.canSpawnSpawn) {
                  event.setResult(Result.DENY);
                  return;
               }

               SRPWorldData data = SRPWorldData.get(parasite.field_70170_p);
               SRPSaveData sopa = SRPSaveData.get(parasite.field_70170_p, 51);
               parasite.setCreatedPhase(sopa.getEvolutionPhase(parasite.field_70170_p.field_73011_w.getDimension()), sopa.getDeveLevel());
               if (SRPConfigSystems.useEvolution && !SRPConfigSystems.phaseCustomSpawner) {
                  if (sopa.getEvolutionPhase(parasite.field_70170_p.field_73011_w.getDimension()) <= -1) {
                     event.setResult(Result.DENY);
                     return;
                  }

                  if (!canSpawninPhase(sopa.getEvolutionPhase(parasite.field_70170_p.field_73011_w.getDimension()), sopa.getDeveLevel(), parasite)) {
                     event.setResult(Result.DENY);
                     return;
                  }
               }

               if (checkEvoLock(parasite.getParasiteIDRegister(), sopa) || checkColoLock(parasite.getParasiteIDRegister(), data, parasite)) {
                  event.setResult(Result.DENY);
                  return;
               }

               if (parasite instanceof EntityCanSpawn) {
                  EntityCanSpawn parasiteSus = (EntityCanSpawn)parasite;
                  if (sopa.getNumberIDDataSpawn(parasiteSus.getIDSpawn()) < parasiteSus.canSpawnByIDData()) {
                     event.setResult(Result.DENY);
                     return;
                  }
               }
            }
         }
      }

      private static boolean canSpawninPhase(int evPhase, int levelUD, EntityParasiteBase parasite) {
         byte type = parasite.getParasiteType();
         switch (evPhase) {
            case -1:
               return false;
            case 0:
               if (type >= SRPConfigSystems.phaseMaxParasiteIDZero || type <= SRPConfigSystems.phaseCancelParasiteIDZero) {
                  return false;
               }
               break;
            case 1:
               if (type >= SRPConfigSystems.phaseMaxParasiteIDOne || type <= SRPConfigSystems.phaseCancelParasiteIDOne) {
                  return false;
               }
               break;
            case 2:
               if (type >= SRPConfigSystems.phaseMaxParasiteIDTwo || type <= SRPConfigSystems.phaseCancelParasiteIDTwo) {
                  return false;
               }
               break;
            case 3:
               if (type >= SRPConfigSystems.phaseMaxParasiteIDThree || type <= SRPConfigSystems.phaseCancelParasiteIDThree) {
                  return false;
               }
               break;
            case 4:
               if (type >= SRPConfigSystems.phaseMaxParasiteIDFour || type <= SRPConfigSystems.phaseCancelParasiteIDFour) {
                  return false;
               }
               break;
            case 5:
               if (type >= SRPConfigSystems.phaseMaxParasiteIDFive || type <= SRPConfigSystems.phaseCancelParasiteIDFive) {
                  return false;
               }
               break;
            case 6:
               if (type >= SRPConfigSystems.phaseMaxParasiteIDSix || type <= SRPConfigSystems.phaseCancelParasiteIDSix) {
                  return false;
               }
               break;
            case 7:
               if (type >= SRPConfigSystems.phaseMaxParasiteIDSeven || type <= SRPConfigSystems.phaseCancelParasiteIDSeven) {
                  return false;
               }
               break;
            case 8:
               if (type >= SRPConfigSystems.phaseMaxParasiteIDEight || type <= SRPConfigSystems.phaseCancelParasiteIDEight) {
                  return false;
               }
               break;
            case 9:
               if (type >= SRPConfigSystems.phaseMaxParasiteIDNine || type <= SRPConfigSystems.phaseCancelParasiteIDNine) {
                  return false;
               }
               break;
            case 10:
               if (type >= SRPConfigSystems.phaseMaxParasiteIDTen || type <= SRPConfigSystems.phaseCancelParasiteIDTen) {
                  return false;
               }
         }

         return true;
      }

      private static boolean checkEvoLock(int in, SRPSaveData data) {
         return data.checkParasiteID(in);
      }

      private static boolean checkColoLock(int in, SRPWorldData data, EntityParasiteBase parasite) {
         if (parasite instanceof EntityCanColony) {
            if (!SRPConfigWorld.coloniesActivated) {
               return true;
            }

            int points = data.totalColonyPoints(0);
            if (points <= 0) {
               return true;
            }

            BlockPos origin = data.nearestColonyPosition(parasite.func_180425_c(), false);
            if (origin == null) {
               if (((EntityCanColony)parasite).onlySpawnInside()) {
                  return true;
               }
            } else if (!((EntityCanColony)parasite).onlySpawnInside()) {
               return true;
            }
         }

         int pointsx = data.totalColonyPoints(0);
         String[] here = new String[2];
         int id = 0;
         int req = 0;

         for (int i = 0; i < SRPConfigWorld.preeValues.length; i++) {
            here = SRPConfigWorld.preeValues[i].split(";");
            id = Integer.parseInt(here[0]);
            if (id == in) {
               if (parasite.field_70170_p.func_180494_b(parasite.func_180425_c()) instanceof BiomeParasiteBase) {
                  return SRPConfigWorld.preeValuesBiome;
               }

               req = Integer.parseInt(here[1]);
               return pointsx < req;
            }
         }

         return false;
      }
   }
}
