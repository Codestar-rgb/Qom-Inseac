package com.dhanantry.scapeandrunparasites.client.renderer.entity.inborn;

import com.dhanantry.scapeandrunparasites.client.model.entity.inborn.ModelKol;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityKol;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderKol extends RenderLiving<EntityKol> {
   public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/kol.png");

   public RenderKol(RenderManager manager) {
      super(manager, new ModelKol(), 0.5F);
   }

   protected ResourceLocation getEntityTexture(EntityKol entity) {
      return TEXTURES;
   }

   protected void applyRotations(EntityKol entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      super.func_77043_a(entityLiving, ageInTicks, rotationYaw, partialTicks);
   }
}
