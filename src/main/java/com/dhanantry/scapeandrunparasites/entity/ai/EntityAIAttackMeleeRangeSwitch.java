package com.dhanantry.scapeandrunparasites.entity.ai;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIAttackMeleeRangeSwitch extends EntityAIBase {
   private EntityParasiteBase parent;
   private float distance;
   private boolean generation;

   public EntityAIAttackMeleeRangeSwitch(EntityParasiteBase in, float meleeDistance) {
      this.parent = in;
      this.distance = meleeDistance * meleeDistance;
      this.generation = false;
   }

   public EntityAIAttackMeleeRangeSwitch(EntityParasiteBase in, float meleeDistance, boolean checkGen) {
      this.parent = in;
      this.distance = meleeDistance * meleeDistance;
      this.generation = checkGen;
   }

   public boolean func_75250_a() {
      if (this.parent.srpTicks >= 10) {
         return false;
      } else if (!this.generation) {
         if (this.parent.func_70638_az() != null) {
            return true;
         } else {
            this.parent.setWorkTask(true);
            return false;
         }
      } else {
         return this.parent.func_70638_az() != null && this.parent.getGeneMod(5);
      }
   }

   public void func_75246_d() {
      if (this.parent.func_70638_az() != null) {
         EntityLivingBase entitylivingbase = this.parent.func_70638_az();
         if (entitylivingbase.func_70068_e(this.parent) < this.distance && this.parent.func_70685_l(entitylivingbase)) {
            this.parent.setWorkTask(true);
         } else {
            this.parent.setWorkTask(false);
         }
      }
   }
}
