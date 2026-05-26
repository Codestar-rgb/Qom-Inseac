/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 */
package com.subspaceparasite.entity.ai;

import com.subspaceparasite.entity.ai.EntityAIAttackMeleeStatus;
import com.subspaceparasite.entity.ai.misc.EntityCutomAttack;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class EntityAIAttackMeleeStatusAOE
extends EntityAIAttackMeleeStatus {
    private double attack;
    private boolean ignoreM;

    public EntityAIAttackMeleeStatusAOE(EntityParasiteBase creature, double speedIn, boolean useLongMemory, double runningD, double attackD) {
        super(creature, speedIn, useLongMemory, runningD);
        this.attack = attackD * attackD;
        this.ignoreM = false;
    }

    public EntityAIAttackMeleeStatusAOE(EntityParasiteBase creature, double speedIn, boolean useLongMemory, double runningD, double attackD, boolean IM) {
        super(creature, speedIn, useLongMemory, runningD);
        this.attack = attackD * attackD;
        this.ignoreM = IM;
    }

    @Override
    protected void checkAndPerformAttack(EntityLivingBase target, double distance) {
        if (this.isTargetParasite(target)) {
            return;
        }
        if (distance <= this.attack && this.attacker.func_70685_l((Entity)target)) {
            if (!this.ignoreM) {
                this.attacker.func_70661_as().func_75497_a((Entity)target, 0.0);
            }
            if (this.attackTick <= 0) {
                this.attackTick = this.attacker.getAttackSpeed();
                EntityCutomAttack thisA = (EntityCutomAttack)((Object)this.attacker);
                thisA.attackEntityAsMobAOE((Entity)target);
            }
        }
    }
}

