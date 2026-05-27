/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.projectile.EntityFireball
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package com.subspaceparasite.entity.projectile;

import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class EntitySPProjectile
extends EntityFireball {
    public EntitySPProjectile(World worldIn) {
        super(worldIn);
        this.func_70105_a(0.3f, 0.3f);
    }

    public EntitySPProjectile(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ) {
        super(worldIn, shooter, accelX, accelY, accelZ);
        this.func_70105_a(0.3f, 0.3f);
    }

    public boolean func_70027_ad() {
        return false;
    }

    public boolean func_70067_L() {
        return false;
    }

    public boolean func_70097_a(DamageSource source, float amount) {
        return false;
    }

    protected boolean attackEntityAsMobMinimum(Entity entityIn, EntityParasiteBase attacker) {
        if (entityIn == null || attacker == null) {
            return false;
        }
        if (!attacker.func_70089_S()) {
            return false;
        }
        if (entityIn instanceof EntityLivingBase && !(entityIn instanceof EntityParasiteBase)) {
            return attacker.attackEntityAsMobMinimum((EntityLivingBase)entityIn, attacker.getMiniDamage());
        }
        return false;
    }
}

