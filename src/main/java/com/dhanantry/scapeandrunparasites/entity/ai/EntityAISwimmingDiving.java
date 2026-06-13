/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.pathfinding.PathNavigateFlying
 *  net.minecraft.pathfinding.PathNavigateGround
 */
package com.dhanantry.scapeandrunparasites.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.pathfinding.PathNavigateGround;

public class EntityAISwimmingDiving
extends EntityAIBase {
    private final EntityLiving parent;
    private double yMotion;

    public EntityAISwimmingDiving(EntityLiving parentIn, double y) {
        this.parent = parentIn;
        this.func_75248_a(4);
        this.yMotion = y;
        if (parentIn.func_70661_as() instanceof PathNavigateGround) {
            ((PathNavigateGround)parentIn.func_70661_as()).func_179693_d(true);
        } else if (parentIn.func_70661_as() instanceof PathNavigateFlying) {
            ((PathNavigateFlying)parentIn.func_70661_as()).func_192877_c(true);
        }
    }

    public boolean func_75250_a() {
        if (this.parent.func_70090_H() || this.parent.func_180799_ab()) {
            EntityLivingBase target;
            if (this.parent.func_70638_az() != null && ((target = this.parent.func_70638_az()).func_70090_H() || target.func_180799_ab()) && target.func_70092_e(this.parent.field_70165_t, target.field_70163_u, this.parent.field_70161_v) < 25.0 && target.field_70163_u - this.parent.field_70163_u < -1.0) {
                this.parent.field_70181_x -= this.yMotion;
                return false;
            }
            return true;
        }
        return false;
    }

    public void func_75246_d() {
        if (this.parent.func_70681_au().nextFloat() < 0.8f) {
            this.parent.func_70683_ar().func_75660_a();
        }
    }
}

