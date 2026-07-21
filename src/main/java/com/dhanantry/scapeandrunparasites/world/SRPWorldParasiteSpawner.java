package com.dhanantry.scapeandrunparasites.world;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPSpawning;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import com.dhanantry.scapeandrunparasites.world.biome.BiomeParasiteBase;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.EntityLiving.SpawnPlacementType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.server.management.PlayerChunkMapEntry;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.common.eventhandler.Event.Result;

public class SRPWorldParasiteSpawner {
   private static final Set<ChunkPos> eligibleChunksForSpawning = Sets.newHashSet();
   private static int lock = 0;
   private static int originC = 0;
   public static boolean triggerSPAWNING = false;
   public static int choiceNUMBER = 1;

   public static int findChunksForSpawning(WorldServer worldServerIn, boolean spawnHostileMobs, boolean spawnPeacefulMobs, boolean spawnOnSetTickRate) {
      return !SRPConfigWorld.originActivated
         ? findChunksForSpawningVanilla(worldServerIn, spawnHostileMobs, spawnPeacefulMobs, spawnOnSetTickRate)
         : findChunksForSpawningOrigin(worldServerIn, spawnHostileMobs, spawnPeacefulMobs, spawnOnSetTickRate);
   }

   public static int findChunksForSpawningVanilla(WorldServer worldServerIn, boolean spawnHostileMobs, boolean spawnPeacefulMobs, boolean spawnOnSetTickRate) {
      if (!spawnHostileMobs && !spawnPeacefulMobs) {
         return 0;
      } else if (!SRPSpawning.totalParasites) {
         lock++;
         if (lock > 40) {
            SRPSpawning.totalParasites = true;
            lock = 0;
         }

         return 0;
      } else {
         eligibleChunksForSpawning.clear();

         for (EntityPlayer entityplayer : worldServerIn.field_73010_i) {
            if (!entityplayer.func_175149_v()) {
               int j = MathHelper.func_76128_c(entityplayer.field_70165_t / 16.0);
               int k = MathHelper.func_76128_c(entityplayer.field_70161_v / 16.0);

               for (int i1 = -8; i1 <= 8; i1++) {
                  for (int j1 = -8; j1 <= 8; j1++) {
                     boolean flag = i1 == -8 || i1 == 8 || j1 == -8 || j1 == 8;
                     ChunkPos chunkpos = new ChunkPos(i1 + j, j1 + k);
                     if (!eligibleChunksForSpawning.contains(chunkpos) && !flag && worldServerIn.func_175723_af().func_177730_a(chunkpos)) {
                        PlayerChunkMapEntry playerchunkmapentry = worldServerIn.func_184164_w().func_187301_b(chunkpos.field_77276_a, chunkpos.field_77275_b);
                        if (playerchunkmapentry != null && playerchunkmapentry.func_187274_e()) {
                           eligibleChunksForSpawning.add(chunkpos);
                        }
                     }
                  }
               }
            }
         }

         int j4 = 0;
         BlockPos blockpos1 = worldServerIn.func_175694_M();
         ArrayList<ChunkPos> shuffled = Lists.newArrayList(eligibleChunksForSpawning);
         Collections.shuffle(shuffled);
         MutableBlockPos blockpos$mutableblockpos = new MutableBlockPos();

         label124:
         for (ChunkPos chunkpos1 : shuffled) {
            BlockPos blockpos = getRandomChunkPosition(worldServerIn, chunkpos1.field_77276_a, chunkpos1.field_77275_b);
            int k1 = blockpos.func_177958_n();
            int l1 = blockpos.func_177956_o();
            int i2 = blockpos.func_177952_p();
            IBlockState iblockstate = worldServerIn.func_180495_p(blockpos);
            if (!iblockstate.func_185915_l()) {
               int j2 = 0;

               for (int k2 = 0; k2 < 3; k2++) {
                  int l2 = k1;
                  int i3 = l1;
                  int j3 = i2;
                  SpawnListEntry biome$spawnlistentry = null;
                  IEntityLivingData ientitylivingdata = null;
                  int l3 = MathHelper.func_76143_f(Math.random() * 4.0);

                  for (int i4 = 0; i4 < l3; i4++) {
                     l2 += worldServerIn.field_73012_v.nextInt(6) - worldServerIn.field_73012_v.nextInt(6);
                     i3 += worldServerIn.field_73012_v.nextInt(1) - worldServerIn.field_73012_v.nextInt(1);
                     j3 += worldServerIn.field_73012_v.nextInt(6) - worldServerIn.field_73012_v.nextInt(6);
                     blockpos$mutableblockpos.func_181079_c(l2, i3, j3);
                     float f = l2 + 0.5F;
                     float f1 = j3 + 0.5F;
                     if (!worldServerIn.func_175636_b(f, i3, f1, 24.0) && blockpos1.func_177954_c(f, i3, f1) >= 576.0) {
                        if (biome$spawnlistentry == null) {
                           biome$spawnlistentry = getSpawnListEntryForTypeAt(worldServerIn, blockpos$mutableblockpos);
                           if (biome$spawnlistentry == null) {
                              break;
                           }
                        }

                        SpawnPlacementType ground = EntitySpawnPlacementRegistry.func_180109_a(biome$spawnlistentry.field_76300_b);
                        if (ground == SpawnPlacementType.IN_AIR) {
                           EntityPlayer closest = getClosestPlayer(f, i3, f1, 24.0, worldServerIn);
                           if (closest != null) {
                              double base = closest.field_70163_u;
                              double randomOff = worldServerIn.field_73012_v.nextInt(21) - 10;
                              base = Math.max(base, worldServerIn.func_72912_H().func_76067_t().getMinimumSpawnHeight(worldServerIn) / 2.0);
                              base = Math.min(base, (double)SRPConfigWorld.spawnerSKYLimitUp);
                              blockpos$mutableblockpos.func_189532_c(l2, base + randomOff, j3);
                           }
                        }

                        if (canCreatureTypeSpawnAtLocation(ground, worldServerIn, blockpos$mutableblockpos)) {
                           EntityParasiteBase entityliving;
                           try {
                              entityliving = (EntityParasiteBase)biome$spawnlistentry.newInstance(worldServerIn);
                              entityliving.canSpawnSpawn = true;
                           } catch (Exception var32) {
                              return j4;
                           }

                           entityliving.func_70012_b(f, i3, f1, worldServerIn.field_73012_v.nextFloat() * 360.0F, 0.0F);
                           Result canSpawn = ForgeEventFactory.canEntitySpawn(entityliving, worldServerIn, f, i3, f1, false);
                           if (canSpawn == Result.ALLOW || canSpawn == Result.DEFAULT && entityliving.func_70601_bi()) {
                              if (!ForgeEventFactory.doSpecialSpawn(entityliving, worldServerIn, f, i3, f1)) {
                                 ientitylivingdata = entityliving.func_180482_a(worldServerIn.func_175649_E(new BlockPos(entityliving)), ientitylivingdata);
                              }

                              if (entityliving.func_70058_J()) {
                                 j2++;
                                 worldServerIn.func_72838_d(entityliving);
                              } else {
                                 entityliving.func_70106_y();
                              }

                              if (j2 >= ForgeEventFactory.getMaxSpawnPackSize(entityliving)) {
                                 continue label124;
                              }
                           }

                           j4 += j2;
                        }
                     }
                  }
               }
            }
         }

         return j4;
      }
   }

