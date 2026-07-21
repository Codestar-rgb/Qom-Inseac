package com.dhanantry.scapeandrunparasites.item;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import java.util.List;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBaseNoTooltip extends Item {
   protected byte version;

   public ItemBaseNoTooltip(String name, int maxStack, byte id) {
      this.setRegistryName(name);
      this.func_77655_b("srparasites." + name);
      this.func_77637_a(SRPMain.SRP_CREATIVETAB);
      this.field_77777_bU = maxStack;
      this.version = id;
   }

   @SideOnly(Side.CLIENT)
   public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
      tooltip.add(TextFormatting.WHITE + I18n.func_135052_a("tootip.srparasites.item." + this.version, new Object[0]));
   }

   public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
      if (this == SRPItems.itemAssimilate) {
         ParasiteEventEntity.convertEntity(target, target.getEntityData(), true, SRPConfigSystems.COTHVictimParasite);
      }

      return super.func_77644_a(stack, target, attacker);
   }
}
