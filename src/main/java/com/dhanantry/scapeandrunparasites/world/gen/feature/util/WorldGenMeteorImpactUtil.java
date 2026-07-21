package com.dhanantry.scapeandrunparasites.world.gen.feature.util;

import com.dhanantry.scapeandrunparasites.world.gen.WorldGenCustomStructures;
import com.dhanantry.scapeandrunparasites.world.gen.structure.WorldGenStructure;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockNewLeaf;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public final class WorldGenMeteorImpactUtil {
   private static final int MIN_CARVE_Y = 5;
   private static final Map<Integer, List<WorldGenMeteorImpactUtil.PendingStructure>> PENDING = new HashMap<>();
   private static final Map<Integer, Set<Long>> MAIN_METEOR_CENTERS = new HashMap<>();

   private WorldGenMeteorImpactUtil() {
   }

   public static void tickPendingStructures(World world) {
      int dim = world.field_73011_w.getDimension();
      List<WorldGenMeteorImpactUtil.PendingStructure> list = PENDING.get(dim);
      if (list != null && !list.isEmpty()) {
         long now = world.func_82737_E();
         Iterator<WorldGenMeteorImpactUtil.PendingStructure> it = list.iterator();

         while (it.hasNext()) {
            WorldGenMeteorImpactUtil.PendingStructure p = it.next();
            if (now >= p.executeAt) {
               Random r = new Random(p.seed);
               WorldGenCustomStructures.generateInPosition(new WorldGenStructure(p.name), r, world, p.origin, p.offX, p.offY, p.offZ);
               it.remove();
            }
         }

         if (list.isEmpty()) {
            PENDING.remove(dim);
         }
      }
   }

   public static void scheduleDelayedStructure(World world, Random rand, String name, BlockPos origin, int offX, int offY, int offZ, int delayTicks) {
      int dim = world.field_73011_w.getDimension();
      List<WorldGenMeteorImpactUtil.PendingStructure> list = PENDING.get(dim);
      if (list == null) {
         list = new ArrayList<>();
         PENDING.put(dim, list);
      }

      long at = world.func_82737_E() + Math.max(1, delayTicks);
      list.add(new WorldGenMeteorImpactUtil.PendingStructure(name, origin, offX, offY, offZ, at, rand.nextLong()));
   }

   public static void markMainMeteor(World world, BlockPos center) {
      int dim = world.field_73011_w.getDimension();
      synchronized (MAIN_METEOR_CENTERS) {
         Set<Long> set = MAIN_METEOR_CENTERS.get(dim);
         if (set == null) {
            set = new HashSet<>();
            MAIN_METEOR_CENTERS.put(dim, set);
         }

         set.add(center.func_177986_g());
         if (set.size() > 512) {
            set.clear();
         }
      }
   }

   public static boolean isNearMainMeteor(World world, BlockPos pos, int minDist) {
      int dim = world.field_73011_w.getDimension();
      Set<Long> set;
      synchronized (MAIN_METEOR_CENTERS) {
         set = MAIN_METEOR_CENTERS.get(dim);
      }

      if (set != null && !set.isEmpty()) {
         int minDistSq = minDist * minDist;

         for (Long l : set) {
            BlockPos c = BlockPos.func_177969_a(l);
            int dx = c.func_177958_n() - pos.func_177958_n();
            int dz = c.func_177952_p() - pos.func_177952_p();
            if (dx * dx + dz * dz <= minDistSq) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public static void carveCraterBowl(
      World world, Random rand, BlockPos surface, int radius, int depth, float steepness, IBlockState rim, IBlockState stain, IBlockState cooked
   ) {
      int cx = surface.func_177958_n();
      int cz = surface.func_177952_p();
      int coreR = Math.max(2, (int)(radius * 0.28F));
      int coreRR = coreR * coreR;

      for (int x = -radius; x <= radius; x++) {
         for (int z = -radius; z <= radius; z++) {
            int d2 = x * x + z * z;
            if (d2 <= radius * radius) {
               BlockPos colTop = world.func_175672_r(new BlockPos(cx + x, surface.func_177956_o(), cz + z));
               int topY = colTop.func_177956_o();
               double dist = Math.sqrt(d2);
               double t = dist / radius;
               double bowl = 1.0 - t;
               double curve = bowl * bowl;
               int cut = topY - (int)Math.round(depth * curve);

               for (int y = topY; y > cut && y > 5; y--) {
                  BlockPos p = new BlockPos(cx + x, y, cz + z);
                  if (!world.func_175667_e(p)) {
                     break;
                  }

                  IBlockState s = world.func_180495_p(p);
                  Material m = s.func_185904_a();
                  if (m != Material.field_151579_a && p.func_177956_o() > 5) {
                     world.func_180501_a(p, Blocks.field_150350_a.func_176223_P(), 2);
                  }
               }

               BlockPos top = world.func_175672_r(new BlockPos(cx + x, surface.func_177956_o(), cz + z)).func_177977_b();
               if (world.func_175667_e(top)) {
                  if (d2 <= coreRR) {
                     if (rand.nextInt(3) == 0) {
                        world.func_180501_a(top, stain, 2);
                     } else {
                        world.func_180501_a(top, cooked, 2);
                     }
                  } else if (dist >= radius - 1.5) {
                     world.func_180501_a(top, rim, 2);
                  } else if (dist >= radius * 0.55 && rand.nextInt(4) == 0) {
                     world.func_180501_a(top, stain, 2);
                  }
               }
            }
         }
      }
   }

   public static void scorchRings(World world, Random rand, BlockPos surface, int radius, IBlockState stain) {
      int cx = surface.func_177958_n();
      int cz = surface.func_177952_p();
      int ring1 = (int)(radius * 1.15F);
      int ring2 = (int)(radius * 1.45F);

      for (int x = -ring2; x <= ring2; x++) {
         for (int z = -ring2; z <= ring2; z++) {
            int d2 = x * x + z * z;
            if (d2 >= ring1 * ring1 && d2 <= ring2 * ring2 && rand.nextInt(3) == 0) {
               BlockPos top = world.func_175672_r(new BlockPos(cx + x, surface.func_177956_o(), cz + z)).func_177977_b();
               if (world.func_175667_e(top)) {
                  world.func_180501_a(top, stain, 2);
               }
            }
         }
      }
   }

   public static void spawnEjecta(World world, Random rand, BlockPos surface, int radius, double dirX, double dirZ, IBlockState rubble, IBlockState stain) {
      int cx = surface.func_177958_n();
      int cz = surface.func_177952_p();
      int max = (int)(radius * 3.2F);
      int min = (int)(radius * 1.1F);

      for (int i = 0; i < radius * 30; i++) {
         double dist = min + rand.nextInt(Math.max(1, max - min));
         double spread = (rand.nextDouble() - 0.5) * 1.25;
         double px = dirX * dist + -dirZ * dist * spread;
         double pz = dirZ * dist + dirX * dist * spread;
         int x = cx + (int)Math.round(px);
         int z = cz + (int)Math.round(pz);
         BlockPos top = world.func_175672_r(new BlockPos(x, surface.func_177956_o(), z)).func_177977_b();
         if (world.func_175667_e(top)) {
            if (rand.nextInt(5) == 0) {
               world.func_180501_a(top, stain, 2);
            } else {
               world.func_180501_a(top, rubble, 2);
            }
         }
      }
   }

   public static void microCraters(World world, Random rand, BlockPos surface, int radius, double dirX, double dirZ, IBlockState stain) {
      int cx = surface.func_177958_n();
      int cz = surface.func_177952_p();
      int count = Math.max(3, radius / 3);

      for (int i = 0; i < count; i++) {
         double dist = radius * (1.2 + rand.nextDouble() * 2.0);
         double spread = (rand.nextDouble() - 0.5) * 1.6;
         double px = dirX * dist + -dirZ * dist * spread;
         double pz = dirZ * dist + dirX * dist * spread;
         int x0 = cx + (int)Math.round(px);
         int z0 = cz + (int)Math.round(pz);
         int r = 2 + rand.nextInt(3);
         int rr = r * r;

         for (int x = -r; x <= r; x++) {
            for (int z = -r; z <= r; z++) {
               int d2 = x * x + z * z;
               if (d2 <= rr) {
                  BlockPos top = world.func_175672_r(new BlockPos(x0 + x, surface.func_177956_o(), z0 + z)).func_177977_b();
                  if (world.func_175667_e(top) && rand.nextInt(3) == 0) {
                     world.func_180501_a(top, stain, 2);
                  }
               }
            }
         }
      }
   }

   public static WorldGenMeteorImpactUtil.TunnelResult carveAngledTunnel(
      World world, BlockPos center, int radius, int length, double dirX, double dirY, double dirZ
   ) {
      double mag = Math.sqrt(dirX * dirX + dirY * dirY + dirZ * dirZ);
      if (mag < 1.0E-6) {
         return new WorldGenMeteorImpactUtil.TunnelResult(false, 0, 0, 0, 0);
      } else {
         dirX /= mag;
         dirY /= mag;
         dirZ /= mag;
         int cx = center.func_177958_n();
         int cy = center.func_177956_o();
         int cz = center.func_177952_p();
         boolean any = false;
         int firstY = 0;
         int lastY = 0;
         int low = Integer.MAX_VALUE;
         int high = Integer.MIN_VALUE;

         for (int t = 0; t <= length; t++) {
            int px = cx + (int)Math.round(dirX * t);
            int py = cy + (int)Math.round(dirY * t);
            int pz = cz + (int)Math.round(dirZ * t);
            WorldGenMeteorImpactUtil.TunnelResultSlice slice = carveSphereCount(world, new BlockPos(px, py, pz), radius);
            if (slice.brokeAny) {
               if (!any) {
                  any = true;
                  firstY = slice.firstBrokenY;
               }

               lastY = slice.lastBrokenY;
               if (slice.lowestY < low) {
                  low = slice.lowestY;
               }

               if (slice.highestY > high) {
                  high = slice.highestY;
               }
            }
         }

         return !any ? new WorldGenMeteorImpactUtil.TunnelResult(false, 0, 0, 0, 0) : new WorldGenMeteorImpactUtil.TunnelResult(true, firstY, lastY, low, high);
      }
   }

   public static void clearVegetationInArea(World world, BlockPos center, int radius, int yMin, int yMax) {
      int cx = center.func_177958_n();
      int cz = center.func_177952_p();
      int rr = radius * radius;

      for (int x = -radius; x <= radius; x++) {
         for (int z = -radius; z <= radius; z++) {
            int d2 = x * x + z * z;
            if (d2 <= rr) {
               int ax = cx + x;
               int az = cz + z;

               for (int y = yMin; y <= yMax; y++) {
                  BlockPos p = new BlockPos(ax, y, az);
                  if (world.func_175667_e(p)) {
                     IBlockState s = world.func_180495_p(p);
                     if (s.func_185904_a() != Material.field_151579_a && isVegetation(s) && p.func_177956_o() > 5) {
                        world.func_180501_a(p, Blocks.field_150350_a.func_176223_P(), 2);
                     }
                  }
               }
            }
         }
      }
   }

   private static WorldGenMeteorImpactUtil.TunnelResultSlice carveSphereCount(World world, BlockPos center, int radius) {
      int cx = center.func_177958_n();
      int cy = center.func_177956_o();
      int cz = center.func_177952_p();
      int rr = radius * radius;
      boolean any = false;
      int firstY = 0;
      int lastY = 0;
      int low = Integer.MAX_VALUE;
      int high = Integer.MIN_VALUE;

      for (int x = -radius; x <= radius; x++) {
         for (int y = -radius; y <= radius; y++) {
            for (int z = -radius; z <= radius; z++) {
               int d2 = x * x + y * y + z * z;
               if (d2 <= rr) {
                  BlockPos p = new BlockPos(cx + x, cy + y, cz + z);
                  if (world.func_175667_e(p)) {
                     IBlockState s = world.func_180495_p(p);
                     if (s.func_185904_a() != Material.field_151579_a && p.func_177956_o() > 5) {
                        world.func_180501_a(p, Blocks.field_150350_a.func_176223_P(), 2);
                        if (!any) {
                           any = true;
                           firstY = p.func_177956_o();
                        }

                        lastY = p.func_177956_o();
                        if (p.func_177956_o() < low) {
                           low = p.func_177956_o();
                        }

                        if (p.func_177956_o() > high) {
                           high = p.func_177956_o();
                        }
                     }
                  }
               }
            }
         }
      }

      return !any
         ? new WorldGenMeteorImpactUtil.TunnelResultSlice(false, 0, 0, 0, 0)
         : new WorldGenMeteorImpactUtil.TunnelResultSlice(true, firstY, lastY, low, high);
   }

   private static boolean isVegetation(IBlockState s) {
      Block b = s.func_177230_c();
      Material m = s.func_185904_a();
      if (m == Material.field_151584_j || m == Material.field_151575_d || m == Material.field_151582_l) {
         return true;
      } else if (b == Blocks.field_150362_t
         || b == Blocks.field_150361_u
         || b == Blocks.field_150364_r
         || b == Blocks.field_150363_s
         || b == Blocks.field_150395_bd) {
         return true;
      } else if (b instanceof BlockOldLeaf || b instanceof BlockNewLeaf) {
         return true;
      } else if (b instanceof BlockOldLog || b instanceof BlockNewLog) {
         return true;
      } else {
         return b instanceof BlockLog ? true : b instanceof BlockVine;
      }
   }

   public static void updateWaterAfterImpact(World world, BlockPos surface, int radius, int depth) {
      int cx = surface.func_177958_n();
      int cy = surface.func_177956_o();
      int cz = surface.func_177952_p();
      int r = Math.max(8, (int)(radius * 1.8F) + 12);
      int yMin = cy - depth - 6;
      if (yMin < 1) {
         yMin = 1;
      }

      int yMax = cy + 24;
      if (yMax > 255) {
         yMax = 255;
      }

      Block still = Blocks.field_150355_j;
      Block flowing = Blocks.field_150358_i;

      for (int x = -r; x <= r; x++) {
         for (int z = -r; z <= r; z++) {
            for (int y = yMin; y <= yMax; y++) {
               BlockPos p = new BlockPos(cx + x, y, cz + z);
               if (world.func_175667_e(p)) {
                  IBlockState s = world.func_180495_p(p);
                  Block b = s.func_177230_c();
                  if (b != still && b != flowing) {
                     if (s.func_185904_a() == Material.field_151579_a) {
                        BlockPos p2 = p.func_177984_a();
                        IBlockState s2 = world.func_180495_p(p2);
                        Block b2 = s2.func_177230_c();
                        if (b2 == still || b2 == flowing) {
                           world.func_175684_a(p2, b2, 1);
                           world.func_175685_c(p2, b2, true);
                        }

                        p2 = p.func_177977_b();
                        s2 = world.func_180495_p(p2);
                        b2 = s2.func_177230_c();
                        if (b2 == still || b2 == flowing) {
                           world.func_175684_a(p2, b2, 1);
                           world.func_175685_c(p2, b2, true);
                        }

                        p2 = p.func_177978_c();
                        s2 = world.func_180495_p(p2);
                        b2 = s2.func_177230_c();
                        if (b2 == still || b2 == flowing) {
                           world.func_175684_a(p2, b2, 1);
                           world.func_175685_c(p2, b2, true);
                        }

                        p2 = p.func_177968_d();
                        s2 = world.func_180495_p(p2);
                        b2 = s2.func_177230_c();
                        if (b2 == still || b2 == flowing) {
                           world.func_175684_a(p2, b2, 1);
                           world.func_175685_c(p2, b2, true);
                        }

                        p2 = p.func_177976_e();
                        s2 = world.func_180495_p(p2);
                        b2 = s2.func_177230_c();
                        if (b2 == still || b2 == flowing) {
                           world.func_175684_a(p2, b2, 1);
                           world.func_175685_c(p2, b2, true);
                        }

                        p2 = p.func_177974_f();
                        s2 = world.func_180495_p(p2);
                        b2 = s2.func_177230_c();
                        if (b2 == still || b2 == flowing) {
                           world.func_175684_a(p2, b2, 1);
                           world.func_175685_c(p2, b2, true);
                        }
                     }
                  } else {
                     world.func_175684_a(p, b, 1);
                     world.func_175685_c(p, b, true);
                  }
               }
            }
         }
      }
   }

   private static final class PendingStructure {
      final String name;
      final BlockPos origin;
      final int offX;
      final int offY;
      final int offZ;
      final long executeAt;
      final long seed;

      PendingStructure(String name, BlockPos origin, int offX, int offY, int offZ, long executeAt, long seed) {
         this.name = name;
         this.origin = origin;
         this.offX = offX;
         this.offY = offY;
         this.offZ = offZ;
         this.executeAt = executeAt;
         this.seed = seed;
      }
   }

   public static final class TunnelResult {
      public final boolean anyBroken;
      public final int firstBrokenY;
      public final int lastBrokenY;
      public final int lowestY;
      public final int highestY;

      public TunnelResult(boolean anyBroken, int firstBrokenY, int lastBrokenY, int lowestY, int highestY) {
         this.anyBroken = anyBroken;
         this.firstBrokenY = firstBrokenY;
         this.lastBrokenY = lastBrokenY;
         this.lowestY = lowestY;
         this.highestY = highestY;
      }
   }

   private static final class TunnelResultSlice {
      final boolean brokeAny;
      final int firstBrokenY;
      final int lastBrokenY;
      final int lowestY;
      final int highestY;

      TunnelResultSlice(boolean brokeAny, int firstBrokenY, int lastBrokenY, int lowestY, int highestY) {
         this.brokeAny = brokeAny;
         this.firstBrokenY = firstBrokenY;
         this.lastBrokenY = lastBrokenY;
         this.lowestY = lowestY;
         this.highestY = highestY;
      }
   }
}
