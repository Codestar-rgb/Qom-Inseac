package com.dhanantry.scapeandrunparasites.network;

import com.dhanantry.scapeandrunparasites.util.handlers.SRPEventHandlerBus;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SRPPacketFog implements IMessage {
   private float rFog;
   private float rFogRed;
   private float rFogGreen;
   private float rFogBlue;

   public SRPPacketFog() {
   }

   public SRPPacketFog(float fog, float red, float green, float blue) {
      this.rFog = fog;
      this.rFogRed = red;
      this.rFogGreen = green;
      this.rFogBlue = blue;
   }

   public void fromBytes(ByteBuf buf) {
      this.rFog = buf.readFloat();
      this.rFogRed = buf.readFloat();
      this.rFogGreen = buf.readFloat();
      this.rFogBlue = buf.readFloat();
   }

   public void toBytes(ByteBuf buf) {
      buf.writeFloat(this.rFog);
      buf.writeFloat(this.rFogRed);
      buf.writeFloat(this.rFogGreen);
      buf.writeFloat(this.rFogBlue);
   }

   public static class Handler implements IMessageHandler<SRPPacketFog, IMessage> {
      public IMessage onMessage(SRPPacketFog message, MessageContext ctx) {
         FMLCommonHandler.instance().getWorldThread(ctx.netHandler).func_152344_a(() -> this.handle(message, ctx));
         return null;
      }

      private void handle(SRPPacketFog message, MessageContext ctx) {
         SRPEventHandlerBus.fog = message.rFog;
         SRPEventHandlerBus.fogRed = message.rFogRed;
         SRPEventHandlerBus.fogGreen = message.rFogGreen;
         SRPEventHandlerBus.fogBlue = message.rFogBlue;
      }
   }
}
