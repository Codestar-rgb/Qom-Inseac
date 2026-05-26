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

public class DraconiteShadowLayer
extends GeoRenderLayer<EntityDraconite> {
    private static final ResourceLocation SHADOW_TEXTURE = new ResourceLocation("srpdraconite", "textures/entity/heblumc.png");
    private static final float SHADOW_ALPHA = 0.4f;
    private static final float SHADOW_VERTICAL_OFFSET = 0.75f;

    public DraconiteShadowLayer(GeoRenderer<EntityDraconite> entityRenderer) {
        super(entityRenderer);
    }

    public void render(PoseStack poseStack, EntityDraconite animatable, BakedGeoModel model, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        if (!animatable.isShadow()) {
            return;
        }
        float intensity = 0.4f;
        int showC = animatable.getShowC();
        if (showC > 0) {
            intensity = 0.4f + (float)showC / 15.0f * 0.15f;
        } else if (showC < 0) {
            float fade = Math.max(0.0f, 1.0f + (float)showC / 10.0f);
            intensity = 0.4f * fade;
        }
        intensity = Math.min(1.0f, Math.max(0.0f, intensity));
        poseStack.pushPose();
        poseStack.translate(0.0f, 0.75f, 0.0f);
        RenderType shadowRenderType = RenderType.entityTranslucent(SHADOW_TEXTURE);
        VertexConsumer shadowBuffer = bufferSource.getBuffer(shadowRenderType);
        this.getRenderer().reRender(model, poseStack, bufferSource, animatable, shadowRenderType, shadowBuffer, partialTick, 0, packedOverlay, 1.0f, 1.0f, 1.0f, intensity);
        float redFlash = animatable.getShadowDamageR();
        if (redFlash > 0.0f) {
            RenderType redRenderType = RenderType.entityTranslucent(SHADOW_TEXTURE);
            VertexConsumer redBuffer = bufferSource.getBuffer(redRenderType);
            float redAlpha = redFlash * 0.8f;
            this.getRenderer().reRender(model, poseStack, bufferSource, animatable, redRenderType, redBuffer, partialTick, 0xF000F0, packedOverlay, redFlash, 0.0f, 0.0f, redAlpha);
        }
        poseStack.popPose();
    }
}

