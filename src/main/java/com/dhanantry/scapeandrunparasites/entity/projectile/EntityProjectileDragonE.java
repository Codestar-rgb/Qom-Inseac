/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.projectile.EntityFireball
 *  net.minecraft.entity.projectile.ProjectileHelper
 *  net.minecraft.init.MobEffects
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.world.World
 *  net.minecraftforge.event.ForgeEventFactory
 */
package com.dhanantry.scapeandrunparasites.entity.projectile;

import com.dhanantry.scapeandrunparasites.entity.EntityToxicCloud;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.EntityNak;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntitySRPProjectile;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class EntityProjectileDragonE
extends EntitySRPProjectile {
    private int ticksInAir;

    public EntityProjectileDragonE(World worldIn) {
        super(worldIn);
        this.func_70105_a(0.3f, 0.3f);
    }

    public EntityProjectileDragonE(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ) {
        super(worldIn, shooter, accelX, accelY, accelZ);
        this.func_70105_a(0.3f, 0.3f);
    }

    protected EnumParticleTypes func_184563_j() {
        return EnumParticleTypes.DRAGON_BREATH;
    }

    protected void func_70227_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0).func_186662_g(4.0);
            List moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
            for (EntityLivingBase mob : moblist) {
                if (mob instanceof EntityParasiteBase && !(mob instanceof EntityNak)) continue;
                SRPPotions.applyStackPotion(SRPPotions.VIRA_E, mob, 200, 1);
                DamageSource damagesource = this.field_70235_a == null ? DamageSource.func_76356_a((Entity)this, (Entity)this) : DamageSource.func_76356_a((Entity)this, (Entity)this.field_70235_a);
                mob.func_70097_a(damagesource, (float)SRPAttributes.INFDRAGONE_RANGED_ATTACK_DAMAGE);
                this.attackEntityAsMobMinimum((Entity)mob, (EntityParasiteBase)this.field_70235_a);
            }
            EntityToxicCloud entityareaeffectcloud = new EntityToxicCloud(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v);
            entityareaeffectcloud.setRadius(3.5f, 0.5f);
            entityareaeffectcloud.setRadiusOnUse(-0.5f);
            entityareaeffectcloud.setWaitTime(10);
            entityareaeffectcloud.setDuration(100);
            entityareaeffectcloud.setParticle(EnumParticleTypes.DRAGON_BREATH);
            entityareaeffectcloud.setRadiusPerTick(-entityareaeffectcloud.getRadius() / (float)entityareaeffectcloud.getDuration());
            entityareaeffectcloud.addEffect(new PotionEffect(MobEffects.field_76436_u, 300, 0));
            entityareaeffectcloud.addEffect(new PotionEffect(MobEffects.field_76433_i, 1, 1));
            this.field_70170_p.func_72838_d((Entity)entityareaeffectcloud);
            this.func_70106_y();
        }
    }

    public void func_70071_h_() {
        if (this.field_70170_p.field_72995_K || (this.field_70235_a == null || !this.field_70235_a.field_70128_L) && this.field_70170_p.func_175667_e(new BlockPos((Entity)this))) {
            int i;
            super.func_70071_h_();
            if (this.func_184564_k()) {
                this.func_70015_d(1);
            }
            ++this.ticksInAir;
            RayTraceResult raytraceresult = ProjectileHelper.func_188802_a((Entity)this, (boolean)true, (this.ticksInAir >= 25 ? 1 : 0) != 0, (Entity)this.field_70235_a);
            if (raytraceresult != null && !ForgeEventFactory.onProjectileImpact((EntityFireball)this, (RayTraceResult)raytraceresult)) {
                this.func_70227_a(raytraceresult);
            }
            this.field_70165_t += this.field_70159_w;
            this.field_70163_u += this.field_70181_x;
            this.field_70161_v += this.field_70179_y;
            ProjectileHelper.func_188803_a((Entity)this, (float)0.2f);
            float f = this.func_82341_c();
            if (this.func_70090_H()) {
                for (i = 0; i < 4; ++i) {
                    float f1 = 0.25f;
                    this.field_70170_p.func_175688_a(EnumParticleTypes.WATER_BUBBLE, this.field_70165_t - this.field_70159_w * 0.25, this.field_70163_u - this.field_70181_x * 0.25, this.field_70161_v - this.field_70179_y * 0.25, this.field_70159_w, this.field_70181_x, this.field_70179_y, new int[0]);
                }
                f = 0.8f;
            }
            this.field_70159_w += this.field_70232_b;
            this.field_70181_x += this.field_70233_c;
            this.field_70179_y += this.field_70230_d;
            this.field_70159_w *= (double)f;
            this.field_70181_x *= (double)f;
            this.field_70179_y *= (double)f;
            for (i = 0; i <= 4; ++i) {
                double d0 = this.field_70146_Z.nextGaussian() * 0.02;
                double d1 = this.field_70146_Z.nextGaussian() * 0.02;
                double d2 = this.field_70146_Z.nextGaussian() * 0.02;
                this.field_70170_p.func_175688_a(this.func_184563_j(), this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, this.field_70163_u + 0.5 + (double)(this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, d0, d1, d2, new int[0]);
            }
            this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        } else {
            this.func_70106_y();
        }
    }
}

