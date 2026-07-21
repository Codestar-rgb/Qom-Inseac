package com.dhanantry.scapeandrunparasites.world.gen.feature;

import com.dhanantry.scapeandrunparasites.block.BlockParasiteTrunk;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.world.gen.WorldGenCustomStructures;
import com.dhanantry.scapeandrunparasites.world.gen.structure.WorldGenStructure;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;

public class WorldGenParasiteBall extends WorldGenParasiteGenAbstract {
   public WorldGenParasiteBall(boolean notify) {
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
            int extra = rand.nextInt(4);
            BlockPos current = position.func_177981_b(2 + extra);
            WorldGenCustomStructures.generateInPosition(new WorldGenStructure("ball"), new Random(), worldIn, current, 3, 0, 3);
            BlockPos center = new BlockPos(position.func_177958_n(), position.func_177956_o(), position.func_177952_p());
            boolean skip = true;

            for (int z = 1; z <= 4; z++) {
               if (rand.nextDouble() <= 0.5 && skip) {
                  skip = false;
               } else {
                  BlockPos root = center.func_177981_b(3 + extra);
                  root = this.getDirectionRoot(root, z, 3);
                  this.placeThin(worldIn, root);
                  root = root.func_177977_b();
                  this.placeThin(worldIn, root);
                  root = this.getDirectionRoot(root, z, 1);
                  this.placeThin(worldIn, root);

                  while (!worldIn.func_180495_p(root.func_177977_b()).func_185913_b()) {
                     root = root.func_177977_b();
                     this.placeThin(worldIn, root);
                  }

                  for (int j = 1; j <= 4; j++) {
                     BlockPos roots = this.getDirectionRoot(root, j, 1);
                     if (!worldIn.func_180495_p(roots).func_185913_b()) {
                        this.placeThin(worldIn, roots);
                     }
                  }
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   private void placeThin(World worldIn, BlockPos pos) {
      if (worldIn.func_180495_p(pos).func_177230_c() == Blocks.field_150350_a) {
         this.func_175903_a(worldIn, pos, SRPBlocks.ParasiteThin.func_176223_P());
      } else {
         this.func_175903_a(worldIn, pos, SRPBlocks.ParasiteTrunk.func_176223_P().func_177226_a(BlockParasiteTrunk.VARIANT, BlockParasiteTrunk.EnumType.TREE));
      }
   }

   private BlockPos getDirectionRoot(BlockPos center, int direction, int times) {
      switch (direction) {
         case 1:
            return center.func_177964_d(times);
         case 2:
            return center.func_177965_g(times);
         case 3:
            return center.func_177970_e(times);
         default:
            return center.func_177985_f(times);
      }
   }
}
