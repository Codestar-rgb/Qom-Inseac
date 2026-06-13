/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.MathHelper
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package com.dhanantry.scapeandrunparasites.client.gui;

import com.dhanantry.scapeandrunparasites.bestiary.client.gui.GuiDistortionHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiDislodgementReport
extends GuiScreen {
    private final ItemStack stack;
    private boolean isJumbled;
    private static final ResourceLocation TEX_BG = new ResourceLocation("srparasites", "textures/gui/dislo_report_gui.png");
    private static final ResourceLocation TEX_FG = new ResourceLocation("srparasites", "textures/gui/dislo_report_fg_gui.png");
    private static final ResourceLocation TEX_ROW_ONE = new ResourceLocation("srparasites", "textures/gui/dislo_report_row_gui_one.png");
    private static final ResourceLocation TEX_ROW_TWO = new ResourceLocation("srparasites", "textures/gui/dislo_report_row_gui_two.png");
    private static final ResourceLocation TEX_ROW_THREE = new ResourceLocation("srparasites", "textures/gui/dislo_report_row_gui_three.png");
    private static final ResourceLocation TEX_ROW_UNKNOWN = new ResourceLocation("srparasites", "textures/gui/dislo_report_row_gui_unknown.png");
    private static final int ROW_TEX_W = 332;
    private static final int ROW_TEX_H = 36;
    private long printWorldTicks;
    private static final int BG_W = 360;
    private static final int BG_H = 300;
    private static final int FG_W = 332;
    private static final int FG_H = 212;
    private int fgX;
    private int fgY;
    private int fgW;
    private int fgH;
    private static final int FG_BORDER_THICKNESS = 1;
    private float uiScale = 1.0f;
    private int screenPanelX;
    private int screenPanelY;
    private int screenPanelW;
    private int screenPanelH;
    private int panelX;
    private int panelY;
    private int panelW;
    private int panelH;
    private int listX;
    private int listY;
    private int listW;
    private int listH;
    private int listScroll = 0;
    private final List<Entry> entries = new ArrayList<Entry>();

    private static ResourceLocation getRowTexForEvent(int event) {
        switch (event) {
            case 0: 
            case 6: 
            case 7: 
            case 9: 
            case 15: 
            case 16: 
            case 19: 
            case 20: {
                return TEX_ROW_ONE;
            }
            case 1: 
            case 4: 
            case 8: 
            case 10: 
            case 13: 
            case 17: 
            case 18: 
            case 22: {
                return TEX_ROW_TWO;
            }
            case 3: 
            case 11: 
            case 12: 
            case 14: 
            case 21: 
            case 25: {
                return TEX_ROW_THREE;
            }
        }
        return TEX_ROW_UNKNOWN;
    }

    private static int severityBucket(int event) {
        switch (event) {
            case 5: 
            case 23: 
            case 24: 
            case 26: 
            case 27: 
            case 28: 
            case 29: 
            case 30: {
                return 3;
            }
            case 3: 
            case 11: 
            case 12: 
            case 14: 
            case 21: 
            case 25: {
                return 2;
            }
            case 1: 
            case 4: 
            case 8: 
            case 10: 
            case 13: 
            case 17: 
            case 18: 
            case 22: {
                return 1;
            }
        }
        return 0;
    }

    private String distort(String s) {
        return GuiDistortionHelper.jamTextIfNeeded(s, this.isJumbled);
    }

    public GuiDislodgementReport(ItemStack stack) {
        this.stack = stack;
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        this.isJumbled = GuiDistortionHelper.isDistortionActive(this.field_146297_k);
        this.entries.clear();
        this.parseEntriesFromNBT();
        Collections.sort(this.entries, new Comparator<Entry>(){

            @Override
            public int compare(Entry a, Entry b) {
                int sb;
                int sa = GuiDislodgementReport.severityBucket(a.event);
                if (sa != (sb = GuiDislodgementReport.severityBucket(b.event))) {
                    return Integer.compare(sb, sa);
                }
                return Integer.compare(b.timeSec, a.timeSec);
            }
        });
        int maxW = this.field_146294_l - 24;
        int maxH = this.field_146295_m - 24;
        float s = Math.min((float)maxW / 360.0f, (float)maxH / 300.0f);
        this.uiScale = Math.min(1.0f, s);
        this.uiScale = Math.max(0.25f, this.uiScale);
        this.screenPanelW = Math.round(360.0f * this.uiScale);
        this.screenPanelH = Math.round(300.0f * this.uiScale);
        this.screenPanelX = (this.field_146294_l - this.screenPanelW) / 2;
        this.screenPanelY = (this.field_146295_m - this.screenPanelH) / 2;
        this.panelX = 0;
        this.panelY = 0;
        this.panelW = 360;
        this.panelH = 300;
        int pad = 14;
        int top = this.panelY + 44;
        int bottom = this.panelY + this.panelH - 44;
        this.fgX = this.panelX + pad;
        this.fgY = top;
        this.fgW = this.panelW - pad * 2;
        this.fgH = bottom - top;
        int inset = 3;
        this.listX = this.fgX + inset;
        this.listY = this.fgY + inset;
        this.listW = this.fgW - inset * 2;
        this.listH = this.fgH - inset * 2;
        this.listScroll = 0;
        this.field_146292_n.clear();
        this.field_146292_n.add(new GuiButtonNoShadow(0, this.screenPanelX + this.screenPanelW - 4 - 80, this.screenPanelY + this.screenPanelH - 24, 80, 20, this.distort(I18n.func_135052_a((String)"gui.done", (Object[])new Object[0]))));
    }

    public boolean func_73868_f() {
        return false;
    }

    protected void func_146284_a(GuiButton button) {
        if (button.field_146127_k == 0) {
            this.field_146297_k.func_147108_a(null);
        }
    }

    protected void func_73869_a(char typedChar, int keyCode) throws IOException {
        if (keyCode == 1) {
            this.field_146297_k.func_147108_a(null);
            return;
        }
        super.func_73869_a(typedChar, keyCode);
    }

    private int toDesignX(int mouseX) {
        return Math.round((float)(mouseX - this.screenPanelX) / this.uiScale);
    }

    private int toDesignY(int mouseY) {
        return Math.round((float)(mouseY - this.screenPanelY) / this.uiScale);
    }

    private boolean isInList(int dx, int dy) {
        return dx >= this.listX && dx < this.listX + this.listW && dy >= this.listY && dy < this.listY + this.listH;
    }

    public void func_146274_d() throws IOException {
        super.func_146274_d();
        int dwheel = Mouse.getEventDWheel();
        if (dwheel != 0) {
            int dy;
            int mx = Mouse.getEventX() * this.field_146294_l / this.field_146297_k.field_71443_c;
            int my = this.field_146295_m - Mouse.getEventY() * this.field_146295_m / this.field_146297_k.field_71440_d - 1;
            int dx = this.toDesignX(mx);
            if (this.isInList(dx, dy = this.toDesignY(my))) {
                int rowH = 36;
                this.clampScrollToGrid(rowH);
                int maxScroll = this.getMaxScroll(rowH);
                this.listScroll = dwheel < 0 ? Math.min(maxScroll, this.listScroll + rowH) : Math.max(0, this.listScroll - rowH);
            }
        }
    }

    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        boolean wasJumbled = this.isJumbled;
        this.isJumbled = GuiDistortionHelper.isDistortionActive(this.field_146297_k);
        if (wasJumbled != this.isJumbled && !this.field_146292_n.isEmpty()) {
            ((GuiButton)this.field_146292_n.get((int)0)).field_146126_j = this.distort(I18n.func_135052_a((String)"gui.done", (Object[])new Object[0]));
        }
        GL11.glDisable((int)3089);
        GlStateManager.func_179094_E();
        GlStateManager.func_179109_b((float)this.screenPanelX, (float)this.screenPanelY, (float)0.0f);
        GlStateManager.func_179152_a((float)this.uiScale, (float)this.uiScale, (float)1.0f);
        this.field_146297_k.func_110434_K().func_110577_a(TEX_BG);
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.func_179147_l();
        GlStateManager.func_179120_a((int)770, (int)771, (int)1, (int)0);
        GuiDislodgementReport.func_152125_a((int)this.panelX, (int)this.panelY, (float)0.0f, (float)0.0f, (int)360, (int)300, (int)360, (int)300, (float)360.0f, (float)300.0f);
        GlStateManager.func_179084_k();
        String title = this.distort(I18n.func_135052_a((String)"gui.srparasites.dislodgement.title", (Object[])new Object[0]));
        this.drawCenteredNoShadow(title, this.panelX + this.panelW / 2, this.panelY + 10, -15658735);
        this.field_146297_k.func_110434_K().func_110577_a(TEX_FG);
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.func_179147_l();
        GlStateManager.func_179120_a((int)770, (int)771, (int)1, (int)0);
        GuiDislodgementReport.func_152125_a((int)this.fgX, (int)this.fgY, (float)0.0f, (float)0.0f, (int)332, (int)212, (int)this.fgW, (int)this.fgH, (float)332.0f, (float)212.0f);
        GlStateManager.func_179084_k();
        this.drawEntriesList();
        GlStateManager.func_179121_F();
        super.func_73863_a(mouseX, mouseY, partialTicks);
    }

    public void func_175273_b(Minecraft mcIn, int w, int h) {
        super.func_175273_b(mcIn, w, h);
        this.func_73866_w_();
    }

    private void drawEntriesList() {
        int rowH = 36;
        this.clampScrollToGrid(rowH);
        int y0 = this.listY - this.listScroll;
        ScaledResolution sr = new ScaledResolution(this.field_146297_k);
        int scale = sr.func_78325_e();
        int fbW = this.field_146297_k.func_147110_a().field_147621_c;
        int fbH = this.field_146297_k.func_147110_a().field_147618_d;
        float guiX0 = (float)this.screenPanelX + (float)this.listX * this.uiScale;
        float guiY0 = (float)this.screenPanelY + (float)this.listY * this.uiScale;
        float guiW = (float)this.listW * this.uiScale;
        float guiH = (float)this.listH * this.uiScale;
        int scX = MathHelper.func_76141_d((float)(guiX0 * (float)scale));
        int scW = MathHelper.func_76123_f((float)(guiW * (float)scale));
        int scYTop = MathHelper.func_76141_d((float)(guiY0 * (float)scale));
        int scH = MathHelper.func_76123_f((float)(guiH * (float)scale));
        int scY = fbH - (scYTop + scH);
        int pad = Math.max(1, scale);
        scY -= pad;
        scW += pad * 2;
        scH += pad * 2;
        if ((scX -= pad) < 0) {
            scW += scX;
            scX = 0;
        }
        if (scY < 0) {
            scH += scY;
            scY = 0;
        }
        if (scX + scW > fbW) {
            scW = fbW - scX;
        }
        if (scY + scH > fbH) {
            scH = fbH - scY;
        }
        if (scW > 0 && scH > 0) {
            GL11.glEnable((int)3089);
            GL11.glScissor((int)scX, (int)scY, (int)scW, (int)scH);
        } else {
            GL11.glDisable((int)3089);
        }
        float s = 0.8f;
        for (int i = 0; i < this.entries.size(); ++i) {
            int ry = y0 + i * rowH;
            if (ry + rowH <= this.listY || ry >= this.listY + this.listH) continue;
            Entry e = this.entries.get(i);
            this.field_146297_k.func_110434_K().func_110577_a(GuiDislodgementReport.getRowTexForEvent(e.event));
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GlStateManager.func_179147_l();
            GlStateManager.func_179120_a((int)770, (int)771, (int)1, (int)0);
            GuiDislodgementReport.func_152125_a((int)this.listX, (int)ry, (float)0.0f, (float)0.0f, (int)332, (int)36, (int)this.listW, (int)rowH, (float)332.0f, (float)36.0f);
            GlStateManager.func_179084_k();
            int rem = this.getRemainingSeconds(e.timeSec);
            String timeStr = GuiDislodgementReport.formatHMS(rem);
            boolean expired = rem <= 0;
            String pre = expired ? "\u00a7m" : "";
            String post = expired ? "\u00a7r" : "";
            String line1Plain = I18n.func_135052_a((String)"gui.srparasites.dislodgement.entry.head", (Object[])new Object[]{e.dim, e.event, timeStr});
            String line2Plain = GuiDislodgementReport.formatMeaning(e.event, e.value);
            String line3Plain = I18n.func_135052_a((String)"gui.srparasites.dislodgement.entry.value", (Object[])new Object[]{e.value});
            int maxW = (int)((float)(this.listW - 6) / s);
            line1Plain = this.trimToWidth(line1Plain, maxW);
            line2Plain = this.trimToWidth(line2Plain, maxW);
            line3Plain = this.trimToWidth(line3Plain, maxW);
            String line1 = this.isJumbled ? GuiDistortionHelper.jamText(line1Plain) : pre + line1Plain + post;
            String line2 = this.isJumbled ? GuiDistortionHelper.jamText(line2Plain) : pre + line2Plain + post;
            String line3 = this.isJumbled ? GuiDistortionHelper.jamText(line3Plain) : pre + line3Plain + post;
            this.drawScaledStringNoShadow(line1, this.listX + 3, ry + 3, s, expired ? -10066330 : -15658735);
            this.drawScaledStringNoShadow(line2, this.listX + 3, ry + 14, s, expired ? -8947849 : -14540254);
            this.drawScaledStringNoShadow(line3, this.listX + 3, ry + 25, s, expired ? -7829368 : -13421773);
        }
        int contentH = this.entries.size() * rowH;
        int maxScroll = Math.max(0, contentH - this.listH);
        if (maxScroll > 0) {
            int barW = 4;
            int barX0 = this.listX + this.listW - barW - 1;
            int barX1 = this.listX + this.listW - 1;
            GuiDislodgementReport.func_73734_a((int)barX0, (int)this.listY, (int)barX1, (int)(this.listY + this.listH), (int)0x22000000);
            int thumbH = Math.max(10, (int)((float)this.listH / (float)contentH * (float)this.listH));
            int thumbY = this.listY + (int)((float)this.listScroll / (float)maxScroll * (float)(this.listH - thumbH));
            GuiDislodgementReport.func_73734_a((int)barX0, (int)thumbY, (int)barX1, (int)(thumbY + thumbH), (int)0x55000000);
        }
        if (this.entries.isEmpty()) {
            String none = this.distort(I18n.func_135052_a((String)"gui.srparasites.dislodgement.none", (Object[])new Object[0]));
            this.drawCenteredNoShadow(none, this.listX + this.listW / 2, this.listY + this.listH / 2 - 4, -12303292);
        }
        GL11.glDisable((int)3089);
    }

    private void parseEntriesFromNBT() {
        String code;
        NBTTagCompound t = this.stack.func_77978_p();
        if (t != null) {
            int day = t.func_74762_e("PrintDay");
            int time = t.func_74762_e("PrintTime");
            this.printWorldTicks = (long)day * 24000L + (long)time;
        } else {
            this.printWorldTicks = 0L;
        }
        String string = code = t != null && t.func_74764_b("DislodgementCode") ? t.func_74779_i("DislodgementCode") : "";
        if (code == null || code.trim().isEmpty()) {
            return;
        }
        String[] parts = code.split(";");
        if (parts.length < 4 || parts.length % 4 != 0) {
            return;
        }
        for (int i = 0; i < parts.length; i += 4) {
            String dim = parts[i].trim();
            int event = GuiDislodgementReport.safeParseInt(parts[i + 1].trim(), -1);
            String value = parts[i + 2].trim();
            int time = GuiDislodgementReport.safeParseInt(parts[i + 3].trim(), 0);
            this.entries.add(new Entry(dim, event, value, time));
        }
    }

    private static int safeParseInt(String s, int fallback) {
        try {
            return Integer.parseInt(s);
        }
        catch (Throwable t) {
            return fallback;
        }
    }

    private static String meaningKey(int event) {
        switch (event) {
            case 0: {
                return "srparasites.dislodgement.event.0";
            }
            case 1: {
                return "srparasites.dislodgement.event.1";
            }
            case 2: {
                return "srparasites.dislodgement.event.2";
            }
            case 3: {
                return "srparasites.dislodgement.event.3";
            }
            case 4: {
                return "srparasites.dislodgement.event.4";
            }
            case 6: {
                return "srparasites.dislodgement.event.6";
            }
            case 7: {
                return "srparasites.dislodgement.event.7";
            }
            case 8: {
                return "srparasites.dislodgement.event.8";
            }
            case 9: {
                return "srparasites.dislodgement.event.9";
            }
            case 10: {
                return "srparasites.dislodgement.event.10";
            }
            case 11: {
                return "srparasites.dislodgement.event.11";
            }
            case 12: {
                return "srparasites.dislodgement.event.12";
            }
            case 13: {
                return "srparasites.dislodgement.event.13";
            }
            case 14: {
                return "srparasites.dislodgement.event.14";
            }
            case 15: {
                return "srparasites.dislodgement.event.15";
            }
            case 16: {
                return "srparasites.dislodgement.event.16";
            }
            case 17: {
                return "srparasites.dislodgement.event.17";
            }
            case 18: {
                return "srparasites.dislodgement.event.18";
            }
            case 19: {
                return "srparasites.dislodgement.event.19";
            }
            case 20: {
                return "srparasites.dislodgement.event.20";
            }
            case 21: {
                return "srparasites.dislodgement.event.21";
            }
            case 22: {
                return "srparasites.dislodgement.event.22";
            }
            case 25: {
                return "srparasites.dislodgement.event.25";
            }
        }
        return "srparasites.dislodgement.event.unknown";
    }

    private static String formatMeaning(int event, String value) {
        String key = GuiDislodgementReport.meaningKey(event);
        switch (event) {
            case 0: 
            case 11: 
            case 15: 
            case 16: 
            case 17: 
            case 18: 
            case 20: {
                return I18n.func_135052_a((String)key, (Object[])new Object[0]);
            }
        }
        return I18n.func_135052_a((String)key, (Object[])new Object[]{value});
    }

    private void drawCenteredNoShadow(String s, int x, int y, int color) {
        int w = this.field_146289_q.func_78256_a(s);
        this.field_146289_q.func_78276_b(s, x - w / 2, y, color);
    }

    private void drawScaledStringNoShadow(String s, float x, float y, float scale, int color) {
        GlStateManager.func_179094_E();
        GlStateManager.func_179109_b((float)x, (float)y, (float)0.0f);
        GlStateManager.func_179152_a((float)scale, (float)scale, (float)1.0f);
        this.field_146289_q.func_78276_b(s, 0, 0, color);
        GlStateManager.func_179121_F();
    }

    private static String formatHMS(int secondsIn) {
        int s = Math.max(0, secondsIn);
        int h = s / 3600;
        int m = s % 3600 / 60;
        int sec = s % 60;
        if (h > 0) {
            return String.format("%d:%02d:%02d", h, m, sec);
        }
        return String.format("%d:%02d", m, sec);
    }

    private int getRemainingSeconds(int originalSeconds) {
        if (this.field_146297_k == null || this.field_146297_k.field_71441_e == null) {
            return originalSeconds;
        }
        long now = this.field_146297_k.field_71441_e.func_72820_D();
        long elapsedTicks = now - this.printWorldTicks;
        if (elapsedTicks < 0L) {
            elapsedTicks = 0L;
        }
        int elapsedSec = (int)(elapsedTicks / 20L);
        return originalSeconds - elapsedSec;
    }

    private String trimToWidth(String s, int maxW) {
        if (this.field_146289_q.func_78256_a(s) <= maxW) {
            return s;
        }
        String ell = "...";
        int ew = this.field_146289_q.func_78256_a(ell);
        String t = s;
        while (t.length() > 0 && this.field_146289_q.func_78256_a(t) + ew > maxW) {
            t = t.substring(0, t.length() - 1);
        }
        return t + ell;
    }

    private int getMaxScroll(int rowH) {
        int contentH = this.entries.size() * rowH;
        int rawMax = Math.max(0, contentH - this.listH);
        int snappedUp = (rawMax + rowH - 1) / rowH * rowH;
        return snappedUp;
    }

    private void clampScrollToGrid(int rowH) {
        int maxScroll = this.getMaxScroll(rowH);
        if (this.listScroll < 0) {
            this.listScroll = 0;
        }
        if (this.listScroll > maxScroll) {
            this.listScroll = maxScroll;
        }
        this.listScroll = this.listScroll / rowH * rowH;
    }

    private static class Entry {
        final String dim;
        final int event;
        final String value;
        final int timeSec;

        Entry(String dim, int event, String value, int timeSec) {
            this.dim = dim;
            this.event = event;
            this.value = value;
            this.timeSec = timeSec;
        }
    }

    private static class GuiButtonNoShadow
    extends GuiButton {
        public GuiButtonNoShadow(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
            super(buttonId, x, y, widthIn, heightIn, buttonText);
        }

        public void func_191745_a(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            if (!this.field_146125_m) {
                return;
            }
            mc.func_110434_K().func_110577_a(field_146122_a);
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.field_146123_n = mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g;
            int hoverState = this.func_146114_a(this.field_146123_n);
            GlStateManager.func_179147_l();
            GlStateManager.func_179120_a((int)770, (int)771, (int)1, (int)0);
            this.func_73729_b(this.field_146128_h, this.field_146129_i, 0, 46 + hoverState * 20, this.field_146120_f / 2, this.field_146121_g);
            this.func_73729_b(this.field_146128_h + this.field_146120_f / 2, this.field_146129_i, 200 - this.field_146120_f / 2, 46 + hoverState * 20, this.field_146120_f / 2, this.field_146121_g);
            int textColor = -15066598;
            if (!this.field_146124_l) {
                textColor = -8947849;
            }
            int sw = mc.field_71466_p.func_78256_a(this.field_146126_j);
            mc.field_71466_p.func_78276_b(this.field_146126_j, this.field_146128_h + this.field_146120_f / 2 - sw / 2, this.field_146129_i + (this.field_146121_g - 8) / 2, textColor);
        }
    }
}

