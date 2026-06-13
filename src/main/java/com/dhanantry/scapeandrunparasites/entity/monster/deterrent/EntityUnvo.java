/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIAttackMelee
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.projectile.EntityFireball
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.entity.monster.deterrent;

import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackProjectile;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanShoot;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPStationary;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityEsor;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileSpineball;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityUnvo
extends EntityPStationary
implements EntityCanShoot {
    public EntityUnvo(World worldIn) {
        super(worldIn);
        this.func_70105_a(0.7f, 4.1f);
        this.field_70728_aV = SRPAttributes.XP_ADAPTED * 2;
        this.type = (byte)40;
        this.buriedT = 5.1;
        this.damageCap = SRPConfig.turretCap;
        this.pointCap = SRPConfig.turretPointCap;
        this.pointReduction = SRPConfig.turretPointRed;
        this.chanceLearn = SRPConfig.turretChanceLe;
        this.chanceLearnFire = SRPConfig.turretChanceLeFire;
        this.DamageTypeCap = SRPConfig.turretPointDamCap;
        this.MiniDamage = SRPConfig.turretMinDamage;
        this.regen = SRPConfig.turretRegen;
    }

    @Override
    public int getParasiteIDRegister() {
        return 30;
    }

    protected void func_184651_r() {
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIAttackProjectile(this, 20, 1, 3));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0, false));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.UNVO_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.UNVO_ARMOR);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.UNVO_ATTACK_DAMAGE);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.turretFollow);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
    }

    public double func_70033_W() {
        if (this.func_184187_bx() != null && this.func_184187_bx() instanceof EntityEsor) {
            return 1.0;
        }
        return super.func_70033_W();
    }

    public float func_70047_e() {
        return 3.6f;
    }

    protected SoundEvent func_184639_G() {
        return SRPSounds.UNVO_GROWL;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        if (this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0) {
            return SRPSounds.MOBSILENCE;
        }
        return SRPSounds.UNVO_HURT;
    }

    protected SoundEvent func_184615_bR() {
        return SRPSounds.UNVO_DEATH;
    }

    public EntityFireball getProj(double accelX, double accelY, double accelZ) {
        this.func_184185_a(SRPSounds.EMANA_SHOOTING, 2.0f, 1.0f);
        EntityLivingBase entitylivingbase = this.func_70638_az();
        accelY = entitylivingbase.func_174813_aQ().field_72338_b + (double)(entitylivingbase.field_70131_O / 4.0f) - (1.0 + this.field_70163_u + (double)(this.field_70131_O / 2.0f));
        EntityProjectileSpineball ball = new EntityProjectileSpineball(this.field_70170_p, (EntityLivingBase)this, accelX, accelY, accelZ, SRPAttributes.UNVO_RANGE_ATTACK_DAMAGE);
        ball.setDurationAmplifier(SRPConfigMobs.unvoPoisonDuration, SRPConfigMobs.unvoPoisonAmplifier);
        ball.setGearDamage(SRPConfigMobs.unvoGearD);
        return ball;
    }

    @Override
    public void playProjSound() {
        this.func_184185_a(SRPSounds.UNVO_SHOOTING, 2.0f, 1.0f);
    }
}

