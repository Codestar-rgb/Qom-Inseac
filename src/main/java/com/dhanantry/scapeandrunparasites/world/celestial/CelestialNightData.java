/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.world.World
 *  net.minecraft.world.storage.MapStorage
 *  net.minecraft.world.storage.WorldSavedData
 */
package com.dhanantry.scapeandrunparasites.world.celestial;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

public class CelestialNightData
extends WorldSavedData {
    public static final String DATA_NAME = "srp_celestial_night";
    private final Map<Integer, DimState> states = new HashMap<Integer, DimState>();

    public CelestialNightData() {
        super(DATA_NAME);
    }

    public CelestialNightData(String name) {
        super(name);
    }

    public static CelestialNightData get(World world) {
        MapStorage storage = world.func_175693_T();
        CelestialNightData data = (CelestialNightData)storage.func_75742_a(CelestialNightData.class, DATA_NAME);
        if (data == null) {
            data = new CelestialNightData(DATA_NAME);
            storage.func_75745_a(DATA_NAME, (WorldSavedData)data);
        }
        return data;
    }

    public DimState getOrCreate(int dim) {
        DimState s = this.states.get(dim);
        if (s == null) {
            s = new DimState();
            this.states.put(dim, s);
        }
        return s;
    }

    public DimState getState(int dim) {
        return this.states.get(dim);
    }

    public void func_76184_a(NBTTagCompound nbt) {
        this.states.clear();
        NBTTagList list = nbt.func_150295_c("dims", 10);
        for (int i = 0; i < list.func_74745_c(); ++i) {
            NBTTagCompound d = list.func_150305_b(i);
            int dim = d.func_74762_e("dim");
            DimState s = new DimState();
            s.nightIndex = d.func_74763_f("night");
            s.phase = d.func_74762_e("phase");
            NBTTagList active = d.func_150295_c("active", 8);
            for (int j = 0; j < active.func_74745_c(); ++j) {
                s.active.add(active.func_150307_f(j));
            }
            NBTTagList forced = d.func_150295_c("forced", 8);
            for (int j = 0; j < forced.func_74745_c(); ++j) {
                s.forced.add(forced.func_150307_f(j));
            }
            this.states.put(dim, s);
        }
    }

    public NBTTagCompound func_189551_b(NBTTagCompound nbt) {
        NBTTagList list = new NBTTagList();
        for (Map.Entry<Integer, DimState> e : this.states.entrySet()) {
            NBTTagCompound d = new NBTTagCompound();
            d.func_74768_a("dim", e.getKey().intValue());
            DimState s = e.getValue();
            d.func_74772_a("night", s.nightIndex);
            d.func_74768_a("phase", s.phase);
            NBTTagList active = new NBTTagList();
            for (String id : s.active) {
                active.func_74742_a((NBTBase)new NBTTagString(id));
            }
            d.func_74782_a("active", (NBTBase)active);
            NBTTagList forced = new NBTTagList();
            for (String id : s.forced) {
                forced.func_74742_a((NBTBase)new NBTTagString(id));
            }
            d.func_74782_a("forced", (NBTBase)forced);
            list.func_74742_a((NBTBase)d);
        }
        nbt.func_74782_a("dims", (NBTBase)list);
        return nbt;
    }

    public static class DimState {
        public long nightIndex = -1L;
        public int phase = 0;
        public final Set<String> active = new HashSet<String>();
        public final Set<String> forced = new HashSet<String>();
    }
}

