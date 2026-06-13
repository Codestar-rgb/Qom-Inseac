/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.MathHelper
 */
package com.dhanantry.scapeandrunparasites.client.renderer.entity;

import com.dhanantry.scapeandrunparasites.client.model.entity.ModelProjectileHomming;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityProjectileHomming;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderProjectileHomming
extends Render<EntityProjectileHomming> {
    private static final ResourceLocation EVOKER_ILLAGER_FANGS = new ResourceLocation("srparasites:textures/entity/projectile/projectileh.png");
    private final ModelProjectileHomming model = new ModelProjectileHomming();

    public RenderProjectileHomming(RenderManager p_i47208_1_) {
        super(p_i47208_1_);
    }

    public void doRender(EntityProjectileHomming entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.func_179094_E();
        float f = this.rotLerp(entity.field_70126_B, entity.field_70177_z, partialTicks);
        float f1 = entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * partialTicks;
        float f2 = (float)entity.field_70173_aa + partialTicks;
        GlStateManager.func_179109_b((float)((float)x), (float)((float)y + 0.15f), (float)((float)z));
        GlStateManager.func_179114_b((float)(MathHelper.func_76126_a((float)(f2 * 0.1f)) * 180.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)(MathHelper.func_76134_b((float)(f2 * 0.1f)) * 180.0f), (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)(MathHelper.func_76126_a((float)(f2 * 0.15f)) * 360.0f), (float)0.0f, (float)0.0f, (float)1.0f);
        float f3 = 0.03125f;
        GlStateManager.func_179091_B();
        GlStateManager.func_179152_a((float)-1.0f, (float)-1.0f, (float)1.0f);
        this.func_180548_c((Entity)entity);
        this.model.func_78088_a((Entity)entity, 0.0f, 0.0f, 0.0f, f, f1, 0.03125f);
        GlStateManager.func_179147_l();
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)0.5f);
        GlStateManager.func_179152_a((float)1.5f, (float)1.5f, (float)1.5f);
        this.model.func_78088_a((Entity)entity, 0.0f, 0.0f, 0.0f, f, f1, 0.03125f);
        GlStateManager.func_179084_k();
        GlStateManager.func_179121_F();
        super.func_76986_a((Entity)entity, x, y, z, entityYaw, partialTicks);
    }

    protected ResourceLocation getEntityTexture(EntityProjectileHomming entity) {
        return EVOKER_ILLAGER_FANGS;
    }

    private float rotLerp(float p_188347_1_, float p_188347_2_, float p_188347_3_) {
        float f;
        for (f = p_188347_2_ - p_188347_1_; f < -180.0f; f += 360.0f) {
        }
        while (f >= 180.0f) {
            f -= 360.0f;
        }
        return p_188347_1_ + p_188347_3_ * f;
    }
}

