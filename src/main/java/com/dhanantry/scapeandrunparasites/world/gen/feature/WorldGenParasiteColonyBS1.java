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

public class WorldGenParasiteColonyBS1 extends WorldGenParasiteColonyBase {
   private IBlockState floor = SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.DIRT);
   private IBlockState tacle = SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.FEELER);
   private IBlockState wall = SRPBlocks.ParasiteRubbleDense
      .func_176223_P()
      .func_177226_a(BlockParasiteRubbleDense.VARIANT, BlockParasiteRubbleDense.EnumType.WALL);

   public WorldGenParasiteColonyBS1(boolean notify, int stage) {
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
      int radius = 4;
      double theta = rand.nextDouble() * 2.0 * Math.PI;
      posss = this.getCirclePoint(posss, radius, theta);
      int height = 8;
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
         return true;
      } else {
         int var22 = 4;
         theta = rand.nextDouble() * 2.0 * Math.PI;
         posss = this.getCirclePoint(posss, var22, theta);
         int var24 = 7;
         xx = 1;
         zz = 1;
         int var27 = 1;
         int var28 = 1;
         changeX = 0;
         changeZ = 0;

         for (int i = 0; i < var24; i++) {
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

               changeX = var28;
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
            int hx = xx - var27;
            int hz = zz - var27;
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

   private BlockPos placeWallsBottom(World worldIn, BlockPos position, int loop, Random rand, IBlockState state, int oY) {
      BlockPos currentP = position;

      for (int current = 0; current < loop; currentP = currentP.func_177984_a()) {
         BlockPos helpRoot = currentP;

         for (int i = 0; i <= 3; i++) {
            BlockPos helper = this.getDirectionRoot(helpRoot, i, 2);
            BlockPos rootH = helper;

            for (int o = 0; o < 1; o++) {
               for (int times = 1; times <= 1; times++) {
                  if (o == 0) {
                     helper = this.getDirectionRoot(rootH, (i + 1) % 4, times + 1);
                  } else {
                     helper = this.getDirectionRoot(rootH, (i + 3) % 4, times + 1);
                  }

                  if (!worldIn.func_180495_p(helper.func_177977_b()).func_185913_b()) {
                     this.placeBlock(worldIn, helper.func_177977_b(), state);
                  }

                  this.placeBlock(worldIn, helper, state);
               }
            }

            helper = this.getDirectionRoot(rootH, i, 1);
            rootH = helper;

            for (int o = 0; o < 2; o++) {
               for (int times = 1; times <= 1; times++) {
                  if (o == 0) {
                     helper = this.getDirectionRoot(rootH, (i + 1) % 4, times);
                     if (current == 2 && rand.nextInt(2) == 0) {
                        BlockPos var28 = this.directionToGrow(helper, i, false);
                        this.placeBlock(worldIn, var28, this.tacle);

                        while (!worldIn.func_180495_p(var28.func_177977_b()).func_185913_b() && var28.func_177977_b().func_177956_o() >= 1) {
                           if (rand.nextInt(1) == 0) {
                              var28 = this.directionToGrow(var28, i * 10, true);
                           } else {
                              var28 = this.directionToGrow(var28, i, false);
                           }

                           int llimit = var28.func_177956_o() < oY ? 5 : 2;
                           int llimitO = var28.func_177956_o() < oY ? 4 : 0;
                           var28 = this.placeColumn(worldIn, var28, rand.nextInt(llimit) + 2 + llimitO, rand, 0.0, this.tacle);
                        }

                        this.placeBlock(worldIn, var28.func_177977_b(), this.tacle);
                     }

                     if (current == loop - 3) {
                        BlockPos var30 = this.directionToGrow(helper, i, false);
                        if (worldIn.func_180495_p(var30.func_177979_c(4)).func_177230_c() == Blocks.field_150350_a) {
                           BlockPos var34 = this.directionToGrow(helper, i, false);
                           this.placeBlock(worldIn, var34, this.tacle);

                           while (!worldIn.func_180495_p(var34.func_177977_b()).func_185913_b() && var34.func_177977_b().func_177956_o() >= 1) {
                              if (rand.nextInt(1) == 0) {
                                 var34 = this.directionToGrow(var34, i * 10, true);
                              } else {
                                 var34 = this.directionToGrow(var34, i, false);
                              }

                              int llimit = var34.func_177956_o() < oY ? 5 : 2;
                              int llimitO = var34.func_177956_o() < oY ? 4 : 0;
                              var34 = this.placeColumn(worldIn, var34, rand.nextInt(llimit) + 2 + llimitO, rand, 0.0, this.tacle);
                           }

                           this.placeBlock(worldIn, var34.func_177977_b(), this.tacle);
                        }
                     }
                  } else {
                     helper = this.getDirectionRoot(rootH, (i + 3) % 4, times);
                     if (current == 5 && times != 1 && rand.nextInt(2) == 0) {
                        BlockPos trunk = this.directionToGrow(helper, i, false);
                        this.placeBlock(worldIn, trunk, this.tacle);

                        while (!worldIn.func_180495_p(trunk.func_177977_b()).func_185913_b() && trunk.func_177977_b().func_177956_o() >= 1) {
                           if (rand.nextInt(1) == 0) {
                              trunk = this.directionToGrow(trunk, i * 10 + 1, true);
                           } else {
                              trunk = this.directionToGrow(trunk, i, false);
                           }

                           int llimit = trunk.func_177956_o() < oY ? 5 : 2;
                           int llimitO = trunk.func_177956_o() < oY ? 4 : 0;
                           trunk = this.placeColumn(worldIn, trunk, rand.nextInt(llimit) + 2 + llimitO, rand, 0.0, this.tacle);
                        }

                        this.placeBlock(worldIn, trunk.func_177977_b(), this.tacle);
                     }

                     if (current == loop - 2 && times != 1) {
                        BlockPos var27 = this.directionToGrow(helper, i, false);
                        if (worldIn.func_180495_p(var27.func_177979_c(5)).func_177230_c() == Blocks.field_150350_a) {
                           BlockPos var31 = this.directionToGrow(helper, i, false);
                           this.placeBlock(worldIn, var31, this.tacle);

                           while (!worldIn.func_180495_p(var31.func_177977_b()).func_185913_b() && var31.func_177977_b().func_177956_o() >= 1) {
                              if (rand.nextInt(1) == 0) {
                                 var31 = this.directionToGrow(var31, i * 10 + 1, true);
                              } else {
                                 var31 = this.directionToGrow(var31, i, false);
                              }

                              int llimit = var31.func_177956_o() < oY ? 5 : 2;
                              int llimitO = var31.func_177956_o() < oY ? 4 : 0;
                              var31 = this.placeColumn(worldIn, var31, rand.nextInt(llimit) + 2 + llimitO, rand, 0.0, this.tacle);
                           }

                           this.placeBlock(worldIn, var31.func_177977_b(), this.tacle);
                        }
                     }
                  }

                  if (!worldIn.func_180495_p(helper.func_177977_b()).func_185913_b()) {
                     this.placeBlock(worldIn, helper.func_177977_b(), state);
                  }

                  this.placeBlock(worldIn, helper, state);
               }
            }

            helper = this.getDirectionRoot(rootH, i, 0);
            if (!worldIn.func_180495_p(helper.func_177977_b()).func_185913_b()) {
               this.placeBlock(worldIn, helper.func_177977_b(), state);
            }

            this.placeBlock(worldIn, helper, state);
         }

         current++;
      }

      return currentP;
   }

   private BlockPos placeWallsTopIn(World worldIn, BlockPos position, int loop, boolean vine, Random rand, int longer, IBlockState state) {
      BlockPos currentP = position;

      for (int current = 0; current < loop; currentP = currentP.func_177984_a()) {
         BlockPos helpRoot = currentP;

         for (int i = 0; i <= 3; i++) {
            BlockPos helper = this.getDirectionRoot(helpRoot, i, 3);
            BlockPos rootH = helper;

            for (int o = 0; o < 2; o++) {
               for (int times = 1; times <= 1; times++) {
                  if (o == 0) {
                     helper = this.getDirectionRoot(rootH, (i + 1) % 4, times + 1);
                  } else {
                     helper = this.getDirectionRoot(rootH, (i + 3) % 4, times + 1);
                  }

                  this.placeBlock(worldIn, helper, state);
                  if (vine && current == 0 && rand.nextBoolean()) {
                     this.addVines(worldIn, helper.func_177977_b(), rand, longer);
                  }
               }
            }

            helper = this.getDirectionRoot(rootH, i, 1);
            rootH = helper;

            for (int o = 0; o < 2; o++) {
               for (int times = 1; times <= 1; times++) {
                  if (o == 0) {
                     helper = this.getDirectionRoot(rootH, (i + 1) % 4, times);
                  } else {
                     helper = this.getDirectionRoot(rootH, (i + 3) % 4, times);
                  }

                  this.placeBlock(worldIn, helper, state);
                  if (vine && current == 0 && rand.nextBoolean()) {
                     this.addVines(worldIn, helper.func_177977_b(), rand, longer);
                  }
               }
            }

            helper = this.getDirectionRoot(rootH, i, 0);
            this.placeBlock(worldIn, helper, state);
            if (vine && current == 0 && rand.nextBoolean()) {
               this.addVines(worldIn, helper.func_177977_b(), rand, longer);
            }
         }

         current++;
      }

      return currentP;
   }
}
