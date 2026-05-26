package com.srp.draconite.client.renderer;

import com.srp.draconite.entity.EntityToxicCloud;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class ToxicCloudRenderer
extends EntityRenderer<EntityToxicCloud> {
    private static final ResourceLocation FOG_TEXTURE = new ResourceLocation("srpdraconite", "textures/particle/toxic_cloud.png");
    private static final ResourceLocation FOG_INNER_TEXTURE = new ResourceLocation("srpdraconite", "textures/particle/toxic_cloud_inner.png");
    private static final ResourceLocation FOG_OUTER_TEXTURE = new ResourceLocation("srpdraconite", "textures/particle/toxic_cloud_outer.png");

    public ToxicCloudRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    public ResourceLocation getTextureLocation(EntityToxicCloud entity) {
        return FOG_TEXTURE;
    }

    public void render(EntityToxicCloud entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        float entityRadius = entity.getRadius();
        float progress = Math.min(1.0f, (float)entity.tickCount / (float)Math.max(1, entity.getDuration()));
        float radius = entityRadius * (0.8f + 0.4f * progress);
        float alpha = 0.6f;
        float fadeIn = 0.1f;
        float fadeOut = 0.2f;
        if (progress < fadeIn) {
            alpha *= progress / fadeIn;
        } else if (progress > 1.0f - fadeOut) {
            alpha *= (1.0f - progress) / fadeOut;
        }
        int fullBright = 0xF000F0;
        float time = (float)entity.tickCount + partialTick;
        this.renderCloudLayer(poseStack, buffer, FOG_OUTER_TEXTURE, radius * 1.1f, radius * 1.1f, 0.04f, 0.45f, 0.08f, alpha * 0.3f, time, 0.015f, -0.05f, fullBright);
        this.renderCloudLayer(poseStack, buffer, FOG_TEXTURE, radius * 0.9f, radius * 0.9f, 0.06f, 0.6f, 0.1f, alpha * 0.55f, time, 0.025f, 0.0f, fullBright);
        this.renderCloudLayer(poseStack, buffer, FOG_INNER_TEXTURE, radius * 0.6f, radius * 0.6f, 0.1f, 0.5f, 0.12f, alpha * 0.75f, time, 0.04f, 0.1f, fullBright);
        poseStack.popPose();
        super.render(entity, entityYaw, partialTick, poseStack, buffer, packedLight);
    }

    private void renderCloudLayer(PoseStack poseStack, MultiBufferSource buffer, ResourceLocation texture, float radiusX, float radiusZ, float red, float green, float blue, float alpha, float time, float rotationSpeed, float yOffset, int packedLight) {
        poseStack.pushPose();
        poseStack.translate(0.0f, yOffset, 0.0f);
        float rotation = time * rotationSpeed;
        poseStack.mulPose(Axis.YP.rotationDegrees(rotation * 57.295776f));
        poseStack.scale(radiusX, 1.0f, radiusZ);
        VertexConsumer consumer = buffer.getBuffer(RenderType.entityTranslucent(texture));
        PoseStack.Pose pose = poseStack.last();
        float size = 1.0f;
        consumer.vertex(pose.pose(), -size, 0.0f, -size).color(red, green, blue, alpha).uv(0.0f, 0.0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(pose.normal(), 0.0f, 1.0f, 0.0f).endVertex();
        consumer.vertex(pose.pose(), -size, 0.0f, size).color(red, green, blue, alpha).uv(0.0f, 1.0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(pose.normal(), 0.0f, 1.0f, 0.0f).endVertex();
        consumer.vertex(pose.pose(), size, 0.0f, size).color(red, green, blue, alpha).uv(1.0f, 1.0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(pose.normal(), 0.0f, 1.0f, 0.0f).endVertex();
        consumer.vertex(pose.pose(), size, 0.0f, -size).color(red, green, blue, alpha).uv(1.0f, 0.0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(pose.normal(), 0.0f, 1.0f, 0.0f).endVertex();
        poseStack.popPose();
    }

    public boolean shouldRender(EntityToxicCloud entity, Frustum frustum, double camX, double camY, double camZ) {
        return entity.distanceToSqr(camX, camY, camZ) < 4096.0;
    }
}

