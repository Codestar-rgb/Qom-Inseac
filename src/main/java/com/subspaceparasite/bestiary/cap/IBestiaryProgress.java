/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ResourceLocation
 */
package com.subspaceparasite.bestiary.cap;

import com.subspaceparasite.bestiary.ParasiteTier;
import java.util.Set;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public interface IBestiaryProgress {
    public int getKills(String var1);

    public void addKill(String var1, int var2);

    public boolean isMobSeen(String var1);

    public void markMobSeen(String var1);

    public boolean isTierSeen(ParasiteTier var1);

    public void markTierSeen(ParasiteTier var1);

    public boolean hasSeenCelestial(String var1);

    public void markCelestialSeen(String var1);

    public void unlockAll();

    public NBTTagCompound serializeNBT();

    public void deserializeNBT(NBTTagCompound var1);

    public boolean hasSeenBlock(ResourceLocation var1);

    public void markBlockSeen(ResourceLocation var1);

    public Set<String> getSeenBlocks();

    public Set<String> getSeenCelestials();

    public void setSeenBlocks(Set<String> var1);

    public boolean hasSeenEffect(String var1);

    public void markEffectSeen(String var1);

    public Set<String> getSeenEffects();

    public float getDamageToParasites();

    public float getDamageFromParasites();

    public int getDeathsByParasites();

    public void clearStatsPageData();

    public void addDamageToParasites(float var1);

    public void addDamageFromParasites(float var1);

    public void addDeathsByParasites(int var1);

    public void copyCombatStatsFrom(IBestiaryProgress var1);

    public void setDamageToParasites(float var1);

    public void setDamageFromParasites(float var1);

    public void setDeathsByParasites(int var1);
}

