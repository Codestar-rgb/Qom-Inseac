package com.dhanantry.scapeandrunparasites.client.renderer.entity.deterrent.nexus;

import com.dhanantry.scapeandrunparasites.client.model.entity.deterrent.nexus.ModelVenkrolSIII;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderMalleable;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.layer.LayerGlowing;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityVenkrolSIII;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderVenkrolSIII extends RenderMalleable<EntityVenkrolSIII> {
   public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/venkrolsiii.png");
   public static final ResourceLocation TEXTURE_FROZEN = new ResourceLocation("srparasites:textures/entity/monster/snowvariants/test.png");

   public RenderVenkrolSIII(RenderManager manager) {
      super(manager, new ModelVenkrolSIII(), 0.6F);
      this.func_177094_a(new LayerGlowing<>(this));
   }

   protected ResourceLocation getEntityTexture(EntityVenkrolSIII entity) {
      switch (entity.getSkin()) {
         case 120:
            return TEXTURE_FROZEN;
         default:
            return TEXTURES;
      }
   }

   protected void applyRotations(EntityVenkrolSIII entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      super.func_77043_a(entityLiving, ageInTicks, rotationYaw, partialTicks);
   }
}
