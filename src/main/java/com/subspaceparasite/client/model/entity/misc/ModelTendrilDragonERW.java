/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package com.subspaceparasite.client.model.entity.misc;

import com.subspaceparasite.client.model.ModelSRP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTendrilDragonERW
extends ModelSRP {
    public ModelRenderer mainbody;
    public ModelRenderer jointRW1;
    public ModelRenderer jd;
    public ModelRenderer jointRW1_1;
    public ModelRenderer w;
    public ModelRenderer skin;
    public ModelRenderer jointRW2;
    public ModelRenderer dec;
    public ModelRenderer dec_1;
    public ModelRenderer dec_2;
    public ModelRenderer dec_3;
    public ModelRenderer w_1;
    public ModelRenderer skin_1;
    public ModelRenderer dec_4;

    public ModelTendrilDragonERW() {
        this.field_78090_t = 200;
        this.field_78089_u = 150;
        this.dec_4 = new ModelRenderer((ModelBase)this, 0, 16);
        this.dec_4.func_78793_a(-51.0f, 0.0f, 0.0f);
        this.dec_4.func_78790_a(-1.0f, -1.0f, -2.0f, 15, 2, 3, 0.0f);
        this.setRotateAngle(this.dec_4, 0.0f, (float)Math.PI, 0.0f);
        this.jointRW2 = new ModelRenderer((ModelBase)this, 66, 0);
        this.jointRW2.func_78793_a(-60.0f, 0.0f, 0.0f);
        this.jointRW2.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.dec_2 = new ModelRenderer((ModelBase)this, 68, 0);
        this.dec_2.func_78793_a(-21.0f, -4.6f, -1.5f);
        this.dec_2.func_78790_a(-1.5f, -1.0f, -1.0f, 3, 3, 2, 0.0f);
        this.setRotateAngle(this.dec_2, 1.5707964f, -0.43982297f, 0.0f);
        this.jd = new ModelRenderer((ModelBase)this, 8, 0);
        this.jd.func_78793_a(0.0f, 0.0f, 0.0f);
        this.jd.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.jd, 0.0f, 0.24993114f, 0.0f);
        this.skin_1 = new ModelRenderer((ModelBase)this, 16, 84);
        this.skin_1.func_78793_a(-56.0f, 0.0f, 0.0f);
        this.skin_1.func_78790_a(-56.0f, 0.0f, 2.0f, 60, 0, 60, 0.0f);
        this.setRotateAngle(this.skin_1, 0.0f, 0.0f, (float)(-Math.PI));
        this.jointRW1 = new ModelRenderer((ModelBase)this, 4, 0);
        this.jointRW1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.jointRW1.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.dec_3 = new ModelRenderer((ModelBase)this, 164, 0);
        this.dec_3.func_78793_a(-13.0f, -4.6f, -1.5f);
        this.dec_3.func_78790_a(-1.5f, -1.0f, -1.0f, 3, 5, 2, 0.0f);
        this.setRotateAngle(this.dec_3, 1.5707964f, -0.87964594f, 0.0f);
        this.jointRW1_1 = new ModelRenderer((ModelBase)this, 12, 0);
        this.jointRW1_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.jointRW1_1.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.mainbody = new ModelRenderer((ModelBase)this, 0, 0);
        this.mainbody.func_78793_a(28.0f, 23.0f, -20.0f);
        this.mainbody.func_78790_a(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.mainbody, -0.25132743f, 0.0f, 0.0f);
        this.dec = new ModelRenderer((ModelBase)this, 74, 0);
        this.dec.func_78793_a(-26.0f, 0.0f, 0.0f);
        this.dec.func_78790_a(-1.0f, -1.0f, -2.5f, 35, 4, 5, 0.0f);
        this.setRotateAngle(this.dec, 0.0f, (float)Math.PI, 0.0f);
        this.w = new ModelRenderer((ModelBase)this, 8, 0);
        this.w.func_78793_a(0.0f, 0.0f, 0.0f);
        this.w.func_78790_a(-25.0f, -4.0f, -4.0f, 25, 8, 8, 0.0f);
        this.setRotateAngle(this.w, 0.29670596f, 0.0f, 0.08726646f);
        this.skin = new ModelRenderer((ModelBase)this, -52, 23);
        this.skin.func_78793_a(0.0f, 0.0f, 2.0f);
        this.skin.func_78790_a(-60.0f, 0.0f, 0.0f, 60, 0, 58, 0.0f);
        this.dec_1 = new ModelRenderer((ModelBase)this, 154, 0);
        this.dec_1.func_78793_a(-19.0f, -1.6f, -4.5f);
        this.dec_1.func_78790_a(-1.5f, -1.0f, -1.0f, 3, 4, 2, 0.0f);
        this.setRotateAngle(this.dec_1, 0.0f, 0.0f, -0.43982297f);
        this.w_1 = new ModelRenderer((ModelBase)this, 74, 9);
        this.w_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.w_1.func_78790_a(-50.0f, -2.0f, -2.0f, 50, 4, 4, 0.0f);
        this.setRotateAngle(this.w_1, 0.0f, 0.0f, 1.0164797f);
        this.w_1.func_78792_a(this.dec_4);
        this.w.func_78792_a(this.jointRW2);
        this.w.func_78792_a(this.dec_2);
        this.jointRW1.func_78792_a(this.jd);
        this.w_1.func_78792_a(this.skin_1);
        this.mainbody.func_78792_a(this.jointRW1);
        this.w.func_78792_a(this.dec_3);
        this.jd.func_78792_a(this.jointRW1_1);
        this.w.func_78792_a(this.dec);
        this.jointRW1_1.func_78792_a(this.w);
        this.w.func_78792_a(this.skin);
        this.w.func_78792_a(this.dec_1);
        this.jointRW2.func_78792_a(this.w_1);
    }

    public void func_78088_a(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.func_78088_a(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.func_78087_a(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        this.mainbody.func_78785_a(scale);
    }
}

