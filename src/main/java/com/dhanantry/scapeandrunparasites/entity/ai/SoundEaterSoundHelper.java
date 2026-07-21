package com.dhanantry.scapeandrunparasites.entity.ai;

import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfHuman;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SoundEaterSoundHelper {
   public static void broadcastSound(World world, BlockPos pos, double radius, int lifeTicks) {
      if (world != null && !world.field_72995_K) {
         AxisAlignedBB box = new AxisAlignedBB(pos).func_72314_b(radius, radius, radius);

         for (EntityInfHuman human : world.func_72872_a(EntityInfHuman.class, box)) {
            if (human.getSkin() == 111) {
               human.notifyHeardSound(pos, lifeTicks);
            }
         }
      }
   }
}
