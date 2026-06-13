/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.dhanantry.scapeandrunparasites.client.model.entity.infected.special;

import com.dhanantry.scapeandrunparasites.client.model.ModelSRP;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSpeSheep
extends ModelSRP {
    public ModelRenderer mainbody;
    public ModelRenderer body;
    public ModelRenderer jointN;
    public ModelRenderer bodym;
    public ModelRenderer JD;
    public ModelRenderer JD_1;
    public ModelRenderer body_1;
    public ModelRenderer body_2;
    public ModelRenderer JD_2;
    public ModelRenderer JD_3;
    public ModelRenderer jointLL;
    public ModelRenderer leg;
    public ModelRenderer jointLL1;
    public ModelRenderer leg_1;
    public ModelRenderer leg_2;
    public ModelRenderer jointRL;
    public ModelRenderer leg_3;
    public ModelRenderer jointRL1;
    public ModelRenderer leg_4;
    public ModelRenderer leg_5;
    public ModelRenderer jointLA;
    public ModelRenderer leg_6;
    public ModelRenderer jointLA1;
    public ModelRenderer leg_7;
    public ModelRenderer leg_8;
    public ModelRenderer jointRA;
    public ModelRenderer leg_9;
    public ModelRenderer jointRA1;
    public ModelRenderer leg_10;
    public ModelRenderer leg_11;
    public ModelRenderer n;
    public ModelRenderer jointN1;
    public ModelRenderer n_1;
    public ModelRenderer neck;
    public ModelRenderer jointdont;
    public ModelRenderer jointH;
    public ModelRenderer head;
    public ModelRenderer mouth;
    public ModelRenderer mouthg;
    public ModelRenderer jointM;
    public ModelRenderer mouth_1;
    public ModelRenderer teeth;
    public ModelRenderer teeth_1;
    public ModelRenderer teeth_2;
    public ModelRenderer teeth_3;
    public ModelRenderer teeth_4;

    public ModelSpeSheep() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.leg_3 = new ModelRenderer((ModelBase)this, 16, 18);
        this.leg_3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.leg_3.func_78790_a(-2.0f, -1.0f, -2.0f, 4, 7, 4, 0.0f);
        this.setRotateAngle(this.leg_3, 0.43982297f, 0.0f, 0.0f);
        this.teeth_2 = new ModelRenderer((ModelBase)this, 35, 2);
        this.teeth_2.func_78793_a(2.5f, 0.0f, -4.6f);
        this.teeth_2.func_78790_a(-0.5f, -1.5f, -0.5f, 1, 2, 1, 0.0f);
        this.setRotateAngle(this.teeth_2, 0.6073746f, 0.0f, 0.0f);
        this.body_2 = new ModelRenderer((ModelBase)this, 88, 0);
        this.body_2.func_78793_a(0.0f, 0.0f, 10.0f);
        this.body_2.func_78790_a(-2.5f, 0.0f, -3.0f, 5, 12, 10, 0.0f);
        this.setRotateAngle(this.body_2, 0.12566371f, 0.0f, 0.0f);
        this.JD = new ModelRenderer((ModelBase)this, 27, 0);
        this.JD.func_78793_a(2.8f, 7.7f, 4.7f);
        this.JD.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.JD, 0.54803336f, 0.0f, 0.0f);
        this.JD_2 = new ModelRenderer((ModelBase)this, 35, 0);
        this.JD_2.func_78793_a(3.0f, 5.7f, 1.1f);
        this.JD_2.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.jointLL = new ModelRenderer((ModelBase)this, 57, 0);
        this.jointLL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.jointLL.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.n = new ModelRenderer((ModelBase)this, 104, 28);
        this.n.func_78793_a(0.0f, -1.0f, 1.0f);
        this.n.func_78790_a(-2.0f, -7.0f, -2.0f, 4, 7, 4, 0.0f);
        this.setRotateAngle(this.n, 2.4504423f, 0.0f, 0.0f);
        this.mouth_1 = new ModelRenderer((ModelBase)this, 49, 42);
        this.mouth_1.func_78793_a(-1.0f, 0.0f, 0.0f);
        this.mouth_1.func_78790_a(-2.5f, 0.0f, -5.3f, 7, 2, 6, 0.0f);
        this.setRotateAngle(this.mouth_1, 0.4537856f, -0.12566371f, -0.12566371f);
        this.body_1 = new ModelRenderer((ModelBase)this, 64, 0);
        this.body_1.func_78793_a(0.0f, 0.0f, 14.0f);
        this.body_1.func_78790_a(-3.5f, -0.5f, -1.5f, 7, 9, 5, 0.0f);
        this.setRotateAngle(this.body_1, 0.39095375f, 0.0f, 0.0f);
        this.bodym = new ModelRenderer((ModelBase)this, 30, 0);
        this.bodym.func_78793_a(0.0f, 0.7f, 6.4f);
        this.bodym.func_78790_a(-3.0f, -1.0f, -1.0f, 6, 11, 11, 0.0f);
        this.setRotateAngle(this.bodym, -0.28274333f, 0.0f, 0.0f);
        this.jointN1 = new ModelRenderer((ModelBase)this, 3, 1);
        this.jointN1.func_78793_a(0.0f, -1.0f, 0.0f);
        this.jointN1.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.leg_4 = new ModelRenderer((ModelBase)this, 115, 19);
        this.leg_4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.leg_4.func_78790_a(-1.5f, 0.0f, -1.5f, 3, 6, 3, 0.0f);
        this.setRotateAngle(this.leg_4, 0.0f, 0.08726646f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 0, 0);
        this.body.func_78793_a(0.0f, -10.0f, -8.0f);
        this.body.func_78790_a(-4.0f, -1.0f, 1.0f, 8, 11, 7, 0.0f);
        this.setRotateAngle(this.body, -0.57595867f, 0.0f, 0.0f);
        this.jointN = new ModelRenderer((ModelBase)this, 23, 0);
        this.jointN.func_78793_a(0.0f, -10.0f, -6.0f);
        this.jointN.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.leg_5 = new ModelRenderer((ModelBase)this, 76, 21);
        this.leg_5.func_78793_a(0.0f, 6.3f, 0.0f);
        this.leg_5.func_78790_a(-2.0f, -1.0f, -2.0f, 4, 2, 4, 0.0f);
        this.leg_8 = new ModelRenderer((ModelBase)this, 92, 22);
        this.leg_8.func_78793_a(0.0f, 11.3f, 0.0f);
        this.leg_8.func_78790_a(-2.0f, -1.0f, -2.0f, 4, 3, 4, 0.0f);
        this.JD_3 = new ModelRenderer((ModelBase)this, 53, 0);
        this.JD_3.func_78793_a(-3.0f, 5.7f, 1.1f);
        this.JD_3.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.jointLA1 = new ModelRenderer((ModelBase)this, 91, 0);
        this.jointLA1.func_78793_a(0.0f, 10.0f, 0.0f);
        this.jointLA1.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.jointdont = new ModelRenderer((ModelBase)this, 94, 1);
        this.jointdont.func_78793_a(0.0f, 1.0f, -2.5f);
        this.jointdont.func_78790_a(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.jointdont, 0.29670596f, 0.0f, 0.0f);
        this.jointRA1 = new ModelRenderer((ModelBase)this, 121, 0);
        this.jointRA1.func_78793_a(0.0f, 10.0f, 0.0f);
        this.jointRA1.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.neck = new ModelRenderer((ModelBase)this, 11, 35);
        this.neck.func_78793_a(0.0f, 3.8f, 0.2f);
        this.neck.func_78790_a(-2.0f, -2.7f, -3.0f, 4, 6, 7, 0.0f);
        this.setRotateAngle(this.neck, 2.5132742f, 0.0f, 0.0f);
        this.leg_10 = new ModelRenderer((ModelBase)this, 62, 25);
        this.leg_10.func_78793_a(0.0f, 0.0f, 0.0f);
        this.leg_10.func_78790_a(-1.5f, 0.0f, -1.5f, 3, 11, 3, 0.0f);
        this.setRotateAngle(this.leg_10, 0.12566371f, 0.08726646f, 0.0f);
        this.teeth_1 = new ModelRenderer((ModelBase)this, 31, 2);
        this.teeth_1.func_78793_a(-1.9f, 0.0f, -2.2f);
        this.teeth_1.func_78790_a(-0.5f, -1.5f, -0.5f, 1, 2, 1, 0.0f);
        this.setRotateAngle(this.teeth_1, 0.0f, 0.0f, -0.2932153f);
        this.jointRL = new ModelRenderer((ModelBase)this, 65, 0);
        this.jointRL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.jointRL.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.jointRA = new ModelRenderer((ModelBase)this, 117, 0);
        this.jointRA.func_78793_a(0.0f, 0.0f, 0.0f);
        this.jointRA.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.mouth = new ModelRenderer((ModelBase)this, 46, 36);
        this.mouth.func_78793_a(0.0f, 0.0f, -0.3f);
        this.mouth.func_78790_a(-3.0f, 0.0f, -2.0f, 6, 2, 4, 0.0f);
        this.teeth_4 = new ModelRenderer((ModelBase)this, 57, 2);
        this.teeth_4.func_78793_a(0.0f, 0.0f, -4.6f);
        this.teeth_4.func_78790_a(-0.5f, -2.5f, -0.5f, 1, 3, 1, 0.0f);
        this.setRotateAngle(this.teeth_4, 0.6702064f, 0.0f, 0.0f);
        this.mouthg = new ModelRenderer((ModelBase)this, 27, 42);
        this.mouthg.func_78793_a(0.0f, 2.0f, -1.8f);
        this.mouthg.func_78790_a(-2.5f, -6.3f, 0.8f, 5, 6, 6, 0.0f);
        this.setRotateAngle(this.mouthg, 0.2443461f, 0.0f, 0.0f);
        this.jointLL1 = new ModelRenderer((ModelBase)this, 61, 0);
        this.jointLL1.func_78793_a(0.0f, 5.0f, 0.0f);
        this.jointLL1.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.leg_6 = new ModelRenderer((ModelBase)this, 32, 22);
        this.leg_6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.leg_6.func_78790_a(-2.0f, -2.0f, -2.0f, 4, 13, 5, 0.0f);
        this.setRotateAngle(this.leg_6, -0.12566371f, 0.0f, -0.06283186f);
        this.jointLA = new ModelRenderer((ModelBase)this, 87, 0);
        this.jointLA.func_78793_a(0.0f, 0.0f, 0.0f);
        this.jointLA.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.leg_2 = new ModelRenderer((ModelBase)this, 0, 18);
        this.leg_2.func_78793_a(0.0f, 6.3f, 0.0f);
        this.leg_2.func_78790_a(-2.0f, -1.0f, -2.0f, 4, 2, 4, 0.0f);
        this.jointM = new ModelRenderer((ModelBase)this, 23, 2);
        this.jointM.func_78793_a(0.0f, -0.4f, 1.9f);
        this.jointM.func_78790_a(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f);
        this.leg_1 = new ModelRenderer((ModelBase)this, 108, 0);
        this.leg_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.leg_1.func_78790_a(-1.5f, 0.0f, -1.5f, 3, 6, 3, 0.0f);
        this.setRotateAngle(this.leg_1, 0.0f, -0.08726646f, 0.0f);
        this.jointRL1 = new ModelRenderer((ModelBase)this, 83, 0);
        this.jointRL1.func_78793_a(0.0f, 5.0f, 0.0f);
        this.jointRL1.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.JD_1 = new ModelRenderer((ModelBase)this, 31, 0);
        this.JD_1.func_78793_a(-2.8f, 7.7f, 4.7f);
        this.JD_1.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.JD_1, 0.54803336f, 0.0f, 0.0f);
        this.jointH = new ModelRenderer((ModelBase)this, 0, 2);
        this.jointH.func_78793_a(0.0f, 0.0f, 0.0f);
        this.jointH.func_78790_a(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f);
        this.mainbody = new ModelRenderer((ModelBase)this, 0, 0);
        this.mainbody.func_78793_a(0.0f, 1.9f, 3.0f);
        this.mainbody.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.leg = new ModelRenderer((ModelBase)this, 64, 14);
        this.leg.func_78793_a(0.0f, 0.0f, 0.0f);
        this.leg.func_78790_a(-2.0f, -1.0f, -2.0f, 4, 7, 4, 0.0f);
        this.setRotateAngle(this.leg, 0.43982297f, 0.0f, 0.0f);
        this.leg_7 = new ModelRenderer((ModelBase)this, 50, 22);
        this.leg_7.func_78793_a(0.0f, 0.0f, 0.0f);
        this.leg_7.func_78790_a(-1.5f, 0.0f, -1.5f, 3, 11, 3, 0.0f);
        this.setRotateAngle(this.leg_7, 0.12566371f, -0.08726646f, 0.0f);
        this.n_1 = new ModelRenderer((ModelBase)this, 69, 34);
        this.n_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.n_1.func_78790_a(-3.0f, -1.5f, -2.5f, 6, 3, 5, 0.0f);
        this.setRotateAngle(this.n_1, 0.56548667f, -0.08726646f, 0.0f);
        this.head = new ModelRenderer((ModelBase)this, 83, 34);
        this.head.func_78793_a(0.0f, 1.7f, -2.0f);
        this.head.func_78790_a(-3.0f, -6.0f, -6.0f, 6, 6, 8, 0.0f);
        this.teeth_3 = new ModelRenderer((ModelBase)this, 53, 2);
        this.teeth_3.func_78793_a(3.9f, 0.0f, -3.2f);
        this.teeth_3.func_78790_a(-0.5f, -1.5f, -0.5f, 1, 2, 1, 0.0f);
        this.setRotateAngle(this.teeth_3, 0.0f, (float)Math.PI, 0.54454273f);
        this.teeth = new ModelRenderer((ModelBase)this, 27, 2);
        this.teeth.func_78793_a(-1.5f, 0.0f, -4.6f);
        this.teeth.func_78790_a(-0.5f, -1.5f, -0.5f, 1, 2, 1, 0.0f);
        this.setRotateAngle(this.teeth, 0.48171088f, 0.0f, 0.0f);
        this.leg_9 = new ModelRenderer((ModelBase)this, 0, 24);
        this.leg_9.func_78793_a(0.0f, 0.0f, 0.0f);
        this.leg_9.func_78790_a(-2.0f, -2.0f, -2.0f, 4, 13, 5, 0.0f);
        this.setRotateAngle(this.leg_9, -0.12566371f, 0.0f, 0.06283186f);
        this.leg_11 = new ModelRenderer((ModelBase)this, 74, 27);
        this.leg_11.func_78793_a(0.0f, 11.3f, 0.0f);
        this.leg_11.func_78790_a(-2.0f, -1.0f, -2.0f, 4, 3, 4, 0.0f);
        this.jointRL.func_78792_a(this.leg_3);
        this.mouth_1.func_78792_a(this.teeth_2);
        this.bodym.func_78792_a(this.body_2);
        this.body.func_78792_a(this.JD);
        this.body_1.func_78792_a(this.JD_2);
        this.JD_2.func_78792_a(this.jointLL);
        this.jointN.func_78792_a(this.n);
        this.jointM.func_78792_a(this.mouth_1);
        this.bodym.func_78792_a(this.body_1);
        this.body.func_78792_a(this.bodym);
        this.n.func_78792_a(this.jointN1);
        this.jointRL1.func_78792_a(this.leg_4);
        this.mainbody.func_78792_a(this.body);
        this.mainbody.func_78792_a(this.jointN);
        this.leg_4.func_78792_a(this.leg_5);
        this.leg_7.func_78792_a(this.leg_8);
        this.body_1.func_78792_a(this.JD_3);
        this.leg_6.func_78792_a(this.jointLA1);
        this.neck.func_78792_a(this.jointdont);
        this.leg_9.func_78792_a(this.jointRA1);
        this.n_1.func_78792_a(this.neck);
        this.jointRA1.func_78792_a(this.leg_10);
        this.mouth_1.func_78792_a(this.teeth_1);
        this.JD_3.func_78792_a(this.jointRL);
        this.JD_1.func_78792_a(this.jointRA);
        this.head.func_78792_a(this.mouth);
        this.mouth_1.func_78792_a(this.teeth_4);
        this.head.func_78792_a(this.mouthg);
        this.leg.func_78792_a(this.jointLL1);
        this.jointLA.func_78792_a(this.leg_6);
        this.JD.func_78792_a(this.jointLA);
        this.leg_1.func_78792_a(this.leg_2);
        this.mouthg.func_78792_a(this.jointM);
        this.jointLL1.func_78792_a(this.leg_1);
        this.leg_3.func_78792_a(this.jointRL1);
        this.body.func_78792_a(this.JD_1);
        this.jointdont.func_78792_a(this.jointH);
        this.jointLL.func_78792_a(this.leg);
        this.jointLA1.func_78792_a(this.leg_7);
        this.jointN1.func_78792_a(this.n_1);
        this.jointH.func_78792_a(this.head);
        this.mouth_1.func_78792_a(this.teeth_3);
        this.mouth_1.func_78792_a(this.teeth);
        this.jointRA.func_78792_a(this.leg_9);
        this.leg_10.func_78792_a(this.leg_11);
    }

    public void func_78088_a(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.func_78088_a(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.mainbody.func_78785_a(scale);
    }

    public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        EntityParasiteBase parasite = (EntityParasiteBase)entityIn;
        this.jointLL.field_78795_f = 0.0f;
        this.jointRL.field_78795_f = 0.0f;
        this.jointLL1.field_78795_f = 0.0f;
        this.jointRL1.field_78795_f = 0.0f;
        this.jointLA.field_78795_f = 0.0f;
        this.jointRA.field_78795_f = 0.0f;
        this.jointLA1.field_78795_f = 0.0f;
        this.jointRA1.field_78795_f = 0.0f;
        this.mainbody.field_82908_p = 0.0f;
        this.mainbody.field_78808_h = 0.0f;
        this.mainbody.field_78795_f = 0.0f;
        this.jointM.field_78795_f = 0.0f;
        this.jointH.field_78795_f = headPitch * 0.016f;
        this.jointH.field_78796_g = netHeadYaw * 0.016f;
        byte i = parasite.getParasiteStatus();
        if (i == 0) {
            float f2;
            float GD;
            if (!parasite.getStillAni()) {
                float GS = 2.3f;
                GD = 1.0f;
                this.swingX(this.jointLL, 0.3f * GS, 1.0f * GD, 1, 1.0f, 0.2f, limbSwing, limbSwingAmount);
                this.swingX(this.jointRL, 0.3f * GS, 1.0f * GD, -1, 1.0f, 0.2f, limbSwing, limbSwingAmount);
                this.moveY(this.mainbody, 0.6f * GS, 1, limbSwing, limbSwingAmount, 0.05f);
                this.swingX(this.jointLA, 0.3f * GS, 1.0f * GD, -1, limbSwing, limbSwingAmount);
                this.swingX(this.jointRA, 0.3f * GS, 1.0f * GD, 1, limbSwing, limbSwingAmount);
            } else {
                float f5 = MathHelper.func_76134_b((float)(ageInTicks * 0.111f)) * 0.2f;
                GD = MathHelper.func_76134_b((float)(ageInTicks * 0.1721f)) * 0.05f;
            }
            float f1 = MathHelper.func_76134_b((float)(ageInTicks * 0.2f)) * 0.1f;
            this.jointM.field_78795_f = f2 = MathHelper.func_76134_b((float)(ageInTicks * 0.19599f)) * 0.1112f;
        } else if (i == 1) {
            float GD;
            if (!parasite.getStillAni()) {
                float GS = 2.2f;
                GD = 1.0f;
                this.swingX(this.jointLL, 0.3f * GS, 1.0f * GD, 1, 1.0f, 0.2f, limbSwing, limbSwingAmount);
                this.swingX(this.jointRL, 0.3f * GS, 1.0f * GD, -1, 1.0f, 0.2f, limbSwing, limbSwingAmount);
                this.moveY(this.mainbody, 0.6f * GS, 1, limbSwing, limbSwingAmount, 0.05f);
                this.swingX(this.jointLA, 0.3f * GS, 1.0f * GD, -1, limbSwing, limbSwingAmount);
                this.swingX(this.jointRA, 0.3f * GS, 1.0f * GD, 1, limbSwing, limbSwingAmount);
            } else {
                float f5 = MathHelper.func_76134_b((float)(ageInTicks * 0.111f)) * 0.2f;
                GD = MathHelper.func_76134_b((float)(ageInTicks * 0.1721f)) * 0.05f;
            }
            float f1 = MathHelper.func_76134_b((float)(ageInTicks * 0.2f)) * 0.1f;
            float f2 = MathHelper.func_76134_b((float)(ageInTicks * 0.5f)) * 0.2f;
            this.jointM.field_78795_f = 0.2f + f2;
        } else if (i == 2) {
            if (!parasite.getStillAni()) {
                float GS = 0.9f;
                float GD = 0.5f;
                this.swingX(this.jointLL, 0.4f * GS, 2.1f * GD, 1, 1.0f, 0.2f, limbSwing, limbSwingAmount);
                this.swingX(this.jointLL1, 0.4f * GS, 1.0f * GD, 1, 0.0f, 0.2f, limbSwing, limbSwingAmount);
                this.swingX(this.jointRL, 0.4f * GS, 2.1f * GD, -1, 1.0f, 0.2f, limbSwing, limbSwingAmount);
                this.swingX(this.jointRL1, 0.4f * GS, 1.0f * GD, -1, 0.0f, 0.2f, limbSwing, limbSwingAmount);
                this.moveY(this.mainbody, 0.8f * GS, 1, limbSwing, limbSwingAmount, 0.08f);
                this.mainbody.field_78795_f = 0.0f;
                this.jointH.field_78795_f = -0.1f;
                this.swingY(this.jointLA, 0.6f * GS, 1.5f * GD, -1, 2.0f, -0.8f, limbSwing, limbSwingAmount);
                this.swingX(this.jointLA, 0.6f * GS, 1.5f * GD, -1, 1.0f, 0.4f, limbSwing, limbSwingAmount);
                this.swingY(this.jointRA, 0.6f * GS, 1.5f * GD, -1, 2.0f, 0.8f, limbSwing, limbSwingAmount);
                this.swingX(this.jointRA, 0.6f * GS, 1.5f * GD, 1, 1.0f, 0.4f, limbSwing, limbSwingAmount);
            }
            float f1 = MathHelper.func_76134_b((float)(ageInTicks * 0.2f)) * 0.1f;
            float f2 = MathHelper.func_76134_b((float)(ageInTicks * 0.5f)) * 0.2f;
            this.jointM.field_78795_f = 0.2f + f2;
        }
        this.underground(parasite, ageInTicks, this.mainbody);
    }
}

