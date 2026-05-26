/*
 * Decompiled with CFR 0.152.
 */
package com.subspaceparasite.client.gui;

public enum SPConfigFile {
    GENERAL("subspaceparasite/SPParasites.cfg", "gui.subspaceparasite.config.title.general"),
    WORLD("subspaceparasite/SPParasitesWorld.cfg", "gui.subspaceparasite.config.title.world"),
    SYSTEMS("subspaceparasite/SPParasitesSystems.cfg", "gui.subspaceparasite.config.title.systems"),
    MOBS("subspaceparasite/SPParasitesMobs.cfg", "gui.subspaceparasite.config.title.mobs");

    public final String relativePath;
    public final String titleKey;

    private SPConfigFile(String relativePath, String titleKey) {
        this.relativePath = relativePath;
        this.titleKey = titleKey;
    }
}

