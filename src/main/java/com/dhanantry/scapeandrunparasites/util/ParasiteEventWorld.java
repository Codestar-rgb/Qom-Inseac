package com.dhanantry.scapeandrunparasites.util;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteSpreading;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.network.SRPCommandDislodgment;
import com.dhanantry.scapeandrunparasites.network.SRPCommandEvolution;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import com.dhanantry.scapeandrunparasites.world.SRPWorldData;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteColonyCore;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteNodeCore;
import java.util.ArrayList;
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
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

public class ParasiteEventWorld {
   public static int disloCool;

   public static boolean blockException(World worldIn, BlockPos pos, Block block, IBlockState state, String[] list, boolean invert, float maxHardness) {
      float bHard = state.func_185887_b(worldIn, pos);
      if (block instanceof BlockBreakable && state.func_185904_a() == Material.field_151588_w) {
         return false;
      } else if (!(bHard > maxHardness) && !(bHard < 0.0F)) {
         return ParasiteEventEntity.checkName(block.getRegistryName().toString(), list, invert)
            ? true
            : block instanceof BlockBreakable
               || block instanceof BlockContainer
               || block instanceof BlockOre
               || block instanceof BlockHorizontal
               || block instanceof BlockTNT
               || block.func_176205_b(worldIn, pos)
               || block instanceof IPlantable
               || !state.func_185917_h();
      } else {
         return true;
      }
   }

   public static int canBiomeStillExist(World worldIn, BlockPos pos, boolean spread) {
      return SRPConfigWorld.nodesActivated && SRPConfigWorld.biomeRegster ? SRPWorldData.get(worldIn).nearestHeartAge(pos, spread, 0) : -1;
   }

   public static int canBiomeStillExistType(World worldIn, BlockPos pos, boolean spread) {
      return SRPConfigWorld.nodesActivated && SRPConfigWorld.biomeRegster ? SRPWorldData.get(worldIn).nearestHeartType(pos, spread, 0) : -1;
   }

