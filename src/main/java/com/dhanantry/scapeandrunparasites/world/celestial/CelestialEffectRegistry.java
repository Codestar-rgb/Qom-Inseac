/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.world.celestial;

import com.dhanantry.scapeandrunparasites.world.CelestialNightData;
import com.dhanantry.scapeandrunparasites.world.celestial.ICelestialEventEffect;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.minecraft.world.World;

public final class CelestialEffectRegistry {
    private static final Map<String, ICelestialEventEffect> EFFECTS = new HashMap<String, ICelestialEventEffect>();

    private CelestialEffectRegistry() {
    }

    public static void register(String id, ICelestialEventEffect effect) {
        if (id == null || effect == null) {
            return;
        }
        EFFECTS.put(id, effect);
    }

    public static ICelestialEventEffect get(String id) {
        return EFFECTS.get(id);
    }

    public static Set<String> getActiveIds(World world) {
        if (world == null || world.field_72995_K) {
            return Collections.emptySet();
        }
        int dim = world.field_73011_w.getDimension();
        CelestialNightData night = CelestialNightData.get(world);
        CelestialNightData.DimState s = night.getState(dim);
        if (s == null) {
            return Collections.emptySet();
        }
        HashSet<String> out = new HashSet<String>();
        out.addAll(s.active);
        out.addAll(s.forced);
        return out;
    }
}

