package com.dhanantry.scapeandrunparasites.world.celestial;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.minecraft.world.World;

public final class CelestialEffectRegistry {
   private static final Map<String, ICelestialEventEffect> EFFECTS = new HashMap<>();

   private CelestialEffectRegistry() {
   }

   public static void register(String id, ICelestialEventEffect effect) {
      if (id != null && effect != null) {
         EFFECTS.put(id, effect);
      }
   }

   public static ICelestialEventEffect get(String id) {
      return EFFECTS.get(id);
   }

   public static Set<String> getActiveIds(World world) {
      if (world != null && !world.field_72995_K) {
         int dim = world.field_73011_w.getDimension();
         CelestialNightData night = CelestialNightData.get(world);
         CelestialNightData.DimState s = night.getState(dim);
         if (s == null) {
            return Collections.emptySet();
         } else {
            boolean blackSky = s.active.contains("dark_days") || s.forced.contains("dark_days");
            Set<String> out = new HashSet<>();
            if (blackSky) {
               out.add("dark_days");
               return out;
            } else {
               out.addAll(s.active);
               out.addAll(s.forced);
               return out;
            }
         }
      } else {
         return Collections.emptySet();
      }
   }
}
