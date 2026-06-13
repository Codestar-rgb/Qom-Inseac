/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.MathHelper
 */
package com.dhanantry.scapeandrunparasites.client.renderer.entity.pure.preeminent;

import com.dhanantry.scapeandrunparasites.client.model.entity.pure.preeminent.ModelVesta;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderMalleable;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.layer.LayerSnow;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent.EntityVesta;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderVesta
extends RenderMalleable<EntityVesta> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/vesta.png");
    public static final ResourceLocation TEXTURE1 = new ResourceLocation("srparasites:textures/entity/monster/vestare.png");

    public RenderVesta(RenderManager manager) {
        super(manager, new ModelVesta(), 1.3f);
        this.func_177094_a(new LayerSnow(this, new ResourceLocation("srparasites:textures/entity/layer/vestasnow.png"), 1.01, 1.001, 1.01));
    }

    protected void preRenderCallback(EntityVesta entitylivingbaseIn, float partialTickTime) {
        float f = entitylivingbaseIn.getSelfeFlashIntensity(partialTickTime);
        float f1 = 1.0f + MathHelper.func_76126_a((float)(f * 100.0f)) * f * 0.01f;
        f = MathHelper.func_76131_a((float)f, (float)0.0f, (float)1.0f);
        f *= f;
        f *= f;
        float f2 = (1.0f + f * 0.4f) * f1;
        float f3 = (1.0f + f * 0.1f) / f1;
        GlStateManager.func_179152_a((float)f2, (float)f3, (float)f2);
    }

    protected ResourceLocation getEntityTexture(EntityVesta entity) {
        switch (entity.getSkin()) {
            case 1: {
                return TEXTURE1;
            }
        }
        return TEXTURES;
    }

    protected void applyRotations(EntityVesta entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.func_77043_a((EntityLivingBase)entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
}

