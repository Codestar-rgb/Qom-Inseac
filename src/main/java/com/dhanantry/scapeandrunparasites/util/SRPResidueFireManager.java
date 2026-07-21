package com.dhanantry.scapeandrunparasites.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent.Unload;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

@EventBusSubscriber(modid = "srparasites")
public final class SRPResidueFireManager {
   private static final Map<Integer, Map<BlockPos, Integer>> TRACK = new HashMap<>();

   private SRPResidueFireManager() {
   }

   public static void lightAndTrack(World w, BlockPos pos, int ttl) {
      if (!w.field_72995_K) {
         if (ttl > 0) {
            if (w.func_175623_d(pos)) {
               w.func_180501_a(pos, Blocks.field_150480_ab.func_176223_P(), 3);
            }

            TRACK.computeIfAbsent(w.field_73011_w.getDimension(), d -> new HashMap<>()).put(pos.func_185334_h(), ttl);
         }
      }
   }

   @SubscribeEvent
   public static void onWorldTick(WorldTickEvent e) {
      if (e.phase == Phase.END && !e.world.field_72995_K) {
         int dim = e.world.field_73011_w.getDimension();
         Map<BlockPos, Integer> m = TRACK.get(dim);
         if (m != null && !m.isEmpty()) {
            Iterator<Entry<BlockPos, Integer>> it = m.entrySet().iterator();

            while (it.hasNext()) {
               Entry<BlockPos, Integer> en = it.next();
               int left = en.getValue() - 1;
               if (left <= 0) {
                  BlockPos p = en.getKey();
                  if (e.world.func_180495_p(p).func_177230_c() == Blocks.field_150480_ab) {
                     e.world.func_175698_g(p);
                  }

                  it.remove();
               } else {
                  en.setValue(left);
               }
            }

            if (m.isEmpty()) {
               TRACK.remove(dim);
            }
         }
      }
   }

   @SubscribeEvent
   public static void onWorldUnload(Unload e) {
      if (!e.getWorld().field_72995_K) {
         TRACK.remove(e.getWorld().field_73011_w.getDimension());
      }
   }
}
