/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.texture.DynamicTexture
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package com.subspaceparasite.client.renderer;

import com.subspaceparasite.client.renderer.RenderSRP;
import com.subspaceparasite.entity.ai.misc.EntityPMalleable;
import java.nio.FloatBuffer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public abstract class RenderMalleable<T extends EntityPMalleable>
extends RenderSRP<T> {
    private static final DynamicTexture TEXTURE_BRIGHTNESS = new DynamicTexture(16, 16);

    public RenderMalleable(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
        super(rendermanagerIn, modelbaseIn, shadowsizeIn);
    }

    protected boolean setBrightness(T entitylivingbaseIn, float partialTicks, boolean combineTextures) {
        boolean flag1;
        float f = entitylivingbaseIn.func_70013_c();
        int i = this.func_77030_a((EntityLivingBase)entitylivingbaseIn, f, partialTicks);
        boolean flag = (i >> 24 & 0xFF) > 0;
        boolean bl = flag1 = ((EntityPMalleable)entitylivingbaseIn).field_70737_aN > 0 || ((EntityPMalleable)entitylivingbaseIn).field_70725_aQ > 0;
        if (!flag && !flag1) {
            return false;
        }
        if (!flag && !combineTextures) {
            return false;
        }
        GlStateManager.func_179138_g((int)OpenGlHelper.field_77478_a);
        GlStateManager.func_179098_w();
        GlStateManager.func_187399_a((int)8960, (int)8704, (int)OpenGlHelper.field_176095_s);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176099_x, (int)8448);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176098_y, (int)OpenGlHelper.field_77478_a);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176097_z, (int)OpenGlHelper.field_176093_u);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176081_B, (int)768);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176082_C, (int)768);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176077_E, (int)7681);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176078_F, (int)OpenGlHelper.field_77478_a);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176085_I, (int)770);
        GlStateManager.func_179138_g((int)OpenGlHelper.field_77476_b);
        GlStateManager.func_179098_w();
        GlStateManager.func_187399_a((int)8960, (int)8704, (int)OpenGlHelper.field_176095_s);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176099_x, (int)OpenGlHelper.field_176094_t);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176098_y, (int)OpenGlHelper.field_176092_v);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176097_z, (int)OpenGlHelper.field_176091_w);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176080_A, (int)OpenGlHelper.field_176092_v);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176081_B, (int)768);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176082_C, (int)768);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176076_D, (int)770);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176077_E, (int)7681);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176078_F, (int)OpenGlHelper.field_176091_w);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176085_I, (int)770);
        this.field_177095_g.position(0);
        if (flag1) {
            switch (((EntityPMalleable)entitylivingbaseIn).getHitStatus()) {
                case 0: {
                    this.field_177095_g.put(1.0f);
                    this.field_177095_g.put(0.0f);
                    this.field_177095_g.put(0.0f);
                    this.field_177095_g.put(0.3f);
                    break;
                }
                case 1: {
                    this.field_177095_g.put(0.0f);
                    this.field_177095_g.put(1.0f);
                    this.field_177095_g.put(0.0f);
                    this.field_177095_g.put(0.3f);
                    break;
                }
                case 2: {
                    this.field_177095_g.put(1.0f);
                    this.field_177095_g.put(0.0f);
                    this.field_177095_g.put(1.0f);
                    this.field_177095_g.put(0.3f);
                    break;
                }
                case 3: {
                    this.field_177095_g.put(0.1f);
                    this.field_177095_g.put(0.2f);
                    this.field_177095_g.put(0.2f);
                    this.field_177095_g.put(1.0f);
                }
            }
        } else {
            float f1 = (float)(i >> 24 & 0xFF) / 255.0f;
            float f2 = (float)(i >> 16 & 0xFF) / 255.0f;
            float f3 = (float)(i >> 8 & 0xFF) / 255.0f;
            float f4 = (float)(i & 0xFF) / 255.0f;
            this.field_177095_g.put(f2);
            this.field_177095_g.put(f3);
            this.field_177095_g.put(f4);
            this.field_177095_g.put(1.0f - f1);
        }
        this.field_177095_g.flip();
        GlStateManager.func_187448_b((int)8960, (int)8705, (FloatBuffer)this.field_177095_g);
        GlStateManager.func_179138_g((int)OpenGlHelper.field_176096_r);
        GlStateManager.func_179098_w();
        GlStateManager.func_179144_i((int)TEXTURE_BRIGHTNESS.func_110552_b());
        GlStateManager.func_187399_a((int)8960, (int)8704, (int)OpenGlHelper.field_176095_s);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176099_x, (int)8448);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176098_y, (int)OpenGlHelper.field_176091_w);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176097_z, (int)OpenGlHelper.field_77476_b);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176081_B, (int)768);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176082_C, (int)768);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176077_E, (int)7681);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176078_F, (int)OpenGlHelper.field_176091_w);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176085_I, (int)770);
        GlStateManager.func_179138_g((int)OpenGlHelper.field_77478_a);
        return true;
    }

    public ResourceLocation getGlowTexture(ResourceLocation base) {
        String path = base.func_110623_a();
        int ind = path.lastIndexOf(46);
        return new ResourceLocation(base.func_110624_b(), path.substring(0, ind) + "_glow" + path.substring(ind));
    }

    public ResourceLocation getBaseTexture(T entitylivingbaseIn) {
        return this.func_110775_a((Entity)entitylivingbaseIn);
    }
}

