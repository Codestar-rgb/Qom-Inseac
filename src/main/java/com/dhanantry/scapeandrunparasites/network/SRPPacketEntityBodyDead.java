package com.dhanantry.scapeandrunparasites.network;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityBodyParts;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SRPPacketEntityBodyDead implements IMessage {
   private int targetId;
   private int partId;

   public SRPPacketEntityBodyDead() {
   }

   public SRPPacketEntityBodyDead(int target, int part) {
      this.targetId = target;
      this.partId = part;
   }

   public void fromBytes(ByteBuf buf) {
      this.targetId = ByteBufUtils.readVarInt(buf, 4);
      this.partId = ByteBufUtils.readVarInt(buf, 4);
   }

   public void toBytes(ByteBuf buf) {
      ByteBufUtils.writeVarInt(buf, this.targetId, 4);
      ByteBufUtils.writeVarInt(buf, this.partId, 4);
   }

   public static class Handler implements IMessageHandler<SRPPacketEntityBodyDead, IMessage> {
      public IMessage onMessage(SRPPacketEntityBodyDead message, MessageContext ctx) {
         FMLCommonHandler.instance().getWorldThread(ctx.netHandler).func_152344_a(() -> this.handle(message, ctx));
         return null;
      }

      private void handle(SRPPacketEntityBodyDead message, MessageContext ctx) {
         Entity target = Minecraft.func_71410_x().field_71441_e.func_73045_a(message.targetId);
         if (target != null && target instanceof EntityBodyParts) {
            EntityBodyParts rTarget = (EntityBodyParts)target;
            rTarget.setBodyPartDead(message.partId);
         }
      }
   }
}
