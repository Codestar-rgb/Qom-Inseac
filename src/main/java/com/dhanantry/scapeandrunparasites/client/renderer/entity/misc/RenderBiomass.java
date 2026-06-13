/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$Profile
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.MathHelper
 */
package com.dhanantry.scapeandrunparasites.client.renderer.entity.misc;

import com.dhanantry.scapeandrunparasites.client.model.entity.misc.ModelBiomassPod;
import com.dhanantry.scapeandrunparasites.client.model.entity.misc.ModelBiomassVenkrol;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderSRP;
import com.dhanantry.scapeandrunparasites.entity.monster.EntityBiomass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderBiomass
extends RenderSRP<EntityBiomass> {
    protected static ModelBase modelV = new ModelBiomassVenkrol();
    protected static ModelBase modelP = new ModelBiomassPod();
    public static final ResourceLocation TEXTUREV = new ResourceLocation("srparasites:textures/entity/monster/biomassvenkrol.png");
    public static final ResourceLocation TEXTUREP = new ResourceLocation("srparasites:textures/entity/monster/biomasspod.png");

    public RenderBiomass(RenderManager p_i47208_1_) {
        super(p_i47208_1_, modelV, 0.5f);
    }

    protected void preRenderCallback(EntityBiomass entitylivingbaseIn, float partialTickTime) {
        float f = entitylivingbaseIn.getSelfeFlashIntensity(partialTickTime);
        float f1 = 1.0f + MathHelper.func_76126_a((float)(f * 100.0f)) * f * 0.01f;
        f = MathHelper.func_76131_a((float)f, (float)0.0f, (float)1.0f);
        f *= f;
        f *= f;
        float f2 = (1.0f + f * 0.4f) * f1;
        float f3 = (1.0f + f * 0.1f) / f1;
        GlStateManager.func_179152_a((float)f2, (float)f3, (float)f2);
    }

    protected ResourceLocation getEntityTexture(EntityBiomass entity) {
        switch (entity.getSkin()) {
            case 1: {
                return TEXTUREV;
            }
            case 2: {
                return TEXTUREV;
            }
            case 3: {
                return TEXTUREV;
            }
            case 4: {
                return TEXTUREP;
            }
            case 5: {
                return TEXTUREP;
            }
            case 6: {
                return TEXTUREP;
            }
        }
        return TEXTUREP;
    }

    protected void applyRotations(EntityBiomass entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.func_77043_a((EntityLivingBase)entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

    protected void renderModel(EntityBiomass entitylivingbaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        boolean flag1;
        boolean flag = this.func_193115_c((EntityLivingBase)entitylivingbaseIn);
        boolean bl = flag1 = !flag && !entitylivingbaseIn.func_98034_c((EntityPlayer)Minecraft.func_71410_x().field_71439_g);
        if (flag || flag1) {
            if (!this.func_180548_c((Entity)entitylivingbaseIn)) {
                return;
            }
            if (flag1) {
                GlStateManager.func_187408_a((GlStateManager.Profile)GlStateManager.Profile.TRANSPARENT_MODEL);
            }
            switch (entitylivingbaseIn.getSkin()) {
                case 1: {
                    modelV.func_78088_a((Entity)entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
                    break;
                }
                case 2: {
                    modelV.func_78088_a((Entity)entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
                    break;
                }
                case 3: {
                    modelV.func_78088_a((Entity)entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
                    break;
                }
                case 4: {
                    modelP.func_78088_a((Entity)entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
                    break;
                }
                case 5: {
                    modelP.func_78088_a((Entity)entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
                    break;
                }
                case 6: {
                    modelP.func_78088_a((Entity)entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
                }
            }
            if (flag1) {
                GlStateManager.func_187440_b((GlStateManager.Profile)GlStateManager.Profile.TRANSPARENT_MODEL);
            }
        }
    }
}

