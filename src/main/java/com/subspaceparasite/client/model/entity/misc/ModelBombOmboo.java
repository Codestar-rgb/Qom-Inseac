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

public class ModelBombOmboo
extends ModelEffect {
    public ModelRenderer mainbody;
    public ModelRenderer dec;
    public ModelRenderer dec_1;
    public ModelRenderer dec_2;
    public ModelRenderer dec_3;
    public ModelRenderer dec_4;

    public ModelBombOmboo() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.mainbody = new ModelRenderer((ModelBase)this, 0, 0);
        this.mainbody.func_78793_a(0.0f, 17.0f, 0.0f);
        this.mainbody.func_78790_a(-3.5f, 0.0f, -5.0f, 7, 7, 10, 0.0f);
        this.dec = new ModelRenderer((ModelBase)this, 24, 0);
        this.dec.func_78793_a(4.0f, 4.0f, 0.0f);
        this.dec.func_78790_a(-2.0f, -2.0f, -3.0f, 4, 4, 6, 0.0f);
        this.setRotateAngle(this.dec, 0.0f, 0.0f, 0.43633232f);
        this.dec_2 = new ModelRenderer((ModelBase)this, 30, 14);
        this.dec_2.func_78793_a(0.0f, 4.0f, -5.5f);
        this.dec_2.func_78790_a(-2.0f, -2.0f, -2.0f, 4, 4, 4, 0.0f);
        this.setRotateAngle(this.dec_2, 0.4712389f, 0.0f, 0.0f);
        this.dec_1 = new ModelRenderer((ModelBase)this, 38, 4);
        this.dec_1.func_78793_a(-4.0f, 4.0f, 0.0f);
        this.dec_1.func_78790_a(-2.0f, -2.0f, -3.0f, 4, 4, 6, 0.0f);
        this.setRotateAngle(this.dec_1, 0.0f, 0.0f, -0.43633232f);
        this.dec_4 = new ModelRenderer((ModelBase)this, 0, 17);
        this.dec_4.func_78793_a(0.0f, 0.3f, 0.0f);
        this.dec_4.func_78790_a(-2.0f, -2.0f, -3.0f, 4, 4, 6, 0.0f);
        this.setRotateAngle(this.dec_4, 0.0f, 0.0f, 0.7853982f);
        this.dec_3 = new ModelRenderer((ModelBase)this, 46, 14);
        this.dec_3.func_78793_a(0.0f, 4.0f, 5.5f);
        this.dec_3.func_78790_a(-2.0f, -2.0f, -2.0f, 4, 4, 4, 0.0f);
        this.setRotateAngle(this.dec_3, -0.4712389f, 0.0f, 0.0f);
        this.mainbody.func_78792_a(this.dec);
        this.mainbody.func_78792_a(this.dec_2);
        this.mainbody.func_78792_a(this.dec_1);
        this.mainbody.func_78792_a(this.dec_4);
        this.mainbody.func_78792_a(this.dec_3);
    }

    public void func_78088_a(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float f5) {
        super.func_78088_a(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, f5);
        this.renderTwo(this.mainbody, ageInTicks, f5, 1.0f, 1.0f, 0.1, 0.8f, 0.05, 0.1);
    }

    public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
    }
}

