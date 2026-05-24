/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.util.ResourceLocation
 */
package com.subspaceparasite.client.renderer.entity.crude;

import com.subspaceparasite.client.model.entity.crude.ModelHostII;
import com.subspaceparasite.client.renderer.RenderMalleable;
import com.subspaceparasite.entity.monster.crude.EntityHostII;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderHostII
extends RenderMalleable<EntityHostII> {
    public static final ResourceLocation TEXTUREM = new ResourceLocation("subspaceparasite:textures/entity/monster/hostii.png");
    public static final ResourceLocation TEXTURE_FROZEN = new ResourceLocation("subspaceparasite:textures/entity/monster/snowvariants/test.png");

    public RenderHostII(RenderManager manager) {
        super(manager, new ModelHostII(), 0.0f);
    }

    protected ResourceLocation getEntityTexture(EntityHostII entity) {
        switch (entity.getSkin()) {
            case 120: {
                return TEXTURE_FROZEN;
            }
        }
        return TEXTUREM;
    }
}

