/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package com.dhanantry.scapeandrunparasites.client.renderer.entity.deterrent;

import com.dhanantry.scapeandrunparasites.client.model.entity.deterrent.ModelNak;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderMalleable;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.EntityNak;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderNak
extends RenderMalleable<EntityNak> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/nak.png");
    public static final ResourceLocation TEXTURE_FROZEN = new ResourceLocation("srparasites:textures/entity/monster/snowvariants/seizerfrozen.png");

    public RenderNak(RenderManager manager) {
        super(manager, new ModelNak(), 0.4f);
    }

    protected ResourceLocation getEntityTexture(EntityNak entity) {
        switch (entity.getSkin()) {
            case 120: {
                return TEXTURE_FROZEN;
            }
        }
        return TEXTURES;
    }

    protected void applyRotations(EntityNak entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.func_77043_a((EntityLivingBase)entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
}

