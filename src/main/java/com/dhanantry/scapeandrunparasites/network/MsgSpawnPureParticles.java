/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.dhanantry.scapeandrunparasites.network;

import com.dhanantry.scapeandrunparasites.client.particle.SRPParticleRegistry;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MsgSpawnPureParticles
implements IMessage {
    public double x;
    public double y;
    public double z;
    public int count;
    public int kind;

    public MsgSpawnPureParticles() {
    }

    public MsgSpawnPureParticles(double x, double y, double z, int count, int kind) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.count = count;
        this.kind = kind;
    }

    public void fromBytes(ByteBuf buf) {
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
        this.count = buf.readInt();
        this.kind = buf.readInt();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeDouble(this.x);
        buf.writeDouble(this.y);
        buf.writeDouble(this.z);
        buf.writeInt(this.count);
        buf.writeInt(this.kind);
    }

    private static class ClientHandler {
        private ClientHandler() {
        }

        public static void handleClient(MsgSpawnPureParticles msg) {
            Minecraft.func_71410_x().func_152344_a(() -> {
                if (Minecraft.func_71410_x().field_71441_e == null) {
                    return;
                }
                SRPParticleRegistry.spawnPureBurst((World)Minecraft.func_71410_x().field_71441_e, msg.x, msg.y, msg.z, msg.count, msg.kind);
            });
        }
    }

    public static class Handler
    implements IMessageHandler<MsgSpawnPureParticles, IMessage> {
        public IMessage onMessage(MsgSpawnPureParticles msg, MessageContext ctx) {
            if (ctx.side == Side.CLIENT) {
                ClientHandler.handleClient(msg);
            }
            return null;
        }
    }
}

