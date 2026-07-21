package com.dhanantry.scapeandrunparasites.item;

import com.dhanantry.scapeandrunparasites.world.SRPWorldData;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CompassOrigin extends ItemCompass {
   public static BlockPos orig;

   public CompassOrigin(String name) {
      super(name, 3);
   }

   @Override
   public BlockPos getOrigin(Entity entityIn, World worldIn) {
      SRPWorldData aaa = SRPWorldData.get(worldIn);
      return aaa.nearestInfectionPosition(true, entityIn.func_180425_c());
   }

   @Override
   public BlockPos getOri() {
      return orig;
   }
}
