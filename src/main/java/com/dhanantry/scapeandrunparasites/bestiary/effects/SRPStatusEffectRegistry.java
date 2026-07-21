package com.dhanantry.scapeandrunparasites.bestiary.effects;

import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public final class SRPStatusEffectRegistry {
   private static final List<SRPStatusEffectRegistry.Entry> ENTRIES = new ArrayList<>();
   private static boolean built = false;

   private SRPStatusEffectRegistry() {
   }

   public static List<SRPStatusEffectRegistry.Entry> all() {
      if (!built) {
         build();
      }

      return ENTRIES;
   }

   private static void build() {
      built = true;
      ENTRIES.clear();

      try {
         for (Field f : SRPPotions.class.getDeclaredFields()) {
            if (Modifier.isStatic(f.getModifiers()) && Potion.class.isAssignableFrom(f.getType())) {
               f.setAccessible(true);
               Object o = f.get(null);
               if (o instanceof Potion) {
                  Potion p = (Potion)o;
                  ResourceLocation rl = p.getRegistryName();
                  if (rl != null && "srparasites".equals(rl.func_110624_b())) {
                     ENTRIES.add(new SRPStatusEffectRegistry.Entry(rl.func_110623_a(), p));
                  }
               }
            }
         }
      } catch (Throwable var7) {
      }

      ENTRIES.sort(Comparator.comparing(a -> a.id));
   }

   public static final class Entry {
      public final String id;
      public final Potion potion;

      public Entry(String id, Potion potion) {
         this.id = id;
         this.potion = potion;
      }
   }
}
