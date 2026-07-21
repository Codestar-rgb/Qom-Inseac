package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.SRPMain;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockLocs extends BlockBase {
   public static final PropertyBool SNOWY = PropertyBool.func_177716_a("snowy");

   public BlockLocs(Material material, String name, float hardness, boolean creative, boolean fullCube) {
      super(material, name, hardness, creative, fullCube);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(SNOWY, Boolean.FALSE));
      this.func_149647_a(SRPMain.SRP_CREATIVETAB);
   }

   protected BlockStateContainer func_180661_e() {
      return new BlockStateContainer(this, new IProperty[]{SNOWY});
   }

   public IBlockState func_176203_a(int meta) {
      return this.func_176223_P();
   }

   public int func_176201_c(IBlockState state) {
      return 0;
   }

   public IBlockState func_176221_a(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      IBlockState above = worldIn.func_180495_p(pos.func_177984_a());
      boolean snowy = above.func_177230_c() == Blocks.field_150431_aC || above.func_177230_c() == Blocks.field_150433_aE;
      return state.func_177226_a(SNOWY, snowy);
   }
}
