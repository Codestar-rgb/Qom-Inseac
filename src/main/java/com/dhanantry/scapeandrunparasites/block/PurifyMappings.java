package com.dhanantry.scapeandrunparasites.block;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;

public final class PurifyMappings {
   private static boolean loaded = false;
   private static final Map<String, String> exact = new HashMap<>();

   private PurifyMappings() {
   }

   private static void readMappings(File file) {
      try (InputStream in = new FileInputStream(file)) {
         readMappings(in);
      } catch (Exception var14) {
      }
   }

   public static void ensureLoaded(World world, String fileName) {
      if (!loaded) {
         loaded = true;
         File devFile = new File("src/main/java/com/dhanantry/scapeandrunparasites/util/config/" + fileName);
         if (devFile.exists()) {
            readMappings(devFile);
         } else {
            String cp = "/com/dhanantry/scapeandrunparasites/util/config/" + fileName;

            try (InputStream in = PurifyMappings.class.getResourceAsStream(cp)) {
               if (in != null) {
                  readMappings(in);
                  return;
               }
            } catch (Exception var18) {
            }

            File cfgDir = Loader.instance().getConfigDir();
            if (!cfgDir.exists()) {
               cfgDir.mkdirs();
            }

            File mappingFile = new File(cfgDir, fileName);
            if (!mappingFile.exists()) {
               writeDefault(mappingFile);
            }

            readMappings(mappingFile);
         }
      }
   }

   public static boolean isSrp(IBlockState st) {
      Block b = st.func_177230_c();
      ResourceLocation rl = b.getRegistryName();
      if (rl == null) {
         return false;
      } else {
         String domain = rl.func_110624_b();
         String path = rl.func_110623_a().toLowerCase(Locale.ROOT);
         if (!"srparasites".equals(domain)) {
            return false;
         } else {
            return !path.equals("infestremain") && !path.contains("infestation_purifier")
               ? path.contains("inf") || path.contains("infect") || path.contains("parasite")
               : false;
         }
      }
   }

   public static IBlockState mapToVanillaState(IBlockState srpState) {
      Block b = srpState.func_177230_c();
      ResourceLocation rl = b.getRegistryName();
      if (rl == null) {
         return null;
      } else {
         String key = rl.toString();
         String toId = exact.get(key);
         if (toId != null) {
            Block vb = (Block)Block.field_149771_c.func_82594_a(id(toId));
            return vb == null ? null : vb.func_176223_P();
         } else {
            String path = rl.func_110623_a();
            if (containsAny(path, "glass_pane")) {
               return def("minecraft:glass_pane");
            } else if (containsAny(path, "glass")) {
               return def("minecraft:glass");
            } else if (containsAny(path, "stone_brick_wall", "stonebrick_wall", "brick_wall", "rubble_wall")) {
               return def("minecraft:cobblestone_wall");
            } else if (containsAny(path, "fence")) {
               return def("minecraft:fence");
            } else if (containsAny(path, "stone_bricks_stairs", "stonebrick_stairs")) {
               return def("minecraft:stone_brick_stairs");
            } else if (containsAny(path, "sandstone_stairs")) {
               return def("minecraft:sandstone_stairs");
            } else if (containsAny(path, "plank_stairs", "planks_stairs", "wood_stairs")) {
               return def("minecraft:oak_stairs");
            } else if (containsAny(path, "ss_chiseled", "chiseled_sandstone")) {
               return def("minecraft:sandstone");
            } else if (containsAny(path, "inf_ss", "sandstone")) {
               return def("minecraft:sandstone");
            } else if (containsAny(path, "infestedsand", "red_sand", "sand_red", "sand")) {
               return def("minecraft:sand");
            } else if (containsAny(path, "pot")) {
               return def("minecraft:flower_pot");
            } else if (containsAny(path, "column", "pillar", "log_axis", "axis")) {
               return def("minecraft:log");
            } else if (containsAny(path, "trunk", "bark", "stem", "wood")) {
               return def("minecraft:log");
            } else if (containsAny(path, "terracotta", "hardened_clay", "stained_clay")) {
               return def("minecraft:stained_hardened_clay");
            } else if (containsAny(path, "stone_polished", "polished")) {
               return def("minecraft:stone");
            } else if (containsAny(path, "stone_bricks", "stonebrick")) {
               return def("minecraft:stonebrick");
            } else if (containsAny(path, "planks", "wood_planks")) {
               return def("minecraft:planks");
            } else if (containsAny(path, "cobblestone")) {
               return def("minecraft:cobblestone");
            } else if (containsAny(path, "rubble", "andesite", "diorite", "granite", "stone")) {
               return def("minecraft:stone");
            } else if (containsAny(path, "leaves")) {
               return def("minecraft:leaves");
            } else {
               return containsAny(path, "stain", "dirt", "grass") ? def("minecraft:dirt") : null;
            }
         }
      }
   }

