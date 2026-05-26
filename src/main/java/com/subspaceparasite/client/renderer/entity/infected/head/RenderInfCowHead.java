/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package com.subspaceparasite.client.renderer.entity.infected.head;

import com.subspaceparasite.client.model.entity.infected.head.ModelInfCowHead;
import com.subspaceparasite.client.renderer.RenderSP;
import com.subspaceparasite.entity.monster.infected.head.EntityInfCowHead;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderInfCowHead
extends RenderSP<EntityInfCowHead> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("subspaceparasite:textures/entity/monster/cowh.png");

    public RenderInfCowHead(RenderManager manager) {
        super(manager, new ModelInfCowHead(), 0.6f);
    }

    protected ResourceLocation getEntityTexture(EntityInfCowHead entity) {
        return TEXTURES;
    }

    protected void applyRotations(EntityInfCowHead entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.func_77043_a((EntityLivingBase)entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
}

