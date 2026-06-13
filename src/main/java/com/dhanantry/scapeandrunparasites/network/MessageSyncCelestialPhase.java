/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 */
package com.dhanantry.scapeandrunparasites.network;

import com.dhanantry.scapeandrunparasites.client.celestial.CelestialPhaseClient;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSyncCelestialPhase
implements IMessage {
    private int dim;
    private int phase;

    public MessageSyncCelestialPhase() {
    }

    public MessageSyncCelestialPhase(int dim, int phase) {
        this.dim = dim;
        this.phase = phase;
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.dim);
        buf.writeInt(this.phase);
    }

    public void fromBytes(ByteBuf buf) {
        this.dim = buf.readInt();
        this.phase = buf.readInt();
    }

    public static class Handler
    implements IMessageHandler<MessageSyncCelestialPhase, IMessage> {
        public IMessage onMessage(MessageSyncCelestialPhase msg, MessageContext ctx) {
            Minecraft.func_71410_x().func_152344_a(() -> CelestialPhaseClient.setPhase(msg.dim, msg.phase));
            return null;
        }
    }
}

