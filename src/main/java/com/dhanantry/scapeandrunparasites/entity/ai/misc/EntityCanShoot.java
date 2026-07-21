package com.dhanantry.scapeandrunparasites.entity.ai.misc;

import net.minecraft.entity.Entity;

public interface EntityCanShoot {
   Entity getProj(double var1, double var3, double var5);

   void playProjSound();
}
