/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.world.World
 */
package com.subspaceparasite.entity.projectile;

import com.subspaceparasite.entity.EntityToxicCloud;
import com.subspaceparasite.entity.projectile.EntitySPProjectile;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityProjectileEffects
extends EntitySPProjectile {
    private ArrayList<PotionEffect> eff;

    public EntityProjectileEffects(World worldIn) {
        super(worldIn);
        this.func_70105_a(0.3f, 0.3f);
        this.eff = new ArrayList();
    }

    public EntityProjectileEffects(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ) {
        super(worldIn, shooter, accelX, accelY, accelZ);
        this.func_70105_a(0.3f, 0.3f);
        this.eff = new ArrayList();
    }

    protected EnumParticleTypes func_184563_j() {
        return EnumParticleTypes.EXPLOSION_NORMAL;
    }

    public void addEffect(PotionEffect in) {
        this.eff.add(in);
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (!this.field_70170_p.field_72995_K) {
            this.field_70181_x -= 0.1;
        }
    }

    protected void func_70227_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            EntityToxicCloud entityareaeffectcloud = new EntityToxicCloud(this.field_70170_p, this.field_70165_t, this.field_70163_u - 1.0, this.field_70161_v);
            entityareaeffectcloud.setRadius(2.3f, 0.5f);
            entityareaeffectcloud.setRadiusOnUse(-0.5f);
            entityareaeffectcloud.setWaitTime(10);
            entityareaeffectcloud.setDuration(100);
            entityareaeffectcloud.setRadiusPerTick(-entityareaeffectcloud.getRadius() / (float)entityareaeffectcloud.getDuration());
            for (PotionEffect in : this.eff) {
                entityareaeffectcloud.addEffect(in);
            }
            this.field_70170_p.func_72838_d((Entity)entityareaeffectcloud);
            this.func_70106_y();
        }
    }
}

