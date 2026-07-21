package com.dhanantry.scapeandrunparasites.client.renderer.entity.hijacked;

import com.dhanantry.scapeandrunparasites.client.model.entity.hijacked.ModelHiBlaze;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderSRP;
import com.dhanantry.scapeandrunparasites.entity.monster.hijacked.EntityHiBlaze;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderHiBlaze extends RenderSRP<EntityHiBlaze> {
   public static final ResourceLocation TEXTURE = new ResourceLocation("srparasites:textures/entity/monster/hiblaze.png");

   public RenderHiBlaze(RenderManager manager) {
      super(manager, new ModelHiBlaze(), 0.6F);
   }

   protected ResourceLocation getEntityTexture(EntityHiBlaze entity) {
      return TEXTURE;
   }

   protected void applyRotations(EntityHiBlaze entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      super.func_77043_a(entityLiving, ageInTicks, rotationYaw, partialTicks);
   }
}
