package com.dhanantry.scapeandrunparasites.item;

import java.util.List;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemFalseApple extends ItemBase {
   private static final int HUNGER = 4;
   private static final float SATURATION = 0.3F;
   private static final int BUGLIN_COUNT = 5;
   private static final ResourceLocation BUGLIN_ID = new ResourceLocation("srparasites", "buglin");
   private boolean alwaysEdible = true;

   public ItemFalseApple(String name) {
      super(name, 64, (byte)0);
      this.func_77625_d(64);
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
      tooltip.add(I18n.func_135052_a("tooltip.srparasites.false_apple", new Object[0]));
   }

   public int func_77626_a(ItemStack stack) {
      return 32;
   }

   public EnumAction func_77661_b(ItemStack stack) {
      return EnumAction.EAT;
   }

   public ActionResult<ItemStack> func_77659_a(World world, EntityPlayer player, EnumHand hand) {
      ItemStack stack = player.func_184586_b(hand);
      if (!this.alwaysEdible && !player.func_71024_bL().func_75121_c()) {
         return new ActionResult(EnumActionResult.FAIL, stack);
      } else {
         player.func_184598_c(hand);
         return new ActionResult(EnumActionResult.SUCCESS, stack);
      }
   }

   public ItemStack func_77654_b(ItemStack stack, World world, EntityLivingBase eater) {
      if (eater instanceof EntityPlayer) {
         EntityPlayer player = (EntityPlayer)eater;
         if (!player.field_71075_bZ.field_75098_d) {
            stack.func_190918_g(1);
         }

         player.func_71024_bL().func_75122_a(4, 0.3F);
         world.func_184148_a(
            null, player.field_70165_t, player.field_70163_u, player.field_70161_v, SoundEvents.field_187739_dZ, SoundCategory.PLAYERS, 0.8F, 1.0F
         );
         player.func_70690_d(new PotionEffect(MobEffects.field_76431_k, 200, 0));
         player.func_70690_d(new PotionEffect(MobEffects.field_76438_s, 600, 0));
         if (!world.field_72995_K) {
            for (int i = 0; i < 5; i++) {
               Entity e = EntityList.func_188429_b(BUGLIN_ID, world);
               if (e != null) {
                  double offX = (world.field_73012_v.nextDouble() - 0.5) * 0.8;
                  double offZ = (world.field_73012_v.nextDouble() - 0.5) * 0.8;
                  e.func_70012_b(player.field_70165_t + offX, player.field_70163_u, player.field_70161_v + offZ, world.field_73012_v.nextFloat() * 360.0F, 0.0F);
                  world.func_72838_d(e);
               }
            }
         }
      }

      return stack;
   }

   public String func_77658_a() {
      return "item.srparasites." + this.getRegistryName().func_110623_a();
   }
}
