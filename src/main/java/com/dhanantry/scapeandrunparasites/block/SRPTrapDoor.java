package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class SRPTrapDoor extends BlockTrapDoor {
   public SRPTrapDoor(Material material, String regName, String modid) {
      super(material);
      this.setRegistryName(regName);
      this.func_149663_c(modid + "." + regName);
      this.func_149711_c(3.0F);
      this.func_149672_a(material == Material.field_151575_d ? SoundType.field_185848_a : SoundType.field_185852_e);
      SRPBlocks.SRP_BLOCKS.add(this);
      Item item = ((Item)new ItemBlock(this).setRegistryName(this.getRegistryName())).func_77655_b(modid + "." + regName).func_77637_a(SRPMain.SRP_CREATIVETAB);
      SRPItems.SRP_ITEMS.add(item);
   }

   public SRPTrapDoor(Material material, String regName) {
      this(material, regName, "srparasites");
   }

   public SRPTrapDoor(String regName) {
      this(Material.field_151575_d, regName, "srparasites");
   }
}
