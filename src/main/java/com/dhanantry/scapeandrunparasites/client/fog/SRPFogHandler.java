package com.dhanantry.scapeandrunparasites.client.fog;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.FogMode;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogColors;
import net.minecraftforge.client.event.EntityViewRenderEvent.RenderFogEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public final class SRPFogHandler {
   public static void register() {
      MinecraftForge.EVENT_BUS.register(new SRPFogHandler());
   }

   private boolean shouldApply() {
      return SRPFogManager.get().isEnabled();
   }

   @SubscribeEvent
   public void onFogColors(FogColors e) {
      if (SRPFogManager.get().isEnabled()) {
         float r = e.getRed();
         float g = e.getGreen();
         float b = e.getBlue();
         float gray = (r + g + b) / 3.0F;
         r = r * 0.75F + gray * 0.25F;
         g = g * 0.75F + gray * 0.25F;
         b = b * 0.75F + gray * 0.25F;
         r *= 0.9F;
         g *= 0.9F;
         b *= 0.9F;
         e.setRed(r);
         e.setGreen(g);
         e.setBlue(b);
      }
   }

   @SubscribeEvent
   public void onFogRender(RenderFogEvent e) {
      if (this.shouldApply()) {
         GlStateManager.func_187430_a(FogMode.LINEAR);
         GlStateManager.func_179102_b(12.0F);
         GlStateManager.func_179153_c(64.0F);
      }
   }
}
