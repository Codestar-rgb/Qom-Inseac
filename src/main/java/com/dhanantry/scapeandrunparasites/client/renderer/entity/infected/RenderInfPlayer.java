package com.dhanantry.scapeandrunparasites.client.renderer.entity.infected;

import com.dhanantry.scapeandrunparasites.client.model.entity.infected.ModelInfPlayer;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderSRP;
import com.dhanantry.scapeandrunparasites.client.renderer.SRPLayerBipedArmor;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderInfPlayer extends RenderSRP<EntityInfPlayer> {
   public static final ResourceLocation TEXTURE = new ResourceLocation("srparasites:textures/entity/monster/infplayer.png");

   public RenderInfPlayer(RenderManager manager) {
      super(manager, new ModelInfPlayer(), 0.5F);
      this.func_177094_a(new SRPLayerBipedArmor(this));
   }

   protected void preRenderCallback(EntityInfPlayer entitylivingbaseIn, float partialTickTime) {
      float f = entitylivingbaseIn.getSelfeFlashIntensity(partialTickTime);
      float ff = entitylivingbaseIn.getSelfeFlashIntensity2();
      float f1 = 1.0F + MathHelper.func_76126_a(f * 100.0F) * f * 0.01F;
      f = MathHelper.func_76131_a(f, 0.0F, 1.0F);
      f *= f;
      f *= f;
      float f2 = (1.0F + f * 0.4F) * f1;
      float f3 = (1.0F + f * 0.1F) / f1;
      GlStateManager.func_179152_a(f2, ff * f3, f2);
   }

   protected ResourceLocation getEntityTexture(EntityInfPlayer entity) {
      return TEXTURE;
   }

   protected void applyRotations(EntityInfPlayer entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      super.func_77043_a(entityLiving, ageInTicks, rotationYaw, partialTicks);
   }
}
