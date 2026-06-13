/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.entity.projectile.ProjectileHelper
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.world.World
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.dhanantry.scapeandrunparasites.entity.projectile;

import com.dhanantry.scapeandrunparasites.entity.EntityOrbBoom;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.network.MsgQlipShake;
import com.dhanantry.scapeandrunparasites.network.SRPNetwork;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventWorld;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteMeteorCrash;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityMeteor
extends Entity {
    private int ticksAlive;
    private int ticksInAir;
    public double accelerationX;
    public double accelerationY;
    public double accelerationZ;
    public float prevRenderYawOffset;
    public float renderYawOffset;
    public float prevRotationYawHead;
    public float rotationYawHead;
    public float prevLimbSwingAmount;
    public float limbSwingAmount;
    public float limbSwing;
    public int hurtTime;
    public int deathTime;
    public static final DataParameter<Boolean> FATHER = EntityDataManager.func_187226_a(EntityMeteor.class, (DataSerializer)DataSerializers.field_187198_h);

    public EntityMeteor(World worldIn) {
        super(worldIn);
        this.func_70105_a(4.5f, 4.5f);
        this.field_70158_ak = true;
        this.setRoot(true);
    }

    public EntityMeteor(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
        this(worldIn);
        this.func_70012_b(x, y, z, this.field_70177_z, this.field_70125_A);
        this.func_70107_b(x, y, z);
        double d0 = MathHelper.func_76133_a((double)(accelX * accelX + accelY * accelY + accelZ * accelZ));
        this.accelerationX = accelX / d0 * 0.1;
        this.accelerationY = accelY / d0 * 0.1;
        this.accelerationZ = accelZ / d0 * 0.1;
    }

    public void func_70106_y() {
        super.func_70106_y();
    }

    protected void func_70088_a() {
        this.field_70180_af.func_187214_a(FATHER, (Object)false);
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_70112_a(double distance) {
        return distance < 65536.0;
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (!this.field_70170_p.field_72995_K && this.getRoot() && this.field_70173_aa % 20 == 0) {
            List players = this.field_70170_p.field_73010_i;
            for (EntityPlayer mob : players) {
                float str = EntityMeteor.getDistancePack(this.func_180425_c(), mob.func_180425_c(), 150);
                if (!(str > 0.0f)) continue;
                SRPNetwork.CHANNEL.sendTo((IMessage)new MsgQlipShake(20, 0, false, true, str * 2.0f), (EntityPlayerMP)mob);
            }
            if (this.field_70146_Z.nextInt(2) == 0) {
                double spread = 0.9;
                double vx = this.field_70159_w + (this.field_70170_p.field_73012_v.nextDouble() - 0.5) * spread;
                double vy = this.field_70181_x + (this.field_70170_p.field_73012_v.nextDouble() - 0.5) * spread;
                double vz = this.field_70179_y + (this.field_70170_p.field_73012_v.nextDouble() - 0.5) * spread;
                EntityMeteor fragment = new EntityMeteor(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, vx, vy, vz);
                fragment.setRoot(false);
                this.field_70170_p.func_72838_d((Entity)fragment);
            }
        }
        ++this.ticksInAir;
        RayTraceResult raytraceresult = ProjectileHelper.func_188802_a((Entity)this, (boolean)true, (this.ticksInAir >= 25 ? 1 : 0) != 0, (Entity)this);
        if (raytraceresult != null && !ForgeEventFactory.onProjectileImpact((Entity)this, (RayTraceResult)raytraceresult)) {
            this.onImpact();
        }
        this.field_70165_t += this.field_70159_w;
        this.field_70163_u += this.field_70181_x;
        this.field_70161_v += this.field_70179_y;
        ProjectileHelper.func_188803_a((Entity)this, (float)0.2f);
        float f = this.getMotionFactor();
        if (this.func_70090_H()) {
            for (int i = 0; i < 4; ++i) {
                float f1 = 0.25f;
                this.field_70170_p.func_175688_a(EnumParticleTypes.WATER_BUBBLE, this.field_70165_t - this.field_70159_w * 0.25, this.field_70163_u - this.field_70181_x * 0.25, this.field_70161_v - this.field_70179_y * 0.25, this.field_70159_w, this.field_70181_x, this.field_70179_y, new int[0]);
            }
            f = 0.8f;
        }
        this.field_70159_w += this.accelerationX;
        this.field_70181_x += this.accelerationY;
        this.field_70179_y += this.accelerationZ;
        this.field_70159_w *= (double)f;
        this.field_70181_x *= (double)f;
        this.field_70179_y *= (double)f;
        this.field_70170_p.func_175688_a(this.getParticleType(), this.field_70165_t, this.field_70163_u + 0.5, this.field_70161_v, 0.0, 0.0, 0.0, new int[0]);
        this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        if (this.ticksInAir > 1200 || this.field_70163_u <= 0.0) {
            this.onImpact();
        }
        if (this.field_70170_p.field_72995_K) {
            for (int i = 0; i < 5; ++i) {
                double offsetX = (this.field_70170_p.field_73012_v.nextDouble() - 0.5) * (double)this.field_70130_N;
                double offsetY = (this.field_70170_p.field_73012_v.nextDouble() - 0.5) * (double)this.field_70131_O * 4.0;
                double offsetZ = (this.field_70170_p.field_73012_v.nextDouble() - 0.5) * (double)this.field_70130_N;
                double particleX = this.field_70165_t + offsetX;
                double particleY = this.field_70163_u + offsetY + (double)this.field_70131_O * 0.5 * 2.0;
                double particleZ = this.field_70161_v + offsetZ;
                double particleVX = -this.field_70159_w + this.field_70170_p.field_73012_v.nextGaussian() * 0.05;
                double particleVY = -this.field_70181_x + this.field_70170_p.field_73012_v.nextGaussian() * 0.05;
                double particleVZ = -this.field_70179_y + this.field_70170_p.field_73012_v.nextGaussian() * 0.05;
                this.field_70170_p.func_175688_a(EnumParticleTypes.FLAME, particleX, particleY, particleZ, particleVX, particleVY, particleVZ, new int[0]);
                if (!this.getRoot()) {
                    this.field_70170_p.func_175688_a(EnumParticleTypes.EXPLOSION_LARGE, particleX, particleY, particleZ, particleVX, particleVY, particleVZ, new int[0]);
                    continue;
                }
                this.field_70170_p.func_175688_a(EnumParticleTypes.EXPLOSION_HUGE, particleX, particleY, particleZ, particleVX, particleVY, particleVZ, new int[0]);
            }
        }
    }

    protected void onImpact() {
        if (!this.field_70170_p.field_72995_K) {
            if (this.getRoot()) {
                ArrayList mobs = Lists.newArrayList();
                mobs.addAll(this.field_70170_p.field_72996_f);
                if (mobs.size() != 0) {
                    for (Entity mob : mobs) {
                        float str;
                        if (!(mob instanceof EntityLivingBase)) continue;
                        if (mob instanceof EntityPlayer && (str = EntityMeteor.getDistancePack(this.func_180425_c(), mob.func_180425_c(), 400)) > 0.0f) {
                            SRPNetwork.CHANNEL.sendTo((IMessage)new MsgQlipShake(150, 0, false, true, str * 8.0f), (EntityPlayerMP)mob);
                        }
                        if ((str = EntityMeteor.getDistancePack(this.func_180425_c(), mob.func_180425_c(), SRPConfigWorld.meteorDamage)) > 0.0f) {
                            mob.func_70097_a(DamageSource.field_82729_p, str * 450.0f);
                        }
                        if (!((double)(str = EntityMeteor.getDistancePack(this.func_180425_c(), mob.func_180425_c(), 800)) > 0.5)) continue;
                        ((EntityLivingBase)mob).func_70690_d(new PotionEffect(SRPPotions.COTH_E, 1200, 0, false, false));
                    }
                }
            }
            int rad = 40;
            if (!this.getRoot()) {
                rad = 8;
            }
            EntityOrbBoom orb = new EntityOrbBoom(this.field_70170_p, null, rad, 1);
            orb.func_82149_j(this);
            this.field_70170_p.func_72838_d((Entity)orb);
            WorldGenParasiteMeteorCrash ccc = new WorldGenParasiteMeteorCrash(false, this.getRoot() ? 5 : 1);
            ccc.func_180709_b(this.field_70170_p, new Random(), this.func_180425_c());
            if (this.getRoot() && SRPConfigWorld.originActivated) {
                ParasiteEventWorld.placeOriginInWorld(this.field_70170_p, new BlockPos(this.field_70165_t, this.field_70163_u, this.field_70161_v), SRPConfigWorld.originHealth, SRPConfigWorld.originRadius);
            }
            this.func_70106_y();
        }
    }

    protected EnumParticleTypes getParticleType() {
        return EnumParticleTypes.SMOKE_NORMAL;
    }

    protected float getMotionFactor() {
        return 0.95f;
    }

    public void func_70014_b(NBTTagCompound compound) {
        compound.func_74782_a("direction", (NBTBase)this.func_70087_a(new double[]{this.field_70159_w, this.field_70181_x, this.field_70179_y}));
        compound.func_74782_a("power", (NBTBase)this.func_70087_a(new double[]{this.accelerationX, this.accelerationY, this.accelerationZ}));
        compound.func_74768_a("life", this.ticksAlive);
        compound.func_74757_a("bigmet", this.getRoot());
    }

    public void func_70037_a(NBTTagCompound compound) {
        NBTTagList nbttaglist;
        if (compound.func_74764_b("bigmet")) {
            this.setRoot(compound.func_74767_n("bigmet"));
        }
        if (compound.func_150297_b("power", 9) && (nbttaglist = compound.func_150295_c("power", 6)).func_74745_c() == 3) {
            this.accelerationX = nbttaglist.func_150309_d(0);
            this.accelerationY = nbttaglist.func_150309_d(1);
            this.accelerationZ = nbttaglist.func_150309_d(2);
        }
        this.ticksAlive = compound.func_74762_e("life");
        if (compound.func_150297_b("direction", 9) && compound.func_150295_c("direction", 6).func_74745_c() == 3) {
            NBTTagList nbttaglist1 = compound.func_150295_c("direction", 6);
            this.field_70159_w = nbttaglist1.func_150309_d(0);
            this.field_70181_x = nbttaglist1.func_150309_d(1);
            this.field_70179_y = nbttaglist1.func_150309_d(2);
        }
    }

    public boolean func_70067_L() {
        return false;
    }

    public float func_70111_Y() {
        return 1.0f;
    }

    public boolean func_70097_a(DamageSource source, float amount) {
        if (this.func_180431_b(source)) {
            return false;
        }
        this.func_70018_K();
        return true;
    }

    public float func_70013_c() {
        return 1.0f;
    }

    public void lookAt(Entity in) {
        this.lookAt(in.field_70165_t, in.field_70163_u, in.field_70161_v);
    }

    public void lookAt(double x, double y, double z) {
        double dx = x - this.field_70165_t;
        double dy = y - (this.field_70163_u + (double)this.func_70047_e());
        double dz = z - this.field_70161_v;
        double yaw = Math.atan2(dz, dx) * 57.29577951308232 - 90.0;
        double distance = Math.sqrt(dx * dx + dz * dz);
        double pitch = -Math.atan2(dy, distance) * 57.29577951308232;
        this.field_70177_z = (float)yaw;
        this.field_70125_A = (float)pitch;
    }

    @SideOnly(value=Side.CLIENT)
    public int func_70070_b() {
        return 0xF000F0;
    }

    public void setRoot(boolean in) {
        this.field_70180_af.func_187227_b(FATHER, (Object)in);
    }

    public boolean getRoot() {
        return (Boolean)this.field_70180_af.func_187225_a(FATHER);
    }

    public static float getDistancePack(BlockPos pos1, BlockPos pos2, int maxDistance) {
        if (maxDistance <= 0) {
            return 0.0f;
        }
        double maxDistSq = maxDistance * maxDistance;
        double distSq = pos1.func_177951_i((Vec3i)pos2);
        double value = 1.0 - distSq / maxDistSq;
        return (float)Math.max(0.0, Math.min(1.0, value));
    }
}

