/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.Entity
 */
package com.dhanantry.scapeandrunparasites.client.model.entity.misc;

import com.dhanantry.scapeandrunparasites.client.model.ModelSRP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelNade
extends ModelSRP {
    public ModelRenderer mainbody;

    public ModelNade() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.mainbody = new ModelRenderer((ModelBase)this, 0, 0);
        this.mainbody.func_78793_a(0.0f, 20.0f, 0.0f);
        this.mainbody.func_78790_a(-8.0f, -12.0f, -8.0f, 16, 16, 16, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        double scale = 0.5;
        GlStateManager.func_179094_E();
        GlStateManager.func_179109_b((float)this.mainbody.field_82906_o, (float)this.mainbody.field_82908_p, (float)this.mainbody.field_82907_q);
        GlStateManager.func_179109_b((float)(this.mainbody.field_78800_c * f5), (float)(this.mainbody.field_78797_d * f5), (float)(this.mainbody.field_78798_e * f5));
        GlStateManager.func_179139_a((double)scale, (double)scale, (double)scale);
        GlStateManager.func_179109_b((float)(-this.mainbody.field_82906_o), (float)(-this.mainbody.field_82908_p), (float)(-this.mainbody.field_82907_q));
        GlStateManager.func_179109_b((float)(-this.mainbody.field_78800_c * f5), (float)(-this.mainbody.field_78797_d * f5), (float)(-this.mainbody.field_78798_e * f5));
        this.mainbody.func_78785_a(f5);
        GlStateManager.func_179121_F();
    }

    public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        this.mainbody.field_82908_p = 0.14f;
    }
}

