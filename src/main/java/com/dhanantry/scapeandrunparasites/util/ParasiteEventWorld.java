/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBreakable
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.BlockHorizontal
 *  net.minecraft.block.BlockOre
 *  net.minecraft.block.BlockTNT
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IPlantable
 */
package com.dhanantry.scapeandrunparasites.util;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteSpreading;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.network.SRPCommandDislodgment;
import com.dhanantry.scapeandrunparasites.network.SRPCommandEvolution;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import com.dhanantry.scapeandrunparasites.world.SRPWorldData;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteColonyCore;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteNodeCore;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockOre;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

public class ParasiteEventWorld {
    public static int disloCool;

    public static boolean blockException(World worldIn, BlockPos pos, Block block, IBlockState state, String[] list, boolean invert, float maxHardness) {
        float bHard = state.func_185887_b(worldIn, pos);
        if (block instanceof BlockBreakable && state.func_185904_a() == Material.field_151588_w) {
            return false;
        }
        if (bHard > maxHardness || bHard < 0.0f) {
            return true;
        }
        if (ParasiteEventEntity.checkName(block.getRegistryName().toString(), list, invert)) {
            return true;
        }
        return block instanceof BlockBreakable || block instanceof BlockContainer || block instanceof BlockOre || block instanceof BlockHorizontal || block instanceof BlockTNT || block.func_176205_b((IBlockAccess)worldIn, pos) || block instanceof IPlantable || !state.func_185917_h();
    }

    public static int canBiomeStillExist(World worldIn, BlockPos pos, boolean spread) {
        if (!SRPConfigWorld.nodesActivated || !SRPConfigWorld.biomeRegster) {
            return -1;
        }
        return SRPWorldData.get(worldIn).nearestHeartAge(pos, spread, 0);
    }

    public static int canBiomeStillExistType(World worldIn, BlockPos pos, boolean spread) {
        if (!SRPConfigWorld.nodesActivated || !SRPConfigWorld.biomeRegster) {
            return -1;
        }
        return SRPWorldData.get(worldIn).nearestHeartType(pos, spread, 0);
    }

