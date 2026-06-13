/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderLivingBase
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package com.dhanantry.scapeandrunparasites.client.renderer.entity.deterrent.nexus;

import com.dhanantry.scapeandrunparasites.client.model.entity.deterrent.nexus.ModelVenkrolSIV;
import com.dhanantry.scapeandrunparasites.client.renderer.LayerVenkrolTornado;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderMalleable;
import com.dhanantry.scapeandrunparasites.client.renderer.entity.layer.LayerGlowing;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityVenkrolSIV;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderVenkrolSIV
extends RenderMalleable<EntityVenkrolSIV> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("srparasites:textures/entity/monster/venkrolsiv.png");
    public static final ResourceLocation TEXTURE_FROZEN = new ResourceLocation("srparasites:textures/entity/monster/snowvariants/test.png");

    public RenderVenkrolSIV(RenderManager manager) {
        super(manager, new ModelVenkrolSIV(), 0.4f);
        this.func_177094_a(new LayerGlowing<EntityVenkrolSIV>(this));
        this.func_177094_a(new LayerVenkrolTornado((RenderLivingBase<EntityVenkrolSIV>)this));
    }

    protected ResourceLocation getEntityTexture(EntityVenkrolSIV entity) {
        switch (entity.getSkin()) {
            case 120: {
                return TEXTURE_FROZEN;
            }
        }
        return TEXTURES;
    }

    protected void applyRotations(EntityVenkrolSIV entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.func_77043_a((EntityLivingBase)entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
}

