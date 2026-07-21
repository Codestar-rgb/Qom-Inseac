package com.dhanantry.scapeandrunparasites.item;

import net.minecraft.item.Item;

public class ItemAdvancementIcon extends Item {
   public ItemAdvancementIcon(String name) {
      this.setRegistryName(name);
      this.func_77655_b("srparasites." + name);
      this.func_77637_a(null);
      this.field_77777_bU = 1;
   }
}
