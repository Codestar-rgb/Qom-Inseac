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
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.SPacketSetPassengers
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package com.dhanantry.scapeandrunparasites.entity.monster.infected.special;

import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIEvade;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIGetFollowers;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISwimmingDiving;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIWaterLeapAtTargetStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPAssimara;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.network.MsgQlipShake;
import com.dhanantry.scapeandrunparasites.network.SRPNetwork;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
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
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketSetPassengers;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class EntitySpeHuman
extends EntityPAssimara {
    private int host;
    double randomX;
    double randomZ;

    public EntitySpeHuman(World worldIn) {
        super(worldIn);
        this.func_70105_a(0.6f, 1.95f);
        this.canModRender = 1;
        this.type = (byte)11;
    }

    @Override
    public int getParasiteIDRegister() {
        return 324;
    }

    @Override
    public int canSpawnByIDData() {
        return SRPConfigMobs.infhumanCanSpawnAssimilatedNat;
    }

    @Override
    public int getIDSpawn() {
        return 6;
    }

    protected void func_184651_r() {
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimmingDiving((EntityLiving)this, 0.08));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIWaterLeapAtTargetStatus(this, 0.7f, 1.5, 3, 20, 0));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAttackMeleeStatus(this, 1.5, false, 0.0));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIGetFollowers(this, 1, 16));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIEvade(this, 20, 0, 1.0, true, 3, true));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.MARHUMAN_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.MARHUMAN_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.27);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.MARHUMAN_KD_RESISTANCE);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.MARHUMAN_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.assimaraFollow);
    }

    public void func_180430_e(float distance, float damageMultiplier) {
        if (distance >= 60.0f) {
            super.func_180430_e(distance, damageMultiplier);
        }
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K && this.srpTicks == 10 && this.func_70638_az() != null && this.func_70638_az().func_70068_e((Entity)this) < 6.25 && !this.func_70638_az().func_184207_aI()) {
            this.setParasiteStatus(3);
            this.func_184205_a((Entity)this.func_70638_az(), true);
            this.func_70638_az().func_70690_d(new PotionEffect(MobEffects.field_76437_t, 60, 0, false, false));
            if (this.func_70638_az() instanceof EntityPlayer) {
                this.func_184102_h().func_184103_al().func_148540_a((Packet)new SPacketSetPassengers((Entity)this.func_70638_az()));
            }
        }
        if (this.func_184187_bx() instanceof EntityLivingBase) {
            this.randomPush((EntityLivingBase)this.func_184187_bx(), 0.13);
            ((EntityLivingBase)this.func_184187_bx()).func_70690_d(new PotionEffect(SRPPotions.NOVISION_E, 20, 0, false, false));
            ((EntityLivingBase)this.func_184187_bx()).func_70690_d(new PotionEffect(MobEffects.field_76419_f, 20, 0, false, false));
            ((EntityLivingBase)this.func_184187_bx()).func_70690_d(new PotionEffect(MobEffects.field_76424_c, 20, -200, false, false));
            if (this.srpTicks == 10) {
                this.func_70652_k(this.func_184187_bx());
            }
        }
    }

    public void randomPush(EntityLivingBase player, double strength) {
        if (this.srpTicks == 10 || this.randomX == 0.0) {
            this.randomX = (Math.random() - 0.5) * 2.0;
            this.randomZ = (Math.random() - 0.5) * 2.0;
            double magnitude = Math.sqrt(this.randomX * this.randomX + this.randomZ * this.randomZ);
            if (magnitude == 0.0) {
                return;
            }
            this.randomX /= magnitude;
            this.randomZ /= magnitude;
        }
        player.field_70159_w += this.randomX * strength;
        player.field_70179_y += this.randomZ * strength;
        if (this.srpTicks == 10 && player instanceof EntityPlayerMP && !this.field_70170_p.field_72995_K) {
            SRPNetwork.CHANNEL.sendTo((IMessage)new MsgQlipShake(250, 0, true, false, 4.0f), (EntityPlayerMP)player);
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
                    } else if (this.func_184187_bx() == null) {
                        this.setParasiteStatus(Math.min(k, 1));
                    }
                } else {
                    this.setParasiteStatus(0);
                    this.func_70624_b(null);
                }
            }
        }
    }

    @Override
    public boolean func_70652_k(@Nonnull Entity entityIn) {
        boolean flag = super.func_70652_k(entityIn);
        if (flag && this.field_70146_Z.nextDouble() < (double)SRPConfig.infectedBleedingChance && entityIn instanceof EntityLivingBase) {
            SRPPotions.applyStackPotion(SRPPotions.BLEED_E, (EntityLivingBase)entityIn, 100, 0);
        }
        return flag;
    }

    public float func_70047_e() {
        return 1.73f;
    }

    public void func_110145_l(Entity entityIn) {
        super.func_110145_l(entityIn);
    }

    protected SoundEvent func_184639_G() {
        if (this.getParasiteStatus() != 0) {
            return SRPSounds.MOBSILENCE;
        }
        return SRPSounds.INFECTEDHUMAN_GROWL;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SRPSounds.INFECTEDHUMAN_HURT;
    }

    protected SoundEvent func_184615_bR() {
        return SRPSounds.INFECTEDHUMAN_DEATH;
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.field_187939_hm;
    }

    protected void func_180429_a(BlockPos pos, Block blockIn) {
        this.func_184185_a(this.getStepSound(), 0.15f, 1.0f);
    }

    @Override
    public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
        IEntityLivingData floo = super.func_180482_a(difficulty, livingdata);
        if (this.field_70146_Z.nextDouble() < SRPConfig.variantChance || this.phaseCreated >= SRPConfigSystems.evolutionParasiteAlwaysVariant) {
            this.setSkin(this.field_70170_p.field_73012_v.nextInt(3) + 1);
        }
        return floo;
    }
}

