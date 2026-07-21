package com.dhanantry.scapeandrunparasites.client.renderer.entity.deterrent;

import com.dhanantry.scapeandrunparasites.client.model.entity.deterrent.ModelUnvo;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderMalleable;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.EntityUnvo;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderUnvo extends RenderMalleable<EntityUnvo> {
   public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/unvo.png");
   public static final ResourceLocation TEXTURE_FROZEN = new ResourceLocation("srparasites:textures/entity/monster/snowvariants/sentryfrozen.png");

   public RenderUnvo(RenderManager manager) {
      super(manager, new ModelUnvo(), 0.5F);
   }

   protected ResourceLocation getEntityTexture(EntityUnvo entity) {
      switch (entity.getSkin()) {
         case 120:
            return TEXTURE_FROZEN;
         default:
            return TEXTURES;
      }
   }
}
