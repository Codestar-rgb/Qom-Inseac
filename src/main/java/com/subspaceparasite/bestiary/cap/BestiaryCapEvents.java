/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.common.capabilities.ICapabilityProvider
 *  net.minecraftforge.event.AttachCapabilitiesEvent
 *  net.minecraftforge.event.entity.player.PlayerEvent$Clone
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.subspaceparasite.bestiary.cap;

import com.subspaceparasite.bestiary.cap.BestiaryCapability;
import com.subspaceparasite.bestiary.cap.BestiaryProvider;
import com.subspaceparasite.bestiary.cap.IBestiaryProgress;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BestiaryCapEvents {
    private static final ResourceLocation KEY = new ResourceLocation("subspaceparasite", "bestiary");

    @SubscribeEvent
    public void onAttachCaps(AttachCapabilitiesEvent<Entity> e) {
        if (e.getObject() instanceof EntityPlayer) {
            e.addCapability(KEY, (ICapabilityProvider)new BestiaryProvider());
        }
    }

    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone e) {
        EntityPlayer oldP = e.getOriginal();
        EntityPlayer newP = e.getEntityPlayer();
        IBestiaryProgress oldCap = (IBestiaryProgress)oldP.getCapability(BestiaryCapability.CAP, null);
        IBestiaryProgress newCap = (IBestiaryProgress)newP.getCapability(BestiaryCapability.CAP, null);
        if (oldCap != null && newCap != null) {
            NBTTagCompound tag = oldCap.serializeNBT();
            newCap.deserializeNBT(tag);
        }
    }
}

