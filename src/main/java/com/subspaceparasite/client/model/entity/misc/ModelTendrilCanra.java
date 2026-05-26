/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package com.subspaceparasite.client.model.entity.misc;

import com.subspaceparasite.client.model.ModelSP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTendrilCanra
extends ModelSP {
    public ModelRenderer mainbody;
    public ModelRenderer taclejointL1;
    public ModelRenderer tentacle;
    public ModelRenderer taclejointL2;
    public ModelRenderer tentacle_1;
    public ModelRenderer taclejointL3;
    public ModelRenderer tentacle_2;
    public ModelRenderer taclejointL4;
    public ModelRenderer tentacle_3;

    public ModelTendrilCanra() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.tentacle_3 = new ModelRenderer((ModelBase)this, 30, 16);
        this.tentacle_3.func_78793_a(0.0f, 0.0f, 1.0f);
        this.tentacle_3.func_78790_a(-1.0f, -1.0f, -1.0f, 2, 2, 12, 0.0f);
        this.setRotateAngle(this.tentacle_3, 0.0f, -0.80285144f, 0.0f);
        this.mainbody = new ModelRenderer((ModelBase)this, 0, 0);
        this.mainbody.func_78793_a(0.0f, 21.7f, 5.0f);
        this.mainbody.func_78790_a(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.mainbody, 0.0f, (float)Math.PI, -1.5707964f);
        this.taclejointL1 = new ModelRenderer((ModelBase)this, 4, 0);
        this.taclejointL1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.taclejointL1.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.taclejointL3 = new ModelRenderer((ModelBase)this, 24, 0);
        this.taclejointL3.func_78793_a(0.0f, 0.0f, 9.0f);
        this.taclejointL3.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.tentacle_2 = new ModelRenderer((ModelBase)this, 0, 15);
        this.tentacle_2.func_78793_a(0.0f, 0.0f, 1.0f);
        this.tentacle_2.func_78790_a(-1.5f, -1.5f, -1.0f, 3, 3, 12, 0.0f);
        this.setRotateAngle(this.tentacle_2, 0.0f, -0.62831855f, 0.0f);
        this.tentacle = new ModelRenderer((ModelBase)this, 0, 0);
        this.tentacle.func_78793_a(0.0f, 0.0f, 0.0f);
        this.tentacle.func_78790_a(-2.5f, -2.5f, -1.0f, 5, 5, 10, 0.0f);
        this.tentacle_1 = new ModelRenderer((ModelBase)this, 30, 0);
        this.tentacle_1.func_78793_a(0.0f, 0.0f, 1.0f);
        this.tentacle_1.func_78790_a(-2.0f, -2.0f, -1.0f, 4, 4, 12, 0.0f);
        this.setRotateAngle(this.tentacle_1, 0.0f, -0.54105204f, 0.0f);
        this.taclejointL4 = new ModelRenderer((ModelBase)this, 28, 0);
        this.taclejointL4.func_78793_a(0.0f, 0.0f, 9.0f);
        this.taclejointL4.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.taclejointL2 = new ModelRenderer((ModelBase)this, 20, 0);
        this.taclejointL2.func_78793_a(0.0f, 0.0f, 7.0f);
        this.taclejointL2.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.taclejointL4.func_78792_a(this.tentacle_3);
        this.mainbody.func_78792_a(this.taclejointL1);
        this.tentacle_1.func_78792_a(this.taclejointL3);
        this.taclejointL3.func_78792_a(this.tentacle_2);
        this.taclejointL1.func_78792_a(this.tentacle);
        this.taclejointL2.func_78792_a(this.tentacle_1);
        this.tentacle_2.func_78792_a(this.taclejointL4);
        this.tentacle.func_78792_a(this.taclejointL2);
    }

    public void func_78088_a(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.func_78088_a(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.func_78087_a(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        this.mainbody.func_78785_a(scale);
    }
}

