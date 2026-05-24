/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.subspaceparasite.client.model.entity.infected.special;

import com.subspaceparasite.client.model.ModelSRP;
import com.subspaceparasite.entity.monster.infected.special.EntitySpeCow;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSpeCow
extends ModelSRP {
    public ModelRenderer mainbody;
    public ModelRenderer body;
    public ModelRenderer body_1;
    public ModelRenderer JD;
    public ModelRenderer neck;
    public ModelRenderer JD_1;
    public ModelRenderer body_2;
    public ModelRenderer body_3;
    public ModelRenderer JD_2;
    public ModelRenderer JD_3;
    public ModelRenderer body_4;
    public ModelRenderer jointBLL;
    public ModelRenderer leg;
    public ModelRenderer jointBLL1;
    public ModelRenderer leg_1;
    public ModelRenderer leg_2;
    public ModelRenderer jointBRL;
    public ModelRenderer leg_3;
    public ModelRenderer jointBRL1;
    public ModelRenderer leg_4;
    public ModelRenderer leg_5;
    public ModelRenderer body_5;
    public ModelRenderer jointFLL;
    public ModelRenderer leg_6;
    public ModelRenderer jointFLL1;
    public ModelRenderer leg_7;
    public ModelRenderer leg_8;
    public ModelRenderer jointdont;
    public ModelRenderer jointH;
    public ModelRenderer head;
    public ModelRenderer ear;
    public ModelRenderer ear_1;
    public ModelRenderer mouthg;
    public ModelRenderer jointM;
    public ModelRenderer mouth;
    public ModelRenderer jointFRL;
    public ModelRenderer leg_9;
    public ModelRenderer jointFRL1;
    public ModelRenderer leg_10;
    public ModelRenderer leg_11;

    public ModelSpeCow() {
        this.field_78090_t = 128;
        this.field_78089_u = 128;
        this.jointBRL = new ModelRenderer((ModelBase)this, 121, 0);
        this.jointBRL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.jointBRL.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.jointH = new ModelRenderer((ModelBase)this, 55, 2);
        this.jointH.func_78793_a(0.0f, 0.0f, 0.0f);
        this.jointH.func_78790_a(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f);
        this.mainbody = new ModelRenderer((ModelBase)this, 0, 0);
        this.mainbody.func_78793_a(0.0f, 12.1f, 2.0f);
        this.mainbody.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.jointFLL1 = new ModelRenderer((ModelBase)this, 39, 2);
        this.jointFLL1.func_78793_a(0.0f, 5.0f, 0.0f);
        this.jointFLL1.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.body_3 = new ModelRenderer((ModelBase)this, 46, 25);
        this.body_3.func_78793_a(0.0f, 8.2f, -2.0f);
        this.body_3.func_78790_a(-5.0f, 0.0f, -3.0f, 10, 7, 15, 0.0f);
        this.JD_3 = new ModelRenderer((ModelBase)this, 51, 0);
        this.JD_3.func_78793_a(-6.1f, 11.4f, 1.0f);
        this.JD_3.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.JD_3, 0.12566371f, 0.0f, 0.0f);
        this.leg_6 = new ModelRenderer((ModelBase)this, 54, 47);
        this.leg_6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.leg_6.func_78790_a(-3.5f, -1.0f, -2.0f, 7, 7, 6, 0.0f);
        this.ear_1 = new ModelRenderer((ModelBase)this, 123, 8);
        this.ear_1.func_78793_a(-1.0f, -1.0f, 0.0f);
        this.ear_1.func_78790_a(-5.0f, -5.0f, -4.0f, 1, 3, 1, 0.0f);
        this.setRotateAngle(this.ear_1, -0.08726646f, 0.0f, 0.06981317f);
        this.leg_10 = new ModelRenderer((ModelBase)this, 112, 59);
        this.leg_10.func_78793_a(0.0f, 0.0f, 0.0f);
        this.leg_10.func_78790_a(-1.5f, 0.0f, -1.5f, 3, 6, 3, 0.0f);
        this.setRotateAngle(this.leg_10, 0.0f, 0.08726646f, 0.0f);
        this.leg_11 = new ModelRenderer((ModelBase)this, 0, 62);
        this.leg_11.func_78793_a(0.0f, 6.3f, 0.0f);
        this.leg_11.func_78790_a(-2.0f, -1.0f, -2.0f, 4, 2, 4, 0.0f);
        this.neck = new ModelRenderer((ModelBase)this, 88, 0);
        this.neck.func_78793_a(0.0f, 1.0f, 0.0f);
        this.neck.func_78790_a(-4.5f, -4.2f, -2.0f, 9, 11, 3, 0.0f);
        this.ear = new ModelRenderer((ModelBase)this, 3, 3);
        this.ear.func_78793_a(1.0f, -2.2f, -0.1f);
        this.ear.func_78790_a(4.0f, -5.0f, -4.0f, 1, 3, 1, 0.0f);
        this.setRotateAngle(this.ear, 0.08726646f, 0.0f, -0.05235988f);
        this.leg_9 = new ModelRenderer((ModelBase)this, 48, 60);
        this.leg_9.func_78793_a(0.0f, 0.0f, 0.0f);
        this.leg_9.func_78790_a(-3.5f, -1.0f, -2.0f, 7, 7, 6, 0.0f);
        this.jointBLL1 = new ModelRenderer((ModelBase)this, 109, 0);
        this.jointBLL1.func_78793_a(0.0f, 5.0f, 0.0f);
        this.jointBLL1.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.body_5 = new ModelRenderer((ModelBase)this, 24, 47);
        this.body_5.func_78793_a(0.0f, -5.0f, 4.0f);
        this.body_5.func_78790_a(-5.0f, -3.5f, -1.0f, 10, 12, 5, 0.0f);
        this.setRotateAngle(this.body_5, -0.12566371f, 0.0f, 0.0f);
        this.jointdont = new ModelRenderer((ModelBase)this, 43, 2);
        this.jointdont.func_78793_a(0.0f, 0.0f, -1.5f);
        this.jointdont.func_78790_a(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.jointdont, 0.108210415f, 0.0f, 0.0f);
        this.mouth = new ModelRenderer((ModelBase)this, 0, 55);
        this.mouth.func_78793_a(0.0f, 0.0f, 0.0f);
        this.mouth.func_78790_a(-4.0f, 0.0f, -2.3f, 8, 4, 3, 0.0f);
        this.setRotateAngle(this.mouth, 0.61784655f, 0.0f, 0.0f);
        this.leg = new ModelRenderer((ModelBase)this, 96, 40);
        this.leg.func_78793_a(0.0f, 0.0f, 0.0f);
        this.leg.func_78790_a(-3.0f, -1.0f, -3.0f, 6, 7, 6, 0.0f);
        this.jointBLL = new ModelRenderer((ModelBase)this, 55, 0);
        this.jointBLL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.jointBLL.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.leg_5 = new ModelRenderer((ModelBase)this, 102, 16);
        this.leg_5.func_78793_a(0.0f, 6.3f, 0.0f);
        this.leg_5.func_78790_a(-2.0f, -1.0f, -2.0f, 4, 2, 4, 0.0f);
        this.leg_7 = new ModelRenderer((ModelBase)this, 46, 25);
        this.leg_7.func_78793_a(0.0f, 0.0f, 0.0f);
        this.leg_7.func_78790_a(-1.5f, 0.0f, -1.5f, 3, 6, 3, 0.0f);
        this.setRotateAngle(this.leg_7, 0.0f, -0.08726646f, 0.0f);
        this.head = new ModelRenderer((ModelBase)this, 80, 53);
        this.head.func_78793_a(0.0f, 1.0f, 0.0f);
        this.head.func_78790_a(-5.0f, -6.0f, -6.0f, 10, 10, 6, 0.0f);
        this.setRotateAngle(this.head, -0.10471976f, 0.0f, 0.0f);
        this.leg_2 = new ModelRenderer((ModelBase)this, 108, 10);
        this.leg_2.func_78793_a(0.0f, 6.3f, 0.0f);
        this.leg_2.func_78790_a(-2.0f, -1.0f, -2.0f, 4, 2, 4, 0.0f);
        this.body_4 = new ModelRenderer((ModelBase)this, 81, 25);
        this.body_4.func_78793_a(0.0f, 9.0f, 1.0f);
        this.body_4.func_78790_a(-7.5f, -3.5f, -1.0f, 15, 10, 5, 0.0f);
        this.leg_4 = new ModelRenderer((ModelBase)this, 46, 2);
        this.leg_4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.leg_4.func_78790_a(-1.5f, 0.0f, -1.5f, 3, 6, 3, 0.0f);
        this.setRotateAngle(this.leg_4, 0.0f, 0.08726646f, 0.0f);
        this.jointBRL1 = new ModelRenderer((ModelBase)this, 3, 1);
        this.jointBRL1.func_78793_a(0.0f, 5.0f, 0.0f);
        this.jointBRL1.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.jointFRL = new ModelRenderer((ModelBase)this, 42, 5);
        this.jointFRL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.jointFRL.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.jointFRL1 = new ModelRenderer((ModelBase)this, 46, 11);
        this.jointFRL1.func_78793_a(0.0f, 5.0f, 0.0f);
        this.jointFRL1.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.leg_3 = new ModelRenderer((ModelBase)this, 0, 42);
        this.leg_3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.leg_3.func_78790_a(-3.0f, -1.0f, -3.0f, 6, 7, 6, 0.0f);
        this.leg_1 = new ModelRenderer((ModelBase)this, 112, 0);
        this.leg_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.leg_1.func_78790_a(-1.5f, 0.0f, -1.5f, 3, 6, 3, 0.0f);
        this.setRotateAngle(this.leg_1, 0.0f, -0.08726646f, 0.0f);
        this.leg_8 = new ModelRenderer((ModelBase)this, 74, 47);
        this.leg_8.func_78793_a(0.0f, 6.3f, 0.0f);
        this.leg_8.func_78790_a(-2.0f, -1.0f, -2.0f, 4, 2, 4, 0.0f);
        this.JD = new ModelRenderer((ModelBase)this, 39, 0);
        this.JD.func_78793_a(5.2f, 8.7f, 4.7f);
        this.JD.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.JD, 0.29670596f, 0.0f, 0.0f);
        this.body_2 = new ModelRenderer((ModelBase)this, 0, 25);
        this.body_2.func_78793_a(0.0f, 0.0f, 8.0f);
        this.body_2.func_78790_a(-8.0f, -3.5f, -1.0f, 16, 10, 7, 0.0f);
        this.setRotateAngle(this.body_2, -0.08726646f, 0.0f, 0.0f);
        this.JD_2 = new ModelRenderer((ModelBase)this, 47, 0);
        this.JD_2.func_78793_a(6.1f, 11.4f, 1.0f);
        this.JD_2.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.JD_2, 0.12566371f, 0.0f, 0.0f);
        this.mouthg = new ModelRenderer((ModelBase)this, 106, 53);
        this.mouthg.func_78793_a(0.0f, 2.0f, -2.7f);
        this.mouthg.func_78790_a(-2.5f, -0.1f, -1.1f, 5, 3, 3, 0.0f);
        this.setRotateAngle(this.mouthg, 0.15707964f, 0.0f, 0.0f);
        this.JD_1 = new ModelRenderer((ModelBase)this, 43, 0);
        this.JD_1.func_78793_a(-5.2f, 8.7f, 4.7f);
        this.JD_1.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.JD_1, 0.29670596f, 0.0f, 0.0f);
        this.jointM = new ModelRenderer((ModelBase)this, 39, 4);
        this.jointM.func_78793_a(0.0f, 1.5f, 1.0f);
        this.jointM.func_78790_a(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f);
        this.jointFLL = new ModelRenderer((ModelBase)this, 0, 2);
        this.jointFLL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.jointFLL.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 0, 0);
        this.body.func_78793_a(0.0f, -10.0f, -11.0f);
        this.body.func_78790_a(-8.0f, -8.0f, 1.0f, 16, 18, 7, 0.0f);
        this.setRotateAngle(this.body, -0.29670596f, 0.0f, 0.0f);
        this.body_1 = new ModelRenderer((ModelBase)this, 46, 0);
        this.body_1.func_78793_a(0.0f, -4.8f, 8.4f);
        this.body_1.func_78790_a(-7.0f, -2.0f, -5.0f, 14, 11, 14, 0.0f);
        this.setRotateAngle(this.body_1, 0.25830874f, 0.0f, 0.0f);
        this.JD_3.func_78792_a(this.jointBRL);
        this.jointdont.func_78792_a(this.jointH);
        this.leg_6.func_78792_a(this.jointFLL1);
        this.body_1.func_78792_a(this.body_3);
        this.body_2.func_78792_a(this.JD_3);
        this.jointFLL.func_78792_a(this.leg_6);
        this.head.func_78792_a(this.ear_1);
        this.jointFRL1.func_78792_a(this.leg_10);
        this.leg_10.func_78792_a(this.leg_11);
        this.body.func_78792_a(this.neck);
        this.head.func_78792_a(this.ear);
        this.jointFRL.func_78792_a(this.leg_9);
        this.leg.func_78792_a(this.jointBLL1);
        this.body_4.func_78792_a(this.body_5);
        this.neck.func_78792_a(this.jointdont);
        this.jointM.func_78792_a(this.mouth);
        this.jointBLL.func_78792_a(this.leg);
        this.JD_2.func_78792_a(this.jointBLL);
        this.leg_4.func_78792_a(this.leg_5);
        this.jointFLL1.func_78792_a(this.leg_7);
        this.jointH.func_78792_a(this.head);
        this.leg_1.func_78792_a(this.leg_2);
        this.body_2.func_78792_a(this.body_4);
        this.jointBRL1.func_78792_a(this.leg_4);
        this.leg_3.func_78792_a(this.jointBRL1);
        this.JD_1.func_78792_a(this.jointFRL);
        this.leg_9.func_78792_a(this.jointFRL1);
        this.jointBRL.func_78792_a(this.leg_3);
        this.jointBLL1.func_78792_a(this.leg_1);
        this.leg_7.func_78792_a(this.leg_8);
        this.body.func_78792_a(this.JD);
        this.body_1.func_78792_a(this.body_2);
        this.body_2.func_78792_a(this.JD_2);
        this.head.func_78792_a(this.mouthg);
        this.body.func_78792_a(this.JD_1);
        this.mouthg.func_78792_a(this.jointM);
        this.JD.func_78792_a(this.jointFLL);
        this.mainbody.func_78792_a(this.body);
        this.body.func_78792_a(this.body_1);
    }

    public void func_78088_a(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.func_78088_a(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.mainbody.func_78785_a(scale);
    }

    public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        EntitySpeCow parasite = (EntitySpeCow)entityIn;
        this.jointFLL.field_78795_f = 0.0f;
        this.jointFRL.field_78795_f = 0.0f;
        this.jointBLL.field_78795_f = 0.0f;
        this.jointBRL.field_78795_f = 0.0f;
        this.mainbody.field_82906_o = 0.0f;
        this.mainbody.field_82908_p = 0.0f;
        this.mainbody.field_82907_q = 0.0f;
        this.mainbody.field_78795_f = 0.0f;
        this.mainbody.field_78808_h = 0.0f;
        this.jointM.field_78795_f = 0.0f;
        this.jointH.field_78795_f = headPitch * 0.016f;
        this.jointH.field_78796_g = netHeadYaw * 0.016f;
        byte i = parasite.getParasiteStatus();
        if (i == 0) {
            float f22;
            if (!parasite.getStillAni()) {
                float GS = 2.0f;
                float GD = 2.0f;
                this.swingX(this.jointFLL, 0.3f * GS, 1.0f * GD, 1, limbSwing, limbSwingAmount);
                this.swingX(this.jointFRL, 0.3f * GS, 1.0f * GD, -1, limbSwing, limbSwingAmount);
                this.swingX(this.jointBLL, 0.3f * GS, 1.0f * GD, -1, limbSwing, limbSwingAmount);
                this.swingX(this.jointBRL, 0.3f * GS, 1.0f * GD, 1, limbSwing, limbSwingAmount);
                this.moveY(this.mainbody, 0.6f * GS, 1, limbSwing, limbSwingAmount, 0.05f);
                this.mainbody.field_78795_f = 0.0f;
            }
            float f1 = MathHelper.func_76134_b((float)(ageInTicks * 0.0998f)) * 0.0215f;
            float f23 = MathHelper.func_76134_b((float)(ageInTicks * 0.19112f)) * 0.0261f;
            float f3 = MathHelper.func_76134_b((float)(ageInTicks * 0.27955f)) * 0.0262f;
            float f4 = MathHelper.func_76134_b((float)(ageInTicks * 0.35222f)) * 0.0265f;
            this.jointM.field_78795_f = f22 = MathHelper.func_76134_b((float)(ageInTicks * 0.2f)) * 0.1f;
        } else if (i == 1) {
            float f22;
            if (!parasite.getStillAni()) {
                float GS = 2.5f;
                float GD = 2.5f;
                this.swingX(this.jointFLL, 0.3f * GS, 1.0f * GD, 1, limbSwing, limbSwingAmount);
                this.swingX(this.jointFRL, 0.3f * GS, 1.0f * GD, -1, limbSwing, limbSwingAmount);
                this.swingX(this.jointBLL, 0.3f * GS, 1.0f * GD, -1, limbSwing, limbSwingAmount);
                this.swingX(this.jointBRL, 0.3f * GS, 1.0f * GD, 1, limbSwing, limbSwingAmount);
                this.moveY(this.mainbody, 0.6f * GS, 1, limbSwing, limbSwingAmount, 0.05f);
                this.mainbody.field_78795_f = 0.0f;
            }
            this.jointM.field_78795_f = f22 = MathHelper.func_76134_b((float)(ageInTicks * 0.5f)) * 0.2f;
        } else if (i == 2) {
            float f22;
            if (!parasite.getStillAni()) {
                float GS = 1.5f;
                float GD = 0.3f;
                this.swingX(this.jointFLL, 0.3f * GS, 3.0f * GD, -1, 0.5f, 0.0f, limbSwing, limbSwingAmount);
                this.swingX(this.jointFRL, 0.3f * GS, 3.0f * GD, -1, -0.5f, 0.0f, limbSwing, limbSwingAmount);
                this.swingX(this.jointBLL, 0.3f * GS, 3.5f * GD, 1, -0.5f, 0.4f, limbSwing, limbSwingAmount);
                this.swingX(this.jointBRL, 0.3f * GS, 3.5f * GD, 1, 0.5f, 0.4f, limbSwing, limbSwingAmount);
                this.moveY(this.mainbody, 0.3f * GS, -1, limbSwing, limbSwingAmount, 0.08f);
                this.swingX(this.mainbody, 0.3f * GS, 0.8f * GD, -1, 0.8f, 0.0f, limbSwing, limbSwingAmount);
            }
            this.jointM.field_78795_f = f22 = MathHelper.func_76134_b((float)(ageInTicks * 0.5f)) * 0.2f;
        } else if (i == 3) {
            if (!parasite.getStillAni()) {
                float GS = 2.5f;
                float GD = 0.3f;
                this.swingX(this.jointFLL, 0.3f * GS, 3.0f * GD, -1, 0.5f, 0.0f, limbSwing, limbSwingAmount);
                this.swingX(this.jointFRL, 0.3f * GS, 3.0f * GD, -1, -0.5f, 0.0f, limbSwing, limbSwingAmount);
                this.swingX(this.jointBLL, 0.3f * GS, 3.5f * GD, 1, -0.5f, 0.4f, limbSwing, limbSwingAmount);
                this.swingX(this.jointBRL, 0.3f * GS, 3.5f * GD, 1, 0.5f, 0.4f, limbSwing, limbSwingAmount);
                this.moveY(this.mainbody, 0.3f * GS, -1, limbSwing, limbSwingAmount, 0.08f);
                this.swingX(this.mainbody, 0.3f * GS, 0.8f * GD, -1, 0.8f, 0.0f, limbSwing, limbSwingAmount);
            } else {
                float f2 = MathHelper.func_76134_b((float)(ageInTicks * 2.6f)) * 0.015f;
                float f1 = MathHelper.func_76134_b((float)(ageInTicks * 2.27f)) * 0.02f;
                this.mainbody.field_82906_o = f2;
                this.mainbody.field_82907_q = f1;
            }
            this.jointM.field_78795_f = -0.5f;
            this.jointH.field_78795_f += 0.6f;
        }
        this.underground(parasite, ageInTicks, this.mainbody);
    }
}

