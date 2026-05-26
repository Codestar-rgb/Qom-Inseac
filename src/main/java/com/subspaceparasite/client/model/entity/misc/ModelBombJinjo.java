/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package com.subspaceparasite.client.model.entity.misc;

import com.subspaceparasite.client.model.ModelEffect;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBombJinjo
extends ModelEffect {
    public ModelRenderer mainbody;
    public ModelRenderer b;
    public ModelRenderer b_1;
    public ModelRenderer b_2;
    public ModelRenderer b_3;
    public ModelRenderer b_4;
    public ModelRenderer b_5;
    public ModelRenderer b_6;
    public ModelRenderer b_7;
    public ModelRenderer b_8;
    public ModelRenderer b_9;

    public ModelBombJinjo() {
        this.field_78090_t = 135;
        this.field_78089_u = 150;
        this.b = new ModelRenderer((ModelBase)this, 0, 0);
        this.b.func_78793_a(0.0f, 18.5f, 0.0f);
        this.b.func_78790_a(-4.0f, -8.0f, -7.0f, 14, 14, 12, 0.0f);
        this.b_5 = new ModelRenderer((ModelBase)this, 0, 53);
        this.b_5.func_78793_a(-1.1f, 6.5f, -6.0f);
        this.b_5.func_78790_a(-4.0f, -8.0f, -7.0f, 14, 14, 12, 0.0f);
        this.setRotateAngle(this.b_5, 0.50265485f, 0.0f, 0.0f);
        this.b_4 = new ModelRenderer((ModelBase)this, 40, 30);
        this.b_4.func_78793_a(1.0f, 2.0f, 0.0f);
        this.b_4.func_78790_a(-4.0f, -12.0f, -3.5f, 15, 22, 6, 0.0f);
        this.mainbody = new ModelRenderer((ModelBase)this, 0, 0);
        this.mainbody.func_78793_a(-3.0f, 0.0f, 0.0f);
        this.mainbody.func_78790_a(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f);
        this.b_1 = new ModelRenderer((ModelBase)this, 52, 0);
        this.b_1.func_78793_a(-8.0f, 4.0f, -5.0f);
        this.b_1.func_78790_a(-4.0f, 0.0f, -4.0f, 10, 15, 15, 0.0f);
        this.setRotateAngle(this.b_1, 0.0f, 0.0f, -0.43982297f);
        this.b_7 = new ModelRenderer((ModelBase)this, 0, 79);
        this.b_7.func_78793_a(-1.1f, 1.5f, 9.0f);
        this.b_7.func_78790_a(-3.0f, -16.0f, -7.0f, 14, 19, 12, 0.0f);
        this.setRotateAngle(this.b_7, 0.12566371f, 0.0f, 0.0f);
        this.b_3 = new ModelRenderer((ModelBase)this, 0, 26);
        this.b_3.func_78793_a(-5.0f, -6.0f, 1.0f);
        this.b_3.func_78790_a(-4.0f, -4.5f, -6.0f, 10, 17, 10, 0.0f);
        this.setRotateAngle(this.b_3, 0.0f, 0.0f, 0.12566371f);
        this.b_9 = new ModelRenderer((ModelBase)this, 90, 109);
        this.b_9.func_78793_a(-1.4f, -8.5f, 2.0f);
        this.b_9.func_78790_a(-1.0f, -16.0f, -7.0f, 8, 14, 10, 0.0f);
        this.setRotateAngle(this.b_9, 0.06283186f, 0.0f, 0.0f);
        this.b_8 = new ModelRenderer((ModelBase)this, 52, 88);
        this.b_8.func_78793_a(-1.1f, -2.5f, -7.0f);
        this.b_8.func_78790_a(-2.5f, -10.0f, -7.0f, 10, 17, 14, 0.0f);
        this.setRotateAngle(this.b_8, -0.12566371f, 0.0f, 0.0f);
        this.b_2 = new ModelRenderer((ModelBase)this, 92, 20);
        this.b_2.func_78793_a(8.0f, 5.0f, -1.0f);
        this.b_2.func_78790_a(-4.0f, -12.0f, -5.0f, 11, 27, 10, 0.0f);
        this.setRotateAngle(this.b_2, 0.0f, 0.0f, 0.18849556f);
        this.b_6 = new ModelRenderer((ModelBase)this, 70, 57);
        this.b_6.func_78793_a(-1.1f, 16.5f, 5.0f);
        this.b_6.func_78790_a(-1.0f, -16.0f, -7.0f, 11, 19, 12, 0.0f);
        this.setRotateAngle(this.b_6, -0.37699112f, 0.0f, 0.0f);
        this.mainbody.func_78792_a(this.b);
        this.mainbody.func_78792_a(this.b_5);
        this.mainbody.func_78792_a(this.b_4);
        this.mainbody.func_78792_a(this.b_1);
        this.mainbody.func_78792_a(this.b_7);
        this.mainbody.func_78792_a(this.b_3);
        this.mainbody.func_78792_a(this.b_9);
        this.mainbody.func_78792_a(this.b_8);
        this.mainbody.func_78792_a(this.b_2);
        this.mainbody.func_78792_a(this.b_6);
    }

    public void func_78088_a(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float f5) {
        super.func_78088_a(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, f5);
        this.renderTwo(this.mainbody, ageInTicks, f5, 1.0f, 1.0f, 0.1, 0.8f, 0.05, 0.1);
    }

    public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
    }
}

