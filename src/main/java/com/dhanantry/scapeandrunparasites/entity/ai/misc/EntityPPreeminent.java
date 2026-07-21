package com.dhanantry.scapeandrunparasites.entity.ai.misc;

import com.dhanantry.scapeandrunparasites.block.BlockGore;
import com.dhanantry.scapeandrunparasites.entity.EntityRemain;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAINearestAttackableTargetStatus;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent.EntityFlam;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityGore;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
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
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class EntityPPreeminent extends EntityPMalleable implements EntityCanSummon, EntityCanColony {
   protected int totalP;
   protected int actualP;
   protected int[] mobID;
   protected int[] mobPT;
   private int ggg;
   protected boolean canTeleportToo;

   public EntityPPreeminent(World worldIn) {
      super(worldIn);
      this.field_70715_bh
         .func_75776_a(
            4,
            new EntityAINearestAttackableTargetStatus(
               this, EntityPlayer.class, 0, SRPConfig.preeminentWalls, false, null, SRPConfig.preeminentSneakPen, SRPConfig.preeminentInviPen
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
                  SRPConfig.preeminentWalls,
                  false,
                  new Predicate<EntityLiving>() {
                     public boolean apply(@Nullable EntityLiving entity) {
                        return !(entity instanceof EntityWaterMob)
                           && !(entity instanceof EntityAnimal)
                           && !(entity instanceof EntityVillager)
                           && !ParasiteEventEntity.checkEntity(entity, SRPConfig.mobattackingBlackList, SRPConfig.mobattackingBlackListWhite);
                     }
                  },
                  SRPConfig.preeminentSneakPen,
                  SRPConfig.preeminentInviPen
               )
            );
      }

      this.field_70714_bg.func_85156_a(this.folow);
      this.borderOrb = -1;
      this.canModRender = 0;
      this.field_70728_aV = SRPAttributes.XP_PREE;
      this.canD = SRPConfig.preeminentdespawn;
      this.damageCap = SRPConfig.preeminentCap;
      this.fuseTime = 70;
      this.type = 61;
      this.killcount = 10.0;
      this.fuseOrb = 13;
      this.orbStartTimer = 15;
      this.foodSteal = SRPConfig.preeminentFoodSteal;
      this.orbItemCool = SRPConfig.preeminentItemOrbCooldown * 20;
      this.pointCap = SRPConfig.preeminentPointCap;
      this.pointReduction = SRPConfig.preeminentPointRed;
      this.chanceLearn = SRPConfig.preeminentChanceLe;
      this.chanceLearnFire = SRPConfig.preeminentChanceLeFire;
      this.DamageTypeCap = SRPConfig.preeminentPointDamCap;
      this.MiniDamage = SRPConfig.preeminentMinDamage;
      this.regen = SRPConfig.preeminentRegen * SRPConfig.globalHealthMultiplier;
      this.oneMindDeathValue = SRPConfig.preeminentOneMindDeathV;
      this.regenEff = 25;
      this.foodRott = SRPConfig.preeminentFoodChance;
      this.foodRootNumber = SRPConfig.preeminentFoodAmount;
      this.cothSpread = SRPConfigSystems.cothPure;
      this.totalP = SRPConfig.preeminentFlamTotal;
      this.mobID = new int[this.totalP];
      this.mobPT = new int[this.totalP];

      for (int i = 0; i < this.mobID.length; i++) {
         this.mobID[i] = -777;
      }

      this.valueEvDeath = SRPConfig.preeminentLoosingEPValue;
      this.setScentHPMultiplier(0.25F);
   }

   @Override
   public boolean onlySpawnInside() {
      return false;
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      this.ggg++;
      if (this.ggg > 80) {
         this.ggg = 0;
      }

      if (this.func_70638_az() != null) {
         this.summonFlam(this.func_70638_az());
      }
   }

   @Override
   protected void fearPlayer(EntityLivingBase player) {
      try {
         if (player == null) {
            return;
         }

         if (!this.func_70685_l(player)) {
            return;
         }

         if (!player.func_70644_a(SRPPotions.FEAR_E)) {
            player.func_70690_d(new PotionEffect(SRPPotions.FEAR_E, 300, 2, false, false));
         } else if (player.func_70660_b(SRPPotions.FEAR_E).func_76458_c() < 2) {
            player.func_70690_d(new PotionEffect(SRPPotions.FEAR_E, 300, 2, false, false));
         }
      } catch (Exception var3) {
      }
   }

   @Override
   public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
      boolean flag = super.func_70097_a(source, amount);
      if (flag) {
      }

      return flag;
   }

   public void func_180430_e(float distance, float damageMultiplier) {
      if (distance >= 150.0F) {
         super.func_180430_e(distance, damageMultiplier);
      }
   }

   protected boolean summonFlam(EntityLivingBase in) {
      if (this.field_70170_p.field_72995_K || !SRPConfigMobs.flamEnabled) {
         return false;
      } else if (this.ggg != 40) {
         return false;
      } else {
         double dis = this.func_70068_e(in);
         this.checkID();
         if (this.getActualParasites() >= this.getTotalParasites()) {
            return false;
         } else {
            this.resetIdleTime();
            EntityFlam wa = new EntityFlam(this.field_70170_p);
            float f19 = MathHelper.func_76126_a(this.field_70177_z * (float) (Math.PI / 180.0) - this.field_70704_bt * 0.01F);
            float f14 = (float) (Math.PI / 18);
            float f16 = MathHelper.func_76134_b(f14);
            float f4 = MathHelper.func_76134_b(this.field_70177_z * (float) (Math.PI / 180.0) - this.field_70704_bt * 0.01F);
            wa.func_70634_a(
               this.field_70165_t + -1.0 * (f19 * 4.0F * f16), this.field_70163_u + this.func_70047_e(), this.field_70161_v - -1.0 * (f4 * 4.0F * f16)
            );
            this.setActualParasites(1);
            this.addID(wa.func_145782_y(), 1);
            wa.setDamageATT(this);
            this.field_70170_p.func_72838_d(wa);
            wa.particleStatus((byte)8);
            wa.particleStatus((byte)8);
            wa.particleStatus((byte)8);
            wa.particleStatus((byte)8);
            wa.func_70624_b(this.func_70638_az());
            byte typeF = (byte)(this.field_70146_Z.nextInt(3) + 1);
            if (typeF == 3) {
               if (!(this.func_70638_az().func_70068_e(this) < 100.0) && this.func_70638_az().field_70122_E && !this.canTeleportToo) {
                  this.canTeleportToo = true;
               } else {
                  typeF = (byte)(this.field_70146_Z.nextInt(2) + 1);
               }
            }

            wa.setFatherTo(this, typeF);
            if (this.func_70644_a(MobEffects.field_76441_p)) {
               wa.func_70690_d(new PotionEffect(MobEffects.field_76441_p, 60, 0, false, false));
            }

            this.ggg -= 100;
            return true;
         }
      }
   }

   public void setTeleFlam(boolean in) {
      this.canTeleportToo = in;
   }

   public boolean getTeleFlam() {
      return this.canTeleportToo;
   }

   @Override
   protected void spawnGore() {
      int range = 4;
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
               && this.field_70170_p.field_73012_v.nextInt(4) == 0) {
               this.field_70170_p.func_175656_a(blockpos, SRPBlocks.gorePur.func_176223_P().func_177226_a(BlockGore.VARIANT, BlockGore.EnumType.FLAT));
            }
         }
      }

      if (this.field_70170_p.func_180495_p(this.func_180425_c().func_177977_b()).func_185913_b()
         && (
            this.field_70170_p.func_180495_p(this.func_180425_c()).func_177230_c() instanceof BlockBush
               || this.field_70170_p.func_180495_p(this.func_180425_c()).func_177230_c() == Blocks.field_150350_a
         )) {
         this.field_70170_p.func_175656_a(this.func_180425_c(), SRPBlocks.gorePur.func_176223_P().func_177226_a(BlockGore.VARIANT, BlockGore.EnumType.BIG));
         EntityRemain nnn = new EntityRemain(this.field_70170_p);
         nnn.func_70012_b(
            this.func_180425_c().func_177958_n() + 0.5, this.func_180425_c().func_177956_o(), this.func_180425_c().func_177952_p() + 0.5, 0.0F, 0.0F
         );
         nnn.setParasite(EntityList.func_191301_a(this).toString());
         nnn.setSkin((byte)this.getSkin());
         nnn.setGoal(20 * SRPConfig.pureRemainValue);
         this.field_70170_p.func_72838_d(nnn);
      }

      for (int i = 0; i < 7 && SRPConfig.paraGore; i++) {
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
         bomb.setType((byte)4);
         bomb.func_82149_j(this);
         bomb.setMotion(d3, d4, d5, 0.25, 0.65);
         this.field_70170_p.func_72838_d(bomb);
      }
   }

   @Override
   public int getTotalParasites() {
      return this.totalP;
   }

   @Override
   public int getActualParasites() {
      return this.actualP;
   }

   @Override
   public void setActualParasites(int i) {
      this.actualP += i;
   }

   @Override
   public void addID(int id, int points) {
      for (int i = 0; i < this.mobID.length; i++) {
         if (this.mobID[i] == -777) {
            this.mobID[i] = id;
            this.mobPT[i] = points;
            return;
         }
      }
   }

   @Override
   public int IDable() {
      int flag = 0;

      for (int i = 0; i < this.mobID.length; i++) {
         if (this.mobID[i] == -777) {
            flag++;
         }
      }

      if (flag > this.totalP) {
         flag = this.totalP;
      }

      return flag;
   }

   @Override
   public void checkID() {
      for (int i = 0; i < this.mobID.length; i++) {
         if (this.mobID[i] > 0) {
            Entity flag = this.field_70170_p.func_73045_a(this.mobID[i]);
            if (flag == null) {
               this.mobID[i] = -777;
               int negative = this.mobPT[i] * -1;
               this.setActualParasites(negative);
            }
         }
      }
   }

   @Override
   public int[] getIDList() {
      return this.mobID;
   }

   @Override
   public int[] getPointList() {
      return this.mobPT;
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void spawnEffectsGore() {
   }

   @Override
   public boolean scaryOrbEffect(EntityLivingBase in, int mobs) {
      boolean flag = super.scaryOrbEffect(in, mobs);
      if (flag && in instanceof EntityPlayer) {
         EntityPlayer playerIn = (EntityPlayer)in;
         if (playerIn.field_71068_ca > 0) {
            playerIn.func_71023_q(-SRPConfig.preeminentExpSteal);
            if (playerIn.field_71106_cc < 0.0F) {
               playerIn.field_71068_ca--;
               playerIn.field_71106_cc = 1.0F;
            }
         } else {
            playerIn.func_71023_q(-SRPConfig.preeminentExpSteal);
            if (playerIn.field_71106_cc < 0.0F) {
               playerIn.field_71106_cc = 0.0F;
            }
         }
      }

      return flag;
   }
}
