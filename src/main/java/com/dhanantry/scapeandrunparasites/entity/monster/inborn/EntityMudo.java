package com.dhanantry.scapeandrunparasites.entity.monster.inborn;

import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAvoidEntityStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAvoidOrAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAINearestAttackableTargetStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISkill;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventWorld;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import com.google.common.base.Predicate;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateClimber;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityMudo extends EntityParasiteBase {
   private static final DataParameter<Byte> CLIMBING = EntityDataManager.func_187226_a(EntityMudo.class, DataSerializers.field_187191_a);

   public EntityMudo(World worldIn) {
      super(worldIn);
      this.func_70105_a(0.85F, 1.0F);
      this.type = 5;
      this.MiniDamage = SRPConfigMobs.mudoMinDamage;
      this.field_70728_aV = SRPAttributes.XP_LiTTLE;
      this.attackSpeedT = 10;
   }

   @Override
   public int getParasiteIDRegister() {
      return 12;
   }

   @Override
   public void applyBonuses(SRPSaveData sabe, World world) {
      super.applyBonuses(sabe, world);
      this.field_70715_bh
         .func_75776_a(
            4,
            new EntityAINearestAttackableTargetStatus(this, EntityPlayer.class, 0, true, false, null, SRPConfig.primitiveSneakPen, SRPConfig.primitiveInviPen)
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
                  SRPConfig.primitiveSneakPen,
                  SRPConfig.primitiveInviPen
               )
            );
      }

      if (SRPConfigSystems.useEvolution) {
         byte phase = sabe.getEvolutionPhase(world.field_73011_w.getDimension());
         if (phase >= SRPConfigSystems.evolutionMudoAttack) {
            if (phase >= SRPConfigSystems.evolutionAssimilatedDehiding) {
               this.field_70715_bh
                  .func_75776_a(
                     4,
                     new EntityAINearestAttackableTargetStatus(
                        this,
                        EntityLiving.class,
                        10,
                        true,
                        false,
                        new Predicate<EntityLiving>() {
                           public boolean apply(@Nullable EntityLiving entity) {
                              return !(entity instanceof EntityMob)
                                 && !(entity instanceof EntityWaterMob)
                                 && !ParasiteEventEntity.checkEntity(entity, SRPConfig.mobattackingBlackList, SRPConfig.mobattackingBlackListWhite);
                           }
                        },
                        SRPConfig.primitiveSneakPen,
                        SRPConfig.primitiveInviPen
                     )
                  );
            } else {
               this.field_70715_bh
                  .func_75776_a(
                     4,
                     new EntityAINearestAttackableTargetStatus(
                        this,
                        EntityLiving.class,
                        10,
                        true,
                        false,
                        new Predicate<EntityLiving>() {
                           public boolean apply(@Nullable EntityLiving entity) {
                              return !entity.func_70644_a(SRPPotions.COTH_E)
                                 && !(entity instanceof EntityMob)
                                 && !(entity instanceof EntityWaterMob)
                                 && !ParasiteEventEntity.checkEntity(entity, SRPConfig.mobattackingBlackList, SRPConfig.mobattackingBlackListWhite);
                           }
                        },
                        SRPConfig.primitiveSneakPen,
                        SRPConfig.primitiveInviPen
                     )
                  );
            }
         } else {
            this.field_70714_bg.func_75776_a(2, new EntityMudo.EntityAIMudoInfest(this, 1.0));
            this.field_70714_bg.func_75776_a(4, new EntityAIAvoidOrAttack(this, 0.0F, 10, 2));
            this.field_70714_bg
               .func_75776_a(
                  5,
                  new EntityAIAvoidEntityStatus(
                     this,
                     EntityLiving.class,
                     new Predicate<EntityLiving>() {
                        public boolean apply(@Nullable EntityLiving entity) {
                           return !(entity instanceof EntityWaterMob)
                              && !(entity instanceof EntityParasiteBase)
                              && !(entity instanceof EntityAnimal)
                              && !(entity instanceof EntityVillager);
                        }
                     },
                     8.0F,
                     1.3
                  )
               );
         }
      }
   }

   @Override
   public void applyGene(boolean[] kool, float[] goon) {
   }

   @Override
   public void func_70624_b(EntityLivingBase entitylivingbaseIn) {
      super.func_70624_b(entitylivingbaseIn);
   }

   protected void func_184651_r() {
      this.field_70714_bg.func_75776_a(0, new EntityAISkill(this, 40, 100, 5, true, 14));
      this.setskillLeapValues(0.7F, 2.5, 0);
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false, new Class[0]));
      this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
      this.field_70714_bg.func_75776_a(3, new EntityAIAttackMeleeStatus(this, 1.3, false, -1.0));
      this.field_70714_bg.func_75776_a(3, new EntityAILeapAtTarget(this, 0.4F));
      this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
      if (SRPConfigMobs.mudoAnimalAttacking && !SRPConfigSystems.useEvolution) {
         this.field_70715_bh.func_75776_a(4, new EntityAINearestAttackableTarget(this, EntityAnimal.class, true));
      }
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      if (this.srpTicks == 10
         && !this.field_70170_p.field_72995_K
         && this.killcount >= SRPConfigMobs.mudoTunnelValue
         && this.phaseCreated < SRPConfigMobs.mudoTunnelPhase
         && this.field_70146_Z.nextInt(30) == 0) {
         if (this.func_70638_az() != null) {
            return;
         }

         if (this.field_70170_p.func_180495_p(this.func_180425_c()).func_177230_c() == Blocks.field_150350_a
            && this.field_70170_p.func_180495_p(this.func_180425_c().func_177977_b()).func_185913_b()
            && this.field_70170_p.func_180495_p(this.func_180425_c().func_177977_b()).func_185917_h()) {
            this.field_70170_p.func_175656_a(this.func_180425_c(), SRPBlocks.buglin.func_176223_P());
            this.killcount = this.killcount - SRPConfigMobs.mudoTunnelValue;
         }
      }
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.MUDO_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.MUDO_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.MUDO_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.MUDO_KD_RESISTANCE);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(32.0);
   }

   @Override
   public boolean func_70652_k(@Nonnull Entity entityIn) {
      boolean flag = super.func_70652_k(entityIn);
      if (flag && entityIn instanceof EntityLivingBase) {
         switch (this.getSkin()) {
            case 5:
               SRPPotions.applyStackPotion(SRPPotions.VIRA_E, (EntityLivingBase)entityIn, 100, 0);
               break;
            case 6:
               SRPPotions.applyStackPotion(SRPPotions.BLEED_E, (EntityLivingBase)entityIn, 100, 0);
         }

         ((EntityLivingBase)entityIn).func_70690_d(new PotionEffect(MobEffects.field_76421_d, 40, 1));
         if (!((EntityLivingBase)entityIn).func_70644_a(SRPPotions.COTH_E)) {
            ((EntityLivingBase)entityIn).func_70690_d(new PotionEffect(SRPPotions.COTH_E, 3600, 0, false, false));
         }
      }

      return flag;
   }

   @Override
   public void func_70074_a(EntityLivingBase entityLivingIn) {
      if (this.killcount >= 0.0) {
         this.killcount++;
      }

      if (!this.field_70170_p.field_72995_K && SRPConfigSystems.useEvolution) {
         SRPSaveData data = SRPSaveData.get(this.field_70170_p, 43);
         data.setTotalKills(this.field_70170_p.field_73011_w.getDimension(), SRPConfigSystems.valueKill, true, this.field_70170_p, true, 37);
      }

      if (!entityLivingIn.func_70644_a(SRPPotions.COTH_E)) {
         entityLivingIn.func_70690_d(new PotionEffect(SRPPotions.COTH_E, 3600, 0, false, false));
      }

      ParasiteEventEntity.convertEntity(entityLivingIn, entityLivingIn.getEntityData(), true, SRPConfigSystems.COTHVictimParasite);
      this.func_70690_d(new PotionEffect(MobEffects.field_76428_l, 80, 0, false, false));
      this.setWait(10);
      if (!this.field_70170_p.field_72995_K && this.killcount > SRPConfigMobs.mudoMangler && !this.field_70128_L && ParasiteEventEntity.canSpawnNext) {
         ParasiteEventEntity.spawnNext(this, new EntityNuuh(this.field_70170_p), true, false);
      }
   }

   public void func_180430_e(float distance, float damageMultiplier) {
      if (distance >= 60.0F) {
         super.func_180430_e(distance, damageMultiplier);
      }
   }

   public double func_70042_X() {
      return this.field_70131_O * 0.5F;
   }

   protected PathNavigate func_175447_b(World worldIn) {
      return new PathNavigateClimber(this, worldIn);
   }

   public void func_70071_h_() {
      super.func_70071_h_();
      if (!this.field_70170_p.field_72995_K) {
         this.setBesideClimbableBlock(this.field_70123_F);
         if (this.srpTicks == 10 && this.killcount > SRPConfigMobs.mudoMangler && ParasiteEventEntity.canSpawnNext) {
            ParasiteEventEntity.spawnNext(this, new EntityNuuh(this.field_70170_p), true, true);
         }
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
      return 0.8F;
   }

   @Override
   public void func_70645_a(DamageSource cause) {
      if (!this.field_70170_p.field_72995_K) {
         if (!SRPConfigWorld.coloniesActivated && !this.canChangeVariant) {
            super.func_70645_a(cause);
         } else if ((ParasiteEventWorld.numberofColonies(this.field_70170_p) < 1 || !SRPConfigMobs.lodoEnabled) && !this.canChangeVariant) {
            super.func_70645_a(cause);
         } else {
            ParasiteEventEntity.spawnNext(this, new EntityLodo(this.field_70170_p), true, false);
         }
      }
   }

   @Override
   public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
      IEntityLivingData floo = super.func_180482_a(difficulty, livingdata);
      if (!this.field_70170_p.field_72995_K) {
         this.rollTextureVariantWeighted();
      }

      return floo;
   }

   private void rollTextureVariantWeighted() {
      float r = this.field_70146_Z.nextFloat();
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
      } else if (r < 0.005F) {
         this.setSkin(2);
      } else if (r < 0.05F) {
         this.setSkin(7);
      } else if (r < 0.15F) {
         this.setSkin(1);
      } else if (r < 0.25F) {
         this.setSkin(4);
      } else if (r < 0.4F) {
         this.setSkin(3);
      }
   }

   protected SoundEvent func_184639_G() {
      return this.getParasiteStatus() != 0 ? SRPSounds.MOBSILENCE : SRPSounds.MUDO_GROWL;
   }

   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
      return SRPSounds.MUDO_HURT;
   }

   protected SoundEvent func_184615_bR() {
      return SRPSounds.MUDO_DEATH;
   }

   protected void func_180429_a(BlockPos pos, Block blockIn) {
      this.func_184185_a(this.getStepSound(), this.func_70599_aP(), this.func_70647_i());
   }

   protected SoundEvent getStepSound() {
      return SRPSounds.SMALL_STEPS;
   }

   static class EntityAIMudoInfest extends EntityAIBase {
      private final EntityMudo parent;
      private int count;

      public EntityAIMudoInfest(EntityMudo animal, double speedIn) {
         this.parent = animal;
         this.count = 0;
         this.func_75248_a(3);
      }

      public boolean func_75250_a() {
         this.count++;
         if (this.count >= 20) {
            boolean tar = this.parent.func_70638_az() == null;
            boolean nav = this.parent.func_70661_as().func_75500_f();
            String na = "";
            if (!tar) {
               na = this.parent.func_70638_az().func_70005_c_();
            }

            this.count = 0;
            return this.parent.func_70638_az() == null && this.parent.func_70661_as().func_75500_f();
         } else {
            return false;
         }
      }

      public void func_75246_d() {
         AxisAlignedBB axisalignedbb = new AxisAlignedBB(
               this.parent.field_70165_t,
               this.parent.field_70163_u,
               this.parent.field_70161_v,
               this.parent.field_70165_t + 1.0,
               this.parent.field_70163_u + 1.0,
               this.parent.field_70161_v + 1.0
            )
            .func_72314_b(12.0, 3.0, 12.0);

         for (EntityLivingBase mob : this.parent.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb)) {
            if (mob != this.parent && !(mob instanceof EntityMob)) {
               if (!mob.func_70644_a(SRPPotions.COTH_E)
                  && this.parent.func_70685_l(mob)
                  && this.parent.func_70661_as().func_75497_a(mob, 1.3)
                  && this.parent.func_70068_e(mob) < 9.0) {
                  this.spawnLingeringCloud();
                  this.parent.func_184185_a(SRPSounds.MUDO_CLOUD, 2.0F, 1.0F);
               }

               if (!this.parent.func_70661_as().func_75500_f()) {
                  return;
               }
            }
         }
      }

      private void spawnLingeringCloud() {
         EntityAreaEffectCloud entityareaeffectcloud = new EntityAreaEffectCloud(
            this.parent.field_70170_p, this.parent.field_70165_t, this.parent.field_70163_u, this.parent.field_70161_v
         );
         entityareaeffectcloud.func_184483_a(this.parent.field_70130_N * 4.0F);
         entityareaeffectcloud.func_184495_b(-0.5F);
         entityareaeffectcloud.func_184485_d(10);
         entityareaeffectcloud.func_184486_b(entityareaeffectcloud.func_184489_o() * 2);
         entityareaeffectcloud.func_184487_c(-entityareaeffectcloud.func_184490_j() / entityareaeffectcloud.func_184489_o());
         entityareaeffectcloud.func_184496_a(new PotionEffect(SRPPotions.COTH_E, 3600, 1, false, false));
         this.parent.field_70170_p.func_72838_d(entityareaeffectcloud);
      }
   }
}
