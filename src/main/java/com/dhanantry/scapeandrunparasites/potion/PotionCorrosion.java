package com.dhanantry.scapeandrunparasites.potion;

import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import java.util.ArrayList;
import java.util.Collections;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class PotionCorrosion extends SRPEffectBase {
   public PotionCorrosion(String name, boolean isBadEffectIn, int liquidColorIn, int IconIndexX, int IconIndexY) {
      super(name, isBadEffectIn, liquidColorIn, IconIndexX, IconIndexY);
   }

   @Override
   public void func_76394_a(EntityLivingBase entity, int amplifier) {
      if (!entity.field_70170_p.field_72995_K) {
         this.effectCorrosive(entity, amplifier);
      }
   }

   private void effectCorrosive(EntityLivingBase entity, int amplifier) {
      ArrayList<ItemStack> off = new ArrayList<>();
      Iterable<ItemStack> gear = entity.func_184193_aE();
      if (gear != null && !gear.equals(Collections.emptyList())) {
         for (ItemStack part : gear) {
            if (!part.func_190926_b() && part.func_77984_f()) {
               off.add(part);
            }
         }

         if (!off.isEmpty()) {
            for (ItemStack partx : off) {
               if (partx.func_77958_k() * SRPConfigSystems.corrNot < partx.func_77958_k() - partx.func_77952_i()) {
                  partx.func_77972_a(SRPConfigSystems.corroValue, entity);
               }
            }
         }
      }
   }
}
