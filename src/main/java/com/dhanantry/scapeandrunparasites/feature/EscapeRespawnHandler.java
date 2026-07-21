package com.dhanantry.scapeandrunparasites.feature;

import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;

public class EscapeRespawnHandler {
   private static final String PERSIST_TAG = "PlayerPersisted";
   private static final String PENDING_TAG = "srp_escape_pending";

   @SubscribeEvent
   public void onRespawn(PlayerRespawnEvent e) {
      if (!e.player.field_70170_p.field_72995_K) {
         if (SRPConfigWorld.escapeEnabled) {
            EntityPlayerMP p = (EntityPlayerMP)e.player;
            boolean pending = p.getEntityData().func_74775_l("PlayerPersisted").func_74767_n("srp_escape_pending");
            if (pending) {
               p.getEntityData().func_74775_l("PlayerPersisted").func_74757_a("srp_escape_pending", false);
               EscapeOnDeathHandler.clearOffer(p);
               BlockPos origin = p.func_180425_c();
               int min = Math.max(0, SRPConfigWorld.escapeMinDistance);
               int max = Math.max(min, SRPConfigWorld.escapeMaxDistance);
               BlockPos target = findSafeRandom((WorldServer)p.field_70170_p, origin, min, max, 24);
               if (target != null) {
                  p.field_71135_a
                     .func_147364_a(target.func_177958_n() + 0.5, target.func_177956_o(), target.func_177952_p() + 0.5, p.field_70177_z, p.field_70125_A);
               }
            }
         }
      }
   }

   private static BlockPos findSafeRandom(WorldServer world, BlockPos origin, int min, int max, int tries) {
      Random r = world.field_73012_v;

      for (int i = 0; i < tries; i++) {
         double ang = r.nextDouble() * Math.PI * 2.0;
         int dist = min + r.nextInt(Math.max(1, max - min + 1));
         int dx = origin.func_177958_n() + (int)Math.round(Math.cos(ang) * dist);
         int dz = origin.func_177952_p() + (int)Math.round(Math.sin(ang) * dist);
         BlockPos top = world.func_175672_r(new BlockPos(dx, 0, dz));
         BlockPos solid = descendToSolid(world, top);
         if (solid != null && isSafe(world, solid)) {
            return solid.func_177984_a();
         }
      }

      return null;
   }

   private static BlockPos descendToSolid(WorldServer w, BlockPos start) {
      BlockPos pos = start;

      for (int i = 0; i < 16; i++) {
         Material m = w.func_180495_p(pos).func_185904_a();
         if (m.func_76220_a()) {
            return pos;
         }

         pos = pos.func_177977_b();
         if (pos.func_177956_o() <= 4) {
            break;
         }
      }

      return null;
   }

   private static boolean isSafe(WorldServer w, BlockPos solid) {
      if (solid == null) {
         return false;
      } else {
         Material m = w.func_180495_p(solid).func_185904_a();
         if (m.func_76220_a() && m != Material.field_151584_j) {
            BlockPos feet = solid.func_177984_a();
            BlockPos head = feet.func_177984_a();
            if (!w.func_175623_d(feet) || !w.func_175623_d(head)) {
               return false;
            } else {
               return w.func_180495_p(feet).func_185904_a().func_76224_d() ? false : !w.func_180495_p(head).func_185904_a().func_76224_d();
            }
         } else {
            return false;
         }
      }
   }
}
