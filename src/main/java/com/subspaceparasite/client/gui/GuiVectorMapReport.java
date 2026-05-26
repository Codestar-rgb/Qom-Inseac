/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.server.integrated.IntegratedServer
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package com.subspaceparasite.client.gui;

import com.subspaceparasite.bestiary.client.gui.GuiDistortionHelper;
import com.subspaceparasite.world.SPWorldData;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiVectorMapReport
extends GuiScreen {
    private static final int RANGE = 2500;
    private final ItemStack stack;
    private boolean isJumbled;
    private int cx;
    private int cz;
    private int vx;
    private int vz;
    private int radius;
    private int day;
    private int index;
    private int total;
    private int legendX;
    private int legendY;
    private int legendW;
    private int legendH;
    private int panelX;
    private int panelY;
    private int panelW;
    private int panelH;
    private int plotX;
    private int plotY;
    private int plotSide;
    private int listX;
    private int listY;
    private int listW;
    private int listH;
    private int listScroll;
    private int selected;
    private float uiScale = 1.0f;
    private int screenPanelX;
    private int screenPanelY;
    private int screenPanelW;
    private int screenPanelH;
    private static final ResourceLocation TEX_ORIGIN = new ResourceLocation("subspaceparasite", "textures/gui/vector_origin.png");
    private static final ResourceLocation TEX_FAR = new ResourceLocation("subspaceparasite", "textures/gui/vector_far.png");
    private static final ResourceLocation TEX_PINPOINT = new ResourceLocation("subspaceparasite", "textures/gui/vector_pinpoint.png");
    private static final ResourceLocation TEX_BG = new ResourceLocation("subspaceparasite", "textures/gui/vector_gui.png");
    private static final int BG_W = 360;
    private static final int BG_H = 300;
    private final List<VectorEntry> vectors = new ArrayList<VectorEntry>();

    private String distort(String s) {
        return GuiDistortionHelper.jamTextIfNeeded(s, this.isJumbled);
    }

    public GuiVectorMapReport(ItemStack stack) {
        this.stack = stack;
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        this.isJumbled = GuiDistortionHelper.isDistortionActive(this.field_146297_k);
        NBTTagCompound tag = this.stack.func_77978_p();
        if (tag == null) {
            tag = new NBTTagCompound();
        }
        this.cx = tag.func_74762_e("CenterX");
        this.cz = tag.func_74762_e("CenterZ");
        this.vx = tag.func_74762_e("VectorX");
        this.vz = tag.func_74762_e("VectorZ");
        this.radius = Math.max(0, tag.func_74762_e("Radius"));
        this.day = Math.max(0, tag.func_74762_e("Day"));
        this.index = Math.max(1, tag.func_74762_e("Index"));
        this.total = Math.max(1, tag.func_74762_e("Total"));
        this.vectors.clear();
        if (tag.func_150297_b("Vectors", 9)) {
            NBTTagList list = tag.func_150295_c("Vectors", 10);
            for (int i = 0; i < list.func_74745_c(); ++i) {
                NBTTagCompound c = list.func_150305_b(i);
                int lvx = c.func_74762_e("VectorX");
                int lvz = c.func_74762_e("VectorZ");
                int lr = Math.max(0, c.func_74762_e("Radius"));
                int lday = Math.max(0, c.func_74762_e("Day"));
                this.vectors.add(new VectorEntry(lvx, lvz, lr, lday));
            }
        }
        if (this.vectors.isEmpty()) {
            this.vectors.add(new VectorEntry(this.vx, this.vz, this.radius, this.day));
        }
        this.fillHealthFromWorldData();
        this.total = Math.max(1, this.vectors.size());
        this.selected = MathHelper.func_76125_a((int)(this.index - 1), (int)0, (int)(this.total - 1));
        VectorEntry sel = this.vectors.get(this.selected);
        this.vx = sel.vx;
        this.vz = sel.vz;
        this.radius = sel.r;
        this.day = sel.day;
        int maxW = this.field_146294_l - 24;
        int maxH = this.field_146295_m - 24;
        float s = Math.min((float)maxW / 360.0f, (float)maxH / 300.0f);
        this.uiScale = Math.min(1.0f, s);
        this.uiScale = Math.max(0.25f, this.uiScale);
        this.screenPanelW = Math.round(360.0f * this.uiScale);
        this.screenPanelH = Math.round(300.0f * this.uiScale);
        this.screenPanelX = (this.field_146294_l - this.screenPanelW) / 2;
        this.screenPanelY = (this.field_146295_m - this.screenPanelH) / 2;
        if (this.screenPanelX < 0) {
            this.screenPanelX = 0;
        }
        if (this.screenPanelY < 0) {
            this.screenPanelY = 0;
        }
        this.panelX = 0;
        this.panelY = 0;
        this.panelW = 360;
        this.panelH = 300;
        int pad = 14;
        int top = this.panelY + 40;
        int bottom = this.panelY + this.panelH - 44;
        int availH = bottom - top;
        this.listX = this.panelX + 10;
        this.listW = 120;
        this.listW -= Math.max(1, this.listW / 16);
        int gutter = 40;
        int plotLeft = this.listX + this.listW + gutter;
        int plotRight = this.panelX + this.panelW - pad;
        int availW = plotRight - plotLeft;
        this.plotSide = Math.min(availW, availH);
        this.plotX = plotLeft;
        this.listY = this.plotY = top + (availH - this.plotSide) / 2;
        this.listH = this.plotSide;
        float legendScale = 0.72f;
        int lineStep = 9;
        int lines = 3;
        int unscaledTextH = lineStep * lines;
        int unscaledPadTop = 2;
        int unscaledPadBottom = 3;
        int unscaledBoxH = unscaledPadTop + unscaledTextH + unscaledPadBottom;
        this.legendH = (int)Math.ceil((float)unscaledBoxH * legendScale) + 2;
        int legendGap = 6;
        this.listH = Math.max(40, this.listH - (legendGap + this.legendH));
        this.legendX = this.listX;
        this.legendW = this.listW;
        this.legendY = this.listY + this.listH + legendGap;
        this.listScroll = 0;
        this.field_146292_n.clear();
        GuiButtonNoShadow done = new GuiButtonNoShadow(0, this.screenPanelX + this.screenPanelW - 4 - 80, this.screenPanelY + this.screenPanelH - 24, 80, 20, this.distort(I18n.func_135052_a((String)"gui.done", (Object[])new Object[0])));
        done.field_146124_l = true;
        this.field_146292_n.add(done);
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

    public void func_146274_d() throws IOException {
        super.func_146274_d();
        int dwheel = Mouse.getEventDWheel();
        if (dwheel != 0) {
            int dy;
            int mx = Mouse.getEventX() * this.field_146294_l / this.field_146297_k.field_71443_c;
            int my = this.field_146295_m - Mouse.getEventY() * this.field_146295_m / this.field_146297_k.field_71440_d - 1;
            int dx = this.toDesignX(mx);
            if (this.isInList(dx, dy = this.toDesignY(my))) {
                int rowH = 32;
                int contentH = this.vectors.size() * rowH;
                int maxScroll = Math.max(0, contentH - this.listH);
                this.listScroll = dwheel < 0 ? Math.min(maxScroll, this.listScroll + rowH) : Math.max(0, this.listScroll - rowH);
            }
        }
    }

    protected void func_73864_a(int mouseX, int mouseY, int mouseButton) throws IOException {
        int rowH;
        int relY;
        int idx;
        int dx = this.toDesignX(mouseX);
        int dy = this.toDesignY(mouseY);
        if (mouseButton == 0 && this.isInList(dx, dy) && (idx = (relY = dy - this.listY + this.listScroll) / (rowH = 32)) >= 0 && idx < this.vectors.size()) {
            this.selected = idx;
            this.index = this.selected + 1;
            VectorEntry sel = this.vectors.get(this.selected);
            this.vx = sel.vx;
            this.vz = sel.vz;
            this.radius = sel.r;
            this.day = sel.day;
        }
        super.func_73864_a(mouseX, mouseY, mouseButton);
    }

    private boolean isInList(int mouseX, int mouseY) {
        return mouseX >= this.listX && mouseX < this.listX + this.listW && mouseY >= this.listY && mouseY < this.listY + this.listH;
    }

    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        float vecPy;
        float vecPx;
        boolean inRange;
        float rawVecPy;
        float rawVecPx;
        int dz;
        int dx;
        VectorEntry ve;
        int i;
        boolean wasJumbled = this.isJumbled;
        this.isJumbled = GuiDistortionHelper.isDistortionActive(this.field_146297_k);
        if (wasJumbled != this.isJumbled && !this.field_146292_n.isEmpty()) {
            ((GuiButton)this.field_146292_n.get((int)0)).field_146126_j = this.distort(I18n.func_135052_a((String)"gui.done", (Object[])new Object[0]));
        }
        GuiVectorMapReport.func_73734_a((int)0, (int)0, (int)this.field_146294_l, (int)this.field_146295_m, (int)0x66000000);
        GlStateManager.func_179094_E();
        GlStateManager.func_179109_b((float)this.screenPanelX, (float)this.screenPanelY, (float)0.0f);
        GlStateManager.func_179152_a((float)this.uiScale, (float)this.uiScale, (float)1.0f);
        this.drawPanelBackgroundDesign();
        String title = this.distort(I18n.func_135052_a((String)"gui.subspaceparasite.vector_map.title", (Object[])new Object[0]));
        String headerLine = this.distort(I18n.func_135052_a((String)"gui.subspaceparasite.vector_map.day", (Object[])new Object[]{this.day}) + "  " + I18n.func_135052_a((String)"gui.subspaceparasite.vector_map.page", (Object[])new Object[]{this.index, this.total}));
        this.drawCenteredNoShadow(title, this.panelX + this.panelW / 2, this.panelY + 8, -15658735);
        this.drawCenteredNoShadow(headerLine, this.panelX + this.panelW / 2, this.panelY + 20, -12303292);
        GuiVectorMapReport.func_73734_a((int)this.plotX, (int)this.plotY, (int)(this.plotX + this.plotSide), (int)(this.plotY + this.plotSide), (int)-526345);
        float pxPerBlock = (float)this.plotSide / 2.0f / 2500.0f;
        int centerPx = this.plotX + this.plotSide / 2;
        int centerPy = this.plotY + this.plotSide / 2;
        this.drawGridAndTicks(pxPerBlock);
        int red = -811901;
        this.beginPlotScissor();
        for (i = 0; i < this.vectors.size(); ++i) {
            ve = this.vectors.get(i);
            dx = ve.vx - this.cx;
            dz = ve.vz - this.cz;
            rawVecPx = (float)centerPx + (float)dx * pxPerBlock;
            rawVecPy = (float)centerPy + (float)dz * pxPerBlock;
            inRange = (long)dx * (long)dx + (long)dz * (long)dz <= 6250000L;
            vecPx = rawVecPx;
            vecPy = rawVecPy;
            if (!inRange) {
                float farOuterR = 4.5f;
                float pad = farOuterR + 1.0f;
                vecPx = MathHelper.func_76131_a((float)rawVecPx, (float)((float)this.plotX + pad), (float)((float)(this.plotX + this.plotSide) - pad));
                vecPy = MathHelper.func_76131_a((float)rawVecPy, (float)((float)this.plotY + pad), (float)((float)(this.plotY + this.plotSide) - pad));
            }
            if (inRange) {
                if (this.isJumbled) continue;
                float rPx = (float)ve.r * pxPerBlock;
                float ringR = MathHelper.func_76131_a((float)rPx, (float)4.0f, (float)((float)this.plotSide / 2.0f - 2.0f));
                this.drawRing(vecPx, vecPy, ringR, 2.0f, red, 80);
                continue;
            }
            int x = (int)vecPx;
            int y = (int)vecPy;
            GuiVectorMapReport.func_73734_a((int)(x - 3), (int)(y - 3), (int)(x + 3), (int)(y + 3), (int)-13312);
        }
        this.endPlotScissor();
        for (i = 0; i < this.vectors.size(); ++i) {
            ve = this.vectors.get(i);
            dx = ve.vx - this.cx;
            dz = ve.vz - this.cz;
            rawVecPx = (float)centerPx + (float)dx * pxPerBlock;
            rawVecPy = (float)centerPy + (float)dz * pxPerBlock;
            inRange = (long)dx * (long)dx + (long)dz * (long)dz <= 6250000L;
            vecPx = rawVecPx;
            vecPy = rawVecPy;
            if (!inRange) {
                float farOuterR = 4.5f;
                float pad = farOuterR + 1.0f;
                vecPx = MathHelper.func_76131_a((float)rawVecPx, (float)((float)this.plotX + pad), (float)((float)(this.plotX + this.plotSide) - pad));
                vecPy = MathHelper.func_76131_a((float)rawVecPy, (float)((float)this.plotY + pad), (float)((float)(this.plotY + this.plotSide) - pad));
            }
            float ringR = 0.0f;
            if (inRange && !this.isJumbled) {
                float rPx = (float)ve.r * pxPerBlock;
                ringR = MathHelper.func_76131_a((float)rPx, (float)4.0f, (float)((float)this.plotSide / 2.0f - 2.0f));
            }
            String n = String.valueOf(i + 1);
            int nw = this.field_146289_q.func_78256_a(n);
            int nx = (int)(vecPx - (float)nw / 2.0f);
            int ny = (int)(inRange ? vecPy - ringR - 10.0f : vecPy - 12.0f);
            nx = MathHelper.func_76125_a((int)nx, (int)(this.plotX + 2), (int)(this.plotX + this.plotSide - 2 - nw));
            ny = MathHelper.func_76125_a((int)ny, (int)(this.plotY + 2), (int)(this.plotY + this.plotSide - 10));
            this.field_146289_q.func_78276_b(n, nx, ny, -15658735);
        }
        this.drawVectorInfoBlock();
        this.drawLegendText();
        this.drawOriginMarker(centerPx, centerPy);
        String rangeStr = this.distort(I18n.func_135052_a((String)"gui.subspaceparasite.vector_map.range", (Object[])new Object[]{5000, 5000}));
        int rsW = this.field_146289_q.func_78256_a(rangeStr);
        this.field_146289_q.func_78276_b(rangeStr, this.panelX + this.panelW / 2 - rsW / 2, this.panelY + 30, -12303292);
        GlStateManager.func_179121_F();
        super.func_73863_a(mouseX, mouseY, partialTicks);
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

    private void drawVectorInfoBlock() {
        GL11.glDisable((int)3089);
        int x0 = this.listX;
        int y0 = this.panelY + 40;
        int vy = 0;
        String l1 = this.distort(I18n.func_135052_a((String)"gui.subspaceparasite.vector_map.vectors", (Object[])new Object[0]));
        float s = 0.85f;
        this.drawScaledStringNoShadow(l1, x0, y0, s, -15658735);
        GuiVectorMapReport.func_73734_a((int)(this.listX - 1), (int)(this.listY - 1), (int)(this.listX + this.listW + 1), (int)(this.listY + this.listH + 1), (int)0x33000000);
        GuiVectorMapReport.func_73734_a((int)this.listX, (int)this.listY, (int)(this.listX + this.listW), (int)(this.listY + this.listH), (int)0x44FFFFFF);
        GlStateManager.func_179098_w();
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        int rowH = 32;
        int y = this.listY - this.listScroll;
        GlStateManager.func_179140_f();
        GlStateManager.func_179097_i();
        GlStateManager.func_179147_l();
        GlStateManager.func_179120_a((int)770, (int)771, (int)1, (int)0);
        GlStateManager.func_179098_w();
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        for (int i = 0; i < this.vectors.size(); ++i) {
            VectorEntry ve = this.vectors.get(i);
            int ry = y + i * rowH;
            if (ry + rowH < this.listY || ry > this.listY + this.listH) continue;
            int bg = i == this.selected ? 0x22000000 : 0xA000000;
            GuiVectorMapReport.func_73734_a((int)this.listX, (int)ry, (int)(this.listX + this.listW), (int)(ry + rowH), (int)bg);
            String aPlain = I18n.func_135052_a((String)"gui.subspaceparasite.vector_map.entry.xyz", (Object[])new Object[]{i + 1, ve.vx, vy, ve.vz});
            String bPlain = I18n.func_135052_a((String)"gui.subspaceparasite.vector_map.entry.size", (Object[])new Object[]{ve.r});
            String cPlain = I18n.func_135052_a((String)"gui.subspaceparasite.vector_map.entry.hp", (Object[])new Object[]{ve.hp >= 0 ? Integer.valueOf(ve.hp) : "?"});
            int maxW = (int)((float)(this.listW - 6) / s);
            aPlain = this.trimToWidth(aPlain, maxW);
            bPlain = this.trimToWidth(bPlain, maxW);
            cPlain = this.trimToWidth(cPlain, maxW);
            String a = this.distort(aPlain);
            String b = this.distort(bPlain);
            String c = this.distort(cPlain);
            GlStateManager.func_179098_w();
            this.drawScaledStringNoShadow(a, this.listX + 3, ry + 3, s, -15658735);
            this.drawScaledStringNoShadow(b, this.listX + 3, ry + 13, s, -14540254);
            this.drawScaledStringNoShadow(c, this.listX + 3, ry + 23, s, -14540254);
        }
        GlStateManager.func_179126_j();
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

    private void drawLegendText() {
        float s = 0.72f;
        String dims = "5000 x 5000";
        String t1 = I18n.func_135052_a((String)"gui.subspaceparasite.vector_map.legend.line1", (Object[])new Object[0]);
        String t2 = I18n.func_135052_a((String)"gui.subspaceparasite.vector_map.legend.line2", (Object[])new Object[]{dims});
        String t3 = I18n.func_135052_a((String)"gui.subspaceparasite.vector_map.legend.line3", (Object[])new Object[0]);
        int wrapW = (int)((float)(this.legendW - 6) / s);
        ArrayList<String> lines = new ArrayList<String>();
        for (String line : this.field_146289_q.func_78271_c(t1, wrapW)) {
            lines.add(this.distort(line));
        }
        for (String line : this.field_146289_q.func_78271_c(t2, wrapW)) {
            lines.add(this.distort(line));
        }
        for (String line : this.field_146289_q.func_78271_c(t3, wrapW)) {
            lines.add(this.distort(line));
        }
        int lineStep = 9;
        int padTop = 2;
        int padBottom = 3;
        int fontH = this.field_146289_q.field_78288_b;
        int y = this.legendY;
        int doneTop = this.panelY + this.panelH - 24;
        int contentH = padTop + lines.size() * lineStep + fontH + padBottom;
        int dynLegendH = (int)Math.ceil((float)contentH * s) + 2;
        int maxY = doneTop - dynLegendH - 2;
        if (y > maxY) {
            y = maxY;
        }
        GuiVectorMapReport.func_73734_a((int)(this.legendX - 1), (int)(y - 1), (int)(this.legendX + this.legendW + 1), (int)(y + dynLegendH + 1), (int)0x33000000);
        GuiVectorMapReport.func_73734_a((int)this.legendX, (int)y, (int)(this.legendX + this.legendW), (int)(y + dynLegendH), (int)0x44FFFFFF);
        int y0 = y + padTop;
        for (int i = 0; i < lines.size(); ++i) {
            this.drawScaledStringNoShadow((String)lines.get(i), this.legendX + 3, y0 + i * lineStep, s, -14540254);
        }
    }

    private void drawGridAndTicks(float pxPerBlock) {
        int centerPx = this.plotX + this.plotSide / 2;
        int centerPy = this.plotY + this.plotSide / 2;
        int gridColor = 0x22000000;
        int axisColor = -2013265920;
        int labelColor = -15066598;
        int step = 500;
        if (this.plotSide >= 280) {
            step = 1000;
        }
        GuiVectorMapReport.func_73734_a((int)centerPx, (int)this.plotY, (int)(centerPx + 1), (int)(this.plotY + this.plotSide), (int)axisColor);
        GuiVectorMapReport.func_73734_a((int)this.plotX, (int)centerPy, (int)(this.plotX + this.plotSide), (int)(centerPy + 1), (int)axisColor);
        int xLabelY = this.plotY + this.plotSide + 4;
        float labelScale = 0.75f;
        for (int v = -2500; v <= 2500; v += step) {
            int gx = centerPx + Math.round((float)v * pxPerBlock);
            int gy = centerPy + Math.round((float)v * pxPerBlock);
            if (v != 0) {
                GuiVectorMapReport.func_73734_a((int)gx, (int)this.plotY, (int)(gx + 1), (int)(this.plotY + this.plotSide), (int)gridColor);
                GuiVectorMapReport.func_73734_a((int)this.plotX, (int)gy, (int)(this.plotX + this.plotSide), (int)(gy + 1), (int)gridColor);
            }
            boolean alt = (v / step & 1) != 0;
            String sxw = String.valueOf(this.cx + v);
            int sx = gx - (int)((float)this.field_146289_q.func_78256_a(sxw) * labelScale) / 2;
            int sy = xLabelY + (alt ? 9 : 0);
            this.drawScaledStringNoShadow(sxw, sx, sy, labelScale, labelColor);
            String syw = String.valueOf(this.cz + v);
            int w = (int)((float)this.field_146289_q.func_78256_a(syw) * labelScale);
            int gapFromPlot = 4;
            int xs = this.plotX - gapFromPlot - w;
            int ys = gy - 4 + (alt ? 4 : 0);
            this.drawScaledStringNoShadow(syw, xs, ys, labelScale, labelColor);
        }
    }

    private void drawSpriteCentered(ResourceLocation tex, float cx, float cy, int w, int h) {
        this.field_146297_k.func_110434_K().func_110577_a(tex);
        GlStateManager.func_179147_l();
        GlStateManager.func_179120_a((int)770, (int)771, (int)1, (int)0);
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        int x = Math.round(cx - (float)w / 2.0f);
        int y = Math.round(cy - (float)h / 2.0f);
        GuiVectorMapReport.func_146110_a((int)x, (int)y, (float)0.0f, (float)0.0f, (int)w, (int)h, (float)w, (float)h);
        GlStateManager.func_179084_k();
    }

    private void drawFilledCircle(float cx, float cy, float r, int color, int segments) {
        segments = Math.max(12, segments);
        float a = (float)(color >>> 24 & 0xFF) / 255.0f;
        float rr = (float)(color >>> 16 & 0xFF) / 255.0f;
        float gg = (float)(color >>> 8 & 0xFF) / 255.0f;
        float bb = (float)(color & 0xFF) / 255.0f;
        GlStateManager.func_179090_x();
        GlStateManager.func_179097_i();
        GlStateManager.func_179147_l();
        GlStateManager.func_179120_a((int)770, (int)771, (int)1, (int)0);
        Tessellator tess = Tessellator.func_178181_a();
        BufferBuilder buf = tess.func_178180_c();
        buf.func_181668_a(6, DefaultVertexFormats.field_181706_f);
        buf.func_181662_b((double)cx, (double)cy, 0.0).func_181666_a(rr, gg, bb, a).func_181675_d();
        for (int i = 0; i <= segments; ++i) {
            double ang = Math.PI * 2 * ((double)i / (double)segments);
            double x = (double)cx + Math.cos(ang) * (double)r;
            double y = (double)cy + Math.sin(ang) * (double)r;
            buf.func_181662_b(x, y, 0.0).func_181666_a(rr, gg, bb, a).func_181675_d();
        }
        tess.func_78381_a();
        GlStateManager.func_179084_k();
        GlStateManager.func_179098_w();
        GlStateManager.func_179126_j();
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private void drawRing(float cx, float cy, float rOuter, float thickness, int color, int segments) {
        segments = Math.max(16, segments);
        thickness = Math.max(1.0f, thickness);
        float rInner = Math.max(0.0f, rOuter - thickness);
        float a = (float)(color >>> 24 & 0xFF) / 255.0f;
        float rr = (float)(color >>> 16 & 0xFF) / 255.0f;
        float gg = (float)(color >>> 8 & 0xFF) / 255.0f;
        float bb = (float)(color & 0xFF) / 255.0f;
        GlStateManager.func_179090_x();
        GlStateManager.func_179147_l();
        GlStateManager.func_179120_a((int)770, (int)771, (int)1, (int)0);
        GlStateManager.func_179118_c();
        GlStateManager.func_179131_c((float)rr, (float)gg, (float)bb, (float)a);
        GL11.glBegin((int)5);
        for (int i = 0; i <= segments; ++i) {
            double ang = Math.PI * 2 * ((double)i / (double)segments);
            float ca = (float)Math.cos(ang);
            float sa = (float)Math.sin(ang);
            GL11.glVertex2f((float)(cx + ca * rOuter), (float)(cy + sa * rOuter));
            GL11.glVertex2f((float)(cx + ca * rInner), (float)(cy + sa * rInner));
        }
        GL11.glEnd();
        GlStateManager.func_179141_d();
        GlStateManager.func_179098_w();
    }

    private void drawOriginMarker(int centerPx, int centerPy) {
        this.drawSpriteCentered(TEX_ORIGIN, (float)centerPx + 0.5f, (float)centerPy + 0.5f, 9, 9);
    }

    private void fillHealthFromWorldData() {
        try {
            IntegratedServer srv = this.field_146297_k.func_71401_C();
            if (srv == null) {
                return;
            }
            WorldServer world = srv.func_71218_a(this.field_146297_k.field_71439_g.field_71093_bK);
            if (world == null) {
                return;
            }
            SPWorldData data = SPWorldData.get((World)world);
            ArrayList<Integer> xs = data.getorigins("x");
            ArrayList<Integer> zs = data.getorigins("z");
            ArrayList<Integer> hs = data.getorigins("h");
            block2: for (VectorEntry ve : this.vectors) {
                for (int i = 0; i < xs.size() && i < zs.size() && i < hs.size(); ++i) {
                    if (xs.get(i) != ve.vx || zs.get(i) != ve.vz) continue;
                    ve.hp = hs.get(i);
                    continue block2;
                }
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    private void drawPanelBackgroundDesign() {
        this.field_146297_k.func_110434_K().func_110577_a(TEX_BG);
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.func_179141_d();
        GlStateManager.func_179147_l();
        GlStateManager.func_179120_a((int)770, (int)771, (int)1, (int)771);
        GuiVectorMapReport.func_152125_a((int)this.panelX, (int)this.panelY, (float)0.0f, (float)0.0f, (int)360, (int)300, (int)360, (int)300, (float)360.0f, (float)300.0f);
        GlStateManager.func_179084_k();
    }

    private void beginPlotScissor() {
        int fbW = this.field_146297_k.func_147110_a().field_147621_c;
        int fbH = this.field_146297_k.func_147110_a().field_147618_d;
        float xRatio = (float)fbW / (float)this.field_146294_l;
        float yRatio = (float)fbH / (float)this.field_146295_m;
        float guiX0 = (float)this.screenPanelX + (float)this.plotX * this.uiScale;
        float guiY0 = (float)this.screenPanelY + (float)this.plotY * this.uiScale;
        float guiW = (float)this.plotSide * this.uiScale;
        float guiH = (float)this.plotSide * this.uiScale;
        int scX = (int)Math.floor(guiX0 * xRatio);
        int scW = (int)Math.ceil(guiW * xRatio);
        int scYTop = (int)Math.floor(guiY0 * yRatio);
        int scH = (int)Math.ceil(guiH * yRatio);
        int scY = fbH - (scYTop + scH);
        int padX = (int)Math.ceil(1.0f * xRatio);
        int padY = (int)Math.ceil(1.0f * yRatio);
        scY -= padY;
        scW += padX * 2;
        scH += padY * 2;
        if ((scX -= padX) < 0) {
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
    }

    private void endPlotScissor() {
        GL11.glDisable((int)3089);
    }

    private static class VectorEntry {
        final int vx;
        final int vz;
        final int r;
        final int day;
        int hp = -1;

        VectorEntry(int vx, int vz, int r, int day) {
            this.vx = vx;
            this.vz = vz;
            this.r = r;
            this.day = day;
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

