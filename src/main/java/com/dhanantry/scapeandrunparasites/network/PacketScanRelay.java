/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.dhanantry.scapeandrunparasites.network;

import com.dhanantry.scapeandrunparasites.tileentity.TileEntityRelayController;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketScanRelay
implements IMessage {
    private int x;
    private int y;
    private int z;

    public PacketScanRelay() {
    }

    public PacketScanRelay(BlockPos pos) {
        this.x = pos.func_177958_n();
        this.y = pos.func_177956_o();
        this.z = pos.func_177952_p();
    }

    public BlockPos getPos() {
        return new BlockPos(this.x, this.y, this.z);
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
    }

    public void fromBytes(ByteBuf buf) {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
    }

    public static class Handler
    implements IMessageHandler<PacketScanRelay, IMessage> {
        public IMessage onMessage(PacketScanRelay msg, MessageContext ctx) {
            if (ctx.side != Side.SERVER) {
                return null;
            }
            EntityPlayerMP player = ctx.getServerHandler().field_147369_b;
            player.func_71121_q().func_152344_a(() -> {
                TileEntity te = player.field_70170_p.func_175625_s(msg.getPos());
                if (te instanceof TileEntityRelayController) {
                    ((TileEntityRelayController)te).performScan(player);
                }
            });
            return null;
        }
    }
}

