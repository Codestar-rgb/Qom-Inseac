/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package com.subspaceparasite.client.renderer.entity.infected.head;

import com.subspaceparasite.client.model.entity.infected.head.ModelInfHorseHead;
import com.subspaceparasite.client.renderer.RenderSP;
import com.subspaceparasite.entity.monster.infected.head.EntityInfHorseHead;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderInfHorseHead
extends RenderSP<EntityInfHorseHead> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("subspaceparasite:textures/entity/monster/horseh.png");

    public RenderInfHorseHead(RenderManager manager) {
        super(manager, new ModelInfHorseHead(), 0.6f);
    }

    protected ResourceLocation getEntityTexture(EntityInfHorseHead entity) {
        return TEXTURES;
    }

    protected void applyRotations(EntityInfHorseHead entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.func_77043_a((EntityLivingBase)entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
}

