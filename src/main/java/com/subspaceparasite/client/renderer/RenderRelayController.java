/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableMap$Builder
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.block.model.BakedQuad
 *  net.minecraft.client.renderer.block.model.IBakedModel
 *  net.minecraft.client.renderer.texture.TextureAtlasSprite
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.EnumSkyBlock
 *  net.minecraftforge.client.model.IModel
 *  net.minecraftforge.client.model.obj.OBJLoader
 *  net.minecraftforge.client.model.obj.OBJModel
 *  net.minecraftforge.common.model.IModelState
 *  net.minecraftforge.common.model.TRSRTransformation
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.lwjgl.opengl.GL11
 */
package com.subspaceparasite.client.renderer;

import com.subspaceparasite.tileentity.TileEntityRelayController;
import com.google.common.collect.ImmutableMap;
import java.util.List;
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
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderRelayController
extends TileEntitySpecialRenderer<TileEntityRelayController> {
    private static final String MAT_MAIN = "m_54acd2a9-1b93-8c80-6bd1-4d05c195c2a9";
    private static final String MTL_TEX_NAME = "pasted.png";
    private static final ResourceLocation TEX_OFF = new ResourceLocation("subspaceparasite", "textures/blocks/arraytowertexture.png");
    private static final ResourceLocation TEX_ON = new ResourceLocation("subspaceparasite", "textures/blocks/arraytowertextureon.png");
    private IBakedModel bakedOff;
    private IBakedModel bakedOn;

    public boolean isGlobalRenderer(TileEntityRelayController te) {
        return true;
    }

    private static String atlasSprite(String texNameNoExt) {
        return "subspaceparasite:blocks/" + texNameNoExt;
    }

    private IBakedModel bakeForceAtlasSprite(String texNameNoExt) throws Exception {
        IModel raw = OBJLoader.INSTANCE.loadModel(new ResourceLocation("subspaceparasite", "models/block/arraytowerlarge.obj"));
        if (raw instanceof OBJModel) {
            raw = ((OBJModel)raw).process(ImmutableMap.of((Object)"flip-v", (Object)"true"));
        }
        String full = RenderRelayController.atlasSprite(texNameNoExt);
        ImmutableMap.Builder map = ImmutableMap.builder();
        map.put((Object)MAT_MAIN, (Object)full);
        map.put((Object)MTL_TEX_NAME, (Object)full);
        map.put((Object)"pasted", (Object)full);
        map.put((Object)"OBJModel.Default.Texture.Name", (Object)full);
        map.put((Object)"OBJModel.White.Texture.Name", (Object)full);
        map.put((Object)"default", (Object)full);
        map.put((Object)"particle", (Object)full);
        map.put((Object)"texture", (Object)full);
        map.put((Object)"tex", (Object)full);
        IModel retextured = raw.retexture(map.build());
        TextureAtlasSprite forced = Minecraft.func_71410_x().func_147117_R().func_110572_b(full);
        Function<ResourceLocation, TextureAtlasSprite> sprites = loc -> forced;
        return retextured.bake((IModelState)TRSRTransformation.identity(), DefaultVertexFormats.field_176600_a, sprites);
    }

    private void ensureBaked() {
        if (this.bakedOff != null && this.bakedOn != null) {
            return;
        }
        try {
            this.bakedOff = this.bakeForceAtlasSprite("arraytowertexture");
            this.bakedOn = this.bakeForceAtlasSprite("arraytowertextureon");
        }
        catch (Exception e) {
            IBakedModel missing;
            this.bakedOff = missing = Minecraft.func_71410_x().func_175602_ab().func_175023_a().func_178126_b().func_174951_a();
            this.bakedOn = missing;
            e.printStackTrace();
        }
    }

    public void render(TileEntityRelayController te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        if (te == null || te.func_145831_w() == null) {
            return;
        }
        if (RenderRelayController.isOptifineShadowPass()) {
            return;
        }
        this.ensureBaked();
        boolean cooldownActive = false;
        try {
            cooldownActive = te.getCooldownRemainingTicks() > 0;
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        IBakedModel model = cooldownActive ? this.bakedOn : this.bakedOff;
        Minecraft mc = Minecraft.func_71410_x();
        BlockPos pos = te.func_174877_v();
        int blockL = te.func_145831_w().func_175642_b(EnumSkyBlock.BLOCK, pos);
        int skyL = te.func_145831_w().func_175642_b(EnumSkyBlock.SKY, pos);
        int level = Math.max(blockL, skyL);
        float brightness = 0.35f + (float)level / 15.0f * 0.65f;
        int r = (int)(brightness * 255.0f) & 0xFF;
        int g = (int)(brightness * 255.0f) & 0xFF;
        int b = (int)(brightness * 255.0f) & 0xFF;
        GlStateManager.func_179094_E();
        GlStateManager.func_179137_b((double)(x + 0.5), (double)y, (double)(z + 0.5));
        GlStateManager.func_179129_p();
        GlStateManager.func_179141_d();
        GlStateManager.func_179092_a((int)516, (float)0.1f);
        GlStateManager.func_179140_f();
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        if (RenderRelayController.isOptifineInstalled()) {
            ResourceLocation tex = cooldownActive ? TEX_ON : TEX_OFF;
            mc.func_110434_K().func_110577_a(tex);
            GL11.glTexParameteri((int)3553, (int)10241, (int)9728);
            GL11.glTexParameteri((int)3553, (int)10240, (int)9728);
            Tessellator tess = Tessellator.func_178181_a();
            BufferBuilder buf = tess.func_178180_c();
            buf.func_181668_a(7, DefaultVertexFormats.field_181709_i);
            List quads = model.func_188616_a(null, null, 0L);
            for (BakedQuad q : quads) {
                RenderRelayController.drawQuadAsStandalonePNG(buf, q, r, g, b, 255);
            }
            tess.func_78381_a();
        } else {
            mc.func_110434_K().func_110577_a(TextureMap.field_110575_b);
            mc.func_175602_ab().func_175019_b().func_178262_a(model, brightness, 1.0f, 1.0f, 1.0f);
        }
        GlStateManager.func_179089_o();
        GlStateManager.func_179118_c();
        GlStateManager.func_179121_F();
    }

    private static void drawQuadAsStandalonePNG(BufferBuilder buf, BakedQuad q, int r, int g, int b, int a) {
        int[] data = q.func_178209_a();
        int stride = data.length / 4;
        TextureAtlasSprite spr = q.func_187508_a();
        for (int v = 0; v < 4; ++v) {
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
                if (du != 0.0f) {
                    u = (uSprite - u0) / du;
                }
                if (dv != 0.0f) {
                    vv = (vSprite - v0) / dv;
                }
            }
            buf.func_181662_b((double)x, (double)y, (double)z).func_187315_a((double)u, (double)vv).func_181669_b(r, g, b, a).func_181675_d();
        }
    }

    private static boolean isOptifineInstalled() {
        try {
            Class.forName("net.optifine.Config");
            return true;
        }
        catch (Throwable throwable) {
            try {
                Class.forName("optifine.OptiFineClassTransformer");
                return true;
            }
            catch (Throwable throwable2) {
                try {
                    Class.forName("net.optifine.shaders.Shaders");
                    return true;
                }
                catch (Throwable throwable3) {
                    return false;
                }
            }
        }
    }

    private static boolean isOptifineShadowPass() {
        try {
            Class<?> shaders = Class.forName("net.optifine.shaders.Shaders");
            return shaders.getField("isShadowPass").getBoolean(null);
        }
        catch (Throwable t) {
            return false;
        }
    }
}

