package com.srp.draconite.entity;

import com.srp.draconite.init.ModEffects;
import com.srp.draconite.entity.EntityDraconite;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class EntityToxicCloud
extends Entity {
    private static final EntityDataAccessor<Float> DATA_RADIUS = SynchedEntityData.defineId(EntityToxicCloud.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> DATA_DURATION = SynchedEntityData.defineId(EntityToxicCloud.class, EntityDataSerializers.INT);
    private static final int WAIT_TIME = 10;
    private Entity owner;
    private MobEffect effect;
    private int effectDuration;
    private int effectAmplifier;
    private int ticksExisted;
    private float dragonBreathDamage = 0.0f;
    private float minimumDamage = 0.0f;
    private boolean hasFlameParticles = false;

    public EntityToxicCloud(EntityType<?> entityType, Level level) {
        super(entityType, level);
        this.noPhysics = true;
    }

    protected void defineSynchedData() {
        this.entityData.define(DATA_RADIUS, 3.5f);
        this.entityData.define(DATA_DURATION, 100);
    }

    protected void readAdditionalSaveData(CompoundTag tag) {
        this.setRadius(tag.getFloat("Radius"));
        this.setDuration(tag.getInt("Duration"));
        this.effectDuration = tag.getInt("EffectDuration");
        this.effectAmplifier = tag.getInt("EffectAmplifier");
        this.dragonBreathDamage = tag.getFloat("DragonBreathDamage");
        this.minimumDamage = tag.getFloat("MinimumDamage");
        this.hasFlameParticles = tag.getBoolean("HasFlameParticles");
    }

    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putFloat("Radius", this.getRadius());
        tag.putInt("Duration", this.getDuration());
        tag.putInt("EffectDuration", this.effectDuration);
        tag.putInt("EffectAmplifier", this.effectAmplifier);
        tag.putFloat("DragonBreathDamage", this.dragonBreathDamage);
        tag.putFloat("MinimumDamage", this.minimumDamage);
        tag.putBoolean("HasFlameParticles", this.hasFlameParticles);
    }

    public float getRadius() {
        return (this.entityData.get(DATA_RADIUS)).floatValue();
    }

    public void setRadius(float radius) {
        this.entityData.set(DATA_RADIUS, radius);
    }

    public int getDuration() {
        return this.entityData.get(DATA_DURATION);
    }

    public void setDuration(int duration) {
        this.entityData.set(DATA_DURATION, duration);
    }

    @Nullable
    public Entity getOwner() {
        return this.owner;
    }

    public void setOwner(Entity owner) {
        this.owner = owner;
    }

    public void setEffect(MobEffect effect, int duration, int amplifier) {
        this.effect = effect;
        this.effectDuration = duration;
        this.effectAmplifier = amplifier;
    }

    public void setDragonBreathDamage(float damage) {
        this.dragonBreathDamage = damage;
    }

    public void setMinimumDamage(float damage) {
        this.minimumDamage = damage;
    }

    public void setHasFlameParticles(boolean has) {
        this.hasFlameParticles = has;
    }

    public void tick() {
        super.tick();
        ++this.ticksExisted;
        int duration = this.getDuration();
        if (this.ticksExisted >= duration) {
            this.discard();
            return;
        }
        float currentRadius = this.getRadius();
        if (!this.level().isClientSide && this.ticksExisted > 10 && this.ticksExisted % 5 == 0) {
            List<LivingEntity> nearbyEntities = this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate((double)currentRadius), entity -> entity.distanceTo(this) <= currentRadius && entity != this.owner && !(entity instanceof EntityDraconite));
            for (LivingEntity entity2 : nearbyEntities) {
                if (this.effect != null) {
                    entity2.addEffect(new MobEffectInstance(this.effect, this.effectDuration, this.effectAmplifier));
                } else {
                    entity2.addEffect(new MobEffectInstance(ModEffects.COTH.get(), 1200, 3));
                }
                entity2.addEffect(new MobEffectInstance(ModEffects.BLEED.get(), 300, 0));
                if (this.dragonBreathDamage > 0.0f) {
                    entity2.hurt(entity2.damageSources().dragonBreath(), this.dragonBreathDamage);
                }
                if (!(this.minimumDamage > 0.0f)) continue;
                entity2.hurt(entity2.damageSources().generic(), this.minimumDamage);
            }
        }
        if (this.level().isClientSide) {
            for (int i = 0; i < 4; ++i) {
                double px = this.getX() + (this.random.nextDouble() - 0.5) * (double)currentRadius * 2.0;
                double py = this.getY() + this.random.nextDouble() * 1.0;
                double pz = this.getZ() + (this.random.nextDouble() - 0.5) * (double)currentRadius * 2.0;
                this.level().addParticle(ParticleTypes.DRAGON_BREATH, px, py, pz, 0.0, 0.05, 0.0);
            }
            if (this.hasFlameParticles && this.random.nextInt(3) == 0) {
                double px = this.getX() + (this.random.nextDouble() - 0.5) * (double)currentRadius;
                double py = this.getY() + this.random.nextDouble() * 0.5;
                double pz = this.getZ() + (this.random.nextDouble() - 0.5) * (double)currentRadius;
                this.level().addParticle(ParticleTypes.FLAME, px, py, pz, 0.0, 0.03, 0.0);
            }
            if (this.random.nextInt(5) == 0) {
                this.level().addParticle(ParticleTypes.DRAGON_BREATH, this.getX(), this.getY() + 0.3, this.getZ(), 0.0, 0.1, 0.0);
            }
        }
    }

    public boolean isPushable() {
        return false;
    }

    public boolean canBeCollidedWith() {
        return false;
    }

    public boolean isInvulnerableTo(DamageSource source) {
        return true;
    }
}
