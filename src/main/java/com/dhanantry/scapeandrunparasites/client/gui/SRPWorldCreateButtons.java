package com.dhanantry.scapeandrunparasites.client.gui;

import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent.Pre;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent.Post;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(modid = "srparasites", value = Side.CLIENT)
public final class SRPWorldCreateButtons {
   private static final int BTN_SRP_WORLD_SETTINGS = 50001;
   private static final boolean ENABLE_SRP_WORLD_SETTINGS_BUTTON = true;

   private SRPWorldCreateButtons() {
   }

   @SubscribeEvent
   public static void onInitGuiPost(Post e) {
      if (SRPConfig.worldGIU) {
         if (e.getGui() instanceof GuiCreateWorld) {
            layoutSrpButton(e.getButtonList());
         }
      }
   }

   private static GuiButton findMoreWorldOptionsButton(List<GuiButton> buttons) {
      String moreOptions = I18n.func_135052_a("selectWorld.moreWorldOptions", new Object[0]);

      for (GuiButton b : buttons) {
         if (b != null && b.field_146126_j != null && b.field_146126_j.startsWith(moreOptions)) {
            return b;
         }
      }

      return null;
   }

   @SubscribeEvent
   public static void onActionPerformedPost(net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent.Post e) {
      if (SRPConfig.worldGIU) {
         if (e.getGui() instanceof GuiCreateWorld) {
            layoutSrpButton(e.getButtonList());
            if (e.getButton() != null) {
               if (isOnMainCreateWorldPage(e.getButtonList())) {
                  if (e.getButton().field_146127_k == 50001) {
                     GuiCreateWorld parent = (GuiCreateWorld)e.getGui();
                     Minecraft.func_71410_x().func_147108_a(new GuiSRPWorldSettings(parent));
                  }
               }
            }
         }
      }
   }

   private static void layoutSrpButton(List<GuiButton> buttons) {
      GuiButton srp = findButton(buttons, 50001);
      if (srp == null) {
         srp = new GuiButton(50001, 0, 0, 150, 20, I18n.func_135052_a("gui.srparasites.worldsettings.open", new Object[0]));
         buttons.add(srp);
      }

      srp.field_146126_j = I18n.func_135052_a("gui.srparasites.worldsettings.open", new Object[0]);
      GuiButton more = findMoreWorldOptionsButton(buttons);
      if (more != null) {
         srp.field_146128_h = more.field_146128_h;
         srp.field_146129_i = more.field_146129_i - 24;
         srp.field_146120_f = more.field_146120_f;
         srp.field_146121_g = more.field_146121_g;
         srp.field_146125_m = true;
         srp.field_146124_l = true;
      } else {
         srp.field_146125_m = false;
         srp.field_146124_l = false;
      }
   }

   private static GuiButton findButton(List<GuiButton> buttons, int id) {
      for (GuiButton b : buttons) {
         if (b != null && b.field_146127_k == id) {
            return b;
         }
      }

      return null;
   }

   private static boolean isOnMainCreateWorldPage(List<GuiButton> buttons) {
      String moreOptions = I18n.func_135052_a("selectWorld.moreWorldOptions", new Object[0]);

      for (GuiButton b : buttons) {
         if (b != null && b.field_146126_j != null && b.field_146126_j.startsWith(moreOptions)) {
            return true;
         }
      }

      return false;
   }

   @SubscribeEvent
   public static void onActionPerformedPre(Pre e) {
      if (e.getGui() instanceof GuiCreateWorld) {
         if (e.getButton() != null) {
            List<GuiButton> buttons = e.getButtonList();
            GuiButton srp = findButton(buttons, 50001);
            if (srp != null) {
               String moreOptions = I18n.func_135052_a("selectWorld.moreWorldOptions", new Object[0]);
               String done = I18n.func_135052_a("gui.done", new Object[0]);
               String clicked = e.getButton().field_146126_j == null ? "" : e.getButton().field_146126_j;
               if (clicked.startsWith(moreOptions)) {
                  srp.field_146125_m = false;
                  srp.field_146124_l = false;
               }
            }
         }
      }
   }
}
