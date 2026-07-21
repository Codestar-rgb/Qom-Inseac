package com.dhanantry.scapeandrunparasites.network.msg;

import com.dhanantry.scapeandrunparasites.SRPMain;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class AbstractPacket<T extends AbstractPacket<T>> implements IMessage, IMessageHandler<T, IMessage> {
   public IMessage onMessage(T message, MessageContext messageContext) {
      SRPMain.proxy.networkMessage(message, messageContext);
      return null;
   }

   @SideOnly(Side.CLIENT)
   public abstract void clientSide(Minecraft var1, T var2, EntityPlayer var3, MessageContext var4);

   public abstract void serverSide(MinecraftServer var1, T var2, EntityPlayerMP var3, MessageContext var4);
}
