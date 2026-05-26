package com.srp.draconite.potion;

import net.minecraft.world.effect.MobEffectCategory;

import java.util.UUID;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class CothEffect
extends MobEffect {
    private static final UUID SLOWDOWN_UUID = UUID.fromString("c1e4a7b2-3f8d-4b6e-9a0c-5d7e2f1b8a3e");

    public CothEffect() {
        super(MobEffectCategory.HARMFUL, 894752);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, SLOWDOWN_UUID.toString(), -0.05, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }

    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide) {
            float damage = 1.0f + (float)amplifier * 0.5f;
            entity.hurt(entity.damageSources().magic(), damage);
        }
    }

    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        int interval = Math.max(10, 40 - amplifier * 5);
        return duration % interval == 0;
    }
}
