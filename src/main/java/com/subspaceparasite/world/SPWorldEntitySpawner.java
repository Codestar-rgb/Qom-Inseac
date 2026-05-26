/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Sets
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockRailBase
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLiving$SpawnPlacementType
 *  net.minecraft.entity.EntitySpawnPlacementRegistry
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.MobEffects
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.server.management.PlayerChunkMapEntry
 *  net.minecraft.util.WeightedRandom
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.BlockPos$MutableBlockPos
 *  net.minecraft.util.math.ChunkPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraft.world.biome.Biome$SpawnListEntry
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.fml.common.eventhandler.Event$Result
 */
package com.subspaceparasite.world;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.init.SPSpawning;
import com.subspaceparasite.util.config.SPConfigSystems;
import com.subspaceparasite.util.config.SPConfigWorld;
import com.subspaceparasite.world.SPSaveData;
import com.subspaceparasite.world.SPWorldData;
import com.subspaceparasite.world.biome.BiomeParasiteBase;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.management.PlayerChunkMapEntry;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.common.eventhandler.Event;

public class SPWorldEntitySpawner {
    private static final Set<ChunkPos> eligibleChunksForSpawning = Sets.newHashSet();
    private static int lock = 0;
    public static boolean triggerSPAWNING = false;
    public static int choiceNUMBER = 1;

    private static void debugSpawn(String msg) {
        if (SPConfigSystems.debugSpawner) {
            SPMain.logger.info("[SP Spawner] " + msg);
        }
    }

    private static String posToString(BlockPos pos) {
        return "(" + pos.func_177958_n() + ", " + pos.func_177956_o() + ", " + pos.func_177952_p() + ")";
    }

    @Nullable
    private static String getSpawnFailureReason(EntityLiving.SpawnPlacementType spawnPlacementTypeIn, World worldIn, BlockPos pos) {
        if (!worldIn.func_175723_af().func_177746_a(pos)) {
            return "outside world border";
        }
        IBlockState iblockstate = worldIn.func_180495_p(pos);
        if (spawnPlacementTypeIn == EntityLiving.SpawnPlacementType.IN_WATER) {
            if (iblockstate.func_185904_a() != Material.field_151586_h) {
                return "spawn block is not water";
            }
            if (worldIn.func_180495_p(pos.func_177977_b()).func_185904_a() != Material.field_151586_h) {
                return "block below is not water";
            }
            if (worldIn.func_180495_p(pos.func_177984_a()).func_185904_a() != Material.field_151586_h) {
                return "block above is not water";
            }
            return null;
        }
        if (spawnPlacementTypeIn == EntityLiving.SpawnPlacementType.IN_AIR) {
            if (iblockstate.func_177230_c() != Blocks.field_150350_a) {
                return "spawn block is not air";
            }
            if (worldIn.func_180495_p(pos.func_177984_a()).func_185915_l()) {
                return "block above is solid";
            }
            return null;
        }
        BlockPos blockpos = pos.func_177977_b();
        IBlockState state = worldIn.func_180495_p(blockpos);
        if (!state.func_177230_c().canCreatureSpawn(state, (IBlockAccess)worldIn, blockpos, spawnPlacementTypeIn)) {
            return "block below does not allow creature spawn: " + state.func_177230_c().getRegistryName();
        }
        Block block = state.func_177230_c();
        if (block == Blocks.field_150357_h || block == Blocks.field_180401_cv) {
            return "block below is forbidden: " + block.getRegistryName();
        }
        if (!SPWorldEntitySpawner.isValidEmptySpawnBlock(iblockstate)) {
            return "spawn block is not empty/valid";
        }
        if (!SPWorldEntitySpawner.isValidEmptySpawnBlock(worldIn.func_180495_p(pos.func_177984_a()))) {
            return "block above is not empty/valid";
        }
        return null;
    }

    public static int findChunksForSpawning(WorldServer worldServerIn, boolean spawnHostileMobs, boolean spawnPeacefulMobs, boolean spawnOnSetTickRate) {
        if (!SPConfigWorld.originActivated) {
            return SPWorldEntitySpawner.findChunksForSpawningVanilla(worldServerIn, spawnHostileMobs, spawnPeacefulMobs, spawnOnSetTickRate);
        }
        return SPWorldEntitySpawner.findChunksForSpawningOrigin(worldServerIn, spawnHostileMobs, spawnPeacefulMobs, spawnOnSetTickRate);
    }