   public static int findChunksForSpawningOrigin(WorldServer worldServerIn, boolean spawnHostileMobs, boolean spawnPeacefulMobs, boolean spawnOnSetTickRate) {
      if (!spawnHostileMobs && !spawnPeacefulMobs) {
         return 0;
      } else if (!SRPSpawning.totalParasites) {
         lock++;
         if (lock > 7) {
            SRPSpawning.totalParasites = true;
            lock = 0;
         }

         return 0;
      } else {
         eligibleChunksForSpawning.clear();
         SRPWorldData worldData = SRPWorldData.get(worldServerIn);
         SRPSaveData saveData225 = SRPSaveData.get(worldServerIn, 225);
         SRPSaveData saveData72 = SRPSaveData.get(worldServerIn, 72);
         boolean originsExist = worldData != null && !worldData.getorigins("x").isEmpty();
         boolean originlessAllowed = saveData225 != null && saveData225.getDeveLevel() >= SRPConfigSystems.deveOriginlessUse;

         for (EntityPlayer entityplayer : worldServerIn.field_73010_i) {
            if (!entityplayer.func_175149_v()) {
               int j = MathHelper.func_76128_c(entityplayer.field_70165_t / 16.0);
               int k = MathHelper.func_76128_c(entityplayer.field_70161_v / 16.0);

               for (int i1 = -8; i1 <= 8; i1++) {
                  for (int j1 = -8; j1 <= 8; j1++) {
                     boolean flag = i1 == -8 || i1 == 8 || j1 == -8 || j1 == 8;
                     ChunkPos chunkpos = new ChunkPos(i1 + j, j1 + k);
                     if (!eligibleChunksForSpawning.contains(chunkpos) && !flag && worldServerIn.func_175723_af().func_177730_a(chunkpos)) {
                        PlayerChunkMapEntry playerchunkmapentry = worldServerIn.func_184164_w().func_187301_b(chunkpos.field_77276_a, chunkpos.field_77275_b);
                        if (playerchunkmapentry != null && playerchunkmapentry.func_187274_e()) {
                           eligibleChunksForSpawning.add(chunkpos);
                        }
                     }
                  }
               }
            }
         }

         filterEligibleChunksForOrigin(worldData, originsExist, originlessAllowed);
         if (eligibleChunksForSpawning.isEmpty()) {
            return 0;
         } else {
            int j4 = 0;
            BlockPos blockpos1 = worldServerIn.func_175694_M();
            ArrayList<ChunkPos> shuffled = Lists.newArrayList(eligibleChunksForSpawning);
            Collections.shuffle(shuffled);
            MutableBlockPos blockpos$mutableblockpos = new MutableBlockPos();

            label138:
            for (ChunkPos chunkpos1 : shuffled) {
               BlockPos blockpos = getRandomChunkPosition(worldServerIn, chunkpos1.field_77276_a, chunkpos1.field_77275_b);
               int k1 = blockpos.func_177958_n();
               int l1 = blockpos.func_177956_o();
               int i2 = blockpos.func_177952_p();
               IBlockState iblockstate = worldServerIn.func_180495_p(blockpos);
               if (!iblockstate.func_185915_l()) {
                  int j2 = 0;

                  for (int k2 = 0; k2 < 3; k2++) {
                     int l2 = k1;
                     int i3 = l1;
                     int j3 = i2;
                     SpawnListEntry biome$spawnlistentry = null;
                     IEntityLivingData ientitylivingdata = null;
                     int l3 = MathHelper.func_76143_f(Math.random() * 4.0);

                     for (int i4 = 0; i4 < l3; i4++) {
                        l2 += worldServerIn.field_73012_v.nextInt(6) - worldServerIn.field_73012_v.nextInt(6);
                        i3 += worldServerIn.field_73012_v.nextInt(1) - worldServerIn.field_73012_v.nextInt(1);
                        j3 += worldServerIn.field_73012_v.nextInt(6) - worldServerIn.field_73012_v.nextInt(6);
                        blockpos$mutableblockpos.func_181079_c(l2, i3, j3);
                        float f = l2 + 0.5F;
                        float f1 = j3 + 0.5F;
                        if (!worldServerIn.func_175636_b(f, i3, f1, 24.0) && blockpos1.func_177954_c(f, i3, f1) >= 576.0) {
                           if (biome$spawnlistentry == null) {
                              biome$spawnlistentry = getSpawnListEntryForTypeAtOrigin(
                                 worldServerIn, blockpos$mutableblockpos, saveData72, worldData, originsExist, originlessAllowed
                              );
                              if (biome$spawnlistentry == null) {
                                 break;
                              }
                           }

                           SpawnPlacementType ground = EntitySpawnPlacementRegistry.func_180109_a(biome$spawnlistentry.field_76300_b);
                           if (ground == SpawnPlacementType.IN_AIR) {
                              if (worldServerIn.field_73012_v.nextDouble() <= 0.7) {
                                 continue;
                              }

                              EntityPlayer closest = getClosestPlayer(f, i3, f1, 24.0, worldServerIn);
                              if (closest != null) {
                                 double base = closest.field_70163_u;
                                 double randomOff = worldServerIn.field_73012_v.nextInt(21) - 10;
                                 base = Math.max(base, worldServerIn.func_72912_H().func_76067_t().getMinimumSpawnHeight(worldServerIn) / 2.0);
                                 base = Math.min(base, (double)SRPConfigWorld.spawnerSKYLimitUp);
                                 blockpos$mutableblockpos.func_189532_c(l2, base + randomOff, j3);
                              }
                           }

                           if (canCreatureTypeSpawnAtLocation(ground, worldServerIn, blockpos$mutableblockpos)) {
                              EntityParasiteBase entityliving;
                              try {
                                 entityliving = (EntityParasiteBase)biome$spawnlistentry.newInstance(worldServerIn);
                                 entityliving.canSpawnSpawn = true;
                              } catch (Exception var37) {
                                 return j4;
                              }

                              entityliving.func_70012_b(f, i3, f1, worldServerIn.field_73012_v.nextFloat() * 360.0F, 0.0F);
                              entityliving.func_70012_b(f, blockpos$mutableblockpos.func_177956_o(), f1, worldServerIn.field_73012_v.nextFloat() * 360.0F, 0.0F);
                              Result canSpawn = ForgeEventFactory.canEntitySpawn(entityliving, worldServerIn, f, i3, f1, false);
                              if (canSpawn == Result.ALLOW || canSpawn == Result.DEFAULT && entityliving.func_70601_bi()) {
                                 if (!ForgeEventFactory.doSpecialSpawn(entityliving, worldServerIn, f, i3, f1)) {
                                    ientitylivingdata = entityliving.func_180482_a(worldServerIn.func_175649_E(new BlockPos(entityliving)), ientitylivingdata);
                                 }

                                 if (entityliving.func_70058_J()) {
                                    j2++;
                                    worldServerIn.func_72838_d(entityliving);
                                 } else {
                                    entityliving.func_70106_y();
                                 }

                                 if (j2 >= ForgeEventFactory.getMaxSpawnPackSize(entityliving)) {
                                    continue label138;
                                 }
                              }

                              j4 += j2;
                           }
                        }
                     }
                  }
               }
            }

            return j4;
         }
      }
   }

