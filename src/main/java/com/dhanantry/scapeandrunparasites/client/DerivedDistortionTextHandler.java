package com.dhanantry.scapeandrunparasites.client;

import com.dhanantry.scapeandrunparasites.bestiary.client.gui.GuiDistortionHelper;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DerivedDistortionTextHandler {
   @SubscribeEvent
   public void onItemTooltip(ItemTooltipEvent event) {
      Minecraft mc = Minecraft.func_71410_x();
      if (GuiDistortionHelper.shouldDistortItemTooltips(mc)) {
         if (event.getToolTip() != null && !event.getToolTip().isEmpty()) {
            List<String> lines = event.getToolTip();

            for (int i = 0; i < lines.size(); i++) {
               String s = lines.get(i);
               if (s != null && !s.isEmpty()) {
                  lines.set(i, GuiDistortionHelper.jamText(s));
               }
            }
         }
      }
   }
}
