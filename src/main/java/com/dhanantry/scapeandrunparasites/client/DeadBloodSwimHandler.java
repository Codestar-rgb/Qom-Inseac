package com.dhanantry.scapeandrunparasites.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class DeadBloodSwimHandler {
   private static boolean isHeadInDeadBlood(EntityPlayer p) {
      return DeadBloodFogHandler.isEyeInDeadBlood(p, Minecraft.func_71410_x().func_184121_ak());
   }

   @SubscribeEvent
   public void onPlayerTick(PlayerTickEvent e) {
      if (e.phase == Phase.END) {
         if (e.player != null) {
            EntityPlayer p = e.player;
            if (p.field_70170_p.field_72995_K && isHeadInDeadBlood(p)) {
               KeyBinding jump = Minecraft.func_71410_x().field_71474_y.field_74314_A;
               if (jump.func_151470_d()) {
                  p.field_70181_x = Math.min(p.field_70181_x + 0.045, 0.12);
               }
            }
         }
      }
   }
}
