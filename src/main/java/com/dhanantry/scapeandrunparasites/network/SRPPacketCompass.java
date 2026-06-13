/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 */
package com.dhanantry.scapeandrunparasites.network;

import com.dhanantry.scapeandrunparasites.item.CompassColony;
import com.dhanantry.scapeandrunparasites.item.CompassNode;
import com.dhanantry.scapeandrunparasites.item.CompassOrigin;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SRPPacketCompass
implements IMessage {
    private int pos_X;
    private int pos_Y;
    private int pos_Z;
    private int type;

    public SRPPacketCompass() {
    }

    public SRPPacketCompass(int x, int y, int z, int t) {
        this.pos_X = x;
        this.pos_Y = y;
        this.pos_Z = z;
        this.type = t;
    }

    public void fromBytes(ByteBuf buf) {
        this.pos_X = buf.readInt();
        this.pos_Y = buf.readInt();
        this.pos_Z = buf.readInt();
        this.type = buf.readInt();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.pos_X);
        buf.writeInt(this.pos_Y);
        buf.writeInt(this.pos_Z);
        buf.writeInt(this.type);
    }

    public static class Handler
    implements IMessageHandler<SRPPacketCompass, IMessage> {
        public IMessage onMessage(SRPPacketCompass message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).func_152344_a(() -> this.handle(message, ctx));
            return null;
        }

        private void handle(SRPPacketCompass message, MessageContext ctx) {
            switch (message.type) {
                case 1: {
                    CompassNode.orig = new BlockPos(message.pos_X, message.pos_Y, message.pos_Z);
                    break;
                }
                case 2: {
                    CompassColony.orig = new BlockPos(message.pos_X, message.pos_Y, message.pos_Z);
                    break;
                }
                case 3: {
                    CompassOrigin.orig = new BlockPos(message.pos_X, message.pos_Y, message.pos_Z);
                }
            }
        }
    }
}

