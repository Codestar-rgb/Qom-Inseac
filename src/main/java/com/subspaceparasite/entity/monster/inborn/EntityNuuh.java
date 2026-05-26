/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILeapAtTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.passive.EntityWaterMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.pathfinding.PathNavigateClimber
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.World
 */
package com.subspaceparasite.entity.monster.inborn;

import com.subspaceparasite.entity.ai.EntityAIAttackMeleeStatus;
import com.subspaceparasite.entity.ai.EntityAIEvadeDash;
import com.subspaceparasite.entity.ai.EntityAINearestAttackableTargetStatus;
import com.subspaceparasite.entity.ai.EntityAISkill;
import com.subspaceparasite.entity.ai.EntityAISwimmingDiving;
import com.subspaceparasite.entity.ai.misc.EntityPMalleable;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.entity.monster.inborn.EntityMudo;
import com.subspaceparasite.init.SPPotions;
import com.subspaceparasite.init.SPSounds;
import com.subspaceparasite.util.ParasiteEventEntity;
import com.subspaceparasite.util.ParasiteEventWorld;
import com.subspaceparasite.util.SPAttributes;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigMobs;
import com.subspaceparasite.util.config.SPConfigSystems;
import com.subspaceparasite.util.config.SPConfigWorld;
import com.google.common.base.Predicate;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateClimber;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityNuuh
extends EntityPMalleable {
    private static final DataParameter<Byte> CLIMBING = EntityDataManager.func_187226_a(EntityNuuh.class, (DataSerializer)DataSerializers.field_187191_a);

    public EntityNuuh(World worldIn) {
        super(worldIn);
        this.func_70105_a(1.0f, 1.0f);
        this.borderOrb = -1;
        this.field_70715_bh.func_75776_a(4, new EntityAINearestAttackableTargetStatus<EntityPlayer>(this, EntityPlayer.class, 0, true, false, null, SPConfig.pureSneakPen, SPConfig.pureInviPen));
        if (SPConfig.mobattacking) {
            this.field_70715_bh.func_75776_a(4, new EntityAINearestAttackableTargetStatus<EntityLiving>(this, EntityLiving.class, 0, true, false, new Predicate<EntityLiving>(){

                public boolean apply(@Nullable EntityLiving entity) {
                    return !(entity instanceof EntityWaterMob) && !(entity instanceof EntityAnimal) && !(entity instanceof EntityVillager) && !ParasiteEventEntity.checkEntity((EntityLivingBase)entity, SPConfig.mobattackingBlackList, SPConfig.mobattackingBlackListWhite);
                }
            }, SPConfig.pureSneakPen, SPConfig.pureInviPen));
        }
        this.field_70728_aV = SPAttributes.XP_PURE;
        this.type = (byte)51;
        this.foodSteal = SPConfig.pureFoodSteal;
        this.pointCap = SPConfig.purePointCap;
        this.pointReduction = SPConfig.purePointRed;
        this.chanceLearn = SPConfig.pureChanceLe;
        this.chanceLearnFire = SPConfig.pureChanceLeFire;
        this.DamageTypeCap = SPConfig.purePointDamCap;
        this.MiniDamage = SPConfigMobs.nuuhMinDamage;
        this.regen = SPConfig.pureRegen * SPConfig.globalHealthMultiplier;
        this.oneMindDeathValue = SPConfig.pureOneMindDeathV;
        this.regenEff = 3;
        this.adaptationCap = 0.95f;
        this.attackSpeedT = 6;
    }

    protected void func_184651_r() {
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISkill(this, 20, 100, 5, true, 14));
        this.setskillLeapValues(0.8f, 2.0, 0);
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimmingDiving((EntityLiving)this, 0.12));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAttackMeleeStatus(this, 1.3, false, -1.0));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAILeapAtTarget((EntityLiving)this, 0.4f));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIEvadeDash(this, 10, 2, 1, 1.0, 15));
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false, new Class[0]));
    }

    @Override
    public int getParasiteIDRegister() {
        return 76;
    }

    @Override
    public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
        EntityLivingBase src = source.func_76346_g() instanceof EntityLivingBase ? (EntityLivingBase)source.func_76346_g() : null;
        boolean took = super.func_70097_a(source, amount);
        if (this.field_70170_p.field_72995_K) {
            return took;
        }
        if (src instanceof EntityPlayer && this.func_70089_S()) {
            EntityPlayer player = (EntityPlayer)src;
            this.func_70604_c((EntityLivingBase)player);
            this.func_70624_b((EntityLivingBase)player);
        }
        return took;
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SPAttributes.NUUH_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SPAttributes.NUUH_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.37);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SPAttributes.NUUH_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SPAttributes.NUUH_KD_RESISTANCE);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(32.0);
    }

    @Override
    public boolean func_70652_k(@Nonnull Entity target) {
        return super.func_70652_k(target);
    }

    public void func_180430_e(float distance, float damageMultiplier) {
        if (distance >= 200.0f) {
            super.func_180430_e(distance, damageMultiplier);
        }
    }

    public void func_70110_aj() {
    }

    protected PathNavigate func_175447_b(World worldIn) {
        return new PathNavigateClimber((EntityLiving)this, worldIn);
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (!this.field_70170_p.field_72995_K) {
            this.setBesideClimbableBlock(this.field_70123_F);
        }
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

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(CLIMBING, (Object)0);
    }

    public boolean func_70617_f_() {
        return this.isBesideClimbableBlock();
    }

    public boolean isBesideClimbableBlock() {
        return ((Byte)this.field_70180_af.func_187225_a(CLIMBING) & 1) != 0;
    }

    public void setBesideClimbableBlock(boolean climbing) {
        byte b0 = (Byte)this.field_70180_af.func_187225_a(CLIMBING);
        if (this.func_70638_az() != null) {
            if (!this.func_70685_l((Entity)this.func_70638_az())) {
                if (this.func_70068_e((Entity)this.func_70638_az()) < 100.0) {
                    b0 = (byte)(b0 & 0xFFFFFFFE);
                    this.field_70180_af.func_187227_b(CLIMBING, (Object)b0);
                    return;
                }
            } else if (this.func_70638_az().field_70163_u + 1.0 < this.field_70163_u) {
                b0 = (byte)(b0 & 0xFFFFFFFE);
                this.field_70180_af.func_187227_b(CLIMBING, (Object)b0);
                return;
            }
        }
        b0 = climbing ? (byte)(b0 | 1) : (byte)(b0 & 0xFFFFFFFE);
        this.field_70180_af.func_187227_b(CLIMBING, (Object)b0);
    }

    public float func_70047_e() {
        return 0.9f;
    }

    @Override
    public void func_70645_a(DamageSource cause) {
        if (!this.field_70170_p.field_72995_K) {
            if (SPConfigWorld.coloniesActivated || this.canChangeVariant) {
                if (ParasiteEventWorld.numberofColonies(this.field_70170_p) >= 1 || this.canChangeVariant) {
                    ParasiteEventEntity.checkColony(this.field_70170_p, cause, this);
                    ParasiteEventEntity.spawnNext(this, new EntityMudo(this.field_70170_p), true, false);
                } else {
                    super.func_70645_a(cause);
                }
            } else {
                super.func_70645_a(cause);
            }
        }
    }

    @Override
    public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
        IEntityLivingData floo = super.func_180482_a(difficulty, livingdata);
        if (this.field_70146_Z.nextDouble() < SPConfig.variantChance || this.phaseCreated >= SPConfigSystems.evolutionParasiteAlwaysVariant || this.canChangeVariant) {
            switch (this.field_70146_Z.nextInt(2)) {
                case 0: {
                    this.setSkin(5);
                    break;
                }
                case 1: {
                    this.setSkin(6);
                }
            }
        }
        return floo;
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

    protected void func_180429_a(BlockPos pos, Block blockIn) {
        this.func_184185_a(this.getStepSound(), this.func_70599_aP(), this.func_70647_i());
    }

    protected SoundEvent getStepSound() {
        return SPSounds.SMALL_STEPS;
    }
}

