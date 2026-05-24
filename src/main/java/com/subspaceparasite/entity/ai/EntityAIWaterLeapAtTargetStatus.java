/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.MathHelper
 */
package com.subspaceparasite.entity.ai;

import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;

public class EntityAIWaterLeapAtTargetStatus
extends EntityAIBase {
    private EntityParasiteBase leaper;
    private EntityLivingBase leapTarget;
    private float leapMotionY;
    private float targetY;
    private double jumpSpeed;
    private int jCooldown;
    private int jumpR;
    private int attackTimer = 0;
    private int attacking = 0;
    private double targetX;
    private double targetZ;

    public EntityAIWaterLeapAtTargetStatus(EntityParasiteBase leapingEntity, float leapMotionYIn, double speed, int distance, int cooldown, int jumpDamageRange) {
        this.leaper = leapingEntity;
        this.leapMotionY = leapMotionYIn;
        this.jumpSpeed = speed;
        this.jCooldown = cooldown;
        this.jumpR = jumpDamageRange;
    }

    public boolean func_75250_a() {
        return this.leaper.func_70090_H() || this.leaper.func_180799_ab() || this.attacking >= 1;
    }

    public void func_75246_d() {
        if (this.leaper.func_70638_az() == null || !this.leaper.shouldWorkTask()) {
            if (this.attackTimer > 0) {
                --this.attackTimer;
            }
        } else if (this.leaper.func_70638_az().field_70128_L || this.leaper.getParasiteStatus() > 2) {
            if (this.attackTimer > 0) {
                --this.attackTimer;
            }
        } else {
            EntityLivingBase entitylivingbase = this.leaper.func_70638_az();
            ++this.attackTimer;
            if (this.attackTimer >= this.jCooldown && this.attacking == 0) {
                ++this.attacking;
                this.targetX = entitylivingbase.field_70165_t;
                this.targetZ = entitylivingbase.field_70161_v;
                this.targetY = (float)(entitylivingbase.field_70163_u - this.leaper.field_70163_u) * 0.07f;
                if (this.targetY <= 0.0f) {
                    this.targetY = 0.0f;
                }
            }
        }
        if (this.attacking >= 1) {
            ++this.attacking;
            if (this.attacking == 2 && this.leaper.field_70122_E) {
                this.leaper.setParasiteStatus(10);
                if (this.leaper.func_70661_as().func_75505_d() != null) {
                    this.leaper.func_70661_as().func_75499_g();
                }
                double d0 = this.targetX - this.leaper.field_70165_t;
                double d1 = this.targetZ - this.leaper.field_70161_v;
                double f = MathHelper.func_76133_a((double)(d0 * d0 + d1 * d1));
                this.leaper.field_70181_x = (double)this.leapMotionY + (double)this.targetY;
                this.leaper.field_70159_w += d0 / f * this.jumpSpeed * 0.9 + this.leaper.field_70159_w * 0.3;
                this.leaper.field_70179_y += d1 / f * this.jumpSpeed * 0.9 + this.leaper.field_70179_y * 0.3;
            }
            if (this.attacking >= 3 && this.leaper.field_70122_E) {
                if (this.jumpR != 0) {
                    float damage = (float)this.leaper.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111125_b();
                    AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.leaper.field_70165_t, this.leaper.field_70163_u, this.leaper.field_70161_v, this.leaper.field_70165_t + 1.0, this.leaper.field_70163_u + 1.0, this.leaper.field_70161_v + 1.0).func_72314_b((double)this.jumpR, 2.0, (double)this.jumpR);
                    List moblist = this.leaper.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
                    for (EntityLivingBase mob : moblist) {
                        if (mob == this.leaper || mob instanceof EntityParasiteBase) continue;
                        mob.func_70653_a((Entity)mob, 2.5f, this.leaper.field_70165_t - mob.field_70165_t, this.leaper.field_70161_v - mob.field_70161_v);
                        this.leaper.func_70652_k((Entity)mob);
                    }
                }
                this.attacking = 0;
                this.attackTimer = 0;
                this.leaper.setParasiteStatus(2);
            }
        }
    }
}

