/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.util.ResourceLocation
 */
package com.subspaceparasite.client.renderer.entity.crude;

import com.subspaceparasite.client.model.entity.crude.ModelHost;
import com.subspaceparasite.client.renderer.RenderMalleable;
import com.subspaceparasite.entity.monster.crude.EntityHost;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderHost
extends RenderMalleable<EntityHost> {
    public static final ResourceLocation TEXTUREM = new ResourceLocation("subspaceparasite:textures/entity/monster/host.png");
    public static final ResourceLocation TEXTURE_FROZEN = new ResourceLocation("subspaceparasite:textures/entity/monster/snowvariants/test.png");

    public RenderHost(RenderManager manager) {
        super(manager, new ModelHost(), 0.0f);
    }

    protected ResourceLocation getEntityTexture(EntityHost entity) {
        switch (entity.getSkin()) {
            case 120: {
                return TEXTURE_FROZEN;
            }
        }
        return TEXTUREM;
    }
}

