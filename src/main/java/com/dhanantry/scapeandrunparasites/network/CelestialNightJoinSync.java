package com.dhanantry.scapeandrunparasites.network;

import com.dhanantry.scapeandrunparasites.world.CelestialNightData;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class CelestialNightJoinSync {
   private static long getNightIndex(World world) {
      return world.func_82737_E() / 24000L;
   }

   private static void sync(EntityPlayerMP player) {
      World world = player.field_70170_p;
      if (world != null && !world.field_72995_K) {
         int dim = player.field_71093_bK;
         SRPSaveData save = SRPSaveData.get(world, dim);
         if (save != null) {
            int phase = save.getEvolutionPhase(dim);
            CelestialNightData nightData = CelestialNightData.get(world);
            CelestialNightData.DimState state = nightData.getOrCreate(dim);
            long nightIndex = getNightIndex(world);
            SRPNetwork.CHANNEL.sendTo(new MsgSyncCelestialPhase(dim, phase, nightIndex, state.active, state.forced), player);
         }
      }
   }

   @SubscribeEvent
   public void onLogin(PlayerLoggedInEvent e) {
      if (e.player instanceof EntityPlayerMP) {
         sync((EntityPlayerMP)e.player);
      }
   }

   @SubscribeEvent
   public void onDimChange(PlayerChangedDimensionEvent e) {
      if (e.player instanceof EntityPlayerMP) {
         sync((EntityPlayerMP)e.player);
      }
   }
}
