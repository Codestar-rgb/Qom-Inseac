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
package com.dhanantry.scapeandrunparasites.entity.ai.misc;

import com.dhanantry.scapeandrunparasites.entity.ai.EntityAINearestAttackableTargetStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPCosmical;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public abstract class EntityPDerived
extends EntityPCosmical {
    public EntityPDerived(World worldIn) {
        super(worldIn);
        this.field_70715_bh.func_75776_a(4, new EntityAINearestAttackableTargetStatus<EntityPlayer>(this, EntityPlayer.class, 0, SRPConfig.derivedWalls, false, null, SRPConfig.derivedSneakPen, SRPConfig.derivedInviPen));
        if (SRPConfig.mobattacking) {
            this.field_70715_bh.func_75776_a(4, new EntityAINearestAttackableTargetStatus<EntityLiving>(this, EntityLiving.class, 0, SRPConfig.derivedWalls, false, new Predicate<EntityLiving>(){

                public boolean apply(@Nullable EntityLiving entity) {
                    return !(entity instanceof EntityWaterMob) && !ParasiteEventEntity.checkEntity((EntityLivingBase)entity, SRPConfig.mobattackingBlackList, SRPConfig.mobattackingBlackListWhite);
                }
            }, SRPConfig.derivedSneakPen, SRPConfig.derivedInviPen));
        }
        this.field_70728_aV = SRPAttributes.XP_DERIVED;
        this.canD = SRPConfig.deriveddespawn;
        this.damageCap = SRPConfig.derivedCap;
        this.canModRender = 1;
        this.type = (byte)71;
        this.fuseOrb = 13;
        this.orbStartTimer = 15;
        this.foodSteal = SRPConfig.derivedFoodSteal;
        this.orbItemCool = SRPConfig.derivedItemOrbCooldown * 20;
        this.pointCap = SRPConfig.derivedPointCap;
        this.pointReduction = SRPConfig.derivedPointRed;
        this.chanceLearn = SRPConfig.derivedChanceLe;
        this.chanceLearnFire = SRPConfig.derivedChanceLeFire;
        this.DamageTypeCap = SRPConfig.derivedPointDamCap;
        this.MiniDamage = SRPConfig.derivedMinDamage;
        this.regen = SRPConfig.derivedRegen * SRPConfig.globalHealthMultiplier;
        this.oneMindDeathValue = SRPConfig.derivedOneMindDeathV;
        this.regenEff = 10;
        this.foodRott = SRPConfig.derivedFoodChance;
        this.foodRootNumber = SRPConfig.derivedFoodAmount;
        this.hackHeal = SRPConfig.derivedHackHealing;
        this.hackEffects = SRPConfig.derivedHackingEffects;
        this.valueEvDeath = SRPConfig.derivedLoosingEPValue;
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

