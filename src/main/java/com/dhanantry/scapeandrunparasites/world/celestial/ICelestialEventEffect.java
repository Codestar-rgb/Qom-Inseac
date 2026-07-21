package com.dhanantry.scapeandrunparasites.world.celestial;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import javax.annotation.Nullable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public interface ICelestialEventEffect {
   default void onNightStart(World world, int dim, int phase, long nightIndex) {
   }

   default void onNightEnd(World world, int dim, int phase, long nightIndex) {
   }

   default void onParasiteSpawn(EntityParasiteBase parasite, @Nullable EntityLivingBase spawner) {
   }
}
