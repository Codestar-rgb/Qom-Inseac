package com.srp.draconite.entity;

import com.srp.draconite.init.ModEffects;
import com.srp.draconite.init.ModSounds;
import com.srp.draconite.entity.EntityDraconite;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.UseAnim;

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
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class EntityOrbScary
extends Entity {
    private static final EntityDataAccessor<Integer> DATA_FUSE_STATE = SynchedEntityData.defineId(EntityOrbScary.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_FUSE_TIMER = SynchedEntityData.defineId(EntityOrbScary.class, EntityDataSerializers.INT);
    private static final int WAIT_DURATION = 40;
    private static final int ACTIVE_DURATION = 200;
    private static final int DYING_DURATION = 35;
    private static final double EFFECT_RADIUS = 6.0;
    private static final int COTH_DURATION = 1200;
    private static final int COTH_AMPLIFIER = 3;
    private static final int ITEM_COOLDOWN_TICKS = 400;
    private static final int HUNGER_LEVEL = 40;
    private static final int MIN_FOOD_ROTTED = 1;
    private static final int MAX_FOOD_ROTTED = 10;
    private static final float MIN_DAMAGE = 7.0f;
    private Entity owner;
    private float bobPhase;
    private boolean followOwner = true;
    private double poosX;
    private double poosY;
    private double poosZ;

    public EntityOrbScary(EntityType<?> entityType, Level level) {
        super(entityType, level);
        this.noPhysics = true;
    }

    protected void defineSynchedData() {
        this.entityData.define(DATA_FUSE_STATE, 0);
        this.entityData.define(DATA_FUSE_TIMER, 40);
    }

    protected void readAdditionalSaveData(CompoundTag tag) {
        this.setFuseState(tag.getInt("FuseState"));
        this.setFuseTimer(tag.getInt("FuseTimer"));
        this.followOwner = tag.getBoolean("FollowOwner");
    }

    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putInt("FuseState", this.getFuseState());
        tag.putInt("FuseTimer", this.getFuseTimer());
        tag.putBoolean("FollowOwner", this.followOwner);
    }

    public int getFuseState() {
        return this.entityData.get(DATA_FUSE_STATE);
    }

    public void setFuseState(int state) {
        this.entityData.set(DATA_FUSE_STATE, state);
    }

    public int getFuseTimer() {
        return this.entityData.get(DATA_FUSE_TIMER);
    }

    public void setFuseTimer(int timer) {
        this.entityData.set(DATA_FUSE_TIMER, timer);
    }

    @Nullable
    public Entity getOwner() {
        return this.owner;
    }

    public void setOwner(Entity owner) {
        this.owner = owner;
    }

    public void setFollowOwner(boolean follow) {
        this.followOwner = follow;
    }

    public void tick() {
        super.tick();
        this.bobPhase += 0.1f;
        if (this.tickCount == 1 && !this.level().isClientSide) {
            this.level().playSound(null, this.getX(), this.getY(), this.getZ(), ModSounds.ORB_SUMMON.get(), SoundSource.HOSTILE, 1.0f, 1.0f);
            this.level().playSound(null, this.getX(), this.getY(), this.getZ(), ModSounds.ORB_IDLE.get(), SoundSource.HOSTILE, 1.0f, 1.0f);
        }
        if (this.level().isClientSide) {
            this.tickClientEffects();
            return;
        }
        int fuseState = this.getFuseState();
        int fuseTimer = this.getFuseTimer();
        switch (fuseState) {
            case 0: {
                if (--fuseTimer <= 0) {
                    this.setFuseState(1);
                    this.setFuseTimer(200);
                    break;
                }
                this.setDeltaMovement(0.0, Math.sin(this.bobPhase) * 0.03, 0.0);
                if (this.owner != null && this.owner.isAlive() && this.followOwner) {
                    this.poosX = this.owner.getX();
                    this.poosY = this.owner.getY();
                    this.poosZ = this.owner.getZ();
                    this.setPos(this.owner.getX(), this.owner.getY() - this.random.nextDouble() * 0.1, this.owner.getZ());
                    break;
                }
                this.poosX = this.getX();
                this.poosY = this.getY();
                this.poosZ = this.getZ();
                if (!this.followOwner || this.owner != null && this.owner.isAlive()) break;
                this.discard();
                return;
            }
            case 1: {
                if (--fuseTimer <= 0) {
                    this.dyingBurst(true, 3);
                    return;
                }
                if (this.owner != null && this.owner.isAlive() && this.followOwner) {
                    this.setPos(this.owner.getX(), this.owner.getY() - this.random.nextDouble() * 0.1, this.owner.getZ());
                } else {
                    this.setPos(this.poosX, this.poosY - this.random.nextDouble() * 0.1, this.poosZ);
                    if (this.followOwner && (this.owner == null || !this.owner.isAlive())) {
                        this.discard();
                        return;
                    }
                }
                if (this.tickCount % 30 == 0) {
                    this.applyScaryOrbEffect();
                }
                if (this.tickCount % 40 != 0) break;
                this.level().playSound(null, this.getX(), this.getY(), this.getZ(), ModSounds.ORB_IDLE.get(), SoundSource.HOSTILE, 1.0f, 0.9f + this.random.nextFloat() * 0.2f);
                break;
            }
            case 2: {
                if (--fuseTimer > 0) break;
                this.selfExplode(true);
                return;
            }
        }
        this.setFuseTimer(fuseTimer);
    }

    private void applyScaryOrbEffect() {
        AABB searchBox = this.getBoundingBox().inflate(6.0);
        List<LivingEntity> nearbyEntities = this.level().getEntitiesOfClass(LivingEntity.class, searchBox, entity -> (double)entity.distanceTo(this) <= 6.0 && entity != this.owner && !(entity instanceof EntityDraconite) && entity.isAlive());
        for (LivingEntity entity2 : nearbyEntities) {
            entity2.addEffect(new MobEffectInstance(ModEffects.COTH.get(), 1200, 3, false, true));
            if (entity2 instanceof Player) {
                Player player = (Player)entity2;
                if (player.isCreative() || player.isSpectator()) continue;
                for (ItemStack stack : player.getInventory().items) {
                    if (stack.isEmpty()) continue;
                    Item item = stack.getItem();
                    if (player.getCooldowns().getCooldownPercent(item, 0.0f) != 0.0f) continue;
                    player.getCooldowns().addCooldown(item, 400);
                }
                for (ItemStack stack : player.getInventory().armor) {
                    if (stack.isEmpty()) continue;
                    player.getCooldowns().addCooldown(stack.getItem(), 400);
                }
                ItemStack offhand = player.getOffhandItem();
                if (!offhand.isEmpty()) {
                    player.getCooldowns().addCooldown(offhand.getItem(), 400);
                }
                entity2.addEffect(new MobEffectInstance(MobEffects.HUNGER, 400, 2));
                FoodData foodData = player.getFoodData();
                for (int i = 0; i < 40; ++i) {
                    foodData.addExhaustion(1.0f);
                }
                int foodToRot = 1 + this.random.nextInt(10);
                int rotted = 0;
                for (int i = 0; i < player.getInventory().items.size() && rotted < foodToRot; ++i) {
                    ItemStack stack = (ItemStack)player.getInventory().items.get(i);
                    if (stack.isEmpty() || stack.getItem().getUseAnimation(stack) != UseAnim.EAT) continue;
                    stack.shrink(1);
                    ++rotted;
                }
                this.level().playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.ORB_S.get(), SoundSource.HOSTILE, 1.0f, 1.0f);
                continue;
            }
            if (entity2 instanceof EntityDraconite) continue;
            float damage = 3.5f;
            Entity entity3 = this.owner;
            if (entity3 instanceof LivingEntity) {
                LivingEntity livingOwner = entity3;
                entity2.hurt(entity2.damageSources().indirectMobAttack(this, livingOwner), damage);
            } else {
                entity2.hurt(entity2.damageSources().generic(), damage);
            }
            entity2.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 0, false, true));
        }
    }

    public void dyingBurst(boolean withDamage, int burstRadius) {
        this.setFuseState(2);
        this.setFuseTimer(35);
        if (withDamage && !this.level().isClientSide) {
            float burstRadiusF = burstRadius;
            List<LivingEntity> nearbyEntities = this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate((double)burstRadiusF), entity -> entity.distanceTo(this) <= burstRadiusF && entity != this.owner && !(entity instanceof EntityDraconite) && entity.isAlive());
            for (LivingEntity entity2 : nearbyEntities) {
                double kbZ;
                double kbX;
                double kbDist;
                entity2.addEffect(new MobEffectInstance(ModEffects.COTH.get(), 200, 2));
                entity2.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 3));
                float damage = 14.0f;
                Entity entity3 = this.owner;
                if (entity3 instanceof LivingEntity) {
                    LivingEntity livingOwner = entity3;
                    entity2.hurt(entity2.damageSources().indirectMobAttack(this, livingOwner), damage);
                } else {
                    entity2.hurt(entity2.damageSources().generic(), damage);
                }
                if (!((kbDist = Math.sqrt((kbX = entity2.getX() - this.getX()) * kbX + (kbZ = entity2.getZ() - this.getZ()) * kbZ)) > 0.01)) continue;
                entity2.knockback(3.0, -kbX / kbDist, -kbZ / kbDist);
            }
        }
        this.level().playSound(null, this.getX(), this.getY(), this.getZ(), ModSounds.ORB_S.get(), SoundSource.HOSTILE, 1.0f, 1.0f);
    }

    public void selfExplode(boolean withParticles) {
        Level level = this.level();
        if (level instanceof ServerLevel) {
            ServerLevel serverLevel = (ServerLevel)level;
            if (withParticles) {
                serverLevel.sendParticles(ParticleTypes.EXPLOSION, this.getX(), this.getY() + 0.5, this.getZ(), 3, 0.5, 0.5, 0.5, 0.1);
                serverLevel.sendParticles(ParticleTypes.DRAGON_BREATH, this.getX(), this.getY() + 0.5, this.getZ(), 20, 1.0, 0.3, 1.0, 0.05);
            }
        }
        this.discard();
    }

    private void tickClientEffects() {
        int fuseState = this.getFuseState();
        if (fuseState == 0) {
            if (this.random.nextInt(4) == 0) {
                this.level().addParticle(ParticleTypes.PORTAL, this.getX() + (this.random.nextDouble() - 0.5) * 0.5, this.getY() + 0.2, this.getZ() + (this.random.nextDouble() - 0.5) * 0.5, 0.0, 0.02, 0.0);
            }
        } else if (fuseState == 1) {
            for (int i = 0; i < 2; ++i) {
                this.level().addParticle(ParticleTypes.DRAGON_BREATH, this.getX() + (this.random.nextDouble() - 0.5) * 0.8, this.getY() + this.random.nextDouble() * 0.5, this.getZ() + (this.random.nextDouble() - 0.5) * 0.8, (this.random.nextDouble() - 0.5) * 0.05, 0.02, (this.random.nextDouble() - 0.5) * 0.05);
            }
            if (this.random.nextInt(6) == 0) {
                this.level().addParticle(ParticleTypes.SQUID_INK, this.getX(), this.getY() + 0.3, this.getZ(), 0.0, 0.02, 0.0);
            }
        } else if (fuseState == 2) {
            for (int i = 0; i < 5; ++i) {
                this.level().addParticle(ParticleTypes.EXPLOSION, this.getX() + (this.random.nextDouble() - 0.5) * 1.5, this.getY() + this.random.nextDouble(), this.getZ() + (this.random.nextDouble() - 0.5) * 1.5, 0.0, 0.05, 0.0);
            }
        }
    }

    public float getFlashIntensity(float partialTicks) {
        int state = this.getFuseState();
        if (state == 1) {
            return 0.3f + (float)Math.sin((double)this.tickCount * 0.2 + (double)partialTicks) * 0.2f;
        }
        if (state == 2) {
            return 0.5f + (float)Math.sin((double)this.tickCount * 0.5 + (double)partialTicks) * 0.3f;
        }
        return 0.1f;
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
