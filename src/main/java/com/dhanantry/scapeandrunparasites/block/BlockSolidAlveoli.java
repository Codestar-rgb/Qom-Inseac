package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.init.SRPSoundTypes;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSolidAlveoli extends BlockBase {
   public BlockSolidAlveoli() {
      super(Material.field_151571_B, "solid_alveoli_block", 1.0F, true, true);
      this.func_149713_g(0);
      this.func_149672_a(SRPSoundTypes.FLESH);
   }

   public boolean func_149662_c(IBlockState s) {
      return false;
   }

   public boolean func_149686_d(IBlockState s) {
      return false;
   }

   public boolean doesSideBlockRendering(IBlockState s, IBlockAccess w, BlockPos p, EnumFacing f) {
      return false;
   }

   @SideOnly(Side.CLIENT)
   public BlockRenderLayer func_180664_k() {
      return BlockRenderLayer.TRANSLUCENT;
   }
}
