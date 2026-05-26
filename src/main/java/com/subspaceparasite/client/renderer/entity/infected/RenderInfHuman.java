/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.MathHelper
 */
package com.subspaceparasite.client.renderer.entity.infected;

import com.subspaceparasite.client.model.entity.infected.ModelInfHuman;
import com.subspaceparasite.client.renderer.RenderSP;
import com.subspaceparasite.entity.monster.infected.EntityInfHuman;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderInfHuman
extends RenderSP<EntityInfHuman> {
    private static final ResourceLocation TEXTURE_DEFAULT = new ResourceLocation("subspaceparasite:textures/entity/monster/human.png");
    private static final ResourceLocation TEXTURE_ALT = new ResourceLocation("subspaceparasite:textures/entity/monster/human1.png");
    private static final ResourceLocation TEXTURE_FROZEN = new ResourceLocation("subspaceparasite:textures/entity/monster/snowvariants/humanfrozen.png");
    private static final ResourceLocation TEXTURE_EATEN = new ResourceLocation("subspaceparasite:textures/entity/monster/humaneaten.png");
    private static final ResourceLocation TEXTURE_FLOOD = new ResourceLocation("subspaceparasite:textures/entity/monster/humanflood.png");
    private static final ResourceLocation TEXTURE_KIM = new ResourceLocation("subspaceparasite:textures/entity/monster/humanforge.png");

    public RenderInfHuman(RenderManager manager) {
        super(manager, new ModelInfHuman(), 0.5f);
    }

    protected void preRenderCallback(EntityInfHuman entity, float partialTickTime) {
        float f = entity.getSelfeFlashIntensity(partialTickTime);
        float ff = entity.getSelfeFlashIntensity2();
        float f1 = 1.0f + MathHelper.func_76126_a((float)(f * 100.0f)) * f * 0.01f;
        f = MathHelper.func_76131_a((float)f, (float)0.0f, (float)1.0f);
        f *= f;
        f *= f;
        float f2 = (1.0f + f * 0.4f) * f1;
        float f3 = (1.0f + f * 0.1f) / f1;
        GlStateManager.func_179152_a((float)f2, (float)(ff * f3), (float)f2);
    }

    protected ResourceLocation getEntityTexture(EntityInfHuman entity) {
        if (entity.func_145818_k_() && "Kim".equalsIgnoreCase(entity.func_95999_t())) {
            return TEXTURE_KIM;
        }
        switch (entity.getSkin()) {
            case 1: {
                return TEXTURE_ALT;
            }
            case 2: {
                return TEXTURE_EATEN;
            }
            case 3: {
                return TEXTURE_FLOOD;
            }
            case 120: {
                return TEXTURE_FROZEN;
            }
        }
        return TEXTURE_DEFAULT;
    }
}

