package com.dhanantry.scapeandrunparasites.world.gen.feature;

import com.dhanantry.scapeandrunparasites.block.BlockParasiteStain;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteTrunk;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;

public class WorldGenParasiteTallFlower extends WorldGenParasiteGenAbstract {
   private IBlockState plant = SRPBlocks.ParasiteTrunk.func_176223_P().func_177226_a(BlockParasiteTrunk.VARIANT, BlockParasiteTrunk.EnumType.PLANT);
   private IBlockState petal = SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.FEELER);
   private IBlockState base = SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.FLESH);

   public WorldGenParasiteTallFlower(boolean notify) {
      super(notify);
   }

   public boolean func_180709_b(World worldIn, Random rand, BlockPos position) {
      int i = 25;
      boolean flag = true;
      if (worldIn.func_180495_p(position.func_177977_b()).func_177230_c() == Blocks.field_150350_a) {
         return false;
      } else if (position.func_177956_o() >= 1 && position.func_177956_o() + i + 1 <= worldIn.func_72800_K()) {
         for (int j = position.func_177956_o(); j <= position.func_177956_o() + 1 + i; j++) {
            int k = 2;
            MutableBlockPos blockpos$mutableblockpos = new MutableBlockPos();

            for (int l = position.func_177958_n() - k; l <= position.func_177958_n() + k && flag; l++) {
               for (int i1 = position.func_177952_p() - k; i1 <= position.func_177952_p() + k && flag; i1++) {
                  if (j < 0 || j >= worldIn.func_72800_K()) {
                     flag = false;
                  } else if (!this.isReplaceable(worldIn, blockpos$mutableblockpos.func_181079_c(l, j, i1))) {
                     flag = false;
                  }
               }
            }
         }

         if (!flag) {
            return false;
         } else {
            int lag = 0;
            BlockPos current = position;

            while (!worldIn.func_180495_p(current.func_177977_b()).func_185913_b() && current.func_177977_b().func_177956_o() >= 1) {
               current = current.func_177977_b();

               for (int yyy = 0; yyy <= lag; yyy++) {
                  for (int xs = -1; xs <= 1; xs++) {
                     for (int zs = -1; zs <= 1; zs++) {
                        this.placeBlock(worldIn, new BlockPos(current.func_177958_n() + xs, current.func_177956_o(), current.func_177952_p() + zs), this.base);
                     }
                  }
               }
            }

            current = position;
            lag = rand.nextInt(2) + 1;

            for (int yyy = 0; yyy < lag; yyy++) {
               for (int xs = -1; xs <= 1; xs++) {
                  for (int zs = -1; zs <= 1; zs++) {
                     this.placeBlock(worldIn, new BlockPos(current.func_177958_n() + xs, current.func_177956_o(), current.func_177952_p() + zs), this.base);
                  }
               }

               current = current.func_177984_a();
            }

            current = current.func_177977_b();
            int times = 0;
            int dirHelper = 0;
            current = current.func_177984_a();
            this.placeBlock(worldIn, current, this.base);

            for (int dir = 0; dir <= 3; dir++) {
               BlockPos var27 = this.getDirectionRoot(current, dir, 1);
               this.placeColumn(worldIn, var27, 2, rand, 0.0, this.base);
            }

            current = this.placeColumn(worldIn, current, 5, rand, 0.0, this.plant);
            if (rand.nextInt(8) != 0) {
               current = this.getDirectionRoot(current, rand.nextInt(4), 1);
            }

            current = this.placeColumn(worldIn, current, 5, rand, 0.0, this.plant);
            if (rand.nextInt(8) != 0) {
               current = this.getDirectionRoot(current, rand.nextInt(4), 1);
            }

            current = this.placeColumn(worldIn, current, 5, rand, 0.0, this.plant);
            if (rand.nextInt(5) != 0) {
               if (rand.nextInt(8) != 0) {
                  current = this.getDirectionRoot(current, rand.nextInt(4), 1);
               }

               current = this.placeColumn(worldIn, current, 5, rand, 0.0, this.plant);
            }

            for (int dir = 0; dir <= 3; dir++) {
               BlockPos var28 = this.getDirectionRoot(current, dir, 1);
               this.placeBlock(worldIn, var28, this.petal);
            }

            current = this.placeColumn(worldIn, current, 1, rand, 0.0, this.petal);
            int var16 = 2;

            for (int yyy = 0; yyy < var16; yyy++) {
               for (int xs = -1; xs <= 1; xs++) {
                  for (int zs = -1; zs <= 1; zs++) {
                     this.placeBlock(worldIn, new BlockPos(current.func_177958_n() + xs, current.func_177956_o(), current.func_177952_p() + zs), this.petal);
                  }
               }

               current = current.func_177984_a();
            }

            this.placeBlock(worldIn, current, this.petal);

            for (int dir = 0; dir <= 3; dir++) {
               BlockPos var29 = this.getDirectionRoot(current.func_177979_c(2), dir, 2);
               var29 = this.placeColumn(worldIn, var29, 4, rand, 0.0, this.petal);
               var29 = this.getDirectionRoot(var29, dir, 1);
               var29 = this.placeColumn(worldIn, var29, 2, rand, 0.0, this.petal);
               var29 = this.getDirectionRoot(var29, dir, 1);
               var29 = this.placeColumn(worldIn, var29, 1, rand, 0.0, this.petal);
            }

            for (int dir = 0; dir <= 3; dir++) {
               BlockPos var35 = this.getDirectionRoot(current.func_177979_c(2), dir, 2);
               var35 = this.directionToGrow(var35, (dir + 1) % 4, false);
               var35 = this.directionToGrow(var35, (dir + 1) % 4, false);
               this.placeColumn(worldIn, var35, 3, rand, 0.0, this.petal);
            }

            return true;
         }
      } else {
         return false;
      }
   }

   private void placeBlock(World worldIn, BlockPos pos, IBlockState state) {
      this.func_175903_a(worldIn, pos, state);
   }

   private BlockPos getDirectionRoot(BlockPos center, int direction, int times) {
      switch (direction) {
         case 0:
            return center.func_177964_d(times);
         case 1:
            return center.func_177965_g(times);
         case 2:
            return center.func_177970_e(times);
         default:
            return center.func_177985_f(times);
      }
   }

   private BlockPos placeColumn(World worldIn, BlockPos pos, int in, Random rand, double extraChance, IBlockState state) {
      int current = pos.func_177956_o();
      int atm = current;
      int times = 0;

      BlockPos newPos;
      for (newPos = pos; current < atm + in; times++) {
         this.placeBlock(worldIn, newPos, state);
         newPos = newPos.func_177984_a();
         current++;
      }

      return newPos;
   }

   private BlockPos directionToGrow(BlockPos atm, int choice, boolean sideCurse) {
      atm = atm.func_177984_a();
      if (sideCurse) {
         switch (choice) {
            case 0:
               atm = atm.func_177978_c();
               atm = atm.func_177974_f();
               break;
            case 1:
               atm = atm.func_177978_c();
               atm = atm.func_177976_e();
               break;
            case 10:
               atm = atm.func_177974_f();
               atm = atm.func_177978_c();
               break;
            case 11:
               atm = atm.func_177974_f();
               atm = atm.func_177968_d();
               break;
            case 20:
               atm = atm.func_177968_d();
               atm = atm.func_177974_f();
               break;
            case 21:
               atm = atm.func_177968_d();
               atm = atm.func_177976_e();
               break;
            case 30:
               atm = atm.func_177976_e();
               atm = atm.func_177978_c();
               break;
            default:
               atm = atm.func_177976_e();
               atm = atm.func_177968_d();
         }

         return atm;
      } else {
         switch (choice) {
            case 0:
               atm = atm.func_177978_c();
               break;
            case 1:
               atm = atm.func_177974_f();
               break;
            case 2:
            default:
               atm = atm.func_177968_d();
               break;
            case 3:
               atm = atm.func_177976_e();
         }

         return atm;
      }
   }
}
