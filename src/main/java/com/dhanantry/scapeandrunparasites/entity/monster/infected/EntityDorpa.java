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
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.projectile.EntityFireball
 *  net.minecraft.init.MobEffects
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.dhanantry.scapeandrunparasites.entity.monster.infected;

import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackProjectile;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIGetFollowers;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanShoot;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPInfected;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileWebball;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.spawn.ParasiteSummon;
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
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityDorpa
extends EntityPInfected
implements EntityCanShoot {
    public EntityDorpa(World worldIn) {
        super(worldIn);
        this.func_70105_a(1.9f, 2.1f);
        this.field_70138_W = 1.0f;
        this.canModRender = 1;
        this.type = (byte)14;
        this.killcount = -10.0;
    }

    @Override
    public int getParasiteIDRegister() {
        return 2;
    }

    @Override
    public int canSpawnByIDData() {
        return 0;
    }

    protected void func_184651_r() {
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAttackMeleeStatus(this, 1.5, false, 0.0));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIAttackProjectile(this, 60, 15, 3));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIGetFollowers(this, 1, 16));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.DORPA_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.DORPA_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.27);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.DORPA_KD_RESISTANCE);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.DORPA_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.infectedFollow);
    }

    public void func_70110_aj() {
    }

    public float func_70047_e() {
        return 1.75f;
    }

    @Override
    public boolean func_70652_k(@Nonnull Entity entityIn) {
        if (this.getSkin() == 1) {
            boolean flag = super.func_70652_k(entityIn);
            if (flag && entityIn instanceof EntityLivingBase) {
                ((EntityLivingBase)entityIn).func_70690_d(new PotionEffect(MobEffects.field_76436_u, 40));
            }
            return flag;
        }
        return super.func_70652_k(entityIn);
    }

    @Override
    protected void selfExplode() {
        super.selfExplode();
        ParasiteSummon.spawnM(this, new String[]{SRPConfigMobs.dorpamob}, 0, false, this.func_95999_t());
    }

    @Override
    protected void spawnGore() {
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void spawnEffectsGore() {
        for (int i = 0; i <= 60; ++i) {
            if (i % 5 == 0) {
                this.spawnParticles(SRPEnumParticle.GCLOUD, 0, 0, 127);
            }
            if (i % 5 != 0) continue;
            this.spawnParticles(SRPEnumParticle.GSPLASH, 1, -1, -1);
        }
    }

    protected SoundEvent func_184639_G() {
        if (this.getParasiteStatus() != 0) {
            return SRPSounds.MOBSILENCE;
        }
        return SRPSounds.INFECTEDSPIDER_GROWL;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SRPSounds.INFECTEDSPIDER_HURT;
    }

    protected SoundEvent func_184615_bR() {
        return SRPSounds.INFECTEDSPIDER_DEATH;
    }

    protected SoundEvent getStepSound() {
        return SRPSounds.INFECTEDSPIDER_STEP;
    }

    protected void func_180429_a(BlockPos pos, Block blockIn) {
        this.func_184185_a(this.getStepSound(), 0.15f, 1.0f);
    }

    @Override
    public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
        IEntityLivingData floo = super.func_180482_a(difficulty, livingdata);
        if (this.field_70170_p.field_73012_v.nextInt(3) == 0) {
            this.setSkin(1);
            this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.DORPA_HEALTH * 0.5);
            this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.DORPA_ATTACK_DAMAGE * 1.25);
            this.func_70606_j((float)this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b());
        }
        return floo;
    }

    public EntityFireball getProj(double accelX, double accelY, double accelZ) {
        this.func_184185_a(SRPSounds.DORPA_RANGE, 2.0f, 1.0f);
        EntityLivingBase entitylivingbase = this.func_70638_az();
        accelY = entitylivingbase.func_174813_aQ().field_72338_b + (double)(entitylivingbase.field_70131_O / 3.0f) - (1.0 + this.field_70163_u + (double)(this.field_70131_O / 2.0f));
        return new EntityProjectileWebball(this.field_70170_p, (EntityLivingBase)this, accelX, accelY, accelZ);
    }

    @Override
    public void playProjSound() {
    }
}