   @Nullable
   private static EntityPlayer getClosestPlayer(double x, double y, double z, double distance, World world) {
      double d0 = -1.0;
      EntityPlayer entityplayer = null;

      for (EntityPlayer entityPlayer : world.field_73010_i) {
         if (EntitySelectors.field_180132_d.apply(entityPlayer)) {
            double d1 = entityPlayer.func_70092_e(x, y, z);
            if (d1 > distance * distance && (d0 == -1.0 || d1 < d0)) {
               d0 = d1;
               entityplayer = entityPlayer;
            }
         }
      }

      return entityplayer;
   }

   private static BlockPos getRandomChunkPosition(World worldIn, int x, int z) {
      Chunk chunk = worldIn.func_72964_e(x, z);
      int i = x * 16 + worldIn.field_73012_v.nextInt(16);
      int j = z * 16 + worldIn.field_73012_v.nextInt(16);
      int k = MathHelper.func_154354_b(chunk.func_177433_f(new BlockPos(i, 0, j)) + 1, 16);
      int l = worldIn.field_73012_v.nextInt(Math.max(1, k > 0 ? k : chunk.func_76625_h() + 16 - 1));
      return new BlockPos(i, l, j);
   }

   public static boolean canCreatureTypeSpawnAtLocation(SpawnPlacementType spawnPlacementTypeIn, World worldIn, BlockPos pos) {
      return !worldIn.func_175723_af().func_177746_a(pos) ? false : canCreatureTypeSpawnBody(spawnPlacementTypeIn, worldIn, pos);
   }

