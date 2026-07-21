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

public class WorldGenParasiteColonyBS2 extends WorldGenParasiteColonyBase {
   private IBlockState floor = SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.DIRT);
   private IBlockState tacle = SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.FEELER);
   private IBlockState wall = SRPBlocks.ParasiteRubbleDense
      .func_176223_P()
      .func_177226_a(BlockParasiteRubbleDense.VARIANT, BlockParasiteRubbleDense.EnumType.WALL);

   public WorldGenParasiteColonyBS2(boolean notify, int stage) {
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
      int missing = 40;
      int radius = 7;
      double theta = rand.nextDouble() * 2.0 * Math.PI;
      posss = this.getCirclePoint(posss, radius, theta);
      int height = 28;
      int xx = 1;
      int zz = 2;
      int tic = 1;
      int cool = 1;
      int changeX = 0;
      int changeZ = 0;

      for (int i = 0; i < height; i++) {
         changeX--;
         changeZ--;
         if (worldIn.field_73012_v.nextInt(2) == 0 && changeX <= 0) {
            if (worldIn.field_73012_v.nextInt(2) == 0) {
               xx = Math.min(2, xx + 1);
               zz = Math.min(1, zz + 1);
            } else {
               xx = Math.max(1, xx - 1);
               zz = Math.max(1, zz - 1);
            }

            changeX = cool;
         }

         this.generateCircle(
            SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, BlockParasiteRubble.EnumType.BONE),
            SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.FLESH),
            worldIn,
            worldIn.field_73012_v,
            posss,
            xx,
            zz,
            1,
            20000,
            6
         );
         int hx = xx - tic;
         int hz = zz - tic;
         this.generateCircle(
            SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.FLESH),
            Blocks.field_189880_di.func_176223_P(),
            worldIn,
            worldIn.field_73012_v,
            posss,
            hx,
            hz,
            1,
            20000,
            6
         );
         posss = posss.func_177981_b(1);
      }

      this.generateSphere(
         worldIn,
         posss,
         3,
         3,
         rand,
         false,
         3,
         false,
         2,
         1,
         5,
         SRPBlocks.ParasiteRubbleDense.func_176223_P().func_177226_a(BlockParasiteRubbleDense.VARIANT, BlockParasiteRubbleDense.EnumType.WALL),
         SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, BlockParasiteRubble.EnumType.BRICKS),
         Blocks.field_150350_a.func_176223_P(),
         missing
      );
      int var22 = 7;
      theta = rand.nextDouble() * 2.0 * Math.PI;
      posss = this.getCirclePoint(posss, var22, theta);
      int var26 = 47;
      xx = 1;
      zz = 1;
      int var32 = 1;
      int var34 = 1;
      changeX = 0;
      changeZ = 0;

      for (int i = 0; i < var26; i++) {
         changeX--;
         changeZ--;
         if (worldIn.field_73012_v.nextInt(2) == 0 && changeX <= 0) {
            if (worldIn.field_73012_v.nextInt(2) == 0) {
               xx = Math.min(2, xx + 1);
               zz = Math.min(1, zz + 1);
            } else {
               xx = Math.max(1, xx - 1);
               zz = Math.max(1, zz - 1);
            }

            changeX = var34;
         }

         this.generateCircle(
            SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, BlockParasiteRubble.EnumType.BONE),
            SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.FLESH),
            worldIn,
            worldIn.field_73012_v,
            posss,
            xx,
            zz,
            1,
            20000,
            6
         );
         int hx = xx - var32;
         int hz = zz - var32;
         this.generateCircle(
            SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.FLESH),
            Blocks.field_189880_di.func_176223_P(),
            worldIn,
            worldIn.field_73012_v,
            posss,
            hx,
            hz,
            1,
            20000,
            6
         );
         posss = posss.func_177981_b(1);
      }

      this.generateSphere(
         worldIn,
         posss,
         3,
         3,
         rand,
         false,
         3,
         false,
         2,
         1,
         5,
         SRPBlocks.ParasiteRubbleDense.func_176223_P().func_177226_a(BlockParasiteRubbleDense.VARIANT, BlockParasiteRubbleDense.EnumType.WALL),
         SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, BlockParasiteRubble.EnumType.BRICKS),
         Blocks.field_150350_a.func_176223_P(),
         missing
      );
      var22 = 2;
      theta = rand.nextDouble() * 2.0 * Math.PI;
      posss = this.getCirclePoint(posss, var22, theta);
      var26 = 17;
      xx = 1;
      zz = 1;
      var32 = 1;
      var34 = 1;
      changeX = 0;
      changeZ = 0;

      for (int i = 0; i < var26; i++) {
         changeX--;
         changeZ--;
         if (worldIn.field_73012_v.nextInt(2) == 0 && changeX <= 0) {
            if (worldIn.field_73012_v.nextInt(2) == 0) {
               xx = Math.min(2, xx + 1);
               zz = Math.min(1, zz + 1);
            } else {
               xx = Math.max(1, xx - 1);
               zz = Math.max(1, zz - 1);
            }

            changeX = var34;
         }

         this.generateCircle(
            SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, BlockParasiteRubble.EnumType.BONE),
            SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.FLESH),
            worldIn,
            worldIn.field_73012_v,
            posss,
            xx,
            zz,
            1,
            20000,
            6
         );
         int hx = xx - var32;
         int hz = zz - var32;
         this.generateCircle(
            SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.FLESH),
            Blocks.field_189880_di.func_176223_P(),
            worldIn,
            worldIn.field_73012_v,
            posss,
            hx,
            hz,
            1,
            20000,
            6
         );
         posss = posss.func_177981_b(1);
      }

      this.generateSphere(
         worldIn,
         posss,
         3,
         3,
         rand,
         false,
         3,
         false,
         2,
         1,
         5,
         SRPBlocks.ParasiteRubbleDense.func_176223_P().func_177226_a(BlockParasiteRubbleDense.VARIANT, BlockParasiteRubbleDense.EnumType.WALL),
         SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, BlockParasiteRubble.EnumType.BRICKS),
         Blocks.field_150350_a.func_176223_P(),
         missing
      );
      return true;
   }
}
