/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.subspaceparasite.client.model.entity.primitive;

import com.subspaceparasite.client.model.ModelSRP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelEmana
extends ModelSRP {
    public ModelRenderer mainbody;
    public ModelRenderer shoulderL;
    public ModelRenderer shoulderR;
    public ModelRenderer shoulderB;
    public ModelRenderer body1;
    public ModelRenderer shoulderL_1;
    public ModelRenderer Ltentacle3;
    public ModelRenderer Ltentacle3_1;
    public ModelRenderer shoulderR_1;
    public ModelRenderer Rtentacle3;
    public ModelRenderer Rtentacle3_1;
    public ModelRenderer shoulderB_1;
    public ModelRenderer Btentacle3;
    public ModelRenderer Btentacle3_1;
    public ModelRenderer body2;
    public ModelRenderer tentacle1;
    public ModelRenderer Rbtentacle3;
    public ModelRenderer Lbtentacle3;
    public ModelRenderer tentacle1_1;
    public ModelRenderer tentacle2;
    public ModelRenderer tentacle2_1;
    public ModelRenderer tentacle3;
    public ModelRenderer tentacle3_1;
    public ModelRenderer Rbtentacle3_1;
    public ModelRenderer Lbtentacle3_1;

    public ModelEmana() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Rbtentacle3 = new ModelRenderer((ModelBase)this, 43, 0);
        this.Rbtentacle3.func_78793_a(-3.2f, 0.3f, 0.0f);
        this.Rbtentacle3.func_78790_a(-0.5f, -1.0f, 0.0f, 1, 2, 1, 0.0f);
        this.setRotateAngle(this.Rbtentacle3, -0.5061455f, -0.82030475f, 0.0f);
        this.Rtentacle3 = new ModelRenderer((ModelBase)this, 4, 0);
        this.Rtentacle3.func_78793_a(-2.0f, -4.0f, 0.0f);
        this.Rtentacle3.func_78790_a(-0.5f, -1.0f, 0.0f, 1, 2, 1, 0.0f);
        this.setRotateAngle(this.Rtentacle3, -0.2617994f, -1.5707964f, 0.0f);
        this.shoulderB_1 = new ModelRenderer((ModelBase)this, 51, 24);
        this.shoulderB_1.func_78793_a(0.0f, 0.5f, 0.0f);
        this.shoulderB_1.func_78790_a(-1.5f, -3.0f, -1.5f, 3, 3, 3, 0.0f);
        this.setRotateAngle(this.shoulderB_1, -0.12217305f, 0.0f, 0.0f);
        this.shoulderB = new ModelRenderer((ModelBase)this, 18, 20);
        this.shoulderB.func_78793_a(0.0f, -4.0f, 7.0f);
        this.shoulderB.func_78790_a(-3.0f, 0.0f, -3.0f, 6, 6, 6, 0.0f);
        this.setRotateAngle(this.shoulderB, -0.5934119f, 0.0f, 0.0f);
        this.tentacle2 = new ModelRenderer((ModelBase)this, 0, 3);
        this.tentacle2.func_78793_a(0.0f, 0.0f, 2.0f);
        this.tentacle2.func_78790_a(-1.0f, -1.5f, 0.0f, 2, 3, 1, 0.0f);
        this.setRotateAngle(this.tentacle2, 0.06981317f, 0.0f, 0.0f);
        this.tentacle1_1 = new ModelRenderer((ModelBase)this, 51, 30);
        this.tentacle1_1.func_78793_a(0.0f, 0.0f, 1.0f);
        this.tentacle1_1.func_78790_a(-1.5f, -2.0f, 0.0f, 3, 4, 3, 0.0f);
        this.tentacle1 = new ModelRenderer((ModelBase)this, 49, 0);
        this.tentacle1.func_78793_a(0.0f, 3.0f, 0.0f);
        this.tentacle1.func_78790_a(-1.5f, -2.0f, 0.0f, 3, 4, 1, 0.0f);
        this.setRotateAngle(this.tentacle1, -1.4486233f, 0.0f, 0.0f);
        this.shoulderR_1 = new ModelRenderer((ModelBase)this, 18, 14);
        this.shoulderR_1.func_78793_a(0.0f, -5.5f, 0.0f);
        this.shoulderR_1.func_78790_a(-1.5f, -3.0f, -1.5f, 3, 3, 3, 0.0f);
        this.setRotateAngle(this.shoulderR_1, -0.12217305f, 0.0f, 0.0f);
        this.tentacle3_1 = new ModelRenderer((ModelBase)this, 53, 3);
        this.tentacle3_1.func_78793_a(0.0f, 0.0f, 1.0f);
        this.tentacle3_1.func_78790_a(-0.5f, -1.0f, 0.0f, 1, 2, 4, 0.0f);
        this.body2 = new ModelRenderer((ModelBase)this, 28, 37);
        this.body2.func_78793_a(0.0f, 4.0f, 0.0f);
        this.body2.func_78790_a(-4.0f, -2.0f, -4.0f, 8, 6, 8, 0.0f);
        this.Rtentacle3_1 = new ModelRenderer((ModelBase)this, 42, 20);
        this.Rtentacle3_1.func_78793_a(0.0f, 0.0f, 1.0f);
        this.Rtentacle3_1.func_78790_a(-0.5f, -1.0f, 0.0f, 1, 2, 5, 0.0f);
        this.mainbody = new ModelRenderer((ModelBase)this, 0, 0);
        this.mainbody.func_78793_a(0.0f, 6.0f, 0.0f);
        this.mainbody.func_78790_a(-5.0f, 0.0f, -5.0f, 10, 4, 10, 0.0f);
        this.Rbtentacle3_1 = new ModelRenderer((ModelBase)this, 12, 45);
        this.Rbtentacle3_1.func_78793_a(0.0f, 0.0f, 1.0f);
        this.Rbtentacle3_1.func_78790_a(-0.5f, -1.0f, 0.0f, 1, 2, 5, 0.0f);
        this.Lbtentacle3 = new ModelRenderer((ModelBase)this, 57, 0);
        this.Lbtentacle3.func_78793_a(3.2f, 0.3f, 0.0f);
        this.Lbtentacle3.func_78790_a(-0.5f, -1.0f, 0.0f, 1, 2, 1, 0.0f);
        this.setRotateAngle(this.Lbtentacle3, -0.5061455f, 0.82030475f, 0.0f);
        this.Ltentacle3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Ltentacle3.func_78793_a(2.0f, -4.0f, 0.0f);
        this.Ltentacle3.func_78790_a(-0.5f, -1.0f, 0.0f, 1, 2, 1, 0.0f);
        this.setRotateAngle(this.Ltentacle3, -0.2617994f, 1.5707964f, 0.0f);
        this.shoulderL = new ModelRenderer((ModelBase)this, 34, 8);
        this.shoulderL.func_78793_a(3.3f, 1.0f, -3.0f);
        this.shoulderL.func_78790_a(-3.0f, -6.0f, -3.0f, 6, 6, 6, 0.0f);
        this.setRotateAngle(this.shoulderL, 0.0f, 0.0f, 0.5934119f);
        this.shoulderL_1 = new ModelRenderer((ModelBase)this, 30, 0);
        this.shoulderL_1.func_78793_a(0.0f, -5.5f, 0.0f);
        this.shoulderL_1.func_78790_a(-1.5f, -3.0f, -1.5f, 3, 3, 3, 0.0f);
        this.setRotateAngle(this.shoulderL_1, -0.12217305f, 0.0f, 0.0f);
        this.Btentacle3 = new ModelRenderer((ModelBase)this, 39, 0);
        this.Btentacle3.func_78793_a(0.0f, 2.0f, 1.7f);
        this.Btentacle3.func_78790_a(-0.5f, -1.0f, 0.0f, 1, 2, 1, 0.0f);
        this.setRotateAngle(this.Btentacle3, -0.4712389f, 0.0f, 0.0f);
        this.Lbtentacle3_1 = new ModelRenderer((ModelBase)this, 19, 47);
        this.Lbtentacle3_1.func_78793_a(0.0f, 0.0f, 1.0f);
        this.Lbtentacle3_1.func_78790_a(-0.5f, -1.0f, 0.0f, 1, 2, 5, 0.0f);
        this.tentacle3 = new ModelRenderer((ModelBase)this, 6, 3);
        this.tentacle3.func_78793_a(0.0f, 0.0f, 3.0f);
        this.tentacle3.func_78790_a(-0.5f, -1.0f, 0.0f, 1, 2, 1, 0.0f);
        this.setRotateAngle(this.tentacle3, 0.05235988f, 0.0f, 0.0f);
        this.body1 = new ModelRenderer((ModelBase)this, 0, 32);
        this.body1.func_78793_a(0.0f, 6.0f, 0.0f);
        this.body1.func_78790_a(-4.5f, -2.0f, -4.5f, 9, 4, 9, 0.0f);
        this.shoulderR = new ModelRenderer((ModelBase)this, 0, 14);
        this.shoulderR.func_78793_a(-3.3f, 1.0f, -3.0f);
        this.shoulderR.func_78790_a(-3.0f, -6.0f, -3.0f, 6, 6, 6, 0.0f);
        this.setRotateAngle(this.shoulderR, 0.0f, 0.0f, -0.5934119f);
        this.tentacle2_1 = new ModelRenderer((ModelBase)this, 0, 45);
        this.tentacle2_1.func_78793_a(0.0f, 0.0f, 1.0f);
        this.tentacle2_1.func_78790_a(-1.0f, -1.5f, 0.0f, 2, 3, 4, 0.0f);
        this.Ltentacle3_1 = new ModelRenderer((ModelBase)this, 42, 0);
        this.Ltentacle3_1.func_78793_a(0.0f, 0.0f, 1.0f);
        this.Ltentacle3_1.func_78790_a(-0.5f, -1.0f, 0.0f, 1, 2, 5, 0.0f);
        this.Btentacle3_1 = new ModelRenderer((ModelBase)this, 35, 27);
        this.Btentacle3_1.func_78793_a(0.0f, 0.0f, 1.0f);
        this.Btentacle3_1.func_78790_a(-0.5f, -1.0f, 0.0f, 1, 2, 7, 0.0f);
        this.body2.func_78792_a(this.Rbtentacle3);
        this.shoulderR.func_78792_a(this.Rtentacle3);
        this.shoulderB.func_78792_a(this.shoulderB_1);
        this.mainbody.func_78792_a(this.shoulderB);
        this.tentacle1_1.func_78792_a(this.tentacle2);
        this.tentacle1.func_78792_a(this.tentacle1_1);
        this.body2.func_78792_a(this.tentacle1);
        this.shoulderR.func_78792_a(this.shoulderR_1);
        this.tentacle3.func_78792_a(this.tentacle3_1);
        this.body1.func_78792_a(this.body2);
        this.Rtentacle3.func_78792_a(this.Rtentacle3_1);
        this.Rbtentacle3.func_78792_a(this.Rbtentacle3_1);
        this.body2.func_78792_a(this.Lbtentacle3);
        this.shoulderL.func_78792_a(this.Ltentacle3);
        this.mainbody.func_78792_a(this.shoulderL);
        this.shoulderL.func_78792_a(this.shoulderL_1);
        this.shoulderB.func_78792_a(this.Btentacle3);
        this.Lbtentacle3.func_78792_a(this.Lbtentacle3_1);
        this.tentacle2_1.func_78792_a(this.tentacle3);
        this.mainbody.func_78792_a(this.body1);
        this.mainbody.func_78792_a(this.shoulderR);
        this.tentacle2.func_78792_a(this.tentacle2_1);
        this.Ltentacle3.func_78792_a(this.Ltentacle3_1);
        this.Btentacle3.func_78792_a(this.Btentacle3_1);
    }

    public void func_78088_a(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.func_78088_a(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.mainbody.func_78785_a(scale);
    }

    public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        float f3 = 0.6f * MathHelper.func_76126_a((float)(ageInTicks / 10.0f));
        this.Ltentacle3.field_78795_f = f3 / 3.0f;
        this.Rtentacle3.field_78795_f = f3 / 3.0f;
        this.Btentacle3.field_78795_f = f3 / 3.0f;
    }
}

