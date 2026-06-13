/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.pathfinding.PathNodeType
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.entity.ai;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.world.World;

public class EntityAIParasiteFollow
extends EntityAIBase {
    private final EntityParasiteBase tameable;
    private EntityLivingBase owner;
    World world;
    private final double followSpeed;
    private final PathNavigate petPathfinder;
    private int timeToRecalcPath;
    double minBlockDistance;
    double maxBlockDistance;
    private float oldWaterCost;
    private boolean canSendOrder;

    public EntityAIParasiteFollow(EntityParasiteBase tameableIn, double followSpeedIn, double mindistance, double maxdistance, boolean send) {
        this.tameable = tameableIn;
        this.world = tameableIn.field_70170_p;
        this.followSpeed = followSpeedIn;
        this.petPathfinder = tameableIn.func_70661_as();
        this.minBlockDistance = mindistance * mindistance;
        this.maxBlockDistance = maxdistance * maxdistance;
        this.canSendOrder = send;
        this.func_75248_a(3);
    }

    public boolean func_75250_a() {
        EntityParasiteBase entitylivingbase = this.tameable.getParasiteFollowing();
        if (entitylivingbase == null) {
            return false;
        }
        if (this.tameable.func_70068_e((Entity)entitylivingbase) < this.minBlockDistance) {
            return false;
        }
        this.owner = entitylivingbase;
        return true;
    }

    public boolean func_75253_b() {
        if (!this.owner.func_70089_S()) {
            this.tameable.setParasiteToFollow(null);
            return false;
        }
        return !this.petPathfinder.func_75500_f() && this.tameable.func_70068_e((Entity)this.owner) > this.maxBlockDistance;
    }

    public void func_75249_e() {
        this.timeToRecalcPath = 0;
        this.oldWaterCost = this.tameable.func_184643_a(PathNodeType.WATER);
        this.tameable.func_184644_a(PathNodeType.WATER, 0.0f);
    }

    public void func_75251_c() {
        this.petPathfinder.func_75499_g();
        this.tameable.func_184644_a(PathNodeType.WATER, this.oldWaterCost);
    }

    public void func_75246_d() {
        this.tameable.func_70671_ap().func_75651_a((Entity)this.owner, 10.0f, (float)this.tameable.func_70646_bf());
        if (this.tameable.func_70638_az() != null && this.canSendOrder && this.tameable.getParasiteFollowing() != null && this.tameable.getParasiteFollowing().func_70638_az() == null && !(this.tameable.func_70638_az() instanceof EntityParasiteBase)) {
            this.tameable.getParasiteFollowing().func_70624_b(this.tameable.func_70638_az());
        }
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = 10;
            if (!this.petPathfinder.func_75497_a((Entity)this.owner, this.followSpeed)) {
                this.tameable.setParasiteToFollow(null);
            }
        }
    }
}

