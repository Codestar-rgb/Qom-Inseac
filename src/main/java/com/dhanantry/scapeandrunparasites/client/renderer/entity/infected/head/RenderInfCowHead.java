package com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.head;

import com.dhanantry.scapeandrunparasites.client.model.entity.infected.head.ModelInfCowHead;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderSRP;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfCowHead;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderInfCowHead extends RenderSRP<EntityInfCowHead> {
   public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/cowh.png");

   public RenderInfCowHead(RenderManager manager) {
      super(manager, new ModelInfCowHead(), 0.6F);
   }

   protected ResourceLocation getEntityTexture(EntityInfCowHead entity) {
      return TEXTURES;
   }

   protected void applyRotations(EntityInfCowHead entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      super.func_77043_a(entityLiving, ageInTicks, rotationYaw, partialTicks);
   }
}
