/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.DamageSource
 */
package com.dhanantry.scapeandrunparasites.entity.ai.misc;

import net.minecraft.util.DamageSource;

public interface EntityBodyParts {
    public boolean attackEntityBodyFrom(DamageSource var1, float var2, int var3, boolean var4);

    public void setBodyPartDead(int var1);
}

