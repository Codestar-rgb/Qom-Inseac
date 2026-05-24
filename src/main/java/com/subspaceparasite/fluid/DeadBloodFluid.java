/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fluids.Fluid
 */
package com.subspaceparasite.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class DeadBloodFluid
extends Fluid {
    public DeadBloodFluid(String fluidName, String still, String flowing, int visco) {
        super(fluidName, new ResourceLocation(still), new ResourceLocation(flowing));
        this.setUnlocalizedName(fluidName);
        this.setDensity(3000);
        this.setViscosity(Math.max(1500, visco));
        this.setGaseous(false);
        this.setTemperature(310);
        this.setLuminosity(0);
    }
}

