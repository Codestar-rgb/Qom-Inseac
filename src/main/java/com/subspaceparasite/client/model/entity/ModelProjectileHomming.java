/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package com.subspaceparasite.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelProjectileHomming
extends ModelBase {
    public ModelRenderer renderer;

    public ModelProjectileHomming() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.renderer = new ModelRenderer((ModelBase)this);
        this.renderer.func_78784_a(0, 0).func_78790_a(-4.0f, -4.0f, -1.0f, 8, 8, 2, 0.0f);
        this.renderer.func_78784_a(0, 10).func_78790_a(-1.0f, -4.0f, -4.0f, 2, 8, 8, 0.0f);
        this.renderer.func_78784_a(20, 0).func_78790_a(-4.0f, -1.0f, -4.0f, 8, 2, 8, 0.0f);
        this.renderer.func_78793_a(0.0f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.func_78087_a(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        this.renderer.func_78785_a(scale);
    }

    public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        super.func_78087_a(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        this.renderer.field_78796_g = netHeadYaw * ((float)Math.PI / 180);
        this.renderer.field_78795_f = headPitch * ((float)Math.PI / 180);
    }
}

