/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.entity.ai;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanShoot;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityAIAttackProjectile
extends EntityAIBase {
    private final EntityParasiteBase parentEntity;
    private int attackTimer;
    private int cooldown;
    private int shootingTimes;
    private int shootingUpdate;
    private int tickInterval;
    private int heighA;
    private boolean canShootH;

    public EntityAIAttackProjectile(EntityParasiteBase parent, int cooldown, int tickInter, int shootingTim) {
        this(parent, cooldown, tickInter, shootingTim, false);
    }

    public EntityAIAttackProjectile(EntityParasiteBase parent, int cooldown, int tickInter, int shootingTim, boolean homming) {
        this.parentEntity = parent;
        this.attackTimer = 0;
        this.cooldown = cooldown;
        this.shootingTimes = shootingTim;
        this.shootingUpdate = 0;
        this.tickInterval = tickInter;
        this.canShootH = homming;
    }

    public boolean func_75250_a() {
        return this.parentEntity.func_70638_az() != null;
    }

    public void func_75251_c() {
        this.shootingUpdate = 0;
        this.attackTimer = 0;
    }

    public void func_75246_d() {
        if (this.parentEntity.func_70638_az() == null || this.parentEntity.getParasiteStatus() >= 3) {
            this.attackTimer = 0;
        } else if (this.parentEntity.func_70638_az().field_70128_L) {
            this.attackTimer = 0;
        } else {
            EntityLivingBase entitylivingbase = this.parentEntity.func_70638_az();
            if (this.parentEntity.getParasiteStatus() == 0) {
                this.parentEntity.setParasiteStatus(1);
            }
            if (entitylivingbase.func_70068_e((Entity)this.parentEntity) < 4225.0 && this.parentEntity.func_70685_l((Entity)entitylivingbase)) {
                ++this.attackTimer;
                if (this.parentEntity.func_70644_a(SRPPotions.RAGE_E)) {
                    ++this.attackTimer;
                }
                if (this.attackTimer == this.cooldown - 10) {
                    EntityCanShoot shooter = (EntityCanShoot)((Object)this.parentEntity);
                    shooter.playProjSound();
                }
                if (this.attackTimer > this.cooldown) {
                    if (this.shootingTimes > this.shootingUpdate) {
                        if (this.attackTimer % this.tickInterval == 0) {
                            this.shoot(entitylivingbase, this.parentEntity.field_70170_p);
                            ++this.shootingUpdate;
                        }
                    } else {
                        this.attackTimer = 0;
                        this.shootingUpdate = 0;
                    }
                }
            } else if (this.attackTimer > 0) {
                --this.attackTimer;
            }
        }
    }

    private void shoot(EntityLivingBase entitylivingbase, World world) {
        this.heighA = !entitylivingbase.field_70122_E && this.canShootH ? ++this.heighA : 0;
        if (this.heighA <= 5) {
            double d1 = 4.0;
            Vec3d vec3d = this.parentEntity.func_70676_i(1.0f);
            double d2 = entitylivingbase.field_70165_t - (this.parentEntity.field_70165_t + vec3d.field_72450_a);
            double d3 = entitylivingbase.func_174813_aQ().field_72338_b + (double)(entitylivingbase.field_70131_O / 2.0f) - (0.5 + this.parentEntity.field_70163_u + (double)(this.parentEntity.field_70131_O / 2.0f));
            double d4 = entitylivingbase.field_70161_v - (this.parentEntity.field_70161_v + vec3d.field_72449_c);
            EntityCanShoot shooter = (EntityCanShoot)((Object)this.parentEntity);
            Entity entitylargefireball = shooter.getProj(d2, d3, d4);
            entitylargefireball.field_70165_t = this.parentEntity.field_70165_t + vec3d.field_72450_a;
            entitylargefireball.field_70163_u = this.parentEntity.field_70163_u + (double)this.parentEntity.func_70047_e() - 0.2;
            entitylargefireball.field_70161_v = this.parentEntity.field_70161_v + vec3d.field_72449_c;
            world.func_72838_d(entitylargefireball);
            this.parentEntity.resetIdleTime();
        }
    }
}

