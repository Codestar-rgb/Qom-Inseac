package com.dhanantry.scapeandrunparasites.util.handlers;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.network.SRPPacketBiomeChangeBatch;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;

public class BiomeBatchSender {
   @SubscribeEvent(priority = EventPriority.NORMAL)
   public void onServerTick(ServerTickEvent event) {
      if (event.phase == Phase.START) {
         Deque<BiomeUpdateQueue.BiomeUpdate> drained = BiomeUpdateQueue.drainUpTo(20);
         if (!drained.isEmpty()) {
            Map<Integer, List<SRPPacketBiomeChangeBatch.Entry>> batches = BiomeUpdateQueue.buildBatches(drained);

            for (Entry<Integer, List<SRPPacketBiomeChangeBatch.Entry>> be : batches.entrySet()) {
               List<SRPPacketBiomeChangeBatch.Entry> entries = be.getValue();

               for (int i = 0; i < entries.size(); i += 1024) {
                  int end = Math.min(i + 1024, entries.size());
                  List<SRPPacketBiomeChangeBatch.Entry> chunk = entries.subList(i, end);
                  SRPPacketBiomeChangeBatch packet = new SRPPacketBiomeChangeBatch(new ArrayList<>(chunk));

                  try {
                     SRPMain.network.sendToDimension(packet, be.getKey());
                  } catch (Exception var12) {
                  }
               }
            }
         }
      }
   }
}
