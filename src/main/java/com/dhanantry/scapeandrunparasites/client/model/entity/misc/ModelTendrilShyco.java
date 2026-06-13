/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.dhanantry.scapeandrunparasites.client.model.entity.misc;

import com.dhanantry.scapeandrunparasites.client.model.ModelSRP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelTendrilShyco
extends ModelSRP {
    public ModelRenderer mainbody;
    public ModelRenderer taclejointL1;
    public ModelRenderer tentacle;
    public ModelRenderer taclejointL2;
    public ModelRenderer dec;
    public ModelRenderer tentacle_1;
    public ModelRenderer taclejointL3;
    public ModelRenderer tentacle_2;
    public ModelRenderer taclejointL4;
    public ModelRenderer tentacle_3;

    public ModelTendrilShyco() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.tentacle = new ModelRenderer((ModelBase)this, 4, 0);
        this.tentacle.func_78793_a(0.0f, 0.0f, 0.0f);
        this.tentacle.func_78790_a(-1.0f, -2.0f, -2.0f, 8, 4, 4, 0.0f);
        this.tentacle_2 = new ModelRenderer((ModelBase)this, 35, 0);
        this.tentacle_2.func_78793_a(0.0f, 0.0f, 1.0f);
        this.tentacle_2.func_78790_a(-1.0f, -1.0f, 0.0f, 2, 2, 11, 0.0f);
        this.setRotateAngle(this.tentacle_2, 0.0f, 1.012291f, 0.0f);
        this.tentacle_3 = new ModelRenderer((ModelBase)this, 17, 10);
        this.tentacle_3.func_78793_a(0.0f, 0.0f, 1.0f);
        this.tentacle_3.func_78790_a(-0.5f, -0.5f, -1.0f, 1, 1, 13, 0.0f);
        this.setRotateAngle(this.tentacle_3, 0.0f, 0.9250245f, 0.0f);
        this.taclejointL1 = new ModelRenderer((ModelBase)this, 4, 0);
        this.taclejointL1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.taclejointL1.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.tentacle_1 = new ModelRenderer((ModelBase)this, 0, 8);
        this.tentacle_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.tentacle_1.func_78790_a(-1.5f, -1.5f, -1.0f, 3, 3, 12, 0.0f);
        this.setRotateAngle(this.tentacle_1, 0.0f, 2.0420353f, 0.0f);
        this.mainbody = new ModelRenderer((ModelBase)this, 0, 0);
        this.mainbody.func_78793_a(0.0f, 22.2f, 6.0f);
        this.mainbody.func_78790_a(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.mainbody, 0.0f, 1.5707964f, 1.5707964f);
        this.taclejointL3 = new ModelRenderer((ModelBase)this, 42, 0);
        this.taclejointL3.func_78793_a(0.0f, 0.0f, 9.0f);
        this.taclejointL3.func_78790_a(-0.5f, -0.5f, 0.0f, 1, 1, 1, 0.0f);
        this.dec = new ModelRenderer((ModelBase)this, 28, 0);
        this.dec.func_78793_a(-0.3f, 0.0f, 0.0f);
        this.dec.func_78790_a(-2.5f, -2.5f, -1.0f, 5, 5, 4, 0.0f);
        this.taclejointL4 = new ModelRenderer((ModelBase)this, 50, 0);
        this.taclejointL4.func_78793_a(0.0f, 0.0f, 9.0f);
        this.taclejointL4.func_78790_a(-0.5f, -0.5f, 0.0f, 1, 1, 1, 0.0f);
        this.taclejointL2 = new ModelRenderer((ModelBase)this, 24, 0);
        this.taclejointL2.func_78793_a(6.0f, 0.0f, 0.0f);
        this.taclejointL2.func_78790_a(-1.0f, -1.0f, 0.0f, 2, 2, 1, 0.0f);
        this.taclejointL1.func_78792_a(this.tentacle);
        this.taclejointL3.func_78792_a(this.tentacle_2);
        this.taclejointL4.func_78792_a(this.tentacle_3);
        this.mainbody.func_78792_a(this.taclejointL1);
        this.taclejointL2.func_78792_a(this.tentacle_1);
        this.tentacle_1.func_78792_a(this.taclejointL3);
        this.tentacle.func_78792_a(this.dec);
        this.tentacle_2.func_78792_a(this.taclejointL4);
        this.tentacle.func_78792_a(this.taclejointL2);
    }

    public void func_78088_a(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.func_78088_a(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.func_78087_a(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        this.mainbody.func_78785_a(scale);
    }

    public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        float f1 = MathHelper.func_76126_a((float)(ageInTicks * 0.06f + 0.0f)) * 0.2f;
        float f2 = MathHelper.func_76126_a((float)(ageInTicks * 0.07f + 8.0f)) * 0.3f;
        float f3 = MathHelper.func_76126_a((float)(ageInTicks * 0.06f + 4.0f)) * 0.4f;
        this.taclejointL2.field_78796_g = f1;
        this.taclejointL3.field_78796_g = f2;
        this.taclejointL4.field_78796_g = f3;
    }
}

