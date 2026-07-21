package com.dhanantry.scapeandrunparasites.client.renderer.entity.inborn;

import com.dhanantry.scapeandrunparasites.client.model.entity.inborn.ModelMor;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityMor;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderMor extends RenderLiving<EntityMor> {
   public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/test.png");

   public RenderMor(RenderManager manager) {
      super(manager, new ModelMor(), 0.5F);
   }

   protected ResourceLocation getEntityTexture(EntityMor entity) {
      return TEXTURES;
   }

   protected void applyRotations(EntityMor entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      super.func_77043_a(entityLiving, ageInTicks, rotationYaw, partialTicks);
   }
}
