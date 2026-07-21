package com.dhanantry.scapeandrunparasites.client.renderer.entity.crude;

import com.dhanantry.scapeandrunparasites.client.model.entity.crude.ModelMes;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderMalleable;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.layer.LayerSnow;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityMes;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderMes extends RenderMalleable<EntityMes> {
   public static final ResourceLocation TEXTUREM = new ResourceLocation("srparasites:textures/entity/monster/mes.png");
   public static final ResourceLocation TEXTURE1 = new ResourceLocation("srparasites:textures/entity/monster/mes1.png");
   public static final ResourceLocation STEXTURE1 = new ResourceLocation("srparasites:textures/entity/monster/smes1.png");
   public static final ResourceLocation STEXTURE2 = new ResourceLocation("srparasites:textures/entity/monster/smes2.png");
   public static final ResourceLocation STEXTURE3 = new ResourceLocation("srparasites:textures/entity/monster/smes3.png");
   public static final ResourceLocation STEXTURE4 = new ResourceLocation("srparasites:textures/entity/monster/smes4.png");
   public static final ResourceLocation STEXTURE5 = new ResourceLocation("srparasites:textures/entity/monster/smes5.png");

   public RenderMes(RenderManager manager) {
      super(manager, new ModelMes(), 0.7F);
      this.func_177094_a(new LayerSnow(this, new ResourceLocation("srparasites:textures/entity/layer/messnow.png"), 1.0, 1.0, 1.0));
   }

   protected void preRenderCallback(EntityMes entitylivingbaseIn, float partialTickTime) {
      float f = entitylivingbaseIn.getSelfeFlashIntensity(partialTickTime);
      float f1 = 1.0F + MathHelper.func_76126_a(f * 100.0F) * f * 0.01F;
      f = MathHelper.func_76131_a(f, 0.0F, 1.0F);
      f *= f;
      f *= f;
      float f2 = (1.0F + f * 0.4F) * f1;
      float f3 = (1.0F + f * 0.1F) / f1;
      GlStateManager.func_179152_a(f2, f3, f2);
   }

   protected ResourceLocation getEntityTexture(EntityMes entity) {
      switch (entity.getSkin()) {
         case 1:
            return TEXTURE1;
         case 121:
            return STEXTURE1;
         case 122:
            return STEXTURE2;
         case 123:
            return STEXTURE3;
         case 124:
            return STEXTURE4;
         case 125:
            return STEXTURE5;
         default:
            return TEXTUREM;
      }
   }

   protected void renderLivingLabel(EntityMes entityIn, String str, double x, double y, double z, int maxDistance) {
      double d0 = entityIn.func_70068_e(this.field_76990_c.field_78734_h);
      if (d0 <= maxDistance * maxDistance) {
         boolean flag = entityIn.func_70093_af();
         float f = this.field_76990_c.field_78735_i;
         float f1 = this.field_76990_c.field_78732_j;
         boolean flag1 = this.field_76990_c.field_78733_k.field_74320_O == 2;
         float f2 = entityIn.field_70131_O + 0.5F - (flag ? 0.25F : 0.0F);
         int i = "deadmau5".equals(str) ? -10 : 0;
         EntityRenderer.func_189692_a(this.func_76983_a(), str, (float)x, (float)y + f2, (float)z, i, f, f1, flag1, flag);
      }
   }
}
