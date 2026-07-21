package com.dhanantry.scapeandrunparasites.util.convert;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.BlockDoublePlant.EnumBlockHalf;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

public final class HarlequinBlockConverter {
   public static Block convert(World world, BlockPos pos, IBlockState state, boolean blotchHere, Random rand, HarlequinBlockConverter.Config cfg) {
      Block b = state.func_177230_c();
      Material mat = state.func_185904_a();
      if (mat == Material.field_151579_a || mat == Material.field_151586_h || mat == Material.field_151587_i) {
         return null;
      } else if (b instanceof BlockLeaves) {
         world.func_180501_a(pos, cfg.ALVEOLI.func_176223_P(), 2);
         if (rand.nextInt(100) < 30) {
            BlockPos below = pos.func_177977_b();
            if (world.func_175623_d(below)) {
               IBlockState growth = setFacingIfPresent(cfg.ALVEOLI_GROWTH.func_176223_P(), EnumFacing.DOWN);
               world.func_180501_a(below, growth, 2);
            }
         }

         return cfg.ALVEOLI;
      } else if (b == Blocks.field_150344_f || b == Blocks.field_150347_e || b == Blocks.field_150341_Y) {
         world.func_180501_a(pos, cfg.HARLESKINN.func_176223_P(), 2);
         return cfg.HARLESKINN;
      } else if (b == Blocks.field_150349_c) {
         if (!blotchHere) {
            world.func_180501_a(pos, cfg.HARLEQUINN_GRASS.func_176223_P(), 2);
            maybePlaceHair(world, pos, rand, cfg);
            return cfg.HARLEQUINN_GRASS;
         } else {
            world.func_180501_a(pos, cfg.HARLESKINN.func_176223_P(), 2);
            BlockPos up = pos.func_177984_a();
            Block above = world.func_180495_p(up).func_177230_c();
            if (above == cfg.TRESSES || above == cfg.HIRSUTE) {
               world.func_175698_g(up);
            }

            return cfg.HARLESKINN;
         }
      } else if (!isTopSunlit(world, pos) || !isAnySand(state) && !isAnySandstone(state) && b != Blocks.field_150348_b) {
         if (!isAnySand(state) && !isAnySandstone(state) && b != Blocks.field_150348_b) {
            return null;
         } else {
            world.func_180501_a(pos, cfg.HARLESKINN.func_176223_P(), 2);
            return cfg.HARLESKINN;
         }
      } else if (blotchHere) {
         world.func_180501_a(pos, cfg.HARLESKINN.func_176223_P(), 2);
         return cfg.HARLESKINN;
      } else {
         world.func_180501_a(pos, cfg.HARLEQUINN_GRASS.func_176223_P(), 2);
         maybePlaceHair(world, pos, rand, cfg);
         return cfg.HARLEQUINN_GRASS;
      }
   }

   private static void maybePlaceHair(World world, BlockPos pos, Random rand, HarlequinBlockConverter.Config cfg) {
      BlockPos up = pos.func_177984_a();
      if (world.func_175623_d(up) && isTopSunlit(world, pos)) {
         int roll = rand.nextInt(100);
         boolean canTresses = canSustain(world, cfg.HARLEQUINN_GRASS.func_176223_P(), pos, cfg.TRESSES);
         boolean canHirsute = canSustain(world, cfg.HARLEQUINN_GRASS.func_176223_P(), pos, cfg.HIRSUTE);
         if (roll < 60 && canTresses && world.func_175623_d(up.func_177984_a())) {
            placeDoublePlant(world, up, cfg.TRESSES);
         } else if (roll < 80 && canHirsute) {
            world.func_180501_a(up, cfg.HIRSUTE.func_176223_P(), 2);
         }
      }
   }

   private static boolean isTopSunlit(World world, BlockPos pos) {
      return world.func_175678_i(pos.func_177984_a());
   }

   private static boolean isAnySand(IBlockState s) {
      return s.func_177230_c() instanceof BlockSand;
   }

   private static boolean isAnySandstone(IBlockState s) {
      return s.func_177230_c() instanceof BlockSandStone || s.func_177230_c() == Blocks.field_180395_cM;
   }

   private static boolean canSustain(World world, IBlockState ground, BlockPos pos, Block plant) {
      if (!(plant instanceof IPlantable)) {
         return true;
      } else {
         try {
            return ground.func_177230_c().canSustainPlant(ground, world, pos, EnumFacing.UP, (IPlantable)plant);
         } catch (Throwable var5) {
            return true;
         }
      }
   }

   private static IBlockState setFacingIfPresent(IBlockState state, EnumFacing face) {
      for (IProperty<?> p : state.func_177227_a()) {
         if (p.func_177701_a().equalsIgnoreCase("facing") && p.func_177699_b() == EnumFacing.class && p.func_177700_c().contains(face)) {
            return state.func_177226_a(p, face);
         }
      }

      return state;
   }

   private static void placeDoublePlant(World world, BlockPos pos, Block plant) {
      try {
         if (plant instanceof BlockDoublePlant) {
            BlockDoublePlant bdp = (BlockDoublePlant)plant;
            world.func_180501_a(pos, bdp.func_176223_P().func_177226_a(BlockDoublePlant.field_176492_b, EnumBlockHalf.LOWER), 2);
            world.func_180501_a(pos.func_177984_a(), bdp.func_176223_P().func_177226_a(BlockDoublePlant.field_176492_b, EnumBlockHalf.UPPER), 2);
         } else {
            world.func_180501_a(pos, plant.func_176223_P(), 2);
         }
      } catch (Throwable var4) {
         world.func_180501_a(pos, plant.func_176223_P(), 2);
      }
   }

   private HarlequinBlockConverter() {
   }

   public static final class Config {
      public final Block HARLESKINN;
      public final Block HARLEQUINN_GRASS;
      public final Block ALVEOLI;
      public final Block ALVEOLI_GROWTH;
      public final Block LIPOMA;
      public final Block TRESSES;
      public final Block HIRSUTE;

      public Config(Block harleskinn, Block harlequinnGrass, Block alveoli, Block alveoliGrowth, Block lipoma, Block tresses, Block hirsute) {
         this.HARLESKINN = harleskinn;
         this.HARLEQUINN_GRASS = harlequinnGrass;
         this.ALVEOLI = alveoli;
         this.ALVEOLI_GROWTH = alveoliGrowth;
         this.LIPOMA = lipoma;
         this.TRESSES = tresses;
         this.HIRSUTE = hirsute;
      }
   }
}
