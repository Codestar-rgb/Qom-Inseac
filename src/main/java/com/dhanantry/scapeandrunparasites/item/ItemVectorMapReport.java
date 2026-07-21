package com.dhanantry.scapeandrunparasites.item;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.client.gui.GuiVectorMapReport;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemVectorMapReport extends Item {
   public static final String NBT_CX = "CenterX";
   public static final String NBT_CZ = "CenterZ";
   public static final String NBT_VX = "VectorX";
   public static final String NBT_VZ = "VectorZ";
   public static final String NBT_R = "Radius";
   public static final String NBT_DAY = "Day";
   public static final String NBT_INDEX = "Index";
   public static final String NBT_TOTAL = "Total";
   public static final String NBT_PRINT_DAY = "PrintDay";
   public static final String NBT_PRINT_TIME = "PrintTime";

   public ItemVectorMapReport(String name) {
      this.setRegistryName("srparasites", name);
      this.func_77655_b("srparasites." + name);
      this.func_77625_d(1);
      this.func_77637_a(SRPMain.SRP_CREATIVETAB);
   }

   public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
      ItemStack stack = playerIn.func_184586_b(handIn);
      if (worldIn.field_72995_K) {
         this.openGui(stack);
      }

      return new ActionResult(EnumActionResult.SUCCESS, stack);
   }

   @SideOnly(Side.CLIENT)
   private void openGui(ItemStack stack) {
      Minecraft.func_71410_x().func_147108_a(new GuiVectorMapReport(stack));
   }

   @SideOnly(Side.CLIENT)
   public void func_150895_a(CreativeTabs tab, NonNullList<ItemStack> items) {
      if (this.func_194125_a(tab)) {
         items.add(new ItemStack(this));
      }
   }

   @SideOnly(Side.CLIENT)
   public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
      tooltip.add(TextFormatting.GRAY + I18n.func_135052_a("tooltip.srparasites.vector_map", new Object[0]));
      NBTTagCompound tag = stack.func_77978_p();
      if (tag != null && tag.func_74764_b("PrintDay")) {
         int day = tag.func_74762_e("PrintDay");
         int time = tag.func_74762_e("PrintTime");
         tooltip.add(TextFormatting.DARK_GRAY + I18n.func_135052_a("tooltip.srparasites.printed", new Object[]{day, time}));
      }
   }

   public static NBTTagCompound getOrCreate(ItemStack s) {
      NBTTagCompound tag = s.func_77978_p();
      if (tag == null) {
         tag = new NBTTagCompound();
         s.func_77982_d(tag);
      }

      return tag;
   }
}
