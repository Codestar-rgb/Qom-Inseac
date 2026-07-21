package com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus;

import com.dhanantry.scapeandrunparasites.entity.ai.EntityAINexusGrow;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPRooter;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventWorld;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import java.util.Arrays;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityLeemSII extends EntityPRooter {
   public EntityLeemSII(World worldIn) {
      super(worldIn);
      this.func_70105_a(1.2F, 5.2F);
      this.buriedT = 2.6;
      this.field_70158_ak = true;
      this.field_70728_aV = SRPAttributes.XP_PRIMITIVE * 2;
      this.buried = 0.1;
      this.setParasiteStatus(3);
      this.damageCap = SRPConfig.nexussiCap;
      this.pointCap = SRPConfig.nexussiiPointCap;
      this.pointReduction = SRPConfig.nexussiiPointRed;
      this.chanceLearn = SRPConfig.nexussiiChanceLe;
      this.chanceLearnFire = SRPConfig.nexussiiChanceLeFire;
      this.DamageTypeCap = SRPConfig.nexussiiPointDamCap;
      this.stage = 2;
      this.totalP = SRPConfigMobs.leemsiilimit;
      this.mobID = new int[SRPConfigMobs.leemsiilimit];
      this.mobPT = new int[SRPConfigMobs.leemsiilimit];
      this.leemRange = SRPConfigMobs.leemsiiRange;
      this.leemRangeEffect = SRPConfigMobs.leemsiiRangeEffect;
      this.leemBalls = SRPConfigMobs.leemsiilimit;
      this.leemCooldownReset = SRPConfigMobs.leemsiiCooldown;
      Arrays.fill(this.mobID, -777);
      this.neededTime = this.setGT(SRPConfig.nexusMinGrowTime, SRPConfig.nexusMaxGrowTime);
      this.valueEvDeath = SRPConfig.nexussiiLoosingEPValue;
      this.rangeB = 2;
   }

   @Override
   public int getParasiteIDRegister() {
      return 311;
   }

   protected void func_184651_r() {
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(4, new EntityAINexusGrow(this, 2, (byte)3));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.VENKROLSII_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.VENKROLSII_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.VENKROLSII_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.nexussiiFollow);
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
   }

   public float func_70047_e() {
      return 1.4F;
   }

   protected SoundEvent func_184639_G() {
      return SRPSounds.MOBSILENCE;
   }

   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
      return this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0 ? SRPSounds.MOBSILENCE : SRPSounds.MOBSILENCE;
   }

   protected SoundEvent func_184615_bR() {
      return SRPSounds.MOBSILENCE;
   }

   @Override
   public float getBombDamage() {
      return (float)SRPAttributes.VENKROL_ATTACK_DAMAGE;
   }

   @Override
   public void func_70645_a(DamageSource cause) {
      if (!this.field_70170_p.field_72995_K) {
         if (!SRPConfigWorld.coloniesActivated && !this.canChangeVariant) {
            super.func_70645_a(cause);
         } else if (ParasiteEventWorld.numberofColonies(this.field_70170_p) < 1 && !this.canChangeVariant) {
            super.func_70645_a(cause);
         } else {
            ParasiteEventEntity.checkColony(this.field_70170_p, cause, this);
            ParasiteEventEntity.spawnNext(this, new EntityLeem(this.field_70170_p), true, false);
         }
      }
   }

   @Override
   protected boolean onDeathDislo(DamageSource cause) {
      ParasiteEventWorld.setDisloWorldPhase(this.field_70170_p, SRPAttributes.EVENTPARANEXUSIID, SRPConfigSystems.chanceEventParaNexusIID, 0, null);
      return super.onDeathDislo(cause);
   }
}
