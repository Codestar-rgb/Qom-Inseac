/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.particle.Particle
 *  net.minecraft.world.World
 */
package com.subspaceparasite.client.particle;

import com.subspaceparasite.client.particle.ParticleBiomass;
import com.subspaceparasite.client.particle.ParticleEen;
import com.subspaceparasite.client.particle.ParticleFog;
import com.subspaceparasite.client.particle.ParticleMultipleGore;
import com.subspaceparasite.client.particle.ParticleRGBSmoke;
import com.subspaceparasite.client.particle.ParticleRHappy;
import com.subspaceparasite.client.particle.ParticleSpore;
import com.subspaceparasite.client.particle.SPEnumParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;

public class ParticleSpawner {
    private static Minecraft mc = Minecraft.func_71410_x();

    public static Particle spawnParticle(SPEnumParticle type, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int r, int g, int b) {
        if (mc != null && mc.func_175606_aa() != null && ParticleSpawner.mc.field_71452_i != null) {
            int canSpawn = Minecraft.func_71410_x().field_71474_y.field_74362_aa;
            if (canSpawn >= 1) {
                return null;
            }
            Particle part = null;
            if (type == SPEnumParticle.FOG) {
                part = new ParticleFog((World)ParticleSpawner.mc.field_71441_e, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, r, g, b);
            } else if (type == SPEnumParticle.SPORE) {
                part = new ParticleSpore((World)ParticleSpawner.mc.field_71441_e, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
            } else if (type == SPEnumParticle.GCLOUD) {
                part = new ParticleRGBSmoke((World)ParticleSpawner.mc.field_71441_e, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, r, g, b);
            } else if (type == SPEnumParticle.GSPLASH) {
                part = new ParticleMultipleGore((World)ParticleSpawner.mc.field_71441_e, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, r);
            } else if (type == SPEnumParticle.RHAPPY) {
                part = new ParticleRHappy((World)ParticleSpawner.mc.field_71441_e, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
            } else if (type == SPEnumParticle.BIOMASS) {
                part = new ParticleBiomass((World)ParticleSpawner.mc.field_71441_e, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
            } else if (type == SPEnumParticle.EEN) {
                part = new ParticleEen((World)ParticleSpawner.mc.field_71441_e, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
            }
            ParticleSpawner.mc.field_71452_i.func_78873_a(part);
            return part;
        }
        return null;
    }
}

