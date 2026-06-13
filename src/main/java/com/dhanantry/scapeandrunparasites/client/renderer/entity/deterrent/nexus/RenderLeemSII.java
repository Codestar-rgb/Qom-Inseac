/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package com.dhanantry.scapeandrunparasites.client.renderer.entity.deterrent.nexus;

import com.dhanantry.scapeandrunparasites.client.model.entity.deterrent.nexus.ModelLeemSII;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderMalleable;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityLeemSII;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderLeemSII
extends RenderMalleable<EntityLeemSII> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/leemsii.png");
    public static final ResourceLocation TEXTURE_FROZEN = new ResourceLocation("srparasites:textures/entity/monster/snowvariants/test.png");

    public RenderLeemSII(RenderManager manager) {
        super(manager, new ModelLeemSII(), 0.4f);
    }

    protected ResourceLocation getEntityTexture(EntityLeemSII entity) {
        switch (entity.getSkin()) {
            case 120: {
                return TEXTURE_FROZEN;
            }
        }
        return TEXTURES;
    }

    protected void applyRotations(EntityLeemSII entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.func_77043_a((EntityLivingBase)entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
}

