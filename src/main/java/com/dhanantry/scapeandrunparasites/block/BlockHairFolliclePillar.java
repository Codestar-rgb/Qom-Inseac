package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import java.util.Objects;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

public class BlockHairFolliclePillar extends BlockRotatedPillar {
   public BlockHairFolliclePillar(String name, Material mat) {
      super(mat);
      this.setRegistryName(name);
      this.func_149663_c("srparasites." + name);
      this.func_149647_a(SRPMain.SRP_CREATIVETAB);
      this.func_149711_c(1.0F);
      SRPBlocks.SRP_BLOCKS.add(this);
      SRPItems.SRP_ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
   }
}
