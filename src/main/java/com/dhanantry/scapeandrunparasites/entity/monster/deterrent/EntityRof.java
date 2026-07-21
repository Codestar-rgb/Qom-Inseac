package com.dhanantry.scapeandrunparasites.entity.monster.deterrent;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPStationary;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityRof extends EntityPStationary {
   private String[] mobL;
   public EntityLivingBase targetScent;
   public int minmob;
   public int maxmob;
   private float attackTimer;
   private boolean upT;

   public EntityRof(World worldIn) {
      super(worldIn);
      this.func_70105_a(1.5F, 4.6F);
      this.field_70728_aV = 0;
      this.type = 40;
      this.buriedT = 5.7;
      this.damageCap = SRPConfig.turretCap;
      this.pointCap = SRPConfig.turretPointCap;
      this.pointReduction = SRPConfig.turretPointRed;
      this.chanceLearn = SRPConfig.turretChanceLe;
      this.chanceLearnFire = SRPConfig.turretChanceLeFire;
      this.DamageTypeCap = SRPConfig.turretPointDamCap;
      this.MiniDamage = SRPConfig.turretMinDamage;
      this.regen = SRPConfig.turretRegen;
      this.valueEvDeath = 0;
      this.delayBuried = 40;
   }

   @Override
   public int getParasiteIDRegister() {
      return 308;
   }

   protected void func_184651_r() {
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(100.0);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(100.0);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(16.0);
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      if (this.upT) {
         this.attackTimer += 0.2F;
         if (this.attackTimer > 3.0F) {
            this.upT = false;
         }
      } else {
         this.attackTimer -= 0.1F;
      }

      if (!this.field_70170_p.field_72995_K) {
         if (this.func_70638_az() != null && this.func_70638_az().func_70068_e(this) > 256.0) {
            this.field_70170_p.func_72960_a(this, (byte)51);
            this.up = true;
         }

         if (this.field_70173_aa > 160 && this.targetScent == null) {
            this.field_70170_p.func_72960_a(this, (byte)51);
            this.up = true;
         }

         if (this.field_70173_aa == 100) {
            this.upT = true;
            this.attackTimer = 0.0F;
            this.field_70170_p.func_72960_a(this, (byte)112);
         }

         if (!this.up && this.field_70173_aa > 120 && !this.buried() && this.targetScent != null) {
            for (int i = 0; i <= this.maxmob; i++) {
               this.spawnWaves();
               if (i >= this.minmob && this.field_70146_Z.nextInt(3) == 0) {
                  this.targetScent = null;
               }
            }

            this.func_184185_a(SRPSounds.ROF_SPIT_OUT, 1.2F, this.func_70647_i());
            this.targetScent = null;
         }
      }
   }

   @Override
   protected void retreat(boolean dead) {
      if (this.up) {
         this.func_184185_a(SRPSounds.ROF_EMERGE, 1.0F, this.func_70647_i() - 0.1F);
         this.setParasiteStatus(3);
         this.buried = this.buried + this.getBuriedSpeed();
         if (this.buried > this.buriedT + 0.7 && !this.field_70170_p.field_72995_K) {
            this.func_70106_y();
         }
      }
   }

   @Override
   public double getBuriedSpeed() {
      return 0.12;
   }

   public void setMob(String[] in) {
      this.mobL = in;
   }

   private EntityLiving getMobFromList() {
      String mob = this.mobL[this.field_70146_Z.nextInt(this.mobL.length)];
      return (EntityLiving)EntityList.func_188429_b(new ResourceLocation(mob), this.field_70170_p);
   }

   public int spawnWaves() {
      if (this.mobL == null) {
         return 0;
      } else {
         EntityLiving out = this.getMobFromList();
         if (out == null) {
            return 0;
         } else {
            out.func_180482_a(this.field_70170_p.func_175649_E(new BlockPos(out)), null);
            out.func_70624_b(this.func_70638_az());
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
            d4 = d4 * d7 * 6.0;
            d5 *= d7;
            out.func_82149_j(this);
            out.field_70163_u = this.field_70163_u + this.field_70131_O + 0.5;
            this.setMotion(out, d3, d4, d5, 0.03, 1.2);
            this.field_70170_p.func_72838_d(out);
            out.func_70690_d(new PotionEffect(SRPPotions.RAGE_E, 1200, 1, false, false));
            out.func_70690_d(new PotionEffect(MobEffects.field_76444_x, 100, 15, false, false));
            return 1;
         }
      }
   }

   public void setMotion(EntityLivingBase in, double xSpeedIn, double ySpeedIn, double zSpeedIn, double capX, double capY) {
      xSpeedIn = Math.min(xSpeedIn, capX);
      ySpeedIn = Math.min(ySpeedIn, capY);
      zSpeedIn = Math.min(zSpeedIn, capX);
      in.field_70159_w = xSpeedIn * (Math.random() * 2.0 - 1.0);
      in.field_70181_x = ySpeedIn;
      in.field_70179_y = zSpeedIn * (Math.random() * 2.0 - 1.0);
   }

   public float func_70047_e() {
      return 1.8F;
   }

   @SideOnly(Side.CLIENT)
   public float getAttackTimer() {
      return this.attackTimer;
   }

   public void setminMax(int minn, int maxx) {
      this.minmob = minn;
      this.maxmob = maxx;
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void func_70103_a(byte id) {
      if (id == 112) {
         this.upT = true;
         this.attackTimer = 0.0F;
      } else {
         super.func_70103_a(id);
      }
   }
}
