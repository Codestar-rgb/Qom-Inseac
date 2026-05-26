package com.srp.draconite.entity;

import com.srp.draconite.init.ModSounds;

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
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class EntityOrbBoom
extends Entity {
    private static final EntityDataAccessor<Integer> DATA_SELFE = SynchedEntityData.defineId(EntityOrbBoom.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_FUSE = SynchedEntityData.defineId(EntityOrbBoom.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_WAIT_START = SynchedEntityData.defineId(EntityOrbBoom.class, EntityDataSerializers.INT);
    private static final int DEFAULT_FUSE = 7;
    private static final int DEFAULT_WAIT_START = 40;
    private static final float BURST_MIN_DAMAGE = 70.0f;
    private static final float BURST_RADIUS = 6.0f;
    private static final int COTH_DURATION = 200;
    private static final int COTH_AMPLIFIER = 1;
    private Entity owner;
    private int lastActiveTime;
    private int timeSinceIgnited;
    private boolean hasBurst = false;
    private float alpha = 1.0f;

    public EntityOrbBoom(EntityType<?> entityType, Level level) {
        super(entityType, level);
        this.noPhysics = true;
        this.setNoGravity(true);
    }

    protected void defineSynchedData() {
        this.entityData.define(DATA_SELFE, -1);
        this.entityData.define(DATA_FUSE, 7);
        this.entityData.define(DATA_WAIT_START, 40);
    }

    protected void readAdditionalSaveData(CompoundTag tag) {
        this.setSelfeState(tag.getInt("SelfeState"));
        this.setFuseState(tag.getInt("FuseState"));
        this.setStartState(tag.getInt("StartState"));
        this.hasBurst = tag.getBoolean("HasBurst");
    }

    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putInt("SelfeState", this.getSelfeState());
        tag.putInt("FuseState", this.getFuseState());
        tag.putInt("StartState", this.getStartState());
        tag.putBoolean("HasBurst", this.hasBurst);
    }

    public int getSelfeState() {
        return this.entityData.get(DATA_SELFE);
    }

    public void setSelfeState(int state) {
        this.entityData.set(DATA_SELFE, state);
    }

    public int getFuseState() {
        return this.entityData.get(DATA_FUSE);
    }

    public void setFuseState(int state) {
        this.entityData.set(DATA_FUSE, state);
    }

    public int getStartState() {
        return this.entityData.get(DATA_WAIT_START);
    }

    public void setStartState(int state) {
        this.entityData.set(DATA_WAIT_START, state);
    }

    @Nullable
    public Entity getOwner() {
        return this.owner;
    }

    public void setOwner(Entity owner) {
        this.owner = owner;
    }

    public void setTargetDirection(Vec3 direction) {
        Vec3 normalized = direction.normalize();
        this.setDeltaMovement(normalized.scale(0.8));
    }

    public void tick() {
        super.tick();
        if (this.level().isClientSide) {
            this.tickClientEffects();
            return;
        }
        if (this.tickCount > this.getStartState()) {
            this.setSelfeState(1);
            ++this.timeSinceIgnited;
            if (this.timeSinceIgnited >= this.getFuseState()) {
                if (!this.hasBurst) {
                    this.selfExplode();
                } else {
                    this.alpha -= 0.2f;
                    if (this.alpha <= 0.0f) {
                        this.discard();
                    }
                }
            }
            this.setDeltaMovement(Vec3.ZERO);
        }
        if (this.getSelfeState() == -1 && this.tickCount % 10 == 0 && this.tickCount > 10) {
            this.orbDoing();
        }
    }

    private void orbDoing() {
        float radius = 3.0f;
        AABB box = this.getBoundingBox().inflate((double)radius);
        List<LivingEntity> entities = this.level().getEntitiesOfClass(LivingEntity.class, box, e -> e.distanceTo(this) <= radius && !(e instanceof EntityDraconite) && e.isAlive());
        for (LivingEntity entity : entities) {
            Entity entity2 = this.owner;
            if (entity2 instanceof LivingEntity) {
                LivingEntity livingOwner = entity2;
                entity.hurt(entity.damageSources().mobAttack(livingOwner), 10.0f);
                continue;
            }
            entity.hurt(entity.damageSources().generic(), 10.0f);
        }
    }

    protected void selfExplode() {
        this.hasBurst = true;
        this.setSelfeState(2);
        AABB box = this.getBoundingBox().inflate(6.0);
        List<LivingEntity> entities = this.level().getEntitiesOfClass(LivingEntity.class, box, e -> e.distanceTo(this) <= 6.0f && !(e instanceof EntityDraconite) && e.isAlive());
        for (LivingEntity entity : entities) {
            double dz;
            entity.hurt(entity.damageSources().generic(), 70.0f);
            entity.addEffect(new MobEffectInstance(ModEffects.COTH.get(), 200, 1));
            double dx = entity.getX() - this.getX();
            double dist = Math.sqrt(dx * dx + (dz = entity.getZ() - this.getZ()) * dz);
            if (!(dist > 0.01)) continue;
            entity.knockback(2.0, -dx / dist, -dz / dist);
        }
        Level level = this.level();
        if (level instanceof ServerLevel) {
            ServerLevel serverLevel = (ServerLevel)level;
            serverLevel.sendParticles(ParticleTypes.EXPLOSION_EMITTER, this.getX(), this.getY() + 0.5, this.getZ(), 3, 0.5, 0.5, 0.5, 0.1);
            serverLevel.sendParticles(ParticleTypes.DRAGON_BREATH, this.getX(), this.getY() + 0.5, this.getZ(), 40, 1.2000000000000002, 0.3, 1.2000000000000002, 0.05);
        }
        this.level().playSound(null, this.getX(), this.getY(), this.getZ(), ModSounds.ORB_E.get(), SoundSource.HOSTILE, 3.0f, 1.0f);
    }

    private void tickClientEffects() {
        if (this.getSelfeState() >= 1) {
            for (int i = 0; i < 3; ++i) {
                this.level().addParticle(ParticleTypes.DRAGON_BREATH, this.getX() + (this.random.nextDouble() - 0.5) * 1.5, this.getY() + this.random.nextDouble(), this.getZ() + (this.random.nextDouble() - 0.5) * 1.5, (this.random.nextDouble() - 0.5) * 0.1, 0.05, (this.random.nextDouble() - 0.5) * 0.1);
            }
            if (this.random.nextInt(3) == 0) {
                this.level().addParticle(ParticleTypes.FLAME, this.getX(), this.getY() + 0.3, this.getZ(), 0.0, 0.05, 0.0);
            }
        }
    }

    public float getFlashIntensity(float partialTicks) {
        return ((float)this.lastActiveTime + (float)(this.timeSinceIgnited - this.lastActiveTime) * partialTicks * 5.0f) / (float)Math.max(1, this.getFuseState() - 2);
    }

    public float getAlpha() {
        return this.alpha;
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

    public void playerTouch(Entity entity) {
    }
}
