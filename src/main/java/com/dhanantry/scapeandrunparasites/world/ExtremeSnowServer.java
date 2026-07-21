package com.dhanantry.scapeandrunparasites.world;

import java.util.Collections;
import java.util.List;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(modid = "srparasites", value = Side.SERVER)
public final class ExtremeSnowServer {
   private ExtremeSnowServer() {
   }

   @SubscribeEvent
   public static void onWorldTick(WorldTickEvent e) {
      if (e.phase == Phase.END) {
         World w = e.world;
         ExtremeSnowData data = ExtremeSnowData.get(w);
         if (data.isEnabled()) {
            List<EntityPlayerMP> players = w.func_73046_m() != null ? w.func_73046_m().func_184103_al().func_181057_v() : Collections.emptyList();
            int triesPerPlayer = (int)(12.0F + 48.0F * data.getIntensity());
            int radius = 18;

            for (EntityPlayerMP p : players) {
               if (p.field_70170_p == w) {
                  for (int i = 0; i < triesPerPlayer; i++) {
                     int x = (int)(p.field_70165_t + (w.field_73012_v.nextInt(radius * 2 + 1) - radius));
                     int z = (int)(p.field_70161_v + (w.field_73012_v.nextInt(radius * 2 + 1) - radius));
                     BlockPos base = new BlockPos(x, (int)p.field_70163_u, z);
                     BlockPos hit = w.func_175725_q(base);
                     boolean force = ExtremeSnowData.get(w).isForceAnywhere();
                     if (w.func_175678_i(hit) && (force || w.func_175708_f(hit, false))) {
                        IBlockState stateAt = w.func_180495_p(hit);
                        if (stateAt.func_177230_c() == Blocks.field_150431_aC) {
                           int layers = (Integer)stateAt.func_177229_b(BlockSnow.field_176315_a);
                           if (layers < 8) {
                              w.func_180501_a(hit, stateAt.func_177226_a(BlockSnow.field_176315_a, layers + 1), 2);
                           }
                        } else if (stateAt.func_177230_c().isAir(stateAt, w, hit)) {
                           w.func_180501_a(hit, Blocks.field_150431_aC.func_176223_P().func_177226_a(BlockSnow.field_176315_a, 1), 2);
                        }
                     }
                  }
               }
            }
         }
      }
   }
}
