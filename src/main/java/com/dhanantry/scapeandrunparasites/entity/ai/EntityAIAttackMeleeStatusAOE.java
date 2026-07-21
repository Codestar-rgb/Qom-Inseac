package com.dhanantry.scapeandrunparasites.entity.ai;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCutomAttack;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import net.minecraft.entity.EntityLivingBase;

public class EntityAIAttackMeleeStatusAOE extends EntityAIAttackMeleeStatus {
   private double attack;
   private boolean ignoreM;

   public EntityAIAttackMeleeStatusAOE(EntityParasiteBase creature, double speedIn, boolean useLongMemory, double runningD, double attackD) {
      super(creature, speedIn, useLongMemory, runningD);
      this.attack = attackD * attackD;
      this.ignoreM = false;
   }

   public EntityAIAttackMeleeStatusAOE(EntityParasiteBase creature, double speedIn, boolean useLongMemory, double runningD, double attackD, boolean IM) {
      super(creature, speedIn, useLongMemory, runningD);
      this.attack = attackD * attackD;
      this.ignoreM = IM;
   }

   @Override
   protected void checkAndPerformAttack(EntityLivingBase target, double distance) {
      if (!this.isTargetParasite(target)) {
         if (distance <= this.attack && this.attacker.func_70685_l(target)) {
            if (!this.ignoreM) {
               this.attacker.func_70661_as().func_75497_a(target, 0.0);
            }

            if (this.attackTick <= 0) {
               this.attackTick = this.attacker.getAttackSpeed();
               EntityCutomAttack thisA = (EntityCutomAttack)this.attacker;
               thisA.attackEntityAsMobAOE(target);
            }
         }
      }
   }
}
