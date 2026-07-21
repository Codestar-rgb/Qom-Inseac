package com.dhanantry.scapeandrunparasites.client.renderer.entity.layer;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class LayerSnow<T extends EntityParasiteBase> implements LayerRenderer<T> {
   private ResourceLocation LAYER;
   private final RenderLiving parasite;

   public LayerSnow(RenderLiving in, ResourceLocation l, double x, double y, double z) {
      this.parasite = in;
      this.LAYER = l;
   }

   public void doRenderLayer(
      T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale
   ) {
      if (entitylivingbaseIn.getColdL()) {
         this.parasite.func_110776_a(this.LAYER);
         GlStateManager.func_179147_l();
         if (entitylivingbaseIn.func_82150_aj()) {
            GlStateManager.func_179132_a(false);
         } else {
            GlStateManager.func_179132_a(true);
         }

         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
         Minecraft.func_71410_x().field_71460_t.func_191514_d(true);
         this.parasite.func_177087_b().func_78088_a(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale + 0.0F);
         Minecraft.func_71410_x().field_71460_t.func_191514_d(false);
         this.parasite.func_177105_a(entitylivingbaseIn);
         GlStateManager.func_179084_k();
         GlStateManager.func_179141_d();
      }
   }

   public boolean func_177142_b() {
      return false;
   }
}
