package com.srp.draconite.client.model;

import com.srp.draconite.entity.EntityDraconite;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DraconiteModel
extends GeoModel<EntityDraconite> {
    private static final ResourceLocation MODEL_RESOURCE = new ResourceLocation("srpdraconite", "geo/draconite.geo.json");
    private static final ResourceLocation TEXTURE_RESOURCE = new ResourceLocation("srpdraconite", "textures/entity/draconite.png");
    private static final ResourceLocation ANIMATION_RESOURCE = new ResourceLocation("srpdraconite", "animations/draconite.animation.json");

    public ResourceLocation getModelResource(EntityDraconite animatable) {
        return MODEL_RESOURCE;
    }

    public ResourceLocation getTextureResource(EntityDraconite animatable) {
        return TEXTURE_RESOURCE;
    }

    public ResourceLocation getAnimationResource(EntityDraconite animatable) {
        return ANIMATION_RESOURCE;
    }
}

