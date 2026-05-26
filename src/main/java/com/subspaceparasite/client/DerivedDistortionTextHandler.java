/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraftforge.event.entity.player.ItemTooltipEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.subspaceparasite.client;

import com.subspaceparasite.bestiary.client.gui.GuiDistortionHelper;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DerivedDistortionTextHandler {
    @SubscribeEvent
    public void onItemTooltip(ItemTooltipEvent event) {
        Minecraft mc = Minecraft.func_71410_x();
        if (!GuiDistortionHelper.shouldDistortItemTooltips(mc)) {
            return;
        }
        if (event.getToolTip() == null || event.getToolTip().isEmpty()) {
            return;
        }
        List lines = event.getToolTip();
        for (int i = 0; i < lines.size(); ++i) {
            String s = (String)lines.get(i);
            if (s == null || s.isEmpty()) continue;
            lines.set(i, GuiDistortionHelper.jamText(s));
        }
    }
}

