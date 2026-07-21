package com.dhanantry.scapeandrunparasites.network;

import com.dhanantry.scapeandrunparasites.item.ItemClockDevelopment;
import com.dhanantry.scapeandrunparasites.item.ItemClockEvolution;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SRPPacketClock implements IMessage {
   private int cooldown;
   private int phase;
   private int development;
   private int type;

   public SRPPacketClock() {
   }

   public SRPPacketClock(int x, int y, int z, int t) {
      this.cooldown = x;
      this.phase = y;
      this.development = z;
      this.type = t;
   }

   public void fromBytes(ByteBuf buf) {
      this.cooldown = buf.readInt();
      this.phase = buf.readInt();
      this.development = buf.readInt();
      this.type = buf.readInt();
   }

   public void toBytes(ByteBuf buf) {
      buf.writeInt(this.cooldown);
      buf.writeInt(this.phase);
      buf.writeInt(this.development);
      buf.writeInt(this.type);
   }

   public static class Handler implements IMessageHandler<SRPPacketClock, IMessage> {
      public IMessage onMessage(SRPPacketClock message, MessageContext ctx) {
         FMLCommonHandler.instance().getWorldThread(ctx.netHandler).func_152344_a(() -> this.handle(message, ctx));
         return null;
      }

      private void handle(SRPPacketClock message, MessageContext ctx) {
         ItemClockEvolution.cooldown = message.cooldown;
         ItemClockEvolution.phase = message.phase;
         ItemClockDevelopment.level = message.development;
      }
   }
}