    public static int placeHeartInWorld(World worldIn, BlockPos pos, int type) {
        type = 0;
        if (!SRPConfigWorld.nodesActivated) {
            return 3;
        }
        if (!SRPConfigWorld.biomeRegster) {
            return 4;
        }
        if (!ParasiteEventWorld.chechBlackListNodes(worldIn)) {
            return 2;
        }
        BlockPos origin = worldIn.func_175694_M();
        if (ParasiteEventWorld.getDistanceSQ(origin.func_177958_n(), origin.func_177956_o(), origin.func_177952_p(), pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p()) < (double)(SRPConfigWorld.minimumDistanceFromSpawnPoint * SRPConfigWorld.minimumDistanceFromSpawnPoint)) {
            return 5;
        }
        SRPWorldData data = SRPWorldData.get(worldIn);
        SRPSaveData dataS = SRPSaveData.get(worldIn, 73);
        if (SRPConfigSystems.useEvolution) {
            if (dataS.getEvolutionPhase(worldIn.field_73011_w.getDimension()) < SRPConfigSystems.evolutionNodeUnlock && dataS.getDeveLevel() < SRPConfigSystems.deveNodesUse) {
                return 6;
            }
        } else if (!SRPConfigWorld.venkrolNode) {
            return 10;
        }
        if ((pos = ParasiteEventEntity.getFloor(worldIn, pos, 100)) == null) {
            return 7;
        }
        int key = data.setNode(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), type);
        if (key == 1) {
            WorldGenParasiteNodeCore gen = new WorldGenParasiteNodeCore(false, 1, type);
            gen.func_180709_b(worldIn, new Random(), pos);
            BlockParasiteSpreading.SpreadBiome(worldIn, pos, 1, type);
            ParasiteEventEntity.alertAllPlayerDim(worldIn, SRPConfigWorld.nodeWarning, 100);
            return 1;
        }
        return key;
    }

    public static double getDistanceSQ(double rootx, double rooty, double rootz, double standingx, double standingy, double standingz) {
        double d0 = rootx - standingx;
        double d1 = rooty - standingy;
        double d2 = rootz - standingz;
        return d0 * d0 + d1 * d1 + d2 * d2;
    }

    private static boolean chechBlackListNodes(World worldIn) {
        for (int i : SRPConfigWorld.blackListedDimensionsNodes) {
            if (i != worldIn.field_73011_w.getDimension()) continue;
            return true;
        }
        return false;
    }

    public static boolean removeHeartInWorld(World worldIn, BlockPos pos) {
        if (!SRPConfigWorld.nodesActivated || !SRPConfigWorld.biomeRegster) {
            return false;
        }
        SRPWorldData data = SRPWorldData.get(worldIn);
        if (data.removeNode(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p())) {
            ParasiteEventWorld.setDisloWorldPhase(worldIn, SRPAttributes.EVENTPARANODEC, SRPConfigSystems.chanceEventParaNodeC, 0, null);
            return true;
        }
        return false;
    }

    public static int getHeartAgePostion(World worldIn, BlockPos pos) {
        SRPWorldData data = SRPWorldData.get(worldIn);
        return data.getHeartPocition(pos, 0);
    }

    public static int nodesPoints(World worldIn) {
        SRPWorldData data = SRPWorldData.get(worldIn);
        return data.totalNodePoints(0);
    }

    private static double getDistanceSq(BlockPos pos, Entity entityIn) {
        double d0 = (double)pos.func_177958_n() - entityIn.field_70165_t;
        double d1 = (double)pos.func_177956_o() - entityIn.field_70163_u;
        double d2 = (double)pos.func_177952_p() - entityIn.field_70161_v;
        return d0 * d0 + d1 * d1 + d2 * d2;
    }

    public static void checkNodeStatus(World worldIn) {
        SRPWorldData data = SRPWorldData.get(worldIn);
        data.checkHeartExistance(worldIn);
    }

    public static int placeColonyInWorld(World worldIn, BlockPos pos) {
        if (!SRPConfigWorld.coloniesActivated) {
            return 3;
        }
        if (!ParasiteEventWorld.chechBlackListColonies(worldIn)) {
            return 2;
        }
        SRPWorldData data = SRPWorldData.get(worldIn);
        SRPSaveData dataS = SRPSaveData.get(worldIn, 78);
        if (SRPConfigSystems.useEvolution) {
            if (dataS.getEvolutionPhase(worldIn.field_73011_w.getDimension()) < SRPConfigSystems.evolutionColonyUnlock && dataS.getDeveLevel() < SRPConfigSystems.deveColoniesUse) {
                return 4;
            }
        } else if (!SRPConfigWorld.dodColony) {
            return 6;
        }
        int newX = ParasiteEventWorld.findNumberMultipleOf(pos.func_177958_n(), 26);
        int newZ = ParasiteEventWorld.findNumberMultipleOf(pos.func_177952_p(), 26);
        BlockPos newPos = new BlockPos(newX, pos.func_177956_o(), newZ);
        if ((newPos = ParasiteEventEntity.getFloor(worldIn, newPos, 100)) == null) {
            return 5;
        }
        int key = data.setColony(newPos.func_177958_n(), newPos.func_177956_o(), newPos.func_177952_p());
        if (key == 1) {
            WorldGenParasiteColonyCore gen = new WorldGenParasiteColonyCore(false, 1);
            gen.func_180709_b(worldIn, new Random(), newPos);
            ParasiteEventEntity.alertAllPlayerDim(worldIn, SRPConfigWorld.colonyWarning, 101);
            return 1;
        }
        return key;
    }

    private static boolean chechBlackListColonies(World worldIn) {
        for (int i : SRPConfigWorld.blackListedDimensionsColonies) {
            if (i != worldIn.field_73011_w.getDimension()) continue;
            return true;
        }
        return false;
    }

    private static int findNumberMultipleOf(int n, int x) {
        if (x > n) {
            // empty if block
        }
        boolean neg = false;
        if (n < 0) {
            n *= -1;
            neg = true;
        }
        n += x / 2;
        n -= n % x;
        if (neg) {
            n *= -1;
        }
        return n;
    }

    public static boolean removeColonyInWorld(World worldIn, BlockPos pos) {
        if (!SRPConfigWorld.coloniesActivated) {
            return false;
        }
        SRPWorldData data = SRPWorldData.get(worldIn);
        if (data.removeColony(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p())) {
            data.resetGlobalAdaptation();
            ParasiteEventWorld.setDisloWorldPhase(worldIn, SRPAttributes.EVENTPARACOLONYC, SRPConfigSystems.chanceEventParaColonyC, 0, null);
            return true;
        }
        return false;
    }

    public static BlockPos rangeOfColony(World worldIn, BlockPos pos, boolean effect) {
        return SRPWorldData.get(worldIn).nearestColonyPosition(pos, effect);
    }

    public static int numberofColonies(World worldIn) {
        return SRPWorldData.get(worldIn).colonunumber();
    }

    public static int spreadOfColony(World worldIn, BlockPos pos) {
        return SRPWorldData.get(worldIn).getColonyDistanceSpreadByPosition(pos, false);
    }

    public static void checkColonyStatus(World worldIn) {
        SRPWorldData data = SRPWorldData.get(worldIn);
        data.checkColonyExistance(worldIn);
    }

    public static int placeOriginInWorld(World worldIn, BlockPos pos, int health, int radius) {
        if (!SRPConfigWorld.originActivated) {
            return 3;
        }
        SRPWorldData data = SRPWorldData.get(worldIn);
        int key = data.setOrigin(worldIn, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), health *= SRPCommandEvolution.getVectorHealthBonus(SRPSaveData.get(worldIn, -421).getEvolutionPhase(worldIn.field_73011_w.getDimension())), radius);
        EntityPlayer nearestPlayer = worldIn.func_184137_a((double)pos.func_177958_n(), (double)pos.func_177956_o(), (double)pos.func_177952_p(), -1.0, false);
        if (nearestPlayer != null) {
            double horizontalDistance = Math.sqrt(Math.pow((double)pos.func_177958_n() - nearestPlayer.field_70165_t, 2.0) + Math.pow((double)pos.func_177952_p() - nearestPlayer.field_70161_v, 2.0));
            SRPMain.logger.info("[EIV DEBUG] placeOriginInWorld called. pos={} health={} radius={} resultKey={} nearestPlayer={} playerPos={} horizontalDistance={} trueDistance={} totalOrigins={}", (Object)pos, (Object)health, (Object)radius, (Object)key, (Object)nearestPlayer.func_70005_c_(), (Object)nearestPlayer.func_180425_c(), (Object)String.format("%.2f", horizontalDistance), (Object)String.format("%.2f", nearestPlayer.func_70011_f((double)pos.func_177958_n(), (double)pos.func_177956_o(), (double)pos.func_177952_p())), (Object)data.getorigins("x").size());
        } else {
            SRPMain.logger.info("[EIV DEBUG] placeOriginInWorld called. pos={} health={} radius={} resultKey={} no nearest player found. totalOrigins={}", (Object)pos, (Object)health, (Object)radius, (Object)key, (Object)data.getorigins("x").size());
        }
        if (key == 1) {
            if (SRPConfigWorld.originNewMess.length() > 0) {
                ParasiteEventEntity.alertAllPlayerDim(worldIn, SRPConfigWorld.originNewMess, 400);
            } else {
                ParasiteEventEntity.alertAllPlayerDim(worldIn, "", 400);
            }
        } else if (key == 2) {
            if (SRPConfigWorld.originNewOutbreakMess.length() > 0) {
                ParasiteEventEntity.alertAllPlayerSer(worldIn, SRPConfigWorld.originNewOutbreakMess, 401);
            } else {
                ParasiteEventEntity.alertAllPlayerSer(worldIn, "", 401);
            }
        }
        return key;
    }

    public static boolean removeOriginInWorld(World worldIn, BlockPos pos) {
        if (!SRPConfigWorld.originActivated) {
            return false;
        }
        SRPWorldData data = SRPWorldData.get(worldIn);
        pos = data.nearestInfectionPosition(false, pos);
        if (pos == null) {
            return false;
        }
        if (data.removeOrigin(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), worldIn)) {
            if (worldIn.field_73011_w.getDimension() == -1) {
                if (SRPConfigWorld.originGoneOB.length() > 0) {
                    ParasiteEventEntity.alertAllPlayerDim(worldIn, SRPConfigWorld.originGoneOB, 402);
                }
            } else if (SRPConfigWorld.originGone.length() > 0) {
                ParasiteEventEntity.alertAllPlayerDim(worldIn, SRPConfigWorld.originGone, 402);
            }
            return true;
        }
        return false;
    }

    public static boolean setOriginInHealth(World worldIn, BlockPos pos, int amount, boolean plus) {
        if (pos == null || amount == 0) {
            return false;
        }
        SRPWorldData data = SRPWorldData.get(worldIn);
        return data.setOriginHealth(worldIn, pos, amount, plus);
    }

    public static void setDisloWorldPhase(World world, ArrayList<Byte> disloEvent, double chance, int cothCheck, BlockPos pos) {
        int dim;
        byte phase;
        byte[] disloEve;
        if (world.field_72995_K) {
            return;
        }
        if (disloCool > 0) {
            return;
        }
        SRPSaveData data = SRPSaveData.get(world, 90);
        if (data.getEvolutionPhase(world.field_73011_w.getDimension()) < SRPConfigSystems.evolutionDislodgment && data.getDeveLevel() < SRPConfigSystems.deveDisloUse) {
            return;
        }
        if (world.field_73012_v.nextDouble() > chance) {
            return;
        }
        if (disloEvent.size() == 0) {
            return;
        }
        if (cothCheck > 1 && pos != null) {
            int coth = 0;
            List moblist = world.func_72872_a(EntityLivingBase.class, new AxisAlignedBB(pos).func_72314_b(5.0, 3.0, 5.0));
            for (EntityLivingBase mob : moblist) {
                if (!mob.func_70644_a(SRPPotions.COTH_E)) continue;
                ++coth;
            }
            if (coth < cothCheck) {
                return;
            }
        }
        if ((disloEve = SRPCommandDislodgment.getDisloPhase(phase = data.getEvolutionPhase(dim = world.field_73011_w.getDimension()))) == null) {
            return;
        }
        ArrayList<Byte> halo = new ArrayList<Byte>();
        for (int i = 0; i < disloEve.length; ++i) {
            for (int k = 0; k < disloEvent.size(); ++k) {
                if (disloEve[i] != disloEvent.get(k)) continue;
                halo.add(disloEvent.get(k));
            }
        }
        if (halo.size() == 0) {
            return;
        }
        boolean looop = false;
        for (int gggg = 10; gggg > 0 && !looop; --gggg) {
            byte dislo = (Byte)halo.get(world.field_73012_v.nextInt(halo.size()));
            int cost = (int)((double)SRPCommandDislodgment.getDisloPointPrice(dislo) * SRPCommandDislodgment.getDisloPhaseCost(phase));
            int duration = (int)((double)SRPCommandDislodgment.getDisloDuration(dislo) * SRPCommandDislodgment.getDisloPhaseDuration(phase));
            int value = (int)((double)SRPCommandDislodgment.getDisloValue(dislo) * SRPCommandDislodgment.getDisloPhaseValue(phase));
            looop = data.setCurrentCode(world.field_73011_w.getDimension(), dislo, value, duration, world, true, cost);
        }
        disloCool = SRPConfigSystems.disloGlobalCooldown;
    }
}

