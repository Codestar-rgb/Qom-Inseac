package com.dhanantry.scapeandrunparasites.client.renderer.entity.crude;

import com.dhanantry.scapeandrunparasites.client.model.entity.crude.ModelInhooS;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderSRP;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityInhooS;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderInhooS extends RenderSRP<EntityInhooS> {
   public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/inhoos.png");
   public static final ResourceLocation TEXTURE_FROZEN = new ResourceLocation("srparasites:textures/entity/monster/snowvariants/test.png");

   public RenderInhooS(RenderManager manager) {
      super(manager, new ModelInhooS(), 0.5F);
   }

   protected ResourceLocation getEntityTexture(EntityInhooS entity) {
      switch (entity.getSkin()) {
         case 120:
            return TEXTURE_FROZEN;
         default:
            return TEXTURES;
      }
   }
}
