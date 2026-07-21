package com.dhanantry.scapeandrunparasites.entity.ai.misc;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface EntityCanMelt {
   void melt();

   boolean isMelting();

   void melting();

   float getTHeigh();

   void setTHeigh(float var1);

   float getaSize();

   void setaSize(float var1);

   @SideOnly(Side.CLIENT)
   float getSelfeFlashIntensity2();
}
