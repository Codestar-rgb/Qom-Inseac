package com.dhanantry.scapeandrunparasites.client.renderer.entity.pure;

import com.dhanantry.scapeandrunparasites.client.model.entity.pure.ModelOrch;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderMalleable;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityOrch;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderOrch extends RenderMalleable<EntityOrch> {
   public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/orch.png");
   public static final ResourceLocation TEXTURESP = new ResourceLocation("srparasites:textures/entity/monster/orchsp1.png");
   public static final ResourceLocation TEXTUREH = new ResourceLocation("srparasites:textures/entity/monster/orchh.png");

   public RenderOrch(RenderManager manager) {
      super(manager, new ModelOrch(), 1.2F);
   }

   protected void preRenderCallback(EntityOrch entitylivingbaseIn, float partialTickTime) {
      float f = entitylivingbaseIn.getSelfeFlashIntensity(partialTickTime);
      float f1 = 1.0F + MathHelper.func_76126_a(f * 100.0F) * f * 0.01F;
      f = MathHelper.func_76131_a(f, 0.0F, 1.0F);
      f *= f;
      f *= f;
      float f2 = (1.0F + f * 0.4F) * f1;
      float f3 = (1.0F + f * 0.1F) / f1;
      GlStateManager.func_179152_a(f2, f3, f2);
   }

   protected ResourceLocation getEntityTexture(EntityOrch entity) {
      switch (entity.getSkin()) {
         case 1:
            return TEXTURESP;
         case 7:
            return TEXTUREH;
         default:
            return TEXTURES;
      }
   }

   protected void applyRotations(EntityOrch entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      super.func_77043_a(entityLiving, ageInTicks, rotationYaw, partialTicks);
   }
}