   public static int placeHeartInWorld(World worldIn, BlockPos pos, int type) {
      int var9 = 0;
      if (!SRPConfigWorld.nodesActivated) {
         return 3;
      } else if (!SRPConfigWorld.biomeRegster) {
         return 4;
      } else if (!chechBlackListNodes(worldIn)) {
         return 2;
      } else {
         BlockPos origin = worldIn.func_175694_M();
         if (getDistanceSQ(
               origin.func_177958_n(), origin.func_177956_o(), origin.func_177952_p(), pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p()
            )
            < SRPConfigWorld.minimumDistanceFromSpawnPoint * SRPConfigWorld.minimumDistanceFromSpawnPoint) {
            return 5;
         } else {
            SRPWorldData data = SRPWorldData.get(worldIn);
            SRPSaveData dataS = SRPSaveData.get(worldIn, 73);
            if (SRPConfigSystems.useEvolution) {
               if (dataS.getEvolutionPhase(worldIn.field_73011_w.getDimension()) < SRPConfigSystems.evolutionNodeUnlock
                  && dataS.getDeveLevel() < SRPConfigSystems.deveNodesUse) {
                  return 6;
               }
            } else if (!SRPConfigWorld.venkrolNode) {
               return 10;
            }

            pos = ParasiteEventEntity.getFloor(worldIn, pos, 100);
            if (pos == null) {
               return 7;
            } else {
               int key = data.setNode(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), var9);
               if (key == 1) {
                  WorldGenParasiteNodeCore gen = new WorldGenParasiteNodeCore(false, 1, var9);
                  gen.func_180709_b(worldIn, new Random(), pos);
                  BlockParasiteSpreading.SpreadBiome(worldIn, pos, 1, var9);
                  ParasiteEventEntity.alertAllPlayerDim(worldIn, SRPConfigWorld.nodeWarning, 100);
                  return 1;
               } else {
                  return key;
               }
            }
         }
      }
   }

   public static double getDistanceSQ(double rootx, double rooty, double rootz, double standingx, double standingy, double standingz) {
      double d0 = rootx - standingx;
      double d1 = rooty - standingy;
      double d2 = rootz - standingz;
      return d0 * d0 + d1 * d1 + d2 * d2;
   }

   private static boolean chechBlackListNodes(World worldIn) {
      for (int i : SRPConfigWorld.blackListedDimensionsNodes) {
         if (i == worldIn.field_73011_w.getDimension()) {
            return true;
         }
      }

      return false;
   }

   public static boolean removeHeartInWorld(World worldIn, BlockPos pos) {
      if (SRPConfigWorld.nodesActivated && SRPConfigWorld.biomeRegster) {
         SRPWorldData data = SRPWorldData.get(worldIn);
         if (data.removeNode(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p())) {
            setDisloWorldPhase(worldIn, SRPAttributes.EVENTPARANODEC, SRPConfigSystems.chanceEventParaNodeC, 0, null);
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
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
      double d0 = pos.func_177958_n() - entityIn.field_70165_t;
      double d1 = pos.func_177956_o() - entityIn.field_70163_u;
      double d2 = pos.func_177952_p() - entityIn.field_70161_v;
      return d0 * d0 + d1 * d1 + d2 * d2;
   }

   public static void checkNodeStatus(World worldIn) {
      SRPWorldData data = SRPWorldData.get(worldIn);
      data.checkHeartExistance(worldIn);
   }

   public static int placeColonyInWorld(World worldIn, BlockPos pos) {
      if (!SRPConfigWorld.coloniesActivated) {
         return 3;
      } else if (!chechBlackListColonies(worldIn)) {
         return 2;
      } else {
         SRPWorldData data = SRPWorldData.get(worldIn);
         SRPSaveData dataS = SRPSaveData.get(worldIn, 78);
         if (SRPConfigSystems.useEvolution) {
            if (dataS.getEvolutionPhase(worldIn.field_73011_w.getDimension()) < SRPConfigSystems.evolutionColonyUnlock
               && dataS.getDeveLevel() < SRPConfigSystems.deveColoniesUse) {
               return 4;
            }
         } else if (!SRPConfigWorld.dodColony) {
            return 6;
         }

         int newX = findNumberMultipleOf(pos.func_177958_n(), 26);
         int newZ = findNumberMultipleOf(pos.func_177952_p(), 26);
         BlockPos newPos = new BlockPos(newX, pos.func_177956_o(), newZ);
         newPos = ParasiteEventEntity.getFloor(worldIn, newPos, 100);
         if (newPos == null) {
            return 5;
         } else {
            int key = data.setColony(newPos.func_177958_n(), newPos.func_177956_o(), newPos.func_177952_p());
            if (key == 1) {
               WorldGenParasiteColonyCore gen = new WorldGenParasiteColonyCore(false, 1);
               gen.func_180709_b(worldIn, new Random(), newPos);
               ParasiteEventEntity.alertAllPlayerDim(worldIn, SRPConfigWorld.colonyWarning, 101);
               return 1;
            } else {
               return key;
            }
         }
      }
   }

   private static boolean chechBlackListColonies(World worldIn) {
      for (int i : SRPConfigWorld.blackListedDimensionsColonies) {
         if (i == worldIn.field_73011_w.getDimension()) {
            return true;
         }
      }

      return false;
   }

   private static int findNumberMultipleOf(int n, int x) {
      if (x > n) {
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
      } else {
         SRPWorldData data = SRPWorldData.get(worldIn);
         if (data.removeColony(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p())) {
            data.resetGlobalAdaptation();
            setDisloWorldPhase(worldIn, SRPAttributes.EVENTPARACOLONYC, SRPConfigSystems.chanceEventParaColonyC, 0, null);
            return true;
         } else {
            return false;
         }
      }
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
      } else {
         SRPWorldData data = SRPWorldData.get(worldIn);
         health *= SRPCommandEvolution.getVectorHealthBonus(SRPSaveData.get(worldIn, -421).getEvolutionPhase(worldIn.field_73011_w.getDimension()));
         int key = data.setOrigin(worldIn, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), health, radius);
         EntityPlayer nearestPlayer = worldIn.func_184137_a(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), -1.0, false);
         if (nearestPlayer != null) {
            double horizontalDistance = Math.sqrt(
               Math.pow(pos.func_177958_n() - nearestPlayer.field_70165_t, 2.0) + Math.pow(pos.func_177952_p() - nearestPlayer.field_70161_v, 2.0)
            );
            SRPMain.logger
               .debug(
                  "[EIV DEBUG] placeOriginInWorld called. pos={} health={} radius={} resultKey={} nearestPlayer={} playerPos={} horizontalDistance={} trueDistance={} totalOrigins={}",
                  pos,
                  health,
                  radius,
                  key,
                  nearestPlayer.func_70005_c_(),
                  nearestPlayer.func_180425_c(),
                  String.format("%.2f", horizontalDistance),
                  String.format("%.2f", nearestPlayer.func_70011_f(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p())),
                  data.getorigins("x").size()
               );
         } else {
            SRPMain.logger
               .debug(
                  "[EIV DEBUG] placeOriginInWorld called. pos={} health={} radius={} resultKey={} no nearest player found. totalOrigins={}",
                  pos,
                  health,
                  radius,
                  key,
                  data.getorigins("x").size()
               );
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
   }

   public static boolean removeOriginInWorld(World worldIn, BlockPos pos) {
      if (!SRPConfigWorld.originActivated) {
         return false;
      } else {
         SRPWorldData data = SRPWorldData.get(worldIn);
         pos = data.nearestInfectionPosition(false, pos);
         if (pos == null) {
            return false;
         } else if (data.removeOrigin(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), worldIn)) {
            if (worldIn.field_73011_w.getDimension() == -1) {
               if (SRPConfigWorld.originGoneOB.length() > 0) {
                  ParasiteEventEntity.alertAllPlayerDim(worldIn, SRPConfigWorld.originGoneOB, 402);
               }
            } else if (SRPConfigWorld.originGone.length() > 0) {
               ParasiteEventEntity.alertAllPlayerDim(worldIn, SRPConfigWorld.originGone, 402);
            }

            return true;
         } else {
            return false;
         }
      }
   }

   public static boolean setOriginInHealth(World worldIn, BlockPos pos, int amount, boolean plus) {
      if (pos != null && amount != 0) {
         SRPWorldData data = SRPWorldData.get(worldIn);
         return data.setOriginHealth(worldIn, pos, amount, plus);
      } else {
         return false;
      }
   }

   public static void setDisloWorldPhase(World world, ArrayList<Byte> disloEvent, double chance, int cothCheck, BlockPos pos) {
      if (!world.field_72995_K) {
         if (disloCool <= 0) {
            SRPSaveData data = SRPSaveData.get(world, 90);
            if (data.getEvolutionPhase(world.field_73011_w.getDimension()) >= SRPConfigSystems.evolutionDislodgment
               || data.getDeveLevel() >= SRPConfigSystems.deveDisloUse) {
               if (!(world.field_73012_v.nextDouble() > chance)) {
                  if (disloEvent.size() != 0) {
                     if (cothCheck > 1 && pos != null) {
                        int coth = 0;

                        for (EntityLivingBase mob : world.func_72872_a(EntityLivingBase.class, new AxisAlignedBB(pos).func_72314_b(5.0, 3.0, 5.0))) {
                           if (mob.func_70644_a(SRPPotions.COTH_E)) {
                              coth++;
                           }
                        }

                        if (coth < cothCheck) {
                           return;
                        }
                     }

                     int dim = world.field_73011_w.getDimension();
                     int phase = data.getEvolutionPhase(dim);
                     byte[] disloEve = SRPCommandDislodgment.getDisloPhase(phase);
                     if (disloEve != null) {
                        ArrayList<Byte> halo = new ArrayList<>();

                        for (int i = 0; i < disloEve.length; i++) {
                           for (int k = 0; k < disloEvent.size(); k++) {
                              if (disloEve[i] == disloEvent.get(k)) {
                                 halo.add(disloEvent.get(k));
                              }
                           }
                        }

                        if (halo.size() != 0) {
                           boolean looop = false;
                           int gggg = 10;

                           while (gggg > 0 && !looop) {
                              gggg--;
                              byte dislo = halo.get(world.field_73012_v.nextInt(halo.size()));
                              int cost = (int)(SRPCommandDislodgment.getDisloPointPrice(dislo) * SRPCommandDislodgment.getDisloPhaseCost((byte)phase));
                              int duration = (int)(SRPCommandDislodgment.getDisloDuration(dislo) * SRPCommandDislodgment.getDisloPhaseDuration((byte)phase));
                              int value = (int)(SRPCommandDislodgment.getDisloValue(dislo) * SRPCommandDislodgment.getDisloPhaseValue((byte)phase));
                              looop = data.setCurrentCode(world.field_73011_w.getDimension(), dislo, value, duration, world, true, cost);
                           }

                           disloCool = SRPConfigSystems.disloGlobalCooldown;
                        }
                     }
                  }
               }
            }
         }
      }
   }
}
