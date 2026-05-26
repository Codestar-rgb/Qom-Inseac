/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package com.subspaceparasite.client.renderer.entity.hijacked;

import com.subspaceparasite.client.model.entity.hijacked.ModelHiSkeleton;
import com.subspaceparasite.client.renderer.RenderSP;
import com.subspaceparasite.entity.monster.hijacked.EntityHiSkeleton;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderHiSkeleton
extends RenderSP<EntityHiSkeleton> {
    public static final ResourceLocation TEXTURE = new ResourceLocation("subspaceparasite:textures/entity/monster/hiskeleton.png");

    public RenderHiSkeleton(RenderManager manager) {
        super(manager, new ModelHiSkeleton(), 0.6f);
    }

    protected ResourceLocation getEntityTexture(EntityHiSkeleton entity) {
        return TEXTURE;
    }

    protected void applyRotations(EntityHiSkeleton entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.func_77043_a((EntityLivingBase)entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
}

