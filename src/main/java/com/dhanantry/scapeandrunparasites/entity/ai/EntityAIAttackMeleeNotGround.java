package com.dhanantry.scapeandrunparasites.entity.ai;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCutomAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.MathHelper;

public class EntityAIAttackMeleeNotGround extends EntityAIBase {
   protected EntityParasiteBase attacker;
   protected int attackTick;
   private double attack;
   private double track;
   private int cooldown;
   private int currentCD;
   private double speed;
   private boolean needAir;
   private int off;

   public EntityAIAttackMeleeNotGround(EntityParasiteBase creature, double attackD, double tracking, double spee, boolean air) {
      this.attacker = creature;
      this.attack = attackD * attackD;
      this.track = tracking * tracking;
      this.cooldown = 0;
      this.currentCD = 80;
      this.speed = spee;
      this.needAir = air;
      this.off = 4;
   }

   public EntityAIAttackMeleeNotGround(EntityParasiteBase creature, double attackD, double tracking, double spee, boolean air, int cd, int offs) {
      this(creature, attackD, tracking, spee, air);
      this.currentCD = cd;
      this.off = offs;
   }

   public boolean func_75250_a() {
      return this.attacker.func_70638_az() != null;
   }

   public void func_75246_d() {
      EntityLivingBase entitylivingbase = this.attacker.func_70638_az();
      if (entitylivingbase != null) {
         if (!entitylivingbase.func_70089_S()) {
            this.attacker.func_70624_b(null);
         } else {
            if (this.needAir) {
               if (this.attacker.func_70090_H()) {
                  return;
               }
            } else if (!this.attacker.func_70090_H()) {
               return;
            }

            this.cooldown++;
            if (this.cooldown < this.currentCD) {
               return;
            }

            this.attacker.func_70671_ap().func_75651_a(entitylivingbase, 30.0F, 30.0F);
            double d0 = this.attacker
               .func_70092_e(entitylivingbase.field_70165_t, entitylivingbase.func_174813_aQ().field_72338_b, entitylivingbase.field_70161_v);
            if (this.attacker.func_70068_e(entitylivingbase) * 0.85 < this.track && this.attacker.func_70685_l(entitylivingbase)) {
               double dd0 = entitylivingbase.field_70165_t - this.attacker.field_70165_t;
               double dd1 = entitylivingbase.field_70161_v - this.attacker.field_70161_v;
               int yy = entitylivingbase.field_70163_u >= this.attacker.field_70163_u + this.off ? 1 : -1;
               float f = MathHelper.func_76133_a(dd0 * dd0 + dd1 * dd1);
               this.attacker.field_70159_w = this.attacker.field_70159_w + (dd0 / f * this.speed + this.attacker.field_70159_w * this.speed);
               this.attacker.field_70179_y = this.attacker.field_70179_y + (dd1 / f * this.speed + this.attacker.field_70179_y * this.speed);
               this.attacker.field_70181_x = yy == 1 ? 0.52 * yy : 0.2 * yy;
               this.attackTick = Math.max(this.attackTick - 1, 0);
               this.checkAndPerformAttack(entitylivingbase, d0);
               if (!this.needAir && entitylivingbase.field_70163_u >= this.attacker.field_70163_u + 1.0) {
                  this.attacker.field_70181_x -= 0.2;
               }

               if (this.cooldown > 140) {
                  this.cooldown = 0;
               }
            }
         }
      }
   }

   protected void checkAndPerformAttack(EntityLivingBase target, double distance) {
      if (distance <= this.attack && this.attackTick <= 0) {
         this.attackTick = 20;
         EntityCutomAttack thisA = (EntityCutomAttack)this.attacker;
         thisA.attackEntityAsMobAOE(target);
      }
   }
}
