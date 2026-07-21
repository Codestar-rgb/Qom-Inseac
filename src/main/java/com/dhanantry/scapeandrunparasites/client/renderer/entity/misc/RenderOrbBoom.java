package com.dhanantry.scapeandrunparasites.client.renderer.entity.misc;

import com.dhanantry.scapeandrunparasites.client.model.ModelSRP;
import com.dhanantry.scapeandrunparasites.client.model.entity.misc.ModelOrbScary;
import com.dhanantry.scapeandrunparasites.entity.EntityOrbBoom;
import java.util.HashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;

public class RenderOrbBoom extends Render<EntityOrbBoom> {
   protected ModelSRP mainModel2 = new ModelOrbScary();
   public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/orbboom.png");
   private static final ResourceLocation LIGHTNING_TEX = new ResourceLocation("srparasites:textures/entity/monster/orbboom_armor.png");
   private static final float SPHERE_RADIUS = 0.317F;
   private static final int SPHERE_STACKS = 18;
   private static final int SPHERE_SLICES = 18;
   private final HashMap<Integer, Float> scaleSmooth = new HashMap<>();

   public RenderOrbBoom(RenderManager p_i47208_1_) {
      super(p_i47208_1_);
   }

   protected ResourceLocation getEntityTexture(EntityOrbBoom entity) {
      return TEXTURES;
   }

   public boolean shouldRender(EntityOrbBoom livingEntity, ICamera camera, double camX, double camY, double camZ) {
      return true;
   }

   public void doRender(EntityOrbBoom entity, double x, double y, double z, float entityYaw, float partialTicks) {
      super.func_76986_a(entity, x, y, z, entityYaw, partialTicks);
      this.doRenderCosmical(entity, x, y, z, entityYaw, partialTicks);
   }

   public void doRenderCosmical(EntityOrbBoom entity, double x, double y, double z, float entityYaw, float partialTicks) {
      GlStateManager.func_179094_E();
      GlStateManager.func_179129_p();
      boolean shouldSit = entity.func_184218_aH() && entity.func_184187_bx() != null && entity.func_184187_bx().shouldRiderSit();
      this.mainModel2.field_78093_q = shouldSit;

      try {
         float f = this.interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, partialTicks);
         float f1 = this.interpolateRotation(entity.prevRotationYawHead, entity.rotationYawHead, partialTicks);
         float f2 = f1 - f;
         if (shouldSit && entity.func_184187_bx() instanceof EntityLivingBase) {
            EntityLivingBase entitylivingbase = (EntityLivingBase)entity.func_184187_bx();
            f = this.interpolateRotation(entitylivingbase.field_70760_ar, entitylivingbase.field_70761_aq, partialTicks);
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
         this.renderLivingAt(entity, x, y, z);
         float f8 = this.handleRotationFloat(entity, partialTicks);
         this.applyRotations(entity, f8, f, partialTicks);
         float f4 = this.prepareScaleCosmical(entity, partialTicks);
         float f5 = 0.0F;
         float f6 = 0.0F;
         if (!entity.func_184218_aH()) {
            f5 = entity.prevLimbSwingAmount + (entity.limbSwingAmount - entity.prevLimbSwingAmount) * partialTicks;
            f6 = entity.limbSwing - entity.limbSwingAmount * (1.0F - partialTicks);
            if (f5 > 1.0F) {
               f5 = 1.0F;
            }

            f2 = f1 - f;
         }

         GlStateManager.func_179141_d();
         this.mainModel2.setLivingAnimations(entity, f6, f5, partialTicks);
         this.mainModel2.func_78087_a(f6, f5, f8, f2, f7, f4, entity);
         this.mainModel2.setRotationAnglesCosmical(f6, f5, f8, f2, f7, f4, entity);
         if (this.field_188301_f) {
            GlStateManager.func_179142_g();
            GlStateManager.func_187431_e(this.func_188298_c(entity));
            GlStateManager.func_187417_n();
            GlStateManager.func_179119_h();
         } else {
            this.renderModelCosmical(entity, f6, f5, f8, f2, f7, f4, partialTicks);
            GlStateManager.func_179132_a(true);
         }

         GlStateManager.func_179101_C();
      } catch (Exception var19) {
      }

      GlStateManager.func_179138_g(OpenGlHelper.field_77476_b);
      GlStateManager.func_179098_w();
      GlStateManager.func_179138_g(OpenGlHelper.field_77478_a);
      GlStateManager.func_179089_o();
      GlStateManager.func_179121_F();
   }

