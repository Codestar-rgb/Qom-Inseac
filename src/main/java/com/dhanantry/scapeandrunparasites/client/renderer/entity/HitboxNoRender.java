package com.dhanantry.scapeandrunparasites.client.renderer.entity;

import com.dhanantry.scapeandrunparasites.entity.EntityHitbox;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class HitboxNoRender extends Render<EntityHitbox> {
   public HitboxNoRender(RenderManager renderManager) {
      super(renderManager);
   }

   @Nullable
   protected ResourceLocation getEntityTexture(EntityHitbox entity) {
      return null;
   }
}
