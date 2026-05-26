package com.srp.draconite.client.renderer;

import com.srp.draconite.entity.EntityDraconiteBodyPart;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class DraconiteBodyPartRenderer
extends EntityRenderer<EntityDraconiteBodyPart> {
    private static final ResourceLocation INVISIBLE_TEXTURE = new ResourceLocation("textures/entity/area_effect_cloud.png");

    public DraconiteBodyPartRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    public ResourceLocation getTextureLocation(EntityDraconiteBodyPart entity) {
        return INVISIBLE_TEXTURE;
    }
}

