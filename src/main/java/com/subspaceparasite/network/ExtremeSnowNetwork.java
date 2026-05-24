/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.fml.common.network.NetworkRegistry
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 *  net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.subspaceparasite.network;

import com.subspaceparasite.client.fx.ClientExtremeSnow;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public final class ExtremeSnowNetwork {
    public static final SimpleNetworkWrapper CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel("srp_snow");

    private ExtremeSnowNetwork() {
    }

    public static void init() {
        int id = 0;
        CHANNEL.registerMessage(S2CExtremeSnow.Handler.class, S2CExtremeSnow.class, id++, Side.CLIENT);
    }

    public static void broadcast(WorldServer world, boolean en, float inten, boolean any, float windDeg, float windSpeed) {
        for (EntityPlayerMP p : world.func_73046_m().func_184103_al().func_181057_v()) {
            if (p.field_70170_p != world) continue;
            CHANNEL.sendTo((IMessage)new S2CExtremeSnow(en, inten, any, windDeg, windSpeed), p);
        }
    }

    public static class S2CExtremeSnow
    implements IMessage {
        public boolean enabled;
        public boolean anywhere;
        public float intensity;
        public float windDeg;
        public float windSpeed;

        public S2CExtremeSnow() {
        }

        public S2CExtremeSnow(boolean en, float i, boolean any, float deg, float spd) {
            this.enabled = en;
            this.intensity = i;
            this.anywhere = any;
            this.windDeg = deg;
            this.windSpeed = spd;
        }

        public void toBytes(ByteBuf b) {
            b.writeBoolean(this.enabled);
            b.writeFloat(this.intensity);
            b.writeBoolean(this.anywhere);
            b.writeFloat(this.windDeg);
            b.writeFloat(this.windSpeed);
        }

        public void fromBytes(ByteBuf b) {
            this.enabled = b.readBoolean();
            this.intensity = b.readFloat();
            this.anywhere = b.readBoolean();
            this.windDeg = b.readFloat();
            this.windSpeed = b.readFloat();
        }

        public static class Handler
        implements IMessageHandler<S2CExtremeSnow, IMessage> {
            public IMessage onMessage(S2CExtremeSnow msg, MessageContext ctx) {
                Minecraft.func_71410_x().func_152344_a(() -> ClientExtremeSnow.setState(msg.enabled, msg.intensity, msg.anywhere, msg.windDeg, msg.windSpeed));
                return null;
            }
        }
    }
}

