package com.dhanantry.scapeandrunparasites.client.model.entity.infected.special;

import com.dhanantry.scapeandrunparasites.client.model.ModelSRP;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.special.EntitySpeCow;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSpeCow extends ModelSRP {
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
      this.jointBRL = new ModelRenderer(this, 121, 0);
      this.jointBRL.func_78793_a(0.0F, 0.0F, 0.0F);
      this.jointBRL.func_78790_a(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
      this.jointH = new ModelRenderer(this, 55, 2);
      this.jointH.func_78793_a(0.0F, 0.0F, 0.0F);
      this.jointH.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
      this.mainbody = new ModelRenderer(this, 0, 0);
      this.mainbody.func_78793_a(0.0F, 12.1F, 2.0F);
      this.mainbody.func_78790_a(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
      this.jointFLL1 = new ModelRenderer(this, 39, 2);
      this.jointFLL1.func_78793_a(0.0F, 5.0F, 0.0F);
      this.jointFLL1.func_78790_a(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
      this.body_3 = new ModelRenderer(this, 46, 25);
      this.body_3.func_78793_a(0.0F, 8.2F, -2.0F);
      this.body_3.func_78790_a(-5.0F, 0.0F, -3.0F, 10, 7, 15, 0.0F);
      this.JD_3 = new ModelRenderer(this, 51, 0);
      this.JD_3.func_78793_a(-6.1F, 11.4F, 1.0F);
      this.JD_3.func_78790_a(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
      this.setRotateAngle(this.JD_3, 0.12566371F, 0.0F, 0.0F);
      this.leg_6 = new ModelRenderer(this, 54, 47);
      this.leg_6.func_78793_a(0.0F, 0.0F, 0.0F);
      this.leg_6.func_78790_a(-3.5F, -1.0F, -2.0F, 7, 7, 6, 0.0F);
      this.ear_1 = new ModelRenderer(this, 123, 8);
      this.ear_1.func_78793_a(-1.0F, -1.0F, 0.0F);
      this.ear_1.func_78790_a(-5.0F, -5.0F, -4.0F, 1, 3, 1, 0.0F);
      this.setRotateAngle(this.ear_1, -0.08726646F, 0.0F, 0.06981317F);
      this.leg_10 = new ModelRenderer(this, 112, 59);
      this.leg_10.func_78793_a(0.0F, 0.0F, 0.0F);
      this.leg_10.func_78790_a(-1.5F, 0.0F, -1.5F, 3, 6, 3, 0.0F);
      this.setRotateAngle(this.leg_10, 0.0F, 0.08726646F, 0.0F);
      this.leg_11 = new ModelRenderer(this, 0, 62);
      this.leg_11.func_78793_a(0.0F, 6.3F, 0.0F);
      this.leg_11.func_78790_a(-2.0F, -1.0F, -2.0F, 4, 2, 4, 0.0F);
      this.neck = new ModelRenderer(this, 88, 0);
      this.neck.func_78793_a(0.0F, 1.0F, 0.0F);
      this.neck.func_78790_a(-4.5F, -4.2F, -2.0F, 9, 11, 3, 0.0F);
      this.ear = new ModelRenderer(this, 3, 3);
      this.ear.func_78793_a(1.0F, -2.2F, -0.1F);
      this.ear.func_78790_a(4.0F, -5.0F, -4.0F, 1, 3, 1, 0.0F);
      this.setRotateAngle(this.ear, 0.08726646F, 0.0F, -0.05235988F);
      this.leg_9 = new ModelRenderer(this, 48, 60);
      this.leg_9.func_78793_a(0.0F, 0.0F, 0.0F);
      this.leg_9.func_78790_a(-3.5F, -1.0F, -2.0F, 7, 7, 6, 0.0F);
      this.jointBLL1 = new ModelRenderer(this, 109, 0);
      this.jointBLL1.func_78793_a(0.0F, 5.0F, 0.0F);
      this.jointBLL1.func_78790_a(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
      this.body_5 = new ModelRenderer(this, 24, 47);
      this.body_5.func_78793_a(0.0F, -5.0F, 4.0F);
      this.body_5.func_78790_a(-5.0F, -3.5F, -1.0F, 10, 12, 5, 0.0F);
      this.setRotateAngle(this.body_5, -0.12566371F, 0.0F, 0.0F);
      this.jointdont = new ModelRenderer(this, 43, 2);
      this.jointdont.func_78793_a(0.0F, 0.0F, -1.5F);
      this.jointdont.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
      this.setRotateAngle(this.jointdont, 0.108210415F, 0.0F, 0.0F);
      this.mouth = new ModelRenderer(this, 0, 55);
      this.mouth.func_78793_a(0.0F, 0.0F, 0.0F);
      this.mouth.func_78790_a(-4.0F, 0.0F, -2.3F, 8, 4, 3, 0.0F);
      this.setRotateAngle(this.mouth, 0.61784655F, 0.0F, 0.0F);
      this.leg = new ModelRenderer(this, 96, 40);
      this.leg.func_78793_a(0.0F, 0.0F, 0.0F);
      this.leg.func_78790_a(-3.0F, -1.0F, -3.0F, 6, 7, 6, 0.0F);
      this.jointBLL = new ModelRenderer(this, 55, 0);
      this.jointBLL.func_78793_a(0.0F, 0.0F, 0.0F);
      this.jointBLL.func_78790_a(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
      this.leg_5 = new ModelRenderer(this, 102, 16);
      this.leg_5.func_78793_a(0.0F, 6.3F, 0.0F);
      this.leg_5.func_78790_a(-2.0F, -1.0F, -2.0F, 4, 2, 4, 0.0F);
      this.leg_7 = new ModelRenderer(this, 46, 25);
      this.leg_7.func_78793_a(0.0F, 0.0F, 0.0F);
      this.leg_7.func_78790_a(-1.5F, 0.0F, -1.5F, 3, 6, 3, 0.0F);
      this.setRotateAngle(this.leg_7, 0.0F, -0.08726646F, 0.0F);
      this.head = new ModelRenderer(this, 80, 53);
      this.head.func_78793_a(0.0F, 1.0F, 0.0F);
      this.head.func_78790_a(-5.0F, -6.0F, -6.0F, 10, 10, 6, 0.0F);
      this.setRotateAngle(this.head, -0.10471976F, 0.0F, 0.0F);
      this.leg_2 = new ModelRenderer(this, 108, 10);
      this.leg_2.func_78793_a(0.0F, 6.3F, 0.0F);
      this.leg_2.func_78790_a(-2.0F, -1.0F, -2.0F, 4, 2, 4, 0.0F);
      this.body_4 = new ModelRenderer(this, 81, 25);
      this.body_4.func_78793_a(0.0F, 9.0F, 1.0F);
      this.body_4.func_78790_a(-7.5F, -3.5F, -1.0F, 15, 10, 5, 0.0F);
      this.leg_4 = new ModelRenderer(this, 46, 2);
      this.leg_4.func_78793_a(0.0F, 0.0F, 0.0F);
      this.leg_4.func_78790_a(-1.5F, 0.0F, -1.5F, 3, 6, 3, 0.0F);
      this.setRotateAngle(this.leg_4, 0.0F, 0.08726646F, 0.0F);
      this.jointBRL1 = new ModelRenderer(this, 3, 1);
      this.jointBRL1.func_78793_a(0.0F, 5.0F, 0.0F);
      this.jointBRL1.func_78790_a(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
      this.jointFRL = new ModelRenderer(this, 42, 5);
      this.jointFRL.func_78793_a(0.0F, 0.0F, 0.0F);
      this.jointFRL.func_78790_a(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
      this.jointFRL1 = new ModelRenderer(this, 46, 11);
      this.jointFRL1.func_78793_a(0.0F, 5.0F, 0.0F);
      this.jointFRL1.func_78790_a(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
      this.leg_3 = new ModelRenderer(this, 0, 42);
      this.leg_3.func_78793_a(0.0F, 0.0F, 0.0F);
      this.leg_3.func_78790_a(-3.0F, -1.0F, -3.0F, 6, 7, 6, 0.0F);
      this.leg_1 = new ModelRenderer(this, 112, 0);
      this.leg_1.func_78793_a(0.0F, 0.0F, 0.0F);
      this.leg_1.func_78790_a(-1.5F, 0.0F, -1.5F, 3, 6, 3, 0.0F);
      this.setRotateAngle(this.leg_1, 0.0F, -0.08726646F, 0.0F);
      this.leg_8 = new ModelRenderer(this, 74, 47);
      this.leg_8.func_78793_a(0.0F, 6.3F, 0.0F);
      this.leg_8.func_78790_a(-2.0F, -1.0F, -2.0F, 4, 2, 4, 0.0F);
      this.JD = new ModelRenderer(this, 39, 0);
      this.JD.func_78793_a(5.2F, 8.7F, 4.7F);
      this.JD.func_78790_a(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
      this.setRotateAngle(this.JD, 0.29670596F, 0.0F, 0.0F);
      this.body_2 = new ModelRenderer(this, 0, 25);
      this.body_2.func_78793_a(0.0F, 0.0F, 8.0F);
      this.body_2.func_78790_a(-8.0F, -3.5F, -1.0F, 16, 10, 7, 0.0F);
      this.setRotateAngle(this.body_2, -0.08726646F, 0.0F, 0.0F);
      this.JD_2 = new ModelRenderer(this, 47, 0);
      this.JD_2.func_78793_a(6.1F, 11.4F, 1.0F);
      this.JD_2.func_78790_a(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
      this.setRotateAngle(this.JD_2, 0.12566371F, 0.0F, 0.0F);
      this.mouthg = new ModelRenderer(this, 106, 53);
      this.mouthg.func_78793_a(0.0F, 2.0F, -2.7F);
      this.mouthg.func_78790_a(-2.5F, -0.1F, -1.1F, 5, 3, 3, 0.0F);
      this.setRotateAngle(this.mouthg, (float) (Math.PI / 20), 0.0F, 0.0F);
      this.JD_1 = new ModelRenderer(this, 43, 0);
      this.JD_1.func_78793_a(-5.2F, 8.7F, 4.7F);
      this.JD_1.func_78790_a(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
      this.setRotateAngle(this.JD_1, 0.29670596F, 0.0F, 0.0F);
      this.jointM = new ModelRenderer(this, 39, 4);
      this.jointM.func_78793_a(0.0F, 1.5F, 1.0F);
      this.jointM.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
      this.jointFLL = new ModelRenderer(this, 0, 2);
      this.jointFLL.func_78793_a(0.0F, 0.0F, 0.0F);
      this.jointFLL.func_78790_a(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
      this.body = new ModelRenderer(this, 0, 0);
      this.body.func_78793_a(0.0F, -10.0F, -11.0F);
      this.body.func_78790_a(-8.0F, -8.0F, 1.0F, 16, 18, 7, 0.0F);
      this.setRotateAngle(this.body, -0.29670596F, 0.0F, 0.0F);
      this.body_1 = new ModelRenderer(this, 46, 0);
      this.body_1.func_78793_a(0.0F, -4.8F, 8.4F);
      this.body_1.func_78790_a(-7.0F, -2.0F, -5.0F, 14, 11, 14, 0.0F);
      this.setRotateAngle(this.body_1, 0.25830874F, 0.0F, 0.0F);
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
      this.jointFLL.field_78795_f = 0.0F;
      this.jointFRL.field_78795_f = 0.0F;
      this.jointBLL.field_78795_f = 0.0F;
      this.jointBRL.field_78795_f = 0.0F;
      this.mainbody.field_82906_o = 0.0F;
      this.mainbody.field_82908_p = 0.0F;
      this.mainbody.field_82907_q = 0.0F;
      this.mainbody.field_78795_f = 0.0F;
      this.mainbody.field_78808_h = 0.0F;
      this.jointM.field_78795_f = 0.0F;
      this.jointH.field_78795_f = headPitch * 0.016F;
      this.jointH.field_78796_g = netHeadYaw * 0.016F;
      int i = parasite.getParasiteStatus();
      if (i == 0) {
         if (!parasite.getStillAni()) {
            float GS = 2.0F;
            float GD = 2.0F;
            this.swingX(this.jointFLL, 0.3F * GS, 1.0F * GD, 1, limbSwing, limbSwingAmount);
            this.swingX(this.jointFRL, 0.3F * GS, 1.0F * GD, -1, limbSwing, limbSwingAmount);
            this.swingX(this.jointBLL, 0.3F * GS, 1.0F * GD, -1, limbSwing, limbSwingAmount);
            this.swingX(this.jointBRL, 0.3F * GS, 1.0F * GD, 1, limbSwing, limbSwingAmount);
            this.moveY(this.mainbody, 0.6F * GS, 1, limbSwing, limbSwingAmount, 0.05F);
            this.mainbody.field_78795_f = 0.0F;
         }

         float f1 = MathHelper.func_76134_b(ageInTicks * 0.0998F) * 0.0215F;
         float f23 = MathHelper.func_76134_b(ageInTicks * 0.19112F) * 0.0261F;
         float f3 = MathHelper.func_76134_b(ageInTicks * 0.27955F) * 0.0262F;
         float f4 = MathHelper.func_76134_b(ageInTicks * 0.35222F) * 0.0265F;
         float f22 = MathHelper.func_76134_b(ageInTicks * 0.2F) * 0.1F;
         this.jointM.field_78795_f = f22;
      } else if (i == 1) {
         if (!parasite.getStillAni()) {
            float GS = 2.5F;
            float GD = 2.5F;
            this.swingX(this.jointFLL, 0.3F * GS, 1.0F * GD, 1, limbSwing, limbSwingAmount);
            this.swingX(this.jointFRL, 0.3F * GS, 1.0F * GD, -1, limbSwing, limbSwingAmount);
            this.swingX(this.jointBLL, 0.3F * GS, 1.0F * GD, -1, limbSwing, limbSwingAmount);
            this.swingX(this.jointBRL, 0.3F * GS, 1.0F * GD, 1, limbSwing, limbSwingAmount);
            this.moveY(this.mainbody, 0.6F * GS, 1, limbSwing, limbSwingAmount, 0.05F);
            this.mainbody.field_78795_f = 0.0F;
         }

         float f22 = MathHelper.func_76134_b(ageInTicks * 0.5F) * 0.2F;
         this.jointM.field_78795_f = f22;
      } else if (i == 2) {
         if (!parasite.getStillAni()) {
            float GS = 1.5F;
            float GD = 0.3F;
            this.swingX(this.jointFLL, 0.3F * GS, 3.0F * GD, -1, 0.5F, 0.0F, limbSwing, limbSwingAmount);
            this.swingX(this.jointFRL, 0.3F * GS, 3.0F * GD, -1, -0.5F, 0.0F, limbSwing, limbSwingAmount);
            this.swingX(this.jointBLL, 0.3F * GS, 3.5F * GD, 1, -0.5F, 0.4F, limbSwing, limbSwingAmount);
            this.swingX(this.jointBRL, 0.3F * GS, 3.5F * GD, 1, 0.5F, 0.4F, limbSwing, limbSwingAmount);
            this.moveY(this.mainbody, 0.3F * GS, -1, limbSwing, limbSwingAmount, 0.08F);
            this.swingX(this.mainbody, 0.3F * GS, 0.8F * GD, -1, 0.8F, 0.0F, limbSwing, limbSwingAmount);
         }

         float f22 = MathHelper.func_76134_b(ageInTicks * 0.5F) * 0.2F;
         this.jointM.field_78795_f = f22;
      } else if (i == 3) {
         if (!parasite.getStillAni()) {
            float GS = 2.5F;
            float GD = 0.3F;
            this.swingX(this.jointFLL, 0.3F * GS, 3.0F * GD, -1, 0.5F, 0.0F, limbSwing, limbSwingAmount);
            this.swingX(this.jointFRL, 0.3F * GS, 3.0F * GD, -1, -0.5F, 0.0F, limbSwing, limbSwingAmount);
            this.swingX(this.jointBLL, 0.3F * GS, 3.5F * GD, 1, -0.5F, 0.4F, limbSwing, limbSwingAmount);
            this.swingX(this.jointBRL, 0.3F * GS, 3.5F * GD, 1, 0.5F, 0.4F, limbSwing, limbSwingAmount);
            this.moveY(this.mainbody, 0.3F * GS, -1, limbSwing, limbSwingAmount, 0.08F);
            this.swingX(this.mainbody, 0.3F * GS, 0.8F * GD, -1, 0.8F, 0.0F, limbSwing, limbSwingAmount);
         } else {
            float f2 = MathHelper.func_76134_b(ageInTicks * 2.6F) * 0.015F;
            float f1 = MathHelper.func_76134_b(ageInTicks * 2.27F) * 0.02F;
            this.mainbody.field_82906_o = f2;
            this.mainbody.field_82907_q = f1;
         }

         this.jointM.field_78795_f = -0.5F;
         this.jointH.field_78795_f += 0.6F;
      }

      this.underground(parasite, ageInTicks, this.mainbody);
   }
}
