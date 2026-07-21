package com.dhanantry.scapeandrunparasites.item;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityThrowableAntiInfestedBlock;
import java.util.List;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemThrowable extends Item {
   public ItemThrowable(String name, int maxStack) {
      this.setRegistryName(name);
      this.func_77655_b("srparasites." + name);
      this.func_77637_a(SRPMain.SRP_CREATIVETAB);
      this.field_77777_bU = maxStack;
   }

   public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
      ItemStack itemstack = playerIn.func_184586_b(handIn);
      if (!playerIn.field_71075_bZ.field_75098_d) {
         itemstack.func_190918_g(1);
      }

      worldIn.func_184148_a(
         (EntityPlayer)null,
         playerIn.field_70165_t,
         playerIn.field_70163_u,
         playerIn.field_70161_v,
         SoundEvents.field_187511_aA,
         SoundCategory.PLAYERS,
         0.5F,
         0.4F / (field_77697_d.nextFloat() * 0.4F + 0.8F)
      );
      if (!worldIn.field_72995_K) {
         EntityThrowableAntiInfestedBlock entityegg = new EntityThrowableAntiInfestedBlock(worldIn, playerIn);
         entityegg.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0F, 0.5F, 5.0F);
         CooldownTracker track = playerIn.func_184811_cZ();
         track.func_185145_a(itemstack.func_77973_b(), 20);
         worldIn.func_72838_d(entityegg);
      }

      playerIn.func_71029_a(StatList.func_188057_b(this));
      return new ActionResult(EnumActionResult.SUCCESS, itemstack);
   }

   @SideOnly(Side.CLIENT)
   public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
      tooltip.add(TextFormatting.AQUA + I18n.func_135052_a("tootip.srparasites.quench", new Object[0]));
   }
}
