/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 */
package com.dhanantry.scapeandrunparasites.entity.ai;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIAttackMeleeRangeSwitch
extends EntityAIBase {
    private EntityParasiteBase parent;
    private float distance;
    private boolean generation;

    public EntityAIAttackMeleeRangeSwitch(EntityParasiteBase in, float meleeDistance) {
        this.parent = in;
        this.distance = meleeDistance * meleeDistance;
        this.generation = false;
    }

    public EntityAIAttackMeleeRangeSwitch(EntityParasiteBase in, float meleeDistance, boolean checkGen) {
        this.parent = in;
        this.distance = meleeDistance * meleeDistance;
        this.generation = checkGen;
    }

    public boolean func_75250_a() {
        if (this.parent.srpTicks >= 10) {
            return false;
        }
        if (this.generation) {
            return this.parent.func_70638_az() != null && this.parent.getGeneMod(5);
        }
        if (this.parent.func_70638_az() != null) {
            return true;
        }
        this.parent.setWorkTask(true);
        return false;
    }

    public void func_75246_d() {
        if (this.parent.func_70638_az() != null) {
            EntityLivingBase entitylivingbase = this.parent.func_70638_az();
            if (entitylivingbase.func_70068_e((Entity)this.parent) < (double)this.distance && this.parent.func_70685_l((Entity)entitylivingbase)) {
                this.parent.setWorkTask(true);
            } else {
                this.parent.setWorkTask(false);
            }
        }
    }
}

