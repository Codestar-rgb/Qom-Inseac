package com.srp.draconite.entity.ai;

import com.srp.draconite.entity.EntityDraconite;

import java.util.EnumSet;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;

public class DraconiteFlightMoveGoal
extends Goal {
    private final EntityDraconite draconite;
    private Vec3 wantedPos;
    private int moveTimer;
    private static final int MOVE_INTERVAL = 80;

    public DraconiteFlightMoveGoal(EntityDraconite draconite) {
        this.draconite = draconite;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    public boolean canUse() {
        if (!this.draconite.isFlying()) {
            return false;
        }
        if (this.draconite.isAttacking()) {
            return false;
        }
        if (this.draconite.getRandom().nextInt(5) != 0) {
            return false;
        }
        LivingEntity target = this.draconite.getTarget();
        if (target != null) {
            double dist = this.draconite.distanceTo(target);
            return dist > 36.0;
        }
        return true;
    }

    public boolean canContinueToUse() {
        return this.draconite.isFlying() && this.moveTimer > 0;
    }

    public void start() {
        this.moveTimer = 80;
        this.wantedPos = this.pickFlightPos();
    }

    public void stop() {
        this.wantedPos = null;
        this.draconite.getNavigation().stop();
    }

    public void tick() {
        --this.moveTimer;
        if (this.wantedPos != null) {
            double dist = this.draconite.distanceToSqr(this.wantedPos);
            if (dist < 25.0 || this.moveTimer <= 0) {
                this.wantedPos = this.pickFlightPos();
                this.moveTimer = 80;
            }
            this.draconite.getMoveControl().setWantedPosition(this.wantedPos.x, this.wantedPos.y, this.wantedPos.z, 0.75);
        }
    }

    @Nullable
    private Vec3 pickFlightPos() {
        LivingEntity target = this.draconite.getTarget();
        if (target != null) {
            double dist = this.draconite.distanceTo(target);
            if (dist > 100.0) {
                return new Vec3(target.getX(), target.getY() + 8.0, target.getZ());
            }
            if (dist < 36.0) {
                double z;
                double x;
                double dz;
                double dx = this.draconite.getX() - target.getX();
                double d = Math.sqrt(dx * dx + (dz = this.draconite.getZ() - target.getZ()) * dz);
                if (d > 0.01) {
                    double offset = 3.0 + this.draconite.getRandom().nextDouble() * 4.0;
                    x = this.draconite.getX() + dx / d * offset;
                    z = this.draconite.getZ() + dz / d * offset;
                } else {
                    x = this.draconite.getX() + (this.draconite.getRandom().nextDouble() - 0.5) * 30.0;
                    z = this.draconite.getZ() + (this.draconite.getRandom().nextDouble() - 0.5) * 30.0;
                }
                double y = this.draconite.getY() + (this.draconite.getRandom().nextDouble() - 0.5) * 11.0;
                return this.clampY(x, y, z);
            }
        }
        double x = this.draconite.getX() + (this.draconite.getRandom().nextDouble() - 0.5) * 30.0;
        double z = this.draconite.getZ() + (this.draconite.getRandom().nextDouble() - 0.5) * 30.0;
        double y = this.draconite.getY() + (this.draconite.getRandom().nextDouble() - 0.5) * 11.0;
        return this.clampY(x, y, z);
    }

    private Vec3 clampY(double x, double y, double z) {
        BlockPos groundPos = new BlockPos((int)x, 0, (int)z);
        BlockPos groundPosObj = this.draconite.level().getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, groundPos);
        int groundY = groundPosObj.getY();
        y = Math.max(y, (double)groundY + 5.0);
        y = Math.min(y, (double)(this.draconite.level().getMaxBuildHeight() - 10));
        return new Vec3(x, y, z);
    }

    public boolean canBeUsedWhileContinuing() {
        return true;
    }
}

