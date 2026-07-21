package com.dhanantry.scapeandrunparasites.client.gui;

public enum SRPConfigFile {
   GENERAL("srparasites/SRParasites.cfg", "gui.srparasites.config.title.general"),
   WORLD("srparasites/SRParasitesWorld.cfg", "gui.srparasites.config.title.world"),
   SYSTEMS("srparasites/SRParasitesSystems.cfg", "gui.srparasites.config.title.systems"),
   MOBS("srparasites/SRParasitesMobs.cfg", "gui.srparasites.config.title.mobs");

   public final String relativePath;
   public final String titleKey;

   private SRPConfigFile(String relativePath, String titleKey) {
      this.relativePath = relativePath;
      this.titleKey = titleKey;
   }
}
