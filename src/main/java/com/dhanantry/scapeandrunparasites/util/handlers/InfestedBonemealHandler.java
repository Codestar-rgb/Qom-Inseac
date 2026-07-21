package com.dhanantry.scapeandrunparasites.util.handlers;

import com.dhanantry.scapeandrunparasites.block.BlockParasiteSapling;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;

public class InfestedBonemealHandler {
   @SubscribeEvent
   public void onBonemeal(BonemealEvent e) {
      if (e.getStack() != null && e.getStack().func_77973_b() == SRPItems.infestedbonemeal) {
         World world = e.getWorld();
         BlockPos pos = e.getPos();
         Random rand = world.field_73012_v;
         IBlockState state = e.getBlock();
         Block block = state.func_177230_c();
         if (block == SRPBlocks.ParasiteSapling) {
            if (block instanceof BlockParasiteSapling) {
               BlockParasiteSapling sap = (BlockParasiteSapling)block;
               BlockParasiteSapling.EnumType type = (BlockParasiteSapling.EnumType)state.func_177229_b(BlockParasiteSapling.VARIANT);
               boolean growable = type == BlockParasiteSapling.EnumType.CONSUMED
                  || type == BlockParasiteSapling.EnumType.DEADHEAD
                  || type == BlockParasiteSapling.EnumType.INFESTED;
               if (!growable) {
                  e.setResult(Result.DENY);
               } else {
                  boolean grew = false;
                  if ((Integer)state.func_177229_b(BlockParasiteSapling.STAGE) == 0) {
                     grew = world.func_180501_a(pos, state.func_177226_a(BlockParasiteSapling.STAGE, 1), 4);
                  } else {
                     sap.generateTree(world, pos, state, rand);
                     grew = true;
                  }

                  e.setResult(grew ? Result.ALLOW : Result.DENY);
               }
            }
         }
      }
   }
}
