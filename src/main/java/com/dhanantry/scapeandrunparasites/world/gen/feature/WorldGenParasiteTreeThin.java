package com.dhanantry.scapeandrunparasites.world.gen.feature;

import com.dhanantry.scapeandrunparasites.block.BlockParasiteCanister;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteTrunk;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;

public class WorldGenParasiteTreeThin extends WorldGenParasiteTreeAbstract {
   public WorldGenParasiteTreeThin(boolean notify) {
      super(notify);
   }

   public boolean func_180709_b(World worldIn, Random rand, BlockPos position) {
      int i = 16;
      boolean flag = true;
      if (worldIn.func_180495_p(position.func_177977_b()).func_177230_c() == Blocks.field_150350_a) {
         return false;
      } else if (position.func_177956_o() >= 1 && position.func_177956_o() + i + 1 <= worldIn.func_72800_K()) {
         for (int j = position.func_177956_o(); j <= position.func_177956_o() + 1 + i; j++) {
            int k = 1;
            if (j == position.func_177956_o()) {
               k = 0;
            }

            if (j >= position.func_177956_o() + 1 + i - 2) {
               k = 2;
            }

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
            BlockPos current = position;

            while (!worldIn.func_180495_p(current.func_177977_b()).func_185913_b() && current.func_177977_b().func_177956_o() >= 1) {
               current = current.func_177977_b();
               this.placeTrunk(worldIn, current);
            }

            current = this.placeColumn(worldIn, position, 3, rand, 0.0, 4);
            this.grow(worldIn, current.func_177978_c(), rand, 1);
            this.grow(worldIn, current.func_177974_f(), rand, 2);
            this.grow(worldIn, current.func_177968_d(), rand, 3);
            this.grow(worldIn, current.func_177976_e(), rand, 4);
            return true;
         }
      } else {
         return false;
      }
   }

   private void placeThin(World worldIn, BlockPos pos) {
      this.func_175903_a(worldIn, pos, SRPBlocks.ParasiteThin.func_176223_P());
   }

   private void placeCanister(World worldIn, BlockPos pos) {
      this.func_175903_a(
         worldIn, pos, SRPBlocks.ParasiteCanister.func_176223_P().func_177226_a(BlockParasiteCanister.VARIANT, BlockParasiteCanister.EnumType.LUMP)
      );
   }

   private void placeTrunk(World worldIn, BlockPos pos) {
      this.func_175903_a(worldIn, pos, SRPBlocks.ParasiteTrunk.func_176223_P().func_177226_a(BlockParasiteTrunk.VARIANT, BlockParasiteTrunk.EnumType.TREE));
   }

   private BlockPos placeColumn(World worldIn, BlockPos pos, int times, Random rand, double extraChance, int minimum) {
      int current = pos.func_177956_o();
      int atm = current;
      BlockPos newPos = pos;
      times = rand.nextInt(times) + minimum;
      if (worldIn.func_180495_p(pos.func_177977_b()).func_177230_c() == Blocks.field_150350_a && rand.nextDouble() <= extraChance) {
         this.placeCanister(worldIn, pos.func_177977_b());
      }

      while (current < atm + times && minimum != 1) {
         this.placeThin(worldIn, newPos);
         newPos = newPos.func_177984_a();
         current++;
      }

      this.placeThin(worldIn, newPos);
      return newPos;
   }

   private void grow(World worldIn, BlockPos pos, Random rand, int direction) {
      pos = this.placeColumn(worldIn, pos, 2, rand, 0.3, 2);
      if (rand.nextInt(3) == 0) {
         pos = this.directionToGrow(pos, direction);
         pos = this.placeColumn(worldIn, pos, 3, rand, 0.5, 2);
         if (rand.nextInt(5) == 0) {
            pos = this.directionToGrow(pos, direction);
            pos = this.placeColumn(worldIn, pos, 3, rand, 0.7, 2);
         } else {
            pos = this.directionToGrow(pos, direction);
            pos = this.placeColumn(worldIn, pos, 1, rand, 0.7, 1);
         }
      } else {
         pos = this.directionToGrow(pos, direction);
         pos = this.placeColumn(worldIn, pos, 1, rand, 0.5, 1);
      }
   }

   private BlockPos directionToGrow(BlockPos pos, int choice) {
      switch (choice) {
         case 1:
            return pos.func_177978_c();
         case 2:
            return pos.func_177974_f();
         case 3:
            return pos.func_177968_d();
         default:
            return pos.func_177976_e();
      }
   }
}
