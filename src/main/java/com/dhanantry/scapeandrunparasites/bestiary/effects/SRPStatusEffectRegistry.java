/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.ResourceLocation
 */
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
    private static final List<Entry> ENTRIES = new ArrayList<Entry>();
    private static boolean built = false;

    private SRPStatusEffectRegistry() {
    }

    public static List<Entry> all() {
        if (!built) {
            SRPStatusEffectRegistry.build();
        }
        return ENTRIES;
    }

    private static void build() {
        built = true;
        ENTRIES.clear();
        try {
            for (Field f : SRPPotions.class.getDeclaredFields()) {
                Potion p;
                ResourceLocation rl;
                if (!Modifier.isStatic(f.getModifiers()) || !Potion.class.isAssignableFrom(f.getType())) continue;
                f.setAccessible(true);
                Object o = f.get(null);
                if (!(o instanceof Potion) || (rl = (p = (Potion)o).getRegistryName()) == null || !"srparasites".equals(rl.func_110624_b())) continue;
                ENTRIES.add(new Entry(rl.func_110623_a(), p));
            }
        }
        catch (Throwable throwable) {
            // empty catch block
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

