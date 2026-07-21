package com.dhanantry.scapeandrunparasites.client.gui;

import net.minecraft.block.BlockWorkbench;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ContainerConsumedWorkbench extends ContainerWorkbench {
   private final World world;
   private final BlockPos pos;

   public ContainerConsumedWorkbench(InventoryPlayer playerInv, World world, BlockPos pos) {
      super(playerInv, world, pos);
      this.world = world;
      this.pos = pos;
   }

   public boolean func_75145_c(EntityPlayer playerIn) {
      return this.world.func_180495_p(this.pos).func_177230_c() instanceof BlockWorkbench;
   }
}
