/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.util.ResourceLocation
 */
package com.dhanantry.scapeandrunparasites.client.renderer.entity.focused;

import com.dhanantry.scapeandrunparasites.client.model.entity.focused.ModelShycoFocused;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderCosmical;
import com.dhanantry.scapeandrunparasites.entity.monster.focused.EntityShycoFocused;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderShycoFocused
extends RenderCosmical<EntityShycoFocused> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/test.png");
    public static final ResourceLocation TEXTURESEC = new ResourceLocation("srparasites:textures/entity/monster/testb.png");

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

