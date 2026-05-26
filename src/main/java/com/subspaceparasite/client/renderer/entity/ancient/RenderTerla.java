/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.util.ResourceLocation
 */
package com.subspaceparasite.client.renderer.entity.ancient;

import com.subspaceparasite.client.model.entity.ancient.ModelTerla;
import com.subspaceparasite.client.renderer.RenderMalleable;
import com.subspaceparasite.entity.monster.ancient.EntityTerla;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderTerla
extends RenderMalleable<EntityTerla> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("subspaceparasite:textures/entity/monster/terla.png");

    public RenderTerla(RenderManager manager) {
        super(manager, new ModelTerla(), 2.4f);
    }

    protected ResourceLocation getEntityTexture(EntityTerla entity) {
        return TEXTURES;
    }
}

