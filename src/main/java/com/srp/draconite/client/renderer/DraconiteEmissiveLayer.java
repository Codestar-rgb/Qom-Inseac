package com.srp.draconite.client.renderer;

import com.srp.draconite.entity.EntityDraconite;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class DraconiteEmissiveLayer
extends GeoRenderLayer<EntityDraconite> {
    private static final ResourceLocation EMISSIVE_TEXTURE = new ResourceLocation("srpdraconite", "textures/entity/draconite_emissive.png");

    public DraconiteEmissiveLayer(GeoRenderer<EntityDraconite> entityRenderer) {
        super(entityRenderer);
    }

    public void render(PoseStack poseStack, EntityDraconite animatable, BakedGeoModel model, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType emissiveType = RenderType.eyes(EMISSIVE_TEXTURE);
        VertexConsumer emissiveBuffer = bufferSource.getBuffer(emissiveType);
        float intensity = 1.0f;
        if (animatable.isAttacking()) {
            intensity = 0.7f + 0.3f * (float)Math.sin((float)animatable.tickCount * 0.5f);
        } else if (animatable.isFlying()) {
            intensity = 0.85f + 0.15f * (float)Math.sin((float)animatable.tickCount * 0.15f);
        }
        if (animatable.isShadow()) {
            intensity *= 0.15f;
        } else if (animatable.isClone()) {
            intensity *= 0.7f;
        }
        int emissiveLight = 0xF000F0;
        if (animatable.isShadow()) {
            emissiveLight = 5;
        }
        this.getRenderer().reRender(model, poseStack, bufferSource, animatable, emissiveType, emissiveBuffer, partialTick, emissiveLight, packedOverlay, intensity, intensity, intensity, intensity > 0.01f ? 1.0f : 0.0f);
        int adaptationVisual = animatable.getAdaptationVisual();
        if (adaptationVisual > 0) {
            float b;
            float g;
            float r;
            float adaptationIntensity = animatable.getAdaptationVisualIntensity();
            float pulseAlpha = adaptationIntensity * (0.6f + 0.4f * (float)Math.sin((float)animatable.tickCount * 0.3f));
            RenderType adaptationRenderType = RenderType.entityTranslucent(EMISSIVE_TEXTURE);
            VertexConsumer adaptationBuffer = bufferSource.getBuffer(adaptationRenderType);
            if (adaptationVisual == 2) {
                r = 0.6f;
                g = 0.1f;
                b = 0.8f;
            } else {
                r = 0.1f;
                g = 0.8f;
                b = 0.2f;
            }
            this.getRenderer().reRender(model, poseStack, bufferSource, animatable, adaptationRenderType, adaptationBuffer, partialTick, 0xF000F0, packedOverlay, r, g, b, pulseAlpha);
        }
    }
}

