/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.subspaceparasite.client.model.entity.inborn;

import com.subspaceparasite.client.model.ModelSP;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelRathol
extends ModelSP {
    public ModelRenderer mainbody;
    public ModelRenderer hip;
    public ModelRenderer body1;
    public ModelRenderer pop1;
    public ModelRenderer pop2;
    public ModelRenderer pop3;
    public ModelRenderer pop4;
    public ModelRenderer pop5;
    public ModelRenderer pop6;
    public ModelRenderer joint1;
    public ModelRenderer joint2;
    public ModelRenderer joint3;
    public ModelRenderer hip2;
    public ModelRenderer frontleg;
    public ModelRenderer leftleg;
    public ModelRenderer rightleg;
    public ModelRenderer pop1_1;
    public ModelRenderer pop2_2;
    public ModelRenderer pop3_3;
    public ModelRenderer up3head;
    public ModelRenderer pop4_4;
    public ModelRenderer up1;
    public ModelRenderer up1_1;
    public ModelRenderer up1arm;
    public ModelRenderer up2;
    public ModelRenderer up2_2;
    public ModelRenderer up2sleg1;
    public ModelRenderer up2sleg2;
    public ModelRenderer up3;
    public ModelRenderer up3_3;

    public ModelRathol() {
        this.field_78090_t = 256;
        this.field_78089_u = 120;
        this.pop1 = new ModelRenderer((ModelBase)this, 55, 0);
        this.pop1.func_78793_a(8.0f, 6.0f, -12.6f);
        this.pop1.func_78790_a(-3.5f, -3.5f, -3.0f, 7, 7, 3, 0.0f);
        this.setRotateAngle(this.pop1, 0.0f, 0.0f, 0.31869712f);
        this.pop4 = new ModelRenderer((ModelBase)this, 224, 0);
        this.pop4.func_78793_a(4.7f, 12.6f, 11.6f);
        this.pop4.func_78790_a(-3.5f, -3.5f, -3.0f, 7, 7, 3, 0.0f);
        this.setRotateAngle(this.pop4, (float)Math.PI, 0.0f, -0.63739425f);
        this.up2 = new ModelRenderer((ModelBase)this, 146, 43);
        this.up2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.up2.func_78790_a(-8.0f, 0.0f, -7.5f, 16, 22, 15, 0.0f);
        this.setRotateAngle(this.up2, -2.321986f, -1.1383038f, 0.18203785f);
        this.pop6 = new ModelRenderer((ModelBase)this, 0, 8);
        this.pop6.func_78793_a(-6.3f, 14.0f, -13.4f);
        this.pop6.func_78790_a(-2.5f, -2.5f, -2.2f, 5, 5, 3, 0.0f);
        this.setRotateAngle(this.pop6, 0.0f, 0.0f, -0.4098033f);
        this.pop3_3 = new ModelRenderer((ModelBase)this, 239, 31);
        this.pop3_3.func_78793_a(0.0f, 0.0f, -2.6f);
        this.pop3_3.func_78790_a(-2.5f, -2.5f, -2.2f, 5, 5, 3, 0.0f);
        this.setRotateAngle(this.pop3_3, 0.0f, 0.0f, -1.1383038f);
        this.up3 = new ModelRenderer((ModelBase)this, 193, 65);
        this.up3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.up3.func_78790_a(-8.0f, 0.0f, -7.5f, 16, 22, 15, 0.0f);
        this.setRotateAngle(this.up3, -2.276433f, (float)Math.PI, -0.4553564f);
        this.up2sleg2 = new ModelRenderer((ModelBase)this, 47, 43);
        this.up2sleg2.func_78793_a(-6.8f, 6.0f, 11.1f);
        this.up2sleg2.func_78790_a(-1.0f, -1.0f, -1.0f, 16, 2, 2, 0.0f);
        this.setRotateAngle(this.up2sleg2, 0.4098033f, 0.0f, 0.4098033f);
        this.pop1_1 = new ModelRenderer((ModelBase)this, 210, 26);
        this.pop1_1.func_78793_a(0.0f, 0.0f, -2.6f);
        this.pop1_1.func_78790_a(-2.5f, -2.5f, -2.2f, 5, 5, 3, 0.0f);
        this.setRotateAngle(this.pop1_1, 0.0f, 0.0f, 0.3642502f);
        this.body1 = new ModelRenderer((ModelBase)this, 110, 0);
        this.body1.func_78793_a(0.0f, 3.0f, 0.0f);
        this.body1.func_78790_a(-12.0f, 0.0f, -13.0f, 24, 17, 26, 0.0f);
        this.joint3 = new ModelRenderer((ModelBase)this, 76, 0);
        this.joint3.func_78793_a(-3.0f, 2.5f, 10.1f);
        this.joint3.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.rightleg = new ModelRenderer((ModelBase)this, 88, 19);
        this.rightleg.func_78793_a(-4.5f, 3.9f, 3.7f);
        this.rightleg.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.up3_3 = new ModelRenderer((ModelBase)this, 124, 80);
        this.up3_3.func_78793_a(0.0f, 3.0f, 0.0f);
        this.up3_3.func_78790_a(-10.0f, 0.0f, -11.0f, 20, 17, 22, 0.0f);
        this.hip = new ModelRenderer((ModelBase)this, 72, 0);
        this.hip.func_78793_a(0.0f, 21.3f, 0.0f);
        this.hip.func_78790_a(-8.5f, 0.0f, -7.5f, 17, 4, 15, 0.0f);
        this.up1 = new ModelRenderer((ModelBase)this, 0, 39);
        this.up1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.up1.func_78790_a(-8.0f, 0.0f, -7.5f, 16, 22, 15, 0.0f);
        this.setRotateAngle(this.up1, -2.5307274f, 0.0f, -0.7853982f);
        this.pop2_2 = new ModelRenderer((ModelBase)this, 226, 26);
        this.pop2_2.func_78793_a(0.0f, 0.0f, -2.6f);
        this.pop2_2.func_78790_a(-2.5f, -2.5f, -2.2f, 5, 5, 3, 0.0f);
        this.setRotateAngle(this.pop2_2, 0.0f, 0.0f, 1.4570009f);
        this.up1arm = new ModelRenderer((ModelBase)this, 238, 39);
        this.up1arm.func_78793_a(-10.0f, 13.7f, 5.7f);
        this.up1arm.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.setRotateAngle(this.up1arm, -2.003289f, -0.10000736f, 0.10000736f);
        this.pop3 = new ModelRenderer((ModelBase)this, 204, 0);
        this.pop3.func_78793_a(11.3f, 16.0f, 9.0f);
        this.pop3.func_78790_a(-3.5f, -3.5f, -3.0f, 7, 7, 3, 0.0f);
        this.setRotateAngle(this.pop3, 0.0f, -1.5707964f, 0.0f);
        this.frontleg = new ModelRenderer((ModelBase)this, 236, 10);
        this.frontleg.field_78809_i = true;
        this.frontleg.func_78793_a(0.0f, 3.9f, -3.7f);
        this.frontleg.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.up2_2 = new ModelRenderer((ModelBase)this, 0, 76);
        this.up2_2.func_78793_a(0.0f, 3.0f, 0.0f);
        this.up2_2.func_78790_a(-10.0f, 0.0f, -11.0f, 20, 17, 22, 0.0f);
        this.pop5 = new ModelRenderer((ModelBase)this, 0, 0);
        this.pop5.func_78793_a(-11.9f, 14.8f, -3.8f);
        this.pop5.func_78790_a(-2.5f, -2.5f, -2.2f, 5, 5, 3, 0.0f);
        this.setRotateAngle(this.pop5, 0.0f, 1.5707964f, 0.0f);
        this.joint2 = new ModelRenderer((ModelBase)this, 72, 0);
        this.joint2.func_78793_a(8.0f, 6.7f, -1.7f);
        this.joint2.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.pop2 = new ModelRenderer((ModelBase)this, 184, 0);
        this.pop2.func_78793_a(-11.3f, 7.2f, 8.2f);
        this.pop2.func_78790_a(-3.5f, -3.5f, -3.1f, 7, 7, 3, 0.0f);
        this.setRotateAngle(this.pop2, 0.0f, 1.5707964f, 0.0f);
        this.pop4_4 = new ModelRenderer((ModelBase)this, 72, 35);
        this.pop4_4.func_78793_a(0.0f, 0.0f, -2.6f);
        this.pop4_4.func_78790_a(-2.5f, -2.5f, -2.2f, 5, 5, 3, 0.0f);
        this.setRotateAngle(this.pop4_4, 0.0f, 0.0f, -0.5462881f);
        this.up3head = new ModelRenderer((ModelBase)this, 210, 34);
        this.up3head.func_78793_a(13.7f, -15.8f, 14.0f);
        this.up3head.func_78790_a(-4.0f, -4.0f, -6.0f, 8, 8, 6, 0.0f);
        this.setRotateAngle(this.up3head, 1.1383038f, -0.63739425f, -0.7285004f);
        this.joint1 = new ModelRenderer((ModelBase)this, 13, 0);
        this.joint1.func_78793_a(-8.2f, 4.6f, -9.1f);
        this.joint1.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.hip2 = new ModelRenderer((ModelBase)this, 184, 10);
        this.hip2.func_78793_a(0.0f, 3.8f, 0.0f);
        this.hip2.func_78790_a(-7.0f, 0.0f, -6.0f, 14, 4, 12, 0.0f);
        this.up1_1 = new ModelRenderer((ModelBase)this, 62, 43);
        this.up1_1.func_78793_a(0.0f, 3.0f, 0.0f);
        this.up1_1.func_78790_a(-10.0f, 0.0f, -11.0f, 20, 17, 22, 0.0f);
        this.mainbody = new ModelRenderer((ModelBase)this, 0, 0);
        this.mainbody.func_78793_a(0.0f, -17.0f, 0.0f);
        this.mainbody.func_78790_a(-9.5f, 0.0f, -8.5f, 19, 22, 17, 0.0f);
        this.leftleg = new ModelRenderer((ModelBase)this, 72, 19);
        this.leftleg.field_78809_i = true;
        this.leftleg.func_78793_a(4.5f, 3.9f, 3.7f);
        this.leftleg.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.up2sleg1 = new ModelRenderer((ModelBase)this, 100, 19);
        this.up2sleg1.func_78793_a(-5.0f, 10.2f, 11.5f);
        this.up2sleg1.func_78790_a(-1.0f, -1.0f, -1.0f, 16, 2, 2, 0.0f);
        this.setRotateAngle(this.up2sleg1, 0.045553092f, 0.091106184f, 0.58119464f);
        this.mainbody.func_78792_a(this.pop1);
        this.mainbody.func_78792_a(this.pop4);
        this.joint2.func_78792_a(this.up2);
        this.mainbody.func_78792_a(this.pop6);
        this.pop3.func_78792_a(this.pop3_3);
        this.joint3.func_78792_a(this.up3);
        this.up2.func_78792_a(this.up2sleg2);
        this.pop1.func_78792_a(this.pop1_1);
        this.mainbody.func_78792_a(this.body1);
        this.mainbody.func_78792_a(this.joint3);
        this.hip2.func_78792_a(this.rightleg);
        this.up3.func_78792_a(this.up3_3);
        this.mainbody.func_78792_a(this.hip);
        this.joint1.func_78792_a(this.up1);
        this.pop2.func_78792_a(this.pop2_2);
        this.up1.func_78792_a(this.up1arm);
        this.mainbody.func_78792_a(this.pop3);
        this.hip2.func_78792_a(this.frontleg);
        this.up2.func_78792_a(this.up2_2);
        this.mainbody.func_78792_a(this.pop5);
        this.mainbody.func_78792_a(this.joint2);
        this.mainbody.func_78792_a(this.pop2);
        this.pop4.func_78792_a(this.pop4_4);
        this.pop3.func_78792_a(this.up3head);
        this.mainbody.func_78792_a(this.joint1);
        this.hip.func_78792_a(this.hip2);
        this.up1.func_78792_a(this.up1_1);
        this.hip2.func_78792_a(this.leftleg);
        this.up2.func_78792_a(this.up2sleg1);
    }

    public void func_78088_a(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float f5) {
        super.func_78088_a(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, f5);
        this.mainbody.func_78785_a(f5);
    }

    public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        float f1 = MathHelper.func_76134_b((float)(limbSwing * 0.5f)) * 1.0f * limbSwingAmount;
        float f2 = MathHelper.func_76134_b((float)(limbSwing * 0.5f + (float)Math.PI)) * 1.0f * limbSwingAmount;
        float age2f = 0.2f * MathHelper.func_76126_a((float)(ageInTicks * 0.3f + 2.0f)) / 4.0f;
        float age4f = 0.2f * MathHelper.func_76126_a((float)(ageInTicks * 0.3f + 4.0f)) / 4.0f;
        float age6f = 0.2f * MathHelper.func_76126_a((float)(ageInTicks * 0.3f + 6.0f)) / 4.0f;
        this.leftleg.field_78795_f = f1;
        this.rightleg.field_78795_f = f1;
        this.frontleg.field_78795_f = f2;
        this.joint1.field_82908_p = age2f;
        this.joint2.field_82908_p = age4f;
        this.joint3.field_82908_p = age6f;
        this.up1_1.field_82908_p = age6f;
        this.up2_2.field_82908_p = age4f;
        this.up3_3.field_82908_p = age2f;
        this.pop1.field_82908_p = age6f / 2.0f;
        this.pop2.field_82908_p = age4f / 2.0f;
        this.pop3.field_82908_p = age6f / 2.0f;
        this.pop4.field_82908_p = age4f / 2.0f;
        this.pop5.field_82908_p = age6f / 2.0f;
        this.pop6.field_82908_p = age4f / 2.0f;
        this.underground((EntityParasiteBase)entityIn, ageInTicks, this.mainbody);
    }
}

