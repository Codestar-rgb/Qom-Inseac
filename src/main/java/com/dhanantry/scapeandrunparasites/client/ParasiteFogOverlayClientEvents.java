package com.dhanantry.scapeandrunparasites.client;

import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Post;
import net.minecraftforge.client.event.TextureStitchEvent.Pre;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(modid = "srparasites", value = Side.CLIENT)
public final class ParasiteFogOverlayClientEvents {
   private static final ResourceLocation FOG_OVERLAY_TEXTURE = new ResourceLocation("srparasites", "blocks/parasite_fog");
   private static TextureAtlasSprite fogOverlaySprite;

   private ParasiteFogOverlayClientEvents() {
   }

   @SubscribeEvent
   public static void onTextureStitch(Pre event) {
      fogOverlaySprite = event.getMap().func_174942_a(FOG_OVERLAY_TEXTURE);
   }

   @SubscribeEvent
   public static void onRenderOverlay(Post event) {
      if (event.getType() == ElementType.ALL) {
         Minecraft mc = Minecraft.func_71410_x();
         if (mc.field_71439_g != null && mc.field_71441_e != null) {
            if (!mc.field_71474_y.field_74319_N) {
               if (fogOverlaySprite != null) {
                  if (isPlayerHeadInFog(mc.field_71439_g)) {
                     renderFogOverlay(mc, event.getResolution().func_78326_a(), event.getResolution().func_78328_b());
                  }
               }
            }
         }
      }
   }

   private static boolean isPlayerHeadInFog(EntityPlayer player) {
      BlockPos eyePos = new BlockPos(player.field_70165_t, player.field_70163_u + player.func_70047_e(), player.field_70161_v);
      IBlockState state = player.field_70170_p.func_180495_p(eyePos);
      return state.func_177230_c() == SRPBlocks.ParasiteFog;
   }

   private static void renderFogOverlay(Minecraft mc, int width, int height) {
      mc.func_110434_K().func_110577_a(TextureMap.field_110575_b);
      GlStateManager.func_179097_i();
      GlStateManager.func_179132_a(false);
      GlStateManager.func_179147_l();
      GlStateManager.func_187428_a(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 0.85F);
      Tessellator tessellator = Tessellator.func_178181_a();
      BufferBuilder buffer = tessellator.func_178180_c();
      double z = -90.0;
      buffer.func_181668_a(7, DefaultVertexFormats.field_181707_g);
      buffer.func_181662_b(0.0, height, z).func_187315_a(fogOverlaySprite.func_94209_e(), fogOverlaySprite.func_94210_h()).func_181675_d();
      buffer.func_181662_b(width, height, z).func_187315_a(fogOverlaySprite.func_94212_f(), fogOverlaySprite.func_94210_h()).func_181675_d();
      buffer.func_181662_b(width, 0.0, z).func_187315_a(fogOverlaySprite.func_94212_f(), fogOverlaySprite.func_94206_g()).func_181675_d();
      buffer.func_181662_b(0.0, 0.0, z).func_187315_a(fogOverlaySprite.func_94209_e(), fogOverlaySprite.func_94206_g()).func_181675_d();
      tessellator.func_78381_a();
      GlStateManager.func_179132_a(true);
      GlStateManager.func_179126_j();
      GlStateManager.func_179084_k();
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
   }
}
