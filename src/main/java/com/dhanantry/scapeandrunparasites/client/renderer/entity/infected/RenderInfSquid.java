package com.dhanantry.scapeandrunparasites.client.renderer.entity.infected;

import com.dhanantry.scapeandrunparasites.client.model.entity.infected.ModelInfSquid;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderSRP;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfSquid;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderInfSquid extends RenderSRP<EntityInfSquid> {
   public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/squid.png");

   public RenderInfSquid(RenderManager manager) {
      super(manager, new ModelInfSquid(), 0.5F);
   }

   protected void preRenderCallback(EntityInfSquid entitylivingbaseIn, float partialTickTime) {
   }

   protected ResourceLocation getEntityTexture(EntityInfSquid entity) {
      return TEXTURES;
   }

   protected void applyRotations(EntityInfSquid entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      super.func_77043_a(entityLiving, ageInTicks, rotationYaw, partialTicks);
   }
}
