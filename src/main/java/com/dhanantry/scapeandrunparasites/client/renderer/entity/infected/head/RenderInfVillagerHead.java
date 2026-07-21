package com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.head;

import com.dhanantry.scapeandrunparasites.client.model.entity.infected.head.ModelInfVillagerHead;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderSRP;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfVillagerHead;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderInfVillagerHead extends RenderSRP<EntityInfVillagerHead> {
   public static final ResourceLocation TEXTURE = new ResourceLocation("srparasites:textures/entity/monster/villagerh.png");
   public static final ResourceLocation TEXTURE1 = new ResourceLocation("srparasites:textures/entity/monster/villagerh1.png");

   public RenderInfVillagerHead(RenderManager manager) {
      super(manager, new ModelInfVillagerHead(), 0.6F);
   }

   protected ResourceLocation getEntityTexture(EntityInfVillagerHead entity) {
      switch (entity.getSkin()) {
         case 1:
            return TEXTURE1;
         default:
            return TEXTURE;
      }
   }

   protected void applyRotations(EntityInfVillagerHead entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      super.func_77043_a(entityLiving, ageInTicks, rotationYaw, partialTicks);
   }
}
