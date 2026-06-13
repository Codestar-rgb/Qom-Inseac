/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.resources.I18n
 */
package com.dhanantry.scapeandrunparasites.client.gui;

import com.dhanantry.scapeandrunparasites.client.gui.GuiButtonCycle;
import com.dhanantry.scapeandrunparasites.client.gui.GuiSRPConfigView;
import com.dhanantry.scapeandrunparasites.client.gui.SRPConfigFile;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import com.dhanantry.scapeandrunparasites.world.SRPWorldEntitySpawner;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiSRPWorldSettings
extends GuiScreen {
    private final GuiScreen parent;
    private static final int BTN_DONE = 0;
    private static final int BTN_DIFFICULTY = 10;
    private static final int BTN_CHALLENGE = 11;
    private static final int BTN_METEOR = 12;
    private static final int BTN_VECTORS = 13;
    private GuiButtonCycle difficultyBtn;
    private GuiButtonCycle challengeBtn;
    private static boolean pendingVectorsEnabled = false;
    private GuiButtonCycle meteorBtn;
    private GuiButtonCycle vectorsBtn;
    private static final int BTN_CFG_GENERAL = 20;
    private static final int BTN_CFG_WORLD = 21;
    private static final int BTN_CFG_SYSTEMS = 22;
    private static final int BTN_CFG_MOBS = 23;

    public GuiSRPWorldSettings(GuiScreen parent) {
        this.parent = parent;
    }

    public void func_73866_w_() {
        int topY;
        this.field_146292_n.clear();
        int centerX = this.field_146294_l / 2;
        int leftX = centerX - 155;
        int rightX = centerX + 5;
        int cfgTopY = topY = 60;
        this.difficultyBtn = new GuiButtonCycle(10, leftX, topY, 150, 20, "gui.srparasites.worldsettings.difficulty", new String[]{"gui.srparasites.worldsettings.difficulty.easy", "gui.srparasites.worldsettings.difficulty.normal", "gui.srparasites.worldsettings.difficulty.hard", "gui.srparasites.worldsettings.difficulty.impossible"}, 1);
        this.meteorBtn = new GuiButtonCycle(12, leftX, topY + 48, 150, 20, "gui.srparasites.worldsettings.meteor", new String[]{"gui.srparasites.worldsettings.meteor.on", "gui.srparasites.worldsettings.meteor.off"}, 1);
        this.field_146292_n.add(this.difficultyBtn);
        this.field_146292_n.add(this.meteorBtn);
        this.field_146292_n.add(new GuiButton(0, centerX - 100, this.field_146295_m - 28, 200, 20, I18n.func_135052_a((String)"gui.srparasites.worldsettings.done", (Object[])new Object[0])));
    }

    protected void func_146284_a(GuiButton button) throws IOException {
        if (button.field_146127_k == 0) {
            this.field_146297_k.func_147108_a(this.parent);
            return;
        }
        if (button instanceof GuiButtonCycle) {
            GuiButtonCycle cyc = (GuiButtonCycle)button;
            cyc.cycle();
        }
        if (button.field_146127_k == 12) {
            boolean bl = SRPWorldEntitySpawner.triggerSPAWNING = !SRPWorldEntitySpawner.triggerSPAWNING;
        }
        if (button.field_146127_k == 10) {
            SRPWorldEntitySpawner.choiceNUMBER = (SRPWorldEntitySpawner.choiceNUMBER + 1) % 4;
        }
        if (button.field_146127_k == 20) {
            this.field_146297_k.func_147108_a((GuiScreen)new GuiSRPConfigView(this, SRPConfigFile.GENERAL));
            return;
        }
        if (button.field_146127_k == 21) {
            this.field_146297_k.func_147108_a((GuiScreen)new GuiSRPConfigView(this, SRPConfigFile.WORLD));
            return;
        }
        if (button.field_146127_k == 22) {
            this.field_146297_k.func_147108_a((GuiScreen)new GuiSRPConfigView(this, SRPConfigFile.SYSTEMS));
            return;
        }
        if (button.field_146127_k == 23) {
            this.field_146297_k.func_147108_a((GuiScreen)new GuiSRPConfigView(this, SRPConfigFile.MOBS));
            return;
        }
    }

    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        if (SRPWorldEntitySpawner.triggerSPAWNING && this.meteorBtn.field_146126_j.contains("Off")) {
            this.meteorBtn.cycle();
        }
        if (this.difficultyBtn.field_146126_j.contains("Easy") && SRPWorldEntitySpawner.choiceNUMBER != 0) {
            this.difficultyBtn.cycle();
        } else if (this.difficultyBtn.field_146126_j.contains("Normal") && SRPWorldEntitySpawner.choiceNUMBER != 1) {
            this.difficultyBtn.cycle();
        } else if (this.difficultyBtn.field_146126_j.contains("Hard") && SRPWorldEntitySpawner.choiceNUMBER != 2) {
            this.difficultyBtn.cycle();
        } else if (this.difficultyBtn.field_146126_j.contains("Impossible") && SRPWorldEntitySpawner.choiceNUMBER != 3) {
            this.difficultyBtn.cycle();
        }
        this.func_146276_q_();
        this.func_73732_a(this.field_146289_q, I18n.func_135052_a((String)"gui.srparasites.worldsettings.title", (Object[])new Object[0]), this.field_146294_l / 2, 20, 0xFFFFFF);
        this.func_73732_a(this.field_146289_q, I18n.func_135052_a((String)"gui.srparasites.worldsettings.subtitle", (Object[])new Object[0]), this.field_146294_l / 2, 34, 0xAAAAAA);
        super.func_73863_a(mouseX, mouseY, partialTicks);
        this.drawTooltips(mouseX, mouseY);
    }

    private void drawTooltips(int mouseX, int mouseY) {
        if (this.difficultyBtn != null && this.difficultyBtn.func_146115_a()) {
            String opt = this.difficultyBtn.getCurrentOptionKey();
            if ("gui.srparasites.worldsettings.challenge.off".equals(opt)) {
                this.func_146283_a(Arrays.asList(I18n.func_135052_a((String)"gui.srparasites.worldsettings.tooltip.challenge.off", (Object[])new Object[0])), mouseX, mouseY);
            }
            if ("gui.srparasites.worldsettings.difficulty.easy".equals(opt)) {
                this.func_146283_a(Arrays.asList(I18n.func_135052_a((String)"gui.srparasites.worldsettings.tooltip.difficulty.easy", (Object[])new Object[0])), mouseX, mouseY);
            } else if ("gui.srparasites.worldsettings.difficulty.normal".equals(opt)) {
                this.func_146283_a(Arrays.asList(I18n.func_135052_a((String)"gui.srparasites.worldsettings.tooltip.difficulty.normal", (Object[])new Object[0])), mouseX, mouseY);
            } else if ("gui.srparasites.worldsettings.difficulty.hard".equals(opt)) {
                this.func_146283_a(Arrays.asList(I18n.func_135052_a((String)"gui.srparasites.worldsettings.tooltip.difficulty.hard", (Object[])new Object[0])), mouseX, mouseY);
            } else if ("gui.srparasites.worldsettings.difficulty.impossible".equals(opt)) {
                this.func_146283_a(Arrays.asList(I18n.func_135052_a((String)"gui.srparasites.worldsettings.tooltip.difficulty.impossible", (Object[])new Object[0])), mouseX, mouseY);
            }
            return;
        }
        if (this.challengeBtn != null && this.challengeBtn.func_146115_a()) {
            String optKey = this.challengeBtn.getCurrentOptionKey();
            if ("gui.srparasites.worldsettings.challenge.c1".equals(optKey)) {
                this.func_146283_a(Arrays.asList(I18n.func_135052_a((String)"gui.srparasites.worldsettings.tooltip.challenge.c1", (Object[])new Object[0])), mouseX, mouseY);
            } else if ("gui.srparasites.worldsettings.challenge.c2".equals(optKey)) {
                this.func_146283_a(Arrays.asList(I18n.func_135052_a((String)"gui.srparasites.worldsettings.tooltip.challenge.c2", (Object[])new Object[0])), mouseX, mouseY);
            } else if ("gui.srparasites.worldsettings.challenge.c3".equals(optKey)) {
                this.func_146283_a(Arrays.asList(I18n.func_135052_a((String)"gui.srparasites.worldsettings.tooltip.challenge.c3", (Object[])new Object[0])), mouseX, mouseY);
            } else {
                this.func_146283_a(Arrays.asList(I18n.func_135052_a((String)"gui.srparasites.worldsettings.tooltip.challenge", (Object[])new Object[0])), mouseX, mouseY);
            }
            return;
        }
        if (this.meteorBtn != null && this.meteorBtn.func_146115_a()) {
            int dayTicks = SRPConfig.dayTickValue > 0 ? SRPConfig.dayTickValue : 24000;
            int gateTicks = SRPConfig.spawnDays;
            int checkTicks = SRPConfigWorld.meteorTick;
            double gateDays = (double)gateTicks / (double)dayTicks;
            double checkDays = (double)checkTicks / (double)dayTicks;
            double chancePct = SRPConfigWorld.meteorChance * 100.0;
            String line = I18n.func_135052_a((String)"gui.srparasites.worldsettings.tooltip.meteor", (Object[])new Object[]{String.format("%.2f", gateDays), String.format("%.3f", checkDays), String.format("%,d", checkTicks), String.format("%.2f", chancePct)});
            this.func_146283_a(Collections.singletonList(line), mouseX, mouseY);
            return;
        }
    }

    public boolean func_73868_f() {
        return false;
    }
}

