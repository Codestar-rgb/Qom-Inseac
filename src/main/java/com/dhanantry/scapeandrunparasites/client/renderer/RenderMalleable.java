package com.dhanantry.scapeandrunparasites.client.renderer;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPMalleable;
import java.nio.Buffer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;

public abstract class RenderMalleable<T extends EntityPMalleable> extends RenderSRP<T> {
   private static final DynamicTexture TEXTURE_BRIGHTNESS = new DynamicTexture(16, 16);

   public RenderMalleable(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
      super(rendermanagerIn, modelbaseIn, shadowsizeIn);
   }

   protected boolean setBrightness(T entitylivingbaseIn, float partialTicks, boolean combineTextures) {
      float f = entitylivingbaseIn.func_70013_c();
      int i = this.func_77030_a(entitylivingbaseIn, f, partialTicks);
      boolean flag = (i >> 24 & 0xFF) > 0;
      boolean flag1 = entitylivingbaseIn.field_70737_aN > 0 || entitylivingbaseIn.field_70725_aQ > 0;
      if (!flag && !flag1) {
         return false;
      } else if (!flag && !combineTextures) {
         return false;
      } else {
         GlStateManager.func_179138_g(OpenGlHelper.field_77478_a);
         GlStateManager.func_179098_w();
         GlStateManager.func_187399_a(8960, 8704, OpenGlHelper.field_176095_s);
         GlStateManager.func_187399_a(8960, OpenGlHelper.field_176099_x, 8448);
         GlStateManager.func_187399_a(8960, OpenGlHelper.field_176098_y, OpenGlHelper.field_77478_a);
         GlStateManager.func_187399_a(8960, OpenGlHelper.field_176097_z, OpenGlHelper.field_176093_u);
         GlStateManager.func_187399_a(8960, OpenGlHelper.field_176081_B, 768);
         GlStateManager.func_187399_a(8960, OpenGlHelper.field_176082_C, 768);
         GlStateManager.func_187399_a(8960, OpenGlHelper.field_176077_E, 7681);
         GlStateManager.func_187399_a(8960, OpenGlHelper.field_176078_F, OpenGlHelper.field_77478_a);
         GlStateManager.func_187399_a(8960, OpenGlHelper.field_176085_I, 770);
         GlStateManager.func_179138_g(OpenGlHelper.field_77476_b);
         GlStateManager.func_179098_w();
         GlStateManager.func_187399_a(8960, 8704, OpenGlHelper.field_176095_s);
         GlStateManager.func_187399_a(8960, OpenGlHelper.field_176099_x, OpenGlHelper.field_176094_t);
         GlStateManager.func_187399_a(8960, OpenGlHelper.field_176098_y, OpenGlHelper.field_176092_v);
         GlStateManager.func_187399_a(8960, OpenGlHelper.field_176097_z, OpenGlHelper.field_176091_w);
         GlStateManager.func_187399_a(8960, OpenGlHelper.field_176080_A, OpenGlHelper.field_176092_v);
         GlStateManager.func_187399_a(8960, OpenGlHelper.field_176081_B, 768);
         GlStateManager.func_187399_a(8960, OpenGlHelper.field_176082_C, 768);
         GlStateManager.func_187399_a(8960, OpenGlHelper.field_176076_D, 770);
         GlStateManager.func_187399_a(8960, OpenGlHelper.field_176077_E, 7681);
         GlStateManager.func_187399_a(8960, OpenGlHelper.field_176078_F, OpenGlHelper.field_176091_w);
         GlStateManager.func_187399_a(8960, OpenGlHelper.field_176085_I, 770);
         ((Buffer)this.field_177095_g).position(0);
         if (flag1) {
            switch (entitylivingbaseIn.getHitStatus()) {
               case 0:
                  this.field_177095_g.put(1.0F);
                  this.field_177095_g.put(0.0F);
                  this.field_177095_g.put(0.0F);
                  this.field_177095_g.put(0.3F);
                  break;
               case 1:
                  this.field_177095_g.put(0.0F);
                  this.field_177095_g.put(1.0F);
                  this.field_177095_g.put(0.0F);
                  this.field_177095_g.put(0.3F);
                  break;
               case 2:
                  this.field_177095_g.put(1.0F);
                  this.field_177095_g.put(0.0F);
                  this.field_177095_g.put(1.0F);
                  this.field_177095_g.put(0.3F);
                  break;
               case 3:
                  this.field_177095_g.put(0.1F);
                  this.field_177095_g.put(0.2F);
                  this.field_177095_g.put(0.2F);
                  this.field_177095_g.put(1.0F);
            }
         } else {
            float f1 = (i >> 24 & 0xFF) / 255.0F;
            float f2 = (i >> 16 & 0xFF) / 255.0F;
            float f3 = (i >> 8 & 0xFF) / 255.0F;
            float f4 = (i & 0xFF) / 255.0F;
            this.field_177095_g.put(f2);
            this.field_177095_g.put(f3);
            this.field_177095_g.put(f4);
            this.field_177095_g.put(1.0F - f1);
         }

         ((Buffer)this.field_177095_g).flip();
         GlStateManager.func_187448_b(8960, 8705, this.field_177095_g);
         GlStateManager.func_179138_g(OpenGlHelper.field_176096_r);
         GlStateManager.func_179098_w();
         GlStateManager.func_179144_i(TEXTURE_BRIGHTNESS.func_110552_b());
         GlStateManager.func_187399_a(8960, 8704, OpenGlHelper.field_176095_s);
         GlStateManager.func_187399_a(8960, OpenGlHelper.field_176099_x, 8448);
         GlStateManager.func_187399_a(8960, OpenGlHelper.field_176098_y, OpenGlHelper.field_176091_w);
         GlStateManager.func_187399_a(8960, OpenGlHelper.field_176097_z, OpenGlHelper.field_77476_b);
         GlStateManager.func_187399_a(8960, OpenGlHelper.field_176081_B, 768);
         GlStateManager.func_187399_a(8960, OpenGlHelper.field_176082_C, 768);
         GlStateManager.func_187399_a(8960, OpenGlHelper.field_176077_E, 7681);
         GlStateManager.func_187399_a(8960, OpenGlHelper.field_176078_F, OpenGlHelper.field_176091_w);
         GlStateManager.func_187399_a(8960, OpenGlHelper.field_176085_I, 770);
         GlStateManager.func_179138_g(OpenGlHelper.field_77478_a);
         return true;
      }
   }

   public ResourceLocation getGlowTexture(ResourceLocation base) {
      String path = base.func_110623_a();
      int ind = path.lastIndexOf(46);
      return new ResourceLocation(base.func_110624_b(), path.substring(0, ind) + "_glow" + path.substring(ind));
   }

   public ResourceLocation getBaseTexture(T entitylivingbaseIn) {
      return this.func_110775_a(entitylivingbaseIn);
   }
}
