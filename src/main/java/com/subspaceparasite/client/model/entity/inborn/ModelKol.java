/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package com.subspaceparasite.client.model.entity.inborn;

import com.subspaceparasite.client.model.ModelSP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelKol
extends ModelSP {
    public ModelRenderer mainbody;
    public ModelRenderer head;
    public ModelRenderer tail;
    public ModelRenderer middleleftleg;
    public ModelRenderer backleftleg;
    public ModelRenderer frontleftleg;
    public ModelRenderer backrightleg;
    public ModelRenderer middlerightleg;
    public ModelRenderer frontrightleg;
    public ModelRenderer bottom;
    public ModelRenderer rbody_joint;
    public ModelRenderer lbody_joint;
    public ModelRenderer leg_1;
    public ModelRenderer leg_1_1;
    public ModelRenderer leg_1_2;
    public ModelRenderer leg_1_3;
    public ModelRenderer leg_1_4;
    public ModelRenderer leg_1_5;
    public ModelRenderer frontleftarm;
    public ModelRenderer frontrightarm;
    public ModelRenderer leg_1_6;
    public ModelRenderer leg_1_7;
    public ModelRenderer rbody;
    public ModelRenderer lbody;

    public ModelKol() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.tail = new ModelRenderer((ModelBase)this, 0, 7);
        this.tail.func_78793_a(0.0f, 0.0f, 3.6f);
        this.tail.func_78790_a(-1.5f, -1.5f, -4.0f, 3, 3, 4, 0.0f);
        this.setRotateAngle(this.tail, -0.2268928f, 0.0f, 0.0f);
        this.leg_1_5 = new ModelRenderer((ModelBase)this, 24, 7);
        this.leg_1_5.func_78793_a(-0.3f, 1.5f, 0.0f);
        this.leg_1_5.func_78790_a(-0.5f, 0.0f, -0.5f, 1, 3, 1, 0.0f);
        this.setRotateAngle(this.leg_1_5, 0.0f, 0.0f, -0.62831855f);
        this.mainbody = new ModelRenderer((ModelBase)this, 0, 0);
        this.mainbody.func_78793_a(0.0f, 18.9f, 1.7f);
        this.mainbody.func_78790_a(-1.0f, -1.0f, -5.0f, 2, 2, 5, 0.0f);
        this.middleleftleg = new ModelRenderer((ModelBase)this, 0, 0);
        this.middleleftleg.func_78793_a(0.9f, 1.1f, -2.6f);
        this.middleleftleg.func_78790_a(-0.5f, -1.0f, -0.5f, 1, 3, 1, 0.0f);
        this.setRotateAngle(this.middleleftleg, 0.0f, 0.0f, -0.5235988f);
        this.leg_1_6 = new ModelRenderer((ModelBase)this, 42, 7);
        this.leg_1_6.func_78793_a(2.6f, -1.1f, 0.9f);
        this.leg_1_6.func_78790_a(-0.5f, 0.0f, -1.0f, 1, 3, 2, 0.0f);
        this.setRotateAngle(this.leg_1_6, -0.62831855f, 0.0f, -0.57595867f);
        this.frontrightarm = new ModelRenderer((ModelBase)this, 36, 7);
        this.frontrightarm.func_78793_a(-0.9f, -1.5f, 0.7f);
        this.frontrightarm.func_78790_a(0.0f, -0.5f, -0.5f, 3, 1, 1, 0.0f);
        this.setRotateAngle(this.frontrightarm, 0.0f, 2.0943952f, 0.0f);
        this.leg_1_2 = new ModelRenderer((ModelBase)this, 0, 7);
        this.leg_1_2.func_78793_a(0.3f, 1.5f, 0.0f);
        this.leg_1_2.func_78790_a(-0.5f, 0.0f, -0.5f, 1, 3, 1, 0.0f);
        this.setRotateAngle(this.leg_1_2, 0.0f, 0.0f, 0.62831855f);
        this.head = new ModelRenderer((ModelBase)this, 46, 0);
        this.head.func_78793_a(0.0f, -1.6f, -4.1f);
        this.head.func_78790_a(-2.0f, -1.5f, -3.0f, 4, 3, 4, 0.0f);
        this.setRotateAngle(this.head, -0.5235988f, 0.0f, 0.0f);
        this.leg_1_1 = new ModelRenderer((ModelBase)this, 58, 0);
        this.leg_1_1.func_78793_a(0.5f, 1.5f, 0.0f);
        this.leg_1_1.func_78790_a(-0.5f, 0.0f, -0.5f, 1, 3, 1, 0.0f);
        this.setRotateAngle(this.leg_1_1, 0.0f, 0.0f, 0.62831855f);
        this.leg_1_3 = new ModelRenderer((ModelBase)this, 16, 7);
        this.leg_1_3.func_78793_a(-0.5f, 1.5f, 0.0f);
        this.leg_1_3.func_78790_a(-0.5f, 0.0f, -0.5f, 1, 3, 1, 0.0f);
        this.setRotateAngle(this.leg_1_3, 0.0f, 0.0f, -0.62831855f);
        this.leg_1_4 = new ModelRenderer((ModelBase)this, 20, 7);
        this.leg_1_4.func_78793_a(-0.5f, 1.5f, 0.0f);
        this.leg_1_4.func_78790_a(-0.5f, 0.0f, -0.5f, 1, 3, 1, 0.0f);
        this.setRotateAngle(this.leg_1_4, 0.0f, 0.0f, -0.62831855f);
        this.bottom = new ModelRenderer((ModelBase)this, 10, 7);
        this.bottom.func_78793_a(0.0f, 0.7f, -5.2f);
        this.bottom.func_78790_a(-1.0f, -3.0f, 0.0f, 2, 3, 1, 0.0f);
        this.setRotateAngle(this.bottom, 0.61086524f, 0.0f, 0.0f);
        this.frontrightleg = new ModelRenderer((ModelBase)this, 42, 0);
        this.frontrightleg.func_78793_a(-0.9f, 1.1f, -4.6f);
        this.frontrightleg.func_78790_a(-0.5f, -1.0f, -0.5f, 1, 3, 1, 0.0f);
        this.setRotateAngle(this.frontrightleg, 0.0f, 0.0f, 0.5235988f);
        this.frontleftarm = new ModelRenderer((ModelBase)this, 28, 7);
        this.frontleftarm.func_78793_a(0.9f, -1.5f, 0.7f);
        this.frontleftarm.func_78790_a(0.0f, -0.5f, -0.5f, 3, 1, 1, 0.0f);
        this.setRotateAngle(this.frontleftarm, 0.0f, 1.1170107f, 0.0f);
        this.leg_1_7 = new ModelRenderer((ModelBase)this, 48, 7);
        this.leg_1_7.func_78793_a(2.6f, -1.1f, -0.9f);
        this.leg_1_7.func_78790_a(-0.5f, 0.0f, -1.0f, 1, 3, 2, 0.0f);
        this.setRotateAngle(this.leg_1_7, 0.62831855f, 0.0f, -0.57595867f);
        this.backleftleg = new ModelRenderer((ModelBase)this, 9, 0);
        this.backleftleg.func_78793_a(0.9f, 1.1f, -0.6f);
        this.backleftleg.func_78790_a(-0.5f, -1.0f, -0.5f, 1, 3, 1, 0.0f);
        this.setRotateAngle(this.backleftleg, 0.0f, 0.0f, -0.5235988f);
        this.leg_1 = new ModelRenderer((ModelBase)this, 46, 0);
        this.leg_1.func_78793_a(0.5f, 1.5f, 0.0f);
        this.leg_1.func_78790_a(-0.5f, 0.0f, -0.5f, 1, 3, 1, 0.0f);
        this.setRotateAngle(this.leg_1, 0.0f, 0.0f, 0.62831855f);
        this.frontleftleg = new ModelRenderer((ModelBase)this, 13, 0);
        this.frontleftleg.func_78793_a(0.9f, 1.1f, -4.6f);
        this.frontleftleg.func_78790_a(-0.5f, -1.0f, -0.5f, 1, 3, 1, 0.0f);
        this.setRotateAngle(this.frontleftleg, 0.0f, 0.0f, -0.5235988f);
        this.lbody_joint = new ModelRenderer((ModelBase)this, 0, 0);
        this.lbody_joint.func_78793_a(0.0f, 0.0f, -0.8f);
        this.lbody_joint.func_78790_a(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f);
        this.middlerightleg = new ModelRenderer((ModelBase)this, 30, 0);
        this.middlerightleg.func_78793_a(-0.9f, 1.1f, -2.6f);
        this.middlerightleg.func_78790_a(-0.5f, -1.0f, -0.5f, 1, 3, 1, 0.0f);
        this.setRotateAngle(this.middlerightleg, 0.0f, 0.0f, 0.5235988f);
        this.lbody = new ModelRenderer((ModelBase)this, 30, 0);
        this.lbody.func_78793_a(0.0f, -1.3f, 0.0f);
        this.lbody.func_78790_a(0.0f, -1.5f, -3.0f, 4, 3, 4, 0.0f);
        this.setRotateAngle(this.lbody, 0.0f, 0.0f, 0.15707964f);
        this.backrightleg = new ModelRenderer((ModelBase)this, 26, 0);
        this.backrightleg.func_78793_a(-0.9f, 1.1f, -0.6f);
        this.backrightleg.func_78790_a(-0.5f, -1.0f, -0.5f, 1, 3, 1, 0.0f);
        this.setRotateAngle(this.backrightleg, 0.0f, 0.0f, 0.5235988f);
        this.rbody_joint = new ModelRenderer((ModelBase)this, 0, 0);
        this.rbody_joint.func_78793_a(0.0f, 0.0f, -0.8f);
        this.rbody_joint.func_78790_a(-0.5f, -0.5f, 0.0f, 1, 1, 1, 0.0f);
        this.rbody = new ModelRenderer((ModelBase)this, 14, 0);
        this.rbody.func_78793_a(0.0f, -1.3f, 0.0f);
        this.rbody.func_78790_a(-4.0f, -1.5f, -3.0f, 4, 3, 4, 0.0f);
        this.setRotateAngle(this.rbody, 0.0f, 0.0f, -0.15707964f);
        this.mainbody.func_78792_a(this.tail);
        this.frontrightleg.func_78792_a(this.leg_1_5);
        this.mainbody.func_78792_a(this.middleleftleg);
        this.frontleftarm.func_78792_a(this.leg_1_6);
        this.bottom.func_78792_a(this.frontrightarm);
        this.frontleftleg.func_78792_a(this.leg_1_2);
        this.mainbody.func_78792_a(this.head);
        this.backleftleg.func_78792_a(this.leg_1_1);
        this.backrightleg.func_78792_a(this.leg_1_3);
        this.middlerightleg.func_78792_a(this.leg_1_4);
        this.mainbody.func_78792_a(this.bottom);
        this.mainbody.func_78792_a(this.frontrightleg);
        this.bottom.func_78792_a(this.frontleftarm);
        this.frontrightarm.func_78792_a(this.leg_1_7);
        this.mainbody.func_78792_a(this.backleftleg);
        this.middleleftleg.func_78792_a(this.leg_1);
        this.mainbody.func_78792_a(this.frontleftleg);
        this.mainbody.func_78792_a(this.lbody_joint);
        this.mainbody.func_78792_a(this.middlerightleg);
        this.lbody_joint.func_78792_a(this.lbody);
        this.mainbody.func_78792_a(this.backrightleg);
        this.mainbody.func_78792_a(this.rbody_joint);
        this.rbody_joint.func_78792_a(this.rbody);
    }

    public void func_78088_a(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.func_78088_a(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.mainbody.func_78785_a(scale);
    }

    public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
    }
}

