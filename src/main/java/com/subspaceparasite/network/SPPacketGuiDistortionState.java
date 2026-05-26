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
package com.subspaceparasite.network;

import com.subspaceparasite.bestiary.client.gui.GuiDistortionHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SPPacketGuiDistortionState
implements IMessage {
    private boolean disabled;
    private boolean creativeOverride;

    public SPPacketGuiDistortionState() {
    }

    public SPPacketGuiDistortionState(boolean disabled) {
        this(disabled, false);
    }

    public SPPacketGuiDistortionState(boolean disabled, boolean creativeOverride) {
        this.disabled = disabled;
        this.creativeOverride = creativeOverride;
    }

    public void fromBytes(ByteBuf buf) {
        this.disabled = buf.readBoolean();
        this.creativeOverride = buf.isReadable() && buf.readBoolean();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(this.disabled);
        buf.writeBoolean(this.creativeOverride);
    }

    public static class Handler
    implements IMessageHandler<SPPacketGuiDistortionState, IMessage> {
        public IMessage onMessage(final SPPacketGuiDistortionState message, MessageContext ctx) {
            Minecraft.func_71410_x().func_152344_a(new Runnable(){

                @Override
                public void run() {
                    GuiDistortionHelper.clientDistortionDisabled = message.disabled;
                    GuiDistortionHelper.clientCreativeOverride = message.creativeOverride;
                }
            });
            return null;
        }
    }
}

