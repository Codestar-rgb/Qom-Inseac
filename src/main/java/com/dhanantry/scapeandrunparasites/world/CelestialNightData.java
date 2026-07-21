package com.dhanantry.scapeandrunparasites.world;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

public class CelestialNightData extends WorldSavedData {
   public static final String DATA_NAME = "srp_celestial_night";
   public long darkDaysEndTime = -1L;
   public boolean darkDaysEndingSoundPlayed = false;
   private final Map<Integer, CelestialNightData.DimState> states = new HashMap<>();

   public CelestialNightData() {
      super("srp_celestial_night");
   }

   public CelestialNightData(String name) {
      super(name);
   }

   public static CelestialNightData get(World world) {
      MapStorage storage = world.func_175693_T();
      CelestialNightData data = (CelestialNightData)storage.func_75742_a(CelestialNightData.class, "srp_celestial_night");
      if (data == null) {
         data = new CelestialNightData("srp_celestial_night");
         storage.func_75745_a("srp_celestial_night", data);
      }

      return data;
   }

   public CelestialNightData.DimState getOrCreate(int dim) {
      CelestialNightData.DimState s = this.states.get(dim);
      if (s == null) {
         s = new CelestialNightData.DimState();
         this.states.put(dim, s);
      }

      return s;
   }

   public CelestialNightData.DimState getState(int dim) {
      return this.states.get(dim);
   }

   public void func_76184_a(NBTTagCompound nbt) {
      this.states.clear();
      NBTTagList list = nbt.func_150295_c("dims", 10);

      for (int i = 0; i < list.func_74745_c(); i++) {
         NBTTagCompound d = list.func_150305_b(i);
         int dim = d.func_74762_e("dim");
         CelestialNightData.DimState s = new CelestialNightData.DimState();
         s.nightIndex = d.func_74763_f("night");
         NBTTagList active = d.func_150295_c("active", 8);

         for (int j = 0; j < active.func_74745_c(); j++) {
            s.active.add(active.func_150307_f(j));
         }

         NBTTagList forced = d.func_150295_c("forced", 8);

         for (int j = 0; j < forced.func_74745_c(); j++) {
            s.forced.add(forced.func_150307_f(j));
         }

         this.states.put(dim, s);
      }
   }

   public NBTTagCompound func_189551_b(NBTTagCompound nbt) {
      NBTTagList list = new NBTTagList();

      for (Entry<Integer, CelestialNightData.DimState> e : this.states.entrySet()) {
         NBTTagCompound d = new NBTTagCompound();
         d.func_74768_a("dim", e.getKey());
         CelestialNightData.DimState s = e.getValue();
         d.func_74772_a("night", s.nightIndex);
         NBTTagList active = new NBTTagList();

         for (String id : s.active) {
            active.func_74742_a(new NBTTagString(id));
         }

         d.func_74782_a("active", active);
         NBTTagList forced = new NBTTagList();

         for (String id : s.forced) {
            forced.func_74742_a(new NBTTagString(id));
         }

         d.func_74782_a("forced", forced);
         list.func_74742_a(d);
      }

      nbt.func_74782_a("dims", list);
      return nbt;
   }

   public static class DimState {
      public long nightIndex = -1L;
      public final Set<String> active = new HashSet<>();
      public final Set<String> forced = new HashSet<>();
   }
}
