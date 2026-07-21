package com.dhanantry.scapeandrunparasites.bestiary.event;

import com.dhanantry.scapeandrunparasites.bestiary.ParasiteTier;
import com.dhanantry.scapeandrunparasites.bestiary.SRPBestiaryRegistry;
import com.dhanantry.scapeandrunparasites.bestiary.cap.BestiaryCapability;
import com.dhanantry.scapeandrunparasites.bestiary.cap.IBestiaryProgress;
import java.lang.reflect.Field;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BestiaryEvents {
   @SubscribeEvent
   public void onLivingDeath(LivingDeathEvent e) {
      if (e.getSource() != null && e.getSource().func_76346_g() instanceof EntityPlayer) {
         EntityPlayer p = (EntityPlayer)e.getSource().func_76346_g();
         IBestiaryProgress prog = (IBestiaryProgress)p.getCapability(BestiaryCapability.CAP, null);
         if (prog != null) {
            EntityLivingBase mob = e.getEntityLiving();
            ResourceLocation key = EntityList.func_191301_a(mob);
            if (key != null) {
               String mobId = key.toString();
               prog.addKill(mobId, 1);
               prog.markMobSeen(mobId);
               ParasiteTier tier = null;

               for (Object be : SRPBestiaryRegistry.all()) {
                  try {
                     Field fMobId = be.getClass().getField("mobId");
                     Field fTier = be.getClass().getField("tier");
                     Object idObj = fMobId.get(be);
                     if (idObj instanceof String && mobId.equals(idObj)) {
                        Object tierObj = fTier.get(be);
                        if (tierObj instanceof ParasiteTier) {
                           tier = (ParasiteTier)tierObj;
                        }
                        break;
                     }
                  } catch (Throwable var14) {
                  }
               }

               if (tier != null) {
                  prog.markTierSeen(tier);
               }
            }
         }
      }
   }
}
