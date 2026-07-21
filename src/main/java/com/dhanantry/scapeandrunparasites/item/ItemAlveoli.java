package com.dhanantry.scapeandrunparasites.item;

import java.util.List;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAlveoli extends ItemBase {
   private static final int DURATION_TICKS = 600;
   private final boolean alwaysEdible = true;

   public ItemAlveoli(String name) {
      super(name, 64, (byte)0);
      this.func_77625_d(64);
   }

   public int func_77626_a(ItemStack stack) {
      return 32;
   }

   public EnumAction func_77661_b(ItemStack stack) {
      return EnumAction.EAT;
   }

   public ActionResult<ItemStack> func_77659_a(World world, EntityPlayer player, EnumHand hand) {
      ItemStack stack = player.func_184586_b(hand);
      player.func_184598_c(hand);
      return new ActionResult(EnumActionResult.SUCCESS, stack);
   }

   public ItemStack func_77654_b(ItemStack stack, World world, EntityLivingBase eater) {
      if (eater instanceof EntityPlayer) {
         EntityPlayer p = (EntityPlayer)eater;
         if (!p.field_71075_bZ.field_75098_d) {
            stack.func_190918_g(1);
         }

         world.func_184148_a(null, p.field_70165_t, p.field_70163_u, p.field_70161_v, SoundEvents.field_187739_dZ, SoundCategory.PLAYERS, 0.8F, 1.0F);
      }

      return stack;
   }

   public String func_77658_a() {
      return "item.srparasites." + this.getRegistryName().func_110623_a();
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
      ResourceLocation rl = this.getRegistryName();
      String key = rl != null ? "tooltip.srparasites." + rl.func_110623_a() : "tooltip.srparasites.alveoli";
      tooltip.add(I18n.func_135052_a(key, new Object[0]));
   }
}
