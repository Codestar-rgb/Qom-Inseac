package com.dhanantry.scapeandrunparasites.network;

import com.dhanantry.scapeandrunparasites.client.fx.ParticleVengeance;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketVengeanceFX implements IMessage {
   public static final byte SPARKS = 0;
   public static final byte IMPACT_DUST = 1;
   public static final byte HEAVY_BLEED = 2;
   public static final byte LIGHTNING_EXPL = 3;
   private byte type;
   private double x;
   private double y;
   private double z;
   private float a;
   private int count;

   public PacketVengeanceFX() {
   }

   public PacketVengeanceFX(byte type, Vec3d pos, float a, int count) {
      this.type = type;
      this.x = pos.field_72450_a;
      this.y = pos.field_72448_b;
      this.z = pos.field_72449_c;
      this.a = a;
      this.count = count;
   }

   public void toBytes(ByteBuf buf) {
      buf.writeByte(this.type);
      buf.writeDouble(this.x);
      buf.writeDouble(this.y);
      buf.writeDouble(this.z);
      buf.writeFloat(this.a);
      buf.writeInt(this.count);
   }

   public void fromBytes(ByteBuf buf) {
      this.type = buf.readByte();
      this.x = buf.readDouble();
      this.y = buf.readDouble();
      this.z = buf.readDouble();
      this.a = buf.readFloat();
      this.count = buf.readInt();
   }

   public static class Handler implements IMessageHandler<PacketVengeanceFX, IMessage> {
      public IMessage onMessage(PacketVengeanceFX msg, MessageContext ctx) {
         if (!FMLCommonHandler.instance().getSide().isClient()) {
            return null;
         } else {
            Minecraft.func_71410_x().func_152344_a(() -> {
               Vec3d pos = new Vec3d(msg.x, msg.y, msg.z);
               switch (msg.type) {
                  case 0:
                     ParticleVengeance.electricSparksAround(pos, msg.a, msg.count);
                     break;
                  case 1:
                     ParticleVengeance.impactDust(pos, msg.count);
                     break;
                  case 2:
                     ParticleVengeance.heavyBleedAround(pos, msg.count);
                     break;
                  case 3:
                     ParticleVengeance.lightningExplosion(pos);
               }
            });
            return null;
         }
      }
   }
}
