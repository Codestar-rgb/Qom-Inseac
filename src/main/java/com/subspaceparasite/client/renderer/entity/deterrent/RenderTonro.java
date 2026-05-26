/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.util.ResourceLocation
 */
package com.subspaceparasite.client.renderer.entity.deterrent;

import com.subspaceparasite.client.model.entity.deterrent.ModelTonro;
import com.subspaceparasite.client.renderer.RenderMalleable;
import com.subspaceparasite.entity.monster.deterrent.EntityTonro;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderTonro
extends RenderMalleable<EntityTonro> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("subspaceparasite:textures/entity/monster/tonro.png");
    public static final ResourceLocation TEXTURE_FROZEN = new ResourceLocation("subspaceparasite:textures/entity/monster/snowvariants/kyphosisfrozen.png");

    public RenderTonro(RenderManager manager) {
        super(manager, new ModelTonro(), 0.4f);
    }

    protected ResourceLocation getEntityTexture(EntityTonro entity) {
        switch (entity.getSkin()) {
            case 120: {
                return TEXTURE_FROZEN;
            }
        }
        return TEXTURES;
    }
}

