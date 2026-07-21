package com.dhanantry.scapeandrunparasites.entity.ai;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIAttackSwell extends EntityAIBase {
   EntityParasiteBase swellingParasite;
   EntityLivingBase creeperAttackTarget;
   double distance;

   public EntityAIAttackSwell(EntityParasiteBase in) {
      this.swellingParasite = in;
      this.func_75248_a(1);
      this.distance = 9.0;
   }

   public EntityAIAttackSwell(EntityParasiteBase in, double dis) {
      this(in);
      this.distance = dis;
      this.func_75248_a(0);
   }

   public boolean func_75250_a() {
      EntityLivingBase entitylivingbase = this.swellingParasite.func_70638_az();
      return this.swellingParasite.getSelfeState() > 0 || entitylivingbase != null && this.swellingParasite.func_70068_e(entitylivingbase) < this.distance;
   }

   public void func_75249_e() {
      this.swellingParasite.func_70661_as().func_75499_g();
      this.creeperAttackTarget = this.swellingParasite.func_70638_az();
   }

   public void func_75251_c() {
      this.creeperAttackTarget = null;
   }

   public void func_75246_d() {
      if (this.creeperAttackTarget == null) {
         this.swellingParasite.setSelfeState(-1);
      } else if (this.swellingParasite.func_70068_e(this.creeperAttackTarget) > 49.0) {
         this.swellingParasite.setSelfeState(-1);
      } else if (!this.swellingParasite.func_70635_at().func_75522_a(this.creeperAttackTarget)) {
         this.swellingParasite.setSelfeState(-1);
      } else {
         this.swellingParasite.setSelfeState(1);
      }
   }
}
