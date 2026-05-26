/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package com.subspaceparasite.client.fog;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class SPFogManager {
    private static final Logger LOG = LogManager.getLogger((String)"SP-Fog");
    private static final SPFogManager INSTANCE = new SPFogManager();
    private boolean enabled = false;

    public static SPFogManager get() {
        return INSTANCE;
    }

    private SPFogManager() {
    }

    public void enable() {
        this.enabled = true;
        LOG.info("[SP Fog] enabled");
    }

    public void disable() {
        this.enabled = false;
        LOG.info("[SP Fog] disabled");
    }

    public boolean isEnabled() {
        return this.enabled;
    }
}

