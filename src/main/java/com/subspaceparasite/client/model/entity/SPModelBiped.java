/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.EnumHandSide
 *  net.minecraft.util.math.MathHelper
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class SPModelBiped
extends ModelBiped {
    private byte parent;
    public ModelRenderer jointH;
    public ModelRenderer jointHW;
    public ModelRenderer body;
    public ModelRenderer rightA;
    public ModelRenderer leftA;
    public ModelRenderer rightL;
    public ModelRenderer leftL;
    public ArmPose leftAP = ArmPose.EMPTY;
    public ArmPose rightAP = ArmPose.EMPTY;
    public boolean field_78117_n;

    public SPModelBiped() {
        this(0.0f);
    }

    public SPModelBiped(float modelSize) {
        this(modelSize, 0.0f, 64, 32);
    }

    public SPModelBiped(float modelSize, float p_i1149_2_, int textureWidthIn, int textureHeightIn) {
        this.field_78090_t = textureWidthIn;
        this.field_78089_u = textureHeightIn;
        this.jointH = new ModelRenderer((ModelBase)this, 0, 0);
        this.jointH.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, modelSize);
        this.jointH.func_78793_a(0.0f, 0.0f + p_i1149_2_, 0.0f);
        this.jointHW = new ModelRenderer((ModelBase)this, 32, 0);
        this.jointHW.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, modelSize + 0.5f);
        this.jointHW.func_78793_a(0.0f, 0.0f + p_i1149_2_, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 16, 16);
        this.body.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 12, 4, modelSize);
        this.body.func_78793_a(0.0f, 0.0f + p_i1149_2_, 0.0f);
        this.rightA = new ModelRenderer((ModelBase)this, 40, 16);
        this.rightA.func_78790_a(-3.0f, -2.0f, -2.0f, 4, 12, 4, modelSize);
        this.rightA.func_78793_a(-5.0f, 2.0f + p_i1149_2_, 0.0f);
        this.leftA = new ModelRenderer((ModelBase)this, 40, 16);
        this.leftA.field_78809_i = true;
        this.leftA.func_78790_a(-1.0f, -2.0f, -2.0f, 4, 12, 4, modelSize);
        this.leftA.func_78793_a(5.0f, 2.0f + p_i1149_2_, 0.0f);
        this.rightL = new ModelRenderer((ModelBase)this, 0, 16);
        this.rightL.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, modelSize);
        this.rightL.func_78793_a(-1.9f, 12.0f + p_i1149_2_, 0.0f);
        this.leftL = new ModelRenderer((ModelBase)this, 0, 16);
        this.leftL.field_78809_i = true;
        this.leftL.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, modelSize);
        this.leftL.func_78793_a(1.9f, 12.0f + p_i1149_2_, 0.0f);
    }

    public void func_78088_a(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netjointHYaw, float jointHPitch, float scale) {
        this.func_78087_a(limbSwing, limbSwingAmount, ageInTicks, netjointHYaw, jointHPitch, scale, entityIn);
        GlStateManager.func_179094_E();
        if (this.field_78091_s) {
            float f = 2.0f;
            GlStateManager.func_179152_a((float)0.75f, (float)0.75f, (float)0.75f);
            GlStateManager.func_179109_b((float)0.0f, (float)(16.0f * scale), (float)0.0f);
            this.jointH.func_78785_a(scale);
            GlStateManager.func_179121_F();
            GlStateManager.func_179094_E();
            GlStateManager.func_179152_a((float)0.5f, (float)0.5f, (float)0.5f);
            GlStateManager.func_179109_b((float)0.0f, (float)(24.0f * scale), (float)0.0f);
            this.body.func_78785_a(scale);
            this.rightA.func_78785_a(scale);
            this.leftA.func_78785_a(scale);
            this.rightL.func_78785_a(scale);
            this.leftL.func_78785_a(scale);
            this.jointHW.func_78785_a(scale);
        } else {
            if (entityIn.func_70093_af()) {
                GlStateManager.func_179109_b((float)0.0f, (float)0.2f, (float)0.0f);
            }
            this.jointH.func_78785_a(scale);
            this.body.func_78785_a(scale);
            this.rightA.func_78785_a(scale);
            this.leftA.func_78785_a(scale);
            this.rightL.func_78785_a(scale);
            this.leftL.func_78785_a(scale);
            this.jointHW.func_78785_a(scale);
        }
        GlStateManager.func_179121_F();
    }

    public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netjointHYaw, float jointHPitch, float scaleFactor, Entity entityIn) {
        this.jointH.field_82908_p = 0.0f;
        this.jointH.field_82907_q = 0.0f;
        this.jointH.field_78795_f = 0.0f;
        this.leftL.field_78795_f = 0.0f;
        this.rightL.field_78795_f = 0.0f;
        switch (this.parent) {
            case 1: {
                this.jointH.field_82908_p = -0.25f;
                this.jointH.field_82907_q = 0.24000001f;
                float f4 = MathHelper.func_76134_b((float)(ageInTicks * 0.08f)) * 0.07f;
                this.jointH.field_78795_f = 0.45f + f4;
                float GS0 = 1.0f;
                if (entityIn.field_70169_q == entityIn.field_70165_t && entityIn.field_70166_s == entityIn.field_70161_v) break;
                float GS = 1.75f;
                float GD = 0.55f;
                GS0 += 0.45f;
                this.swingX(this.leftL, 0.3f * GS, 5.0f * GD, -1, limbSwing, limbSwingAmount);
                this.swingX(this.rightL, 0.3f * GS, 5.0f * GD, 1, limbSwing, limbSwingAmount);
            }
        }
    }

    public void setParent(byte in) {
        this.parent = in;
    }

    public void func_178686_a(ModelBase model) {
        super.func_178686_a(model);
        if (model instanceof SPModelBiped) {
            SPModelBiped modelbiped = (SPModelBiped)model;
            this.leftAP = modelbiped.leftAP;
            this.rightAP = modelbiped.rightAP;
            this.field_78117_n = modelbiped.field_78117_n;
        }
    }

    public void func_178719_a(boolean visible) {
        this.jointH.field_78806_j = visible;
        this.jointHW.field_78806_j = visible;
        this.body.field_78806_j = visible;
        this.rightA.field_78806_j = visible;
        this.leftA.field_78806_j = visible;
        this.rightL.field_78806_j = visible;
        this.leftL.field_78806_j = visible;
    }

    public void func_187073_a(float scale, EnumHandSide side) {
        this.func_187074_a(side).func_78794_c(scale);
    }

    protected ModelRenderer func_187074_a(EnumHandSide side) {
        return side == EnumHandSide.LEFT ? this.leftA : this.rightA;
    }

    protected EnumHandSide func_187072_a(Entity entityIn) {
        if (entityIn instanceof EntityLivingBase) {
            EntityLivingBase entitylivingbase = (EntityLivingBase)entityIn;
            EnumHandSide enumhandside = entitylivingbase.func_184591_cq();
            return entitylivingbase.field_184622_au == EnumHand.MAIN_HAND ? enumhandside : enumhandside.func_188468_a();
        }
        return EnumHandSide.RIGHT;
    }

    public void swingX(ModelRenderer modelRenderer, float speed, float degree, int invert, float limbSwing, float limbSwingAmount) {
        modelRenderer.field_78795_f = (float)((double)((float)invert * limbSwingAmount * degree) * Math.cos(limbSwing * speed) * (double)limbSwingAmount);
    }

    @SideOnly(value=Side.CLIENT)
    public static enum ArmPose {
        EMPTY,
        ITEM,
        BLOCK,
        BOW_AND_ARROW;

    }
}

