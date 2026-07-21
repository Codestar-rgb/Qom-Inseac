package com.dhanantry.scapeandrunparasites.network.msg;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class C2SRequestEscape implements IMessage, IMessageHandler<C2SRequestEscape, IMessage> {
   public void toBytes(ByteBuf buf) {
   }

   public void fromBytes(ByteBuf buf) {
   }

   public IMessage onMessage(C2SRequestEscape msg, MessageContext ctx) {
      ctx.getServerHandler().field_147369_b.getEntityData().func_74775_l("PlayerPersisted").func_74757_a("srp_escape_pending", true);
      return null;
   }
}
