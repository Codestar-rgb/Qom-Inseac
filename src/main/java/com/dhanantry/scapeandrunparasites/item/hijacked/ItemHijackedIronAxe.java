package com.dhanantry.scapeandrunparasites.item.hijacked;

import java.util.List;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemHijackedIronAxe extends ItemAxe {
   private final String name;
   private static final float ATTACK_DAMAGE = 7.5F;
   private static final float ATTACK_SPEED = -3.05F;

   public ItemHijackedIronAxe(String name, ToolMaterial mat) {
      super(mat, 7.5F, -3.05F);
      this.name = name;
      this.setRegistryName(name);
      this.func_77655_b("srparasites." + name);
   }

   public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
      HijackedHitEffects.apply(attacker, target);
      return super.func_77644_a(stack, target, attacker);
   }

   @SideOnly(Side.CLIENT)
   public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
      String key = "item.srparasites." + this.name + ".desc";
      String s = I18n.func_135052_a(key, new Object[0]);
      if (!s.equals(key)) {
         tooltip.add(s);
      }
   }
}
