package com.srp.draconite.entity.ai;

import com.srp.draconite.entity.EntityDraconite;

import java.util.EnumSet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

public class DraconiteFlameSkillGoal
extends Goal {
    private final EntityDraconite draconite;
    private final int cooldownTicks;
    private final int initialDelay;
    private int cooldownTimer;
    private int skillTicks;
    private static final int SKILL_DURATION = 80;
    private static final int FIREBALL_INTERVAL = 8;

    public DraconiteFlameSkillGoal(EntityDraconite draconite, int cooldownTicks, int initialDelay) {
        this.draconite = draconite;
        this.cooldownTicks = cooldownTicks;
        this.initialDelay = initialDelay;
        this.cooldownTimer = initialDelay;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean canUse() {
        if (!this.draconite.isHeadAlive()) {
            return false;
        }
        if (this.draconite.isStunned()) {
            return false;
        }
        LivingEntity target = this.draconite.getTarget();
        if (target == null || !target.isAlive()) {
            return false;
        }
        double dist = this.draconite.distanceTo(target);
        if (dist > 80.0 || dist < 5.0) {
            return false;
        }
        if (this.cooldownTimer > 0) {
            --this.cooldownTimer;
            return false;
        }
        return true;
    }

    public boolean canContinueToUse() {
        return this.skillTicks > 0 && this.draconite.isHeadAlive() && !this.draconite.isStunned();
    }

    public void start() {
        this.skillTicks = 80;
        this.draconite.setAttacking(true);
        this.draconite.setAttackState(3);
        this.draconite.attackAnimationTimer = 80;
        this.draconite.triggerRainingFireballs(this.draconite.getTarget());
        this.draconite.playRoarSound();
        this.draconite.playShootSound();
    }

    public void stop() {
        this.skillTicks = 0;
        this.cooldownTimer = this.cooldownTicks;
        this.draconite.setAttacking(false);
        this.draconite.setAttackState(0);
    }

    public void tick() {
        --this.skillTicks;
        LivingEntity target = this.draconite.getTarget();
        if (target != null) {
            double hoverY = target.getY() + 12.0 + Math.sin((double)this.draconite.tickCount * 0.05) * 2.0;
            this.draconite.getMoveControl().setWantedPosition(target.getX(), hoverY, target.getZ(), 0.8);
            this.draconite.getLookControl().setLookAt(target, 30.0f, 30.0f);
        }
        if (this.skillTicks % 10 == 0 && this.skillTicks > 0 && target != null) {
            this.draconite.shootFireballAt(target);
        }
    }

    public boolean canBeUsedWhileContinuing() {
        return true;
    }
}

