/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
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
 *  net.minecraft.entity.passive.EntityWaterMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package com.subspaceparasite.entity.monster.inborn;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.entity.ai.EntityAIFlightAttack;
import com.subspaceparasite.entity.ai.EntityAINearestAttackableTargetStatus;
import com.subspaceparasite.entity.ai.misc.EntityCanFly;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.init.SPPotions;
import com.subspaceparasite.init.SPSounds;
import com.subspaceparasite.network.SPPacketParticle;
import com.subspaceparasite.util.ParasiteEventEntity;
import com.subspaceparasite.util.SPAttributes;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigSystems;
import com.google.common.base.Predicate;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class EntityViin
extends EntityParasiteBase
implements EntityCanFly {
    private int lifespan = 0;
    protected static final DataParameter<Byte> VEX_FLAGS = EntityDataManager.func_187226_a(EntityViin.class, (DataSerializer)DataSerializers.field_187191_a);

    public EntityViin(World worldIn) {
        super(worldIn);
        this.field_70765_h = new AIMoveControl(this);
        this.func_70105_a(0.85f, 1.0f);
        this.type = (byte)5;
        this.lifespan = 0;
        this.field_70714_bg.func_85156_a((EntityAIBase)this.folow);
        this.field_70715_bh.func_75776_a(4, new EntityAINearestAttackableTargetStatus<EntityPlayer>(this, EntityPlayer.class, 0, true, false, null, SPConfig.pureSneakPen, SPConfig.pureInviPen));
        if (SPConfig.mobattacking) {
            this.field_70715_bh.func_75776_a(4, new EntityAINearestAttackableTargetStatus<EntityLiving>(this, EntityLiving.class, 0, true, false, new Predicate<EntityLiving>(){

                public boolean apply(@Nullable EntityLiving entity) {
                    return !(entity instanceof EntityWaterMob) && !ParasiteEventEntity.checkEntity((EntityLivingBase)entity, SPConfig.mobattackingBlackList, SPConfig.mobattackingBlackListWhite);
                }
            }, SPConfig.pureSneakPen, SPConfig.pureInviPen));
        }
        this.attackSpeedT = 6;
    }

    @Override
    public int getParasiteIDRegister() {
        return 334;
    }

    protected void func_184651_r() {
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new AIChargeAttack());
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new AIMoveRandom());
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIFlightAttack(this, SPConfig.adaptedFollow));
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false, new Class[0]));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        ++this.lifespan;
        if (this.lifespan > 1200) {
            SPMain.network.sendToAll((IMessage)new SPPacketParticle(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.5f, 0.5f, 11));
            this.func_70106_y();
        }
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SPAttributes.VIIN_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SPAttributes.VIIN_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.34559);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SPAttributes.VIIN_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SPAttributes.VIIN_KD_RESISTANCE);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SPConfig.adaptedFollow);
    }

    @Override
    public boolean func_70652_k(@Nonnull Entity entityIn) {
        return false;
    }

    @Override
    protected void func_82167_n(Entity entityIn) {
        super.func_82167_n(entityIn);
        if (entityIn == this.func_70638_az()) {
            EntityLivingBase target = (EntityLivingBase)entityIn;
            if (target.func_110143_aJ() <= target.func_110138_aP() * SPConfigSystems.hijackHealth) {
                if (ParasiteEventEntity.convertEntityFeral((EntityLivingBase)entityIn, entityIn.getEntityData(), true, SPConfigSystems.COTHVictimParasite) || ParasiteEventEntity.hijackEntity((EntityLivingBase)entityIn, SPConfigSystems.HIJACKVictimParasite)) {
                    SPMain.network.sendToAll((IMessage)new SPPacketParticle(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.5f, 0.5f, 11));
                    SPMain.network.sendToAll((IMessage)new SPPacketParticle(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.5f, 0.5f, 11));
                } else {
                    entityIn.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), (float)this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e());
                    SPMain.network.sendToAll((IMessage)new SPPacketParticle(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.5f, 0.5f, 10));
                }
            } else {
                entityIn.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), (float)this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e());
                SPMain.network.sendToAll((IMessage)new SPPacketParticle(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.5f, 0.5f, 10));
            }
            SPMain.network.sendToAll((IMessage)new SPPacketParticle(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.5f, 0.5f, 10));
            SPMain.network.sendToAll((IMessage)new SPPacketParticle(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.5f, 0.5f, 10));
            SPMain.network.sendToAll((IMessage)new SPPacketParticle(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.5f, 0.5f, 10));
            SPPotions.applyStackPotion(SPPotions.VIRA_E, (EntityLivingBase)entityIn, 120, 2);
            this.func_184185_a(SPSounds.BUTHOL_BOOM, 0.4f, (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2f + 1.0f);
            this.func_70106_y();
        }
    }

    @Override
    protected void func_70609_aI() {
        SPMain.network.sendToAll((IMessage)new SPPacketParticle(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.5f, 0.5f, 10));
        this.func_70106_y();
    }

    @Override
    public void func_70074_a(EntityLivingBase entityLivingIn) {
    }

    public void func_180430_e(float distance, float damageMultiplier) {
    }

    public double func_70042_X() {
        return this.field_70131_O * 0.5f;
    }

    public float func_70047_e() {
        return 0.8f;
    }

    @Override
    protected boolean onDeathDislo(DamageSource cause) {
        return false;
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        this.func_189654_d(true);
    }

    protected SoundEvent func_184639_G() {
        if (this.getParasiteStatus() != 0) {
            return SPSounds.MOBSILENCE;
        }
        return SPSounds.MOBSILENCE;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SPSounds.MOBSILENCE;
    }

    protected SoundEvent func_184615_bR() {
        return SPSounds.MOBSILENCE;
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

    class AIChargeAttack
    extends EntityAIBase {
        public AIChargeAttack() {
            this.func_75248_a(1);
        }

        public boolean func_75250_a() {
            if (EntityViin.this.func_70638_az() != null && !EntityViin.this.func_70605_aq().func_75640_a() && EntityViin.this.field_70146_Z.nextInt(7) == 0) {
                return EntityViin.this.func_70068_e((Entity)EntityViin.this.func_70638_az()) > 1.0;
            }
            return false;
        }

        public boolean func_75253_b() {
            return EntityViin.this.func_70605_aq().func_75640_a() && EntityViin.this.isCharging() && EntityViin.this.func_70638_az() != null && EntityViin.this.func_70638_az().func_70089_S();
        }

        public void func_75249_e() {
            EntityLivingBase entitylivingbase = EntityViin.this.func_70638_az();
            Vec3d vec3d = entitylivingbase.func_174824_e(1.0f);
            EntityViin.this.field_70765_h.func_75642_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c, 2.0);
            EntityViin.this.setCharging(true);
        }

        public void func_75251_c() {
            EntityViin.this.setCharging(false);
        }

        public void func_75246_d() {
            EntityLivingBase entitylivingbase = EntityViin.this.func_70638_az();
            if (entitylivingbase != null && entitylivingbase.func_70089_S()) {
                if (EntityViin.this.func_174813_aQ().func_72326_a(entitylivingbase.func_174813_aQ())) {
                    EntityViin.this.func_70652_k((Entity)entitylivingbase);
                    EntityViin.this.setCharging(false);
                } else {
                    double d0 = EntityViin.this.func_70068_e((Entity)entitylivingbase);
                    if (d0 < 9.0) {
                        Vec3d vec3d = entitylivingbase.func_174824_e(1.0f);
                        EntityViin.this.field_70765_h.func_75642_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c, 2.0);
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
            return !EntityViin.this.func_70605_aq().func_75640_a() && EntityViin.this.field_70146_Z.nextInt(7) == 0;
        }

        public boolean func_75253_b() {
            return false;
        }

        public void func_75246_d() {
            BlockPos blockpos = new BlockPos((Entity)EntityViin.this);
            for (int i = 0; i < 3; ++i) {
                BlockPos blockpos1 = blockpos.func_177982_a(EntityViin.this.field_70146_Z.nextInt(15) - 7, EntityViin.this.field_70146_Z.nextInt(11) - 5, EntityViin.this.field_70146_Z.nextInt(15) - 7);
                if (!EntityViin.this.field_70170_p.func_175623_d(blockpos1)) continue;
                EntityViin.this.field_70765_h.func_75642_a((double)blockpos1.func_177958_n() + 0.5, (double)blockpos1.func_177956_o() + 0.5, (double)blockpos1.func_177952_p() + 0.5, 0.25);
                if (EntityViin.this.func_70638_az() != null) break;
                EntityViin.this.func_70671_ap().func_75650_a((double)blockpos1.func_177958_n() + 0.5, (double)blockpos1.func_177956_o() + 0.5, (double)blockpos1.func_177952_p() + 0.5, 180.0f, 20.0f);
                break;
            }
        }
    }

    class AIMoveControl
    extends EntityMoveHelper {
        public AIMoveControl(EntityViin vex) {
            super((EntityLiving)vex);
        }

        public void func_75641_c() {
            if (this.field_188491_h == EntityMoveHelper.Action.MOVE_TO) {
                double d0 = this.field_75646_b - EntityViin.this.field_70165_t;
                double d1 = this.field_75647_c - EntityViin.this.field_70163_u;
                double d2 = this.field_75644_d - EntityViin.this.field_70161_v;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if ((d3 = (double)MathHelper.func_76133_a((double)d3)) < EntityViin.this.func_174813_aQ().func_72320_b()) {
                    this.field_188491_h = EntityMoveHelper.Action.WAIT;
                    EntityViin.this.field_70159_w *= 0.5;
                    EntityViin.this.field_70181_x *= 0.5;
                    EntityViin.this.field_70179_y *= 0.5;
                } else {
                    EntityViin.this.field_70159_w += d0 / d3 * 0.05 * this.field_75645_e;
                    EntityViin.this.field_70181_x += d1 / d3 * 0.05 * this.field_75645_e;
                    EntityViin.this.field_70179_y += d2 / d3 * 0.05 * this.field_75645_e;
                    if (EntityViin.this.func_70638_az() == null) {
                        EntityViin.this.field_70761_aq = EntityViin.this.field_70177_z = -((float)MathHelper.func_181159_b((double)EntityViin.this.field_70159_w, (double)EntityViin.this.field_70179_y)) * 57.295776f;
                    } else {
                        double d4 = EntityViin.this.func_70638_az().field_70165_t - EntityViin.this.field_70165_t;
                        double d5 = EntityViin.this.func_70638_az().field_70161_v - EntityViin.this.field_70161_v;
                        EntityViin.this.field_70761_aq = EntityViin.this.field_70177_z = -((float)MathHelper.func_181159_b((double)d4, (double)d5)) * 57.295776f;
                    }
                }
            }
        }
    }
}

