package com.dhanantry.scapeandrunparasites.world.gen.feature;

import com.dhanantry.scapeandrunparasites.block.BlockColonyCore;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteRubble;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteRubbleDense;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteStain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldGenParasiteColonyCore extends WorldGenParasiteColonyBase {
   public WorldGenParasiteColonyCore(boolean notify, int stage) {
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
      posss = posss.func_177979_c(7);
      int height = 12 + rand.nextInt(3);
      int xx = 2;
      int zz = 2;
      int tic = 6;
      int cool = 3;
      int changeX = 0;
      int changeZ = 0;

      for (int i = 0; i < height; i++) {
         changeX--;
         changeZ--;
         if (worldIn.field_73012_v.nextInt(2) == 0 && changeX <= 0) {
            if (worldIn.field_73012_v.nextInt(3) == 0) {
               xx = Math.min(8, xx + 2);
               zz = Math.min(8, zz + 2);
            } else {
               xx = Math.max(5, xx - 1);
               zz = Math.max(5, zz - 1);
            }

            changeX = cool;
         }

         this.generateCircle(
            SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, BlockParasiteRubble.EnumType.FLESH),
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

      int bonusH = rand.nextInt(5);
      height = 10 + rand.nextInt(5);
      int kil = 3;
      int sec = 2;
      double spa = height / sec;
      posss = posss.func_177979_c(3);
      this.generateDNAHelix(
         SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.FLESH),
         worldIn,
         worldIn.field_73012_v,
         posss,
         kil,
         sec,
         spa
      );
      this.generateDNAHelix(
         SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.SACKFLESH),
         worldIn,
         worldIn.field_73012_v,
         posss.func_177981_b(1),
         kil,
         sec,
         spa
      );
      this.generateDNAHelix(
         SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, BlockParasiteRubble.EnumType.BONE),
         worldIn,
         worldIn.field_73012_v,
         posss.func_177981_b(2),
         kil,
         sec,
         spa
      );
      this.generateDNAHelix(Blocks.field_189880_di.func_176223_P(), worldIn, worldIn.field_73012_v, posss.func_177981_b(3), --kil, sec, spa);
      kil += 2;
      int var76 = 2;
      this.generateDNAHelix(
         SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.FEELER),
         worldIn,
         worldIn.field_73012_v,
         posss,
         kil,
         var76,
         spa
      );
      posss = posss.func_177981_b(height);
      this.generateSphere(
         worldIn,
         posss,
         8,
         6,
         rand,
         false,
         6,
         false,
         2,
         1,
         5,
         SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, BlockParasiteRubble.EnumType.FLESH),
         SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.FLESH),
         Blocks.field_150350_a.func_176223_P(),
         missing
      );
      posss = posss.func_177981_b(22);
      int radius = 7;
      double theta = rand.nextDouble() * 2.0 * Math.PI;
      posss = this.getCirclePoint(posss, radius, theta);
      int var37 = 7;
      xx = 1;
      zz = 2;
      int var52 = 1;
      int var57 = 1;
      changeX = 0;
      changeZ = 0;

      for (int i = 0; i < var37; i++) {
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

            changeX = var57;
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
         int hx = xx - var52;
         int hz = zz - var52;
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
      int var77 = 7;
      theta = rand.nextDouble() * 2.0 * Math.PI;
      posss = this.getCirclePoint(posss, var77, theta);
      var37 = 27;
      xx = 1;
      zz = 1;
      var52 = 1;
      var57 = 1;
      changeX = 0;
      changeZ = 0;

      for (int i = 0; i < var37; i++) {
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

            changeX = var57;
         }

         this.generateCircle(
            SRPBlocks.ParasiteRubble.func_176223_P().func_177226_a(BlockParasiteRubble.VARIANT, BlockParasiteRubble.EnumType.FLESH),
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
         int hx = xx - var52;
         int hz = zz - var52;
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
      var77 = 13;
      theta = rand.nextDouble() * 2.0 * Math.PI;
      posss = this.getCirclePoint(posss, var77, theta);
      var37 = 10;
      xx = 1;
      zz = 1;
      var52 = 1;
      var57 = 1;
      changeX = 0;
      changeZ = 0;

      for (int i = 0; i < var37; i++) {
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

            changeX = var57;
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
         int hx = xx - var52;
         int hz = zz - var52;
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
      posss = posss.func_177981_b(7);
      var77 = 2;
      var37 = 8;
      xx = 1;
      zz = 2;
      var52 = 1;
      var57 = 1;
      changeX = 0;
      changeZ = 0;

      for (int i = 0; i < var37; i++) {
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

            changeX = var57;
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
         int hx = xx - var52;
         int hz = zz - var52;
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
         this.placeCore(worldIn, posss, 1);
         return true;
      } else {
         var77 = 4;
         theta = rand.nextDouble() * 2.0 * Math.PI;
         posss = this.getCirclePoint(posss, var77, theta);
         var37 = 7;
         xx = 1;
         zz = 1;
         var52 = 1;
         var57 = 1;
         changeX = 0;
         changeZ = 0;

         for (int i = 0; i < var37; i++) {
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

               changeX = var57;
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
            int hx = xx - var52;
            int hz = zz - var52;
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
         this.placeCore(worldIn, posss, 1);
         return true;
      }
   }

   private void placeCore(World worldIn, BlockPos pos, int stage) {
      this.func_175903_a(worldIn, pos, SRPBlocks.ColonyHeart.func_176223_P().func_177226_a(BlockColonyCore.ACTIVE, stage));
   }
}
