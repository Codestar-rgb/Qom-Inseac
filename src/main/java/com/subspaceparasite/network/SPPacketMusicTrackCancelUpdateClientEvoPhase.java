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

public class SPPacketMusicTrackCancelUpdateClientEvoPhase
implements IMessage {
    private int phase;

    public SPPacketMusicTrackCancelUpdateClientEvoPhase() {
    }

    public SPPacketMusicTrackCancelUpdateClientEvoPhase(int in) {
        this.phase = in;
    }

    public void fromBytes(ByteBuf buf) {
        this.phase = buf.readInt();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.phase);
    }

    public static class Handler
    implements IMessageHandler<SPPacketMusicTrackCancelUpdateClientEvoPhase, IMessage> {
        public IMessage onMessage(SPPacketMusicTrackCancelUpdateClientEvoPhase message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).func_152344_a(() -> this.handle(message, ctx));
            return null;
        }

        private void handle(SPPacketMusicTrackCancelUpdateClientEvoPhase message, MessageContext ctx) {
            Minecraft.func_71410_x().func_147118_V().func_189520_a("", SoundCategory.MUSIC);
            SPEventHandlerBus.resetSouncTicker(200);
            SPEventHandlerBus.clientCurrentEvoPhase = (byte)message.phase;
        }
    }
}