    public static int findChunksForSpawningVanilla(WorldServer worldServerIn, boolean spawnHostileMobs, boolean spawnPeacefulMobs, boolean spawnOnSetTickRate) {
        if (!spawnHostileMobs && !spawnPeacefulMobs) {
            return 0;
        }
        if (!SPSpawning.totalParasites) {
            if (++lock > 30) {
                SPSpawning.totalParasites = true;
                lock = 0;
            }
            return 0;
        }
        eligibleChunksForSpawning.clear();
        for (EntityPlayer entityplayer : worldServerIn.field_73010_i) {
            if (entityplayer.func_175149_v()) continue;
            int j = MathHelper.func_76128_c((double)(entityplayer.field_70165_t / 16.0));
            int k = MathHelper.func_76128_c((double)(entityplayer.field_70161_v / 16.0));
            for (int i1 = -8; i1 <= 8; ++i1) {
                for (int j1 = -8; j1 <= 8; ++j1) {
                    PlayerChunkMapEntry playerchunkmapentry;
                    boolean flag = i1 == -8 || i1 == 8 || j1 == -8 || j1 == 8;
                    ChunkPos chunkpos = new ChunkPos(i1 + j, j1 + k);
                    if (eligibleChunksForSpawning.contains(chunkpos) || flag || !worldServerIn.func_175723_af().func_177730_a(chunkpos) || (playerchunkmapentry = worldServerIn.func_184164_w().func_187301_b(chunkpos.field_77276_a, chunkpos.field_77275_b)) == null || !playerchunkmapentry.func_187274_e()) continue;
                    eligibleChunksForSpawning.add(chunkpos);
                }
            }
        }
        int j4 = 0;
        BlockPos blockpos1 = worldServerIn.func_175694_M();
        ArrayList shuffled = Lists.newArrayList(eligibleChunksForSpawning);
        Collections.shuffle(shuffled);
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        block5: for (ChunkPos chunkpos1 : shuffled) {
            BlockPos blockpos = SPWorldEntitySpawner.getRandomChunkPosition((World)worldServerIn, chunkpos1.field_77276_a, chunkpos1.field_77275_b);
            int k1 = blockpos.func_177958_n();
            int l1 = blockpos.func_177956_o();
            int i2 = blockpos.func_177952_p();
            IBlockState iblockstate = worldServerIn.func_180495_p(blockpos);
            if (iblockstate.func_185915_l()) continue;
            int j2 = 0;
            block6: for (int k2 = 0; k2 < 3; ++k2) {
                int l2 = k1;
                int i3 = l1;
                int j3 = i2;
                Biome.SpawnListEntry biome$spawnlistentry = null;
                IEntityLivingData ientitylivingdata = null;
                int l3 = MathHelper.func_76143_f((double)(Math.random() * 4.0));
                for (int i4 = 0; i4 < l3; ++i4) {
                    boolean bypassForgeDenyForFloating;
                    EntityParasiteBase entityliving;
                    BlockPos beforeAdjust;
                    blockpos$mutableblockpos.func_181079_c(l2 += worldServerIn.field_73012_v.nextInt(6) - worldServerIn.field_73012_v.nextInt(6), i3 += worldServerIn.field_73012_v.nextInt(5) - worldServerIn.field_73012_v.nextInt(5), j3 += worldServerIn.field_73012_v.nextInt(6) - worldServerIn.field_73012_v.nextInt(6));
                    float f = (float)l2 + 0.5f;
                    float f1 = (float)j3 + 0.5f;
                    if (worldServerIn.func_175636_b((double)f, (double)i3, (double)f1, 24.0) || !(blockpos1.func_177954_c((double)f, (double)i3, (double)f1) >= 576.0)) continue;
                    if (biome$spawnlistentry == null && (biome$spawnlistentry = SPWorldEntitySpawner.getSpawnListEntryForTypeAt(worldServerIn, (BlockPos)blockpos$mutableblockpos)) == null) continue block6;
                    EntityLiving.SpawnPlacementType ground = EntitySpawnPlacementRegistry.func_180109_a((Class)biome$spawnlistentry.field_76300_b);
                    if (ground == EntityLiving.SpawnPlacementType.IN_AIR) {
                        beforeAdjust = blockpos$mutableblockpos.func_185334_h();
                        SPWorldEntitySpawner.adjustAirSpawnPos((World)worldServerIn, blockpos$mutableblockpos);
                        if (!beforeAdjust.equals((Object)blockpos$mutableblockpos)) {
                            SPWorldEntitySpawner.debugSpawn("VANILLA adjusted air spawn pos from " + SPWorldEntitySpawner.posToString(beforeAdjust) + " to " + SPWorldEntitySpawner.posToString((BlockPos)blockpos$mutableblockpos) + " for " + biome$spawnlistentry.field_76300_b.getSimpleName());
                        }
                    } else if (ground == EntityLiving.SpawnPlacementType.IN_WATER) {
                        beforeAdjust = blockpos$mutableblockpos.func_185334_h();
                        SPWorldEntitySpawner.adjustWaterSpawnPos((World)worldServerIn, blockpos$mutableblockpos);
                        if (!beforeAdjust.equals((Object)blockpos$mutableblockpos)) {
                            SPWorldEntitySpawner.debugSpawn("VANILLA adjusted water spawn pos from " + SPWorldEntitySpawner.posToString(beforeAdjust) + " to " + SPWorldEntitySpawner.posToString((BlockPos)blockpos$mutableblockpos) + " for " + biome$spawnlistentry.field_76300_b.getSimpleName());
                        }
                    } else if (ground == EntityLiving.SpawnPlacementType.ON_GROUND) {
                        beforeAdjust = blockpos$mutableblockpos.func_185334_h();
                        SPWorldEntitySpawner.adjustGroundSpawnPos((World)worldServerIn, blockpos$mutableblockpos);
                        if (!beforeAdjust.equals((Object)blockpos$mutableblockpos)) {
                            SPWorldEntitySpawner.debugSpawn("VANILLA adjusted ground spawn pos from " + SPWorldEntitySpawner.posToString(beforeAdjust) + " to " + SPWorldEntitySpawner.posToString((BlockPos)blockpos$mutableblockpos) + " for " + biome$spawnlistentry.field_76300_b.getSimpleName());
                        }
                    }
                    String failureReason = SPWorldEntitySpawner.getSpawnFailureReason(ground, (World)worldServerIn, (BlockPos)blockpos$mutableblockpos);
                    if (failureReason != null) {
                        SPWorldEntitySpawner.debugSpawn("VANILLA rejected location for " + biome$spawnlistentry.field_76300_b.getSimpleName() + " at " + SPWorldEntitySpawner.posToString((BlockPos)blockpos$mutableblockpos) + " | reason=" + failureReason);
                        continue;
                    }
                    try {
                        entityliving = (EntityParasiteBase)biome$spawnlistentry.newInstance((World)worldServerIn);
                        entityliving.canSpawnSpawn = true;
                    }
                    catch (Exception exception) {
                        SPWorldEntitySpawner.debugSpawn("VANILLA failed to create entity instance for " + biome$spawnlistentry.field_76300_b.getName() + " at " + SPWorldEntitySpawner.posToString((BlockPos)blockpos$mutableblockpos) + " | reason=" + exception.getClass().getSimpleName() + ": " + exception.getMessage());
                        return j4;
                    }
                    float spawnX = (float)blockpos$mutableblockpos.func_177958_n() + 0.5f;
                    float spawnY = blockpos$mutableblockpos.func_177956_o();
                    float spawnZ = (float)blockpos$mutableblockpos.func_177952_p() + 0.5f;
                    entityliving.func_70012_b(spawnX, spawnY, spawnZ, worldServerIn.field_73012_v.nextFloat() * 360.0f, 0.0f);
                    Event.Result canSpawn = ForgeEventFactory.canEntitySpawn((EntityLiving)entityliving, (World)worldServerIn, (float)spawnX, (float)spawnY, (float)spawnZ, (boolean)false);
                    boolean canSpawnHere = entityliving.func_70601_bi();
                    boolean bl = bypassForgeDenyForFloating = canSpawn == Event.Result.DENY && (ground == EntityLiving.SpawnPlacementType.IN_WATER || ground == EntityLiving.SpawnPlacementType.IN_AIR) && canSpawnHere;
                    if (canSpawn == Event.Result.DENY || canSpawn == Event.Result.DENY && !bypassForgeDenyForFloating) {
                        SPWorldEntitySpawner.debugSpawn("VANILLA denied by Forge canEntitySpawn for " + entityliving.func_70005_c_() + " at " + SPWorldEntitySpawner.posToString((BlockPos)blockpos$mutableblockpos));
                    } else if (canSpawn == Event.Result.ALLOW || bypassForgeDenyForFloating || canSpawn == Event.Result.DEFAULT && canSpawnHere) {
                        if (!ForgeEventFactory.doSpecialSpawn((EntityLiving)entityliving, (World)worldServerIn, (float)spawnX, (float)spawnY, (float)spawnZ)) {
                            ientitylivingdata = entityliving.func_180482_a(worldServerIn.func_175649_E(new BlockPos((Entity)entityliving)), ientitylivingdata);
                        }
                        if (entityliving.func_70058_J()) {
                            worldServerIn.func_72838_d((Entity)entityliving);
                            SPWorldEntitySpawner.applyDebugGlow((EntityLiving)entityliving);
                            SPWorldEntitySpawner.debugSpawn("VANILLA spawned " + entityliving.func_70005_c_() + " at " + SPWorldEntitySpawner.posToString(entityliving.func_180425_c()) + " | packCount=" + ++j2 + " | glow=" + SPConfigSystems.debugSpawner);
                        } else {
                            SPWorldEntitySpawner.debugSpawn("VANILLA failed collision check for " + entityliving.func_70005_c_() + " at " + SPWorldEntitySpawner.posToString((BlockPos)blockpos$mutableblockpos));
                            entityliving.func_70106_y();
                        }
                        if (j2 >= ForgeEventFactory.getMaxSpawnPackSize((EntityLiving)entityliving)) {
                            SPWorldEntitySpawner.debugSpawn("VANILLA reached max pack size for " + entityliving.func_70005_c_() + " at " + SPWorldEntitySpawner.posToString((BlockPos)blockpos$mutableblockpos));
                            continue block5;
                        }
                    } else {
                        SPWorldEntitySpawner.debugSpawn("VANILLA getCanSpawnHere returned false | selected=" + biome$spawnlistentry.field_76300_b.getName() + " | actualClass=" + entityliving.getClass().getName() + " | actualName=" + entityliving.func_70005_c_() + " | pos=" + SPWorldEntitySpawner.posToString((BlockPos)blockpos$mutableblockpos));
                    }
                    j4 += j2;
                }
            }
        }
        return j4;
    }

