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

import com.subspaceparasite.client.model.ModelSP;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSpeVillager
extends ModelSP {
    public ModelRenderer mainbody;
    public ModelRenderer body;
    public ModelRenderer b;
    public ModelRenderer b_1;
    public ModelRenderer b_2;
    public ModelRenderer b_3;
    public ModelRenderer joint;
    public ModelRenderer body_1;
    public ModelRenderer jointLA;
    public ModelRenderer jointRA;
    public ModelRenderer body_2;
    public ModelRenderer jointLL;
    public ModelRenderer jointRL;
    public ModelRenderer jointdont;
    public ModelRenderer leg;
    public ModelRenderer jointLL1;
    public ModelRenderer leg_1;
    public ModelRenderer jointdont_1;
    public ModelRenderer leg_2;
    public ModelRenderer jointRL1;
    public ModelRenderer leg_3;
    public ModelRenderer jointdont_2;
    public ModelRenderer arm;
    public ModelRenderer dec;
    public ModelRenderer jointdont_3;
    public ModelRenderer arm_1;
    public ModelRenderer dec_1;
    public ModelRenderer neck;
    public ModelRenderer joint_1;
    public ModelRenderer jointM;
    public ModelRenderer head;
    public ModelRenderer head_1;
    public ModelRenderer dec_2;
    public ModelRenderer mouth;
    public ModelRenderer nose;
    public ModelRenderer m;
    public ModelRenderer jointM_1;
    public ModelRenderer mouth_1;
    public ModelRenderer teeth;
    public ModelRenderer teeth_1;
    public ModelRenderer teeth_2;
    public ModelRenderer teeth_3;
    public ModelRenderer teeth_4;
    public ModelRenderer teeth_5;

    public ModelSpeVillager() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.mainbody = new ModelRenderer((ModelBase)this, 0, 0);
        this.mainbody.func_78793_a(0.0f, 24.5f, -0.2f);
        this.mainbody.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.jointRL = new ModelRenderer((ModelBase)this, 58, 0);
        this.jointRL.func_78793_a(-3.0f, -0.6f, -0.4f);
        this.jointRL.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.mouth_1 = new ModelRenderer((ModelBase)this, 0, 31);
        this.mouth_1.func_78793_a(0.0f, 0.0f, -0.3f);
        this.mouth_1.func_78790_a(-3.5f, 0.0f, -4.3f, 7, 2, 5, 0.0f);
        this.setRotateAngle(this.mouth_1, 0.67718774f, 0.0f, 0.0f);
        this.dec_1 = new ModelRenderer((ModelBase)this, 27, 17);
        this.dec_1.func_78793_a(-0.6f, 0.0f, 0.3f);
        this.dec_1.func_78790_a(-1.5f, -1.5f, -1.5f, 3, 3, 3, 0.0f);
        this.setRotateAngle(this.dec_1, -0.08726646f, 0.2443461f, -0.57595867f);
        this.leg_1 = new ModelRenderer((ModelBase)this, 75, 10);
        this.leg_1.func_78793_a(0.0f, 1.4f, 0.3f);
        this.leg_1.func_78790_a(-2.0f, -1.2f, -2.5f, 4, 7, 5, 0.0f);
        this.setRotateAngle(this.leg_1, 0.32463124f, 0.0f, 0.0f);
        this.arm = new ModelRenderer((ModelBase)this, 0, 14);
        this.arm.func_78793_a(0.0f, 0.0f, 0.0f);
        this.arm.func_78790_a(0.0f, -1.0f, -1.0f, 7, 2, 2, 0.0f);
        this.setRotateAngle(this.arm, 0.0f, 0.0f, 0.82030475f);
        this.teeth_5 = new ModelRenderer((ModelBase)this, 94, 2);
        this.teeth_5.func_78793_a(3.9f, 0.0f, -3.2f);
        this.teeth_5.func_78790_a(-0.5f, -1.5f, -0.5f, 1, 2, 1, 0.0f);
        this.setRotateAngle(this.teeth_5, 0.0f, (float)Math.PI, 0.10471976f);
        this.leg_3 = new ModelRenderer((ModelBase)this, 48, 13);
        this.leg_3.func_78793_a(0.0f, 1.4f, 0.3f);
        this.leg_3.func_78790_a(-2.0f, -1.2f, -2.5f, 4, 7, 5, 0.0f);
        this.setRotateAngle(this.leg_3, 0.32463124f, 0.0f, 0.0f);
        this.leg = new ModelRenderer((ModelBase)this, 113, 7);
        this.leg.func_78793_a(0.0f, 2.0f, 0.0f);
        this.leg.func_78790_a(-1.5f, -1.0f, -1.5f, 3, 6, 3, 0.0f);
        this.setRotateAngle(this.leg, -0.6387905f, 0.0f, 0.0f);
        this.jointRL1 = new ModelRenderer((ModelBase)this, 94, 0);
        this.jointRL1.func_78793_a(0.0f, 4.0f, 0.0f);
        this.jointRL1.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.dec = new ModelRenderer((ModelBase)this, 18, 14);
        this.dec.func_78793_a(-0.6f, 0.0f, -0.3f);
        this.dec.func_78790_a(-1.5f, -1.5f, -1.5f, 3, 3, 3, 0.0f);
        this.setRotateAngle(this.dec, 0.08726646f, -0.2443461f, -0.57595867f);
        this.b = new ModelRenderer((ModelBase)this, 32, 0);
        this.b.func_78793_a(0.0f, -25.0f, 4.3f);
        this.b.func_78790_a(-1.0f, -2.0f, -1.0f, 2, 6, 3, 0.0f);
        this.setRotateAngle(this.b, -0.18849556f, 0.0f, 0.0f);
        this.body_2 = new ModelRenderer((ModelBase)this, 27, 9);
        this.body_2.func_78793_a(0.0f, 4.5f, 0.0f);
        this.body_2.func_78790_a(-4.0f, -1.0f, -2.5f, 8, 3, 5, 0.0f);
        this.body_1 = new ModelRenderer((ModelBase)this, 94, 0);
        this.body_1.func_78793_a(0.0f, 5.0f, 0.0f);
        this.body_1.func_78790_a(-3.0f, -1.0f, -2.0f, 6, 5, 5, 0.0f);
        this.setRotateAngle(this.body_1, -0.13962634f, 0.0f, 0.0f);
        this.jointM = new ModelRenderer((ModelBase)this, 123, 0);
        this.jointM.func_78793_a(0.0f, -1.2f, -2.4f);
        this.jointM.func_78790_a(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f);
        this.jointdont_3 = new ModelRenderer((ModelBase)this, 115, 0);
        this.jointdont_3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.jointdont_3.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.jointdont_3, 0.19198622f, 2.7750735f, 0.0f);
        this.b_3 = new ModelRenderer((ModelBase)this, 80, 0);
        this.b_3.func_78793_a(0.0f, -23.8f, 0.4f);
        this.b_3.func_78790_a(-1.5f, -3.0f, -2.0f, 3, 6, 4, 0.0f);
        this.setRotateAngle(this.b_3, -0.25132743f, 0.0f, 0.0f);
        this.head_1 = new ModelRenderer((ModelBase)this, 57, 22);
        this.head_1.func_78793_a(0.0f, -3.0f, 0.0f);
        this.head_1.func_78790_a(-5.0f, -10.0f, -5.0f, 10, 10, 9, 0.0f);
        this.setRotateAngle(this.head_1, -0.43982297f, 0.0f, 0.0f);
        this.jointdont = new ModelRenderer((ModelBase)this, 75, 0);
        this.jointdont.func_78793_a(0.0f, 0.0f, 0.0f);
        this.jointdont.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.jointdont, 0.0f, -0.06981317f, 0.0f);
        this.teeth = new ModelRenderer((ModelBase)this, 42, 1);
        this.teeth.func_78793_a(-2.9f, 0.0f, -4.6f);
        this.teeth.func_78790_a(-0.5f, -1.5f, -0.5f, 1, 2, 1, 0.0f);
        this.setRotateAngle(this.teeth, 0.10471976f, 0.0f, 0.0f);
        this.teeth_2 = new ModelRenderer((ModelBase)this, 25, 2);
        this.teeth_2.func_78793_a(-3.9f, 0.0f, -3.2f);
        this.teeth_2.func_78790_a(-0.5f, -1.5f, -0.5f, 1, 2, 1, 0.0f);
        this.setRotateAngle(this.teeth_2, 0.0f, 0.0f, -0.10471976f);
        this.body = new ModelRenderer((ModelBase)this, 0, 0);
        this.body.func_78793_a(0.0f, -21.5f, 0.8f);
        this.body.func_78790_a(-4.5f, -2.0f, -3.0f, 9, 7, 7, 0.0f);
        this.setRotateAngle(this.body, 0.4537856f, 0.0f, 0.0f);
        this.head = new ModelRenderer((ModelBase)this, 90, 20);
        this.head.func_78793_a(0.0f, -4.2f, -0.3f);
        this.head.func_78790_a(-3.5f, 1.0f, 2.0f, 7, 7, 3, 0.0f);
        this.setRotateAngle(this.head, -0.12217305f, 0.0f, 0.0f);
        this.mouth = new ModelRenderer((ModelBase)this, 95, 30);
        this.mouth.func_78793_a(0.0f, 2.0f, 4.0f);
        this.mouth.func_78790_a(-4.5f, -2.0f, -4.0f, 9, 5, 4, 0.0f);
        this.b_2 = new ModelRenderer((ModelBase)this, 58, 0);
        this.b_2.func_78793_a(0.0f, -30.3f, 1.6f);
        this.b_2.func_78790_a(-3.0f, -2.0f, -2.5f, 6, 8, 5, 0.0f);
        this.setRotateAngle(this.b_2, 0.56548667f, 0.0f, 0.0f);
        this.jointLA = new ModelRenderer((ModelBase)this, 29, 0);
        this.jointLA.func_78793_a(5.0f, 1.0f, 0.1f);
        this.jointLA.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.teeth_4 = new ModelRenderer((ModelBase)this, 75, 2);
        this.teeth_4.func_78793_a(2.9f, 0.0f, -4.6f);
        this.teeth_4.func_78790_a(-0.5f, -1.5f, -0.5f, 1, 2, 1, 0.0f);
        this.setRotateAngle(this.teeth_4, 0.10471976f, 0.0f, 0.0f);
        this.jointdont_1 = new ModelRenderer((ModelBase)this, 90, 0);
        this.jointdont_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.jointdont_1.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.jointdont_1, 0.0f, 0.06981317f, 0.0f);
        this.b_1 = new ModelRenderer((ModelBase)this, 42, 0);
        this.b_1.func_78793_a(0.0f, -30.6f, 1.0f);
        this.b_1.func_78790_a(-2.0f, -2.0f, 1.3f, 4, 5, 4, 0.0f);
        this.setRotateAngle(this.b_1, -0.06283186f, 0.0f, 0.0f);
        this.jointLL1 = new ModelRenderer((ModelBase)this, 79, 0);
        this.jointLL1.func_78793_a(0.0f, 4.0f, 0.0f);
        this.jointLL1.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.jointdont_2 = new ModelRenderer((ModelBase)this, 111, 0);
        this.jointdont_2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.jointdont_2.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.jointdont_2, -0.19198622f, 0.36651915f, 0.0f);
        this.leg_2 = new ModelRenderer((ModelBase)this, 93, 10);
        this.leg_2.func_78793_a(0.0f, 2.0f, 0.0f);
        this.leg_2.func_78790_a(-1.5f, -1.0f, -1.5f, 3, 6, 3, 0.0f);
        this.setRotateAngle(this.leg_2, -0.6387905f, 0.0f, 0.0f);
        this.teeth_1 = new ModelRenderer((ModelBase)this, 0, 2);
        this.teeth_1.func_78793_a(-1.6f, 0.0f, -4.6f);
        this.teeth_1.func_78790_a(-0.5f, -2.5f, -0.5f, 1, 3, 1, 0.0f);
        this.setRotateAngle(this.teeth_1, 0.10471976f, 0.0f, 0.0f);
        this.jointRA = new ModelRenderer((ModelBase)this, 39, 0);
        this.jointRA.func_78793_a(-5.0f, 1.0f, 0.1f);
        this.jointRA.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.jointM_1 = new ModelRenderer((ModelBase)this, 3, 1);
        this.jointM_1.func_78793_a(0.0f, 3.1f, 0.0f);
        this.jointM_1.func_78790_a(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f);
        this.teeth_3 = new ModelRenderer((ModelBase)this, 58, 2);
        this.teeth_3.func_78793_a(1.6f, 0.0f, -4.6f);
        this.teeth_3.func_78790_a(-0.5f, -1.5f, -0.5f, 1, 2, 1, 0.0f);
        this.setRotateAngle(this.teeth_3, 0.10471976f, 0.0f, 0.0f);
        this.arm_1 = new ModelRenderer((ModelBase)this, 105, 16);
        this.arm_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.arm_1.func_78790_a(0.0f, -1.0f, -1.0f, 7, 2, 2, 0.0f);
        this.setRotateAngle(this.arm_1, 0.0f, 0.0f, 0.82030475f);
        this.neck = new ModelRenderer((ModelBase)this, 0, 18);
        this.neck.func_78793_a(0.0f, -3.0f, -1.5f);
        this.neck.func_78790_a(-2.5f, -1.5f, -2.0f, 5, 7, 4, 0.0f);
        this.setRotateAngle(this.neck, 0.43982297f, 0.0f, 0.0f);
        this.m = new ModelRenderer((ModelBase)this, 36, 22);
        this.m.func_78793_a(0.0f, 1.2f, -1.0f);
        this.m.func_78790_a(-2.0f, -2.0f, 0.0f, 4, 6, 3, 0.0f);
        this.setRotateAngle(this.m, -0.43982297f, 0.0f, 0.0f);
        this.jointLL = new ModelRenderer((ModelBase)this, 54, 0);
        this.jointLL.func_78793_a(3.0f, -0.6f, -0.4f);
        this.jointLL.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.joint_1 = new ModelRenderer((ModelBase)this, 119, 0);
        this.joint_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.joint_1.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.dec_2 = new ModelRenderer((ModelBase)this, 18, 23);
        this.dec_2.func_78793_a(0.0f, -6.0f, -3.0f);
        this.dec_2.func_78790_a(-3.0f, 0.0f, 0.0f, 6, 5, 3, 0.0f);
        this.nose = new ModelRenderer((ModelBase)this, 110, 20);
        this.nose.func_78793_a(0.0f, -1.0f, -0.7f);
        this.nose.func_78790_a(-1.5f, -3.0f, -6.0f, 3, 5, 2, 0.0f);
        this.setRotateAngle(this.nose, 0.0f, 0.0f, 0.37699112f);
        this.joint = new ModelRenderer((ModelBase)this, 25, 0);
        this.joint.func_78793_a(0.0f, -27.0f, -1.0f);
        this.joint.func_78790_a(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f);
        this.body_2.func_78792_a(this.jointRL);
        this.jointM_1.func_78792_a(this.mouth_1);
        this.arm_1.func_78792_a(this.dec_1);
        this.jointLL1.func_78792_a(this.leg_1);
        this.jointdont_2.func_78792_a(this.arm);
        this.mouth_1.func_78792_a(this.teeth_5);
        this.jointRL1.func_78792_a(this.leg_3);
        this.jointdont.func_78792_a(this.leg);
        this.leg_2.func_78792_a(this.jointRL1);
        this.arm.func_78792_a(this.dec);
        this.mainbody.func_78792_a(this.b);
        this.body_1.func_78792_a(this.body_2);
        this.body.func_78792_a(this.body_1);
        this.neck.func_78792_a(this.jointM);
        this.jointRA.func_78792_a(this.jointdont_3);
        this.mainbody.func_78792_a(this.b_3);
        this.joint_1.func_78792_a(this.head_1);
        this.jointLL.func_78792_a(this.jointdont);
        this.mouth_1.func_78792_a(this.teeth);
        this.mouth_1.func_78792_a(this.teeth_2);
        this.mainbody.func_78792_a(this.body);
        this.joint_1.func_78792_a(this.head);
        this.head.func_78792_a(this.mouth);
        this.mainbody.func_78792_a(this.b_2);
        this.body.func_78792_a(this.jointLA);
        this.mouth_1.func_78792_a(this.teeth_4);
        this.jointRL.func_78792_a(this.jointdont_1);
        this.mainbody.func_78792_a(this.b_1);
        this.leg.func_78792_a(this.jointLL1);
        this.jointLA.func_78792_a(this.jointdont_2);
        this.jointdont_1.func_78792_a(this.leg_2);
        this.mouth_1.func_78792_a(this.teeth_1);
        this.body.func_78792_a(this.jointRA);
        this.m.func_78792_a(this.jointM_1);
        this.mouth_1.func_78792_a(this.teeth_3);
        this.jointdont_3.func_78792_a(this.arm_1);
        this.joint.func_78792_a(this.neck);
        this.jointM.func_78792_a(this.m);
        this.body_2.func_78792_a(this.jointLL);
        this.neck.func_78792_a(this.joint_1);
        this.joint_1.func_78792_a(this.dec_2);
        this.head_1.func_78792_a(this.nose);
        this.mainbody.func_78792_a(this.joint);
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
        this.mainbody.field_82908_p = 0.0f;
        this.mainbody.field_78808_h = 0.0f;
        this.mainbody.field_78795_f = 0.0f;
        this.jointLA.field_78795_f = 0.0f;
        this.jointLA.field_78796_g = 0.0f;
        this.jointRA.field_78795_f = 0.0f;
        this.jointRA.field_78796_g = 0.0f;
        this.jointM.field_78795_f = 0.0f;
        this.joint.field_78795_f = headPitch * 0.016f;
        this.joint.field_78796_g = netHeadYaw * 0.016f;
        byte i = parasite.getParasiteStatus();
        if (i == 0) {
            float f2;
            if (!parasite.getStillAni()) {
                float GS = 2.0f;
                float GD = 1.0f;
                this.swingX(this.jointLL, 0.3f * GS, 1.0f * GD, 1, 1.0f, 0.2f, limbSwing, limbSwingAmount);
                this.swingX(this.jointRL, 0.3f * GS, 1.0f * GD, -1, 1.0f, 0.2f, limbSwing, limbSwingAmount);
                this.moveY(this.mainbody, 0.6f * GS, 1, limbSwing, limbSwingAmount, 0.05f);
                this.swingX(this.jointLA, 0.3f * GS, 1.0f * GD, -1, limbSwing, limbSwingAmount);
                this.swingX(this.jointRA, 0.3f * GS, 1.0f * GD, 1, limbSwing, limbSwingAmount);
            } else {
                float f5 = MathHelper.func_76134_b((float)(ageInTicks * 0.111f)) * 0.2f;
                float f6 = MathHelper.func_76134_b((float)(ageInTicks * 0.1721f)) * 0.05f;
                this.jointLA.field_78795_f = -1.0f * f5;
                this.jointRA.field_78795_f = f5;
                this.jointLA.field_78808_h = -1.0f * f6;
                this.jointRA.field_78808_h = f6;
            }
            float f1 = MathHelper.func_76134_b((float)(ageInTicks * 0.2f)) * 0.1f;
            this.jointM.field_78795_f = f2 = MathHelper.func_76134_b((float)(ageInTicks * 0.19599f)) * 0.1112f;
        } else if (i == 1) {
            if (!parasite.getStillAni()) {
                float GS = 2.0f;
                float GD = 1.0f;
                this.swingX(this.jointLL, 0.3f * GS, 1.0f * GD, 1, 1.0f, 0.2f, limbSwing, limbSwingAmount);
                this.swingX(this.jointRL, 0.3f * GS, 1.0f * GD, -1, 1.0f, 0.2f, limbSwing, limbSwingAmount);
                this.moveY(this.mainbody, 0.6f * GS, 1, limbSwing, limbSwingAmount, 0.05f);
                this.swingX(this.jointLA, 0.3f * GS, 1.0f * GD, -1, limbSwing, limbSwingAmount);
                this.swingX(this.jointRA, 0.3f * GS, 1.0f * GD, 1, limbSwing, limbSwingAmount);
                this.jointLA.field_78795_f += -1.2f;
                this.jointRA.field_78795_f += -1.3f;
            } else {
                float f5 = MathHelper.func_76134_b((float)(ageInTicks * 0.111f)) * 0.2f;
                float f6 = MathHelper.func_76134_b((float)(ageInTicks * 0.1721f)) * 0.05f;
                this.jointLA.field_78795_f = -1.0f * f5;
                this.jointRA.field_78795_f = f5;
                this.jointLA.field_78808_h = -1.0f * f6;
                this.jointRA.field_78808_h = f6;
            }
            float f1 = MathHelper.func_76134_b((float)(ageInTicks * 0.2f)) * 0.1f;
            float f2 = MathHelper.func_76134_b((float)(ageInTicks * 0.5f)) * 0.2f;
            this.jointM.field_78795_f = 0.2f + f2;
        } else if (i == 2) {
            if (!parasite.getStillAni()) {
                float GS = 0.8f;
                float GD = 0.5f;
                this.swingX(this.jointLL, 0.4f * GS, 2.1f * GD, 1, 1.0f, 0.2f, limbSwing, limbSwingAmount);
                this.swingX(this.jointLL1, 0.4f * GS, 1.0f * GD, 1, 0.0f, 0.2f, limbSwing, limbSwingAmount);
                this.swingX(this.jointRL, 0.4f * GS, 2.1f * GD, -1, 1.0f, 0.2f, limbSwing, limbSwingAmount);
                this.swingX(this.jointRL1, 0.4f * GS, 1.0f * GD, -1, 0.0f, 0.2f, limbSwing, limbSwingAmount);
                this.moveY(this.mainbody, 0.8f * GS, 1, limbSwing, limbSwingAmount, 0.08f);
                this.mainbody.field_78795_f = 0.0f;
                this.swingY(this.jointLA, 0.6f * GS, 1.5f * GD, -1, 2.0f, -0.8f, limbSwing, limbSwingAmount);
                this.swingX(this.jointLA, 0.6f * GS, 1.5f * GD, -1, 1.0f, 0.4f, limbSwing, limbSwingAmount);
                this.jointLA.field_78795_f += -1.0f;
                this.swingY(this.jointRA, 0.6f * GS, 1.5f * GD, -1, 2.0f, 0.8f, limbSwing, limbSwingAmount);
                this.swingX(this.jointRA, 0.6f * GS, 1.5f * GD, 1, 1.0f, 0.4f, limbSwing, limbSwingAmount);
                this.jointRA.field_78795_f += -1.0f;
            }
            float f1 = MathHelper.func_76134_b((float)(ageInTicks * 0.2f)) * 0.1f;
            float f2 = MathHelper.func_76134_b((float)(ageInTicks * 0.5f)) * 0.2f;
            this.jointM.field_78795_f = 0.2f + f2;
        }
        this.underground(parasite, ageInTicks, this.mainbody);
    }
}

