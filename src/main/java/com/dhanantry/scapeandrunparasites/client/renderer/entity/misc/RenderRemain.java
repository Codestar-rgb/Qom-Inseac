/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.util.ResourceLocation
 */
package com.dhanantry.scapeandrunparasites.client.renderer.entity.misc;

import com.dhanantry.scapeandrunparasites.entity.EntityRemain;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderRemain
extends Render<EntityRemain> {
    private static final ResourceLocation EVOKER_ILLAGER_FANGS = new ResourceLocation("textures/entity/illager/fangs.png");

    public RenderRemain(RenderManager p_i47208_1_) {
        super(p_i47208_1_);
    }

    protected ResourceLocation getEntityTexture(EntityRemain entity) {
        return EVOKER_ILLAGER_FANGS;
    }
}

