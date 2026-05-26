/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 */
package com.subspaceparasite.potion;

import com.subspaceparasite.potion.SPEffectBase;
import net.minecraft.entity.EntityLivingBase;

public class PotionOverheat
extends SPEffectBase {
    public PotionOverheat(String name, boolean isBadEffectIn, int liquidColorIn, int IconIndexX, int IconIndexY) {
        super(name, isBadEffectIn, liquidColorIn, IconIndexX, IconIndexY);
    }

    @Override
    public void func_76394_a(EntityLivingBase entity, int amplifier) {
        if (entity.field_70170_p.field_72995_K) {
            return;
        }
        if (entity.field_70173_aa % 20 == 0) {
            entity.func_70015_d(2);
        }
    }
}

