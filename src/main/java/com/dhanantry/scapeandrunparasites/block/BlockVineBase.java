package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import java.util.Objects;
import net.minecraft.block.BlockVine;
import net.minecraft.item.ItemBlock;

public class BlockVineBase extends BlockVine {
   public BlockVineBase(String name, float hardness, boolean creative, boolean tickRandom) {
      this.setRegistryName(name);
      this.func_149663_c("srparasites." + name);
      this.func_149711_c(hardness);
      this.func_149675_a(tickRandom);
      if (creative) {
         this.func_149647_a(SRPMain.SRP_CREATIVETAB);
      }

      SRPBlocks.SRP_BLOCKS.add(this);
      SRPItems.SRP_ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
   }
}
