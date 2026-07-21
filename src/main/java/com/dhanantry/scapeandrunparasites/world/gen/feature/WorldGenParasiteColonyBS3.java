package com.dhanantry.scapeandrunparasites.world.gen.feature;

import com.dhanantry.scapeandrunparasites.block.BlockParasiteRubble;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteRubbleDense;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteStain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldGenParasiteColonyBS3 extends WorldGenParasiteColonyBase {
   private IBlockState floor = SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.DIRT);
   private IBlockState tacle = SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.FEELER);
   private IBlockState wall = SRPBlocks.ParasiteRubbleDense
      .func_176223_P()
      .func_177226_a(BlockParasiteRubbleDense.VARIANT, BlockParasiteRubbleDense.EnumType.WALL);

   public WorldGenParasiteColonyBS3(boolean notify, int stage) {
      super(notify, stage);
   }

   public boolean func_180709_b(World worldIn, Random rand, BlockPos posss) {
      this.replaceCircleGround(
         worldIn, posss.func_177977_b(), 12, SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.RED)
      );
      this.replaceCircleGround(
         worldIn, posss.func_177979_c(2), 8, SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.RED)
      );
      this.replaceCircleGround(
         worldIn, posss.func_177979_c(3), 8, SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.RED)
      );
      int radius = 3;
      double theta = rand.nextDouble() * 2.0 * Math.PI;
      BlockPos var9 = this.getCirclePoint(posss, radius, theta);
      int missing = 40;
      this.generateSphere(
         worldIn,
         var9,
         2,
         10,
         rand,
         false,
         3,
         false,
         2,
         1,
         2,
         SRPBlocks.ParasiteRubbleDense.func_176223_P().func_177226_a(BlockParasiteRubbleDense.VARIANT, BlockParasiteRubbleDense.EnumType.WALL),
         SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, BlockParasiteRubble.EnumType.BRICKS),
         Blocks.field_150350_a.func_176223_P(),
         missing
      );
      int var11 = 6;
      theta = rand.nextDouble() * 2.0 * Math.PI;
      posss = this.getCirclePoint(posss, var11, theta);
      this.generateSphere(
         worldIn,
         posss,
         2,
         10,
         rand,
         false,
         3,
         false,
         4,
         2,
         4,
         SRPBlocks.ParasiteRubbleDense.func_176223_P().func_177226_a(BlockParasiteRubbleDense.VARIANT, BlockParasiteRubbleDense.EnumType.WALL),
         SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, BlockParasiteRubble.EnumType.BRICKS),
         Blocks.field_150350_a.func_176223_P(),
         missing
      );
      return true;
   }
}
