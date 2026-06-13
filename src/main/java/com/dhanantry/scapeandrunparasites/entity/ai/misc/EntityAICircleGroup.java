/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  com.google.common.base.Predicates
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.entity.ai.misc;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityAICircleGroup
extends EntityAIBase {
    private final EntityCreature mob;
    private final double speed;
    private final int minGroup;
    private final double minRadius;
    private final double maxRadius;
    private final int scanRadius;
    private final Predicate<? super Entity> sameGroup;
    private float speedMul = 1.0f;
    private double effSpeed = 1.0;
    private float lapTicksBase = 100.0f;
    private double smCenterX;
    private double smCenterZ;
    private double smRadius;
    private double smTargetX;
    private double smTargetY;
    private double smTargetZ;
    private float smYaw;
    private int tickAge = 0;
    private float seedF;
    private float wobbleA = 0.8f;
    private float wobbleF = 0.06f;
    private float wanderA = 0.6f;
    private float wanderF = 0.09f;
    private float angJitterAmp = (float)Math.toRadians(0.6);
    private float angJitterFreq = 0.07f;
    private double centerX;
    private double centerZ;
    private double radius;
    private int dirSign = 0;
    private static final String NBT_RING_DIR = "SRP_RingDir";
    private float myAngle;
    private float angularVel;
    private int recalcCenterTicker = 0;
    private static final int RECALC_CENTER_EVERY = 10;
    private int recalcWaypointTicker = 0;
    private static final int RECALC_WAYPOINT_EVERY = 8;
    private final List<EntityCreature> groupSnapshot = new ArrayList<EntityCreature>();

    public EntityAICircleGroup(EntityCreature mob, double speed, int minGroup, double minRadius, double maxRadius, int scanRadius, Predicate<? super Entity> sameGroup) {
        this.mob = mob;
        this.speed = speed;
        this.minGroup = minGroup;
        this.minRadius = minRadius;
        this.maxRadius = maxRadius;
        this.scanRadius = scanRadius;
        this.sameGroup = sameGroup == null ? Predicates.alwaysTrue() : sameGroup;
        this.func_75248_a(3);
    }

    public boolean func_75250_a() {
        if (this.mob.func_184218_aH() || this.mob.func_70638_az() != null || this.mob.func_70090_H()) {
            return false;
        }
        this.snapshotGroup();
        if (this.groupSnapshot.size() < this.minGroup) {
            return false;
        }
        this.estimateCenterAndRadius();
        this.assignInitialAngle();
        return true;
    }

    public boolean func_75253_b() {
        if (this.mob.field_70128_L) {
            return false;
        }
        if (this.mob.func_70638_az() != null) {
            return false;
        }
        this.snapshotGroup();
        int n = this.groupSnapshot.size();
        return n >= Math.max(2, this.minGroup - 2);
    }

    public void func_75249_e() {
        this.tickAge = 0;
        this.seedF = (float)(this.mob.func_145782_y() % 997) * 0.73f;
        int s = this.mob.func_145782_y() * 1103515245 + 12345;
        float u01 = (float)((s ^ s >>> 16) & Integer.MAX_VALUE) / 2.1474836E9f;
        this.speedMul = 0.65f + 0.35f * u01;
        this.effSpeed = this.speed * (double)this.speedMul;
        this.smCenterX = this.centerX;
        this.smCenterZ = this.centerZ;
        this.smRadius = this.radius;
        this.smTargetX = this.mob.field_70165_t;
        this.smTargetY = this.mob.field_70163_u;
        this.smTargetZ = this.mob.field_70161_v;
        this.smYaw = this.mob.field_70177_z;
        this.recalcCenterTicker = 0;
        this.recalcWaypointTicker = 0;
    }

    public void func_75251_c() {
        this.mob.func_70661_as().func_75499_g();
    }

    public void func_75246_d() {
        ++this.tickAge;
        if (++this.recalcCenterTicker >= 10) {
            this.recalcCenterTicker = 0;
            this.snapshotGroup();
            this.estimateCenterAndRadius();
        }
        this.smCenterX += (this.centerX - this.smCenterX) * 0.15;
        this.smCenterZ += (this.centerZ - this.smCenterZ) * 0.15;
        this.smRadius += (this.radius - this.smRadius) * 0.2;
        this.dirSign = this.getGroupDirSign();
        float wMax = (float)(Math.PI * 2 / (double)this.lapTicksBase);
        this.angularVel = (float)this.dirSign * wMax * this.speedMul;
        float jitter = this.angJitterAmp * (0.5f * MathHelper.func_76126_a((float)(((float)this.tickAge + this.seedF) * this.angJitterFreq)) + 0.5f * MathHelper.func_76134_b((float)(((float)this.tickAge * 0.73f + this.seedF) * this.angJitterFreq * 0.7f)));
        this.myAngle = this.normalizeAngle(this.myAngle + (this.angularVel + jitter));
        double rEff = this.smRadius + (double)(this.wobbleA * MathHelper.func_76126_a((float)(((float)this.tickAge + this.seedF) * this.wobbleF)));
        double nx = MathHelper.func_76134_b((float)this.myAngle);
        double nz = MathHelper.func_76126_a((float)this.myAngle);
        double tnx = -MathHelper.func_76126_a((float)this.myAngle) * (float)this.dirSign;
        double tnz = MathHelper.func_76134_b((float)this.myAngle) * (float)this.dirSign;
        double side = this.wanderA * MathHelper.func_76126_a((float)(((float)this.tickAge + this.seedF * 3.0f) * this.wanderF));
        double rawX = this.smCenterX + nx * rEff + tnx * side;
        double rawZ = this.smCenterZ + nz * rEff + tnz * side;
        double rawY = this.findGroundY(this.mob.field_70170_p, rawX, rawZ, this.mob.field_70163_u);
        this.smTargetX += (rawX - this.smTargetX) * 0.35;
        this.smTargetZ += (rawZ - this.smTargetZ) * 0.35;
        double dy = rawY - this.smTargetY;
        if (dy > 0.4) {
            dy = 0.4;
        }
        if (dy < -0.4) {
            dy = -0.4;
        }
        this.smTargetY += dy;
        double dx = this.smTargetX - this.mob.field_70165_t;
        double dz = this.smTargetZ - this.mob.field_70161_v;
        double distSq = dx * dx + dz * dz;
        if (distSq > 4.0 || ++this.recalcWaypointTicker >= 8) {
            this.recalcWaypointTicker = 0;
            this.mob.func_70661_as().func_75492_a(this.smTargetX, this.smTargetY, this.smTargetZ, this.effSpeed);
        }
        float targetYaw = (float)(MathHelper.func_181159_b((double)tnz, (double)tnx) * 57.29577951308232) - 90.0f;
        this.mob.field_70177_z = this.smYaw = this.approachAngle(this.smYaw, targetYaw, 20.0f);
        this.mob.field_70759_as = this.smYaw;
        this.mob.field_70761_aq = this.smYaw;
        this.mob.func_70605_aq().func_75642_a(this.smTargetX, this.smTargetY, this.smTargetZ, this.effSpeed);
        double tangentPush = 0.03;
        this.mob.field_70159_w += tnx * 0.03;
        this.mob.field_70179_y += tnz * 0.03;
        this.mob.func_70671_ap().func_75650_a(this.smTargetX, this.smTargetY + (double)this.mob.func_70047_e(), this.smTargetZ, 30.0f, 30.0f);
        this.pushApartSlightly(tnx, tnz);
    }

    private void snapshotGroup() {
        this.groupSnapshot.clear();
        AxisAlignedBB box = new AxisAlignedBB(this.mob.field_70165_t - (double)this.scanRadius, this.mob.field_70163_u - 8.0, this.mob.field_70161_v - (double)this.scanRadius, this.mob.field_70165_t + (double)this.scanRadius, this.mob.field_70163_u + 8.0, this.mob.field_70161_v + (double)this.scanRadius);
        this.groupSnapshot.add(this.mob);
        List nearby = this.mob.field_70170_p.func_72839_b((Entity)this.mob, box);
        for (Entity e : nearby) {
            if (!(e instanceof EntityCreature) || !this.sameGroup.apply((Object)e)) continue;
            this.groupSnapshot.add((EntityCreature)e);
        }
        this.groupSnapshot.sort(Comparator.comparingInt(Entity::func_145782_y));
    }

    private void estimateCenterAndRadius() {
        if (this.groupSnapshot.isEmpty()) {
            this.centerX = this.mob.field_70165_t;
            this.centerZ = this.mob.field_70161_v;
            this.radius = MathHelper.func_151237_a((double)3.0, (double)this.minRadius, (double)this.maxRadius);
            return;
        }
        double sx = 0.0;
        double sz = 0.0;
        for (EntityCreature e : this.groupSnapshot) {
            sx += e.field_70165_t;
            sz += e.field_70161_v;
        }
        this.centerX = sx / (double)this.groupSnapshot.size();
        this.centerZ = sz / (double)this.groupSnapshot.size();
        double r = 0.0;
        for (EntityCreature e : this.groupSnapshot) {
            double dx = e.field_70165_t - this.centerX;
            double dz = e.field_70161_v - this.centerZ;
            r += Math.sqrt(dx * dx + dz * dz);
        }
        if ((r /= (double)this.groupSnapshot.size()) < this.minRadius * 0.6) {
            r = Math.max(this.minRadius, Math.min(this.maxRadius, 1.2 * Math.sqrt(this.groupSnapshot.size())));
        }
        this.radius = MathHelper.func_151237_a((double)r, (double)this.minRadius, (double)this.maxRadius);
    }

    private void assignInitialAngle() {
        int idx = 0;
        int n = this.groupSnapshot.size();
        for (int i = 0; i < n; ++i) {
            if (this.groupSnapshot.get(i) != this.mob) continue;
            idx = i;
            break;
        }
        float baseAngle = (float)((double)idx * (Math.PI * 2 / (double)Math.max(1, n)));
        this.myAngle = this.normalizeAngle(baseAngle);
    }

    private int getGroupDirSign() {
        return (this.mob.func_145782_y() & 1) == 0 ? 1 : -1;
    }

    private float normalizeAngle(float a) {
        while ((double)a < -Math.PI) {
            a = (float)((double)a + Math.PI * 2);
        }
        while ((double)a > Math.PI) {
            a = (float)((double)a - Math.PI * 2);
        }
        return a;
    }

    private float approachAngle(float current, float target, float maxStep) {
        float delta;
        for (delta = target - current; delta < -180.0f; delta += 360.0f) {
        }
        while (delta > 180.0f) {
            delta -= 360.0f;
        }
        if (delta > maxStep) {
            delta = maxStep;
        }
        if (delta < -maxStep) {
            delta = -maxStep;
        }
        return current + delta;
    }

    private double findGroundY(World world, double x, double z, double fallbackY) {
        BlockPos top = world.func_175645_m(new BlockPos(MathHelper.func_76128_c((double)x), 0, MathHelper.func_76128_c((double)z)));
        int y = top.func_177956_o();
        if (Math.abs((double)y - fallbackY) > 6.0) {
            return fallbackY;
        }
        return (double)y + 0.2;
    }

    private void pushApartSlightly(double tnx, double tnz) {
        AxisAlignedBB bb = this.mob.func_174813_aQ().func_72314_b(0.6, 0.2, 0.6);
        List crowd = this.mob.field_70170_p.func_72839_b((Entity)this.mob, bb);
        for (Entity e : crowd) {
            if (!(e instanceof EntityLivingBase)) continue;
            double dx = this.mob.field_70165_t - e.field_70165_t;
            double dz = this.mob.field_70161_v - e.field_70161_v;
            double d2 = dx * dx + dz * dz + 0.001;
            double strength = Math.min(0.035, 0.02 / d2);
            double px = (dx * 0.5 + tnx * 0.5) * strength;
            double pz = (dz * 0.5 + tnz * 0.5) * strength;
            this.mob.field_70159_w += px;
            this.mob.field_70179_y += pz;
        }
    }
}

