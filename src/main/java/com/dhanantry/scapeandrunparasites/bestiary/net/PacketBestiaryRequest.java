package com.dhanantry.scapeandrunparasites.bestiary.net;

import com.dhanantry.scapeandrunparasites.bestiary.cap.BestiaryCapability;
import com.dhanantry.scapeandrunparasites.bestiary.cap.IBestiaryProgress;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketBestiaryRequest implements IMessage {
   private static final boolean DEBUG = false;

   public void toBytes(ByteBuf buf) {
   }

   public void fromBytes(ByteBuf buf) {
   }

   public static class Handler implements IMessageHandler<PacketBestiaryRequest, IMessage> {
      public IMessage onMessage(PacketBestiaryRequest msg, MessageContext ctx) {
         EntityPlayerMP mp = ctx.getServerHandler().field_147369_b;
         mp.func_71121_q().func_152344_a(() -> {
            IBestiaryProgress prog = (IBestiaryProgress)mp.getCapability(BestiaryCapability.CAP, null);
            if (prog == null) {
               System.out.println("[SRP][BESTIARY] Request: player has NO capability");
            } else {
               BestiaryNetwork.CH.sendTo(new PacketBestiarySync(prog), mp);
            }
         });
         return null;
      }
   }
}
