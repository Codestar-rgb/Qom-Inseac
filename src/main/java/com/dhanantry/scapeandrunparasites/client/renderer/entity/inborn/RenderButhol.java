/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.MathHelper
 */
package com.dhanantry.scapeandrunparasites.client.renderer.entity.inborn;

import com.dhanantry.scapeandrunparasites.client.model.entity.inborn.ModelButhol;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityButhol;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderButhol
extends RenderLiving<EntityButhol> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/buthol.png");
    public static final ResourceLocation TEXTURES1 = new ResourceLocation("srparasites:textures/entity/monster/butholone.png");
    public static final ResourceLocation STEXTURE = new ResourceLocation("srparasites:textures/entity/monster/sbuthol.png");

    public RenderButhol(RenderManager manager) {
        super(manager, (ModelBase)new ModelButhol(), 1.3f);
    }

    protected void preRenderCallback(EntityButhol entitylivingbaseIn, float partialTickTime) {
        float f = entitylivingbaseIn.getSelfeFlashIntensity(partialTickTime);
        float f1 = 1.0f + MathHelper.func_76126_a((float)(f * 100.0f)) * f * 0.01f;
        f = MathHelper.func_76131_a((float)f, (float)0.0f, (float)1.0f);
        f *= f;
        f *= f;
        float f2 = (1.0f + f * 0.4f) * f1;
        float f3 = (1.0f + f * 0.1f) / f1;
        GlStateManager.func_179152_a((float)f2, (float)f3, (float)f2);
    }

    protected ResourceLocation getEntityTexture(EntityButhol entity) {
        switch (entity.getSkin()) {
            case 1: {
                return TEXTURES1;
            }
            case 120: {
                return STEXTURE;
            }
        }
        return TEXTURES;
    }

    protected void applyRotations(EntityButhol entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.func_77043_a((EntityLivingBase)entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
}

