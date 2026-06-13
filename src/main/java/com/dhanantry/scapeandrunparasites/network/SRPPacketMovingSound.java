/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 */
package com.dhanantry.scapeandrunparasites.network;

import com.dhanantry.scapeandrunparasites.SRPMain;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SRPPacketMovingSound
implements IMessage {
    int evPhase;
    float v;

    public SRPPacketMovingSound() {
    }

    public SRPPacketMovingSound(int phase) {
        this.evPhase = phase;
        this.v = 1.0f;
    }

    public SRPPacketMovingSound(int phase, float volum) {
        this.evPhase = phase;
        this.v = volum;
    }

    public void fromBytes(ByteBuf ByteBuf2) {
        this.evPhase = ByteBuf2.readInt();
        this.v = ByteBuf2.readFloat();
    }

    public void toBytes(ByteBuf ByteBuf2) {
        ByteBuf2.writeInt(this.evPhase);
        ByteBuf2.writeFloat(this.v);
    }

    public static class Handler
    implements IMessageHandler<SRPPacketMovingSound, IMessage> {
        public IMessage onMessage(SRPPacketMovingSound message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).func_152344_a(() -> this.handle(message, ctx));
            return null;
        }

        private void handle(SRPPacketMovingSound message, MessageContext ctx) {
            SRPMain.proxy.playMovingSound(message.evPhase, message.v);
        }
    }
}

