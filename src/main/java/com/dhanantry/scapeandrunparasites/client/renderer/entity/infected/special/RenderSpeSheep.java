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
package com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.special;

import com.dhanantry.scapeandrunparasites.client.model.entity.infected.special.ModelSpeSheep;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderSRP;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.special.EntitySpeSheep;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderSpeSheep
extends RenderSRP<EntitySpeSheep> {
    public static final ResourceLocation TEXTURE = new ResourceLocation("srparasites:textures/entity/monster/spesheep.png");

    public RenderSpeSheep(RenderManager manager) {
        super(manager, new ModelSpeSheep(), 0.5f);
    }

    protected void preRenderCallback(EntitySpeSheep entitylivingbaseIn, float partialTickTime) {
        float f = entitylivingbaseIn.getSelfeFlashIntensity(partialTickTime);
        float f1 = 1.0f + MathHelper.func_76126_a((float)(f * 100.0f)) * f * 0.01f;
        f = MathHelper.func_76131_a((float)f, (float)0.0f, (float)1.0f);
        f *= f;
        f *= f;
        float f2 = (1.0f + f * 0.4f) * f1;
        float f3 = (1.0f + f * 0.1f) / f1;
        GlStateManager.func_179152_a((float)(f2 * 1.1f), (float)(f3 * 1.1f), (float)(f2 * 1.1f));
    }

    protected ResourceLocation getEntityTexture(EntitySpeSheep entity) {
        return TEXTURE;
    }

    protected void applyRotations(EntitySpeSheep entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.func_77043_a((EntityLivingBase)entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
}

