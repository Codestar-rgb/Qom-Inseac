package com.dhanantry.scapeandrunparasites.client.renderer;

import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Post;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ScreenOverlayRenderer {
   private static final ResourceLocation VIRAL = new ResourceLocation("srparasites:textures/gui/screen_viral.png");
   private static final ResourceLocation BLEED = new ResourceLocation("srparasites:textures/gui/screen_bleed.png");
   private static final ResourceLocation VOMIT = new ResourceLocation("srparasites:textures/gui/screen_vomit.png");
   private static final ResourceLocation VISION = new ResourceLocation("srparasites:textures/gui/screen_novision.png");
   private int vomitY = 0;

   @SubscribeEvent
   public void renderOverlay(Post event) {
      if (event.getType() == ElementType.ALL) {
         Minecraft mc = Minecraft.func_71410_x();
         ScaledResolution scaledRes = new ScaledResolution(mc);
         int screenWidth = scaledRes.func_78326_a();
         int screenHeight = scaledRes.func_78328_b();
         if (mc.field_71439_g.func_70644_a(SRPPotions.VIRA_E)) {
            this.screenEffectViral(mc, VIRAL, screenWidth, screenHeight);
         }

         if (mc.field_71439_g.func_70644_a(SRPPotions.BLEED_E)) {
            this.screenEffectBleed(mc, BLEED, screenWidth, screenHeight);
         }

         if (mc.field_71439_g.func_70644_a(SRPPotions.NOVISION_E)) {
            this.screenEffectVision(mc, VISION, screenWidth, screenHeight);
         }

         if (mc.field_71439_g.func_70644_a(SRPPotions.VOMIT_E)) {
            this.screenEffectVomit(mc, VOMIT, screenWidth, screenHeight);
         } else {
            this.vomitY = 0;
         }
      }
   }

   private void screenEffectBleed(Minecraft mc, ResourceLocation in, int screenW, int creenH) {
      GlStateManager.func_179094_E();
      GlStateManager.func_179141_d();
      GlStateManager.func_179147_l();
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.func_179120_a(770, 771, 1, 0);
      mc.func_110434_K().func_110577_a(in);
      int posX = 0;
      int posY = 0;
      Gui.func_146110_a(posX, posY, 0.0F, 0.0F, screenW, creenH, screenW, creenH);
      GlStateManager.func_179084_k();
      GlStateManager.func_179121_F();
   }

   private void screenEffectViral(Minecraft mc, ResourceLocation in, int screenW, int creenH) {
      GlStateManager.func_179094_E();
      GlStateManager.func_179141_d();
      GlStateManager.func_179147_l();
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.func_179120_a(770, 771, 1, 0);
      mc.func_110434_K().func_110577_a(in);
      int posX = 0;
      int posY = 0;
      Gui.func_146110_a(posX, posY, 0.0F, 0.0F, screenW, creenH, screenW, creenH);
      GlStateManager.func_179084_k();
      GlStateManager.func_179121_F();
   }

   private void screenEffectVomit(Minecraft mc, ResourceLocation in, int screenW, int creenH) {
      GlStateManager.func_179094_E();
      GlStateManager.func_179141_d();
      GlStateManager.func_179147_l();
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.func_179120_a(770, 771, 1, 0);
      mc.func_110434_K().func_110577_a(in);
      int extra = 8;
      int posX = 0;
      int posY = this.vomitY;
      int textureHeight = creenH * extra;
      Gui.func_146110_a(posX, posY, 0.0F, 0.0F, screenW, textureHeight, screenW, textureHeight);
      GlStateManager.func_179084_k();
      GlStateManager.func_179121_F();
      this.vomitY++;
      if (this.vomitY >= 0) {
         this.vomitY = -creenH * 7;
      }
   }

   private void screenEffectVision(Minecraft mc, ResourceLocation in, int screenW, int creenH) {
      GlStateManager.func_179094_E();
      GlStateManager.func_179141_d();
      GlStateManager.func_179147_l();
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.func_179120_a(770, 771, 1, 0);
      mc.func_110434_K().func_110577_a(in);
      int posX = 0;
      int posY = 0;
      Gui.func_146110_a(posX, posY, 0.0F, 0.0F, screenW, creenH, screenW, creenH);
      GlStateManager.func_179084_k();
      GlStateManager.func_179121_F();
   }
}
