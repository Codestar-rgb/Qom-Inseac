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
package com.subspaceparasite.bestiary.net;

import com.subspaceparasite.bestiary.cap.BestiaryCapability;
import com.subspaceparasite.bestiary.cap.IBestiaryProgress;
import com.subspaceparasite.bestiary.net.BestiaryNetwork;
import com.subspaceparasite.bestiary.net.PacketBestiarySync;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketBestiaryRequest
implements IMessage {
    private static final boolean DEBUG = false;

    public void toBytes(ByteBuf buf) {
    }

    public void fromBytes(ByteBuf buf) {
    }

    public static class Handler
    implements IMessageHandler<PacketBestiaryRequest, IMessage> {
        public IMessage onMessage(PacketBestiaryRequest msg, MessageContext ctx) {
            EntityPlayerMP mp = ctx.getServerHandler().field_147369_b;
            mp.func_71121_q().func_152344_a(() -> {
                IBestiaryProgress prog = (IBestiaryProgress)mp.getCapability(BestiaryCapability.CAP, null);
                if (prog == null) {
                    System.out.println("[SRP][BESTIARY] Request: player has NO capability");
                    return;
                }
                BestiaryNetwork.CH.sendTo((IMessage)new PacketBestiarySync(prog), mp);
            });
            return null;
        }
    }
}

