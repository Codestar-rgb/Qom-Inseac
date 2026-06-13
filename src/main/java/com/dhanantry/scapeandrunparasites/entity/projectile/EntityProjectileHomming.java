/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityMoveHelper
 *  net.minecraft.entity.ai.EntityMoveHelper$Action
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTUtil
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.dhanantry.scapeandrunparasites.entity.projectile;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityProjectileHomming
extends EntityLiving {
    private float damage;
    private EntityLivingBase owner;
    private Entity target;
    @Nullable
    private UUID ownerUniqueId;
    private BlockPos ownerBlockPos;
    @Nullable
    private UUID targetUniqueId;
    private BlockPos targetBlockPos;

    public EntityProjectileHomming(World worldIn) {
        super(worldIn);
        this.func_70105_a(0.3125f, 0.3125f);
        this.field_70145_X = true;
        this.field_70765_h = new AIMoveControl(this);
        this.func_189654_d(true);
    }

    public SoundCategory func_184176_by() {
        return SoundCategory.HOSTILE;
    }

    @SideOnly(value=Side.CLIENT)
    public EntityProjectileHomming(World worldIn, double x, double y, double z, double motionXIn, double motionYIn, double motionZIn) {
        this(worldIn);
        this.func_70012_b(x, y, z, this.field_70177_z, this.field_70125_A);
        this.field_70159_w = motionXIn;
        this.field_70181_x = motionYIn;
        this.field_70179_y = motionZIn;
    }

    public EntityProjectileHomming(World worldIn, EntityLivingBase ownerIn, Entity targetIn, float damage) {
        this(worldIn);
        this.owner = ownerIn;
        BlockPos blockpos = new BlockPos((Entity)ownerIn);
        double d0 = (double)blockpos.func_177958_n() + 0.5;
        double d1 = (double)blockpos.func_177956_o() + 0.5;
        double d2 = (double)blockpos.func_177952_p() + 0.5;
        this.func_70012_b(d0, d1, d2, this.field_70177_z, this.field_70125_A);
        this.target = targetIn;
        this.damage = damage;
    }

    public void func_70014_b(NBTTagCompound compound) {
        super.func_70014_b(compound);
        if (this.owner != null) {
            BlockPos blockpos = new BlockPos((Entity)this.owner);
            NBTTagCompound nbttagcompound = NBTUtil.func_186862_a((UUID)this.owner.func_110124_au());
            nbttagcompound.func_74768_a("X", blockpos.func_177958_n());
            nbttagcompound.func_74768_a("Y", blockpos.func_177956_o());
            nbttagcompound.func_74768_a("Z", blockpos.func_177952_p());
            compound.func_74782_a("Owner", (NBTBase)nbttagcompound);
        }
        if (this.target != null) {
            BlockPos blockpos1 = new BlockPos(this.target);
            NBTTagCompound nbttagcompound1 = NBTUtil.func_186862_a((UUID)this.target.func_110124_au());
            nbttagcompound1.func_74768_a("X", blockpos1.func_177958_n());
            nbttagcompound1.func_74768_a("Y", blockpos1.func_177956_o());
            nbttagcompound1.func_74768_a("Z", blockpos1.func_177952_p());
            compound.func_74782_a("Target", (NBTBase)nbttagcompound1);
        }
    }

    public void func_70037_a(NBTTagCompound compound) {
        super.func_70037_a(compound);
        if (compound.func_150297_b("Owner", 10)) {
            NBTTagCompound nbttagcompound = compound.func_74775_l("Owner");
            this.ownerUniqueId = NBTUtil.func_186860_b((NBTTagCompound)nbttagcompound);
            this.ownerBlockPos = new BlockPos(nbttagcompound.func_74762_e("X"), nbttagcompound.func_74762_e("Y"), nbttagcompound.func_74762_e("Z"));
        }
        if (compound.func_150297_b("Target", 10)) {
            NBTTagCompound nbttagcompound1 = compound.func_74775_l("Target");
            this.targetUniqueId = NBTUtil.func_186860_b((NBTTagCompound)nbttagcompound1);
            this.targetBlockPos = new BlockPos(nbttagcompound1.func_74762_e("X"), nbttagcompound1.func_74762_e("Y"), nbttagcompound1.func_74762_e("Z"));
        }
    }

    public void func_70071_h_() {
        if (!this.field_70170_p.field_72995_K && this.field_70170_p.func_175659_aa() == EnumDifficulty.PEACEFUL) {
            this.func_70106_y();
        } else {
            super.func_70071_h_();
            if (!this.field_70170_p.field_72995_K) {
                if (this.target == null && this.targetUniqueId != null) {
                    this.func_70106_y();
                }
                if (this.owner == null && this.ownerUniqueId != null) {
                    this.func_70106_y();
                }
                if (this.target != null && !this.target.field_70128_L) {
                    this.field_70765_h.func_75642_a(this.target.field_70165_t, this.target.field_70163_u - (double)this.target.field_70131_O * 1.5, this.target.field_70161_v, 1.5);
                }
                AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0).func_186662_g(2.0);
                List moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
                boolean flag = false;
                for (EntityLivingBase mob : moblist) {
                    if (mob == null || mob instanceof EntityParasiteBase || mob instanceof EntityProjectileHomming) continue;
                    DamageSource damagesource = this.owner == null ? DamageSource.func_76356_a((Entity)this, (Entity)this) : DamageSource.func_76356_a((Entity)this, (Entity)this.owner);
                    if (flag) continue;
                    flag = mob.func_70097_a(damagesource, this.damage);
                }
                if (flag) {
                    this.func_70106_y();
                }
                if (this.field_70173_aa > 200) {
                    this.func_70106_y();
                }
            }
        }
    }

    public boolean func_70027_ad() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_70112_a(double distance) {
        return distance < 16384.0;
    }

    protected void bulletHit(RayTraceResult result) {
        if (result.field_72308_g == null) {
            ((WorldServer)this.field_70170_p).func_175739_a(EnumParticleTypes.SMOKE_NORMAL, this.field_70165_t, this.field_70163_u, this.field_70161_v, 7, 0.2, 0.2, 0.2, 0.0, new int[0]);
        } else {
            DamageSource damagesource;
            boolean flag;
            if (result.field_72308_g instanceof EntityParasiteBase || !(flag = result.field_72308_g.func_70097_a(damagesource = this.owner == null ? DamageSource.func_76356_a((Entity)this, (Entity)this) : DamageSource.func_76356_a((Entity)this, (Entity)this.owner), this.damage)) || result.field_72308_g instanceof EntityLivingBase) {
                // empty if block
            }
            this.func_70106_y();
        }
    }

    public boolean func_70067_L() {
        return true;
    }

    public boolean func_70097_a(DamageSource source, float amount) {
        if (!this.field_70170_p.field_72995_K) {
            this.func_184185_a(SoundEvents.field_187777_eQ, 1.0f, 1.0f);
            ((WorldServer)this.field_70170_p).func_175739_a(EnumParticleTypes.CRIT, this.field_70165_t, this.field_70163_u, this.field_70161_v, 15, 0.2, 0.2, 0.2, 0.0, new int[0]);
            this.func_70106_y();
        }
        return true;
    }

    class AIMoveControl
    extends EntityMoveHelper {
        public AIMoveControl(EntityProjectileHomming vex) {
            super((EntityLiving)vex);
        }

        public void func_75641_c() {
            if (this.field_188491_h == EntityMoveHelper.Action.MOVE_TO) {
                double d0 = this.field_75646_b - EntityProjectileHomming.this.field_70165_t;
                double d1 = this.field_75647_c - EntityProjectileHomming.this.field_70163_u;
                double d2 = this.field_75644_d - EntityProjectileHomming.this.field_70161_v;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if ((d3 = (double)MathHelper.func_76133_a((double)d3)) < EntityProjectileHomming.this.func_174813_aQ().func_72320_b()) {
                    this.field_188491_h = EntityMoveHelper.Action.WAIT;
                    EntityProjectileHomming.this.field_70159_w *= 0.5;
                    EntityProjectileHomming.this.field_70181_x *= 0.5;
                    EntityProjectileHomming.this.field_70179_y *= 0.5;
                } else {
                    EntityProjectileHomming.this.field_70159_w += d0 / d3 * 0.05 * this.field_75645_e;
                    EntityProjectileHomming.this.field_70181_x += d1 / d3 * 0.05 * this.field_75645_e;
                    EntityProjectileHomming.this.field_70179_y += d2 / d3 * 0.05 * this.field_75645_e;
                    if (EntityProjectileHomming.this.target == null) {
                        EntityProjectileHomming.this.field_70177_z = -((float)MathHelper.func_181159_b((double)EntityProjectileHomming.this.field_70159_w, (double)EntityProjectileHomming.this.field_70179_y)) * 57.295776f;
                    } else {
                        double d4 = ((EntityProjectileHomming)EntityProjectileHomming.this).target.field_70165_t - EntityProjectileHomming.this.field_70165_t;
                        double d5 = ((EntityProjectileHomming)EntityProjectileHomming.this).target.field_70161_v - EntityProjectileHomming.this.field_70161_v;
                        EntityProjectileHomming.this.field_70177_z = -((float)MathHelper.func_181159_b((double)d4, (double)d5)) * 57.295776f;
                    }
                }
            }
        }
    }
}

