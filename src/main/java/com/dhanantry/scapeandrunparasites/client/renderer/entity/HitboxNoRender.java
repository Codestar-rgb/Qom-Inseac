/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.util.ResourceLocation
 */
package com.dhanantry.scapeandrunparasites.client.renderer.entity;

import com.dhanantry.scapeandrunparasites.entity.EntityHitbox;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class HitboxNoRender
extends Render<EntityHitbox> {
    public HitboxNoRender(RenderManager renderManager) {
        super(renderManager);
    }

    @Nullable
    protected ResourceLocation getEntityTexture(EntityHitbox entity) {
        return null;
    }
}

