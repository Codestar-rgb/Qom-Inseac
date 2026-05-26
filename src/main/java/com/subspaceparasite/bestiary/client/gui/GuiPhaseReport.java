/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package com.subspaceparasite.bestiary.client.gui;

import com.subspaceparasite.bestiary.client.gui.GuiDistortionHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiPhaseReport
extends GuiScreen {
    private final ItemStack stack;
    private static final ResourceLocation TEX_BG = new ResourceLocation("subspaceparasite", "textures/gui/dislo_report_gui.png");
    private static final ResourceLocation TEX_FG = new ResourceLocation("subspaceparasite", "textures/gui/dislo_report_fg_gui.png");
    private static final int BG_W = 360;
    private static final int BG_H = 300;
    private static final int FG_W = 332;
    private static final int FG_H = 212;
    private static final int FG_BORDER_THICKNESS = 1;
    private boolean isJumbled;
    private float uiScale = 1.0f;
    private int screenPanelX;
    private int screenPanelY;
    private int screenPanelW;
    private int screenPanelH;
    private int panelX;
    private int panelY;
    private int panelW;
    private int panelH;
    private int fgX;
    private int fgY;
    private int fgW;
    private int fgH;
    private int textX;
    private int textY;
    private int textW;
    private int textH;
    private int chartX;
    private int chartY;
    private int chartW;
    private int chartH;
    private int bottomLineY;
    private int statusY;
    private int textScroll = 0;
    private final List<String> lines = new ArrayList<String>();
    private int phase;
    private int totalPoints;
    private int nextPoints;
    private String progress;
    private int cooldown;
    private boolean canGain;
    private boolean canLoss;
    private int dimension;
    private int mobcap;
    private int generation;
    private int genTicks;
    private int parasiteCount;
    private int cothCount;
    private int totalMobCount;

    public GuiPhaseReport(ItemStack stack) {
        this.stack = stack;
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        this.readNBT();
        this.isJumbled = GuiDistortionHelper.isDistortionActive(this.field_146297_k);
        this.buildLines();
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
        int contentX = this.fgX + inset;
        int contentY = this.fgY + inset;
        int contentW = this.fgW - inset * 2;
        int contentH = this.fgH - inset * 2;
        int gap = 10;
        this.textX = contentX + 8;
        this.textY = contentY + 8;
        this.textH = contentH - 42;
        this.textW = Math.min(185, contentW - 110);
        this.chartW = contentW - this.textW - gap - 8;
        this.chartX = this.textX + this.textW + gap;
        this.chartY = contentY + 8;
        this.chartH = contentH - 42;
        this.bottomLineY = contentY + contentH - 24;
        this.statusY = this.bottomLineY + 8;
        this.textScroll = 0;
        this.field_146292_n.clear();
        this.field_146292_n.add(new GuiButtonNoShadow(0, this.screenPanelX + this.screenPanelW - 4 - 80, this.screenPanelY + this.screenPanelH - 24, 80, 20, GuiDistortionHelper.jamTextIfNeeded(I18n.func_135052_a((String)"gui.done", (Object[])new Object[0]), this.isJumbled)));
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

    public void func_175273_b(Minecraft mcIn, int w, int h) {
        super.func_175273_b(mcIn, w, h);
        this.func_73866_w_();
    }

    private int toDesignX(int mouseX) {
        return Math.round((float)(mouseX - this.screenPanelX) / this.uiScale);
    }

    private int toDesignY(int mouseY) {
        return Math.round((float)(mouseY - this.screenPanelY) / this.uiScale);
    }

    private boolean isInText(int dx, int dy) {
        return dx >= this.textX && dx < this.textX + this.textW && dy >= this.textY && dy < this.textY + this.textH;
    }

    public void func_146274_d() throws IOException {
        super.func_146274_d();
        int dwheel = Mouse.getEventDWheel();
        if (dwheel != 0) {
            int dy;
            int mx = Mouse.getEventX() * this.field_146294_l / this.field_146297_k.field_71443_c;
            int my = this.field_146295_m - Mouse.getEventY() * this.field_146295_m / this.field_146297_k.field_71440_d - 1;
            int dx = this.toDesignX(mx);
            if (this.isInText(dx, dy = this.toDesignY(my))) {
                int maxScroll = this.getMaxScroll();
                this.textScroll = dwheel < 0 ? Math.min(maxScroll, this.textScroll + 10) : Math.max(0, this.textScroll - 10);
            }
        }
    }

    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        GL11.glDisable((int)3089);
        GlStateManager.func_179094_E();
        GlStateManager.func_179109_b((float)this.screenPanelX, (float)this.screenPanelY, (float)0.0f);
        GlStateManager.func_179152_a((float)this.uiScale, (float)this.uiScale, (float)1.0f);
        this.field_146297_k.func_110434_K().func_110577_a(TEX_BG);
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.func_179147_l();
        GlStateManager.func_179120_a((int)770, (int)771, (int)1, (int)0);
        GuiPhaseReport.func_152125_a((int)this.panelX, (int)this.panelY, (float)0.0f, (float)0.0f, (int)360, (int)300, (int)360, (int)300, (float)360.0f, (float)300.0f);
        GlStateManager.func_179084_k();
        String title = GuiDistortionHelper.jamTextIfNeeded(I18n.func_135052_a((String)"gui.subspaceparasite.phase_report.title", (Object[])new Object[0]), this.isJumbled);
        this.drawCenteredNoShadow(title, this.panelX + this.panelW / 2, this.panelY + 10, -10066330);
        this.field_146297_k.func_110434_K().func_110577_a(TEX_FG);
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.func_179147_l();
        GlStateManager.func_179120_a((int)770, (int)771, (int)1, (int)0);
        GuiPhaseReport.func_152125_a((int)this.fgX, (int)this.fgY, (float)0.0f, (float)0.0f, (int)332, (int)212, (int)this.fgW, (int)this.fgH, (float)332.0f, (float)212.0f);
        GlStateManager.func_179084_k();
        this.drawTextPanel();
        this.drawPieSection();
        this.drawThinHorizontalLine(this.textX, this.bottomLineY, this.textX + this.textW - 6, -10066330);
        String gainLabel = I18n.func_135052_a((String)"gui.subspaceparasite.phase_report.point_gain", (Object[])new Object[0]);
        String gainValue = this.canGain ? I18n.func_135052_a((String)"gui.subspaceparasite.phase_report.enabled", (Object[])new Object[0]) : I18n.func_135052_a((String)"gui.subspaceparasite.phase_report.disabled", (Object[])new Object[0]);
        String lossLabel = I18n.func_135052_a((String)"gui.subspaceparasite.phase_report.point_loss", (Object[])new Object[0]);
        String lossValue = this.canLoss ? I18n.func_135052_a((String)"gui.subspaceparasite.phase_report.enabled", (Object[])new Object[0]) : I18n.func_135052_a((String)"gui.subspaceparasite.phase_report.disabled", (Object[])new Object[0]);
        gainLabel = GuiDistortionHelper.jamTextIfNeeded(gainLabel, this.isJumbled);
        gainValue = GuiDistortionHelper.jamTextIfNeeded(gainValue, this.isJumbled);
        lossLabel = GuiDistortionHelper.jamTextIfNeeded(lossLabel, this.isJumbled);
        lossValue = GuiDistortionHelper.jamTextIfNeeded(lossValue, this.isJumbled);
        this.drawStatusLine(gainLabel, gainValue, this.textX, this.statusY, this.canGain ? -11141291 : -43691);
        this.drawStatusLine(lossLabel, lossValue, this.textX + 110, this.statusY, this.canLoss ? -11141291 : -43691);
        GlStateManager.func_179121_F();
        super.func_73863_a(mouseX, mouseY, partialTicks);
    }

    private void drawTextPanel() {
        this.enableDesignScissor(this.textX, this.textY, this.textW, this.textH);
        int x = this.textX;
        int y = this.textY - this.textScroll;
        int wrapWidth = this.textW - 8;
        for (String line : this.lines) {
            List wrapped = this.field_146289_q.func_78271_c(line, wrapWidth);
            for (String wrappedLine : wrapped) {
                if (y + 10 >= this.textY && y < this.textY + this.textH) {
                    this.field_146289_q.func_175065_a(wrappedLine, (float)x, (float)y, -15658735, false);
                }
                y += 10;
            }
        }
        GL11.glDisable((int)3089);
    }

    private void drawPieSection() {
        NBTTagCompound tag = this.stack.func_77978_p();
        if (tag == null || !tag.func_74764_b("PhaseValue")) {
            return;
        }
        int pure = Math.max(0, this.totalMobCount - this.parasiteCount - this.cothCount);
        int infected = Math.max(0, this.cothCount);
        int parasites = Math.max(0, this.parasiteCount);
        pure = GuiDistortionHelper.getDisplayValue(pure, this.isJumbled, 11);
        parasites = GuiDistortionHelper.getDisplayValue(parasites, this.isJumbled, 22);
        int total = pure + (infected = GuiDistortionHelper.getDisplayValue(infected, this.isJumbled, 33)) + parasites;
        if (total <= 0) {
            total = 1;
        }
        int cx = this.chartX + this.chartW / 2;
        int cy = this.chartY + 52;
        int radius = Math.min(this.chartW / 2 - 10, 38);
        String chartTitle = GuiDistortionHelper.jamTextIfNeeded(I18n.func_135052_a((String)"gui.subspaceparasite.phase_report.mob_chart", (Object[])new Object[0]), this.isJumbled);
        this.drawCenteredNoShadow(chartTitle, cx, this.chartY, -15658735);
        this.drawPieSlice(cx, cy, radius, 0.0f, 360.0f * ((float)pure / (float)total), -4210753);
        this.drawPieSlice(cx, cy, radius, 360.0f * ((float)pure / (float)total), 360.0f * ((float)(pure + parasites) / (float)total), -43691);
        this.drawPieSlice(cx, cy, radius, 360.0f * ((float)(pure + parasites) / (float)total), 360.0f, -43521);
        this.drawCircleOutline(cx, cy, radius, -11184811);
        int legendY = cy + radius + 12;
        String labelPure = I18n.func_135052_a((String)"gui.subspaceparasite.phase_report.uninfected_hosts", (Object[])new Object[0]);
        String labelParasites = I18n.func_135052_a((String)"gui.subspaceparasite.phase_report.parasite_entities", (Object[])new Object[0]);
        String labelInfected = I18n.func_135052_a((String)"gui.subspaceparasite.phase_report.infected_hosts", (Object[])new Object[0]);
        int displayPure = pure;
        int displayParasites = parasites;
        int displayInfected = infected;
        labelPure = GuiDistortionHelper.jamTextIfNeeded(labelPure, this.isJumbled);
        labelParasites = GuiDistortionHelper.jamTextIfNeeded(labelParasites, this.isJumbled);
        labelInfected = GuiDistortionHelper.jamTextIfNeeded(labelInfected, this.isJumbled);
        this.drawLegendWidget(this.chartX + 4, legendY, this.chartW - 8, 14, -10066330, labelPure, displayPure);
        this.drawLegendWidget(this.chartX + 4, legendY + 16, this.chartW - 8, 14, -43691, labelParasites, displayParasites);
        this.drawLegendWidget(this.chartX + 4, legendY + 32, this.chartW - 8, 14, -43521, labelInfected, displayInfected);
    }

    private void drawLegendWidget(int x, int y, int w, int h, int color, String label, int value) {
        GuiPhaseReport.func_73734_a((int)x, (int)y, (int)(x + w), (int)(y + h), (int)0x22000000);
        GuiPhaseReport.func_73734_a((int)x, (int)y, (int)(x + 6), (int)(y + h), (int)color);
        this.field_146289_q.func_175065_a(label, (float)(x + 10), (float)(y + 3), -15658735, false);
        String val = GuiDistortionHelper.jamTextIfNeeded(String.valueOf(value), this.isJumbled);
        this.field_146289_q.func_175065_a(val, (float)(x + w - this.field_146289_q.func_78256_a(val) - 4), (float)(y + 3), color, false);
    }

    private void drawStatusLine(String label, String value, int x, int y, int valueColor) {
        this.field_146289_q.func_175065_a(label + ": ", (float)x, (float)y, -10066330, false);
        int off = this.field_146289_q.func_78256_a(label + ": ");
        this.field_146289_q.func_175065_a(value, (float)(x + off), (float)y, valueColor, false);
    }

    private void drawPieSlice(int cx, int cy, int radius, float startDeg, float endDeg, int color) {
        if (endDeg <= startDeg) {
            return;
        }
        int a = color >> 24 & 0xFF;
        int r = color >> 16 & 0xFF;
        int g = color >> 8 & 0xFF;
        int b = color & 0xFF;
        GlStateManager.func_179147_l();
        GlStateManager.func_179090_x();
        GlStateManager.func_179129_p();
        Tessellator tess = Tessellator.func_178181_a();
        BufferBuilder buf = tess.func_178180_c();
        buf.func_181668_a(6, DefaultVertexFormats.field_181706_f);
        buf.func_181662_b((double)cx, (double)cy, 0.0).func_181669_b(r, g, b, a).func_181675_d();
        int steps = Math.max(12, (int)Math.ceil(Math.abs(endDeg - startDeg) / 6.0f));
        for (int i = 0; i <= steps; ++i) {
            float t = (float)i / (float)steps;
            float ang = startDeg + (endDeg - startDeg) * t;
            double rad = Math.toRadians((double)ang - 90.0);
            double px = (double)cx + Math.cos(rad) * (double)radius;
            double py = (double)cy + Math.sin(rad) * (double)radius;
            buf.func_181662_b(px, py, 0.0).func_181669_b(r, g, b, a).func_181675_d();
        }
        tess.func_78381_a();
        GlStateManager.func_179089_o();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private void drawCircleOutline(int cx, int cy, int radius, int color) {
        int a = color >> 24 & 0xFF;
        int r = color >> 16 & 0xFF;
        int g = color >> 8 & 0xFF;
        int b = color & 0xFF;
        GlStateManager.func_179147_l();
        GlStateManager.func_179090_x();
        GlStateManager.func_179129_p();
        Tessellator tess = Tessellator.func_178181_a();
        BufferBuilder buf = tess.func_178180_c();
        buf.func_181668_a(2, DefaultVertexFormats.field_181706_f);
        int steps = 40;
        for (int i = 0; i < steps; ++i) {
            double ang = Math.toRadians((double)i / (double)steps * 360.0 - 90.0);
            double px = (double)cx + Math.cos(ang) * (double)radius;
            double py = (double)cy + Math.sin(ang) * (double)radius;
            buf.func_181662_b(px, py, 0.0).func_181669_b(r, g, b, a).func_181675_d();
        }
        tess.func_78381_a();
        GlStateManager.func_179089_o();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private void readNBT() {
        NBTTagCompound tag = this.stack.func_77978_p();
        if (tag == null || !tag.func_74764_b("PhaseValue")) {
            this.lines.clear();
            this.lines.add(I18n.func_135052_a((String)"gui.subspaceparasite.phase_report.no_data", (Object[])new Object[0]));
            this.lines.add("");
            this.lines.add(I18n.func_135052_a((String)"gui.subspaceparasite.phase_report.no_data_hint", (Object[])new Object[0]));
            return;
        }
        this.dimension = tag.func_74762_e("PhaseDimension");
        this.phase = tag.func_74762_e("PhaseValue");
        this.totalPoints = tag.func_74762_e("PhaseTotalPoints");
        this.nextPoints = tag.func_74762_e("PhasePointsNext");
        this.progress = tag.func_74779_i("PhaseProgress");
        this.cooldown = tag.func_74762_e("PhaseCooldown");
        this.canGain = tag.func_74767_n("PhaseCanGain");
        this.canLoss = tag.func_74767_n("PhaseCanLoss");
        this.mobcap = tag.func_74762_e("PhaseMobcap");
        this.generation = tag.func_74762_e("PhaseGeneration");
        this.genTicks = tag.func_74762_e("PhaseGenTicks");
        this.parasiteCount = tag.func_74762_e("PhaseParasiteCount");
        this.cothCount = tag.func_74762_e("PhaseCothCount");
        this.totalMobCount = tag.func_74762_e("PhaseTotalMobs");
    }

    private void buildLines() {
        if (!this.lines.isEmpty()) {
            return;
        }
        this.lines.clear();
        this.lines.add(this.formatDisplayLine(I18n.func_135052_a((String)"command.srpevolution.getphase.dimension", (Object[])new Object[]{this.dimension})));
        this.lines.add(this.formatDisplayLine(I18n.func_135052_a((String)"command.srpevolution.getphase.phase", (Object[])new Object[]{this.phase})));
        this.lines.add(this.formatDisplayLine(I18n.func_135052_a((String)"command.srpevolution.getphase.total_points", (Object[])new Object[]{this.totalPoints})));
        this.lines.add(this.formatDisplayLine(I18n.func_135052_a((String)"command.srpevolution.getphase.points_next", (Object[])new Object[]{this.nextPoints})));
        this.lines.add(this.formatDisplayLine(I18n.func_135052_a((String)"command.srpevolution.getphase.progress", (Object[])new Object[]{this.progress})));
        this.lines.add(this.formatDisplayLine(I18n.func_135052_a((String)"command.srpevolution.getphase.cooldown", (Object[])new Object[]{this.formatCooldown(this.cooldown)})));
        this.lines.add(this.formatDisplayLine(I18n.func_135052_a((String)"command.srpevolution.getphase.mobcap", (Object[])new Object[]{this.mobcap})));
        this.lines.add(this.formatDisplayLine(I18n.func_135052_a((String)"command.srpevolution.getphase.generation", (Object[])new Object[]{this.generation})));
        this.lines.add(this.formatDisplayLine(I18n.func_135052_a((String)"command.srpevolution.getphase.gen_ticks", (Object[])new Object[]{this.genTicks})));
        this.lines.add(this.formatDisplayLine(I18n.func_135052_a((String)"command.srpevolution.getphase.parasite_count", (Object[])new Object[]{this.parasiteCount})));
        this.lines.add(this.formatDisplayLine(I18n.func_135052_a((String)"command.srpevolution.getphase.coth_count", (Object[])new Object[]{this.cothCount})));
    }

    private String formatDisplayLine(String s) {
        s = GuiDistortionHelper.toneDownTextColors(s);
        return GuiDistortionHelper.jamTextIfNeeded(s, this.isJumbled);
    }

    private String toneDownTextColors(String s) {
        if (s == null) {
            return "";
        }
        return s.replace("\u00a7e", "\u00a78").replace("\u00a76", "\u00a78").replace("\u00a77", "\u00a78");
    }

    private String formatCooldown(int totalSeconds) {
        if (totalSeconds <= 0) {
            return "0s";
        }
        int hours = totalSeconds / 3600;
        int minutes = totalSeconds % 3600 / 60;
        int seconds = totalSeconds % 60;
        if (hours > 0) {
            if (seconds > 0) {
                return hours + "h " + minutes + "m " + seconds + "s";
            }
            return hours + "h " + minutes + "m";
        }
        if (minutes > 0) {
            if (seconds > 0) {
                return minutes + "m " + seconds + "s";
            }
            return minutes + "m";
        }
        return seconds + "s";
    }

    private int getContentHeight() {
        int wrapWidth = this.textW - 8;
        int h = 0;
        for (String line : this.lines) {
            List wrapped = this.field_146289_q.func_78271_c(line, wrapWidth);
            h += Math.max(1, wrapped.size()) * 10;
        }
        return h;
    }

    private int getMaxScroll() {
        return Math.max(0, this.getContentHeight() - this.textH);
    }

    private void drawCenteredNoShadow(String s, int x, int y, int color) {
        int w = this.field_146289_q.func_78256_a(s);
        this.field_146289_q.func_78276_b(s, x - w / 2, y, color);
    }

    private void drawThinHorizontalLine(int x1, int y, int x2, int color) {
        GuiPhaseReport.func_73734_a((int)x1, (int)y, (int)x2, (int)(y + 1), (int)color);
    }

    private void enableDesignScissor(int x, int y, int w, int h) {
        ScaledResolution sr = new ScaledResolution(this.field_146297_k);
        int factor = sr.func_78325_e();
        int sx = Math.round(((float)this.screenPanelX + (float)x * this.uiScale) * (float)factor);
        int sy = Math.round(((float)this.screenPanelY + (float)y * this.uiScale) * (float)factor);
        int sw = Math.round((float)w * this.uiScale * (float)factor);
        int sh = Math.round((float)h * this.uiScale * (float)factor);
        int fbH = this.field_146297_k.field_71440_d;
        GL11.glEnable((int)3089);
        GL11.glScissor((int)sx, (int)(fbH - (sy + sh)), (int)sw, (int)sh);
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

