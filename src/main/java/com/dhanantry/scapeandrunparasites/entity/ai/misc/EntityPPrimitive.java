package com.dhanantry.scapeandrunparasites.entity.ai.misc;

import com.dhanantry.scapeandrunparasites.block.BlockGore;
import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.entity.EntityRemain;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAINearestAttackableTargetStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISkill;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityGore;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import com.google.common.base.Predicate;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class EntityPPrimitive extends EntityPMalleable {
   private static final float PRIMITIVE_BLOCK_CHANCE = 0.1F;

   public EntityPPrimitive(World worldIn) {
      super(worldIn);
      this.field_70715_bh
         .func_75776_a(
            4,
            new EntityAINearestAttackableTargetStatus(
               this, EntityPlayer.class, 0, SRPConfig.primitiveWalls, false, null, SRPConfig.primitiveSneakPen, SRPConfig.primitiveInviPen
            )
         );
      if (SRPConfig.canOrbAttack) {
         this.field_70714_bg.func_75776_a(2, new EntityAISkill(this, 80, 4, false, 21));
      }

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
      this.canModRender = 1;
      this.type = 31;
      this.fuseOrb = 11;
      this.orbStartTimer = 30;
      this.foodSteal = SRPConfig.primitiveFoodSteal;
      this.orbItemCool = SRPConfig.primitiveItemOrbCooldown * 20;
      this.pointCap = SRPConfig.primitivePointCap;
      this.pointReduction = SRPConfig.primitivePointRed;
      this.chanceLearn = SRPConfig.primitiveChanceLe;
      this.chanceLearnFire = SRPConfig.primitiveChanceLeFire;
      this.DamageTypeCap = SRPConfig.primitivePointDamCap;
      this.MiniDamage = SRPConfig.primitiveMinDamage;
      this.regen = SRPConfig.primitiveRegen * SRPConfig.globalHealthMultiplier;
      this.oneMindDeathValue = SRPConfig.primitiveOneMindDeathV;
      this.regenEff = 5;
      this.foodRott = SRPConfig.primitiveFoodChance;
      this.foodRootNumber = SRPConfig.primitiveFoodAmount;
      this.cothSpread = SRPConfigSystems.cothPrimitive;
      this.valueEvDeath = SRPConfig.primitiveLoosingEPValue;
      this.setScentHPMultiplier(1.5F);
   }

   @Override
   protected void fearPlayer(EntityLivingBase player) {
   }

   protected void fearPlayer(EntityLivingBase player, float damageDealt) {
      if (player != null && !player.field_70170_p.field_72995_K) {
         if (!(damageDealt <= 8.0F)) {
            int level = 1 + Math.max(0, (int)Math.floor((damageDealt - 8.0F) / 4.0F));
            int cap = 3;
            level = Math.min(level, cap);
            int duration = 300 + 40 * (level - 1);
            duration = MathHelper.func_76125_a(duration, 200, 500);
            int amplifier = level - 1;
            player.func_70690_d(new PotionEffect(SRPPotions.FEAR_E, duration, amplifier, false, true));
         }
      }
   }

   @Override
   public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
      if (this.canRandomBlock() && amount > 0.0F && !source.func_76363_c() && this.field_70173_aa > 5 && this.field_70146_Z.nextFloat() < 0.1F) {
         this.field_70170_p
            .func_184148_a(
               null,
               this.field_70165_t,
               this.field_70163_u + this.field_70131_O * 0.5,
               this.field_70161_v,
               SoundEvents.field_187767_eL,
               SoundCategory.HOSTILE,
               0.7F,
               1.15F + this.field_70146_Z.nextFloat() * 0.15F
            );
         if (this.field_70170_p instanceof WorldServer) {
            ((WorldServer)this.field_70170_p)
               .func_175739_a(
                  EnumParticleTypes.CRIT,
                  this.field_70165_t,
                  this.field_70163_u + this.field_70131_O * 0.6,
                  this.field_70161_v,
                  6,
                  0.08,
                  0.08,
                  0.08,
                  0.01,
                  new int[0]
               );
         }

         Entity src = source.func_76346_g();
         if (src instanceof EntityLivingBase) {
            this.func_70604_c((EntityLivingBase)src);
         }

         return false;
      } else if (this.field_70170_p.field_72995_K) {
         return super.func_70097_a(source, amount);
      } else {
         if (source.func_76346_g() instanceof EntityLivingBase) {
            EntityLivingBase attacker = (EntityLivingBase)source.func_76346_g();
            if (attacker.func_70644_a(SRPPotions.KILLPRI_E)) {
               int amp = attacker.func_70660_b(SRPPotions.KILLPRI_E).func_76458_c();
               float totalRed = MathHelper.func_76131_a(SRPConfigSystems.parasiteKillingReduction * (amp + 1.0F), 0.0F, 0.95F);
               float reduced = amount * (1.0F - totalRed);
               return super.func_70097_a(source, Math.max(0.0F, reduced));
            }
         }

         return super.func_70097_a(source, amount);
      }
   }

   @Override
   protected void attackEntityFromEffects(int range, int count) {
      this.particleStatus((byte)51);
      double i1 = MathHelper.func_76128_c(this.field_70163_u + 0.1);
      double l1 = this.field_70165_t;
      double i2 = this.field_70161_v;
      int counttt = 0;

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
               && this.field_70170_p.field_73012_v.nextInt(4) == 0) {
               this.field_70170_p.func_175656_a(blockpos, SRPBlocks.gorePri.func_176223_P().func_177226_a(BlockGore.VARIANT, BlockGore.EnumType.FLAT));
               if (++counttt >= count) {
                  return;
               }
            }
         }
      }
   }

   @Override
   protected void attackEntityFromCap(int go) {
      for (int i = 0; i < go && SRPConfig.paraGore; i++) {
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
         double d7 = 0.8 / (d6 / 4.0 + 0.1);
         d7 *= this.field_70170_p.field_73012_v.nextFloat() * this.field_70170_p.field_73012_v.nextFloat() + 0.3F;
         d3 *= d7;
         d4 = d4 * d7 * 2.0;
         d5 *= d7;
         EntityGore bomb = new EntityGore(this.field_70170_p);
         bomb.setType((byte)2);
         bomb.func_82149_j(this);
         bomb.setMotion(d3, d4, d5, 0.2, 0.8);
         this.field_70170_p.func_72838_d(bomb);
      }
   }

   @Override
   protected void spawnGore() {
      this.attackEntityFromEffects(2, 100);
      if (this.field_70170_p.func_180495_p(this.func_180425_c().func_177977_b()).func_185913_b()
         && (
            this.field_70170_p.func_180495_p(this.func_180425_c()).func_177230_c() instanceof BlockBush
               || this.field_70170_p.func_180495_p(this.func_180425_c()).func_177230_c() == Blocks.field_150350_a
         )) {
         this.field_70170_p.func_175656_a(this.func_180425_c(), SRPBlocks.gorePri.func_176223_P().func_177226_a(BlockGore.VARIANT, BlockGore.EnumType.BIG));
         EntityRemain nnn = new EntityRemain(this.field_70170_p);
         nnn.func_70012_b(
            this.func_180425_c().func_177958_n() + 0.5, this.func_180425_c().func_177956_o(), this.func_180425_c().func_177952_p() + 0.5, 0.0F, 0.0F
         );
         nnn.setParasite(EntityList.func_191301_a(this).toString());
         nnn.setSkin((byte)this.getSkin());
         nnn.setGoal(20 * SRPConfig.primitiveRemainValue);
         this.field_70170_p.func_72838_d(nnn);
      }

      this.attackEntityFromCap(4);
   }

   @Override
   public boolean func_70652_k(@Nonnull Entity entityIn) {
      if (!(entityIn instanceof EntityLivingBase)) {
         return super.func_70652_k(entityIn);
      } else {
         EntityLivingBase target = (EntityLivingBase)entityIn;
         float beforeH = target.func_110143_aJ();
         float beforeA = target.func_110139_bj();
         boolean hit = super.func_70652_k(entityIn);
         if (hit && !this.field_70170_p.field_72995_K) {
            float afterH = target.func_110143_aJ();
            float afterA = target.func_110139_bj();
            float dealt = Math.max(0.0F, beforeH + beforeA - (afterH + afterA));
            if (dealt > 0.0F) {
               this.fearPlayer(target, dealt);
            }
         }

         return hit;
      }
   }

   @Override
   protected boolean onDeathDislo(DamageSource cause) {
      if (SRPConfigSystems.disloSameVersionDyeing && this.disloNumberTwentytwo) {
         boolean flag = super.onDeathDislo(cause);
         if (!flag && cause.func_76346_g() instanceof EntityLivingBase) {
            int count = SRPSaveData.get(this.field_70170_p, 29).getCurrentCode(this.field_70170_p.field_73011_w.getDimension(), 22);
            EntityLivingBase target = (EntityLivingBase)cause.func_76346_g();
            if (count >= 1) {
               target.func_184596_c(SRPPotions.KILLADA_E);
               target.func_184596_c(SRPPotions.KILLPUR_E);
               target.func_184596_c(SRPPotions.KILLFER_E);
               target.func_184596_c(SRPPotions.KILLCRU_E);
               target.func_184596_c(SRPPotions.KILLNEX_E);
               SRPPotions.applyStackPotion(SRPPotions.KILLPRI_E, target, 1200, count);
            }
         }

         return flag;
      } else {
         return super.onDeathDislo(cause);
      }
   }

   protected boolean canRandomBlock() {
      return true;
   }

   public void func_180430_e(float distance, float damageMultiplier) {
      if (distance >= 40.0F) {
         super.func_180430_e(distance, damageMultiplier);
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
   public boolean scaryOrbEffect(EntityLivingBase in, int mobs) {
      boolean flag = super.scaryOrbEffect(in, mobs);
      if (flag) {
      }

      return flag;
   }
}
