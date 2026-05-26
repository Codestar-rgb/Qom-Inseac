/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.MoverType
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityMoveHelper
 *  net.minecraft.entity.ai.EntityMoveHelper$Action
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.World
 */
package com.subspaceparasite.entity.monster.pure;

import com.subspaceparasite.entity.EntityParasiticScent;
import com.subspaceparasite.entity.ai.EntityAIFlightAttack;
import com.subspaceparasite.entity.ai.EntityAIFlightLimits;
import com.subspaceparasite.entity.ai.misc.EntityCanFly;
import com.subspaceparasite.entity.ai.misc.EntityPPure;
import com.subspaceparasite.init.SPSounds;
import com.subspaceparasite.util.SPAttributes;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigMobs;
import com.subspaceparasite.util.config.SPConfigSystems;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntitySoo
extends EntityPPure
implements EntityCanFly {
    private int scentCool;
    protected static final DataParameter<Byte> VEX_FLAGS = EntityDataManager.func_187226_a(EntitySoo.class, (DataSerializer)DataSerializers.field_187191_a);

    public EntitySoo(World worldIn) {
        super(worldIn);
        this.field_70765_h = new AIMoveControl(this);
        this.func_70105_a(1.9f, 2.6f);
        this.func_189654_d(true);
        this.field_70714_bg.func_85156_a((EntityAIBase)this.folow);
        this.field_70158_ak = true;
        this.borderOrb = -1;
        this.totalP = SPConfigMobs.alafhaTotalActiveMobs;
        this.mobID = new int[this.totalP + SPConfigMobs.alafhaLimit];
        this.mobPT = new int[this.totalP + SPConfigMobs.alafhaLimit];
        for (int i = 0; i < this.mobID.length; ++i) {
            this.mobID[i] = -777;
        }
        if (SPConfigMobs.alafhaMaxY != 256) {
            this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIFlightLimits(this, SPConfigMobs.alafhaMaxY, true));
        }
        this.adaptationCap = 0.95f;
        this.scentCool = 800;
    }

    @Override
    public int getParasiteIDRegister() {
        return 82;
    }

    protected void func_184651_r() {
        super.func_184651_r();
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIFlightAttack(this, 64.0));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new AIMoveRandom());
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SPAttributes.ALAFHA_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SPAttributes.ALAFHA_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SPAttributes.ALAFHA_KD_RESISTANCE);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SPConfig.pureFollow);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SPAttributes.ALAFHA_MELLE);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        if (this.field_70122_E && !this.field_70170_p.field_72995_K) {
            this.field_70765_h.func_75642_a(this.field_70165_t, this.field_70163_u + 5.0, this.field_70161_v, 0.5);
        }
        --this.scentCool;
        if (this.scentCool < 0 && this.srpTicks == 10 && SPConfigSystems.useScent && this.getLevelCreated() >= SPConfigSystems.deveScentUse) {
            List serverList = this.field_70170_p.field_72996_f;
            int count = 0;
            for (int x = 0; x < serverList.size(); ++x) {
                if (!(serverList.get(x) instanceof EntityParasiticScent)) continue;
                ++count;
            }
            if (count > SPConfigSystems.scentCap) {
                return;
            }
            if (this.func_70638_az() != null) {
                EntityParasiticScent sss = new EntityParasiticScent(this.field_70170_p);
                sss.func_82149_j((Entity)this.func_70638_az());
                sss.setTargetToKill(this.func_70638_az(), false);
                sss.setDieToE(true);
                sss.setCanFollow(true);
                this.field_70170_p.func_72838_d((Entity)sss);
                this.field_70708_bq = 0;
                this.scentCool = 800;
            }
        }
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        this.func_189654_d(true);
    }

    @Override
    public void func_180430_e(float distance, float damageMultiplier) {
    }

    public float func_70047_e() {
        return 1.6f;
    }

    protected SoundEvent func_184639_G() {
        return SPSounds.ALAFHA_GROWL;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        if (this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0) {
            return SPSounds.MOBSILENCE;
        }
        return SPSounds.ALAFHA_HURT;
    }

    protected SoundEvent func_184615_bR() {
        return SPSounds.ALAFHA_DEATH;
    }

    protected float func_70599_aP() {
        return 2.0f;
    }

    @Override
    public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
        return super.func_180482_a(difficulty, livingdata);
    }

    public void func_70091_d(MoverType type, double x, double y, double z) {
        super.func_70091_d(type, x, y, z);
        this.func_145775_I();
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(VEX_FLAGS, (Object)0);
    }

    private boolean getVexFlag(int mask) {
        byte i = (Byte)this.field_70180_af.func_187225_a(VEX_FLAGS);
        return (i & mask) != 0;
    }

    private void setVexFlag(int mask, boolean value) {
        int i = ((Byte)this.field_70180_af.func_187225_a(VEX_FLAGS)).byteValue();
        i = value ? (i |= mask) : (i &= ~mask);
        this.field_70180_af.func_187227_b(VEX_FLAGS, (Object)((byte)(i & 0xFF)));
    }

    class AIMoveRandom
    extends EntityAIBase {
        public AIMoveRandom() {
            this.func_75248_a(1);
        }

        public boolean func_75250_a() {
            double d2;
            double d1;
            EntityMoveHelper entitymovehelper = EntitySoo.this.func_70605_aq();
            if (!entitymovehelper.func_75640_a()) {
                return true;
            }
            double d0 = entitymovehelper.func_179917_d() - EntitySoo.this.field_70165_t;
            double d3 = d0 * d0 + (d1 = entitymovehelper.func_179919_e() - EntitySoo.this.field_70163_u) * d1 + (d2 = entitymovehelper.func_179918_f() - EntitySoo.this.field_70161_v) * d2;
            return d3 < 1.0 || d3 > 3600.0;
        }

        public boolean func_75253_b() {
            return false;
        }

        public void func_75246_d() {
            if (EntitySoo.this.func_70638_az() != null) {
                BlockPos blockpos = new BlockPos((Entity)EntitySoo.this);
                int flag = 1;
                double speed = 0.11;
                if (EntitySoo.this.func_70068_e((Entity)EntitySoo.this.func_70638_az()) > 400.0) {
                    blockpos = new BlockPos((Entity)EntitySoo.this.func_70638_az());
                    flag = 2;
                    speed += 0.11;
                } else if (EntitySoo.this.func_70068_e((Entity)EntitySoo.this.func_70638_az()) < 100.0) {
                    blockpos = new BlockPos((Entity)EntitySoo.this.func_70638_az());
                    flag = 3;
                    speed += 0.11;
                }
                for (int i = 0; i < 3; ++i) {
                    BlockPos blockpos1 = blockpos.func_177982_a(EntitySoo.this.field_70146_Z.nextInt(15) - 7, EntitySoo.this.field_70146_Z.nextInt(9) - 5, EntitySoo.this.field_70146_Z.nextInt(15) - 7);
                    if (flag == 2) {
                        blockpos1 = blockpos.func_177982_a(EntitySoo.this.field_70146_Z.nextInt(6) - 2, EntitySoo.this.field_70146_Z.nextInt(7) - 2, EntitySoo.this.field_70146_Z.nextInt(6) - 2);
                    } else if (flag == 3) {
                        blockpos1 = blockpos.func_177982_a(EntitySoo.this.field_70146_Z.nextInt(4) + 3, EntitySoo.this.field_70146_Z.nextInt(5) + 4, EntitySoo.this.field_70146_Z.nextInt(4) + 3);
                    }
                    if (!EntitySoo.this.field_70170_p.func_175623_d(blockpos1)) continue;
                    EntitySoo.this.field_70765_h.func_75642_a((double)blockpos1.func_177958_n() + 0.5, (double)blockpos1.func_177956_o() + 0.5, (double)blockpos1.func_177952_p() + 0.5, speed);
                    if (EntitySoo.this.func_70638_az() == null) {
                        EntitySoo.this.func_70671_ap().func_75650_a((double)blockpos1.func_177958_n() + 0.5, (double)blockpos1.func_177956_o() + 0.5, (double)blockpos1.func_177952_p() + 0.5, 180.0f, 20.0f);
                    }
                    break;
                }
            } else {
                Random random = EntitySoo.this.func_70681_au();
                double d0 = EntitySoo.this.field_70165_t + (double)((random.nextFloat() * 2.0f - 1.0f) * 16.0f);
                double d1 = EntitySoo.this.field_70163_u + (double)((random.nextFloat() * 2.0f - 1.0f) * 16.0f);
                double d2 = EntitySoo.this.field_70161_v + (double)((random.nextFloat() * 2.0f - 1.0f) * 16.0f);
                EntitySoo.this.func_70605_aq().func_75642_a(d0, d1, d2, 0.5);
            }
        }
    }

    class AIMoveControl
    extends EntityMoveHelper {
        public AIMoveControl(EntitySoo vex) {
            super((EntityLiving)vex);
        }

        public void func_75641_c() {
            if (this.field_188491_h == EntityMoveHelper.Action.MOVE_TO) {
                double d0 = this.field_75646_b - EntitySoo.this.field_70165_t;
                double d1 = this.field_75647_c - EntitySoo.this.field_70163_u;
                double d2 = this.field_75644_d - EntitySoo.this.field_70161_v;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if ((d3 = (double)MathHelper.func_76133_a((double)d3)) < EntitySoo.this.func_174813_aQ().func_72320_b()) {
                    this.field_188491_h = EntityMoveHelper.Action.WAIT;
                    EntitySoo.this.field_70159_w *= 0.5;
                    EntitySoo.this.field_70181_x *= 0.5;
                    EntitySoo.this.field_70179_y *= 0.5;
                } else {
                    EntitySoo.this.field_70159_w += d0 / d3 * 0.05 * this.field_75645_e;
                    EntitySoo.this.field_70181_x += d1 / d3 * 0.05 * this.field_75645_e;
                    EntitySoo.this.field_70179_y += d2 / d3 * 0.05 * this.field_75645_e;
                    if (EntitySoo.this.func_70638_az() == null) {
                        EntitySoo.this.field_70761_aq = EntitySoo.this.field_70177_z = -((float)MathHelper.func_181159_b((double)EntitySoo.this.field_70159_w, (double)EntitySoo.this.field_70179_y)) * 57.295776f;
                    } else {
                        double d4 = EntitySoo.this.func_70638_az().field_70165_t - EntitySoo.this.field_70165_t;
                        double d5 = EntitySoo.this.func_70638_az().field_70161_v - EntitySoo.this.field_70161_v;
                        EntitySoo.this.field_70761_aq = EntitySoo.this.field_70177_z = -((float)MathHelper.func_181159_b((double)d4, (double)d5)) * 57.295776f;
                    }
                }
            }
        }
    }
}

