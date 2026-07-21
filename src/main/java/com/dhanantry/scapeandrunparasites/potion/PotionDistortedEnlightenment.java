package com.dhanantry.scapeandrunparasites.potion;

import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;

public class PotionDistortedEnlightenment extends SRPEffectBase {
   public PotionDistortedEnlightenment(String name, boolean isBadEffectIn, int liquidColorIn, int iconIndexX, int iconIndexY) {
      super(name, isBadEffectIn, liquidColorIn, iconIndexX, iconIndexY);
   }

   @Override
   public boolean func_76397_a(int duration, int amplifier) {
      return true;
   }

   @Override
   public void func_76394_a(EntityLivingBase entity, int amplifier) {
      super.func_76394_a(entity, amplifier);
      if (!entity.field_70170_p.field_72995_K) {
         entity.func_184195_f(true);
      }
   }

   public void func_111187_a(EntityLivingBase entity, AbstractAttributeMap attributeMapIn, int amplifier) {
      super.func_111187_a(entity, attributeMapIn, amplifier);
      if (!entity.field_70170_p.field_72995_K && !entity.func_70644_a(SRPPotions.DISTORTED_ENLIGHTENMENT_E)) {
         entity.func_184195_f(false);
      }
   }
}
