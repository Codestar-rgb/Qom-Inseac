/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.MinecraftServer
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.network.msg;

import com.subspaceparasite.SPMain;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class AbstractPacket<T extends AbstractPacket<T>>
implements IMessage,
IMessageHandler<T, IMessage> {
    public IMessage onMessage(T message, MessageContext messageContext) {
        SPMain.proxy.networkMessage(message, messageContext);
        return null;
    }

    @SideOnly(value=Side.CLIENT)
    public abstract void clientSide(Minecraft var1, T var2, EntityPlayer var3, MessageContext var4);

    public abstract void serverSide(MinecraftServer var1, T var2, EntityPlayerMP var3, MessageContext var4);
}

