package com.dhanantry.scapeandrunparasites.client.renderer.entity.inborn;

import com.dhanantry.scapeandrunparasites.client.model.entity.inborn.ModelLodo;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityLodo;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderLodo extends RenderLiving<EntityLodo> {
   public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/lodo.png");
   public static final ResourceLocation STEXTURE = new ResourceLocation("srparasites:textures/entity/monster/slodo.png");

   public RenderLodo(RenderManager manager) {
      super(manager, new ModelLodo(), 0.2F);
   }

   protected ResourceLocation getEntityTexture(EntityLodo entity) {
      switch (entity.getSkin()) {
         case 120:
            return STEXTURE;
         default:
            return TEXTURES;
      }
   }

   protected void applyRotations(EntityLodo entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      super.func_77043_a(entityLiving, ageInTicks, rotationYaw, partialTicks);
   }
}
