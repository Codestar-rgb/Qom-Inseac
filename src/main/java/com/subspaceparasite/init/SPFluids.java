/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fluids.Fluid
 *  net.minecraftforge.fluids.FluidRegistry
 */
package com.subspaceparasite.init;

import com.subspaceparasite.fluid.DeadBloodFluid;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class SPFluids {
    public static final DeadBloodFluid DEADBLOOD_FLUID = new DeadBloodFluid("deadblood", "subspaceparasite:blocks/deadblood_still", "subspaceparasite:blocks/deadblood_flowing", 500);

    public static void init() {
        SPFluids.registerFluid(DEADBLOOD_FLUID);
    }

    public static void registerFluid(Fluid fluid) {
        FluidRegistry.registerFluid((Fluid)fluid);
        FluidRegistry.addBucketForFluid((Fluid)fluid);
    }
}

