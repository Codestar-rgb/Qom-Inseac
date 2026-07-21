package com.dhanantry.scapeandrunparasites.world.gen.feature;

import com.dhanantry.scapeandrunparasites.block.BlockParasiteStain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;

public class WorldGenParasiteTenFlower extends WorldGenParasiteGenAbstract {
   public WorldGenParasiteTenFlower(boolean notify) {
      super(notify);
   }

   public boolean func_180709_b(World worldIn, Random rand, BlockPos position) {
      int i = 12;
      boolean flag = true;
      if (worldIn.func_180495_p(position.func_177977_b()).func_177230_c() == Blocks.field_150350_a) {
         return false;
      } else if (position.func_177956_o() >= 1 && position.func_177956_o() + i + 1 <= worldIn.func_72800_K()) {
         for (int j = position.func_177956_o(); j <= position.func_177956_o() + 1 + i; j++) {
            int k = 4;
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
                        this.placeTrunk(worldIn, new BlockPos(current.func_177958_n() + xs, current.func_177956_o(), current.func_177952_p() + zs));
                     }
                  }
               }
            }

            current = position;
            lag = rand.nextInt(2) + 1;

            for (int yyy = 0; yyy <= lag; yyy++) {
               for (int xs = -1; xs <= 1; xs++) {
                  for (int zs = -1; zs <= 1; zs++) {
                     this.placeTrunk(worldIn, new BlockPos(current.func_177958_n() + xs, current.func_177956_o(), current.func_177952_p() + zs));
                  }
               }

               current = current.func_177984_a();
            }

            current = current.func_177977_b();
            int times = 0;
            int dirHelper = 0;

            for (int dir = 0; dir <= 3; dir++) {
               BlockPos var27 = this.getDirectionRoot(current, dir, 2);
               this.placeTrunk(worldIn, var27);
            }

            current = current.func_177984_a();

            for (int dir = 0; dir <= 3; dir++) {
               BlockPos var28 = current;
               times = 1;

               for (int var19 = rand.nextInt(2) + 3; times <= var19; times++) {
                  var28 = this.getDirectionRoot(var28, dir, 1);
                  this.placeTrunk(worldIn, var28);
               }

               var28 = var28.func_177984_a();
               this.placeTrunk(worldIn, var28);
               var28 = this.getDirectionRoot(var28, dir, 1);
               this.placeTrunk(worldIn, var28);
               BlockPos posHelper0 = var28;

               for (int help = 1; help <= 3; help += 2) {
                  dirHelper = (dir + help) % 4;
                  var28 = this.getDirectionRoot(posHelper0, dirHelper, 1);
                  var28 = var28.func_177984_a();
                  var28 = this.placeColumn(worldIn, var28, rand.nextInt(3) + 1, rand, 0.0);
                  int firrr = help != 1 || dir != 0 && dir != 3 ? 1 : 0;
                  if (dir == 2 && help == 3) {
                     firrr = 0;
                  }

                  if (dir == 1 && help == 3) {
                     firrr = 0;
                  }

                  var28 = this.directionToGrow(var28.func_177977_b(), dir * 10 + firrr, true);
                  var28 = this.placeColumn(worldIn, var28, rand.nextInt(3) + 1, rand, 0.0);
                  var28 = this.directionToGrow(var28, dir * 10 + firrr, true);
                  var28 = this.placeColumn(worldIn, var28.func_177977_b(), rand.nextInt(3) + 1, rand, 0.0);
               }
            }

            current = this.placeColumn(worldIn, current, rand.nextInt(4) + 4, rand, 0.0);

            for (int dir = 0; dir <= 3; dir++) {
               BlockPos var38 = this.placeColumn(worldIn, this.getDirectionRoot(current, dir, 1), rand.nextInt(3) + 2, rand, 0.0);
               int glob = 3;
               var38 = var38.func_177979_c(2);

               for (int kkk = 0; kkk <= glob; kkk++) {
                  if (kkk == 0) {
                     this.placeColumn(worldIn, this.getDirectionRoot(var38, dir, 1), 1, rand, 0.0);
                     var38 = var38.func_177984_a();
                  }

                  var38 = this.getDirectionRoot(var38, dir, kkk == 0 ? 2 : 1);
                  var38 = this.placeColumn(worldIn, var38, rand.nextInt(4) + 1, rand, 0.0);
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   private void placeTrunk(World worldIn, BlockPos pos) {
      this.func_175903_a(worldIn, pos, SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.FEELER));
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

   private BlockPos placeColumn(World worldIn, BlockPos pos, int in, Random rand, double extraChance) {
      int current = pos.func_177956_o();
      int atm = current;
      int times = 0;

      BlockPos newPos;
      for (newPos = pos; current < atm + in; times++) {
         this.placeTrunk(worldIn, newPos);
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
