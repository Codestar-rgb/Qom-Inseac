package com.dhanantry.scapeandrunparasites.world.celestial;

import com.dhanantry.scapeandrunparasites.client.celestial.CelestialPhaseClient;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketCelestialNightState implements IMessage {
   private int dim;
   private int phase;
   private long nightIndex;
   private String[] active;
   private String[] forced;

   public PacketCelestialNightState() {
   }

   public PacketCelestialNightState(int dim, int phase, long nightIndex, Set<String> active, Set<String> forced) {
      this.dim = dim;
      this.phase = phase;
      this.nightIndex = nightIndex;
      this.active = active.toArray(new String[0]);
      this.forced = forced.toArray(new String[0]);
   }

   public void toBytes(ByteBuf buf) {
      buf.writeInt(this.dim);
      buf.writeInt(this.phase);
      buf.writeLong(this.nightIndex);
      buf.writeInt(this.active.length);

      for (String s : this.active) {
         writeString(buf, s);
      }

      buf.writeInt(this.forced.length);

      for (String s : this.forced) {
         writeString(buf, s);
      }
   }

   public void fromBytes(ByteBuf buf) {
      this.dim = buf.readInt();
      this.phase = buf.readInt();
      this.nightIndex = buf.readLong();
      int a = buf.readInt();
      this.active = new String[a];

      for (int i = 0; i < a; i++) {
         this.active[i] = readString(buf);
      }

      int f = buf.readInt();
      this.forced = new String[f];

      for (int i = 0; i < f; i++) {
         this.forced[i] = readString(buf);
      }
   }

   private static void writeString(ByteBuf buf, String s) {
      byte[] b = s.getBytes(StandardCharsets.UTF_8);
      buf.writeInt(b.length);
      buf.writeBytes(b);
   }

   private static String readString(ByteBuf buf) {
      int len = buf.readInt();
      byte[] b = new byte[len];
      buf.readBytes(b);
      return new String(b, StandardCharsets.UTF_8);
   }

   public static class Handler implements IMessageHandler<PacketCelestialNightState, IMessage> {
      public IMessage onMessage(PacketCelestialNightState msg, MessageContext ctx) {
         Minecraft.func_71410_x().func_152344_a(() -> {
            Set<String> a = new HashSet<>();

            for (String s : msg.active) {
               a.add(s);
            }

            Set<String> f = new HashSet<>();

            for (String s : msg.forced) {
               f.add(s);
            }

            CelestialPhaseClient.setServerNightState(msg.dim, msg.phase, msg.nightIndex, a, f);
         });
         return null;
      }
   }
}
