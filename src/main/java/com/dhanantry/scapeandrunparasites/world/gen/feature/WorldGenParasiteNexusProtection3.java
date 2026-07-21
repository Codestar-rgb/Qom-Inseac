package com.dhanantry.scapeandrunparasites.world.gen.feature;

import com.dhanantry.scapeandrunparasites.block.BlockParasiteRubble;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteRubbleDense;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteStain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldGenParasiteNexusProtection3 extends WorldGenParasiteColonyBase {
   public WorldGenParasiteNexusProtection3(boolean notify, int stage) {
      super(notify, stage);
      this.wall = SRPBlocks.ParasiteRubbleDense.func_176223_P().func_177226_a(BlockParasiteRubbleDense.VARIANT, BlockParasiteRubbleDense.EnumType.WALL);
      this.tacle = SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.FEELER);
      this.floor = SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.DIRT);
   }

   public boolean func_180709_b(World worldIn, Random rand, BlockPos posss) {
      int radius = 8;
      int steps = 64;
      IBlockState pillarBlock = SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, BlockParasiteRubble.EnumType.FUNGUS);
      IBlockState pillarBlock2 = SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.FEELER);

      for (BlockPos pos : this.getCirclePoints(posss, radius, steps)) {
         int min = 1;
         int max = 5;
         this.generatePillar(worldIn, pos, worldIn.field_73012_v.nextInt(max - min + 1) + min, pillarBlock, pillarBlock2);
      }

      pillarBlock = SRPBlocks.ParasiteFog.func_176223_P();

      while (radius > 0) {
         for (BlockPos pos : this.getCirclePoints(posss, --radius, steps)) {
            int min = 7;
            int max = 14;
            this.generatePillar(worldIn, pos, worldIn.field_73012_v.nextInt(max - min + 1) + min, pillarBlock, pillarBlock);
         }
      }

      this.generateCircle(
         SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.FEELER),
         SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.FLESH),
         worldIn,
         worldIn.field_73012_v,
         posss.func_177979_c(5),
         8,
         8,
         5,
         2,
         0
      );
      this.generateCircle(
         SRPBlocks.ParasiteFog.func_176223_P(),
         SRPBlocks.ParasiteFog.func_176223_P(),
         worldIn,
         worldIn.field_73012_v,
         posss.func_177979_c(5),
         4,
         4,
         5,
         20000000,
         0
      );
      this.replaceCircleGround(worldIn, posss.func_177977_b(), 8, pillarBlock2);
      return true;
   }
}
