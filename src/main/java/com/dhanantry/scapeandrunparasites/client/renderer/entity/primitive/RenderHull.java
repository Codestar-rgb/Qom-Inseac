/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.MathHelper
 */
package com.dhanantry.scapeandrunparasites.client.renderer.entity.primitive;

import com.dhanantry.scapeandrunparasites.client.model.entity.primitive.ModelHull;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderMalleable;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityHull;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderHull
extends RenderMalleable<EntityHull> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/hull.png");
    public static final ResourceLocation TEXTUREH = new ResourceLocation("srparasites:textures/entity/monster/hullh.png");
    private float secShadow = 0.8f;
    public static final ResourceLocation STEXTURE = new ResourceLocation("srparasites:textures/entity/monster/shull.png");
    public static final ResourceLocation FROZEN_TEXTURE = new ResourceLocation("srparasites:textures/entity/monster/snowvariants/primitivemanducatorfrozen.png");

    public RenderHull(RenderManager manager) {
        super(manager, new ModelHull(), 0.8f);
    }

    protected void preRenderCallback(EntityHull entitylivingbaseIn, float partialTickTime) {
        float f = entitylivingbaseIn.getSelfeFlashIntensity(partialTickTime);
        float f1 = 1.0f + MathHelper.func_76126_a((float)(f * 100.0f)) * f * 0.01f;
        f = MathHelper.func_76131_a((float)f, (float)0.0f, (float)1.0f);
        f *= f;
        f *= f;
        float f2 = (1.0f + f * 0.4f) * f1;
        float f3 = (1.0f + f * 0.1f) / f1;
        GlStateManager.func_179152_a((float)f2, (float)f3, (float)f2);
    }

    protected ResourceLocation getEntityTexture(EntityHull entity) {
        switch (entity.getSkin()) {
            case 7: {
                return TEXTUREH;
            }
            case 120: {
                return STEXTURE;
            }
        }
        return TEXTURES;
    }

    protected void applyRotations(EntityHull entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.func_77043_a((EntityLivingBase)entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

    protected void renderModel(EntityHull entitylivingbaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        boolean flag = this.func_193115_c((EntityLivingBase)entitylivingbaseIn);
        boolean flag1 = !flag;
        boolean flag2 = entitylivingbaseIn.getSSS();
        if (flag || flag1 || flag2) {
            float f1 = 0.15f;
            if (flag2) {
                f1 = 0.04f;
            }
            if (!this.func_180548_c((Entity)entitylivingbaseIn)) {
                return;
            }
            if (flag1 || flag2) {
                this.field_76989_e = 0.0f;
                GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)f1);
                GlStateManager.func_179132_a((boolean)false);
                GlStateManager.func_179147_l();
                GlStateManager.func_187401_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                GlStateManager.func_179092_a((int)516, (float)0.003921569f);
            }
            this.field_77045_g.func_78088_a((Entity)entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
            if (flag1 || flag2) {
                GlStateManager.func_179084_k();
                GlStateManager.func_179092_a((int)516, (float)0.1f);
                GlStateManager.func_179132_a((boolean)true);
            } else {
                this.field_76989_e = this.secShadow;
            }
        }
    }
}

