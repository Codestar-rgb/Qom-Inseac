package com.dhanantry.scapeandrunparasites.client.renderer.entity.deterrent.nexus;

import com.dhanantry.scapeandrunparasites.client.model.entity.deterrent.nexus.ModelDodSIII;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderMalleable;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityDodSIII;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderDodSIII extends RenderMalleable<EntityDodSIII> {
   public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/dodsiii.png");
   public static final ResourceLocation TEXTURE_FROZEN = new ResourceLocation("srparasites:textures/entity/monster/snowvariants/dispatcher3snowy.png");

   public RenderDodSIII(RenderManager manager) {
      super(manager, new ModelDodSIII(), 0.4F);
   }

   protected ResourceLocation getEntityTexture(EntityDodSIII entity) {
      switch (entity.getSkin()) {
         case 120:
            return TEXTURE_FROZEN;
         default:
            return TEXTURES;
      }
   }
}
