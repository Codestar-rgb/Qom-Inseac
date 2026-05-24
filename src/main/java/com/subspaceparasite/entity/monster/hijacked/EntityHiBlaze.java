/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.MoverType
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityMoveHelper
 *  net.minecraft.entity.ai.EntityMoveHelper$Action
 *  net.minecraft.entity.projectile.EntityFireball
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.MobEffects
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.entity.monster.hijacked;

import com.subspaceparasite.entity.ai.EntityAIAttackProjectile;
import com.subspaceparasite.entity.ai.EntityAIFlightAttack;
import com.subspaceparasite.entity.ai.EntityAIFlightLimits;
import com.subspaceparasite.entity.ai.misc.EntityCanFly;
import com.subspaceparasite.entity.ai.misc.EntityCanShoot;
import com.subspaceparasite.entity.ai.misc.EntityPHijacked;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.entity.projectile.EntityProjectileSpineball;
import com.subspaceparasite.init.SPSounds;
import com.subspaceparasite.util.SPAttributes;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigMobs;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityHiBlaze
extends EntityPHijacked
implements EntityCanShoot,
EntityCanFly {
    protected static final DataParameter<Byte> VEX_FLAGS = EntityDataManager.func_187226_a(EntityHiBlaze.class, (DataSerializer)DataSerializers.field_187191_a);
    private int count;

    public EntityHiBlaze(World worldIn) {
        super(worldIn);
        this.field_70765_h = new AIMoveControl(this);
        this.func_70105_a(0.6f, 0.95f);
        this.type = (byte)11;
        this.func_189654_d(true);
        this.field_70714_bg.func_85156_a((EntityAIBase)this.folow);
        if (SPConfigMobs.emanaMaxY != 256) {
            this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIFlightLimits(this, SPConfigMobs.emanaMaxY, true));
        }
    }

    @Override
    public int getParasiteIDRegister() {
        return 302;
    }

    @Override
    public int canSpawnByIDData() {
        return SPConfigMobs.infbearCanSpawnAssimilatedNat;
    }

    protected void func_184651_r() {
        super.func_184651_r();
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIFlightAttack(this, SPConfig.primitiveFollow));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new AIMoveRandom());
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAIAttackProjectile(this, 80, 20, 1));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SPAttributes.EMANA_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SPAttributes.EMANA_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SPAttributes.EMANA_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SPAttributes.EMANA_KD_RESISTANCE);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SPConfig.primitiveFollow);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K && this.srpTicks == 10) {
            if (this.field_70122_E && !this.field_70170_p.field_72995_K) {
                this.field_70765_h.func_75642_a(this.field_70165_t, this.field_70163_u + 5.0, this.field_70161_v, 0.5);
            }
            if ((this.field_70170_p.func_180495_p(this.func_180425_c().func_177979_c(1)).func_177230_c() != Blocks.field_150350_a || this.field_70170_p.func_180495_p(this.func_180425_c().func_177979_c(2)).func_177230_c() != Blocks.field_150350_a) && this.func_70638_az() != null) {
                this.field_70181_x = 0.1;
            }
            List moblist = this.field_70170_p.func_72872_a(EntityParasiteBase.class, new AxisAlignedBB(this.func_180425_c()).func_72314_b(7.0, 3.0, 7.0));
            for (EntityParasiteBase mob : moblist) {
                mob.func_70690_d(new PotionEffect(MobEffects.field_76426_n, 60, 0, false, true));
            }
        }
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        this.func_189654_d(true);
    }

    public void func_180430_e(float distance, float damageMultiplier) {
    }

    public float func_70047_e() {
        return 0.6f;
    }

    protected SoundEvent func_184639_G() {
        if (this.getParasiteStatus() != 0) {
            return SPSounds.MOBSILENCE;
        }
        return SPSounds.HI_BLAZE_GROWL;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SPSounds.HI_BLAZE_HURT;
    }

    protected SoundEvent func_184615_bR() {
        return SPSounds.HI_BLAZE_DEATH;
    }

    @Override
    public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
        IEntityLivingData floo = super.func_180482_a(difficulty, livingdata);
        return floo;
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

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_70103_a(byte id) {
        if (id == 100) {
            for (int i = 0; i <= 1; ++i) {
                this.spawnParticles(EnumParticleTypes.FLAME);
            }
        } else {
            super.func_70103_a(id);
        }
    }

    public EntityFireball getProj(double accelX, double accelY, double accelZ) {
        float pit = 1.0f;
        if (this.count == 4) {
            pit = 2.0f;
        }
        EntityProjectileSpineball ball = new EntityProjectileSpineball(this.field_70170_p, (EntityLivingBase)this, accelX, accelY, accelZ, SPAttributes.EMANA_RANGED_DAMAGE);
        ball.setDurationAmplifier(SPConfigMobs.emanaPoisonDuration, SPConfigMobs.emanaPoisonAmplifier);
        ball.setGearDamage(SPConfigMobs.emanaGearD);
        return ball;
    }

    @Override
    public void playProjSound() {
        ++this.count;
        if (this.count == 4) {
            this.field_70170_p.func_72960_a((Entity)this, (byte)100);
            float v = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.4f + 2.0f;
            this.func_184185_a(this.func_184601_bQ(DamageSource.field_76377_j), 4.0f, v);
            return;
        }
        this.func_184185_a(SPSounds.HI_BLAZE_FIRE, 2.0f, 1.0f);
    }

    class AIMoveRandom
    extends EntityAIBase {
        public AIMoveRandom() {
            this.func_75248_a(1);
        }

        public boolean func_75250_a() {
            return !EntityHiBlaze.this.func_70605_aq().func_75640_a() && EntityHiBlaze.this.field_70146_Z.nextInt(7) == 0;
        }

        public boolean func_75253_b() {
            return false;
        }

        public void func_75246_d() {
            BlockPos blockpos = new BlockPos((Entity)EntityHiBlaze.this);
            int flag = 1;
            double speed = 0.2;
            if (EntityHiBlaze.this.func_70638_az() != null) {
                if (EntityHiBlaze.this.func_70068_e((Entity)EntityHiBlaze.this.func_70638_az()) > 100.0) {
                    blockpos = new BlockPos((Entity)EntityHiBlaze.this.func_70638_az());
                    flag = 2;
                } else if (EntityHiBlaze.this.func_70068_e((Entity)EntityHiBlaze.this.func_70638_az()) < 36.0) {
                    blockpos = new BlockPos((Entity)EntityHiBlaze.this.func_70638_az());
                    flag = 3;
                }
            }
            for (int i = 0; i < 3; ++i) {
                BlockPos blockpos1 = blockpos.func_177982_a(EntityHiBlaze.this.field_70146_Z.nextInt(15) - 7, EntityHiBlaze.this.field_70146_Z.nextInt(11) - 5, EntityHiBlaze.this.field_70146_Z.nextInt(15) - 7);
                if (flag == 2) {
                    blockpos1 = blockpos.func_177982_a(EntityHiBlaze.this.field_70146_Z.nextInt(6) - 2, EntityHiBlaze.this.field_70146_Z.nextInt(7) - 2, EntityHiBlaze.this.field_70146_Z.nextInt(6) - 2);
                } else if (flag == 3) {
                    blockpos1 = blockpos.func_177982_a(EntityHiBlaze.this.field_70146_Z.nextInt(4) + 3, EntityHiBlaze.this.field_70146_Z.nextInt(5) + 4, EntityHiBlaze.this.field_70146_Z.nextInt(4) + 3);
                }
                if (!EntityHiBlaze.this.field_70170_p.func_175623_d(blockpos1)) continue;
                EntityHiBlaze.this.field_70765_h.func_75642_a((double)blockpos1.func_177958_n() + 0.5, (double)blockpos1.func_177956_o() + 0.5, (double)blockpos1.func_177952_p() + 0.5, speed);
                if (EntityHiBlaze.this.func_70638_az() != null) break;
                EntityHiBlaze.this.func_70671_ap().func_75650_a((double)blockpos1.func_177958_n() + 0.5, (double)blockpos1.func_177956_o() + 0.5, (double)blockpos1.func_177952_p() + 0.5, 180.0f, 20.0f);
                break;
            }
        }
    }

    class AIMoveControl
    extends EntityMoveHelper {
        public AIMoveControl(EntityHiBlaze vex) {
            super((EntityLiving)vex);
        }

        public void func_75641_c() {
            if (this.field_188491_h == EntityMoveHelper.Action.MOVE_TO) {
                double d0 = this.field_75646_b - EntityHiBlaze.this.field_70165_t;
                double d1 = this.field_75647_c - EntityHiBlaze.this.field_70163_u;
                double d2 = this.field_75644_d - EntityHiBlaze.this.field_70161_v;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if ((d3 = (double)MathHelper.func_76133_a((double)d3)) < EntityHiBlaze.this.func_174813_aQ().func_72320_b()) {
                    this.field_188491_h = EntityMoveHelper.Action.WAIT;
                    EntityHiBlaze.this.field_70159_w *= 0.5;
                    EntityHiBlaze.this.field_70181_x *= 0.5;
                    EntityHiBlaze.this.field_70179_y *= 0.5;
                } else {
                    EntityHiBlaze.this.field_70159_w += d0 / d3 * 0.05 * this.field_75645_e;
                    EntityHiBlaze.this.field_70181_x += d1 / d3 * 0.05 * this.field_75645_e;
                    EntityHiBlaze.this.field_70179_y += d2 / d3 * 0.05 * this.field_75645_e;
                    if (EntityHiBlaze.this.func_70638_az() == null) {
                        EntityHiBlaze.this.field_70761_aq = EntityHiBlaze.this.field_70177_z = -((float)MathHelper.func_181159_b((double)EntityHiBlaze.this.field_70159_w, (double)EntityHiBlaze.this.field_70179_y)) * 57.295776f;
                    } else {
                        double d4 = EntityHiBlaze.this.func_70638_az().field_70165_t - EntityHiBlaze.this.field_70165_t;
                        double d5 = EntityHiBlaze.this.func_70638_az().field_70161_v - EntityHiBlaze.this.field_70161_v;
                        EntityHiBlaze.this.field_70761_aq = EntityHiBlaze.this.field_70177_z = -((float)MathHelper.func_181159_b((double)d4, (double)d5)) * 57.295776f;
                    }
                }
            }
        }
    }
}

