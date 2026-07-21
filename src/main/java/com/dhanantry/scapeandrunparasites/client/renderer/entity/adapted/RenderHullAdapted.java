package com.dhanantry.scapeandrunparasites.client.renderer.entity.adapted;

import com.dhanantry.scapeandrunparasites.client.model.entity.adapted.ModelHullAdapted;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderMalleable;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityHullAdapted;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderHullAdapted extends RenderMalleable<EntityHullAdapted> {
   public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/hulla_old.png");
   public static final ResourceLocation TEXTUREH = new ResourceLocation("srparasites:textures/entity/monster/hullah_old.png");
   public static final ResourceLocation TEXTURE_FROZEN = new ResourceLocation("srparasites:textures/entity/monster/snowvariants/test.png");
   private float secShadow = 1.0F;

   public RenderHullAdapted(RenderManager manager) {
      super(manager, new ModelHullAdapted(), 1.0F);
   }

   protected void preRenderCallback(EntityHullAdapted entitylivingbaseIn, float partialTickTime) {
      float f = entitylivingbaseIn.getSelfeFlashIntensity(partialTickTime);
      float f1 = 1.0F + MathHelper.func_76126_a(f * 100.0F) * f * 0.01F;
      f = MathHelper.func_76131_a(f, 0.0F, 1.0F);
      f *= f;
      f *= f;
      float f2 = (1.0F + f * 0.4F) * f1;
      float f3 = (1.0F + f * 0.1F) / f1;
      GlStateManager.func_179152_a(f2, f3, f2);
   }

   protected ResourceLocation getEntityTexture(EntityHullAdapted entity) {
      switch (entity.getSkin()) {
         case 7:
            return TEXTUREH;
         case 120:
            return TEXTURE_FROZEN;
         default:
            return TEXTURES;
      }
   }

   protected void renderModel(
      EntityHullAdapted entitylivingbaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor
   ) {
      boolean flag = this.func_193115_c(entitylivingbaseIn);
      boolean flag1 = !flag;
      boolean flag2 = entitylivingbaseIn.getSSS();
      if (flag || flag1 || flag2) {
         float f1 = 0.15F;
         if (flag2) {
            f1 = 0.02F;
         }

         if (!this.func_180548_c(entitylivingbaseIn)) {
            return;
         }

         if (flag1 || flag2) {
            this.field_76989_e = 0.0F;
            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, f1);
            GlStateManager.func_179132_a(false);
            GlStateManager.func_179147_l();
            GlStateManager.func_187401_a(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
            GlStateManager.func_179092_a(516, 0.003921569F);
         }

         this.field_77045_g.func_78088_a(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
         if (!flag1 && !flag2) {
            this.field_76989_e = this.secShadow;
         } else {
            GlStateManager.func_179084_k();
            GlStateManager.func_179092_a(516, 0.1F);
            GlStateManager.func_179132_a(true);
         }
      }
   }
}
