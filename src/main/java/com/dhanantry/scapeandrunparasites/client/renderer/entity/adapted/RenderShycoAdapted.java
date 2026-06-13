/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.MathHelper
 */
package com.dhanantry.scapeandrunparasites.client.renderer.entity.adapted;

import com.dhanantry.scapeandrunparasites.client.model.entity.adapted.ModelShycoAdapted;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderMalleable;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityShycoAdapted;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderShycoAdapted
extends RenderMalleable<EntityShycoAdapted> {
    public static final ResourceLocation TEXTURE = new ResourceLocation("srparasites:textures/entity/monster/shycoa.png");
    public static final ResourceLocation TEXTURE2 = new ResourceLocation("srparasites:textures/entity/monster/shycoatyrant.png");
    public static final ResourceLocation TEXTURE3 = new ResourceLocation("srparasites:textures/entity/monster/shycoalovecraft.png");
    public static final ResourceLocation TEXTURE4 = new ResourceLocation("srparasites:textures/entity/monster/shycoaabyss.png");
    public static final ResourceLocation TEXTUREV = new ResourceLocation("srparasites:textures/entity/monster/shycoav.png");
    public static final ResourceLocation TEXTUREB = new ResourceLocation("srparasites:textures/entity/monster/shycoab.png");
    public static final ResourceLocation TEXTUREH = new ResourceLocation("srparasites:textures/entity/monster/shycoah.png");
    public static final ResourceLocation TEXTURE_FROZEN = new ResourceLocation("srparasites:textures/entity/monster/snowvariants/test.png");

    public RenderShycoAdapted(RenderManager manager) {
        super(manager, new ModelShycoAdapted(), 1.0f);
    }

    protected void preRenderCallback(EntityShycoAdapted entitylivingbaseIn, float partialTickTime) {
        float f = entitylivingbaseIn.getSelfeFlashIntensity(partialTickTime);
        float f1 = 1.0f + MathHelper.func_76126_a((float)(f * 100.0f)) * f * 0.01f;
        f = MathHelper.func_76131_a((float)f, (float)0.0f, (float)1.0f);
        f *= f;
        f *= f;
        float f2 = (1.0f + f * 0.4f) * f1;
        float f3 = (1.0f + f * 0.1f) / f1;
        GlStateManager.func_179152_a((float)f2, (float)f3, (float)f2);
    }

    protected ResourceLocation getEntityTexture(EntityShycoAdapted entity) {
        switch (entity.getSkin()) {
            case 1: {
                return TEXTURE2;
            }
            case 5: {
                return TEXTUREV;
            }
            case 6: {
                return TEXTUREB;
            }
            case 7: {
                return TEXTUREH;
            }
            case 120: {
                return TEXTURE_FROZEN;
            }
        }
        return TEXTURE;
    }
}

