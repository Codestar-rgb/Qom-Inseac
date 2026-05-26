package com.srp.draconite.entity.ai;

import com.srp.draconite.entity.EntityDraconite;

import java.util.EnumSet;
import java.util.List;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.AABB;

public class DraconiteMeleeAOEGoal
extends Goal {
    private final EntityDraconite draconite;
    private final double speedModifier;
    private final float minRange;
    private final float maxRange;
    private int attackCooldown;
    private int pathRecalcTimer;
    private static final int ATTACK_COOLDOWN_TICKS = 20;

    public DraconiteMeleeAOEGoal(EntityDraconite draconite, double speedModifier, float minRange, float maxRange) {
        this.draconite = draconite;
        this.speedModifier = speedModifier;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean canUse() {
        LivingEntity target = this.draconite.getTarget();
        if (target == null || !target.isAlive()) {
            return false;
        }
        if (this.draconite.isFlying()) {
            return false;
        }
        double distance = this.draconite.distanceTo(target);
        return distance <= (double)this.maxRange * 1.5;
    }

    public boolean canContinueToUse() {
        LivingEntity target = this.draconite.getTarget();
        if (target == null || !target.isAlive()) {
            return false;
        }
        if (this.draconite.isFlying()) {
            return false;
        }
        double distance = this.draconite.distanceTo(target);
        return distance <= (double)this.maxRange * 2.0;
    }

    public void start() {
        this.attackCooldown = 0;
        this.pathRecalcTimer = 0;
    }

    public void stop() {
        this.draconite.setAggressive(false);
        this.draconite.setAttackState(0);
        this.draconite.getNavigation().stop();
    }

    public void tick() {
        LivingEntity target = this.draconite.getTarget();
        if (target == null) {
            return;
        }
        double distance = this.draconite.distanceTo(target);
        this.draconite.getLookControl().setLookAt(target, 30.0f, 30.0f);
        if (distance > (double)this.minRange) {
            --this.pathRecalcTimer;
            if (this.pathRecalcTimer <= 0) {
                this.draconite.getNavigation().moveTo(target, this.speedModifier);
                this.pathRecalcTimer = 5;
            }
        } else {
            this.draconite.getNavigation().stop();
        }
        if (distance <= (double)this.maxRange) {
            --this.attackCooldown;
            if (this.attackCooldown <= 0) {
                this.performAOEAttack();
                this.attackCooldown = 20;
                this.draconite.setAggressive(true);
                this.draconite.setAttackState(1);
                this.draconite.setAttacking(true);
                this.draconite.attackAnimationTimer = 20;
            }
        }
    }

    private void performAOEAttack() {
        AABB attackBox = this.draconite.getBoundingBox().inflate((double)this.maxRange);
        List<LivingEntity> nearbyEntities = this.draconite.level().getEntitiesOfClass(LivingEntity.class, attackBox, entity -> entity != this.draconite && entity.isAlive() && this.draconite.distanceTo(entity) <= this.maxRange && !this.isAllied(entity) && this.draconite.hasLineOfSight(entity));
        float attackDamage = (float)this.draconite.getAttributeValue(Attributes.ATTACK_DAMAGE);
        for (LivingEntity entity2 : nearbyEntities) {
            double kbZ;
            double distanceRatio = this.draconite.distanceTo(entity2) / this.maxRange;
            float damageMultiplier = (float)(1.0 - distanceRatio * 0.5);
            entity2.hurt(this.draconite.damageSources().mobAttack(this.draconite), attackDamage * damageMultiplier);
            double kbX = entity2.getX() - this.draconite.getX();
            double kbDist = Math.sqrt(kbX * kbX + (kbZ = entity2.getZ() - this.draconite.getZ()) * kbZ);
            if (!(kbDist > 0.01)) continue;
            entity2.knockback((double)(2.0f * damageMultiplier), -kbX / kbDist, -kbZ / kbDist);
        }
    }

    private boolean isAllied(LivingEntity entity) {
        if (entity instanceof EntityDraconite) {
            return true;
        }
        return this.draconite.isAlliedTo(entity);
    }

    public boolean canBeUsedWhileContinuing() {
        return true;
    }
}

