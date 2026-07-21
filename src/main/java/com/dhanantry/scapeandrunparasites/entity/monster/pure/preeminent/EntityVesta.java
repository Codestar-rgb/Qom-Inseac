package com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent;

import com.dhanantry.scapeandrunparasites.entity.EntityBody;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIGetFollowers;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIGiveEffectsArea;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISwimmingDiving;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIWaterLeapAtTargetStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityBodyParts;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPPreeminent;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityVesta extends EntityPPreeminent implements EntityBodyParts {
   private EntityBody head;

   public EntityVesta(World worldIn) {
      super(worldIn);
      this.func_70105_a(1.75F, 3.6F);
      this.field_70138_W = 1.0F;
      this.borderOrb = -1;
      this.canTeleportToo = true;
      this.field_70158_ak = true;
      this.type = 31;
      this.attackSpeedT = 10;
      this.head = new EntityBody(this, 3.8F, 3.8F, 1.0F, 3.1F, 1.6F, 1, 1, false, 0.2F);
   }

   @Override
   public int getParasiteIDRegister() {
      return 88;
   }

   protected void func_184651_r() {
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(0, new EntityAISwimmingDiving(this, 0.15));
      this.field_70714_bg.func_75776_a(2, new EntityAIWaterLeapAtTargetStatus(this, 0.7F, 1.5, 3, 20, 0));
      this.field_70714_bg.func_75776_a(3, new EntityAIAttackMeleeStatus(this, 1.3, false, 8.0));
      this.field_70714_bg.func_75776_a(6, new EntityAIGetFollowers(this, 2, 16));
      this.field_70714_bg.func_75776_a(3, new EntityAIGiveEffectsArea(this, SRPAttributes.VESTA_CD, SRPAttributes.VESTA_RANGE, SRPConfigMobs.vestaeffects));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.VESTA_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.VESTA_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.242);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(2.0);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.VESTA_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.preeminentFollow);
   }

   @Override
   protected void func_70088_a() {
      super.func_70088_a();
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      this.head.func_70071_h_();
      if (this.srpTicks == 25) {
         AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.func_180425_c()).func_186662_g(32.0);

         for (EntityParasiteBase mob : this.field_70170_p.func_72872_a(EntityParasiteBase.class, axisalignedbb)) {
            SRPPotions.applyStackPotion(SRPPotions.LINK_E, mob, 6666, 0);
            SRPPotions.applyStackPotion(SRPPotions.FOSTER_E, mob, 6666, 0);
         }
      }
   }

   @Override
   public boolean attackEntityBodyFrom(DamageSource source, float amount, int id, boolean notify) {
      if (this.field_70146_Z.nextBoolean()) {
         SRPPotions.applyStackPotion(SRPPotions.BLEED_E, this, 80, 0);
      }

      return this.func_70097_a(source, amount * 3.0F);
   }

   @Override
   public void setBodyPartDead(int id) {
   }

   @Override
   public void func_70106_y() {
      if (this.head != null) {
         this.field_70170_p.func_72973_f(this.head);
      }

      super.func_70106_y();
   }

   @Override
   public boolean scaryOrbEffect(EntityLivingBase in, int mobs) {
      boolean flag = super.scaryOrbEffect(in, mobs);
      if (flag) {
         ParasiteEventEntity.orbApplyEffects(in, this, SRPConfigMobs.vestaOrbEffects, mobs);
      }

      return flag;
   }

   public float func_70047_e() {
      return 1.5F;
   }

   protected SoundEvent func_184639_G() {
      return this.getParasiteStatus() != 0 ? SRPSounds.MOBSILENCE : SRPSounds.VESTA_GROWL;
   }

   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
      return this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0 ? SRPSounds.MOBSILENCE : SRPSounds.VESTA_HURT;
   }

   protected void func_180429_a(BlockPos pos, Block blockIn) {
      this.func_184185_a(SRPSounds.HEAVY_STEPS_MULTIPLE, 0.15F, 1.0F);
   }

   protected SoundEvent func_184615_bR() {
      return SRPSounds.VESTA_DEATH;
   }

   @Override
   public void func_70074_a(EntityLivingBase entityLivingIn) {
      super.func_70074_a(entityLivingIn);
      this.particleStatus((byte)5);
   }

   protected float func_70599_aP() {
      return 5.0F;
   }

   @Override
   public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
      IEntityLivingData floo = super.func_180482_a(difficulty, livingdata);
      if (this.field_70146_Z.nextDouble() < SRPConfig.variantChance || this.phaseCreated >= SRPConfigSystems.evolutionParasiteAlwaysVariant) {
         switch (2) {
            case 2:
               this.setSkin(1);
               this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.VESTA_ARMOR * 1.5);
               this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.1694);
         }
      }

      return floo;
   }
}
