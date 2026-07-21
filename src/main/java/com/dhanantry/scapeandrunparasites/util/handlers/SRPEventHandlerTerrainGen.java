package com.dhanantry.scapeandrunparasites.util.handlers;

import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventWorld;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import net.minecraft.block.IGrowable;
import net.minecraftforge.event.terraingen.SaplingGrowTreeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;

public class SRPEventHandlerTerrainGen {
   @SubscribeEvent
   public void allowTreeGrowthTick(SaplingGrowTreeEvent event) {
      if (event.getWorld().func_180495_p(event.getPos()).func_177230_c() instanceof IGrowable) {
         if (event.getWorld().func_180495_p(event.getPos()).func_177230_c() != SRPBlocks.ParasiteSapling) {
            int heart = ParasiteEventWorld.canBiomeStillExist(event.getWorld(), event.getPos(), false);
            switch (heart) {
               case 1:
                  if (event.getWorld().field_73012_v.nextDouble() < SRPConfigWorld.nodeCropStopNodeOne) {
                     event.setResult(Result.DENY);
                  }
                  break;
               case 2:
                  if (event.getWorld().field_73012_v.nextDouble() < SRPConfigWorld.nodeCropStopNodeTwo) {
                     event.setResult(Result.DENY);
                  }
                  break;
               case 3:
                  if (event.getWorld().field_73012_v.nextDouble() < SRPConfigWorld.nodeCropStopNodeThree) {
                     event.setResult(Result.DENY);
                  }
            }
         }
      }
   }
}
