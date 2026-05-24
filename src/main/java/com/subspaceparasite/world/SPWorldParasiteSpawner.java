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
 *  net.minecraft.server.management.PlayerChunkMapEntry
 *  net.minecraft.util.EntitySelectors
 *  net.minecraft.util.WeightedRandom
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.BlockPos$MutableBlockPos
 *  net.minecraft.util.math.ChunkPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraft.world.biome.Biome$SpawnListEntry
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.fml.common.eventhandler.Event$Result
 */
package com.subspaceparasite.world;

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
import net.minecraft.server.management.PlayerChunkMapEntry;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.common.eventhandler.Event;

public class SPWorldParasiteSpawner {
    private static final Set<ChunkPos> eligibleChunksForSpawning = Sets.newHashSet();
    private static int lock = 0;
    private static int originC = 0;
    public static boolean triggerSPAWNING = false;
    public static int choiceNUMBER = 1;

    public static int findChunksForSpawning(WorldServer worldServerIn, boolean spawnHostileMobs, boolean spawnPeacefulMobs, boolean spawnOnSetTickRate) {
        if (!SPConfigWorld.originActivated) {
            return SPWorldParasiteSpawner.findChunksForSpawningVanilla(worldServerIn, spawnHostileMobs, spawnPeacefulMobs, spawnOnSetTickRate);
        }
        return SPWorldParasiteSpawner.findChunksForSpawningOrigin(worldServerIn, spawnHostileMobs, spawnPeacefulMobs, spawnOnSetTickRate);
    }

