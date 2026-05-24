/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.util.ResourceLocation
 */
package com.subspaceparasite.client.renderer.entity.derived;

import com.subspaceparasite.client.model.entity.derived.ModelKirin;
import com.subspaceparasite.client.renderer.RenderCosmical;
import com.subspaceparasite.entity.monster.derived.EntityKirin;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderKirin
extends RenderCosmical<EntityKirin> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("subspaceparasite:textures/entity/monster/kirin.png");
    public static final ResourceLocation TEXTURESEC = new ResourceLocation("subspaceparasite:textures/entity/monster/testb.png");

    public RenderKirin(RenderManager manager) {
        super(manager, new ModelKirin(), 1.3f);
    }

    protected ResourceLocation getEntityTexture(EntityKirin entity) {
        return TEXTURES;
    }

    @Override
    protected ResourceLocation getEntityTextureCosmical(EntityKirin entity) {
        return TEXTURESEC;
    }
}

