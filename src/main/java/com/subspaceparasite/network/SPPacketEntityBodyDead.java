/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.network.ByteBufUtils
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 */
package com.subspaceparasite.network;

import com.subspaceparasite.entity.ai.misc.EntityBodyParts;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SPPacketEntityBodyDead
implements IMessage {
    private int targetId;
    private int partId;

    public SPPacketEntityBodyDead() {
    }

    public SPPacketEntityBodyDead(int target, int part) {
        this.targetId = target;
        this.partId = part;
    }

    public void fromBytes(ByteBuf buf) {
        this.targetId = ByteBufUtils.readVarInt((ByteBuf)buf, (int)4);
        this.partId = ByteBufUtils.readVarInt((ByteBuf)buf, (int)4);
    }

    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeVarInt((ByteBuf)buf, (int)this.targetId, (int)4);
        ByteBufUtils.writeVarInt((ByteBuf)buf, (int)this.partId, (int)4);
    }

    public static class Handler
    implements IMessageHandler<SPPacketEntityBodyDead, IMessage> {
        public IMessage onMessage(SPPacketEntityBodyDead message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).func_152344_a(() -> this.handle(message, ctx));
            return null;
        }

        private void handle(SPPacketEntityBodyDead message, MessageContext ctx) {
            Entity target = Minecraft.func_71410_x().field_71441_e.func_73045_a(message.targetId);
            if (target != null && target instanceof EntityBodyParts) {
                EntityBodyParts rTarget = (EntityBodyParts)target;
                rTarget.setBodyPartDead(message.partId);
            }
        }
    }
}

