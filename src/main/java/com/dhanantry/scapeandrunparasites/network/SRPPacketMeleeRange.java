/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.SoundEvents
 *  net.minecraftforge.fml.common.network.ByteBufUtils
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 */
package com.dhanantry.scapeandrunparasites.network;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.item.tool.IHaveReach;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SRPPacketMeleeRange
implements IMessage {
    private int entityId;

    public SRPPacketMeleeRange() {
    }

    public SRPPacketMeleeRange(int parEntityId) {
        this.entityId = parEntityId;
    }

    public void fromBytes(ByteBuf buf) {
        this.entityId = ByteBufUtils.readVarInt((ByteBuf)buf, (int)4);
    }

    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeVarInt((ByteBuf)buf, (int)this.entityId, (int)4);
    }

    public static class Handler
    implements IMessageHandler<SRPPacketMeleeRange, IMessage> {
        public IMessage onMessage(final SRPPacketMeleeRange message, MessageContext ctx) {
            final EntityPlayerMP thePlayer = SRPMain.proxy.getPlayerEntityFromContext(ctx);
            thePlayer.func_184102_h().func_152344_a(new Runnable(){

                @Override
                public void run() {
                    Entity theEntity = thePlayer.field_70170_p.func_73045_a(message.entityId);
                    if (thePlayer.func_184607_cu() == null || theEntity == null) {
                        return;
                    }
                    if (thePlayer.func_184614_ca().func_77973_b() instanceof IHaveReach) {
                        IHaveReach theExtendedReachWeapon = (IHaveReach)thePlayer.func_184614_ca().func_77973_b();
                        double distanceSq = thePlayer.func_70068_e(theEntity);
                        double reachSq = theExtendedReachWeapon.getReach() * theExtendedReachWeapon.getReach();
                        if (reachSq >= distanceSq) {
                            thePlayer.func_71059_n(theEntity);
                            thePlayer.field_70170_p.func_184148_a((EntityPlayer)null, thePlayer.field_70165_t, thePlayer.field_70163_u, thePlayer.field_70161_v, SoundEvents.field_187730_dW, thePlayer.func_184176_by(), 1.0f, 1.0f);
                            thePlayer.func_184810_cG();
                        }
                    }
                }
            });
            return null;
        }
    }
}

