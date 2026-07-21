package com.dhanantry.scapeandrunparasites.network;

import com.dhanantry.scapeandrunparasites.client.celestial.CelestialObjectDefinition;
import com.dhanantry.scapeandrunparasites.client.celestial.CelestialObjectRegistry;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import com.dhanantry.scapeandrunparasites.world.CelestialNightData;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import com.dhanantry.scapeandrunparasites.world.celestial.CelestialEffectRegistry;
import com.dhanantry.scapeandrunparasites.world.celestial.ICelestialEventEffect;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

public class CelestialPhaseSyncHandler {
   private static final boolean DEBUG = false;
   private static final Random RAND = new Random();
   private static final Map<Integer, Boolean> WAS_NIGHT = new HashMap<>();

   private static void debug(String msg) {
   }

   private static long getNightIndex(World world) {
      return world.func_82737_E() / 24000L;
   }

   private static boolean isNight(World world) {
      long dayTime = world.func_72820_D() % 24000L;
      return dayTime >= 13000L && dayTime <= 23000L;
   }

   private static void rollNight(World world, int dim, int phase, CelestialNightData.DimState state) {
      state.active.clear();
      long nightIndex = state.nightIndex;
      long baseSeed = world.func_72905_C() ^ nightIndex * 918273L;

      for (CelestialObjectDefinition def : CelestialObjectRegistry.getObjects()) {
         if (!"dark_days".equals(def.id) && def.isPhaseAllowed(phase)) {
            long seed = baseSeed + def.id.hashCode() * 31L;
            RAND.setSeed(seed);
            if (RAND.nextFloat() <= def.chancePerNight) {
               state.active.add(def.id);
            }
         }
      }
   }

   private static void callNightStart(World world, int dim, int phase, long nightIndex, Set<String> started) {
      for (String id : started) {
         ICelestialEventEffect fx = CelestialEffectRegistry.get(id);
         if (fx != null) {
            fx.onNightStart(world, dim, phase, nightIndex);
         }
      }
   }

   private static void callNightEnd(World world, int dim, int phase, long nightIndex, Set<String> ended) {
      for (String id : ended) {
         ICelestialEventEffect fx = CelestialEffectRegistry.get(id);
         if (fx != null) {
            fx.onNightEnd(world, dim, phase, nightIndex);
         }
      }
   }

   public static void syncDim(World world, int dim, int phase, long nightIndex, Set<String> activeSet, Set<String> forcedSet) {
      if (world.func_73046_m() == null) {
         debug("world.getMinecraftServer() is null, cannot send (dim=" + dim + ")");
      } else {
         for (EntityPlayerMP player : world.func_73046_m().func_184103_al().func_181057_v()) {
            if (player.field_71093_bK == dim) {
               debug("Sending phase=" + phase + " dim=" + dim + " night=" + nightIndex + " to " + player.func_70005_c_());
               SRPNetwork.CHANNEL.sendTo(new MsgSyncCelestialPhase(dim, phase, nightIndex, activeSet, forcedSet), player);
            }
         }
      }
   }

   @SubscribeEvent
   public void onWorldTick(WorldTickEvent event) {
      World world = event.world;
      if (!world.field_72995_K && event.phase == Phase.END) {
         if (SRPConfigWorld.enableCelestialObjects) {
            if (world.field_73011_w.func_76569_d()) {
               int dim = world.field_73011_w.getDimension();
               SRPSaveData data = SRPSaveData.get(world, dim);
               if (data == null) {
                  debug("SRPSaveData is null for dim " + dim);
               } else {
                  int phase = data.getEvolutionPhase(dim);
                  debug("SERVER phase=" + phase + " dim=" + dim);
                  CelestialNightData nightData = CelestialNightData.get(world);
                  CelestialNightData.DimState state = nightData.getOrCreate(dim);
                  boolean nightNow = isNight(world);
                  boolean nightPrev = WAS_NIGHT.get(dim) != null ? WAS_NIGHT.get(dim) : false;
                  if (nightNow && !nightPrev) {
                     Set<String> prev = new HashSet<>(state.active);
                     prev.addAll(state.forced);
                     state.nightIndex = getNightIndex(world);
                     rollNight(world, dim, phase, state);
                     Set<String> next = new HashSet<>(state.active);
                     next.addAll(state.forced);
                     Set<String> started = new HashSet<>(next);
                     started.removeAll(prev);
                     Set<String> ended = new HashSet<>(prev);
                     ended.removeAll(next);
                     callNightEnd(world, dim, phase, state.nightIndex, ended);
                     callNightStart(world, dim, phase, state.nightIndex, started);
                     nightData.func_76185_a();
                     syncDim(world, dim, phase, state.nightIndex, state.active, state.forced);
                  }

                  if (!nightNow && nightPrev) {
                     Set<String> prev = new HashSet<>(state.active);
                     prev.addAll(state.forced);
                     callNightEnd(world, dim, phase, state.nightIndex, prev);
                  }

                  WAS_NIGHT.put(dim, nightNow);
               }
            }
         }
      }
   }
}
