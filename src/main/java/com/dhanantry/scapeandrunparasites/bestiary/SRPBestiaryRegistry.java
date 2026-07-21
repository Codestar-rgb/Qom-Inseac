package com.dhanantry.scapeandrunparasites.bestiary;

import com.dhanantry.scapeandrunparasites.bestiary.blocks.SRPBlockCompendiumRegistry;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.util.ResourceLocation;

public final class SRPBestiaryRegistry {
   private static final Gson GSON = new Gson();
   private static final Map<String, BestiaryEntry> ENTRIES = new LinkedHashMap<>();
   private static final Map<String, Float> RENDER_SCALE = new HashMap<>();

   public static BestiaryEntry get(String id) {
      return ENTRIES.get(id);
   }

   public static Collection<BestiaryEntry> all() {
      return ENTRIES.values();
   }

   public static float getRenderScale(String id) {
      return RENDER_SCALE.getOrDefault(id, 1.0F);
   }

   public static void registerDefaults() {
      ENTRIES.clear();
      RENDER_SCALE.clear();
      ClassLoader cl = SRPBestiaryRegistry.class.getClassLoader();
      String base = "assets/srparasites/bestiary/";
      SRPBestiaryRegistry.IndexJson index = fromJson(cl, "assets/srparasites/bestiary/index.json", SRPBestiaryRegistry.IndexJson.class);
      if (index == null) {
         log("[Bestiary] index.json not found or failed to parse. No entries loaded.");
      } else {
         SRPBlockCompendiumRegistry.registerDefaults();
         if (index.scales != null) {
            for (Entry<String, Double> e : index.scales.entrySet()) {
               if (e.getKey() != null && e.getValue() != null) {
                  RENDER_SCALE.put(e.getKey(), e.getValue().floatValue());
               }
            }
         }

         int loaded = 0;
         if (index.mobs != null) {
            for (String name : index.mobs) {
               if (name != null && !name.isEmpty()) {
                  SRPBestiaryRegistry.MobJson j = fromJson(cl, "assets/srparasites/bestiary/" + name + ".json", SRPBestiaryRegistry.MobJson.class);
                  if (j == null) {
                     log("[Bestiary] Skipping '%s' (missing or invalid JSON).", name);
                  } else {
                     String id = j.id != null && !j.id.isEmpty() ? j.id : j.nameKey;
                     if (id != null && !id.isEmpty()) {
                        ParasiteTier tier = parseTier(j.tier);
                        if (tier == null) {
                           log("[Bestiary] Skipping '%s' (invalid tier '%s').", id, j.tier);
                        } else {
                           int hp = -1;
                           float dmg = -1.0F;
                           if (j.stats != null && "override".equalsIgnoreCase(j.stats.source)) {
                              if (j.stats.hp != null) {
                                 hp = j.stats.hp.intValue();
                              }

                              if (j.stats.damage != null) {
                                 dmg = j.stats.damage.floatValue();
                              }
                           }

                           int loreMin = j.minLoreKill != null ? j.minLoreKill : 10;
                           int statMin = j.minStatKill != null ? j.minStatKill : 3;
                           BestiaryEntry entry = new BestiaryEntry(
                              id,
                              tier,
                              j.nameKey != null ? j.nameKey : makeDefaultNameKey(id),
                              hp,
                              dmg,
                              j.loreKey != null ? j.loreKey : makeDefaultLoreKey(id),
                              loreMin,
                              statMin
                           );
                           ENTRIES.put(entry.mobId, entry);
                           loaded++;
                        }
                     } else {
                        log("[Bestiary] Skipping profile '%s' (no id/nameKey).", name);
                     }
                  }
               }
            }
         }

         log("[Bestiary] Loaded %d entries. Scales: %d", loaded, RENDER_SCALE.size());
         if (loaded == 0) {
            log("[Bestiary] WARNING: 0 entries loaded. Check your index.json, profile filenames, and tier strings.");
         }
      }
   }

   private static ParasiteTier parseTier(String raw) {
      if (raw == null) {
         return null;
      } else {
         String s = raw.trim();
         if (s.isEmpty()) {
            return null;
         } else {
            s = s.replace(' ', '_').toUpperCase(Locale.ROOT);
            if (s.startsWith("TIER_")) {
               s = s.replace("TIER_", "STAGE_");
            }

            try {
               return ParasiteTier.valueOf(s);
            } catch (IllegalArgumentException var3) {
               return null;
            }
         }
      }
   }

   private static String makeDefaultNameKey(String id) {
      ResourceLocation rl = new ResourceLocation(id);
      return "entity." + rl.func_110624_b() + "." + rl.func_110623_a() + ".name";
   }

   private static String makeDefaultLoreKey(String id) {
      ResourceLocation rl = new ResourceLocation(id);
      return "bestiary.lore." + rl.func_110624_b() + "." + rl.func_110623_a();
   }

   private static <T> T fromJson(ClassLoader cl, String path, Class<T> type) {
      try (InputStream in = cl.getResourceAsStream(path)) {
         if (in != null) {
            InputStreamReader r = new InputStreamReader(in, StandardCharsets.UTF_8);
            return (T)GSON.fromJson(r, type);
         } else {
            return null;
         }
      } catch (Exception var19) {
         return null;
      }
   }

   private static void log(String fmt, Object... args) {
      System.out.println(String.format(Locale.ROOT, fmt, args));
   }

   private static final class IndexJson {
      @SerializedName("mobs")
      List<String> mobs;
      @SerializedName("scales")
      Map<String, Double> scales;
   }

   private static final class MobJson {
      @SerializedName("id")
      String id;
      @SerializedName("nameKey")
      String nameKey;
      @SerializedName("tier")
      String tier;
      @SerializedName("loreKey")
      String loreKey;
      @SerializedName("stats")
      SRPBestiaryRegistry.MobJson.Stats stats;
      @SerializedName("minlorekill")
      Integer minLoreKill;
      @SerializedName("minstatkill")
      Integer minStatKill;

      private static final class Stats {
         @SerializedName("source")
         String source;
         @SerializedName("hp")
         Double hp;
         @SerializedName("damage")
         Double damage;
      }
   }
}
