package com.dhanantry.scapeandrunparasites.client.renderer.entity.awakened;

import com.dhanantry.scapeandrunparasites.client.model.ModelSRP;
import com.dhanantry.scapeandrunparasites.client.model.entity.awakened.ModelOroncoAW;
import com.dhanantry.scapeandrunparasites.client.model.entity.awakened.ModelOroncoAWFL;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderMalleable;
import com.dhanantry.scapeandrunparasites.entity.monster.awakened.EntityOroncoAW;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderOroncoAW extends RenderMalleable<EntityOroncoAW> {
   protected ModelSRP mainModel2 = new ModelOroncoAWFL();
   protected ModelSRP mainModel3 = new ModelOroncoAWFL();
   public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/test.png");

   public RenderOroncoAW(RenderManager manager) {
      super(manager, new ModelOroncoAW(), 4.0F);
   }

   protected ResourceLocation getEntityTexture(EntityOroncoAW entity) {
      return TEXTURES;
   }
}
