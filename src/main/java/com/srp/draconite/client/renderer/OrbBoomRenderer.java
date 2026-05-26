package com.srp.draconite.client.renderer;

import com.srp.draconite.entity.EntityOrbBoom;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class OrbBoomRenderer
extends EntityRenderer<EntityOrbBoom> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("srpdraconite", "textures/entity/fireball.png");

    public OrbBoomRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.0f;
    }

    public ResourceLocation getTextureLocation(EntityOrbBoom entity) {
        return TEXTURE;
    }

    public void render(EntityOrbBoom entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        float flashIntensity = entity.getFlashIntensity(partialTicks);
        float scale = 0.4f + flashIntensity * 0.2f;
        poseStack.scale(scale, scale, scale);
        int brightness = 0xF000F0;
        float r = 0.9f + flashIntensity * 0.1f;
        float g = 0.4f + flashIntensity * 0.2f;
        float b = 0.1f;
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityTranslucent(TEXTURE));
        this.renderBillboardQuad(poseStack, vertexConsumer, brightness, r, g, b, 1.0f);
        poseStack.scale(1.5f, 1.5f, 1.5f);
        VertexConsumer auraConsumer = buffer.getBuffer(RenderType.entityTranslucent(TEXTURE));
        this.renderBillboardQuad(poseStack, auraConsumer, brightness, r, g * 0.5f, b, 0.3f);
        poseStack.popPose();
    }

    private void renderBillboardQuad(PoseStack poseStack, VertexConsumer consumer, int light, float r, float g, float b, float alpha) {
        PoseStack.Pose pose = poseStack.last();
        float size = 0.5f;
        consumer.vertex(pose.pose(), -size, -size, 0.0f).color(r, g, b, alpha).uv(0.0f, 1.0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(pose.normal(), 0.0f, 0.0f, 1.0f).endVertex();
        consumer.vertex(pose.pose(), size, -size, 0.0f).color(r, g, b, alpha).uv(1.0f, 1.0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(pose.normal(), 0.0f, 0.0f, 1.0f).endVertex();
        consumer.vertex(pose.pose(), size, size, 0.0f).color(r, g, b, alpha).uv(1.0f, 0.0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(pose.normal(), 0.0f, 0.0f, 1.0f).endVertex();
        consumer.vertex(pose.pose(), -size, size, 0.0f).color(r, g, b, alpha).uv(0.0f, 0.0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(pose.normal(), 0.0f, 0.0f, 1.0f).endVertex();
    }

    protected int getBlockLightLevel(EntityOrbBoom entity, BlockPos pos) {
        return 15;
    }
}

