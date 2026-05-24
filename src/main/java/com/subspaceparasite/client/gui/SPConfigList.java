/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiSlot
 *  net.minecraft.util.text.TextFormatting
 */
package com.subspaceparasite.client.gui;

import com.subspaceparasite.client.gui.GuiSPConfigEdit;
import com.subspaceparasite.client.gui.GuiSPConfigView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.util.text.TextFormatting;

public class SPConfigList
extends GuiSlot {
    private static final int MAX_VALUE_PX = 210;
    private static final int MAX_HEADER_PX = 300;
    private static final int RIGHT_PADDING = 24;
    private static final int SCROLLBAR_OUTSET = 10;
    private static final int SCROLLBAR_VISIBLE_PX = 3;
    private final GuiSPConfigView screen;
    private final List<Entry> entries;

    public SPConfigList(GuiSPConfigView screen, Minecraft mc, int width, int height, int topIn, int bottomIn, int slotHeightIn, List<Entry> entries) {
        super(mc, width, height, topIn, bottomIn, slotHeightIn);
        this.screen = screen;
        this.entries = entries != null ? entries : new ArrayList();
        this.field_148152_e = 10;
        this.field_148151_d = width - 10;
    }

    protected int func_148137_d() {
        return this.field_148151_d - 6;
    }

    protected int func_148127_b() {
        return this.entries.size();
    }

    private String ellipsizeToWidth(String s, int maxPx) {
        if (s == null) {
            return "";
        }
        String plain = SPConfigList.stripFormatting(s);
        if (this.field_148161_k.field_71466_p.func_78256_a(plain) <= maxPx) {
            return s;
        }
        String dots = "...";
        int dotsW = this.field_148161_k.field_71466_p.func_78256_a(dots);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < plain.length(); ++i) {
            sb.append(plain.charAt(i));
            int w = this.field_148161_k.field_71466_p.func_78256_a(sb.toString());
            if (w + dotsW <= maxPx) continue;
            String cut = sb.toString().trim();
            return cut + dots;
        }
        return plain;
    }

    protected void func_148144_a(int index, boolean doubleClick, int mouseX, int mouseY) {
        Entry e = this.entries.get(index);
        if (e == null || e.header) {
            return;
        }
        this.field_148161_k.func_147108_a((GuiScreen)new GuiSPConfigEdit(this.screen, e));
    }

    protected boolean func_148131_a(int index) {
        return false;
    }

    protected void func_148123_a() {
    }

    private static String prettifyCategoryName(String raw) {
        if (raw == null) {
            return "";
        }
        String s = raw;
        if (s.startsWith("subspaceparasite:")) {
            s = s.substring("subspaceparasite:".length());
        }
        s = s.replace('.', ' ');
        String[] parts = s.split("_");
        StringBuilder out = new StringBuilder();
        for (String p : parts) {
            if (p == null || (p = p.trim()).isEmpty()) continue;
            if (out.length() > 0) {
                out.append(' ');
            }
            String lower = p.toLowerCase(Locale.ROOT);
            out.append(Character.toUpperCase(lower.charAt(0)));
            if (lower.length() <= 1) continue;
            out.append(lower.substring(1));
        }
        return out.toString().trim();
    }

    protected void func_192637_a(int idx, int right, int top, int height, int mouseX, int mouseY, float partialTicks) {
        Entry e = this.entries.get(idx);
        if (e.header) {
            String pretty = SPConfigList.prettifyCategoryName(e.headerText);
            String txt = TextFormatting.YELLOW.toString() + TextFormatting.BOLD + pretty;
            String headerDraw = this.ellipsizeToWidth(txt, 300);
            int x = this.field_148152_e + 6;
            int y = top + 4;
            this.field_148161_k.field_71466_p.func_78276_b(headerDraw, x, y, 0xFFFFFF);
            int textW = this.field_148161_k.field_71466_p.func_78256_a(SPConfigList.stripFormatting(headerDraw));
            int lineX1 = x + textW + 6;
            int lineX2 = this.field_148151_d - 6;
            int lineY = y + 8;
            if (lineX2 > lineX1) {
                Gui.func_73734_a((int)lineX1, (int)lineY, (int)lineX2, (int)(lineY + 1), (int)0x55FFFFFF);
            }
            return;
        }
        String leftText = e.key;
        int xLeft = this.field_148152_e + 6;
        this.field_148161_k.field_71466_p.func_78276_b(leftText, xLeft, top + 4, 0xFFFFFF);
        String rawRight = TextFormatting.GRAY + (e.value == null ? "" : e.value);
        String rightText = TextFormatting.GRAY + this.ellipsizeToWidth(rawRight, 210);
        int rightBoxLeft = this.field_148151_d - 6 - 210;
        int w = this.field_148161_k.field_71466_p.func_78256_a(SPConfigList.stripFormatting(rightText));
        int xRight = rightBoxLeft + Math.max(0, 210 - w);
        this.field_148161_k.field_71466_p.func_78276_b(rightText, xRight, top + 4, 0xFFFFFF);
        if (mouseX >= this.field_148152_e && mouseX <= this.field_148151_d && mouseY >= top && mouseY <= top + height && e.comment != null && !e.comment.trim().isEmpty()) {
            List<String> tip = this.wrapTooltip(e.comment.trim(), 260);
            this.screen.setHoverTooltip(tip);
        }
    }

    private static String stripFormatting(String s) {
        return s == null ? "" : TextFormatting.func_110646_a((String)s);
    }

    private List<String> wrapTooltip(String text, int maxWidthPx) {
        String[] rawLines;
        ArrayList<String> out = new ArrayList<String>();
        if (text == null) {
            return out;
        }
        for (String line : rawLines = text.split("\n")) {
            String cur = "";
            for (String word : line.split(" ")) {
                String test;
                String string = test = cur.isEmpty() ? word : cur + " " + word;
                if (this.field_148161_k.field_71466_p.func_78256_a(test) > maxWidthPx && !cur.isEmpty()) {
                    out.add(cur);
                    cur = word;
                    continue;
                }
                cur = test;
            }
            if (cur.isEmpty()) continue;
            out.add(cur);
        }
        return out;
    }

    public static class Entry {
        public final boolean header;
        public final String headerText;
        public final String category;
        public final String key;
        public final String value;
        public final String comment;

        private Entry(boolean header, String headerText, String category, String key, String value, String comment) {
            this.header = header;
            this.headerText = headerText;
            this.category = category;
            this.key = key;
            this.value = value;
            this.comment = comment;
        }

        public static Entry header(String text) {
            return new Entry(true, text, null, null, null, null);
        }

        public static Entry prop(String category, String key, String value, String comment) {
            return new Entry(false, null, category, key, value, comment);
        }
    }
}

