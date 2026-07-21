package com.dhanantry.scapeandrunparasites.client.renderer.entity.inborn;

import com.dhanantry.scapeandrunparasites.client.model.entity.inborn.ModelViin;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderSRP;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityViin;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderViin extends RenderSRP<EntityViin> {
   public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/lice.png");

   public RenderViin(RenderManager manager) {
      super(manager, new ModelViin(), 0.5F);
   }

   protected ResourceLocation getEntityTexture(EntityViin entity) {
      return TEXTURES;
   }

   protected void applyRotations(EntityViin entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      super.func_77043_a(entityLiving, ageInTicks, rotationYaw, partialTicks);
   }
}
