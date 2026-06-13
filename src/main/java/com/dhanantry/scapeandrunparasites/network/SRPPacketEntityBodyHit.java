/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.util.DamageSource
 *  net.minecraftforge.fml.common.network.ByteBufUtils
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 */
package com.dhanantry.scapeandrunparasites.network;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityBodyParts;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SRPPacketEntityBodyHit
implements IMessage {
    private int targetId;
    private int partId;

    public SRPPacketEntityBodyHit() {
    }

    public SRPPacketEntityBodyHit(int target, int part) {
        this.targetId = target;
        this.partId = part;
    }

    public void fromBytes(ByteBuf buf) {
        this.targetId = ByteBufUtils.readVarInt((ByteBuf)buf, (int)4);
        this.partId = ByteBufUtils.readVarInt((ByteBuf)buf, (int)4);
    }

    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeVarInt((ByteBuf)buf, (int)this.targetId, (int)4);
        ByteBufUtils.writeVarInt((ByteBuf)buf, (int)this.partId, (int)4);
    }

    public static class Handler
    implements IMessageHandler<SRPPacketEntityBodyHit, IMessage> {
        public IMessage onMessage(final SRPPacketEntityBodyHit message, MessageContext ctx) {
            final EntityPlayerMP thePlayer = SRPMain.proxy.getPlayerEntityFromContext(ctx);
            thePlayer.func_184102_h().func_152344_a(new Runnable(){

                @Override
                public void run() {
                    Entity target = thePlayer.field_70170_p.func_73045_a(message.targetId);
                    if (target == null) {
                        return;
                    }
                    if (target instanceof EntityBodyParts) {
                        float dmg = (float)thePlayer.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
                        float f3 = thePlayer.func_184825_o(0.0f) * dmg;
                        EntityBodyParts rTarget = (EntityBodyParts)target;
                        rTarget.attackEntityBodyFrom(DamageSource.func_76365_a((EntityPlayer)thePlayer), f3, message.partId, true);
                    }
                }
            });
            return null;
        }
    }
}

