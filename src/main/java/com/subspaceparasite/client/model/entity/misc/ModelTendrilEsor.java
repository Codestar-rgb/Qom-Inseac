/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.subspaceparasite.client.model.entity.misc;

import com.subspaceparasite.client.model.ModelSRP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelTendrilEsor
extends ModelSRP {
    public ModelRenderer mainbody;
    public ModelRenderer taclejointLA0;
    public ModelRenderer tentacle;
    public ModelRenderer taclejointLA1;
    public ModelRenderer tentacle_1;
    public ModelRenderer taclejointLA2;
    public ModelRenderer tentacle_2;
    public ModelRenderer taclejointLA3;
    public ModelRenderer tentacle_3;

    public ModelTendrilEsor() {
        this.field_78090_t = 55;
        this.field_78089_u = 32;
        this.taclejointLA0 = new ModelRenderer((ModelBase)this, 4, 0);
        this.taclejointLA0.func_78793_a(0.0f, 0.0f, 0.0f);
        this.taclejointLA0.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.taclejointLA1 = new ModelRenderer((ModelBase)this, 14, 0);
        this.taclejointLA1.func_78793_a(0.0f, 0.0f, 6.0f);
        this.taclejointLA1.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.tentacle_1 = new ModelRenderer((ModelBase)this, 14, 0);
        this.tentacle_1.func_78793_a(0.0f, 0.0f, 1.0f);
        this.tentacle_1.func_78790_a(-1.5f, -1.5f, -1.0f, 3, 3, 7, 0.0f);
        this.setRotateAngle(this.tentacle_1, 0.5061455f, 0.0f, 0.0f);
        this.tentacle = new ModelRenderer((ModelBase)this, 0, 0);
        this.tentacle.func_78793_a(0.0f, 0.0f, 0.0f);
        this.tentacle.func_78790_a(-1.0f, -1.0f, -2.0f, 2, 2, 10, 0.0f);
        this.tentacle_3 = new ModelRenderer((ModelBase)this, 0, 12);
        this.tentacle_3.func_78793_a(0.0f, 0.0f, 1.0f);
        this.tentacle_3.func_78790_a(-0.5f, -0.5f, -1.0f, 1, 1, 12, 0.0f);
        this.setRotateAngle(this.tentacle_3, 0.82030475f, 0.0f, 0.0f);
        this.tentacle_2 = new ModelRenderer((ModelBase)this, 22, 0);
        this.tentacle_2.func_78793_a(0.0f, 0.0f, 1.0f);
        this.tentacle_2.func_78790_a(-1.0f, -1.0f, -1.0f, 2, 2, 12, 0.0f);
        this.setRotateAngle(this.tentacle_2, 0.8552113f, 0.0f, 0.0f);
        this.taclejointLA2 = new ModelRenderer((ModelBase)this, 27, 0);
        this.taclejointLA2.func_78793_a(0.0f, 0.0f, 4.0f);
        this.taclejointLA2.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.mainbody = new ModelRenderer((ModelBase)this, 0, 0);
        this.mainbody.func_78793_a(0.0f, 23.0f, 6.0f);
        this.mainbody.func_78790_a(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.mainbody, 0.0f, (float)Math.PI, 0.0f);
        this.taclejointLA3 = new ModelRenderer((ModelBase)this, 38, 0);
        this.taclejointLA3.func_78793_a(0.0f, 0.0f, 9.0f);
        this.taclejointLA3.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.mainbody.func_78792_a(this.taclejointLA0);
        this.tentacle.func_78792_a(this.taclejointLA1);
        this.taclejointLA1.func_78792_a(this.tentacle_1);
        this.taclejointLA0.func_78792_a(this.tentacle);
        this.taclejointLA3.func_78792_a(this.tentacle_3);
        this.taclejointLA2.func_78792_a(this.tentacle_2);
        this.tentacle_1.func_78792_a(this.taclejointLA2);
        this.tentacle_2.func_78792_a(this.taclejointLA3);
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
        this.taclejointLA1.field_78795_f = f1;
        this.taclejointLA2.field_78795_f = f2;
        this.taclejointLA3.field_78795_f = f3;
    }
}

