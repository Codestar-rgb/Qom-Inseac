/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.MoverType
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.dhanantry.scapeandrunparasites.entity.projectile;

import com.dhanantry.scapeandrunparasites.client.particle.ParticleSpawner;
import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityNade
extends Entity {
    public float prevRenderYawOffset;
    public float renderYawOffset;
    public float prevRotationYawHead;
    public float rotationYawHead;
    public float prevLimbSwingAmount;
    public float limbSwingAmount;
    public float limbSwing;
    public int hurtTime;
    public int deathTime;
    protected int lastActiveTime;
    protected int timeSinceIgnited;
    protected int timerDDD;
    private EntityParasiteBase father;
    private double poosX;
    private double poosY;
    private double poosZ;
    private static final DataParameter<Integer> SELFE = EntityDataManager.func_187226_a(EntityNade.class, (DataSerializer)DataSerializers.field_187192_b);
    private static final DataParameter<Integer> FUSE = EntityDataManager.func_187226_a(EntityNade.class, (DataSerializer)DataSerializers.field_187192_b);
    private static final DataParameter<Integer> WAITSTART = EntityDataManager.func_187226_a(EntityNade.class, (DataSerializer)DataSerializers.field_187192_b);

    public EntityNade(World worldIn) {
        super(worldIn);
        this.func_70105_a(0.5f, 0.5f);
        this.field_70158_ak = true;
        this.field_70178_ae = true;
        this.lastActiveTime = 0;
        this.timeSinceIgnited = 0;
        this.setFuseState(3);
        this.setStartState(10);
    }

    public EntityNade(World worldIn, int fuse, int waitStart) {
        this(worldIn);
        this.setFuseState(fuse);
        this.setStartState(waitStart);
    }

    protected void func_70088_a() {
        this.field_70180_af.func_187214_a(SELFE, (Object)-1);
        this.field_70180_af.func_187214_a(FUSE, (Object)-1);
        this.field_70180_af.func_187214_a(WAITSTART, (Object)-1);
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70173_aa > 3) {
            this.setSelfeState(1);
            this.dyingBurst(true, 1);
            if (this.field_70170_p.field_72995_K) {
                int i;
                for (i = 0; i < 5; ++i) {
                    this.spawnParticles(EnumParticleTypes.SMOKE_NORMAL);
                }
                for (i = 0; i < 2; ++i) {
                    this.spawnParticles(EnumParticleTypes.SMOKE_LARGE);
                }
                return;
            }
            this.field_70165_t = this.poosX;
            if (this.field_70122_E) {
                this.field_70163_u += this.field_70146_Z.nextDouble() * 0.01;
            }
            this.field_70161_v = this.poosZ;
        } else {
            if (this.field_70170_p.field_72995_K) {
                int i;
                for (i = 0; i < 5; ++i) {
                    this.spawnParticles(EnumParticleTypes.SMOKE_NORMAL);
                }
                for (i = 0; i < 2; ++i) {
                    this.spawnParticles(EnumParticleTypes.SMOKE_LARGE);
                }
                return;
            }
            this.poosX = this.field_70165_t;
            this.poosZ = this.field_70161_v;
            if (this.field_70173_aa == 2) {
                this.func_184185_a(SRPSounds.NADE_S, 1.0f, 1.0f);
            }
        }
    }

    public int getStartState() {
        return (Integer)this.field_70180_af.func_187225_a(WAITSTART);
    }

    public void setStartState(int state) {
        this.field_70180_af.func_187227_b(WAITSTART, (Object)state);
    }

    public int getFuseState() {
        return (Integer)this.field_70180_af.func_187225_a(FUSE);
    }

    public void setFuseState(int state) {
        this.field_70180_af.func_187227_b(FUSE, (Object)state);
    }

    public int getSelfeState() {
        return (Integer)this.field_70180_af.func_187225_a(SELFE);
    }

    public void setSelfeState(int state) {
        this.field_70180_af.func_187227_b(SELFE, (Object)state);
    }

    protected void dyingBurst(boolean fromDeath, int value) {
        int i = this.getSelfeState();
        if (i <= 0 || this.timeSinceIgnited == 0) {
            // empty if block
        }
        this.timeSinceIgnited += i * value;
        if (this.timeSinceIgnited < 0) {
            this.timeSinceIgnited = 0;
        }
        if (this.timeSinceIgnited >= this.getFuseState()) {
            this.timeSinceIgnited = this.getFuseState();
            this.selfExplode();
            this.field_70169_q = this.field_70165_t;
            if (this.field_70122_E) {
                this.field_70167_r = this.field_70163_u;
            }
            this.field_70166_s = this.field_70161_v;
            if (!this.func_189652_ae()) {
                this.field_70181_x -= (double)0.04f;
            }
            this.func_70091_d(MoverType.SELF, this.field_70159_w, this.field_70181_x, this.field_70179_y);
            this.field_70159_w *= (double)0.98f;
            this.field_70181_x *= (double)0.98f;
            this.field_70179_y *= (double)0.98f;
            if (this.field_70122_E) {
                this.field_70159_w *= (double)0.7f;
                this.field_70179_y *= (double)0.7f;
                this.field_70181_x *= -0.5;
            }
        } else {
            this.func_70105_a(this.field_70130_N + 0.8f, this.field_70131_O + 0.32f);
        }
    }

    protected void selfExplode() {
        this.setSelfeState(2);
        if (this.getSelfeState() == 2) {
            ++this.timerDDD;
            if (!this.field_70170_p.field_72995_K) {
                if (this.father != null && this.father.func_70089_S()) {
                    float f = this.field_70130_N / 2.0f;
                    float f1 = this.field_70131_O;
                    AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.field_70165_t - (double)f, this.field_70163_u, this.field_70161_v - (double)f, this.field_70165_t + (double)f, this.field_70163_u + (double)f1, this.field_70161_v + (double)f);
                    List moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
                    for (EntityLivingBase mob : moblist) {
                        if (mob instanceof EntityParasiteBase) continue;
                        mob.func_70097_a(DamageSource.field_76377_j, (float)this.father.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e());
                        this.father.attackEntityAsMobMinimum(mob, this.father.getMiniDamage());
                    }
                }
            } else {
                int par = this.getFuseState();
                int n = par + par / 2;
            }
            if (this.timerDDD > this.getStartState()) {
                this.func_70106_y();
            }
        }
    }

    public void func_70108_f(Entity entityIn) {
        if (entityIn instanceof EntityPlayer && ((EntityPlayer)entityIn).field_71075_bZ.field_75098_d) {
            return;
        }
    }

    public AxisAlignedBB func_70046_E() {
        return new AxisAlignedBB(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
    }

    public void setFatherS(EntityParasiteBase in) {
        this.father = in;
    }

    protected void func_70037_a(NBTTagCompound compound) {
    }

    protected void func_70014_b(NBTTagCompound compound) {
    }

    @SideOnly(value=Side.CLIENT)
    public void spawnParticles(EnumParticleTypes particleType) {
        double d0 = this.field_70146_Z.nextGaussian() * 0.02;
        double d1 = this.field_70146_Z.nextGaussian() * 0.02;
        double d2 = this.field_70146_Z.nextGaussian() * 0.02;
        this.field_70170_p.func_175688_a(particleType, this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, this.field_70163_u + 0.5 + (double)(this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, d0, d1, d2, new int[0]);
    }

    @SideOnly(value=Side.CLIENT)
    public void spawnParticles(SRPEnumParticle particleType, int r, int g, int b) {
        double d0 = this.field_70146_Z.nextGaussian() * 0.02;
        double d1 = this.field_70146_Z.nextGaussian() * 0.02;
        double d2 = this.field_70146_Z.nextGaussian() * 0.02;
        ParticleSpawner.spawnParticle(particleType, this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, this.field_70163_u + 0.5 + (double)(this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, d0, d1, d2, r, g, b);
    }

    @SideOnly(value=Side.CLIENT)
    public void spawnOrbEffects(int cap1) {
        for (int i = -cap1; i <= cap1; ++i) {
            for (int j = -cap1; j <= cap1; ++j) {
                if (i > -2 && i < 2 && j == -1) {
                    j = 2;
                }
                if (this.field_70146_Z.nextInt(16) != 0) continue;
                for (int k = 0; k <= 5; ++k) {
                    ParticleSpawner.spawnParticle(SRPEnumParticle.EEN, this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5) * ((double)this.field_70130_N * 2.0), this.field_70163_u + this.field_70146_Z.nextDouble() * 2.0 * (double)this.field_70131_O, this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5) * ((double)this.field_70130_N * 2.0), (double)((float)i + this.field_70146_Z.nextFloat()) - 0.5, (float)k - this.field_70146_Z.nextFloat() - 1.0f, (double)((float)j + this.field_70146_Z.nextFloat()) - 0.5, 0, 0, 0);
                }
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public float getSelfeFlashIntensity(float p_70831_1_) {
        return ((float)this.lastActiveTime + (float)(this.timeSinceIgnited - this.lastActiveTime) * p_70831_1_ * 5.0f) / (float)(this.getFuseState() - 2);
    }
}

