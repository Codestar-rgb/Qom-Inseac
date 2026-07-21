package com.dhanantry.scapeandrunparasites.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;

public class ParticleSpawner {
   private static Minecraft mc = Minecraft.func_71410_x();

   public static Particle spawnParticle(
      SRPEnumParticle type, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int r, int g, int b
   ) {
      if (mc != null && mc.func_175606_aa() != null && mc.field_71452_i != null) {
         int canSpawn = Minecraft.func_71410_x().field_71474_y.field_74362_aa;
         if (canSpawn >= 1) {
            return null;
         } else {
            Particle part = null;
            if (type == SRPEnumParticle.FOG) {
               part = new ParticleFog(mc.field_71441_e, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, r, g, b);
            } else if (type == SRPEnumParticle.SPORE) {
               part = new ParticleSpore(mc.field_71441_e, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
            } else if (type == SRPEnumParticle.GCLOUD) {
               part = new ParticleRGBSmoke(mc.field_71441_e, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, r, g, b);
            } else if (type == SRPEnumParticle.GSPLASH) {
               part = new ParticleMultipleGore(mc.field_71441_e, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, r);
            } else if (type == SRPEnumParticle.RHAPPY) {
               part = new ParticleRHappy(mc.field_71441_e, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
            } else if (type == SRPEnumParticle.BIOMASS) {
               part = new ParticleBiomass(mc.field_71441_e, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
            } else if (type == SRPEnumParticle.EEN) {
               part = new ParticleEen(mc.field_71441_e, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
            } else if (type == SRPEnumParticle.FLASH) {
               part = new ParticleFlash(mc.field_71441_e, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, r, g, b);
            } else if (type == SRPEnumParticle.DOT) {
               part = new ParticleDot(mc.field_71441_e, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, r, g, b);
            } else if (type == SRPEnumParticle.WIND) {
               part = new ParticleWind(mc.field_71441_e, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
            } else if (type == SRPEnumParticle.COOLER_FOG) {
               part = new ParticleCoolerFog(mc.field_71441_e, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
            } else if (type == SRPEnumParticle.RAGE) {
               part = new ParticleRage(mc.field_71441_e, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
            }

            if (type == SRPEnumParticle.BLOOD) {
               part = new ParticleBlood(mc.field_71441_e, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, r);
            }

            if (part != null) {
               mc.field_71452_i.func_78873_a(part);
            }

            return part;
         }
      } else {
         return null;
      }
   }
}
