/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiIngame
 *  net.minecraft.client.gui.GuiNewChat
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 */
package com.subspaceparasite.client;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.bestiary.client.gui.GuiDistortionHelper;
import com.subspaceparasite.client.gui.DistortedGuiNewChat;
import com.subspaceparasite.client.gui.DistortedGuiSubtitleOverlay;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class DistortionGuiSwapHandler {
    private boolean triedSwap = false;

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }
        if (this.triedSwap) {
            return;
        }
        Minecraft mc = Minecraft.func_71410_x();
        if (mc == null || mc.field_71456_v == null) {
            return;
        }
        this.triedSwap = true;
        this.trySwapIngameGui(mc);
    }

    private void trySwapIngameGui(Minecraft mc) {
        try {
            GuiIngame oldGui = mc.field_71456_v;
            this.trySwapChatGui(oldGui, mc);
            this.trySwapSubtitleOverlay(oldGui, mc);
        }
        catch (Exception e) {
            GuiDistortionHelper.chatHookAvailable = false;
            GuiDistortionHelper.subtitleHookAvailable = false;
            SPMain.logger.warn("Failed to swap GUI distortion hooks.");
            SPMain.logger.error("Exception while swapping distorted GUI hooks.", (Throwable)e);
        }
    }

    private void trySwapChatGui(GuiIngame gui, Minecraft mc) {
        try {
            Field chatField = this.findChatField(gui.getClass());
            if (chatField == null) {
                GuiDistortionHelper.chatHookAvailable = false;
                SPMain.logger.warn("Could not find chat GUI field on {}", (Object)gui.getClass().getName());
                return;
            }
            chatField.setAccessible(true);
            this.removeFinalIfPresent(chatField);
            GuiNewChat oldChat = (GuiNewChat)chatField.get(gui);
            if (oldChat == null) {
                GuiDistortionHelper.chatHookAvailable = false;
                SPMain.logger.warn("Chat GUI field was null on {}", (Object)gui.getClass().getName());
                return;
            }
            DistortedGuiNewChat newChat = new DistortedGuiNewChat(mc);
            newChat.copyStateFrom(oldChat);
            chatField.set(gui, (Object)newChat);
            Object current = chatField.get(gui);
            SPMain.logger.info("Chat GUI swapped to {}", (Object)(current == null ? "null" : current.getClass().getName()));
        }
        catch (Exception e) {
            GuiDistortionHelper.chatHookAvailable = false;
            SPMain.logger.warn("Failed to swap chat GUI.");
            SPMain.logger.error("Exception while swapping distorted chat GUI.", (Throwable)e);
        }
    }

    private void trySwapSubtitleOverlay(GuiIngame newGui, Minecraft mc) {
        try {
            Field subtitleField = this.findSubtitleField(newGui.getClass());
            if (subtitleField == null) {
                GuiDistortionHelper.subtitleHookAvailable = false;
                SPMain.logger.warn("Could not find subtitle overlay field in {}", (Object)newGui.getClass().getName());
                return;
            }
            subtitleField.setAccessible(true);
            this.removeFinalIfPresent(subtitleField);
            Object oldValue = subtitleField.get(newGui);
            SPMain.logger.info("Found subtitle field: {} type={} old={}", (Object)subtitleField.getName(), (Object)subtitleField.getType().getName(), (Object)(oldValue == null ? "null" : oldValue.getClass().getName()));
            subtitleField.set(newGui, (Object)new DistortedGuiSubtitleOverlay(mc));
            Object newValue = subtitleField.get(newGui);
            SPMain.logger.info("Subtitle overlay swapped to {}", (Object)(newValue == null ? "null" : newValue.getClass().getName()));
        }
        catch (Exception e) {
            GuiDistortionHelper.subtitleHookAvailable = false;
            SPMain.logger.warn("Failed to swap subtitle overlay for distortion hook.");
            SPMain.logger.error("Exception while swapping distorted subtitle overlay.", (Throwable)e);
        }
    }

    private Field findIngameGuiField(Class<?> clazz) {
        for (Class<?> c = clazz; c != null; c = c.getSuperclass()) {
            try {
                Field f = c.getDeclaredField("ingameGUI");
                return f;
            }
            catch (NoSuchFieldException f) {
                try {
                    Field f2 = c.getDeclaredField("field_71456_v");
                    return f2;
                }
                catch (NoSuchFieldException noSuchFieldException) {
                    for (Field f3 : c.getDeclaredFields()) {
                        Class<?> type = f3.getType();
                        if (!type.getName().equals("net.minecraft.client.gui.GuiIngame") && !type.getSimpleName().equals("GuiIngame")) continue;
                        return f3;
                    }
                    continue;
                }
            }
        }
        return null;
    }

    private Field findChatField(Class<?> clazz) {
        for (Class<?> c = clazz; c != null; c = c.getSuperclass()) {
            try {
                Field f = c.getDeclaredField("persistantChatGUI");
                return f;
            }
            catch (NoSuchFieldException f) {
                try {
                    Field f2 = c.getDeclaredField("field_73840_e");
                    return f2;
                }
                catch (NoSuchFieldException noSuchFieldException) {
                    for (Field f3 : c.getDeclaredFields()) {
                        Class<?> type = f3.getType();
                        if (!type.getName().equals("net.minecraft.client.gui.GuiNewChat") && !type.getSimpleName().equals("GuiNewChat")) continue;
                        return f3;
                    }
                    continue;
                }
            }
        }
        return null;
    }

    private Field findSubtitleField(Class<?> clazz) {
        for (Class<?> c = clazz; c != null; c = c.getSuperclass()) {
            for (Field f : c.getDeclaredFields()) {
                Class<?> type = f.getType();
                if (!type.getName().equals("net.minecraft.client.gui.GuiSubtitleOverlay") && !type.getSimpleName().equals("GuiSubtitleOverlay")) continue;
                return f;
            }
        }
        return null;
    }

    private void copyGuiIngameFields(GuiIngame from, GuiIngame to) throws Exception {
        for (Class<?> c = from.getClass(); c != null; c = c.getSuperclass()) {
            Field[] fields;
            for (Field f : fields = c.getDeclaredFields()) {
                if (Modifier.isStatic(f.getModifiers())) continue;
                try {
                    f.setAccessible(true);
                    this.removeFinalIfPresent(f);
                    f.set(to, f.get(from));
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
        }
    }

    private void removeFinalIfPresent(Field f) {
        try {
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(f, f.getModifiers() & 0xFFFFFFEF);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }
}

