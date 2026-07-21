package com.dhanantry.scapeandrunparasites.entity.ai.misc;

import net.minecraft.entity.EntityLivingBase;

public interface EntityCanPullMobs {
   boolean hasTargetedEntity();

   void setTargetedEntity(int var1);

   EntityLivingBase getTargetedEntity();

   boolean checkAttackTarget(EntityLivingBase var1);

   void setPStatus(int var1);

   void setPullingMobEffects(EntityLivingBase var1);

   void resetPullSkill();

   int getAcceleration();
}
