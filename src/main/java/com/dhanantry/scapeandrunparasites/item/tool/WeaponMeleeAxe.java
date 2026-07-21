package com.dhanantry.scapeandrunparasites.item.tool;

import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import java.util.List;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WeaponMeleeAxe extends WeaponToolMeleeBase {
   public WeaponMeleeAxe(ToolMaterial material, String name, double attackspeed, float range, float attackD, boolean fear, byte id) {
      super(material, name, attackspeed, range, attackD, fear, id);
   }

   @Override
   public void func_77622_d(ItemStack stack, World worldIn, EntityPlayer playerIn) {
      super.func_77622_d(stack, worldIn, playerIn);
   }

   @Override
   public Item getNext() {
      return this == SRPItems.weapon_axe ? SRPItems.weapon_axeSentient : null;
   }

   @Override
   public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
      boolean flag = super.func_77644_a(stack, target, attacker);
      if (flag) {
         double chance = 0.25;
         int amp = 0;
         if (this.calling) {
            chance = 0.5;
            amp = 1;
         }

         if (attacker.field_70170_p.field_73012_v.nextDouble() < chance) {
            SRPPotions.applyStackPotion(SRPPotions.CORRO_E, target, 100, amp);
         }
      }

      return flag;
   }

   @Override
   public EnumRarity func_77613_e(ItemStack stack) {
      return super.func_77613_e(stack);
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
      super.func_77624_a(stack, worldIn, tooltip, flagIn);
      tooltip.add(TextFormatting.YELLOW + I18n.func_135052_a("tootip.srparasites.weaponm." + this.idTool, new Object[0]));
      tooltip.add(TextFormatting.RED + I18n.func_135052_a("tootip.srparasites.weaponm." + this.idTool * 10, new Object[0]));
      if (this.calling && SRPConfigSystems.useScent) {
         tooltip.add(TextFormatting.BLACK + I18n.func_135052_a("tootip.srparasites.weaponm." + this.idTool * 100, new Object[0]));
      }
   }
}
