package com.srp.draconite.entity;

import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class EntityDraconiteBodyPart
extends Entity {
    private static final EntityDataAccessor<Float> DATA_HEALTH = SynchedEntityData.defineId(EntityDraconiteBodyPart.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<String> DATA_PART_NAME = SynchedEntityData.defineId(EntityDraconiteBodyPart.class, EntityDataSerializers.STRING);
    private EntityDraconite owner;
    private int hurtFlashTimer;

    public EntityDraconiteBodyPart(EntityType<?> entityType, Level level) {
        super(entityType, level);
        this.noPhysics = true;
    }

    protected void defineSynchedData() {
        this.entityData.define(DATA_HEALTH, 0.0f);
        this.entityData.define(DATA_PART_NAME, "unknown");
    }

    protected void readAdditionalSaveData(CompoundTag tag) {
        this.setPartName(tag.getString("PartName"));
        this.setHealth(tag.getFloat("Health"));
    }

    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putString("PartName", this.getPartName());
        tag.putFloat("Health", this.getHealth());
    }

    public float getHealth() {
        return (this.entityData.get(DATA_HEALTH)).floatValue();
    }

    public void setHealth(float health) {
        float clampedHealth = Math.max(0.0f, health);
        this.entityData.set(DATA_HEALTH, clampedHealth);
        if (clampedHealth <= 0.0f) {
            this.discard();
        }
    }

    @Nullable
    public EntityDraconite getOwner() {
        return this.owner;
    }

    public void setOwner(EntityDraconite owner) {
        this.owner = owner;
    }

    public String getPartName() {
        return (String)this.entityData.get(DATA_PART_NAME);
    }

    public void setPartName(String name) {
        this.entityData.set(DATA_PART_NAME, name);
    }

    public int getHurtFlashTimer() {
        return this.hurtFlashTimer;
    }

    public boolean hurt(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        }
        if (!this.isAlive()) {
            return false;
        }
        float newHealth = this.getHealth() - amount;
        this.setHealth(newHealth);
        this.hurtFlashTimer = 8;
        if (newHealth <= 0.0f) {
            this.discard();
        }
        return true;
    }

    public boolean isPushable() {
        return true;
    }

    public void tick() {
        super.tick();
        if (this.owner == null || !this.owner.isAlive() || this.owner.isRemoved()) {
            this.discard();
            return;
        }
        this.invulnerableTime = 0;
        if (this.hurtFlashTimer > 0) {
            --this.hurtFlashTimer;
        }
    }

    public boolean isInvulnerableTo(DamageSource source) {
        if (source.is(DamageTypeTags.IS_FIRE)) {
            return true;
        }
        return super.isInvulnerableTo(source);
    }

    public boolean isPickable() {
        return true;
    }

    public boolean canBeCollidedWith() {
        return false;
    }
}
