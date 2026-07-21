package com.dhanantry.scapeandrunparasites.util;

import com.dhanantry.scapeandrunparasites.block.BlockInfestedRemain;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = "srparasites")
public final class SRPRemainHooks {
   private SRPRemainHooks() {
   }

   @SubscribeEvent
   public static void onLightningSpawn(EntityJoinWorldEvent e) {
      if (e.getEntity() instanceof EntityLightningBolt) {
         World w = e.getWorld();
         if (!w.field_72995_K) {
            BlockPos strike = e.getEntity().func_180425_c();
            int r = 5;
            MutableBlockPos cur = new MutableBlockPos();

            for (int dx = -r; dx <= r; dx++) {
               for (int dy = -r; dy <= r; dy++) {
                  for (int dz = -r; dz <= r; dz++) {
                     cur.func_181079_c(strike.func_177958_n() + dx, strike.func_177956_o() + dy, strike.func_177952_p() + dz);
                     IBlockState s = w.func_180495_p(cur);
                     if (s.func_177230_c() instanceof BlockInfestedRemain) {
                        w.func_175698_g(cur);
                     }
                  }
               }
            }
         }
      }
   }
}
