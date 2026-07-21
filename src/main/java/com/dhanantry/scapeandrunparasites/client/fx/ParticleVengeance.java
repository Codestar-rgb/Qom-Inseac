package com.dhanantry.scapeandrunparasites.client.fx;

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;

public final class ParticleVengeance {
   private static final Random RAND = new Random();

   private ParticleVengeance() {
   }

   private static WorldClient world() {
      return Minecraft.func_71410_x().field_71441_e;
   }

   public static void electricSparksAround(Vec3d center, float radius, int count) {
      WorldClient w = world();
      if (w != null) {
         for (int i = 0; i < count; i++) {
            double ang = RAND.nextDouble() * Math.PI * 2.0;
            double r = radius * (0.2 + RAND.nextDouble() * 0.8);
            double px = center.field_72450_a + Math.cos(ang) * r + (RAND.nextDouble() - 0.5) * 0.2;
            double py = center.field_72448_b + 0.2 + RAND.nextDouble() * 1.4;
            double pz = center.field_72449_c + Math.sin(ang) * r + (RAND.nextDouble() - 0.5) * 0.2;
            double vx = (RAND.nextDouble() - 0.5) * 0.8;
            double vy = (RAND.nextDouble() - 0.5) * 0.3;
            double vz = (RAND.nextDouble() - 0.5) * 0.8;
            w.func_175688_a(EnumParticleTypes.SPELL_MOB, px, py, pz, 0.65, 0.1, 0.9, new int[0]);
            if (RAND.nextInt(3) == 0) {
               w.func_175688_a(EnumParticleTypes.CRIT_MAGIC, px, py, pz, vx, vy, vz, new int[0]);
            }

            if (RAND.nextInt(4) == 0) {
               w.func_175688_a(EnumParticleTypes.END_ROD, px, py, pz, vx * 0.2, vy * 0.2, vz * 0.2, new int[0]);
            }
         }
      }
   }

   public static void impactDust(Vec3d hitPos, int count) {
      WorldClient w = world();
      if (w != null) {
         int blockStateId = 1;
         int[] args = new int[]{blockStateId};

         for (int i = 0; i < count; i++) {
            double px = hitPos.field_72450_a + (RAND.nextDouble() - 0.5) * 0.4;
            double py = hitPos.field_72448_b + RAND.nextDouble() * 0.2;
            double pz = hitPos.field_72449_c + (RAND.nextDouble() - 0.5) * 0.4;
            double vx = (RAND.nextDouble() - 0.5) * 0.35;
            double vy = 0.1 + RAND.nextDouble() * 0.25;
            double vz = (RAND.nextDouble() - 0.5) * 0.35;
            w.func_175688_a(EnumParticleTypes.BLOCK_DUST, px, py, pz, vx, vy, vz, args);
            if (RAND.nextBoolean()) {
               w.func_175688_a(EnumParticleTypes.DAMAGE_INDICATOR, px, py + 0.1, pz, 0.0, 0.05, 0.0, new int[0]);
            }
         }
      }
   }

   public static void heavyBleedAround(Vec3d center, int count) {
      WorldClient w = world();
      if (w != null) {
         for (int i = 0; i < count; i++) {
            double px = center.field_72450_a + (RAND.nextDouble() - 0.5) * 0.8;
            double py = center.field_72448_b + 0.3 + RAND.nextDouble() * 1.2;
            double pz = center.field_72449_c + (RAND.nextDouble() - 0.5) * 0.8;
            w.func_175688_a(EnumParticleTypes.SPELL_MOB, px, py, pz, 0.55, 0.02, 0.02, new int[0]);
            if (RAND.nextInt(4) == 0) {
               w.func_175688_a(EnumParticleTypes.DRIP_LAVA, px, py, pz, 0.0, 0.0, 0.0, new int[0]);
            }
         }
      }
   }

   public static void lightningExplosion(Vec3d pos) {
      WorldClient w = world();
      if (w != null) {
         w.func_175688_a(EnumParticleTypes.EXPLOSION_HUGE, pos.field_72450_a, pos.field_72448_b + 0.5, pos.field_72449_c, 0.0, 0.0, 0.0, new int[0]);

         for (int i = 0; i < 12; i++) {
            double vx = (RAND.nextDouble() - 0.5) * 0.6;
            double vy = RAND.nextDouble() * 0.4;
            double vz = (RAND.nextDouble() - 0.5) * 0.6;
            w.func_175688_a(EnumParticleTypes.SMOKE_LARGE, pos.field_72450_a, pos.field_72448_b + 0.3, pos.field_72449_c, vx, vy, vz, new int[0]);
         }

         for (int i = 0; i < 18; i++) {
            double vx = (RAND.nextDouble() - 0.5) * 1.1;
            double vy = RAND.nextDouble() * 0.7;
            double vz = (RAND.nextDouble() - 0.5) * 1.1;
            w.func_175688_a(EnumParticleTypes.CRIT_MAGIC, pos.field_72450_a, pos.field_72448_b + 0.6, pos.field_72449_c, vx, vy, vz, new int[0]);
         }
      }
   }
}
