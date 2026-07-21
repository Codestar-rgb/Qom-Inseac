package com.dhanantry.scapeandrunparasites.entity.monster.inborn;

import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIEvadeDash;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAINearestAttackableTargetStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISkill;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISwimmingDiving;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPMalleable;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventWorld;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import com.google.common.base.Predicate;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateClimber;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityNuuh extends EntityPMalleable {
   private static final DataParameter<Byte> CLIMBING = EntityDataManager.func_187226_a(EntityNuuh.class, DataSerializers.field_187191_a);

   public EntityNuuh(World worldIn) {
      super(worldIn);
      this.func_70105_a(1.0F, 1.0F);
      this.borderOrb = -1;
      this.field_70715_bh
         .func_75776_a(
            4, new EntityAINearestAttackableTargetStatus(this, EntityPlayer.class, 0, true, false, null, SRPConfig.pureSneakPen, SRPConfig.pureInviPen)
         );
      if (SRPConfig.mobattacking) {
         this.field_70715_bh
            .func_75776_a(
               4,
               new EntityAINearestAttackableTargetStatus(
                  this,
                  EntityLiving.class,
                  0,
                  true,
                  false,
                  new Predicate<EntityLiving>() {
                     public boolean apply(@Nullable EntityLiving entity) {
                        return !(entity instanceof EntityWaterMob)
                           && !(entity instanceof EntityAnimal)
                           && !(entity instanceof EntityVillager)
                           && !ParasiteEventEntity.checkEntity(entity, SRPConfig.mobattackingBlackList, SRPConfig.mobattackingBlackListWhite);
                     }
                  },
                  SRPConfig.pureSneakPen,
                  SRPConfig.pureInviPen
               )
            );
      }

      this.field_70728_aV = SRPAttributes.XP_PURE;
      this.type = 51;
      this.foodSteal = SRPConfig.pureFoodSteal;
      this.pointCap = SRPConfig.purePointCap;
      this.pointReduction = SRPConfig.purePointRed;
      this.chanceLearn = SRPConfig.pureChanceLe;
      this.chanceLearnFire = SRPConfig.pureChanceLeFire;
      this.DamageTypeCap = SRPConfig.purePointDamCap;
      this.MiniDamage = SRPConfigMobs.nuuhMinDamage;
      this.regen = SRPConfig.pureRegen * SRPConfig.globalHealthMultiplier;
      this.oneMindDeathValue = SRPConfig.pureOneMindDeathV;
      this.regenEff = 3;
      this.adaptationCap = 0.95F;
      this.attackSpeedT = 6;
   }

   protected void func_184651_r() {
      this.field_70714_bg.func_75776_a(0, new EntityAISkill(this, 20, 100, 5, true, 14));
      this.setskillLeapValues(0.8F, 2.0, 0);
      this.field_70714_bg.func_75776_a(0, new EntityAISwimmingDiving(this, 0.12));
      this.field_70714_bg.func_75776_a(3, new EntityAIAttackMeleeStatus(this, 1.3, false, -1.0));
      this.field_70714_bg.func_75776_a(3, new EntityAILeapAtTarget(this, 0.4F));
      this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
      this.field_70714_bg.func_75776_a(2, new EntityAIEvadeDash(this, 10, 2, 1, 1.0, 15));
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false, new Class[0]));
   }

   @Override
   public int getParasiteIDRegister() {
      return 76;
   }

   @Override
   public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
      EntityLivingBase src = source.func_76346_g() instanceof EntityLivingBase ? (EntityLivingBase)source.func_76346_g() : null;
      boolean took = super.func_70097_a(source, amount);
      if (this.field_70170_p.field_72995_K) {
         return took;
      } else {
         if (src instanceof EntityPlayer && this.func_70089_S()) {
            EntityPlayer player = (EntityPlayer)src;
            this.func_70604_c(player);
            this.func_70624_b(player);
         }

         return took;
      }
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.NUUH_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.NUUH_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.37);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.NUUH_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.NUUH_KD_RESISTANCE);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(32.0);
   }

   @Override
   public boolean func_70652_k(@Nonnull Entity target) {
      return super.func_70652_k(target);
   }

   public void func_180430_e(float distance, float damageMultiplier) {
      if (distance >= 200.0F) {
         super.func_180430_e(distance, damageMultiplier);
      }
   }

   public void func_70110_aj() {
   }

   protected PathNavigate func_175447_b(World worldIn) {
      return new PathNavigateClimber(this, worldIn);
   }

   public void func_70071_h_() {
      super.func_70071_h_();
      if (!this.field_70170_p.field_72995_K) {
         this.setBesideClimbableBlock(this.field_70123_F);
      }
   }

   @Override
   protected void func_82167_n(Entity entityIn) {
      super.func_82167_n(entityIn);
      if (!this.field_70170_p.field_72995_K) {
         if (entityIn instanceof EntityLivingBase && !(entityIn instanceof EntityParasiteBase) && this.getSkin() == 5) {
            SRPPotions.applyStackPotion(SRPPotions.VIRA_E, (EntityLivingBase)entityIn, 100, 0);
         }
      }
   }

   @Override
   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_187214_a(CLIMBING, (byte)0);
   }

   public boolean func_70617_f_() {
      return this.isBesideClimbableBlock();
   }

   public boolean isBesideClimbableBlock() {
      return ((Byte)this.field_70180_af.func_187225_a(CLIMBING) & 1) != 0;
   }

   public void setBesideClimbableBlock(boolean climbing) {
      byte b0 = (Byte)this.field_70180_af.func_187225_a(CLIMBING);
      if (this.func_70638_az() != null) {
         if (!this.func_70685_l(this.func_70638_az())) {
            if (this.func_70068_e(this.func_70638_az()) < 100.0) {
               b0 = (byte)(b0 & -2);
               this.field_70180_af.func_187227_b(CLIMBING, b0);
               return;
            }
         } else if (this.func_70638_az().field_70163_u + 1.0 < this.field_70163_u) {
            b0 = (byte)(b0 & -2);
            this.field_70180_af.func_187227_b(CLIMBING, b0);
            return;
         }
      }

      if (climbing) {
         b0 = (byte)(b0 | 1);
      } else {
         b0 = (byte)(b0 & -2);
      }

      this.field_70180_af.func_187227_b(CLIMBING, b0);
   }

   public float func_70047_e() {
      return 0.9F;
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
            ParasiteEventEntity.spawnNext(this, new EntityMudo(this.field_70170_p), true, false);
         }
      }
   }

   @Override
   public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
      IEntityLivingData floo = super.func_180482_a(difficulty, livingdata);
      if (this.field_70146_Z.nextDouble() < SRPConfig.variantChance
         || this.phaseCreated >= SRPConfigSystems.evolutionParasiteAlwaysVariant
         || this.canChangeVariant) {
         switch (this.field_70146_Z.nextInt(2)) {
            case 0:
               this.setSkin(5);
               break;
            case 1:
               this.setSkin(6);
         }
      }

      return floo;
   }

   protected SoundEvent func_184639_G() {
      return this.getParasiteStatus() != 0 ? SRPSounds.MOBSILENCE : SRPSounds.NUUH_GROWL;
   }

   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
      return SRPSounds.NUUH_HURT;
   }

   protected SoundEvent func_184615_bR() {
      return SRPSounds.NUUH_DEATH;
   }

   protected void func_180429_a(BlockPos pos, Block blockIn) {
      this.func_184185_a(this.getStepSound(), this.func_70599_aP(), this.func_70647_i());
   }

   protected SoundEvent getStepSound() {
      return SRPSounds.SMALL_STEPS;
   }
}
