package com.dhanantry.scapeandrunparasites.client.celestial;

import net.minecraft.client.Minecraft;

public final class BlackSkyClient {
   private BlackSkyClient() {
   }

   public static boolean isDarkDaysActive() {
      Minecraft mc = Minecraft.func_71410_x();
      if (mc == null || mc.field_71441_e == null) {
         return false;
      } else if (!mc.field_71441_e.field_73011_w.func_76569_d()) {
         return false;
      } else {
         int dim = mc.field_71441_e.field_73011_w.getDimension();
         return CelestialPhaseClient.getActiveIds(dim).contains("dark_days") || CelestialPhaseClient.getForcedIds(dim).contains("dark_days");
      }
   }

   public static boolean isBlackSkyActive() {
      return isDarkDaysActive();
   }
}
