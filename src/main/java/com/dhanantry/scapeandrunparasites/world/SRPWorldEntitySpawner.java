package com.dhanantry.scapeandrunparasites.world;

import com.dhanantry.scapeandrunparasites.SRPMain;
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
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.EntityLiving.SpawnPlacementType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.management.PlayerChunkMapEntry;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.common.eventhandler.Event.Result;

public class SRPWorldEntitySpawner {
   private static final Set<ChunkPos> eligibleChunksForSpawning = Sets.newHashSet();
   private static int lock = 0;
   public static boolean triggerSPAWNING = false;
   public static int choiceNUMBER = 1;

   private static void debugSpawn(String msg) {
      if (SRPConfigSystems.debugSpawner) {
         SRPMain.logger.info("[SRP Spawner] " + msg);
      }
   }

   private static String posToString(BlockPos pos) {
      return "(" + pos.func_177958_n() + ", " + pos.func_177956_o() + ", " + pos.func_177952_p() + ")";
   }

   @Nullable
   private static String getSpawnFailureReason(SpawnPlacementType spawnPlacementTypeIn, World worldIn, BlockPos pos) {
      if (!worldIn.func_175723_af().func_177746_a(pos)) {
         return "outside world border";
      } else {
         IBlockState iblockstate = worldIn.func_180495_p(pos);
         if (spawnPlacementTypeIn == SpawnPlacementType.IN_WATER) {
            if (iblockstate.func_185904_a() != Material.field_151586_h) {
               return "spawn block is not water";
            } else if (worldIn.func_180495_p(pos.func_177977_b()).func_185904_a() != Material.field_151586_h) {
               return "block below is not water";
            } else {
               return worldIn.func_180495_p(pos.func_177984_a()).func_185904_a() != Material.field_151586_h ? "block above is not water" : null;
            }
         } else if (spawnPlacementTypeIn == SpawnPlacementType.IN_AIR) {
            if (iblockstate.func_177230_c() != Blocks.field_150350_a) {
               return "spawn block is not air";
            } else {
               return worldIn.func_180495_p(pos.func_177984_a()).func_185915_l() ? "block above is solid" : null;
            }
         } else {
            BlockPos blockpos = pos.func_177977_b();
            IBlockState state = worldIn.func_180495_p(blockpos);
            if (!state.func_177230_c().canCreatureSpawn(state, worldIn, blockpos, spawnPlacementTypeIn)) {
               return "block below does not allow creature spawn: " + state.func_177230_c().getRegistryName();
            } else {
               Block block = state.func_177230_c();
               if (block == Blocks.field_150357_h || block == Blocks.field_180401_cv) {
                  return "block below is forbidden: " + block.getRegistryName();
               } else if (!isValidEmptySpawnBlock(iblockstate)) {
                  return "spawn block is not empty/valid";
               } else {
                  return !isValidEmptySpawnBlock(worldIn.func_180495_p(pos.func_177984_a())) ? "block above is not empty/valid" : null;
               }
            }
         }
      }
   }

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
         if (lock > 30) {
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

         label171:
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
                     i3 += worldServerIn.field_73012_v.nextInt(5) - worldServerIn.field_73012_v.nextInt(5);
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
                           BlockPos beforeAdjust = blockpos$mutableblockpos.func_185334_h();
                           adjustAirSpawnPos(worldServerIn, blockpos$mutableblockpos);
                           if (!beforeAdjust.equals(blockpos$mutableblockpos)) {
                              debugSpawn(
                                 "VANILLA adjusted air spawn pos from "
                                    + posToString(beforeAdjust)
                                    + " to "
                                    + posToString(blockpos$mutableblockpos)
                                    + " for "
                                    + biome$spawnlistentry.field_76300_b.getSimpleName()
                              );
                           }
                        } else if (ground == SpawnPlacementType.IN_WATER) {
                           BlockPos beforeAdjust = blockpos$mutableblockpos.func_185334_h();
                           adjustWaterSpawnPos(worldServerIn, blockpos$mutableblockpos);
                           if (!beforeAdjust.equals(blockpos$mutableblockpos)) {
                              debugSpawn(
                                 "VANILLA adjusted water spawn pos from "
                                    + posToString(beforeAdjust)
                                    + " to "
                                    + posToString(blockpos$mutableblockpos)
                                    + " for "
                                    + biome$spawnlistentry.field_76300_b.getSimpleName()
                              );
                           }
                        } else if (ground == SpawnPlacementType.ON_GROUND) {
                           BlockPos beforeAdjust = blockpos$mutableblockpos.func_185334_h();
                           adjustGroundSpawnPos(worldServerIn, blockpos$mutableblockpos);
                           if (!beforeAdjust.equals(blockpos$mutableblockpos)) {
                              debugSpawn(
                                 "VANILLA adjusted ground spawn pos from "
                                    + posToString(beforeAdjust)
                                    + " to "
                                    + posToString(blockpos$mutableblockpos)
                                    + " for "
                                    + biome$spawnlistentry.field_76300_b.getSimpleName()
                              );
                           }
                        }

