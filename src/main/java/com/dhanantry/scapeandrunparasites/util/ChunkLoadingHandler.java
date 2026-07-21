package com.dhanantry.scapeandrunparasites.util;

import java.util.List;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.LoadingCallback;
import net.minecraftforge.common.ForgeChunkManager.Ticket;

public class ChunkLoadingHandler implements LoadingCallback {
   public void ticketsLoaded(List<Ticket> tickets, World world) {
      for (Ticket ticket : tickets) {
         NBTTagCompound tag = ticket.getModData();
         if (tag.func_74764_b("x") && tag.func_74764_b("z") && tag.func_74764_b("e")) {
            int x = tag.func_74762_e("x");
            int z = tag.func_74762_e("z");
            ticket.bindEntity(world.func_73045_a(tag.func_74762_e("e")));
            BlockPos pos = new BlockPos(x, 0, z);
            ChunkPos chunkPos = new ChunkPos(pos);
            ForgeChunkManager.forceChunk(ticket, chunkPos);
         }
      }
   }
}
