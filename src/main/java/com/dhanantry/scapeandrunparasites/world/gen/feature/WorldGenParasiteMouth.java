package com.dhanantry.scapeandrunparasites.world.gen.feature;

import com.dhanantry.scapeandrunparasites.block.BlockParasiteStain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import java.util.Random;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStairs.EnumHalf;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldGenParasiteMouth extends WorldGenParasiteGenAbstract {
   public WorldGenParasiteMouth(boolean notify) {
      super(notify);
   }

   public boolean func_180709_b(World worldIn, Random rand, BlockPos position) {
      this.genMouth(worldIn, rand, position);
      int rana = rand.nextInt(7) + 1;
      if (rand.nextBoolean()) {
         rana *= -1;
      }

      int x = rana + position.func_177958_n();
      int z = position.func_177952_p();
      if (x == 7) {
         rana = rand.nextInt(7) + 1;
         if (rand.nextBoolean()) {
            rana *= -1;
         }

         z += rana;
      } else {
         int var9 = 7;
         if (rand.nextBoolean()) {
            var9 *= -1;
         }

         z += var9;
      }

      BlockPos newp = new BlockPos(x, position.func_177956_o(), z);
      newp = ParasiteEventEntity.getFloor(worldIn, newp, 5);
      if (newp != null) {
         this.genMouth(worldIn, rand, newp);
      }

      return true;
   }

   private void genMouth(World worldIn, Random rand, BlockPos position) {
      for (int yyy = 0; yyy <= 2; yyy++) {
         for (int xs = -2; xs <= 2; xs++) {
            for (int zs = -2; zs <= 2; zs++) {
               if (worldIn.func_180495_p(new BlockPos(position.func_177958_n() + xs, position.func_177956_o() + yyy, position.func_177952_p() + zs))
                     .func_177230_c()
                  != Blocks.field_150350_a) {
                  return;
               }
            }
         }
      }

      this.placePeri(worldIn, position, 1, 2);
      this.placePeri(worldIn, position, 2, 1);
      BlockPos current = position;
      int i = 7;

      while ((!worldIn.func_180495_p(current.func_177977_b()).func_185913_b() || i >= 0) && current.func_177977_b().func_177956_o() > 2) {
         i--;
         this.func_175903_a(worldIn, current, Blocks.field_150350_a.func_176223_P());
         current = current.func_177977_b();
         this.placePeri(worldIn, current, 5, 1);
      }

      this.placeFlesh(worldIn, current);
   }

   private void placeMouth(World worldIn, BlockPos pos) {
      this.func_175903_a(worldIn, pos, SRPBlocks.ParasiteMouth.func_176223_P());
   }

   private void placeFlesh(World worldIn, BlockPos pos) {
      this.func_175903_a(worldIn, pos, SRPBlocks.ParasiteStain.func_176223_P().func_177226_a(BlockParasiteStain.VARIANT, BlockParasiteStain.EnumType.FLESH));
   }

   private void placeFleshStair(World worldIn, BlockPos pos, int direction, boolean bottom, int times) {
      switch (direction) {
         case 0:
            if (bottom) {
               this.func_175903_a(
                  worldIn,
                  pos.func_177964_d(times),
                  SRPBlocks.ParasiteStainFleshStair
                     .func_176223_P()
                     .func_177226_a(BlockStairs.field_176309_a, EnumFacing.SOUTH)
                     .func_177226_a(BlockStairs.field_176308_b, EnumHalf.BOTTOM)
               );
               this.placeColumn(worldIn, pos.func_177964_d(times).func_177977_b(), 2, null, 0.0);
            } else {
               this.func_175903_a(
                  worldIn,
                  pos.func_177964_d(times),
                  SRPBlocks.ParasiteStainFleshStair
                     .func_176223_P()
                     .func_177226_a(BlockStairs.field_176309_a, EnumFacing.SOUTH)
                     .func_177226_a(BlockStairs.field_176308_b, EnumHalf.TOP)
               );
            }
            break;
         case 1:
            if (bottom) {
               this.func_175903_a(
                  worldIn,
                  pos.func_177965_g(times),
                  SRPBlocks.ParasiteStainFleshStair
                     .func_176223_P()
                     .func_177226_a(BlockStairs.field_176309_a, EnumFacing.WEST)
                     .func_177226_a(BlockStairs.field_176308_b, EnumHalf.BOTTOM)
               );
               this.placeColumn(worldIn, pos.func_177965_g(times).func_177977_b(), 2, null, 0.0);
            } else {
               this.func_175903_a(
                  worldIn,
                  pos.func_177965_g(times),
                  SRPBlocks.ParasiteStainFleshStair
                     .func_176223_P()
                     .func_177226_a(BlockStairs.field_176309_a, EnumFacing.WEST)
                     .func_177226_a(BlockStairs.field_176308_b, EnumHalf.TOP)
               );
            }
            break;
         case 2:
         default:
            if (bottom) {
               this.func_175903_a(
                  worldIn,
                  pos.func_177970_e(times),
                  SRPBlocks.ParasiteStainFleshStair
                     .func_176223_P()
                     .func_177226_a(BlockStairs.field_176309_a, EnumFacing.NORTH)
                     .func_177226_a(BlockStairs.field_176308_b, EnumHalf.BOTTOM)
               );
               this.placeColumn(worldIn, pos.func_177970_e(times).func_177977_b(), 2, null, 0.0);
            } else {
               this.func_175903_a(
                  worldIn,
                  pos.func_177970_e(times),
                  SRPBlocks.ParasiteStainFleshStair
                     .func_176223_P()
                     .func_177226_a(BlockStairs.field_176309_a, EnumFacing.NORTH)
                     .func_177226_a(BlockStairs.field_176308_b, EnumHalf.TOP)
               );
            }
            break;
         case 3:
            if (bottom) {
               this.func_175903_a(
                  worldIn,
                  pos.func_177985_f(times),
                  SRPBlocks.ParasiteStainFleshStair
                     .func_176223_P()
                     .func_177226_a(BlockStairs.field_176309_a, EnumFacing.EAST)
                     .func_177226_a(BlockStairs.field_176308_b, EnumHalf.BOTTOM)
               );
               this.placeColumn(worldIn, pos.func_177985_f(times).func_177977_b(), 2, null, 0.0);
            } else {
               this.func_175903_a(
                  worldIn,
                  pos.func_177985_f(times),
                  SRPBlocks.ParasiteStainFleshStair
                     .func_176223_P()
                     .func_177226_a(BlockStairs.field_176309_a, EnumFacing.EAST)
                     .func_177226_a(BlockStairs.field_176308_b, EnumHalf.TOP)
               );
            }
      }
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
      int currentY = pos.func_177956_o();
      int atm = current;
      int times = 0;

      BlockPos newPos;
      for (newPos = pos; current < atm + in && currentY > 2; times++) {
         currentY--;
         this.placeFlesh(worldIn, newPos);
         newPos = newPos.func_177977_b();
         current++;
      }

      return newPos;
   }

   private void placePeri(World worldIn, BlockPos position, int type, int area) {
      for (int i = 0; i <= 3; i++) {
         BlockPos helper = this.getDirectionRoot(position, i, area);
         this.placeColumn(worldIn, helper.func_177977_b(), 2, null, 0.0);
         if (type == 5) {
            this.placeFlesh(worldIn, helper);
         } else if (type == 1) {
            this.placeFleshStair(worldIn, helper, i, true, 0);
         } else {
            this.placeMouth(worldIn, helper);
         }

         BlockPos rootH = helper;

         for (int o = 0; o < 2; o++) {
            if (o == 0) {
               helper = this.directionToGrow(rootH, (i + 1) % 4, false);
            } else {
               helper = this.directionToGrow(rootH, (i + 3) % 4, false);
            }

            this.placeColumn(worldIn, helper.func_177977_b(), 2, null, 0.0);
            if (type == 5) {
               this.placeFlesh(worldIn, helper);
            } else if (type == 1) {
               this.placeFleshStair(worldIn, helper, i, true, 0);
            } else {
               this.placeMouth(worldIn, helper);
            }
         }
      }
   }

   private BlockPos directionToGrow(BlockPos atm, int choice, boolean sideCurse) {
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
               atm = atm.func_177968_d();
               break;
            case 11:
               atm = atm.func_177974_f();
               atm = atm.func_177978_c();
               break;
            case 20:
               atm = atm.func_177968_d();
               atm = atm.func_177976_e();
               break;
            case 21:
               atm = atm.func_177968_d();
               atm = atm.func_177974_f();
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

   private void positionSides(int choice) {
   }
}