                        String failureReason = getSpawnFailureReason(ground, worldServerIn, blockpos$mutableblockpos);
                        if (failureReason != null) {
                           debugSpawn(
                              "VANILLA rejected location for "
                                 + biome$spawnlistentry.field_76300_b.getSimpleName()
                                 + " at "
                                 + posToString(blockpos$mutableblockpos)
                                 + " | reason="
                                 + failureReason
                           );
                        } else {
                           EntityParasiteBase entityliving;
                           try {
                              entityliving = (EntityParasiteBase)biome$spawnlistentry.newInstance(worldServerIn);
                              entityliving.canSpawnSpawn = true;
                           } catch (Exception var35) {
                              debugSpawn(
                                 "VANILLA failed to create entity instance for "
                                    + biome$spawnlistentry.field_76300_b.getName()
                                    + " at "
                                    + posToString(blockpos$mutableblockpos)
                                    + " | reason="
                                    + var35.getClass().getSimpleName()
                                    + ": "
                                    + var35.getMessage()
                              );
                              return j4;
                           }

                           float spawnX = blockpos$mutableblockpos.func_177958_n() + 0.5F;
                           float spawnY = blockpos$mutableblockpos.func_177956_o();
                           float spawnZ = blockpos$mutableblockpos.func_177952_p() + 0.5F;
                           entityliving.func_70012_b(spawnX, spawnY, spawnZ, worldServerIn.field_73012_v.nextFloat() * 360.0F, 0.0F);
                           Result canSpawn = ForgeEventFactory.canEntitySpawn(entityliving, worldServerIn, spawnX, spawnY, spawnZ, false);
                           boolean canSpawnHere = entityliving.func_70601_bi();
                           boolean bypassForgeDenyForFloating = canSpawn == Result.DENY
                              && (ground == SpawnPlacementType.IN_WATER || ground == SpawnPlacementType.IN_AIR)
                              && canSpawnHere;
                           if (canSpawn != Result.DENY && (canSpawn != Result.DENY || bypassForgeDenyForFloating)) {
                              if (canSpawn == Result.ALLOW || bypassForgeDenyForFloating || canSpawn == Result.DEFAULT && canSpawnHere) {
                                 if (!ForgeEventFactory.doSpecialSpawn(entityliving, worldServerIn, spawnX, spawnY, spawnZ)) {
                                    ientitylivingdata = entityliving.func_180482_a(worldServerIn.func_175649_E(new BlockPos(entityliving)), ientitylivingdata);
                                 }

                                 if (entityliving.func_70058_J()) {
                                    j2++;
                                    worldServerIn.func_72838_d(entityliving);
                                    applyDebugGlow(entityliving);
                                    debugSpawn(
                                       "VANILLA spawned "
                                          + entityliving.func_70005_c_()
                                          + " at "
                                          + posToString(entityliving.func_180425_c())
                                          + " | packCount="
                                          + j2
                                          + " | glow="
                                          + SRPConfigSystems.debugSpawner
                                    );
                                 } else {
                                    debugSpawn(
                                       "VANILLA failed collision check for " + entityliving.func_70005_c_() + " at " + posToString(blockpos$mutableblockpos)
                                    );
                                    entityliving.func_70106_y();
                                 }

                                 if (j2 >= ForgeEventFactory.getMaxSpawnPackSize(entityliving)) {
                                    debugSpawn(
                                       "VANILLA reached max pack size for " + entityliving.func_70005_c_() + " at " + posToString(blockpos$mutableblockpos)
                                    );
                                    continue label171;
                                 }
                              } else {
                                 debugSpawn(
                                    "VANILLA getCanSpawnHere returned false | selected="
                                       + biome$spawnlistentry.field_76300_b.getName()
                                       + " | actualClass="
                                       + entityliving.getClass().getName()
                                       + " | actualName="
                                       + entityliving.func_70005_c_()
                                       + " | pos="
                                       + posToString(blockpos$mutableblockpos)
                                 );
                              }
                           } else {
                              debugSpawn(
                                 "VANILLA denied by Forge canEntitySpawn for " + entityliving.func_70005_c_() + " at " + posToString(blockpos$mutableblockpos)
                              );
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

   private static void adjustGroundSpawnPos(World worldIn, MutableBlockPos pos) {
      BlockPos adjusted = findGroundSpawnPos(worldIn, pos.func_185334_h());
      pos.func_189533_g(adjusted);
   }

   private static void adjustAirSpawnPos(World worldIn, MutableBlockPos pos) {
      BlockPos adjusted = findAirSpawnPos(worldIn, pos.func_185334_h());
      pos.func_189533_g(adjusted);
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
            Collections.shuffle(shuffled, worldServerIn.field_73012_v);
            MutableBlockPos blockpos$mutableblockpos = new MutableBlockPos();

            label183:
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
                     int l3 = 1 + worldServerIn.field_73012_v.nextInt(4);

                     for (int i4 = 0; i4 < l3; i4++) {
                        l2 += worldServerIn.field_73012_v.nextInt(6) - worldServerIn.field_73012_v.nextInt(6);
                        i3 += worldServerIn.field_73012_v.nextInt(5) - worldServerIn.field_73012_v.nextInt(5);
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
                                 debugSpawn("ORIGIN no spawn entry available at " + posToString(blockpos$mutableblockpos));
                                 break;
                              }
                           }

                           SpawnPlacementType ground = EntitySpawnPlacementRegistry.func_180109_a(biome$spawnlistentry.field_76300_b);
                           if (ground == SpawnPlacementType.IN_AIR) {
                              BlockPos beforeAdjust = SRPConfigSystems.debugSpawner ? blockpos$mutableblockpos.func_185334_h() : null;
                              adjustAirSpawnPos(worldServerIn, blockpos$mutableblockpos);
                              if (beforeAdjust != null && !beforeAdjust.equals(blockpos$mutableblockpos)) {
                                 debugSpawn(
                                    "ORIGIN adjusted air spawn pos from "
                                       + posToString(beforeAdjust)
                                       + " to "
                                       + posToString(blockpos$mutableblockpos)
                                       + " for "
                                       + biome$spawnlistentry.field_76300_b.getSimpleName()
                                 );
                              }
                           } else if (ground == SpawnPlacementType.IN_WATER) {
                              BlockPos beforeAdjust = SRPConfigSystems.debugSpawner ? blockpos$mutableblockpos.func_185334_h() : null;
                              adjustWaterSpawnPos(worldServerIn, blockpos$mutableblockpos);
                              if (beforeAdjust != null && !beforeAdjust.equals(blockpos$mutableblockpos)) {
                                 debugSpawn(
                                    "ORIGIN adjusted water spawn pos from "
                                       + posToString(beforeAdjust)
                                       + " to "
                                       + posToString(blockpos$mutableblockpos)
                                       + " for "
                                       + biome$spawnlistentry.field_76300_b.getSimpleName()
                                 );
                              }
                           } else if (ground == SpawnPlacementType.ON_GROUND) {
                              BlockPos beforeAdjust = SRPConfigSystems.debugSpawner ? blockpos$mutableblockpos.func_185334_h() : null;
                              adjustGroundSpawnPos(worldServerIn, blockpos$mutableblockpos);
                              if (beforeAdjust != null && !beforeAdjust.equals(blockpos$mutableblockpos)) {
                                 debugSpawn(
                                    "ORIGIN adjusted ground spawn pos from "
                                       + posToString(beforeAdjust)
                                       + " to "
                                       + posToString(blockpos$mutableblockpos)
                                       + " for "
                                       + biome$spawnlistentry.field_76300_b.getSimpleName()
                                 );
                              }
                           }

                           String failureReason = getSpawnFailureReason(ground, worldServerIn, blockpos$mutableblockpos);
                           if (failureReason != null) {
                              debugSpawn(
                                 "ORIGIN rejected location for "
                                    + biome$spawnlistentry.field_76300_b.getSimpleName()
                                    + " at "
                                    + posToString(blockpos$mutableblockpos)
                                    + " | reason="
                                    + failureReason
                              );
                           } else {
                              boolean withinOrigin = isPosWithinOrigin(worldServerIn, blockpos$mutableblockpos, worldData, originsExist, originlessAllowed);
                              if (!withinOrigin) {
                                 debugSpawn(
                                    "ORIGIN adjusted position outside origin for "
                                       + biome$spawnlistentry.field_76300_b.getSimpleName()
                                       + " at "
                                       + posToString(blockpos$mutableblockpos)
                                 );
                              } else {
                                 EntityParasiteBase entityliving;
                                 try {
                                    entityliving = (EntityParasiteBase)biome$spawnlistentry.newInstance(worldServerIn);
                                    entityliving.canSpawnSpawn = true;
                                 } catch (Exception var40) {
                                    debugSpawn(
                                       "ORIGIN failed to create entity instance for "
                                          + biome$spawnlistentry.field_76300_b.getName()
                                          + " at "
                                          + posToString(blockpos$mutableblockpos)
                                          + " | reason="
                                          + var40.getClass().getSimpleName()
                                          + ": "
                                          + var40.getMessage()
                                    );
                                    return j4;
                                 }

                                 float spawnX = blockpos$mutableblockpos.func_177958_n() + 0.5F;
                                 float spawnY = blockpos$mutableblockpos.func_177956_o();
                                 float spawnZ = blockpos$mutableblockpos.func_177952_p() + 0.5F;
                                 entityliving.func_70012_b(spawnX, spawnY, spawnZ, worldServerIn.field_73012_v.nextFloat() * 360.0F, 0.0F);
                                 Result canSpawn = ForgeEventFactory.canEntitySpawn(entityliving, worldServerIn, spawnX, spawnY, spawnZ, false);
                                 boolean canSpawnHere = entityliving.func_70601_bi();
                                 if (canSpawn == Result.DENY) {
                                    debugSpawn(
                                       "ORIGIN denied by Forge canEntitySpawn for "
                                          + entityliving.func_70005_c_()
                                          + " at "
                                          + posToString(blockpos$mutableblockpos)
                                    );
                                 } else if (canSpawn == Result.ALLOW || canSpawn == Result.DEFAULT && canSpawnHere) {
                                    if (!ForgeEventFactory.doSpecialSpawn(entityliving, worldServerIn, spawnX, spawnY, spawnZ)) {
                                       ientitylivingdata = entityliving.func_180482_a(
                                          worldServerIn.func_175649_E(new BlockPos(entityliving)), ientitylivingdata
                                       );
                                    }

                                    if (entityliving.func_70058_J()) {
                                       j2++;
                                       worldServerIn.func_72838_d(entityliving);
                                       applyDebugGlow(entityliving);
                                       debugSpawn(
                                          "ORIGIN spawned "
                                             + entityliving.func_70005_c_()
                                             + " at "
                                             + posToString(entityliving.func_180425_c())
                                             + " | packCount="
                                             + j2
                                             + " | glow="
                                             + SRPConfigSystems.debugSpawner
                                       );
                                    } else {
                                       debugSpawn(
                                          "ORIGIN failed collision check for " + entityliving.func_70005_c_() + " at " + posToString(blockpos$mutableblockpos)
                                       );
                                       entityliving.func_70106_y();
                                    }

                                    if (j2 >= ForgeEventFactory.getMaxSpawnPackSize(entityliving)) {
                                       debugSpawn(
                                          "ORIGIN reached max pack size for " + entityliving.func_70005_c_() + " at " + posToString(blockpos$mutableblockpos)
                                       );
                                       continue label183;
                                    }
                                 } else if (!canSpawnHere) {
                                    debugSpawn(
                                       "ORIGIN getCanSpawnHere returned false | selected="
                                          + biome$spawnlistentry.field_76300_b.getName()
                                          + " | actualClass="
                                          + entityliving.getClass().getName()
                                          + " | actualName="
                                          + entityliving.func_70005_c_()
                                          + " | pos="
                                          + posToString(blockpos$mutableblockpos)
                                    );
                                 } else {
                                    debugSpawn(
                                       "ORIGIN spawn failed for "
                                          + entityliving.func_70005_c_()
                                          + " at "
                                          + posToString(blockpos$mutableblockpos)
                                          + " | reason=unknown DEFAULT rejection"
                                    );
                                 }

                                 j4 += j2;
                              }
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

   private static boolean isPosWithinOrigin(World world, BlockPos pos) {
      SRPWorldData data = SRPWorldData.get(world);
      boolean originsExist = data != null && !data.getorigins("x").isEmpty();
      SRPSaveData saveData225 = SRPSaveData.get(world, 225);
      boolean originlessAllowed = saveData225 != null && saveData225.getDeveLevel() >= SRPConfigSystems.deveOriginlessUse;
      return isPosWithinOrigin(world, pos, data, originsExist, originlessAllowed);
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

   private static BlockPos getRandomChunkPosition(World worldIn, int x, int z) {
      int i = x * 16 + worldIn.field_73012_v.nextInt(16);
      int j = z * 16 + worldIn.field_73012_v.nextInt(16);
      int minY = 1;
      int maxY = Math.max(minY + 1, worldIn.func_72940_L() - 2);
      int l = minY + worldIn.field_73012_v.nextInt(maxY - minY + 1);
      return new BlockPos(i, l, j);
   }

   @Nullable
   public static SpawnListEntry getSpawnListEntryForTypeAt(WorldServer worldServerIn, BlockPos pos) {
      SRPSaveData dat = SRPSaveData.get(worldServerIn, 72);
      int id = worldServerIn.field_73011_w.getDimension();
      if (dat == null) {
         debugSpawn("No spawn entry: save data unavailable in dim " + id + " at " + posToString(pos));
         return null;
      } else if (SRPConfigWorld.originActivated) {
         SRPWorldData data = SRPWorldData.get(worldServerIn);
         boolean originsExist = data != null && !data.getorigins("x").isEmpty();
         SRPSaveData saveData225 = SRPSaveData.get(worldServerIn, 225);
         boolean originlessAllowed = saveData225 != null && saveData225.getDeveLevel() >= SRPConfigSystems.deveOriginlessUse;
         return getSpawnListEntryForTypeAtOrigin(worldServerIn, pos, dat, data, originsExist, originlessAllowed);
      } else {
         List<SpawnListEntry> list = SRPSpawning.getSpawns(worldServerIn, id, dat.getEvolutionPhase(id), dat);
         SpawnListEntry chosen = list != null && !list.isEmpty() ? (SpawnListEntry)WeightedRandom.func_76271_a(worldServerIn.field_73012_v, list) : null;
         if (chosen == null) {
            debugSpawn("Spawn list empty/null in dim " + id + " at " + posToString(pos) + " | originActivated=false | phase=" + dat.getEvolutionPhase(id));
         } else {
            debugSpawn(
               "Selected spawn entry "
                  + chosen.field_76300_b.getSimpleName()
                  + " in dim "
                  + id
                  + " at "
                  + posToString(pos)
                  + " | originActivated=false | phase="
                  + dat.getEvolutionPhase(id)
            );
         }

         return chosen;
      }
   }

   @Nullable
   private static SpawnListEntry getSpawnListEntryForTypeAtOrigin(
      WorldServer worldServerIn, BlockPos pos, SRPSaveData dat, SRPWorldData data, boolean originsExist, boolean originlessAllowed
   ) {
      int id = worldServerIn.field_73011_w.getDimension();
      if (!isPosWithinOrigin(worldServerIn, pos, data, originsExist, originlessAllowed)) {
         debugSpawn("ORIGIN position rejected (not within origin) at " + posToString(pos));
         return null;
      } else {
         byte phase = dat.getEvolutionPhase(id);
         if (phase == -1) {
            if (!originsExist) {
               return null;
            } else {
               List<SpawnListEntry> list = SRPSpawning.getSpawns(worldServerIn, id, phase, dat);
               SpawnListEntry chosen = list != null && !list.isEmpty() ? (SpawnListEntry)WeightedRandom.func_76271_a(worldServerIn.field_73012_v, list) : null;
               if (chosen == null) {
                  debugSpawn("Spawn list empty/null in dim " + id + " at " + posToString(pos) + " | originActivated=true | phase=" + phase + " | outbreak=true");
               } else {
                  debugSpawn(
                     "Selected spawn entry "
                        + chosen.field_76300_b.getSimpleName()
                        + " in dim "
                        + id
                        + " at "
                        + posToString(pos)
                        + " | originActivated=true | phase="
                        + phase
                        + " | outbreak=true"
                  );
               }

               return chosen;
            }
         } else {
            int phaseToUse = originsExist ? phase : 0;
            List<SpawnListEntry> list = SRPSpawning.getSpawns(worldServerIn, id, phaseToUse, dat);
            SpawnListEntry chosen = list != null && !list.isEmpty() ? (SpawnListEntry)WeightedRandom.func_76271_a(worldServerIn.field_73012_v, list) : null;
            if (chosen == null) {
               debugSpawn(
                  "Spawn list empty/null in dim "
                     + id
                     + " at "
                     + posToString(pos)
                     + " | originActivated=true | phase="
                     + phaseToUse
                     + (originsExist ? " | originsPresent=true" : " | forcedPhase=0 | noOrigins=true")
               );
            } else {
               debugSpawn(
                  "Selected spawn entry "
                     + chosen.field_76300_b.getSimpleName()
                     + " in dim "
                     + id
                     + " at "
                     + posToString(pos)
                     + " | originActivated=true | phase="
                     + phaseToUse
                     + (originsExist ? " | originsPresent=true" : " | forcedPhase=0 | noOrigins=true")
               );
            }

            return chosen;
         }
      }
   }

   public static boolean canCreatureTypeSpawnAtLocation(SpawnPlacementType spawnPlacementTypeIn, World worldIn, BlockPos pos) {
      return getSpawnFailureReason(spawnPlacementTypeIn, worldIn, pos) == null;
   }

   public static boolean canCreatureTypeSpawnBody(SpawnPlacementType spawnPlacementTypeIn, World worldIn, BlockPos pos) {
      return canCreatureTypeSpawnAtLocation(spawnPlacementTypeIn, worldIn, pos);
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

   private static BlockPos findGroundSpawnPos(World worldIn, BlockPos start) {
      BlockPos best = null;
      int bestDist = Integer.MAX_VALUE;
      int minY = Math.max(1, start.func_177956_o() - 12);
      int maxY = Math.min(worldIn.func_72940_L() - 2, start.func_177956_o() + 12);

      for (int y = minY; y <= maxY; y++) {
         BlockPos candidate = new BlockPos(start.func_177958_n(), y, start.func_177952_p());
         BlockPos below = candidate.func_177977_b();
         IBlockState belowState = worldIn.func_180495_p(below);
         IBlockState atState = worldIn.func_180495_p(candidate);
         IBlockState aboveState = worldIn.func_180495_p(candidate.func_177984_a());
         Block belowBlock = belowState.func_177230_c();
         boolean validFloor = !belowState.func_185904_a().func_76224_d()
            && belowBlock != Blocks.field_150350_a
            && belowBlock != Blocks.field_150362_t
            && belowBlock != Blocks.field_150361_u
            && belowBlock != Blocks.field_150357_h
            && belowBlock != Blocks.field_180401_cv
            && belowBlock.canCreatureSpawn(belowState, worldIn, below, SpawnPlacementType.ON_GROUND);
         boolean openAt = isValidEmptySpawnBlock(atState);
         boolean openAbove = isValidEmptySpawnBlock(aboveState);
         if (validFloor && openAt && openAbove) {
            int dist = Math.abs(y - start.func_177956_o());
            if (dist < bestDist) {
               bestDist = dist;
               best = candidate;
            }
         }
      }

      return best != null ? best : start;
   }

   private static void applyDebugGlow(EntityLiving entityliving) {
      if (SRPConfigSystems.debugSpawner && entityliving != null) {
         entityliving.func_70690_d(new PotionEffect(MobEffects.field_188423_x, 1200, 0, false, false));
      }
   }

   private static BlockPos findWaterSpawnPos(World worldIn, BlockPos start) {
      int x = start.func_177958_n();
      int z = start.func_177952_p();
      BlockPos top = worldIn.func_175672_r(new BlockPos(x, 0, z));
      int topY = Math.max(1, top.func_177956_o() + 2);
      BlockPos best = null;
      int bestDist = Integer.MAX_VALUE;

      for (int y = 1; y <= topY; y++) {
         BlockPos pos = new BlockPos(x, y, z);
         IBlockState at = worldIn.func_180495_p(pos);
         IBlockState below = worldIn.func_180495_p(pos.func_177977_b());
         IBlockState above = worldIn.func_180495_p(pos.func_177984_a());
         boolean valid = at.func_185904_a() == Material.field_151586_h
            && below.func_185904_a() == Material.field_151586_h
            && above.func_185904_a() == Material.field_151586_h;
         if (valid) {
            int dist = Math.abs(y - start.func_177956_o());
            if (dist < bestDist) {
               bestDist = dist;
               best = pos;
            }
         }
      }

      return best != null ? best : start;
   }

   private static void adjustWaterSpawnPos(World worldIn, MutableBlockPos pos) {
      BlockPos adjusted = findWaterSpawnPos(worldIn, pos.func_185334_h());
      pos.func_189533_g(adjusted);
   }

   private static BlockPos findAirSpawnPos(World worldIn, BlockPos start) {
      int x = start.func_177958_n();
      int z = start.func_177952_p();
      int topY = worldIn.func_175672_r(new BlockPos(x, 0, z)).func_177956_o();
      int minY = Math.max(2, topY + 8);
      int maxY = Math.min(SRPConfigWorld.spawnerSKYLimitUp, worldIn.func_72940_L() - 2);
      if (maxY < minY) {
         return start;
      } else {
         List<BlockPos> validPositions = new ArrayList<>();

         for (int y = minY; y <= maxY; y++) {
            BlockPos pos = new BlockPos(x, y, z);
            IBlockState at = worldIn.func_180495_p(pos);
            boolean valid = at.func_177230_c() == Blocks.field_150350_a
               && worldIn.func_180495_p(pos.func_177984_a()).func_177230_c() == Blocks.field_150350_a
               && worldIn.func_180495_p(pos.func_177977_b()).func_177230_c() == Blocks.field_150350_a;
            if (valid) {
               validPositions.add(pos);
            }
         }

         return validPositions.isEmpty() ? start : validPositions.get(worldIn.field_73012_v.nextInt(validPositions.size()));
      }
   }
}
