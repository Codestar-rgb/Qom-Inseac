package com.dhanantry.scapeandrunparasites.bestiary.cap;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class BestiaryStorage implements IStorage<IBestiaryProgress> {
   public NBTBase writeNBT(Capability<IBestiaryProgress> cap, IBestiaryProgress inst, EnumFacing side) {
      return inst == null ? new NBTTagCompound() : inst.serializeNBT();
   }

   public void readNBT(Capability<IBestiaryProgress> cap, IBestiaryProgress inst, EnumFacing side, NBTBase nbt) {
      if (inst != null && nbt instanceof NBTTagCompound) {
         inst.deserializeNBT((NBTTagCompound)nbt);
      }
   }
}
