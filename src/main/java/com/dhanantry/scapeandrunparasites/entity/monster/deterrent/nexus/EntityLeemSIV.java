package com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus;

import com.dhanantry.scapeandrunparasites.entity.EntityBody;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityBodyParts;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPRooter;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
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

public class EntityLeemSIV extends EntityPRooter implements EntityBodyParts {
   private EntityBody head;

   public EntityLeemSIV(World worldIn) {
      super(worldIn);
      this.func_70105_a(1.7F, 5.4F);
      this.buriedT = 2.6;
      this.field_70158_ak = true;
      this.field_70728_aV = SRPAttributes.XP_ADAPTED * 4;
      this.buried = 0.1;
      this.setParasiteStatus(3);
      this.damageCap = SRPConfig.nexussiCap;
      this.pointCap = SRPConfig.nexussivPointCap;
      this.pointReduction = SRPConfig.nexussivPointRed;
      this.chanceLearn = SRPConfig.nexussivChanceLe;
      this.chanceLearnFire = SRPConfig.nexussivChanceLeFire;
      this.DamageTypeCap = SRPConfig.nexussivPointDamCap;
      this.stage = 4;
      this.totalP = SRPConfigMobs.leemsivlimit;
      this.mobID = new int[SRPConfigMobs.leemsivlimit];
      this.mobPT = new int[SRPConfigMobs.leemsivlimit];
      this.leemRange = SRPConfigMobs.leemsivRange;
      this.leemRangeEffect = SRPConfigMobs.leemsivRangeEffect;
      this.leemBalls = SRPConfigMobs.leemsivlimit;
      this.leemCooldownReset = SRPConfigMobs.leemsivCooldown;
      Arrays.fill(this.mobID, -777);
      this.neededTime = this.setGT(SRPConfig.nexusMinGrowTime, SRPConfig.nexusMaxGrowTime);
      this.head = new EntityBody(this, 5.2F, 5.3F, 1.0F, 0.0F, 5.45F, -1, 1, false, 0.2F);
      this.valueEvDeath = SRPConfig.nexussivLoosingEPValue;
      this.rangeB = 5;
   }

   @Override
   public int getParasiteIDRegister() {
      return 313;
   }

   protected void func_184651_r() {
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.VENKROLSIV_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.VENKROLSIV_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.VENKROLSIV_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.nexussivFollow);
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      this.head.func_70071_h_();
   }

   @Override
   public boolean attackEntityBodyFrom(DamageSource source, float amount, int id, boolean notify) {
      if (this.field_70146_Z.nextBoolean()) {
         SRPPotions.applyStackPotion(SRPPotions.BLEED_E, this, 80, 0);
      }

      return this.func_70097_a(source, amount * 3.0F);
   }

   @Override
   public void func_70106_y() {
      if (this.head != null) {
         this.field_70170_p.func_72973_f(this.head);
      }

      super.func_70106_y();
   }

   @Override
   public void setBodyPartDead(int id) {
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
            ParasiteEventEntity.spawnNext(this, new EntityLeemSIII(this.field_70170_p), true, false);
         }
      }
   }

   @Override
   protected boolean onDeathDislo(DamageSource cause) {
      ParasiteEventWorld.setDisloWorldPhase(this.field_70170_p, SRPAttributes.EVENTPARANEXUSIVD, SRPConfigSystems.chanceEventParaNexusIVD, 0, null);
      return super.onDeathDislo(cause);
   }
}
