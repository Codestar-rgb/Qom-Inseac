/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.DamageSource
 */
package com.subspaceparasite.potion;

import com.subspaceparasite.potion.SPEffectBase;
import com.subspaceparasite.util.config.SPConfigSystems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

public class PotionBleed
extends SPEffectBase {
    public PotionBleed(String name, boolean isBadEffectIn, int liquidColorIn, int IconIndexX, int IconIndexY) {
        super(name, isBadEffectIn, liquidColorIn, IconIndexX, IconIndexY);
    }

    @Override
    public void func_76394_a(EntityLivingBase entity, int amplifier) {
        if (entity.field_70170_p.field_72995_K) {
            return;
        }
        float damage = entity.func_110138_aP() * SPConfigSystems.bleedingDamage;
        if (entity.field_70165_t != entity.field_70169_q || entity.field_70161_v != entity.field_70166_s) {
            damage *= (float)(amplifier + 1);
        }
        damage = Math.min(damage, SPConfigSystems.bleedingDamageCap);
        entity.func_70097_a(DamageSource.field_76376_m, damage);
    }
}

