/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.MathHelper
 */
package com.subspaceparasite.client.renderer.entity.primitive;

import com.subspaceparasite.client.model.entity.primitive.ModelWymo;
import com.subspaceparasite.client.renderer.RenderMalleable;
import com.subspaceparasite.entity.monster.primitive.EntityWymo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderWymo
extends RenderMalleable<EntityWymo> {
    public static final ResourceLocation TEXTUREH = new ResourceLocation("subspaceparasite:textures/entity/monster/wymo.png");
    public static final ResourceLocation FROZEN_TEXTURE = new ResourceLocation("subspaceparasite:textures/entity/monster/snowvariants/primitivetozoonfrozen.png");

    public RenderWymo(RenderManager manager) {
        super(manager, new ModelWymo(), 0.0f);
    }

    protected ResourceLocation getEntityTexture(EntityWymo entity) {
        return TEXTUREH;
    }

    protected void preRenderCallback(EntityWymo entitylivingbaseIn, float partialTickTime) {
        float xx = 1.0f;
        float yy = 1.0f;
        switch (entitylivingbaseIn.getBodyNumber()) {
            case 1: {
                xx = 1.23f;
                yy = 1.23f;
                break;
            }
            case 2: {
                xx = 1.47f;
                yy = 1.47f;
                break;
            }
            case 3: {
                xx = 1.23f;
                yy = 1.27f;
                break;
            }
            case 4: {
                xx = 1.05f;
                yy = 1.17f;
            }
        }
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

