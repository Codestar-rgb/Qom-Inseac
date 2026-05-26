/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.util.ResourceLocation
 */
package com.subspaceparasite.client.renderer.entity.focused;

import com.subspaceparasite.client.model.entity.focused.ModelShycoFocused;
import com.subspaceparasite.client.renderer.RenderCosmical;
import com.subspaceparasite.entity.monster.focused.EntityShycoFocused;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderShycoFocused
extends RenderCosmical<EntityShycoFocused> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("subspaceparasite:textures/entity/monster/test.png");
    public static final ResourceLocation TEXTURESEC = new ResourceLocation("subspaceparasite:textures/entity/monster/testb.png");

    public RenderShycoFocused(RenderManager manager) {
        super(manager, new ModelShycoFocused(), 0.5f);
    }

    protected ResourceLocation getEntityTexture(EntityShycoFocused entity) {
        return TEXTURES;
    }

    @Override
    protected ResourceLocation getEntityTextureCosmical(EntityShycoFocused entity) {
        return TEXTURESEC;
    }
}

