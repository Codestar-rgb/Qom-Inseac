/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package com.subspaceparasite.client.renderer.entity.infected.head;

import com.subspaceparasite.client.model.entity.infected.head.ModelInfEndermanHead;
import com.subspaceparasite.client.renderer.RenderSRP;
import com.subspaceparasite.entity.monster.infected.head.EntityInfEndermanHead;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderInfEndermanHead
extends RenderSRP<EntityInfEndermanHead> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("subspaceparasite:textures/entity/monster/endermanh.png");

    public RenderInfEndermanHead(RenderManager manager) {
        super(manager, new ModelInfEndermanHead(), 0.6f);
    }

    protected ResourceLocation getEntityTexture(EntityInfEndermanHead entity) {
        return TEXTURES;
    }

    protected void applyRotations(EntityInfEndermanHead entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.func_77043_a((EntityLivingBase)entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
}

