/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 */
package com.dhanantry.scapeandrunparasites.network;

import com.dhanantry.scapeandrunparasites.network.MsgScanCooldown;
import com.dhanantry.scapeandrunparasites.network.SRPNetwork;
import com.dhanantry.scapeandrunparasites.tileentity.TileEntityRelayController;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MsgRequestScan
implements IMessage {
    private BlockPos pos;

    public MsgRequestScan() {
    }

    public MsgRequestScan(BlockPos pos) {
        this.pos = pos;
    }

    public void toBytes(ByteBuf buf) {
        buf.writeLong(this.pos.func_177986_g());
    }

    public void fromBytes(ByteBuf buf) {
        this.pos = BlockPos.func_177969_a((long)buf.readLong());
    }

    public static class Handler
    implements IMessageHandler<MsgRequestScan, IMessage> {
        public IMessage onMessage(MsgRequestScan msg, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().field_147369_b;
            WorldServer world = player.func_71121_q();
            world.func_152344_a(() -> {
                boolean ok;
                TileEntity te = world.func_175625_s(msg.pos);
                if (!(te instanceof TileEntityRelayController)) {
                    return;
                }
                TileEntityRelayController rc = (TileEntityRelayController)te;
                boolean started = false;
                if (rc.canScan() && (ok = rc.performScan(player))) {
                    rc.startCooldown();
                    started = true;
                }
                int remaining = rc.getCooldownRemainingTicks();
                int total = rc.getCooldownTotalTicks();
                SRPNetwork.CHANNEL.sendTo((IMessage)new MsgScanCooldown(msg.pos, total, remaining, started), player);
            });
            return null;
        }
    }
}

