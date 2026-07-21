package com.dhanantry.scapeandrunparasites.network;

import com.dhanantry.scapeandrunparasites.SRPMain;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SRPPacketBiomeChangeBatch implements IMessage {
   public static final int MAX_ENTRIES = 1024;
   private List<SRPPacketBiomeChangeBatch.Entry> entries = new ArrayList<>();

   public SRPPacketBiomeChangeBatch() {
   }

   public SRPPacketBiomeChangeBatch(List<SRPPacketBiomeChangeBatch.Entry> entries) {
      this.entries = (List<SRPPacketBiomeChangeBatch.Entry>)(entries.size() > 1024 ? new ArrayList<>(entries.subList(0, 1024)) : entries);
   }

   public void fromBytes(ByteBuf buf) {
      int count = buf.readInt();
      this.entries = new ArrayList<>(count);

      for (int i = 0; i < count; i++) {
         int x = buf.readInt();
         int y = buf.readInt();
         int z = buf.readInt();
         boolean convert = buf.readBoolean();
         int type = buf.readInt();
         this.entries.add(new SRPPacketBiomeChangeBatch.Entry(x, y, z, convert, type));
      }
   }

   public void toBytes(ByteBuf buf) {
      buf.writeInt(this.entries.size());

      for (SRPPacketBiomeChangeBatch.Entry e : this.entries) {
         buf.writeInt(e.x);
         buf.writeInt(e.y);
         buf.writeInt(e.z);
         buf.writeBoolean(e.convert);
         buf.writeInt(e.type);
      }
   }

   public static class Entry {
      public int x;
      public int y;
      public int z;
      public boolean convert;
      public int type;

      public Entry() {
      }

      public Entry(int x, int y, int z, boolean convert, int type) {
         this.x = x;
         this.y = y;
         this.z = z;
         this.convert = convert;
         this.type = type;
      }
   }

   public static class Handler implements IMessageHandler<SRPPacketBiomeChangeBatch, IMessage> {
      public IMessage onMessage(SRPPacketBiomeChangeBatch message, MessageContext ctx) {
         FMLCommonHandler.instance().getWorldThread(ctx.netHandler).func_152344_a(() -> this.handle(message, ctx));
         return null;
      }

      private void handle(SRPPacketBiomeChangeBatch message, MessageContext ctx) {
         for (SRPPacketBiomeChangeBatch.Entry e : message.entries) {
            BlockPos bp = new BlockPos(e.x, e.y, e.z);
            SRPMain.proxy.spreadBiome(bp, e.convert, e.type);
         }
      }
   }
}
