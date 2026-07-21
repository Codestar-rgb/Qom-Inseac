package com.dhanantry.scapeandrunparasites.client.renderer.entity.hijacked;

import com.dhanantry.scapeandrunparasites.client.model.entity.hijacked.ModelHiGolem;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderSRP;
import com.dhanantry.scapeandrunparasites.entity.monster.hijacked.EntityHiGolem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderHiGolem extends RenderSRP<EntityHiGolem> {
   public static final ResourceLocation TEXTURE = new ResourceLocation("srparasites:textures/entity/monster/higolem.png");

   public RenderHiGolem(RenderManager manager) {
      super(manager, new ModelHiGolem(), 0.6F);
   }

   protected ResourceLocation getEntityTexture(EntityHiGolem entity) {
      return TEXTURE;
   }

   protected void applyRotations(EntityHiGolem entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      super.func_77043_a(entityLiving, ageInTicks, rotationYaw, partialTicks);
   }
}
