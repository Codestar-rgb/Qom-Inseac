/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.EnumFacing
 *  net.minecraftforge.common.capabilities.Capability
 *  net.minecraftforge.common.capabilities.Capability$IStorage
 *  net.minecraftforge.common.capabilities.CapabilityInject
 *  net.minecraftforge.common.capabilities.CapabilityManager
 */
package com.dhanantry.scapeandrunparasites.bestiary.cap;

import com.dhanantry.scapeandrunparasites.bestiary.cap.BestiaryProgress;
import com.dhanantry.scapeandrunparasites.bestiary.cap.IBestiaryProgress;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public final class BestiaryCapability {
    @CapabilityInject(value=IBestiaryProgress.class)
    public static final Capability<IBestiaryProgress> CAP = null;

    private BestiaryCapability() {
    }

    public static void register() {
        CapabilityManager.INSTANCE.register(IBestiaryProgress.class, (Capability.IStorage)new Storage(), BestiaryProgress::new);
    }

    public static class Storage
    implements Capability.IStorage<IBestiaryProgress> {
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