    private static void adjustGroundSpawnPos(World worldIn, BlockPos.MutableBlockPos pos) {
        BlockPos adjusted = SPWorldEntitySpawner.findGroundSpawnPos(worldIn, pos.func_185334_h());
        pos.func_189533_g((Vec3i)adjusted);
    }

    private static void adjustAirSpawnPos(World worldIn, BlockPos.MutableBlockPos pos) {
        BlockPos adjusted = SPWorldEntitySpawner.findAirSpawnPos(worldIn, pos.func_185334_h());
        pos.func_189533_g((Vec3i)adjusted);
    }

    public static int findChunksForSpawningOrigin(WorldServer worldServerIn, boolean spawnHostileMobs, boolean spawnPeacefulMobs, boolean spawnOnSetTickRate) {
        if (!spawnHostileMobs && !spawnPeacefulMobs) {
            return 0;
        }
        if (!SPSpawning.totalParasites) {
            if (++lock > 7) {
                SPSpawning.totalParasites = true;
                lock = 0;
            }
            return 0;
        }
        eligibleChunksForSpawning.clear();
        SPWorldData worldData = SPWorldData.get((World)worldServerIn);
        SPSaveData saveData225 = SPSaveData.get((World)worldServerIn, 225);
        SPSaveData saveData72 = SPSaveData.get((World)worldServerIn, 72);
        boolean originsExist = worldData != null && !worldData.getorigins("x").isEmpty();
        boolean originlessAllowed = saveData225 != null && saveData225.getDeveLevel() >= SPConfigSystems.deveOriginlessUse;
        for (EntityPlayer entityplayer : worldServerIn.field_73010_i) {
            if (entityplayer.func_175149_v()) continue;
            int j = MathHelper.func_76128_c((double)(entityplayer.field_70165_t / 16.0));
            int k = MathHelper.func_76128_c((double)(entityplayer.field_70161_v / 16.0));
            for (int i1 = -8; i1 <= 8; ++i1) {
                for (int j1 = -8; j1 <= 8; ++j1) {
                    PlayerChunkMapEntry playerchunkmapentry;
                    boolean flag = i1 == -8 || i1 == 8 || j1 == -8 || j1 == 8;
                    ChunkPos chunkpos = new ChunkPos(i1 + j, j1 + k);
                    if (eligibleChunksForSpawning.contains(chunkpos) || flag || !worldServerIn.func_175723_af().func_177730_a(chunkpos) || (playerchunkmapentry = worldServerIn.func_184164_w().func_187301_b(chunkpos.field_77276_a, chunkpos.field_77275_b)) == null || !playerchunkmapentry.func_187274_e()) continue;
                    eligibleChunksForSpawning.add(chunkpos);
                }
            }
        }
        SPWorldEntitySpawner.filterEligibleChunksForOrigin(worldData, originsExist, originlessAllowed);
        if (eligibleChunksForSpawning.isEmpty()) {
            return 0;
        }
        int j4 = 0;
        BlockPos blockpos1 = worldServerIn.func_175694_M();
        ArrayList shuffled = Lists.newArrayList(eligibleChunksForSpawning);
        Collections.shuffle(shuffled, worldServerIn.field_73012_v);
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        block5: for (ChunkPos chunkpos1 : shuffled) {
            BlockPos blockpos = SPWorldEntitySpawner.getRandomChunkPosition((World)worldServerIn, chunkpos1.field_77276_a, chunkpos1.field_77275_b);
            int k1 = blockpos.func_177958_n();
            int l1 = blockpos.func_177956_o();
            int i2 = blockpos.func_177952_p();
            IBlockState iblockstate = worldServerIn.func_180495_p(blockpos);
            if (iblockstate.func_185915_l()) continue;
            int j2 = 0;
            block6: for (int k2 = 0; k2 < 3; ++k2) {
                int l2 = k1;
                int i3 = l1;
                int j3 = i2;
                Biome.SpawnListEntry biome$spawnlistentry = null;
                IEntityLivingData ientitylivingdata = null;
                int l3 = 1 + worldServerIn.field_73012_v.nextInt(4);
                for (int i4 = 0; i4 < l3; ++i4) {
                    EntityParasiteBase entityliving;
                    BlockPos beforeAdjust;
                    blockpos$mutableblockpos.func_181079_c(l2 += worldServerIn.field_73012_v.nextInt(6) - worldServerIn.field_73012_v.nextInt(6), i3 += worldServerIn.field_73012_v.nextInt(5) - worldServerIn.field_73012_v.nextInt(5), j3 += worldServerIn.field_73012_v.nextInt(6) - worldServerIn.field_73012_v.nextInt(6));
                    float f = (float)l2 + 0.5f;
                    float f1 = (float)j3 + 0.5f;
                    if (worldServerIn.func_175636_b((double)f, (double)i3, (double)f1, 24.0) || !(blockpos1.func_177954_c((double)f, (double)i3, (double)f1) >= 576.0)) continue;
                    if (biome$spawnlistentry == null && (biome$spawnlistentry = SPWorldEntitySpawner.getSpawnListEntryForTypeAtOrigin(worldServerIn, (BlockPos)blockpos$mutableblockpos, saveData72, worldData, originsExist, originlessAllowed)) == null) {
                        SPWorldEntitySpawner.debugSpawn("ORIGIN no spawn entry available at " + SPWorldEntitySpawner.posToString((BlockPos)blockpos$mutableblockpos));
                        continue block6;
                    }
                    EntityLiving.SpawnPlacementType ground = EntitySpawnPlacementRegistry.func_180109_a((Class)biome$spawnlistentry.field_76300_b);
                    if (ground == EntityLiving.SpawnPlacementType.IN_AIR) {
                        beforeAdjust = SPConfigSystems.debugSpawner ? blockpos$mutableblockpos.func_185334_h() : null;
                        SPWorldEntitySpawner.adjustAirSpawnPos((World)worldServerIn, blockpos$mutableblockpos);
                        if (beforeAdjust != null && !beforeAdjust.equals((Object)blockpos$mutableblockpos)) {
                            SPWorldEntitySpawner.debugSpawn("ORIGIN adjusted air spawn pos from " + SPWorldEntitySpawner.posToString(beforeAdjust) + " to " + SPWorldEntitySpawner.posToString((BlockPos)blockpos$mutableblockpos) + " for " + biome$spawnlistentry.field_76300_b.getSimpleName());
                        }
                    } else if (ground == EntityLiving.SpawnPlacementType.IN_WATER) {
                        beforeAdjust = SPConfigSystems.debugSpawner ? blockpos$mutableblockpos.func_185334_h() : null;
                        SPWorldEntitySpawner.adjustWaterSpawnPos((World)worldServerIn, blockpos$mutableblockpos);
                        if (beforeAdjust != null && !beforeAdjust.equals((Object)blockpos$mutableblockpos)) {
                            SPWorldEntitySpawner.debugSpawn("ORIGIN adjusted water spawn pos from " + SPWorldEntitySpawner.posToString(beforeAdjust) + " to " + SPWorldEntitySpawner.posToString((BlockPos)blockpos$mutableblockpos) + " for " + biome$spawnlistentry.field_76300_b.getSimpleName());
                        }
                    } else if (ground == EntityLiving.SpawnPlacementType.ON_GROUND) {
                        beforeAdjust = SPConfigSystems.debugSpawner ? blockpos$mutableblockpos.func_185334_h() : null;
                        SPWorldEntitySpawner.adjustGroundSpawnPos((World)worldServerIn, blockpos$mutableblockpos);
                        if (beforeAdjust != null && !beforeAdjust.equals((Object)blockpos$mutableblockpos)) {
                            SPWorldEntitySpawner.debugSpawn("ORIGIN adjusted ground spawn pos from " + SPWorldEntitySpawner.posToString(beforeAdjust) + " to " + SPWorldEntitySpawner.posToString((BlockPos)blockpos$mutableblockpos) + " for " + biome$spawnlistentry.field_76300_b.getSimpleName());
                        }
                    }
                    String failureReason = SPWorldEntitySpawner.getSpawnFailureReason(ground, (World)worldServerIn, (BlockPos)blockpos$mutableblockpos);
                    if (failureReason != null) {
                        SPWorldEntitySpawner.debugSpawn("ORIGIN rejected location for " + biome$spawnlistentry.field_76300_b.getSimpleName() + " at " + SPWorldEntitySpawner.posToString((BlockPos)blockpos$mutableblockpos) + " | reason=" + failureReason);
                        continue;
                    }
                    boolean withinOrigin = SPWorldEntitySpawner.isPosWithinOrigin((World)worldServerIn, (BlockPos)blockpos$mutableblockpos, worldData, originsExist, originlessAllowed);
                    if (!withinOrigin) {
                        SPWorldEntitySpawner.debugSpawn("ORIGIN adjusted position outside origin for " + biome$spawnlistentry.field_76300_b.getSimpleName() + " at " + SPWorldEntitySpawner.posToString((BlockPos)blockpos$mutableblockpos));
                        continue;
                    }
                    try {
                        entityliving = (EntityParasiteBase)biome$spawnlistentry.newInstance((World)worldServerIn);
                        entityliving.canSpawnSpawn = true;
                    }
                    catch (Exception exception) {
                        SPWorldEntitySpawner.debugSpawn("ORIGIN failed to create entity instance for " + biome$spawnlistentry.field_76300_b.getName() + " at " + SPWorldEntitySpawner.posToString((BlockPos)blockpos$mutableblockpos) + " | reason=" + exception.getClass().getSimpleName() + ": " + exception.getMessage());
                        return j4;
                    }
                    float spawnX = (float)blockpos$mutableblockpos.func_177958_n() + 0.5f;
                    float spawnY = blockpos$mutableblockpos.func_177956_o();
                    float spawnZ = (float)blockpos$mutableblockpos.func_177952_p() + 0.5f;
                    entityliving.func_70012_b(spawnX, spawnY, spawnZ, worldServerIn.field_73012_v.nextFloat() * 360.0f, 0.0f);
                    Event.Result canSpawn = ForgeEventFactory.canEntitySpawn((EntityLiving)entityliving, (World)worldServerIn, (float)spawnX, (float)spawnY, (float)spawnZ, (boolean)false);
                    boolean canSpawnHere = entityliving.func_70601_bi();
                    if (canSpawn == Event.Result.DENY) {
                        SPWorldEntitySpawner.debugSpawn("ORIGIN denied by Forge canEntitySpawn for " + entityliving.func_70005_c_() + " at " + SPWorldEntitySpawner.posToString((BlockPos)blockpos$mutableblockpos));
                    } else if (canSpawn == Event.Result.ALLOW || canSpawn == Event.Result.DEFAULT && canSpawnHere) {
                        if (!ForgeEventFactory.doSpecialSpawn((EntityLiving)entityliving, (World)worldServerIn, (float)spawnX, (float)spawnY, (float)spawnZ)) {
                            ientitylivingdata = entityliving.func_180482_a(worldServerIn.func_175649_E(new BlockPos((Entity)entityliving)), ientitylivingdata);
                        }
                        if (entityliving.func_70058_J()) {
                            worldServerIn.func_72838_d((Entity)entityliving);
                            SPWorldEntitySpawner.applyDebugGlow((EntityLiving)entityliving);
                            SPWorldEntitySpawner.debugSpawn("ORIGIN spawned " + entityliving.func_70005_c_() + " at " + SPWorldEntitySpawner.posToString(entityliving.func_180425_c()) + " | packCount=" + ++j2 + " | glow=" + SPConfigSystems.debugSpawner);
                        } else {
                            SPWorldEntitySpawner.debugSpawn("ORIGIN failed collision check for " + entityliving.func_70005_c_() + " at " + SPWorldEntitySpawner.posToString((BlockPos)blockpos$mutableblockpos));
                            entityliving.func_70106_y();
                        }
                        if (j2 >= ForgeEventFactory.getMaxSpawnPackSize((EntityLiving)entityliving)) {
                            SPWorldEntitySpawner.debugSpawn("ORIGIN reached max pack size for " + entityliving.func_70005_c_() + " at " + SPWorldEntitySpawner.posToString((BlockPos)blockpos$mutableblockpos));
                            continue block5;
                        }
                    } else if (!canSpawnHere) {
                        SPWorldEntitySpawner.debugSpawn("ORIGIN getCanSpawnHere returned false | selected=" + biome$spawnlistentry.field_76300_b.getName() + " | actualClass=" + entityliving.getClass().getName() + " | actualName=" + entityliving.func_70005_c_() + " | pos=" + SPWorldEntitySpawner.posToString((BlockPos)blockpos$mutableblockpos));
                    } else {
                        SPWorldEntitySpawner.debugSpawn("ORIGIN spawn failed for " + entityliving.func_70005_c_() + " at " + SPWorldEntitySpawner.posToString((BlockPos)blockpos$mutableblockpos) + " | reason=unknown DEFAULT rejection");
                    }
                    j4 += j2;
                }
            }
        }
        return j4;
    }

