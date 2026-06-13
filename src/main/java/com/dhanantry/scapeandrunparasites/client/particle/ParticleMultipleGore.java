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

public class ParticleMultipleGore
extends Particle {
    public static final ResourceLocation PARTICLES_TEXTUREINFECTED = new ResourceLocation("srparasites", "particle/splashi");
    public static final ResourceLocation PARTICLES_TEXTURESPIDER = new ResourceLocation("srparasites", "particle/splashd");
    public static final ResourceLocation PARTICLES_TEXTUREPRIMITIVE = new ResourceLocation("srparasites", "particle/splashp");
    public static final ResourceLocation PARTICLES_TEXTUREADAPTED = new ResourceLocation("srparasites", "particle/splasha");
    public static final ResourceLocation PARTICLES_TEXTUREVOMIT = new ResourceLocation("srparasites", "particle/splashv");

    protected ParticleMultipleGore(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int texture) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0.0, 0.0, 0.0);
        if (texture == 0) {
            this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(PARTICLES_TEXTUREINFECTED.toString()));
        } else if (texture == 1) {
            this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(PARTICLES_TEXTURESPIDER.toString()));
        } else if (texture == 2) {
            this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(PARTICLES_TEXTUREPRIMITIVE.toString()));
        } else if (texture == 3) {
            this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(PARTICLES_TEXTUREADAPTED.toString()));
        } else if (texture == 4) {
            this.func_187117_a(Minecraft.func_71410_x().func_147117_R().func_110572_b(PARTICLES_TEXTUREVOMIT.toString()));
        }
        this.field_187129_i = xSpeedIn;
        this.field_187130_j = ySpeedIn;
        this.field_187131_k = zSpeedIn;
        this.func_187115_a(0.01f, 0.01f);
        this.field_70545_g = 0.06f;
        this.field_70547_e = (int)(8.0 / (Math.random() * 0.8 + 0.2));
        this.field_70545_g = 0.04f;
        this.field_70547_e = 20 * (this.field_187136_p.nextInt(3) + 1);
    }

    public void func_189213_a() {
        this.field_187123_c = this.field_187126_f;
        this.field_187124_d = this.field_187127_g;
        this.field_187125_e = this.field_187128_h;
        this.field_187130_j -= (double)this.field_70545_g;
        this.func_187110_a(this.field_187129_i, this.field_187130_j, this.field_187131_k);
        this.field_187129_i *= (double)0.98f;
        this.field_187130_j *= (double)0.98f;
        this.field_187131_k *= (double)0.98f;
        if (this.field_70547_e-- <= 0) {
            this.func_187112_i();
        }
        if (this.field_187132_l) {
            this.field_187129_i *= (double)0.7f;
            this.field_187131_k *= (double)0.7f;
        }
    }

    public int func_70537_b() {
        return 1;
    }
}

