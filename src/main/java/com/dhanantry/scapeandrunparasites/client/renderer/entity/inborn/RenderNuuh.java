package com.dhanantry.scapeandrunparasites.client.renderer.entity.inborn;

import com.dhanantry.scapeandrunparasites.client.model.entity.inborn.ModelNuuh;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderMalleable;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityNuuh;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderNuuh extends RenderMalleable<EntityNuuh> {
   public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/nuuh.png");
   public static final ResourceLocation TEXTUREV = new ResourceLocation("srparasites:textures/entity/monster/nuuhv.png");
   public static final ResourceLocation TEXTUREB = new ResourceLocation("srparasites:textures/entity/monster/nuuhb.png");
   public static final ResourceLocation STEXTURE = new ResourceLocation("srparasites:textures/entity/monster/snuuh.png");

   public RenderNuuh(RenderManager manager) {
      super(manager, new ModelNuuh(), 0.7F);
   }

   protected ResourceLocation getEntityTexture(EntityNuuh entity) {
      switch (entity.getSkin()) {
         case 5:
            return TEXTUREV;
         case 6:
            return TEXTUREB;
         case 120:
            return STEXTURE;
         default:
            return TEXTURES;
      }
   }

   protected void applyRotations(EntityNuuh entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      super.func_77043_a(entityLiving, ageInTicks, rotationYaw, partialTicks);
   }
}
