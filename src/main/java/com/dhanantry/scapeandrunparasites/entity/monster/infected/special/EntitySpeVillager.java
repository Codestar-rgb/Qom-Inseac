package com.dhanantry.scapeandrunparasites.entity.monster.infected.special;

import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeRangeSwitch;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackRangedStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIGetFollowers;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISwimmingDiving;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPAssimara;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileSpineball;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import javax.annotation.Nonnull;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntitySpeVillager extends EntityPAssimara implements IRangedAttackMob {
   private int host;

   public EntitySpeVillager(World worldIn) {
      super(worldIn);
      this.func_70105_a(0.6F, 2.75F);
      this.canModRender = 1;
      this.type = 11;
   }

   @Override
   public int getIDSpawn() {
      return 27;
   }

   @Override
   public int getParasiteIDRegister() {
      return 323;
   }

   @Override
   public int canSpawnByIDData() {
      return SRPConfigMobs.infvillagerCanSpawnAssimilatedNat;
   }

   protected void func_184651_r() {
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(0, new EntityAISwimmingDiving(this, 0.08));
      this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
      this.field_70714_bg.func_75776_a(6, new EntityAIGetFollowers(this, 1, 16));
      this.field_70714_bg.func_75776_a(6, new EntityAIAttackMeleeRangeSwitch(this, 0.0F));
      this.field_70714_bg.func_75776_a(4, new EntityAIAttackRangedStatus(this, 1.2, 40, (float)SRPConfig.assimaraFollow / 2.0F, true));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.MARVILLAGER_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.MARVILLAGER_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.225);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.MARVILLAGER_KD_RESISTANCE);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.MARVILLAGER_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.assimaraFollow);
   }

   @Override
   protected void func_70088_a() {
      super.func_70088_a();
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
   }

   @Override
   public boolean func_70652_k(@Nonnull Entity entityIn) {
      boolean flag = super.func_70652_k(entityIn);
      if (flag && this.field_70146_Z.nextDouble() < SRPConfig.infectedBleedingChance && entityIn instanceof EntityLivingBase) {
         SRPPotions.applyStackPotion(SRPPotions.BLEED_E, (EntityLivingBase)entityIn, 100, 0);
      }

      return flag;
   }

   public float func_70047_e() {
      return 1.973F;
   }

   protected SoundEvent func_184639_G() {
      return this.getParasiteStatus() != 0 ? SRPSounds.MOBSILENCE : SRPSounds.INFECTEDHUMAN_GROWL;
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
      this.func_184185_a(this.getStepSound(), 0.15F, 1.0F);
   }

   @Override
   public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
      IEntityLivingData floo = super.func_180482_a(difficulty, livingdata);
      if (this.field_70146_Z.nextDouble() < SRPConfig.variantChance || this.phaseCreated >= SRPConfigSystems.evolutionParasiteAlwaysVariant) {
         this.setSkin(1);
      }

      return floo;
   }

   public void func_82196_d(EntityLivingBase target, float distanceFactor) {
      Vec3d vec3d = this.func_70676_i(1.0F);
      double d2 = target.field_70165_t - (this.field_70165_t + vec3d.field_72450_a);
      double d3 = target.func_174813_aQ().field_72338_b + target.field_70131_O / 2.0F - (0.5 + this.field_70163_u + this.field_70131_O / 2.0F);
      double d4 = target.field_70161_v - (this.field_70161_v + vec3d.field_72449_c);
      this.func_184185_a(SRPSounds.EMANA_SHOOTING, 2.0F, 1.0F);
      d3 = target.func_174813_aQ().field_72338_b + target.field_70131_O / 4.0F - (0.0 + this.field_70163_u + this.field_70131_O / 2.0F);
      EntityProjectileSpineball entitylargefireball = new EntityProjectileSpineball(this.field_70170_p, this, d2, d3, d4, 3.0F);
      entitylargefireball.field_70165_t = this.field_70165_t + vec3d.field_72450_a;
      entitylargefireball.field_70163_u = this.field_70163_u + this.func_70047_e() - 0.2;
      entitylargefireball.field_70161_v = this.field_70161_v + vec3d.field_72449_c;
      this.field_70170_p.func_72838_d(entitylargefireball);
   }

   public void func_184724_a(boolean swingingArms) {
   }
}
