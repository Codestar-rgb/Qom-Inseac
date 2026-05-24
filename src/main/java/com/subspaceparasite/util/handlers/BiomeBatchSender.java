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
package com.subspaceparasite.util.handlers;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.network.SPPacketBiomeChangeBatch;
import com.subspaceparasite.util.handlers.BiomeUpdateQueue;
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
        Map<Integer, List<SPPacketBiomeChangeBatch.Entry>> batches = BiomeUpdateQueue.buildBatches(drained);
        for (Map.Entry<Integer, List<SPPacketBiomeChangeBatch.Entry>> be : batches.entrySet()) {
            SPPacketBiomeChangeBatch packet = new SPPacketBiomeChangeBatch(be.getValue());
            try {
                SPMain.network.sendToDimension((IMessage)packet, be.getKey().intValue());
            }
            catch (Exception exception) {}
        }
    }
}

