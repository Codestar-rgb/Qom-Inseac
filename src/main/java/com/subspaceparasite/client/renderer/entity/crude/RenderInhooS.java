/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.util.ResourceLocation
 */
package com.subspaceparasite.client.renderer.entity.crude;

import com.subspaceparasite.client.model.entity.crude.ModelInhooS;
import com.subspaceparasite.client.renderer.RenderSRP;
import com.subspaceparasite.entity.monster.crude.EntityInhooS;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderInhooS
extends RenderSRP<EntityInhooS> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("subspaceparasite:textures/entity/monster/inhoos.png");
    public static final ResourceLocation TEXTURE_FROZEN = new ResourceLocation("subspaceparasite:textures/entity/monster/snowvariants/test.png");

    public RenderInhooS(RenderManager manager) {
        super(manager, new ModelInhooS(), 0.5f);
    }

    protected ResourceLocation getEntityTexture(EntityInhooS entity) {
        switch (entity.getSkin()) {
            case 120: {
                return TEXTURE_FROZEN;
            }
        }
        return TEXTURES;
    }
}

