/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 */
package com.subspaceparasite.entity.monster.deterrent.nexus;

import com.subspaceparasite.client.particle.SPEnumParticle;
import com.subspaceparasite.entity.ai.EntityAIBlockInfest;
import com.subspaceparasite.entity.ai.EntityAINexusGrow;
import com.subspaceparasite.entity.ai.EntityAIVenkrolSummon;
import com.subspaceparasite.entity.ai.misc.EntityPBeckon;
import com.subspaceparasite.init.SPSounds;
import com.subspaceparasite.util.ParasiteEventWorld;
import com.subspaceparasite.util.SPAttributes;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigMobs;
import com.subspaceparasite.util.config.SPConfigSystems;
import java.util.Arrays;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityVenkrol
extends EntityPBeckon {
    public EntityAIVenkrolSummon summonV = new EntityAIVenkrolSummon(this, SPConfigMobs.venkrollimit, 20 * SPConfigMobs.venkrolCooldown, 1, SPConfigMobs.venkrolCAMinimumV, SPConfigMobs.venkrolCAExtraM);

    public EntityVenkrol(World worldIn) {
        super(worldIn);
        this.func_70105_a(0.5f, 1.5f);
        this.buriedT = 2.6;
        this.totalP = SPConfigMobs.venkrolTotalActiveMobs;
        this.mobID = new int[this.totalP + SPConfigMobs.venkrollimit];
        this.mobPT = new int[this.totalP + SPConfigMobs.venkrollimit];
        this.stage = 1;
        this.field_70728_aV = SPAttributes.XP_INFECTED * 2;
        if (SPAttributes.rsBlockI) {
            this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIBlockInfest(this, 1));
        }
        Arrays.fill(this.mobID, -777);
        this.setBODY(1.0f);
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)this.summonV);
        this.damageCap = SPConfig.nexussiCap;
        this.pointCap = SPConfig.nexussiPointCap;
        this.pointReduction = SPConfig.nexussiPointRed;
        this.chanceLearn = SPConfig.nexussiChanceLe;
        this.chanceLearnFire = SPConfig.nexussiChanceLeFire;
        this.DamageTypeCap = SPConfig.nexussiPointDamCap;
        this.neededTime = this.setGT(SPConfig.nexusMinGrowTime, SPConfig.nexusMaxGrowTime);
        this.valueEvDeath = SPConfig.nexussiLoosingEPValue;
    }

    @Override
    public int getParasiteIDRegister() {
        return 16;
    }

    protected void func_184651_r() {
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAINexusGrow(this, 1));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SPAttributes.VENKROL_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SPAttributes.VENKROL_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SPAttributes.VENKROL_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SPConfig.nexussiFollow);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        if (this.field_70170_p.field_72995_K && this.topParticles > 0 && this.field_70173_aa % 10 == 0) {
            for (int i = 0; i <= 1; ++i) {
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
        return 1.4f;
    }

    public void setBODY(float in) {
        if ((in += this.getBODY()) > 0.6f) {
            in = 0.6f;
        }
        if (in < 0.0f) {
            in = 0.0f;
        }
        this.body = in;
    }

    @Override
    public boolean func_70601_bi() {
        IBlockState iblockstate = this.field_70170_p.func_180495_p(new BlockPos((Entity)this).func_177977_b());
        return this.func_180484_a(new BlockPos(this.field_70165_t, this.func_174813_aQ().field_72338_b, this.field_70161_v)) >= 0.0f && iblockstate.func_189884_a((Entity)this) && this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL;
    }

    protected SoundEvent func_184639_G() {
        return SPSounds.VENKROLSI_GROWL;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        if (this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0) {
            return SPSounds.MOBSILENCE;
        }
        return SPSounds.VENKROLSI_HURT;
    }

    protected SoundEvent func_184615_bR() {
        return SPSounds.VENKROLSI_DEATH;
    }

    @Override
    public float getBombDamage() {
        return (float)SPAttributes.VENKROL_ATTACK_DAMAGE;
    }

    @Override
    protected boolean onDeathDislo(DamageSource cause) {
        ParasiteEventWorld.setDisloWorldPhase(this.field_70170_p, SPAttributes.EVENTPARANEXUSID, SPConfigSystems.chanceEventParaNexusID, 0, null);
        return super.onDeathDislo(cause);
    }
}

