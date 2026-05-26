/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.MathHelper
 */
package com.subspaceparasite.client.renderer.entity.infected;

import com.subspaceparasite.client.model.entity.infected.ModelInfSheep;
import com.subspaceparasite.client.renderer.RenderSP;
import com.subspaceparasite.entity.monster.infected.EntityInfSheep;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderInfSheep
extends RenderSP<EntityInfSheep> {
    private static final ResourceLocation TEX_WHITE = new ResourceLocation("subspaceparasite", "textures/entity/monster/sheep.png");
    private static final ResourceLocation TEX_GREY = new ResourceLocation("subspaceparasite", "textures/entity/monster/sheep_grey.png");
    private static final ResourceLocation TEX_BLACK = new ResourceLocation("subspaceparasite", "textures/entity/monster/sheep_black.png");

    public RenderInfSheep(RenderManager manager) {
        super(manager, new ModelInfSheep(), 0.5f);
    }

    protected void preRenderCallback(EntityInfSheep entitylivingbaseIn, float partialTickTime) {
        float f = entitylivingbaseIn.getSelfeFlashIntensity(partialTickTime);
        float ff = entitylivingbaseIn.getSelfeFlashIntensity2();
        float f1 = 1.0f + MathHelper.func_76126_a((float)(f * 100.0f)) * f * 0.01f;
        f = MathHelper.func_76131_a((float)f, (float)0.0f, (float)1.0f);
        f *= f;
        f *= f;
        float f2 = (1.0f + f * 0.4f) * f1;
        float f3 = (1.0f + f * 0.1f) / f1;
        GlStateManager.func_179152_a((float)f2, (float)(ff * f3), (float)f2);
    }

    protected ResourceLocation getEntityTexture(EntityInfSheep entity) {
        switch (entity.getTextureVariant()) {
            case 1: {
                return TEX_GREY;
            }
            case 2: {
                return TEX_BLACK;
            }
        }
        return TEX_WHITE;
    }

    protected void applyRotations(EntityInfSheep entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.func_77043_a((EntityLivingBase)entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
}

