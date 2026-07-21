package com.dhanantry.scapeandrunparasites.client.renderer;

import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityVenkrolSIV;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.ISound.AttenuationType;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class LayerVenkrolTornado implements LayerRenderer<EntityVenkrolSIV> {
   private static final Map<Integer, LayerVenkrolTornado.VenkrolVortexLoopSound> ACTIVE_SOUNDS = new HashMap<>();
   private final RenderLivingBase<EntityVenkrolSIV> renderer;
   private static final ResourceLocation TORNADO_TEX = new ResourceLocation("srparasites", "textures/particle/fog1.png");
   private static final ResourceLocation TORNADO_OUTER_TEX = new ResourceLocation("srparasites", "textures/particle/fog1_outer.png");

   private boolean tornadoConditions(EntityVenkrolSIV entity) {
      if (!SRPConfigWorld.venkrolTornadoEnabled) {
         return false;
      } else {
         World world = entity.field_70170_p;
         if (world == null) {
            return false;
         } else {
            BlockPos pos = entity.func_180425_c();
            return !world.func_175678_i(pos.func_177984_a()) ? false : world.func_72896_J() && world.func_72911_I();
         }
      }
   }

   private void updateTornadoSound(EntityVenkrolSIV entity) {
      Minecraft mc = Minecraft.func_71410_x();
      if (mc != null && mc.field_71439_g != null) {
         SoundHandler handler = mc.func_147118_V();
         int id = entity.func_145782_y();
         if (this.tornadoConditions(entity) && !entity.field_70128_L) {
            LayerVenkrolTornado.VenkrolVortexLoopSound sound = ACTIVE_SOUNDS.get(id);
            if (sound != null && (sound.isFinished() || !handler.func_147692_c(sound))) {
               ACTIVE_SOUNDS.remove(id);
               sound = null;
            }

            if (sound == null) {
               sound = new LayerVenkrolTornado.VenkrolVortexLoopSound(entity);
               ACTIVE_SOUNDS.put(id, sound);
               handler.func_147682_a(sound);
            } else {
               sound.setEntity(entity);
               if (sound.isFadingOut()) {
                  sound.cancelFadeOut();
               }
            }
         } else {
            LayerVenkrolTornado.VenkrolVortexLoopSound existing = ACTIVE_SOUNDS.get(id);
            if (existing != null) {
               existing.fadeOut();
            }
         }
      }
   }

   public LayerVenkrolTornado(RenderLivingBase<EntityVenkrolSIV> renderer) {
      this.renderer = renderer;
   }

   public void doRenderLayer(
      EntityVenkrolSIV entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale
   ) {
      World world = entity.field_70170_p;
      if (world != null) {
         if (!SRPConfigWorld.venkrolTornadoEnabled) {
            this.updateTornadoSound(entity);
         } else {
            this.updateTornadoSound(entity);
            BlockPos pos = entity.func_180425_c();
            if (world.func_175678_i(pos.func_177984_a())) {
               if (world.func_72896_J() && world.func_72911_I()) {
                  GlStateManager.func_179094_E();
                  GlStateManager.func_179140_f();
                  GlStateManager.func_179129_p();
                  GlStateManager.func_179147_l();
                  GlStateManager.func_179112_b(770, 771);
                  GlStateManager.func_179109_b(0.0F, 2.0F, 0.0F);
                  this.renderer.func_110776_a(TORNADO_TEX);
                  Tessellator tess = Tessellator.func_178181_a();
                  BufferBuilder buf = tess.func_178180_c();
                  float height = 100.0F;
                  float bottomRadius = 5.0F;
                  float topRadius = 20.0F;
                  int verticalSegments = 32;
                  int aroundSegments = 240;
                  float time = (entity.field_70173_aa + partialTicks) * 0.1F;
                  float spinSpeed = 1.5F;
                  float twistAmount = (float)Math.toRadians(90.0);
                  float segmentHeight = height / verticalSegments;
                  int entitySeed = entity.func_145782_y();
                  buf.func_181668_a(7, DefaultVertexFormats.field_181709_i);

                  for (int yStep = 0; yStep < verticalSegments; yStep++) {
                     float t0 = (float)yStep / verticalSegments;
                     float t1 = (float)(yStep + 1) / verticalSegments;
                     float y0 = -t0 * height;
                     float y1 = -t1 * height;
                     float r0 = bottomRadius + (topRadius - bottomRadius) * t0;
                     float r1 = bottomRadius + (topRadius - bottomRadius) * t1;
                     float alpha0 = (float)Math.sin(t0 * (float) Math.PI);
                     float alpha1 = (float)Math.sin(t1 * (float) Math.PI);
                     float maxAlpha = 0.85F;
                     alpha0 *= maxAlpha;
                     alpha1 *= maxAlpha;
                     float baseHalfWidth = 0.8F;
                     float coverageFactor = 0.7F;
                     float circumference0 = (float) (Math.PI * 2) * r0;
                     float circumference1 = (float) (Math.PI * 2) * r1;
                     float arcPerSlice0 = circumference0 / aroundSegments;
                     float arcPerSlice1 = circumference1 / aroundSegments;
                     float neededHalfWidth0 = arcPerSlice0 * coverageFactor * 0.5F;
                     float neededHalfWidth1 = arcPerSlice1 * coverageFactor * 0.5F;
                     float stripHalfWidth0 = Math.max(baseHalfWidth, neededHalfWidth0);
                     float stripHalfWidth1 = Math.max(baseHalfWidth, neededHalfWidth1);
                     float swayBase = 0.8F;
                     float swayTop = 5.5F;
                     float swayMag0 = swayBase + (swayTop - swayBase) * t0;
                     float swayMag1 = swayBase + (swayTop - swayBase) * t1;
                     float phase0 = time * 0.7F + t0 * 8.0F;
                     float phase1 = time * 0.7F + t1 * 8.0F;
                     float sway0x = (float)Math.sin(phase0) * swayMag0;
                     float sway0z = (float)Math.cos(phase0 * 0.9) * swayMag0;
                     float sway1x = (float)Math.sin(phase1) * swayMag1;
                     float sway1z = (float)Math.cos(phase1 * 0.9) * swayMag1;

                     for (int i = 0; i < aroundSegments; i++) {
                        float frac = (float)i / aroundSegments;
                        int panelSeed = entitySeed + yStep * 131 + i * 911;
                        float phaseNoise = pseudoNoise(panelSeed, 11);
                        float phaseOffset = phaseNoise * 0.2F;
                        float speedNoise = pseudoNoise(panelSeed, 23);
                        float localSpin = spinSpeed * (1.0F + speedNoise * 0.15F);
                        float baseAngle = (float)(frac * Math.PI * 2.0) + time * localSpin + phaseOffset;
                        float angle0 = baseAngle + t0 * twistAmount;
                        float angle1 = baseAngle + t1 * twistAmount;
                        float dir0x = (float)Math.cos(angle0);
                        float dir0z = (float)Math.sin(angle0);
                        float dir1x = (float)Math.cos(angle1);
                        float dir1z = (float)Math.sin(angle1);
                        float cx0 = dir0x * r0 + sway0x;
                        float cz0 = dir0z * r0 + sway0z;
                        float cx1 = dir1x * r1 + sway1x;
                        float cz1 = dir1z * r1 + sway1z;
                        int panelYSeed = entitySeed + yStep * 131 + i * 977;
                        float vNoise0 = pseudoNoise(panelYSeed, 41);
                        float vNoise1 = pseudoNoise(panelYSeed, 59);
                        float yOffset0 = vNoise0 * segmentHeight * 0.35F;
                        float yOffset1 = vNoise1 * segmentHeight * 0.35F;
                        float y0Panel = y0 + yOffset0;
                        float y1Panel = y1 + yOffset1;
                        float right0x = -dir0z;
                        float right1x = -dir1z;
                        float x0L = cx0 - right0x * stripHalfWidth0;
                        float z0L = cz0 - dir0x * stripHalfWidth0;
                        float x0R = cx0 + right0x * stripHalfWidth0;
                        float z0R = cz0 + dir0x * stripHalfWidth0;
                        float x1L = cx1 - right1x * stripHalfWidth1;
                        float z1L = cz1 - dir1x * stripHalfWidth1;
                        float x1R = cx1 + right1x * stripHalfWidth1;
                        float z1R = cz1 + dir1x * stripHalfWidth1;
                        float u0 = 0.0F;
                        float u1 = 1.0F;
                        float v0 = 0.0F;
                        float v1 = 1.0F;
                        int r = 255;
                        int g = 255;
                        int b = 255;
                        buf.func_181662_b(x0L, y0Panel, z0L).func_187315_a(u0, v0).func_181669_b(r, g, b, (int)(alpha0 * 255.0F)).func_181675_d();
                        buf.func_181662_b(x0R, y0Panel, z0R).func_187315_a(u1, v0).func_181669_b(r, g, b, (int)(alpha0 * 255.0F)).func_181675_d();
                        buf.func_181662_b(x1R, y1Panel, z1R).func_187315_a(u1, v1).func_181669_b(r, g, b, (int)(alpha1 * 255.0F)).func_181675_d();
                        buf.func_181662_b(x1L, y1Panel, z1L).func_187315_a(u0, v1).func_181669_b(r, g, b, (int)(alpha1 * 255.0F)).func_181675_d();
                     }
                  }

                  tess.func_78381_a();
                  this.renderer.func_110776_a(TORNADO_OUTER_TEX);
                  buf.func_181668_a(7, DefaultVertexFormats.field_181709_i);

                  for (int yStep = 0; yStep < verticalSegments; yStep++) {
                     float t0 = (float)yStep / verticalSegments;
                     float t1 = (float)(yStep + 1) / verticalSegments;
                     float y0 = -t0 * height;
                     float y1 = -t1 * height;
                     float r0 = bottomRadius + (topRadius - bottomRadius) * t0;
                     float r1 = bottomRadius + (topRadius - bottomRadius) * t1;
                     float alpha0 = (float)Math.sin(t0 * (float) Math.PI);
                     float alpha1 = (float)Math.sin(t1 * (float) Math.PI);
                     float extraAlphaScale = 0.6F;
                     alpha0 *= extraAlphaScale;
                     alpha1 *= extraAlphaScale;
                     float swayBase = 0.8F;
                     float swayTop = 5.5F;
                     float swayMag0 = swayBase + (swayTop - swayBase) * t0;
                     float swayMag1 = swayBase + (swayTop - swayBase) * t1;
                     float phase0 = time * 0.7F + t0 * 8.0F;
                     float phase1 = time * 0.7F + t1 * 8.0F;
                     float sway0x = (float)Math.sin(phase0) * swayMag0;
                     float sway0z = (float)Math.cos(phase0 * 0.9) * swayMag0;
                     float sway1x = (float)Math.sin(phase1) * swayMag1;
                     float sway1z = (float)Math.cos(phase1 * 0.9) * swayMag1;
                     float baseHalfWidth = 0.8F;
                     float coverageFactor = 0.7F;
                     float circumference0 = (float) (Math.PI * 2) * (r0 + 3.0F);
                     float circumference1 = (float) (Math.PI * 2) * (r1 + 3.0F);
                     float arcPerSlice0 = circumference0 / aroundSegments;
                     float arcPerSlice1 = circumference1 / aroundSegments;
                     float neededHalfWidth0 = arcPerSlice0 * coverageFactor * 0.5F;
                     float neededHalfWidth1 = arcPerSlice1 * coverageFactor * 0.5F;
                     float stripHalfWidth0 = Math.max(baseHalfWidth, neededHalfWidth0);
                     float stripHalfWidth1 = Math.max(baseHalfWidth, neededHalfWidth1);
                     int extraPanelsPerRing = 6;
                     float extraShell = 3.0F;
                     float extraSpinBase = 3.0F;

                     for (int e = 0; e < extraPanelsPerRing; e++) {
                        int panelSeed = entitySeed + yStep * 131 + e * 911;
                        float angleNoise = pseudoNoise(panelSeed, 11);
                        float baseAngle = angleNoise * (float) Math.PI;
                        float speedNoise = pseudoNoise(panelSeed, 23);
                        float localSpin = extraSpinBase * (1.0F + speedNoise * 0.4F);
                        float phaseNoise = pseudoNoise(panelSeed, 37);
                        float phaseOffset = phaseNoise * (float) Math.PI;
                        float angle0 = baseAngle + time * localSpin + phaseOffset + t0 * twistAmount;
                        float angle1 = baseAngle + time * localSpin + phaseOffset + t1 * twistAmount;
                        float dir0x = (float)Math.cos(angle0);
                        float dir0z = (float)Math.sin(angle0);
                        float dir1x = (float)Math.cos(angle1);
                        float dir1z = (float)Math.sin(angle1);
                        float shellNoise0 = Math.abs(pseudoNoise(panelSeed, 41));
                        float shellNoise1 = Math.abs(pseudoNoise(panelSeed, 53));
                        float rOuter0 = r0 + 1.0F + shellNoise0 * extraShell;
                        float rOuter1 = r1 + 1.0F + shellNoise1 * extraShell;
                        float cx0 = dir0x * rOuter0 + sway0x;
                        float cz0 = dir0z * rOuter0 + sway0z;
                        float cx1 = dir1x * rOuter1 + sway1x;
                        float cz1 = dir1z * rOuter1 + sway1z;
                        float right0x = -dir0z;
                        float right1x = -dir1z;
                        float debrisHalfWidth0 = stripHalfWidth0 * 0.7F;
                        float debrisHalfWidth1 = stripHalfWidth1 * 0.7F;
                        float x0L = cx0 - right0x * debrisHalfWidth0;
                        float z0L = cz0 - dir0x * debrisHalfWidth0;
                        float x0R = cx0 + right0x * debrisHalfWidth0;
                        float z0R = cz0 + dir0x * debrisHalfWidth0;
                        float x1L = cx1 - right1x * debrisHalfWidth1;
                        float z1L = cz1 - dir1x * debrisHalfWidth1;
                        float x1R = cx1 + right1x * debrisHalfWidth1;
                        float z1R = cz1 + dir1x * debrisHalfWidth1;
                        float u0 = 0.0F;
                        float u1 = 1.0F;
                        float v0 = 0.0F;
                        float v1 = 1.0F;
                        int rCol = 255;
                        int gCol = 255;
                        int bCol = 255;
                        buf.func_181662_b(x0L, y0, z0L).func_187315_a(u0, v0).func_181669_b(rCol, gCol, bCol, (int)(alpha0 * 255.0F)).func_181675_d();
                        buf.func_181662_b(x0R, y0, z0R).func_187315_a(u1, v0).func_181669_b(rCol, gCol, bCol, (int)(alpha0 * 255.0F)).func_181675_d();
                        buf.func_181662_b(x1R, y1, z1R).func_187315_a(u1, v1).func_181669_b(rCol, gCol, bCol, (int)(alpha1 * 255.0F)).func_181675_d();
                        buf.func_181662_b(x1L, y1, z1L).func_187315_a(u0, v1).func_181669_b(rCol, gCol, bCol, (int)(alpha1 * 255.0F)).func_181675_d();
                     }
                  }

                  tess.func_78381_a();
                  this.renderer.func_110776_a(TORNADO_OUTER_TEX);
                  buf.func_181668_a(7, DefaultVertexFormats.field_181709_i);
                  float cloudCapHeight = 40.0F;
                  float cloudInnerRadius = topRadius * 1.2F;
                  float cloudOuterRadius = topRadius * 5.0F;
                  int cloudRings = 5;
                  int cloudSegments = 32;
                  float cloudSpinBase = 0.7F;
                  float joinY = -height;
                  float capYOffset = 25.0F;
                  float capBottomY = joinY - 2.0F - capYOffset;
                  float capTopY = capBottomY - cloudCapHeight;

                  for (int ring = 0; ring < cloudRings; ring++) {
                     float ct0 = (float)ring / cloudRings;
                     float ct1 = (float)(ring + 1) / cloudRings;
                     float cy0 = capBottomY + ct0 * (capTopY - capBottomY);
                     float cy1 = capBottomY + ct1 * (capTopY - capBottomY);
                     float rt0 = 1.0F - ct0;
                     float rt1 = 1.0F - ct1;
                     float cr0 = cloudInnerRadius + (cloudOuterRadius - cloudInnerRadius) * rt0;
                     float cr1 = cloudInnerRadius + (cloudOuterRadius - cloudInnerRadius) * rt1;
                     float ht0 = ct0;
                     float ht1 = ct1;
                     float capAlpha0 = 1.0F - ct0;
                     float capAlpha1 = 1.0F - ct1;
                     capAlpha0 *= capAlpha0;
                     capAlpha1 *= capAlpha1;
                     float maxCapAlpha = 0.75F;
                     capAlpha0 *= maxCapAlpha;
                     capAlpha1 *= maxCapAlpha;
                     float thicknessFactor0 = 1.0F - ct0 * 0.8F;
                     float thicknessFactor1 = 1.0F - ct1 * 0.8F;
                     float swayBaseCap = 0.5F;
                     float swayTopCap = 3.0F;
                     float swayMagC0 = swayBaseCap + (swayTopCap - swayBaseCap) * ct0;
                     float swayMagC1 = swayBaseCap + (swayTopCap - swayBaseCap) * ct1;
                     float phaseC0 = time * 0.4F + ct0 * 5.0F;
                     float phaseC1 = time * 0.4F + ct1 * 5.0F;
                     float swayCx0 = (float)Math.sin(phaseC0) * swayMagC0;
                     float swayCz0 = (float)Math.cos(phaseC0 * 0.9) * swayMagC0;
                     float swayCx1 = (float)Math.sin(phaseC1) * swayMagC1;
                     float swayCz1 = (float)Math.cos(phaseC1 * 0.9) * swayMagC1;
                     float capCoverage = 0.9F;
                     float capCirc0 = (float) (Math.PI * 2) * cr0;
                     float capCirc1 = (float) (Math.PI * 2) * cr1;
                     float capHalfW0 = capCirc0 / cloudSegments * capCoverage * 0.5F;
                     float capHalfW1 = capCirc1 / cloudSegments * capCoverage * 0.5F;

                     for (int i = 0; i < cloudSegments; i++) {
                        float frac = (float)i / cloudSegments;
                        int cloudSeed = entitySeed + ring * 733 + i * 1871;
                        float spinNoise = pseudoNoise(cloudSeed, 71);
                        float phaseNoise = pseudoNoise(cloudSeed, 83);
                        float radiusNoise = pseudoNoise(cloudSeed, 97);
                        float localSpin = cloudSpinBase * (1.0F + spinNoise * 0.25F);
                        float angleBase = (float)(frac * Math.PI * 2.0) + time * localSpin + phaseNoise * (float) Math.PI;
                        float angle0 = angleBase + ht0 * twistAmount * 0.4F;
                        float angle1 = angleBase + ht1 * twistAmount * 0.4F;
                        float dir0x = (float)Math.cos(angle0);
                        float dir0z = (float)Math.sin(angle0);
                        float dir1x = (float)Math.cos(angle1);
                        float dir1z = (float)Math.sin(angle1);
                        float rc0 = cr0 + radiusNoise * 1.5F;
                        float rc1 = cr1 + radiusNoise * 1.5F;
                        float ccx0 = dir0x * rc0 + swayCx0;
                        float ccz0 = dir0z * rc0 + swayCz0;
                        float ccx1 = dir1x * rc1 + swayCx1;
                        float ccz1 = dir1z * rc1 + swayCz1;
                        float right0x = -dir0z;
                        float right1x = -dir1z;
                        float cy0Panel = cy0 * thicknessFactor0;
                        float cy1Panel = cy1 * thicknessFactor1;
                        float slabHalfW0 = capHalfW0 * 1.2F;
                        float slabHalfW1 = capHalfW1 * 1.2F;
                        float x0L = ccx0 - right0x * slabHalfW0;
                        float z0L = ccz0 - dir0x * slabHalfW0;
                        float x0R = ccx0 + right0x * slabHalfW0;
                        float z0R = ccz0 + dir0x * slabHalfW0;
                        float x1L = ccx1 - right1x * slabHalfW1;
                        float z1L = ccz1 - dir1x * slabHalfW1;
                        float x1R = ccx1 + right1x * slabHalfW1;
                        float z1R = ccz1 + dir1x * slabHalfW1;
                        float u0 = 0.0F;
                        float u1 = 1.0F;
                        float v0 = 0.0F;
                        float v1 = 1.0F;
                        int rCol = 255;
                        int gCol = 255;
                        int bCol = 255;
                        buf.func_181662_b(x0L, cy0Panel, z0L).func_187315_a(u0, v0).func_181669_b(rCol, gCol, bCol, (int)(capAlpha0 * 255.0F)).func_181675_d();
                        buf.func_181662_b(x0R, cy0Panel, z0R).func_187315_a(u1, v0).func_181669_b(rCol, gCol, bCol, (int)(capAlpha0 * 255.0F)).func_181675_d();
                        buf.func_181662_b(x1R, cy1Panel, z1R).func_187315_a(u1, v1).func_181669_b(rCol, gCol, bCol, (int)(capAlpha1 * 255.0F)).func_181675_d();
                        buf.func_181662_b(x1L, cy1Panel, z1L).func_187315_a(u0, v1).func_181669_b(rCol, gCol, bCol, (int)(capAlpha1 * 255.0F)).func_181675_d();
                     }
                  }

                  tess.func_78381_a();
                  this.renderer.func_110776_a(TORNADO_OUTER_TEX);
                  buf.func_181668_a(7, DefaultVertexFormats.field_181709_i);
                  float innerCloudCapHeight = cloudCapHeight * 0.6F;
                  float innerCloudInnerRadius = cloudInnerRadius * 0.5F;
                  float innerCloudOuterRadius = cloudOuterRadius * 0.5F;
                  int innerCloudRings = 4;
                  int innerCloudSegments = 28;
                  float innerCloudSpinBase = cloudSpinBase * 0.8F;
                  float innerOffsetDown = 40.0F;
                  float innerCapBottomY = joinY + innerOffsetDown;
                  float innerCapTopY = innerCapBottomY - innerCloudCapHeight;

                  for (int ring = 0; ring < innerCloudRings; ring++) {
                     float ct0 = (float)ring / innerCloudRings;
                     float ct1 = (float)(ring + 1) / innerCloudRings;
                     float cy0 = innerCapBottomY + ct0 * (innerCapTopY - innerCapBottomY);
                     float cy1 = innerCapBottomY + ct1 * (innerCapTopY - innerCapBottomY);
                     float rt0 = 1.0F - ct0;
                     float rt1 = 1.0F - ct1;
                     float cr0 = innerCloudInnerRadius + (innerCloudOuterRadius - innerCloudInnerRadius) * rt0;
                     float cr1 = innerCloudInnerRadius + (innerCloudOuterRadius - innerCloudInnerRadius) * rt1;
                     float ht0 = ct0;
                     float ht1 = ct1;
                     float capAlpha0 = 1.0F - ct0;
                     float capAlpha1 = 1.0F - ct1;
                     capAlpha0 *= capAlpha0;
                     capAlpha1 *= capAlpha1;
                     float maxCapAlpha = 0.55F;
                     capAlpha0 *= maxCapAlpha;
                     capAlpha1 *= maxCapAlpha;
                     float thicknessFactor0 = 1.0F - ct0 * 0.6F;
                     float thicknessFactor1 = 1.0F - ct1 * 0.6F;
                     float swayBaseCap = 0.4F;
                     float swayTopCap = 2.0F;
                     float swayMagC0 = swayBaseCap + (swayTopCap - swayBaseCap) * ct0;
                     float swayMagC1 = swayBaseCap + (swayTopCap - swayBaseCap) * ct1;
                     float phaseC0 = time * 0.35F + ct0 * 4.0F;
                     float phaseC1 = time * 0.35F + ct1 * 4.0F;
                     float swayCx0 = (float)Math.sin(phaseC0) * swayMagC0;
                     float swayCz0 = (float)Math.cos(phaseC0 * 0.9) * swayMagC0;
                     float swayCx1 = (float)Math.sin(phaseC1) * swayMagC1;
                     float swayCz1 = (float)Math.cos(phaseC1 * 0.9) * swayMagC1;
                     float capCoverage = 0.9F;
                     float capCirc0 = (float) (Math.PI * 2) * cr0;
                     float capCirc1 = (float) (Math.PI * 2) * cr1;
                     float capHalfW0 = capCirc0 / innerCloudSegments * capCoverage * 0.5F;
                     float capHalfW1 = capCirc1 / innerCloudSegments * capCoverage * 0.5F;

                     for (int i = 0; i < innerCloudSegments; i++) {
                        float frac = (float)i / innerCloudSegments;
                        int cloudSeed = entitySeed + 9999 + ring * 733 + i * 1871;
                        float spinNoise = pseudoNoise(cloudSeed, 71);
                        float phaseNoise = pseudoNoise(cloudSeed, 83);
                        float radiusNoise = pseudoNoise(cloudSeed, 97);
                        float localSpin = innerCloudSpinBase * (1.0F + spinNoise * 0.25F);
                        float angleBase = (float)(frac * Math.PI * 2.0) + time * localSpin + phaseNoise * (float) Math.PI;
                        float angle0 = angleBase + ht0 * twistAmount * 0.4F;
                        float angle1 = angleBase + ht1 * twistAmount * 0.4F;
                        float dir0x = (float)Math.cos(angle0);
                        float dir0z = (float)Math.sin(angle0);
                        float dir1x = (float)Math.cos(angle1);
                        float dir1z = (float)Math.sin(angle1);
                        float rc0 = cr0 + radiusNoise * 1.2F;
                        float rc1 = cr1 + radiusNoise * 1.2F;
                        float ccx0 = dir0x * rc0 + swayCx0;
                        float ccz0 = dir0z * rc0 + swayCz0;
                        float ccx1 = dir1x * rc1 + swayCx1;
                        float ccz1 = dir1z * rc1 + swayCz1;
                        float right0x = -dir0z;
                        float right1x = -dir1z;
                        float cy0Panel = cy0 * thicknessFactor0;
                        float cy1Panel = cy1 * thicknessFactor1;
                        float slabHalfW0 = capHalfW0 * 1.1F;
                        float slabHalfW1 = capHalfW1 * 1.1F;
                        float x0L = ccx0 - right0x * slabHalfW0;
                        float z0L = ccz0 - dir0x * slabHalfW0;
                        float x0R = ccx0 + right0x * slabHalfW0;
                        float z0R = ccz0 + dir0x * slabHalfW0;
                        float x1L = ccx1 - right1x * slabHalfW1;
                        float z1L = ccz1 - dir1x * slabHalfW1;
                        float x1R = ccx1 + right1x * slabHalfW1;
                        float z1R = ccz1 + dir1x * slabHalfW1;
                        float u0 = 0.0F;
                        float u1 = 1.0F;
                        float v0 = 0.0F;
                        float v1 = 1.0F;
                        int rCol = 255;
                        int gCol = 255;
                        int bCol = 255;
                        buf.func_181662_b(x0L, cy0Panel, z0L).func_187315_a(u0, v0).func_181669_b(rCol, gCol, bCol, (int)(capAlpha0 * 255.0F)).func_181675_d();
                        buf.func_181662_b(x0R, cy0Panel, z0R).func_187315_a(u1, v0).func_181669_b(rCol, gCol, bCol, (int)(capAlpha0 * 255.0F)).func_181675_d();
                        buf.func_181662_b(x1R, cy1Panel, z1R).func_187315_a(u1, v1).func_181669_b(rCol, gCol, bCol, (int)(capAlpha1 * 255.0F)).func_181675_d();
                        buf.func_181662_b(x1L, cy1Panel, z1L).func_187315_a(u0, v1).func_181669_b(rCol, gCol, bCol, (int)(capAlpha1 * 255.0F)).func_181675_d();
                     }
                  }

                  tess.func_78381_a();
                  this.renderer.func_110776_a(TORNADO_OUTER_TEX);
                  BufferBuilder buf2 = tess.func_178180_c();
                  buf2.func_181668_a(7, DefaultVertexFormats.field_181709_i);
                  float ringInnerRadius = bottomRadius * 1.05F;
                  float ringOuterRadius = bottomRadius * 1.95F;
                  float bottomY = joinY + height;
                  float ringHeight = 4.0F;
                  float verticalOffset = -4.0F;
                  float ringBottomY = bottomY + 0.2F + verticalOffset;
                  float ringTopY = ringBottomY + ringHeight;
                  int ringRings = 2;
                  int ringSegments = 64;
                  float ringSpinBase = cloudSpinBase * 3.0F;

                  for (int ring = 0; ring < ringRings; ring++) {
                     float ct0 = (float)ring / ringRings;
                     float ct1 = (float)(ring + 1) / ringRings;
                     float cy0 = ringBottomY + ct0 * (ringTopY - ringBottomY);
                     float cy1 = ringBottomY + ct1 * (ringTopY - ringBottomY);
                     float rt0 = 1.0F - ct0;
                     float rt1 = 1.0F - ct1;
                     float cr0 = ringInnerRadius + (ringOuterRadius - ringInnerRadius) * rt0;
                     float cr1 = ringInnerRadius + (ringOuterRadius - ringInnerRadius) * rt1;
                     float ht0 = ct0;
                     float ht1 = ct1;
                     float alpha0 = 0.8F;
                     float alpha1 = 0.7F;
                     float thicknessFactor0 = 1.0F - ct0 * 0.2F;
                     float thicknessFactor1 = 1.0F - ct1 * 0.2F;
                     float swayBase = 0.15F;
                     float swayTop = 0.4F;
                     float swayMag0 = swayBase + (swayTop - swayBase) * ct0;
                     float swayMag1 = swayBase + (swayTop - swayBase) * ct1;
                     float phase0 = time * 0.7F + ct0 * 2.0F;
                     float phase1 = time * 0.7F + ct1 * 2.0F;
                     float swayX0 = (float)Math.sin(phase0) * swayMag0;
                     float swayZ0 = (float)Math.cos(phase0 * 0.9) * swayMag0;
                     float swayX1 = (float)Math.sin(phase1) * swayMag1;
                     float swayZ1 = (float)Math.cos(phase1 * 0.9) * swayMag1;
                     float coverage = 0.9F;
                     float circ0 = (float) (Math.PI * 2) * cr0;
                     float circ1 = (float) (Math.PI * 2) * cr1;
                     float halfW0 = circ0 / ringSegments * coverage * 0.5F;
                     float halfW1 = circ1 / ringSegments * coverage * 0.5F;

                     for (int i = 0; i < ringSegments; i++) {
                        float frac = (float)i / ringSegments;
                        int cloudSeed = entitySeed + 77777 + ring * 733 + i * 1871;
                        float spinNoise = pseudoNoise(cloudSeed, 71);
                        float phaseNoise = pseudoNoise(cloudSeed, 83);
                        float radiusNoise = pseudoNoise(cloudSeed, 97);
                        float localSpin = ringSpinBase * (1.0F + spinNoise * 0.3F);
                        float angleBase = (float)(frac * Math.PI * 2.0) + time * localSpin + phaseNoise * (float) Math.PI;
                        float angle0 = angleBase + ht0 * twistAmount * 0.2F;
                        float angle1 = angleBase + ht1 * twistAmount * 0.2F;
                        float dir0x = (float)Math.cos(angle0);
                        float dir0z = (float)Math.sin(angle0);
                        float dir1x = (float)Math.cos(angle1);
                        float dir1z = (float)Math.sin(angle1);
                        float rc0 = cr0 + radiusNoise * 0.5F;
                        float rc1 = cr1 + radiusNoise * 0.5F;
                        float ccx0 = dir0x * rc0 + swayX0;
                        float ccz0 = dir0z * rc0 + swayZ0;
                        float ccx1 = dir1x * rc1 + swayX1;
                        float ccz1 = dir1z * rc1 + swayZ1;
                        float right0x = -dir0z;
                        float right1x = -dir1z;
                        float cy0Panel = cy0 * thicknessFactor0;
                        float cy1Panel = cy1 * thicknessFactor1;
                        float hNoise = pseudoNoise(cloudSeed, 113);
                        float hJitterAmp = 1.5F;
                        float heightOffset = (hNoise - 0.5F) * hJitterAmp;
                        float cy0J = cy0Panel + heightOffset;
                        float cy1J = cy1Panel + heightOffset;
                        float x0L = ccx0 - right0x * halfW0;
                        float z0L = ccz0 - dir0x * halfW0;
                        float x0R = ccx0 + right0x * halfW0;
                        float z0R = ccz0 + dir0x * halfW0;
                        float x1L = ccx1 - right1x * halfW1;
                        float z1L = ccz1 - dir1x * halfW1;
                        float x1R = ccx1 + right1x * halfW1;
                        float z1R = ccz1 + dir1x * halfW1;
                        float u0 = 0.0F;
                        float u1 = 1.0F;
                        float v0 = 0.0F;
                        float v1 = 1.0F;
                        int rCol = 255;
                        int gCol = 255;
                        int bCol = 255;
                        buf.func_181662_b(x0L, cy0J, z0L).func_187315_a(u0, v0).func_181669_b(rCol, gCol, bCol, (int)(alpha0 * 255.0F)).func_181675_d();
                        buf.func_181662_b(x0R, cy0J, z0R).func_187315_a(u1, v0).func_181669_b(rCol, gCol, bCol, (int)(alpha0 * 255.0F)).func_181675_d();
                        buf.func_181662_b(x1R, cy1J, z1R).func_187315_a(u1, v1).func_181669_b(rCol, gCol, bCol, (int)(alpha1 * 255.0F)).func_181675_d();
                        buf.func_181662_b(x1L, cy1J, z1L).func_187315_a(u0, v1).func_181669_b(rCol, gCol, bCol, (int)(alpha1 * 255.0F)).func_181675_d();
                     }
                  }

                  tess.func_78381_a();
                  GlStateManager.func_179089_o();
                  GlStateManager.func_179084_k();
                  GlStateManager.func_179145_e();
                  GlStateManager.func_179121_F();
               }
            }
         }
      }
   }

   public boolean func_177142_b() {
      return false;
   }

   private static float pseudoNoise(int seed, int salt) {
      int s = seed * 73428767 ^ salt * 912931;
      s = s * 1103515245 + 12345;
      int v = s >>> 16 & 32767;
      return v / 16384.0F - 1.0F;
   }

   private static class VenkrolVortexLoopSound extends MovingSound {
      private EntityVenkrolSIV entity;
      private static final float HEAR_DISTANCE = 100.0F;
      private boolean fadingOut = false;
      private int fadeTicks = 0;
      private static final int FADE_DURATION = 10;
      private boolean finished = false;

      boolean isFadingOut() {
         return this.fadingOut;
      }

      boolean isFinished() {
         return this.finished;
      }

      void cancelFadeOut() {
         this.fadingOut = false;
         this.fadeTicks = 0;
      }

      VenkrolVortexLoopSound(EntityVenkrolSIV entity) {
         super(SRPSounds.BECKON_VORTEX, SoundCategory.WEATHER);
         this.entity = entity;
         this.field_147659_g = true;
         this.field_147665_h = 0;
         this.field_147666_i = AttenuationType.LINEAR;
         this.field_147662_b = 8.25F;
         this.field_147663_c = 1.0F;
         this.updatePos();
      }

      void setEntity(EntityVenkrolSIV e) {
         this.entity = e;
      }

      void fadeOut() {
         this.fadingOut = true;
         this.fadeTicks = 10;
      }

      private void updatePos() {
         this.field_147660_d = (float)this.entity.field_70165_t;
         this.field_147661_e = (float)(this.entity.field_70163_u + 2.0);
         this.field_147658_f = (float)this.entity.field_70161_v;
      }

      public void func_73660_a() {
         if (this.entity != null && !this.entity.field_70128_L) {
            this.updatePos();
            if (!this.fadingOut) {
               Minecraft mc = Minecraft.func_71410_x();
               if (mc != null && mc.field_71439_g != null) {
                  float dx = (float)(mc.field_71439_g.field_70165_t - this.entity.field_70165_t);
                  float dy = (float)(mc.field_71439_g.field_70163_u - (this.entity.field_70163_u + 2.0));
                  float dz = (float)(mc.field_71439_g.field_70161_v - this.entity.field_70161_v);
                  float dist = MathHelper.func_76129_c(dx * dx + dy * dy + dz * dz);
                  float t = 1.0F - MathHelper.func_76131_a(dist / 100.0F, 0.0F, 1.0F);
                  this.field_147662_b = 6.25F * (0.35F + 0.65F * (t * t));
               }
            } else {
               this.fadeTicks--;
               this.field_147662_b *= 0.7F;
               if (this.fadeTicks <= 0 || this.field_147662_b < 0.01F) {
                  this.finished = true;
               }
            }
         } else {
            this.finished = true;
         }
      }
   }
}
