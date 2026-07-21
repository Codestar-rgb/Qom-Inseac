package com.dhanantry.scapeandrunparasites.network;

import com.dhanantry.scapeandrunparasites.util.handlers.SRPEventHandlerBus;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SRPPacketUpdateEvoPhaseClient implements IMessage {
   private int phase;
   private boolean vector;

   public SRPPacketUpdateEvoPhaseClient() {
   }

   public SRPPacketUpdateEvoPhaseClient(int in, boolean v) {
      this.phase = in;
      this.vector = v;
   }

   public void fromBytes(ByteBuf buf) {
      this.phase = buf.readInt();
      this.vector = buf.readBoolean();
   }

   public void toBytes(ByteBuf buf) {
      buf.writeInt(this.phase);
      buf.writeBoolean(this.vector);
   }

   public static class Handler implements IMessageHandler<SRPPacketUpdateEvoPhaseClient, IMessage> {
      public IMessage onMessage(SRPPacketUpdateEvoPhaseClient message, MessageContext ctx) {
         FMLCommonHandler.instance().getWorldThread(ctx.netHandler).func_152344_a(() -> this.handle(message, ctx));
         return null;
      }

      private void handle(SRPPacketUpdateEvoPhaseClient message, MessageContext ctx) {
         int prev = SRPEventHandlerBus.clientVector;
         if (message.vector) {
            if (prev <= 0) {
               Minecraft.func_71410_x().func_147118_V().func_189520_a("", SoundCategory.MUSIC);
            }

            SRPEventHandlerBus.clientVector = 200;
         }

         SRPEventHandlerBus.clientCurrentEvoPhase = (byte)message.phase;
      }
   }
}