   public static boolean canCreatureTypeSpawnBody(SpawnPlacementType spawnPlacementTypeIn, World worldIn, BlockPos pos) {
      IBlockState iblockstate = worldIn.func_180495_p(pos);
      if (spawnPlacementTypeIn == SpawnPlacementType.IN_WATER) {
         return iblockstate.func_185904_a() == Material.field_151586_h
            && worldIn.func_180495_p(pos.func_177977_b()).func_185904_a() == Material.field_151586_h
            && !worldIn.func_180495_p(pos.func_177984_a()).func_185915_l();
      } else if (spawnPlacementTypeIn == SpawnPlacementType.IN_AIR) {
         return iblockstate.func_177230_c() == Blocks.field_150350_a
            && worldIn.func_180495_p(pos.func_177977_b()).func_177230_c() == Blocks.field_150350_a
            && worldIn.func_180495_p(pos.func_177984_a()).func_177230_c() == Blocks.field_150350_a;
      } else {
         BlockPos blockpos = pos.func_177977_b();
         IBlockState state = worldIn.func_180495_p(blockpos);
         if (!state.func_177230_c().canCreatureSpawn(state, worldIn, blockpos, spawnPlacementTypeIn)) {
            return false;
         } else {
            Block block = worldIn.func_180495_p(blockpos).func_177230_c();
            boolean flag = block != Blocks.field_150357_h && block != Blocks.field_180401_cv;
            return flag && isValidEmptySpawnBlock(iblockstate) && isValidEmptySpawnBlock(worldIn.func_180495_p(pos.func_177984_a()));
         }
      }
   }

