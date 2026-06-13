/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.util.ResourceLocation
 */
package com.dhanantry.scapeandrunparasites.bestiary.cap;

import com.dhanantry.scapeandrunparasites.bestiary.ParasiteTier;
import com.dhanantry.scapeandrunparasites.bestiary.cap.IBestiaryProgress;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ResourceLocation;

public class BestiaryProgress
implements IBestiaryProgress {
    private final Map<String, Integer> kills = new HashMap<String, Integer>();
    private final Set<String> seenMobs = new HashSet<String>();
    private final Set<String> seenTiers = new HashSet<String>();
    private float damageToParasites = 0.0f;
    private float damageFromParasites = 0.0f;
    private int deathsByParasites = 0;
    private final Set<String> seenBlocks = new HashSet<String>();
    private final Set<String> seenCelestials = new HashSet<String>();
    private final Set<String> seenEffects = new HashSet<String>();

    @Override
    public void clearStatsPageData() {
        this.kills.clear();
        this.damageToParasites = 0.0f;
        this.damageFromParasites = 0.0f;
        this.deathsByParasites = 0;
    }

    @Override
    public int getKills(String mobId) {
        return this.kills.getOrDefault(mobId, 0);
    }

    @Override
    public void addKill(String mobId, int amount) {
        if (amount <= 0) {
            return;
        }
        this.kills.merge(mobId, amount, Integer::sum);
        this.seenMobs.add(mobId);
    }

    @Override
    public void copyCombatStatsFrom(IBestiaryProgress other) {
        if (other == null) {
            return;
        }
        this.damageToParasites = other.getDamageToParasites();
        this.damageFromParasites = other.getDamageFromParasites();
        this.deathsByParasites = other.getDeathsByParasites();
    }

    @Override
    public boolean isMobSeen(String mobId) {
        return this.seenMobs.contains(mobId);
    }

    @Override
    public void markMobSeen(String mobId) {
        if (mobId != null) {
            this.seenMobs.add(mobId);
        }
    }

    @Override
    public boolean isTierSeen(ParasiteTier tier) {
        return this.seenTiers.contains(tier.name());
    }

    @Override
    public void markTierSeen(ParasiteTier tier) {
        if (tier != null) {
            this.seenTiers.add(tier.name());
        }
    }

    @Override
    public boolean hasSeenCelestial(String id) {
        return id != null && this.seenCelestials.contains(id);
    }

    @Override
    public void markCelestialSeen(String id) {
        if (id != null) {
            this.seenCelestials.add(id);
        }
    }

    @Override
    public void unlockAll() {
        for (ParasiteTier t : ParasiteTier.values()) {
            this.seenTiers.add(t.name());
        }
    }

    @Override
    public boolean hasSeenBlock(ResourceLocation id) {
        return id != null && this.seenBlocks.contains(id.toString());
    }

    @Override
    public void markBlockSeen(ResourceLocation id) {
        if (id != null) {
            this.seenBlocks.add(id.toString());
        }
    }

    @Override
    public Set<String> getSeenBlocks() {
        return this.seenBlocks;
    }

    @Override
    public void setSeenBlocks(Set<String> ids) {
        this.seenBlocks.clear();
        if (ids != null) {
            this.seenBlocks.addAll(ids);
        }
    }

    @Override
    public Set<String> getSeenCelestials() {
        return this.seenCelestials;
    }

    @Override
    public boolean hasSeenEffect(String id) {
        return id != null && this.seenEffects.contains(id);
    }

    @Override
    public void markEffectSeen(String id) {
        if (id != null) {
            this.seenEffects.add(id);
        }
    }

    @Override
    public Set<String> getSeenEffects() {
        return this.seenEffects;
    }

    @Override
    public float getDamageToParasites() {
        return this.damageToParasites;
    }

    @Override
    public float getDamageFromParasites() {
        return this.damageFromParasites;
    }

    @Override
    public int getDeathsByParasites() {
        return this.deathsByParasites;
    }

    @Override
    public void addDamageToParasites(float amount) {
        if (amount > 0.0f) {
            this.damageToParasites += amount;
        }
    }

    @Override
    public void addDamageFromParasites(float amount) {
        if (amount > 0.0f) {
            this.damageFromParasites += amount;
        }
    }

    @Override
    public void addDeathsByParasites(int amount) {
        if (amount > 0) {
            this.deathsByParasites += amount;
        }
    }

    @Override
    public void setDamageToParasites(float amount) {
        this.damageToParasites = Math.max(0.0f, amount);
    }

    @Override
    public void setDamageFromParasites(float amount) {
        this.damageFromParasites = Math.max(0.0f, amount);
    }

    @Override
    public void setDeathsByParasites(int amount) {
        this.deathsByParasites = Math.max(0, amount);
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound tag = new NBTTagCompound();
        NBTTagCompound k = new NBTTagCompound();
        for (Map.Entry<String, Integer> entry : this.kills.entrySet()) {
            k.func_74768_a(entry.getKey(), entry.getValue().intValue());
        }
        tag.func_74782_a("kills", (NBTBase)k);
        NBTTagList sm = new NBTTagList();
        for (String string : this.seenMobs) {
            sm.func_74742_a((NBTBase)new NBTTagString(string));
        }
        tag.func_74782_a("seenMobs", (NBTBase)sm);
        NBTTagList nBTTagList = new NBTTagList();
        for (String string : this.seenTiers) {
            nBTTagList.func_74742_a((NBTBase)new NBTTagString(string));
        }
        tag.func_74782_a("seenTiers", (NBTBase)nBTTagList);
        NBTTagList nBTTagList2 = new NBTTagList();
        for (String string : this.seenBlocks) {
            nBTTagList2.func_74742_a((NBTBase)new NBTTagString(string));
        }
        tag.func_74782_a("seenBlocks", (NBTBase)nBTTagList2);
        NBTTagList nBTTagList3 = new NBTTagList();
        for (String s : this.seenCelestials) {
            nBTTagList3.func_74742_a((NBTBase)new NBTTagString(s));
        }
        tag.func_74782_a("seenCelestials", (NBTBase)nBTTagList3);
        NBTTagList nBTTagList4 = new NBTTagList();
        for (String s : this.seenEffects) {
            nBTTagList4.func_74742_a((NBTBase)new NBTTagString(s));
        }
        tag.func_74782_a("seenEffects", (NBTBase)nBTTagList4);
        tag.func_74776_a("damageToParasites", this.damageToParasites);
        tag.func_74776_a("damageFromParasites", this.damageFromParasites);
        tag.func_74768_a("deathsByParasites", this.deathsByParasites);
        return tag;
    }

    @Override
    public void deserializeNBT(NBTTagCompound tag) {
        this.kills.clear();
        this.seenMobs.clear();
        this.seenTiers.clear();
        this.seenBlocks.clear();
        this.seenCelestials.clear();
        this.seenEffects.clear();
        this.damageToParasites = 0.0f;
        this.damageFromParasites = 0.0f;
        this.deathsByParasites = 0;
        NBTTagCompound k = tag.func_74775_l("kills");
        for (String key : k.func_150296_c()) {
            this.kills.put(key, k.func_74762_e(key));
        }
        NBTTagList sm = tag.func_150295_c("seenMobs", 8);
        for (int i = 0; i < sm.func_74745_c(); ++i) {
            this.seenMobs.add(sm.func_150307_f(i));
        }
        NBTTagList st = tag.func_150295_c("seenTiers", 8);
        for (int i = 0; i < st.func_74745_c(); ++i) {
            this.seenTiers.add(st.func_150307_f(i));
        }
        NBTTagList sb = tag.func_150295_c("seenBlocks", 8);
        for (int i = 0; i < sb.func_74745_c(); ++i) {
            this.seenBlocks.add(sb.func_150307_f(i));
        }
        NBTTagList sc = tag.func_150295_c("seenCelestials", 8);
        for (int i = 0; i < sc.func_74745_c(); ++i) {
            this.seenCelestials.add(sc.func_150307_f(i));
        }
        NBTTagList se = tag.func_150295_c("seenEffects", 8);
        for (int i = 0; i < se.func_74745_c(); ++i) {
            this.seenEffects.add(se.func_150307_f(i));
        }
        this.damageToParasites = tag.func_74760_g("damageToParasites");
        this.damageFromParasites = tag.func_74760_g("damageFromParasites");
        this.deathsByParasites = tag.func_74762_e("deathsByParasites");
    }
}

