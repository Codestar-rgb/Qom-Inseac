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

import com.subspaceparasite.util.handlers.SPEventHandlerBus;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SPPacketFog
implements IMessage {
    private float rFog;
    private float rFogRed;
    private float rFogGreen;
    private float rFogBlue;

    public SPPacketFog() {
    }

    public SPPacketFog(float fog, float red, float green, float blue) {
        this.rFog = fog;
        this.rFogRed = red;
        this.rFogGreen = green;
        this.rFogBlue = blue;
    }

    public void fromBytes(ByteBuf buf) {
        this.rFog = buf.readFloat();
        this.rFogRed = buf.readFloat();
        this.rFogGreen = buf.readFloat();
        this.rFogBlue = buf.readFloat();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeFloat(this.rFog);
        buf.writeFloat(this.rFogRed);
        buf.writeFloat(this.rFogGreen);
        buf.writeFloat(this.rFogBlue);
    }

    public static class Handler
    implements IMessageHandler<SPPacketFog, IMessage> {
        public IMessage onMessage(SPPacketFog message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).func_152344_a(() -> this.handle(message, ctx));
            return null;
        }

        private void handle(SPPacketFog message, MessageContext ctx) {
            SPEventHandlerBus.fog = message.rFog;
            SPEventHandlerBus.fogRed = message.rFogRed;
            SPEventHandlerBus.fogGreen = message.rFogGreen;
            SPEventHandlerBus.fogBlue = message.rFogBlue;
        }
    }
}

