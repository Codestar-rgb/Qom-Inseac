package com.srp.draconite.client.renderer;

import com.srp.draconite.entity.EntityDraconiteFireball;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class DraconiteFireballRenderer
extends EntityRenderer<EntityDraconiteFireball> {
    private static final ResourceLocation CUSTOM_TEXTURE = new ResourceLocation("srpdraconite", "textures/entity/fireball.png");

    public DraconiteFireballRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    public ResourceLocation getTextureLocation(EntityDraconiteFireball entity) {
        return CUSTOM_TEXTURE;
    }

    public void render(EntityDraconiteFireball entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        float baseScale = 1.2f;
        float pulse = 1.0f + 0.1f * (float)Math.sin(((float)entity.tickCount + partialTick) * 0.5f);
        float scale = baseScale * pulse;
        poseStack.scale(scale, scale, scale);
        int fullBright = 0xF000F0;
        VertexConsumer coreConsumer = buffer.getBuffer(RenderType.entitySolid(CUSTOM_TEXTURE));
        this.renderBillboardQuad(poseStack, coreConsumer, fullBright, 1.0f, 0.9f, 0.4f, 1.0f);
        poseStack.pushPose();
        float glowScale = 1.3f;
        poseStack.scale(glowScale, glowScale, glowScale);
        VertexConsumer glowConsumer = buffer.getBuffer(RenderType.entityTranslucent(CUSTOM_TEXTURE));
        this.renderBillboardQuad(poseStack, glowConsumer, fullBright, 1.0f, 0.5f, 0.1f, 0.6f);
        poseStack.popPose();
        poseStack.pushPose();
        float greenScale = 1.6f;
        poseStack.scale(greenScale, greenScale, greenScale);
        VertexConsumer greenConsumer = buffer.getBuffer(RenderType.entityTranslucent(CUSTOM_TEXTURE));
        this.renderBillboardQuad(poseStack, greenConsumer, fullBright, 0.1f, 0.8f, 0.2f, 0.25f);
        poseStack.popPose();
        poseStack.popPose();
        if (entity.level().isClientSide) {
            for (int i = 0; i < 3; ++i) {
                double px = entity.getX() + (entity.level().random.nextDouble() - 0.5) * 0.4;
                double py = entity.getY() + (entity.level().random.nextDouble() - 0.5) * 0.4;
                double pz = entity.getZ() + (entity.level().random.nextDouble() - 0.5) * 0.4;
                entity.level().addParticle(ParticleTypes.FLAME, px, py, pz, -entity.getDeltaMovement().x * 0.1, -entity.getDeltaMovement().y * 0.1, -entity.getDeltaMovement().z * 0.1);
            }
            if (entity.level().random.nextInt(2) == 0) {
                entity.level().addParticle(ParticleTypes.LARGE_SMOKE, entity.getX(), entity.getY(), entity.getZ(), (entity.level().random.nextDouble() - 0.5) * 0.1, entity.level().random.nextDouble() * 0.1, (entity.level().random.nextDouble() - 0.5) * 0.1);
            }
            if (entity.level().random.nextInt(4) == 0) {
                entity.level().addParticle(ParticleTypes.ENCHANT, entity.getX() + (entity.level().random.nextDouble() - 0.5) * 0.5, entity.getY() + (entity.level().random.nextDouble() - 0.5) * 0.5, entity.getZ() + (entity.level().random.nextDouble() - 0.5) * 0.5, 0.0, 0.05, 0.0);
            }
        }
        super.render(entity, entityYaw, partialTick, poseStack, buffer, packedLight);
    }

    private void renderBillboardQuad(PoseStack poseStack, VertexConsumer consumer, int packedLight, float red, float green, float blue, float alpha) {
        PoseStack.Pose pose = poseStack.last();
        float size = 0.5f;
        consumer.vertex(pose.pose(), -size, -size, 0.0f).color(red, green, blue, alpha).uv(0.0f, 1.0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(pose.normal(), 0.0f, 0.0f, 1.0f).endVertex();
        consumer.vertex(pose.pose(), size, -size, 0.0f).color(red, green, blue, alpha).uv(1.0f, 1.0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(pose.normal(), 0.0f, 0.0f, 1.0f).endVertex();
        consumer.vertex(pose.pose(), size, size, 0.0f).color(red, green, blue, alpha).uv(1.0f, 0.0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(pose.normal(), 0.0f, 0.0f, 1.0f).endVertex();
        consumer.vertex(pose.pose(), -size, size, 0.0f).color(red, green, blue, alpha).uv(0.0f, 0.0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(pose.normal(), 0.0f, 0.0f, 1.0f).endVertex();
        consumer.vertex(pose.pose(), size, -size, 0.0f).color(red, green, blue, alpha).uv(1.0f, 1.0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(pose.normal(), 0.0f, 0.0f, -1.0f).endVertex();
        consumer.vertex(pose.pose(), -size, -size, 0.0f).color(red, green, blue, alpha).uv(0.0f, 1.0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(pose.normal(), 0.0f, 0.0f, -1.0f).endVertex();
        consumer.vertex(pose.pose(), -size, size, 0.0f).color(red, green, blue, alpha).uv(0.0f, 0.0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(pose.normal(), 0.0f, 0.0f, -1.0f).endVertex();
        consumer.vertex(pose.pose(), size, size, 0.0f).color(red, green, blue, alpha).uv(1.0f, 0.0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(pose.normal(), 0.0f, 0.0f, -1.0f).endVertex();
    }
}

