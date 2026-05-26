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

import com.subspaceparasite.entity.EntityBody;
import com.subspaceparasite.entity.ai.EntityAIAttackMeleeNotGround;
import com.subspaceparasite.entity.ai.EntityAIAttackProjectile;
import com.subspaceparasite.entity.ai.EntityAIFlightAttack;
import com.subspaceparasite.entity.ai.EntityAIFlightLimits;
import com.subspaceparasite.entity.ai.misc.EntityBodyParts;
import com.subspaceparasite.entity.ai.misc.EntityCanFly;
import com.subspaceparasite.entity.ai.misc.EntityCanShoot;
import com.subspaceparasite.entity.ai.misc.EntityCanSummon;
import com.subspaceparasite.entity.ai.misc.EntityCutomAttack;
import com.subspaceparasite.entity.ai.misc.EntityPPure;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.entity.projectile.EntityProjectileAlafhaBall;
import com.subspaceparasite.init.SPSounds;
import com.subspaceparasite.util.ParasiteEventEntity;
import com.subspaceparasite.util.SPAttributes;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigMobs;
import com.subspaceparasite.util.config.SPConfigSystems;
import java.util.Random;
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

public class EntityAlafha
extends EntityPPure
implements EntityBodyParts,
EntityCutomAttack,
EntityCanShoot,
EntityCanFly {
    private EntityBody head;
    protected static final DataParameter<Byte> VEX_FLAGS = EntityDataManager.func_187226_a(EntityAlafha.class, (DataSerializer)DataSerializers.field_187191_a);

    public EntityAlafha(World worldIn) {
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
        this.head = new EntityBody(this, 1.2f, 1.2f, 1.0f, 3.0f, 0.0f, -1, 1, false, 0.2f);
        this.adaptationCap = 0.95f;
    }

    @Override
    public int getParasiteIDRegister() {
        return 9;
    }

    protected void func_184651_r() {
        super.func_184651_r();
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIFlightAttack(this, SPConfig.pureFollow));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new AIMoveRandom());
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIAirVomitSummon(this, SPConfigMobs.alafhaSummoningCooldown, SPConfigMobs.alafhaLimit, SPConfigMobs.alafhaMobList));
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAIAttackProjectile(this, 20, 10, 4));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIAttackMeleeNotGround(this, 4.5, 16.0, 0.045, true));
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
        if ((this.field_70170_p.func_180495_p(this.func_180425_c().func_177979_c(1)).func_177230_c() != Blocks.field_150350_a || this.field_70170_p.func_180495_p(this.func_180425_c().func_177979_c(2)).func_177230_c() != Blocks.field_150350_a) && this.func_70638_az() != null) {
            this.field_70181_x = 0.5;
        }
        this.head.func_70071_h_();
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        this.func_189654_d(true);
    }

    @Override
    public boolean attackEntityBodyFrom(DamageSource source, float amount, int id, boolean notify) {
        return this.func_70097_a(source, amount * 3.0f);
    }

    @Override
    public void setBodyPartDead(int id) {
    }

    @Override
    public void func_180430_e(float distance, float damageMultiplier) {
    }

    @Override
    public boolean attackEntityAsMobAOE(Entity entityIn) {
        return this.func_70652_k(entityIn);
    }

    public float func_70047_e() {
        return 1.6f;
    }

    @Override
    public void func_70106_y() {
        if (this.head != null) {
            this.field_70170_p.func_72973_f((Entity)this.head);
        }
        super.func_70106_y();
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
        IEntityLivingData floo = super.func_180482_a(difficulty, livingdata);
        if (this.field_70146_Z.nextDouble() < SPConfig.variantChance || this.phaseCreated >= SPConfigSystems.evolutionParasiteAlwaysVariant || this.canChangeVariant) {
            switch (this.field_70146_Z.nextInt(1)) {
                case 0: {
                    this.setSkin(7);
                }
            }
        }
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

    public EntityFireball getProj(double accelX, double accelY, double accelZ) {
        this.func_184185_a(SPSounds.ALAFHA_SHOOTING, 2.0f, 1.0f);
        return new EntityProjectileAlafhaBall(this.field_70170_p, (EntityLivingBase)this, accelX, accelY, accelZ);
    }

    @Override
    public void playProjSound() {
        this.func_184185_a(SPSounds.ALAFHA_SHOOTINGPOST, 2.0f, 1.0f);
    }

    static class EntityAIAirVomitSummon
    extends EntityAIBase {
        private final EntityParasiteBase parentEntity;
        private int limit = 0;
        private int sCooldown;
        private int sLimit;
        private String[] sMobs;
        private int attackTimer = 0;
        private int attacking = 0;
        private double targetX;
        private double targetY;
        private double targetZ;

        public EntityAIAirVomitSummon(EntityParasiteBase summoner, int cooldown, int limit, String[] mobList) {
            this.parentEntity = summoner;
            this.sCooldown = cooldown;
            this.sLimit = limit;
            this.sMobs = mobList;
        }

        public boolean func_75250_a() {
            if (this.attacking >= 1) {
                return true;
            }
            if (this.parentEntity.func_70638_az() != null) {
                return this.parentEntity.func_70638_az().field_70122_E;
            }
            return false;
        }

        public void func_75249_e() {
        }

        public void func_75251_c() {
        }

        public void func_75246_d() {
            if (this.attacking >= 1) {
                ++this.attacking;
                this.parentEntity.setParasiteStatus(10);
                this.parentEntity.func_70661_as().func_75499_g();
                if (this.attacking == 2) {
                    // empty if block
                }
                if (this.attacking % 20 == 0 && this.attacking >= 40) {
                    EntityCanSummon parent = (EntityCanSummon)((Object)this.parentEntity);
                    parent.checkID();
                    if (parent.getActualParasites() < parent.getTotalParasites() && this.limit < this.sLimit && ParasiteEventEntity.spawnBiomassFromProjectile(this.parentEntity, this.sMobs, this.parentEntity.func_70638_az())) {
                        ++this.limit;
                        this.parentEntity.particleStatus((byte)8);
                    }
                }
                if (this.attacking >= 80 || this.limit >= this.sLimit) {
                    this.attacking = 0;
                    this.attackTimer = 0;
                    this.limit = 0;
                    this.parentEntity.setParasiteStatus(2);
                }
            } else if (this.parentEntity.func_70638_az() == null) {
                if (this.attackTimer > 0) {
                    --this.attackTimer;
                }
            } else if (this.parentEntity.func_70638_az().field_70128_L) {
                if (this.attackTimer > 0) {
                    --this.attackTimer;
                }
            } else {
                EntityLivingBase entitylivingbase = this.parentEntity.func_70638_az();
                if (this.parentEntity.func_70685_l((Entity)entitylivingbase) && this.parentEntity.func_70068_e((Entity)entitylivingbase) < 256.0 && entitylivingbase.field_70122_E) {
                    ++this.attackTimer;
                    if (this.attackTimer >= this.sCooldown && this.attacking == 0) {
                        ++this.attacking;
                        this.targetX = this.parentEntity.field_70165_t;
                        this.targetY = this.parentEntity.field_70163_u;
                        this.targetZ = this.parentEntity.field_70161_v;
                    }
                } else if (this.attackTimer > 0 && this.attackTimer > 0) {
                    --this.attackTimer;
                }
            }
        }
    }

    class AIMoveRandom
    extends EntityAIBase {
        public AIMoveRandom() {
            this.func_75248_a(1);
        }

        public boolean func_75250_a() {
            double d2;
            double d1;
            EntityMoveHelper entitymovehelper = EntityAlafha.this.func_70605_aq();
            if (!entitymovehelper.func_75640_a()) {
                return true;
            }
            double d0 = entitymovehelper.func_179917_d() - EntityAlafha.this.field_70165_t;
            double d3 = d0 * d0 + (d1 = entitymovehelper.func_179919_e() - EntityAlafha.this.field_70163_u) * d1 + (d2 = entitymovehelper.func_179918_f() - EntityAlafha.this.field_70161_v) * d2;
            return d3 < 1.0 || d3 > 3600.0;
        }

        public boolean func_75253_b() {
            return false;
        }

        public void func_75246_d() {
            if (EntityAlafha.this.func_70638_az() != null) {
                BlockPos blockpos = new BlockPos((Entity)EntityAlafha.this);
                int flag = 1;
                double speed = 0.11;
                if (EntityAlafha.this.func_70068_e((Entity)EntityAlafha.this.func_70638_az()) > 400.0) {
                    blockpos = new BlockPos((Entity)EntityAlafha.this.func_70638_az());
                    flag = 2;
                    speed += 0.11;
                } else if (EntityAlafha.this.func_70068_e((Entity)EntityAlafha.this.func_70638_az()) < 100.0) {
                    blockpos = new BlockPos((Entity)EntityAlafha.this.func_70638_az());
                    flag = 3;
                    speed += 0.11;
                }
                for (int i = 0; i < 3; ++i) {
                    BlockPos blockpos1 = blockpos.func_177982_a(EntityAlafha.this.field_70146_Z.nextInt(15) - 7, EntityAlafha.this.field_70146_Z.nextInt(9) - 5, EntityAlafha.this.field_70146_Z.nextInt(15) - 7);
                    if (flag == 2) {
                        blockpos1 = blockpos.func_177982_a(EntityAlafha.this.field_70146_Z.nextInt(6) - 2, EntityAlafha.this.field_70146_Z.nextInt(7) - 2, EntityAlafha.this.field_70146_Z.nextInt(6) - 2);
                    } else if (flag == 3) {
                        blockpos1 = blockpos.func_177982_a(EntityAlafha.this.field_70146_Z.nextInt(4) + 3, EntityAlafha.this.field_70146_Z.nextInt(5) + 4, EntityAlafha.this.field_70146_Z.nextInt(4) + 3);
                    }
                    if (!EntityAlafha.this.field_70170_p.func_175623_d(blockpos1)) continue;
                    EntityAlafha.this.field_70765_h.func_75642_a((double)blockpos1.func_177958_n() + 0.5, (double)blockpos1.func_177956_o() + 0.5, (double)blockpos1.func_177952_p() + 0.5, speed);
                    if (EntityAlafha.this.func_70638_az() == null) {
                        EntityAlafha.this.func_70671_ap().func_75650_a((double)blockpos1.func_177958_n() + 0.5, (double)blockpos1.func_177956_o() + 0.5, (double)blockpos1.func_177952_p() + 0.5, 180.0f, 20.0f);
                    }
                    break;
                }
            } else {
                Random random = EntityAlafha.this.func_70681_au();
                double d0 = EntityAlafha.this.field_70165_t + (double)((random.nextFloat() * 2.0f - 1.0f) * 16.0f);
                double d1 = EntityAlafha.this.field_70163_u + (double)((random.nextFloat() * 2.0f - 1.0f) * 16.0f);
                double d2 = EntityAlafha.this.field_70161_v + (double)((random.nextFloat() * 2.0f - 1.0f) * 16.0f);
                EntityAlafha.this.func_70605_aq().func_75642_a(d0, d1, d2, 0.5);
            }
        }
    }

    class AIMoveControl
    extends EntityMoveHelper {
        public AIMoveControl(EntityAlafha vex) {
            super((EntityLiving)vex);
        }

        public void func_75641_c() {
            if (this.field_188491_h == EntityMoveHelper.Action.MOVE_TO) {
                double d0 = this.field_75646_b - EntityAlafha.this.field_70165_t;
                double d1 = this.field_75647_c - EntityAlafha.this.field_70163_u;
                double d2 = this.field_75644_d - EntityAlafha.this.field_70161_v;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if ((d3 = (double)MathHelper.func_76133_a((double)d3)) < EntityAlafha.this.func_174813_aQ().func_72320_b()) {
                    this.field_188491_h = EntityMoveHelper.Action.WAIT;
                    EntityAlafha.this.field_70159_w *= 0.5;
                    EntityAlafha.this.field_70181_x *= 0.5;
                    EntityAlafha.this.field_70179_y *= 0.5;
                } else {
                    EntityAlafha.this.field_70159_w += d0 / d3 * 0.05 * this.field_75645_e;
                    EntityAlafha.this.field_70181_x += d1 / d3 * 0.05 * this.field_75645_e;
                    EntityAlafha.this.field_70179_y += d2 / d3 * 0.05 * this.field_75645_e;
                    if (EntityAlafha.this.func_70638_az() == null) {
                        EntityAlafha.this.field_70761_aq = EntityAlafha.this.field_70177_z = -((float)MathHelper.func_181159_b((double)EntityAlafha.this.field_70159_w, (double)EntityAlafha.this.field_70179_y)) * 57.295776f;
                    } else {
                        double d4 = EntityAlafha.this.func_70638_az().field_70165_t - EntityAlafha.this.field_70165_t;
                        double d5 = EntityAlafha.this.func_70638_az().field_70161_v - EntityAlafha.this.field_70161_v;
                        EntityAlafha.this.field_70761_aq = EntityAlafha.this.field_70177_z = -((float)MathHelper.func_181159_b((double)d4, (double)d5)) * 57.295776f;
                    }
                }
            }
        }
    }
}

