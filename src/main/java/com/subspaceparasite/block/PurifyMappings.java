/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.Loader
 */
package com.subspaceparasite.block;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;

public final class PurifyMappings {
    private static boolean loaded = false;
    private static final Map<String, String> exact = new HashMap<String, String>();

    private PurifyMappings() {
    }

    private static void readMappings(File file) {
        try (FileInputStream in = new FileInputStream(file);){
            PurifyMappings.readMappings(in);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static void ensureLoaded(World world, String fileName) {
        File mappingFile;
        if (loaded) {
            return;
        }
        loaded = true;
        File devFile = new File("src/main/java/com/subspaceparasite/util/config/" + fileName);
        if (devFile.exists()) {
            PurifyMappings.readMappings(devFile);
            return;
        }
        String cp = "/com/subspaceparasite/util/config/" + fileName;
        try (InputStream in2 = PurifyMappings.class.getResourceAsStream(cp);){
            if (in2 != null) {
                PurifyMappings.readMappings(in2);
                return;
            }
        }
        catch (Exception in2) {
            // empty catch block
        }
        File cfgDir = Loader.instance().getConfigDir();
        if (!cfgDir.exists()) {
            cfgDir.mkdirs();
        }
        if (!(mappingFile = new File(cfgDir, fileName)).exists()) {
            PurifyMappings.writeDefault(mappingFile);
        }
        PurifyMappings.readMappings(mappingFile);
    }

    public static boolean isSrp(IBlockState st) {
        Block b = st.func_177230_c();
        ResourceLocation rl = b.getRegistryName();
        if (rl == null) {
            return false;
        }
        String domain = rl.func_110624_b();
        String path = rl.func_110623_a().toLowerCase(Locale.ROOT);
        if (!"subspaceparasite".equals(domain)) {
            return false;
        }
        if (path.equals("infestremain") || path.contains("infestation_purifier")) {
            return false;
        }
        return path.contains("inf") || path.contains("infect") || path.contains("parasite");
    }

    public static IBlockState mapToVanillaState(IBlockState srpState) {
        Block b = srpState.func_177230_c();
        ResourceLocation rl = b.getRegistryName();
        if (rl == null) {
            return null;
        }
        String key = rl.toString();
        String toId = exact.get(key);
        if (toId != null) {
            Block vb = (Block)Block.field_149771_c.func_82594_a((Object)PurifyMappings.id(toId));
            return vb == null ? null : vb.func_176223_P();
        }
        String path = rl.func_110623_a();
        if (PurifyMappings.containsAny(path, "glass_pane")) {
            return PurifyMappings.def("minecraft:glass_pane");
        }
        if (PurifyMappings.containsAny(path, "glass")) {
            return PurifyMappings.def("minecraft:glass");
        }
        if (PurifyMappings.containsAny(path, "stone_brick_wall", "stonebrick_wall", "brick_wall", "rubble_wall")) {
            return PurifyMappings.def("minecraft:cobblestone_wall");
        }
        if (PurifyMappings.containsAny(path, "fence")) {
            return PurifyMappings.def("minecraft:fence");
        }
        if (PurifyMappings.containsAny(path, "stone_bricks_stairs", "stonebrick_stairs")) {
            return PurifyMappings.def("minecraft:stone_brick_stairs");
        }
        if (PurifyMappings.containsAny(path, "sandstone_stairs")) {
            return PurifyMappings.def("minecraft:sandstone_stairs");
        }
        if (PurifyMappings.containsAny(path, "plank_stairs", "planks_stairs", "wood_stairs")) {
            return PurifyMappings.def("minecraft:oak_stairs");
        }
        if (PurifyMappings.containsAny(path, "ss_chiseled", "chiseled_sandstone")) {
            return PurifyMappings.def("minecraft:sandstone");
        }
        if (PurifyMappings.containsAny(path, "inf_ss", "sandstone")) {
            return PurifyMappings.def("minecraft:sandstone");
        }
        if (PurifyMappings.containsAny(path, "infestedsand", "red_sand", "sand_red", "sand")) {
            return PurifyMappings.def("minecraft:sand");
        }
        if (PurifyMappings.containsAny(path, "pot")) {
            return PurifyMappings.def("minecraft:flower_pot");
        }
        if (PurifyMappings.containsAny(path, "column", "pillar", "log_axis", "axis")) {
            return PurifyMappings.def("minecraft:log");
        }
        if (PurifyMappings.containsAny(path, "trunk", "bark", "stem", "wood")) {
            return PurifyMappings.def("minecraft:log");
        }
        if (PurifyMappings.containsAny(path, "terracotta", "hardened_clay", "stained_clay")) {
            return PurifyMappings.def("minecraft:stained_hardened_clay");
        }
        if (PurifyMappings.containsAny(path, "stone_polished", "polished")) {
            return PurifyMappings.def("minecraft:stone");
        }
        if (PurifyMappings.containsAny(path, "stone_bricks", "stonebrick")) {
            return PurifyMappings.def("minecraft:stonebrick");
        }
        if (PurifyMappings.containsAny(path, "planks", "wood_planks")) {
            return PurifyMappings.def("minecraft:planks");
        }
        if (PurifyMappings.containsAny(path, "cobblestone")) {
            return PurifyMappings.def("minecraft:cobblestone");
        }
        if (PurifyMappings.containsAny(path, "rubble", "andesite", "diorite", "granite", "stone")) {
            return PurifyMappings.def("minecraft:stone");
        }
        if (PurifyMappings.containsAny(path, "leaves")) {
            return PurifyMappings.def("minecraft:leaves");
        }
        if (PurifyMappings.containsAny(path, "stain", "dirt", "grass")) {
            return PurifyMappings.def("minecraft:dirt");
        }
        return null;
    }

    private static boolean containsAny(String s, String ... keys) {
        s = s.toLowerCase(Locale.ROOT);
        for (String k : keys) {
            if (!s.contains(k)) continue;
            return true;
        }
        return false;
    }

    private static IBlockState def(String id) {
        Block b = (Block)Block.field_149771_c.func_82594_a((Object)PurifyMappings.id(id));
        return b == null ? null : b.func_176223_P();
    }

    private static ResourceLocation id(String full) {
        String t = full.trim();
        if (!t.contains(":")) {
            return null;
        }
        String[] p = t.split(":");
        if (p.length != 2) {
            return null;
        }
        return new ResourceLocation(p[0], p[1]);
    }

    private static void readMappings(InputStream in) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));){
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts;
                String s = line.trim();
                if (s.isEmpty() || s.startsWith("#") || (parts = s.split("\\s*-\\s*")).length != 2) continue;
                ResourceLocation from = PurifyMappings.id(parts[0]);
                ResourceLocation to = PurifyMappings.id(parts[1]);
                if (from == null || to == null) continue;
                Block fb = (Block)Block.field_149771_c.func_82594_a((Object)from);
                Block vb = (Block)Block.field_149771_c.func_82594_a((Object)to);
                if (fb == null || vb == null) continue;
                exact.put(from.toString(), to.toString());
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private static void writeDefault(File f) {
        ArrayList<String> defaults = new ArrayList<String>();
        defaults.add("# SRP -> Vanilla mappings (exact); heuristics handle the rest");
        defaults.add("subspaceparasite:infestedstain - minecraft:dirt");
        defaults.add("subspaceparasite:infested_leaves - minecraft:leaves");
        defaults.add("subspaceparasite:infestedrubble - minecraft:stone");
        defaults.add("subspaceparasite:infested_cobblestone - minecraft:cobblestone");
        defaults.add("subspaceparasite:infested_planks - minecraft:planks");
        defaults.add("subspaceparasite:infested_plank_stairs - minecraft:oak_stairs");
        defaults.add("subspaceparasite:infested_stone_bricks - minecraft:stonebrick");
        defaults.add("subspaceparasite:infested_stone_polished - minecraft:stone");
        defaults.add("subspaceparasite:infested_terracotta - minecraft:stained_hardened_clay");
        defaults.add("subspaceparasite:infested_column - minecraft:log");
        defaults.add("subspaceparasite:infested_pot - minecraft:flower_pot");
        defaults.add("subspaceparasite:infestedsand - minecraft:sand");
        defaults.add("subspaceparasite:inf_ss - minecraft:sandstone");
        defaults.add("subspaceparasite:inf_ss_chiseled - minecraft:sandstone");
        defaults.add("subspaceparasite:infested_sandstone_stairs - minecraft:sandstone_stairs");
        defaults.add("subspaceparasite:infested_stone_bricks_stairs - minecraft:stone_brick_stairs");
        defaults.add("subspaceparasite:infested_fence - minecraft:fence");
        defaults.add("subspaceparasite:infested_stone_brick_wall - minecraft:cobblestone_wall");
        defaults.add("subspaceparasite:infested_glass - minecraft:glass");
        defaults.add("subspaceparasite:infested_glass_pane - minecraft:glass_pane");
        try (PrintWriter pw = new PrintWriter(new OutputStreamWriter((OutputStream)new FileOutputStream(f), StandardCharsets.UTF_8));){
            for (String d : defaults) {
                pw.println(d);
            }
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }
}

