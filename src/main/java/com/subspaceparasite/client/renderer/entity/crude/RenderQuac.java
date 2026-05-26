/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.MathHelper
 */
package com.subspaceparasite.client.renderer.entity.crude;

import com.subspaceparasite.client.model.entity.crude.ModelQuac;
import com.subspaceparasite.client.renderer.RenderSP;
import com.subspaceparasite.entity.monster.crude.EntityQuac;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderQuac
extends RenderSP<EntityQuac> {
    public static final ResourceLocation TEXTUREH = new ResourceLocation("subspaceparasite:textures/entity/monster/quac.png");
    public static final ResourceLocation TEXTURE_FROZEN = new ResourceLocation("subspaceparasite:textures/entity/monster/snowvariants/test.png");

    public RenderQuac(RenderManager manager) {
        super(manager, new ModelQuac(), 0.0f);
    }

    protected ResourceLocation getEntityTexture(EntityQuac entity) {
        switch (entity.getSkin()) {
            case 120: {
                return TEXTURE_FROZEN;
            }
        }
        return TEXTUREH;
    }

    protected void preRenderCallback(EntityQuac entitylivingbaseIn, float partialTickTime) {
        float xx = 1.0f;
        float yy = 1.0f;
        float f = entitylivingbaseIn.getSelfeFlashIntensity(partialTickTime);
        float f1 = 1.0f + MathHelper.func_76126_a((float)(f * 100.0f)) * f * 0.01f;
        f = MathHelper.func_76131_a((float)f, (float)0.0f, (float)1.0f);
        f *= f;
        f *= f;
        float f2 = (1.0f + f * 0.4f) * f1;
        float f3 = (1.0f + f * 0.1f) / f1;
        GlStateManager.func_179152_a((float)(xx * f2), (float)(yy * f3), (float)f2);
    }
}

