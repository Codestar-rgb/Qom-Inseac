package com.dhanantry.scapeandrunparasites.client.renderer.entity.deterrent;

import com.dhanantry.scapeandrunparasites.client.model.entity.deterrent.ModelLeemB;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderSRP;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.EntityLeemB;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderLeemB extends RenderSRP<EntityLeemB> {
   public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/leemb.png");

   public RenderLeemB(RenderManager manager) {
      super(manager, new ModelLeemB(), 0.4F);
   }

   protected ResourceLocation getEntityTexture(EntityLeemB entity) {
      return TEXTURES;
   }

   protected void applyRotations(EntityLeemB entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      super.func_77043_a(entityLiving, ageInTicks, rotationYaw, partialTicks);
   }
}
