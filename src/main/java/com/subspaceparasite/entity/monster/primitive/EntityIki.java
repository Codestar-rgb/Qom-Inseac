/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
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
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.entity.monster.primitive;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.client.particle.SPEnumParticle;
import com.subspaceparasite.entity.EntityHitbox;
import com.subspaceparasite.entity.ai.EntityAIFlightAttack;
import com.subspaceparasite.entity.ai.EntityAIFlightLimits;
import com.subspaceparasite.entity.ai.misc.EntityCanFly;
import com.subspaceparasite.entity.ai.misc.EntityCanShoot;
import com.subspaceparasite.entity.ai.misc.EntityPPrimitive;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.entity.monster.crude.EntityLesh;
import com.subspaceparasite.entity.monster.inborn.EntityAta;
import com.subspaceparasite.entity.projectile.EntityBomb;
import com.subspaceparasite.entity.projectile.EntityProjectileNade;
import com.subspaceparasite.entity.projectile.EntityProjectileSpineball;
import com.subspaceparasite.init.SPSounds;
import com.subspaceparasite.network.SPPacketParticle;
import com.subspaceparasite.util.ParasiteEventEntity;
import com.subspaceparasite.util.ParasiteEventWorld;
import com.subspaceparasite.util.SPAttributes;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigMobs;
import com.subspaceparasite.util.config.SPConfigWorld;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
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
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityIki
extends EntityPPrimitive
implements EntityCanShoot,
EntityCanFly {
    private EntityHitbox back;
    protected static final DataParameter<Byte> VEX_FLAGS = EntityDataManager.func_187226_a(EntityIki.class, (DataSerializer)DataSerializers.field_187191_a);
    private int count;

    public EntityIki(World worldIn) {
        super(worldIn);
        this.field_70765_h = new AIMoveControl(this);
        this.func_70105_a(1.1f, 1.4f);
        this.func_189654_d(true);
        this.field_70714_bg.func_85156_a((EntityAIBase)this.folow);
        this.borderOrb = -1;
        this.field_70158_ak = true;
        if (SPConfigMobs.emanaMaxY != 256) {
            this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIFlightLimits(this, SPConfigMobs.emanaMaxY, true));
        }
        this.back = new EntityHitbox((EntityLiving)this, -1.6f, 1.2f, -0.2f, 1.6f, 1.6f, 0.5f);
        this.hitboxes = new EntityHitbox[]{this.back};
    }

    @Override
    public int getParasiteIDRegister() {
        return 92;
    }

    protected SoundEvent func_184639_G() {
        if (this.getParasiteStatus() != 0) {
            return SPSounds.MOBSILENCE;
        }
        return SPSounds.IKI_GROWL;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        if (this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0) {
            return SPSounds.MOBSILENCE;
        }
        return SPSounds.IKI_HURT;
    }

    protected SoundEvent func_184615_bR() {
        return SPSounds.IKI_DEATH;
    }

    protected void func_184651_r() {
        super.func_184651_r();
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIFlightAttack(this, SPConfig.primitiveFollow));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new AIChargeAttack());
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)new AIBomb(this));
        this.field_70714_bg.func_75776_a(7, (EntityAIBase)new AIMoveRandom());
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SPAttributes.IKI_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SPAttributes.IKI_A_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SPAttributes.IKI_A_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SPAttributes.IKI_A_KD_RESISTANCE);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SPConfig.primitiveFollow);
    }

    @Override
    public void func_70636_d() {
        block4: {
            block3: {
                super.func_70636_d();
                if (this.field_70170_p.field_72995_K) break block3;
                if (this.srpTicks != 10) break block4;
                if (this.field_70122_E && !this.field_70170_p.field_72995_K) {
                    this.field_70765_h.func_75642_a(this.field_70165_t, this.field_70163_u + 5.0, this.field_70161_v, 0.5);
                }
                if (this.field_70170_p.func_180495_p(this.func_180425_c().func_177979_c(1)).func_177230_c() == Blocks.field_150350_a && this.field_70170_p.func_180495_p(this.func_180425_c().func_177979_c(2)).func_177230_c() == Blocks.field_150350_a || this.func_70638_az() == null) break block4;
                this.field_70181_x = 0.5;
                break block4;
            }
            if (this.field_70146_Z.nextInt(25) == 0) {
                for (int i = 0; i <= 20; ++i) {
                    if (i % 5 != 0) continue;
                    this.spawnParticlesGoreMouth(SPEnumParticle.GSPLASH, 0, -1, -1, 0.2, 0.0);
                }
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
        return 0.7f;
    }

    @Override
    public void func_70645_a(DamageSource cause) {
        if (!this.field_70170_p.field_72995_K) {
            if (SPConfigWorld.coloniesActivated || this.canChangeVariant) {
                if (ParasiteEventWorld.numberofColonies(this.field_70170_p) >= 1 || this.canChangeVariant) {
                    ParasiteEventEntity.checkColony(this.field_70170_p, cause, this);
                    ParasiteEventEntity.spawnNext(this, new EntityLesh(this.field_70170_p), true, false);
                } else {
                    super.func_70645_a(cause);
                }
            } else {
                super.func_70645_a(cause);
            }
        }
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
        this.func_184185_a(SPSounds.EMANA_SHOOTING, 2.0f, pit);
        if (this.count >= 4) {
            this.count = 0;
            return new EntityProjectileNade(this.field_70170_p, (EntityLivingBase)this, accelX, accelY, accelZ, 3, 60);
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
        this.func_184185_a(SPSounds.EMANA_SHOOTINGPOST, 2.0f, 1.0f);
    }

    public boolean isCharging() {
        return this.getVexFlag(1);
    }

    public void setCharging(boolean charging) {
        this.setVexFlag(1, charging);
    }

    class AIBomb
    extends EntityAIBase {
        private final EntityParasiteBase parent;
        private int ccc;

        public AIBomb(EntityParasiteBase parentIn) {
            this.parent = parentIn;
            this.ccc = 0;
        }

        public boolean func_75250_a() {
            ++this.ccc;
            if (this.ccc >= 20) {
                this.ccc = 0;
                if (this.parent.func_70638_az() != null) {
                    EntityLivingBase target = this.parent.func_70638_az();
                    if (!target.field_70122_E) {
                        this.ccc = 10;
                        return false;
                    }
                    if (target.func_70092_e(this.parent.field_70165_t, target.field_70163_u, this.parent.field_70161_v) < 256.0) {
                        return true;
                    }
                }
            }
            return false;
        }

        public void func_75246_d() {
            List serverList = this.parent.field_70170_p.field_72996_f;
            int count = 0;
            for (int x = 0; x < serverList.size(); ++x) {
                if (!(serverList.get(x) instanceof EntityAta)) continue;
                ++count;
            }
            if (count < SPConfig.worldGnatCap) {
                EntityAta out = new EntityAta(this.parent.field_70170_p);
                if (this.parent.func_70638_az() != null) {
                    out.func_82149_j((Entity)this.parent);
                    this.parent.field_70170_p.func_72838_d((Entity)out);
                    SPMain.network.sendToAll((IMessage)new SPPacketParticle(this.parent.field_70165_t, this.parent.field_70163_u, this.parent.field_70161_v, 0.5f, 0.5f, 10));
                }
            } else {
                EntityBomb bomb = new EntityBomb(this.parent.field_70170_p, this.parent, false);
                if (this.parent.func_70638_az() != null) {
                    if (this.parent.func_70638_az().field_70163_u > this.parent.field_70163_u) {
                        return;
                    }
                    bomb.func_82149_j((Entity)this.parent);
                    bomb.setFuse(60);
                    bomb.setStren(0.0f);
                    bomb.setSkin(1);
                    bomb.setDamage((float)this.parent.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111125_b(), 2);
                    bomb.field_70125_A -= -20.0f;
                    this.parent.field_70170_p.func_72838_d((Entity)bomb);
                    bomb.updateSTR();
                    SPMain.network.sendToAll((IMessage)new SPPacketParticle(this.parent.field_70165_t, this.parent.field_70163_u, this.parent.field_70161_v, 0.5f, 0.5f, 10));
                }
            }
        }
    }

    class AIChargeAttack
    extends EntityAIBase {
        public AIChargeAttack() {
            this.func_75248_a(1);
        }

        public boolean func_75250_a() {
            if (EntityIki.this.func_70638_az() != null && EntityIki.this.field_70146_Z.nextInt(5) == 0) {
                return EntityIki.this.func_70068_e((Entity)EntityIki.this.func_70638_az()) > 4.0;
            }
            return false;
        }

        public boolean func_75253_b() {
            return EntityIki.this.func_70605_aq().func_75640_a() && EntityIki.this.isCharging() && EntityIki.this.func_70638_az() != null && EntityIki.this.func_70638_az().func_70089_S();
        }

        public void func_75249_e() {
            EntityLivingBase entitylivingbase = EntityIki.this.func_70638_az();
            Vec3d vec3d = entitylivingbase.func_174824_e(1.0f);
            EntityIki.this.field_70765_h.func_75642_a(vec3d.field_72450_a, entitylivingbase.field_70163_u + 7.0, vec3d.field_72449_c, 0.2);
            EntityIki.this.setCharging(true);
        }

        public void func_75251_c() {
            EntityIki.this.setCharging(false);
        }

        public void func_75246_d() {
            EntityLivingBase entitylivingbase = EntityIki.this.func_70638_az();
            if (entitylivingbase != null && entitylivingbase.func_70089_S()) {
                if (EntityIki.this.func_174813_aQ().func_72326_a(entitylivingbase.func_174813_aQ())) {
                    EntityIki.this.func_70652_k((Entity)entitylivingbase);
                    EntityIki.this.setCharging(false);
                } else {
                    double d0 = EntityIki.this.func_70068_e((Entity)entitylivingbase);
                    if (d0 < 9.0) {
                        Vec3d vec3d = entitylivingbase.func_174824_e(1.0f);
                        EntityIki.this.field_70765_h.func_75642_a(vec3d.field_72450_a, entitylivingbase.field_70163_u + 7.0, vec3d.field_72449_c, 1.0);
                    } else {
                        Vec3d vec3d = entitylivingbase.func_174824_e(1.0f);
                        EntityIki.this.field_70765_h.func_75642_a(vec3d.field_72450_a, entitylivingbase.field_70163_u + 7.0, vec3d.field_72449_c, 1.1);
                    }
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
            return !EntityIki.this.func_70605_aq().func_75640_a() && EntityIki.this.field_70146_Z.nextInt(7) == 0;
        }

        public boolean func_75253_b() {
            return false;
        }

        public void func_75246_d() {
            BlockPos blockpos = new BlockPos((Entity)EntityIki.this);
            int flag = 1;
            double speed = 0.5;
            if (EntityIki.this.func_70638_az() != null) {
                if (EntityIki.this.func_70068_e((Entity)EntityIki.this.func_70638_az()) > 100.0) {
                    blockpos = new BlockPos((Entity)EntityIki.this.func_70638_az());
                    flag = 2;
                    speed += 0.25;
                } else if (EntityIki.this.func_70068_e((Entity)EntityIki.this.func_70638_az()) < 36.0) {
                    blockpos = new BlockPos((Entity)EntityIki.this.func_70638_az());
                    flag = 3;
                    speed += 0.25;
                }
            }
            for (int i = 0; i < 3; ++i) {
                BlockPos blockpos1 = blockpos.func_177982_a(EntityIki.this.field_70146_Z.nextInt(15) - 7, EntityIki.this.field_70146_Z.nextInt(11) - 5, EntityIki.this.field_70146_Z.nextInt(15) - 7);
                if (flag == 2) {
                    blockpos1 = blockpos.func_177982_a(EntityIki.this.field_70146_Z.nextInt(6) - 2, EntityIki.this.field_70146_Z.nextInt(7) - 2, EntityIki.this.field_70146_Z.nextInt(6) - 2);
                } else if (flag == 3) {
                    blockpos1 = blockpos.func_177982_a(EntityIki.this.field_70146_Z.nextInt(4) + 3, EntityIki.this.field_70146_Z.nextInt(5) + 4, EntityIki.this.field_70146_Z.nextInt(4) + 3);
                }
                if (!EntityIki.this.field_70170_p.func_175623_d(blockpos1)) continue;
                EntityIki.this.field_70765_h.func_75642_a((double)blockpos1.func_177958_n() + 0.5, (double)blockpos1.func_177956_o() + 0.5, (double)blockpos1.func_177952_p() + 0.5, speed);
                if (EntityIki.this.func_70638_az() != null) break;
                EntityIki.this.func_70671_ap().func_75650_a((double)blockpos1.func_177958_n() + 0.5, (double)blockpos1.func_177956_o() + 0.5, (double)blockpos1.func_177952_p() + 0.5, 180.0f, 20.0f);
                break;
            }
        }
    }

    class AIMoveControl
    extends EntityMoveHelper {
        public AIMoveControl(EntityIki vex) {
            super((EntityLiving)vex);
        }

        public void func_75641_c() {
            if (this.field_188491_h == EntityMoveHelper.Action.MOVE_TO) {
                double d0 = this.field_75646_b - EntityIki.this.field_70165_t;
                double d1 = this.field_75647_c - EntityIki.this.field_70163_u;
                double d2 = this.field_75644_d - EntityIki.this.field_70161_v;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if ((d3 = (double)MathHelper.func_76133_a((double)d3)) < EntityIki.this.func_174813_aQ().func_72320_b()) {
                    this.field_188491_h = EntityMoveHelper.Action.WAIT;
                    EntityIki.this.field_70159_w *= 0.5;
                    EntityIki.this.field_70181_x *= 0.5;
                    EntityIki.this.field_70179_y *= 0.5;
                } else {
                    EntityIki.this.field_70159_w += d0 / d3 * 0.05 * this.field_75645_e;
                    EntityIki.this.field_70181_x += d1 / d3 * 0.05 * this.field_75645_e;
                    EntityIki.this.field_70179_y += d2 / d3 * 0.05 * this.field_75645_e;
                    if (EntityIki.this.func_70638_az() == null) {
                        EntityIki.this.field_70761_aq = EntityIki.this.field_70177_z = -((float)MathHelper.func_181159_b((double)EntityIki.this.field_70159_w, (double)EntityIki.this.field_70179_y)) * 57.295776f;
                    } else {
                        double d4 = EntityIki.this.func_70638_az().field_70165_t - EntityIki.this.field_70165_t;
                        double d5 = EntityIki.this.func_70638_az().field_70161_v - EntityIki.this.field_70161_v;
                        EntityIki.this.field_70761_aq = EntityIki.this.field_70177_z = -((float)MathHelper.func_181159_b((double)d4, (double)d5)) * 57.295776f;
                    }
                }
            }
        }
    }
}

