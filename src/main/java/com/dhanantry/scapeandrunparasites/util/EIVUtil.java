package com.dhanantry.scapeandrunparasites.util;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import com.dhanantry.scapeandrunparasites.world.SRPWorldData;
import com.dhanantry.scapeandrunparasites.world.SRPWorldEntitySpawner;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EIVUtil {
   public static void createRandomOrigin(World worldIn, int min, int max) {
      if (SRPConfigWorld.originActivated) {
         if (!SRPWorldEntitySpawner.triggerSPAWNING) {
            SRPWorldData data = SRPWorldData.get(worldIn);
            int number = data.getorigins("x").size();
            if (number == 0) {
               if (worldIn.field_73012_v.nextInt(SRPConfigWorld.originCreatingRandZero) != 0) {
                  return;
               }
            } else if (worldIn.field_73012_v.nextInt(SRPConfigWorld.originCreatingRand) != 0) {
               return;
            }

            if (!worldIn.field_73010_i.isEmpty()) {
               EntityPlayer player = (EntityPlayer)worldIn.field_73010_i.get(worldIn.field_73012_v.nextInt(worldIn.field_73010_i.size()));
               double distance;
               if (max <= min) {
                  distance = min;
               } else {
                  distance = min + worldIn.field_73012_v.nextDouble() * (max - min);
               }

               double angle = worldIn.field_73012_v.nextDouble() * Math.PI * 2.0;
               int dx = (int)Math.round(Math.cos(angle) * distance);
               int dz = (int)Math.round(Math.sin(angle) * distance);
               BlockPos pos = player.func_180425_c().func_177982_a(dx, 64, dz);
               int key = data.setOrigin(
                  worldIn, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), SRPConfigWorld.originHealth, SRPConfigWorld.originRadius
               );
               if (key == 1 && !SRPConfigWorld.originNewMess.isEmpty()) {
                  ParasiteEventEntity.alertAllPlayerDim(worldIn, SRPConfigWorld.originNewMess, 400);
               }

               if (key == 2 && !SRPConfigWorld.originNewOutbreakMess.isEmpty()) {
                  ParasiteEventEntity.alertAllPlayerSer(worldIn, SRPConfigWorld.originNewOutbreakMess, 401);
               }

               double horizontalDistance = Math.sqrt(dx * dx + dz * dz);
               double trueDistance = player.func_70011_f(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
               SRPMain.logger
                  .debug(
                     "[EIV DEBUG] RANDOM_ORIGIN result. Player={} playerPos={} originPos={} dx={} dz={} horizontalDistance={} trueDistance={} min={} max={} health={} radius={} resultKey={} totalOrigins={}",
                     new Object[]{
                        player.func_70005_c_(),
                        player.func_180425_c(),
                        pos,
                        dx,
                        dz,
                        String.format("%.2f", horizontalDistance),
                        String.format("%.2f", trueDistance),
                        min,
                        max,
                        SRPConfigWorld.originHealth,
                        SRPConfigWorld.originRadius,
                        key,
                        data.getorigins("x").size()
                     }
                  );
            }
         }
      }
   }
}
