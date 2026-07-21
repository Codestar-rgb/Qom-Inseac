package com.dhanantry.scapeandrunparasites.item;

import com.dhanantry.scapeandrunparasites.client.gui.GuiDislodgementReport;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDislodgementReport extends ItemBase {
   public static final String NBT_PRINT_DAY = "PrintDay";
   public static final String NBT_PRINT_TIME = "PrintTime";
   public static final String NBT_CODE = "DislodgementCode";

   public ItemDislodgementReport(String name, int maxStack, byte id) {
      super(name, maxStack, id);
      this.func_77625_d(1);
   }

   public static NBTTagCompound getOrCreate(ItemStack stack) {
      NBTTagCompound t = stack.func_77978_p();
      if (t == null) {
         t = new NBTTagCompound();
         stack.func_77982_d(t);
      }

      return t;
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
      Minecraft.func_71410_x().func_147108_a(new GuiDislodgementReport(stack));
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
      tooltip.add(TextFormatting.GRAY + I18n.func_135052_a("tooltip.srparasites.dislodgement_report", new Object[0]));
      NBTTagCompound tag = stack.func_77978_p();
      if (tag != null && tag.func_74764_b("PrintDay")) {
         int day = tag.func_74762_e("PrintDay");
         int time = tag.func_74762_e("PrintTime");
         tooltip.add(TextFormatting.DARK_GRAY + I18n.func_135052_a("tooltip.srparasites.printed", new Object[]{day, time}));
      }
   }

   private static int safeParseInt(String s, int fallback) {
      try {
         return Integer.parseInt(s);
      } catch (Throwable var3) {
         return fallback;
      }
   }

   private static String getDislodgementEventKey(int event) {
      switch (event) {
         case 0:
            return "srparasites.dislodgement.event.0";
         case 1:
            return "srparasites.dislodgement.event.1";
         case 2:
            return "srparasites.dislodgement.event.2";
         case 3:
            return "srparasites.dislodgement.event.3";
         case 4:
            return "srparasites.dislodgement.event.4";
         case 5:
         case 23:
         case 24:
         default:
            return "srparasites.dislodgement.event.unknown";
         case 6:
            return "srparasites.dislodgement.event.6";
         case 7:
            return "srparasites.dislodgement.event.7";
         case 8:
            return "srparasites.dislodgement.event.8";
         case 9:
            return "srparasites.dislodgement.event.9";
         case 10:
            return "srparasites.dislodgement.event.10";
         case 11:
            return "srparasites.dislodgement.event.11";
         case 12:
            return "srparasites.dislodgement.event.12";
         case 13:
            return "srparasites.dislodgement.event.13";
         case 14:
            return "srparasites.dislodgement.event.14";
         case 15:
            return "srparasites.dislodgement.event.15";
         case 16:
            return "srparasites.dislodgement.event.16";
         case 17:
            return "srparasites.dislodgement.event.17";
         case 18:
            return "srparasites.dislodgement.event.18";
         case 19:
            return "srparasites.dislodgement.event.19";
         case 20:
            return "srparasites.dislodgement.event.20";
         case 21:
            return "srparasites.dislodgement.event.21";
         case 22:
            return "srparasites.dislodgement.event.22";
         case 25:
            return "srparasites.dislodgement.event.25";
      }
   }

   private static String formatDislodgementEventMeaningClient(int event, String valueStr) {
      String key = getDislodgementEventKey(event);
      switch (event) {
         case 0:
         case 11:
         case 15:
         case 16:
         case 17:
         case 18:
         case 20:
            return I18n.func_135052_a(key, new Object[0]);
         case 1:
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
         case 7:
         case 8:
         case 9:
         case 10:
         case 12:
         case 13:
         case 14:
         case 19:
         default:
            return I18n.func_135052_a(key, new Object[]{valueStr});
      }
   }
}
