/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.entity.monster;

import com.subspaceparasite.client.particle.SPEnumParticle;
import com.subspaceparasite.entity.ai.misc.EntityCanSummon;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.init.SPPotions;
import com.subspaceparasite.init.SPSounds;
import com.subspaceparasite.util.config.SPConfigSystems;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityBiomass
extends EntityParasiteBase {
    private int fuse;
    private EntityParasiteBase entityin;
    private EntityLivingBase target;
    private String parasite;
    private int point;
    private static final DataParameter<Float> STAGE = EntityDataManager.func_187226_a(EntityBiomass.class, (DataSerializer)DataSerializers.field_187193_c);
    private boolean payfather;
    private float currentWidth;
    private float currentHeight;
    private float capWidth;
    private float capHeight;
    private float growWidth;
    private float growHeight;

    public EntityBiomass(World worldIn) {
        super(worldIn);
        this.func_70105_a(0.98f, 0.98f);
        this.field_70714_bg.func_85156_a((EntityAIBase)this.folow);
        this.field_70714_bg.func_85156_a((EntityAIBase)this.aiWander);
        this.fuse = 777;
        this.field_70156_m = true;
        this.type = (byte)100;
        this.currentWidth = 0.98f;
        this.currentHeight = 0.98f;
        this.capWidth = 0.98f;
        this.capHeight = 0.98f;
    }

    public EntityBiomass(World worldIn, double x, double y, double z) {
        this(worldIn);
        this.func_70107_b(x, y, z);
        float f = (float)(Math.random() * (Math.PI * 2));
        this.field_70159_w = -((float)Math.sin(f)) * 0.02f;
        this.field_70181_x = 0.2f;
        this.field_70179_y = -((float)Math.cos(f)) * 0.02f;
        this.setFuse(80);
        this.field_70169_q = x;
        this.field_70167_r = y;
        this.field_70166_s = z;
    }

    public EntityBiomass(World worldIn, EntityParasiteBase fatherEntity, float stage, EntityLivingBase target, boolean payfather) {
        this(worldIn);
        this.entityin = fatherEntity;
        this.setStage(stage);
        this.target = target;
        this.payfather = payfather;
    }

    public EntityBiomass(World worldIn, EntityParasiteBase entityin, EntityLivingBase target) {
        this(worldIn);
        this.entityin = entityin;
        this.target = target;
        this.setStage(1.0f);
        this.payfather = true;
    }

    @Override
    public int getParasiteIDRegister() {
        return 205;
    }

    public void func_180430_e(float distance, float damageMultiplier) {
        if (distance >= 18.0f) {
            super.func_180430_e(distance, damageMultiplier);
        }
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(15.0);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(0.0);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(0.0);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(0.0);
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(STAGE, (Object)Float.valueOf(0.0f));
    }

    @Override
    public void func_70636_d() {
        block34: {
            block33: {
                super.func_70636_d();
                if (!this.field_70170_p.field_72995_K) {
                    if (!this.field_70128_L && this.func_110143_aJ() > 0.0f) {
                        if (this.func_110143_aJ() < this.func_110138_aP()) {
                            this.func_70606_j(this.func_110143_aJ() + 0.1f);
                        }
                    } else {
                        return;
                    }
                }
                if (!this.field_70170_p.field_72995_K) break block33;
                switch ((int)this.getStage()) {
                    case 1: {
                        if (this.field_70173_aa % 10 != 0) break;
                        for (int i = 0; i <= 1; ++i) {
                            this.spawnParticles(SPEnumParticle.BIOMASS, 0, 0, 0);
                        }
                        break block34;
                    }
                    case 2: {
                        if (this.field_70173_aa % 5 != 0) break;
                        for (int i = 0; i <= 1; ++i) {
                            this.spawnParticles(SPEnumParticle.BIOMASS, 0, 0, 0);
                        }
                        break block34;
                    }
                    case 3: {
                        if (this.field_70173_aa % 3 != 0) break;
                        for (int i = 0; i <= 2; ++i) {
                            this.spawnParticles(SPEnumParticle.BIOMASS, 0, 0, 0);
                        }
                        break block34;
                    }
                    case 4: {
                        if (this.field_70173_aa % 10 != 0) break;
                        for (int i = 0; i <= 1; ++i) {
                            this.spawnParticles(SPEnumParticle.BIOMASS, 0, 0, 0);
                        }
                        break block34;
                    }
                    case 5: {
                        if (this.field_70173_aa % 10 != 0) break;
                        for (int i = 0; i <= 1; ++i) {
                            this.spawnParticles(SPEnumParticle.BIOMASS, 0, 0, 0);
                        }
                        break block34;
                    }
                    case 6: {
                        if (this.field_70173_aa % 5 != 0) break;
                        for (int i = 0; i <= 1; ++i) {
                            this.spawnParticles(SPEnumParticle.BIOMASS, 0, 0, 0);
                        }
                        break;
                    }
                }
                break block34;
            }
            if (this.field_70173_aa % 10 == 0) {
                this.applyCOTH(2);
            }
        }
        if (this.field_70122_E) {
            --this.fuse;
        }
        if (this.field_70173_aa >= 200) {
            --this.fuse;
        }
        if (this.fuse <= 0) {
            this.particleStatus((byte)7);
            this.func_70106_y();
            if (!this.field_70170_p.field_72995_K) {
                this.explode();
                this.field_70170_p.func_72960_a((Entity)this, (byte)18);
            }
        } else if (this.field_70122_E) {
            switch ((int)this.getStage()) {
                case 1: {
                    this.growWidth += 0.005f;
                    this.growHeight += 0.006f;
                    this.field_70170_p.func_72960_a((Entity)this, (byte)11);
                    break;
                }
                case 2: {
                    this.growWidth += 0.007f;
                    this.growHeight += 0.009f;
                    this.field_70170_p.func_72960_a((Entity)this, (byte)12);
                    break;
                }
                case 3: {
                    this.growWidth += 0.015f;
                    this.growHeight += 0.02f;
                    this.field_70170_p.func_72960_a((Entity)this, (byte)13);
                    break;
                }
                case 4: {
                    this.growWidth += 0.007f;
                    this.growHeight += 0.009f;
                    this.field_70170_p.func_72960_a((Entity)this, (byte)15);
                    break;
                }
                case 5: {
                    this.growWidth += 0.005f;
                    this.growHeight += 0.006f;
                    this.field_70170_p.func_72960_a((Entity)this, (byte)16);
                    break;
                }
                case 6: {
                    this.growWidth += 0.007f;
                    this.growHeight += 0.009f;
                    this.field_70170_p.func_72960_a((Entity)this, (byte)17);
                }
            }
        }
    }

    public void applyCOTH(int r) {
        AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0).func_186662_g((double)r);
        List moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
        for (EntityLivingBase mob : moblist) {
            if (mob == this || mob instanceof EntityParasiteBase) continue;
            mob.func_70690_d(new PotionEffect(SPPotions.COTH_E, 200, 1, false, false));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public float getGrowW() {
        return this.growWidth;
    }

    @SideOnly(value=Side.CLIENT)
    public float getGrowHeight() {
        return this.growHeight;
    }

    private boolean explode() {
        this.applyCOTH(2);
        this.func_184185_a(SPSounds.FLESH_PRIMITIVE, 1.0f, 1.0f);
        if (this.fuse == 777) {
            return false;
        }
        if (this.entityin == null) {
            return this.explode2();
        }
        EntityLiving entityout = (EntityLiving)EntityList.func_188429_b((ResourceLocation)new ResourceLocation(this.parasite), (World)this.field_70170_p);
        if (entityout == null) {
            return false;
        }
        entityout.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a((double)(16.0f + (this.getStage() - 1.0f) * 8.0f));
        entityout.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.entityin.field_70177_z, this.entityin.field_70125_A);
        entityout.func_180482_a(this.entityin.field_70170_p.func_175649_E(new BlockPos((Entity)entityout)), (IEntityLivingData)null);
        if (SPConfigSystems.rageEnable) {
            entityout.func_70690_d(new PotionEffect(SPPotions.RAGE_E, 1200, 1, false, false));
        }
        entityout.func_70690_d(new PotionEffect(SPPotions.DEBAR_E, 120000, 1, false, false));
        if (this.func_70027_ad()) {
            entityout.func_70606_j(entityout.func_110138_aP() * 0.5f);
            entityout.func_70015_d(8);
        }
        this.entityin.field_70170_p.func_72838_d((Entity)entityout);
        if (this.target != null) {
            entityout.func_70624_b(this.target);
        }
        if (this.payfather && this.entityin instanceof EntityCanSummon) {
            EntityCanSummon father = (EntityCanSummon)((Object)this.entityin);
            father.setActualParasites(this.point);
            father.addID(entityout.func_145782_y(), this.point);
        }
        return true;
    }

    private boolean explode2() {
        if (this.fuse == 777 || this.parasite == null) {
            return false;
        }
        EntityLiving entityout = (EntityLiving)EntityList.func_188429_b((ResourceLocation)new ResourceLocation(this.parasite), (World)this.field_70170_p);
        if (entityout == null) {
            return false;
        }
        entityout.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a((double)(16.0f + (this.getStage() - 1.0f) * 8.0f));
        entityout.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, this.field_70125_A);
        entityout.func_180482_a(this.field_70170_p.func_175649_E(new BlockPos((Entity)entityout)), (IEntityLivingData)null);
        if (SPConfigSystems.rageEnable) {
            entityout.func_70690_d(new PotionEffect(SPPotions.RAGE_E, 1200, 1, false, false));
        }
        entityout.func_70690_d(new PotionEffect(SPPotions.DEBAR_E, 120000, 1, false, false));
        if (this.func_70027_ad()) {
            entityout.func_70606_j(entityout.func_110138_aP() * 0.5f);
            entityout.func_70015_d(8);
        }
        this.field_70170_p.func_72838_d((Entity)entityout);
        if (this.target != null) {
            entityout.func_70624_b(this.target);
        }
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    private void explotionParticles() {
        double b = 0.0;
        switch ((int)this.getStage()) {
            case 2: {
                b = 0.3;
                break;
            }
            case 3: {
                b = 0.6;
                break;
            }
            case 4: {
                b = 0.3;
                break;
            }
            case 6: {
                b = 0.3;
            }
        }
        for (int i = 0; i <= 10; ++i) {
            double d0 = (float)this.field_70165_t + this.field_70146_Z.nextFloat();
            double d1 = (double)((float)this.field_70163_u + this.field_70146_Z.nextFloat()) + 1.0;
            double d2 = (float)this.field_70161_v + this.field_70146_Z.nextFloat();
            double d3 = d0 - this.field_70165_t;
            double d4 = d1 - this.field_70163_u;
            double d5 = d2 - this.field_70161_v;
            double d6 = MathHelper.func_76133_a((double)(d3 * d3 + d4 * d4 + d5 * d5));
            d3 /= d6;
            d4 /= d6;
            d5 /= d6;
            double d7 = 0.5 / (d6 / 1.0 + 0.1);
            d3 = d3 * (d7 *= (double)(this.field_70170_p.field_73012_v.nextFloat() * this.field_70170_p.field_73012_v.nextFloat() + 0.7f)) * b;
            d4 = d4 * d7 * (b * 0.5);
            d5 = d5 * d7 * b;
            this.spawnParticles(SPEnumParticle.BIOMASS, 0, 0, 0, d0, d1, d2, d3, d4, d5);
        }
    }

    public void setMotion(double xSpeedIn, double ySpeedIn, double zSpeedIn, double capX, double capY) {
        xSpeedIn = Math.min(xSpeedIn, capX);
        ySpeedIn = Math.min(ySpeedIn, capY);
        zSpeedIn = Math.min(zSpeedIn, capX);
        this.field_70159_w = xSpeedIn * (Math.random() * 2.0 - 1.0);
        this.field_70181_x = ySpeedIn;
        this.field_70179_y = zSpeedIn * (Math.random() * 2.0 - 1.0);
    }

    public void setSizeGrow(float width, float height, float capWidht, float capHeight) {
        this.currentWidth = width;
        this.currentHeight = height;
        this.capWidth = capWidht;
        this.capHeight = capHeight;
    }

    public void setFuse(int fuseIn) {
        this.fuse = fuseIn;
    }

    public void setParasite(String in, int points) {
        this.parasite = in;
        this.point = points;
    }

    public int getFuse() {
        return this.fuse;
    }

    @Override
    public int getSkin() {
        return super.getSkin();
    }

    @Override
    public void setSkin(int texture) {
        super.setSkin(texture);
    }

    public float getStage() {
        return ((Float)this.field_70180_af.func_187225_a(STAGE)).floatValue();
    }

    public void setStage(float in) {
        this.field_70180_af.func_187227_b(STAGE, (Object)Float.valueOf(in));
    }

    @Override
    public void func_70014_b(NBTTagCompound compound) {
        super.func_70014_b(compound);
        compound.func_74777_a("Fuse", (short)this.getFuse());
        if (this.entityin != null) {
            compound.func_74768_a("parasitefather", this.entityin.func_145782_y());
        }
        if (this.target != null) {
            compound.func_74768_a("parasitetarget", this.target.func_145782_y());
        }
        if (this.parasite != null) {
            compound.func_74778_a("parasiteparasite", this.parasite);
        }
        compound.func_74768_a("parasitepoint", this.point);
        compound.func_74757_a("parasitepayfather", this.payfather);
    }

    @Override
    public void func_70037_a(NBTTagCompound compound) {
        super.func_70037_a(compound);
        this.setFuse(compound.func_74765_d("Fuse"));
        if (compound.func_150297_b("parasitefather", 99) && this.field_70170_p.func_73045_a(compound.func_74762_e("parasitefather")) instanceof EntityParasiteBase) {
            this.entityin = (EntityParasiteBase)this.field_70170_p.func_73045_a(compound.func_74762_e("parasitefather"));
        }
        if (compound.func_150297_b("parasitetarget", 99)) {
            this.target = (EntityLivingBase)this.field_70170_p.func_73045_a(compound.func_74762_e("parasitetarget"));
        }
        if (compound.func_150297_b("parasiteparasite", 8)) {
            this.parasite = compound.func_74779_i("parasiteparasite").toString();
        }
        if (compound.func_150297_b("parasitepoint", 99)) {
            this.point = compound.func_74762_e("parasitepoint");
        }
        if (compound.func_150297_b("parasitepayfather", 99)) {
            this.payfather = compound.func_74767_n("parasitepayfather");
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_70103_a(byte id) {
        if (id == 11) {
            this.growWidth += 0.005f;
            this.growHeight += 0.006f;
        } else if (id == 12) {
            this.growWidth += 0.01f;
            this.growHeight += 0.012f;
        } else if (id == 13) {
            this.growWidth += 0.001f;
            this.growHeight += 0.001f;
        } else if (id == 15) {
            this.growWidth += 0.01f;
            this.growHeight += 0.012f;
        } else if (id == 16) {
            this.growWidth += 0.005f;
            this.growHeight += 0.006f;
        } else if (id == 17) {
            this.growWidth += 0.01f;
            this.growHeight += 0.012f;
        } else if (id == 18) {
            this.explotionParticles();
        } else {
            super.func_70103_a(id);
        }
    }
}

