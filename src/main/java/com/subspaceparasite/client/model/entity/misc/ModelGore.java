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
import com.subspaceparasite.entity.projectile.EntityGore;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGore
extends ModelSP {
    public ModelRenderer sim;
    public ModelRenderer pri;
    public ModelRenderer ada;
    public ModelRenderer pure;
    public ModelRenderer dec;
    public ModelRenderer dec_1;
    public ModelRenderer dec_2;
    public ModelRenderer dec_3;
    public ModelRenderer dec_4;
    public ModelRenderer dec_5;
    public ModelRenderer dec_6;
    public ModelRenderer dec_7;

    public ModelGore() {
        this.field_78090_t = 128;
        this.field_78089_u = 128;
        this.dec = new ModelRenderer((ModelBase)this, 80, 4);
        this.dec.func_78793_a(0.0f, 0.0f, 0.0f);
        this.dec.func_78790_a(-0.5f, -8.0f, -8.0f, 1, 16, 16, 0.0f);
        this.setRotateAngle(this.dec, 0.7853982f, 0.0f, 0.0f);
        this.dec_1 = new ModelRenderer((ModelBase)this, 48, 20);
        this.dec_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.dec_1.func_78790_a(-0.5f, -8.0f, -8.0f, 1, 16, 16, 0.0f);
        this.setRotateAngle(this.dec_1, 0.7853982f, 1.5707964f, 0.0f);
        this.dec_4 = new ModelRenderer((ModelBase)this, 38, 52);
        this.dec_4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.dec_4.func_78790_a(-0.5f, -10.0f, -10.0f, 1, 20, 20, 0.0f);
        this.setRotateAngle(this.dec_4, 0.7853982f, 1.5707964f, 0.0f);
        this.sim = new ModelRenderer((ModelBase)this, 0, 0);
        this.sim.func_78793_a(0.0f, 21.0f, 0.0f);
        this.sim.func_78790_a(-3.0f, -3.0f, -3.0f, 6, 6, 6, 0.0f);
        this.setRotateAngle(this.sim, 0.0f, -0.7853982f, 0.0f);
        this.dec_6 = new ModelRenderer((ModelBase)this, 58, 72);
        this.dec_6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.dec_6.func_78790_a(-0.5f, -11.0f, -11.0f, 1, 22, 22, 0.0f);
        this.setRotateAngle(this.dec_6, 0.7853982f, 1.5707964f, 0.0f);
        this.dec_3 = new ModelRenderer((ModelBase)this, 0, 40);
        this.dec_3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.dec_3.func_78790_a(-0.5f, -9.0f, -9.0f, 1, 18, 18, 0.0f);
        this.setRotateAngle(this.dec_3, 0.7853982f, 0.0f, 0.0f);
        this.dec_5 = new ModelRenderer((ModelBase)this, 82, 52);
        this.dec_5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.dec_5.func_78790_a(-0.5f, -10.0f, -10.0f, 1, 20, 20, 0.0f);
        this.setRotateAngle(this.dec_5, 0.7853982f, 0.0f, 0.0f);
        this.pure = new ModelRenderer((ModelBase)this, 0, 16);
        this.pure.func_78793_a(0.0f, 18.0f, 0.0f);
        this.pure.func_78790_a(-6.0f, -6.0f, -6.0f, 12, 12, 12, 0.0f);
        this.setRotateAngle(this.pure, 0.0f, -0.7853982f, 0.0f);
        this.dec_2 = new ModelRenderer((ModelBase)this, 64, 36);
        this.dec_2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.dec_2.func_78790_a(-0.5f, -9.0f, -9.0f, 1, 18, 18, 0.0f);
        this.setRotateAngle(this.dec_2, 0.7853982f, 1.5707964f, 0.0f);
        this.pri = new ModelRenderer((ModelBase)this, 24, 0);
        this.pri.func_78793_a(0.0f, 20.0f, 0.0f);
        this.pri.func_78790_a(-4.0f, -4.0f, -4.0f, 8, 8, 8, 0.0f);
        this.setRotateAngle(this.pri, 0.0f, -0.7853982f, 0.0f);
        this.ada = new ModelRenderer((ModelBase)this, 56, 0);
        this.ada.func_78793_a(0.0f, 19.0f, 0.0f);
        this.ada.func_78790_a(-5.0f, -5.0f, -5.0f, 10, 10, 10, 0.0f);
        this.setRotateAngle(this.ada, 0.0f, -0.7853982f, 0.0f);
        this.dec_7 = new ModelRenderer((ModelBase)this, 0, 76);
        this.dec_7.func_78793_a(0.0f, 0.0f, 0.0f);
        this.dec_7.func_78790_a(-0.5f, -11.0f, -11.0f, 1, 22, 22, 0.0f);
        this.setRotateAngle(this.dec_7, 0.7853982f, 0.0f, 0.0f);
        this.sim.func_78792_a(this.dec);
        this.sim.func_78792_a(this.dec_1);
        this.ada.func_78792_a(this.dec_4);
        this.pure.func_78792_a(this.dec_6);
        this.pri.func_78792_a(this.dec_3);
        this.ada.func_78792_a(this.dec_5);
        this.pri.func_78792_a(this.dec_2);
        this.pure.func_78792_a(this.dec_7);
    }

    public void func_78088_a(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float f5) {
        super.func_78088_a(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, f5);
        this.func_78087_a(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, f5, entityIn);
        EntityGore in = (EntityGore)entityIn;
        switch (in.getSkin()) {
            case 1: {
                this.sim.func_78785_a(f5);
                break;
            }
            case 2: {
                this.pri.func_78785_a(f5);
                break;
            }
            case 3: {
                this.ada.func_78785_a(f5);
                break;
            }
            case 4: {
                this.pure.func_78785_a(f5);
                break;
            }
            case 10: {
                this.sim.func_78785_a(f5);
            }
        }
    }

    public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
    }
}

