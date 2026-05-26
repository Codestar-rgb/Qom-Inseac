/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiTextField
 *  net.minecraft.client.resources.I18n
 */
package com.subspaceparasite.client.gui;

import com.subspaceparasite.client.gui.GuiSPConfigView;
import com.subspaceparasite.client.gui.SPConfigList;
import java.io.IOException;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;

public class GuiSPConfigEdit
extends GuiScreen {
    private final GuiSPConfigView parent;
    private final SPConfigList.Entry entry;
    private GuiTextField field;
    private static final int BTN_CANCEL = 0;
    private static final int BTN_APPLY = 1;

    public GuiSPConfigEdit(GuiSPConfigView parent, SPConfigList.Entry entry) {
        this.parent = parent;
        this.entry = entry;
    }

    public void func_73866_w_() {
        this.field_146292_n.clear();
        int cx = this.field_146294_l / 2;
        this.field = new GuiTextField(7001, this.field_146289_q, cx - 140, 70, 280, 20);
        this.field.func_146203_f(256);
        this.field.func_146180_a(this.entry.value == null ? "" : this.entry.value);
        this.field.func_146195_b(true);
        this.field_146292_n.add(new GuiButton(0, cx - 140, this.field_146295_m - 28, 138, 20, I18n.func_135052_a((String)"gui.cancel", (Object[])new Object[0])));
        this.field_146292_n.add(new GuiButton(1, cx + 2, this.field_146295_m - 28, 138, 20, I18n.func_135052_a((String)"gui.subspaceparasite.config.apply", (Object[])new Object[0])));
    }

    protected void func_146284_a(GuiButton button) throws IOException {
        if (button.field_146127_k == 0) {
            this.field_146297_k.func_147108_a((GuiScreen)this.parent);
            return;
        }
        if (button.field_146127_k == 1) {
            String newVal = this.field.func_146179_b();
            this.parent.applyEditedValue(this.entry, newVal);
            this.field_146297_k.func_147108_a((GuiScreen)this.parent);
        }
    }

    protected void func_73869_a(char typedChar, int keyCode) throws IOException {
        if (this.field != null && this.field.func_146201_a(typedChar, keyCode)) {
            return;
        }
        super.func_73869_a(typedChar, keyCode);
    }

    protected void func_73864_a(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.func_73864_a(mouseX, mouseY, mouseButton);
        if (this.field != null) {
            this.field.func_146192_a(mouseX, mouseY, mouseButton);
        }
    }

    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        this.func_146276_q_();
        this.func_73732_a(this.field_146289_q, I18n.func_135052_a((String)"gui.subspaceparasite.config.edit.title", (Object[])new Object[0]), this.field_146294_l / 2, 20, 0xFFFFFF);
        this.func_73731_b(this.field_146289_q, I18n.func_135052_a((String)"gui.subspaceparasite.config.edit.key", (Object[])new Object[]{this.entry.category, this.entry.key}), this.field_146294_l / 2 - 140, 44, 0xAAAAAA);
        if (this.field != null) {
            this.field.func_146194_f();
            this.field.func_146178_a();
        }
        this.func_73731_b(this.field_146289_q, I18n.func_135052_a((String)"gui.subspaceparasite.config.edit.todo", (Object[])new Object[0]), this.field_146294_l / 2 - 140, 98, 0x888888);
        super.func_73863_a(mouseX, mouseY, partialTicks);
    }

    public boolean func_73868_f() {
        return false;
    }
}

