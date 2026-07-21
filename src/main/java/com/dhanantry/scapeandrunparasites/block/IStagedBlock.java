package com.dhanantry.scapeandrunparasites.block;

import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;

public interface IStagedBlock {
   PropertyInteger getStageProperty();

   default IBlockState withStage(IBlockState base, int stage) {
      PropertyInteger prop = this.getStageProperty();
      return prop != null && base.func_177227_a().contains(prop) ? base.func_177226_a(prop, stage) : base;
   }
}
