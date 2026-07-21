package com.dhanantry.scapeandrunparasites.world.gen.feature;

import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public abstract class WorldGenParasiteTreeAbstract extends WorldGenAbstractTree {
   public WorldGenParasiteTreeAbstract(boolean notify) {
      super(notify);
   }

   protected boolean func_150523_a(Block blockType) {
      Material material = blockType.func_176223_P().func_185904_a();
      return material == Material.field_151579_a
         || material == Material.field_151584_j
         || blockType == Blocks.field_150349_c
         || blockType == Blocks.field_150346_d
         || blockType == Blocks.field_150364_r
         || blockType == Blocks.field_150363_s
         || blockType == Blocks.field_150345_g
         || blockType == Blocks.field_150395_bd
         || blockType == SRPBlocks.ParasiteBush;
   }

   protected void func_175921_a(World worldIn, BlockPos pos) {
      if (worldIn.func_180495_p(pos).func_177230_c() != Blocks.field_150346_d) {
         this.func_175903_a(worldIn, pos, SRPBlocks.ParasiteStain.func_176223_P());
      }
   }

   public boolean isReplaceable(World world, BlockPos pos) {
      IBlockState state = world.func_180495_p(pos);
      return state.func_177230_c().isAir(state, world, pos)
         || state.func_177230_c().isLeaves(state, world, pos)
         || state.func_177230_c().isWood(world, pos)
         || this.func_150523_a(state.func_177230_c())
         || state.func_177230_c() == SRPBlocks.ParasiteBush;
   }
}
