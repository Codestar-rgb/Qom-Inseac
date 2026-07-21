package com.dhanantry.scapeandrunparasites.client.renderer.entity.misc;

import com.dhanantry.scapeandrunparasites.client.model.entity.misc.ModelGore;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityGore;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderGore extends Render<EntityGore> {
   protected ModelBase model = new ModelGore();
   public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/gore.png");

   public RenderGore(RenderManager p_i47208_1_) {
      super(p_i47208_1_);
      this.field_76989_e = 0.1F;
   }

   protected ResourceLocation getEntityTexture(EntityGore entity) {
      return TEXTURES;
   }

   public void doRender(EntityGore entity, double x, double y, double z, float entityYaw, float partialTicks) {
      GlStateManager.func_179094_E();
      GlStateManager.func_179109_b((float)x, (float)y + 1.5F, (float)z);
      this.func_110776_a(TEXTURES);
      GlStateManager.func_179114_b(180.0F, 0.0F, 0.0F, 1.0F);
      if (this.field_188301_f) {
         GlStateManager.func_179142_g();
         GlStateManager.func_187431_e(this.func_188298_c(entity));
      }

      this.model.func_78088_a(entity, 0.0F, 0.0F, entity.field_70173_aa, entity.field_70177_z, entity.field_70125_A, 0.0625F);
      if (this.field_188301_f) {
         GlStateManager.func_187417_n();
         GlStateManager.func_179119_h();
      }

      GlStateManager.func_179121_F();
      super.func_76986_a(entity, x, y, z, entityYaw, partialTicks);
   }

   protected float interpolateRotation(float prevYawOffset, float yawOffset, float partialTicks) {
      float f = yawOffset - prevYawOffset;

      while (f < -180.0F) {
         f += 360.0F;
      }

      while (f >= 180.0F) {
         f -= 360.0F;
      }

      return prevYawOffset + partialTicks * f;
   }
}
