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

public class ModelTendrilNogla
extends ModelSRP {
    public ModelRenderer mainbody;
    public ModelRenderer taclejointL;
    public ModelRenderer tentacle;
    public ModelRenderer taclejointL1;
    public ModelRenderer tentacle_1;
    public ModelRenderer taclejointL2;
    public ModelRenderer tentacle_2;
    public ModelRenderer taclejointL3;
    public ModelRenderer tentacle_3;

    public ModelTendrilNogla() {
        this.field_78090_t = 80;
        this.field_78089_u = 32;
        this.taclejointL2 = new ModelRenderer((ModelBase)this, 32, 0);
        this.taclejointL2.func_78793_a(0.0f, -0.2f, 9.0f);
        this.taclejointL2.func_78790_a(-1.0f, -1.0f, 0.0f, 2, 2, 1, 0.0f);
        this.tentacle_1 = new ModelRenderer((ModelBase)this, 38, 0);
        this.tentacle_1.func_78793_a(0.0f, 0.0f, 1.0f);
        this.tentacle_1.func_78790_a(-2.0f, -2.0f, 0.0f, 4, 4, 11, 0.0f);
        this.setRotateAngle(this.tentacle_1, -0.61086524f, 0.0f, 0.0f);
        this.tentacle_3 = new ModelRenderer((ModelBase)this, 42, 16);
        this.tentacle_3.func_78793_a(0.0f, 0.0f, 1.0f);
        this.tentacle_3.func_78790_a(-1.0f, -1.0f, 0.0f, 2, 2, 13, 0.0f);
        this.setRotateAngle(this.tentacle_3, -0.7853982f, 0.0f, 0.0f);
        this.taclejointL1 = new ModelRenderer((ModelBase)this, 24, 0);
        this.taclejointL1.func_78793_a(0.0f, -0.2f, 11.0f);
        this.taclejointL1.func_78790_a(-1.5f, -1.5f, 0.0f, 3, 3, 1, 0.0f);
        this.mainbody = new ModelRenderer((ModelBase)this, 0, 0);
        this.mainbody.func_78793_a(0.0f, 21.5f, 6.0f);
        this.mainbody.func_78790_a(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f);
        this.tentacle_2 = new ModelRenderer((ModelBase)this, 27, 15);
        this.tentacle_2.func_78793_a(0.0f, 0.0f, 1.0f);
        this.tentacle_2.func_78790_a(-1.5f, -1.5f, 0.0f, 3, 3, 11, 0.0f);
        this.setRotateAngle(this.tentacle_2, -0.8552113f, 0.0f, 0.0f);
        this.taclejointL3 = new ModelRenderer((ModelBase)this, 38, 0);
        this.taclejointL3.func_78793_a(0.0f, -0.2f, 9.0f);
        this.taclejointL3.func_78790_a(-0.5f, -0.5f, 0.0f, 1, 1, 1, 0.0f);
        this.taclejointL = new ModelRenderer((ModelBase)this, 4, 0);
        this.taclejointL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.taclejointL.func_78790_a(-2.0f, -2.0f, 0.0f, 4, 4, 1, 0.0f);
        this.setRotateAngle(this.taclejointL, 0.0f, (float)Math.PI, (float)Math.PI);
        this.tentacle = new ModelRenderer((ModelBase)this, 0, 0);
        this.tentacle.func_78793_a(0.0f, 0.0f, 0.0f);
        this.tentacle.func_78790_a(-2.5f, -2.5f, -1.0f, 5, 5, 14, 0.0f);
        this.tentacle_1.func_78792_a(this.taclejointL2);
        this.taclejointL1.func_78792_a(this.tentacle_1);
        this.taclejointL3.func_78792_a(this.tentacle_3);
        this.tentacle.func_78792_a(this.taclejointL1);
        this.taclejointL2.func_78792_a(this.tentacle_2);
        this.tentacle_2.func_78792_a(this.taclejointL3);
        this.mainbody.func_78792_a(this.taclejointL);
        this.taclejointL.func_78792_a(this.tentacle);
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
        this.taclejointL1.field_78795_f = f1;
        this.taclejointL2.field_78795_f = f2;
        this.taclejointL3.field_78795_f = f3;
    }
}

