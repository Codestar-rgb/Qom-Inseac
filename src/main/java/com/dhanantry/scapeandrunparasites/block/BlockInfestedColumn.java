package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

public class BlockInfestedColumn extends BlockRotatedPillar {
   public BlockInfestedColumn(String name) {
      super(Material.field_151576_e);
      this.setRegistryName(name);
      this.func_149663_c("srparasites." + name);
      this.func_149711_c(1.5F);
      this.func_149752_b(10.0F);
      this.func_149672_a(SoundType.field_185851_d);
      this.func_149647_a(SRPMain.SRP_CREATIVETAB);
      SRPBlocks.SRP_BLOCKS.add(this);
      SRPItems.SRP_ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
   }
}
