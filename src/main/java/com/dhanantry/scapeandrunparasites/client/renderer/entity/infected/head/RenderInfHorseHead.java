package com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.head;

import com.dhanantry.scapeandrunparasites.client.model.entity.infected.head.ModelInfHorseHead;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderSRP;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfHorseHead;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderInfHorseHead extends RenderSRP<EntityInfHorseHead> {
   public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/horseh.png");

   public RenderInfHorseHead(RenderManager manager) {
      super(manager, new ModelInfHorseHead(), 0.6F);
   }

   protected ResourceLocation getEntityTexture(EntityInfHorseHead entity) {
      return TEXTURES;
   }

   protected void applyRotations(EntityInfHorseHead entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      super.func_77043_a(entityLiving, ageInTicks, rotationYaw, partialTicks);
   }
}
