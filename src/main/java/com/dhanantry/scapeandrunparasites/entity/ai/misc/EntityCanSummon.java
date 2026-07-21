package com.dhanantry.scapeandrunparasites.entity.ai.misc;

public interface EntityCanSummon {
   void addID(int var1, int var2);

   int IDable();

   void checkID();

   int[] getIDList();

   int[] getPointList();

   int getTotalParasites();

   int getActualParasites();

   void setActualParasites(int var1);
}
