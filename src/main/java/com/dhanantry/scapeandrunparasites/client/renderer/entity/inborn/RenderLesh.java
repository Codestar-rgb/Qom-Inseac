package com.dhanantry.scapeandrunparasites.client.renderer.entity.inborn;

import com.dhanantry.scapeandrunparasites.client.model.entity.inborn.ModelLesh;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityLesh;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderLesh extends RenderLiving<EntityLesh> {
   public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/lesh.png");

   public RenderLesh(RenderManager manager) {
      super(manager, new ModelLesh(), 0.2F);
   }

   protected void preRenderCallback(EntityLesh entitylivingbaseIn, float partialTickTime) {
      float ff = entitylivingbaseIn.getSelfeFlashIntensityS();
      float f = entitylivingbaseIn.getSelfeFlashIntensity(partialTickTime);
      float f1 = 1.0F + MathHelper.func_76126_a(f * 100.0F) * f * 0.01F;
      f = MathHelper.func_76131_a(f, 0.0F, 1.0F);
      f *= f;
      f *= f;
      float f2 = (1.0F + f * 0.4F) * f1;
      float f3 = (1.0F + f * 0.1F) / f1;
      GlStateManager.func_179152_a(ff * f2, ff * f3, ff * f2);
   }

   protected ResourceLocation getEntityTexture(EntityLesh entity) {
      return TEXTURES;
   }

   protected void applyRotations(EntityLesh entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      super.func_77043_a(entityLiving, ageInTicks, rotationYaw, partialTicks);
   }
}
