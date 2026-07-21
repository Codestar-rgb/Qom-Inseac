package com.dhanantry.scapeandrunparasites.world.gen.feature;

import com.dhanantry.scapeandrunparasites.block.BlockParasiteRubbleDense;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteStain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.world.gen.WorldGenCustomStructures;
import com.dhanantry.scapeandrunparasites.world.gen.structure.WorldGenStructure;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldGenParasiteNexusProtection2 extends WorldGenParasiteColonyBase {
   public WorldGenParasiteNexusProtection2(boolean notify, int stage) {
      super(notify, stage);
      this.wall = SRPBlocks.ParasiteRubbleDense.func_176223_P().func_177226_a(BlockParasiteRubbleDense.VARIANT, BlockParasiteRubbleDense.EnumType.WALL);
      this.tacle = SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.FEELER);
      this.floor = SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.DIRT);
   }

   public boolean func_180709_b(World worldIn, Random rand, BlockPos posss) {
      int radius = 6;
      int height = 5;
      IBlockState pillarBlock = Blocks.field_150348_b.func_176223_P();
      this.generateRandomPillar(worldIn, posss.func_177979_c(1), radius, height, pillarBlock);
      int var7 = 9;
      int var8 = 9;
      pillarBlock = Blocks.field_150348_b.func_176223_P();
      this.generateRandomPillar(worldIn, posss.func_177979_c(1), var7, var8, pillarBlock);
      return true;
   }

   public void generateRandomPillar(World worldIn, BlockPos center, int radius, int height, IBlockState blockState) {
      Random rand = new Random();
      double theta = rand.nextDouble() * 2.0 * Math.PI;
      BlockPos basePos = this.getCirclePoint(center, radius, theta);
      basePos = ParasiteEventEntity.getFloor(worldIn, basePos, 7);
      if (basePos != null) {
         String out;
         basePos = basePos.func_177977_b();
         out = "beckon_";
         int outt = worldIn.field_73012_v.nextInt(4) + 2;
         out = out + outt + "x" + outt;
         label25:
         switch (outt) {
            case 2:
               out = out + "_1";
               break;
            case 3:
               switch (worldIn.field_73012_v.nextInt(5) + 1) {
                  case 1:
                     out = out + "_1";
                     break label25;
                  case 2:
                     out = out + "_2";
                     break label25;
                  case 3:
                     out = out + "_3";
                     break label25;
                  case 4:
                     out = out + "_4";
                     break label25;
                  case 5:
                     out = out + "_5";
                  default:
                     break label25;
               }
            case 4:
               switch (worldIn.field_73012_v.nextInt(2) + 1) {
                  case 1:
                     out = out + "_1";
                     break label25;
                  case 2:
                     out = out + "_2";
                  default:
                     break label25;
               }
            case 5:
               out = out + "_1";
         }

         System.out.println("ds aa222 " + out);
         WorldGenCustomStructures.generateInPosition(new WorldGenStructure(out), rand, worldIn, basePos, 0, 0, 0);
      }
   }
}
