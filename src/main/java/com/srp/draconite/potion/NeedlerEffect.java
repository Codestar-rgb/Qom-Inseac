package com.srp.draconite.potion;

import net.minecraft.world.effect.MobEffectCategory;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;

public class NeedlerEffect
extends MobEffect {
    public NeedlerEffect() {
        super(MobEffectCategory.HARMFUL, 894752);
    }

    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide) {
            float damage = 2.0f + (float)amplifier * 0.5f;
            entity.hurt(entity.damageSources().magic(), damage);
        }
    }

    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        int interval = Math.max(10, 30 - amplifier * 3);
        return duration % interval == 0;
    }

    public static float getProjectileDamageMultiplier(int amplifier) {
        return 1.0f + 0.2f * (float)amplifier;
    }
}
