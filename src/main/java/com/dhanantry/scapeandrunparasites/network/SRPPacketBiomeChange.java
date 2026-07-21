package com.dhanantry.scapeandrunparasites.network;

import com.dhanantry.scapeandrunparasites.SRPMain;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SRPPacketBiomeChange implements IMessage {
   private BlockPos blockPos;
   private boolean convertToParasite;
   private int type;

   public SRPPacketBiomeChange() {
   }

   public SRPPacketBiomeChange(BlockPos pos, boolean convert, int typeP) {
      this.blockPos = pos;
      this.convertToParasite = convert;
      this.type = typeP;
   }

   public void fromBytes(ByteBuf ByteBuf) {
      this.blockPos = new BlockPos(ByteBuf.readInt(), ByteBuf.readInt(), ByteBuf.readInt());
      this.convertToParasite = ByteBuf.readBoolean();
      this.type = ByteBuf.readInt();
   }

   public void toBytes(ByteBuf ByteBuf) {
      ByteBuf.writeInt(this.blockPos.func_177958_n());
      ByteBuf.writeInt(this.blockPos.func_177956_o());
      ByteBuf.writeInt(this.blockPos.func_177952_p());
      ByteBuf.writeBoolean(this.convertToParasite);
      ByteBuf.writeInt(this.type);
   }

   public static class Handler implements IMessageHandler<SRPPacketBiomeChange, IMessage> {
      public IMessage onMessage(SRPPacketBiomeChange message, MessageContext ctx) {
         FMLCommonHandler.instance().getWorldThread(ctx.netHandler).func_152344_a(() -> this.handle(message, ctx));
         return null;
      }

      private void handle(SRPPacketBiomeChange message, MessageContext ctx) {
         SRPMain.proxy.spreadBiome(message.blockPos, message.convertToParasite, message.type);
      }
   }
}
