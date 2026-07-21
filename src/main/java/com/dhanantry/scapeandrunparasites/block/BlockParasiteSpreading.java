package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.init.SRPBiomes;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventWorld;
import com.dhanantry.scapeandrunparasites.util.SRPReference;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import com.dhanantry.scapeandrunparasites.util.convert.BeckonBlockInfestation;
import com.dhanantry.scapeandrunparasites.util.handlers.BiomeUpdateQueue;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import com.dhanantry.scapeandrunparasites.world.SRPWorldData;
import com.dhanantry.scapeandrunparasites.world.biome.BiomeParasiteBase;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class BlockParasiteSpreading extends BlockBase {
   public static int blockParasiteCount;
   public boolean isInfestedBlock;

   public BlockParasiteSpreading(Material material, String name, float hardness, boolean creative, boolean isInfested) {
      super(material, name, hardness, creative, true);
      this.isInfestedBlock = isInfested;
   }

   public BlockParasiteSpreading(Material material, String name, float hardness, boolean creative, boolean isInfested, float resistance) {
      this(material, name, hardness, creative, isInfested);
      this.func_149752_b(resistance);
   }

   public void func_180645_a(World worldIn, BlockPos pos, IBlockState state, Random rand) {
      if (worldIn.func_175697_a(pos, 3)) {
         IBlockState aboveState = worldIn.func_180495_p(pos.func_177984_a());
         Block aboveBlock = aboveState.func_177230_c();
         boolean hasSnowAbove = aboveBlock == Blocks.field_150431_aC || aboveBlock == Blocks.field_150433_aE;
         if (this == SRPBlocks.HarlequinnGrass) {
            if (hasSnowAbove) {
               worldIn.func_180501_a(pos, SRPBlocks.LocsBlock.func_176223_P(), 3);
               return;
            }
         } else if (this == SRPBlocks.HarleskinnBlock && aboveBlock == SRPBlocks.LocsBlock) {
            worldIn.func_180501_a(pos, SRPBlocks.HarlequinnGrass.func_176223_P(), 3);
            return;
         }

         if (!this.isInfestedBlock) {
            this.func_180650_b(worldIn, pos, state, rand);
         } else if (rand.nextDouble() < 0.5) {
            if (worldIn.func_180494_b(pos) instanceof BiomeParasiteBase) {
               BiomeParasiteBase biomePara = (BiomeParasiteBase)worldIn.func_180494_b(pos);
               String[] blockList = biomePara.getDirt().split(":");
               Block one = Block.func_149684_b(blockList[0] + ":" + blockList[1]);
               worldIn.func_175656_a(pos, one.func_176203_a(Integer.parseInt(blockList[2])));
            } else {
               int heart = ParasiteEventWorld.canBiomeStillExist(worldIn, pos, true);
               if (heart > 0) {
                  SpreadBiome(worldIn, pos, heart, ParasiteEventWorld.canBiomeStillExistType(worldIn, pos, true));
               }
            }
         } else if (rand.nextDouble() <= 0.05) {
            this.func_180650_b(worldIn, pos, state, rand);
         }
      }
   }

   public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
      if (!worldIn.field_72995_K) {
         if (this.isInfestedBlock) {
            canInfestBlock(worldIn, pos, rand, this.func_176201_c(state), true);
         } else if (worldIn.func_180494_b(pos) instanceof BiomeParasiteBase) {
            spreadBiomeBlockStain(worldIn, pos, rand);
         } else {
            int heart = ParasiteEventWorld.canBiomeStillExist(worldIn, pos, true);
            if (heart > 0) {
               SpreadBiome(worldIn, pos, heart, ParasiteEventWorld.canBiomeStillExistType(worldIn, pos, true));
            }
         }
      }
   }

   public void func_176199_a(World worldIn, BlockPos pos, Entity entityIn) {
   }

   @Override
   public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
      boolean flag = super.removedByPlayer(state, world, pos, player, willHarvest);
      if (world.field_72995_K) {
         return flag;
      } else {
         if (SRPConfigSystems.useEvolution && this.func_176201_c(state) > 0) {
            SRPSaveData data = SRPSaveData.get(world, 9);
            data.setTotalKills(world.field_73011_w.getDimension(), -SRPConfigSystems.valueLossBlockStain, true, world, false, 19);
         }

         return flag;
      }
   }

   public static void SpreadBiome(World worldIn, BlockPos pos, int age, int type) {
      if (SRPConfigWorld.nodesActivated && SRPConfigWorld.biomeRegster) {
         SRPWorldData data = SRPWorldData.get(worldIn);
         int distance = data.getDistanceSpreadByAge(age, false);

         for (int x = pos.func_177958_n() - 4; x <= pos.func_177958_n() + 4; x++) {
            for (int z = pos.func_177952_p() - 4; z <= pos.func_177952_p() + 4; z++) {
               BlockPos convert = new BlockPos(x, pos.func_177956_o(), z);
               if (data.isInRangeOfHeart(convert, distance) != -1
                  && !ParasiteEventEntity.checkName(
                     worldIn.func_180494_b(convert).getRegistryName().toString(), SRPConfigWorld.biomeBlackList, SRPConfigWorld.biomeBlackListInverted
                  )) {
                  Biome current = worldIn.func_180494_b(convert);
                  if (!(current instanceof BiomeParasiteBase)) {
                     Biome target = SRPReference.getBiomeFromInt(type);
                     if (target == null || current != target) {
                        positionToParasiteBiome(worldIn, convert, type);
                        BiomeUpdateQueue.enqueue(
                           convert.func_177958_n(), convert.func_177956_o(), convert.func_177952_p(), true, type, worldIn.field_73011_w.getDimension()
                        );
                     }
                  }
               }
            }
         }
      }
   }

   public static void spreadBiomeBlockStain(World worldIn, BlockPos pos, Random rand) {
      if (worldIn.func_180494_b(pos) instanceof BiomeParasiteBase) {
         BiomeParasiteBase biomePara = (BiomeParasiteBase)worldIn.func_180494_b(pos);
         int covertedBlocks = 0;

         for (int dir = 0; dir <= 5; dir++) {
            BlockPos helper = directionToSpread(pos, dir);
            covertedBlocks += biomePara.convertBlock(helper, worldIn, rand);
            spreadToAir(worldIn, helper);
            blockParasiteCount += covertedBlocks;
         }

         if (covertedBlocks != 0 && SRPConfigSystems.useEvolution) {
            SRPSaveData.get(worldIn, 76)
               .setTotalKills(worldIn.field_73011_w.getDimension(), SRPConfigSystems.valueBlock * covertedBlocks, true, worldIn, true, 20);
         }
      }
   }

   public static void spreadBiomeBlockTrunk(World worldIn, BlockPos pos, Random rand) {
      if (worldIn.func_175659_aa() != EnumDifficulty.PEACEFUL) {
         int posx = pos.func_177958_n();
         int posz = pos.func_177952_p();
         int posy = pos.func_177956_o();

         for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
               for (int y = -1; y <= 1; y++) {
                  BlockPos helper = new BlockPos(posx + x, posy + y, posz + z);
                  IBlockState lookingState = worldIn.func_180495_p(helper);
                  Block lookingBlock = lookingState.func_177230_c();
                  if (lookingBlock.isWood(worldIn, helper) && lookingBlock != SRPBlocks.ParasiteTrunk) {
                     worldIn.func_175656_a(helper, SRPBlocks.ParasiteTrunk.func_176223_P());
                     spreadToAir(worldIn, helper);
                     if (SRPConfigSystems.useEvolution) {
                        SRPSaveData.get(worldIn, 77).setTotalKills(worldIn.field_73011_w.getDimension(), SRPConfigSystems.valueBlock, true, worldIn, true, 21);
                     }
                  }
               }
            }
         }
      }
   }

   public static void positionToParasiteBiome(World worldIn, BlockPos pos, int type) {
      byte biomeID = (byte)Biome.func_185362_a(SRPBiomes.biomeShrouded);
      GameRules rules = worldIn.func_82736_K();
      if (rules.func_82766_b("srpForceHarlequin")) {
         biomeID = (byte)Biome.func_185362_a(SRPBiomes.biomeHarlequin);
         int inChunkX = pos.func_177958_n() & 15;
         int inChunkZ = pos.func_177952_p() & 15;
         Biome currentForced = worldIn.func_180494_b(pos);
         Biome forced = SRPBiomes.biomeHarlequin;
         if (currentForced != forced) {
            worldIn.func_175726_f(pos).func_76605_m()[inChunkZ << 4 | inChunkX] = biomeID;
            worldIn.func_175726_f(pos).func_76630_e();
            worldIn.func_72975_g(pos.func_177958_n(), pos.func_177952_p(), 0, pos.func_177956_o());
         }
      } else {
         Biome target = SRPReference.getBiomeFromInt(type);
         if (target != null) {
            Biome current = worldIn.func_180494_b(pos);
            if (current != target) {
               biomeID = (byte)Biome.func_185362_a(target);
               int inChunkX = pos.func_177958_n() & 15;
               int inChunkZ = pos.func_177952_p() & 15;
               worldIn.func_175726_f(pos).func_76605_m()[inChunkZ << 4 | inChunkX] = biomeID;
               worldIn.func_175726_f(pos).func_76630_e();
               worldIn.func_72975_g(pos.func_177958_n(), pos.func_177952_p(), 0, pos.func_177956_o());
            }
         }
      }
   }

   public static BlockPos directionToSpread(BlockPos atm, int choice) {
      switch (choice) {
         case 0:
            atm = atm.func_177978_c();
            break;
         case 1:
            atm = atm.func_177974_f();
            break;
         case 2:
            atm = atm.func_177976_e();
            break;
         case 3:
            atm = atm.func_177968_d();
            break;
         case 4:
            atm = atm.func_177984_a();
            break;
         default:
            atm = atm.func_177977_b();
      }

      return atm;
   }

   public static void spreadToAir(World worldIn, BlockPos atm) {
   }

   public static void canInfestBlock(World worldIn, BlockPos pos, Random rand, int stage, boolean fromVenkrol) {
      BeckonBlockInfestation.beckonInfestation(worldIn, pos, rand, stage, fromVenkrol);
   }
}
