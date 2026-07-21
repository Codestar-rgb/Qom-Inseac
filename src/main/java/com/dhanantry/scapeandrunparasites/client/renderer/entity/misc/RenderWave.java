package com.dhanantry.scapeandrunparasites.client.renderer.entity.misc;

import com.dhanantry.scapeandrunparasites.client.model.entity.misc.ModelNULL;
import com.dhanantry.scapeandrunparasites.entity.monster.EntityWave;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderWave extends RenderLiving<EntityWave> {
   public static final ResourceLocation TEXTURESS = new ResourceLocation("srparasites:textures/entity/monster/tendrilshyco.png");

   public RenderWave(RenderManager manager) {
      super(manager, new ModelNULL(), 0.0F);
   }

   protected ResourceLocation getEntityTexture(EntityWave entity) {
      return TEXTURESS;
   }
}
