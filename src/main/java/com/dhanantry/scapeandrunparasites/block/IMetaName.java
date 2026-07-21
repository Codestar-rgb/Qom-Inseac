package com.dhanantry.scapeandrunparasites.block;

import net.minecraft.item.ItemBlock;

public interface IMetaName {
   ItemBlock getItemBlock();

   Enum[] getVariants();
}
