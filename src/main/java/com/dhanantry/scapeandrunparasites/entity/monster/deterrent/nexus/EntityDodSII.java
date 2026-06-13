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
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityDod;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventWorld;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import java.util.Arrays;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityDodSII
extends EntityPDispatcher {
    public EntityDodSII(World worldIn) {
        super(worldIn);
        this.func_70105_a(3.2f, 3.6f);
        this.field_70158_ak = true;
        this.field_70728_aV = SRPAttributes.XP_PRIMITIVE * 2;
        this.buried = 0.1;
        this.setParasiteStatus(3);
        this.damageCap = SRPConfig.nexussiiCap;
        this.pointCap = SRPConfig.nexussiiPointCap;
        this.pointReduction = SRPConfig.nexussiiPointRed;
        this.chanceLearn = SRPConfig.nexussiiChanceLe;
        this.chanceLearnFire = SRPConfig.nexussiiChanceLeFire;
        this.DamageTypeCap = SRPConfig.nexussiiPointDamCap;
        this.totalP = SRPConfigMobs.dodsiiTotalActiveMobs;
        this.mobID = new int[5];
        this.mobPT = new int[5];
        this.stage = (byte)2;
        Arrays.fill(this.mobID, -777);
        this.neededTime = this.setGT(SRPConfig.nexussiiMinGrowTime, SRPConfig.nexussiiMaxGrowTime);
        this.valueEvDeath = SRPConfig.nexussiiLoosingEPValue;
    }

    @Override
    public int getParasiteIDRegister() {
        return 77;
    }

    protected void func_184651_r() {
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAINexusGrow(this, 2, 2));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIDodAttack(this, 2, 32, 15.0f));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.DODSII_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.DODSII_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.DODSII_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.nexussiiFollow * (double)SRPConfigMobs.dodsiiFollowRangeMult);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
    }

    public float func_70047_e() {
        return 3.4f;
    }

    @Override
    public float getBombDamage() {
        return (float)SRPAttributes.DODSII_ATTACK_DAMAGE;
    }

    @Override
    public boolean storeParasite(EntityParasiteBase in) {
        if (super.storeParasite(in)) {
            return true;
        }
        if (this.storeLodo(in, true)) {
            return true;
        }
        if (this.storeInf(in, true)) {
            return true;
        }
        if (this.storeCrude(in, true)) {
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
    public void func_70645_a(DamageSource cause) {
        if (!this.field_70170_p.field_72995_K) {
            if (SRPConfigWorld.coloniesActivated || this.canChangeVariant) {
                if (ParasiteEventWorld.numberofColonies(this.field_70170_p) >= 1 || this.canChangeVariant) {
                    ParasiteEventEntity.checkColony(this.field_70170_p, cause, this);
                    ParasiteEventEntity.spawnNext(this, new EntityDod(this.field_70170_p), true, false);
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
        ParasiteEventWorld.setDisloWorldPhase(this.field_70170_p, SRPAttributes.EVENTPARANEXUSIID, SRPConfigSystems.chanceEventParaNexusIID, 0, null);
        return super.onDeathDislo(cause);
    }
}

