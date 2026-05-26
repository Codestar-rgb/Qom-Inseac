/*
 * Decompiled with CFR 0.152.
 */
package com.subspaceparasite.client.celestial;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class CelestialPhaseClient {
    private static final Map<Integer, Integer> PHASES = new HashMap<Integer, Integer>();
    private static final Map<Integer, Long> NIGHT_INDEX = new HashMap<Integer, Long>();
    private static final Map<Integer, Set<String>> ACTIVE_IDS = new HashMap<Integer, Set<String>>();
    private static final Map<Integer, Set<String>> FORCED_IDS = new HashMap<Integer, Set<String>>();
    private static final boolean DEBUG = false;

    private CelestialPhaseClient() {
    }

    private static void debug(String msg) {
    }

    public static void setPhase(int dim, int phase) {
        CelestialPhaseClient.debug("setPhase dim=" + dim + " -> " + phase);
        PHASES.put(dim, phase);
    }

    public static int getPhase(int dim) {
        Integer p = PHASES.get(dim);
        int result = p != null ? p : 0;
        CelestialPhaseClient.debug("getPhase dim=" + dim + " -> " + result);
        return result;
    }

    public static void setServerNightState(int dim, int phase, long nightIndex, Set<String> active, Set<String> forced) {
        PHASES.put(dim, phase);
        NIGHT_INDEX.put(dim, nightIndex);
        if (active == null) {
            active = Collections.emptySet();
        }
        if (forced == null) {
            forced = Collections.emptySet();
        }
        ACTIVE_IDS.put(dim, new HashSet<String>(active));
        FORCED_IDS.put(dim, new HashSet<String>(forced));
        CelestialPhaseClient.debug("setServerNightState dim=" + dim + " phase=" + phase + " night=" + nightIndex + " active=" + active.size() + " forced=" + forced.size());
    }

    public static boolean isActiveTonight(int dim, String id) {
        Set<String> s = ACTIVE_IDS.get(dim);
        return s != null && s.contains(id);
    }

    public static boolean isForcedTonight(int dim, String id) {
        Set<String> s = FORCED_IDS.get(dim);
        return s != null && s.contains(id);
    }

    public static long getNightIndex(int dim) {
        Long n = NIGHT_INDEX.get(dim);
        return n != null ? n : -1L;
    }

    public static Set<String> getActiveIds(int dim) {
        Set<String> s = ACTIVE_IDS.get(dim);
        return s == null ? Collections.emptySet() : new HashSet<String>(s);
    }

    public static Set<String> getForcedIds(int dim) {
        Set<String> s = FORCED_IDS.get(dim);
        return s == null ? Collections.emptySet() : new HashSet<String>(s);
    }

    public static void clearDim(int dim) {
        PHASES.remove(dim);
        NIGHT_INDEX.remove(dim);
        ACTIVE_IDS.remove(dim);
        FORCED_IDS.remove(dim);
    }
}

