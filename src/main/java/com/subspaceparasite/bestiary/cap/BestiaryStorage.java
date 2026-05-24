/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.EnumFacing
 *  net.minecraftforge.common.capabilities.Capability
 *  net.minecraftforge.common.capabilities.Capability$IStorage
 */
package com.subspaceparasite.bestiary.cap;

import com.subspaceparasite.bestiary.cap.IBestiaryProgress;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class BestiaryStorage
implements Capability.IStorage<IBestiaryProgress> {
    public NBTBase writeNBT(Capability<IBestiaryProgress> cap, IBestiaryProgress inst, EnumFacing side) {
        return inst == null ? new NBTTagCompound() : inst.serializeNBT();
    }

    public void readNBT(Capability<IBestiaryProgress> cap, IBestiaryProgress inst, EnumFacing side, NBTBase nbt) {
        if (inst != null && nbt instanceof NBTTagCompound) {
            inst.deserializeNBT((NBTTagCompound)nbt);
        }
    }
}

