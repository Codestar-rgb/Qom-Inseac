package com.srp.draconite.entity.ai;

import com.srp.draconite.entity.EntityDraconite;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.phys.Vec3;

public class DraconiteMoveControl
extends MoveControl {
    private final EntityDraconite draconite;

    public DraconiteMoveControl(EntityDraconite draconite) {
        super(draconite);
        this.draconite = draconite;
    }

    public void tick() {
        if (this.draconite.isFlying()) {
            this.tickFlying();
        } else {
            this.tickGround();
        }
    }

    private void tickGround() {
        double dy;
        double dz;
        if (this.operation != MoveControl.Operation.MOVE_TO) {
            this.draconite.setZza(0.0f);
            return;
        }
        double dx = this.wantedX - this.draconite.getX();
        double distSq = dx * dx + (dz = this.wantedZ - this.draconite.getZ()) * dz;
        if (distSq < 1.0E-4) {
            this.draconite.setZza(0.0f);
            return;
        }
        float targetYaw = (float)(Mth.atan2((double)dz, (double)dx) * 57.29577951308232) - 90.0f;
        this.draconite.setYRot(this.rotLerp(this.draconite.getYRot(), targetYaw, 10.0f));
        this.draconite.yBodyRot = this.draconite.getYRot();
        this.draconite.yBodyRotO = this.draconite.getYRot();
        float speed = (float)(this.speedModifier * this.draconite.getAttributeValue(Attributes.MOVEMENT_SPEED));
        this.draconite.setSpeed(speed);
        this.draconite.setZza(speed);
        if (this.draconite.isAggressive() && this.draconite.isOnGround()) {
            this.draconite.getJumpControl().jumpFromGround();
        }
        if ((dy = this.wantedY - this.draconite.getY()) > 0.5 && this.draconite.isOnGround()) {
            this.draconite.getJumpControl().jumpFromGround();
        }
    }

    private void tickFlying() {
        double avgEdgeLength;
        double dz;
        double dy;
        if (this.operation != MoveControl.Operation.MOVE_TO) {
            Vec3 velocity = this.draconite.getDeltaMovement();
            this.draconite.setDeltaMovement(velocity.scale(0.5));
            this.draconite.setZza(0.0f);
            return;
        }
        double dx = this.wantedX - this.draconite.getX();
        double distSq = dx * dx + (dy = this.wantedY - this.draconite.getY()) * dy + (dz = this.wantedZ - this.draconite.getZ()) * dz;
        if (distSq < (avgEdgeLength = this.draconite.getBoundingBox().getSize()) * avgEdgeLength) {
            this.draconite.setDeltaMovement(this.draconite.getDeltaMovement().scale(0.5));
            this.draconite.setZza(0.0f);
            return;
        }
        double dist = Math.sqrt(distSq);
        double accelFactor = 0.05 * this.speedModifier;
        Vec3 currentMotion = this.draconite.getDeltaMovement();
        double motionX = currentMotion.x + dx / dist * accelFactor;
        double motionY = currentMotion.y + dy / dist * accelFactor;
        double motionZ = currentMotion.z + dz / dist * accelFactor;
        this.draconite.setDeltaMovement(motionX, motionY, motionZ);
        if (this.draconite.getTarget() == null) {
            float targetYaw = -((float)Mth.atan2((double)motionX, (double)motionZ)) * 57.295776f;
            this.draconite.setYRot(this.rotLerp(this.draconite.getYRot(), targetYaw, 10.0f));
        } else {
            double tx = this.draconite.getTarget().getX() - this.draconite.getX();
            double tz = this.draconite.getTarget().getZ() - this.draconite.getZ();
            float targetYaw = -((float)Mth.atan2((double)tx, (double)tz)) * 57.295776f;
            this.draconite.setYRot(this.rotLerp(this.draconite.getYRot(), targetYaw, 10.0f));
        }
        this.draconite.yBodyRot = this.draconite.getYRot();
        this.draconite.yBodyRotO = this.draconite.getYRot();
        double flyingSpeed = this.draconite.getAttributeValue(Attributes.FLYING_SPEED);
        this.draconite.setSpeed((float)(this.speedModifier * flyingSpeed));
        this.draconite.setZza((float)(this.speedModifier * flyingSpeed));
    }

    protected float rotLerp(float from, float to, float maxDelta) {
        float diff = Mth.wrapDegrees((float)(to - from));
        if (diff > maxDelta) {
            diff = maxDelta;
        } else if (diff < -maxDelta) {
            diff = -maxDelta;
        }
        return from + diff;
    }
}

