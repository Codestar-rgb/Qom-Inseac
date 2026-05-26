/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.world.World
 */
package com.subspaceparasite.entity.projectile;

import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.entity.projectile.EntityNade;
import com.subspaceparasite.entity.projectile.EntitySPProjectile;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityProjectileNade
extends EntitySPProjectile {
    private int duration;
    private int fuse;

    public EntityProjectileNade(World worldIn) {
        super(worldIn);
        this.func_70105_a(0.3f, 0.3f);
    }

    public EntityProjectileNade(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ, int fuse, int duration) {
        super(worldIn, shooter, accelX, accelY, accelZ);
        this.func_70105_a(0.3f, 0.3f);
        this.fuse = fuse;
        this.duration = duration;
    }

    protected EnumParticleTypes func_184563_j() {
        return EnumParticleTypes.SLIME;
    }

    protected void func_70227_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70235_a == null) {
                return;
            }
            if (!this.field_70235_a.func_70089_S()) {
                return;
            }
            EntityNade nade = new EntityNade(this.field_70170_p, this.fuse, this.duration);
            nade.setFatherS((EntityParasiteBase)this.field_70235_a);
            nade.func_82149_j((Entity)this);
            this.field_70170_p.func_72838_d((Entity)nade);
            this.func_70106_y();
        }
    }
}

