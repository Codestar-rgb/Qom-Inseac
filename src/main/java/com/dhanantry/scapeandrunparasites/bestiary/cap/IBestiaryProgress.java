package com.dhanantry.scapeandrunparasites.bestiary.cap;

import com.dhanantry.scapeandrunparasites.bestiary.ParasiteTier;
import java.util.Set;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public interface IBestiaryProgress {
   int getKills(String var1);

   void addKill(String var1, int var2);

   boolean isMobSeen(String var1);

   void markMobSeen(String var1);

   boolean isTierSeen(ParasiteTier var1);

   void markTierSeen(ParasiteTier var1);

   boolean hasSeenCelestial(String var1);

   void markCelestialSeen(String var1);

   void unlockAll();

   NBTTagCompound serializeNBT();

   void deserializeNBT(NBTTagCompound var1);

   boolean hasSeenBlock(ResourceLocation var1);

   void markBlockSeen(ResourceLocation var1);

   Set<String> getSeenBlocks();

   Set<String> getSeenCelestials();

   void setSeenBlocks(Set<String> var1);

   boolean hasSeenEffect(String var1);

   void markEffectSeen(String var1);

   Set<String> getSeenEffects();

   float getDamageToParasites();

   float getDamageFromParasites();

   int getDeathsByParasites();

   void clearStatsPageData();

   void addDamageToParasites(float var1);

   void addDamageFromParasites(float var1);

   void addDeathsByParasites(int var1);

   void copyCombatStatsFrom(IBestiaryProgress var1);

   void setDamageToParasites(float var1);

   void setDamageFromParasites(float var1);

   void setDeathsByParasites(int var1);
}
