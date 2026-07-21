package com.dhanantry.scapeandrunparasites.client.renderer.entity.crude;

import com.dhanantry.scapeandrunparasites.client.model.entity.crude.ModelHeed;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderSRP;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityHeed;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderHeed extends RenderSRP<EntityHeed> {
   public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/heed.png");
   public static final ResourceLocation TEXTURE_FROZEN = new ResourceLocation("srparasites:textures/entity/monster/snowvariants/test.png");

   public RenderHeed(RenderManager manager) {
      super(manager, new ModelHeed(), 0.8F);
   }

   protected void preRenderCallback(EntityHeed entitylivingbaseIn, float partialTickTime) {
      float f = entitylivingbaseIn.getSelfeFlashIntensity(partialTickTime);
      float f1 = 1.0F + MathHelper.func_76126_a(f * 100.0F) * f * 0.01F;
      f = MathHelper.func_76131_a(f, 0.0F, 1.0F);
      f *= f;
      f *= f;
      float f2 = (1.0F + f * 0.4F) * f1;
      float f3 = (1.0F + f * 0.1F) / f1;
      GlStateManager.func_179152_a(f2, f3, f2);
   }

   protected ResourceLocation getEntityTexture(EntityHeed entity) {
      switch (entity.getSkin()) {
         case 120:
            return TEXTURE_FROZEN;
         default:
            return TEXTURES;
      }
   }
}
