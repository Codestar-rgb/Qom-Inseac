package com.dhanantry.scapeandrunparasites.client.renderer.entity.projectile;

import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileHebluLight;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class RenderHebluLight extends Render<EntityProjectileHebluLight> {
   private static final ResourceLocation TEXTURE = new ResourceLocation("srparasites", "textures/particle/arc_flash.png");
   private static final ResourceLocation TAIL_TEXTURE = new ResourceLocation("srparasites", "textures/particle/arc_flash_tail.png");

   public RenderHebluLight(RenderManager renderManager) {
      super(renderManager);
      this.field_76989_e = 0.0F;
   }

   public void doRender(EntityProjectileHebluLight entity, double x, double y, double z, float entityYaw, float partialTicks) {
      double mx = entity.field_70159_w;
      double my = entity.field_70181_x;
      double mz = entity.field_70179_y;
      double speed = Math.sqrt(mx * mx + my * my + mz * mz);
      if (speed < 0.001) {
         speed = 0.001;
         mz = 0.001;
      }

      float yaw = (float)(Math.atan2(mx, mz) * 180.0 / Math.PI);
      float horizontal = (float)Math.sqrt(mx * mx + mz * mz);
      float pitch = (float)(-Math.atan2(my, horizontal) * 180.0 / Math.PI);
      float width = 0.62F - (float)Math.min(speed * 0.045, 0.24);
      float coreWidth = 0.82F - (float)Math.min(speed * 0.05, 0.28);
      float backLength = 3.4F + (float)Math.min(speed * 3.4, 8.5);
      float frontLength = 0.45F + (float)Math.min(speed * 0.28, 0.85);
      float r;
      float g;
      float b;
      if (entity.isParriedLight()) {
         r = 0.0F;
         g = 0.45F;
         b = 1.0F;
      } else {
         float danger = entity.getPlayerDangerColorAmount();
         r = 1.0F;
         g = 0.86F - danger * 0.86F;
         b = 0.42F - danger * 0.42F;
      }

      GlStateManager.func_179094_E();
      GlStateManager.func_179109_b((float)x, (float)y, (float)z);
      GlStateManager.func_179140_f();
      GlStateManager.func_179147_l();
      GlStateManager.func_179129_p();
      GlStateManager.func_179132_a(false);
      GlStateManager.func_187401_a(SourceFactor.SRC_ALPHA, DestFactor.ONE);
      GlStateManager.func_179114_b(yaw, 0.0F, 1.0F, 0.0F);
      GlStateManager.func_179114_b(pitch, 1.0F, 0.0F, 0.0F);
      this.func_110776_a(TAIL_TEXTURE);
      this.drawCrossRibbon(width * 1.25F, frontLength, backLength, r, g, b, 0.3F);
      this.drawCrossRibbon(width * 0.72F, frontLength * 0.65F, backLength * 1.25F, r, g, b, 0.48F);
      this.drawCrossRibbon(width * 0.38F, frontLength * 0.45F, backLength * 1.55F, r, g, b, 0.78F);
      this.func_110776_a(TEXTURE);
      this.drawCrossCore(coreWidth, 0.85F, r, g, b, 0.9F);
      GlStateManager.func_179132_a(true);
      GlStateManager.func_179089_o();
      GlStateManager.func_179084_k();
      GlStateManager.func_179145_e();
      GlStateManager.func_179121_F();
      super.func_76986_a(entity, x, y, z, entityYaw, partialTicks);
   }

   private void drawCrossRibbon(float width, float frontLength, float backLength, float r, float g, float b, float alpha) {
      this.drawRibbonPlaneX(width, frontLength, backLength, r, g, b, alpha);
      this.drawRibbonPlaneY(width, frontLength, backLength, r, g, b, alpha);
   }

   private void drawCrossCore(float width, float length, float r, float g, float b, float alpha) {
      this.drawRibbonPlaneX(width, length, length, r, g, b, alpha);
      this.drawRibbonPlaneY(width, length, length, r, g, b, alpha);
   }

   private void drawRibbonPlaneX(float width, float frontLength, float backLength, float r, float g, float b, float alpha) {
      float halfW = width * 0.5F;
      Tessellator tessellator = Tessellator.func_178181_a();
      BufferBuilder buffer = tessellator.func_178180_c();
      buffer.func_181668_a(7, DefaultVertexFormats.field_181709_i);
      buffer.func_181662_b(-halfW, 0.0, -backLength).func_187315_a(0.0, 1.0).func_181666_a(r, g, b, 0.0F).func_181675_d();
      buffer.func_181662_b(halfW, 0.0, -backLength).func_187315_a(1.0, 1.0).func_181666_a(r, g, b, 0.0F).func_181675_d();
      buffer.func_181662_b(halfW, 0.0, frontLength).func_187315_a(1.0, 0.0).func_181666_a(r, g, b, alpha).func_181675_d();
      buffer.func_181662_b(-halfW, 0.0, frontLength).func_187315_a(0.0, 0.0).func_181666_a(r, g, b, alpha).func_181675_d();
      tessellator.func_78381_a();
   }

   private void drawRibbonPlaneY(float width, float frontLength, float backLength, float r, float g, float b, float alpha) {
      float halfW = width * 0.5F;
      Tessellator tessellator = Tessellator.func_178181_a();
      BufferBuilder buffer = tessellator.func_178180_c();
      buffer.func_181668_a(7, DefaultVertexFormats.field_181709_i);
      buffer.func_181662_b(0.0, -halfW, -backLength).func_187315_a(0.0, 1.0).func_181666_a(r, g, b, 0.0F).func_181675_d();
      buffer.func_181662_b(0.0, halfW, -backLength).func_187315_a(1.0, 1.0).func_181666_a(r, g, b, 0.0F).func_181675_d();
      buffer.func_181662_b(0.0, halfW, frontLength).func_187315_a(1.0, 0.0).func_181666_a(r, g, b, alpha).func_181675_d();
      buffer.func_181662_b(0.0, -halfW, frontLength).func_187315_a(0.0, 0.0).func_181666_a(r, g, b, alpha).func_181675_d();
      tessellator.func_78381_a();
   }

   @Nullable
   protected ResourceLocation getEntityTexture(EntityProjectileHebluLight entity) {
      return TEXTURE;
   }
}
