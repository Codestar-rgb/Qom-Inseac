/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
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
package com.dhanantry.scapeandrunparasites.entity;

import com.dhanantry.scapeandrunparasites.client.particle.ParticleSpawner;
import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPCosmical;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPMalleable;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPPreeminent;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPStationary;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
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

public class EntityOrbVoid
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
    private boolean followF;
    private int rad;
    private double str;
    public double offsetOrb;
    private double poosX;
    private double poosY;
    private double poosZ;
    private static final DataParameter<Integer> SELFE = EntityDataManager.func_187226_a(EntityOrbVoid.class, (DataSerializer)DataSerializers.field_187192_b);
    private static final DataParameter<Integer> FUSE = EntityDataManager.func_187226_a(EntityOrbVoid.class, (DataSerializer)DataSerializers.field_187192_b);
    private static final DataParameter<Integer> WAITSTART = EntityDataManager.func_187226_a(EntityOrbVoid.class, (DataSerializer)DataSerializers.field_187192_b);
    private ArrayList<DataParameter<Integer>> tracking = new ArrayList();
    private static final DataParameter<Integer> TARGET_ENTITY1 = EntityDataManager.func_187226_a(EntityOrbVoid.class, (DataSerializer)DataSerializers.field_187192_b);
    private static final DataParameter<Integer> TARGET_ENTITY2 = EntityDataManager.func_187226_a(EntityOrbVoid.class, (DataSerializer)DataSerializers.field_187192_b);
    private static final DataParameter<Integer> TARGET_ENTITY3 = EntityDataManager.func_187226_a(EntityOrbVoid.class, (DataSerializer)DataSerializers.field_187192_b);
    private static final DataParameter<Integer> TARGET_ENTITY4 = EntityDataManager.func_187226_a(EntityOrbVoid.class, (DataSerializer)DataSerializers.field_187192_b);
    private static final DataParameter<Integer> TARGET_ENTITY5 = EntityDataManager.func_187226_a(EntityOrbVoid.class, (DataSerializer)DataSerializers.field_187192_b);
    private static final DataParameter<Integer> TARGET_ENTITY6 = EntityDataManager.func_187226_a(EntityOrbVoid.class, (DataSerializer)DataSerializers.field_187192_b);
    private static final DataParameter<Integer> TARGET_ENTITY7 = EntityDataManager.func_187226_a(EntityOrbVoid.class, (DataSerializer)DataSerializers.field_187192_b);
    private static final DataParameter<Integer> TARGET_ENTITY8 = EntityDataManager.func_187226_a(EntityOrbVoid.class, (DataSerializer)DataSerializers.field_187192_b);
    private static final DataParameter<Integer> TARGET_ENTITY9 = EntityDataManager.func_187226_a(EntityOrbVoid.class, (DataSerializer)DataSerializers.field_187192_b);

    public EntityOrbVoid(World worldIn) {
        super(worldIn);
        this.func_70105_a(0.5f, 0.5f);
        this.field_70158_ak = true;
        this.field_70178_ae = true;
        this.lastActiveTime = 0;
        this.timeSinceIgnited = 0;
        this.followF = false;
        this.setFuseState(7);
        this.setStartState(40);
        this.tracking.add(TARGET_ENTITY1);
        this.tracking.add(TARGET_ENTITY2);
        this.tracking.add(TARGET_ENTITY3);
        this.tracking.add(TARGET_ENTITY4);
        this.tracking.add(TARGET_ENTITY5);
        this.tracking.add(TARGET_ENTITY6);
        this.tracking.add(TARGET_ENTITY7);
        this.tracking.add(TARGET_ENTITY8);
        this.tracking.add(TARGET_ENTITY9);
        this.func_189654_d(false);
        this.rad = 40;
        this.str = 0.2;
    }

    public EntityOrbVoid(World worldIn, EntityPMalleable in, int fuse, int waitStart) {
        this(worldIn);
        this.father = in;
        this.prevRenderYawOffset = in.field_70760_ar;
        this.renderYawOffset = in.field_70761_aq;
        this.prevRotationYawHead = in.field_70758_at;
        this.rotationYawHead = in.field_70759_as;
        this.prevLimbSwingAmount = in.field_184618_aE;
        this.limbSwingAmount = in.field_70721_aZ;
        this.limbSwing = in.field_184619_aG;
        this.setFuseState(fuse);
        this.setStartState(waitStart);
    }

    public EntityOrbVoid(World worldIn, EntityPMalleable in, int fuse, int waitStart, boolean stayPY) {
        this(worldIn, in, fuse, waitStart);
        this.followF = stayPY;
    }

    protected void func_70088_a() {
        this.field_70180_af.func_187214_a(SELFE, (Object)-1);
        this.field_70180_af.func_187214_a(FUSE, (Object)-1);
        this.field_70180_af.func_187214_a(WAITSTART, (Object)-1);
        this.field_70180_af.func_187214_a(TARGET_ENTITY1, (Object)0);
        this.field_70180_af.func_187214_a(TARGET_ENTITY2, (Object)0);
        this.field_70180_af.func_187214_a(TARGET_ENTITY3, (Object)0);
        this.field_70180_af.func_187214_a(TARGET_ENTITY4, (Object)0);
        this.field_70180_af.func_187214_a(TARGET_ENTITY5, (Object)0);
        this.field_70180_af.func_187214_a(TARGET_ENTITY6, (Object)0);
        this.field_70180_af.func_187214_a(TARGET_ENTITY7, (Object)0);
        this.field_70180_af.func_187214_a(TARGET_ENTITY8, (Object)0);
        this.field_70180_af.func_187214_a(TARGET_ENTITY9, (Object)0);
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70173_aa > this.getStartState()) {
            this.orbDoing();
            this.setSelfeState(1);
            this.dyingBurst(true, 1);
            if (this.field_70170_p.field_72995_K) {
                for (int i = 0; i < 4; ++i) {
                    this.field_70170_p.func_175688_a(EnumParticleTypes.PORTAL, this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5) * ((double)this.field_70130_N * 3.0), this.field_70163_u + this.field_70146_Z.nextDouble() * (double)this.field_70131_O - 0.25, this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5) * ((double)this.field_70130_N * 3.0), (this.field_70146_Z.nextDouble() - 0.5) * 2.0, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5) * 2.0, new int[0]);
                }
                if (this.father != null) {
                    this.prevRenderYawOffset = this.father.field_70760_ar;
                    this.renderYawOffset = this.father.field_70761_aq;
                    this.prevRotationYawHead = this.father.field_70758_at;
                    this.rotationYawHead = this.father.field_70759_as;
                }
                this.spawnOrbEffects(4);
                return;
            }
            if (this.father != null) {
                if (this.father.func_70089_S() && this.followF) {
                    this.field_70165_t = this.father.field_70165_t;
                    this.field_70163_u = this.father.field_70163_u - this.field_70146_Z.nextDouble() * 0.1 + (double)this.father.field_70131_O + this.offsetOrb;
                    this.field_70161_v = this.father.field_70161_v;
                } else {
                    this.field_70165_t = this.poosX;
                    this.field_70163_u = this.poosY - this.field_70146_Z.nextDouble() * 0.1;
                    this.field_70161_v = this.poosZ;
                    if (this.followF) {
                        this.func_70106_y();
                    }
                }
            } else {
                this.field_70165_t = this.poosX;
                this.field_70163_u = this.poosY - this.field_70146_Z.nextDouble() * 0.1;
                this.field_70161_v = this.poosZ;
            }
        } else {
            if (this.field_70170_p.field_72995_K) {
                this.spawnOrbEffects(4);
                return;
            }
            if (this.father != null) {
                if (this.father.func_70089_S() && this.followF) {
                    this.poosX = this.father.field_70165_t;
                    this.poosY = this.father.field_70163_u + (double)this.father.field_70131_O + this.offsetOrb;
                    this.poosZ = this.father.field_70161_v;
                    this.field_70165_t = this.father.field_70165_t;
                    this.field_70163_u = this.father.field_70163_u - this.field_70146_Z.nextDouble() * 0.1 + (double)this.father.field_70131_O + this.offsetOrb;
                    this.field_70161_v = this.father.field_70161_v;
                } else {
                    if (this.followF) {
                        this.func_70106_y();
                    }
                    this.poosX = this.field_70165_t;
                    this.poosY = this.field_70163_u;
                    this.poosZ = this.field_70161_v;
                }
            } else {
                this.poosX = this.field_70165_t;
                this.poosY = this.field_70163_u;
                this.poosZ = this.field_70161_v;
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
        this.timeSinceIgnited += i * value;
        if (this.timeSinceIgnited < 0) {
            this.timeSinceIgnited = 0;
        }
        if (this.timeSinceIgnited >= this.getFuseState()) {
            this.timeSinceIgnited = this.getFuseState();
            this.selfExplode();
        } else {
            this.func_70105_a(this.field_70130_N + 0.8f, this.field_70131_O + 0.32f);
        }
    }

    protected void selfExplode() {
        this.setSelfeState(2);
        if (this.getSelfeState() == 2) {
            ++this.timerDDD;
            if (this.timerDDD > 80) {
                this.func_70105_a(Math.max(0.1f, this.field_70130_N - 0.8f), Math.max(0.1f, this.field_70131_O - 0.32f));
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
                    for (int i = 0; i <= par; ++i) {
                        this.field_70170_p.func_175688_a(EnumParticleTypes.PORTAL, this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5) * ((double)this.field_70130_N * 2.0), this.field_70163_u + this.field_70146_Z.nextDouble() * 2.0 * (double)this.field_70131_O, this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5) * ((double)this.field_70130_N * 2.0), this.field_70146_Z.nextGaussian(), 0.0, this.field_70146_Z.nextGaussian(), new int[]{0, 0, 0});
                    }
                }
                this.func_184185_a(SRPSounds.ORB_E, 1.0f, 1.0f);
                if (this.timerDDD > 90) {
                    this.func_70106_y();
                }
            }
        }
    }

    private void orbDoing() {
        for (EntityLivingBase target : this.getTargetedEntityVictims()) {
            this.pullEntity(target);
        }
        if (this.field_70170_p.field_72995_K) {
            return;
        }
        float f = this.field_70130_N / 2.0f;
        float f1 = this.field_70131_O;
        if (!this.field_70170_p.field_72995_K) {
            this.resetTargetedEntity();
            AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.field_70165_t - (double)f, this.field_70163_u - (double)f1, this.field_70161_v - (double)f, this.field_70165_t + (double)f, this.field_70163_u + (double)f1, this.field_70161_v + (double)f).func_186662_g((double)this.rad);
            List moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
            for (EntityLivingBase target : moblist) {
                if (this.father != null && this.father == target || target instanceof EntityPCosmical || target instanceof EntityPStationary || target instanceof EntityPPreeminent) continue;
                if (target instanceof EntityPlayer) {
                    EntityPlayer pla = (EntityPlayer)target;
                    if (((EntityPlayer)target).field_71075_bZ.field_75102_a) continue;
                    this.setTargetedEntity(target.func_145782_y());
                    continue;
                }
                this.pullEntity(target);
            }
        }
    }

    public void pullEntity(EntityLivingBase target) {
        double ti = target.func_70068_e((Entity)this);
        target.func_184210_p();
        if (ti < 4.0) {
            target.field_70165_t = this.field_70165_t;
            target.field_70163_u = this.field_70163_u;
            target.field_70161_v = this.field_70161_v;
            target.field_70159_w = 0.0;
            target.field_70181_x = 0.0;
            target.field_70179_y = 0.0;
        } else {
            double deltaX = this.field_70165_t - target.field_70165_t;
            double deltaY = this.field_70163_u - target.field_70163_u;
            double deltaZ = this.field_70161_v - target.field_70161_v;
            double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
            if (distance == 0.0) {
                return;
            }
            target.field_70159_w += (deltaX /= distance) * this.str;
            target.field_70181_x += (deltaY /= distance) * this.str;
            target.field_70179_y += (deltaZ /= distance) * this.str;
        }
        if (ti < 25.0 && this.father != null) {
            this.father.attackEntityAsMobMinimum(target, this.father.getMiniDamage() / 10.0f);
            target.func_70097_a(DamageSource.field_76376_m, 10.0f);
        }
    }

    public void resetTargetedEntity() {
        for (DataParameter<Integer> mob : this.tracking) {
            this.field_70180_af.func_187227_b(mob, (Object)0);
        }
    }

    public void setTargetedEntity(int entityId) {
        for (DataParameter<Integer> mob : this.tracking) {
            if ((Integer)this.field_70180_af.func_187225_a(mob) != 0) continue;
            this.field_70180_af.func_187227_b(mob, (Object)entityId);
            return;
        }
    }

    public ArrayList<EntityLivingBase> getTargetedEntityVictims() {
        ArrayList<EntityLivingBase> mobs = new ArrayList<EntityLivingBase>();
        for (DataParameter<Integer> mob : this.tracking) {
            Entity entity;
            if ((Integer)this.field_70180_af.func_187225_a(mob) == 0 || (entity = this.field_70170_p.func_73045_a(((Integer)this.field_70180_af.func_187225_a(mob)).intValue())) == null) continue;
            mobs.add((EntityLivingBase)entity);
        }
        return mobs;
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
    public boolean func_70112_a(double distance) {
        return distance < 65536.0;
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
                    this.field_70170_p.func_175688_a(EnumParticleTypes.PORTAL, this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5) * ((double)this.field_70130_N * 2.0), this.field_70163_u + this.field_70146_Z.nextDouble() * 2.0 * (double)this.field_70131_O, this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5) * ((double)this.field_70130_N * 2.0), (double)((float)i + this.field_70146_Z.nextFloat()) - 0.5, (double)((float)k - this.field_70146_Z.nextFloat() - 1.0f), (double)((float)j + this.field_70146_Z.nextFloat()) - 0.5, new int[]{0, 0, 0});
                }
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public float getSelfeFlashIntensity(float p_70831_1_) {
        return ((float)this.lastActiveTime + (float)(this.timeSinceIgnited - this.lastActiveTime) * p_70831_1_ * 5.0f) / (float)(this.getFuseState() - 2);
    }
}

