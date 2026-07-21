package com.dhanantry.scapeandrunparasites.client.celestial;

import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class CelestialObjectRegistry {
   private static final Random RAND = new Random();
   private static CelestialObjectDefinition FORCED = null;
   private static final List<CelestialObjectDefinition> OBJECTS = new ArrayList<>();
   private static final Set<String> FORCED_IDS = new HashSet<>();

   public static CelestialObjectDefinition getForced() {
      return FORCED;
   }

   public static int getObjectCount() {
      return OBJECTS.size();
   }

   public static boolean isInitialized() {
      return !OBJECTS.isEmpty();
   }

   public static int getHalfDiscoveryThreshold() {
      int total = getObjectCount();
      return (total + 1) / 2;
   }

   public static boolean forceEvent(World world, String id) {
      if (id == null) {
         return false;
      } else if (isDisabledByConfig(id)) {
         return false;
      } else if (!"none".equalsIgnoreCase(id) && !"clear".equalsIgnoreCase(id) && !"off".equalsIgnoreCase(id)) {
         boolean found = false;

         for (CelestialObjectDefinition def : OBJECTS) {
            if (def.id.equals(id)) {
               found = true;
               break;
            }
         }

         if (!found) {
            return false;
         } else {
            if (FORCED_IDS.contains(id)) {
               FORCED_IDS.remove(id);
            } else {
               FORCED_IDS.add(id);
            }

            return true;
         }
      } else {
         clearForced();
         return true;
      }
   }

   public static List<String> getAllIds() {
      List<String> ids = new ArrayList<>();

      for (CelestialObjectDefinition def : getObjects()) {
         ids.add(def.id);
      }

      return ids;
   }

   public static void init() {
      OBJECTS.clear();
      addIfEnabled(
         new CelestialObjectDefinition(
            "mercury",
            new ResourceLocation("srparasites", "textures/celestial/solar/mercury.png"),
            0,
            10,
            0.4F,
            false,
            true,
            1.85F,
            2.0F,
            2.0F,
            false,
            1.2F,
            false,
            1,
            20,
            RAND.nextFloat() * 360.0F,
            8.0F + RAND.nextFloat() * 50.0F
         )
      );
      addIfEnabled(
         new CelestialObjectDefinition(
            "mars",
            new ResourceLocation("srparasites", "textures/celestial/solar/mars.png"),
            0,
            10,
            0.35F,
            false,
            true,
            1.0F,
            2.0F,
            2.0F,
            false,
            1.0F,
            false,
            1,
            20,
            RAND.nextFloat() * 360.0F,
            8.0F + RAND.nextFloat() * 50.0F
         )
      );
      addIfEnabled(
         new CelestialObjectDefinition(
            "jupiter",
            new ResourceLocation("srparasites", "textures/celestial/solar/jupiter.png"),
            0,
            10,
            0.25F,
            false,
            true,
            2.0F,
            2.0F,
            2.0F,
            false,
            0.35F,
            false,
            1,
            20,
            RAND.nextFloat() * 360.0F,
            8.0F + RAND.nextFloat() * 50.0F
         )
      );
      addIfEnabled(
         new CelestialObjectDefinition(
            "saturn",
            new ResourceLocation("srparasites", "textures/celestial/solar/saturn.png"),
            0,
            10,
            0.22F,
            false,
            true,
            1.55F,
            2.0F,
            2.0F,
            false,
            0.32F,
            false,
            1,
            20,
            RAND.nextFloat() * 360.0F,
            8.0F + RAND.nextFloat() * 50.0F
         )
      );
      addIfEnabled(
         new CelestialObjectDefinition(
            "uranus",
            new ResourceLocation("srparasites", "textures/celestial/solar/uranus.png"),
            0,
            10,
            1.0F,
            false,
            true,
            1.15F,
            2.0F,
            2.0F,
            false,
            0.15F,
            false,
            1,
            20,
            RAND.nextFloat() * 360.0F,
            8.0F + RAND.nextFloat() * 50.0F
         )
      );
      addIfEnabled(
         new CelestialObjectDefinition(
            "neptune",
            new ResourceLocation("srparasites", "textures/celestial/solar/neptune.png"),
            0,
            10,
            0.15F,
            false,
            true,
            1.1F,
            2.0F,
            2.0F,
            false,
            0.22F,
            false,
            1,
            20,
            RAND.nextFloat() * 360.0F,
            8.0F + RAND.nextFloat() * 50.0F
         )
      );
      addIfEnabled(
         new CelestialObjectDefinition(
            "pluto",
            new ResourceLocation("srparasites", "textures/celestial/solar/pluto.png"),
            0,
            10,
            0.08F,
            false,
            true,
            1.0F,
            1.0F,
            0.1F,
            false,
            0.0F,
            false,
            1,
            20,
            RAND.nextFloat() * 360.0F,
            8.0F + RAND.nextFloat() * 50.0F
         )
      );
      addIfEnabled(
         new CelestialObjectDefinition(
            "venus",
            new ResourceLocation("srparasites", "textures/celestial/solar/venus.png"),
            0,
            10,
            0.75F,
            false,
            true,
            2.1F,
            2.0F,
            2.0F,
            false,
            0.18F,
            false,
            1,
            20,
            RAND.nextFloat() * 360.0F,
            8.0F + RAND.nextFloat() * 50.0F
         )
      );
      addIfEnabled(
         new CelestialObjectDefinition(
            "blip",
            new ResourceLocation("srparasites", "textures/celestial/bld_planet.png"),
            0,
            10,
            1.0F,
            true,
            false,
            4.0F,
            1.0F,
            0.1F,
            false,
            0.0F,
            false,
            1,
            20,
            0.0F,
            60.0F,
            CelestialObjectDefinition.OrbitPath.RING,
            360.0F,
            60.0F,
            60.0F,
            9000.0F,
            false
         )
      );
      addIfEnabled(
         new CelestialObjectDefinition(
            "pulse",
            new ResourceLocation("srparasites", "textures/celestial/star1.png"),
            3,
            10,
            1.0F,
            false,
            true,
            10.0F,
            1.0F,
            0.1F,
            false,
            0.0F,
            false,
            1,
            0,
            40.0F,
            45.0F
         )
      );
      addIfEnabled(
         new CelestialObjectDefinition(
            "eight",
            new ResourceLocation("srparasites", "textures/celestial/eight.png"),
            4,
            10,
            0.005F,
            false,
            true,
            20.0F,
            1.0F,
            0.1F,
            false,
            0.0F,
            false,
            1,
            0,
            180.0F,
            30.0F
         )
      );
      addIfEnabled(
         new CelestialObjectDefinition(
            "twenty_seven",
            new ResourceLocation("srparasites", "textures/celestial/twenty_seven.png"),
            2,
            10,
            0.01F,
            false,
            true,
            90.0F,
            1.0F,
            1.0F,
            false,
            0.0F,
            false,
            1,
            20,
            250.0F,
            50.0F
         )
      );
      addIfEnabled(
         new CelestialObjectDefinition(
            "three",
            new ResourceLocation("srparasites", "textures/celestial/three.png"),
            5,
            10,
            0.15F,
            false,
            false,
            6.0F,
            1.0F,
            0.1F,
            false,
            4.0F,
            false,
            1,
            15,
            60.0F,
            40.0F,
            CelestialObjectDefinition.OrbitPath.RING,
            140.0F,
            30.0F,
            45.0F,
            12000.0F,
            false
         )
      );
      addIfEnabled(
         new CelestialObjectDefinition(
            "eighty_three",
            new ResourceLocation("srparasites", "textures/celestial/eighty_three.png"),
            7,
            10,
            0.05F,
            false,
            true,
            5.0F,
            1.0F,
            0.1F,
            false,
            0.0F,
            true,
            4,
            20,
            310.0F,
            70.0F
         )
      );
      addIfEnabled(
         new CelestialObjectDefinition(
            "four_comet",
            new ResourceLocation("srparasites", "textures/celestial/four_comet.png"),
            3,
            10,
            0.35F,
            false,
            false,
            6.0F,
            1.0F,
            0.1F,
            true,
            0.0F,
            false,
            1,
            3,
            0.0F,
            15.0F,
            CelestialObjectDefinition.OrbitPath.ARC,
            180.0F,
            5.0F,
            25.0F,
            900.0F,
            true
         )
      );
      addIfEnabled(
         new CelestialObjectDefinition(
            "arrow",
            new ResourceLocation("srparasites", "textures/celestial/tetrahedron.png"),
            0,
            10,
            0.005F,
            false,
            true,
            4.0F,
            2.0F,
            2.0F,
            false,
            0.35F,
            false,
            1,
            20,
            90.0F,
            22.0F + RAND.nextFloat() * 50.0F
         )
      );
      addIfEnabled(
         new CelestialObjectDefinition(
            "dark_days",
            new ResourceLocation("srparasites", "textures/celestial/black_sky.png"),
            0,
            10,
            5.0E-4F,
            false,
            true,
            1.0F,
            1.0F,
            0.0F,
            false,
            0.0F,
            false,
            1,
            20,
            0.0F,
            90.0F
         )
      );
   }

   public static List<CelestialObjectDefinition> getObjects() {
      return OBJECTS;
   }

   public static CelestialObjectDefinition getById(String id) {
      for (CelestialObjectDefinition d : OBJECTS) {
         if (d.id.equals(id)) {
            return d;
         }
      }

      return null;
   }

   private static void addIfEnabled(CelestialObjectDefinition def) {
      if (def != null) {
         if (!isDisabledByConfig(def.id)) {
            OBJECTS.add(def);
         }
      }
   }

   public static boolean isDisabledByConfig(String id) {
      if (id == null) {
         return true;
      } else {
         return "dark_days".equals(id) && !SRPConfigWorld.darkDaysEnabled ? true : SRPConfigWorld.isCelestialEventBlacklisted(id);
      }
   }

   public static boolean isForced(String id) {
      return id != null && FORCED_IDS.contains(id);
   }

   public static Set<String> getForcedIds() {
      return Collections.unmodifiableSet(FORCED_IDS);
   }

   public static void clearForced() {
      FORCED_IDS.clear();
   }

   public static boolean rollsThisNight(World world, CelestialObjectDefinition def) {
      if (world == null || def == null) {
         return false;
      } else if (isDisabledByConfig(def.id)) {
         return false;
      } else if ("dark_days".equals(def.id)) {
         return SRPConfigWorld.darkDaysEnabled && FORCED_IDS.contains(def.id);
      } else if (FORCED_IDS.contains(def.id)) {
         return true;
      } else {
         long nightIndex = world.func_82737_E() / 24000L;
         long seed = nightIndex * 918273L + def.id.hashCode();
         RAND.setSeed(seed);
         return RAND.nextFloat() <= def.chancePerNight;
      }
   }
}
