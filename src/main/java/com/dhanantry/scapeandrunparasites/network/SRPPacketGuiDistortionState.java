package com.dhanantry.scapeandrunparasites.network;

import com.dhanantry.scapeandrunparasites.bestiary.client.gui.GuiDistortionHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SRPPacketGuiDistortionState implements IMessage {
   private boolean disabled;
   private boolean creativeOverride;

   public SRPPacketGuiDistortionState() {
   }

   public SRPPacketGuiDistortionState(boolean disabled) {
      this(disabled, false);
   }

   public SRPPacketGuiDistortionState(boolean disabled, boolean creativeOverride) {
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

   public static class Handler implements IMessageHandler<SRPPacketGuiDistortionState, IMessage> {
      public IMessage onMessage(final SRPPacketGuiDistortionState message, MessageContext ctx) {
         Minecraft.func_71410_x().func_152344_a(new Runnable() {
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
