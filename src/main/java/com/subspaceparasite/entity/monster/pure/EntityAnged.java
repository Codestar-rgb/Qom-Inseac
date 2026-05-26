/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.entity.monster.pure;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.entity.EntityBody;
import com.subspaceparasite.entity.ai.EntityAIAttackMeleeRangeSwitch;
import com.subspaceparasite.entity.ai.EntityAIAttackMeleeStatus;
import com.subspaceparasite.entity.ai.EntityAIAttackRangedStatus;
import com.subspaceparasite.entity.ai.misc.EntityBodyParts;
import com.subspaceparasite.entity.ai.misc.EntityPPure;
import com.subspaceparasite.entity.monster.EntityTendril;
import com.subspaceparasite.entity.projectile.EntityProjectileAngedball;
import com.subspaceparasite.init.SPSounds;
import com.subspaceparasite.network.SPPacketEntityBodyDead;
import com.subspaceparasite.util.ParasiteEventEntity;
import com.subspaceparasite.util.SPAttributes;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigMobs;
import com.subspaceparasite.util.config.SPConfigSystems;
import javax.annotation.Nonnull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityAnged
extends EntityPPure
implements IRangedAttackMob,
EntityBodyParts {
    private EntityBody leftTendril;
    private EntityBody rightTendril;
    private float leftTendrilHealth;
    private float rightTendrilHealth;

    public EntityAnged(World worldIn) {
        super(worldIn);
        this.func_70105_a(1.6f, 3.1f);
        this.type = (byte)51;
        this.field_70138_W = 1.0f;
        this.leftTendril = new EntityBody(this, 0.7f, 0.9f, 1.0f, 1.1f, 2.3f, 1, 1, true);
        this.rightTendril = new EntityBody(this, 0.7f, 0.9f, 1.0f, 1.1f, 2.3f, -1, 2, true);
        this.leftTendrilHealth = (float)((double)this.func_110138_aP() * SPConfig.tendrilHealth);
        this.rightTendrilHealth = (float)((double)this.func_110138_aP() * SPConfig.tendrilHealth);
    }

    @Override
    public int getParasiteIDRegister() {
        return 25;
    }

    protected void func_184651_r() {
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIAttackMeleeRangeSwitch(this, 5.0f));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIAttackMeleeStatus(this, 1.5, false, 0.0));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIAttackRangedStatus(this, 1.5, 20, (float)SPConfig.pureFollow / 2.0f, false));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SPAttributes.ANGED_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SPAttributes.ANGED_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.2);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SPAttributes.ANGED_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SPAttributes.ANGED_KD_RESISTANCE);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SPConfig.pureFollow);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        if (this.leftTendrilHealth > 0.0f) {
            this.leftTendril.func_70071_h_();
        }
        if (this.rightTendrilHealth > 0.0f) {
            this.rightTendril.func_70071_h_();
        }
    }

    public float func_70047_e() {
        return 3.0f;
    }

    @Override
    public boolean func_70652_k(@Nonnull Entity entityIn) {
        boolean flag = super.func_70652_k(entityIn);
        if (flag && entityIn instanceof EntityLivingBase) {
            ((EntityLivingBase)entityIn).func_70653_a(entityIn, 1.0f, this.field_70165_t - entityIn.field_70165_t, this.field_70161_v - entityIn.field_70161_v);
        }
        return flag;
    }

    @Override
    public boolean attackEntityBodyFrom(DamageSource source, float amount, int id, boolean notify) {
        if (this.field_70170_p.field_72995_K) {
            return false;
        }
        boolean flag = this.func_70097_a(source, amount);
        if (!flag) {
            return false;
        }
        if (this.leftTendril.getId() == id) {
            this.leftTendrilHealth -= amount;
            if (this.leftTendrilHealth <= 0.0f) {
                EntityTendril tendril = new EntityTendril(this.field_70170_p);
                tendril.setSkin(6);
                tendril.func_82149_j(this.leftTendril);
                this.field_70170_p.func_72838_d((Entity)tendril);
                this.field_70170_p.func_72973_f((Entity)this.leftTendril);
                this.field_70170_p.func_72960_a((Entity)this, (byte)11);
                this.cutResistances(SPConfig.purePointDamCap / 2);
                SPMain.network.sendToAll((IMessage)new SPPacketEntityBodyDead(this.func_145782_y(), id));
            }
        } else if (this.rightTendril.getId() == id) {
            this.rightTendrilHealth -= amount;
            if (this.rightTendrilHealth <= 0.0f) {
                EntityTendril tendril = new EntityTendril(this.field_70170_p);
                tendril.setSkin(6);
                tendril.func_82149_j(this.rightTendril);
                this.field_70170_p.func_72838_d((Entity)tendril);
                this.field_70170_p.func_72973_f((Entity)this.rightTendril);
                this.field_70170_p.func_72960_a((Entity)this, (byte)22);
                this.cutResistances(SPConfig.purePointDamCap / 2);
                SPMain.network.sendToAll((IMessage)new SPPacketEntityBodyDead(this.func_145782_y(), id));
            }
        }
        return flag;
    }

    @Override
    public void setBodyPartDead(int id) {
        if (this.leftTendril.getId() == id) {
            this.field_70170_p.func_72973_f((Entity)this.leftTendril);
        } else if (this.rightTendril.getId() == id) {
            this.field_70170_p.func_72973_f((Entity)this.rightTendril);
        }
    }

    protected SoundEvent func_184639_G() {
        return SPSounds.ANGED_GROWL;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        if (this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0) {
            return SPSounds.MOBSILENCE;
        }
        return SPSounds.ANGED_HURT;
    }

    protected SoundEvent func_184615_bR() {
        return SPSounds.ANGED_DEATH;
    }

    @Override
    public boolean scaryOrbEffect(EntityLivingBase in, int mobs) {
        boolean flag = super.scaryOrbEffect(in, mobs);
        if (flag) {
            ParasiteEventEntity.orbApplyEffects(in, this, SPConfigMobs.angedOrbEffects, mobs);
        }
        return flag;
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

    public void func_82196_d(EntityLivingBase target, float distanceFactor) {
        Vec3d vec3d = this.func_70676_i(1.0f);
        double d2 = target.field_70165_t - (this.field_70165_t + vec3d.field_72450_a);
        double d3 = target.func_174813_aQ().field_72338_b + (double)(target.field_70131_O / 2.0f) - (0.5 + this.field_70163_u + (double)(this.field_70131_O / 2.0f));
        double d4 = target.field_70161_v - (this.field_70161_v + vec3d.field_72449_c);
        this.func_184185_a(SPSounds.EMANA_SHOOTING, 2.0f, 1.0f);
        d3 = target.func_174813_aQ().field_72338_b + (double)(target.field_70131_O / 4.0f) - (1.0 + this.field_70163_u + (double)(this.field_70131_O / 2.0f));
        EntityProjectileAngedball entitylargefireball = new EntityProjectileAngedball(this.field_70170_p, (EntityLivingBase)this, d2, d3, d4);
        entitylargefireball.field_70165_t = this.field_70165_t + vec3d.field_72450_a;
        entitylargefireball.field_70163_u = this.field_70163_u + (double)this.func_70047_e() - 0.2;
        entitylargefireball.field_70161_v = this.field_70161_v + vec3d.field_72449_c;
        this.field_70170_p.func_72838_d((Entity)entitylargefireball);
    }

    public void func_184724_a(boolean swingingArms) {
    }

    @Override
    public void func_70014_b(NBTTagCompound compound) {
        super.func_70014_b(compound);
        compound.func_74776_a("parasiteleftTendril", this.leftTendrilHealth);
        compound.func_74776_a("parasiterightTendril", this.rightTendrilHealth);
    }

    @Override
    public void func_70037_a(NBTTagCompound compound) {
        super.func_70037_a(compound);
        if (compound.func_150297_b("parasiteleftTendril", 99)) {
            this.leftTendrilHealth = compound.func_74760_g("parasiteleftTendril");
            if (this.leftTendrilHealth <= 0.0f) {
                this.field_70170_p.func_72960_a((Entity)this, (byte)11);
            }
        }
        if (compound.func_150297_b("parasiterightTendril", 99)) {
            this.rightTendrilHealth = compound.func_74760_g("parasiterightTendril");
            if (this.rightTendrilHealth <= 0.0f) {
                this.field_70170_p.func_72960_a((Entity)this, (byte)22);
            }
        }
    }

    @Override
    public void func_70106_y() {
        if (this.leftTendril != null) {
            this.field_70170_p.func_72973_f((Entity)this.leftTendril);
        }
        if (this.rightTendril != null) {
            this.field_70170_p.func_72973_f((Entity)this.rightTendril);
        }
        super.func_70106_y();
    }

    @SideOnly(value=Side.CLIENT)
    public float getLeft() {
        return this.leftTendrilHealth;
    }

    @SideOnly(value=Side.CLIENT)
    public float getRight() {
        return this.rightTendrilHealth;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_70103_a(byte id) {
        if (id == 11) {
            this.leftTendrilHealth = 0.0f;
        } else if (id == 22) {
            this.rightTendrilHealth = 0.0f;
        } else {
            super.func_70103_a(id);
        }
    }
}

