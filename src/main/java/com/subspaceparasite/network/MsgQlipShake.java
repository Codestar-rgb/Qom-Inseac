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
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.network;

import com.subspaceparasite.client.ClientQlipShake;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MsgQlipShake
implements IMessage {
    private int duration;
    private int delay;
    private boolean dark;
    private boolean shake;
    private float shakeValue;

    public MsgQlipShake() {
    }

    public MsgQlipShake(int durationTicks, int delayTicks, boolean darkScreen, boolean shakeScreen, float value) {
        this.duration = durationTicks;
        this.delay = delayTicks;
        this.dark = darkScreen;
        this.shake = shakeScreen;
        this.shakeValue = value;
    }

    public void fromBytes(ByteBuf buf) {
        this.duration = buf.readInt();
        this.delay = buf.readInt();
        this.dark = buf.readBoolean();
        this.shake = buf.readBoolean();
        this.shakeValue = buf.readFloat();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.duration);
        buf.writeInt(this.delay);
        buf.writeBoolean(this.dark);
        buf.writeBoolean(this.shake);
        buf.writeFloat(this.shakeValue);
    }

    public static class Handler
    implements IMessageHandler<MsgQlipShake, IMessage> {
        @SideOnly(value=Side.CLIENT)
        public IMessage onMessage(MsgQlipShake msg, MessageContext ctx) {
            Minecraft.func_71410_x().func_152344_a(() -> ClientQlipShake.INSTANCE.triggerDelayed(msg.duration, msg.delay, msg.dark, msg.shake, msg.shakeValue));
            return null;
        }
    }
}

