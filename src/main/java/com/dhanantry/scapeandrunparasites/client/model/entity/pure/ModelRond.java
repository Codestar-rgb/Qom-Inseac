package com.dhanantry.scapeandrunparasites.client.model.entity.pure;

import com.dhanantry.scapeandrunparasites.client.model.ModelSRP;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import net.minecraft.entity.Entity;

public class ModelRond extends ModelSRP {
   public void func_78088_a(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      super.func_78088_a(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
   }

   public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
      EntityParasiteBase parasite = (EntityParasiteBase)entityIn;
      int i = parasite.getParasiteStatus();
      if (i == 0) {
         if (!parasite.getStillAni()) {
            float GS = 2.1F;
            float var11 = 0.3F;
         }
      } else if ((i == 1 || i == 2) && !parasite.getStillAni()) {
         float GS = 2.1F;
         float var13 = 0.3F;
      }
   }
}
