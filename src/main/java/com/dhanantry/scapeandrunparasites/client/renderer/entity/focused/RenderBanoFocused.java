package com.dhanantry.scapeandrunparasites.client.renderer.entity.focused;

import com.dhanantry.scapeandrunparasites.client.model.entity.focused.ModelBanoFocused;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderCosmical;
import com.dhanantry.scapeandrunparasites.entity.monster.focused.EntityBanoFocused;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBanoFocused extends RenderCosmical<EntityBanoFocused> {
   public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/test.png");
   public static final ResourceLocation TEXTURESEC = new ResourceLocation("srparasites:textures/entity/monster/testb.png");

   public RenderBanoFocused(RenderManager manager) {
      super(manager, new ModelBanoFocused(), 0.5F);
   }

   protected ResourceLocation getEntityTexture(EntityBanoFocused entity) {
      return TEXTURES;
   }

   protected ResourceLocation getEntityTextureCosmical(EntityBanoFocused entity) {
      return TEXTURESEC;
   }
}
