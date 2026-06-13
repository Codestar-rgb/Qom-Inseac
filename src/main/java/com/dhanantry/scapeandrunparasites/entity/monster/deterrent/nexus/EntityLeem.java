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
package com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus;

import com.dhanantry.scapeandrunparasites.entity.ai.EntityAINexusGrow;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPRooter;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventWorld;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import java.util.Arrays;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityLeem
extends EntityPRooter {
    public EntityLeem(World worldIn) {
        super(worldIn);
        this.func_70105_a(1.2f, 2.8f);
        this.buriedT = 2.6;
        this.field_70728_aV = SRPAttributes.XP_INFECTED * 2;
        this.buried = 0.1;
        this.setParasiteStatus(3);
        this.damageCap = SRPConfig.nexussiCap;
        this.pointCap = SRPConfig.nexussiPointCap;
        this.pointReduction = SRPConfig.nexussiPointRed;
        this.chanceLearn = SRPConfig.nexussiChanceLe;
        this.chanceLearnFire = SRPConfig.nexussiChanceLeFire;
        this.DamageTypeCap = SRPConfig.nexussiPointDamCap;
        this.stage = 1;
        this.totalP = SRPConfigMobs.leemlimit;
        this.mobID = new int[SRPConfigMobs.leemlimit];
        this.mobPT = new int[SRPConfigMobs.leemlimit];
        this.leemRange = SRPConfigMobs.leemRange;
        this.leemRangeEffect = SRPConfigMobs.leemRangeEffect;
        this.leemBalls = SRPConfigMobs.leemlimit;
        this.leemCooldownReset = SRPConfigMobs.leemCooldown;
        Arrays.fill(this.mobID, -777);
        this.neededTime = this.setGT(SRPConfig.nexusMinGrowTime, SRPConfig.nexusMaxGrowTime);
        this.valueEvDeath = SRPConfig.nexussiLoosingEPValue;
    }

    @Override
    public int getParasiteIDRegister() {
        return 310;
    }

    protected void func_184651_r() {
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAINexusGrow(this, 1, 3));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.VENKROL_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.VENKROL_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.VENKROL_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.nexussiFollow);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
    }

    public float func_70047_e() {
        return 1.4f;
    }

    protected SoundEvent func_184639_G() {
        return SRPSounds.MOBSILENCE;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        if (this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0) {
            return SRPSounds.MOBSILENCE;
        }
        return SRPSounds.MOBSILENCE;
    }

    protected SoundEvent func_184615_bR() {
        return SRPSounds.MOBSILENCE;
    }

    @Override
    public float getBombDamage() {
        return (float)SRPAttributes.VENKROL_ATTACK_DAMAGE;
    }

    @Override
    protected boolean onDeathDislo(DamageSource cause) {
        ParasiteEventWorld.setDisloWorldPhase(this.field_70170_p, SRPAttributes.EVENTPARANEXUSID, SRPConfigSystems.chanceEventParaNexusID, 0, null);
        return super.onDeathDislo(cause);
    }
}

