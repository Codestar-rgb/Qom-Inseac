/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 */
package com.subspaceparasite.client.particle;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

public enum SPEnumParticle {
    FOG("fog", 0, false),
    SPORE("spore", 1, false),
    GCLOUD("gcloud", 2, false),
    GSPLASH("gsplash", 3, false),
    RHAPPY("rhappy", 4, false),
    BIOMASS("biomass", 5, false),
    EEN("een", 6, false);

    private final String particleName;
    private final int particleID;
    private final boolean shouldIgnoreRange;
    private final int argumentCount;
    private static final Map<Integer, SPEnumParticle> PARTICLES;
    private static final Map<String, SPEnumParticle> BY_NAME;

    private SPEnumParticle(String particleNameIn, int particleIDIn, boolean shouldIgnoreRangeIn, int argumentCountIn) {
        this.particleName = particleNameIn;
        this.particleID = particleIDIn;
        this.shouldIgnoreRange = shouldIgnoreRangeIn;
        this.argumentCount = argumentCountIn;
    }

    private SPEnumParticle(String particleNameIn, int particleIDIn, boolean shouldIgnoreRangeIn) {
        this(particleNameIn, particleIDIn, shouldIgnoreRangeIn, 0);
    }

    public static Set<String> getParticleNames() {
        return BY_NAME.keySet();
    }

    public String getParticleName() {
        return this.particleName;
    }

    public int getParticleID() {
        return this.particleID;
    }

    public int getArgumentCount() {
        return this.argumentCount;
    }

    public boolean getShouldIgnoreRange() {
        return this.shouldIgnoreRange;
    }

    @Nullable
    public static SPEnumParticle getParticleFromId(int particleId) {
        return PARTICLES.get(particleId);
    }

    @Nullable
    public static SPEnumParticle getByName(String nameIn) {
        return BY_NAME.get(nameIn);
    }

    static {
        PARTICLES = Maps.newHashMap();
        BY_NAME = Maps.newHashMap();
        for (SPEnumParticle enumparticletypes : SPEnumParticle.values()) {
            PARTICLES.put(enumparticletypes.getParticleID(), enumparticletypes);
            BY_NAME.put(enumparticletypes.getParticleName(), enumparticletypes);
        }
    }
}

