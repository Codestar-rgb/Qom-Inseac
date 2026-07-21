package com.dhanantry.scapeandrunparasites.network;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import com.dhanantry.scapeandrunparasites.world.SRPWorldData;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SRPPacketRequestEvoPhaseClient implements IMessage {
   public void fromBytes(ByteBuf buf) {
   }

   public void toBytes(ByteBuf buf) {
   }

   public static class Handler implements IMessageHandler<SRPPacketRequestEvoPhaseClient, IMessage> {
      public IMessage onMessage(SRPPacketRequestEvoPhaseClient message, MessageContext ctx) {
         final EntityPlayerMP thePlayer = SRPMain.proxy.getPlayerEntityFromContext(ctx);
         thePlayer.func_184102_h()
            .func_152344_a(
               new Runnable() {
                  @Override
                  public void run() {
                     SRPMain.network
                        .sendTo(
                           new SRPPacketUpdateEvoPhaseClient(
                              SRPSaveData.get(thePlayer.field_70170_p, 110).getEvolutionPhase(thePlayer.field_70170_p.field_73011_w.getDimension()),
                              SRPWorldData.get(thePlayer.field_70170_p).nearestInfectionPosition(false, thePlayer.func_180425_c()) != null
                           ),
                           thePlayer
                        );
                  }
               }
            );
         return null;
      }
   }
}
