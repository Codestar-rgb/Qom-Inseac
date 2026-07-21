package com.dhanantry.scapeandrunparasites.client.renderer.entity.inborn;

import com.dhanantry.scapeandrunparasites.client.model.entity.inborn.ModelMudo;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityMudo;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderMudo extends RenderLiving<EntityMudo> {
   public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/mudo.png");
   public static final ResourceLocation TEXTUREV = new ResourceLocation("srparasites:textures/entity/monster/mudov.png");
   public static final ResourceLocation TEXTUREB = new ResourceLocation("srparasites:textures/entity/monster/mudob.png");
   public static final ResourceLocation STEXTURE = new ResourceLocation("srparasites:textures/entity/monster/mudo_snowy.png");
   public static final ResourceLocation TEXTURE_CLASSIC = new ResourceLocation("srparasites:textures/entity/monster/mudo_classic.png");
   public static final ResourceLocation TEXTURE_GOLD = new ResourceLocation("srparasites:textures/entity/monster/mudo_golden.png");
   public static final ResourceLocation TEXTURE_STRIPED = new ResourceLocation("srparasites:textures/entity/monster/mudo_striped.png");
   public static final ResourceLocation TEXTURE_FLUFFY = new ResourceLocation("srparasites:textures/entity/monster/mudo_fluffy.png");
   public static final ResourceLocation TEXTURE_WEIRD = new ResourceLocation("srparasites:textures/entity/monster/mudo_weird.png");

   public RenderMudo(RenderManager manager) {
      super(manager, new ModelMudo(), 0.5F);
   }

   protected ResourceLocation getEntityTexture(EntityMudo entity) {
      switch (entity.getSkin()) {
         case 1:
            return TEXTURE_CLASSIC;
         case 2:
            return TEXTURE_GOLD;
         case 3:
            return TEXTURE_STRIPED;
         case 4:
            return TEXTURE_FLUFFY;
         case 5:
            return TEXTUREV;
         case 6:
            return TEXTUREB;
         case 7:
            return TEXTURE_WEIRD;
         case 120:
            return STEXTURE;
         default:
            return TEXTURES;
      }
   }

   protected void applyRotations(EntityMudo entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      super.func_77043_a(entityLiving, ageInTicks, rotationYaw, partialTicks);
   }
}
