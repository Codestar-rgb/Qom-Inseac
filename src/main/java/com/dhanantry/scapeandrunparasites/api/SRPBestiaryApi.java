package com.dhanantry.scapeandrunparasites.api;

import com.dhanantry.scapeandrunparasites.bestiary.cap.BestiaryCapability;
import com.dhanantry.scapeandrunparasites.bestiary.cap.IBestiaryProgress;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.entity.player.EntityPlayer;

public final class SRPBestiaryApi {
   private SRPBestiaryApi() {
   }

   public static boolean hasSeenCelestial(EntityPlayer player, String celestialId) {
      if (player != null && celestialId != null && !celestialId.isEmpty()) {
         IBestiaryProgress progress = (IBestiaryProgress)player.getCapability(BestiaryCapability.CAP, null);
         return progress != null && progress.hasSeenCelestial(celestialId);
      } else {
         return false;
      }
   }

   public static Set<String> getSeenCelestials(EntityPlayer player) {
      if (player == null) {
         return Collections.emptySet();
      } else {
         IBestiaryProgress progress = (IBestiaryProgress)player.getCapability(BestiaryCapability.CAP, null);
         return progress != null && progress.getSeenCelestials() != null
            ? Collections.unmodifiableSet(new HashSet<>(progress.getSeenCelestials()))
            : Collections.emptySet();
      }
   }

   public static int getSeenCelestialCount(EntityPlayer player) {
      return getSeenCelestials(player).size();
   }
}
