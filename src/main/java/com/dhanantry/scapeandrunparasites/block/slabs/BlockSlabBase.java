package com.dhanantry.scapeandrunparasites.block.slabs;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.block.IMetaName;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;

public abstract class BlockSlabBase extends BlockSlab implements IMetaName {
   public BlockSlabBase(Material materialIn, String name, float hardness, boolean creative, boolean tickRandom, BlockSlab half) {
      super(materialIn);
      this.setRegistryName(name);
      this.func_149663_c("srparasites." + name);
      this.func_149711_c(hardness);
      if (creative) {
         this.func_149647_a(SRPMain.SRP_CREATIVETAB);
      }

      this.field_149783_u = !this.func_176552_j();
   }

   public abstract BlockSlab getHalfBlock();

   public abstract BlockSlab getDoubleBlock();
}
