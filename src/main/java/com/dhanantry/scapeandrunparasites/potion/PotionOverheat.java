package com.dhanantry.scapeandrunparasites.potion;

import net.minecraft.entity.EntityLivingBase;

public class PotionOverheat extends SRPEffectBase {
   public PotionOverheat(String name, boolean isBadEffectIn, int liquidColorIn, int IconIndexX, int IconIndexY) {
      super(name, isBadEffectIn, liquidColorIn, IconIndexX, IconIndexY);
   }

   @Override
   public void func_76394_a(EntityLivingBase entity, int amplifier) {
      if (!entity.field_70170_p.field_72995_K) {
         if (entity.field_70173_aa % 20 == 0) {
            entity.func_70015_d(2);
         }
      }
   }
}