   public static boolean isValidEmptySpawnBlock(IBlockState state) {
      if (state.func_185898_k()) {
         return false;
      } else if (state.func_185897_m()) {
         return false;
      } else {
         return state.func_185904_a().func_76224_d() ? false : !BlockRailBase.func_176563_d(state);
      }
   }

   private static void filterEligibleChunksForOrigin(SRPWorldData data, boolean originsExist, boolean originlessAllowed) {
      if (originsExist && !originlessAllowed && data != null && !eligibleChunksForSpawning.isEmpty()) {
         ArrayList<Integer> originsX = data.getorigins("x");
         ArrayList<Integer> originsZ = data.getorigins("z");
         ArrayList<Integer> originsA = data.getorigins("a");
         int originCount = Math.min(originsX.size(), Math.min(originsZ.size(), originsA.size()));
         ArrayList<Integer> coloniesX = data.getColonies("x");
         ArrayList<Integer> coloniesY = data.getColonies("y");
         ArrayList<Integer> coloniesZ = data.getColonies("z");
         int colonyCount = Math.min(coloniesX.size(), Math.min(coloniesY.size(), coloniesZ.size()));
         int[] colonyRadii = new int[colonyCount];

         for (int i = 0; i < colonyCount; i++) {
            BlockPos colonyPos = new BlockPos(coloniesX.get(i), coloniesY.get(i), coloniesZ.get(i));
            colonyRadii[i] = Math.max(0, data.getColonyDistanceSpreadByPosition(colonyPos, false));
         }

         Iterator<ChunkPos> chunkIterator = eligibleChunksForSpawning.iterator();

         while (chunkIterator.hasNext()) {
            ChunkPos chunkPos = chunkIterator.next();
            boolean inRange = false;

            for (int i = 0; i < originCount; i++) {
               if (chunkIntersectsRadius2D(chunkPos.field_77276_a, chunkPos.field_77275_b, originsX.get(i), originsZ.get(i), originsA.get(i))) {
                  inRange = true;
                  break;
               }
            }

            if (!inRange) {
               for (int ix = 0; ix < colonyCount; ix++) {
                  if (chunkIntersectsRadius2D(chunkPos.field_77276_a, chunkPos.field_77275_b, coloniesX.get(ix), coloniesZ.get(ix), colonyRadii[ix])) {
                     inRange = true;
                     break;
                  }
               }
            }

            if (!inRange) {
               chunkIterator.remove();
            }
         }
      }
   }

