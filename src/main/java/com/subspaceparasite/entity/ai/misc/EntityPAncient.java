/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  javax.annotation.Nullable
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.passive.EntityWaterMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.world.World
 */
package com.subspaceparasite.entity.ai.misc;

import com.subspaceparasite.entity.ai.EntityAINearestAttackableTargetStatus;
import com.subspaceparasite.entity.ai.misc.EntityPMalleable;
import com.subspaceparasite.init.SPPotions;
import com.subspaceparasite.util.ParasiteEventEntity;
import com.subspaceparasite.util.SPAttributes;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigSystems;
import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public abstract class EntityPAncient
extends EntityPMalleable {
    public EntityPAncient(World worldIn) {
        super(worldIn);
        this.field_70715_bh.func_75776_a(4, new EntityAINearestAttackableTargetStatus<EntityPlayer>(this, EntityPlayer.class, 0, false, false, null, SPConfig.preeminentSneakPen, SPConfig.preeminentInviPen));
        if (SPConfig.mobattacking) {
            this.field_70715_bh.func_75776_a(4, new EntityAINearestAttackableTargetStatus<EntityLiving>(this, EntityLiving.class, 0, !SPConfigSystems.useOneMind, !SPConfigSystems.useOneMind, new Predicate<EntityLiving>(){

                public boolean apply(@Nullable EntityLiving entity) {
                    return !(entity instanceof EntityWaterMob) && !(entity instanceof EntityAnimal) && !(entity instanceof EntityVillager) && !ParasiteEventEntity.checkEntity((EntityLivingBase)entity, SPConfig.mobattackingBlackList, SPConfig.mobattackingBlackListWhite);
                }
            }, SPConfig.preeminentSneakPen, SPConfig.preeminentInviPen));
        }
        this.field_70728_aV = SPAttributes.XP_ADAPTED;
        this.canD = SPConfig.ancientdespawn;
        this.damageCap = SPConfig.ancientCap;
        this.canModRender = 1;
        this.killcount = -10.0;
        this.field_70158_ak = true;
        this.pointCap = SPConfig.ancientPointCap;
        this.pointReduction = SPConfig.ancientPointRed;
        this.chanceLearn = SPConfig.ancientChanceLe;
        this.chanceLearnFire = SPConfig.ancientChanceLeFire;
        this.DamageTypeCap = SPConfig.ancientPointDamCap;
        this.MiniDamage = SPConfig.ancientMinDamage;
        this.regen = SPConfig.ancientRegen * SPConfig.globalHealthMultiplier;
        this.oneMindDeathValue = SPConfig.ancientOneMindDeathV;
        this.valueEvDeath = SPConfig.ancientLoosingEPValue;
        this.setScentHPMultiplier(0.25f);
    }

    @Override
    protected void fearPlayer(EntityLivingBase player) {
        try {
            if (player == null) {
                return;
            }
            if (!this.func_70685_l((Entity)player)) {
                return;
            }
            if (!player.func_70644_a(SPPotions.FEAR_E)) {
                player.func_70690_d(new PotionEffect(SPPotions.FEAR_E, 300, 3, false, false));
            } else if (player.func_70660_b(SPPotions.FEAR_E).func_76458_c() < 3) {
                player.func_70690_d(new PotionEffect(SPPotions.FEAR_E, 300, 3, false, false));
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void func_180430_e(float distance, float damageMultiplier) {
        if (distance >= 100.0f) {
            super.func_180430_e(distance, damageMultiplier);
        }
    }

    @Override
    public boolean scaryOrbEffect(EntityLivingBase in, int mobs) {
        boolean flag = super.scaryOrbEffect(in, mobs);
        if (flag) {
            // empty if block
        }
        return flag;
    }
}

