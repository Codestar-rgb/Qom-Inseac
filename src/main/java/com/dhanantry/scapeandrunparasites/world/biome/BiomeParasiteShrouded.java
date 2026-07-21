package com.dhanantry.scapeandrunparasites.world.biome;

import com.dhanantry.scapeandrunparasites.block.BlockParasiteBush;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteSapling;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteBall;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteBigBall;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteBush;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteMouth;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteSpine;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteTallFlower;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteTenFlower;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteTree;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteTreeThin;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeParasiteShrouded extends BiomeParasiteBase {
   public static WorldGenAbstractTree treeP = new WorldGenParasiteTree(false);
   public static WorldGenAbstractTree treePT = new WorldGenParasiteTreeThin(false);
   public WorldGenerator grassP1 = new WorldGenParasiteBush(false, BlockParasiteBush.EnumType.EYE, 1);
   public WorldGenerator grassP2 = new WorldGenParasiteBush(false, BlockParasiteBush.EnumType.POP, 2);

   public BiomeParasiteShrouded() {
      super(new BiomeProperties("Parasite Biome Shrouded").func_185398_c(0.13F).func_185400_d(0.5F));
      this.field_76760_I.field_76832_z = 1;
      this.field_76760_I.field_189870_A = 0.0F;
      this.field_76760_I.field_76803_B = 15;
      this.field_76760_I.field_76802_A = 4;
      this.field_76760_I.field_76804_C = 2;
      this.field_76760_I.field_76800_F = 15;
      this.field_76760_I.field_76807_J = 1;
   }

   public WorldGenAbstractTree func_150567_a(Random rand) {
      return rand.nextInt(3) == 0 ? treePT : treeP;
   }

   public WorldGenerator func_76730_b(Random rand) {
      return rand.nextInt(2) == 0 ? this.grassP2 : this.grassP1;
   }

   public void func_180624_a(World worldIn, Random rand, BlockPos pos) {
      super.func_180624_a(worldIn, rand, pos);
   }

   public void func_180622_a(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
      super.func_180622_a(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
      double d0 = 1.0;
      if (d0 > 0.0) {
         int i = x & 15;
         int j = z & 15;

         for (int k = 255; k >= 0; k--) {
            if (chunkPrimerIn.func_177856_a(j, k, i).func_185904_a() != Material.field_151579_a) {
               if (k == 62 && chunkPrimerIn.func_177856_a(j, k, i).func_177230_c() != Blocks.field_150355_j) {
                  chunkPrimerIn.func_177855_a(j, k, i, field_185372_h);
               }
               break;
            }
         }
      }
   }

   @SideOnly(Side.CLIENT)
   public int func_76731_a(float currentTemperature) {
      return SRPConfigWorld.biomeOneSkyColor;
   }

   @SideOnly(Side.CLIENT)
   public int func_180627_b(BlockPos blockPos) {
      return SRPConfigWorld.biomeOneGrassColor;
   }

   @SideOnly(Side.CLIENT)
   public int func_180625_c(BlockPos blockPos) {
      return SRPConfigWorld.biomeOneFoliageColor;
   }

   public int getWaterColorMultiplier() {
      return SRPConfigWorld.biomeOneWaterColor;
   }

   @Override
   public float getRedValue() {
      return SRPConfigWorld.biomeOneFogRed / 255.0F;
   }

   @Override
   public float getGreenValue() {
      return SRPConfigWorld.biomeOneFogGreen / 255.0F;
   }

   @Override
   public float getBlueValue() {
      return SRPConfigWorld.biomeOneFogBlue / 255.0F;
   }

   @Override
   public String[] getBlockList() {
      return SRPConfigWorld.biomeOneBlockList;
   }

   @Override
   public String getDirt() {
      return "srparasites:parasitestain:0";
   }

   @Override
   public String getGravel() {
      return "srparasites:parasitestain:5";
   }

   @Override
   public String getLog() {
      return "srparasites:parasitetrunk:0";
   }

   @Override
   public String getStone() {
      return "srparasites:parasiterubble:2";
   }

   @Override
   public String getCobblestone() {
      return "srparasites:parasiterubble:0";
   }

   @Override
   public String getSand() {
      return "srparasites:parasitestain:1";
   }

   @Override
   public String getSandstone() {
      return "srparasites:parasiterubble:1";
   }

   @Override
   public String getLeaves() {
      return "minecraft:air:0";
   }

   @Override
   public String getLeavesG() {
      return "minecraft:air:0";
   }

   @Override
   public String getPlank() {
      return "srparasites:parasiteplank:0";
   }

   @Override
   public String getBush() {
      return "srparasites:parasitebush:0";
   }

   @Override
   public void spawnGenFeatureParasite(World worldIn, BlockPos pos, Random rand) {
      double bonus = 0.0;
      if (worldIn.func_180495_p(pos).func_177230_c() != Blocks.field_150350_a && worldIn.func_180495_p(pos).func_185904_a() == Material.field_151586_h) {
         pos = ParasiteEventEntity.getFloor(worldIn, pos, 10);
         bonus = 3.0E-5;
         if (pos == null) {
            return;
         }
      }

      if (rand.nextDouble() < 0.0015) {
         if (rand.nextDouble() < 0.2) {
            WorldGenAbstractTree gen = new WorldGenParasiteTreeThin(false);
            if (!gen.func_180709_b(worldIn, rand, pos) && worldIn.func_180495_p(pos).func_177230_c() == Blocks.field_150350_a) {
               worldIn.func_175656_a(
                  pos, SRPBlocks.ParasiteSapling.func_176223_P().func_177226_a(BlockParasiteSapling.VARIANT, BlockParasiteSapling.EnumType.TREE)
               );
            }
         } else {
            WorldGenAbstractTree gen = new WorldGenParasiteTree(false);
            if (!gen.func_180709_b(worldIn, rand, pos) && worldIn.func_180495_p(pos).func_177230_c() == Blocks.field_150350_a) {
               worldIn.func_175656_a(
                  pos, SRPBlocks.ParasiteSapling.func_176223_P().func_177226_a(BlockParasiteSapling.VARIANT, BlockParasiteSapling.EnumType.TREETHIN)
               );
            }
         }
      } else if (rand.nextDouble() < 0.001) {
         WorldGenParasiteTallFlower gen = new WorldGenParasiteTallFlower(false);
         if (!gen.func_180709_b(worldIn, rand, pos) && worldIn.func_180495_p(pos).func_177230_c() == Blocks.field_150350_a) {
            worldIn.func_175656_a(
               pos, SRPBlocks.ParasiteSapling.func_176223_P().func_177226_a(BlockParasiteSapling.VARIANT, BlockParasiteSapling.EnumType.FLOWERTALL)
            );
         }
      } else if (rand.nextDouble() < 5.0E-4) {
         WorldGenParasiteSpine gen = new WorldGenParasiteSpine(false);
         if (!gen.func_180709_b(worldIn, rand, pos)) {
         }
      } else if (rand.nextDouble() < 1.0E-4) {
         WorldGenParasiteTenFlower gen = new WorldGenParasiteTenFlower(false);
         if (!gen.func_180709_b(worldIn, rand, pos)) {
         }
      } else if (rand.nextDouble() < 7.0E-5 + bonus) {
         WorldGenParasiteBall gen = new WorldGenParasiteBall(false);
         if (!gen.func_180709_b(worldIn, rand, pos)) {
         }
      } else if (rand.nextDouble() < 2.0E-5 + bonus) {
         WorldGenParasiteBigBall gen = new WorldGenParasiteBigBall(false);
         if (!gen.func_180709_b(worldIn, rand, pos)) {
         }
      } else {
         if (rand.nextInt(500) == 0) {
            WorldGenParasiteBush gen = new WorldGenParasiteBush(false, BlockParasiteBush.EnumType.TENDRIL, 4);
            gen.func_180709_b(worldIn, rand, pos);
         }

         if (rand.nextInt(250) == 0) {
            switch (rand.nextInt(3)) {
               case 0:
                  WorldGenParasiteBush gen1 = new WorldGenParasiteBush(false, BlockParasiteBush.EnumType.POP, 1);
                  gen1.func_180709_b(worldIn, rand, pos);
                  break;
               case 1:
                  WorldGenParasiteBush gen2 = new WorldGenParasiteBush(false, BlockParasiteBush.EnumType.EYE, 2);
                  gen2.func_180709_b(worldIn, rand, pos);
                  break;
               case 2:
                  WorldGenParasiteBush gen3 = new WorldGenParasiteBush(false, BlockParasiteBush.EnumType.TOOH, 3);
                  gen3.func_180709_b(worldIn, rand, pos);
            }
         }

         if (rand.nextDouble() < 3.0E-4) {
            WorldGenParasiteMouth gen = new WorldGenParasiteMouth(false);
            if (gen.func_180709_b(worldIn, rand, pos)) {
               return;
            }
         }
      }
   }

   @Override
   public void spawnGenRoofParasite(World worldIn, BlockPos pos, Random rand) {
      if (worldIn.func_180495_p(pos).func_177230_c() == Blocks.field_150350_a) {
         worldIn.func_175656_a(pos, SRPBlocks.ParasiteBush.func_176223_P().func_177226_a(BlockParasiteBush.VARIANT, BlockParasiteBush.EnumType.BINE));
         pos = pos.func_177977_b();
         if (worldIn.func_180495_p(pos).func_177230_c() == Blocks.field_150350_a) {
            worldIn.func_175656_a(pos, SRPBlocks.ParasiteBush.func_176223_P().func_177226_a(BlockParasiteBush.VARIANT, BlockParasiteBush.EnumType.BINE));
            pos = pos.func_177977_b();
            if (rand.nextInt(2) == 0) {
               if (worldIn.func_180495_p(pos).func_177230_c() != Blocks.field_150350_a) {
                  return;
               }

               worldIn.func_175656_a(pos, SRPBlocks.ParasiteBush.func_176223_P().func_177226_a(BlockParasiteBush.VARIANT, BlockParasiteBush.EnumType.BINE));
               pos = pos.func_177977_b();
               if (rand.nextInt(2) == 0) {
                  if (worldIn.func_180495_p(pos).func_177230_c() != Blocks.field_150350_a) {
                     return;
                  }

                  worldIn.func_175656_a(pos, SRPBlocks.ParasiteBush.func_176223_P().func_177226_a(BlockParasiteBush.VARIANT, BlockParasiteBush.EnumType.BINE));
                  pos = pos.func_177977_b();
                  if (rand.nextInt(2) == 0) {
                     if (worldIn.func_180495_p(pos).func_177230_c() != Blocks.field_150350_a) {
                        return;
                     }

                     worldIn.func_175656_a(
                        pos, SRPBlocks.ParasiteBush.func_176223_P().func_177226_a(BlockParasiteBush.VARIANT, BlockParasiteBush.EnumType.BINE)
                     );
                  }
               }
            }
         }
      }
   }
}
