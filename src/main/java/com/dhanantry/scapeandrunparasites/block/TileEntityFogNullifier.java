package com.dhanantry.scapeandrunparasites.block;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityFogNullifier extends TileEntity {
   private int usesRemaining = 0;

   public int getUsesRemaining() {
      return this.usesRemaining;
   }

   public void setUsesRemaining(int uses) {
      this.usesRemaining = Math.max(0, uses);
   }

   public NBTTagCompound func_189515_b(NBTTagCompound compound) {
      super.func_189515_b(compound);
      compound.func_74768_a("UsesRemaining", this.usesRemaining);
      return compound;
   }

   public void func_145839_a(NBTTagCompound compound) {
      super.func_145839_a(compound);
      this.usesRemaining = compound.func_74762_e("UsesRemaining");
   }
}
