/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.math.MathHelper
 */
package com.subspaceparasite.client.model.entity.inborn;

import com.subspaceparasite.client.model.ModelSP;
import com.subspaceparasite.entity.monster.inborn.EntityLodo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class ModelLodo
extends ModelSP {
    public ModelRenderer mainbody;
    public ModelRenderer bodyfront;
    public ModelRenderer joint1;
    public ModelRenderer dec;
    public ModelRenderer dec_1;
    public ModelRenderer dec_2;
    public ModelRenderer dec_3;
    public ModelRenderer body;
    public ModelRenderer joint2;
    public ModelRenderer bodyMiddle;
    public ModelRenderer joint3;
    public ModelRenderer dec_4;
    public ModelRenderer dec_5;
    public ModelRenderer dec_6;
    public ModelRenderer dec_7;
    public ModelRenderer dec_8;
    public ModelRenderer dec_9;
    public ModelRenderer dec_10;
    public ModelRenderer body_1;
    public ModelRenderer joint4;
    public ModelRenderer bodyback;
    public ModelRenderer joint5;
    public ModelRenderer dec_11;
    public ModelRenderer dec_12;
    public ModelRenderer dec_13;
    public ModelRenderer dec_14;
    public ModelRenderer body_2;

    public ModelLodo() {
        this.field_78090_t = 64;
        this.field_78089_u = 16;
        this.bodyMiddle = new ModelRenderer((ModelBase)this, 16, 3);
        this.bodyMiddle.func_78793_a(0.0f, 0.0f, 2.5f);
        this.bodyMiddle.func_78790_a(-3.0f, -3.0f, -1.5f, 6, 4, 3, 0.0f);
        this.dec_4 = new ModelRenderer((ModelBase)this, 31, 2);
        this.dec_4.func_78793_a(0.0f, -2.6f, -0.7f);
        this.dec_4.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.dec_4, -0.38397244f, 0.0f, 0.0f);
        this.dec_7 = new ModelRenderer((ModelBase)this, 31, 4);
        this.dec_7.func_78793_a(2.6f, -1.7f, -0.7f);
        this.dec_7.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.dec_7, 0.0f, -0.38397244f, 0.0f);
        this.dec_1 = new ModelRenderer((ModelBase)this, 26, 0);
        this.dec_1.func_78793_a(-0.9f, -0.5f, -0.2f);
        this.dec_1.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.dec_1, -0.38397244f, 0.0f, 0.0f);
        this.dec_11 = new ModelRenderer((ModelBase)this, 8, 5);
        this.dec_11.func_78793_a(-0.9f, -0.6f, 1.2f);
        this.dec_11.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.dec_11, -0.38397244f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 35, 0);
        this.body.func_78793_a(0.0f, 0.0f, 1.5f);
        this.body.func_78790_a(-2.5f, -2.0f, -1.0f, 5, 3, 3, 0.0f);
        this.dec_10 = new ModelRenderer((ModelBase)this, 0, 5);
        this.dec_10.func_78793_a(-2.6f, -0.2f, -0.7f);
        this.dec_10.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.dec_10, 0.0f, 0.38397244f, 0.0f);
        this.joint4 = new ModelRenderer((ModelBase)this, 2, 5);
        this.joint4.func_78793_a(0.0f, 0.0f, 0.4f);
        this.joint4.func_78790_a(-1.0f, 0.0f, 0.0f, 2, 1, 2, 0.0f);
        this.dec_2 = new ModelRenderer((ModelBase)this, 30, 0);
        this.dec_2.func_78793_a(1.5f, 0.0f, -0.2f);
        this.dec_2.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.dec_2, 0.0f, -0.38397244f, 0.0f);
        this.dec_5 = new ModelRenderer((ModelBase)this, 0, 3);
        this.dec_5.func_78793_a(1.8f, -2.6f, -0.7f);
        this.dec_5.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.dec_5, -0.38397244f, 0.0f, 0.0f);
        this.joint3 = new ModelRenderer((ModelBase)this, 54, 1);
        this.joint3.func_78793_a(0.0f, 0.0f, 0.4f);
        this.joint3.func_78790_a(-1.0f, 0.0f, 0.0f, 2, 1, 2, 0.0f);
        this.joint5 = new ModelRenderer((ModelBase)this, 47, 6);
        this.joint5.func_78793_a(0.0f, 0.0f, 0.4f);
        this.joint5.func_78790_a(-1.0f, 0.0f, 0.0f, 2, 1, 2, 0.0f);
        this.dec = new ModelRenderer((ModelBase)this, 22, 0);
        this.dec.func_78793_a(0.9f, -0.5f, -0.2f);
        this.dec.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.dec, -0.38397244f, 0.0f, 0.0f);
        this.bodyback = new ModelRenderer((ModelBase)this, 0, 8);
        this.bodyback.func_78793_a(0.0f, 0.0f, 1.5f);
        this.bodyback.func_78790_a(-2.0f, -1.0f, -2.0f, 4, 2, 4, 0.0f);
        this.dec_6 = new ModelRenderer((ModelBase)this, 51, 3);
        this.dec_6.func_78793_a(-1.8f, -2.6f, -0.7f);
        this.dec_6.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.dec_6, -0.38397244f, 0.0f, 0.0f);
        this.mainbody = new ModelRenderer((ModelBase)this, 0, 0);
        this.mainbody.func_78793_a(0.0f, 23.0f, -3.0f);
        this.mainbody.func_78790_a(-1.0f, 0.0f, -3.0f, 2, 1, 2, 0.0f);
        this.dec_8 = new ModelRenderer((ModelBase)this, 54, 4);
        this.dec_8.func_78793_a(2.6f, -0.2f, -0.7f);
        this.dec_8.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.dec_8, 0.0f, -0.38397244f, 0.0f);
        this.dec_3 = new ModelRenderer((ModelBase)this, 34, 0);
        this.dec_3.func_78793_a(-1.5f, 0.0f, -0.2f);
        this.dec_3.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.dec_3, 0.0f, 0.38397244f, 0.0f);
        this.dec_14 = new ModelRenderer((ModelBase)this, 57, 6);
        this.dec_14.func_78793_a(-1.6f, 0.0f, 1.2f);
        this.dec_14.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.dec_14, 0.0f, 0.38397244f, 0.0f);
        this.body_2 = new ModelRenderer((ModelBase)this, 45, 9);
        this.body_2.func_78793_a(0.0f, 0.0f, 1.5f);
        this.body_2.func_78790_a(-1.0f, 0.0f, -2.0f, 2, 1, 5, 0.0f);
        this.dec_12 = new ModelRenderer((ModelBase)this, 12, 5);
        this.dec_12.func_78793_a(0.9f, -0.6f, 1.2f);
        this.dec_12.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.dec_12, -0.38397244f, 0.0f, 0.0f);
        this.dec_9 = new ModelRenderer((ModelBase)this, 58, 4);
        this.dec_9.func_78793_a(-2.6f, -1.7f, -0.7f);
        this.dec_9.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.dec_9, 0.0f, 0.38397244f, 0.0f);
        this.dec_13 = new ModelRenderer((ModelBase)this, 53, 6);
        this.dec_13.func_78793_a(1.6f, 0.0f, 1.2f);
        this.dec_13.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.dec_13, 0.0f, -0.38397244f, 0.0f);
        this.bodyfront = new ModelRenderer((ModelBase)this, 5, 0);
        this.bodyfront.func_78793_a(0.0f, 0.0f, -1.5f);
        this.bodyfront.func_78790_a(-2.0f, -1.0f, -1.0f, 4, 2, 3, 0.0f);
        this.joint2 = new ModelRenderer((ModelBase)this, 48, 0);
        this.joint2.func_78793_a(0.0f, 0.0f, -0.5f);
        this.joint2.func_78790_a(-1.0f, 0.0f, 0.0f, 2, 1, 2, 0.0f);
        this.body_1 = new ModelRenderer((ModelBase)this, 34, 6);
        this.body_1.func_78793_a(0.0f, 0.0f, 1.5f);
        this.body_1.func_78790_a(-2.5f, -2.0f, -2.0f, 5, 3, 3, 0.0f);
        this.joint1 = new ModelRenderer((ModelBase)this, 16, 0);
        this.joint1.func_78793_a(0.0f, 0.0f, 0.4f);
        this.joint1.func_78790_a(-1.0f, 0.0f, 0.0f, 2, 1, 2, 0.0f);
        this.joint2.func_78792_a(this.bodyMiddle);
        this.bodyMiddle.func_78792_a(this.dec_4);
        this.bodyMiddle.func_78792_a(this.dec_7);
        this.bodyfront.func_78792_a(this.dec_1);
        this.bodyback.func_78792_a(this.dec_11);
        this.joint1.func_78792_a(this.body);
        this.bodyMiddle.func_78792_a(this.dec_10);
        this.body_1.func_78792_a(this.joint4);
        this.bodyfront.func_78792_a(this.dec_2);
        this.bodyMiddle.func_78792_a(this.dec_5);
        this.bodyMiddle.func_78792_a(this.joint3);
        this.bodyback.func_78792_a(this.joint5);
        this.bodyfront.func_78792_a(this.dec);
        this.joint4.func_78792_a(this.bodyback);
        this.bodyMiddle.func_78792_a(this.dec_6);
        this.bodyMiddle.func_78792_a(this.dec_8);
        this.bodyfront.func_78792_a(this.dec_3);
        this.bodyback.func_78792_a(this.dec_14);
        this.joint5.func_78792_a(this.body_2);
        this.bodyback.func_78792_a(this.dec_12);
        this.bodyMiddle.func_78792_a(this.dec_9);
        this.bodyback.func_78792_a(this.dec_13);
        this.mainbody.func_78792_a(this.bodyfront);
        this.body.func_78792_a(this.joint2);
        this.joint3.func_78792_a(this.body_1);
        this.bodyfront.func_78792_a(this.joint1);
    }

    public void func_78088_a(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.func_78088_a(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.mainbody.func_78785_a(scale);
    }

    public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        EntityLodo ven = (EntityLodo)entityIn;
        float f6 = (float)ven.getFloorTimer();
        if (f6 >= 0.0f) {
            float f2 = MathHelper.func_76134_b((float)(ageInTicks * 2.6f)) * 0.015f;
            float f1 = MathHelper.func_76134_b((float)(ageInTicks * 2.27f)) * 0.02f;
            this.mainbody.field_82906_o = f2;
            this.mainbody.field_82907_q = f1;
            this.mainbody.field_78795_f = 1.6f;
            this.mainbody.field_82907_q = 0.2f;
            this.mainbody.field_82908_p = f6;
            f1 = MathHelper.func_76134_b((float)(ageInTicks * 0.643219f)) * 0.06510051f;
            f2 = MathHelper.func_76134_b((float)(ageInTicks * 0.643219f)) * 0.06510015f;
            this.joint1.field_78796_g = f1;
            this.joint2.field_78796_g = f1;
            this.joint3.field_78796_g = f1;
            this.joint4.field_78796_g = f1;
            this.joint5.field_78796_g = f1;
            this.mainbody.field_78808_h = f2;
            this.bodyMiddle.field_78808_h = f2;
        } else {
            this.mainbody.field_82906_o = 0.0f;
            this.mainbody.field_82907_q = 0.0f;
            this.mainbody.field_78795_f = 0.0f;
            this.mainbody.field_82907_q = 0.0f;
            this.mainbody.field_82908_p = 0.0f;
            float f1 = MathHelper.func_76134_b((float)(limbSwing * 1.5f)) * 1.0f * limbSwingAmount * 0.25f;
            float f2 = MathHelper.func_76134_b((float)(limbSwing * 1.5f)) * 1.0f * limbSwingAmount * 0.15f;
            this.joint1.field_78796_g = f1;
            this.joint2.field_78796_g = f1;
            this.joint3.field_78796_g = f1;
            this.joint4.field_78796_g = f1;
            this.joint5.field_78796_g = f1;
            this.mainbody.field_78808_h = f2;
            this.bodyMiddle.field_78808_h = f2;
        }
    }

    public void func_78086_a(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
    }
}

