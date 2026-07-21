package com.dhanantry.scapeandrunparasites.client;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.FogMode;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogColors;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogDensity;
import net.minecraftforge.client.event.EntityViewRenderEvent.RenderFogEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent.OverlayType;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@EventBusSubscriber(value = Side.CLIENT, modid = "srparasites")
public final class DeadBloodFogHandler {
   private static final String DEADBLOOD_BLOCK_ID = "srparasites:deadblood";
   private static final String DEADBLOOD_FLUID_NAME = "deadblood";

   private static boolean isDeadBloodState(IBlockState s) {
      if (s == null) {
         return false;
      } else {
         Block b = s.func_177230_c();
         ResourceLocation key = b.getRegistryName();
         if (key != null && "srparasites:deadblood".equals(key.toString())) {
            return true;
         } else if (!(b instanceof IFluidBlock)) {
            return false;
         } else {
            IFluidBlock fb = (IFluidBlock)b;
            return fb.getFluid() != null && "deadblood".equals(fb.getFluid().getName());
         }
      }
   }

   @SideOnly(Side.CLIENT)
   public static boolean isEyeInDeadBlood(Entity e, double pt) {
      return e != null && isDeadBloodState(ActiveRenderInfo.func_186703_a(e.field_70170_p, e, (float)pt));
   }

   @SubscribeEvent
   public static void onFogColors(FogColors event) {
      if (isEyeInDeadBlood(event.getEntity(), event.getRenderPartialTicks())) {
         event.setRed(0.08F);
         event.setGreen(0.2F);
         event.setBlue(0.07F);
      }
   }

   @SubscribeEvent
   public static void onFogDensity(FogDensity event) {
      if (isEyeInDeadBlood(event.getEntity(), event.getRenderPartialTicks())) {
         event.setDensity(0.48F);
         event.setCanceled(true);
         GlStateManager.func_187430_a(FogMode.EXP2);
      }
   }

   @SubscribeEvent
   public static void onRenderFog(RenderFogEvent event) {
      if (isEyeInDeadBlood(event.getEntity(), event.getRenderPartialTicks())) {
         GlStateManager.func_187430_a(FogMode.LINEAR);
         GL11.glFogf(2915, 0.0F);
         GL11.glFogf(2916, 2.5F);
      }
   }

   @SubscribeEvent
   public static void onOverlay(RenderBlockOverlayEvent event) {
      if (event.getOverlayType() == OverlayType.WATER && isEyeInDeadBlood(event.getPlayer(), 0.0)) {
         event.setCanceled(true);
      }
   }
}
