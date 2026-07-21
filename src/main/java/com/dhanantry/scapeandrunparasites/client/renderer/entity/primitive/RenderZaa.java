package com.dhanantry.scapeandrunparasites.client.renderer.entity.primitive;

import com.dhanantry.scapeandrunparasites.client.model.entity.primitive.ModelZaa;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderMalleable;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityZaa;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderZaa extends RenderMalleable<EntityZaa> {
   public static final ResourceLocation TEXTUREH = new ResourceLocation("srparasites:textures/entity/monster/zaa.png");
   public static final ResourceLocation FROZEN_TEXTURE = new ResourceLocation("srparasites:textures/entity/monster/snowvariants/primitiveburrowerfrozen.png");

   public RenderZaa(RenderManager manager) {
      super(manager, new ModelZaa(), 0.0F);
   }

   protected ResourceLocation getEntityTexture(EntityZaa entity) {
      return TEXTUREH;
   }

   protected void preRenderCallback(EntityZaa entitylivingbaseIn, float partialTickTime) {
      float xx = 1.0F;
      float yy = 1.0F;
      switch (entitylivingbaseIn.getBodyNumber()) {
         case 1:
            xx = 1.23F;
            yy = 1.23F;
            break;
         case 2:
            xx = 1.47F;
            yy = 1.47F;
            break;
         case 3:
            xx = 1.23F;
            yy = 1.27F;
            break;
         case 4:
            xx = 1.05F;
            yy = 1.17F;
      }

      float f = entitylivingbaseIn.getSelfeFlashIntensity(partialTickTime);
      float f1 = 1.0F + MathHelper.func_76126_a(f * 100.0F) * f * 0.01F;
      f = MathHelper.func_76131_a(f, 0.0F, 1.0F);
      f *= f;
      f *= f;
      float f2 = (1.0F + f * 0.4F) * f1;
      float f3 = (1.0F + f * 0.1F) / f1;
      GlStateManager.func_179152_a(xx * f2, yy * f3, f2);
   }
}
