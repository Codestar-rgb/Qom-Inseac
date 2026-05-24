/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package com.subspaceparasite.bestiary.systems;

import net.minecraft.util.ResourceLocation;

public final class SystemEntry {
    public final String id;
    public final ResourceLocation icon;

    public SystemEntry(String id, ResourceLocation icon) {
        this.id = id;
        this.icon = icon;
    }

    public String nameKey() {
        return "bestiary.system." + this.id + ".name";
    }

    public String descKey() {
        return "bestiary.system." + this.id + ".desc";
    }
}

