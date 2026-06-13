/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 */
package com.dhanantry.scapeandrunparasites.entity.ai.misc;

import net.minecraft.entity.EntityLivingBase;

public interface EntityCanPullMobs {
    public boolean hasTargetedEntity();

    public void setTargetedEntity(int var1);

    public EntityLivingBase getTargetedEntity();

    public boolean checkAttackTarget(EntityLivingBase var1);

    public void setPStatus(int var1);

    public void setPullingMobEffects(EntityLivingBase var1);

    public void resetPullSkill();

    public int getAcceleration();
}