   private static boolean chunkIntersectsRadius2D(int chunkX, int chunkZ, int centerX, int centerZ, int radius) {
      if (radius <= 0) {
         return false;
      } else {
         int minX = chunkX << 4;
         int maxX = minX + 15;
         int minZ = chunkZ << 4;
         int maxZ = minZ + 15;
         int closestX = Math.max(minX, Math.min(centerX, maxX));
         int closestZ = Math.max(minZ, Math.min(centerZ, maxZ));
         long dx = (long)centerX - closestX;
         long dz = (long)centerZ - closestZ;
         long radiusSq = (long)radius * radius;
         return dx * dx + dz * dz <= radiusSq;
      }
   }

   @Nullable
   public static SpawnListEntry getSpawnListEntryForTypeAt(WorldServer worldServerIn, BlockPos pos) {
      SRPSaveData dat = SRPSaveData.get(worldServerIn, 72);
      int id = worldServerIn.field_73011_w.getDimension();
      if (dat == null) {
         return null;
      } else if (SRPConfigWorld.originActivated) {
         SRPWorldData data = SRPWorldData.get(worldServerIn);
         boolean originsExist = data != null && !data.getorigins("x").isEmpty();
         SRPSaveData saveData225 = SRPSaveData.get(worldServerIn, 225);
         boolean originlessAllowed = saveData225 != null && saveData225.getDeveLevel() >= SRPConfigSystems.deveOriginlessUse;
         return getSpawnListEntryForTypeAtOrigin(worldServerIn, pos, dat, data, originsExist, originlessAllowed);
      } else {
         List<SpawnListEntry> list = SRPSpawning.getSpawns(worldServerIn, id, dat.getEvolutionPhase(id), dat);
         return list != null && !list.isEmpty() ? (SpawnListEntry)WeightedRandom.func_76271_a(worldServerIn.field_73012_v, list) : null;
      }
   }

   @Nullable
   private static SpawnListEntry getSpawnListEntryForTypeAtOrigin(
      WorldServer worldServerIn, BlockPos pos, SRPSaveData dat, SRPWorldData data, boolean originsExist, boolean originlessAllowed
   ) {
      int id = worldServerIn.field_73011_w.getDimension();
      if (!isPosWithinOrigin(worldServerIn, pos, data, originsExist, originlessAllowed)) {
         return null;
      } else {
         byte phase = dat.getEvolutionPhase(id);
         if (phase == -1) {
            if (!originsExist) {
               return null;
            } else {
               List<SpawnListEntry> list = SRPSpawning.getSpawns(worldServerIn, id, phase, dat);
               return list != null && !list.isEmpty() ? (SpawnListEntry)WeightedRandom.func_76271_a(worldServerIn.field_73012_v, list) : null;
            }
         } else {
            int phaseToUse = originsExist ? phase : 0;
            List<SpawnListEntry> list = SRPSpawning.getSpawns(worldServerIn, id, phaseToUse, dat);
            return list != null && !list.isEmpty() ? (SpawnListEntry)WeightedRandom.func_76271_a(worldServerIn.field_73012_v, list) : null;
         }
      }
   }

   private static boolean isPosWithinOrigin(World world, BlockPos pos, SRPWorldData data, boolean originsExist, boolean originlessAllowed) {
      if (world.func_180494_b(pos) instanceof BiomeParasiteBase) {
         return true;
      } else if (data == null) {
         return false;
      } else {
         int a = data.nearestInfectionValue(pos, false);
         return data.nearestColonyPosition(pos, true) != null ? true : a > 0 || originsExist && originlessAllowed || !originsExist && !data.getTriggerMet();
      }
   }

   private static String posToString(BlockPos pos) {
      return "(" + pos.func_177958_n() + ", " + pos.func_177956_o() + ", " + pos.func_177952_p() + ")";
   }
}
