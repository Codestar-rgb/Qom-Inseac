/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.entity.RenderLivingBase
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.entity.layers.LayerRenderer
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.MathHelper
 */
package com.subspaceparasite.client.renderer.entity.infected;

import com.subspaceparasite.client.model.entity.infected.ModelInfPlayer;
import com.subspaceparasite.client.renderer.RenderSP;
import com.subspaceparasite.client.renderer.SPLayerBipedArmor;
import com.subspaceparasite.entity.monster.infected.EntityInfPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderInfPlayer
extends RenderSP<EntityInfPlayer> {
    public static final ResourceLocation TEXTURE = new ResourceLocation("subspaceparasite:textures/entity/monster/infplayer.png");

    public RenderInfPlayer(RenderManager manager) {
        super(manager, new ModelInfPlayer(), 0.5f);
        this.func_177094_a((LayerRenderer)new SPLayerBipedArmor((RenderLivingBase<?>)this));
    }

    protected void preRenderCallback(EntityInfPlayer entitylivingbaseIn, float partialTickTime) {
        float f = entitylivingbaseIn.getSelfeFlashIntensity(partialTickTime);
        float ff = entitylivingbaseIn.getSelfeFlashIntensity2();
        float f1 = 1.0f + MathHelper.func_76126_a((float)(f * 100.0f)) * f * 0.01f;
        f = MathHelper.func_76131_a((float)f, (float)0.0f, (float)1.0f);
        f *= f;
        f *= f;
        float f2 = (1.0f + f * 0.4f) * f1;
        float f3 = (1.0f + f * 0.1f) / f1;
        GlStateManager.func_179152_a((float)f2, (float)(ff * f3), (float)f2);
    }

    protected ResourceLocation getEntityTexture(EntityInfPlayer entity) {
        return TEXTURE;
    }

    protected void applyRotations(EntityInfPlayer entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.func_77043_a((EntityLivingBase)entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
}

