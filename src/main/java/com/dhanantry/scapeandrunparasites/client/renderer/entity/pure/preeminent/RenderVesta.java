package com.dhanantry.scapeandrunparasites.client.renderer.entity.pure.preeminent;

import com.dhanantry.scapeandrunparasites.client.model.entity.pure.preeminent.ModelVesta;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderMalleable;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.layer.LayerSnow;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent.EntityVesta;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderVesta extends RenderMalleable<EntityVesta> {
   public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/vesta.png");
   public static final ResourceLocation TEXTURE1 = new ResourceLocation("srparasites:textures/entity/monster/vestare.png");

   public RenderVesta(RenderManager manager) {
      super(manager, new ModelVesta(), 1.3F);
      this.func_177094_a(new LayerSnow(this, new ResourceLocation("srparasites:textures/entity/layer/vestasnow.png"), 1.01, 1.001, 1.01));
   }

   protected void preRenderCallback(EntityVesta entitylivingbaseIn, float partialTickTime) {
      float f = entitylivingbaseIn.getSelfeFlashIntensity(partialTickTime);
      float f1 = 1.0F + MathHelper.func_76126_a(f * 100.0F) * f * 0.01F;
      f = MathHelper.func_76131_a(f, 0.0F, 1.0F);
      f *= f;
      f *= f;
      float f2 = (1.0F + f * 0.4F) * f1;
      float f3 = (1.0F + f * 0.1F) / f1;
      GlStateManager.func_179152_a(f2, f3, f2);
   }

   protected ResourceLocation getEntityTexture(EntityVesta entity) {
      switch (entity.getSkin()) {
         case 1:
            return TEXTURE1;
         default:
            return TEXTURES;
      }
   }

   protected void applyRotations(EntityVesta entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      super.func_77043_a(entityLiving, ageInTicks, rotationYaw, partialTicks);
   }
}
