/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.RandomPositionGenerator
 *  net.minecraft.util.math.Vec3d
 */
package com.subspaceparasite.entity.ai;

import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.Vec3d;

public class EntityAIWanderStatus
extends EntityAIBase {
    protected final EntityParasiteBase entity;
    protected double x;
    protected double y;
    protected double z;
    protected final double speed;
    protected int executionChance;
    protected boolean mustUpdate;
    protected float probability;
    protected boolean avoidW;

    public EntityAIWanderStatus(EntityParasiteBase creatureIn, double speedIn, float prob, boolean avoidWater) {
        this(creatureIn, speedIn, 120, prob, avoidWater);
    }

    public EntityAIWanderStatus(EntityParasiteBase creatureIn, double speedIn, int chance, float prob, boolean aW) {
        this.entity = creatureIn;
        this.speed = speedIn;
        this.executionChance = chance;
        this.probability = prob;
        this.avoidW = aW;
        this.func_75248_a(1);
    }

    public boolean func_75250_a() {
        Vec3d vec3d;
        if (this.entity.getParasiteStatus() != 0) {
            return false;
        }
        if (!this.mustUpdate && this.executionChance > 0) {
            if (this.entity.func_70654_ax() >= 100) {
                return false;
            }
            if (this.entity.func_70681_au().nextInt(this.executionChance) != 0) {
                return false;
            }
        }
        if ((vec3d = this.getPosition()) == null) {
            return false;
        }
        this.x = vec3d.field_72450_a;
        this.y = vec3d.field_72448_b;
        this.z = vec3d.field_72449_c;
        this.mustUpdate = false;
        if (!this.entity.checkPositionWander(this.x, this.y, this.y)) {
            return false;
        }
        return this.entity.func_70661_as().func_75505_d() == null || this.executionChance == 0;
    }

    @Nullable
    protected Vec3d getPosition() {
        if (this.avoidW) {
            if (this.entity.func_70090_H()) {
                Vec3d vec3d = RandomPositionGenerator.func_191377_b((EntityCreature)this.entity, (int)15, (int)7);
                return vec3d == null ? RandomPositionGenerator.func_75463_a((EntityCreature)this.entity, (int)10, (int)7) : vec3d;
            }
            if (this.entity.getParasiteFollowing() == null) {
                return this.entity.func_70681_au().nextFloat() >= this.probability ? RandomPositionGenerator.func_191377_b((EntityCreature)this.entity, (int)10, (int)7) : RandomPositionGenerator.func_75463_a((EntityCreature)this.entity, (int)10, (int)7);
            }
            Random ran = new Random();
            if (ran.nextInt(3) == 0) {
                return this.entity.func_70681_au().nextFloat() >= this.probability ? RandomPositionGenerator.func_191377_b((EntityCreature)this.entity, (int)3, (int)2) : RandomPositionGenerator.func_75463_a((EntityCreature)this.entity, (int)3, (int)2);
            }
            return null;
        }
        if (this.entity.getParasiteFollowing() == null) {
            return RandomPositionGenerator.func_75463_a((EntityCreature)this.entity, (int)10, (int)7);
        }
        Random ran = new Random();
        if (ran.nextInt(3) == 0) {
            return RandomPositionGenerator.func_75463_a((EntityCreature)this.entity, (int)0, (int)0);
        }
        return null;
    }

    public boolean func_75253_b() {
        return !this.entity.func_70661_as().func_75500_f() && this.entity.getParasiteStatus() == 0;
    }

    public void func_75249_e() {
        this.entity.func_70661_as().func_75492_a(this.x, this.y, this.z, this.speed);
    }

    public void makeUpdate() {
        this.mustUpdate = true;
    }

    public void setExecutionValues(int newProb, float newChance) {
        this.executionChance = newProb;
        this.probability = newChance;
    }
}

