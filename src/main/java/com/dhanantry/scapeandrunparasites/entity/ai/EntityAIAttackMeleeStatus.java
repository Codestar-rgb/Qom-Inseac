/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.pathfinding.Path
 *  net.minecraft.pathfinding.PathPoint
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.entity.ai;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityAIAttackMeleeStatus
extends EntityAIBase {
    World world;
    protected EntityParasiteBase attacker;
    protected int attackTick;
    double speedTowardsTarget;
    boolean longMemory;
    Path path;
    private int delayCounter;
    private double targetX;
    private double targetY;
    private double targetZ;
    private double blockDistance;
    private int failedPathFindingPenalty = 0;
    private boolean canPenalize = false;

    public EntityAIAttackMeleeStatus(EntityParasiteBase creature, double speedIn, boolean useLongMemory, double runningD) {
        this.attacker = creature;
        this.world = creature.field_70170_p;
        this.speedTowardsTarget = speedIn;
        this.longMemory = useLongMemory;
        this.blockDistance = runningD * runningD;
        this.func_75248_a(3);
    }

    public boolean func_75250_a() {
        EntityLivingBase entitylivingbase = this.attacker.func_70638_az();
        if (entitylivingbase == null) {
            return false;
        }
        if (!entitylivingbase.func_70089_S() || !this.attacker.shouldWorkTask()) {
            return false;
        }
        if (this.canPenalize) {
            if (--this.delayCounter <= 0) {
                this.path = this.attacker.func_70661_as().func_75494_a((Entity)entitylivingbase);
                this.delayCounter = 4 + this.attacker.func_70681_au().nextInt(7);
                return this.path != null;
            }
            return true;
        }
        this.path = this.attacker.func_70661_as().func_75494_a((Entity)entitylivingbase);
        if (this.path != null) {
            return true;
        }
        return this.getAttackReachSqr(entitylivingbase) >= this.attacker.func_70092_e(entitylivingbase.field_70165_t, entitylivingbase.func_174813_aQ().field_72338_b, entitylivingbase.field_70161_v);
    }

    public boolean func_75253_b() {
        EntityLivingBase entitylivingbase = this.attacker.func_70638_az();
        if (entitylivingbase == null) {
            return false;
        }
        if (!entitylivingbase.func_70089_S() || !this.attacker.shouldWorkTask()) {
            return false;
        }
        if (!this.longMemory) {
            return !this.attacker.func_70661_as().func_75500_f();
        }
        if (!this.attacker.func_180485_d(new BlockPos((Entity)entitylivingbase))) {
            return false;
        }
        return !(entitylivingbase instanceof EntityPlayer) || !((EntityPlayer)entitylivingbase).func_175149_v() && !((EntityPlayer)entitylivingbase).func_184812_l_();
    }

    public void func_75249_e() {
        EntityLivingBase entitylivingbase = this.attacker.func_70638_az();
        if (this.attacker.getParasiteStatus() <= 2 && this.attacker.getLLeap() < 1) {
            if ((this.attacker.func_70068_e((Entity)entitylivingbase) > this.blockDistance || this.attacker.getAttackCooldownAni() == 0) && this.attacker.getGeneMod(3)) {
                this.attacker.func_70661_as().func_75484_a(this.path, this.speedTowardsTarget);
                this.attacker.setParasiteStatus(2);
                this.attacker.setAttackCooldownAni(0);
            } else {
                this.attacker.func_70661_as().func_75484_a(this.path, 1.0);
                this.attacker.setParasiteStatus(1);
            }
        }
        this.delayCounter = 0;
    }

    public void func_75251_c() {
        EntityLivingBase entitylivingbase = this.attacker.func_70638_az();
        if (entitylivingbase instanceof EntityPlayer && (((EntityPlayer)entitylivingbase).func_175149_v() || ((EntityPlayer)entitylivingbase).func_184812_l_())) {
            this.attacker.func_70624_b(null);
        }
        this.attacker.func_70661_as().func_75499_g();
    }

    public void func_75246_d() {
        EntityLivingBase entitylivingbase = this.attacker.func_70638_az();
        if (entitylivingbase == null) {
            return;
        }
        this.attacker.func_70671_ap().func_75651_a((Entity)entitylivingbase, 30.0f, 30.0f);
        double d0 = this.attacker.func_70092_e(entitylivingbase.field_70165_t, entitylivingbase.func_174813_aQ().field_72338_b, entitylivingbase.field_70161_v);
        --this.delayCounter;
        if ((this.longMemory || this.attacker.func_70635_at().func_75522_a((Entity)entitylivingbase)) && this.delayCounter <= 0 && (this.targetX == 0.0 && this.targetY == 0.0 && this.targetZ == 0.0 || entitylivingbase.func_70092_e(this.targetX, this.targetY, this.targetZ) >= 1.0 || this.attacker.func_70681_au().nextFloat() < 0.05f)) {
            this.targetX = entitylivingbase.field_70165_t;
            this.targetY = entitylivingbase.func_174813_aQ().field_72338_b;
            this.targetZ = entitylivingbase.field_70161_v;
            this.delayCounter = 4 + this.attacker.func_70681_au().nextInt(7);
            if (this.canPenalize) {
                PathPoint finalPathPoint;
                this.delayCounter += this.failedPathFindingPenalty;
                this.failedPathFindingPenalty = this.attacker.func_70661_as().func_75505_d() != null ? ((finalPathPoint = this.attacker.func_70661_as().func_75505_d().func_75870_c()) != null && entitylivingbase.func_70092_e((double)finalPathPoint.field_75839_a, (double)finalPathPoint.field_75837_b, (double)finalPathPoint.field_75838_c) < 1.0 ? 0 : (this.failedPathFindingPenalty += 10)) : (this.failedPathFindingPenalty += 10);
            }
            if (d0 > 1024.0) {
                this.delayCounter += 10;
            } else if (d0 > 256.0) {
                this.delayCounter += 5;
            }
            if (this.attacker.getParasiteStatus() <= 2 && this.attacker.getLLeap() < 1) {
                if ((this.attacker.func_70068_e((Entity)entitylivingbase) > this.blockDistance || this.attacker.getAttackCooldownAni() == 0) && this.attacker.getGeneMod(3)) {
                    if (!this.attacker.func_70661_as().func_75497_a((Entity)entitylivingbase, this.speedTowardsTarget)) {
                        this.delayCounter += 15;
                    }
                    this.attacker.setParasiteStatus(2);
                    this.attacker.setAttackCooldownAni(0);
                } else {
                    if (!this.attacker.func_70661_as().func_75497_a((Entity)entitylivingbase, 1.0)) {
                        this.delayCounter += 15;
                    }
                    this.attacker.setParasiteStatus(1);
                }
            }
        }
        --this.attackTick;
        if (this.attackTick > 0 && this.attacker.func_70644_a(SRPPotions.PIVOT_E)) {
            this.attackTick -= this.attacker.func_70660_b(SRPPotions.PIVOT_E).func_76458_c() * 2;
        }
        this.checkAndPerformAttack(entitylivingbase, d0);
    }

    protected void checkAndPerformAttack(EntityLivingBase target, double distance) {
        double d0 = this.getAttackReachSqr(target);
        if (distance <= d0 && this.attackTick <= 0 && this.attacker.func_70685_l((Entity)target)) {
            this.attackTick = this.attacker.getAttackSpeed();
            this.attacker.func_70652_k((Entity)target);
        }
    }

    protected double getAttackReachSqr(EntityLivingBase attackTarget) {
        return this.attacker.field_70130_N * 2.0f * this.attacker.field_70130_N * 2.0f + attackTarget.field_70130_N;
    }

    protected boolean isTargetParasite(EntityLivingBase target) {
        if (SRPConfigSystems.useOneMind && target instanceof EntityParasiteBase) {
            if (((EntityParasiteBase)target).func_70638_az() instanceof EntityParasiteBase) {
                this.attacker.func_70624_b(null);
                return true;
            }
            if (SRPPotions.applySense((EntityLivingBase)this.attacker, 200, this.attacker.func_70068_e((Entity)target), SRPConfigSystems.oneMinRangeCap)) {
                this.attacker.func_70624_b(((EntityParasiteBase)target).func_70638_az());
            } else {
                this.attacker.func_70624_b(null);
            }
            return true;
        }
        return false;
    }
}

