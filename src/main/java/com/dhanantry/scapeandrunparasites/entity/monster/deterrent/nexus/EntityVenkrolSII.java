package com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus;

import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIBlockInfest;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAINexusGrow;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIVenkrolSummon;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPBeckon;
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

public class EntityVenkrolSII extends EntityPBeckon {
   public EntityAIVenkrolSummon summonV = new EntityAIVenkrolSummon(
      this, SRPConfigMobs.venkrolsiilimit, 20 * SRPConfigMobs.venkrolsiiCooldown, 2, SRPConfigMobs.venkrolsiiCAMinimumV, SRPConfigMobs.venkrolsiiCAExtraM
   );

   public EntityVenkrolSII(World worldIn) {
      super(worldIn);
      this.func_70105_a(0.6F, 2.8F);
      this.buriedT = 4.4;
      this.totalP = SRPConfigMobs.venkrolsiiTotalActiveMobs;
      this.mobID = new int[this.totalP + SRPConfigMobs.venkrolsiilimit];
      this.mobPT = new int[this.totalP + SRPConfigMobs.venkrolsiilimit];
      this.stage = 2;
      this.field_70728_aV = SRPAttributes.XP_PRIMITIVE * 2;
      if (SRPAttributes.rsBlockI) {
         this.field_70714_bg.func_75776_a(3, new EntityAIBlockInfest(this, 2));
      }

      Arrays.fill(this.mobID, -777);
      this.setBODY(1.0F);
      this.field_70714_bg.func_75776_a(2, this.summonV);
      this.damageCap = SRPConfig.nexussiiCap;
      this.pointCap = SRPConfig.nexussiiPointCap;
      this.pointReduction = SRPConfig.nexussiiPointRed;
      this.chanceLearn = SRPConfig.nexussiiChanceLe;
      this.chanceLearnFire = SRPConfig.nexussiiChanceLeFire;
      this.DamageTypeCap = SRPConfig.nexussiiPointDamCap;
      this.neededTime = this.setGT(SRPConfig.nexussiiMinGrowTime, SRPConfig.nexussiiMaxGrowTime);
      this.valueEvDeath = SRPConfig.nexussiiLoosingEPValue;
   }

   @Override
   public int getParasiteIDRegister() {
      return 18;
   }

   protected void func_184651_r() {
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(4, new EntityAINexusGrow(this, 2));
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
      if (this.field_70170_p.field_72995_K && this.topParticles > 0 && this.field_70173_aa % 5 == 0) {
         for (int i = 0; i <= 2; i++) {
            this.spawnParticlesTop(SRPEnumParticle.BIOMASS, 0, 0, 0);
         }
      }

      if (this.getParasiteStatus() == 0) {
         this.setBODY(0.04F);
      } else {
         this.setBODY(-0.04F);
      }
   }

   public float func_70047_e() {
      return 2.7F;
   }

   public void setBODY(float in) {
      in += this.getBODY();
      if (in > 0.5F) {
         in = 0.5F;
      }

      if (in < 0.0F) {
         in = 0.0F;
      }

      this.body = in;
   }

   protected SoundEvent func_184639_G() {
      return SRPSounds.VENKROLSII_GROWL;
   }

   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
      return this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0 ? SRPSounds.MOBSILENCE : SRPSounds.VENKROLSII_HURT;
   }

   protected SoundEvent func_184615_bR() {
      return SRPSounds.VENKROLSII_DEATH;
   }

   protected float func_70599_aP() {
      return 1.0F;
   }

   @Override
   public float getBombDamage() {
      return (float)SRPAttributes.VENKROLSII_ATTACK_DAMAGE;
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
            ParasiteEventEntity.spawnNext(this, new EntityVenkrol(this.field_70170_p), true, false);
         }
      }
   }

   @Override
   protected boolean onDeathDislo(DamageSource cause) {
      ParasiteEventWorld.setDisloWorldPhase(this.field_70170_p, SRPAttributes.EVENTPARANEXUSIID, SRPConfigSystems.chanceEventParaNexusIID, 0, null);
      return super.onDeathDislo(cause);
   }
}
