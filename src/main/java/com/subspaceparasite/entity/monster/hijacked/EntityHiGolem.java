/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.MobEffects
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.entity.monster.hijacked;

import com.subspaceparasite.entity.EntityBody;
import com.subspaceparasite.entity.EntityDamage;
import com.subspaceparasite.entity.ai.EntityAIAttackMeleeStatus;
import com.subspaceparasite.entity.ai.EntityAIGetFollowers;
import com.subspaceparasite.entity.ai.EntityAISkill;
import com.subspaceparasite.entity.ai.EntityAISwimmingDiving;
import com.subspaceparasite.entity.ai.EntityAIWaterLeapAtTargetStatus;
import com.subspaceparasite.entity.ai.misc.EntityBodyParts;
import com.subspaceparasite.entity.ai.misc.EntityPHijacked;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.init.SPSounds;
import com.subspaceparasite.network.MsgQlipShake;
import com.subspaceparasite.network.SPNetwork;
import com.subspaceparasite.util.SPAttributes;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigMobs;
import javax.annotation.Nonnull;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityHiGolem
extends EntityPHijacked
implements EntityBodyParts {
    private EntityBody leftTendril;
    private int attacking;
    private float lockedHeadYaw = 0.0f;
    private boolean skillCharge;
    private BlockPos positi;
    private int positicool;
    public boolean chargeFlag;
    private int timeGrab;

    public EntityHiGolem(World worldIn) {
        super(worldIn);
        this.func_70105_a(1.1f, 2.7f);
        this.type = (byte)11;
        this.field_70138_W = 1.0f;
        this.chargeFlag = false;
        this.leftTendril = new EntityBody(this, 0.1f, 0.1f, 1.0f, 0.0f, 0.0f, 1, 1, true);
    }

    @Override
    public int getParasiteIDRegister() {
        return 301;
    }

    @Override
    public int canSpawnByIDData() {
        return SPConfigMobs.higolemCanSpawnAssimilatedNat;
    }

    protected void func_184651_r() {
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimmingDiving((EntityLiving)this, 0.08));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIWaterLeapAtTargetStatus(this, 0.7f, 1.5, 3, 20, 0));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAttackMeleeStatus(this, 1.5, false, 0.0));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIGetFollowers(this, 1, 16));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAISkill(this, 100, 32, 3, true, 1, true));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SPAttributes.HIGOLEM_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SPAttributes.HIGOLEM_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.2725);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SPAttributes.HIGOLEM_KD_RESISTANCE);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SPAttributes.HIGOLEM_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SPConfig.hijackedFollow);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        this.leftTendril.func_70071_h_();
        if (this.srpTicks == 10 && !this.field_70170_p.field_72995_K && this.func_184188_bt().size() == 0 && this.chargeFlag && this.positi == null) {
            this.leftTendril.setBodySize(0.1f, 0.1f);
            this.chargeFlag = false;
            this.field_70170_p.func_72960_a((Entity)this, (byte)22);
        }
    }

    @Override
    protected void handleParasiteStatus() {
        byte k = this.getParasiteStatus();
        if (this.getAttackCooldownAni() != 0 || k == 1 || k == 2 || k == 3) {
            if (this.getAttackCooldownAni() != 0) {
                int i = this.getAttackCooldownAni() - 1;
                this.setAttackCooldownAni(i);
            }
            if (k == 1 || k == 2 || k == 3) {
                if (this.func_70638_az() != null) {
                    if (!this.func_70638_az().func_70089_S()) {
                        this.func_70624_b(null);
                        this.setParasiteStatus(0);
                    } else if (this.positi == null) {
                        this.setParasiteStatus(Math.min(k, 1));
                    }
                } else {
                    this.setParasiteStatus(0);
                    this.func_70624_b(null);
                }
            }
        }
    }

    public void func_184232_k(Entity passenger) {
        if (this.func_184196_w(passenger)) {
            this.field_70759_as = this.lockedHeadYaw;
            if (this.chargeFlag) {
                this.leftTendril.setBodySize(1.2f, 2.8f);
            }
            if (!this.field_70170_p.field_72995_K) {
                this.func_70690_d(new PotionEffect(MobEffects.field_76421_d, 20, 200, false, false));
                if (this.srpTicks == 10 && this.positi == null) {
                    if (passenger instanceof EntityPlayer) {
                        EntityPlayer player = (EntityPlayer)passenger;
                        SPNetwork.CHANNEL.sendTo((IMessage)new MsgQlipShake(250, 0, true, false, 4.0f), (EntityPlayerMP)player);
                        if (player.field_71075_bZ.field_75102_a) {
                            return;
                        }
                    }
                    this.func_70652_k(passenger);
                    this.attackEntityAsMobMinimum((EntityLivingBase)passenger, this.getMiniDamage() * 5.0f);
                }
            }
            Vec3d vec3d = this.func_174806_f(this.field_70125_A, this.field_70759_as);
            passenger.field_70159_w = 0.0;
            passenger.field_70179_y = 0.0;
            passenger.func_70107_b(this.field_70165_t + vec3d.field_72450_a * 1.5, this.field_70163_u + (double)this.func_70047_e(), this.field_70161_v + vec3d.field_72449_c * 1.5);
        }
    }

    @Override
    public boolean attackEntityBodyFrom(DamageSource source, float amount, int id, boolean notify) {
        return this.func_70097_a(source, amount);
    }

    @Override
    public void setBodyPartDead(int id) {
        if (this.leftTendril.getId() == id) {
            this.field_70170_p.func_72973_f((Entity)this.leftTendril);
        }
    }

    @Override
    public boolean func_70652_k(@Nonnull Entity entityIn) {
        boolean flag = super.func_70652_k(entityIn);
        if (flag) {
            // empty if block
        }
        return flag;
    }

    @Override
    public void func_70106_y() {
        if (this.leftTendril != null) {
            this.field_70170_p.func_72973_f((Entity)this.leftTendril);
        }
        super.func_70106_y();
    }

    public float func_70047_e() {
        return 1.73f;
    }

    protected SoundEvent func_184639_G() {
        if (this.getParasiteStatus() != 0) {
            return SPSounds.MOBSILENCE;
        }
        return SPSounds.HIGOLEM_GROWL;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SPSounds.HIGOLEM_HURT;
    }

    protected SoundEvent func_184615_bR() {
        return SPSounds.HIGOLEM_DEATH;
    }

    protected void func_180429_a(BlockPos pos, Block blockIn) {
        this.func_184185_a(SoundEvents.field_187605_cG, 0.15f, 1.0f);
    }

    @Override
    public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
        IEntityLivingData floo = super.func_180482_a(difficulty, livingdata);
        return floo;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_70103_a(byte id) {
        if (id == 11) {
            this.chargeFlag = true;
        } else if (id == 22) {
            this.chargeFlag = false;
            this.leftTendril.setBodySize(0.1f, 0.1f);
        } else if (id == 100) {
            for (int i = 0; i <= 1; ++i) {
                this.spawnParticles(EnumParticleTypes.FLAME);
            }
        } else {
            super.func_70103_a(id);
        }
    }

    @Override
    public boolean getFinished(byte attID) {
        switch (attID) {
            case 1: {
                return this.skillCharge;
            }
        }
        return super.getFinished(attID);
    }

    @Override
    public void setFinished(byte attID, boolean in) {
        switch (attID) {
            case 1: {
                this.skillCharge = in;
                return;
            }
        }
        super.setFinished(attID, in);
    }

    @Override
    public void doSpecialSkill(byte id) {
        switch (id) {
            case 1: {
                this.charge();
                return;
            }
        }
        super.doSpecialSkill(id);
    }

    private void charge() {
        ++this.attacking;
        this.miniCapA = true;
        if (this.positi != null) {
            this.chargeMoving();
            for (EntityLivingBase mob : this.field_70170_p.func_72872_a(EntityLivingBase.class, this.func_174813_aQ().func_72314_b(2.0, 0.0, 2.0))) {
                if (mob == this || mob instanceof EntityParasiteBase || mob == this.func_70638_az()) continue;
                float f = (float)MathHelper.func_181159_b((double)(mob.field_70161_v - this.field_70161_v), (double)(mob.field_70165_t - this.field_70165_t));
                EntityDamage damage = new EntityDamage(this.field_70170_p, mob.field_70165_t, mob.field_70163_u, mob.field_70161_v, f, (EntityLivingBase)this, 1.0f, false, 0.5f);
                this.field_70170_p.func_72838_d((Entity)damage);
            }
            this.skillBreakBlocks();
            if (!this.field_70122_E) {
                this.field_70159_w *= 0.7;
                this.field_70179_y *= 0.7;
            }
        } else {
            if (this.attacking < 20) {
                EntityLivingBase entitylivingbase;
                this.field_70170_p.func_72960_a((Entity)this, (byte)100);
                if (this.attacking == 2) {
                    float v = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.4f + 2.0f;
                    this.func_184185_a(this.func_184601_bQ(DamageSource.field_76377_j), 4.0f, v);
                }
                if ((entitylivingbase = this.func_70638_az()) == null || !this.field_70122_E || this.func_70090_H() || entitylivingbase.field_70163_u > this.field_70163_u + 3.0) {
                    this.skillCharge = true;
                    this.attacking = 0;
                    this.miniCapA = false;
                    this.setParasiteStatus(0);
                    return;
                }
                if (!entitylivingbase.func_70089_S() || !this.func_184188_bt().isEmpty()) {
                    this.skillCharge = true;
                    this.attacking = 0;
                    this.miniCapA = false;
                    this.setParasiteStatus(0);
                    return;
                }
                if (this.attacking <= 19) {
                    double dis = this.func_70032_d((Entity)entitylivingbase);
                    this.setParasiteStatus(3);
                    this.func_70661_as().func_75499_g();
                }
            }
            if (this.attacking == 20) {
                this.positi = this.getChargeDestination((Entity)this.func_70638_az(), 0.5);
                this.chargeFlag = true;
                this.field_70170_p.func_72960_a((Entity)this, (byte)11);
            }
            if (this.attacking >= 60 && this.field_70165_t == this.field_70169_q && this.field_70161_v == this.field_70166_s) {
                this.attacking = 0;
                this.miniCapA = false;
                this.skillCharge = true;
                this.setParasiteStatus(1);
            }
        }
    }

    private BlockPos getChargeDestination(Entity target, double distance) {
        double d0 = this.func_70068_e(target);
        if (target != this) {
            this.timeGrab = (int)this.func_70032_d(target);
        }
        if ((d0 < 9.0 || d0 >= 256.0) && target != this || !this.func_70685_l(target) && target != this) {
            return null;
        }
        if (target.field_70163_u > this.field_70163_u + 2.0) {
            return null;
        }
        Vec3d lookVec = target.func_70676_i(1.0f);
        double startX = target.field_70165_t;
        double startY = target.field_70163_u + (double)target.func_70047_e();
        double startZ = target.field_70161_v;
        Vec3d startVec = new Vec3d(startX, startY, startZ);
        Vec3d destVec = startVec.func_178787_e(lookVec.func_186678_a(distance));
        boolean bon = true;
        this.lockedHeadYaw = this.field_70759_as;
        return new BlockPos(destVec.field_72450_a * (double)bon, destVec.field_72448_b, destVec.field_72449_c * (double)bon);
    }

    private void chargeMoving() {
        this.field_70759_as = this.lockedHeadYaw;
        this.setParasiteStatus(3);
        ++this.positicool;
        double str = 0.35;
        double deltaX = (double)this.positi.func_177958_n() - this.field_70165_t;
        double deltaY = (double)this.positi.func_177956_o() - this.field_70163_u;
        double deltaZ = (double)this.positi.func_177952_p() - this.field_70161_v;
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
        if (distance == 0.0) {
            return;
        }
        deltaY /= distance;
        this.field_70159_w += (deltaX /= distance) * str;
        this.field_70179_y += (deltaZ /= distance) * str;
        this.lookAt(this.positi.func_177958_n(), this.positi.func_177956_o(), this.positi.func_177952_p());
        if (this.func_70638_az() != null && this.func_70068_e((Entity)this.func_70638_az()) <= 9.0 && this.func_184188_bt().isEmpty()) {
            this.func_70638_az().func_184205_a((Entity)this, true);
            if (this.func_70638_az() instanceof EntityPlayerMP) {
                EntityPlayer player = (EntityPlayer)this.func_70638_az();
                SPNetwork.CHANNEL.sendTo((IMessage)new MsgQlipShake(2500, 0, true, false, 4.0f), (EntityPlayerMP)player);
            }
        }
        if (this.positicool == 2) {
            this.positi = this.getChargeDestination((Entity)this, 1000.0);
        }
        if (this.positicool > this.timeGrab + 50) {
            this.positi = null;
            this.positicool = 0;
        }
    }
}

