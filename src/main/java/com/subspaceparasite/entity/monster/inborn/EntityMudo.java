/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAreaEffectCloud
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILeapAtTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.passive.EntityWaterMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.MobEffects
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.pathfinding.PathNavigateClimber
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.World
 */
package com.subspaceparasite.entity.monster.inborn;

import com.subspaceparasite.entity.ai.EntityAIAttackMeleeStatus;
import com.subspaceparasite.entity.ai.EntityAIAvoidEntityStatus;
import com.subspaceparasite.entity.ai.EntityAIAvoidOrAttack;
import com.subspaceparasite.entity.ai.EntityAINearestAttackableTargetStatus;
import com.subspaceparasite.entity.ai.EntityAISkill;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.entity.monster.inborn.EntityLodo;
import com.subspaceparasite.entity.monster.inborn.EntityNuuh;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.init.SPPotions;
import com.subspaceparasite.init.SPSounds;
import com.subspaceparasite.util.ParasiteEventEntity;
import com.subspaceparasite.util.ParasiteEventWorld;
import com.subspaceparasite.util.SPAttributes;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigMobs;
import com.subspaceparasite.util.config.SPConfigSystems;
import com.subspaceparasite.util.config.SPConfigWorld;
import com.subspaceparasite.world.SPSaveData;
import com.google.common.base.Predicate;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateClimber;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityMudo
extends EntityParasiteBase {
    private static final DataParameter<Byte> CLIMBING = EntityDataManager.func_187226_a(EntityMudo.class, (DataSerializer)DataSerializers.field_187191_a);

    public EntityMudo(World worldIn) {
        super(worldIn);
        this.func_70105_a(0.85f, 1.0f);
        this.type = (byte)5;
        this.MiniDamage = SPConfigMobs.mudoMinDamage;
        this.field_70728_aV = SPAttributes.XP_LiTTLE;
        this.attackSpeedT = 10;
    }

    @Override
    public int getParasiteIDRegister() {
        return 12;
    }

    @Override
    public void applyBonuses(SPSaveData sabe, World world) {
        super.applyBonuses(sabe, world);
        this.field_70715_bh.func_75776_a(4, new EntityAINearestAttackableTargetStatus<EntityPlayer>(this, EntityPlayer.class, 0, true, false, null, SPConfig.primitiveSneakPen, SPConfig.primitiveInviPen));
        if (SPConfig.mobattacking) {
            this.field_70715_bh.func_75776_a(4, new EntityAINearestAttackableTargetStatus<EntityLiving>(this, EntityLiving.class, 0, true, false, new Predicate<EntityLiving>(){

                public boolean apply(@Nullable EntityLiving entity) {
                    return !(entity instanceof EntityWaterMob) && !(entity instanceof EntityAnimal) && !(entity instanceof EntityVillager) && !ParasiteEventEntity.checkEntity((EntityLivingBase)entity, SPConfig.mobattackingBlackList, SPConfig.mobattackingBlackListWhite);
                }
            }, SPConfig.primitiveSneakPen, SPConfig.primitiveInviPen));
        }
        if (SPConfigSystems.useEvolution) {
            byte phase = sabe.getEvolutionPhase(world.field_73011_w.getDimension());
            if (phase >= SPConfigSystems.evolutionMudoAttack) {
                if (phase >= SPConfigSystems.evolutionAssimilatedDehiding) {
                    this.field_70715_bh.func_75776_a(4, new EntityAINearestAttackableTargetStatus<EntityLiving>(this, EntityLiving.class, 10, true, false, new Predicate<EntityLiving>(){

                        public boolean apply(@Nullable EntityLiving entity) {
                            return !(entity instanceof EntityMob) && !(entity instanceof EntityWaterMob) && !ParasiteEventEntity.checkEntity((EntityLivingBase)entity, SPConfig.mobattackingBlackList, SPConfig.mobattackingBlackListWhite);
                        }
                    }, SPConfig.primitiveSneakPen, SPConfig.primitiveInviPen));
                } else {
                    this.field_70715_bh.func_75776_a(4, new EntityAINearestAttackableTargetStatus<EntityLiving>(this, EntityLiving.class, 10, true, false, new Predicate<EntityLiving>(){

                        public boolean apply(@Nullable EntityLiving entity) {
                            return !entity.func_70644_a(SPPotions.COTH_E) && !(entity instanceof EntityMob) && !(entity instanceof EntityWaterMob) && !ParasiteEventEntity.checkEntity((EntityLivingBase)entity, SPConfig.mobattackingBlackList, SPConfig.mobattackingBlackListWhite);
                        }
                    }, SPConfig.primitiveSneakPen, SPConfig.primitiveInviPen));
                }
            } else {
                this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIMudoInfest(this, 1.0));
                this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIAvoidOrAttack(this, 0.0f, 10, 2));
                this.field_70714_bg.func_75776_a(5, new EntityAIAvoidEntityStatus<EntityLiving>(this, EntityLiving.class, new Predicate<EntityLiving>(){

                    public boolean apply(@Nullable EntityLiving entity) {
                        return !(entity instanceof EntityWaterMob) && !(entity instanceof EntityParasiteBase) && !(entity instanceof EntityAnimal) && !(entity instanceof EntityVillager);
                    }
                }, 8.0f, 1.3));
            }
        }
    }

    @Override
    public void applyGene(boolean[] kool, float[] goon) {
    }

    @Override
    public void func_70624_b(EntityLivingBase entitylivingbaseIn) {
        super.func_70624_b(entitylivingbaseIn);
    }

    protected void func_184651_r() {
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISkill(this, 40, 100, 5, true, 14));
        this.setskillLeapValues(0.7f, 2.5, 0);
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false, new Class[0]));
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAttackMeleeStatus(this, 1.3, false, -1.0));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAILeapAtTarget((EntityLiving)this, 0.4f));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        if (SPConfigMobs.mudoAnimalAttacking && !SPConfigSystems.useEvolution) {
            this.field_70715_bh.func_75776_a(4, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityAnimal.class, true));
        }
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        if (this.srpTicks == 10 && !this.field_70170_p.field_72995_K && this.killcount >= (double)SPConfigMobs.mudoTunnelValue && this.phaseCreated < SPConfigMobs.mudoTunnelPhase && this.field_70146_Z.nextInt(30) == 0) {
            if (this.func_70638_az() != null) {
                return;
            }
            if (this.field_70170_p.func_180495_p(this.func_180425_c()).func_177230_c() == Blocks.field_150350_a && this.field_70170_p.func_180495_p(this.func_180425_c().func_177977_b()).func_185913_b() && this.field_70170_p.func_180495_p(this.func_180425_c().func_177977_b()).func_185917_h()) {
                this.field_70170_p.func_175656_a(this.func_180425_c(), SPBlocks.buglin.func_176223_P());
                this.killcount -= (double)SPConfigMobs.mudoTunnelValue;
            }
        }
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SPAttributes.MUDO_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SPAttributes.MUDO_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SPAttributes.MUDO_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SPAttributes.MUDO_KD_RESISTANCE);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(32.0);
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
            ((EntityLivingBase)entityIn).func_70690_d(new PotionEffect(MobEffects.field_76421_d, 40, 1));
            if (!((EntityLivingBase)entityIn).func_70644_a(SPPotions.COTH_E)) {
                ((EntityLivingBase)entityIn).func_70690_d(new PotionEffect(SPPotions.COTH_E, 3600, 0, false, false));
            }
        }
        return flag;
    }

    @Override
    public void func_70074_a(EntityLivingBase entityLivingIn) {
        if (this.killcount >= 0.0) {
            this.killcount += 1.0;
        }
        if (!this.field_70170_p.field_72995_K && SPConfigSystems.useEvolution) {
            SPSaveData data = SPSaveData.get(this.field_70170_p, 43);
            data.setTotalKills(this.field_70170_p.field_73011_w.getDimension(), SPConfigSystems.valueKill, true, this.field_70170_p, true, 37);
        }
        if (!entityLivingIn.func_70644_a(SPPotions.COTH_E)) {
            entityLivingIn.func_70690_d(new PotionEffect(SPPotions.COTH_E, 3600, 0, false, false));
        }
        ParasiteEventEntity.convertEntity(entityLivingIn, entityLivingIn.getEntityData(), true, SPConfigSystems.COTHVictimParasite);
        this.func_70690_d(new PotionEffect(MobEffects.field_76428_l, 80, 0, false, false));
        this.setWait(10);
        if (!this.field_70170_p.field_72995_K && this.killcount > (double)SPConfigMobs.mudoMangler && !this.field_70128_L && ParasiteEventEntity.canSpawnNext) {
            ParasiteEventEntity.spawnNext(this, new EntityNuuh(this.field_70170_p), true, false);
        }
    }

    public void func_180430_e(float distance, float damageMultiplier) {
        if (distance >= 60.0f) {
            super.func_180430_e(distance, damageMultiplier);
        }
    }

    public double func_70042_X() {
        return this.field_70131_O * 0.5f;
    }

    protected PathNavigate func_175447_b(World worldIn) {
        return new PathNavigateClimber((EntityLiving)this, worldIn);
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (!this.field_70170_p.field_72995_K) {
            this.setBesideClimbableBlock(this.field_70123_F);
            if (this.srpTicks == 10 && this.killcount > (double)SPConfigMobs.mudoMangler && ParasiteEventEntity.canSpawnNext) {
                ParasiteEventEntity.spawnNext(this, new EntityNuuh(this.field_70170_p), true, true);
            }
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
        return 0.8f;
    }

    @Override
    public void func_70645_a(DamageSource cause) {
        if (!this.field_70170_p.field_72995_K) {
            if (SPConfigWorld.coloniesActivated || this.canChangeVariant) {
                if (ParasiteEventWorld.numberofColonies(this.field_70170_p) >= 1 && SPConfigMobs.lodoEnabled || this.canChangeVariant) {
                    ParasiteEventEntity.spawnNext(this, new EntityLodo(this.field_70170_p), true, false);
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
        return SPSounds.MUDO_GROWL;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SPSounds.MUDO_HURT;
    }

    protected SoundEvent func_184615_bR() {
        return SPSounds.MUDO_DEATH;
    }

    protected void func_180429_a(BlockPos pos, Block blockIn) {
        this.func_184185_a(this.getStepSound(), this.func_70599_aP(), this.func_70647_i());
    }

    protected SoundEvent getStepSound() {
        return SPSounds.SMALL_STEPS;
    }

    static class EntityAIMudoInfest
    extends EntityAIBase {
        private final EntityMudo parent;
        private int count;

        public EntityAIMudoInfest(EntityMudo animal, double speedIn) {
            this.parent = animal;
            this.count = 0;
            this.func_75248_a(3);
        }

        public boolean func_75250_a() {
            ++this.count;
            if (this.count >= 20) {
                boolean tar = this.parent.func_70638_az() == null;
                boolean nav = this.parent.func_70661_as().func_75500_f();
                String na = "";
                if (!tar) {
                    na = this.parent.func_70638_az().func_70005_c_();
                }
                this.count = 0;
                return this.parent.func_70638_az() == null && this.parent.func_70661_as().func_75500_f();
            }
            return false;
        }

        public void func_75246_d() {
            AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.parent.field_70165_t, this.parent.field_70163_u, this.parent.field_70161_v, this.parent.field_70165_t + 1.0, this.parent.field_70163_u + 1.0, this.parent.field_70161_v + 1.0).func_72314_b(12.0, 3.0, 12.0);
            List moblist = this.parent.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
            for (EntityLivingBase mob : moblist) {
                if (mob == this.parent || mob instanceof EntityMob) continue;
                if (!mob.func_70644_a(SPPotions.COTH_E) && this.parent.func_70685_l((Entity)mob) && this.parent.func_70661_as().func_75497_a((Entity)mob, 1.3) && this.parent.func_70068_e((Entity)mob) < 9.0) {
                    this.spawnLingeringCloud();
                    this.parent.func_184185_a(SPSounds.MUDO_CLOUD, 2.0f, 1.0f);
                }
                if (this.parent.func_70661_as().func_75500_f()) continue;
                return;
            }
        }

        private void spawnLingeringCloud() {
            EntityAreaEffectCloud entityareaeffectcloud = new EntityAreaEffectCloud(this.parent.field_70170_p, this.parent.field_70165_t, this.parent.field_70163_u, this.parent.field_70161_v);
            entityareaeffectcloud.func_184483_a(this.parent.field_70130_N * 4.0f);
            entityareaeffectcloud.func_184495_b(-0.5f);
            entityareaeffectcloud.func_184485_d(10);
            entityareaeffectcloud.func_184486_b(entityareaeffectcloud.func_184489_o() * 2);
            entityareaeffectcloud.func_184487_c(-entityareaeffectcloud.func_184490_j() / (float)entityareaeffectcloud.func_184489_o());
            entityareaeffectcloud.func_184496_a(new PotionEffect(SPPotions.COTH_E, 3600, 1, false, false));
            this.parent.field_70170_p.func_72838_d((Entity)entityareaeffectcloud);
        }
    }
}

