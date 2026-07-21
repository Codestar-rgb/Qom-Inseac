package com.dhanantry.scapeandrunparasites.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent.OverlayType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Post;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(value = Side.CLIENT, modid = "srparasites")
public final class DeadBloodOverlayHandler {
   private static final ResourceLocation FLOW_OVERLAY = new ResourceLocation("srparasites", "textures/blocks/deadblood_flowing.png");
   private static final float OVERLAY_ALPHA = 0.55F;
   private static final float SCROLL_SPEED = 0.02F;
   private static final float UV_SCALE = 0.25F;

   private static boolean eyesInDeadBlood(Entity e, double pt) {
      return DeadBloodFogHandler.isEyeInDeadBlood(e, pt);
   }

   @SubscribeEvent
   public static void onWaterOverlay(RenderBlockOverlayEvent event) {
      if (event.getOverlayType() == OverlayType.WATER && eyesInDeadBlood(event.getPlayer(), 0.0)) {
         event.setCanceled(true);
      }
   }

   @SubscribeEvent
   public static void onOverlayRender(Post event) {
      if (event.getType() == ElementType.ALL) {
         Minecraft mc = Minecraft.func_71410_x();
         Entity viewer = mc.func_175606_aa();
         if (eyesInDeadBlood(viewer, mc.func_184121_ak())) {
            ScaledResolution sr = new ScaledResolution(mc);
            int w = sr.func_78326_a();
            int h = sr.func_78328_b();
            TextureAtlasSprite sprite = mc.func_147117_R().func_110572_b("srparasites:blocks/deadblood_flowing");
            GlStateManager.func_179097_i();
            GlStateManager.func_179132_a(false);
            GlStateManager.func_179147_l();
            GlStateManager.func_187428_a(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
            mc.func_110434_K().func_110577_a(TextureMap.field_110575_b);
            float alpha = 0.45F;
            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 0.45F);
            float u0 = sprite.func_94209_e();
            float v0 = sprite.func_94206_g();
            float u1 = sprite.func_94212_f();
            float v1 = sprite.func_94210_h();
            Tessellator tess = Tessellator.func_178181_a();
            BufferBuilder buf = tess.func_178180_c();
            buf.func_181668_a(7, DefaultVertexFormats.field_181707_g);
            buf.func_181662_b(0.0, h, -90.0).func_187315_a(u0, v1).func_181675_d();
            buf.func_181662_b(w, h, -90.0).func_187315_a(u1, v1).func_181675_d();
            buf.func_181662_b(w, 0.0, -90.0).func_187315_a(u1, v0).func_181675_d();
            buf.func_181662_b(0.0, 0.0, -90.0).func_187315_a(u0, v0).func_181675_d();
            tess.func_78381_a();
            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.func_179084_k();
            GlStateManager.func_179132_a(true);
            GlStateManager.func_179126_j();
         }
      }
   }
}
