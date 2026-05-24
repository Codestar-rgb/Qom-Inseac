/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.world.World
 */
package com.subspaceparasite.entity.monster.deterrent.nexus;

import com.subspaceparasite.entity.EntityBody;
import com.subspaceparasite.entity.ai.misc.EntityBodyParts;
import com.subspaceparasite.entity.ai.misc.EntityPRooter;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityLeemSIII;
import com.subspaceparasite.init.SPPotions;
import com.subspaceparasite.init.SPSounds;
import com.subspaceparasite.util.ParasiteEventEntity;
import com.subspaceparasite.util.ParasiteEventWorld;
import com.subspaceparasite.util.SPAttributes;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigMobs;
import com.subspaceparasite.util.config.SPConfigSystems;
import com.subspaceparasite.util.config.SPConfigWorld;
import java.util.Arrays;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityLeemSIV
extends EntityPRooter
implements EntityBodyParts {
    private EntityBody head;

    public EntityLeemSIV(World worldIn) {
        super(worldIn);
        this.func_70105_a(1.7f, 5.4f);
        this.buriedT = 2.6;
        this.field_70158_ak = true;
        this.field_70728_aV = SPAttributes.XP_ADAPTED * 4;
        this.buried = 0.1;
        this.setParasiteStatus(3);
        this.damageCap = SPConfig.nexussiCap;
        this.pointCap = SPConfig.nexussivPointCap;
        this.pointReduction = SPConfig.nexussivPointRed;
        this.chanceLearn = SPConfig.nexussivChanceLe;
        this.chanceLearnFire = SPConfig.nexussivChanceLeFire;
        this.DamageTypeCap = SPConfig.nexussivPointDamCap;
        this.stage = (byte)4;
        this.totalP = SPConfigMobs.leemsivlimit;
        this.mobID = new int[SPConfigMobs.leemsivlimit];
        this.mobPT = new int[SPConfigMobs.leemsivlimit];
        this.leemRange = SPConfigMobs.leemsivRange;
        this.leemRangeEffect = SPConfigMobs.leemsivRangeEffect;
        this.leemBalls = SPConfigMobs.leemsivlimit;
        this.leemCooldownReset = SPConfigMobs.leemsivCooldown;
        Arrays.fill(this.mobID, -777);
        this.neededTime = this.setGT(SPConfig.nexusMinGrowTime, SPConfig.nexusMaxGrowTime);
        this.head = new EntityBody(this, 5.2f, 5.3f, 1.0f, 0.0f, 5.45f, -1, 1, false, 0.2f);
        this.valueEvDeath = SPConfig.nexussivLoosingEPValue;
        this.rangeB = 5;
    }

    @Override
    public int getParasiteIDRegister() {
        return 313;
    }

    protected void func_184651_r() {
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SPAttributes.VENKROLSIV_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SPAttributes.VENKROLSIV_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SPAttributes.VENKROLSIV_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SPConfig.nexussivFollow);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        this.head.func_70071_h_();
    }

    @Override
    public boolean attackEntityBodyFrom(DamageSource source, float amount, int id, boolean notify) {
        if (this.field_70146_Z.nextBoolean()) {
            SPPotions.applyStackPotion(SPPotions.BLEED_E, (EntityLivingBase)this, 80, 0);
        }
        return this.func_70097_a(source, amount * 3.0f);
    }

    @Override
    public void func_70106_y() {
        if (this.head != null) {
            this.field_70170_p.func_72973_f((Entity)this.head);
        }
        super.func_70106_y();
    }

    @Override
    public void setBodyPartDead(int id) {
    }

    public float func_70047_e() {
        return 1.4f;
    }

    protected SoundEvent func_184639_G() {
        return SPSounds.MOBSILENCE;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        if (this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0) {
            return SPSounds.MOBSILENCE;
        }
        return SPSounds.MOBSILENCE;
    }

    protected SoundEvent func_184615_bR() {
        return SPSounds.MOBSILENCE;
    }

    @Override
    public float getBombDamage() {
        return (float)SPAttributes.VENKROL_ATTACK_DAMAGE;
    }

    @Override
    public void func_70645_a(DamageSource cause) {
        if (!this.field_70170_p.field_72995_K) {
            if (SPConfigWorld.coloniesActivated || this.canChangeVariant) {
                if (ParasiteEventWorld.numberofColonies(this.field_70170_p) >= 1 || this.canChangeVariant) {
                    ParasiteEventEntity.checkColony(this.field_70170_p, cause, this);
                    ParasiteEventEntity.spawnNext(this, new EntityLeemSIII(this.field_70170_p), true, false);
                } else {
                    super.func_70645_a(cause);
                }
            } else {
                super.func_70645_a(cause);
            }
        }
    }

    @Override
    protected boolean onDeathDislo(DamageSource cause) {
        ParasiteEventWorld.setDisloWorldPhase(this.field_70170_p, SPAttributes.EVENTPARANEXUSIVD, SPConfigSystems.chanceEventParaNexusIVD, 0, null);
        return super.onDeathDislo(cause);
    }
}

