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
package com.subspaceparasite.network;

import com.subspaceparasite.SPMain;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SPPacketMovingSound
implements IMessage {
    int evPhase;
    float v;

    public SPPacketMovingSound() {
    }

    public SPPacketMovingSound(int phase) {
        this.evPhase = phase;
        this.v = 1.0f;
    }

    public SPPacketMovingSound(int phase, float volum) {
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
    implements IMessageHandler<SPPacketMovingSound, IMessage> {
        public IMessage onMessage(SPPacketMovingSound message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).func_152344_a(() -> this.handle(message, ctx));
            return null;
        }

        private void handle(SPPacketMovingSound message, MessageContext ctx) {
            SPMain.proxy.playMovingSound(message.evPhase, message.v);
        }
    }
}

