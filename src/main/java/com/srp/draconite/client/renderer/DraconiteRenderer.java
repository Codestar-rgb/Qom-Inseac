package com.srp.draconite.client.renderer;

import com.srp.draconite.client.model.DraconiteModel;
import com.srp.draconite.entity.EntityDraconite;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.GeoRenderer;

public class DraconiteRenderer
extends GeoEntityRenderer<EntityDraconite> {
    public DraconiteRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DraconiteModel());
        this.shadowRadius = 1.5f;
        this.addRenderLayer(new DraconiteEmissiveLayer(this));
        this.addRenderLayer(new DraconiteShadowLayer(this));
    }

    public ResourceLocation getTextureLocation(EntityDraconite animatable) {
        return new ResourceLocation("srpdraconite", "textures/entity/draconite.png");
    }

    public void render(EntityDraconite entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        if (entity.isShadow() && entity.getShakeTimer() > 0) {
            float shakeIntensity = (float)entity.getShakeTimer() / 15.0f;
            float shakeX = (entity.getRandom().nextFloat() - 0.5f) * 0.15f * shakeIntensity;
            float shakeZ = (entity.getRandom().nextFloat() - 0.5f) * 0.15f * shakeIntensity;
            poseStack.translate(shakeX, 0.0f, shakeZ);
        }
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    public float getMotionAnimThreshold(EntityDraconite animatable) {
        return 0.01f;
    }
}

