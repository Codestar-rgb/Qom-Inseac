package com.dhanantry.scapeandrunparasites.client.renderer.entity.infected;

import com.dhanantry.scapeandrunparasites.client.model.entity.infected.ModelInfHuman;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderSRP;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfHuman;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderInfHuman extends RenderSRP<EntityInfHuman> {
   private static final ResourceLocation TEXTURE_DEFAULT = new ResourceLocation("srparasites:textures/entity/monster/human.png");
   private static final ResourceLocation TEXTURE_ALT = new ResourceLocation("srparasites:textures/entity/monster/human1.png");
   private static final ResourceLocation TEXTURE_FROZEN = new ResourceLocation("srparasites:textures/entity/monster/snowvariants/humanfrozen.png");
   private static final ResourceLocation TEXTURE_EATEN = new ResourceLocation("srparasites:textures/entity/monster/humaneaten.png");
   private static final ResourceLocation TEXTURE_FLOOD = new ResourceLocation("srparasites:textures/entity/monster/humanflood.png");
   private static final ResourceLocation TEXTURE_KIM = new ResourceLocation("srparasites:textures/entity/monster/humanforge.png");

   public RenderInfHuman(RenderManager manager) {
      super(manager, new ModelInfHuman(), 0.5F);
   }

   protected void preRenderCallback(EntityInfHuman entity, float partialTickTime) {
      float f = entity.getSelfeFlashIntensity(partialTickTime);
      float ff = entity.getSelfeFlashIntensity2();
      float f1 = 1.0F + MathHelper.func_76126_a(f * 100.0F) * f * 0.01F;
      f = MathHelper.func_76131_a(f, 0.0F, 1.0F);
      f *= f;
      f *= f;
      float f2 = (1.0F + f * 0.4F) * f1;
      float f3 = (1.0F + f * 0.1F) / f1;
      GlStateManager.func_179152_a(f2, ff * f3, f2);
   }

   protected ResourceLocation getEntityTexture(EntityInfHuman entity) {
      if (entity.func_145818_k_() && "Kim".equalsIgnoreCase(entity.func_95999_t())) {
         return TEXTURE_KIM;
      } else {
         switch (entity.getSkin()) {
            case 1:
               return TEXTURE_ALT;
            case 2:
               return TEXTURE_EATEN;
            case 3:
               return TEXTURE_FLOOD;
            case 120:
               return TEXTURE_FROZEN;
            default:
               return TEXTURE_DEFAULT;
         }
      }
   }
}
