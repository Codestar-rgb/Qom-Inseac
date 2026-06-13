/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.particle.Particle
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleBiomass
extends Particle {
    public static final ResourceLocation PARTICLES_TEXTURE = new ResourceLocation("srparasites", "particle/biomass");

    protected ParticleBiomass(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn);
        this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(PARTICLES_TEXTURE.toString()));
        this.field_70544_f = 3.0f;
        this.field_70547_e = 35;
        this.field_187129_i = xSpeedIn;
        this.field_187130_j = ySpeedIn;
        this.field_187131_k = zSpeedIn;
    }

    public void func_189213_a() {
        this.field_187123_c = this.field_187126_f;
        this.field_187124_d = this.field_187127_g;
        this.field_187125_e = this.field_187128_h;
        if (this.field_70546_d++ >= this.field_70547_e) {
            this.func_187112_i();
        }
        if (this.field_70546_d >= 20) {
            this.field_70544_f = (float)((double)this.field_70544_f - 0.1);
        }
        this.field_187130_j += 0.005;
        this.func_187110_a(this.field_187129_i, this.field_187130_j, this.field_187131_k);
        this.field_187129_i *= (double)0.98f;
        this.field_187130_j *= (double)0.98f;
        this.field_187131_k *= (double)0.98f;
        if (this.field_187132_l) {
            this.field_187129_i *= (double)0.7f;
            this.field_187131_k *= (double)0.7f;
        }
    }

    public int func_70537_b() {
        return 1;
    }
}

