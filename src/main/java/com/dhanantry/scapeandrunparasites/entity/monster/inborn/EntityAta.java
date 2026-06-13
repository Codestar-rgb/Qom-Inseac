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
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILeapAtTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
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
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package com.dhanantry.scapeandrunparasites.entity.monster.inborn;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAINearestAttackableTargetStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISkill;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISwimmingDiving;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.network.SRPPacketParticle;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.google.common.base.Predicate;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
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
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class EntityAta
extends EntityParasiteBase {
    private static final DataParameter<Byte> CLIMBING = EntityDataManager.func_187226_a(EntityAta.class, (DataSerializer)DataSerializers.field_187191_a);
    private int lifespan = 0;

    public EntityAta(World worldIn) {
        super(worldIn);
        this.func_70105_a(0.85f, 1.0f);
        this.type = (byte)5;
        this.lifespan = 0;
        this.field_70714_bg.func_85156_a((EntityAIBase)this.folow);
        this.field_70715_bh.func_75776_a(4, new EntityAINearestAttackableTargetStatus<EntityPlayer>(this, EntityPlayer.class, 0, true, false, null, SRPConfig.pureSneakPen, SRPConfig.pureInviPen));
        if (SRPConfig.mobattacking) {
            this.field_70715_bh.func_75776_a(4, new EntityAINearestAttackableTargetStatus<EntityLiving>(this, EntityLiving.class, 0, true, false, new Predicate<EntityLiving>(){

                public boolean apply(@Nullable EntityLiving entity) {
                    return !(entity instanceof EntityWaterMob) && !ParasiteEventEntity.checkEntity((EntityLivingBase)entity, SRPConfig.mobattackingBlackList, SRPConfig.mobattackingBlackListWhite);
                }
            }, SRPConfig.pureSneakPen, SRPConfig.pureInviPen));
        }
        this.attackSpeedT = 6;
    }

    @Override
    public int getParasiteIDRegister() {
        return 91;
    }

    protected void func_184651_r() {
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISkill(this, 20, 100, 5, true, 14));
        this.setskillLeapValues(0.4f, 1.0, 0);
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false, new Class[0]));
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimmingDiving((EntityLiving)this, 0.12));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAttackMeleeStatus(this, 1.3, false, -1.0));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAILeapAtTarget((EntityLiving)this, 0.4f));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        ++this.lifespan;
        if (this.lifespan > 1200) {
            SRPMain.network.sendToAll((IMessage)new SRPPacketParticle(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.5f, 0.5f, 11));
            this.func_70106_y();
        }
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.ATA_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.ATA_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.34559);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.ATA_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.ATA_KD_RESISTANCE);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(32.0);
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
            if (target.func_110143_aJ() <= target.func_110138_aP() * SRPConfigSystems.hijackHealth) {
                if (ParasiteEventEntity.convertEntityFeral((EntityLivingBase)entityIn, entityIn.getEntityData(), true, SRPConfigSystems.COTHVictimParasite) || ParasiteEventEntity.hijackEntity((EntityLivingBase)entityIn, SRPConfigSystems.HIJACKVictimParasite)) {
                    SRPMain.network.sendToAll((IMessage)new SRPPacketParticle(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.5f, 0.5f, 11));
                    SRPMain.network.sendToAll((IMessage)new SRPPacketParticle(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.5f, 0.5f, 11));
                } else {
                    entityIn.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), (float)this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e());
                    SRPMain.network.sendToAll((IMessage)new SRPPacketParticle(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.5f, 0.5f, 10));
                }
            } else {
                entityIn.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), (float)this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e());
                SRPMain.network.sendToAll((IMessage)new SRPPacketParticle(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.5f, 0.5f, 10));
            }
            SRPMain.network.sendToAll((IMessage)new SRPPacketParticle(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.5f, 0.5f, 10));
            SRPMain.network.sendToAll((IMessage)new SRPPacketParticle(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.5f, 0.5f, 10));
            SRPMain.network.sendToAll((IMessage)new SRPPacketParticle(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.5f, 0.5f, 10));
            this.func_184185_a(SRPSounds.BUTHOL_BOOM, 0.3f, (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2f + 1.0f);
            SRPPotions.applyStackPotion(SRPPotions.VIRA_E, (EntityLivingBase)entityIn, 120, 2);
            this.func_70106_y();
        }
    }

    @Override
    protected void func_70609_aI() {
        SRPMain.network.sendToAll((IMessage)new SRPPacketParticle(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.5f, 0.5f, 10));
        this.func_70106_y();
    }

    @Override
    public void func_70074_a(EntityLivingBase entityLivingIn) {
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
        super.func_70645_a(cause);
    }

    @Override
    protected boolean onDeathDislo(DamageSource cause) {
        return false;
    }

    protected SoundEvent func_184639_G() {
        if (this.getParasiteStatus() != 0) {
            return SRPSounds.MOBSILENCE;
        }
        return SRPSounds.MOBSILENCE;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SRPSounds.MOBSILENCE;
    }

    protected SoundEvent func_184615_bR() {
        return SRPSounds.MOBSILENCE;
    }

    protected void func_180429_a(BlockPos pos, Block blockIn) {
        this.func_184185_a(this.getStepSound(), this.func_70599_aP(), this.func_70647_i());
    }

    protected SoundEvent getStepSound() {
        return SRPSounds.SMALL_STEPS;
    }
}

