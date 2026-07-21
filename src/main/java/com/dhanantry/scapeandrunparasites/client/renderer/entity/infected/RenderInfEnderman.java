package com.dhanantry.scapeandrunparasites.client.renderer.entity.infected;

import com.dhanantry.scapeandrunparasites.client.model.entity.infected.ModelInfEnderman;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderSRP;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfEnderman;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderInfEnderman extends RenderSRP<EntityInfEnderman> {
   public static final ResourceLocation TEXTURE = new ResourceLocation("srparasites:textures/entity/monster/infenderman.png");
   public static final ResourceLocation TEXTURE_1 = new ResourceLocation("srparasites:textures/entity/monster/infenderman1.png");
   public static final ResourceLocation TEXTURE_ARIRAL = new ResourceLocation("srparasites:textures/entity/monster/infenderman_ariral.png");

   public RenderInfEnderman(RenderManager manager) {
      super(manager, new ModelInfEnderman(), 0.5F);
   }

   protected void preRenderCallback(EntityInfEnderman entitylivingbaseIn, float partialTickTime) {
      float f = entitylivingbaseIn.getSelfeFlashIntensity(partialTickTime);
      float f1 = 1.0F + MathHelper.func_76126_a(f * 100.0F) * f * 0.01F;
      f = MathHelper.func_76131_a(f, 0.0F, 1.0F);
      f *= f;
      f *= f;
      float f2 = (1.0F + f * 0.4F) * f1;
      float f3 = (1.0F + f * 0.1F) / f1;
      GlStateManager.func_179152_a(f2, f3, f2);
   }

   protected ResourceLocation getEntityTexture(EntityInfEnderman entity) {
      if (entity.isAriral()) {
         return TEXTURE_ARIRAL;
      } else {
         return entity.getTextureVariant() == 1 ? TEXTURE_1 : TEXTURE;
      }
   }

   protected void applyRotations(EntityInfEnderman entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      super.func_77043_a(entityLiving, ageInTicks, rotationYaw, partialTicks);
   }
}
