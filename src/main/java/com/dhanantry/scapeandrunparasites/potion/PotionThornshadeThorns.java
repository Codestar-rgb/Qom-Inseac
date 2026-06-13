/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 */
package com.dhanantry.scapeandrunparasites.potion;

import com.dhanantry.scapeandrunparasites.potion.SRPEffectBase;
import net.minecraft.entity.EntityLivingBase;

public class PotionThornshadeThorns
extends SRPEffectBase {
    public PotionThornshadeThorns(String name, boolean isBadEffectIn, int liquidColorIn, int iconIndexX, int iconIndexY) {
        super(name, isBadEffectIn, liquidColorIn, iconIndexX, iconIndexY);
    }

    @Override
    public void func_76394_a(EntityLivingBase entity, int amplifier) {
    }

    @Override
    public boolean func_76397_a(int duration, int amplifier) {
        return false;
    }
}

