/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package com.subspaceparasite.client.renderer.entity.infected.head;

import com.subspaceparasite.client.model.entity.infected.head.ModelInfDragonEHead;
import com.subspaceparasite.client.renderer.RenderSP;
import com.subspaceparasite.entity.monster.infected.head.EntityInfDragonEHead;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderInfDragonEHead
extends RenderSP<EntityInfDragonEHead> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("subspaceparasite:textures/entity/monster/dragoneh.png");

    public RenderInfDragonEHead(RenderManager manager) {
        super(manager, new ModelInfDragonEHead(), 0.6f);
    }

    protected ResourceLocation getEntityTexture(EntityInfDragonEHead entity) {
        return TEXTURES;
    }

    protected void applyRotations(EntityInfDragonEHead entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.func_77043_a((EntityLivingBase)entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
}

