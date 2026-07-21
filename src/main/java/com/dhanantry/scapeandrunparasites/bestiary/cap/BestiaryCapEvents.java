package com.dhanantry.scapeandrunparasites.bestiary.cap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.Clone;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BestiaryCapEvents {
   private static final ResourceLocation KEY = new ResourceLocation("srparasites", "bestiary");

   @SubscribeEvent
   public void onAttachCaps(AttachCapabilitiesEvent<Entity> e) {
      if (e.getObject() instanceof EntityPlayer) {
         e.addCapability(KEY, new BestiaryProvider());
      }
   }

   @SubscribeEvent
   public void onPlayerClone(Clone e) {
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
