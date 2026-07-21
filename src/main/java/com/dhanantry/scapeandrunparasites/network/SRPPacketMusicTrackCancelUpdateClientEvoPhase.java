package com.dhanantry.scapeandrunparasites.network;

import com.dhanantry.scapeandrunparasites.util.handlers.SRPEventHandlerBus;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SRPPacketMusicTrackCancelUpdateClientEvoPhase implements IMessage {
   private int phase;

   public SRPPacketMusicTrackCancelUpdateClientEvoPhase() {
   }

   public SRPPacketMusicTrackCancelUpdateClientEvoPhase(int in) {
      this.phase = in;
   }

   public void fromBytes(ByteBuf buf) {
      this.phase = buf.readInt();
   }

   public void toBytes(ByteBuf buf) {
      buf.writeInt(this.phase);
   }

   public static class Handler implements IMessageHandler<SRPPacketMusicTrackCancelUpdateClientEvoPhase, IMessage> {
      public IMessage onMessage(SRPPacketMusicTrackCancelUpdateClientEvoPhase message, MessageContext ctx) {
         FMLCommonHandler.instance().getWorldThread(ctx.netHandler).func_152344_a(() -> this.handle(message, ctx));
         return null;
      }

      private void handle(SRPPacketMusicTrackCancelUpdateClientEvoPhase message, MessageContext ctx) {
         Minecraft.func_71410_x().func_147118_V().func_189520_a("", SoundCategory.MUSIC);
         SRPEventHandlerBus.resetSouncTicker(200);
         SRPEventHandlerBus.clientCurrentEvoPhase = (byte)message.phase;
      }
   }
}
