package com.dhanantry.scapeandrunparasites.item.hijacked;

import java.util.List;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class ItemHijackedBase extends Item {
   protected final String name;

   protected ItemHijackedBase(String name) {
      this.name = name;
   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
      String key = "item.srparasites." + this.name + ".desc";
      String s = I18n.func_135052_a(key, new Object[0]);
      if (!s.equals(key)) {
         tooltip.add(s);
      }
   }
}
