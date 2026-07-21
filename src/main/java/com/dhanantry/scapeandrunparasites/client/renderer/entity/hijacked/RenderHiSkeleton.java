package com.dhanantry.scapeandrunparasites.client.renderer.entity.hijacked;

import com.dhanantry.scapeandrunparasites.client.model.entity.hijacked.ModelHiSkeleton;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderSRP;
import com.dhanantry.scapeandrunparasites.entity.monster.hijacked.EntityHiSkeleton;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderHiSkeleton extends RenderSRP<EntityHiSkeleton> {
   public static final ResourceLocation TEXTURE = new ResourceLocation("srparasites:textures/entity/monster/hiskeleton.png");

   public RenderHiSkeleton(RenderManager manager) {
      super(manager, new ModelHiSkeleton(), 0.6F);
   }

   protected ResourceLocation getEntityTexture(EntityHiSkeleton entity) {
      return TEXTURE;
   }

   protected void applyRotations(EntityHiSkeleton entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      super.func_77043_a(entityLiving, ageInTicks, rotationYaw, partialTicks);
   }
}
