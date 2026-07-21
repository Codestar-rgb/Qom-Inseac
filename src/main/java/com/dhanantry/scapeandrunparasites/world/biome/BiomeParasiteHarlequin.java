package com.dhanantry.scapeandrunparasites.world.biome;

import com.dhanantry.scapeandrunparasites.block.BlockParasiteBush;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteBush;
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

public class BiomeParasiteHarlequin extends BiomeParasiteBase {
   public static WorldGenAbstractTree treeP = new WorldGenParasiteTree(false);
   public static WorldGenAbstractTree treePT = new WorldGenParasiteTreeThin(false);
   public WorldGenerator grassP1 = new WorldGenParasiteBush(false, BlockParasiteBush.EnumType.EYE, 1);
   public WorldGenerator grassP2 = new WorldGenParasiteBush(false, BlockParasiteBush.EnumType.POP, 2);

   public BiomeParasiteHarlequin() {
      super(new BiomeProperties("Parasite Biome Harlequin").func_185398_c(0.13F).func_185400_d(0.5F).func_185410_a(0.0F).func_185395_b(0.5F).func_185411_b());
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
      return SRPConfigWorld.biomeThreeSkyColor;
   }

   @SideOnly(Side.CLIENT)
   public int func_180627_b(BlockPos blockPos) {
      return SRPConfigWorld.biomeThreeGrassColor;
   }

   @SideOnly(Side.CLIENT)
   public int func_180625_c(BlockPos blockPos) {
      return SRPConfigWorld.biomeThreeFoliageColor;
   }

   public int getWaterColorMultiplier() {
      return SRPConfigWorld.biomeThreeWaterColor;
   }

   @Override
   public float getRedValue() {
      return SRPConfigWorld.biomeThreeFogRed / 255.0F;
   }

   @Override
   public float getGreenValue() {
      return SRPConfigWorld.biomeThreeFogGreen / 255.0F;
   }

   @Override
   public float getBlueValue() {
      return SRPConfigWorld.biomeThreeFogBlue / 255.0F;
   }

   @Override
   public String[] getBlockList() {
      return SRPConfigWorld.biomeThreeBlockList;
   }

   @Override
   public String getDirt() {
      return "srparasites:harlequinn_grass:0";
   }

   @Override
   public String getGravel() {
      return "srparasites:infestedsand:0";
   }

   @Override
   public String getLog() {
      return "srparasites:parasitetrunk:0";
   }

   @Override
   public String getStone() {
      return "srparasites:harleskinn_block:0";
   }

   @Override
   public String getCobblestone() {
      return "srparasites:harleskinn_block:0";
   }

   @Override
   public String getSand() {
      return "srparasites:harleskinn_block:0";
   }

   @Override
   public String getSandstone() {
      return "srparasites:harleskinn_block:0";
   }

   @Override
   public String getLeaves() {
      return "srparasites:alveoli:0";
   }

   @Override
   public String getLeavesG() {
      return "srparasites:alveoli_growth:0";
   }

   @Override
   public String getPlank() {
      return "srparasites:harleskinn_block:0";
   }

   @Override
   public String getBush() {
      return "srparasites:alveoli_growth:0";
   }

   @Override
   public void spawnGenFeatureParasite(World worldIn, BlockPos pos, Random rand) {
   }

   @Override
   public void spawnGenRoofParasite(World worldIn, BlockPos pos, Random rand) {
   }
}
