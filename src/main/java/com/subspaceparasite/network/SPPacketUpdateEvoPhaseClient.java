/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.SoundCategory
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 */
package com.subspaceparasite.network;

import com.subspaceparasite.util.handlers.SPEventHandlerBus;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SPPacketUpdateEvoPhaseClient
implements IMessage {
    private int phase;
    private boolean vector;

    public SPPacketUpdateEvoPhaseClient() {
    }

    public SPPacketUpdateEvoPhaseClient(int in, boolean v) {
        this.phase = in;
        this.vector = v;
    }

    public void fromBytes(ByteBuf buf) {
        this.phase = buf.readInt();
        this.vector = buf.readBoolean();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.phase);
        buf.writeBoolean(this.vector);
    }

    public static class Handler
    implements IMessageHandler<SPPacketUpdateEvoPhaseClient, IMessage> {
        public IMessage onMessage(SPPacketUpdateEvoPhaseClient message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).func_152344_a(() -> this.handle(message, ctx));
            return null;
        }

        private void handle(SPPacketUpdateEvoPhaseClient message, MessageContext ctx) {
            int prev = SPEventHandlerBus.clientVector;
            if (message.vector) {
                if (prev <= 0) {
                    Minecraft.func_71410_x().func_147118_V().func_189520_a("", SoundCategory.MUSIC);
                }
                SPEventHandlerBus.clientVector = 200;
            }
            SPEventHandlerBus.clientCurrentEvoPhase = (byte)message.phase;
        }
    }
}

