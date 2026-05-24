/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.pathfinding.Path
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 */
package com.subspaceparasite.entity.ai;

import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityAIAttackVenkrol
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
    protected final int attackInterval = 20;
    private int failedPathFindingPenalty = 0;
    private boolean canPenalize = false;

    public EntityAIAttackVenkrol(EntityParasiteBase creature, double speedIn, boolean useLongMemory) {
        this.attacker = creature;
        this.world = creature.field_70170_p;
        this.speedTowardsTarget = speedIn;
        this.longMemory = useLongMemory;
        this.func_75248_a(3);
    }

    public boolean func_75250_a() {
        EntityLivingBase entitylivingbase = this.attacker.func_70638_az();
        if (!this.attacker.shouldWorkTask()) {
            return false;
        }
        if (entitylivingbase == null) {
            return false;
        }
        if (!entitylivingbase.func_70089_S()) {
            return false;
        }
        if (this.canPenalize) {
            if (--this.delayCounter <= 0) {
                this.delayCounter = 4 + this.attacker.func_70681_au().nextInt(7);
                return this.path != null;
            }
            return true;
        }
        if (this.path != null) {
            return true;
        }
        return this.getAttackReachSqr(entitylivingbase) >= this.attacker.func_70092_e(entitylivingbase.field_70165_t, entitylivingbase.func_174813_aQ().field_72338_b, entitylivingbase.field_70161_v);
    }

    public boolean func_75253_b() {
        EntityLivingBase entitylivingbase = this.attacker.func_70638_az();
        if (!this.attacker.shouldWorkTask()) {
            return false;
        }
        if (entitylivingbase == null) {
            return false;
        }
        if (!entitylivingbase.func_70089_S()) {
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
        this.attacker.setParasiteStatus(1);
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
        double d0 = this.attacker.func_70092_e(entitylivingbase.field_70165_t, entitylivingbase.func_174813_aQ().field_72338_b, entitylivingbase.field_70161_v);
        --this.delayCounter;
        if ((this.longMemory || this.attacker.func_70635_at().func_75522_a((Entity)entitylivingbase)) && this.delayCounter <= 0 && (this.targetX == 0.0 && this.targetY == 0.0 && this.targetZ == 0.0 || entitylivingbase.func_70092_e(this.targetX, this.targetY, this.targetZ) >= 1.0 || this.attacker.func_70681_au().nextFloat() < 0.05f)) {
            this.targetX = entitylivingbase.field_70165_t;
            this.targetY = entitylivingbase.func_174813_aQ().field_72338_b;
            this.targetZ = entitylivingbase.field_70161_v;
            this.delayCounter = 4 + this.attacker.func_70681_au().nextInt(7);
            if (this.canPenalize) {
                this.delayCounter += this.failedPathFindingPenalty;
                this.failedPathFindingPenalty += 10;
            }
            if (d0 > 1024.0) {
                this.delayCounter += 10;
            } else if (d0 > 256.0) {
                this.delayCounter += 5;
            }
            this.attacker.setParasiteStatus(1);
        }
        this.attacker.setParasiteStatus(1);
        this.attackTick = Math.max(this.attackTick - 1, 0);
        this.checkAndPerformAttack(entitylivingbase, d0);
    }

    protected void checkAndPerformAttack(EntityLivingBase target, double distance) {
        double d0 = this.getAttackReachSqr(target);
        if (distance <= d0 && this.attackTick <= 0) {
            this.attackTick = 20;
            this.attacker.func_184609_a(EnumHand.MAIN_HAND);
            this.attacker.func_70652_k((Entity)target);
        }
    }

    protected double getAttackReachSqr(EntityLivingBase attackTarget) {
        return this.attacker.field_70130_N * 2.0f * this.attacker.field_70130_N * 2.0f + attackTarget.field_70130_N;
    }
}

