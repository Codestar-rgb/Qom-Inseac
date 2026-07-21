package com.dhanantry.scapeandrunparasites.entity.ai;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.MathHelper;

public class EntityAIAttackRangedStatus extends EntityAIBase {
   private final EntityParasiteBase entityHost;
   private final IRangedAttackMob rangedAttackEntityHost;
   private EntityLivingBase attackTarget;
   private int rangedAttackTime = -1;
   private final double entityMoveSpeed;
   private int seeTime;
   private final int maxRangedAttackTime;
   private final float attackRadius;
   private final float maxAttackDistance;
   private boolean pullAway;

   public EntityAIAttackRangedStatus(IRangedAttackMob attacker, double movespeed, int maxAttackTime, float maxAttackDistanceIn, boolean away) {
      if (!(attacker instanceof EntityLivingBase)) {
         throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
      } else {
         this.rangedAttackEntityHost = attacker;
         this.entityHost = (EntityParasiteBase)attacker;
         this.entityMoveSpeed = movespeed;
         this.maxRangedAttackTime = maxAttackTime;
         this.attackRadius = maxAttackDistanceIn;
         this.maxAttackDistance = maxAttackDistanceIn * maxAttackDistanceIn;
         this.pullAway = away;
         this.func_75248_a(3);
      }
   }

   public boolean func_75250_a() {
      if (!this.entityHost.field_70122_E) {
         return false;
      } else {
         EntityLivingBase entitylivingbase = this.entityHost.func_70638_az();
         if (entitylivingbase != null && !this.entityHost.shouldWorkTask()) {
            this.attackTarget = entitylivingbase;
            return true;
         } else {
            return false;
         }
      }
   }

   public boolean func_75253_b() {
      return (this.func_75250_a() || !this.entityHost.func_70661_as().func_75500_f()) && !this.entityHost.shouldWorkTask();
   }

   public void func_75251_c() {
   }

   public void func_75246_d() {
      if (this.entityHost.srpTicks == 10) {
         if (this.entityHost.getGeneMod(3)) {
            this.entityHost.setParasiteStatus(2);
         } else {
            this.entityHost.setParasiteStatus(1);
         }
      }

      if (this.attackTarget == null) {
         this.func_75251_c();
      } else if (!this.attackTarget.func_70089_S()) {
         this.func_75251_c();
      } else {
         double d0 = this.entityHost
            .func_70092_e(this.attackTarget.field_70165_t, this.attackTarget.func_174813_aQ().field_72338_b, this.attackTarget.field_70161_v);
         boolean flag = this.entityHost.func_70635_at().func_75522_a(this.attackTarget);
         boolean flag2 = d0 <= this.maxAttackDistance;
         if (flag) {
            this.seeTime++;
         } else {
            this.seeTime = 0;
         }

         if (this.entityHost.srpTicks == 10) {
            double follo = this.entityHost.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111126_e();
            if (d0 > follo * follo) {
               this.entityHost.func_70624_b(null);
               return;
            }
         }

         if (flag2 && this.seeTime >= 10) {
            this.entityHost.func_70661_as().func_75499_g();
         } else {
            this.entityHost.func_70661_as().func_75497_a(this.attackTarget, this.entityMoveSpeed);
         }

         if (d0 <= 25.0 && this.pullAway) {
            double deltaX = this.attackTarget.field_70165_t - this.entityHost.field_70165_t;
            double deltaY = this.attackTarget.field_70163_u - this.entityHost.field_70163_u;
            double deltaZ = this.attackTarget.field_70161_v - this.entityHost.field_70161_v;
            double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
            if (distance == 0.0) {
               return;
            }

            deltaX /= distance;
            deltaY /= distance;
            deltaZ /= distance;
            double strength = 0.08;
            this.entityHost.field_70159_w -= deltaX * strength;
            this.entityHost.field_70181_x -= deltaY * strength;
            this.entityHost.field_70179_y -= deltaZ * strength;
         }

         this.entityHost.func_70671_ap().func_75651_a(this.attackTarget, 30.0F, 30.0F);
         if (this.entityHost.func_70644_a(SRPPotions.RAGE_E)) {
            this.rangedAttackTime--;
         }

         if (--this.rangedAttackTime <= 0) {
            if (!flag) {
               return;
            }

            if (flag && flag2) {
               float f = MathHelper.func_76133_a(d0) / this.attackRadius;
               float lvt_5_1_ = MathHelper.func_76131_a(f, 0.1F, 1.0F);
               this.rangedAttackEntityHost.func_82196_d(this.attackTarget, lvt_5_1_);
               this.entityHost.resetIdleTime();
               this.rangedAttackTime = this.maxRangedAttackTime;
            }
         } else if (this.rangedAttackTime < 0) {
         }
      }
   }
}
