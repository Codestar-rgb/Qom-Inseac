/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.entity.ai.misc;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPInfected;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import net.minecraft.world.World;

public abstract class EntityPAssimara
extends EntityPInfected {
    public EntityPAssimara(World worldIn) {
        super(worldIn);
        this.field_70728_aV = SRPAttributes.XP_INFECTED;
        this.damageCap = SRPConfig.assimaraCap;
        this.canD = SRPConfig.assimaradespawn;
        this.MiniDamage = SRPConfig.assimaraMinDamage;
        this.oneMindDeathValue = SRPConfig.assimaraOneMindDeathV;
        this.foodSteal = 0.5f;
        this.valueEvDeath = SRPConfig.assimaraLoosingEPValue;
        this.thisMelting = false;
        this.setScentHPMultiplier(2.5f);
    }

    @Override
    public void setHost(String mobname) {
    }
}

