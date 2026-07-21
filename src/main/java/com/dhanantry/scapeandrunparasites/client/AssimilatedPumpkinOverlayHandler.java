package com.dhanantry.scapeandrunparasites.client;

import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AssimilatedPumpkinOverlayHandler {
   private static final ResourceLocation OVERLAY_TEX = new ResourceLocation("srparasites", "textures/gui/assimilated_pumpkin_overlay.png");

   @SubscribeEvent
   public void onRenderHelmet(Pre event) {
      if (event.getType() == ElementType.HELMET) {
         Minecraft mc = Minecraft.func_71410_x();
         if (mc.field_71439_g != null && mc.field_71474_y.field_74320_O == 0) {
            ItemStack head = mc.field_71439_g.func_184582_a(EntityEquipmentSlot.HEAD);
            if (!head.func_190926_b()) {
               Item item = head.func_77973_b();
               Item a = Item.func_150898_a(SRPBlocks.AssimilatedPumpkin);
               Item b = Item.func_150898_a(SRPBlocks.AssimilatedJackOLantern);
               if (item == a || item == b) {
                  ScaledResolution res = new ScaledResolution(mc);
                  int w = res.func_78326_a();
                  int h = res.func_78328_b();
                  GlStateManager.func_179097_i();
                  GlStateManager.func_179132_a(false);
                  GlStateManager.func_179120_a(770, 771, 1, 0);
                  GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
                  mc.func_110434_K().func_110577_a(OVERLAY_TEX);
                  Tessellator tess = Tessellator.func_178181_a();
                  BufferBuilder buf = tess.func_178180_c();
                  buf.func_181668_a(7, DefaultVertexFormats.field_181707_g);
                  buf.func_181662_b(0.0, h, -90.0).func_187315_a(0.0, 1.0).func_181675_d();
                  buf.func_181662_b(w, h, -90.0).func_187315_a(1.0, 1.0).func_181675_d();
                  buf.func_181662_b(w, 0.0, -90.0).func_187315_a(1.0, 0.0).func_181675_d();
                  buf.func_181662_b(0.0, 0.0, -90.0).func_187315_a(0.0, 0.0).func_181675_d();
                  tess.func_78381_a();
                  GlStateManager.func_179132_a(true);
                  GlStateManager.func_179126_j();
                  GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
               }
            }
         }
      }
   }
}
