/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.Loader
 */
package com.subspaceparasite.compatibility;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.util.config.SPConfigWorld;
import java.util.HashMap;
import net.minecraftforge.fml.common.Loader;

public class ModCompatibility {
    public static HashMap<String, Boolean> modCompatModules = new HashMap();

    public static void preInit() {
        modCompatModules.put("ReachFix", Loader.isModLoaded((String)"reachfix"));
        modCompatModules.put("REID", Loader.isModLoaded((String)"JustEnoughIDs"));
        SPMain.logger.info(String.format("[MOD COMPATIBILITY] Checked for %d mods that may be in the list that need patches or alternative code", modCompatModules.size()));
    }

    public static void postInit() {
        if (modCompatModules.get("REID").booleanValue() && SPConfigWorld.biomeRegster && SPConfigWorld.nodesActivated) {
            SPMain.logger.error("[MOD COMPATIBILITY] Scape and Run: Parasites is NOT compatible with Roughly Enough IDs or Just Enough IDs. Nodes and biome spread will not function properly as a consequence.");
        }
        SPMain.logger.info(String.format("[MOD COMPATIBILITY] %d of %d modules activated", ModCompatibility.modulesActive(), modCompatModules.size()));
    }

    public static int modulesActive() {
        int i = 0;
        for (boolean b : modCompatModules.values()) {
            if (!b) continue;
            ++i;
        }
        return i;
    }

    public static void warnAddonDeprecatedFunction(String oldFunction, String newFunction) {
        SPMain.logger.warn(String.format("[MOD COMPATIBILITY] An addon is currently using a function that is now deprecated! The old function is %s, and replaced with %s. Failure to update will cause crashes once backwards compatibility is removed. For now, the old function is similar in function to the older version as a failsafe. Please report this to the addon author ASAP.", oldFunction, newFunction));
    }
}

