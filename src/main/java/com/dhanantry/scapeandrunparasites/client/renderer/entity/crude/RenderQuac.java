package com.dhanantry.scapeandrunparasites.client.renderer.entity.crude;

import com.dhanantry.scapeandrunparasites.client.model.entity.crude.ModelQuac;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderSRP;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityQuac;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderQuac extends RenderSRP<EntityQuac> {
   public static final ResourceLocation TEXTUREH = new ResourceLocation("srparasites:textures/entity/monster/quac.png");
   public static final ResourceLocation TEXTURE_FROZEN = new ResourceLocation("srparasites:textures/entity/monster/snowvariants/test.png");

   public RenderQuac(RenderManager manager) {
      super(manager, new ModelQuac(), 0.0F);
   }

   protected ResourceLocation getEntityTexture(EntityQuac entity) {
      switch (entity.getSkin()) {
         case 120:
            return TEXTURE_FROZEN;
         default:
            return TEXTUREH;
      }
   }

   protected void preRenderCallback(EntityQuac entitylivingbaseIn, float partialTickTime) {
      float xx = 1.0F;
      float yy = 1.0F;
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
