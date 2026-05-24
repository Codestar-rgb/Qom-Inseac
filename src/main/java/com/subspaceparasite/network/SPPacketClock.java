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

import com.subspaceparasite.item.ItemClockDevelopment;
import com.subspaceparasite.item.ItemClockEvolution;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SPPacketClock
implements IMessage {
    private int cooldown;
    private int phase;
    private int development;
    private int type;

    public SPPacketClock() {
    }

    public SPPacketClock(int x, int y, int z, int t) {
        this.cooldown = x;
        this.phase = y;
        this.development = z;
        this.type = t;
    }

    public void fromBytes(ByteBuf buf) {
        this.cooldown = buf.readInt();
        this.phase = buf.readInt();
        this.development = buf.readInt();
        this.type = buf.readInt();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.cooldown);
        buf.writeInt(this.phase);
        buf.writeInt(this.development);
        buf.writeInt(this.type);
    }

    public static class Handler
    implements IMessageHandler<SPPacketClock, IMessage> {
        public IMessage onMessage(SPPacketClock message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).func_152344_a(() -> this.handle(message, ctx));
            return null;
        }

        private void handle(SPPacketClock message, MessageContext ctx) {
            ItemClockEvolution.cooldown = message.cooldown;
            ItemClockEvolution.phase = message.phase;
            ItemClockDevelopment.level = message.development;
        }
    }
}

