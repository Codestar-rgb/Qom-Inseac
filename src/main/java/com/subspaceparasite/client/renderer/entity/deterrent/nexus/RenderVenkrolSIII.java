/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package com.subspaceparasite.client.renderer.entity.deterrent.nexus;

import com.subspaceparasite.client.model.entity.deterrent.nexus.ModelVenkrolSIII;
import com.subspaceparasite.client.renderer.RenderMalleable;
import com.subspaceparasite.client.renderer.entity.layer.LayerGlowing;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityVenkrolSIII;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderVenkrolSIII
extends RenderMalleable<EntityVenkrolSIII> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("subspaceparasite:textures/entity/monster/venkrolsiii.png");
    public static final ResourceLocation TEXTURE_FROZEN = new ResourceLocation("subspaceparasite:textures/entity/monster/snowvariants/test.png");

    public RenderVenkrolSIII(RenderManager manager) {
        super(manager, new ModelVenkrolSIII(), 0.6f);
        this.func_177094_a(new LayerGlowing<EntityVenkrolSIII>(this));
    }

    protected ResourceLocation getEntityTexture(EntityVenkrolSIII entity) {
        switch (entity.getSkin()) {
            case 120: {
                return TEXTURE_FROZEN;
            }
        }
        return TEXTURES;
    }

    protected void applyRotations(EntityVenkrolSIII entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.func_77043_a((EntityLivingBase)entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
}

