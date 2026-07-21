package com.dhanantry.scapeandrunparasites.network.msg;

import com.dhanantry.scapeandrunparasites.client.EscapeClientState;
import com.dhanantry.scapeandrunparasites.client.gui.GuiGameOverEscape;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class S2CSetEscapeOffer implements IMessage, IMessageHandler<S2CSetEscapeOffer, IMessage> {
   private boolean offer;

   public S2CSetEscapeOffer() {
   }

   public S2CSetEscapeOffer(boolean offer) {
      this.offer = offer;
   }

   public void toBytes(ByteBuf buf) {
      buf.writeBoolean(this.offer);
   }

   public void fromBytes(ByteBuf buf) {
      this.offer = buf.readBoolean();
   }

   public IMessage onMessage(S2CSetEscapeOffer msg, MessageContext ctx) {
      if (ctx.side == Side.CLIENT) {
         S2CSetEscapeOffer.ClientHandler.handleClient(msg);
      }

      return null;
   }

   private static class ClientHandler {
      public static void handleClient(S2CSetEscapeOffer msg) {
         Minecraft mc = Minecraft.func_71410_x();
         mc.func_152344_a(() -> {
            EscapeClientState.OFFER = msg.offer;
            if (mc.field_71462_r instanceof GuiGameOver && !(mc.field_71462_r instanceof GuiGameOverEscape)) {
               mc.func_147108_a(new GuiGameOverEscape());
            } else if (mc.field_71462_r instanceof GuiGameOverEscape) {
               mc.func_147108_a(new GuiGameOverEscape());
            }
         });
      }
   }
}
