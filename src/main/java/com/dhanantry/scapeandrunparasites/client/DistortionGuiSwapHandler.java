package com.dhanantry.scapeandrunparasites.client;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.bestiary.client.gui.GuiDistortionHelper;
import com.dhanantry.scapeandrunparasites.client.gui.DistortedGuiNewChat;
import com.dhanantry.scapeandrunparasites.client.gui.DistortedGuiSubtitleOverlay;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

public class DistortionGuiSwapHandler {
   private boolean triedSwap = false;

   @SubscribeEvent
   public void onClientTick(ClientTickEvent event) {
      if (event.phase == Phase.END) {
         if (!this.triedSwap) {
            Minecraft mc = Minecraft.func_71410_x();
            if (mc != null && mc.field_71456_v != null) {
               this.triedSwap = true;
               this.trySwapIngameGui(mc);
            }
         }
      }
   }

   private void trySwapIngameGui(Minecraft mc) {
      try {
         GuiIngame oldGui = mc.field_71456_v;
         this.trySwapChatGui(oldGui, mc);
         this.trySwapSubtitleOverlay(oldGui, mc);
      } catch (Exception var3) {
         GuiDistortionHelper.chatHookAvailable = false;
         GuiDistortionHelper.subtitleHookAvailable = false;
         SRPMain.logger.warn("Failed to swap GUI distortion hooks.");
         SRPMain.logger.error("Exception while swapping distorted GUI hooks.", var3);
      }
   }

   private void trySwapChatGui(GuiIngame gui, Minecraft mc) {
      try {
         Field chatField = this.findChatField(gui.getClass());
         if (chatField == null) {
            GuiDistortionHelper.chatHookAvailable = false;
            SRPMain.logger.warn("Could not find chat GUI field on {}", gui.getClass().getName());
            return;
         }

         chatField.setAccessible(true);
         this.removeFinalIfPresent(chatField);
         GuiNewChat oldChat = (GuiNewChat)chatField.get(gui);
         if (oldChat == null) {
            GuiDistortionHelper.chatHookAvailable = false;
            SRPMain.logger.warn("Chat GUI field was null on {}", gui.getClass().getName());
            return;
         }

         DistortedGuiNewChat newChat = new DistortedGuiNewChat(mc);
         newChat.copyStateFrom(oldChat);
         chatField.set(gui, newChat);
         Object current = chatField.get(gui);
         SRPMain.logger.info("Chat GUI swapped to {}", current == null ? "null" : current.getClass().getName());
      } catch (Exception var7) {
         GuiDistortionHelper.chatHookAvailable = false;
         SRPMain.logger.warn("Failed to swap chat GUI.");
         SRPMain.logger.error("Exception while swapping distorted chat GUI.", var7);
      }
   }

   private void trySwapSubtitleOverlay(GuiIngame newGui, Minecraft mc) {
      try {
         Field subtitleField = this.findSubtitleField(newGui.getClass());
         if (subtitleField == null) {
            GuiDistortionHelper.subtitleHookAvailable = false;
            SRPMain.logger.warn("Could not find subtitle overlay field in {}", newGui.getClass().getName());
            return;
         }

         subtitleField.setAccessible(true);
         this.removeFinalIfPresent(subtitleField);
         Object oldValue = subtitleField.get(newGui);
         SRPMain.logger
            .info(
               "Found subtitle field: {} type={} old={}",
               subtitleField.getName(),
               subtitleField.getType().getName(),
               oldValue == null ? "null" : oldValue.getClass().getName()
            );
         subtitleField.set(newGui, new DistortedGuiSubtitleOverlay(mc));
         Object newValue = subtitleField.get(newGui);
         SRPMain.logger.info("Subtitle overlay swapped to {}", newValue == null ? "null" : newValue.getClass().getName());
      } catch (Exception var6) {
         GuiDistortionHelper.subtitleHookAvailable = false;
         SRPMain.logger.warn("Failed to swap subtitle overlay for distortion hook.");
         SRPMain.logger.error("Exception while swapping distorted subtitle overlay.", var6);
      }
   }

   private Field findIngameGuiField(Class<?> clazz) {
      for (Class<?> c = clazz; c != null; c = c.getSuperclass()) {
         try {
            return c.getDeclaredField("ingameGUI");
         } catch (NoSuchFieldException var9) {
            try {
               return c.getDeclaredField("field_71456_v");
            } catch (NoSuchFieldException var8) {
               for (Field f : c.getDeclaredFields()) {
                  Class<?> type = f.getType();
                  if (type.getName().equals("net.minecraft.client.gui.GuiIngame") || type.getSimpleName().equals("GuiIngame")) {
                     return f;
                  }
               }
            }
         }
      }

      return null;
   }

   private Field findChatField(Class<?> clazz) {
      for (Class<?> c = clazz; c != null; c = c.getSuperclass()) {
         try {
            return c.getDeclaredField("persistantChatGUI");
         } catch (NoSuchFieldException var9) {
            try {
               return c.getDeclaredField("field_73840_e");
            } catch (NoSuchFieldException var8) {
               for (Field f : c.getDeclaredFields()) {
                  Class<?> type = f.getType();
                  if (type.getName().equals("net.minecraft.client.gui.GuiNewChat") || type.getSimpleName().equals("GuiNewChat")) {
                     return f;
                  }
               }
            }
         }
      }

      return null;
   }

   private Field findSubtitleField(Class<?> clazz) {
      for (Class<?> c = clazz; c != null; c = c.getSuperclass()) {
         for (Field f : c.getDeclaredFields()) {
            Class<?> type = f.getType();
            if (type.getName().equals("net.minecraft.client.gui.GuiSubtitleOverlay") || type.getSimpleName().equals("GuiSubtitleOverlay")) {
               return f;
            }
         }
      }

      return null;
   }

   private void copyGuiIngameFields(GuiIngame from, GuiIngame to) throws Exception {
      for (Class<?> c = from.getClass(); c != null; c = c.getSuperclass()) {
         Field[] fields = c.getDeclaredFields();

         for (Field f : fields) {
            if (!Modifier.isStatic(f.getModifiers())) {
               try {
                  f.setAccessible(true);
                  this.removeFinalIfPresent(f);
                  f.set(to, f.get(from));
               } catch (Exception var10) {
               }
            }
         }
      }
   }

   private void removeFinalIfPresent(Field f) {
      try {
         Field modifiersField = Field.class.getDeclaredField("modifiers");
         modifiersField.setAccessible(true);
         modifiersField.setInt(f, f.getModifiers() & -17);
      } catch (Exception var3) {
      }
   }
}
