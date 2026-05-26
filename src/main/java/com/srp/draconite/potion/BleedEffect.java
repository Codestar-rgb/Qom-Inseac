package com.srp.draconite.potion;

import net.minecraft.world.effect.MobEffectCategory;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;

public class BleedEffect
extends MobEffect {
    public BleedEffect() {
        super(MobEffectCategory.HARMFUL, 0x8B0000);
    }

    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.level().isClientSide) {
            return;
        }
        float damage = 1.0f + 0.5f * (float)amplifier;
        entity.hurt(entity.damageSources().magic(), damage);
    }

    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        int interval = Math.max(10, 40 - amplifier * 5);
        return duration % interval == 0;
    }
}
