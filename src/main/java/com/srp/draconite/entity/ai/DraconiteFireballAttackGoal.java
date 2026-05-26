package com.srp.draconite.entity.ai;

import com.srp.draconite.entity.EntityDraconite;
import com.srp.draconite.init.ModEffects;

import java.util.EnumSet;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

public class DraconiteFireballAttackGoal
extends Goal {
    private final EntityDraconite draconite;
    private int attackTimer;
    private static final int CHARGE_TIME = 20;
    private static final int FIRE_COOLDOWN = -60;
    private static final int RAIN_COOLDOWN = -45;
    private static final double ATTACK_RANGE_SQ = 4096.0;

    public DraconiteFireballAttackGoal(EntityDraconite draconite) {
        this.draconite = draconite;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean canUse() {
        return this.draconite.isFlying() && this.draconite.getTarget() != null && this.draconite.getTarget().isAlive() && this.draconite.isHeadAlive();
    }

    public boolean canContinueToUse() {
        return this.canUse() && this.draconite.getTarget() != null && this.draconite.getTarget().isAlive();
    }

    public void start() {
        this.attackTimer = 0;
    }

    public void stop() {
        this.draconite.setAttacking(false);
        this.draconite.setAttackState(0);
        this.attackTimer = 0;
    }

    public void tick() {
        LivingEntity target = this.draconite.getTarget();
        if (target == null) {
            return;
        }
        this.draconite.getLookControl().setLookAt(target, 30.0f, 30.0f);
        double distance = this.draconite.distanceToSqr(target);
        if (distance > 256.0) {
            this.draconite.getMoveControl().setWantedPosition(target.getX(), target.getY() + 8.0, target.getZ(), 1.0);
        } else if (distance < 64.0) {
            double dz;
            double dx = this.draconite.getX() - target.getX();
            double dist = Math.sqrt(dx * dx + (dz = this.draconite.getZ() - target.getZ()) * dz);
            if (dist > 0.01) {
                this.draconite.getMoveControl().setWantedPosition(this.draconite.getX() + dx / dist * 10.0, this.draconite.getY() + 3.0, this.draconite.getZ() + dz / dist * 10.0, 1.0);
            }
        } else {
            double hoverY = target.getY() + 8.0 + Math.sin((double)this.draconite.tickCount * 0.03) * 2.0;
            this.draconite.getMoveControl().setWantedPosition(target.getX() + Math.sin((double)this.draconite.tickCount * 0.02) * 5.0, hoverY, target.getZ() + Math.cos((double)this.draconite.tickCount * 0.02) * 5.0, 0.5);
        }
        if (distance < 4096.0 && this.draconite.getSensing().hasLineOfSight(target)) {
            ++this.attackTimer;
            if (this.draconite.hasEffect(ModEffects.RAGE.get())) {
                ++this.attackTimer;
            }
            this.draconite.setAttacking(this.attackTimer > 10);
            if (this.attackTimer >= 20) {
                if (target.isOnGround() && this.draconite.getRandom().nextInt(3) == 0) {
                    this.draconite.triggerRainingFireballs(target);
                    this.draconite.playShootSound();
                    this.attackTimer = -45;
                } else {
                    this.draconite.shootFireballAt(target);
                    this.attackTimer = -60;
                }
            }
        } else if (this.attackTimer > 0) {
            --this.attackTimer;
        }
    }

    public boolean canBeUsedWhileContinuing() {
        return true;
    }
}

