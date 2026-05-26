/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.world.WorldServer
 */
package com.subspaceparasite.potion;

import com.subspaceparasite.potion.SPEffectBase;
import com.subspaceparasite.util.config.SPConfigSystems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.WorldServer;

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
        WorldServer ws = (WorldServer)entity.field_70170_p;
        double x = entity.field_70165_t;
        double y = entity.field_70163_u;
        double z = entity.field_70161_v;
        ws.func_180505_a(EnumParticleTypes.DAMAGE_INDICATOR, false, x, y, z, 1, 0.15, 0.3, 0.15, 0.0, new int[0]);
        float damage = entity.func_110138_aP() * SPConfigSystems.bleedingDamage;
        if (entity.field_70165_t != entity.field_70169_q || entity.field_70161_v != entity.field_70166_s) {
            damage *= (float)(amplifier + 1);
        }
        damage = Math.min(damage, SPConfigSystems.bleedingDamageCap);
        entity.func_70097_a(DamageSource.field_76376_m, damage);
    }
}

