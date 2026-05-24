/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 */
package com.subspaceparasite.potion;

import com.subspaceparasite.entity.ai.misc.EntityPMalleable;
import com.subspaceparasite.potion.SPEffectBase;
import net.minecraft.entity.EntityLivingBase;

public class PotionFoster
extends SPEffectBase {
    public PotionFoster(String name, boolean isBadEffectIn, int liquidColorIn, int IconIndexX, int IconIndexY) {
        super(name, isBadEffectIn, liquidColorIn, IconIndexX, IconIndexY);
    }

    @Override
    public void func_76394_a(EntityLivingBase entity, int amplifier) {
        if (entity.field_70170_p.field_72995_K) {
            return;
        }
        if (entity instanceof EntityPMalleable) {
            ((EntityPMalleable)entity).increaseAllResistances();
        }
    }
}

