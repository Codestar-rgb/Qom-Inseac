/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package com.dhanantry.scapeandrunparasites.client.model.entity.misc;

import com.dhanantry.scapeandrunparasites.client.model.ModelSRP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTendrilDragonELW
extends ModelSRP {
    public ModelRenderer mainbody;
    public ModelRenderer jointLW1;
    public ModelRenderer jd;
    public ModelRenderer jointLW1_1;
    public ModelRenderer w;
    public ModelRenderer skin;
    public ModelRenderer jointLW2;
    public ModelRenderer dec;
    public ModelRenderer dec_1;
    public ModelRenderer dec_2;
    public ModelRenderer w_1;
    public ModelRenderer skin_1;
    public ModelRenderer dec_3;

    public ModelTendrilDragonELW() {
        this.field_78090_t = 200;
        this.field_78089_u = 150;
        this.jointLW1 = new ModelRenderer((ModelBase)this, 4, 0);
        this.jointLW1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.jointLW1.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.w_1 = new ModelRenderer((ModelBase)this, 160, 6);
        this.w_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.w_1.func_78790_a(-15.0f, -2.0f, -2.0f, 15, 4, 4, 0.0f);
        this.setRotateAngle(this.w_1, 0.0f, 0.0f, -0.9536479f);
        this.skin = new ModelRenderer((ModelBase)this, -49, 24);
        this.skin.func_78793_a(0.0f, 0.0f, 2.0f);
        this.skin.func_78790_a(-60.0f, 0.0f, 0.0f, 60, 0, 58, 0.0f);
        this.w = new ModelRenderer((ModelBase)this, 8, 0);
        this.w.func_78793_a(0.0f, 0.0f, 0.0f);
        this.w.func_78790_a(-38.0f, -4.0f, -4.0f, 38, 8, 8, 0.0f);
        this.setRotateAngle(this.w, -0.29670596f, 0.0f, -0.08726646f);
        this.mainbody = new ModelRenderer((ModelBase)this, 0, 0);
        this.mainbody.func_78793_a(-23.0f, 22.0f, -22.0f);
        this.mainbody.func_78790_a(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.mainbody, -0.25132743f, 0.0f, 0.0f);
        this.jointLW2 = new ModelRenderer((ModelBase)this, 92, 0);
        this.jointLW2.func_78793_a(-60.0f, 0.0f, 0.0f);
        this.jointLW2.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.jd = new ModelRenderer((ModelBase)this, 8, 0);
        this.jd.func_78793_a(0.0f, 0.0f, 0.0f);
        this.jd.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.jd, 0.0f, 0.24993114f, (float)Math.PI);
        this.skin_1 = new ModelRenderer((ModelBase)this, 16, 85);
        this.skin_1.func_78793_a(-56.0f, 0.0f, 0.0f);
        this.skin_1.func_78790_a(-56.0f, 0.0f, 2.0f, 60, 0, 60, 0.0f);
        this.setRotateAngle(this.skin_1, 0.0f, 0.0f, (float)(-Math.PI));
        this.dec_1 = new ModelRenderer((ModelBase)this, 154, 0);
        this.dec_1.func_78793_a(-6.0f, -4.6f, -0.5f);
        this.dec_1.func_78790_a(-1.5f, -1.0f, -1.0f, 3, 5, 2, 0.0f);
        this.setRotateAngle(this.dec_1, 1.5707964f, -1.8221238f, 0.0f);
        this.dec_2 = new ModelRenderer((ModelBase)this, 164, 0);
        this.dec_2.func_78793_a(-4.0f, -0.6f, -4.5f);
        this.dec_2.func_78790_a(-1.5f, -1.0f, -1.0f, 3, 4, 2, 0.0f);
        this.setRotateAngle(this.dec_2, 0.0f, 0.0f, 0.50265485f);
        this.jointLW1_1 = new ModelRenderer((ModelBase)this, 12, 0);
        this.jointLW1_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.jointLW1_1.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.dec_3 = new ModelRenderer((ModelBase)this, 97, 14);
        this.dec_3.func_78793_a(-16.0f, 0.0f, 0.0f);
        this.dec_3.func_78790_a(-1.0f, -1.0f, -2.0f, 47, 2, 3, 0.0f);
        this.setRotateAngle(this.dec_3, 0.0f, (float)Math.PI, 0.0f);
        this.dec = new ModelRenderer((ModelBase)this, 100, 0);
        this.dec.func_78793_a(-39.0f, 0.0f, 0.0f);
        this.dec.func_78790_a(-1.0f, -1.0f, -2.5f, 22, 4, 5, 0.0f);
        this.setRotateAngle(this.dec, 0.0f, (float)Math.PI, 0.0f);
        this.mainbody.func_78792_a(this.jointLW1);
        this.jointLW2.func_78792_a(this.w_1);
        this.w.func_78792_a(this.skin);
        this.jointLW1_1.func_78792_a(this.w);
        this.w.func_78792_a(this.jointLW2);
        this.jointLW1.func_78792_a(this.jd);
        this.w_1.func_78792_a(this.skin_1);
        this.w.func_78792_a(this.dec_1);
        this.w.func_78792_a(this.dec_2);
        this.jd.func_78792_a(this.jointLW1_1);
        this.w_1.func_78792_a(this.dec_3);
        this.w.func_78792_a(this.dec);
    }

    public void func_78088_a(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.func_78088_a(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.func_78087_a(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        this.mainbody.func_78785_a(scale);
    }
}

