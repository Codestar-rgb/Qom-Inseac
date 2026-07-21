package com.dhanantry.scapeandrunparasites.client.renderer;

import com.dhanantry.scapeandrunparasites.client.model.ModelSRP;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPCosmical;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.Profile;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderLivingEvent.Post;
import net.minecraftforge.client.event.RenderLivingEvent.Pre;
import net.minecraftforge.common.MinecraftForge;

public abstract class RenderCosmical<T extends EntityPCosmical> extends RenderMalleable<T> {
   protected ModelSRP mainModel2;
   private static final ResourceLocation GUARDIAN_BEAM_TEXTURE = new ResourceLocation("srparasites:textures/entity/layer/cosmichasking.png");

   public RenderCosmical(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
      super(rendermanagerIn, modelbaseIn, shadowsizeIn);
      this.mainModel2 = (ModelSRP)modelbaseIn;
      this.func_177094_a(new LayerCosmicalHacking(this, this.mainModel2));
   }

   public RenderCosmical(RenderManager rendermanagerIn, ModelBase modelbaseIn, ModelSRP modelbaseIn2, float shadowsizeIn) {
      super(rendermanagerIn, modelbaseIn, shadowsizeIn);
      this.mainModel2 = modelbaseIn2;
   }

   public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
      super.func_76986_a(entity, x, y, z, entityYaw, partialTicks);
      this.doRenderCosmical(entity, x, y, z, entityYaw, partialTicks);
      this.doRenderLaser(entity, x, y, z, entityYaw, partialTicks);
   }

   public void doRenderLaser(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
      for (EntityLivingBase entitylivingbase : entity.getTargetedEntityVictims()) {
         if (entitylivingbase != null) {
            float f = 10.0F;
            Tessellator tessellator = Tessellator.func_178181_a();
            BufferBuilder bufferbuilder = tessellator.func_178180_c();
            this.func_110776_a(GUARDIAN_BEAM_TEXTURE);
            GlStateManager.func_187421_b(3553, 10242, 10497);
            GlStateManager.func_187421_b(3553, 10243, 10497);
            GlStateManager.func_179129_p();
            GlStateManager.func_179084_k();
            GlStateManager.func_179132_a(true);
            float f1 = 240.0F;
            OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, 240.0F, 240.0F);
            GlStateManager.func_187428_a(SourceFactor.SRC_ALPHA, DestFactor.ONE, SourceFactor.ONE, DestFactor.ZERO);
            float f2 = (float)entity.field_70170_p.func_82737_E() + partialTicks;
            float f3 = f2 * 0.5F % 1.0F;
            float f4 = entity.func_70047_e();
            GlStateManager.func_179094_E();
            GlStateManager.func_179109_b((float)x, (float)y + f4, (float)z);
            Vec3d vec3d = this.getPosition(entitylivingbase, entitylivingbase.field_70131_O * 0.5, partialTicks);
            Vec3d vec3d1 = this.getPosition(entity, f4, partialTicks);
            Vec3d vec3d2 = vec3d.func_178788_d(vec3d1);
            double d0 = vec3d2.func_72433_c() + 1.0;
            vec3d2 = vec3d2.func_72432_b();
            float f5 = (float)Math.acos(vec3d2.field_72448_b);
            float f6 = (float)Math.atan2(vec3d2.field_72449_c, vec3d2.field_72450_a);
            GlStateManager.func_179114_b(((float) (Math.PI / 2) + -f6) * (180.0F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
            GlStateManager.func_179114_b(f5 * (180.0F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
            int i = 1;
            double d1 = f2 * 0.05 * -1.5;
            bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181709_i);
            float f7 = f * f;
            int j = 178 + (int)(f7 * 191.0F);
            int k = 0 + (int)(f7 * 191.0F);
            int l = 250 - (int)(f7 * 64.0F);
            double d2 = 0.2;
            double d3 = 0.282;
            double d4 = 0.0 + Math.cos(d1 + (Math.PI * 3.0 / 4.0)) * 0.282;
            double d5 = 0.0 + Math.sin(d1 + (Math.PI * 3.0 / 4.0)) * 0.282;
            double d6 = 0.0 + Math.cos(d1 + (Math.PI / 4)) * 0.282;
            double d7 = 0.0 + Math.sin(d1 + (Math.PI / 4)) * 0.282;
            double d8 = 0.0 + Math.cos(d1 + (Math.PI * 5.0 / 4.0)) * 0.282;
            double d9 = 0.0 + Math.sin(d1 + (Math.PI * 5.0 / 4.0)) * 0.282;
            double d10 = 0.0 + Math.cos(d1 + (Math.PI * 7.0 / 4.0)) * 0.282;
            double d11 = 0.0 + Math.sin(d1 + (Math.PI * 7.0 / 4.0)) * 0.282;
            double d12 = 0.0 + Math.cos(d1 + Math.PI) * 0.2;
            double d13 = 0.0 + Math.sin(d1 + Math.PI) * 0.2;
            double d14 = 0.0 + Math.cos(d1 + 0.0) * 0.2;
            double d15 = 0.0 + Math.sin(d1 + 0.0) * 0.2;
            double d16 = 0.0 + Math.cos(d1 + (Math.PI / 2)) * 0.2;
            double d17 = 0.0 + Math.sin(d1 + (Math.PI / 2)) * 0.2;
            double d18 = 0.0 + Math.cos(d1 + (Math.PI * 3.0 / 2.0)) * 0.2;
            double d19 = 0.0 + Math.sin(d1 + (Math.PI * 3.0 / 2.0)) * 0.2;
            double d20 = 0.0;
            double d21 = 0.4999;
            double d22 = -1.0F + f3;
            double d23 = d0 * 2.5 + d22;
            bufferbuilder.func_181662_b(d12, d0, d13).func_187315_a(0.4999, d23).func_181669_b(j, k, l, 255).func_181675_d();
            bufferbuilder.func_181662_b(d12, 0.0, d13).func_187315_a(0.4999, d22).func_181669_b(j, k, l, 255).func_181675_d();
            bufferbuilder.func_181662_b(d14, 0.0, d15).func_187315_a(0.0, d22).func_181669_b(j, k, l, 255).func_181675_d();
            bufferbuilder.func_181662_b(d14, d0, d15).func_187315_a(0.0, d23).func_181669_b(j, k, l, 255).func_181675_d();
            bufferbuilder.func_181662_b(d16, d0, d17).func_187315_a(0.4999, d23).func_181669_b(j, k, l, 255).func_181675_d();
            bufferbuilder.func_181662_b(d16, 0.0, d17).func_187315_a(0.4999, d22).func_181669_b(j, k, l, 255).func_181675_d();
            bufferbuilder.func_181662_b(d18, 0.0, d19).func_187315_a(0.0, d22).func_181669_b(j, k, l, 255).func_181675_d();
            bufferbuilder.func_181662_b(d18, d0, d19).func_187315_a(0.0, d23).func_181669_b(j, k, l, 255).func_181675_d();
            double d24 = 0.0;
            if (entity.field_70173_aa % 2 == 0) {
               d24 = 0.5;
            }

            bufferbuilder.func_181662_b(d4, d0, d5).func_187315_a(0.5, d24 + 0.5).func_181669_b(j, k, l, 255).func_181675_d();
            bufferbuilder.func_181662_b(d6, d0, d7).func_187315_a(1.0, d24 + 0.5).func_181669_b(j, k, l, 255).func_181675_d();
            bufferbuilder.func_181662_b(d10, d0, d11).func_187315_a(1.0, d24).func_181669_b(j, k, l, 255).func_181675_d();
            bufferbuilder.func_181662_b(d8, d0, d9).func_187315_a(0.5, d24).func_181669_b(j, k, l, 255).func_181675_d();
            tessellator.func_78381_a();
            GlStateManager.func_179121_F();
         }
      }
   }

   private Vec3d getPosition(EntityLivingBase entityLivingBaseIn, double p_177110_2_, float p_177110_4_) {
      double d0 = entityLivingBaseIn.field_70142_S + (entityLivingBaseIn.field_70165_t - entityLivingBaseIn.field_70142_S) * p_177110_4_;
      double d1 = p_177110_2_ + entityLivingBaseIn.field_70137_T + (entityLivingBaseIn.field_70163_u - entityLivingBaseIn.field_70137_T) * p_177110_4_;
      double d2 = entityLivingBaseIn.field_70136_U + (entityLivingBaseIn.field_70161_v - entityLivingBaseIn.field_70136_U) * p_177110_4_;
      return new Vec3d(d0, d1, d2);
   }

   public void doRenderCosmical(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
      if (!entity.getCloneC()) {
         if (!MinecraftForge.EVENT_BUS.post(new Pre(entity, this, partialTicks, x, y, z))) {
            GlStateManager.func_179094_E();
            GlStateManager.func_179129_p();
            this.mainModel2.field_78095_p = this.func_77040_d(entity, partialTicks);
            boolean shouldSit = entity.func_184218_aH() && entity.func_184187_bx() != null && entity.func_184187_bx().shouldRiderSit();
            this.mainModel2.field_78093_q = shouldSit;
            this.mainModel2.field_78091_s = entity.func_70631_g_();

            try {
               float f = this.func_77034_a(entity.field_70760_ar, entity.field_70761_aq, partialTicks);
               float f1 = this.func_77034_a(entity.field_70758_at, entity.field_70759_as, partialTicks);
               float f2 = f1 - f;
               if (shouldSit && entity.func_184187_bx() instanceof EntityLivingBase) {
                  EntityLivingBase entitylivingbase = (EntityLivingBase)entity.func_184187_bx();
                  f = this.func_77034_a(entitylivingbase.field_70760_ar, entitylivingbase.field_70761_aq, partialTicks);
                  f2 = f1 - f;
                  float f3 = MathHelper.func_76142_g(f2);
                  if (f3 < -85.0F) {
                     f3 = -85.0F;
                  }

                  if (f3 >= 85.0F) {
                     f3 = 85.0F;
                  }

                  f = f1 - f3;
                  if (f3 * f3 > 2500.0F) {
                     f += f3 * 0.2F;
                  }

                  f2 = f1 - f;
               }

               float f7 = entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * partialTicks;
               this.func_77039_a(entity, x, y, z);
               float f8 = this.func_77044_a(entity, partialTicks);
               this.func_77043_a(entity, f8, f, partialTicks);
               float f4 = this.prepareScaleCosmical(entity, partialTicks);
               float f5 = 0.0F;
               float f6 = 0.0F;
               if (!entity.func_184218_aH()) {
                  f5 = entity.field_184618_aE + (entity.field_70721_aZ - entity.field_184618_aE) * partialTicks;
                  f6 = entity.field_184619_aG - entity.field_70721_aZ * (1.0F - partialTicks);
                  if (entity.func_70631_g_()) {
                     f6 *= 3.0F;
                  }

                  if (f5 > 1.0F) {
                     f5 = 1.0F;
                  }

                  f2 = f1 - f;
               }

               GlStateManager.func_179141_d();
               this.mainModel2.func_78086_a(entity, f6, f5, partialTicks);
               this.mainModel2.setRotationAnglesCosmical(f6, f5, f8, f2, f7, f4, entity);
               if (this.field_188301_f) {
                  boolean flag1 = this.func_177088_c(entity);
                  GlStateManager.func_179142_g();
                  GlStateManager.func_187431_e(this.func_188298_c(entity));
                  if (!this.field_188323_j) {
                     this.renderModelCosmical(entity, f6, f5, f8, f2, f7, f4);
                  }

                  this.func_177093_a(entity, f6, f5, partialTicks, f8, f2, f7, f4);
                  GlStateManager.func_187417_n();
                  GlStateManager.func_179119_h();
                  if (flag1) {
                     this.func_180565_e();
                  }
               } else {
                  this.renderModelCosmical(entity, f6, f5, f8, f2, f7, f4);
                  GlStateManager.func_179132_a(true);
                  this.func_177093_a(entity, f6, f5, partialTicks, f8, f2, f7, f4);
               }

               GlStateManager.func_179101_C();
            } catch (Exception var20) {
            }

            GlStateManager.func_179138_g(OpenGlHelper.field_77476_b);
            GlStateManager.func_179098_w();
            GlStateManager.func_179138_g(OpenGlHelper.field_77478_a);
            GlStateManager.func_179089_o();
            GlStateManager.func_179121_F();
            MinecraftForge.EVENT_BUS.post(new Post(entity, this, partialTicks, x, y, z));
         }
      }
   }

   protected void renderModel(
      T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor
   ) {
      if (entitylivingbaseIn.getCloneC()) {
         boolean flag = this.func_193115_c(entitylivingbaseIn);
         boolean flag1 = !flag && !entitylivingbaseIn.func_98034_c(Minecraft.func_71410_x().field_71439_g);
         if (flag || flag1) {
            if (!this.bindEntityTextureCosmical(entitylivingbaseIn)) {
               return;
            }

            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 0.5F);
            GlStateManager.func_179132_a(true);
            GlStateManager.func_179147_l();
            GlStateManager.func_187401_a(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
            GlStateManager.func_179092_a(516, 0.003921569F);
            if (entitylivingbaseIn.getShadowStatus()) {
               this.mainModel2.func_78088_a(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
            }

            GlStateManager.func_179084_k();
            GlStateManager.func_179092_a(516, 0.1F);
            GlStateManager.func_179132_a(true);
         }
      } else {
         boolean flag = this.func_193115_c(entitylivingbaseIn);
         boolean flag1 = !flag && !entitylivingbaseIn.func_98034_c(Minecraft.func_71410_x().field_71439_g);
         if (flag || flag1) {
            if (!this.func_180548_c(entitylivingbaseIn)) {
               return;
            }

            if (flag1) {
               GlStateManager.func_187408_a(Profile.TRANSPARENT_MODEL);
            }

            this.field_77045_g.func_78088_a(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
            if (flag1) {
               GlStateManager.func_187440_b(Profile.TRANSPARENT_MODEL);
            }
         }
      }
   }

   protected void renderModelCosmical(
      T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor
   ) {
      boolean flag = this.func_193115_c(entitylivingbaseIn);
      boolean flag1 = !flag && !entitylivingbaseIn.func_98034_c(Minecraft.func_71410_x().field_71439_g);
      if (flag || flag1) {
         if (!this.bindEntityTextureCosmical(entitylivingbaseIn)) {
            return;
         }

         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, entitylivingbaseIn.shadowDamageR);
         GlStateManager.func_179132_a(true);
         GlStateManager.func_179147_l();
         GlStateManager.func_187401_a(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
         GlStateManager.func_179092_a(516, 0.003921569F);
         if (entitylivingbaseIn.getShadowStatus()) {
            this.mainModel2.renderC(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
         }

         GlStateManager.func_179084_k();
         GlStateManager.func_179092_a(516, 0.1F);
         GlStateManager.func_179132_a(true);
      }
   }

   protected abstract ResourceLocation getEntityTextureCosmical(T var1);

   protected boolean bindEntityTextureCosmical(T entity) {
      ResourceLocation resourcelocation = this.getEntityTextureCosmical(entity);
      if (resourcelocation == null) {
         return false;
      } else {
         this.func_110776_a(resourcelocation);
         return true;
      }
   }

   protected void preRenderCallback(T entitylivingbaseIn, float partialTickTime) {
      if (entitylivingbaseIn.getCloneC()) {
         GlStateManager.func_179139_a(1.2, 1.2, 1.2);
      }
   }

   protected float prepareScaleCosmical(T entitylivingbaseIn, float partialTicks) {
      GlStateManager.func_179091_B();
      GlStateManager.func_179152_a(-1.0F, -1.0F, 1.0F);
      this.preRenderCallbackCosmical(entitylivingbaseIn, partialTicks);
      float f = 0.0625F;
      GlStateManager.func_179109_b(0.0F, -1.85F, 0.0F);
      return 0.0625F;
   }

   protected void preRenderCallbackCosmical(T entitylivingbaseIn, float partialTickTime) {
      GlStateManager.func_179139_a(1.2, 1.2, 1.2);
   }
}
