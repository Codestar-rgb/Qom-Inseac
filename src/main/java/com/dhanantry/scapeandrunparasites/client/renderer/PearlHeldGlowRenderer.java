package com.dhanantry.scapeandrunparasites.client.renderer;

import com.dhanantry.scapeandrunparasites.init.SRPItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(modid = "srparasites", value = Side.CLIENT)
public final class PearlHeldGlowRenderer {
   private static final ResourceLocation KEY_PEARL_STATE = new ResourceLocation("srparasites", "pearl_state");
   private static final float GLOW_BASE_ALPHA = 0.4F;
   private static final float GLOW_R = 0.85F;
   private static final float GLOW_G = 0.95F;
   private static final float GLOW_B = 1.0F;
   private static final int GLOW_LAYERS = 2;
   private static final int GLOW_SAMPLES = 16;
   private static final float GLOW_BASE_RADIUS = 0.0075F;
   private static final float GLOW_RADIUS_STEP = 0.0035F;
   private static final float GLOW_TIME_SPEED = 0.44F;
   private static final float GLOW_ANGLE_WOBBLE = 0.24F;
   private static final float GLOW_RADIUS_JITTER = 0.0032F;
   private static final float VIBE_BASE_AMPLITUDE = 0.0012F;
   private static final float VIBE_FREQ_A = 19.0F;
   private static final float VIBE_FREQ_B = 31.0F;
   private static final float GLITCH_CHANCE_PER_TICK = 0.09F;
   private static final int GLITCH_MIN_FRAMES = 2;
   private static final int GLITCH_MAX_FRAMES = 5;
   private static final int GLITCH_PASSES = 7;
   private static final float GLITCH_ALPHA = 0.6F;
   private static final float GLITCH_OFFSET_MAX = 0.016F;
   private static final float GLITCH_ROT_MAX_DEG = 14.0F;
   private static final float GLITCH_SCALE_MIN = 1.0F;
   private static final float GLITCH_SCALE_MAX = 1.09F;
   private static final float GLITCH_SHAKE_FREQ = 27.0F;
   private static final float GLITCH_SHAKE_BASE = 0.0045F;
   private static int glitchFrames = 0;
   private static int lastTickSeen = -1;

   private static float ampForState(int s) {
      switch (s) {
         case 1:
            return 1.0F;
         case 2:
            return 1.65F;
         case 3:
            return 2.4F;
         default:
            return 0.35F;
      }
   }

   @SubscribeEvent
   public static void onRenderSpecificHand(RenderSpecificHandEvent e) {
      Minecraft mc = Minecraft.func_71410_x();
      if (mc.field_71439_g != null) {
         if (mc.field_71474_y.field_74320_O == 0) {
            ItemStack stack = e.getItemStack();
            if (!stack.func_190926_b() && stack.func_77973_b() == SRPItems.pearl) {
               int state = 0;
               IItemPropertyGetter getter = SRPItems.pearl.func_185045_a(KEY_PEARL_STATE);
               if (getter != null) {
                  state = MathHelper.func_76125_a((int)getter.func_185085_a(stack, mc.field_71441_e, mc.field_71439_g), 0, 3);
               }

               float amp = ampForState(state);
               int tick = mc.field_71439_g.field_70173_aa;
               if (tick != lastTickSeen) {
                  lastTickSeen = tick;
                  float chance = 0.09F * (0.75F + 0.5F * amp);
                  if (glitchFrames <= 0 && mc.field_71441_e.field_73012_v.nextFloat() < chance) {
                     glitchFrames = 2 + mc.field_71441_e.field_73012_v.nextInt(4);
                  }
               }

               AbstractClientPlayer player = mc.field_71439_g;
               ItemRenderer ir = mc.func_175597_ag();
               float pt = e.getPartialTicks();
               float pitch = e.getInterpolatedPitch();
               EnumHand hand = e.getHand();
               float swing = e.getSwingProgress();
               float equip = e.getEquipProgress();
               e.setCanceled(true);
               ir.func_187457_a(player, pt, pitch, hand, swing, stack, equip);
               float t = (player.field_70173_aa + pt) * 0.44F;
               GlStateManager.func_179094_E();
               GlStateManager.func_179140_f();
               GlStateManager.func_179147_l();
               GlStateManager.func_179112_b(770, 1);
               GlStateManager.func_179132_a(false);
               GlStateManager.func_179097_i();

               for (int layer = 0; layer < 2; layer++) {
                  float baseR = 0.0075F + layer * 0.0035F;
                  float layerAlpha = 0.4F * (1.0F - layer * 0.2F);

                  for (int i = 0; i < 16; i++) {
                     float a = i / 16.0F * (float) (Math.PI * 2);
                     float aW = MathHelper.func_76126_a(t * 0.9F + i * 0.7F) * 0.24F;
                     float ang = a + aW;
                     float rJ = MathHelper.func_76126_a(t * 1.7F + i * 1.3F + layer * 0.8F) * 0.0032F;
                     float r = baseR + rJ;
                     float dx = MathHelper.func_76134_b(ang) * r;
                     float dy = MathHelper.func_76126_a(ang) * r;
                     float jx = (MathHelper.func_76126_a(t * 19.0F + i * 0.73F) + MathHelper.func_76134_b(t * 31.0F + i * 0.41F)) * 0.5F * (0.0012F * amp);
                     float jy = (MathHelper.func_76134_b(t * 16.53F + i * 0.31F) + MathHelper.func_76126_a(t * 34.72F + i * 0.27F)) * 0.5F * (0.0012F * amp);
                     GlStateManager.func_179094_E();
                     GlStateManager.func_179131_c(0.85F, 0.95F, 1.0F, layerAlpha);
                     GlStateManager.func_179109_b(dx + jx, dy + jy, 0.0F);
                     ir.func_187457_a(player, pt, pitch, hand, swing, stack, equip);
                     GlStateManager.func_179121_F();
                  }
               }

               if (glitchFrames > 0) {
                  float shakeAmp = 0.0045F * (0.75F + 0.6F * amp);
                  float shake = MathHelper.func_76126_a((player.field_70173_aa + pt) * (27.0F * (0.9F + 0.2F * amp))) * shakeAmp;

                  for (int p = 0; p < 7; p++) {
                     float offX = (mc.field_71441_e.field_73012_v.nextFloat() * 2.0F - 1.0F) * (0.016F * (0.6F + 0.6F * amp)) + shake;
                     float offY = (mc.field_71441_e.field_73012_v.nextFloat() * 2.0F - 1.0F) * (0.016F * (0.6F + 0.6F * amp)) + shake;
                     float rot = (mc.field_71441_e.field_73012_v.nextFloat() * 2.0F - 1.0F) * (14.0F * (0.75F + 0.4F * amp));
                     float scl = 1.0F + mc.field_71441_e.field_73012_v.nextFloat() * 0.09000003F;
                     GlStateManager.func_179094_E();
                     GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 0.6F);
                     GlStateManager.func_179109_b(offX, offY, 0.0F);
                     GlStateManager.func_179114_b(rot, 0.0F, 0.0F, 1.0F);
                     GlStateManager.func_179152_a(scl, scl, scl);
                     ir.func_187457_a(player, pt, pitch, hand, swing, stack, equip);
                     GlStateManager.func_179121_F();
                  }

                  glitchFrames--;
               }

               GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
               GlStateManager.func_179126_j();
               GlStateManager.func_179132_a(true);
               GlStateManager.func_179084_k();
               GlStateManager.func_179145_e();
               GlStateManager.func_179121_F();
            }
         }
      }
   }
}
