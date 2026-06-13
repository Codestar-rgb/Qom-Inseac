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
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIAttackMelee
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.passive.EntityWaterMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.MobEffects
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.World
 *  net.minecraftforge.event.ForgeEventFactory
 */
package com.dhanantry.scapeandrunparasites.entity.monster.inborn;

import com.dhanantry.scapeandrunparasites.entity.EntityToxicCloud;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackSwell;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAINearestAttackableTargetStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.util.spawn.ParasiteSummon;
import com.google.common.base.Predicate;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class EntityGothol
extends EntityParasiteBase {
    public EntityGothol(World worldIn) {
        super(worldIn);
        this.field_70715_bh.func_75776_a(4, new EntityAINearestAttackableTargetStatus<EntityPlayer>(this, EntityPlayer.class, 0, true, false, null, SRPConfig.adaptedSneakPen, SRPConfig.adaptedInviPen));
        if (SRPConfig.mobattacking) {
            this.field_70715_bh.func_75776_a(4, new EntityAINearestAttackableTargetStatus<EntityLiving>(this, EntityLiving.class, 0, true, false, new Predicate<EntityLiving>(){

                public boolean apply(@Nullable EntityLiving entity) {
                    return !(entity instanceof EntityWaterMob) && !(entity instanceof EntityAnimal) && !ParasiteEventEntity.checkEntity((EntityLivingBase)entity, SRPConfig.mobattackingBlackList, SRPConfig.mobattackingBlackListWhite);
                }
            }, SRPConfig.adaptedSneakPen, SRPConfig.adaptedInviPen));
        }
        this.func_70105_a(0.85f, 2.3f);
        this.field_70728_aV = SRPAttributes.XP_PRIMITIVE;
        this.fuseTime = 70;
        this.type = (byte)41;
        this.killcount = -10.0;
    }

    @Override
    public int getParasiteIDRegister() {
        return 304;
    }

    protected void func_184651_r() {
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIAttackSwell(this));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.1, false));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.GOTHOL_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.GOTHOL_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.2);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.GOTHOL_KD_RESISTANCE);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.GOTHOL_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(32.0);
    }

    protected SoundEvent func_184639_G() {
        if (this.getParasiteStatus() != 0) {
            return SRPSounds.MOBSILENCE;
        }
        return SRPSounds.CARRIER_GROWL;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SRPSounds.CARRIER_HURT;
    }

    protected SoundEvent func_184615_bR() {
        return SRPSounds.CARRIER_DEATH;
    }

    @Override
    public void func_70014_b(NBTTagCompound compound) {
        super.func_70014_b(compound);
        compound.func_74777_a("Fuse", (short)this.fuseTime);
    }

    @Override
    public void func_70037_a(NBTTagCompound compound) {
        super.func_70037_a(compound);
        if (compound.func_150297_b("Fuse", 99)) {
            this.fuseTime = compound.func_74765_d("Fuse");
        }
    }

    public void func_70071_h_() {
        if (this.func_70089_S()) {
            this.lastActiveTime = this.timeSinceIgnited;
            if ((double)this.func_110143_aJ() < (double)this.func_110138_aP() * 0.05) {
                this.setSelfeState(1);
            }
            this.dyingBurst(false, 2);
        }
        super.func_70071_h_();
    }

    @Override
    protected void func_70609_aI() {
        if (this.func_70027_ad()) {
            super.func_70609_aI();
        } else {
            this.setSelfeState(1);
            this.dyingBurst(true, 2);
        }
    }

    @Override
    public boolean func_70652_k(@Nonnull Entity entityIn) {
        return true;
    }

    @Override
    protected void selfExplode() {
        if (!this.field_70170_p.field_72995_K) {
            double i1 = MathHelper.func_76128_c((double)(this.field_70163_u + 0.1));
            double l1 = this.field_70165_t;
            double i2 = this.field_70161_v;
            World world = this.field_70170_p;
            int range = 3;
            for (int k2 = -1 * range; k2 <= 1 * range; ++k2) {
                for (int l2 = -1 * range; l2 <= 1 * range; ++l2) {
                    double i3 = l1 + (double)k2;
                    double l = i2 + (double)l2;
                    BlockPos blockpos = new BlockPos(i3, i1, l);
                    if ((blockpos = ParasiteEventEntity.getFloor(this.field_70170_p, blockpos, 3)) == null || this.field_70170_p.field_73012_v.nextInt(2) != 0) continue;
                    this.field_70170_p.func_175656_a(blockpos, SRPBlocks.InfestRemain.func_176203_a(1));
                }
            }
        }
        switch (this.getSkin()) {
            case 1: {
                boolean flag = ForgeEventFactory.getMobGriefingEvent((World)this.field_70170_p, (Entity)this) && SRPConfigMobs.gotholGriefing;
                ParasiteEventEntity.createExplosion(this.field_70170_p, (Entity)this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 4.0f, flag);
                if (!this.field_70170_p.field_72995_K) {
                    AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0).func_186662_g(11.0);
                    List moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
                    for (EntityLivingBase mob : moblist) {
                        if (mob instanceof EntityParasiteBase) continue;
                        mob.func_70097_a(DamageSource.field_82727_n, (float)this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e());
                        SRPPotions.applyStackPotion(SRPPotions.VIRA_E, mob, 400, 4);
                        mob.func_70690_d(new PotionEffect(SRPPotions.VOMIT_E, 500, 0, false, true));
                    }
                    this.func_184185_a(SRPSounds.RATHOL_BOOM, 2.0f, 1.0f);
                    this.field_70729_aU = true;
                    this.func_70106_y();
                    this.spawnLingeringCloud();
                }
                return;
            }
        }
        boolean flag = ForgeEventFactory.getMobGriefingEvent((World)this.field_70170_p, (Entity)this) && SRPConfigMobs.gotholGriefing;
        ParasiteEventEntity.createExplosion(this.field_70170_p, (Entity)this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 4.0f, flag);
        if (!this.field_70170_p.field_72995_K) {
            AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0).func_186662_g(7.0);
            List moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
            for (EntityLivingBase mob : moblist) {
                if (mob instanceof EntityParasiteBase) continue;
                SRPPotions.applyStackPotion(SRPPotions.VIRA_E, mob, 400, 2);
                mob.func_70690_d(new PotionEffect(SRPPotions.VOMIT_E, 500, 0, false, true));
            }
            this.func_184185_a(SRPSounds.RATHOL_BOOM, 2.0f, 1.0f);
            this.field_70729_aU = true;
            this.func_70106_y();
            this.spawnLingeringCloud();
            ParasiteSummon.spawnM(this, SRPConfigMobs.gotholMobs, 0, false, this.func_95999_t());
        }
    }

    private void spawnLingeringCloud() {
        switch (this.getSkin()) {
            case 1: {
                EntityToxicCloud entityareaeffectcloud = new EntityToxicCloud(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v);
                entityareaeffectcloud.setRadius(this.field_70130_N * 2.5f, 0.5f);
                entityareaeffectcloud.setWaitTime(4);
                entityareaeffectcloud.setDuration(entityareaeffectcloud.getDuration() * 2);
                entityareaeffectcloud.setRadiusPerTick(-entityareaeffectcloud.getRadius() / (float)entityareaeffectcloud.getDuration());
                entityareaeffectcloud.addEffect(new PotionEffect(MobEffects.field_76436_u, 300, 2));
                entityareaeffectcloud.addEffect(new PotionEffect(SRPPotions.COTH_E, 3600, 2, false, false));
                entityareaeffectcloud.addEffect(new PotionEffect(SRPPotions.VIRA_E, 3600, 2, false, false));
                this.field_70170_p.func_72838_d((Entity)entityareaeffectcloud);
                return;
            }
        }
        EntityToxicCloud entityareaeffectcloud = new EntityToxicCloud(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v);
        entityareaeffectcloud.setRadius(this.field_70130_N * 2.5f, 0.5f);
        entityareaeffectcloud.setWaitTime(10);
        entityareaeffectcloud.setDuration(entityareaeffectcloud.getDuration() * 2);
        entityareaeffectcloud.setRadiusPerTick(-entityareaeffectcloud.getRadius() / (float)entityareaeffectcloud.getDuration());
        entityareaeffectcloud.addEffect(new PotionEffect(MobEffects.field_76436_u, 300, 0));
        entityareaeffectcloud.addEffect(new PotionEffect(SRPPotions.COTH_E, 3600, 0, false, false));
        entityareaeffectcloud.addEffect(new PotionEffect(SRPPotions.VIRA_E, 3600, 0, false, false));
        this.field_70170_p.func_72838_d((Entity)entityareaeffectcloud);
    }

    @Override
    public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
        IEntityLivingData floo = super.func_180482_a(difficulty, livingdata);
        if (this.field_70146_Z.nextDouble() < SRPConfig.variantChance || this.phaseCreated >= SRPConfigSystems.evolutionParasiteAlwaysVariant) {
            switch (this.field_70146_Z.nextInt(1)) {
                case 0: {
                    this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3);
                    this.setSkin(1);
                }
            }
        }
        return floo;
    }
}

