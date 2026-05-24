/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 */
package com.subspaceparasite.network;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.network.SPPacketUpdateEvoPhaseClient;
import com.subspaceparasite.world.SPSaveData;
import com.subspaceparasite.world.SPWorldData;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SPPacketRequestEvoPhaseClient
implements IMessage {
    public void fromBytes(ByteBuf buf) {
    }

    public void toBytes(ByteBuf buf) {
    }

    public static class Handler
    implements IMessageHandler<SPPacketRequestEvoPhaseClient, IMessage> {
        public IMessage onMessage(SPPacketRequestEvoPhaseClient message, MessageContext ctx) {
            final EntityPlayerMP thePlayer = SPMain.proxy.getPlayerEntityFromContext(ctx);
            thePlayer.func_184102_h().func_152344_a(new Runnable(){

                @Override
                public void run() {
                    SPMain.network.sendTo((IMessage)new SPPacketUpdateEvoPhaseClient(SPSaveData.get(thePlayer.field_70170_p, 110).getEvolutionPhase(thePlayer.field_70170_p.field_73011_w.getDimension()), SPWorldData.get(thePlayer.field_70170_p).nearestInfectionPosition(false, thePlayer.func_180425_c()) != null), thePlayer);
                }
            });
            return null;
        }
    }
}

