/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.BlockPos
 */
package com.subspaceparasite.entity.ai;

import com.subspaceparasite.entity.monster.infected.EntityInfHuman;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

public class EntityAISoundEaterStalk
extends EntityAIBase {
    private final EntityInfHuman mob;
    private final double hearingRangeSq;
    private int ticksSinceNoise = 0;
    private static final int QUIET_THRESHOLD_TICKS = 100;

    public EntityAISoundEaterStalk(EntityInfHuman mob, double hearingRange) {
        this.mob = mob;
        this.hearingRangeSq = hearingRange * hearingRange;
        this.func_75248_a(0);
    }

    public boolean func_75250_a() {
        return this.mob.getSkin() == 111;
    }

    public boolean func_75253_b() {
        return this.mob.getSkin() == 111 && !this.mob.field_70128_L;
    }

    public void func_75249_e() {
        this.ticksSinceNoise = 0;
    }

    public void func_75251_c() {
        this.ticksSinceNoise = 0;
    }

    public void func_75246_d() {
        BlockPos soundPos;
        if (this.mob.field_70170_p.field_72995_K) {
            return;
        }
        this.mob.tickSoundMemory();
        EntityPlayer nearest = this.mob.field_70170_p.func_72890_a((Entity)this.mob, Math.sqrt(this.hearingRangeSq));
        if (nearest != null && (nearest.func_175149_v() || nearest.field_71075_bZ.field_75098_d)) {
            nearest = null;
        }
        boolean noisy = false;
        boolean canSee = false;
        if (nearest != null && nearest.func_70089_S() && this.mob.func_70068_e((Entity)nearest) <= this.hearingRangeSq) {
            noisy = this.isTargetNoisy((EntityLivingBase)nearest);
            canSee = this.mob.func_70685_l((Entity)nearest);
        }
        if (nearest == null || !nearest.func_70089_S() || this.mob.func_70068_e((Entity)nearest) > this.hearingRangeSq) {
            ++this.ticksSinceNoise;
            if (this.ticksSinceNoise >= 100) {
                this.forceDropAggro();
            }
        } else if (noisy) {
            this.ticksSinceNoise = 0;
            soundPos = new BlockPos(nearest.field_70165_t, nearest.field_70163_u, nearest.field_70161_v);
            this.mob.notifyHeardSound(soundPos, 100);
            if (canSee) {
                if (this.mob.func_70638_az() != nearest) {
                    this.mob.func_70624_b((EntityLivingBase)nearest);
                }
            } else if (this.mob.func_70638_az() == nearest) {
                this.mob.func_70624_b(null);
                this.mob.func_70661_as().func_75499_g();
            }
        } else if (!canSee) {
            if (this.mob.func_70638_az() instanceof EntityPlayer) {
                this.mob.func_70624_b(null);
                this.mob.func_70661_as().func_75499_g();
            }
            ++this.ticksSinceNoise;
            if (this.ticksSinceNoise >= 100) {
                this.forceDropAggro();
            }
        } else {
            this.ticksSinceNoise = 0;
        }
        if (this.mob.func_70638_az() == null && (soundPos = this.mob.getHeardSoundPos()) != null) {
            double sz;
            double sy;
            double sx = (double)soundPos.func_177958_n() + 0.5;
            if (this.mob.func_70092_e(sx, sy = (double)soundPos.func_177956_o(), sz = (double)soundPos.func_177952_p() + 0.5) < 2.0) {
                this.mob.clearHeardSound();
            } else {
                this.mob.func_70661_as().func_75492_a(sx, sy, sz, 1.2);
            }
        }
    }

    private void forceDropAggro() {
        this.mob.func_70624_b(null);
        this.mob.func_70604_c(null);
        this.mob.func_70661_as().func_75499_g();
        this.ticksSinceNoise = 0;
    }

    private boolean isTargetNoisy(EntityLivingBase t) {
        if (!(t instanceof EntityPlayer)) {
            return false;
        }
        EntityPlayer p = (EntityPlayer)t;
        if (p.func_175149_v() || p.field_71075_bZ.field_75098_d) {
            return false;
        }
        if (p.func_70093_af()) {
            return false;
        }
        if (p.func_70051_ag()) {
            return true;
        }
        if (p.func_184587_cr()) {
            return true;
        }
        if (p.field_82175_bq || p.field_110158_av > 0) {
            return true;
        }
        if (Math.abs(p.field_70181_x) > 0.08) {
            return true;
        }
        double dx = p.field_70159_w;
        double dz = p.field_70179_y;
        double speedSq = dx * dx + dz * dz;
        return speedSq > 0.003;
    }
}

