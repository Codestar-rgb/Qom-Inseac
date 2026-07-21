package com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.head;

import com.dhanantry.scapeandrunparasites.client.model.entity.infected.head.ModelInfPigHead;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderSRP;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfPigHead;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderInfPigHead extends RenderSRP<EntityInfPigHead> {
   public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/pigh.png");

   public RenderInfPigHead(RenderManager manager) {
      super(manager, new ModelInfPigHead(), 0.6F);
   }

   protected ResourceLocation getEntityTexture(EntityInfPigHead entity) {
      return TEXTURES;
   }

   protected void applyRotations(EntityInfPigHead entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      super.func_77043_a(entityLiving, ageInTicks, rotationYaw, partialTicks);
   }
}
