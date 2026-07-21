package com.dhanantry.scapeandrunparasites.entity.ai;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.MobEffects;
import net.minecraft.util.math.MathHelper;

public class EntityAIEvade extends EntityAIBase {
   private final EntityParasiteBase parent;
   private int eCooldown;
   private int cooldown;
   private int eDuration;
   private int tracker;
   private boolean inC;
   private double blockDistance;
   private boolean useDis;
   private int disValue;
   private boolean ignoreGene;

   public EntityAIEvade(EntityParasiteBase in, int cooldown, int duration, double distance) {
      this.parent = in;
      this.tracker = 0;
      this.eCooldown = cooldown;
      this.cooldown = cooldown + 1;
      this.eDuration = duration;
      this.blockDistance = distance * distance;
      this.inC = false;
   }

   public EntityAIEvade(EntityParasiteBase in, int cooldown, int duration, double distance, boolean use, int diss, boolean ignoreGen) {
      this(in, cooldown, duration, distance);
      this.useDis = use;
      this.disValue = diss;
      this.ignoreGene = ignoreGen;
   }

   public boolean func_75250_a() {
      return this.ignoreGene
         ? (this.parent.func_70638_az() != null || this.inC)
               && this.parent.getParasiteStatus() > 0
               && this.parent.getParasiteStatus() < 3
               && this.parent.field_70122_E
            || this.inC
         : this.parent.func_70638_az() != null
               && this.parent.getParasiteStatus() > 0
               && this.parent.getParasiteStatus() < 3
               && this.parent.field_70122_E
               && this.parent.getGeneMod(5)
            || this.inC;
   }

   public void func_75251_c() {
      this.cooldown = 0;
      this.tracker = 0;
      this.inC = false;
      this.parent.field_70702_br = 0.0F;
   }

   public void func_75246_d() {
      EntityLivingBase target = this.parent.func_70638_az();
      if ((target != null || this.inC) && !this.parent.func_70644_a(MobEffects.field_76421_d)) {
         if (this.inC && !this.useDis) {
            this.tracker++;
            if (this.tracker >= this.eDuration) {
               this.parent.field_70702_br = 0.0F;
               this.tracker = 0;
               this.cooldown = 0;
               this.inC = false;
            }
         } else {
            boolean flag = this.useDis ? this.parent.func_70068_e(target) > this.disValue * this.disValue : this.parent.func_70068_e(target) < 225.0;
            if (this.parent.func_70068_e(target) > this.blockDistance && this.parent.func_70685_l(target) && flag) {
               this.cooldown++;
            }

            if (this.cooldown >= this.eCooldown) {
               this.parent.lookAt(target);
               if (this.useDis) {
                  this.jumpTowardsTarget(target);
                  this.parent.func_70661_as().func_75499_g();
                  return;
               }

               if (this.parent.field_70702_br == 0.0F) {
                  int i = this.parent.func_70681_au().nextInt(2) == 0 ? 1 : -1;
                  this.parent.field_70702_br = i;
                  this.inC = true;
                  double dd0 = target.field_70165_t - this.parent.field_70165_t;
                  double dd1 = target.field_70161_v - this.parent.field_70161_v;
                  float f = MathHelper.func_76133_a(dd0 * dd0 + dd1 * dd1);
                  this.parent.field_70159_w = this.parent.field_70159_w + (dd0 / f * 1.77 * 0.8F + this.parent.field_70159_w * 0.2F);
                  this.parent.field_70179_y = this.parent.field_70179_y + (dd1 / f * 1.77 * 0.8F + this.parent.field_70179_y * 0.2F);
                  this.parent.field_70181_x = 0.2 + this.parent.field_70131_O * 0.1;
                  this.parent.func_70661_as().func_75499_g();
               }
            }
         }
      }
   }

   private void jumpTowardsTarget(Entity target) {
      double dx = target.field_70165_t - this.parent.field_70165_t;
      double dz = target.field_70161_v - this.parent.field_70161_v;
      double distance = Math.sqrt(dx * dx + dz * dz);
      if (!(distance < this.eDuration)) {
         double angle = Math.toRadians(45.0);
         double gravity = 0.8;
         double velocity = Math.sqrt(distance * gravity / Math.sin(2.0 * angle));
         velocity = Math.min(velocity, this.blockDistance);
         double horizontalVelocity = velocity * 1.9 * Math.cos(angle);
         double verticalVelocity = velocity * 0.6 * Math.sin(angle);
         this.parent.field_70159_w = horizontalVelocity * (dx / distance);
         this.parent.field_70179_y = horizontalVelocity * (dz / distance);
         this.parent.field_70181_x = verticalVelocity;
      }
   }
}
