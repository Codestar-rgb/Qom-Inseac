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

import com.subspaceparasite.client.model.entity.infected.ModelDorpa;
import com.subspaceparasite.client.renderer.RenderSP;
import com.subspaceparasite.entity.monster.infected.EntityDorpa;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderDorpa
extends RenderSP<EntityDorpa> {
    public static final ResourceLocation TEXTURE = new ResourceLocation("subspaceparasite:textures/entity/monster/dorpa.png");
    public static final ResourceLocation TEXTURE2 = new ResourceLocation("subspaceparasite:textures/entity/monster/dorpa2.png");

    public RenderDorpa(RenderManager manager) {
        super(manager, new ModelDorpa(), 1.2f);
    }

    protected void preRenderCallback(EntityDorpa entitylivingbaseIn, float partialTickTime) {
        float f = entitylivingbaseIn.getSelfeFlashIntensity(partialTickTime);
        float f1 = 1.0f + MathHelper.func_76126_a((float)(f * 100.0f)) * f * 0.01f;
        f = MathHelper.func_76131_a((float)f, (float)0.0f, (float)1.0f);
        f *= f;
        f *= f;
        float f2 = (1.0f + f * 0.4f) * f1;
        float f3 = (1.0f + f * 0.1f) / f1;
        GlStateManager.func_179152_a((float)(0.78f * f2), (float)(0.78f * f3), (float)(0.78f * f2));
    }

    protected ResourceLocation getEntityTexture(EntityDorpa entity) {
        switch (entity.getSkin()) {
            case 1: {
                return TEXTURE2;
            }
        }
        return TEXTURE;
    }

    protected void applyRotations(EntityDorpa entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.func_77043_a((EntityLivingBase)entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
}

