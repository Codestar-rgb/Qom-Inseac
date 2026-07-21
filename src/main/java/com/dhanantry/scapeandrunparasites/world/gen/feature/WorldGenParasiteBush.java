package com.dhanantry.scapeandrunparasites.world.gen.feature;

import com.dhanantry.scapeandrunparasites.block.BlockInfestedBush;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteBush;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldGenParasiteBush extends WorldGenParasiteGenAbstract {
   private final IBlockState tallGrassState;
   private int type;
   private boolean parasite;

   public WorldGenParasiteBush(boolean notify, BlockParasiteBush.EnumType variant, int type) {
      super(notify);
      this.tallGrassState = SRPBlocks.ParasiteBush.func_176223_P().func_177226_a(BlockParasiteBush.VARIANT, variant);
      this.type = type;
      this.parasite = true;
   }

   public WorldGenParasiteBush(boolean notify, BlockInfestedBush.EnumType variant, int type) {
      super(notify);
      this.tallGrassState = SRPBlocks.InfestedBush.func_176223_P().func_177226_a(BlockInfestedBush.VARIANT, variant);
      this.type = type;
      this.parasite = false;
   }

   public boolean func_180709_b(World worldIn, Random rand, BlockPos position) {
      if (this.type == 3) {
         this.flowerGen(worldIn, rand, position);
         return true;
      } else if (this.type == 4) {
         for (int i = 0; i < 128; i++) {
            BlockPos blockpos = position.func_177982_a(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
            if (this.parasite) {
               if (worldIn.func_180495_p(blockpos).func_177230_c() == Blocks.field_150350_a
                  && SRPBlocks.ParasiteBush.func_180671_f(worldIn, blockpos, this.tallGrassState)
                  && this.checkFloor(worldIn, blockpos)) {
                  this.VinGen(worldIn, rand, blockpos);
               }
            } else if (worldIn.func_180495_p(blockpos).func_177230_c() == Blocks.field_150350_a
               && SRPBlocks.InfestedBush.func_180671_f(worldIn, blockpos, this.tallGrassState)
               && this.checkFloor(worldIn, blockpos)) {
               this.VinGen(worldIn, rand, blockpos);
            }
         }

         this.VinGen(worldIn, rand, position);
         return true;
      } else {
         for (IBlockState iblockstate = worldIn.func_180495_p(position);
            (iblockstate.func_177230_c() == Blocks.field_150350_a || iblockstate.func_177230_c().isLeaves(iblockstate, worldIn, position))
               && position.func_177956_o() > 0;
            iblockstate = worldIn.func_180495_p(position)
         ) {
            position = position.func_177977_b();
         }

         switch (this.type) {
            case 1:
               for (int ixx = 0; ixx < 128; ixx++) {
                  BlockPos blockpos = position.func_177982_a(
                     rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8)
                  );
                  if (this.parasite) {
                     if (worldIn.func_180495_p(blockpos).func_177230_c() == Blocks.field_150350_a
                        && SRPBlocks.ParasiteBush.func_180671_f(worldIn, blockpos, this.tallGrassState)
                        && this.checkFloor(worldIn, blockpos)) {
                        worldIn.func_180501_a(blockpos, this.tallGrassState, 2);
                     }
                  } else if (worldIn.func_180495_p(blockpos).func_177230_c() == Blocks.field_150350_a
                     && SRPBlocks.InfestedBush.func_180671_f(worldIn, blockpos, this.tallGrassState)
                     && this.checkFloor(worldIn, blockpos)) {
                     worldIn.func_180501_a(blockpos, this.tallGrassState, 2);
                  }
               }
               break;
            case 2:
               for (int ix = 0; ix < 4; ix++) {
                  BlockPos blockpos = position.func_177982_a(
                     rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8)
                  );
                  if (this.parasite) {
                     if (worldIn.func_180495_p(blockpos).func_177230_c() == Blocks.field_150350_a
                        && SRPBlocks.ParasiteBush.func_180671_f(worldIn, blockpos, this.tallGrassState)
                        && this.checkFloor(worldIn, blockpos)) {
                        worldIn.func_180501_a(blockpos, this.tallGrassState, 2);
                     }
                  } else if (worldIn.func_180495_p(blockpos).func_177230_c() == Blocks.field_150350_a
                     && SRPBlocks.InfestedBush.func_180671_f(worldIn, blockpos, this.tallGrassState)
                     && this.checkFloor(worldIn, blockpos)) {
                     worldIn.func_180501_a(blockpos, this.tallGrassState, 2);
                  }
               }
         }

         return true;
      }
   }

   public boolean flowerGen(World worldIn, Random rand, BlockPos position) {
      for (int i = 0; i < 64; i++) {
         BlockPos blockpos = position.func_177982_a(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
         if (this.parasite) {
            if (worldIn.func_180495_p(blockpos).func_177230_c() == Blocks.field_150350_a
               && blockpos.func_177956_o() < 255
               && SRPBlocks.ParasiteBush.func_180671_f(worldIn, blockpos, this.tallGrassState)
               && this.checkFloor(worldIn, blockpos)) {
               worldIn.func_180501_a(blockpos, this.tallGrassState, 2);
            }
         } else if (worldIn.func_180495_p(blockpos).func_177230_c() == Blocks.field_150350_a
            && blockpos.func_177956_o() < 255
            && SRPBlocks.InfestedBush.func_180671_f(worldIn, blockpos, this.tallGrassState)
            && this.checkFloor(worldIn, blockpos)) {
            worldIn.func_180501_a(blockpos, this.tallGrassState, 2);
         }
      }

      return true;
   }

   public boolean VinGen(World worldIn, Random rand, BlockPos position) {
      for (int i = 0; i < 10; i++) {
         BlockPos blockpos = position.func_177982_a(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
         if (worldIn.func_180495_p(blockpos).func_177230_c() == Blocks.field_150350_a) {
            int j = 1 + rand.nextInt(rand.nextInt(3) + 3);

            for (int k = 0; k < j; k++) {
               if (this.parasite) {
                  if (SRPBlocks.ParasiteBush.func_180671_f(worldIn, blockpos.func_177981_b(k), this.tallGrassState)
                     && worldIn.func_180495_p(blockpos.func_177981_b(k)).func_177230_c() == Blocks.field_150350_a) {
                     worldIn.func_180501_a(blockpos.func_177981_b(k), this.tallGrassState, 2);
                  }
               } else if (SRPBlocks.InfestedBush.func_180671_f(worldIn, blockpos, this.tallGrassState)
                  && worldIn.func_180495_p(blockpos.func_177981_b(k)).func_177230_c() == Blocks.field_150350_a) {
                  worldIn.func_180501_a(blockpos.func_177981_b(k), this.tallGrassState, 2);
               }
            }
         }
      }

      return true;
   }

   private boolean checkFloor(World worldIn, BlockPos position) {
      return worldIn.func_180495_p(position.func_177977_b()).func_185917_h();
   }
}
