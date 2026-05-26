package com.srp.draconite.potion;

import net.minecraft.world.effect.MobEffectCategory;

import java.util.UUID;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class RageEffect
extends MobEffect {
    private static final UUID SPEED_UUID = UUID.fromString("d3b41f7a-6c8e-4e1a-9b5d-0f3e8a2c1b4d");
    private static final UUID ATTACK_DAMAGE_UUID = UUID.fromString("a7c52e9f-3d1b-4a8e-8c6d-2f5b7e9a1d3c");

    public RageEffect() {
        super(MobEffectCategory.BENEFICIAL, 894752);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, SPEED_UUID.toString(), 0.2, AttributeModifier.Operation.MULTIPLY_TOTAL);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, ATTACK_DAMAGE_UUID.toString(), 3.0, AttributeModifier.Operation.ADDITION);
    }
}
