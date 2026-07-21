package com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus;

import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIBlockInfest;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAINexusGrow;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIVenkrolSummon;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPBeckon;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventWorld;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import java.util.Arrays;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityVenkrol extends EntityPBeckon {
   public EntityAIVenkrolSummon summonV = new EntityAIVenkrolSummon(
      this, SRPConfigMobs.venkrollimit, 20 * SRPConfigMobs.venkrolCooldown, 1, SRPConfigMobs.venkrolCAMinimumV, SRPConfigMobs.venkrolCAExtraM
   );

   public EntityVenkrol(World worldIn) {
      super(worldIn);
      this.func_70105_a(0.5F, 1.5F);
      this.buriedT = 2.6;
      this.totalP = SRPConfigMobs.venkrolTotalActiveMobs;
      this.mobID = new int[this.totalP + SRPConfigMobs.venkrollimit];
      this.mobPT = new int[this.totalP + SRPConfigMobs.venkrollimit];
      this.stage = 1;
      this.field_70728_aV = SRPAttributes.XP_INFECTED * 2;
      if (SRPAttributes.rsBlockI) {
         this.field_70714_bg.func_75776_a(3, new EntityAIBlockInfest(this, 1));
      }

      Arrays.fill(this.mobID, -777);
      this.setBODY(1.0F);
      this.field_70714_bg.func_75776_a(2, this.summonV);
      this.damageCap = SRPConfig.nexussiCap;
      this.pointCap = SRPConfig.nexussiPointCap;
      this.pointReduction = SRPConfig.nexussiPointRed;
      this.chanceLearn = SRPConfig.nexussiChanceLe;
      this.chanceLearnFire = SRPConfig.nexussiChanceLeFire;
      this.DamageTypeCap = SRPConfig.nexussiPointDamCap;
      this.neededTime = this.setGT(SRPConfig.nexusMinGrowTime, SRPConfig.nexusMaxGrowTime);
      this.valueEvDeath = SRPConfig.nexussiLoosingEPValue;
   }

   @Override
   public int getParasiteIDRegister() {
      return 16;
   }

   protected void func_184651_r() {
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(4, new EntityAINexusGrow(this, 1));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.VENKROL_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.VENKROL_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.VENKROL_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.nexussiFollow);
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      if (this.field_70170_p.field_72995_K && this.topParticles > 0 && this.field_70173_aa % 10 == 0) {
         for (int i = 0; i <= 1; i++) {
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
      return 1.4F;
   }

   public void setBODY(float in) {
      in += this.getBODY();
      if (in > 0.6F) {
         in = 0.6F;
      }

      if (in < 0.0F) {
         in = 0.0F;
      }

      this.body = in;
   }

   @Override
   public boolean func_70601_bi() {
      IBlockState iblockstate = this.field_70170_p.func_180495_p(new BlockPos(this).func_177977_b());
      return this.func_180484_a(new BlockPos(this.field_70165_t, this.func_174813_aQ().field_72338_b, this.field_70161_v)) >= 0.0F
         && iblockstate.func_189884_a(this)
         && this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL;
   }

   protected SoundEvent func_184639_G() {
      return SRPSounds.VENKROLSI_GROWL;
   }

   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
      return this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0 ? SRPSounds.MOBSILENCE : SRPSounds.VENKROLSI_HURT;
   }

   protected SoundEvent func_184615_bR() {
      return SRPSounds.VENKROLSI_DEATH;
   }

   @Override
   public float getBombDamage() {
      return (float)SRPAttributes.VENKROL_ATTACK_DAMAGE;
   }

   @Override
   protected boolean onDeathDislo(DamageSource cause) {
      ParasiteEventWorld.setDisloWorldPhase(this.field_70170_p, SRPAttributes.EVENTPARANEXUSID, SRPConfigSystems.chanceEventParaNexusID, 0, null);
      return super.onDeathDislo(cause);
   }
}
