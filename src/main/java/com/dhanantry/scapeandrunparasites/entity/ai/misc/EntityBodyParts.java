package com.dhanantry.scapeandrunparasites.entity.ai.misc;

import net.minecraft.util.DamageSource;

public interface EntityBodyParts {
   boolean attackEntityBodyFrom(DamageSource var1, float var2, int var3, boolean var4);

   void setBodyPartDead(int var1);
}
