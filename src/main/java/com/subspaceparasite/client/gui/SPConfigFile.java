/*
 * Decompiled with CFR 0.152.
 */
package com.subspaceparasite.client.gui;

public enum SPConfigFile {
    GENERAL("subspaceparasite/SParasites.cfg", "gui.subspaceparasite.config.title.general"),
    WORLD("subspaceparasite/SParasitesWorld.cfg", "gui.subspaceparasite.config.title.world"),
    SYSTEMS("subspaceparasite/SParasitesSystems.cfg", "gui.subspaceparasite.config.title.systems"),
    MOBS("subspaceparasite/SParasitesMobs.cfg", "gui.subspaceparasite.config.title.mobs");

    public final String relativePath;
    public final String titleKey;

    private SPConfigFile(String relativePath, String titleKey) {
        this.relativePath = relativePath;
        this.titleKey = titleKey;
    }
}

