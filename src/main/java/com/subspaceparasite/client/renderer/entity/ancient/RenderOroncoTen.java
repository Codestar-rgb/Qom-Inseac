/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.util.ResourceLocation
 */
package com.subspaceparasite.client.renderer.entity.ancient;

import com.subspaceparasite.client.model.entity.ancient.ModelOroncoTen;
import com.subspaceparasite.client.renderer.RenderSP;
import com.subspaceparasite.entity.monster.ancient.EntityOroncoTen;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderOroncoTen
extends RenderSP<EntityOroncoTen> {
    public static final ResourceLocation TEXTURE00 = new ResourceLocation("subspaceparasite:textures/entity/monster/oroncoten0.png");
    public static final ResourceLocation TEXTURE01 = new ResourceLocation("subspaceparasite:textures/entity/monster/oroncoten1.png");

    public RenderOroncoTen(RenderManager manager) {
        super(manager, new ModelOroncoTen(), 0.8f);
    }

    protected ResourceLocation getEntityTexture(EntityOroncoTen entity) {
        switch (entity.getSkin()) {
            case 1: {
                return TEXTURE01;
            }
        }
        return TEXTURE00;
    }
}

