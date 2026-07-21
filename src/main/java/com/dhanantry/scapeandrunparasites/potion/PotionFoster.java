package com.dhanantry.scapeandrunparasites.potion;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPMalleable;
import net.minecraft.entity.EntityLivingBase;

public class PotionFoster extends SRPEffectBase {
   public PotionFoster(String name, boolean isBadEffectIn, int liquidColorIn, int IconIndexX, int IconIndexY) {
      super(name, isBadEffectIn, liquidColorIn, IconIndexX, IconIndexY);
   }

   @Override
   public void func_76394_a(EntityLivingBase entity, int amplifier) {
      if (!entity.field_70170_p.field_72995_K) {
         if (entity instanceof EntityPMalleable) {
            ((EntityPMalleable)entity).increaseAllResistances();
         }
      }
   }
}
