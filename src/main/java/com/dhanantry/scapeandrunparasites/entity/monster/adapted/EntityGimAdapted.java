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
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.entity.monster.adapted;

import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIBlockResidue;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIGetFollowers;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISwimmingDiving;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIWaterLeapAtTargetStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPAdapted;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityGim;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventWorld;
import com.dhanantry.scapeandrunparasites.util.PathNavigateClimberStatus;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
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
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityGimAdapted
extends EntityPAdapted {
    private static final DataParameter<Byte> CLIMBING = EntityDataManager.func_187226_a(EntityGimAdapted.class, (DataSerializer)DataSerializers.field_187191_a);
    private int border;
    private boolean skillpulling;

    public EntityGimAdapted(World worldIn) {
        super(worldIn);
        this.func_70105_a(1.511f, 3.655f);
        this.field_70138_W = 1.0f;
        this.field_70158_ak = true;
    }

    @Override
    public int getParasiteIDRegister() {
        return 315;
    }

    protected void func_184651_r() {
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimmingDiving((EntityLiving)this, 0.11));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIWaterLeapAtTargetStatus(this, 0.7f, 1.5, 3, 20, 0));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAttackMeleeStatus(this, 1.3, false, 8.0));
        if (SRPConfig.parasiteGenResidue) {
            this.field_70714_bg.func_75776_a(9, (EntityAIBase)new EntityAIBlockResidue(this, 2));
        }
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIGetFollowers(this, 3, 32));
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(CLIMBING, (Object)0);
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.GIM_HEALTH + SRPAttributes.GIM_A_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.GIM_ARMOR + SRPAttributes.GIM_A_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.33);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.GIM_KD_RESISTANCE + SRPAttributes.GIM_A_KD_RESISTANCE);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.GIM_ATTACK_DAMAGE + SRPAttributes.GIM_A_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.adaptedFollow);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
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

    public boolean func_70617_f_() {
        return this.isBesideClimbableBlock();
    }

    protected PathNavigate func_175447_b(World worldIn) {
        return new PathNavigateClimberStatus((EntityLiving)this, worldIn);
    }

    @Override
    public boolean func_70652_k(@Nonnull Entity entityIn) {
        boolean flag = super.func_70652_k(entityIn);
        if (flag && entityIn instanceof EntityLivingBase) {
            switch (this.getSkin()) {
                case 5: {
                    SRPPotions.applyStackPotion(SRPPotions.VIRA_E, (EntityLivingBase)entityIn, 100, 0);
                    break;
                }
                case 6: {
                    SRPPotions.applyStackPotion(SRPPotions.BLEED_E, (EntityLivingBase)entityIn, 100, 0);
                }
            }
        }
        return flag;
    }

    @Override
    protected void func_82167_n(Entity entityIn) {
        super.func_82167_n(entityIn);
        if (this.field_70170_p.field_72995_K) {
            return;
        }
        if (entityIn instanceof EntityLivingBase && !(entityIn instanceof EntityParasiteBase) && this.getSkin() == 5) {
            SRPPotions.applyStackPotion(SRPPotions.VIRA_E, (EntityLivingBase)entityIn, 100, 0);
        }
    }

    public float func_70047_e() {
        return 1.5f;
    }

    @Override
    public boolean scaryOrbEffect(EntityLivingBase in, int mobs) {
        boolean flag = super.scaryOrbEffect(in, mobs);
        if (flag) {
            ParasiteEventEntity.orbApplyEffects(in, this, SRPConfigMobs.gimadaptedOrbEffects, mobs);
        }
        return flag;
    }

    @Override
    public void func_70645_a(DamageSource cause) {
        if (!this.field_70170_p.field_72995_K) {
            if (SRPConfigWorld.coloniesActivated || this.canChangeVariant) {
                if (ParasiteEventWorld.numberofColonies(this.field_70170_p) >= 1 || this.canChangeVariant) {
                    ParasiteEventEntity.checkColony(this.field_70170_p, cause, this);
                    ParasiteEventEntity.spawnNext(this, new EntityGim(this.field_70170_p), true, false);
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
        return floo;
    }

    protected SoundEvent func_184639_G() {
        if (this.getParasiteStatus() != 0) {
            return SRPSounds.MOBSILENCE;
        }
        return SRPSounds.AGIM_GROWL;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        if (this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0) {
            return SRPSounds.MOBSILENCE;
        }
        return SRPSounds.AGIM_HURT;
    }

    protected SoundEvent func_184615_bR() {
        return SRPSounds.AGIM_DEATH;
    }

    protected void func_180429_a(BlockPos pos, Block blockIn) {
        this.func_184185_a(this.getStepSound(), this.func_70599_aP(), this.func_70647_i());
    }

    protected SoundEvent getStepSound() {
        return SRPSounds.HEAVY_STEPS_MULTIPLE;
    }

    @Override
    public boolean getFinished(byte attID) {
        switch (attID) {
            case 1: {
                return this.skillpulling;
            }
        }
        return super.getFinished(attID);
    }

    @Override
    public void setFinished(byte attID, boolean in) {
        switch (attID) {
            case 1: {
                this.skillpulling = in;
                return;
            }
        }
        super.setFinished(attID, in);
    }

    @Override
    public void doSpecialSkill(byte id) {
        switch (id) {
            case 1: {
                this.pullingE();
                return;
            }
        }
        super.doSpecialSkill(id);
    }

    private void pullingE() {
        if (!this.field_70122_E) {
            this.skillpulling = true;
            this.setParasiteStatus(0);
            this.border = 0;
            return;
        }
        if (this.border == 0) {
            // empty if block
        }
        if (this.border <= 2) {
            // empty if block
        }
        if (this.field_70173_aa % 20 != 0) {
            return;
        }
        ++this.border;
        if (this.func_70638_az() == null) {
            this.skillpulling = true;
            this.setParasiteStatus(0);
            this.border = 0;
            return;
        }
        if (!this.func_70685_l((Entity)this.func_70638_az())) {
            this.skillpulling = true;
            this.setParasiteStatus(0);
            this.border = 0;
            return;
        }
        if (this.border > 6) {
            this.skillpulling = true;
            this.setParasiteStatus(0);
            this.border = 0;
        }
    }

    public void resetPullSkill() {
    }
}

