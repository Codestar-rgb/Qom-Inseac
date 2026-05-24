/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package com.subspaceparasite.client.renderer.entity.deterrent.nexus;

import com.subspaceparasite.client.model.entity.deterrent.nexus.ModelVenkrol;
import com.subspaceparasite.client.renderer.RenderMalleable;
import com.subspaceparasite.client.renderer.entity.layer.LayerGlowing;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityVenkrol;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderVenkrol
extends RenderMalleable<EntityVenkrol> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("subspaceparasite:textures/entity/monster/venkrol.png");
    public static final ResourceLocation TEXTURE_FROZEN = new ResourceLocation("subspaceparasite:textures/entity/monster/snowvariants/test.png");

    public RenderVenkrol(RenderManager manager) {
        super(manager, new ModelVenkrol(), 0.4f);
        this.func_177094_a(new LayerGlowing<EntityVenkrol>(this));
    }

    protected ResourceLocation getEntityTexture(EntityVenkrol entity) {
        switch (entity.getSkin()) {
            case 120: {
                return TEXTURE_FROZEN;
            }
        }
        return TEXTURES;
    }

    protected void applyRotations(EntityVenkrol entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.func_77043_a((EntityLivingBase)entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
}

