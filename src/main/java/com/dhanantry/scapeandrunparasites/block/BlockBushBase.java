package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import java.util.Objects;
import net.minecraft.block.BlockBush;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBushBase extends BlockBush {
   public BlockBushBase(String name, float hardness, boolean creative, boolean tickRandom) {
      this.setRegistryName(name);
      this.func_149663_c("srparasites." + name);
      this.func_149711_c(hardness);
      this.func_149675_a(tickRandom);
      if (creative) {
         this.func_149647_a(SRPMain.SRP_CREATIVETAB);
      }

      SRPBlocks.SRP_BLOCKS.add(this);
      Item itemBlock = new ItemBlock(this);
      SRPItems.SRP_ITEMS.add(itemBlock.setRegistryName(Objects.requireNonNull(this.getRegistryName())));
   }
}
