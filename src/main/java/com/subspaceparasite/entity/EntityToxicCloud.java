/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 *  net.minecraft.block.material.EnumPushReaction
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.init.PotionTypes
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.potion.PotionType
 *  net.minecraft.potion.PotionUtils
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.entity;

import com.subspaceparasite.client.particle.ParticleSpawner;
import com.subspaceparasite.client.particle.SPEnumParticle;
import com.subspaceparasite.entity.ai.misc.EntityPCosmical;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.init.SPPotions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.PotionTypes;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityToxicCloud
extends Entity {
    private static final DataParameter<Float> RADIUS = EntityDataManager.func_187226_a(EntityToxicCloud.class, (DataSerializer)DataSerializers.field_187193_c);
    private static final DataParameter<Float> HEIG = EntityDataManager.func_187226_a(EntityToxicCloud.class, (DataSerializer)DataSerializers.field_187193_c);
    private static final DataParameter<Byte> PART = EntityDataManager.func_187226_a(EntityToxicCloud.class, (DataSerializer)DataSerializers.field_187191_a);
    private static final DataParameter<Boolean> IGNORE_RADIUS = EntityDataManager.func_187226_a(EntityToxicCloud.class, (DataSerializer)DataSerializers.field_187198_h);
    private static final DataParameter<Integer> PARTICLE = EntityDataManager.func_187226_a(EntityToxicCloud.class, (DataSerializer)DataSerializers.field_187192_b);
    private static final DataParameter<Integer> PARTICLE_PARAM_1 = EntityDataManager.func_187226_a(EntityToxicCloud.class, (DataSerializer)DataSerializers.field_187192_b);
    private static final DataParameter<Integer> PARTICLE_PARAM_2 = EntityDataManager.func_187226_a(EntityToxicCloud.class, (DataSerializer)DataSerializers.field_187192_b);
    private PotionType potion = PotionTypes.field_185229_a;
    private final List<PotionEffect> effects = Lists.newArrayList();
    private final Map<Entity, Integer> reapplicationDelayMap = Maps.newHashMap();
    private int duration = 600;
    private int waitTime = 20;
    private int reapplicationDelay = 20;
    private float radiusOnUse;
    private float radiusPerTick;
    private EntityParasiteBase owner;
    private UUID ownerUniqueId;
    public int effectParticles;

    public EntityToxicCloud(World worldIn) {
        super(worldIn);
        this.field_70145_X = true;
        this.field_70178_ae = true;
        this.setRadius(3.0f, 0.5f);
    }

    public EntityToxicCloud(World worldIn, double x, double y, double z) {
        this(worldIn);
        this.func_70107_b(x, y, z);
    }

    protected void func_70088_a() {
        this.func_184212_Q().func_187214_a(PART, (Object)0);
        this.func_184212_Q().func_187214_a(RADIUS, (Object)Float.valueOf(0.5f));
        this.func_184212_Q().func_187214_a(HEIG, (Object)Float.valueOf(0.5f));
        this.func_184212_Q().func_187214_a(IGNORE_RADIUS, (Object)Boolean.FALSE);
        this.func_184212_Q().func_187214_a(PARTICLE, (Object)EnumParticleTypes.SPELL_MOB.func_179348_c());
        this.func_184212_Q().func_187214_a(PARTICLE_PARAM_1, (Object)0);
        this.func_184212_Q().func_187214_a(PARTICLE_PARAM_2, (Object)0);
    }

    public void setRadius(float radiusIn, float heightIn) {
        double d0 = this.field_70165_t;
        double d1 = this.field_70163_u;
        double d2 = this.field_70161_v;
        this.func_70105_a(radiusIn * 2.0f, heightIn);
        this.func_70107_b(d0, d1, d2);
        if (!this.field_70170_p.field_72995_K) {
            this.func_184212_Q().func_187227_b(RADIUS, (Object)Float.valueOf(radiusIn));
            this.func_184212_Q().func_187227_b(HEIG, (Object)Float.valueOf(heightIn));
        }
    }

    public float getRadius() {
        return ((Float)this.func_184212_Q().func_187225_a(RADIUS)).floatValue();
    }

    public float getHeight() {
        return ((Float)this.func_184212_Q().func_187225_a(HEIG)).floatValue();
    }

    public void setPotion(PotionType potionIn) {
        this.potion = potionIn;
    }

    public void addEffect(PotionEffect effect) {
        this.effects.add(effect);
    }

    public byte getColor() {
        return (Byte)this.func_184212_Q().func_187225_a(PART);
    }

    public void setColor(int colorIn) {
        this.func_184212_Q().func_187227_b(PART, (Object)((byte)colorIn));
    }

    public EnumParticleTypes getParticle() {
        return EnumParticleTypes.func_179342_a((int)((Integer)this.func_184212_Q().func_187225_a(PARTICLE)));
    }

    public void setParticle(EnumParticleTypes particleIn) {
        this.func_184212_Q().func_187227_b(PARTICLE, (Object)particleIn.func_179348_c());
    }

    public int getParticleParam1() {
        return (Integer)this.func_184212_Q().func_187225_a(PARTICLE_PARAM_1);
    }

    public void setParticleParam1(int particleParam) {
        this.func_184212_Q().func_187227_b(PARTICLE_PARAM_1, (Object)particleParam);
    }

    public int getParticleParam2() {
        return (Integer)this.func_184212_Q().func_187225_a(PARTICLE_PARAM_2);
    }

    public void setParticleParam2(int particleParam) {
        this.func_184212_Q().func_187227_b(PARTICLE_PARAM_2, (Object)particleParam);
    }

    protected void setIgnoreRadius(boolean ignoreRadius) {
        this.func_184212_Q().func_187227_b(IGNORE_RADIUS, (Object)ignoreRadius);
    }

    public boolean shouldIgnoreRadius() {
        return (Boolean)this.func_184212_Q().func_187225_a(IGNORE_RADIUS);
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int durationIn) {
        this.duration = durationIn;
    }

    public void func_70071_h_() {
        block29: {
            boolean flag1;
            float f;
            boolean flag;
            block27: {
                int[] aint;
                EnumParticleTypes enumparticletypes;
                block28: {
                    super.func_70071_h_();
                    flag = this.shouldIgnoreRadius();
                    f = this.getRadius();
                    if (!this.field_70170_p.field_72995_K) break block27;
                    enumparticletypes = this.getParticle();
                    aint = new int[enumparticletypes.func_179345_d()];
                    if (aint.length > 0) {
                        aint[0] = this.getParticleParam1();
                    }
                    if (aint.length > 1) {
                        aint[1] = this.getParticleParam2();
                    }
                    if (!flag) break block28;
                    if (this.field_70146_Z.nextInt(3) == 0) break block29;
                    for (int i = 0; i < 2; ++i) {
                        float f1 = this.field_70146_Z.nextFloat() * ((float)Math.PI * 2);
                        float f2 = MathHelper.func_76129_c((float)this.field_70146_Z.nextFloat()) * 0.2f;
                        float f3 = MathHelper.func_76134_b((float)f1) * f2;
                        float f4 = MathHelper.func_76126_a((float)f1) * f2;
                        if (enumparticletypes == EnumParticleTypes.SPELL_MOB) {
                            int j = this.field_70146_Z.nextBoolean() ? 0xFFFFFF : (int)this.getColor();
                            int k = j >> 16 & 0xFF;
                            int l = j >> 8 & 0xFF;
                            int i1 = j & 0xFF;
                            this.field_70170_p.func_190523_a(EnumParticleTypes.SPELL_MOB.func_179348_c(), this.field_70165_t + (double)f3, this.field_70163_u + (double)(this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double)f4, (double)((float)k / 255.0f), (double)((float)l / 255.0f), (double)((float)i1 / 255.0f), new int[0]);
                            continue;
                        }
                        this.field_70170_p.func_190523_a(enumparticletypes.func_179348_c(), this.field_70165_t + (double)f3, this.field_70163_u + (double)(this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double)f4, 0.0, 0.0, 0.0, aint);
                    }
                    break block29;
                }
                float f5 = (float)Math.PI * f * f;
                int k1 = 0;
                while ((float)k1 < f5) {
                    if (this.field_70146_Z.nextInt(3) == 0) {
                        float f6 = this.field_70146_Z.nextFloat() * ((float)Math.PI * 2);
                        float f7 = MathHelper.func_76129_c((float)this.field_70146_Z.nextFloat()) * f;
                        float f8 = MathHelper.func_76134_b((float)f6) * f7;
                        float f9 = MathHelper.func_76126_a((float)f6) * f7;
                        if (enumparticletypes == EnumParticleTypes.SPELL_MOB) {
                            byte l1 = this.getColor();
                            int i2 = l1 >> 16 & 0xFF;
                            int j2 = l1 >> 8 & 0xFF;
                            int j1 = l1 & 0xFF;
                            double d11 = this.field_70165_t + (double)f8 * 1.0;
                            double d22 = this.field_70163_u + (double)(this.field_70146_Z.nextFloat() * this.field_70131_O);
                            double d33 = this.field_70161_v + (double)f9 * 1.0;
                            double d44 = (float)i2 / 255.0f;
                            double d55 = (float)j2 / 255.0f;
                            double d66 = (float)j1 / 255.0f;
                            if (this.effectParticles == 1) {
                                this.field_70170_p.func_190523_a(EnumParticleTypes.FLAME.func_179348_c(), d11, d22, d33, d44, d55, d66, new int[0]);
                                this.spawnParticles(SPEnumParticle.GCLOUD, 0, 0, 0, d11, d22 + 0.5, d33, d44, d55, d66);
                            } else {
                                this.field_70170_p.func_190523_a(EnumParticleTypes.SPELL_MOB.func_179348_c(), d11, d22, d33, d44, d55, d66, new int[0]);
                            }
                        } else {
                            this.field_70170_p.func_190523_a(enumparticletypes.func_179348_c(), this.field_70165_t + (double)f8, this.field_70163_u + (double)(this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double)f9, (0.5 - this.field_70146_Z.nextDouble()) * 0.15, (double)0.01f, (0.5 - this.field_70146_Z.nextDouble()) * 0.15, aint);
                        }
                    }
                    ++k1;
                }
                break block29;
            }
            if (this.field_70173_aa >= this.waitTime + this.duration) {
                this.func_70106_y();
                return;
            }
            boolean bl = flag1 = this.field_70173_aa < this.waitTime;
            if (flag != flag1) {
                this.setIgnoreRadius(flag1);
            }
            if (flag1) {
                return;
            }
            if (this.radiusPerTick != 0.0f) {
                if ((f += this.radiusPerTick) < 0.5f) {
                    this.func_70106_y();
                    return;
                }
                this.setRadius(f, this.getHeight());
            }
            if (this.field_70173_aa % 5 == 0) {
                this.reapplicationDelayMap.entrySet().removeIf(entry -> this.field_70173_aa >= (Integer)entry.getValue());
                ArrayList potions = Lists.newArrayList();
                for (PotionEffect potioneffect1 : this.potion.func_185170_a()) {
                    potions.add(new PotionEffect(potioneffect1.func_188419_a(), potioneffect1.func_76459_b() / 4, potioneffect1.func_76458_c(), potioneffect1.func_82720_e(), potioneffect1.func_188418_e()));
                }
                potions.addAll(this.effects);
                if (potions.isEmpty()) {
                    this.reapplicationDelayMap.clear();
                } else {
                    List list = this.field_70170_p.func_72872_a(EntityLivingBase.class, this.func_174813_aQ());
                    if (!list.isEmpty()) {
                        for (EntityLivingBase entitylivingbase : list) {
                            double d1;
                            double d0;
                            double d2;
                            boolean pb = entitylivingbase instanceof EntityParasiteBase;
                            if (this.owner != null) {
                                if (!pb) {
                                    this.owner.attackEntityAsMobMinimum(entitylivingbase, this.owner.getMiniDamage() / 2.0f);
                                }
                                if (!(entitylivingbase instanceof EntityPCosmical)) {
                                    entitylivingbase.func_70097_a(DamageSource.field_188407_q, 10.0f);
                                }
                            }
                            if (pb || this.reapplicationDelayMap.containsKey(entitylivingbase) || !entitylivingbase.func_184603_cC() || !((d2 = (d0 = entitylivingbase.field_70165_t - this.field_70165_t) * d0 + (d1 = entitylivingbase.field_70161_v - this.field_70161_v) * d1) <= (double)(f * f))) continue;
                            this.reapplicationDelayMap.put((Entity)entitylivingbase, this.field_70173_aa + this.reapplicationDelay);
                            for (PotionEffect potioneffect : potions) {
                                if (potioneffect.func_188419_a().func_76403_b()) {
                                    potioneffect.func_188419_a().func_180793_a((Entity)this, (Entity)this.getOwner(), entitylivingbase, potioneffect.func_76458_c(), 0.5);
                                    continue;
                                }
                                SPPotions.applyStackPotion(potioneffect.func_188419_a(), entitylivingbase, potioneffect.func_76459_b(), potioneffect.func_76458_c());
                            }
                            if (this.radiusOnUse == 0.0f) continue;
                            if ((f += this.radiusOnUse) < 0.5f) {
                                this.func_70106_y();
                                return;
                            }
                            this.setRadius(f, this.getHeight());
                        }
                    }
                }
            }
        }
    }

    public void setRadiusOnUse(float radiusOnUseIn) {
        this.radiusOnUse = radiusOnUseIn;
    }

    public void setRadiusPerTick(float radiusPerTickIn) {
        this.radiusPerTick = radiusPerTickIn;
    }

    public void setWaitTime(int waitTimeIn) {
        this.waitTime = waitTimeIn;
    }

    public void setOwner(@Nullable EntityParasiteBase ownerIn) {
        this.owner = ownerIn;
        this.ownerUniqueId = ownerIn == null ? null : ownerIn.func_110124_au();
    }

    @Nullable
    public EntityParasiteBase getOwner() {
        Entity entity;
        if (this.owner == null && this.ownerUniqueId != null && this.field_70170_p instanceof WorldServer && (entity = ((WorldServer)this.field_70170_p).func_175733_a(this.ownerUniqueId)) instanceof EntityParasiteBase) {
            this.owner = (EntityParasiteBase)entity;
        }
        return this.owner;
    }

    public void func_70103_a(byte id) {
        if (id == 77) {
            this.effectParticles = 1;
        } else {
            super.func_70103_a(id);
        }
    }

    protected void func_70037_a(NBTTagCompound compound) {
        EnumParticleTypes enumparticletypes;
        this.field_70173_aa = compound.func_74762_e("Age");
        this.duration = compound.func_74762_e("Duration");
        this.waitTime = compound.func_74762_e("WaitTime");
        this.reapplicationDelay = compound.func_74762_e("ReapplicationDelay");
        this.radiusOnUse = compound.func_74760_g("RadiusOnUse");
        this.radiusPerTick = compound.func_74760_g("RadiusPerTick");
        this.setRadius(compound.func_74760_g("Radius"), compound.func_74760_g("heig"));
        this.ownerUniqueId = compound.func_186857_a("OwnerUUID");
        if (compound.func_150297_b("Particle", 8) && (enumparticletypes = EnumParticleTypes.func_186831_a((String)compound.func_74779_i("Particle"))) != null) {
            this.setParticle(enumparticletypes);
            this.setParticleParam1(compound.func_74762_e("ParticleParam1"));
            this.setParticleParam2(compound.func_74762_e("ParticleParam2"));
        }
        if (compound.func_150297_b("Color", 99)) {
            this.setColor(compound.func_74762_e("Color"));
        }
        if (compound.func_150297_b("Potion", 8)) {
            this.setPotion(PotionUtils.func_185187_c((NBTTagCompound)compound));
        }
        if (compound.func_150297_b("Effects", 9)) {
            NBTTagList nbttaglist = compound.func_150295_c("Effects", 10);
            this.effects.clear();
            for (int i = 0; i < nbttaglist.func_74745_c(); ++i) {
                PotionEffect potioneffect = PotionEffect.func_82722_b((NBTTagCompound)nbttaglist.func_150305_b(i));
                if (potioneffect == null) continue;
                this.addEffect(potioneffect);
            }
        }
    }

    protected void func_70014_b(NBTTagCompound compound) {
        compound.func_74768_a("Age", this.field_70173_aa);
        compound.func_74768_a("Duration", this.duration);
        compound.func_74768_a("WaitTime", this.waitTime);
        compound.func_74768_a("ReapplicationDelay", this.reapplicationDelay);
        compound.func_74776_a("RadiusOnUse", this.radiusOnUse);
        compound.func_74776_a("RadiusPerTick", this.radiusPerTick);
        compound.func_74776_a("Radius", this.getRadius());
        compound.func_74776_a("heig", this.getHeight());
        compound.func_74778_a("Particle", this.getParticle().func_179346_b());
        compound.func_74768_a("ParticleParam1", this.getParticleParam1());
        compound.func_74768_a("ParticleParam2", this.getParticleParam2());
        if (this.ownerUniqueId != null) {
            compound.func_186854_a("OwnerUUID", this.ownerUniqueId);
        }
        if (this.potion != PotionTypes.field_185229_a && this.potion != null) {
            compound.func_74778_a("Potion", ((ResourceLocation)PotionType.field_185176_a.func_177774_c((Object)this.potion)).toString());
        }
        if (!this.effects.isEmpty()) {
            NBTTagList nbttaglist = new NBTTagList();
            for (PotionEffect potioneffect : this.effects) {
                nbttaglist.func_74742_a((NBTBase)potioneffect.func_82719_a(new NBTTagCompound()));
            }
            compound.func_74782_a("Effects", (NBTBase)nbttaglist);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void spawnParticles(SPEnumParticle particleType, int r, int g, int b, double d0, double d1, double d2, double d3, double d4, double d5) {
        ParticleSpawner.spawnParticle(particleType, d0, d1, d2, d3, d4, d5, r, g, b);
    }

    public void func_184206_a(DataParameter<?> key) {
        if (RADIUS.equals(key)) {
            this.setRadius(this.getRadius(), this.getHeight());
        }
        super.func_184206_a(key);
    }

    public EnumPushReaction getMobilityFlag() {
        return EnumPushReaction.IGNORE;
    }
}

