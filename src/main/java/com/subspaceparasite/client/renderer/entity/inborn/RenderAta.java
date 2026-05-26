/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package com.subspaceparasite.client.renderer.entity.inborn;

import com.subspaceparasite.client.model.entity.inborn.ModelAta;
import com.subspaceparasite.client.renderer.RenderSP;
import com.subspaceparasite.entity.monster.inborn.EntityAta;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderAta
extends RenderSP<EntityAta> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("subspaceparasite:textures/entity/monster/gnat.png");

    public RenderAta(RenderManager manager) {
        super(manager, new ModelAta(), 0.5f);
    }

    protected ResourceLocation getEntityTexture(EntityAta entity) {
        return TEXTURES;
    }

    protected void applyRotations(EntityAta entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.func_77043_a((EntityLivingBase)entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
}

