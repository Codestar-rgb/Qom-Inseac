/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.eventhandler.EventPriority
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ServerTickEvent
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package com.dhanantry.scapeandrunparasites.util.handlers;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.network.SRPPacketBiomeChangeBatch;
import com.dhanantry.scapeandrunparasites.util.handlers.BiomeUpdateQueue;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class BiomeBatchSender {
    @SubscribeEvent(priority=EventPriority.NORMAL)
    public void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.START) {
            return;
        }
        Deque<BiomeUpdateQueue.BiomeUpdate> drained = BiomeUpdateQueue.drainUpTo(20);
        if (drained.isEmpty()) {
            return;
        }
        Map<Integer, List<SRPPacketBiomeChangeBatch.Entry>> batches = BiomeUpdateQueue.buildBatches(drained);
        for (Map.Entry<Integer, List<SRPPacketBiomeChangeBatch.Entry>> be : batches.entrySet()) {
            List<SRPPacketBiomeChangeBatch.Entry> entries = be.getValue();
            for (int i = 0; i < entries.size(); i += 1024) {
                int end = Math.min(i + 1024, entries.size());
                List<SRPPacketBiomeChangeBatch.Entry> chunk = entries.subList(i, end);
                SRPPacketBiomeChangeBatch packet = new SRPPacketBiomeChangeBatch(new ArrayList<SRPPacketBiomeChangeBatch.Entry>(chunk));
                try {
                    SRPMain.network.sendToDimension((IMessage)packet, be.getKey().intValue());
                    continue;
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
        }
    }
}

