/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package com.subspaceparasite.entity.ai.misc;

import com.subspaceparasite.entity.ai.misc.EntityPInfected;
import com.subspaceparasite.util.SPAttributes;
import com.subspaceparasite.util.config.SPConfig;
import net.minecraft.world.World;

public abstract class EntityPAssimara
extends EntityPInfected {
    public EntityPAssimara(World worldIn) {
        super(worldIn);
        this.field_70728_aV = SPAttributes.XP_INFECTED;
        this.damageCap = SPConfig.assimaraCap;
        this.canD = SPConfig.assimaradespawn;
        this.MiniDamage = SPConfig.assimaraMinDamage;
        this.oneMindDeathValue = SPConfig.assimaraOneMindDeathV;
        this.foodSteal = 0.5f;
        this.valueEvDeath = SPConfig.assimaraLoosingEPValue;
        this.thisMelting = false;
        this.setScentHPMultiplier(2.5f);
    }

    @Override
    public void setHost(String mobname) {
    }
}

