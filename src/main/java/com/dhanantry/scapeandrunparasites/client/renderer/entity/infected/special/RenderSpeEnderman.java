package com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.special;

import com.dhanantry.scapeandrunparasites.client.model.entity.infected.special.ModelSpeEnderman;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderSRP;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.special.EntitySpeEnderman;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderSpeEnderman extends RenderSRP<EntitySpeEnderman> {
   public static final ResourceLocation TEXTURE = new ResourceLocation("srparasites:textures/entity/monster/speenderman.png");

   public RenderSpeEnderman(RenderManager manager) {
      super(manager, new ModelSpeEnderman(), 0.5F);
   }

   protected void preRenderCallback(EntitySpeEnderman entitylivingbaseIn, float partialTickTime) {
      float f = entitylivingbaseIn.getSelfeFlashIntensity(partialTickTime);
      float f1 = 1.0F + MathHelper.func_76126_a(f * 100.0F) * f * 0.01F;
      f = MathHelper.func_76131_a(f, 0.0F, 1.0F);
      f *= f;
      f *= f;
      float f2 = (1.0F + f * 0.4F) * f1;
      float f3 = (1.0F + f * 0.1F) / f1;
      GlStateManager.func_179152_a(f2 * 1.1F, f3 * 1.1F, f2 * 1.1F);
   }

   protected ResourceLocation getEntityTexture(EntitySpeEnderman entity) {
      return TEXTURE;
   }

   protected void applyRotations(EntitySpeEnderman entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      super.func_77043_a(entityLiving, ageInTicks, rotationYaw, partialTicks);
   }
}
