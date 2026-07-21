package com.dhanantry.scapeandrunparasites.bestiary.systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import net.minecraft.util.ResourceLocation;

public final class SRPSystemsRegistry {
   private static final List<SystemEntry> ENTRIES = new ArrayList<>();
   private static boolean built = false;

   private SRPSystemsRegistry() {
   }

   private static void add(String id, String iconPath) {
      ENTRIES.add(new SystemEntry(id, new ResourceLocation("srparasites", iconPath)));
   }

   private static void build() {
      if (!built) {
         built = true;
         add("reinforcement", "textures/gui/systems/reinforcement.png");
         add("merge", "textures/gui/systems/merge.png");
         add("status_effects", "textures/gui/systems/status_effects.png");
         add("eiv", "textures/gui/potion_cold_utr.png");
         add("evolution", "textures/gui/systems/evolution.png");
         add("collective_consciousness", "textures/gui/systems/collective_consciousness.png");
         add("scent", "textures/gui/systems/scent.png");
         add("ubiquitous_development", "textures/gui/systems/ubiquitous_development.png");
         add("dislodgment", "textures/gui/systems/dislodgment.png");
         add("generations", "textures/gui/systems/generations.png");
         add("vectors", "textures/gui/systems/vectors.png");
         add("colonies", "textures/gui/systems/colonies.png");
         add("nodes", "textures/gui/systems/nodes.png");
         add("hives", "textures/gui/systems/hives.png");
         add("nests", "textures/gui/systems/nests.png");
         add("variants", "textures/gui/systems/variants.png");
         add("derived_distortion", "textures/gui/systems/derived_distortion.png");
         ENTRIES.sort(Comparator.comparing(e -> e.id));
      }
   }

   public static List<SystemEntry> all() {
      build();
      return Collections.unmodifiableList(ENTRIES);
   }
}
