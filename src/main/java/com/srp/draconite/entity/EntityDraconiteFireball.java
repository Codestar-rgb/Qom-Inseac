package com.srp.draconite.entity;

import com.srp.draconite.init.ModSounds;
import com.srp.draconite.entity.EntityDraconite;
import net.minecraft.world.entity.projectile.Fireball;

import java.util.List;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

/**
 * Draconite fireball - a pure fireball projectile.
 * Does NOT spawn toxic clouds or apply poison effects.
 * It simply deals fire damage and sets targets on fire,
 * matching the original SRP Heblu's fireball behavior.
 */
public class EntityDraconiteFireball
extends Fireball {
    private static final float BURST_RADIUS = 4.0f;
    private static final float BURST_DAMAGE = 70.0f;
    private static final int FIRE_DURATION = 8;

    public EntityDraconiteFireball(EntityType<? extends Fireball> entityType, Level level, LivingEntity owner, double accelX, double accelY, double accelZ) {
        super(entityType, level, owner, accelX, accelY, accelZ);
        this.setOwner(owner);
    }

    public EntityDraconiteFireball(EntityType<? extends Fireball> entityType, Level level) {
        super(entityType, level);
    }

    public void tick() {
        super.tick();
        if (this.level().isClientSide) {
            this.level().addParticle(ParticleTypes.FLAME, this.getX() + (this.random.nextDouble() - 0.5) * 0.3, this.getY() + (this.random.nextDouble() - 0.5) * 0.3, this.getZ() + (this.random.nextDouble() - 0.5) * 0.3, 0.0, 0.0, 0.0);
        }
    }

    protected void onHit(HitResult result) {
        if (!this.level().isClientSide) {
            this.createBurstExplosion();
            this.discard();
        }
    }

    protected void onHitEntity(EntityHitResult result) {
        if (!this.level().isClientSide) {
            this.createBurstExplosion();
            this.discard();
        }
    }

    private void createBurstExplosion() {
        if (this.level().isClientSide) {
            return;
        }
        Level level = this.level();
        if (level instanceof ServerLevel) {
            ServerLevel serverLevel = (ServerLevel)level;
            serverLevel.sendParticles(ParticleTypes.EXPLOSION_EMITTER, this.getX(), this.getY() + 0.5, this.getZ(), 3, 0.5, 0.5, 0.5, 0.1);
            serverLevel.sendParticles(ParticleTypes.FLAME, this.getX(), this.getY() + 0.5, this.getZ(), 40, 1.2, 0.5, 1.2, 0.2);
        }
        this.level().playSound(null, this.getX(), this.getY(), this.getZ(), ModSounds.MOB_EXPLOSION.get(), SoundSource.HOSTILE, 3.0f, 0.8f);
        AABB burstBox = new AABB(this.getX() - BURST_RADIUS, this.getY() - BURST_RADIUS, this.getZ() - BURST_RADIUS, this.getX() + BURST_RADIUS, this.getY() + BURST_RADIUS, this.getZ() + BURST_RADIUS);
        List<LivingEntity> entities = this.level().getEntitiesOfClass(LivingEntity.class, burstBox, entity -> entity.distanceTo(this) <= BURST_RADIUS && !(entity instanceof EntityDraconite) && entity.isAlive());
        Entity owner = this.getOwner();
        for (LivingEntity target : entities) {
            float damage = BURST_DAMAGE;
            if (owner instanceof LivingEntity) {
                LivingEntity livingOwner = (LivingEntity)owner;
                target.hurt(target.damageSources().mobProjectile(this, livingOwner), damage);
            } else {
                target.hurt(target.damageSources().mobProjectile(this, null), damage);
            }
            target.setSecondsOnFire(FIRE_DURATION);
        }
    }

    public boolean isPushable() {
        return false;
    }

    public boolean hurt(DamageSource source, float amount) {
        return false;
    }

    public boolean isInvulnerableTo(DamageSource source) {
        return true;
    }
}
