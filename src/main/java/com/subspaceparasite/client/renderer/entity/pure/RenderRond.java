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
package com.subspaceparasite.client.renderer.entity.pure;

import com.subspaceparasite.client.model.entity.pure.ModelRond;
import com.subspaceparasite.client.renderer.RenderMalleable;
import com.subspaceparasite.entity.monster.pure.EntityRond;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderRond
extends RenderMalleable<EntityRond> {
    public static final ResourceLocation TEXTURE = new ResourceLocation("subspaceparasite:textures/entity/monster/rond.png");

    public RenderRond(RenderManager manager) {
        super(manager, new ModelRond(), 0.5f);
    }

    protected void preRenderCallback(EntityRond entitylivingbaseIn, float partialTickTime) {
        float f = entitylivingbaseIn.getSelfeFlashIntensity(partialTickTime);
        float f1 = 1.0f + MathHelper.func_76126_a((float)(f * 100.0f)) * f * 0.01f;
        f = MathHelper.func_76131_a((float)f, (float)0.0f, (float)1.0f);
        f *= f;
        f *= f;
        float f2 = (1.0f + f * 0.4f) * f1;
        float f3 = (1.0f + f * 0.1f) / f1;
        GlStateManager.func_179152_a((float)f2, (float)f3, (float)f2);
    }

    protected ResourceLocation getEntityTexture(EntityRond entity) {
        switch (entity.getSkin()) {
            default: 
        }
        return TEXTURE;
    }

    protected void applyRotations(EntityRond entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.func_77043_a((EntityLivingBase)entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
}

