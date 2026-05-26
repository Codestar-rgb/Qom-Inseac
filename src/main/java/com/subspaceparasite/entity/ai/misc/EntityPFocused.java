/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  javax.annotation.Nullable
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntityWaterMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 */
package com.subspaceparasite.entity.ai.misc;

import com.subspaceparasite.entity.ai.EntityAINearestAttackableTargetStatus;
import com.subspaceparasite.entity.ai.misc.EntityPCosmical;
import com.subspaceparasite.util.ParasiteEventEntity;
import com.subspaceparasite.util.SPAttributes;
import com.subspaceparasite.util.config.SPConfig;
import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public abstract class EntityPFocused
extends EntityPCosmical {
    public EntityPFocused(World worldIn) {
        super(worldIn);
        this.field_70715_bh.func_75776_a(4, new EntityAINearestAttackableTargetStatus<EntityPlayer>(this, EntityPlayer.class, 0, SPConfig.derivedWalls, false, null, SPConfig.derivedSneakPen, SPConfig.derivedInviPen));
        if (SPConfig.mobattacking) {
            this.field_70715_bh.func_75776_a(4, new EntityAINearestAttackableTargetStatus<EntityLiving>(this, EntityLiving.class, 0, SPConfig.derivedWalls, false, new Predicate<EntityLiving>(){

                public boolean apply(@Nullable EntityLiving entity) {
                    return !(entity instanceof EntityWaterMob) && !ParasiteEventEntity.checkEntity((EntityLivingBase)entity, SPConfig.mobattackingBlackList, SPConfig.mobattackingBlackListWhite);
                }
            }, SPConfig.derivedSneakPen, SPConfig.derivedInviPen));
        }
        this.field_70728_aV = SPAttributes.XP_DERIVED;
        this.canD = SPConfig.deriveddespawn;
        this.damageCap = SPConfig.derivedCap;
        this.canModRender = 1;
        this.type = (byte)71;
        this.fuseOrb = 13;
        this.orbStartTimer = 15;
        this.foodSteal = SPConfig.derivedFoodSteal;
        this.orbItemCool = SPConfig.derivedItemOrbCooldown * 20;
        this.pointCap = SPConfig.derivedPointCap;
        this.pointReduction = SPConfig.derivedPointRed;
        this.chanceLearn = SPConfig.derivedChanceLe;
        this.chanceLearnFire = SPConfig.derivedChanceLeFire;
        this.DamageTypeCap = SPConfig.derivedPointDamCap;
        this.MiniDamage = SPConfig.derivedMinDamage;
        this.regen = SPConfig.derivedRegen * SPConfig.globalHealthMultiplier;
        this.oneMindDeathValue = SPConfig.derivedOneMindDeathV;
        this.regenEff = 10;
        this.foodRott = SPConfig.derivedFoodChance;
        this.foodRootNumber = SPConfig.derivedFoodAmount;
        this.hackHeal = SPConfig.derivedHackHealing;
        this.hackEffects = SPConfig.derivedHackingEffects;
        this.valueEvDeath = SPConfig.derivedLoosingEPValue;
        this.setScentHPMultiplier(1.5f);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
    }

    @Override
    public void func_180430_e(float distance, float damageMultiplier) {
        super.func_180430_e(distance, 0.0f);
    }
}

