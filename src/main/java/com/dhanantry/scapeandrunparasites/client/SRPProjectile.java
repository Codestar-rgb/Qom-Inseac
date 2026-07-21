package com.dhanantry.scapeandrunparasites.client;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class SRPProjectile extends Render {
   private final float scale;
   private ResourceLocation texture;

   public SRPProjectile(RenderManager manager, float scale) {
      super(manager);
      this.scale = scale;
   }

   public SRPProjectile(RenderManager manager, float scale, ResourceLocation sprite) {
      super(manager);
      this.scale = scale;
      this.setTexture(sprite);
   }

   public SRPProjectile setTexture(ResourceLocation sprite) {
      this.texture = sprite;
      return this;
   }

   public void func_76986_a(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
      GlStateManager.func_179094_E();
      this.func_180548_c(entity);
      GlStateManager.func_179137_b(x, y, z);
      GlStateManager.func_179091_B();
      GlStateManager.func_179152_a(this.scale, this.scale, this.scale);
      Tessellator tessellator = Tessellator.func_178181_a();
      BufferBuilder bufferbuilder = tessellator.func_178180_c();
      GlStateManager.func_179114_b(180.0F - this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
      GlStateManager.func_179114_b(-this.field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F);
      if (this.field_188301_f) {
         GlStateManager.func_179142_g();
         GlStateManager.func_187431_e(this.func_188298_c(entity));
      }

      bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181710_j);
      bufferbuilder.func_181662_b(-0.5, -0.25, 0.0).func_187315_a(0.0, 1.0).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
      bufferbuilder.func_181662_b(0.5, -0.25, 0.0).func_187315_a(1.0, 1.0).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
      bufferbuilder.func_181662_b(0.5, 0.75, 0.0).func_187315_a(1.0, 0.0).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
      bufferbuilder.func_181662_b(-0.5, 0.75, 0.0).func_187315_a(0.0, 0.0).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
      tessellator.func_78381_a();
      if (this.field_188301_f) {
         GlStateManager.func_187417_n();
         GlStateManager.func_179119_h();
      }

      GlStateManager.func_179101_C();
      GlStateManager.func_179121_F();
      super.func_76986_a(entity, x, y, z, entityYaw, partialTicks);
   }

   protected ResourceLocation func_110775_a(Entity entity) {
      return this.texture;
   }
}
