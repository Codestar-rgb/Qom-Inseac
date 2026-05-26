/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.util.ResourceLocation
 */
package com.subspaceparasite.client.renderer.entity.deterrent;

import com.subspaceparasite.client.model.entity.deterrent.ModelUnvo;
import com.subspaceparasite.client.renderer.RenderMalleable;
import com.subspaceparasite.entity.monster.deterrent.EntityUnvo;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderUnvo
extends RenderMalleable<EntityUnvo> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("subspaceparasite:textures/entity/monster/unvo.png");
    public static final ResourceLocation TEXTURE_FROZEN = new ResourceLocation("subspaceparasite:textures/entity/monster/snowvariants/sentryfrozen.png");

    public RenderUnvo(RenderManager manager) {
        super(manager, new ModelUnvo(), 0.5f);
    }

    protected ResourceLocation getEntityTexture(EntityUnvo entity) {
        switch (entity.getSkin()) {
            case 120: {
                return TEXTURE_FROZEN;
            }
        }
        return TEXTURES;
    }
}

