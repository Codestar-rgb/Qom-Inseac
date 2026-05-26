/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package com.subspaceparasite.client.renderer.entity.infected;

import com.subspaceparasite.client.model.entity.infected.ModelInfDragonE;
import com.subspaceparasite.client.renderer.RenderSP;
import com.subspaceparasite.entity.monster.infected.EntityInfDragonE;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderInfDragonE
extends RenderSP<EntityInfDragonE> {
    public static final ResourceLocation TEXTURE = new ResourceLocation("subspaceparasite:textures/entity/monster/infdragone.png");

    public RenderInfDragonE(RenderManager manager) {
        super(manager, new ModelInfDragonE(), 1.2f);
    }

    protected ResourceLocation getEntityTexture(EntityInfDragonE entity) {
        return TEXTURE;
    }

    protected void applyRotations(EntityInfDragonE entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.func_77043_a((EntityLivingBase)entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
}

