package com.dhanantry.scapeandrunparasites.client.fog;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class SRPFogManager {
   private static final Logger LOG = LogManager.getLogger("SRP-Fog");
   private static final SRPFogManager INSTANCE = new SRPFogManager();
   private boolean enabled = false;

   public static SRPFogManager get() {
      return INSTANCE;
   }

   private SRPFogManager() {
   }

   public void enable() {
      this.enabled = true;
      LOG.info("[SRP Fog] enabled");
   }

   public void disable() {
      this.enabled = false;
      LOG.info("[SRP Fog] disabled");
   }

   public boolean isEnabled() {
      return this.enabled;
   }
}
