package com.dhanantry.scapeandrunparasites.client.renderer.entity.primitive;

import com.dhanantry.scapeandrunparasites.client.model.entity.primitive.ModelNogla;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderMalleable;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityNogla;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Locale;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;

public class RenderNogla extends RenderMalleable<EntityNogla> {
   public static final ResourceLocation TEXTURE = new ResourceLocation("srparasites:textures/entity/monster/nogla.png");
   public static final ResourceLocation TEXTURE2 = new ResourceLocation("srparasites:textures/entity/monster/noglasp1.png");
   public static final ResourceLocation TEXTUREV = new ResourceLocation("srparasites:textures/entity/monster/noglav.png");
   public static final ResourceLocation TEXTUREB = new ResourceLocation("srparasites:textures/entity/monster/noglab.png");
   public static final ResourceLocation TEXTUREH = new ResourceLocation("srparasites:textures/entity/monster/noglah.png");
   public static final ResourceLocation STEXTURE = new ResourceLocation("srparasites:textures/entity/monster/snogla.png");
   public static final ResourceLocation RICARDO_TEX = new ResourceLocation("srparasites:textures/entity/monster/ricardo.png");
   public static final ResourceLocation RICARDO_BALD_TEX = new ResourceLocation("srparasites:textures/entity/monster/ricardo_bald.png");
   public static final ResourceLocation FROZEN_TEXTURE = new ResourceLocation("srparasites:textures/entity/monster/snowvariants/primitivereekerfrozen.png");

   public RenderNogla(RenderManager manager) {
      super(manager, new ModelNogla(), 1.3F);
   }

   protected void preRenderCallback(EntityNogla entitylivingbaseIn, float partialTickTime) {
      float f = entitylivingbaseIn.getSelfeFlashIntensity(partialTickTime);
      float f1 = 1.0F + MathHelper.func_76126_a(f * 100.0F) * f * 0.01F;
      f = MathHelper.func_76131_a(f, 0.0F, 1.0F);
      f *= f;
      f *= f;
      float f2 = (1.0F + f * 0.4F) * f1;
      float f3 = (1.0F + f * 0.1F) / f1;
      GlStateManager.func_179152_a(f2, f3, f2);
   }

   protected ResourceLocation getEntityTexture(EntityNogla entity) {
      if (entity.isRicardoBald()) {
         return RICARDO_BALD_TEX;
      } else if (this.isRicardoName(entity)) {
         return RICARDO_TEX;
      } else {
         switch (entity.getSkin()) {
            case 1:
               return TEXTURE2;
            case 5:
               return TEXTUREV;
            case 6:
               return TEXTUREB;
            case 7:
               return TEXTUREH;
            case 120:
               return STEXTURE;
            default:
               return TEXTURE;
         }
      }
   }

   private boolean isRicardoName(EntityNogla entity) {
      if (!SRPConfigMobs.noglaRicardoVariantEnabled) {
         return false;
      } else if (!entity.func_145818_k_()) {
         return false;
      } else {
         String raw = TextFormatting.func_110646_a(entity.func_95999_t());
         if (raw == null) {
            return false;
         } else {
            String name = normalizeLower(raw);
            if ("ricardo".equals(name)) {
               return true;
            } else {
               String localized = I18n.func_135052_a("entity.srparasites.nametag.ricardo", new Object[0]);
               return localized != null && !localized.isEmpty() && name.equals(normalizeLower(localized));
            }
         }
      }
   }

   private static String normalizeLower(String s) {
      String n = Normalizer.normalize(s, Form.NFD).replaceAll("\\p{M}", "");
      return n.trim().toLowerCase(Locale.ROOT);
   }
}
