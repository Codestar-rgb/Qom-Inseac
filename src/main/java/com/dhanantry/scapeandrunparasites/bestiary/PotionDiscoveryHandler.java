package com.dhanantry.scapeandrunparasites.bestiary;

import com.dhanantry.scapeandrunparasites.bestiary.cap.BestiaryCapability;
import com.dhanantry.scapeandrunparasites.bestiary.cap.IBestiaryProgress;
import com.dhanantry.scapeandrunparasites.bestiary.net.BestiaryNetwork;
import com.dhanantry.scapeandrunparasites.bestiary.net.PacketBestiarySync;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.PotionEvent.PotionAddedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PotionDiscoveryHandler {
   @SubscribeEvent
   public void onPotionAdded(PotionAddedEvent e) {
      if (e.getEntityLiving() != null && e.getEntityLiving().field_70170_p != null) {
         if (!e.getEntityLiving().field_70170_p.field_72995_K) {
            if (e.getEntityLiving() instanceof EntityPlayerMP) {
               EntityPlayerMP p = (EntityPlayerMP)e.getEntityLiving();
               PotionEffect pe = e.getPotionEffect();
               if (pe != null) {
                  Potion potion = pe.func_188419_a();
                  ResourceLocation rl = potion.getRegistryName();
                  if (rl != null) {
                     if ("srparasites".equals(rl.func_110624_b())) {
                        IBestiaryProgress prog = (IBestiaryProgress)p.getCapability(BestiaryCapability.CAP, null);
                        if (prog != null) {
                           String id = rl.func_110623_a();
                           if (!prog.hasSeenEffect(id)) {
                              prog.markEffectSeen(id);
                              BestiaryNetwork.CH.sendTo(new PacketBestiarySync(prog), p);
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }
}
