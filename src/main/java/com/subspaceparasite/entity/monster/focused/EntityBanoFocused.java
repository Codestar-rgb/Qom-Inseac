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
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.init.MobEffects
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.entity.monster.focused;

import com.subspaceparasite.entity.ai.EntityAIAttackMeleeStatusAOE;
import com.subspaceparasite.entity.ai.EntityAIBlockResidue;
import com.subspaceparasite.entity.ai.EntityAIGetFollowers;
import com.subspaceparasite.entity.ai.EntityAIGiveEffectsArea;
import com.subspaceparasite.entity.ai.EntityAISkill;
import com.subspaceparasite.entity.ai.EntityAISwimmingDiving;
import com.subspaceparasite.entity.ai.misc.EntityBodyParts;
import com.subspaceparasite.entity.ai.misc.EntityCutomAttack;
import com.subspaceparasite.entity.ai.misc.EntityPCosmical;
import com.subspaceparasite.entity.ai.misc.EntityPFocused;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.entity.monster.primitive.EntityBano;
import com.subspaceparasite.init.SPPotions;
import com.subspaceparasite.init.SPSounds;
import com.subspaceparasite.util.ParasiteEventEntity;
import com.subspaceparasite.util.ParasiteEventWorld;
import com.subspaceparasite.util.SPAttributes;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigMobs;
import com.subspaceparasite.util.config.SPConfigSystems;
import com.subspaceparasite.util.config.SPConfigWorld;
import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityBanoFocused
extends EntityPFocused
implements EntityCutomAttack,
EntityBodyParts {
    private float attackTimer;
    private boolean up;
    private int border;
    private boolean skillEnraged;

    public EntityBanoFocused(World worldIn) {
        super(worldIn);
        this.func_70105_a(1.3f, 3.8f);
        this.field_70158_ak = true;
    }

    @Override
    public int getParasiteIDRegister() {
        return 56;
    }

    protected void func_184651_r() {
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimmingDiving((EntityLiving)this, 0.11));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAISkill(this, 140, 10, true, 1));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIAttackMeleeStatusAOE(this, 1.3, false, 8.0, 5.0));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIGiveEffectsArea(this, SPAttributes.ZETMO_A_CD, SPAttributes.ZETMO_A_RANGE, SPConfigMobs.zetmoadaptedeffects));
        if (SPConfig.parasiteGenResidue) {
            this.field_70714_bg.func_75776_a(9, (EntityAIBase)new EntityAIBlockResidue(this, 3));
        }
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIGetFollowers(this, 3, 32));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SPAttributes.ZETMO_HEALTH + SPAttributes.ZETMO_A_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SPAttributes.ZETMO_ARMOR + SPAttributes.ZETMO_A_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.22);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SPAttributes.ZETMO_KD_RESISTANCE + SPAttributes.ZETMO_A_KD_RESISTANCE);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SPAttributes.ZETMO_ATTACK_DAMAGE + SPAttributes.ZETMO_A_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SPConfig.adaptedFollow);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        if (this.up) {
            this.attackTimer = (float)((double)this.attackTimer + 0.2);
            if ((double)this.attackTimer > 1.5) {
                this.up = false;
            }
        } else {
            this.attackTimer = (float)((double)this.attackTimer - 0.1);
        }
    }

    @Override
    public boolean func_70652_k(@Nonnull Entity entityIn) {
        boolean flag = super.func_70652_k(entityIn);
        if (flag && entityIn instanceof EntityLivingBase) {
            switch (this.getSkin()) {
                case 5: {
                    SPPotions.applyStackPotion(SPPotions.VIRA_E, (EntityLivingBase)entityIn, 100, 0);
                    break;
                }
                case 6: {
                    SPPotions.applyStackPotion(SPPotions.BLEED_E, (EntityLivingBase)entityIn, 100, 0);
                }
            }
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
        return flag;
    }

    @Override
    public void setBodyPartDead(int id) {
    }

    @Override
    protected void func_82167_n(Entity entityIn) {
        super.func_82167_n(entityIn);
        if (this.field_70170_p.field_72995_K) {
            return;
        }
        if (entityIn instanceof EntityLivingBase && !(entityIn instanceof EntityParasiteBase) && this.getSkin() == 5) {
            SPPotions.applyStackPotion(SPPotions.VIRA_E, (EntityLivingBase)entityIn, 100, 0);
        }
    }

    public float func_70047_e() {
        return 3.3f;
    }

    @Override
    public void func_70645_a(DamageSource cause) {
        if (!this.field_70170_p.field_72995_K) {
            if (SPConfigWorld.coloniesActivated || this.canChangeVariant) {
                if (ParasiteEventWorld.numberofColonies(this.field_70170_p) >= 1 || this.canChangeVariant) {
                    ParasiteEventEntity.checkColony(this.field_70170_p, cause, this);
                    ParasiteEventEntity.spawnNext(this, new EntityBano(this.field_70170_p), true, false);
                } else {
                    super.func_70645_a(cause);
                }
            } else {
                super.func_70645_a(cause);
            }
        }
    }

    protected SoundEvent func_184639_G() {
        if (this.getParasiteStatus() != 0) {
            return SPSounds.MOBSILENCE;
        }
        return SPSounds.AZETMO_GROWL;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        if (this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0) {
            return SPSounds.MOBSILENCE;
        }
        return SPSounds.AZETMO_HURT;
    }

    protected SoundEvent func_184615_bR() {
        return SPSounds.AZETMO_DEATH;
    }

    @Override
    public boolean scaryOrbEffect(EntityLivingBase in, int mobs) {
        boolean flag = super.scaryOrbEffect(in, mobs);
        if (flag) {
            ParasiteEventEntity.orbApplyEffects(in, this, SPConfigMobs.zetmoadaptedOrbEffects, mobs);
        }
        return flag;
    }

    @Override
    public void func_70106_y() {
        super.func_70106_y();
    }

    @Override
    public boolean attackEntityAsMobAOE(Entity entityIn) {
        if (this.borderOrb != 0) {
            return false;
        }
        this.up = true;
        this.attackTimer = 0.0f;
        this.field_70170_p.func_72960_a((Entity)this, (byte)12);
        boolean flag = false;
        AxisAlignedBB axisalignedbb = new AxisAlignedBB(entityIn.field_70165_t, entityIn.field_70163_u, entityIn.field_70161_v, entityIn.field_70165_t + 1.0, entityIn.field_70163_u + 1.0, entityIn.field_70161_v + 1.0).func_186662_g(2.0);
        List moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
        for (EntityLivingBase mob : moblist) {
            if (mob instanceof EntityParasiteBase) {
                if (this.func_70638_az() != mob) continue;
                this.func_70624_b(null);
                return false;
            }
            if (mob == this || !this.func_70685_l((Entity)mob) || !this.func_70652_k((Entity)mob)) continue;
            flag = true;
        }
        return flag;
    }

    @Override
    public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
        IEntityLivingData floo = super.func_180482_a(difficulty, livingdata);
        if (this.field_70146_Z.nextDouble() < SPConfig.variantChance || this.phaseCreated >= SPConfigSystems.evolutionParasiteAlwaysVariant || this.canChangeVariant) {
            switch (this.field_70146_Z.nextInt(3)) {
                case 0: {
                    this.setSkin(5);
                    break;
                }
                case 1: {
                    this.setSkin(6);
                    break;
                }
                case 2: {
                    this.setSkin(7);
                }
            }
        }
        return floo;
    }

    @Override
    public void func_70014_b(NBTTagCompound compound) {
        super.func_70014_b(compound);
    }

    @Override
    public void func_70037_a(NBTTagCompound compound) {
        super.func_70037_a(compound);
    }

    @SideOnly(value=Side.CLIENT)
    public float getAttackTimer() {
        return this.attackTimer;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_70103_a(byte id) {
        if (id == 12) {
            this.up = true;
            this.attackTimer = 0.0f;
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
                return this.skillEnraged;
            }
        }
        return super.getFinished(attID);
    }

    @Override
    public void setFinished(byte attID, boolean in) {
        switch (attID) {
            case 1: {
                this.skillEnraged = in;
                return;
            }
        }
        super.setFinished(attID, in);
    }

    @Override
    public void doSpecialSkill(byte id) {
        switch (id) {
            case 1: {
                this.smash();
                return;
            }
        }
        super.doSpecialSkill(id);
    }

    private void smash() {
        if (!this.field_70122_E) {
            this.skillEnraged = true;
            this.setParasiteStatus(0);
            this.border = 0;
            return;
        }
        if (this.border == 2) {
            float v = this.field_70146_Z.nextFloat() * 0.4f + 1.0f;
            this.func_184185_a(SPSounds.ATTACKBANO, 4.0f, v);
        }
        if (this.border < 20) {
            this.field_70170_p.func_72960_a((Entity)this, (byte)100);
            this.setParasiteStatus(25);
            this.func_70661_as().func_75499_g();
            this.func_70690_d(new PotionEffect(MobEffects.field_76421_d, 110, 100, false, false));
        }
        ++this.border;
        if (this.border >= 20) {
            this.setParasiteStatus(3);
            if (this.border == 0) {
                this.func_184185_a(SPSounds.SWIPE, 5.0f, 1.0f);
            }
            if (this.border % 7 == 0) {
                this.func_184185_a(SPSounds.SWIPE, 5.0f, 1.0f);
            }
            AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0).func_72314_b(7.0, 3.0, 7.0);
            List moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
            for (EntityLivingBase mob : moblist) {
                if (mob == this || mob instanceof EntityParasiteBase || !this.func_70685_l((Entity)mob)) continue;
                this.func_70652_k((Entity)mob);
                mob.func_70653_a((Entity)mob, 2.0f, mob.field_70165_t - this.field_70165_t, mob.field_70161_v - this.field_70161_v);
            }
        }
        if (this.border > 100) {
            this.skillEnraged = true;
            this.setParasiteStatus(0);
            this.border = 0;
        }
    }

    @Override
    protected EntityPCosmical getThis() {
        return new EntityBanoFocused(this.field_70170_p);
    }
}

