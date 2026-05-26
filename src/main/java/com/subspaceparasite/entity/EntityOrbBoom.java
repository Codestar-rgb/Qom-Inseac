/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
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
package com.subspaceparasite.entity;

import com.subspaceparasite.client.particle.ParticleSpawner;
import com.subspaceparasite.client.particle.SPEnumParticle;
import com.subspaceparasite.entity.ai.misc.EntityPCosmical;
import com.subspaceparasite.entity.ai.misc.EntityPMalleable;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.init.SPSounds;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
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

public class EntityOrbBoom
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
    EntityPMalleable father;
    private double str;
    public double offsetOrb;
    private double poosX;
    private double poosY;
    private double poosZ;
    private static final DataParameter<Integer> SELFE = EntityDataManager.func_187226_a(EntityOrbBoom.class, (DataSerializer)DataSerializers.field_187192_b);
    private static final DataParameter<Integer> FUSE = EntityDataManager.func_187226_a(EntityOrbBoom.class, (DataSerializer)DataSerializers.field_187192_b);
    private static final DataParameter<Integer> WAITSTART = EntityDataManager.func_187226_a(EntityOrbBoom.class, (DataSerializer)DataSerializers.field_187192_b);
    public float alpha;

    public EntityOrbBoom(World worldIn) {
        super(worldIn);
        this.func_70105_a(0.5f, 0.5f);
        this.field_70158_ak = true;
        this.field_70178_ae = true;
        this.lastActiveTime = 0;
        this.timeSinceIgnited = 0;
        this.setFuseState(7);
        this.setStartState(40);
        this.func_189654_d(false);
        this.alpha = 1.0f;
        this.str = 0.2;
    }

    public EntityOrbBoom(World worldIn, EntityPMalleable in, int fuse, int waitStart) {
        this(worldIn);
        if (in != null) {
            this.father = in;
            this.prevRenderYawOffset = in.field_70760_ar;
            this.renderYawOffset = in.field_70761_aq;
            this.prevRotationYawHead = in.field_70758_at;
            this.rotationYawHead = in.field_70759_as;
            this.prevLimbSwingAmount = in.field_184618_aE;
            this.limbSwingAmount = in.field_70721_aZ;
            this.limbSwing = in.field_184619_aG;
        }
        this.setFuseState(fuse);
        this.setStartState(waitStart);
    }

    public EntityOrbBoom(World worldIn, EntityPMalleable in, int fuse, int waitStart, boolean stayPY) {
        this(worldIn, in, fuse, waitStart);
    }

    protected void func_70088_a() {
        this.field_70180_af.func_187214_a(SELFE, (Object)-1);
        this.field_70180_af.func_187214_a(FUSE, (Object)-1);
        this.field_70180_af.func_187214_a(WAITSTART, (Object)-1);
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70173_aa > this.getStartState()) {
            this.orbDoing();
            this.setSelfeState(1);
            this.dyingBurst(true, 1);
            if (this.field_70170_p.field_72995_K) {
                if (this.father != null) {
                    this.prevRenderYawOffset = this.father.field_70760_ar;
                    this.renderYawOffset = this.father.field_70761_aq;
                    this.prevRotationYawHead = this.father.field_70758_at;
                    this.rotationYawHead = this.father.field_70759_as;
                }
                this.spawnOrbEffects(4);
                return;
            }
            this.field_70165_t = this.poosX;
            this.field_70163_u = this.poosY - this.field_70146_Z.nextDouble() * 0.1;
            this.field_70161_v = this.poosZ;
        } else {
            if (this.field_70170_p.field_72995_K) {
                this.spawnOrbEffects(4);
                return;
            }
            this.poosX = this.field_70165_t;
            this.poosY = this.field_70163_u;
            this.poosZ = this.field_70161_v;
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
        this.timeSinceIgnited += i * value;
        if (this.timeSinceIgnited < 0) {
            this.timeSinceIgnited = 0;
        }
        if (this.timeSinceIgnited >= this.getFuseState()) {
            this.timeSinceIgnited = this.getFuseState();
            this.selfExplode();
        } else {
            this.func_70105_a(this.field_70130_N + 1.0f, this.field_70131_O + 0.4f);
        }
    }

    protected void selfExplode() {
        this.setSelfeState(2);
        if (this.getSelfeState() == 2) {
            ++this.timerDDD;
            if (this.timerDDD > 1) {
                this.alpha = Math.max(this.alpha - 0.2f, 0.0f);
                if (!this.field_70170_p.field_72995_K) {
                    if (this.father != null) {
                        float f = this.field_70130_N / 2.0f;
                        float f1 = this.field_70131_O;
                        AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.field_70165_t - (double)f, this.field_70163_u - (double)f1, this.field_70161_v - (double)f, this.field_70165_t + (double)f, this.field_70163_u + (double)f1, this.field_70161_v + (double)f);
                        List moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
                        for (EntityLivingBase mob : moblist) {
                            if (mob instanceof EntityParasiteBase) continue;
                            this.father.attackEntityAsMobMinimum(mob, this.father.getMiniDamage() * 5.0f);
                        }
                    }
                } else {
                    int par = this.getFuseState();
                    par += par / 2;
                    double offsetX = (this.field_70170_p.field_73012_v.nextDouble() - 0.5) * (double)this.field_70130_N;
                    double offsetY = (this.field_70170_p.field_73012_v.nextDouble() - 0.5) * (double)this.field_70131_O;
                    double offsetZ = (this.field_70170_p.field_73012_v.nextDouble() - 0.5) * (double)this.field_70130_N;
                    double particleX = this.field_70165_t + offsetX;
                    double particleY = this.field_70163_u + offsetY;
                    double particleZ = this.field_70161_v + offsetZ;
                    double particleVX = -this.field_70159_w + this.field_70170_p.field_73012_v.nextGaussian() * 0.05;
                    double particleVY = -this.field_70181_x + this.field_70170_p.field_73012_v.nextGaussian() * 0.05;
                    double particleVZ = -this.field_70179_y + this.field_70170_p.field_73012_v.nextGaussian() * 0.05;
                    this.field_70170_p.func_175688_a(EnumParticleTypes.EXPLOSION_HUGE, particleX, particleY, particleZ, particleVX, particleVY, particleVZ, new int[0]);
                }
                this.func_184185_a(SPSounds.ORB_E, 1.0f, 1.0f);
                if (this.timerDDD > 5) {
                    this.func_70106_y();
                }
            }
        }
    }

    private void orbDoing() {
        if (this.field_70170_p.field_72995_K) {
            return;
        }
        if (this.field_70173_aa % 10 != 0) {
            return;
        }
        float f = this.field_70130_N / 2.0f;
        float f1 = this.field_70131_O;
        AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.field_70165_t - (double)f, this.field_70163_u - (double)f1, this.field_70161_v - (double)f, this.field_70165_t + (double)f, this.field_70163_u + (double)f1, this.field_70161_v + (double)f);
        List moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
        if (this.father == null) {
            for (EntityLivingBase mob : moblist) {
                mob.func_70097_a(DamageSource.field_76376_m, 10.0f);
            }
            return;
        }
        for (EntityLivingBase mob : moblist) {
            if (mob instanceof EntityPCosmical) continue;
            this.father.func_70652_k((Entity)mob);
        }
    }

    public void func_70108_f(Entity entityIn) {
    }

    public AxisAlignedBB func_70046_E() {
        return new AxisAlignedBB(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
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
    public void spawnParticles(SPEnumParticle particleType, int r, int g, int b) {
        double d0 = this.field_70146_Z.nextGaussian() * 0.02;
        double d1 = this.field_70146_Z.nextGaussian() * 0.02;
        double d2 = this.field_70146_Z.nextGaussian() * 0.02;
        ParticleSpawner.spawnParticle(particleType, this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, this.field_70163_u + 0.5 + (double)(this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, d0, d1, d2, r, g, b);
    }

    @SideOnly(value=Side.CLIENT)
    public void spawnOrbEffects(int cap1) {
        for (int i = -cap1; i <= cap1; ++i) {
            for (int j = -cap1; j <= cap1; ++j) {
                if (i <= -2 || i >= 2 || j != -1) continue;
                j = 2;
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_70112_a(double distance) {
        return distance < 65536.0;
    }

    @SideOnly(value=Side.CLIENT)
    public float getSelfeFlashIntensity(float p_70831_1_) {
        return ((float)this.lastActiveTime + (float)(this.timeSinceIgnited - this.lastActiveTime) * p_70831_1_ * 5.0f) / (float)(this.getFuseState() - 2);
    }
}

