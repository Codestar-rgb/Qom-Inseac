package com.dhanantry.scapeandrunparasites.client.renderer.entity.misc;

import com.dhanantry.scapeandrunparasites.client.model.entity.misc.ModelBombHost;
import com.dhanantry.scapeandrunparasites.client.model.entity.misc.ModelBombJinjo;
import com.dhanantry.scapeandrunparasites.client.model.entity.misc.ModelBombOmboo;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityBomb;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBomb extends Render<EntityBomb> {
   protected ModelBase modelO = new ModelBombOmboo();
   protected ModelBase modelH = new ModelBombHost();
   protected ModelBase modelJ = new ModelBombJinjo();
   public static final ResourceLocation TEXTUREO = new ResourceLocation("srparasites:textures/entity/monster/bombo.png");
   public static final ResourceLocation TEXTUREH = new ResourceLocation("srparasites:textures/entity/monster/bombh.png");
   public static final ResourceLocation TEXTUREJ = new ResourceLocation("srparasites:textures/entity/monster/bombj.png");

   public RenderBomb(RenderManager p_i47208_1_) {
      super(p_i47208_1_);
      this.field_76989_e = 0.5F;
   }

   protected ResourceLocation getEntityTexture(EntityBomb entity) {
      switch (entity.getSkin()) {
         case 0:
            return TEXTUREO;
         case 1:
            return TEXTUREH;
         case 2:
            return TEXTUREJ;
         case 3:
            return TEXTUREJ;
         default:
            return TEXTUREO;
      }
   }

   public void doRender(EntityBomb entity, double x, double y, double z, float entityYaw, float partialTicks) {
      GlStateManager.func_179094_E();
      switch (entity.getSkin()) {
         case 0:
            GlStateManager.func_179109_b((float)x, (float)y + 1.5F, (float)z);
            this.func_110776_a(TEXTUREO);
            break;
         case 1:
            GlStateManager.func_179109_b((float)x, (float)y + 1.5F, (float)z);
            this.func_110776_a(TEXTUREH);
            break;
         case 2:
            GlStateManager.func_179109_b((float)x, (float)y + 1.5F, (float)z);
            this.func_110776_a(TEXTUREJ);
            break;
         case 3:
            GlStateManager.func_179109_b((float)x, (float)y + 1.5F, (float)z);
            this.func_110776_a(TEXTUREJ);
      }

      GlStateManager.func_179114_b(180.0F, 0.0F, 0.0F, 1.0F);
      if (this.field_188301_f) {
         GlStateManager.func_179142_g();
         GlStateManager.func_187431_e(this.func_188298_c(entity));
      }

      switch (entity.getSkin()) {
         case 0:
            this.modelO.func_78088_a(entity, 0.0F, 0.0F, entity.field_70173_aa, entity.field_70177_z, entity.field_70125_A, 0.0625F);
            break;
         case 1:
            this.modelH.func_78088_a(entity, 0.0F, 0.0F, entity.field_70173_aa, entity.field_70177_z, entity.field_70125_A, 0.0625F);
            break;
         case 2:
            this.modelJ.func_78088_a(entity, 0.0F, 0.0F, entity.field_70173_aa, entity.field_70177_z, entity.field_70125_A, 0.0625F);
            break;
         case 3:
            this.modelJ.func_78088_a(entity, 0.0F, 0.0F, entity.field_70173_aa, entity.field_70177_z, entity.field_70125_A, 0.0625F);
      }

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
