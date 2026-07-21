package com.dhanantry.scapeandrunparasites.client.renderer.entity.infected;

import com.dhanantry.scapeandrunparasites.client.model.entity.infected.ModelInfSheep;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderSRP;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfSheep;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderInfSheep extends RenderSRP<EntityInfSheep> {
   private static final ResourceLocation TEX_WHITE = new ResourceLocation("srparasites", "textures/entity/monster/sheep.png");
   private static final ResourceLocation TEX_GREY = new ResourceLocation("srparasites", "textures/entity/monster/sheep_grey.png");
   private static final ResourceLocation TEX_BLACK = new ResourceLocation("srparasites", "textures/entity/monster/sheep_black.png");

   public RenderInfSheep(RenderManager manager) {
      super(manager, new ModelInfSheep(), 0.5F);
   }

   protected void preRenderCallback(EntityInfSheep entitylivingbaseIn, float partialTickTime) {
      float f = entitylivingbaseIn.getSelfeFlashIntensity(partialTickTime);
      float ff = entitylivingbaseIn.getSelfeFlashIntensity2();
      float f1 = 1.0F + MathHelper.func_76126_a(f * 100.0F) * f * 0.01F;
      f = MathHelper.func_76131_a(f, 0.0F, 1.0F);
      f *= f;
      f *= f;
      float f2 = (1.0F + f * 0.4F) * f1;
      float f3 = (1.0F + f * 0.1F) / f1;
      GlStateManager.func_179152_a(f2, ff * f3, f2);
   }

   protected ResourceLocation getEntityTexture(EntityInfSheep entity) {
      switch (entity.getTextureVariant()) {
         case 1:
            return TEX_GREY;
         case 2:
            return TEX_BLACK;
         default:
            return TEX_WHITE;
      }
   }

   protected void applyRotations(EntityInfSheep entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      super.func_77043_a(entityLiving, ageInTicks, rotationYaw, partialTicks);
   }
}
