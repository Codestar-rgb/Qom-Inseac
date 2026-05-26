/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  com.google.common.base.Predicates
 *  javax.annotation.Nullable
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.RandomPositionGenerator
 *  net.minecraft.pathfinding.Path
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.EntitySelectors
 *  net.minecraft.util.math.Vec3d
 */
package com.subspaceparasite.entity.ai;

import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.Vec3d;

public class EntityAIAvoidEntityStatus<T extends Entity>
extends EntityAIBase {
    private final Predicate<Entity> canBeSeenSelector = new Predicate<Entity>(){

        public boolean apply(@Nullable Entity p_apply_1_) {
            return p_apply_1_.func_70089_S() && EntityAIAvoidEntityStatus.this.entity.func_70635_at().func_75522_a(p_apply_1_) && !EntityAIAvoidEntityStatus.this.entity.func_184191_r(p_apply_1_);
        }
    };
    protected EntityParasiteBase entity;
    private final double farSpeed;
    protected T closestLivingEntity;
    private final float avoidDistance;
    private Path path;
    private final PathNavigate navigation;
    private final Class<T> classToAvoid;
    private final Predicate<? super T> avoidTargetSelector;

    public EntityAIAvoidEntityStatus(EntityParasiteBase entityIn, Class<T> classToAvoidIn, float avoidDistanceIn, double farSpeedIn) {
        this(entityIn, classToAvoidIn, Predicates.alwaysTrue(), avoidDistanceIn, farSpeedIn);
    }

    public EntityAIAvoidEntityStatus(EntityParasiteBase entityIn, Class<T> classToAvoidIn, Predicate<? super T> avoidTargetSelectorIn, float avoidDistanceIn, double farSpeedIn) {
        this.entity = entityIn;
        this.classToAvoid = classToAvoidIn;
        this.avoidTargetSelector = avoidTargetSelectorIn;
        this.avoidDistance = avoidDistanceIn;
        this.farSpeed = farSpeedIn;
        this.navigation = entityIn.func_70661_as();
        this.func_75248_a(1);
    }

    public boolean func_75250_a() {
        List list = this.entity.field_70170_p.func_175647_a(this.classToAvoid, this.entity.func_174813_aQ().func_72314_b((double)this.avoidDistance, 3.0, (double)this.avoidDistance), Predicates.and((Predicate[])new Predicate[]{EntitySelectors.field_188444_d, this.canBeSeenSelector, this.avoidTargetSelector}));
        if (list.isEmpty() || this.entity.shouldWorkTask()) {
            return false;
        }
        this.closestLivingEntity = (Entity)list.get(0);
        Vec3d vec3d = RandomPositionGenerator.func_75461_b((EntityCreature)this.entity, (int)16, (int)7, (Vec3d)new Vec3d(((Entity)this.closestLivingEntity).field_70165_t, ((Entity)this.closestLivingEntity).field_70163_u, ((Entity)this.closestLivingEntity).field_70161_v));
        if (vec3d == null) {
            return false;
        }
        if (this.closestLivingEntity.func_70092_e(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c) < this.closestLivingEntity.func_70068_e((Entity)this.entity)) {
            return false;
        }
        this.path = this.navigation.func_75488_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c);
        return this.path != null;
    }

    public boolean func_75253_b() {
        return !this.navigation.func_75500_f();
    }

    public void func_75249_e() {
        this.navigation.func_75484_a(this.path, this.farSpeed);
    }

    public void func_75251_c() {
        this.closestLivingEntity = null;
    }

    public void func_75246_d() {
        this.entity.func_70661_as().func_75489_a(this.farSpeed);
    }
}

