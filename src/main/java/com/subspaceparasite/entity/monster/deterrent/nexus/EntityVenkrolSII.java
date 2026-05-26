/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.world.World
 */
package com.subspaceparasite.entity.monster.deterrent.nexus;

import com.subspaceparasite.client.particle.SPEnumParticle;
import com.subspaceparasite.entity.ai.EntityAIBlockInfest;
import com.subspaceparasite.entity.ai.EntityAINexusGrow;
import com.subspaceparasite.entity.ai.EntityAIVenkrolSummon;
import com.subspaceparasite.entity.ai.misc.EntityPBeckon;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityVenkrol;
import com.subspaceparasite.init.SPSounds;
import com.subspaceparasite.util.ParasiteEventEntity;
import com.subspaceparasite.util.ParasiteEventWorld;
import com.subspaceparasite.util.SPAttributes;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigMobs;
import com.subspaceparasite.util.config.SPConfigSystems;
import com.subspaceparasite.util.config.SPConfigWorld;
import java.util.Arrays;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityVenkrolSII
extends EntityPBeckon {
    public EntityAIVenkrolSummon summonV = new EntityAIVenkrolSummon(this, SPConfigMobs.venkrolsiilimit, 20 * SPConfigMobs.venkrolsiiCooldown, 2, SPConfigMobs.venkrolsiiCAMinimumV, SPConfigMobs.venkrolsiiCAExtraM);

    public EntityVenkrolSII(World worldIn) {
        super(worldIn);
        this.func_70105_a(0.6f, 2.8f);
        this.buriedT = 4.4;
        this.totalP = SPConfigMobs.venkrolsiiTotalActiveMobs;
        this.mobID = new int[this.totalP + SPConfigMobs.venkrolsiilimit];
        this.mobPT = new int[this.totalP + SPConfigMobs.venkrolsiilimit];
        this.stage = (byte)2;
        this.field_70728_aV = SPAttributes.XP_PRIMITIVE * 2;
        if (SPAttributes.rsBlockI) {
            this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIBlockInfest(this, 2));
        }
        Arrays.fill(this.mobID, -777);
        this.setBODY(1.0f);
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)this.summonV);
        this.damageCap = SPConfig.nexussiiCap;
        this.pointCap = SPConfig.nexussiiPointCap;
        this.pointReduction = SPConfig.nexussiiPointRed;
        this.chanceLearn = SPConfig.nexussiiChanceLe;
        this.chanceLearnFire = SPConfig.nexussiiChanceLeFire;
        this.DamageTypeCap = SPConfig.nexussiiPointDamCap;
        this.neededTime = this.setGT(SPConfig.nexussiiMinGrowTime, SPConfig.nexussiiMaxGrowTime);
        this.valueEvDeath = SPConfig.nexussiiLoosingEPValue;
    }

    @Override
    public int getParasiteIDRegister() {
        return 18;
    }

    protected void func_184651_r() {
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAINexusGrow(this, 2));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SPAttributes.VENKROLSII_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SPAttributes.VENKROLSII_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SPAttributes.VENKROLSII_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SPConfig.nexussiiFollow);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        if (this.field_70170_p.field_72995_K && this.topParticles > 0 && this.field_70173_aa % 5 == 0) {
            for (int i = 0; i <= 2; ++i) {
                this.spawnParticlesTop(SPEnumParticle.BIOMASS, 0, 0, 0);
            }
        }
        if (this.getParasiteStatus() == 0) {
            this.setBODY(0.04f);
        } else {
            this.setBODY(-0.04f);
        }
    }

    public float func_70047_e() {
        return 2.7f;
    }

    public void setBODY(float in) {
        if ((in += this.getBODY()) > 0.5f) {
            in = 0.5f;
        }
        if (in < 0.0f) {
            in = 0.0f;
        }
        this.body = in;
    }

    protected SoundEvent func_184639_G() {
        return SPSounds.VENKROLSII_GROWL;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        if (this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0) {
            return SPSounds.MOBSILENCE;
        }
        return SPSounds.VENKROLSII_HURT;
    }

    protected SoundEvent func_184615_bR() {
        return SPSounds.VENKROLSII_DEATH;
    }

    protected float func_70599_aP() {
        return 1.0f;
    }

    @Override
    public float getBombDamage() {
        return (float)SPAttributes.VENKROLSII_ATTACK_DAMAGE;
    }

    @Override
    public void func_70645_a(DamageSource cause) {
        if (!this.field_70170_p.field_72995_K) {
            if (SPConfigWorld.coloniesActivated || this.canChangeVariant) {
                if (ParasiteEventWorld.numberofColonies(this.field_70170_p) >= 1 || this.canChangeVariant) {
                    ParasiteEventEntity.checkColony(this.field_70170_p, cause, this);
                    ParasiteEventEntity.spawnNext(this, new EntityVenkrol(this.field_70170_p), true, false);
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
        ParasiteEventWorld.setDisloWorldPhase(this.field_70170_p, SPAttributes.EVENTPARANEXUSIID, SPConfigSystems.chanceEventParaNexusIID, 0, null);
        return super.onDeathDislo(cause);
    }
}

