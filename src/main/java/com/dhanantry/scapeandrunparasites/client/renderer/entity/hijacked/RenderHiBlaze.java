/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package com.dhanantry.scapeandrunparasites.client.renderer.entity.hijacked;

import com.dhanantry.scapeandrunparasites.client.model.entity.hijacked.ModelHiBlaze;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderSRP;
import com.dhanantry.scapeandrunparasites.entity.monster.hijacked.EntityHiBlaze;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderHiBlaze
extends RenderSRP<EntityHiBlaze> {
    public static final ResourceLocation TEXTURE = new ResourceLocation("srparasites:textures/entity/monster/hiblaze.png");

    public RenderHiBlaze(RenderManager manager) {
        super(manager, new ModelHiBlaze(), 0.6f);
    }

    protected ResourceLocation getEntityTexture(EntityHiBlaze entity) {
        return TEXTURE;
    }

    protected void applyRotations(EntityHiBlaze entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.func_77043_a((EntityLivingBase)entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
}