    private static boolean isPosWithinOrigin(World world, BlockPos pos) {
        SPWorldData data = SPWorldData.get(world);
        boolean originsExist = data != null && !data.getorigins("x").isEmpty();
        SPSaveData saveData225 = SPSaveData.get(world, 225);
        boolean originlessAllowed = saveData225 != null && saveData225.getDeveLevel() >= SPConfigSystems.deveOriginlessUse;
        return SPWorldEntitySpawner.isPosWithinOrigin(world, pos, data, originsExist, originlessAllowed);
    }

    private static boolean isPosWithinOrigin(World world, BlockPos pos, SPWorldData data, boolean originsExist, boolean originlessAllowed) {
        if (world.func_180494_b(pos) instanceof BiomeParasiteBase) {
            return true;
        }
        if (data == null) {
            return false;
        }
        int a = data.nearestInfectionValue(pos, false);
        if (data.nearestColonyPosition(pos, true) != null) {
            return true;
        }
        return a > 0 || originsExist && originlessAllowed || !originsExist && !data.getTriggerMet();
    }

    private static boolean chunkIntersectsRadius2D(int chunkX, int chunkZ, int centerX, int centerZ, int radius) {
        long radiusSq;
        int closestZ;
        long dz;
        if (radius <= 0) {
            return false;
        }
        int minX = chunkX << 4;
        int maxX = minX + 15;
        int minZ = chunkZ << 4;
        int maxZ = minZ + 15;
        int closestX = Math.max(minX, Math.min(centerX, maxX));
        long dx = (long)centerX - (long)closestX;
        return dx * dx + (dz = (long)centerZ - (long)(closestZ = Math.max(minZ, Math.min(centerZ, maxZ)))) * dz <= (radiusSq = (long)radius * (long)radius);
    }

