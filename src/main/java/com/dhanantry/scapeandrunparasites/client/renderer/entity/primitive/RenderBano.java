package com.dhanantry.scapeandrunparasites.client.renderer.entity.primitive;

import com.dhanantry.scapeandrunparasites.client.model.entity.primitive.ModelBano;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderMalleable;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityBano;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderBano extends RenderMalleable<EntityBano> {
   public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/bano.png");
   public static final ResourceLocation TEXTUREV = new ResourceLocation("srparasites:textures/entity/monster/banov.png");
   public static final ResourceLocation TEXTUREH = new ResourceLocation("srparasites:textures/entity/monster/banoh.png");
   public static final ResourceLocation STEXTURE = new ResourceLocation("srparasites:textures/entity/monster/sbano.png");
   public static final ResourceLocation FROZEN_TEXTURE = new ResourceLocation("srparasites:textures/entity/monster/snowvariants/primitivebolsterfrozen.png");

   public RenderBano(RenderManager manager) {
      super(manager, new ModelBano(), 0.5F);
   }

   protected void preRenderCallback(EntityBano entitylivingbaseIn, float partialTickTime) {
      float f = entitylivingbaseIn.getSelfeFlashIntensity(partialTickTime);
      float f1 = 1.0F + MathHelper.func_76126_a(f * 100.0F) * f * 0.01F;
      f = MathHelper.func_76131_a(f, 0.0F, 1.0F);
      f *= f;
      f *= f;
      float f2 = (1.0F + f * 0.4F) * f1;
      float f3 = (1.0F + f * 0.1F) / f1;
      GlStateManager.func_179152_a(f2, f3, f2);
   }

   protected ResourceLocation getEntityTexture(EntityBano entity) {
      switch (entity.getSkin()) {
         case 5:
            return TEXTUREV;
         case 7:
            return TEXTUREH;
         case 120:
            return STEXTURE;
         default:
            return TEXTURES;
      }
   }

   protected void applyRotations(EntityBano entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      super.func_77043_a(entityLiving, ageInTicks, rotationYaw, partialTicks);
   }
}
