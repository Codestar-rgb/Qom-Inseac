/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.EnumFacing
 *  net.minecraftforge.common.capabilities.Capability
 *  net.minecraftforge.common.capabilities.ICapabilitySerializable
 */
package com.dhanantry.scapeandrunparasites.bestiary.cap;

import com.dhanantry.scapeandrunparasites.bestiary.cap.BestiaryCapability;
import com.dhanantry.scapeandrunparasites.bestiary.cap.BestiaryProgress;
import com.dhanantry.scapeandrunparasites.bestiary.cap.IBestiaryProgress;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class BestiaryProvider
implements ICapabilitySerializable<NBTTagCompound> {
    private final IBestiaryProgress impl = new BestiaryProgress();

    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == BestiaryCapability.CAP;
    }

    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return (T)(capability == BestiaryCapability.CAP ? this.impl : null);
    }

    public NBTTagCompound serializeNBT() {
        return this.impl.serializeNBT();
    }

    public void deserializeNBT(NBTTagCompound nbt) {
        this.impl.deserializeNBT(nbt);
    }
}

