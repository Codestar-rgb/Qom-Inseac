/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.util.ResourceLocation
 */
package com.subspaceparasite.client.renderer.entity.focused;

import com.subspaceparasite.client.model.entity.focused.ModelBanoFocused;
import com.subspaceparasite.client.renderer.RenderCosmical;
import com.subspaceparasite.entity.monster.focused.EntityBanoFocused;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBanoFocused
extends RenderCosmical<EntityBanoFocused> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("subspaceparasite:textures/entity/monster/test.png");
    public static final ResourceLocation TEXTURESEC = new ResourceLocation("subspaceparasite:textures/entity/monster/testb.png");

    public RenderBanoFocused(RenderManager manager) {
        super(manager, new ModelBanoFocused(), 0.5f);
    }

    protected ResourceLocation getEntityTexture(EntityBanoFocused entity) {
        return TEXTURES;
    }

    @Override
    protected ResourceLocation getEntityTextureCosmical(EntityBanoFocused entity) {
        return TEXTURESEC;
    }
}

