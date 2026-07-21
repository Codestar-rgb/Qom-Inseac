package com.dhanantry.scapeandrunparasites.world.gen.feature;

import com.dhanantry.scapeandrunparasites.block.BlockParasiteCanister;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteTrunk;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;

public class WorldGenParasiteTree extends WorldGenParasiteTreeAbstract {
   public WorldGenParasiteTree(boolean notify) {
      super(notify);
   }

   public boolean func_180709_b(World worldIn, Random rand, BlockPos position) {
      int i = 20;
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
            int first = rand.nextInt(4);
            BlockPos var11 = this.directionToGrow(position, first, false);
            this.placeTrunk(worldIn, var11);

            while (!worldIn.func_180495_p(var11.func_177977_b()).func_185913_b() && var11.func_177977_b().func_177956_o() >= 1) {
               var11 = var11.func_177977_b();
               this.placeTrunk(worldIn, var11);
            }

            var11 = this.directionToGrow(position, ++first, false);
            this.placeTrunk(worldIn, var11);

            while (!worldIn.func_180495_p(var11.func_177977_b()).func_185913_b() && var11.func_177977_b().func_177956_o() >= 1) {
               var11 = var11.func_177977_b();
               this.placeTrunk(worldIn, var11);
            }

            var11 = position;

            while (!worldIn.func_180495_p(var11.func_177977_b()).func_185913_b() && var11.func_177977_b().func_177956_o() >= 1) {
               var11 = var11.func_177977_b();
               this.placeTrunk(worldIn, var11);
            }

            var11 = this.placeColumn(worldIn, position, rand.nextInt(3) + 5, rand, -1.0);
            first = rand.nextInt(4);
            var11 = this.directionToGrow(var11, first, false);
            var11 = this.placeColumn(worldIn, var11, rand.nextInt(3) + 5, rand, 0.3);
            if (rand.nextDouble() <= 0.3) {
               return true;
            } else {
               var11 = this.directionToGrow(var11, rand.nextInt(4), false);
               var11 = this.placeColumn(worldIn, var11, rand.nextInt(3) + 5, rand, 0.3);
               if (rand.nextDouble() <= 0.3) {
                  return true;
               } else {
                  var11 = this.directionToGrow(var11, rand.nextInt(4), false);
                  var11 = this.placeColumn(worldIn, var11, rand.nextInt(3) + 5, rand, 0.3);
                  return true;
               }
            }
         }
      } else {
         return false;
      }
   }

   private void placeTrunk(World worldIn, BlockPos pos) {
      this.func_175903_a(worldIn, pos, SRPBlocks.ParasiteTrunk.func_176223_P().func_177226_a(BlockParasiteTrunk.VARIANT, BlockParasiteTrunk.EnumType.TREE));
   }

   private void placeTrunk(World worldIn, BlockPos pos, int direction) {
      switch (direction) {
         case 1:
            this.func_175903_a(
               worldIn, pos, SRPBlocks.ParasiteTrunk.func_176223_P().func_177226_a(BlockParasiteTrunk.VARIANT, BlockParasiteTrunk.EnumType.TREE)
            );
         case 2:
         case 3:
      }
   }

   private void placeCanister(World worldIn, BlockPos pos) {
      this.func_175903_a(
         worldIn, pos, SRPBlocks.ParasiteCanister.func_176223_P().func_177226_a(BlockParasiteCanister.VARIANT, BlockParasiteCanister.EnumType.SAC)
      );
   }

   private BlockPos placeColumn(World worldIn, BlockPos pos, int in, Random rand, double extraChance) {
      int current = pos.func_177956_o();
      int atm = current;
      int times = 0;
      BlockPos newPos = pos;
      if (worldIn.func_180495_p(pos.func_177977_b()).func_177230_c() == Blocks.field_150350_a && rand.nextDouble() <= 0.3 + extraChance) {
         this.placeCanister(worldIn, pos.func_177977_b());
      }

      while (current < atm + in) {
         if (times == 2 && extraChance != -1.0) {
            int bone = rand.nextInt(4);
            int btwo = rand.nextInt(4);
            if (bone == btwo) {
               bone++;
            }

            this.addBranchs(worldIn, newPos, rand, rand.nextInt(3) + 1, bone);
            this.addBranchs(worldIn, newPos, rand, rand.nextInt(3) + 1, btwo);
         }

         this.placeTrunk(worldIn, newPos);
         newPos = newPos.func_177984_a();
         current++;
         times++;
      }

      this.placeTrunk(worldIn, newPos);
      return newPos;
   }

   private void addBranchs(World worldIn, BlockPos pos, Random rand, int size, int direction) {
      pos = this.directionToGrow(pos, direction, false);
      this.placeTrunk(worldIn, pos);
      pos = pos.func_177984_a();
      pos = this.directionToGrow(pos, direction, false);
      int current = 0;

      int curve;
      for (curve = direction * 10 + rand.nextInt(2); current < size; current++) {
         this.placeTrunk(worldIn, pos);
         if (rand.nextDouble() <= 0.25) {
            this.placeCanister(worldIn, pos.func_177977_b());
         }

         if (rand.nextDouble() <= 0.5) {
            pos = this.directionToGrow(pos, direction, false);
         } else {
            pos = this.directionToGrow(pos, curve, true);
         }
      }

      pos = pos.func_177977_b();
      this.placeTrunk(worldIn, pos);
      if (rand.nextDouble() <= 0.25) {
         this.placeCanister(worldIn, pos.func_177977_b());
      }

      if (rand.nextDouble() <= 0.5) {
         if (rand.nextDouble() <= 0.5) {
            pos = this.directionToGrow(pos.func_177977_b(), direction, false);
         } else {
            pos = this.directionToGrow(pos.func_177977_b(), curve, true);
         }

         this.placeTrunk(worldIn, pos);
         if (rand.nextDouble() <= 0.25) {
            this.placeCanister(worldIn, pos.func_177977_b());
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
               atm = atm.func_177978_c();
               break;
            case 11:
               atm = atm.func_177974_f();
               atm = atm.func_177968_d();
               break;
            case 20:
               atm = atm.func_177976_e();
               atm = atm.func_177978_c();
               break;
            case 30:
               atm = atm.func_177968_d();
               atm = atm.func_177974_f();
               break;
            case 31:
               atm = atm.func_177968_d();
               atm = atm.func_177976_e();
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
               atm = atm.func_177976_e();
               break;
            default:
               atm = atm.func_177968_d();
         }

         return atm;
      }
   }
}
