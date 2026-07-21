package com.dhanantry.scapeandrunparasites.client.shader;

import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@EventBusSubscriber(modid = "srparasites", value = Side.CLIENT)
public final class DistortedEnlightenmentShaderEvents {
   private static boolean wasActive = false;

   private DistortedEnlightenmentShaderEvents() {
   }

   @SubscribeEvent
   public static void onClientTick(ClientTickEvent event) {
      if (event.phase == Phase.END) {
         Minecraft mc = Minecraft.func_71410_x();
         if (mc != null && mc.field_71441_e != null && mc.field_71439_g != null) {
            boolean active = mc.field_71439_g.func_70644_a(SRPPotions.DISTORTED_ENLIGHTENMENT_E);
            if (active) {
               BreatheShaderManager.get().enableFor(8);
               wasActive = true;
            } else if (wasActive) {
               BreatheShaderManager.get().disable();
               wasActive = false;
            }
         } else {
            if (wasActive) {
               BreatheShaderManager.get().disable();
               wasActive = false;
            }
         }
      }
   }
}
