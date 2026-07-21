package com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus;

import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIDodAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAINexusGrow;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPDispatcher;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
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
import net.minecraft.world.World;

public class EntityDodSIII extends EntityPDispatcher {
   public EntityDodSIII(World worldIn) {
      super(worldIn);
      this.func_70105_a(3.9F, 4.8F);
      this.field_70158_ak = true;
      this.field_70728_aV = SRPAttributes.XP_ADAPTED * 2;
      this.buried = 0.1;
      this.setParasiteStatus(3);
      this.damageCap = SRPConfig.nexussiiiCap;
      this.pointCap = SRPConfig.nexussiiiPointCap;
      this.pointReduction = SRPConfig.nexussiiiPointRed;
      this.chanceLearn = SRPConfig.nexussiiiChanceLe;
      this.chanceLearnFire = SRPConfig.nexussiiiChanceLeFire;
      this.DamageTypeCap = SRPConfig.nexussiiiPointDamCap;
      this.totalP = SRPConfigMobs.dodsiiiTotalActiveMobs;
      this.mobID = new int[5];
      this.mobPT = new int[5];
      this.stage = 3;
      Arrays.fill(this.mobID, -777);
      this.neededTime = this.setGT(SRPConfig.nexussiiiMinGrowTime, SRPConfig.nexussiiiMaxGrowTime);
      this.valueEvDeath = SRPConfig.nexussiiiLoosingEPValue;
   }

   @Override
   public int getParasiteIDRegister() {
      return 78;
   }

   protected void func_184651_r() {
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(4, new EntityAINexusGrow(this, 3, (byte)2));
      this.field_70714_bg.func_75776_a(2, new EntityAIDodAttack(this, 3, (byte)42, 20.0F));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.DODSIII_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.DODSIII_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.DODSIII_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.nexussiiiFollow * SRPConfigMobs.dodsiiiFollowRangeMult);
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
   }

   public float func_70047_e() {
      return 4.6F;
   }

   @Override
   public float getBombDamage() {
      return (float)SRPAttributes.DODSIII_ATTACK_DAMAGE;
   }

   @Override
   public boolean storeParasite(EntityParasiteBase in) {
      if (super.storeParasite(in)) {
         return true;
      } else if (this.storeLodo(in, true)) {
         return true;
      } else if (this.storeInf(in, true)) {
         return true;
      } else if (this.storeCrude(in, true)) {
         return true;
      } else if (this.storeMudo(in, true)) {
         return true;
      } else if (this.storeMangler(in, true)) {
         return true;
      } else {
         this.storeAll(in);
         return false;
      }
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
            ParasiteEventEntity.spawnNext(this, new EntityDodSII(this.field_70170_p), true, false);
         }
      }
   }

   @Override
   protected boolean onDeathDislo(DamageSource cause) {
      ParasiteEventWorld.setDisloWorldPhase(this.field_70170_p, SRPAttributes.EVENTPARANEXUSIIID, SRPConfigSystems.chanceEventParaNexusIIID, 0, null);
      return super.onDeathDislo(cause);
   }
}
