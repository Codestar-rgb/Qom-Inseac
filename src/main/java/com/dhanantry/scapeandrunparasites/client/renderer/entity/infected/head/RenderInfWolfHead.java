/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package com.dhanantry.scapeandrunparasites.client.renderer.entity.infected.head;

import com.dhanantry.scapeandrunparasites.client.model.entity.infected.head.ModelInfWolfHead;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderSRP;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfWolfHead;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderInfWolfHead
extends RenderSRP<EntityInfWolfHead> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/wolfh.png");

    public RenderInfWolfHead(RenderManager manager) {
        super(manager, new ModelInfWolfHead(), 0.4f);
    }

    protected ResourceLocation getEntityTexture(EntityInfWolfHead entity) {
        return TEXTURES;
    }

    protected void applyRotations(EntityInfWolfHead entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.func_77043_a((EntityLivingBase)entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
}

