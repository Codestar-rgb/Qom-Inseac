package com.dhanantry.scapeandrunparasites.item;

import com.dhanantry.scapeandrunparasites.bestiary.client.gui.GuiPhaseReport;
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

public class ItemPhaseReport extends ItemBase {
   public static final String NBT_PRINT_DAY = "PrintDay";
   public static final String NBT_PRINT_TIME = "PrintTime";
   public static final String NBT_DIMENSION = "PhaseDimension";
   public static final String NBT_PHASE = "PhaseValue";
   public static final String NBT_TOTAL_POINTS = "PhaseTotalPoints";
   public static final String NBT_POINTS_NEXT = "PhasePointsNext";
   public static final String NBT_PROGRESS = "PhaseProgress";
   public static final String NBT_COOLDOWN = "PhaseCooldown";
   public static final String NBT_CAN_GAIN = "PhaseCanGain";
   public static final String NBT_CAN_LOSS = "PhaseCanLoss";
   public static final String NBT_MOBCAP = "PhaseMobcap";
   public static final String NBT_GENERATION = "PhaseGeneration";
   public static final String NBT_GEN_TICKS = "PhaseGenTicks";
   public static final String NBT_PARASITE_COUNT = "PhaseParasiteCount";
   public static final String NBT_COTH_COUNT = "PhaseCothCount";
   public static final String NBT_TOTAL_MOBS = "PhaseTotalMobs";

   public ItemPhaseReport(String name, int maxStack, byte id) {
      super(name, maxStack, id);
      this.func_77625_d(1);
   }

   public static NBTTagCompound getOrCreate(ItemStack stack) {
      NBTTagCompound tag = stack.func_77978_p();
      if (tag == null) {
         tag = new NBTTagCompound();
         stack.func_77982_d(tag);
      }

      return tag;
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
      Minecraft.func_71410_x().func_147108_a(new GuiPhaseReport(stack));
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
      if (tooltip != null) {
         tooltip.add(TextFormatting.GRAY + I18n.func_135052_a("tooltip.srparasites.phase_report", new Object[0]));
         NBTTagCompound tag = stack.func_77978_p();
         if (tag != null && tag.func_74764_b("PrintDay")) {
            int day = tag.func_74762_e("PrintDay");
            int time = tag.func_74762_e("PrintTime");
            tooltip.add(TextFormatting.DARK_GRAY + I18n.func_135052_a("tooltip.srparasites.printed", new Object[]{day, time}));
         }
      }
   }

   public String func_77653_i(ItemStack stack) {
      return net.minecraft.util.text.translation.I18n.func_74838_a("item.srparasites.phase_report.name");
   }
}
