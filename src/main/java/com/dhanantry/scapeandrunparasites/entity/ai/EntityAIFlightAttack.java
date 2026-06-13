/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAITarget
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.passive.EntityWaterMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 */
package com.dhanantry.scapeandrunparasites.entity.ai;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class EntityAIFlightAttack
extends EntityAIBase {
    private final EntityParasiteBase parent;
    private int delay;
    private double distance;
    private boolean xRay;
    private int fromF;

    public EntityAIFlightAttack(EntityParasiteBase in, double distance) {
        this.parent = in;
        this.delay = 0;
        this.distance = distance * distance;
    }

    public EntityAIFlightAttack(EntityParasiteBase in, double distance, boolean ignoreSee, int floorS) {
        this(in, distance);
        this.xRay = ignoreSee;
        this.fromF = floorS;
    }

    public boolean func_75250_a() {
        return this.parent.getWait() == 0 && this.parent.srpTicks <= 10;
    }

    public void func_75246_d() {
        this.parent.reduceIdleChance(2, 1);
        if (this.parent.func_70638_az() != null) {
            if (this.parent.func_70638_az() instanceof EntityParasiteBase) {
                this.parent.func_70624_b(null);
                this.delay = 0;
                this.parent.setParasiteStatus(0);
                if (this.parent.getParasiteType() != 61) {
                    this.parent.func_70605_aq().func_75642_a(this.parent.field_70165_t, this.parent.field_70163_u, this.parent.field_70161_v, 1.0);
                }
                return;
            }
            if (this.parent.field_70170_p.func_73045_a(this.parent.func_70638_az().func_145782_y()) == null) {
                this.parent.func_70624_b(null);
                this.delay = 0;
                this.parent.setParasiteStatus(0);
                if (this.parent.getParasiteType() != 61) {
                    this.parent.func_70605_aq().func_75642_a(this.parent.field_70165_t, this.parent.field_70163_u, this.parent.field_70161_v, 1.0);
                }
                return;
            }
            EntityLivingBase target = this.parent.func_70638_az();
            if (target instanceof EntityPlayer && !this.canAttackPlayer((EntityPlayer)target)) {
                this.parent.func_70624_b(null);
                this.delay = 0;
                this.parent.setParasiteStatus(0);
            }
            if (!this.parent.func_70685_l((Entity)target)) {
                if (this.xRay) {
                    if (this.parent.field_70170_p.field_73012_v.nextInt(5) == 0) {
                        ++this.delay;
                    }
                } else {
                    ++this.delay;
                }
            } else {
                this.delay = target.func_70068_e((Entity)this.parent) >= this.distance ? ++this.delay : 0;
            }
            if (this.delay >= 6) {
                this.parent.func_70624_b(null);
                this.parent.setParasiteStatus(0);
                this.delay = 0;
                if (this.parent.getParasiteType() != 61) {
                    this.parent.func_70605_aq().func_75642_a(this.parent.field_70165_t, this.parent.field_70163_u, this.parent.field_70161_v, 1.0);
                }
            }
        } else {
            this.attackSurr();
        }
    }

    protected void attackSurr() {
        BlockPos posi = this.parent.func_180425_c();
        if (this.fromF != 0 && this.parent.field_70170_p.field_73012_v.nextInt(this.fromF) == 0 && (posi = ParasiteEventEntity.getFloor(this.parent.field_70170_p, posi, 25)) == null) {
            posi = this.parent.func_180425_c();
        }
        AxisAlignedBB axisalignedbb = new AxisAlignedBB(posi).func_186662_g(this.parent.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111126_e());
        List moblist = this.parent.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
        for (EntityLivingBase mob : moblist) {
            if (mob == null) continue;
            if (mob instanceof EntityPlayer) {
                if (!this.canAttackPlayer((EntityPlayer)mob)) continue;
                this.parent.func_70624_b(mob);
                return;
            }
            if (!(mob instanceof EntityLiving) || !SRPConfig.mobattacking || ParasiteEventEntity.checkEntity((EntityLivingBase)((EntityLiving)mob), SRPConfig.mobattackingBlackList, SRPConfig.mobattackingBlackListWhite) || this.fromF != 0 && mob.func_70068_e((Entity)this.parent) > this.distance || mob == this.parent || !this.canTargetEntity(mob) || !this.parent.func_70685_l((Entity)mob) && !this.xRay || !(mob.func_70068_e((Entity)this.parent) < 1024.0) || !mob.func_70089_S() || !this.parent.func_70686_a(mob.getClass())) continue;
            this.parent.func_70624_b(mob);
            this.parent.setParasiteStatus(1);
            return;
        }
    }

    private boolean canTargetEntity(EntityLivingBase in) {
        return !(in instanceof EntityParasiteBase) && !(in instanceof EntityAnimal) && !(in instanceof EntityCreeper) && !(in instanceof EntityWaterMob);
    }

    private boolean canAttackPlayer(EntityPlayer in) {
        if (in.field_71075_bZ.field_75102_a) {
            return false;
        }
        return EntityAITarget.func_179445_a((EntityLiving)this.parent, (EntityLivingBase)in, (boolean)false, (boolean)true);
    }
}

