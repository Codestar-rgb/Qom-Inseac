/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package com.subspaceparasite.client.renderer.entity.infected.head;

import com.subspaceparasite.client.model.entity.infected.head.ModelInfPlayerHead;
import com.subspaceparasite.client.renderer.RenderSRP;
import com.subspaceparasite.entity.monster.infected.head.EntityInfPlayerHead;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderInfPlayerHead
extends RenderSRP<EntityInfPlayerHead> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("subspaceparasite:textures/entity/monster/playerh.png");

    public RenderInfPlayerHead(RenderManager manager) {
        super(manager, new ModelInfPlayerHead(), 0.6f);
    }

    protected ResourceLocation getEntityTexture(EntityInfPlayerHead entity) {
        return TEXTURES;
    }

    protected void applyRotations(EntityInfPlayerHead entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.func_77043_a((EntityLivingBase)entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
}

