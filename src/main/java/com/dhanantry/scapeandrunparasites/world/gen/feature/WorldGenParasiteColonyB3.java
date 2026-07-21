package com.dhanantry.scapeandrunparasites.world.gen.feature;

import com.dhanantry.scapeandrunparasites.block.BlockParasiteRubble;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteRubbleDense;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteStain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldGenParasiteColonyB3 extends WorldGenParasiteColonyBase {
   public WorldGenParasiteColonyB3(boolean notify, int stage) {
      super(notify, stage);
      this.wall = SRPBlocks.ParasiteRubbleDense.func_176223_P().func_177226_a(BlockParasiteRubbleDense.VARIANT, BlockParasiteRubbleDense.EnumType.WALL);
      this.tacle = SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.FEELER);
      this.floor = SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.DIRT);
   }

   public boolean func_180709_b(World worldIn, Random rand, BlockPos posss) {
      this.replaceCircleGround(
         worldIn, posss.func_177977_b(), 12, SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.RED)
      );
      this.replaceCircleGround(
         worldIn,
         posss.func_177979_c(2),
         12,
         SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.RED)
      );
      this.replaceCircleGround(
         worldIn,
         posss.func_177979_c(3),
         12,
         SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.RED)
      );
      int missing = 40;
      this.generateSphere(
         worldIn,
         posss,
         4,
         3,
         rand,
         false,
         6,
         false,
         2,
         1,
         5,
         SRPBlocks.ParasiteRubbleDense.func_176223_P().func_177226_a(BlockParasiteRubbleDense.VARIANT, BlockParasiteRubbleDense.EnumType.WALL),
         SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, BlockParasiteRubble.EnumType.BRICKS),
         Blocks.field_150350_a.func_176223_P(),
         missing
      );
      posss = posss.func_177981_b(12);
      int radius = 8;
      double theta = rand.nextDouble() * 2.0 * Math.PI;
      posss = this.getCirclePoint(posss, radius, theta);
      int height = 20;
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
               xx = Math.min(3, xx + 1);
               zz = Math.min(2, zz + 1);
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
      posss = posss.func_177981_b(10);
      if (rand.nextBoolean()) {
         this.addEntrance(worldIn, rand, posss, 5);
         return true;
      } else {
         int var23 = 4;
         theta = rand.nextDouble() * 2.0 * Math.PI;
         posss = this.getCirclePoint(posss, var23, theta);
         int var25 = 15;
         xx = 1;
         zz = 1;
         int var28 = 1;
         int var29 = 1;
         changeX = 0;
         changeZ = 0;

         for (int i = 0; i < var25; i++) {
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

               changeX = var29;
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
            int hx = xx - var28;
            int hz = zz - var28;
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
         this.addEntrance(worldIn, rand, posss, 5);
         return true;
      }
   }
}
