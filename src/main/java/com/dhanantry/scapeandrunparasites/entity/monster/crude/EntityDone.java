package com.dhanantry.scapeandrunparasites.entity.monster.crude;

import com.dhanantry.scapeandrunparasites.block.BlockGore;
import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.entity.EntityRemain;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIAttackMeleeStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIGetFollowers;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAINearestAttackableTargetStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPMalleable;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityGore;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.google.common.base.Predicate;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityDone extends EntityPMalleable {
   private static final DataParameter<Integer> TARGET_ENTITY = EntityDataManager.func_187226_a(EntityDone.class, DataSerializers.field_187192_b);
   private EntityLivingBase targetedEntity;
   private int pulling;
   private boolean canPull;

   public EntityDone(World worldIn) {
      super(worldIn);
      this.func_70105_a(0.8F, 3.4F);
      this.borderOrb = -1;
      this.canModRender = 1;
      this.type = 11;
      this.field_70715_bh
         .func_75776_a(
            4,
            new EntityAINearestAttackableTargetStatus(
               this, EntityPlayer.class, 0, SRPConfig.primitiveWalls, false, null, SRPConfig.adaptedSneakPen, SRPConfig.adaptedInviPen
            )
         );
      if (SRPConfig.mobattacking) {
         this.field_70715_bh
            .func_75776_a(
               4,
               new EntityAINearestAttackableTargetStatus(
                  this,
                  EntityLiving.class,
                  0,
                  SRPConfig.primitiveWalls,
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

      this.field_70728_aV = SRPAttributes.XP_PRIMITIVE;
      this.damageCap = SRPConfig.primitiveCap;
      this.canD = SRPConfig.primitivedespawn;
      this.type = 31;
      this.foodSteal = SRPConfig.primitiveFoodSteal;
      this.pointCap = SRPConfig.primitivePointCap;
      this.pointReduction = SRPConfig.primitivePointRed;
      this.chanceLearn = SRPConfig.primitiveChanceLe;
      this.chanceLearnFire = SRPConfig.primitiveChanceLeFire;
      this.DamageTypeCap = SRPConfig.primitivePointDamCap;
      this.MiniDamage = SRPConfig.primitiveMinDamage;
      this.regen = SRPConfig.primitiveRegen * SRPConfig.globalHealthMultiplier;
      this.oneMindDeathValue = SRPConfig.primitiveOneMindDeathV;
      this.regenEff = 1;
      this.valueEvDeath = SRPConfig.primitiveLoosingEPValue;
   }

   @Override
   public int getParasiteIDRegister() {
      return 319;
   }

   @Override
   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_187214_a(TARGET_ENTITY, 0);
   }

   protected void func_184651_r() {
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
      this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
      this.field_70714_bg.func_75776_a(3, new EntityAIAttackMeleeStatus(this, 1.3, false, 0.0));
      this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
      this.field_70714_bg.func_75776_a(6, new EntityAIGetFollowers(this, 1, 16));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.DONE_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.DONE_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.DONE_KD_RESISTANCE);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.DONE_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(SRPConfig.primitiveFollow);
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      if (!this.field_70170_p.field_72995_K) {
         if (!this.canPull) {
            this.pulling--;
            if (this.pulling == 0) {
               this.canPull = true;
            }
         }

         if (this.func_70638_az() != null) {
            if (!this.func_70638_az().func_70089_S()) {
               this.func_70624_b(null);
               this.setTargetedEntity(0);
            } else if (this.func_70685_l(this.func_70638_az())
               && this.func_70068_e(this.func_70638_az()) > 0.0
               && this.canPull
               && this.getTargetedEntity() != null) {
               this.func_70638_az().func_70690_d(new PotionEffect(MobEffects.field_76421_d, 20, 1, false, false));
               this.func_70638_az().func_70690_d(new PotionEffect(MobEffects.field_76419_f, 20, 1, false, false));
               this.lookAt(this.getTargetedEntity());
               this.attackEntityAsMobMinimum(this.func_70638_az(), 0.02F);
               this.setParasiteStatus(3);
               this.pulling++;
               if (this.pulling > 200 || this.func_70068_e(this.func_70638_az()) > 9.0) {
                  this.setTargetedEntity(0);
                  this.canPull = false;
               }
            } else {
               this.setTargetedEntity(0);
            }
         } else {
            this.setTargetedEntity(0);
         }
      }

      this.field_70703_bu = false;
      if (this.getTargetedEntity() != null && this.func_70068_e(this.getTargetedEntity()) > 0.0) {
         EntityLivingBase target = this.getTargetedEntity();
         target.func_184210_p();
         double str = 0.3;
         double deltaX = this.field_70165_t - target.field_70165_t;
         double deltaY = this.field_70163_u - target.field_70163_u;
         double deltaZ = this.field_70161_v - target.field_70161_v;
         str = 0.13;
         double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
         if (distance == 0.0) {
            return;
         }

         deltaX /= distance;
         deltaY /= distance;
         deltaZ /= distance;
         target.field_70159_w += deltaX * str;
         target.field_70181_x += deltaY * str;
         target.field_70179_y += deltaZ * str;
      }
   }

   @Override
   protected void handleParasiteStatus() {
      int k = this.getParasiteStatus();
      if (this.getAttackCooldownAni() != 0 || k == 1 || k == 2 || k == 3) {
         if (this.getAttackCooldownAni() != 0) {
            int i = this.getAttackCooldownAni() - 1;
            this.setAttackCooldownAni(i);
         }

         if (k == 1 || k == 2 || k == 3) {
            if (this.func_70638_az() != null) {
               if (!this.func_70638_az().func_70089_S()) {
                  this.func_70624_b(null);
                  this.setParasiteStatus(0);
               } else if (!this.canPull) {
                  this.setParasiteStatus(Math.min(k, 2));
               }
            } else {
               this.setParasiteStatus(0);
               this.func_70624_b(null);
            }
         }
      }
   }

   public void setTargetedEntity(int entityId) {
      if (this.canPull || entityId == 0) {
         this.field_70180_af.func_187227_b(TARGET_ENTITY, entityId);
      }
   }

   public boolean hasTargetedEntity() {
      return !this.canPull ? false : (Integer)this.field_70180_af.func_187225_a(TARGET_ENTITY) != 0;
   }

   public EntityLivingBase getTargetedEntity() {
      if (!this.hasTargetedEntity()) {
         return null;
      } else if (this.field_70170_p.field_72995_K) {
         if (this.targetedEntity != null) {
            return this.targetedEntity;
         } else {
            Entity entity = this.field_70170_p.func_73045_a((Integer)this.field_70180_af.func_187225_a(TARGET_ENTITY));
            if (entity instanceof EntityLivingBase) {
               this.targetedEntity = (EntityLivingBase)entity;
               return this.targetedEntity;
            } else {
               return null;
            }
         }
      } else {
         return this.func_70638_az();
      }
   }

   @Override
   public void applyGene(boolean[] kool, float[] goon) {
      super.applyGene(kool, goon);
      this.geneWaterleap = true;
   }

   @Override
   protected void handleWater(boolean check) {
      if (check && this.field_70170_p.func_180495_p(this.func_180425_c()).func_177230_c() instanceof BlockLiquid && this.func_70638_az() != null) {
         this.liquidLeap++;
         if (this.liquidLeap > 8) {
            this.liquidLeap = 8;
         }
      }

      if (this.liquidLeap >= 1) {
         if (this.geneWaterleap) {
            double h = 0.3;
            double str = 1.2;
            this.func_70661_as().func_75499_g();
            EntityLivingBase entitylivingbase = this.func_70638_az();
            if (entitylivingbase != null) {
               Block bl = this.field_70170_p.func_180495_p(this.func_180425_c()).func_177230_c();
               if (!bl.func_176205_b(this.field_70170_p, this.func_180425_c())) {
                  h = 0.3;
                  str = 1.0;
               }

               this.liquidLeap--;
               double dd0 = entitylivingbase.field_70165_t - this.field_70165_t;
               double dd1 = entitylivingbase.field_70161_v - this.field_70161_v;
               float f = MathHelper.func_76133_a(dd0 * dd0 + dd1 * dd1);
               this.field_70159_w = this.field_70159_w + (dd0 / f * str * 0.8F + this.field_70159_w * 0.2F);
               this.field_70179_y = this.field_70179_y + (dd1 / f * str * 0.8F + this.field_70179_y * 0.2F);
               this.field_70181_x = h;
               this.lookAt(entitylivingbase);
            }
         } else {
            this.liquidLeap--;
         }
      }
   }

   @Override
   public boolean func_70652_k(@Nonnull Entity entityIn) {
      boolean flag = super.func_70652_k(entityIn);
      if (flag && !this.hasTargetedEntity()) {
         this.setTargetedEntity(entityIn.func_145782_y());
         ((EntityLivingBase)entityIn).func_70690_d(new PotionEffect(MobEffects.field_76437_t, 60, 3, false, false));
      }

      return flag;
   }

   public float func_70047_e() {
      return 1.73F;
   }

   protected SoundEvent func_184639_G() {
      return this.getParasiteStatus() != 0 ? SRPSounds.MOBSILENCE : SRPSounds.DONE_GROWL;
   }

   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
      return this.field_70146_Z.nextBoolean() && this.getHitStatus() > 0 ? SRPSounds.MOBSILENCE : SRPSounds.DONE_HURT;
   }

   protected SoundEvent func_184615_bR() {
      return SRPSounds.DONE_DEATH;
   }

   @Override
   public boolean scaryOrbEffect(EntityLivingBase in, int mobs) {
      boolean flag = super.scaryOrbEffect(in, mobs);
      if (flag) {
      }

      return flag;
   }

   @Override
   protected void spawnGore() {
      int range = 2;
      double i1 = MathHelper.func_76128_c(this.field_70163_u + 0.1);
      double l1 = this.field_70165_t;
      double i2 = this.field_70161_v;

      for (int k2 = -1 * range; k2 <= 1 * range && SRPConfig.paraGore; k2++) {
         for (int l2 = -1 * range; l2 <= 1 * range; l2++) {
            double i3 = l1 + k2;
            double l = i2 + l2;
            BlockPos blockpos = new BlockPos(i3, i1, l);
            Block block = this.field_70170_p.func_180495_p(blockpos).func_177230_c();
            Block blockDown = this.field_70170_p.func_180495_p(blockpos.func_177977_b()).func_177230_c();
            if (block == Blocks.field_150350_a
               && blockDown != Blocks.field_150350_a
               && this.field_70170_p.func_180495_p(blockpos.func_177977_b()).func_185913_b()
               && blockDown != SRPBlocks.InfestedStain
               && this.field_70170_p.field_73012_v.nextInt(3) == 0) {
               this.field_70170_p.func_175656_a(blockpos, SRPBlocks.goreFer.func_176223_P().func_177226_a(BlockGore.VARIANT, BlockGore.EnumType.FLAT));
            }
         }
      }

      if (this.field_70170_p.func_180495_p(this.func_180425_c().func_177977_b()).func_185913_b()
         && (
            this.field_70170_p.func_180495_p(this.func_180425_c()).func_177230_c() instanceof BlockBush
               || this.field_70170_p.func_180495_p(this.func_180425_c()).func_177230_c() == Blocks.field_150350_a
         )) {
         this.field_70170_p.func_175656_a(this.func_180425_c(), SRPBlocks.goreFer.func_176223_P().func_177226_a(BlockGore.VARIANT, BlockGore.EnumType.BIG));
         EntityRemain nnn = new EntityRemain(this.field_70170_p);
         nnn.func_70012_b(
            this.func_180425_c().func_177958_n() + 0.5, this.func_180425_c().func_177956_o(), this.func_180425_c().func_177952_p() + 0.5, 0.0F, 0.0F
         );
         nnn.setParasite(EntityList.func_191301_a(this).toString());
         nnn.setSkin((byte)this.getSkin());
         nnn.setGoal(20 * SRPConfig.primitiveRemainValue);
         this.field_70170_p.func_72838_d(nnn);
      }

      for (int i = 0; i < 4 && SRPConfig.paraGore; i++) {
         double d0 = (float)this.field_70165_t + this.field_70170_p.field_73012_v.nextFloat();
         double d1 = (float)this.field_70163_u + this.field_70170_p.field_73012_v.nextFloat();
         double d2 = (float)this.field_70161_v + this.field_70170_p.field_73012_v.nextFloat();
         double d3 = d0 - this.field_70165_t;
         double d4 = d1 - this.field_70163_u;
         double d5 = d2 - this.field_70161_v;
         double d6 = MathHelper.func_76133_a(d3 * d3 + d4 * d4 + d5 * d5);
         d3 /= d6;
         d4 /= d6;
         d5 /= d6;
         double d7 = 0.5 / (d6 / 4.0 + 0.1);
         d7 *= this.field_70170_p.field_73012_v.nextFloat() * this.field_70170_p.field_73012_v.nextFloat() + 0.3F;
         d3 *= d7;
         d4 = d4 * d7 * 2.0;
         d5 *= d7;
         EntityGore bomb = new EntityGore(this.field_70170_p);
         bomb.setType((byte)2);
         bomb.func_82149_j(this);
         bomb.setMotion(d3, d4, d5, 0.15, 0.55);
         this.field_70170_p.func_72838_d(bomb);
      }
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void spawnEffectsGore() {
      for (int i = 0; i <= 60; i++) {
         if (i % 4 == 0) {
            this.spawnParticles(SRPEnumParticle.GCLOUD, 150, 0, 0);
         }

         if (i % 5 == 0) {
            this.spawnParticles(SRPEnumParticle.GSPLASH, 2, -1, -1);
         }
      }
   }

   @Override
   public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
      return super.func_180482_a(difficulty, livingdata);
   }
}
