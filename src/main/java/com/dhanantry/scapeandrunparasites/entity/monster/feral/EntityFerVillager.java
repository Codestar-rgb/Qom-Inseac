package com.dhanantry.scapeandrunparasites.entity.monster.feral;

import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIGetFollowers;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISwimmingDiving;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIWaterLeapAtTargetStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPFeral;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import javax.annotation.Nonnull;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityFerVillager extends EntityPFeral {
   public EntityFerVillager(World worldIn) {
      super(worldIn);
      this.func_70105_a(0.6F, 1.95F);
      this.canModRender = 1;
      this.type = 11;
   }

   @Override
   public int getIDSpawn() {
      return 27;
   }

   @Override
   public int getParasiteIDRegister() {
      return 99;
   }

   @Override
   public int canSpawnByIDData() {
      return SRPConfigMobs.infvillagerCanSpawnAssimilatedNat;
   }

   protected void func_184651_r() {
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(0, new EntityAISwimmingDiving(this, 0.08));
      this.field_70714_bg.func_75776_a(2, new EntityAIWaterLeapAtTargetStatus(this, 0.7F, 1.5, 3, 20, 0));
      this.field_70714_bg.func_75776_a(3, new EntityAIAttackMeleeStatus(this, 1.5, false, 0.0));
      this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
      this.field_70714_bg.func_75776_a(6, new EntityAIGetFollowers(this, 1, 16));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.FERVILLAGER_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.FERVILLAGER_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.260000004172325);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.FERVILLAGER_KD_RESISTANCE);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.FERVILLAGER_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.feralFollow);
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
   }

   @Override
   public boolean func_70652_k(@Nonnull Entity entityIn) {
      boolean flag = super.func_70652_k(entityIn);
      if (flag) {
      }

      return flag;
   }

   public float func_70047_e() {
      return 1.73F;
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

   protected float func_70647_i() {
      return (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 0.5F;
   }

   protected SoundEvent getStepSound() {
      return SoundEvents.field_187939_hm;
   }

   protected void func_180429_a(BlockPos pos, Block blockIn) {
      this.func_184185_a(this.getStepSound(), 0.15F, 1.0F);
   }

   @Override
   public void func_70014_b(NBTTagCompound compound) {
      super.func_70014_b(compound);
   }

   @Override
   public void func_70037_a(NBTTagCompound compound) {
      super.func_70037_a(compound);
   }

   @Override
   public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
      return super.func_180482_a(difficulty, livingdata);
   }
}
