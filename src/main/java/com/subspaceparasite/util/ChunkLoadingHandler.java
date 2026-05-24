/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.ChunkPos
 *  net.minecraft.world.World
 *  net.minecraftforge.common.ForgeChunkManager
 *  net.minecraftforge.common.ForgeChunkManager$LoadingCallback
 *  net.minecraftforge.common.ForgeChunkManager$Ticket
 */
package com.subspaceparasite.util;

import java.util.List;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;

public class ChunkLoadingHandler
implements ForgeChunkManager.LoadingCallback {
    public void ticketsLoaded(List<ForgeChunkManager.Ticket> tickets, World world) {
        for (ForgeChunkManager.Ticket ticket : tickets) {
            NBTTagCompound tag = ticket.getModData();
            if (!tag.func_74764_b("x") || !tag.func_74764_b("z") || !tag.func_74764_b("e")) continue;
            int x = tag.func_74762_e("x");
            int z = tag.func_74762_e("z");
            ticket.bindEntity(world.func_73045_a(tag.func_74762_e("e")));
            BlockPos pos = new BlockPos(x, 0, z);
            ChunkPos chunkPos = new ChunkPos(pos);
            ForgeChunkManager.forceChunk((ForgeChunkManager.Ticket)ticket, (ChunkPos)chunkPos);
        }
    }
}

