/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.particle.IParticleFactory
 *  net.minecraft.client.particle.Particle
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.dhanantry.scapeandrunparasites.client.particle;

import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class ParticleFog
extends Particle {
    public static final ResourceLocation PARTICLES_TEXTURE = new ResourceLocation("srparasites", "particle/fog");

    public ParticleFog(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int r, int g, int b) {
        super(worldIn, xCoordIn + 0.0, yCoordIn, zCoordIn + 0.0, xSpeedIn, ySpeedIn, zSpeedIn);
        this.field_187129_i = xSpeedIn * (double)0.2f + (Math.random() * 2.0 - 1.0) * (double)0.02f;
        this.field_187130_j = ySpeedIn * (double)0.2f + (Math.random() * 2.0 - 1.0) * (double)0.02f;
        this.field_187131_k = zSpeedIn * (double)0.2f + (Math.random() * 2.0 - 1.0) * (double)0.02f;
        this.field_70552_h = r;
        this.field_70553_i = g;
        this.field_70551_j = b;
        this.field_70544_f = 0.0f;
        this.func_82338_g(0.8f);
        this.field_70547_e = 200;
        this.field_70545_g = 0.0f;
    }

    public Particle func_70543_e(float multiplier) {
        this.field_187129_i *= (double)multiplier;
        this.field_187130_j *= (double)multiplier;
        this.field_187131_k *= (double)multiplier;
        return this;
    }

    public void func_189213_a() {
        this.field_187123_c = this.field_187126_f;
        this.field_187124_d = this.field_187127_g;
        this.field_187125_e = this.field_187128_h;
        if ((double)this.field_70544_f < 8.0 && this.field_70546_d < 175) {
            this.field_70544_f = (float)((double)this.field_70544_f + 0.3);
        }
        if (this.field_70546_d++ >= this.field_70547_e) {
            this.func_187112_i();
        }
        this.func_70536_a(7 - this.field_70546_d * 8 / this.field_70547_e);
        if (this.field_70546_d >= 175) {
            this.field_70544_f = (float)((double)this.field_70544_f - 0.3);
        }
        this.field_187130_j -= 0.04 * (double)this.field_70545_g;
        this.func_187110_a(this.field_187129_i, this.field_187130_j, this.field_187131_k);
        this.field_187129_i *= (double)0.98f;
        this.field_187130_j += 1.0E-16;
        this.field_187131_k *= (double)0.98f;
        if (this.field_187132_l) {
            this.field_187129_i *= (double)0.7f;
            this.field_187131_k *= (double)0.7f;
            this.field_187130_j += 1.0E-5;
        }
    }

    public int func_70537_b() {
        return 0;
    }

    @SideOnly(value=Side.CLIENT)
    public static class Factory
    implements IParticleFactory {
        public Particle func_178902_a(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int ... p_178902_15_) {
            return new ParticleFog(worldIn, xCoordIn, yCoordIn, zCoordIn, (float)xSpeedIn, (float)ySpeedIn, (float)zSpeedIn, 0, 0, 0);
        }
    }
}

