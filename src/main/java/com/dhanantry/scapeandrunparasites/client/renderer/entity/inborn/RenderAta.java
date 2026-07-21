package com.dhanantry.scapeandrunparasites.client.renderer.entity.inborn;

import com.dhanantry.scapeandrunparasites.client.model.entity.inborn.ModelAta;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderSRP;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityAta;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderAta extends RenderSRP<EntityAta> {
   public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/gnat.png");

   public RenderAta(RenderManager manager) {
      super(manager, new ModelAta(), 0.5F);
   }

   protected ResourceLocation getEntityTexture(EntityAta entity) {
      return TEXTURES;
   }

   protected void applyRotations(EntityAta entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      super.func_77043_a(entityLiving, ageInTicks, rotationYaw, partialTicks);
   }
}
