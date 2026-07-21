package com.dhanantry.scapeandrunparasites.client.renderer.entity.deterrent.nexus;

import com.dhanantry.scapeandrunparasites.client.model.entity.deterrent.nexus.ModelVenkrolSIV;
import com.dhanantry.scapeandrunparasites.client.renderer.LayerVenkrolTornado;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderMalleable;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.layer.LayerGlowing;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityVenkrolSIV;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderVenkrolSIV extends RenderMalleable<EntityVenkrolSIV> {
   public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/venkrolsiv.png");
   public static final ResourceLocation TEXTURE_FROZEN = new ResourceLocation("srparasites:textures/entity/monster/snowvariants/test.png");

   public RenderVenkrolSIV(RenderManager manager) {
      super(manager, new ModelVenkrolSIV(), 0.4F);
      this.func_177094_a(new LayerGlowing<>(this));
      this.func_177094_a(new LayerVenkrolTornado(this));
   }

   protected ResourceLocation getEntityTexture(EntityVenkrolSIV entity) {
      switch (entity.getSkin()) {
         case 120:
            return TEXTURE_FROZEN;
         default:
            return TEXTURES;
      }
   }

   protected void applyRotations(EntityVenkrolSIV entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      super.func_77043_a(entityLiving, ageInTicks, rotationYaw, partialTicks);
   }
}
