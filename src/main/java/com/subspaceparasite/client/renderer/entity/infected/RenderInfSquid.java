/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package com.subspaceparasite.client.renderer.entity.infected;

import com.subspaceparasite.client.model.entity.infected.ModelInfSquid;
import com.subspaceparasite.client.renderer.RenderSRP;
import com.subspaceparasite.entity.monster.infected.EntityInfSquid;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderInfSquid
extends RenderSRP<EntityInfSquid> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("subspaceparasite:textures/entity/monster/squid.png");

    public RenderInfSquid(RenderManager manager) {
        super(manager, new ModelInfSquid(), 0.5f);
    }

    protected void preRenderCallback(EntityInfSquid entitylivingbaseIn, float partialTickTime) {
    }

    protected ResourceLocation getEntityTexture(EntityInfSquid entity) {
        return TEXTURES;
    }

    protected void applyRotations(EntityInfSquid entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.func_77043_a((EntityLivingBase)entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
}

