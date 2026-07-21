package com.dhanantry.scapeandrunparasites.init;

import com.dhanantry.scapeandrunparasites.fluid.DeadBloodFluid;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class SRPFluids {
   public static final DeadBloodFluid DEADBLOOD_FLUID = new DeadBloodFluid(
      "deadblood", "srparasites:blocks/deadblood_still", "srparasites:blocks/deadblood_flowing", 500
   );

   public static void init() {
      registerFluid(DEADBLOOD_FLUID);
   }

   public static void registerFluid(Fluid fluid) {
      FluidRegistry.registerFluid(fluid);
      FluidRegistry.addBucketForFluid(fluid);
   }
}
