package com.dhanantry.scapeandrunparasites.potion;

import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;

public class PotionContamination extends SRPEffectBase {
   public PotionContamination(String name, boolean isBadEffectIn, int liquidColorIn, int IconIndexX, int IconIndexY) {
      super(name, isBadEffectIn, liquidColorIn, IconIndexX, IconIndexY);
   }

   @Override
   public void func_76394_a(EntityLivingBase entity, int amplifier) {
      if (!entity.field_70170_p.field_72995_K) {
         if (entity.field_70173_aa % 40 == 0) {
            if (entity.func_110143_aJ() > 1.0F) {
               entity.func_70097_a(DamageSource.field_76376_m, 1.0F);
            }

            int dur = entity.func_70660_b(SRPPotions.CONTA_E).func_76459_b();
            AxisAlignedBB axisalignedbb = new AxisAlignedBB(
                  entity.field_70165_t,
                  entity.field_70163_u,
                  entity.field_70161_v,
                  entity.field_70165_t + 1.0,
                  entity.field_70163_u + 1.0,
                  entity.field_70161_v + 1.0
               )
               .func_72314_b(4.0, 3.0, 4.0);

            for (EntityLivingBase mob : entity.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb)) {
               SRPPotions.applyStackPotion(SRPPotions.CONTA_E, mob, dur, amplifier);
            }
         }
      }
   }
}
