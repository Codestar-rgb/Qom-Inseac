package com.dhanantry.scapeandrunparasites.item;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTheSignCharm extends ItemBase {
   public ItemTheSignCharm(String name) {
      super(name, 1, (byte)0);
      this.func_77625_d(1);
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void func_77624_a(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
      tooltip.add(TextFormatting.RED + I18n.func_135052_a("tooltip.srparasites.the_sign_charm.red", new Object[0]));
      tooltip.add(TextFormatting.WHITE + I18n.func_135052_a("tooltip.srparasites.the_sign_charm.white", new Object[0]));
      tooltip.add(TextFormatting.GRAY.toString() + TextFormatting.ITALIC + I18n.func_135052_a("tooltip.srparasites.the_sign_charm.gray", new Object[0]));
   }
}
