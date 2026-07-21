package com.dhanantry.scapeandrunparasites.bestiary.cap;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public final class BestiaryCapability {
   @CapabilityInject(IBestiaryProgress.class)
   public static final Capability<IBestiaryProgress> CAP = null;

   private BestiaryCapability() {
   }

   public static void register() {
      CapabilityManager.INSTANCE.register(IBestiaryProgress.class, new BestiaryCapability.Storage(), BestiaryProgress::new);
   }

   public static class Storage implements IStorage<IBestiaryProgress> {
      public NBTBase writeNBT(Capability<IBestiaryProgress> cap, IBestiaryProgress inst, EnumFacing side) {
         return inst.serializeNBT();
      }

      public void readNBT(Capability<IBestiaryProgress> cap, IBestiaryProgress inst, EnumFacing side, NBTBase nbt) {
         if (nbt instanceof NBTTagCompound) {
            inst.deserializeNBT((NBTTagCompound)nbt);
         }
      }
   }
}
