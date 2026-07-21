package com.dhanantry.scapeandrunparasites.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PotionTheSign extends SRPEffectBase {
   private static final ResourceLocation ICON = new ResourceLocation("srparasites", "textures/gui/the_sign.png");

   public PotionTheSign() {
      super("the_sign", false, 8970751, 0, 0);
      this.func_76390_b("mob_effect.srparasites.the_sign");
   }

   @Override
   public boolean func_76400_d() {
      return false;
   }

   @SideOnly(Side.CLIENT)
   public void renderInventoryEffect(PotionEffect effect, Gui gui, int x, int y, float z) {
      Minecraft.func_71410_x().func_110434_K().func_110577_a(ICON);
      Gui.func_146110_a(x + 3, y + 3, 0.0F, 0.0F, 16, 16, 16.0F, 16.0F);
   }

   @SideOnly(Side.CLIENT)
   public void renderHUDEffect(PotionEffect effect, Gui gui, int x, int y, float z, float alpha) {
      Minecraft.func_71410_x().func_110434_K().func_110577_a(ICON);
      Gui.func_146110_a(x + 4, y + 4, 0.0F, 0.0F, 16, 16, 16.0F, 16.0F);
   }
}
