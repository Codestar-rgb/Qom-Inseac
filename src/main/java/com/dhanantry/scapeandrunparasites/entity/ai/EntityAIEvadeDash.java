package com.dhanantry.scapeandrunparasites.entity.ai;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.MathHelper;

public class EntityAIEvadeDash extends EntityAIBase {
   private final EntityParasiteBase parent;
   private int eCooldown;
   private int cooldown;
   private int eDuration;
   private int tracker;
   private boolean inC;
   private int blockDistance;
   private double dash;
   private int maxDis;

   public EntityAIEvadeDash(EntityParasiteBase in, int cooldown, int duration, int distance) {
      this.parent = in;
      this.tracker = 0;
      this.eCooldown = cooldown;
      this.cooldown = cooldown + 1;
      this.eDuration = duration;
      this.blockDistance = distance * distance;
      this.inC = false;
      this.dash = 3.0;
      this.maxDis = 225;
   }

   public EntityAIEvadeDash(EntityParasiteBase in, int cooldown, int duration, int distance, double dash, int maxD) {
      this(in, cooldown, duration, distance);
      this.dash = dash;
      this.maxDis = maxD * maxD;
   }

   public boolean func_75250_a() {
      return (this.parent.func_70638_az() != null || this.inC)
         && this.parent.getParasiteStatus() > 0
         && this.parent.getParasiteStatus() < 3
         && this.parent.field_70122_E;
   }

   public void func_75251_c() {
      this.cooldown = 0;
      this.tracker = 0;
      this.inC = false;
      this.parent.field_70702_br = 0.0F;
   }

   public void func_75246_d() {
      EntityLivingBase target = this.parent.func_70638_az();
      if (target != null || this.inC) {
         if (this.inC) {
            this.tracker++;
            if (this.tracker >= this.eDuration) {
               this.parent.field_70702_br = 0.0F;
               this.tracker = 0;
               this.cooldown = 0;
               this.inC = false;
            }
         } else {
            if (this.parent.func_70068_e(target) > this.blockDistance
               && this.parent.func_70685_l(target)
               && this.parent.func_70068_e(target) < this.maxDis
               && this.cooldown < this.eCooldown) {
               this.cooldown++;
            }

            if (this.cooldown >= this.eCooldown) {
               this.parent.particleStatus((byte)10);
               double dd0 = target.field_70165_t - this.parent.field_70165_t;
               double dd1 = target.field_70161_v - this.parent.field_70161_v;
               float f = MathHelper.func_76133_a(dd0 * dd0 + dd1 * dd1);
               double i = 0.0;
               double j = 0.0;
               if (this.parent.field_70170_p.field_73012_v.nextBoolean()) {
                  i = this.dash;
               } else {
                  j = this.dash;
               }

               this.parent.field_70159_w = this.parent.field_70159_w + (dd0 / f * this.dash * 0.8F + this.parent.field_70159_w * 0.2F + i);
               this.parent.field_70179_y = this.parent.field_70179_y + (dd1 / f * this.dash * 0.8F + this.parent.field_70179_y * 0.2F + j);
               this.parent.func_70661_as().func_75499_g();
               this.func_75251_c();
            }
         }
      }
   }
}
