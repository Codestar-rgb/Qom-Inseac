/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.util.math.MathHelper
 */
package com.subspaceparasite.client.model;

import com.subspaceparasite.client.model.ModelSRP;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.MathHelper;

public abstract class ModelEffect
extends ModelSRP {
    public void renderTwo(ModelRenderer in, float ageInTicks, float f5, float width, float height, double n1, float n2, double n3, double n4) {
        double f1 = n1 + (double)MathHelper.func_76126_a((float)(ageInTicks * n2)) * n3 + n4;
        GlStateManager.func_179094_E();
        GlStateManager.func_179109_b((float)in.field_82906_o, (float)in.field_82908_p, (float)in.field_82907_q);
        GlStateManager.func_179109_b((float)(in.field_78800_c * f5), (float)(in.field_78797_d * f5), (float)(in.field_78798_e * f5));
        GlStateManager.func_179139_a((double)(f1 + (double)width), (double)(f1 + (double)height), (double)(f1 + (double)width));
        GlStateManager.func_179109_b((float)(-in.field_82906_o), (float)(-in.field_82908_p), (float)(-in.field_82907_q));
        GlStateManager.func_179109_b((float)(-in.field_78800_c * f5), (float)(-in.field_78797_d * f5), (float)(-in.field_78798_e * f5));
        in.func_78785_a(f5);
        GlStateManager.func_179121_F();
    }
}