    public static int findChunksForSpawningVanilla(WorldServer worldServerIn, boolean spawnHostileMobs, boolean spawnPeacefulMobs, boolean spawnOnSetTickRate) {
        if (!spawnHostileMobs && !spawnPeacefulMobs) {
            return 0;
        }
        if (!SPSpawning.totalParasites) {
            if (++lock > 40) {
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
            BlockPos blockpos = SPWorldParasiteSpawner.getRandomChunkPosition((World)worldServerIn, chunkpos1.field_77276_a, chunkpos1.field_77275_b);
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
                    EntityParasiteBase entityliving;
                    EntityPlayer closest;
                    blockpos$mutableblockpos.func_181079_c(l2 += worldServerIn.field_73012_v.nextInt(6) - worldServerIn.field_73012_v.nextInt(6), i3 += worldServerIn.field_73012_v.nextInt(1) - worldServerIn.field_73012_v.nextInt(1), j3 += worldServerIn.field_73012_v.nextInt(6) - worldServerIn.field_73012_v.nextInt(6));
                    float f = (float)l2 + 0.5f;
                    float f1 = (float)j3 + 0.5f;
                    if (worldServerIn.func_175636_b((double)f, (double)i3, (double)f1, 24.0) || !(blockpos1.func_177954_c((double)f, (double)i3, (double)f1) >= 576.0)) continue;
                    if (biome$spawnlistentry == null && (biome$spawnlistentry = SPWorldParasiteSpawner.getSpawnListEntryForTypeAt(worldServerIn, (BlockPos)blockpos$mutableblockpos)) == null) continue block6;
                    EntityLiving.SpawnPlacementType ground = EntitySpawnPlacementRegistry.func_180109_a((Class)biome$spawnlistentry.field_76300_b);
                    if (ground == EntityLiving.SpawnPlacementType.IN_AIR && (closest = SPWorldParasiteSpawner.getClosestPlayer(f, i3, f1, 24.0, (World)worldServerIn)) != null) {
                        double base = closest.field_70163_u;
                        double randomOff = worldServerIn.field_73012_v.nextInt(21) - 10;
                        base = Math.max(base, (double)worldServerIn.func_72912_H().func_76067_t().getMinimumSpawnHeight((World)worldServerIn) / 2.0);
                        base = Math.min(base, (double)SPConfigWorld.spawnerSKYLimitUp);
                        blockpos$mutableblockpos.func_189532_c((double)l2, base + randomOff, (double)j3);
                    }
                    if (!SPWorldParasiteSpawner.canCreatureTypeSpawnAtLocation(ground, (World)worldServerIn, (BlockPos)blockpos$mutableblockpos)) continue;
                    try {
                        entityliving = (EntityParasiteBase)biome$spawnlistentry.newInstance((World)worldServerIn);
                        entityliving.canSpawnSpawn = true;
                    }
                    catch (Exception exception) {
                        return j4;
                    }
                    entityliving.func_70012_b(f, i3, f1, worldServerIn.field_73012_v.nextFloat() * 360.0f, 0.0f);
                    Event.Result canSpawn = ForgeEventFactory.canEntitySpawn((EntityLiving)entityliving, (World)worldServerIn, (float)f, (float)i3, (float)f1, (boolean)false);
                    if (canSpawn == Event.Result.ALLOW || canSpawn == Event.Result.DEFAULT && entityliving.func_70601_bi()) {
                        if (!ForgeEventFactory.doSpecialSpawn((EntityLiving)entityliving, (World)worldServerIn, (float)f, (float)i3, (float)f1)) {
                            ientitylivingdata = entityliving.func_180482_a(worldServerIn.func_175649_E(new BlockPos((Entity)entityliving)), ientitylivingdata);
                        }
                        if (entityliving.func_70058_J()) {
                            ++j2;
                            worldServerIn.func_72838_d((Entity)entityliving);
                        } else {
                            entityliving.func_70106_y();
                        }
                        if (j2 >= ForgeEventFactory.getMaxSpawnPackSize((EntityLiving)entityliving)) continue block5;
                    }
                    j4 += j2;
                }
            }
        }
        return j4;
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
        SPWorldParasiteSpawner.filterEligibleChunksForOrigin(worldData, originsExist, originlessAllowed);
        if (eligibleChunksForSpawning.isEmpty()) {
            return 0;
        }
        int j4 = 0;
        BlockPos blockpos1 = worldServerIn.func_175694_M();
        ArrayList shuffled = Lists.newArrayList(eligibleChunksForSpawning);
        Collections.shuffle(shuffled);
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        block5: for (ChunkPos chunkpos1 : shuffled) {
            BlockPos blockpos = SPWorldParasiteSpawner.getRandomChunkPosition((World)worldServerIn, chunkpos1.field_77276_a, chunkpos1.field_77275_b);
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
                    EntityParasiteBase entityliving;
                    blockpos$mutableblockpos.func_181079_c(l2 += worldServerIn.field_73012_v.nextInt(6) - worldServerIn.field_73012_v.nextInt(6), i3 += worldServerIn.field_73012_v.nextInt(1) - worldServerIn.field_73012_v.nextInt(1), j3 += worldServerIn.field_73012_v.nextInt(6) - worldServerIn.field_73012_v.nextInt(6));
                    float f = (float)l2 + 0.5f;
                    float f1 = (float)j3 + 0.5f;
                    if (worldServerIn.func_175636_b((double)f, (double)i3, (double)f1, 24.0) || !(blockpos1.func_177954_c((double)f, (double)i3, (double)f1) >= 576.0)) continue;
                    if (biome$spawnlistentry == null && (biome$spawnlistentry = SPWorldParasiteSpawner.getSpawnListEntryForTypeAtOrigin(worldServerIn, (BlockPos)blockpos$mutableblockpos, saveData72, worldData, originsExist, originlessAllowed)) == null) continue block6;
                    EntityLiving.SpawnPlacementType ground = EntitySpawnPlacementRegistry.func_180109_a((Class)biome$spawnlistentry.field_76300_b);
                    if (ground == EntityLiving.SpawnPlacementType.IN_AIR) {
                        if (worldServerIn.field_73012_v.nextDouble() <= 0.7) continue;
                        EntityPlayer closest = SPWorldParasiteSpawner.getClosestPlayer(f, i3, f1, 24.0, (World)worldServerIn);
                        if (closest != null) {
                            double base = closest.field_70163_u;
                            double randomOff = worldServerIn.field_73012_v.nextInt(21) - 10;
                            base = Math.max(base, (double)worldServerIn.func_72912_H().func_76067_t().getMinimumSpawnHeight((World)worldServerIn) / 2.0);
                            base = Math.min(base, (double)SPConfigWorld.spawnerSKYLimitUp);
                            blockpos$mutableblockpos.func_189532_c((double)l2, base + randomOff, (double)j3);
                        }
                    }
                    if (!SPWorldParasiteSpawner.canCreatureTypeSpawnAtLocation(ground, (World)worldServerIn, (BlockPos)blockpos$mutableblockpos)) continue;
                    try {
                        entityliving = (EntityParasiteBase)biome$spawnlistentry.newInstance((World)worldServerIn);
                        entityliving.canSpawnSpawn = true;
                    }
                    catch (Exception exception) {
                        return j4;
                    }
                    entityliving.func_70012_b(f, i3, f1, worldServerIn.field_73012_v.nextFloat() * 360.0f, 0.0f);
                    entityliving.func_70012_b(f, blockpos$mutableblockpos.func_177956_o(), f1, worldServerIn.field_73012_v.nextFloat() * 360.0f, 0.0f);
                    Event.Result canSpawn = ForgeEventFactory.canEntitySpawn((EntityLiving)entityliving, (World)worldServerIn, (float)f, (float)i3, (float)f1, (boolean)false);
                    if (canSpawn == Event.Result.ALLOW || canSpawn == Event.Result.DEFAULT && entityliving.func_70601_bi()) {
                        if (!ForgeEventFactory.doSpecialSpawn((EntityLiving)entityliving, (World)worldServerIn, (float)f, (float)i3, (float)f1)) {
                            ientitylivingdata = entityliving.func_180482_a(worldServerIn.func_175649_E(new BlockPos((Entity)entityliving)), ientitylivingdata);
                        }
                        if (entityliving.func_70058_J()) {
                            ++j2;
                            worldServerIn.func_72838_d((Entity)entityliving);
                        } else {
                            entityliving.func_70106_y();
                        }
                        if (j2 >= ForgeEventFactory.getMaxSpawnPackSize((EntityLiving)entityliving)) continue block5;
                    }
                    j4 += j2;
                }
            }
        }
        return j4;
    }

    @Nullable
    private static EntityPlayer getClosestPlayer(double x, double y, double z, double distance, World world) {
        double d0 = -1.0;
        EntityPlayer entityplayer = null;
        List list = world.field_73010_i;
        for (EntityPlayer entityPlayer : list) {
            double d1;
            if (!EntitySelectors.field_180132_d.apply((Object)entityPlayer) || !((d1 = entityPlayer.func_70092_e(x, y, z)) > distance * distance) || d0 != -1.0 && !(d1 < d0)) continue;
            d0 = d1;
            entityplayer = entityPlayer;
        }
        return entityplayer;
    }

    private static BlockPos getRandomChunkPosition(World worldIn, int x, int z) {
        Chunk chunk = worldIn.func_72964_e(x, z);
        int i = x * 16 + worldIn.field_73012_v.nextInt(16);
        int j = z * 16 + worldIn.field_73012_v.nextInt(16);
        int k = MathHelper.func_154354_b((int)(chunk.func_177433_f(new BlockPos(i, 0, j)) + 1), (int)16);
        int l = worldIn.field_73012_v.nextInt(Math.max(1, k > 0 ? k : chunk.func_76625_h() + 16 - 1));
        return new BlockPos(i, l, j);
    }

    public static boolean canCreatureTypeSpawnAtLocation(EntityLiving.SpawnPlacementType spawnPlacementTypeIn, World worldIn, BlockPos pos) {
        if (!worldIn.func_175723_af().func_177746_a(pos)) {
            return false;
        }
        return SPWorldParasiteSpawner.canCreatureTypeSpawnBody(spawnPlacementTypeIn, worldIn, pos);
    }

    public static boolean canCreatureTypeSpawnBody(EntityLiving.SpawnPlacementType spawnPlacementTypeIn, World worldIn, BlockPos pos) {
        IBlockState iblockstate = worldIn.func_180495_p(pos);
        if (spawnPlacementTypeIn == EntityLiving.SpawnPlacementType.IN_WATER) {
            return iblockstate.func_185904_a() == Material.field_151586_h && worldIn.func_180495_p(pos.func_177977_b()).func_185904_a() == Material.field_151586_h && !worldIn.func_180495_p(pos.func_177984_a()).func_185915_l();
        }
        if (spawnPlacementTypeIn == EntityLiving.SpawnPlacementType.IN_AIR) {
            return iblockstate.func_177230_c() == Blocks.field_150350_a && worldIn.func_180495_p(pos.func_177977_b()).func_177230_c() == Blocks.field_150350_a && worldIn.func_180495_p(pos.func_177984_a()).func_177230_c() == Blocks.field_150350_a;
        }
        BlockPos blockpos = pos.func_177977_b();
        IBlockState state = worldIn.func_180495_p(blockpos);
        if (!state.func_177230_c().canCreatureSpawn(state, (IBlockAccess)worldIn, blockpos, spawnPlacementTypeIn)) {
            return false;
        }
        Block block = worldIn.func_180495_p(blockpos).func_177230_c();
        boolean flag = block != Blocks.field_150357_h && block != Blocks.field_180401_cv;
        return flag && SPWorldParasiteSpawner.isValidEmptySpawnBlock(iblockstate) && SPWorldParasiteSpawner.isValidEmptySpawnBlock(worldIn.func_180495_p(pos.func_177984_a()));
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
                if (!SPWorldParasiteSpawner.chunkIntersectsRadius2D(chunkPos.field_77276_a, chunkPos.field_77275_b, originsX.get(i), originsZ.get(i), originsA.get(i))) continue;
                inRange = true;
                break;
            }
            if (!inRange) {
                for (i = 0; i < colonyCount; ++i) {
                    if (!SPWorldParasiteSpawner.chunkIntersectsRadius2D(chunkPos.field_77276_a, chunkPos.field_77275_b, coloniesX.get(i), coloniesZ.get(i), colonyRadii[i])) continue;
                    inRange = true;
                    break;
                }
            }
            if (inRange) continue;
            chunkIterator.remove();
        }
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

    @Nullable
    public static Biome.SpawnListEntry getSpawnListEntryForTypeAt(WorldServer worldServerIn, BlockPos pos) {
        SPSaveData dat = SPSaveData.get((World)worldServerIn, 72);
        int id = worldServerIn.field_73011_w.getDimension();
        if (dat == null) {
            return null;
        }
        if (SPConfigWorld.originActivated) {
            SPWorldData data = SPWorldData.get((World)worldServerIn);
            boolean originsExist = data != null && !data.getorigins("x").isEmpty();
            SPSaveData saveData225 = SPSaveData.get((World)worldServerIn, 225);
            boolean originlessAllowed = saveData225 != null && saveData225.getDeveLevel() >= SPConfigSystems.deveOriginlessUse;
            return SPWorldParasiteSpawner.getSpawnListEntryForTypeAtOrigin(worldServerIn, pos, dat, data, originsExist, originlessAllowed);
        }
        List<Biome.SpawnListEntry> list = SPSpawning.getSpawns((World)worldServerIn, id, dat.getEvolutionPhase(id), dat);
        Biome.SpawnListEntry chosen = list != null && !list.isEmpty() ? (Biome.SpawnListEntry)WeightedRandom.func_76271_a((Random)worldServerIn.field_73012_v, list) : null;
        return chosen;
    }

    @Nullable
    private static Biome.SpawnListEntry getSpawnListEntryForTypeAtOrigin(WorldServer worldServerIn, BlockPos pos, SPSaveData dat, SPWorldData data, boolean originsExist, boolean originlessAllowed) {
        int id = worldServerIn.field_73011_w.getDimension();
        if (!SPWorldParasiteSpawner.isPosWithinOrigin((World)worldServerIn, pos, data, originsExist, originlessAllowed)) {
            return null;
        }
        byte phase = dat.getEvolutionPhase(id);
        if (phase == -1) {
            if (!originsExist) {
                return null;
            }
            List<Biome.SpawnListEntry> list = SPSpawning.getSpawns((World)worldServerIn, id, phase, dat);
            Biome.SpawnListEntry chosen = list != null && !list.isEmpty() ? (Biome.SpawnListEntry)WeightedRandom.func_76271_a((Random)worldServerIn.field_73012_v, list) : null;
            return chosen;
        }
        byte phaseToUse = originsExist ? phase : (byte)0;
        List<Biome.SpawnListEntry> list = SPSpawning.getSpawns((World)worldServerIn, id, phaseToUse, dat);
        Biome.SpawnListEntry chosen = list != null && !list.isEmpty() ? (Biome.SpawnListEntry)WeightedRandom.func_76271_a((Random)worldServerIn.field_73012_v, list) : null;
        return chosen;
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

    private static String posToString(BlockPos pos) {
        return "(" + pos.func_177958_n() + ", " + pos.func_177956_o() + ", " + pos.func_177952_p() + ")";
    }
}

