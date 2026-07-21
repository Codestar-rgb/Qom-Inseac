package com.dhanantry.scapeandrunparasites.client.renderer;

import com.dhanantry.scapeandrunparasites.tileentity.TileEntityRelayController;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import java.util.function.Function;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderRelayController extends TileEntitySpecialRenderer<TileEntityRelayController> {
   private static final String MAT_MAIN = "m_54acd2a9-1b93-8c80-6bd1-4d05c195c2a9";
   private static final String MTL_TEX_NAME = "pasted.png";
   private static final ResourceLocation TEX_OFF = new ResourceLocation("srparasites", "textures/blocks/arraytowertexture.png");
   private static final ResourceLocation TEX_ON = new ResourceLocation("srparasites", "textures/blocks/arraytowertextureon.png");
   private IBakedModel bakedOff;
   private IBakedModel bakedOn;

   public boolean isGlobalRenderer(TileEntityRelayController te) {
      return true;
   }

   private static String atlasSprite(String texNameNoExt) {
      return "srparasites:blocks/" + texNameNoExt;
   }

   private IBakedModel bakeForceAtlasSprite(String texNameNoExt) throws Exception {
      IModel raw = OBJLoader.INSTANCE.loadModel(new ResourceLocation("srparasites", "models/block/arraytowerlarge.obj"));
      if (raw instanceof OBJModel) {
         raw = ((OBJModel)raw).process(ImmutableMap.of("flip-v", "true"));
      }

      String full = atlasSprite(texNameNoExt);
      Builder<String, String> map = ImmutableMap.builder();
      map.put("m_54acd2a9-1b93-8c80-6bd1-4d05c195c2a9", full);
      map.put("pasted.png", full);
      map.put("pasted", full);
      map.put("OBJModel.Default.Texture.Name", full);
      map.put("OBJModel.White.Texture.Name", full);
      map.put("default", full);
      map.put("particle", full);
      map.put("texture", full);
      map.put("tex", full);
      IModel retextured = raw.retexture(map.build());
      TextureAtlasSprite forced = Minecraft.func_71410_x().func_147117_R().func_110572_b(full);
      Function<ResourceLocation, TextureAtlasSprite> sprites = loc -> forced;
      return retextured.bake(TRSRTransformation.identity(), DefaultVertexFormats.field_176600_a, sprites);
   }

   private void ensureBaked() {
      if (this.bakedOff == null || this.bakedOn == null) {
         try {
            this.bakedOff = this.bakeForceAtlasSprite("arraytowertexture");
            this.bakedOn = this.bakeForceAtlasSprite("arraytowertextureon");
         } catch (Exception var3) {
            IBakedModel missing = Minecraft.func_71410_x().func_175602_ab().func_175023_a().func_178126_b().func_174951_a();
            this.bakedOff = missing;
            this.bakedOn = missing;
            var3.printStackTrace();
         }
      }
   }

   public void render(TileEntityRelayController te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
      if (te != null && te.func_145831_w() != null) {
         if (!isOptifineShadowPass()) {
            this.ensureBaked();
            boolean cooldownActive = false;

            try {
               cooldownActive = te.getCooldownRemainingTicks() > 0;
            } catch (Throwable var28) {
            }

            IBakedModel model = cooldownActive ? this.bakedOn : this.bakedOff;
            Minecraft mc = Minecraft.func_71410_x();
            BlockPos pos = te.func_174877_v();
            int blockL = te.func_145831_w().func_175642_b(EnumSkyBlock.BLOCK, pos);
            int skyL = te.func_145831_w().func_175642_b(EnumSkyBlock.SKY, pos);
            int level = Math.max(blockL, skyL);
            float brightness = 0.35F + level / 15.0F * 0.65F;
            int r = (int)(brightness * 255.0F) & 0xFF;
            int g = (int)(brightness * 255.0F) & 0xFF;
            int b = (int)(brightness * 255.0F) & 0xFF;
            GlStateManager.func_179094_E();
            GlStateManager.func_179137_b(x + 0.5, y, z + 0.5);
            GlStateManager.func_179129_p();
            GlStateManager.func_179141_d();
            GlStateManager.func_179092_a(516, 0.1F);
            GlStateManager.func_179140_f();
            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
            if (isOptifineInstalled()) {
               ResourceLocation tex = cooldownActive ? TEX_ON : TEX_OFF;
               mc.func_110434_K().func_110577_a(tex);
               GL11.glTexParameteri(3553, 10241, 9728);
               GL11.glTexParameteri(3553, 10240, 9728);
               Tessellator tess = Tessellator.func_178181_a();
               BufferBuilder buf = tess.func_178180_c();
               buf.func_181668_a(7, DefaultVertexFormats.field_181709_i);

               for (BakedQuad q : model.func_188616_a(null, null, 0L)) {
                  drawQuadAsStandalonePNG(buf, q, r, g, b, 255);
               }

               tess.func_78381_a();
            } else {
               mc.func_110434_K().func_110577_a(TextureMap.field_110575_b);
               mc.func_175602_ab().func_175019_b().func_178262_a(model, brightness, 1.0F, 1.0F, 1.0F);
            }

            GlStateManager.func_179089_o();
            GlStateManager.func_179118_c();
            GlStateManager.func_179121_F();
         }
      }
   }

   private static void drawQuadAsStandalonePNG(BufferBuilder buf, BakedQuad q, int r, int g, int b, int a) {
      int[] data = q.func_178209_a();
      int stride = data.length / 4;
      TextureAtlasSprite spr = q.func_187508_a();

      for (int v = 0; v < 4; v++) {
         int i = v * stride;
         float x = Float.intBitsToFloat(data[i]);
         float y = Float.intBitsToFloat(data[i + 1]);
         float z = Float.intBitsToFloat(data[i + 2]);
         float uSprite = Float.intBitsToFloat(data[i + 4]);
         float vSprite = Float.intBitsToFloat(data[i + 5]);
         float u = uSprite;
         float vv = vSprite;
         if (spr != null) {
            float u0 = spr.func_94209_e();
            float v0 = spr.func_94206_g();
            float u1 = spr.func_94212_f();
            float v1 = spr.func_94210_h();
            float du = u1 - u0;
            float dv = v1 - v0;
            if (du != 0.0F) {
               u = (uSprite - u0) / du;
            }

            if (dv != 0.0F) {
               vv = (vSprite - v0) / dv;
            }
         }

         buf.func_181662_b(x, y, z).func_187315_a(u, vv).func_181669_b(r, g, b, a).func_181675_d();
      }
   }

   private static boolean isOptifineInstalled() {
      try {
         Class.forName("net.optifine.Config");
         return true;
      } catch (Throwable var3) {
         try {
            Class.forName("optifine.OptiFineClassTransformer");
            return true;
         } catch (Throwable var2) {
            try {
               Class.forName("net.optifine.shaders.Shaders");
               return true;
            } catch (Throwable var1) {
               return false;
            }
         }
      }
   }

   private static boolean isOptifineShadowPass() {
      try {
         Class<?> shaders = Class.forName("net.optifine.shaders.Shaders");
         return shaders.getField("isShadowPass").getBoolean(null);
      } catch (Throwable var1) {
         return false;
      }
   }
}
