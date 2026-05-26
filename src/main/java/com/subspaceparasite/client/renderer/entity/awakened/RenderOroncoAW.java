/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.util.ResourceLocation
 */
package com.subspaceparasite.client.renderer.entity.awakened;

import com.subspaceparasite.client.model.ModelSP;
import com.subspaceparasite.client.model.entity.awakened.ModelOroncoAW;
import com.subspaceparasite.client.model.entity.awakened.ModelOroncoAWFL;
import com.subspaceparasite.client.renderer.RenderMalleable;
import com.subspaceparasite.entity.monster.awakened.EntityOroncoAW;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderOroncoAW
extends RenderMalleable<EntityOroncoAW> {
    protected ModelSP mainModel2 = new ModelOroncoAWFL();
    protected ModelSP mainModel3 = new ModelOroncoAWFL();
    public static final ResourceLocation TEXTURES = new ResourceLocation("subspaceparasite:textures/entity/monster/test.png");

    public RenderOroncoAW(RenderManager manager) {
        super(manager, new ModelOroncoAW(), 4.0f);
    }

    protected ResourceLocation getEntityTexture(EntityOroncoAW entity) {
        return TEXTURES;
    }
}