    private static void filterEligibleChunksForOrigin(SPWorldData data, boolean originsExist, boolean originlessAllowed) {
        if (!originsExist || originlessAllowed || data == null || eligibleChunksForSpawning.isEmpty()) {
            return;
        }
        ArrayList<Integer> originsX = data.getorigins("x");
        ArrayList<Integer> originsZ = data.getorigins("z");
        ArrayList<Integer> originsA = data.getorigins("a");
        int originCount = Math.min(originsX.size(), Math.min(originsZ.size(), originsA.size()));
        ArrayList<Integer> coloniesX = data.getColonies("x");
        ArrayList<Integer> coloniesY = data.getColonies("y");
        ArrayList<Integer> coloniesZ = data.getColonies("z");
        int colonyCount = Math.min(coloniesX.size(), Math.min(coloniesY.size(), coloniesZ.size()));
        int[] colonyRadii = new int[colonyCount];
        for (int i = 0; i < colonyCount; ++i) {
            BlockPos colonyPos = new BlockPos(coloniesX.get(i).intValue(), coloniesY.get(i).intValue(), coloniesZ.get(i).intValue());
            colonyRadii[i] = Math.max(0, data.getColonyDistanceSpreadByPosition(colonyPos, false));
        }
        Iterator<ChunkPos> chunkIterator = eligibleChunksForSpawning.iterator();
        while (chunkIterator.hasNext()) {
            int i;
            ChunkPos chunkPos = chunkIterator.next();
            boolean inRange = false;
            for (i = 0; i < originCount; ++i) {
                if (!SPWorldEntitySpawner.chunkIntersectsRadius2D(chunkPos.field_77276_a, chunkPos.field_77275_b, originsX.get(i), originsZ.get(i), originsA.get(i))) continue;
                inRange = true;
                break;
            }
            if (!inRange) {
                for (i = 0; i < colonyCount; ++i) {
                    if (!SPWorldEntitySpawner.chunkIntersectsRadius2D(chunkPos.field_77276_a, chunkPos.field_77275_b, coloniesX.get(i), coloniesZ.get(i), colonyRadii[i])) continue;
                    inRange = true;
                    break;
                }
            }
            if (inRange) continue;
            chunkIterator.remove();
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
    public static Biome.SpawnListEntry getSpawnListEntryForTypeAt(WorldServer worldServerIn, BlockPos pos) {
        Biome.SpawnListEntry chosen;
        SPSaveData dat = SPSaveData.get((World)worldServerIn, 72);
        int id = worldServerIn.field_73011_w.getDimension();
        if (dat == null) {
            SPWorldEntitySpawner.debugSpawn("No spawn entry: save data unavailable in dim " + id + " at " + SPWorldEntitySpawner.posToString(pos));
            return null;
        }
        if (SPConfigWorld.originActivated) {
            SPWorldData data = SPWorldData.get((World)worldServerIn);
            boolean originsExist = data != null && !data.getorigins("x").isEmpty();
            SPSaveData saveData225 = SPSaveData.get((World)worldServerIn, 225);
            boolean originlessAllowed = saveData225 != null && saveData225.getDeveLevel() >= SPConfigSystems.deveOriginlessUse;
            return SPWorldEntitySpawner.getSpawnListEntryForTypeAtOrigin(worldServerIn, pos, dat, data, originsExist, originlessAllowed);
        }
        List<Biome.SpawnListEntry> list = SPSpawning.getSpawns((World)worldServerIn, id, dat.getEvolutionPhase(id), dat);
        Biome.SpawnListEntry spawnListEntry = chosen = list != null && !list.isEmpty() ? (Biome.SpawnListEntry)WeightedRandom.func_76271_a((Random)worldServerIn.field_73012_v, list) : null;
        if (chosen == null) {
            SPWorldEntitySpawner.debugSpawn("Spawn list empty/null in dim " + id + " at " + SPWorldEntitySpawner.posToString(pos) + " | originActivated=false | phase=" + dat.getEvolutionPhase(id));
        } else {
            SPWorldEntitySpawner.debugSpawn("Selected spawn entry " + chosen.field_76300_b.getSimpleName() + " in dim " + id + " at " + SPWorldEntitySpawner.posToString(pos) + " | originActivated=false | phase=" + dat.getEvolutionPhase(id));
        }
        return chosen;
    }

    @Nullable
    private static Biome.SpawnListEntry getSpawnListEntryForTypeAtOrigin(WorldServer worldServerIn, BlockPos pos, SPSaveData dat, SPWorldData data, boolean originsExist, boolean originlessAllowed) {
        Biome.SpawnListEntry chosen;
        int id = worldServerIn.field_73011_w.getDimension();
        if (!SPWorldEntitySpawner.isPosWithinOrigin((World)worldServerIn, pos, data, originsExist, originlessAllowed)) {
            SPWorldEntitySpawner.debugSpawn("ORIGIN position rejected (not within origin) at " + SPWorldEntitySpawner.posToString(pos));
            return null;
        }
        byte phase = dat.getEvolutionPhase(id);
        if (phase == -1) {
            Biome.SpawnListEntry chosen2;
            if (!originsExist) {
                return null;
            }
            List<Biome.SpawnListEntry> list = SPSpawning.getSpawns((World)worldServerIn, id, phase, dat);
            Biome.SpawnListEntry spawnListEntry = chosen2 = list != null && !list.isEmpty() ? (Biome.SpawnListEntry)WeightedRandom.func_76271_a((Random)worldServerIn.field_73012_v, list) : null;
            if (chosen2 == null) {
                SPWorldEntitySpawner.debugSpawn("Spawn list empty/null in dim " + id + " at " + SPWorldEntitySpawner.posToString(pos) + " | originActivated=true | phase=" + phase + " | outbreak=true");
            } else {
                SPWorldEntitySpawner.debugSpawn("Selected spawn entry " + chosen2.field_76300_b.getSimpleName() + " in dim " + id + " at " + SPWorldEntitySpawner.posToString(pos) + " | originActivated=true | phase=" + phase + " | outbreak=true");
            }
            return chosen2;
        }
        byte phaseToUse = originsExist ? phase : (byte)0;
        List<Biome.SpawnListEntry> list = SPSpawning.getSpawns((World)worldServerIn, id, phaseToUse, dat);
        Biome.SpawnListEntry spawnListEntry = chosen = list != null && !list.isEmpty() ? (Biome.SpawnListEntry)WeightedRandom.func_76271_a((Random)worldServerIn.field_73012_v, list) : null;
        if (chosen == null) {
            SPWorldEntitySpawner.debugSpawn("Spawn list empty/null in dim " + id + " at " + SPWorldEntitySpawner.posToString(pos) + " | originActivated=true | phase=" + phaseToUse + (originsExist ? " | originsPresent=true" : " | forcedPhase=0 | noOrigins=true"));
        } else {
            SPWorldEntitySpawner.debugSpawn("Selected spawn entry " + chosen.field_76300_b.getSimpleName() + " in dim " + id + " at " + SPWorldEntitySpawner.posToString(pos) + " | originActivated=true | phase=" + phaseToUse + (originsExist ? " | originsPresent=true" : " | forcedPhase=0 | noOrigins=true"));
        }
        return chosen;
    }

    public static boolean canCreatureTypeSpawnAtLocation(EntityLiving.SpawnPlacementType spawnPlacementTypeIn, World worldIn, BlockPos pos) {
        return SPWorldEntitySpawner.getSpawnFailureReason(spawnPlacementTypeIn, worldIn, pos) == null;
    }

    public static boolean canCreatureTypeSpawnBody(EntityLiving.SpawnPlacementType spawnPlacementTypeIn, World worldIn, BlockPos pos) {
        return SPWorldEntitySpawner.canCreatureTypeSpawnAtLocation(spawnPlacementTypeIn, worldIn, pos);
    }

    public static boolean isValidEmptySpawnBlock(IBlockState state) {
        if (state.func_185898_k()) {
            return false;
        }
        if (state.func_185897_m()) {
            return false;
        }
        if (state.func_185904_a().func_76224_d()) {
            return false;
        }
        return !BlockRailBase.func_176563_d((IBlockState)state);
    }

    private static BlockPos findGroundSpawnPos(World worldIn, BlockPos start) {
        BlockPos best = null;
        int bestDist = Integer.MAX_VALUE;
        int minY = Math.max(1, start.func_177956_o() - 12);
        int maxY = Math.min(worldIn.func_72940_L() - 2, start.func_177956_o() + 12);
        for (int y = minY; y <= maxY; ++y) {
            int dist;
            BlockPos candidate = new BlockPos(start.func_177958_n(), y, start.func_177952_p());
            BlockPos below = candidate.func_177977_b();
            IBlockState belowState = worldIn.func_180495_p(below);
            IBlockState atState = worldIn.func_180495_p(candidate);
            IBlockState aboveState = worldIn.func_180495_p(candidate.func_177984_a());
            Block belowBlock = belowState.func_177230_c();
            boolean validFloor = !belowState.func_185904_a().func_76224_d() && belowBlock != Blocks.field_150350_a && belowBlock != Blocks.field_150362_t && belowBlock != Blocks.field_150361_u && belowBlock != Blocks.field_150357_h && belowBlock != Blocks.field_180401_cv && belowBlock.canCreatureSpawn(belowState, (IBlockAccess)worldIn, below, EntityLiving.SpawnPlacementType.ON_GROUND);
            boolean openAt = SPWorldEntitySpawner.isValidEmptySpawnBlock(atState);
            boolean openAbove = SPWorldEntitySpawner.isValidEmptySpawnBlock(aboveState);
            if (!validFloor || !openAt || !openAbove || (dist = Math.abs(y - start.func_177956_o())) >= bestDist) continue;
            bestDist = dist;
            best = candidate;
        }
        return best != null ? best : start;
    }

    private static void applyDebugGlow(EntityLiving entityliving) {
        if (!SPConfigSystems.debugSpawner || entityliving == null) {
            return;
        }
        entityliving.func_70690_d(new PotionEffect(MobEffects.field_188423_x, 1200, 0, false, false));
    }

    private static BlockPos findWaterSpawnPos(World worldIn, BlockPos start) {
        int x = start.func_177958_n();
        int z = start.func_177952_p();
        BlockPos top = worldIn.func_175672_r(new BlockPos(x, 0, z));
        int topY = Math.max(1, top.func_177956_o() + 2);
        BlockPos best = null;
        int bestDist = Integer.MAX_VALUE;
        for (int y = 1; y <= topY; ++y) {
            int dist;
            boolean valid;
            BlockPos pos = new BlockPos(x, y, z);
            IBlockState at = worldIn.func_180495_p(pos);
            IBlockState below = worldIn.func_180495_p(pos.func_177977_b());
            IBlockState above = worldIn.func_180495_p(pos.func_177984_a());
            boolean bl = valid = at.func_185904_a() == Material.field_151586_h && below.func_185904_a() == Material.field_151586_h && above.func_185904_a() == Material.field_151586_h;
            if (!valid || (dist = Math.abs(y - start.func_177956_o())) >= bestDist) continue;
            bestDist = dist;
            best = pos;
        }
        return best != null ? best : start;
    }

    private static void adjustWaterSpawnPos(World worldIn, BlockPos.MutableBlockPos pos) {
        BlockPos adjusted = SPWorldEntitySpawner.findWaterSpawnPos(worldIn, pos.func_185334_h());
        pos.func_189533_g((Vec3i)adjusted);
    }

    private static BlockPos findAirSpawnPos(World worldIn, BlockPos start) {
        int x = start.func_177958_n();
        int z = start.func_177952_p();
        int topY = worldIn.func_175672_r(new BlockPos(x, 0, z)).func_177956_o();
        int minY = Math.max(2, topY + 8);
        int maxY = Math.min(SPConfigWorld.spawnerSKYLimitUp, worldIn.func_72940_L() - 2);
        if (maxY < minY) {
            return start;
        }
        ArrayList<BlockPos> validPositions = new ArrayList<BlockPos>();
        for (int y = minY; y <= maxY; ++y) {
            boolean valid;
            BlockPos pos = new BlockPos(x, y, z);
            IBlockState at = worldIn.func_180495_p(pos);
            boolean bl = valid = at.func_177230_c() == Blocks.field_150350_a && worldIn.func_180495_p(pos.func_177984_a()).func_177230_c() == Blocks.field_150350_a && worldIn.func_180495_p(pos.func_177977_b()).func_177230_c() == Blocks.field_150350_a;
            if (!valid) continue;
            validPositions.add(pos);
        }
        if (validPositions.isEmpty()) {
            return start;
        }
        return (BlockPos)validPositions.get(worldIn.field_73012_v.nextInt(validPositions.size()));
    }
}

