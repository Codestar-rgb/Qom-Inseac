/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.dhanantry.scapeandrunparasites.network;

import com.dhanantry.scapeandrunparasites.client.celestial.CelestialPhaseClient;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MsgSyncCelestialPhase
implements IMessage {
    private int dim;
    private int phase;
    private long nightIndex;
    private String[] activeIds = new String[0];
    private String[] forcedIds = new String[0];

    public MsgSyncCelestialPhase() {
    }

    public MsgSyncCelestialPhase(int dim, int phase, long nightIndex, Set<String> active, Set<String> forced) {
        this.dim = dim;
        this.phase = phase;
        this.nightIndex = nightIndex;
        this.activeIds = active == null ? new String[]{} : active.toArray(new String[0]);
        this.forcedIds = forced == null ? new String[]{} : forced.toArray(new String[0]);
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.dim);
        buf.writeInt(this.phase);
        buf.writeLong(this.nightIndex);
        buf.writeInt(this.activeIds.length);
        for (String s : this.activeIds) {
            MsgSyncCelestialPhase.writeString(buf, s);
        }
        buf.writeInt(this.forcedIds.length);
        for (String s : this.forcedIds) {
            MsgSyncCelestialPhase.writeString(buf, s);
        }
    }

    public void fromBytes(ByteBuf buf) {
        this.dim = buf.readInt();
        this.phase = buf.readInt();
        this.nightIndex = buf.readLong();
        int a = buf.readInt();
        this.activeIds = new String[a];
        for (int i = 0; i < a; ++i) {
            this.activeIds[i] = MsgSyncCelestialPhase.readString(buf);
        }
        int f = buf.readInt();
        this.forcedIds = new String[f];
        for (int i = 0; i < f; ++i) {
            this.forcedIds[i] = MsgSyncCelestialPhase.readString(buf);
        }
    }

    private static void writeString(ByteBuf buf, String s) {
        byte[] b = s.getBytes(StandardCharsets.UTF_8);
        buf.writeInt(b.length);
        buf.writeBytes(b);
    }

    private static String readString(ByteBuf buf) {
        int len = buf.readInt();
        byte[] b = new byte[len];
        buf.readBytes(b);
        return new String(b, StandardCharsets.UTF_8);
    }

    public static class Handler
    implements IMessageHandler<MsgSyncCelestialPhase, IMessage> {
        private static final boolean DEBUG = false;

        private static void debug(String msg) {
        }

        public IMessage onMessage(MsgSyncCelestialPhase msg, MessageContext ctx) {
            if (ctx.side != Side.CLIENT) {
                Handler.debug("received on non-client side, ignoring (dim=" + msg.dim + ", phase=" + msg.phase + ")");
                return null;
            }
            Handler.debug("RECEIVED on client: dim=" + msg.dim + " phase=" + msg.phase + " night=" + msg.nightIndex);
            Minecraft.func_71410_x().func_152344_a(() -> {
                HashSet<String> active = new HashSet<String>();
                for (String s : msg.activeIds) {
                    active.add(s);
                }
                HashSet<String> forced = new HashSet<String>();
                for (String s : msg.forcedIds) {
                    forced.add(s);
                }
                Handler.debug("applying to CelestialPhaseClient: dim=" + msg.dim + " phase=" + msg.phase + " night=" + msg.nightIndex + " active=" + active.size() + " forced=" + forced.size());
                CelestialPhaseClient.setServerNightState(msg.dim, msg.phase, msg.nightIndex, active, forced);
            });
            return null;
        }
    }
}

