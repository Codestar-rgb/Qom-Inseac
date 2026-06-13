/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.client.celestial;

import com.dhanantry.scapeandrunparasites.client.celestial.CelestialObjectDefinition;
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
    private static final List<CelestialObjectDefinition> OBJECTS = new ArrayList<CelestialObjectDefinition>();
    private static final Set<String> FORCED_IDS = new HashSet<String>();

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
        int total = CelestialObjectRegistry.getObjectCount();
        return (total + 1) / 2;
    }

    public static boolean forceEvent(World world, String id) {
        if (id == null) {
            return false;
        }
        if ("none".equalsIgnoreCase(id) || "clear".equalsIgnoreCase(id) || "off".equalsIgnoreCase(id)) {
            CelestialObjectRegistry.clearForced();
            return true;
        }
        boolean found = false;
        for (CelestialObjectDefinition def : OBJECTS) {
            if (!def.id.equals(id)) continue;
            found = true;
            break;
        }
        if (!found) {
            return false;
        }
        if (FORCED_IDS.contains(id)) {
            FORCED_IDS.remove(id);
        } else {
            FORCED_IDS.add(id);
        }
        return true;
    }

    public static List<String> getAllIds() {
        ArrayList<String> ids = new ArrayList<String>();
        for (CelestialObjectDefinition def : CelestialObjectRegistry.getObjects()) {
            ids.add(def.id);
        }
        return ids;
    }

    public static void init() {
        OBJECTS.clear();
        OBJECTS.add(new CelestialObjectDefinition("mercury", new ResourceLocation("srparasites", "textures/celestial/solar/mercury.png"), 0, 10, 0.4f, false, true, 1.85f, 2.0f, 2.0f, false, 1.2f, false, 1, 20, RAND.nextFloat() * 360.0f, 8.0f + RAND.nextFloat() * 50.0f));
        OBJECTS.add(new CelestialObjectDefinition("mars", new ResourceLocation("srparasites", "textures/celestial/solar/mars.png"), 0, 10, 0.35f, false, true, 1.0f, 2.0f, 2.0f, false, 1.0f, false, 1, 20, RAND.nextFloat() * 360.0f, 8.0f + RAND.nextFloat() * 50.0f));
        OBJECTS.add(new CelestialObjectDefinition("jupiter", new ResourceLocation("srparasites", "textures/celestial/solar/jupiter.png"), 0, 10, 0.25f, false, true, 2.0f, 2.0f, 2.0f, false, 0.35f, false, 1, 20, RAND.nextFloat() * 360.0f, 8.0f + RAND.nextFloat() * 50.0f));
        OBJECTS.add(new CelestialObjectDefinition("saturn", new ResourceLocation("srparasites", "textures/celestial/solar/saturn.png"), 0, 10, 0.22f, false, true, 1.55f, 2.0f, 2.0f, false, 0.32f, false, 1, 20, RAND.nextFloat() * 360.0f, 8.0f + RAND.nextFloat() * 50.0f));
        OBJECTS.add(new CelestialObjectDefinition("uranus", new ResourceLocation("srparasites", "textures/celestial/solar/uranus.png"), 0, 10, 1.0f, false, true, 1.15f, 2.0f, 2.0f, false, 0.15f, false, 1, 20, RAND.nextFloat() * 360.0f, 8.0f + RAND.nextFloat() * 50.0f));
        OBJECTS.add(new CelestialObjectDefinition("neptune", new ResourceLocation("srparasites", "textures/celestial/solar/neptune.png"), 0, 10, 0.15f, false, true, 1.1f, 2.0f, 2.0f, false, 0.22f, false, 1, 20, RAND.nextFloat() * 360.0f, 8.0f + RAND.nextFloat() * 50.0f));
        OBJECTS.add(new CelestialObjectDefinition("pluto", new ResourceLocation("srparasites", "textures/celestial/solar/pluto.png"), 0, 10, 0.08f, false, true, 1.0f, 1.0f, 0.1f, false, 0.0f, false, 1, 20, RAND.nextFloat() * 360.0f, 8.0f + RAND.nextFloat() * 50.0f));
        OBJECTS.add(new CelestialObjectDefinition("venus", new ResourceLocation("srparasites", "textures/celestial/solar/venus.png"), 0, 10, 0.75f, false, true, 2.1f, 2.0f, 2.0f, false, 0.18f, false, 1, 20, RAND.nextFloat() * 360.0f, 8.0f + RAND.nextFloat() * 50.0f));
        OBJECTS.add(new CelestialObjectDefinition("blip", new ResourceLocation("srparasites", "textures/celestial/bld_planet.png"), 0, 10, 1.0f, true, false, 4.0f, 1.0f, 0.1f, false, 0.0f, false, 1, 20, 0.0f, 60.0f, CelestialObjectDefinition.OrbitPath.RING, 360.0f, 60.0f, 60.0f, 9000.0f, false));
        OBJECTS.add(new CelestialObjectDefinition("pulse", new ResourceLocation("srparasites", "textures/celestial/star1.png"), 3, 10, 1.0f, false, true, 10.0f, 1.0f, 0.1f, false, 0.0f, false, 1, 0, 40.0f, 45.0f));
        OBJECTS.add(new CelestialObjectDefinition("eight", new ResourceLocation("srparasites", "textures/celestial/eight.png"), 4, 10, 0.005f, false, true, 20.0f, 1.0f, 0.1f, false, 0.0f, false, 1, 0, 180.0f, 30.0f));
        OBJECTS.add(new CelestialObjectDefinition("twenty_seven", new ResourceLocation("srparasites", "textures/celestial/twenty_seven.png"), 2, 10, 0.01f, false, true, 90.0f, 1.0f, 0.1f, false, 0.0f, false, 1, 20, 250.0f, 50.0f));
        OBJECTS.add(new CelestialObjectDefinition("three", new ResourceLocation("srparasites", "textures/celestial/three.png"), 5, 10, 0.15f, false, false, 6.0f, 1.0f, 0.1f, false, 4.0f, false, 1, 15, 60.0f, 40.0f, CelestialObjectDefinition.OrbitPath.RING, 140.0f, 30.0f, 45.0f, 12000.0f, false));
        OBJECTS.add(new CelestialObjectDefinition("eighty_three", new ResourceLocation("srparasites", "textures/celestial/eighty_three.png"), 7, 10, 0.05f, false, true, 5.0f, 1.0f, 0.1f, false, 0.0f, true, 4, 20, 310.0f, 70.0f));
        OBJECTS.add(new CelestialObjectDefinition("four_comet", new ResourceLocation("srparasites", "textures/celestial/four_comet.png"), 3, 10, 0.35f, false, false, 6.0f, 1.0f, 0.1f, true, 0.0f, false, 1, 3, 0.0f, 15.0f, CelestialObjectDefinition.OrbitPath.ARC, 180.0f, 5.0f, 25.0f, 900.0f, true));
        OBJECTS.add(new CelestialObjectDefinition("arrow", new ResourceLocation("srparasites", "textures/celestial/tetrahedron.png"), 0, 10, 0.005f, false, true, 4.0f, 2.0f, 2.0f, false, 0.35f, false, 1, 20, 90.0f, 22.0f + RAND.nextFloat() * 50.0f));
    }

    public static List<CelestialObjectDefinition> getObjects() {
        return OBJECTS;
    }

    public static CelestialObjectDefinition getById(String id) {
        for (CelestialObjectDefinition d : OBJECTS) {
            if (!d.id.equals(id)) continue;
            return d;
        }
        return null;
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
        if (FORCED_IDS.contains(def.id)) {
            return true;
        }
        long nightIndex = world.func_82737_E() / 24000L;
        long seed = nightIndex * 918273L + (long)def.id.hashCode();
        RAND.setSeed(seed);
        return RAND.nextFloat() <= def.chancePerNight;
    }
}

