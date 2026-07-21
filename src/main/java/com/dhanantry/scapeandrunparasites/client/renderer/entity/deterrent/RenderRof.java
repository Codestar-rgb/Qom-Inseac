package com.dhanantry.scapeandrunparasites.client.renderer.entity.deterrent;

import com.dhanantry.scapeandrunparasites.client.model.entity.deterrent.ModelRof;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderMalleable;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.EntityRof;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderRof extends RenderMalleable<EntityRof> {
   public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/rof.png");

   public RenderRof(RenderManager manager) {
      super(manager, new ModelRof(), 0.0F);
   }

   protected ResourceLocation getEntityTexture(EntityRof entity) {
      return TEXTURES;
   }

   protected void applyRotations(EntityRof entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      super.func_77043_a(entityLiving, ageInTicks, rotationYaw, partialTicks);
   }
}
