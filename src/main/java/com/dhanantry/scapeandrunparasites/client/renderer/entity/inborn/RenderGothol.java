package com.dhanantry.scapeandrunparasites.client.renderer.entity.inborn;

import com.dhanantry.scapeandrunparasites.client.model.entity.inborn.ModelGothol;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityGothol;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderGothol extends RenderLiving<EntityGothol> {
   public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/gothol.png");
   public static final ResourceLocation TEXTURES1 = new ResourceLocation("srparasites:textures/entity/monster/test.png");
   public static final ResourceLocation STEXTURE = new ResourceLocation("srparasites:textures/entity/monster/sgothol.png");

   public RenderGothol(RenderManager manager) {
      super(manager, new ModelGothol(), 1.2F);
   }

   protected void preRenderCallback(EntityGothol entitylivingbaseIn, float partialTickTime) {
      float f = entitylivingbaseIn.getSelfeFlashIntensity(partialTickTime);
      float f1 = 1.0F + MathHelper.func_76126_a(f * 100.0F) * f * 0.01F;
      f = MathHelper.func_76131_a(f, 0.0F, 1.0F);
      f *= f;
      f *= f;
      float f2 = (1.0F + f * 0.4F) * f1;
      float f3 = (1.0F + f * 0.1F) / f1;
      GlStateManager.func_179152_a(f2, f3, f2);
   }

   protected ResourceLocation getEntityTexture(EntityGothol entity) {
      switch (entity.getSkin()) {
         case 1:
            return TEXTURES;
         case 120:
            return STEXTURE;
         default:
            return TEXTURES;
      }
   }

   protected void applyRotations(EntityGothol entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      super.func_77043_a(entityLiving, ageInTicks, rotationYaw, partialTicks);
   }
}