   private static boolean containsAny(String s, String... keys) {
      s = s.toLowerCase(Locale.ROOT);

      for (String k : keys) {
         if (s.contains(k)) {
            return true;
         }
      }

      return false;
   }

   private static IBlockState def(String id) {
      Block b = (Block)Block.field_149771_c.func_82594_a(id(id));
      return b == null ? null : b.func_176223_P();
   }

   private static ResourceLocation id(String full) {
      String t = full.trim();
      if (!t.contains(":")) {
         return null;
      } else {
         String[] p = t.split(":");
         return p.length != 2 ? null : new ResourceLocation(p[0], p[1]);
      }
   }

   private static void readMappings(InputStream in) {
      String line;
      try (BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
         while ((line = br.readLine()) != null) {
            String s = line.trim();
            if (!s.isEmpty() && !s.startsWith("#")) {
               String[] parts = s.split("\\s*-\\s*");
               if (parts.length == 2) {
                  ResourceLocation from = id(parts[0]);
                  ResourceLocation to = id(parts[1]);
                  if (from != null && to != null) {
                     Block fb = (Block)Block.field_149771_c.func_82594_a(from);
                     Block vb = (Block)Block.field_149771_c.func_82594_a(to);
                     if (fb != null && vb != null) {
                        exact.put(from.toString(), to.toString());
                     }
                  }
               }
            }
         }
      } catch (Exception var20) {
      }
   }

   private static void writeDefault(File f) {
      List<String> defaults = new ArrayList<>();
      defaults.add("# SRP -> Vanilla mappings (exact); heuristics handle the rest");
      defaults.add("srparasites:infestedstain - minecraft:dirt");
      defaults.add("srparasites:infested_leaves - minecraft:leaves");
      defaults.add("srparasites:infestedrubble - minecraft:stone");
      defaults.add("srparasites:infested_cobblestone - minecraft:cobblestone");
      defaults.add("srparasites:infested_planks - minecraft:planks");
      defaults.add("srparasites:infested_plank_stairs - minecraft:oak_stairs");
      defaults.add("srparasites:infested_stone_bricks - minecraft:stonebrick");
      defaults.add("srparasites:infested_stone_polished - minecraft:stone");
      defaults.add("srparasites:infested_terracotta - minecraft:stained_hardened_clay");
      defaults.add("srparasites:infested_column - minecraft:log");
      defaults.add("srparasites:infested_pot - minecraft:flower_pot");
      defaults.add("srparasites:infestedsand - minecraft:sand");
      defaults.add("srparasites:inf_ss - minecraft:sandstone");
      defaults.add("srparasites:inf_ss_chiseled - minecraft:sandstone");
      defaults.add("srparasites:infested_sandstone_stairs - minecraft:sandstone_stairs");
      defaults.add("srparasites:infested_stone_bricks_stairs - minecraft:stone_brick_stairs");
      defaults.add("srparasites:infested_fence - minecraft:fence");
      defaults.add("srparasites:infested_stone_brick_wall - minecraft:cobblestone_wall");
      defaults.add("srparasites:infested_glass - minecraft:glass");
      defaults.add("srparasites:infested_glass_pane - minecraft:glass_pane");

      try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8))) {
         for (String d : defaults) {
            pw.println(d);
         }
      } catch (IOException var16) {
      }
   }
}