   protected void renderModelCosmical(
      EntityOrbBoom e, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, float partialTicks
   ) {
      boolean flag = false;
      boolean flag1 = !flag && !e.func_98034_c(Minecraft.func_71410_x().field_71439_g);
      if (flag || flag1) {
         if (!this.bindEntityTextureCosmical(e)) {
            return;
         }

         boolean cameraInside = this.isCameraInsideOrb(e, partialTicks);
         float prevBX = OpenGlHelper.lastBrightnessX;
         float prevBY = OpenGlHelper.lastBrightnessY;
         GlStateManager.func_179138_g(OpenGlHelper.field_77476_b);
         GlStateManager.func_179098_w();
         OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, 240.0F, 240.0F);
         GlStateManager.func_179138_g(OpenGlHelper.field_77478_a);
         GlStateManager.func_179126_j();
         GlStateManager.func_179143_c(515);
         GlStateManager.func_179132_a(true);
         GlStateManager.func_179140_f();
         GlStateManager.func_179084_k();
         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
         this.renderTexturedSphere(0.317F, 18, 18);
         GlStateManager.func_179145_e();
         GlStateManager.func_179138_g(OpenGlHelper.field_77476_b);
         OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, prevBX, prevBY);
         GlStateManager.func_179138_g(OpenGlHelper.field_77478_a);
         GlStateManager.func_179132_a(false);
         GlStateManager.func_179147_l();
         GlStateManager.func_187401_a(SourceFactor.ONE, DestFactor.ONE);
         this.renderChargedAura(e, partialTicks);
         GlStateManager.func_179084_k();
         GlStateManager.func_179132_a(true);
         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      }
   }

   private boolean isCameraInsideOrb(EntityOrbBoom e, float partialTicks) {
      Entity view = Minecraft.func_71410_x().func_175606_aa();
      if (view == null) {
         return false;
      } else {
         Vec3d eye = view.func_174824_e(partialTicks);
         double GROW = 1.25;
         AxisAlignedBB bb = e.func_174813_aQ().func_186662_g(1.25);
         return bb.func_72318_a(eye);
      }
   }

   private void renderChargedAura(EntityOrbBoom e, float partialTicks) {
      this.func_110776_a(LIGHTNING_TEX);
      GlStateManager.func_179094_E();
      float sca = 1.12F;
      GlStateManager.func_179152_a(sca, sca, sca);
      GlStateManager.func_179140_f();
      GlStateManager.func_179132_a(false);
      GlStateManager.func_179147_l();
      GlStateManager.func_187401_a(SourceFactor.ONE, DestFactor.ONE);
      float prevBX = OpenGlHelper.lastBrightnessX;
      float prevBY = OpenGlHelper.lastBrightnessY;
      GlStateManager.func_179138_g(OpenGlHelper.field_77476_b);
      GlStateManager.func_179098_w();
      OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, 240.0F, 240.0F);
      GlStateManager.func_179138_g(OpenGlHelper.field_77478_a);
      float t = e.field_70173_aa + partialTicks;
      float pulse = 0.25F + 0.15F * MathHelper.func_76126_a(t * 0.25F);
      GlStateManager.func_179131_c(0.6F, 0.85F, 1.0F, pulse);
      float uScroll = t * 0.01F;
      float vScroll = t * 0.015F;
      this.renderSwirlSphere(0.317F, 18, 18, uScroll, vScroll);
      GlStateManager.func_179138_g(OpenGlHelper.field_77476_b);
      OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, prevBX, prevBY);
      GlStateManager.func_179138_g(OpenGlHelper.field_77478_a);
      GlStateManager.func_179084_k();
      GlStateManager.func_179132_a(true);
      GlStateManager.func_179145_e();
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.func_179121_F();
   }

   private void renderSwirlSphere(float radius, int stacks, int slices, float uOff, float vOff) {
      Tessellator tess = Tessellator.func_178181_a();
      BufferBuilder bb = tess.func_178180_c();
      float uScale = 2.0F;
      float vScale = 2.0F;

      for (int i = 0; i < stacks; i++) {
         float v0 = (float)i / stacks;
         float v1 = (float)(i + 1) / stacks;
         double phi0 = Math.PI * v0;
         double phi1 = Math.PI * v1;
         bb.func_181668_a(5, DefaultVertexFormats.field_181707_g);

         for (int j = 0; j <= slices; j++) {
            float u = (float)j / slices;
            double theta = (Math.PI * 2) * u;
            double x0 = Math.sin(phi0) * Math.cos(theta);
            double y0 = Math.cos(phi0);
            double z0 = Math.sin(phi0) * Math.sin(theta);
            double x1 = Math.sin(phi1) * Math.cos(theta);
            double y1 = Math.cos(phi1);
            double z1 = Math.sin(phi1) * Math.sin(theta);
            float uu = u * 2.0F + uOff;
            float vv0 = (1.0F - v0) * 2.0F + vOff;
            float vv1 = (1.0F - v1) * 2.0F + vOff;
            bb.func_181662_b(x0 * radius, y0 * radius, z0 * radius).func_187315_a(uu, vv0).func_181675_d();
            bb.func_181662_b(x1 * radius, y1 * radius, z1 * radius).func_187315_a(uu, vv1).func_181675_d();
         }

         tess.func_78381_a();
      }
   }

   private void renderTexturedSphere(float radius, int stacks, int slices) {
      Tessellator tess = Tessellator.func_178181_a();
      BufferBuilder bb = tess.func_178180_c();

      for (int i = 0; i < stacks; i++) {
         float v0 = (float)i / stacks;
         float v1 = (float)(i + 1) / stacks;
         double phi0 = Math.PI * v0;
         double phi1 = Math.PI * v1;
         bb.func_181668_a(5, DefaultVertexFormats.field_181707_g);

         for (int j = 0; j <= slices; j++) {
            float u = (float)j / slices;
            double theta = (Math.PI * 2) * u;
            double x0 = Math.sin(phi0) * Math.cos(theta);
            double y0 = Math.cos(phi0);
            double z0 = Math.sin(phi0) * Math.sin(theta);
            double x1 = Math.sin(phi1) * Math.cos(theta);
            double y1 = Math.cos(phi1);
            double z1 = Math.sin(phi1) * Math.sin(theta);
            bb.func_181662_b(x0 * radius, y0 * radius, z0 * radius).func_187315_a(u, 1.0F - v0).func_181675_d();
            bb.func_181662_b(x1 * radius, y1 * radius, z1 * radius).func_187315_a(u, 1.0F - v1).func_181675_d();
         }

         tess.func_78381_a();
      }
   }

   protected boolean bindEntityTextureCosmical(EntityOrbBoom entity) {
      ResourceLocation resourcelocation = this.getEntityTexture(entity);
      if (resourcelocation == null) {
         return false;
      } else {
         this.func_110776_a(resourcelocation);
         return true;
      }
   }

   protected float prepareScaleCosmical(EntityOrbBoom entitylivingbaseIn, float partialTicks) {
      GlStateManager.func_179091_B();
      GlStateManager.func_179152_a(-1.0F, -1.0F, 1.0F);
      this.preRenderCallbackCosmical(entitylivingbaseIn, partialTicks);
      return 0.0625F;
   }

   protected void preRenderCallbackCosmical(EntityOrbBoom e, float partialTickTime) {
      float age = e.field_70173_aa + partialTickTime;
      float base = Math.max(e.field_70130_N * 2.0F, e.field_70131_O * 1.9F) * 0.8F;
      float GROW_TICKS = 35.0F;
      float g = MathHelper.func_76131_a(age / 35.0F, 0.0F, 1.0F);
      g = g * g * (3.0F - 2.0F * g);
      float normalScale = base * (0.35F + 0.95F * g);
      float tau = MathHelper.func_76131_a(age / 35.0F, 0.0F, 1.0F);
      float BOUNCE_AMP = 0.28F;
      float DAMP = 5.0F;
      float FREQ = 10.0F;
      float bounce = 0.28F * (float)Math.exp(-5.0F * tau) * MathHelper.func_76126_a(10.0F * tau * (float) Math.PI);
      float targetScale = normalScale * (1.0F + bounce);
      if (targetScale < 0.001F) {
         targetScale = 0.001F;
      }

      int id = e.func_145782_y();
      Float prev = this.scaleSmooth.get(id);
      if (prev == null) {
         prev = targetScale;
      }

      float smooth = prev + (targetScale - prev) * 0.2F;
      this.scaleSmooth.put(id, smooth);
      if (e.field_70128_L) {
         this.scaleSmooth.remove(id);
      }

      GlStateManager.func_179152_a(smooth, smooth, smooth);
      float hoverAmp = 0.05F * (0.2F + 0.8F * g);
      float hover = MathHelper.func_76126_a(age * 0.1F + id * 0.3F) * hoverAmp;
      float yaw = age * 1.4F % 360.0F;
      float pitch = 8.0F * MathHelper.func_76126_a(age * 0.07F + id);
      GlStateManager.func_179114_b(yaw, 0.0F, 1.0F, 0.0F);
      GlStateManager.func_179114_b(pitch, 1.0F, 0.0F, 0.0F);
   }

   protected float interpolateRotation(float prevYawOffset, float yawOffset, float partialTicks) {
      float f = yawOffset - prevYawOffset;

      while (f < -180.0F) {
         f += 360.0F;
      }

      while (f >= 180.0F) {
         f -= 360.0F;
      }

      return prevYawOffset + partialTicks * f;
   }

   protected void renderLivingAt(EntityOrbBoom entityLivingBaseIn, double x, double y, double z) {
      GlStateManager.func_179109_b((float)x, (float)y, (float)z);
   }

   protected float handleRotationFloat(EntityOrbBoom livingBase, float partialTicks) {
      return livingBase.field_70173_aa + partialTicks;
   }

   protected void applyRotations(EntityOrbBoom entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      GlStateManager.func_179114_b(180.0F - rotationYaw, 0.0F, 1.0F, 0.0F);
      if (entityLiving.deathTime > 0) {
         float f = (entityLiving.deathTime + partialTicks - 1.0F) / 20.0F * 1.6F;
         f = MathHelper.func_76129_c(f);
         if (f > 1.0F) {
            f = 1.0F;
         }

         GlStateManager.func_179114_b(f * 90.0F, 0.0F, 0.0F, 1.0F);
      } else {
         String s = TextFormatting.func_110646_a(entityLiving.func_70005_c_());
         if (s != null && ("Dinnerbone".equals(s) || "Grumm".equals(s))) {
            GlStateManager.func_179109_b(0.0F, entityLiving.field_70131_O + 0.1F, 0.0F);
            GlStateManager.func_179114_b(180.0F, 0.0F, 0.0F, 1.0F);
         }
      }
   }
}
