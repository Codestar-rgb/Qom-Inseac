/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package com.subspaceparasite.client.renderer.entity.hijacked;

import com.subspaceparasite.client.model.entity.hijacked.ModelHiGolem;
import com.subspaceparasite.client.renderer.RenderSRP;
import com.subspaceparasite.entity.monster.hijacked.EntityHiGolem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderHiGolem
extends RenderSRP<EntityHiGolem> {
    public static final ResourceLocation TEXTURE = new ResourceLocation("subspaceparasite:textures/entity/monster/higolem.png");

    public RenderHiGolem(RenderManager manager) {
        super(manager, new ModelHiGolem(), 0.6f);
    }

    protected ResourceLocation getEntityTexture(EntityHiGolem entity) {
        return TEXTURE;
    }

    protected void applyRotations(EntityHiGolem entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.func_77043_a((EntityLivingBase)entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
}

