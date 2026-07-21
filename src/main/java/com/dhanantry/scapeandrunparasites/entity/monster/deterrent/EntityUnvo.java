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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityUnvo extends EntityPStationary implements EntityCanShoot {
   public EntityUnvo(World worldIn) {
      super(worldIn);
      this.func_70105_a(0.7F, 4.1F);
      this.field_70728_aV = SRPAttributes.XP_ADAPTED * 2;
      this.type = 40;
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
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(2, new EntityAIAttackProjectile(this, 20, 1, 3));
      this.field_70714_bg.func_75776_a(3, new EntityAIAttackMelee(this, 1.0, false));
      this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
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
      return this.func_184187_bx() != null && this.func_184187_bx() instanceof EntityEsor ? 1.0 : super.func_70033_W();
   }

   public float func_70047_e() {
      return 3.6F;
   }

   protected SoundEvent func_184639_G() {
      return SRPSounds.UNVO_GROWL;
   }

   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
      return this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0 ? SRPSounds.MOBSILENCE : SRPSounds.UNVO_HURT;
   }

   protected SoundEvent func_184615_bR() {
      return SRPSounds.UNVO_DEATH;
   }

   public EntityFireball getProj(double accelX, double accelY, double accelZ) {
      this.func_184185_a(SRPSounds.EMANA_SHOOTING, 2.0F, 1.0F);
      EntityLivingBase entitylivingbase = this.func_70638_az();
      accelY = entitylivingbase.func_174813_aQ().field_72338_b + entitylivingbase.field_70131_O / 4.0F - (1.0 + this.field_70163_u + this.field_70131_O / 2.0F);
      EntityProjectileSpineball ball = new EntityProjectileSpineball(this.field_70170_p, this, accelX, accelY, accelZ, SRPAttributes.UNVO_RANGE_ATTACK_DAMAGE);
      ball.setDurationAmplifier(SRPConfigMobs.unvoPoisonDuration, SRPConfigMobs.unvoPoisonAmplifier);
      ball.setGearDamage(SRPConfigMobs.unvoGearD);
      return ball;
   }

   @Override
   public void playProjSound() {
      this.func_184185_a(SRPSounds.UNVO_SHOOTING, 2.0F, 1.0F);
   }
}
