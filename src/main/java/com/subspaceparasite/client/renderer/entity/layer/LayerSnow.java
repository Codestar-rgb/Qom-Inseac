/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.client.renderer.entity.layers.LayerRenderer
 *  net.minecraft.util.ResourceLocation
 */
package com.subspaceparasite.client.renderer.entity.layer;

import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class LayerSnow<T extends EntityParasiteBase>
implements LayerRenderer<T> {
    private ResourceLocation LAYER;
    private final RenderLiving parasite;

    public LayerSnow(RenderLiving in, ResourceLocation l, double x, double y, double z) {
        this.parasite = in;
        this.LAYER = l;
    }

    public void doRenderLayer(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if (!((EntityParasiteBase)entitylivingbaseIn).getColdL()) {
            return;
        }
        this.parasite.func_110776_a(this.LAYER);
        GlStateManager.func_179147_l();
        if (entitylivingbaseIn.func_82150_aj()) {
            GlStateManager.func_179132_a((boolean)false);
        } else {
            GlStateManager.func_179132_a((boolean)true);
        }
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Minecraft.func_71410_x().field_71460_t.func_191514_d(true);
        this.parasite.func_177087_b().func_78088_a(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale + 0.0f);
        Minecraft.func_71410_x().field_71460_t.func_191514_d(false);
        this.parasite.func_177105_a(entitylivingbaseIn);
        GlStateManager.func_179084_k();
        GlStateManager.func_179141_d();
    }

    public boolean func_177142_b() {
        return false;
    }
}

