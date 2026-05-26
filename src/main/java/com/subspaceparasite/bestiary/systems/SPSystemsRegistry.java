/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package com.subspaceparasite.bestiary.systems;

import com.subspaceparasite.bestiary.systems.SystemEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import net.minecraft.util.ResourceLocation;

public final class SPSystemsRegistry {
    private static final List<SystemEntry> ENTRIES = new ArrayList<SystemEntry>();
    private static boolean built = false;

    private SPSystemsRegistry() {
    }

    private static void add(String id, String iconPath) {
        ENTRIES.add(new SystemEntry(id, new ResourceLocation("subspaceparasite", iconPath)));
    }

    private static void build() {
        if (built) {
            return;
        }
        built = true;
        SPSystemsRegistry.add("reinforcement", "textures/gui/systems/reinforcement.png");
        SPSystemsRegistry.add("merge", "textures/gui/systems/merge.png");
        SPSystemsRegistry.add("status_effects", "textures/gui/systems/status_effects.png");
        SPSystemsRegistry.add("eiv", "textures/gui/potion_cold_utr.png");
        SPSystemsRegistry.add("evolution", "textures/gui/systems/evolution.png");
        SPSystemsRegistry.add("collective_consciousness", "textures/gui/systems/collective_consciousness.png");
        SPSystemsRegistry.add("scent", "textures/gui/systems/scent.png");
        SPSystemsRegistry.add("ubiquitous_development", "textures/gui/systems/ubiquitous_development.png");
        SPSystemsRegistry.add("dislodgment", "textures/gui/systems/dislodgment.png");
        SPSystemsRegistry.add("generations", "textures/gui/systems/generations.png");
        SPSystemsRegistry.add("vectors", "textures/gui/systems/vectors.png");
        SPSystemsRegistry.add("colonies", "textures/gui/systems/colonies.png");
        SPSystemsRegistry.add("nodes", "textures/gui/systems/nodes.png");
        SPSystemsRegistry.add("hives", "textures/gui/systems/hives.png");
        SPSystemsRegistry.add("nests", "textures/gui/systems/nests.png");
        SPSystemsRegistry.add("variants", "textures/gui/systems/variants.png");
        SPSystemsRegistry.add("derived_distortion", "textures/gui/systems/derived_distortion.png");
        ENTRIES.sort(Comparator.comparing(e -> e.id));
    }

    public static List<SystemEntry> all() {
        SPSystemsRegistry.build();
        return Collections.unmodifiableList(ENTRIES);
    }
}

