/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.MathHelper
 */
package com.dhanantry.scapeandrunparasites.client.renderer.entity.inborn;

import com.dhanantry.scapeandrunparasites.client.model.entity.inborn.ModelLesh;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityLesh;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderLesh
extends RenderLiving<EntityLesh> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/lesh.png");

    public RenderLesh(RenderManager manager) {
        super(manager, (ModelBase)new ModelLesh(), 0.2f);
    }

    protected void preRenderCallback(EntityLesh entitylivingbaseIn, float partialTickTime) {
        float ff = entitylivingbaseIn.getSelfeFlashIntensityS();
        float f = entitylivingbaseIn.getSelfeFlashIntensity(partialTickTime);
        float f1 = 1.0f + MathHelper.func_76126_a((float)(f * 100.0f)) * f * 0.01f;
        f = MathHelper.func_76131_a((float)f, (float)0.0f, (float)1.0f);
        f *= f;
        f *= f;
        float f2 = (1.0f + f * 0.4f) * f1;
        float f3 = (1.0f + f * 0.1f) / f1;
        GlStateManager.func_179152_a((float)(ff * f2), (float)(ff * f3), (float)(ff * f2));
    }

    protected ResourceLocation getEntityTexture(EntityLesh entity) {
        return TEXTURES;
    }

    protected void applyRotations(EntityLesh entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.func_77043_a((EntityLivingBase)entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
}

