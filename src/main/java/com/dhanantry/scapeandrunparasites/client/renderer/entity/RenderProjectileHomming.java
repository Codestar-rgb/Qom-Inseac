package com.dhanantry.scapeandrunparasites.client.renderer.entity;

import com.dhanantry.scapeandrunparasites.client.model.entity.ModelProjectileHomming;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileHomming;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderProjectileHomming extends Render<EntityProjectileHomming> {
   private static final ResourceLocation EVOKER_ILLAGER_FANGS = new ResourceLocation("srparasites:textures/entity/projectile/projectileh.png");
   private final ModelProjectileHomming model = new ModelProjectileHomming();

   public RenderProjectileHomming(RenderManager p_i47208_1_) {
      super(p_i47208_1_);
   }

   public void doRender(EntityProjectileHomming entity, double x, double y, double z, float entityYaw, float partialTicks) {
      GlStateManager.func_179094_E();
      float f = this.rotLerp(entity.field_70126_B, entity.field_70177_z, partialTicks);
      float f1 = entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * partialTicks;
      float f2 = entity.field_70173_aa + partialTicks;
      GlStateManager.func_179109_b((float)x, (float)y + 0.15F, (float)z);
      GlStateManager.func_179114_b(MathHelper.func_76126_a(f2 * 0.1F) * 180.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.func_179114_b(MathHelper.func_76134_b(f2 * 0.1F) * 180.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.func_179114_b(MathHelper.func_76126_a(f2 * 0.15F) * 360.0F, 0.0F, 0.0F, 1.0F);
      float f3 = 0.03125F;
      GlStateManager.func_179091_B();
      GlStateManager.func_179152_a(-1.0F, -1.0F, 1.0F);
      this.func_180548_c(entity);
      this.model.func_78088_a(entity, 0.0F, 0.0F, 0.0F, f, f1, 0.03125F);
      GlStateManager.func_179147_l();
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 0.5F);
      GlStateManager.func_179152_a(1.5F, 1.5F, 1.5F);
      this.model.func_78088_a(entity, 0.0F, 0.0F, 0.0F, f, f1, 0.03125F);
      GlStateManager.func_179084_k();
      GlStateManager.func_179121_F();
      super.func_76986_a(entity, x, y, z, entityYaw, partialTicks);
   }

   protected ResourceLocation getEntityTexture(EntityProjectileHomming entity) {
      return EVOKER_ILLAGER_FANGS;
   }

   private float rotLerp(float p_188347_1_, float p_188347_2_, float p_188347_3_) {
      float f = p_188347_2_ - p_188347_1_;

      while (f < -180.0F) {
         f += 360.0F;
      }

      while (f >= 180.0F) {
         f -= 360.0F;
      }

      return p_188347_1_ + p_188347_3_ * f;
   }
}
