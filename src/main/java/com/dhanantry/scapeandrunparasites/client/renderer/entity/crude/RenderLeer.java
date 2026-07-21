package com.dhanantry.scapeandrunparasites.client.renderer.entity.crude;

import com.dhanantry.scapeandrunparasites.client.model.entity.crude.ModelLeer;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderSRP;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityLeer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class RenderLeer extends RenderSRP<EntityLeer> {
   public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/leer.png");
   private static final ResourceLocation GUARDIAN_BEAM_TEXTURE = new ResourceLocation("srparasites:textures/entity/monster/test.png");
   public static final ResourceLocation TEXTURE_FROZEN = new ResourceLocation("srparasites:textures/entity/monster/snowvariants/test.png");

   public RenderLeer(RenderManager manager) {
      super(manager, new ModelLeer(), 0.8F);
   }

   protected void preRenderCallback(EntityLeer entitylivingbaseIn, float partialTickTime) {
      float f = entitylivingbaseIn.getSelfeFlashIntensity(partialTickTime);
      float f1 = 1.0F + MathHelper.func_76126_a(f * 100.0F) * f * 0.01F;
      f = MathHelper.func_76131_a(f, 0.0F, 1.0F);
      f *= f;
      f *= f;
      float f2 = (1.0F + f * 0.4F) * f1;
      float f3 = (1.0F + f * 0.1F) / f1;
      GlStateManager.func_179152_a(f2, f3, f2);
   }

   protected ResourceLocation getEntityTexture(EntityLeer entity) {
      switch (entity.getSkin()) {
         case 120:
            return TEXTURE_FROZEN;
         default:
            return TEXTURES;
      }
   }

   public void doRender(EntityLeer entity, double x, double y, double z, float entityYaw, float partialTicks) {
      super.func_76986_a(entity, x, y, z, entityYaw, partialTicks);

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
            int j = 64 + (int)(f7 * 191.0F);
            int k = 32 + (int)(f7 * 191.0F);
            int l = 128 - (int)(f7 * 64.0F);
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
}
