/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.world.World
 */
package com.subspaceparasite.entity.projectile;

import com.subspaceparasite.entity.EntityOrbBoom;
import com.subspaceparasite.entity.EntityToxicCloud;
import com.subspaceparasite.entity.ai.misc.EntityPMalleable;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.entity.monster.derived.EntityHeblu;
import com.subspaceparasite.entity.projectile.EntitySPProjectile;
import com.subspaceparasite.init.SPPotions;
import com.subspaceparasite.util.SPAttributes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityProjectileAlafhaBall
extends EntitySPProjectile {
    public EntityProjectileAlafhaBall(World worldIn) {
        super(worldIn);
        this.func_70105_a(0.3f, 0.3f);
    }

    public EntityProjectileAlafhaBall(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ) {
        super(worldIn, shooter, accelX, accelY, accelZ);
        this.func_70105_a(0.3f, 0.3f);
    }

    protected EnumParticleTypes func_184563_j() {
        return EnumParticleTypes.EXPLOSION_NORMAL;
    }

    protected void func_70227_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            DamageSource damagesource = this.field_70235_a == null ? DamageSource.func_76356_a((Entity)this, (Entity)this) : DamageSource.func_76356_a((Entity)this, (Entity)this.field_70235_a);
            for (EntityLivingBase entitylivingbase : this.field_70170_p.func_72872_a(EntityLivingBase.class, this.func_174813_aQ().func_72314_b(3.0, 3.0, 3.0))) {
                if (entitylivingbase instanceof EntityParasiteBase) continue;
                SPPotions.applyStackPotion(SPPotions.DLER_E, entitylivingbase, 300, 0);
                entitylivingbase.func_70097_a(damagesource, SPAttributes.ALAFHA_ATTACK_DAMAGE);
                this.attackEntityAsMobMinimum((Entity)entitylivingbase, (EntityParasiteBase)this.field_70235_a);
            }
            this.field_70170_p.func_184148_a((EntityPlayer)null, this.field_70165_t, this.field_70163_u, this.field_70161_v, SoundEvents.field_187539_bB, SoundCategory.BLOCKS, 4.0f, (1.0f + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2f) * 0.7f);
            this.spawnLingeringCloud();
            this.func_70106_y();
        }
    }

    private void spawnLingeringCloud() {
        if (this.field_70235_a != null && this.field_70235_a instanceof EntityHeblu) {
            EntityToxicCloud entityareaeffectcloud = new EntityToxicCloud(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v);
            entityareaeffectcloud.setRadius(5.0f, 0.9f);
            entityareaeffectcloud.setDuration(60);
            entityareaeffectcloud.setRadiusPerTick(-entityareaeffectcloud.getRadius() / (float)entityareaeffectcloud.getDuration());
            entityareaeffectcloud.setOwner((EntityParasiteBase)this.field_70235_a);
            entityareaeffectcloud.addEffect(new PotionEffect(SPPotions.COTH_E, 300, 0, false, true));
            this.field_70170_p.func_72838_d((Entity)entityareaeffectcloud);
            this.field_70170_p.func_72960_a((Entity)entityareaeffectcloud, (byte)77);
            EntityOrbBoom orb = new EntityOrbBoom(this.field_70170_p, (EntityPMalleable)this.field_70235_a, 15, 1);
            orb.func_82149_j(entityareaeffectcloud);
            this.field_70170_p.func_72838_d((Entity)orb);
            return;
        }
        EntityToxicCloud entityareaeffectcloud = new EntityToxicCloud(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v);
        entityareaeffectcloud.setRadius(2.0f, 0.5f);
        entityareaeffectcloud.setRadiusOnUse(-0.5f);
        entityareaeffectcloud.setWaitTime(30);
        entityareaeffectcloud.setDuration(60);
        entityareaeffectcloud.setRadiusPerTick(-entityareaeffectcloud.getRadius() / (float)entityareaeffectcloud.getDuration());
        entityareaeffectcloud.addEffect(new PotionEffect(SPPotions.DLER_E, 360, 0, false, false));
        this.field_70170_p.func_72838_d((Entity)entityareaeffectcloud);
    }
}

