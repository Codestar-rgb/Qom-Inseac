package com.srp.draconite.entity.ai;

import com.srp.draconite.entity.EntityDraconite;
import com.srp.draconite.entity.EntityOrbScary;
import com.srp.draconite.entity.EntityOrbBoom;
import com.srp.draconite.init.ModEntityTypes;

import java.util.EnumSet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

public class DraconiteOrbAttackGoal
extends Goal {
    private final EntityDraconite draconite;
    private int cooldownTimer;
    private int orbBarrageTicks;
    private boolean isBarraging;
    private static final int COOLDOWN_TICKS = 200;
    private static final int BARRAGE_DURATION = 60;
    private static final int INITIAL_DELAY = 100;
    private static final int MAX_SCORB_PER_BARRAGE = 3;
    private static final int MAX_BOOM_PER_BARRAGE = 2;
    private static final double SCORB_SPAWN_RADIUS = 3.0;

    public DraconiteOrbAttackGoal(EntityDraconite draconite) {
        this.draconite = draconite;
        this.setFlags(EnumSet.of(Goal.Flag.LOOK));
        this.cooldownTimer = 100;
    }

    public boolean canUse() {
        LivingEntity target = this.draconite.getTarget();
        if (target == null || !target.isAlive()) {
            return false;
        }
        if (this.cooldownTimer > 0) {
            --this.cooldownTimer;
            return false;
        }
        double distance = this.draconite.distanceTo(target);
        return distance >= 5.0 && distance <= 64.0;
    }

    public boolean canContinueToUse() {
        return this.isBarraging && this.draconite.getTarget() != null;
    }

    public void start() {
        this.isBarraging = true;
        this.orbBarrageTicks = 60;
        this.draconite.setAttacking(true);
        this.draconite.setAttackState(5);
        this.draconite.attackAnimationTimer = 60;
    }

    public void stop() {
        this.isBarraging = false;
        this.orbBarrageTicks = 0;
        this.cooldownTimer = 200;
        this.draconite.setAttacking(false);
        if (this.draconite.getAttackState() == 5) {
            this.draconite.setAttackState(0);
        }
    }

    public void tick() {
        LivingEntity target = this.draconite.getTarget();
        if (target == null) {
            this.stop();
            return;
        }
        this.draconite.getLookControl().setLookAt(target, 30.0f, 30.0f);
        --this.orbBarrageTicks;
        int elapsed = 60 - this.orbBarrageTicks;
        if (elapsed == 5 || elapsed == 25) {
            this.spawnScorbOrbs();
        }
        if (elapsed == 15 || elapsed == 40) {
            this.spawnBoomOrb(target);
        }
        if (elapsed == 50 && this.draconite.isFlying()) {
            this.draconite.setRainingOrbs(true);
        }
        if (this.orbBarrageTicks <= 0) {
            this.stop();
        }
    }

    private void spawnScorbOrbs() {
        if (this.draconite.level().isClientSide) {
            return;
        }
        int count = 1 + this.draconite.getRandom().nextInt(3);
        for (int i = 0; i < count; ++i) {
            double angle = this.draconite.getRandom().nextDouble() * Math.PI * 2.0;
            double offsetX = Math.cos(angle) * 3.0;
            double offsetZ = Math.sin(angle) * 3.0;
            double offsetY = 1.0 + this.draconite.getRandom().nextDouble() * 2.0;
            EntityOrbScary orb = new EntityOrbScary(ModEntityTypes.ORB_SCARY.get(), this.draconite.level());
            orb.setPos(this.draconite.getX() + offsetX, this.draconite.getY() + offsetY, this.draconite.getZ() + offsetZ);
            orb.setOwner(this.draconite);
            orb.setFollowOwner(false);
            this.draconite.level().addFreshEntity(orb);
        }
        this.draconite.playShootSound();
    }

    private void spawnBoomOrb(LivingEntity target) {
        if (this.draconite.level().isClientSide) {
            return;
        }
        double dx = target.getX() - this.draconite.getX();
        double dy = target.getEyeHeight(0.5) - (this.draconite.getY() + 2.0);
        double dz = target.getZ() - this.draconite.getZ();
        EntityOrbBoom orb = new EntityOrbBoom(ModEntityTypes.ORB_BOOM.get(), this.draconite.level());
        orb.setPos(this.draconite.getX(), this.draconite.getY() + 2.0, this.draconite.getZ());
        orb.setOwner(this.draconite);
        orb.setTargetDirection(new Vec3(dx, dy, dz));
        this.draconite.level().addFreshEntity(orb);
        this.draconite.playShootSound();
    }

    public boolean canBeUsedWhileContinuing() {
        return true;
    }
}

