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
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityMoveHelper
 *  net.minecraft.entity.ai.EntityMoveHelper$Action
 *  net.minecraft.init.Blocks
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 */
package com.subspaceparasite.entity.monster.pure.preeminent;

import com.subspaceparasite.client.particle.SPEnumParticle;
import com.subspaceparasite.entity.ai.EntityAIFlightAttack;
import com.subspaceparasite.entity.ai.EntityAIFlightLimits;
import com.subspaceparasite.entity.ai.misc.EntityCanFly;
import com.subspaceparasite.entity.ai.misc.EntityPPreeminent;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.entity.monster.inborn.EntityKol;
import com.subspaceparasite.entity.projectile.EntityBomb;
import com.subspaceparasite.init.SPSounds;
import com.subspaceparasite.util.ParasiteEventEntity;
import com.subspaceparasite.util.SPAttributes;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigMobs;
import com.subspaceparasite.util.config.SPConfigWorld;
import com.subspaceparasite.world.SPWorldData;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.init.Blocks;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityJinjo
extends EntityPPreeminent
implements EntityCanFly {
    protected static final DataParameter<Byte> VEX_FLAGS = EntityDataManager.func_187226_a(EntityJinjo.class, (DataSerializer)DataSerializers.field_187191_a);

    public EntityJinjo(World worldIn) {
        super(worldIn);
        this.field_70765_h = new AIMoveControl(this);
        this.func_70105_a(3.7f, 4.4f);
        this.func_189654_d(true);
        if (SPConfigMobs.ombooMaxY != 256) {
            this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIFlightLimits(this, SPConfigMobs.ombooMaxY, true));
        }
        this.canTeleportToo = true;
        this.adaptationCap = 0.95f;
        this.field_70158_ak = true;
    }

    @Override
    public int getParasiteIDRegister() {
        return 65;
    }

    protected void func_184651_r() {
        super.func_184651_r();
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIFlightAttack(this, SPConfig.pureFollow));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new AIChargeAttack());
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new AIMoveRandom());
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)new AIBomb(this));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIFlightLimits(this, 10, false));
    }

    protected SoundEvent func_184639_G() {
        if (this.getParasiteStatus() != 0) {
            return SPSounds.MOBSILENCE;
        }
        return SPSounds.JINJO_GROWL;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        if (this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0) {
            return SPSounds.MOBSILENCE;
        }
        return SPSounds.JINJO_HURT;
    }

    protected float func_70599_aP() {
        return 6.0f;
    }

    protected SoundEvent func_184615_bR() {
        return SPSounds.JINJO_DEATH;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SPAttributes.JINJO_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SPAttributes.JINJO_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SPAttributes.JINJO_KD_RESISTANCE);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SPAttributes.JINJO_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SPConfig.preeminentFollow);
    }

    @Override
    public void func_70636_d() {
        block7: {
            block6: {
                super.func_70636_d();
                if (this.field_70170_p.field_72995_K) break block6;
                if (this.field_70122_E) {
                    this.field_70765_h.func_75642_a(this.field_70165_t, this.field_70163_u + 5.0, this.field_70161_v, 0.5);
                }
                if (this.srpTicks == 10 && (this.field_70170_p.func_180495_p(this.func_180425_c().func_177979_c(1)).func_177230_c() != Blocks.field_150350_a || this.field_70170_p.func_180495_p(this.func_180425_c().func_177979_c(2)).func_177230_c() != Blocks.field_150350_a) && this.func_70638_az() != null) {
                    this.field_70181_x = 0.5;
                }
                if (this.srpTicks != 10 || this.field_70146_Z.nextInt(7) != 0) break block7;
                if (!SPConfigWorld.coloniesActivated) {
                    return;
                }
                AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0).func_186662_g(16.0);
                List moblist = this.field_70170_p.func_72872_a(EntityKol.class, axisalignedbb);
                if (moblist.size() > 3) {
                    return;
                }
                SPWorldData data = SPWorldData.get(this.field_70170_p);
                BlockPos origin = data.nearestColonyPosition(this.func_180425_c(), false);
                if (origin == null) break block7;
                EntityKol aaa = new EntityKol(this.field_70170_p);
                aaa.func_82149_j((Entity)this);
                aaa.setTask(origin, data.getColonyDistanceSpreadByPosition(origin, false));
                this.field_70170_p.func_72838_d((Entity)aaa);
                break block7;
            }
            if (this.field_70146_Z.nextInt(30) == 0) {
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
    public boolean scaryOrbEffect(EntityLivingBase in, int mobs) {
        boolean flag = super.scaryOrbEffect(in, mobs);
        if (flag) {
            ParasiteEventEntity.orbApplyEffects(in, this, SPConfigMobs.jinjoOrbEffects, mobs);
        }
        return flag;
    }

    @Override
    public void func_180430_e(float distance, float damageMultiplier) {
    }

    public float func_70047_e() {
        return 2.4f;
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
            if (this.ccc >= 100) {
                this.ccc = 0;
                if (this.parent.func_70638_az() != null) {
                    EntityLivingBase target = this.parent.func_70638_az();
                    if (!target.field_70122_E) {
                        this.ccc = 70;
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
            EntityBomb out = new EntityBomb(this.parent.field_70170_p, this.parent, SPConfigMobs.jinjoGriefing);
            if (this.parent.func_70638_az() != null) {
                boolean flag = this.parent.func_70685_l((Entity)this.parent.func_70638_az()) || this.parent.field_70170_p.field_73012_v.nextInt(3) == 0;
                out.func_82149_j((Entity)this.parent);
                out.setFuse(80);
                out.setStren(flag ? 4.0f : 8.0f);
                out.setSkin(flag ? 2 : 3);
                out.setDamage((float)this.parent.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e() * SPConfigMobs.jinjoExplotionMult, 7);
                this.parent.field_70170_p.func_72838_d((Entity)out);
                out.updateSTR();
            }
        }
    }

    class AIChargeAttack
    extends EntityAIBase {
        public AIChargeAttack() {
            this.func_75248_a(1);
        }

        public boolean func_75250_a() {
            if (EntityJinjo.this.func_70638_az() != null && EntityJinjo.this.field_70146_Z.nextInt(5) == 0) {
                return EntityJinjo.this.func_70068_e((Entity)EntityJinjo.this.func_70638_az()) > 4.0;
            }
            return false;
        }

        public boolean func_75253_b() {
            return EntityJinjo.this.func_70605_aq().func_75640_a() && EntityJinjo.this.isCharging() && EntityJinjo.this.func_70638_az() != null && EntityJinjo.this.func_70638_az().func_70089_S();
        }

        public void func_75249_e() {
            EntityLivingBase entitylivingbase = EntityJinjo.this.func_70638_az();
            Vec3d vec3d = entitylivingbase.func_174824_e(1.0f);
            EntityJinjo.this.field_70765_h.func_75642_a(vec3d.field_72450_a, entitylivingbase.field_70163_u + 20.0, vec3d.field_72449_c, 0.2);
            EntityJinjo.this.setCharging(true);
        }

        public void func_75251_c() {
            EntityJinjo.this.setCharging(false);
        }

        public void func_75246_d() {
            EntityLivingBase entitylivingbase = EntityJinjo.this.func_70638_az();
            if (entitylivingbase != null && entitylivingbase.func_70089_S()) {
                if (EntityJinjo.this.func_174813_aQ().func_72326_a(entitylivingbase.func_174813_aQ())) {
                    EntityJinjo.this.func_70652_k((Entity)entitylivingbase);
                    EntityJinjo.this.setCharging(false);
                } else {
                    double d0 = EntityJinjo.this.func_70068_e((Entity)entitylivingbase);
                    if (d0 < 9.0) {
                        Vec3d vec3d = entitylivingbase.func_174824_e(1.0f);
                        EntityJinjo.this.field_70765_h.func_75642_a(vec3d.field_72450_a, entitylivingbase.field_70163_u + 20.0, vec3d.field_72449_c, 1.0);
                    } else {
                        Vec3d vec3d = entitylivingbase.func_174824_e(1.0f);
                        EntityJinjo.this.field_70765_h.func_75642_a(vec3d.field_72450_a, entitylivingbase.field_70163_u + 20.0, vec3d.field_72449_c, 1.1);
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
            return !EntityJinjo.this.func_70605_aq().func_75640_a() && EntityJinjo.this.field_70146_Z.nextInt(7) == 0;
        }

        public boolean func_75253_b() {
            return false;
        }

        public void func_75246_d() {
            BlockPos blockpos = new BlockPos((Entity)EntityJinjo.this);
            int flag = 1;
            double speed = 0.1;
            if (EntityJinjo.this.func_70638_az() != null) {
                if (EntityJinjo.this.func_70068_e((Entity)EntityJinjo.this.func_70638_az()) > 100.0) {
                    blockpos = new BlockPos((Entity)EntityJinjo.this.func_70638_az());
                    flag = 2;
                    speed += 0.05;
                } else if (EntityJinjo.this.func_70068_e((Entity)EntityJinjo.this.func_70638_az()) < 36.0) {
                    blockpos = new BlockPos((Entity)EntityJinjo.this.func_70638_az());
                    flag = 3;
                    speed += 0.05;
                }
            }
            for (int i = 0; i < 3; ++i) {
                BlockPos blockpos1 = blockpos.func_177982_a(EntityJinjo.this.field_70146_Z.nextInt(15) - 7, EntityJinjo.this.field_70146_Z.nextInt(11) - 5, EntityJinjo.this.field_70146_Z.nextInt(15) - 7);
                if (flag == 2) {
                    blockpos1 = blockpos.func_177982_a(EntityJinjo.this.field_70146_Z.nextInt(6) - 2, EntityJinjo.this.field_70146_Z.nextInt(7) - 2, EntityJinjo.this.field_70146_Z.nextInt(6) - 2);
                } else if (flag == 3) {
                    blockpos1 = blockpos.func_177982_a(EntityJinjo.this.field_70146_Z.nextInt(4) + 3, EntityJinjo.this.field_70146_Z.nextInt(5) + 4, EntityJinjo.this.field_70146_Z.nextInt(4) + 3);
                }
                if (!EntityJinjo.this.field_70170_p.func_175623_d(blockpos1)) continue;
                EntityJinjo.this.field_70765_h.func_75642_a((double)blockpos1.func_177958_n() + 0.5, (double)blockpos1.func_177956_o() + 1.0, (double)blockpos1.func_177952_p() + 0.5, speed);
                if (EntityJinjo.this.func_70638_az() != null) break;
                EntityJinjo.this.func_70671_ap().func_75650_a((double)blockpos1.func_177958_n() + 0.5, (double)blockpos1.func_177956_o() + 1.0, (double)blockpos1.func_177952_p() + 0.5, 180.0f, 20.0f);
                break;
            }
        }
    }

    class AIMoveControl
    extends EntityMoveHelper {
        public AIMoveControl(EntityJinjo vex) {
            super((EntityLiving)vex);
        }

        public void func_75641_c() {
            if (this.field_188491_h == EntityMoveHelper.Action.MOVE_TO) {
                double d0 = this.field_75646_b - EntityJinjo.this.field_70165_t;
                double d1 = this.field_75647_c - EntityJinjo.this.field_70163_u;
                double d2 = this.field_75644_d - EntityJinjo.this.field_70161_v;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if ((d3 = (double)MathHelper.func_76133_a((double)d3)) < EntityJinjo.this.func_174813_aQ().func_72320_b()) {
                    this.field_188491_h = EntityMoveHelper.Action.WAIT;
                    EntityJinjo.this.field_70159_w *= 0.5;
                    EntityJinjo.this.field_70181_x *= 0.5;
                    EntityJinjo.this.field_70179_y *= 0.5;
                } else {
                    EntityJinjo.this.field_70159_w += d0 / d3 * 0.05 * this.field_75645_e;
                    EntityJinjo.this.field_70181_x += d1 / d3 * 0.05 * this.field_75645_e;
                    EntityJinjo.this.field_70179_y += d2 / d3 * 0.05 * this.field_75645_e;
                    if (EntityJinjo.this.func_70638_az() == null) {
                        EntityJinjo.this.field_70761_aq = EntityJinjo.this.field_70177_z = -((float)MathHelper.func_181159_b((double)EntityJinjo.this.field_70159_w, (double)EntityJinjo.this.field_70179_y)) * 57.295776f;
                    } else {
                        double d4 = EntityJinjo.this.func_70638_az().field_70165_t - EntityJinjo.this.field_70165_t;
                        double d5 = EntityJinjo.this.func_70638_az().field_70161_v - EntityJinjo.this.field_70161_v;
                        EntityJinjo.this.field_70761_aq = EntityJinjo.this.field_70177_z = -((float)MathHelper.func_181159_b((double)d4, (double)d5)) * 57.295776f;
                    }
                }
            }
        }
    }
}

