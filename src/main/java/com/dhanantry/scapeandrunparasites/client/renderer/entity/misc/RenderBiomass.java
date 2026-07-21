package com.dhanantry.scapeandrunparasites.client.renderer.entity.misc;

import com.dhanantry.scapeandrunparasites.client.model.entity.misc.ModelBiomassPod;
import com.dhanantry.scapeandrunparasites.client.model.entity.misc.ModelBiomassVenkrol;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderSRP;
import com.dhanantry.scapeandrunparasites.entity.monster.EntityBiomass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.Profile;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderBiomass extends RenderSRP<EntityBiomass> {
   protected static ModelBase modelV = new ModelBiomassVenkrol();
   protected static ModelBase modelP = new ModelBiomassPod();
   public static final ResourceLocation TEXTUREV = new ResourceLocation("srparasites:textures/entity/monster/biomassvenkrol.png");
   public static final ResourceLocation TEXTUREP = new ResourceLocation("srparasites:textures/entity/monster/biomasspod.png");

   public RenderBiomass(RenderManager p_i47208_1_) {
      super(p_i47208_1_, modelV, 0.5F);
   }

   protected void preRenderCallback(EntityBiomass entitylivingbaseIn, float partialTickTime) {
      float f = entitylivingbaseIn.getSelfeFlashIntensity(partialTickTime);
      float f1 = 1.0F + MathHelper.func_76126_a(f * 100.0F) * f * 0.01F;
      f = MathHelper.func_76131_a(f, 0.0F, 1.0F);
      f *= f;
      f *= f;
      float f2 = (1.0F + f * 0.4F) * f1;
      float f3 = (1.0F + f * 0.1F) / f1;
      GlStateManager.func_179152_a(f2, f3, f2);
   }

   protected ResourceLocation getEntityTexture(EntityBiomass entity) {
      switch (entity.getSkin()) {
         case 1:
            return TEXTUREV;
         case 2:
            return TEXTUREV;
         case 3:
            return TEXTUREV;
         case 4:
            return TEXTUREP;
         case 5:
            return TEXTUREP;
         case 6:
            return TEXTUREP;
         default:
            return TEXTUREP;
      }
   }

   protected void applyRotations(EntityBiomass entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      super.func_77043_a(entityLiving, ageInTicks, rotationYaw, partialTicks);
   }

   protected void renderModel(
      EntityBiomass entitylivingbaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor
   ) {
      boolean flag = this.func_193115_c(entitylivingbaseIn);
      boolean flag1 = !flag && !entitylivingbaseIn.func_98034_c(Minecraft.func_71410_x().field_71439_g);
      if (flag || flag1) {
         if (!this.func_180548_c(entitylivingbaseIn)) {
            return;
         }

         if (flag1) {
            GlStateManager.func_187408_a(Profile.TRANSPARENT_MODEL);
         }

         switch (entitylivingbaseIn.getSkin()) {
            case 1:
               modelV.func_78088_a(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
               break;
            case 2:
               modelV.func_78088_a(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
               break;
            case 3:
               modelV.func_78088_a(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
               break;
            case 4:
               modelP.func_78088_a(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
               break;
            case 5:
               modelP.func_78088_a(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
               break;
            case 6:
               modelP.func_78088_a(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
         }

         if (flag1) {
            GlStateManager.func_187440_b(Profile.TRANSPARENT_MODEL);
         }
      }
   }
}
