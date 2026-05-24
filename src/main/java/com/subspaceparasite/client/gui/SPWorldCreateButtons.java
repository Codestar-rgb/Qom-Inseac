/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiCreateWorld
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.resources.I18n
 *  net.minecraftforge.client.event.GuiScreenEvent$ActionPerformedEvent$Post
 *  net.minecraftforge.client.event.GuiScreenEvent$ActionPerformedEvent$Pre
 *  net.minecraftforge.client.event.GuiScreenEvent$InitGuiEvent$Post
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.subspaceparasite.client.gui;

import com.subspaceparasite.client.gui.GuiSPWorldSettings;
import com.subspaceparasite.util.config.SPConfig;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid="subspaceparasite", value={Side.CLIENT})
public final class SPWorldCreateButtons {
    private static final int BTN_SP_WORLD_SETTINGS = 50001;
    private static final boolean ENABLE_SP_WORLD_SETTINGS_BUTTON = true;

    private SPWorldCreateButtons() {
    }

    @SubscribeEvent
    public static void onInitGuiPost(GuiScreenEvent.InitGuiEvent.Post e) {
        if (!SPConfig.worldGIU) {
            return;
        }
        if (!(e.getGui() instanceof GuiCreateWorld)) {
            return;
        }
        SPWorldCreateButtons.layoutSrpButton(e.getButtonList());
    }

    private static GuiButton findMoreWorldOptionsButton(List<GuiButton> buttons) {
        String moreOptions = I18n.func_135052_a((String)"selectWorld.moreWorldOptions", (Object[])new Object[0]);
        for (GuiButton b : buttons) {
            if (b == null || b.field_146126_j == null || !b.field_146126_j.startsWith(moreOptions)) continue;
            return b;
        }
        return null;
    }

    @SubscribeEvent
    public static void onActionPerformedPost(GuiScreenEvent.ActionPerformedEvent.Post e) {
        if (!SPConfig.worldGIU) {
            return;
        }
        if (!(e.getGui() instanceof GuiCreateWorld)) {
            return;
        }
        SPWorldCreateButtons.layoutSrpButton(e.getButtonList());
        if (e.getButton() == null) {
            return;
        }
        if (!SPWorldCreateButtons.isOnMainCreateWorldPage(e.getButtonList())) {
            return;
        }
        if (e.getButton().field_146127_k == 50001) {
            GuiCreateWorld parent = (GuiCreateWorld)e.getGui();
            Minecraft.func_71410_x().func_147108_a((GuiScreen)new GuiSPWorldSettings((GuiScreen)parent));
        }
    }

    private static void layoutSrpButton(List<GuiButton> buttons) {
        GuiButton srp = SPWorldCreateButtons.findButton(buttons, 50001);
        if (srp == null) {
            srp = new GuiButton(50001, 0, 0, 150, 20, I18n.func_135052_a((String)"gui.subspaceparasite.worldsettings.open", (Object[])new Object[0]));
            buttons.add(srp);
        }
        srp.field_146126_j = I18n.func_135052_a((String)"gui.subspaceparasite.worldsettings.open", (Object[])new Object[0]);
        GuiButton more = SPWorldCreateButtons.findMoreWorldOptionsButton(buttons);
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
            if (b == null || b.field_146127_k != id) continue;
            return b;
        }
        return null;
    }

    private static boolean isOnMainCreateWorldPage(List<GuiButton> buttons) {
        String moreOptions = I18n.func_135052_a((String)"selectWorld.moreWorldOptions", (Object[])new Object[0]);
        for (GuiButton b : buttons) {
            if (b == null || b.field_146126_j == null || !b.field_146126_j.startsWith(moreOptions)) continue;
            return true;
        }
        return false;
    }

    @SubscribeEvent
    public static void onActionPerformedPre(GuiScreenEvent.ActionPerformedEvent.Pre e) {
        String clicked;
        if (!(e.getGui() instanceof GuiCreateWorld)) {
            return;
        }
        if (e.getButton() == null) {
            return;
        }
        List buttons = e.getButtonList();
        GuiButton srp = SPWorldCreateButtons.findButton(buttons, 50001);
        if (srp == null) {
            return;
        }
        String moreOptions = I18n.func_135052_a((String)"selectWorld.moreWorldOptions", (Object[])new Object[0]);
        String done = I18n.func_135052_a((String)"gui.done", (Object[])new Object[0]);
        String string = clicked = e.getButton().field_146126_j == null ? "" : e.getButton().field_146126_j;
        if (clicked.startsWith(moreOptions)) {
            srp.field_146125_m = false;
            srp.field_146124_l = false;
            return;
        }
    }
}

