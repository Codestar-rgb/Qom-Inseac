/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.subspaceparasite.client.model.entity.misc;

import com.subspaceparasite.client.model.ModelSP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelTendrilBano
extends ModelSP {
    public ModelRenderer mainbody;
    public ModelRenderer jointMLT0;
    public ModelRenderer JD;
    public ModelRenderer tacle;
    public ModelRenderer jointMLT1;
    public ModelRenderer tacle_1;
    public ModelRenderer jointMLT2;
    public ModelRenderer tacle_2;
    public ModelRenderer jointMLT3;
    public ModelRenderer tacle_3;
    public ModelRenderer jointMLT4;
    public ModelRenderer tacle_4;
    public ModelRenderer jointMLT5;
    public ModelRenderer tacle_5;
    public ModelRenderer jointMLT6;
    public ModelRenderer tacle_6;

    public ModelTendrilBano() {
        this.field_78090_t = 100;
        this.field_78089_u = 64;
        this.tacle_1 = new ModelRenderer((ModelBase)this, 19, 0);
        this.tacle_1.func_78793_a(0.0f, -1.0f, 1.0f);
        this.tacle_1.func_78790_a(-2.0f, -3.0f, -2.0f, 4, 6, 19, 0.0f);
        this.setRotateAngle(this.tacle_1, -0.4712389f, 0.0f, 0.0f);
        this.tacle_5 = new ModelRenderer((ModelBase)this, 54, 30);
        this.tacle_5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.tacle_5.func_78790_a(-2.0f, -2.0f, -1.0f, 4, 4, 11, 0.0f);
        this.setRotateAngle(this.tacle_5, -1.3089969f, 0.0f, 0.0f);
        this.tacle_6 = new ModelRenderer((ModelBase)this, 73, 34);
        this.tacle_6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.tacle_6.func_78790_a(-1.0f, -1.0f, -1.0f, 2, 2, 11, 0.0f);
        this.setRotateAngle(this.tacle_6, -0.89011794f, 0.0f, 0.0f);
        this.jointMLT5 = new ModelRenderer((ModelBase)this, 50, 0);
        this.jointMLT5.func_78793_a(0.0f, 0.0f, 17.0f);
        this.jointMLT5.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.tacle = new ModelRenderer((ModelBase)this, 0, 0);
        this.tacle.func_78793_a(0.0f, 0.0f, 0.0f);
        this.tacle.func_78790_a(-3.0f, -3.0f, -2.0f, 6, 6, 13, 0.0f);
        this.setRotateAngle(this.tacle, 0.0f, 0.0f, (float)Math.PI);
        this.tacle_3 = new ModelRenderer((ModelBase)this, 0, 25);
        this.tacle_3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.tacle_3.func_78790_a(-2.0f, -2.0f, -1.0f, 4, 4, 18, 0.0f);
        this.setRotateAngle(this.tacle_3, -0.9773844f, 0.0f, 0.0f);
        this.JD = new ModelRenderer((ModelBase)this, 8, 0);
        this.JD.func_78793_a(0.0f, 0.0f, 0.0f);
        this.JD.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.jointMLT3 = new ModelRenderer((ModelBase)this, 33, 0);
        this.jointMLT3.func_78793_a(0.0f, 0.0f, 18.0f);
        this.jointMLT3.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.mainbody = new ModelRenderer((ModelBase)this, 0, 0);
        this.mainbody.func_78793_a(0.0f, 21.3f, 9.0f);
        this.mainbody.func_78790_a(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.mainbody, 0.0f, (float)Math.PI, 0.0f);
        this.jointMLT4 = new ModelRenderer((ModelBase)this, 46, 0);
        this.jointMLT4.func_78793_a(0.0f, 0.0f, 15.9f);
        this.jointMLT4.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.jointMLT6 = new ModelRenderer((ModelBase)this, 54, 0);
        this.jointMLT6.func_78793_a(0.0f, 0.0f, 10.0f);
        this.jointMLT6.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.jointMLT0 = new ModelRenderer((ModelBase)this, 4, 0);
        this.jointMLT0.func_78793_a(0.0f, 0.0f, 0.0f);
        this.jointMLT0.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.tacle_4 = new ModelRenderer((ModelBase)this, 26, 30);
        this.tacle_4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.tacle_4.func_78790_a(-2.5f, -2.5f, 0.0f, 5, 5, 18, 0.0f);
        this.setRotateAngle(this.tacle_4, -1.2566371f, 0.0f, 0.0f);
        this.tacle_2 = new ModelRenderer((ModelBase)this, 44, 4);
        this.tacle_2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.tacle_2.func_78790_a(-2.5f, -2.5f, -2.0f, 5, 5, 21, 0.0f);
        this.setRotateAngle(this.tacle_2, -1.1868238f, 0.0f, 0.0f);
        this.jointMLT1 = new ModelRenderer((ModelBase)this, 25, 0);
        this.jointMLT1.func_78793_a(0.0f, 0.0f, 10.5f);
        this.jointMLT1.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.jointMLT2 = new ModelRenderer((ModelBase)this, 29, 0);
        this.jointMLT2.func_78793_a(0.0f, 0.0f, 17.5f);
        this.jointMLT2.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.jointMLT1.func_78792_a(this.tacle_1);
        this.jointMLT5.func_78792_a(this.tacle_5);
        this.jointMLT6.func_78792_a(this.tacle_6);
        this.tacle_4.func_78792_a(this.jointMLT5);
        this.JD.func_78792_a(this.tacle);
        this.jointMLT3.func_78792_a(this.tacle_3);
        this.jointMLT0.func_78792_a(this.JD);
        this.tacle_2.func_78792_a(this.jointMLT3);
        this.tacle_3.func_78792_a(this.jointMLT4);
        this.tacle_5.func_78792_a(this.jointMLT6);
        this.mainbody.func_78792_a(this.jointMLT0);
        this.jointMLT4.func_78792_a(this.tacle_4);
        this.jointMLT2.func_78792_a(this.tacle_2);
        this.tacle.func_78792_a(this.jointMLT1);
        this.tacle_1.func_78792_a(this.jointMLT2);
    }

    public void func_78088_a(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.func_78088_a(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.func_78087_a(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        this.mainbody.func_78785_a(scale);
    }

    public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        float f1 = MathHelper.func_76126_a((float)(ageInTicks * 0.06f + 0.0f)) * 0.2f;
        float f2 = MathHelper.func_76126_a((float)(ageInTicks * 0.07f + 8.0f)) * 0.3f;
        float f3 = MathHelper.func_76126_a((float)(ageInTicks * 0.06f + 4.0f)) * 0.4f;
        this.jointMLT1.field_78795_f = f1;
        this.jointMLT2.field_78795_f = f2;
        this.jointMLT3.field_78795_f = f3;
        this.jointMLT4.field_78795_f = f1;
        this.jointMLT5.field_78795_f = f2;
        this.jointMLT6.field_78795_f = f3;
    }
}

