package com.srp.draconite.client.renderer;

import com.srp.draconite.entity.EntityOrbScary;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class OrbScaryRenderer
extends EntityRenderer<EntityOrbScary> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("srpdraconite", "textures/entity/fireball.png");

    public OrbScaryRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.0f;
    }

    public ResourceLocation getTextureLocation(EntityOrbScary entity) {
        return TEXTURE;
    }

    public void render(EntityOrbScary entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        float flashIntensity = entity.getFlashIntensity(partialTicks);
        float scale = 0.5f + flashIntensity * 0.3f;
        double bobOffset = Math.sin((double)entity.tickCount * 0.1 + (double)partialTicks * 0.1) * 0.1;
        poseStack.translate(0.0, bobOffset, 0.0);
        poseStack.scale(scale, scale, scale);
        int brightness = 0xF000F0;
        float r = 0.2f + flashIntensity * 0.3f;
        float g = 0.6f + flashIntensity * 0.2f;
        float b = 0.3f + flashIntensity * 0.1f;
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityTranslucent(TEXTURE));
        this.renderSphere(poseStack, vertexConsumer, brightness, r, g, b, 1.0f);
        poseStack.scale(1.4f, 1.4f, 1.4f);
        VertexConsumer auraConsumer = buffer.getBuffer(RenderType.entityTranslucent(TEXTURE));
        this.renderSphere(poseStack, auraConsumer, brightness, r * 0.8f, g * 0.8f, b * 0.8f, 0.3f);
        poseStack.popPose();
    }

    private void renderSphere(PoseStack poseStack, VertexConsumer consumer, int light, float r, float g, float b, float alpha) {
        PoseStack.Pose pose = poseStack.last();
        float size = 0.5f;
        consumer.vertex(pose.pose(), -size, -size, 0.0f).color(r, g, b, alpha).uv(0.0f, 1.0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(pose.normal(), 0.0f, 0.0f, 1.0f).endVertex();
        consumer.vertex(pose.pose(), size, -size, 0.0f).color(r, g, b, alpha).uv(1.0f, 1.0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(pose.normal(), 0.0f, 0.0f, 1.0f).endVertex();
        consumer.vertex(pose.pose(), size, size, 0.0f).color(r, g, b, alpha).uv(1.0f, 0.0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(pose.normal(), 0.0f, 0.0f, 1.0f).endVertex();
        consumer.vertex(pose.pose(), -size, size, 0.0f).color(r, g, b, alpha).uv(0.0f, 0.0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(pose.normal(), 0.0f, 0.0f, 1.0f).endVertex();
    }

    protected int getBlockLightLevel(EntityOrbScary entity, BlockPos pos) {
        return 15;
    }
}

