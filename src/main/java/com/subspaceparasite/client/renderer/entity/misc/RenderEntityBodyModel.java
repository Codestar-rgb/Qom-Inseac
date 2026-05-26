/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GLAllocation
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$Profile
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.texture.DynamicTexture
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.text.TextFormatting
 */
package com.subspaceparasite.client.renderer.entity.misc;

import com.subspaceparasite.client.model.ModelSP;
import com.subspaceparasite.client.model.entity.deterrent.nexus.ModelDodSIVH;
import com.subspaceparasite.entity.EntityBodyModel;
import java.nio.FloatBuffer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class RenderEntityBodyModel
extends Render<EntityBodyModel> {
    public static final ResourceLocation TDODSIVH = new ResourceLocation("subspaceparasite:textures/entity/monster/dodsivh.png");
    public static final ResourceLocation STEXTURE = new ResourceLocation("subspaceparasite:textures/entity/monster/sdodivhead.png");
    protected ModelSP MDODSIVH = new ModelDodSIVH();
    private static final DynamicTexture TEXTURE_BRIGHTNESS = new DynamicTexture(16, 16);
    protected FloatBuffer brightnessBuffer = GLAllocation.func_74529_h((int)4);

    public RenderEntityBodyModel(RenderManager p_i47208_1_) {
        super(p_i47208_1_);
    }

    protected ResourceLocation getEntityTexture(EntityBodyModel entity) {
        switch (entity.getSkin()) {
            case 0: {
                return TDODSIVH;
            }
            case 120: {
                return STEXTURE;
            }
        }
        return TDODSIVH;
    }

    public void doRender(EntityBodyModel entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.func_179094_E();
        byte typePart = entity.getSkin();
        float f = this.interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, partialTicks);
        float f1 = this.interpolateRotation(entity.prevRotationYawHead, entity.rotationYawHead, partialTicks);
        float f2 = f1 - f;
        float f7 = entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * partialTicks;
        this.renderLivingAt(entity, x, y, z);
        float f8 = this.handleRotationFloat(entity, partialTicks);
        this.applyRotations(entity, f8, f, partialTicks);
        float f4 = this.prepareScale(entity, partialTicks);
        float f5 = 0.0f;
        float f6 = 0.0f;
        f5 = entity.prevLimbSwingAmount + (entity.limbSwingAmount - entity.prevLimbSwingAmount) * partialTicks;
        f6 = entity.limbSwing - entity.limbSwingAmount * (1.0f - partialTicks);
        GlStateManager.func_179141_d();
        switch (typePart) {
            case 0: {
                this.MDODSIVH.setLivingAnimations(entity, f6, f5, partialTicks);
                this.MDODSIVH.func_78087_a(f6, f5, f8, f2, f7, f4, entity);
                break;
            }
            case 120: {
                this.MDODSIVH.setLivingAnimations(entity, f6, f5, partialTicks);
                this.MDODSIVH.func_78087_a(f6, f5, f8, f2, f7, f4, entity);
            }
        }
        boolean flag = this.setDoRenderBrightness(entity, partialTicks);
        this.renderModel(entity, f6, f5, f8, f2, f7, f4);
        if (flag) {
            this.unsetBrightness();
        }
        GlStateManager.func_179132_a((boolean)true);
        GlStateManager.func_179101_C();
        GlStateManager.func_179138_g((int)OpenGlHelper.field_77476_b);
        GlStateManager.func_179098_w();
        GlStateManager.func_179138_g((int)OpenGlHelper.field_77478_a);
        GlStateManager.func_179089_o();
        GlStateManager.func_179121_F();
    }

    protected float interpolateRotation(float prevYawOffset, float yawOffset, float partialTicks) {
        float f;
        for (f = yawOffset - prevYawOffset; f < -180.0f; f += 360.0f) {
        }
        while (f >= 180.0f) {
            f -= 360.0f;
        }
        return prevYawOffset + partialTicks * f;
    }

    protected void renderLivingAt(EntityBodyModel entityLivingBaseIn, double x, double y, double z) {
        GlStateManager.func_179109_b((float)((float)x), (float)((float)y), (float)((float)z));
    }

    protected float handleRotationFloat(EntityBodyModel livingBase, float partialTicks) {
        return (float)livingBase.field_70173_aa + partialTicks;
    }

    protected void applyRotations(EntityBodyModel entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        GlStateManager.func_179114_b((float)(180.0f - rotationYaw), (float)0.0f, (float)1.0f, (float)0.0f);
        String s = TextFormatting.func_110646_a((String)entityLiving.func_70005_c_());
        if (s != null && ("Dinnerbone".equals(s) || "Grumm".equals(s))) {
            GlStateManager.func_179109_b((float)0.0f, (float)(entityLiving.field_70131_O + 0.1f), (float)0.0f);
            GlStateManager.func_179114_b((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        }
    }

    public float prepareScale(EntityBodyModel entitylivingbaseIn, float partialTicks) {
        GlStateManager.func_179091_B();
        GlStateManager.func_179152_a((float)-1.0f, (float)-1.0f, (float)1.0f);
        this.preRenderCallback(entitylivingbaseIn, partialTicks);
        float f = 0.0625f;
        GlStateManager.func_179109_b((float)0.0f, (float)-1.501f, (float)0.0f);
        return 0.0625f;
    }

    protected void preRenderCallback(EntityBodyModel entitylivingbaseIn, float partialTickTime) {
    }

    protected void renderModel(EntityBodyModel entitylivingbaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        boolean flag1;
        boolean flag = this.isVisible(entitylivingbaseIn);
        boolean bl = flag1 = !flag && !entitylivingbaseIn.func_98034_c((EntityPlayer)Minecraft.func_71410_x().field_71439_g);
        if (flag || flag1) {
            if (!this.func_180548_c(entitylivingbaseIn)) {
                return;
            }
            if (flag1) {
                GlStateManager.func_187408_a((GlStateManager.Profile)GlStateManager.Profile.TRANSPARENT_MODEL);
            }
            switch (entitylivingbaseIn.getSkin()) {
                case 0: {
                    this.MDODSIVH.func_78088_a(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
                    break;
                }
                case 120: {
                    this.MDODSIVH.func_78088_a(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
                }
            }
            if (flag1) {
                GlStateManager.func_187440_b((GlStateManager.Profile)GlStateManager.Profile.TRANSPARENT_MODEL);
            }
        }
    }

    protected boolean setDoRenderBrightness(EntityBodyModel entityLivingBaseIn, float partialTicks) {
        return this.setBrightness(entityLivingBaseIn, partialTicks, true);
    }

    protected boolean setBrightness(EntityBodyModel entitylivingbaseIn, float partialTicks, boolean combineTextures) {
        boolean flag1;
        float f = entitylivingbaseIn.func_70013_c();
        int i = this.getColorMultiplier(entitylivingbaseIn, f, partialTicks);
        boolean flag = (i >> 24 & 0xFF) > 0;
        boolean bl = flag1 = entitylivingbaseIn.hurtTime > 0 || entitylivingbaseIn.deathTime > 0;
        if (!flag && !flag1) {
            return false;
        }
        if (!flag && !combineTextures) {
            return false;
        }
        GlStateManager.func_179138_g((int)OpenGlHelper.field_77478_a);
        GlStateManager.func_179098_w();
        GlStateManager.func_187399_a((int)8960, (int)8704, (int)OpenGlHelper.field_176095_s);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176099_x, (int)8448);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176098_y, (int)OpenGlHelper.field_77478_a);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176097_z, (int)OpenGlHelper.field_176093_u);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176081_B, (int)768);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176082_C, (int)768);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176077_E, (int)7681);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176078_F, (int)OpenGlHelper.field_77478_a);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176085_I, (int)770);
        GlStateManager.func_179138_g((int)OpenGlHelper.field_77476_b);
        GlStateManager.func_179098_w();
        GlStateManager.func_187399_a((int)8960, (int)8704, (int)OpenGlHelper.field_176095_s);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176099_x, (int)OpenGlHelper.field_176094_t);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176098_y, (int)OpenGlHelper.field_176092_v);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176097_z, (int)OpenGlHelper.field_176091_w);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176080_A, (int)OpenGlHelper.field_176092_v);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176081_B, (int)768);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176082_C, (int)768);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176076_D, (int)770);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176077_E, (int)7681);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176078_F, (int)OpenGlHelper.field_176091_w);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176085_I, (int)770);
        this.brightnessBuffer.position(0);
        if (flag1) {
            this.brightnessBuffer.put(1.0f);
            this.brightnessBuffer.put(0.0f);
            this.brightnessBuffer.put(0.0f);
            this.brightnessBuffer.put(0.3f);
        } else {
            float f1 = (float)(i >> 24 & 0xFF) / 255.0f;
            float f2 = (float)(i >> 16 & 0xFF) / 255.0f;
            float f3 = (float)(i >> 8 & 0xFF) / 255.0f;
            float f4 = (float)(i & 0xFF) / 255.0f;
            this.brightnessBuffer.put(f2);
            this.brightnessBuffer.put(f3);
            this.brightnessBuffer.put(f4);
            this.brightnessBuffer.put(1.0f - f1);
        }
        this.brightnessBuffer.flip();
        GlStateManager.func_187448_b((int)8960, (int)8705, (FloatBuffer)this.brightnessBuffer);
        GlStateManager.func_179138_g((int)OpenGlHelper.field_176096_r);
        GlStateManager.func_179098_w();
        GlStateManager.func_179144_i((int)TEXTURE_BRIGHTNESS.func_110552_b());
        GlStateManager.func_187399_a((int)8960, (int)8704, (int)OpenGlHelper.field_176095_s);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176099_x, (int)8448);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176098_y, (int)OpenGlHelper.field_176091_w);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176097_z, (int)OpenGlHelper.field_77476_b);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176081_B, (int)768);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176082_C, (int)768);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176077_E, (int)7681);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176078_F, (int)OpenGlHelper.field_176091_w);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176085_I, (int)770);
        GlStateManager.func_179138_g((int)OpenGlHelper.field_77478_a);
        return true;
    }

    protected int getColorMultiplier(EntityBodyModel entitylivingbaseIn, float lightBrightness, float partialTickTime) {
        return 0;
    }

    protected boolean isVisible(EntityBodyModel p_193115_1_) {
        return !p_193115_1_.func_82150_aj() || this.field_188301_f;
    }

    protected void unsetBrightness() {
        GlStateManager.func_179138_g((int)OpenGlHelper.field_77478_a);
        GlStateManager.func_179098_w();
        GlStateManager.func_187399_a((int)8960, (int)8704, (int)OpenGlHelper.field_176095_s);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176099_x, (int)8448);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176098_y, (int)OpenGlHelper.field_77478_a);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176097_z, (int)OpenGlHelper.field_176093_u);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176081_B, (int)768);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176082_C, (int)768);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176077_E, (int)8448);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176078_F, (int)OpenGlHelper.field_77478_a);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176079_G, (int)OpenGlHelper.field_176093_u);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176085_I, (int)770);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176086_J, (int)770);
        GlStateManager.func_179138_g((int)OpenGlHelper.field_77476_b);
        GlStateManager.func_187399_a((int)8960, (int)8704, (int)OpenGlHelper.field_176095_s);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176099_x, (int)8448);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176081_B, (int)768);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176082_C, (int)768);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176098_y, (int)5890);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176097_z, (int)OpenGlHelper.field_176091_w);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176077_E, (int)8448);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176085_I, (int)770);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176078_F, (int)5890);
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.func_179138_g((int)OpenGlHelper.field_176096_r);
        GlStateManager.func_179090_x();
        GlStateManager.func_179144_i((int)0);
        GlStateManager.func_187399_a((int)8960, (int)8704, (int)OpenGlHelper.field_176095_s);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176099_x, (int)8448);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176081_B, (int)768);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176082_C, (int)768);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176098_y, (int)5890);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176097_z, (int)OpenGlHelper.field_176091_w);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176077_E, (int)8448);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176085_I, (int)770);
        GlStateManager.func_187399_a((int)8960, (int)OpenGlHelper.field_176078_F, (int)5890);
        GlStateManager.func_179138_g((int)OpenGlHelper.field_77478_a);
    }
}

