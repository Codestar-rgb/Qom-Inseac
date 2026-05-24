/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.util.ResourceLocation
 */
package com.subspaceparasite.client.renderer.entity.derived;

import com.subspaceparasite.client.model.entity.derived.ModelHeblu;
import com.subspaceparasite.client.renderer.RenderCosmical;
import com.subspaceparasite.entity.monster.derived.EntityHeblu;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderHeblu
extends RenderCosmical<EntityHeblu> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("subspaceparasite:textures/entity/monster/heblu.png");
    public static final ResourceLocation TEXTURESEC = new ResourceLocation("subspaceparasite:textures/entity/monster/heblumc.png");

    public RenderHeblu(RenderManager manager) {
        super(manager, new ModelHeblu(), 1.3f);
    }

    protected ResourceLocation getEntityTexture(EntityHeblu entity) {
        return TEXTURES;
    }

    @Override
    protected ResourceLocation getEntityTextureCosmical(EntityHeblu entity) {
        return TEXTURESEC;
    }
}

