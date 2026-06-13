/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.entity.projectile;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.EntityNak;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntitySRPProjectile;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityProjectileElviaBall
extends EntitySRPProjectile {
    public EntityProjectileElviaBall(World worldIn) {
        super(worldIn);
        this.func_70105_a(0.3f, 0.3f);
    }

    public EntityProjectileElviaBall(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ) {
        super(worldIn, shooter, accelX, accelY, accelZ);
        this.func_70105_a(0.3f, 0.3f);
    }

    protected EnumParticleTypes func_184563_j() {
        return EnumParticleTypes.EXPLOSION_NORMAL;
    }

    protected void func_70227_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null && result.field_72308_g instanceof EntityLivingBase) {
                if (result.field_72308_g instanceof EntityParasiteBase && !(result.field_72308_g instanceof EntityNak)) {
                    this.func_70106_y();
                    return;
                }
                DamageSource damagesource = this.field_70235_a == null ? DamageSource.func_76356_a((Entity)this, (Entity)this) : DamageSource.func_76356_a((Entity)this, (Entity)this.field_70235_a);
                result.field_72308_g.func_70097_a(damagesource, (float)SRPAttributes.ELVIA_ATTACK_DAMAGE);
                this.attackEntityAsMobMinimum(result.field_72308_g, (EntityParasiteBase)this.field_70235_a);
            }
            this.func_70106_y();
        }
    }
}

