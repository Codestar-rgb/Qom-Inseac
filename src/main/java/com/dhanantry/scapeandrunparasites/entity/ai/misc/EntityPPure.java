package com.dhanantry.scapeandrunparasites.entity.ai.misc;

import com.dhanantry.scapeandrunparasites.block.BlockGore;
import com.dhanantry.scapeandrunparasites.entity.EntityRemain;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAINearestAttackableTargetStatus;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISkill;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.EntityDodT;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.EntityNak;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityGore;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import com.google.common.base.Predicate;
import java.util.List;
import java.util.Random;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class EntityPPure extends EntityPMalleable implements EntityCanSummon {
   protected int totalP;
   protected int actualP;
   protected int[] mobID;
   protected int[] mobPT;
   private int ggg;

   public EntityPPure(World worldIn) {
      super(worldIn);
      this.field_70715_bh
         .func_75776_a(
            4,
            new EntityAINearestAttackableTargetStatus(
               this, EntityPlayer.class, 0, SRPConfig.pureWalls, false, null, SRPConfig.pureSneakPen, SRPConfig.pureInviPen
            )
         );
      if (SRPConfig.canOrbAttack) {
         this.field_70714_bg.func_75776_a(2, new EntityAISkill(this, 60, 5, false, 21));
      }

      if (SRPConfig.mobattacking) {
         this.field_70715_bh
            .func_75776_a(
               4,
               new EntityAINearestAttackableTargetStatus(
                  this,
                  EntityLiving.class,
                  0,
                  SRPConfig.pureWalls,
                  false,
                  new Predicate<EntityLiving>() {
                     public boolean apply(@Nullable EntityLiving entity) {
                        return !(entity instanceof EntityWaterMob)
                           && !(entity instanceof EntityAnimal)
                           && !ParasiteEventEntity.checkEntity(entity, SRPConfig.mobattackingBlackList, SRPConfig.mobattackingBlackListWhite);
                     }
                  },
                  SRPConfig.pureSneakPen,
                  SRPConfig.pureInviPen
               )
            );
      }

      this.field_70714_bg.func_85156_a(this.folow);
      this.field_70728_aV = SRPAttributes.XP_ADAPTED;
      this.canD = SRPConfig.puredespawn;
      this.damageCap = SRPConfig.pureCap;
      this.canModRender = 1;
      this.fuseTime = 70;
      this.type = 51;
      this.killcount = 10.0;
      this.fuseOrb = 23;
      this.orbStartTimer = 5;
      this.foodSteal = SRPConfig.pureFoodSteal;
      this.orbItemCool = SRPConfig.pureItemOrbCooldown * 20;
      this.pointCap = SRPConfig.purePointCap;
      this.pointReduction = SRPConfig.purePointRed;
      this.chanceLearn = SRPConfig.pureChanceLe;
      this.chanceLearnFire = SRPConfig.pureChanceLeFire;
      this.DamageTypeCap = SRPConfig.purePointDamCap;
      this.MiniDamage = SRPConfig.pureMinDamage;
      this.regen = SRPConfig.pureRegen * SRPConfig.globalHealthMultiplier;
      this.oneMindDeathValue = SRPConfig.pureOneMindDeathV;
      this.regenEff = 15;
      this.foodRott = SRPConfig.pureFoodChance;
      this.foodRootNumber = SRPConfig.pureFoodAmount;
      this.cothSpread = SRPConfigSystems.cothPure;
      this.totalP = SRPConfig.pureSeiZ;
      this.mobID = new int[this.totalP];
      this.mobPT = new int[this.totalP];

      for (int i = 0; i < this.mobID.length; i++) {
         this.mobID[i] = -777;
      }

      this.valueEvDeath = SRPConfig.pureLoosingEPValue;
      this.setScentHPMultiplier(1.0F);
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      this.ggg++;
      if (this.ggg > 60) {
         this.ggg = 0;
      }

      if (this.func_70638_az() != null) {
         this.summonTentacles(this.func_70638_az());
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
      if (this.field_70170_p.field_72995_K) {
         return super.func_70097_a(source, amount);
      } else {
         if (source.func_76346_g() instanceof EntityLivingBase) {
            EntityLivingBase attacker = (EntityLivingBase)source.func_76346_g();
            if (attacker.func_70644_a(SRPPotions.KILLPUR_E)) {
               int amp = attacker.func_70660_b(SRPPotions.KILLPUR_E).func_76458_c();
               float totalRed = MathHelper.func_76131_a(SRPConfigSystems.parasiteKillingReduction * (amp + 1.0F), 0.0F, 0.95F);
               float reduced = amount * (1.0F - totalRed);
               boolean flag = super.func_70097_a(source, Math.max(0.0F, reduced));
               if (flag) {
                  this.summonTentacles((EntityLivingBase)source.func_76346_g());
               }

               return flag;
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
               this.field_70170_p.func_175656_a(blockpos, SRPBlocks.gorePur.func_176223_P().func_177226_a(BlockGore.VARIANT, BlockGore.EnumType.FLAT));
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
         bomb.setType((byte)4);
         bomb.func_82149_j(this);
         bomb.setMotion(d3, d4, d5, 0.2, 0.8);
         this.field_70170_p.func_72838_d(bomb);
      }
   }

   @Override
   protected void spawnGore() {
      this.attackEntityFromEffects(3, 100);
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

      this.attackEntityFromCap(5);
   }

   @Override
   protected boolean onDeathDislo(DamageSource cause) {
      if (SRPConfigSystems.disloSameVersionDyeing && this.disloNumberTwentytwo) {
         boolean flag = super.onDeathDislo(cause);
         if (!flag && cause.func_76346_g() instanceof EntityLivingBase) {
            int count = SRPSaveData.get(this.field_70170_p, 28).getCurrentCode(this.field_70170_p.field_73011_w.getDimension(), 22);
            EntityLivingBase target = (EntityLivingBase)cause.func_76346_g();
            if (count >= 1) {
               target.func_184596_c(SRPPotions.KILLPRI_E);
               target.func_184596_c(SRPPotions.KILLADA_E);
               target.func_184596_c(SRPPotions.KILLFER_E);
               target.func_184596_c(SRPPotions.KILLCRU_E);
               target.func_184596_c(SRPPotions.KILLNEX_E);
               SRPPotions.applyStackPotion(SRPPotions.KILLPUR_E, target, 1200, count);
            }
         }

         return flag;
      } else {
         return super.onDeathDislo(cause);
      }
   }

   public void func_180430_e(float distance, float damageMultiplier) {
      if (distance >= 80.0F) {
         super.func_180430_e(distance, damageMultiplier);
      }
   }

   protected void summonTentacles(EntityLivingBase in) {
      if (this.ggg == 20) {
         if (!SRPConfigSystems.useEvolution || ParasiteEventEntity.getRSchance(this.field_70170_p) != 0.0) {
            double dis = this.func_70068_e(in);
            boolean flag = !this.func_70685_l(in);
            if ((!(dis > 64.0) || !flag || this.field_70146_Z.nextInt(3) != 0) && this.field_70146_Z.nextInt(10) != 0) {
               if (in.func_70644_a(MobEffects.field_76421_d) && in.func_70660_b(MobEffects.field_76421_d).func_76458_c() == 2) {
                  return;
               }

               this.checkID();
               if (this.getActualParasites() < this.getTotalParasites() && this.spawnT(in, 3, 1, 16, 1)) {
               }
            } else {
               this.checkID();
               if (this.getActualParasites() < this.getTotalParasites() && this.spawnT(in, 5, 3, 2, 2)) {
                  this.func_70690_d(new PotionEffect(MobEffects.field_76421_d, 150, 120, false, false));
               }
            }
         }
      }
   }

   protected boolean spawnT(EntityLivingBase in, int range2, int mini2, int check, int type) {
      int range = range2;
      int mini = mini2;
      Random rand = new Random();
      double x = in.field_70165_t;
      double y = in.field_70163_u;
      double z = in.field_70161_v;
      double randomx = rand.nextInt(range2) + mini2;
      double randomz = rand.nextInt(range2) + mini2;
      double negative = rand.nextInt(2);
      if (negative == 0.0) {
         randomx *= -1.0;
      }

      negative = rand.nextInt(2);
      if (negative == 0.0) {
         randomz *= -1.0;
      }

      int limit = 0;
      boolean flag = true;
      EntityPStationary entityout = new EntityDodT(this.field_70170_p);
      ((EntityDodT)entityout).setTele(this);
      if (type == 1) {
         List<Entity> serverList = this.field_70170_p.field_72996_f;
         int count = 0;

         for (int k = 0; k < serverList.size(); k++) {
            if (serverList.get(k) instanceof EntityNak) {
               count++;
            }
         }

         if (count >= 7) {
            entityout.func_70106_y();
            return false;
         }

         entityout = new EntityNak(this.field_70170_p);
      }

      while (flag) {
         if (limit >= 5) {
            entityout.func_70106_y();
            return false;
         }

         BlockPos pos = new BlockPos(x + randomx, y, z + randomz);
         pos = ParasiteEventEntity.getFloor(this.field_70170_p, pos, 5);
         if (pos != null) {
            IBlockState state = this.field_70170_p.func_180495_p(pos);
            if (this.field_70170_p.func_180495_p(pos.func_177977_b()).func_185917_h()) {
               AxisAlignedBB axisalignedbb = new AxisAlignedBB(
                     pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), pos.func_177958_n() + 1, pos.func_177956_o() + 1, pos.func_177952_p() + 1
                  )
                  .func_186662_g(check);
               List<EntityLivingBase> moblist = this.field_70170_p.func_72872_a(EntityNak.class, axisalignedbb);
               if (moblist.size() > 10) {
                  return false;
               }

               axisalignedbb = new AxisAlignedBB(
                     pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), pos.func_177958_n() + 1, pos.func_177956_o() + 1, pos.func_177952_p() + 1
                  )
                  .func_186662_g(2.0);
               moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
               if (moblist.isEmpty()) {
                  entityout.func_70012_b(x + randomx, y, z + randomz, this.field_70177_z, this.field_70125_A);
                  if (this.field_70170_p.func_184144_a(entityout, entityout.func_174813_aQ()).isEmpty()) {
                     entityout.func_180482_a(this.field_70170_p.func_175649_E(new BlockPos(entityout)), (IEntityLivingData)null);
                     entityout.func_70624_b(in);
                     this.setActualParasites(1);
                     this.addID(entityout.func_145782_y(), 1);
                     entityout.setBuried();
                     this.field_70170_p.func_72838_d(entityout);
                     this.field_70170_p.func_72960_a(entityout, (byte)50);
                     flag = false;
                     return true;
                  }

                  randomx = rand.nextInt(range) + mini;
                  randomz = rand.nextInt(range) + mini;
                  negative = rand.nextInt(2);
                  if (negative == 0.0) {
                     randomx *= -1.0;
                  }

                  negative = rand.nextInt(2);
                  if (negative == 0.0) {
                     randomz *= -1.0;
                  }

                  limit++;
                  continue;
               }
            }
         }

         randomx = rand.nextInt(range) + mini;
         randomz = rand.nextInt(range) + mini;
         negative = rand.nextInt(2);
         if (negative == 0.0) {
            randomx *= -1.0;
         }

         negative = rand.nextInt(2);
         if (negative == 0.0) {
            randomz *= -1.0;
         }

         limit++;
      }

      return false;
   }

   private EntityDodT getTentacle() {
      for (int i = 0; i < this.mobID.length; i++) {
         if (this.mobID[i] > 0 && this.field_70170_p.func_73045_a(this.mobID[i]) instanceof EntityDodT) {
            EntityDodT flag = (EntityDodT)this.field_70170_p.func_73045_a(this.mobID[i]);
            if (flag == null) {
               this.mobID[i] = -777;
               int negative = this.mobPT[i] * -1;
               this.setActualParasites(negative);
            } else if (flag.func_70089_S() && flag.getTele() == null && !flag.buried()) {
               return flag;
            }
         }
      }

      return null;
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
            playerIn.func_71023_q(-SRPConfig.pureExpSteal);
            if (playerIn.field_71106_cc < 0.0F) {
               playerIn.field_71068_ca--;
               playerIn.field_71106_cc = 1.0F;
            }
         } else {
            playerIn.func_71023_q(-SRPConfig.pureExpSteal);
            if (playerIn.field_71106_cc < 0.0F) {
               playerIn.field_71106_cc = 0.0F;
            }
         }
      }

      return flag;
   }
}
