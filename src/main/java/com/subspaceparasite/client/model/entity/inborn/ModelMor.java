/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package com.subspaceparasite.client.model.entity.inborn;

import com.subspaceparasite.client.model.ModelEffect;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMor
extends ModelEffect {
    public ModelRenderer mainbody;
    public ModelRenderer dec;
    public ModelRenderer dec_1;
    public ModelRenderer dec_2;
    public ModelRenderer dec_3;
    public ModelRenderer mainbody_1;
    public ModelRenderer dec_4;
    public ModelRenderer dec_5;
    public ModelRenderer dec_6;
    public ModelRenderer dec_7;

    public ModelMor() {
        this.field_78090_t = 64;
        this.field_78089_u = 16;
        this.dec = new ModelRenderer((ModelBase)this, 16, 0);
        this.dec.func_78793_a(-2.0f, -2.0f, -2.0f);
        this.dec.func_78790_a(-1.5f, -1.5f, -2.5f, 3, 3, 3, 0.0f);
        this.setRotateAngle(this.dec, -0.36651915f, 0.7853982f, 0.0f);
        this.dec_4 = new ModelRenderer((ModelBase)this, 29, 6);
        this.dec_4.func_78793_a(-2.0f, -2.0f, -2.0f);
        this.dec_4.func_78790_a(-1.5f, -1.5f, -2.5f, 3, 3, 3, 0.0f);
        this.setRotateAngle(this.dec_4, -0.36651915f, 0.7853982f, 0.0f);
        this.dec_6 = new ModelRenderer((ModelBase)this, 38, 9);
        this.dec_6.func_78793_a(2.0f, 2.0f, -2.0f);
        this.dec_6.func_78790_a(-1.5f, -1.5f, -2.5f, 3, 3, 3, 0.0f);
        this.setRotateAngle(this.dec_6, 0.36651915f, -0.7853982f, 0.0f);
        this.mainbody_1 = new ModelRenderer((ModelBase)this, 13, 6);
        this.mainbody_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.mainbody_1.func_78790_a(-2.5f, -2.5f, -2.5f, 5, 5, 3, 0.0f);
        this.setRotateAngle(this.mainbody_1, 0.0f, (float)Math.PI, 0.0f);
        this.dec_7 = new ModelRenderer((ModelBase)this, 50, 9);
        this.dec_7.func_78793_a(-2.0f, 2.0f, -2.0f);
        this.dec_7.func_78790_a(-1.5f, -1.5f, -2.5f, 3, 3, 3, 0.0f);
        this.setRotateAngle(this.dec_7, 0.36651915f, 0.7853982f, 0.0f);
        this.mainbody = new ModelRenderer((ModelBase)this, 0, 0);
        this.mainbody.func_78793_a(0.0f, 20.0f, 0.0f);
        this.mainbody.func_78790_a(-2.5f, -2.5f, -2.5f, 5, 5, 3, 0.0f);
        this.dec_5 = new ModelRenderer((ModelBase)this, 0, 8);
        this.dec_5.func_78793_a(2.0f, -2.0f, -2.0f);
        this.dec_5.func_78790_a(-1.5f, -1.5f, -2.5f, 3, 3, 3, 0.0f);
        this.setRotateAngle(this.dec_5, -0.36651915f, -0.7853982f, 0.0f);
        this.dec_1 = new ModelRenderer((ModelBase)this, 28, 0);
        this.dec_1.func_78793_a(2.0f, -2.0f, -2.0f);
        this.dec_1.func_78790_a(-1.5f, -1.5f, -2.5f, 3, 3, 3, 0.0f);
        this.setRotateAngle(this.dec_1, -0.36651915f, -0.7853982f, 0.0f);
        this.dec_2 = new ModelRenderer((ModelBase)this, 40, 0);
        this.dec_2.func_78793_a(2.0f, 2.0f, -2.0f);
        this.dec_2.func_78790_a(-1.5f, -1.5f, -2.5f, 3, 3, 3, 0.0f);
        this.setRotateAngle(this.dec_2, 0.36651915f, -0.7853982f, 0.0f);
        this.dec_3 = new ModelRenderer((ModelBase)this, 49, 3);
        this.dec_3.func_78793_a(-2.0f, 2.0f, -2.0f);
        this.dec_3.func_78790_a(-1.5f, -1.5f, -2.5f, 3, 3, 3, 0.0f);
        this.setRotateAngle(this.dec_3, 0.36651915f, 0.7853982f, 0.0f);
        this.mainbody.func_78792_a(this.dec);
        this.mainbody_1.func_78792_a(this.dec_4);
        this.mainbody_1.func_78792_a(this.dec_6);
        this.mainbody.func_78792_a(this.mainbody_1);
        this.mainbody_1.func_78792_a(this.dec_7);
        this.mainbody_1.func_78792_a(this.dec_5);
        this.mainbody.func_78792_a(this.dec_1);
        this.mainbody.func_78792_a(this.dec_2);
        this.mainbody.func_78792_a(this.dec_3);
    }

    public void func_78088_a(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float f5) {
        super.func_78088_a(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, f5);
        this.renderTwo(this.mainbody, ageInTicks, f5, 1.0f, 1.0f, 0.1, 0.8f, 0.05, 0.1);
    }

    public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
    }
}

