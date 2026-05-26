/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiTextField
 *  net.minecraft.client.resources.I18n
 *  net.minecraftforge.common.config.ConfigCategory
 *  net.minecraftforge.common.config.Configuration
 *  net.minecraftforge.common.config.Property
 */
package com.subspaceparasite.client.gui;

import com.subspaceparasite.client.gui.SPConfigFile;
import com.subspaceparasite.client.gui.SPConfigList;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class GuiSPConfigView
extends GuiScreen {
    private GuiTextField searchField;
    private String lastSearch = "";
    private List<SPConfigList.Entry> allEntries;
    private final GuiScreen parent;
    private final SPConfigFile which;
    private static final int BTN_BACK = 0;
    private Configuration cfg;
    private SPConfigList list;
    private List<String> hoverTooltip;

    public GuiSPConfigView(GuiScreen parent, SPConfigFile which) {
        this.parent = parent;
        this.which = which;
    }

    public void func_73866_w_() {
        this.field_146292_n.clear();
        this.hoverTooltip = null;
        this.field_146292_n.add(new GuiButton(0, this.field_146294_l / 2 - 100, this.field_146295_m - 28, 200, 20, I18n.func_135052_a((String)"gui.back", (Object[])new Object[0])));
        File cfgFile = new File(new File(Minecraft.func_71410_x().field_71412_D, "config"), this.which.relativePath);
        this.cfg = new Configuration(cfgFile);
        try {
            this.cfg.load();
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.allEntries = this.buildEntries(this.cfg);
        this.searchField = new GuiTextField(9001, this.field_146289_q, this.field_146294_l / 2 - 140, 44, 280, 18);
        this.searchField.func_146203_f(64);
        this.searchField.func_146195_b(true);
        List<SPConfigList.Entry> filtered = this.filterEntries(this.allEntries, "");
        this.list = new SPConfigList(this, this.field_146297_k, this.field_146294_l, this.field_146295_m, 66, this.field_146295_m - 36, 18, filtered);
    }

    private List<SPConfigList.Entry> buildEntries(Configuration cfg) {
        ArrayList<SPConfigList.Entry> out = new ArrayList<SPConfigList.Entry>();
        if (cfg == null) {
            out.add(SPConfigList.Entry.header("Config is null"));
            return out;
        }
        for (String catName : cfg.getCategoryNames()) {
            ConfigCategory cat = cfg.getCategory(catName);
            if (cat == null) continue;
            out.add(SPConfigList.Entry.header(catName));
            for (Map.Entry kv : cat.entrySet()) {
                Property p = (Property)kv.getValue();
                if (p == null) continue;
                String key = p.getName();
                String value = GuiSPConfigView.prettyValue(p);
                String comment = p.getComment();
                out.add(SPConfigList.Entry.prop(catName, key, value, comment));
            }
        }
        if (out.isEmpty()) {
            out.add(SPConfigList.Entry.header("(No entries found)"));
        }
        return out;
    }

    private static String prettyValue(Property p) {
        try {
            switch (p.getType()) {
                case BOOLEAN: {
                    return String.valueOf(p.getBoolean());
                }
                case INTEGER: {
                    if (p.isList()) {
                        return GuiSPConfigView.intListToString(p.getIntList());
                    }
                    return String.valueOf(p.getInt());
                }
                case DOUBLE: {
                    if (p.isList()) {
                        return GuiSPConfigView.doubleListToString(p.getDoubleList());
                    }
                    return String.valueOf(p.getDouble());
                }
            }
            if (p.isList()) {
                return GuiSPConfigView.stringListToString(p.getStringList());
            }
            return p.getString();
        }
        catch (Exception e) {
            return p.getString();
        }
    }

    private List<SPConfigList.Entry> filterEntries(List<SPConfigList.Entry> src, String query) {
        String q;
        ArrayList<SPConfigList.Entry> out = new ArrayList<SPConfigList.Entry>();
        if (src == null) {
            return out;
        }
        String string = q = query == null ? "" : query.trim().toLowerCase(Locale.ROOT);
        if (q.isEmpty()) {
            return new ArrayList<SPConfigList.Entry>(src);
        }
        String pendingHeader = null;
        boolean headerAdded = false;
        for (SPConfigList.Entry e : src) {
            if (e.header) {
                pendingHeader = e.headerText;
                headerAdded = false;
                continue;
            }
            String hay = (e.category == null ? "" : e.category) + " " + (e.key == null ? "" : e.key) + " " + (e.value == null ? "" : e.value) + " " + (e.comment == null ? "" : e.comment);
            if (!hay.toLowerCase(Locale.ROOT).contains(q)) continue;
            if (pendingHeader != null && !headerAdded) {
                out.add(SPConfigList.Entry.header(pendingHeader));
                headerAdded = true;
            }
            out.add(e);
        }
        if (out.isEmpty()) {
            out.add(SPConfigList.Entry.header("(No matches)"));
        }
        return out;
    }

    protected void func_73869_a(char typedChar, int keyCode) throws IOException {
        if (this.searchField != null && this.searchField.func_146201_a(typedChar, keyCode)) {
            String now = this.searchField.func_146179_b();
            if (!now.equals(this.lastSearch)) {
                this.lastSearch = now;
                List<SPConfigList.Entry> filtered = this.filterEntries(this.allEntries, now);
                this.list = new SPConfigList(this, this.field_146297_k, this.field_146294_l, this.field_146295_m, 66, this.field_146295_m - 36, 18, filtered);
            }
            return;
        }
        super.func_73869_a(typedChar, keyCode);
    }

    protected void func_73864_a(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.func_73864_a(mouseX, mouseY, mouseButton);
        if (this.searchField != null) {
            this.searchField.func_146192_a(mouseX, mouseY, mouseButton);
        }
    }

    protected void func_146286_b(int mouseX, int mouseY, int state) {
        super.func_146286_b(mouseX, mouseY, state);
    }

    private static String intListToString(int[] a) {
        if (a == null) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a.length; ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(a[i]);
        }
        return sb.append("]").toString();
    }

    private static String doubleListToString(double[] a) {
        if (a == null) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a.length; ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(a[i]);
        }
        return sb.append("]").toString();
    }

    private static String stringListToString(String[] a) {
        if (a == null) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a.length; ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(a[i]);
        }
        return sb.append("]").toString();
    }

    protected void func_146284_a(GuiButton button) throws IOException {
        if (button.field_146127_k == 0) {
            this.field_146297_k.func_147108_a(this.parent);
        }
    }

    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        this.func_146276_q_();
        this.func_73732_a(this.field_146289_q, I18n.func_135052_a((String)this.which.titleKey, (Object[])new Object[0]), this.field_146294_l / 2, 14, 0xFFFFFF);
        this.func_73732_a(this.field_146289_q, I18n.func_135052_a((String)"gui.subspaceparasite.config.subtitle.readonly", (Object[])new Object[0]), this.field_146294_l / 2, 28, 0xAAAAAA);
        this.hoverTooltip = null;
        if (this.list != null) {
            this.list.func_148128_a(mouseX, mouseY, partialTicks);
        }
        if (this.searchField != null) {
            this.func_73731_b(this.field_146289_q, I18n.func_135052_a((String)"gui.subspaceparasite.config.search", (Object[])new Object[0]), this.field_146294_l / 2 - 140, 34, 0xAAAAAA);
            this.searchField.func_146194_f();
            this.searchField.func_146178_a();
        }
        super.func_73863_a(mouseX, mouseY, partialTicks);
        if (this.hoverTooltip != null && !this.hoverTooltip.isEmpty()) {
            this.func_146283_a(this.hoverTooltip, mouseX, mouseY);
        }
    }

    public void applyEditedValue(SPConfigList.Entry target, String newVal) {
        if (target == null) {
            return;
        }
        if (this.allEntries == null) {
            return;
        }
        for (int i = 0; i < this.allEntries.size(); ++i) {
            SPConfigList.Entry e = this.allEntries.get(i);
            if (e == null || e.header || !GuiSPConfigView.safeEq(e.category, target.category) || !GuiSPConfigView.safeEq(e.key, target.key)) continue;
            this.allEntries.set(i, SPConfigList.Entry.prop(e.category, e.key, newVal, e.comment));
            break;
        }
        List<SPConfigList.Entry> filtered = this.filterEntries(this.allEntries, this.lastSearch);
        this.list = new SPConfigList(this, this.field_146297_k, this.field_146294_l, this.field_146295_m, 66, this.field_146295_m - 36, 18, filtered);
    }

    private static boolean safeEq(String a, String b) {
        return a == null ? b == null : a.equals(b);
    }

    public void func_146274_d() throws IOException {
        super.func_146274_d();
        if (this.list != null) {
            this.list.func_178039_p();
        }
    }

    public void setHoverTooltip(List<String> tooltip) {
        this.hoverTooltip = tooltip;
    }
}

