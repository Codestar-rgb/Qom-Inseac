package com.dhanantry.scapeandrunparasites.client.celestial;

import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import net.minecraft.client.Minecraft;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogColors;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogDensity;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(modid = "srparasites", value = Side.CLIENT)
public final class BlackSkyClientEvents {
   private static boolean mutedMusic = false;
   private static float oldMusicVolume = 1.0F;
   private static DarkDaysRumbleSound rumbleSound = null;

   private BlackSkyClientEvents() {
   }

   @SubscribeEvent
   public static void onFogColors(FogColors e) {
      if (SRPConfigWorld.enableCelestialObjects) {
         if (BlackSkyClient.isDarkDaysActive()) {
            if (!CelestialSkyRenderer.isSRPDarkDaysShaderPackActive()) {
               e.setRed(0.0F);
               e.setGreen(0.0F);
               e.setBlue(0.0F);
            }
         }
      }
   }

   @SubscribeEvent
   public static void onFogDensity(FogDensity e) {
      if (SRPConfigWorld.enableCelestialObjects) {
         if (BlackSkyClient.isDarkDaysActive()) {
            if (!CelestialSkyRenderer.isSRPDarkDaysShaderPackActive()) {
               e.setDensity(0.003F);
               e.setCanceled(true);
            }
         }
      }
   }

   @SubscribeEvent
   public static void onClientTick(ClientTickEvent e) {
      if (e.phase == Phase.END) {
         Minecraft mc = Minecraft.func_71410_x();
         if (mc != null && mc.field_71474_y != null) {
            boolean active = mc.field_71441_e != null && mc.field_71439_g != null && BlackSkyClient.isDarkDaysActive();
            if (active) {
               if (!mutedMusic) {
                  oldMusicVolume = mc.field_71474_y.func_186711_a(SoundCategory.MUSIC);
                  mutedMusic = true;
               }

               mc.field_71474_y.func_186712_a(SoundCategory.MUSIC, 0.0F);
               if (rumbleSound == null || !mc.func_147118_V().func_147692_c(rumbleSound)) {
                  rumbleSound = new DarkDaysRumbleSound();
                  mc.func_147118_V().func_147682_a(rumbleSound);
               }
            } else {
               rumbleSound = null;
               if (mutedMusic) {
                  mc.field_71474_y.func_186712_a(SoundCategory.MUSIC, oldMusicVolume);
                  mutedMusic = false;
               }
            }
         }
      }
   }
}
