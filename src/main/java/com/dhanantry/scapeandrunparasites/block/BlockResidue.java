package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPSoundTypes;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockResidue extends BlockBase {
   public BlockResidue(String name) {
      super(Material.field_151576_e, name, 1.5F, true, true, 10.0F);
      this.func_149672_a(SRPSoundTypes.VOMIT);
   }

   public boolean func_149686_d(IBlockState state) {
      return true;
   }

   public boolean func_149662_c(IBlockState state) {
      return true;
   }

   @SideOnly(Side.CLIENT)
   public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
      return layer == BlockRenderLayer.SOLID;
   }

   private boolean tooManyNeighborPlants(World world, BlockPos pos) {
      int count = 0;

      for (EnumFacing f : EnumFacing.values()) {
         if (world.func_180495_p(pos.func_177972_a(f)).func_177230_c() == SRPBlocks.ResiduePlants) {
            if (++count >= 2) {
               return true;
            }
         }
      }

      return false;
   }

   public void func_180650_b(World world, BlockPos pos, IBlockState state, Random rand) {
      if (!world.field_72995_K) {
         if (rand.nextInt(16) == 0) {
            for (int tries = 0; tries < 3; tries++) {
               EnumFacing face = EnumFacing.values()[rand.nextInt(6)];
               BlockPos plantPos = pos.func_177972_a(face);
               if (world.func_175623_d(plantPos)
                  && !this.tooManyNeighborPlants(world, plantPos)
                  && SRPBlocks.ResiduePlants.func_176198_a(world, plantPos, face)) {
                  IBlockState plantState = SRPBlocks.ResiduePlants.func_176223_P().func_177226_a(BlockResiduePlants.FACING, face);
                  world.func_180501_a(plantPos, plantState, 2);
                  break;
               }
            }
         }
      }
   }

   public void func_176199_a(World world, BlockPos pos, Entity entity) {
      if (entity instanceof EntityPlayer && entity.field_70122_E) {
         entity.field_70159_w *= 0.5;
         entity.field_70179_y *= 0.5;
      }

      super.func_176199_a(world, pos, entity);
   }
}
