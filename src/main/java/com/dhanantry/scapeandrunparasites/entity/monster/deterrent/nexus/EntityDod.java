/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus;

import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIDodAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAINexusGrow;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPDispatcher;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
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
import net.minecraft.world.World;

public class EntityDod
extends EntityPDispatcher {
    public EntityDod(World worldIn) {
        super(worldIn);
        this.func_70105_a(2.7f, 2.5f);
        this.field_70728_aV = SRPAttributes.XP_INFECTED * 2;
        this.buried = 0.1;
        this.setParasiteStatus(3);
        this.damageCap = SRPConfig.nexussiCap;
        this.pointCap = SRPConfig.nexussiPointCap;
        this.pointReduction = SRPConfig.nexussiPointRed;
        this.chanceLearn = SRPConfig.nexussiChanceLe;
        this.chanceLearnFire = SRPConfig.nexussiChanceLeFire;
        this.DamageTypeCap = SRPConfig.nexussiPointDamCap;
        this.totalP = SRPConfigMobs.dodsiTotalActiveMobs;
        this.mobID = new int[3];
        this.mobPT = new int[3];
        this.stage = 1;
        Arrays.fill(this.mobID, -777);
        this.neededTime = this.setGT(SRPConfig.nexusMinGrowTime, SRPConfig.nexusMaxGrowTime);
        this.valueEvDeath = SRPConfig.nexussiLoosingEPValue;
    }

    @Override
    public int getParasiteIDRegister() {
        return 73;
    }

    protected void func_184651_r() {
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAINexusGrow(this, 1, 2));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIDodAttack(this, 1, 16, 10.0f));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.DOD_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.DOD_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.DOD_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.nexussiFollow * (double)SRPConfigMobs.dodsiFollowRangeMult);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
    }

    public float func_70047_e() {
        return 2.3f;
    }

    @Override
    public float getBombDamage() {
        return (float)SRPAttributes.DOD_ATTACK_DAMAGE;
    }

    @Override
    public boolean storeParasite(EntityParasiteBase in) {
        if (super.storeParasite(in)) {
            return true;
        }
        if (this.storeLodo(in, true)) {
            return true;
        }
        if (this.storeInf(in, false)) {
            return true;
        }
        if (this.storeCrude(in, false)) {
            return true;
        }
        if (this.storeMudo(in, false)) {
            return true;
        }
        if (this.storeMangler(in, false)) {
            return true;
        }
        this.storeAll(in);
        return false;
    }

    @Override
    protected boolean onDeathDislo(DamageSource cause) {
        ParasiteEventWorld.setDisloWorldPhase(this.field_70170_p, SRPAttributes.EVENTPARANEXUSID, SRPConfigSystems.chanceEventParaNexusID, 0, null);
        return super.onDeathDislo(cause);
    }
}

