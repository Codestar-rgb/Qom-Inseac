/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package com.subspaceparasite.client.renderer.entity.inborn;

import com.subspaceparasite.client.model.entity.inborn.ModelViin;
import com.subspaceparasite.client.renderer.RenderSRP;
import com.subspaceparasite.entity.monster.inborn.EntityViin;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderViin
extends RenderSRP<EntityViin> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("subspaceparasite:textures/entity/monster/lice.png");

    public RenderViin(RenderManager manager) {
        super(manager, new ModelViin(), 0.5f);
    }

    protected ResourceLocation getEntityTexture(EntityViin entity) {
        return TEXTURES;
    }

    protected void applyRotations(EntityViin entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.func_77043_a((EntityLivingBase)entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
}

