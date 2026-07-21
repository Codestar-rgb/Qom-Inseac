package com.dhanantry.scapeandrunparasites.client.renderer.entity.awakened;

import com.dhanantry.scapeandrunparasites.client.model.ModelSRP;
import com.dhanantry.scapeandrunparasites.entity.monster.awakened.EntityOroncoAW;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class LayerOroncoAW implements LayerRenderer<EntityOroncoAW> {
   public static final ResourceLocation TEXTUREO = new ResourceLocation("srparasites:textures/entity/monster/test.png");
   private int part;
   private final RenderOroncoAW parent;
   private ModelSRP model;

   public LayerOroncoAW(RenderOroncoAW in) {
      this.parent = in;
   }

   public void doRenderLayer(
      EntityOroncoAW entitylivingbaseIn,
      float limbSwing,
      float limbSwingAmount,
      float partialTicks,
      float ageInTicks,
      float netHeadYaw,
      float headPitch,
      float scale
   ) {
   }

   public boolean func_177142_b() {
      return false;
   }
}
