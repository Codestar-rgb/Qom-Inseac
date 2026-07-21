package com.dhanantry.scapeandrunparasites.client.gui;

import com.dhanantry.scapeandrunparasites.bestiary.client.gui.GuiDistortionHelper;
import com.dhanantry.scapeandrunparasites.world.SRPWorldData;
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
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiVectorMapReport extends GuiScreen {
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
   private float uiScale = 1.0F;
   private int screenPanelX;
   private int screenPanelY;
   private int screenPanelW;
   private int screenPanelH;
   private static final ResourceLocation TEX_ORIGIN = new ResourceLocation("srparasites", "textures/gui/vector_origin.png");
   private static final ResourceLocation TEX_FAR = new ResourceLocation("srparasites", "textures/gui/vector_far.png");
   private static final ResourceLocation TEX_PINPOINT = new ResourceLocation("srparasites", "textures/gui/vector_pinpoint.png");
   private static final ResourceLocation TEX_BG = new ResourceLocation("srparasites", "textures/gui/vector_gui.png");
   private static final int BG_W = 360;
   private static final int BG_H = 300;
   private final List<GuiVectorMapReport.VectorEntry> vectors = new ArrayList<>();

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

         for (int i = 0; i < list.func_74745_c(); i++) {
            NBTTagCompound c = list.func_150305_b(i);
            int lvx = c.func_74762_e("VectorX");
            int lvz = c.func_74762_e("VectorZ");
            int lr = Math.max(0, c.func_74762_e("Radius"));
            int lday = Math.max(0, c.func_74762_e("Day"));
            this.vectors.add(new GuiVectorMapReport.VectorEntry(lvx, lvz, lr, lday));
         }
      }

      if (this.vectors.isEmpty()) {
         this.vectors.add(new GuiVectorMapReport.VectorEntry(this.vx, this.vz, this.radius, this.day));
      }

      this.fillHealthFromWorldData();
      this.total = Math.max(1, this.vectors.size());
      this.selected = MathHelper.func_76125_a(this.index - 1, 0, this.total - 1);
      GuiVectorMapReport.VectorEntry sel = this.vectors.get(this.selected);
      this.vx = sel.vx;
      this.vz = sel.vz;
      this.radius = sel.r;
      this.day = sel.day;
      int maxW = this.field_146294_l - 24;
      int maxH = this.field_146295_m - 24;
      float s = Math.min(maxW / 360.0F, maxH / 300.0F);
      this.uiScale = Math.min(1.0F, s);
      this.uiScale = Math.max(0.25F, this.uiScale);
      this.screenPanelW = Math.round(360.0F * this.uiScale);
      this.screenPanelH = Math.round(300.0F * this.uiScale);
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
      this.listW = this.listW - Math.max(1, this.listW / 16);
      int gutter = 40;
      int plotLeft = this.listX + this.listW + gutter;
      int plotRight = this.panelX + this.panelW - pad;
      int availW = plotRight - plotLeft;
      this.plotSide = Math.min(availW, availH);
      this.plotX = plotLeft;
      this.plotY = top + (availH - this.plotSide) / 2;
      this.listY = this.plotY;
      this.listH = this.plotSide;
      float legendScale = 0.72F;
      int lineStep = 9;
      int lines = 3;
      int unscaledTextH = lineStep * lines;
      int unscaledPadTop = 2;
      int unscaledPadBottom = 3;
      int unscaledBoxH = unscaledPadTop + unscaledTextH + unscaledPadBottom;
      this.legendH = (int)Math.ceil(unscaledBoxH * legendScale) + 2;
      int legendGap = 6;
      this.listH = Math.max(40, this.listH - (legendGap + this.legendH));
      this.legendX = this.listX;
      this.legendW = this.listW;
      this.legendY = this.listY + this.listH + legendGap;
      this.listScroll = 0;
      this.field_146292_n.clear();
      GuiVectorMapReport.GuiButtonNoShadow done = new GuiVectorMapReport.GuiButtonNoShadow(
         0,
         this.screenPanelX + this.screenPanelW - 4 - 80,
         this.screenPanelY + this.screenPanelH - 24,
         80,
         20,
         this.distort(I18n.func_135052_a("gui.done", new Object[0]))
      );
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
      } else {
         super.func_73869_a(typedChar, keyCode);
      }
   }

   private int toDesignX(int mouseX) {
      return Math.round((mouseX - this.screenPanelX) / this.uiScale);
   }

   private int toDesignY(int mouseY) {
      return Math.round((mouseY - this.screenPanelY) / this.uiScale);
   }

   public void func_146274_d() throws IOException {
      super.func_146274_d();
      int dwheel = Mouse.getEventDWheel();
      if (dwheel != 0) {
         int mx = Mouse.getEventX() * this.field_146294_l / this.field_146297_k.field_71443_c;
         int my = this.field_146295_m - Mouse.getEventY() * this.field_146295_m / this.field_146297_k.field_71440_d - 1;
         int dx = this.toDesignX(mx);
         int dy = this.toDesignY(my);
         if (this.isInList(dx, dy)) {
            int rowH = 32;
            int contentH = this.vectors.size() * rowH;
            int maxScroll = Math.max(0, contentH - this.listH);
            if (dwheel < 0) {
               this.listScroll = Math.min(maxScroll, this.listScroll + rowH);
            } else {
               this.listScroll = Math.max(0, this.listScroll - rowH);
            }
         }
      }
   }

   protected void func_73864_a(int mouseX, int mouseY, int mouseButton) throws IOException {
      int dx = this.toDesignX(mouseX);
      int dy = this.toDesignY(mouseY);
      if (mouseButton == 0 && this.isInList(dx, dy)) {
         int rowH = 32;
         int relY = dy - this.listY + this.listScroll;
         int idx = relY / rowH;
         if (idx >= 0 && idx < this.vectors.size()) {
            this.selected = idx;
            this.index = this.selected + 1;
            GuiVectorMapReport.VectorEntry sel = this.vectors.get(this.selected);
            this.vx = sel.vx;
            this.vz = sel.vz;
            this.radius = sel.r;
            this.day = sel.day;
         }
      }

      super.func_73864_a(mouseX, mouseY, mouseButton);
   }

   private boolean isInList(int mouseX, int mouseY) {
      return mouseX >= this.listX && mouseX < this.listX + this.listW && mouseY >= this.listY && mouseY < this.listY + this.listH;
   }

   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
      boolean wasJumbled = this.isJumbled;
      this.isJumbled = GuiDistortionHelper.isDistortionActive(this.field_146297_k);
      if (wasJumbled != this.isJumbled && !this.field_146292_n.isEmpty()) {
         ((GuiButton)this.field_146292_n.get(0)).field_146126_j = this.distort(I18n.func_135052_a("gui.done", new Object[0]));
      }

      func_73734_a(0, 0, this.field_146294_l, this.field_146295_m, 1711276032);
      GlStateManager.func_179094_E();
      GlStateManager.func_179109_b(this.screenPanelX, this.screenPanelY, 0.0F);
      GlStateManager.func_179152_a(this.uiScale, this.uiScale, 1.0F);
      this.drawPanelBackgroundDesign();
      String title = this.distort(I18n.func_135052_a("gui.srparasites.vector_map.title", new Object[0]));
      String headerLine = this.distort(
         I18n.func_135052_a("gui.srparasites.vector_map.day", new Object[]{this.day})
            + "  "
            + I18n.func_135052_a("gui.srparasites.vector_map.page", new Object[]{this.index, this.total})
      );
      this.drawCenteredNoShadow(title, this.panelX + this.panelW / 2, this.panelY + 8, -15658735);
      this.drawCenteredNoShadow(headerLine, this.panelX + this.panelW / 2, this.panelY + 20, -12303292);
      func_73734_a(this.plotX, this.plotY, this.plotX + this.plotSide, this.plotY + this.plotSide, -526345);
      float pxPerBlock = this.plotSide / 2.0F / 2500.0F;
      int centerPx = this.plotX + this.plotSide / 2;
      int centerPy = this.plotY + this.plotSide / 2;
      this.drawGridAndTicks(pxPerBlock);
      int red = -811901;
      this.beginPlotScissor();

      for (int i = 0; i < this.vectors.size(); i++) {
         GuiVectorMapReport.VectorEntry ve = this.vectors.get(i);
         int dx = ve.vx - this.cx;
         int dz = ve.vz - this.cz;
         float rawVecPx = centerPx + dx * pxPerBlock;
         float rawVecPy = centerPy + dz * pxPerBlock;
         boolean inRange = (long)dx * dx + (long)dz * dz <= 6250000L;
         float vecPx = rawVecPx;
         float vecPy = rawVecPy;
         if (!inRange) {
            float farOuterR = 4.5F;
            float pad = farOuterR + 1.0F;
            vecPx = MathHelper.func_76131_a(rawVecPx, this.plotX + pad, this.plotX + this.plotSide - pad);
            vecPy = MathHelper.func_76131_a(rawVecPy, this.plotY + pad, this.plotY + this.plotSide - pad);
         }

         if (inRange) {
            if (!this.isJumbled) {
               float rPx = ve.r * pxPerBlock;
               float ringR = MathHelper.func_76131_a(rPx, 4.0F, this.plotSide / 2.0F - 2.0F);
               this.drawRing(vecPx, vecPy, ringR, 2.0F, red, 80);
            }
         } else {
            int x = (int)vecPx;
            int y = (int)vecPy;
            func_73734_a(x - 3, y - 3, x + 3, y + 3, -13312);
         }
      }

      this.endPlotScissor();

      for (int i = 0; i < this.vectors.size(); i++) {
         GuiVectorMapReport.VectorEntry vex = this.vectors.get(i);
         int dxx = vex.vx - this.cx;
         int dzx = vex.vz - this.cz;
         float rawVecPxx = centerPx + dxx * pxPerBlock;
         float rawVecPyx = centerPy + dzx * pxPerBlock;
         boolean inRangex = (long)dxx * dxx + (long)dzx * dzx <= 6250000L;
         float vecPxx = rawVecPxx;
         float vecPyx = rawVecPyx;
         if (!inRangex) {
            float farOuterR = 4.5F;
            float pad = farOuterR + 1.0F;
            vecPxx = MathHelper.func_76131_a(rawVecPxx, this.plotX + pad, this.plotX + this.plotSide - pad);
            vecPyx = MathHelper.func_76131_a(rawVecPyx, this.plotY + pad, this.plotY + this.plotSide - pad);
         }

         float ringR = 0.0F;
         if (inRangex && !this.isJumbled) {
            float rPx = vex.r * pxPerBlock;
            ringR = MathHelper.func_76131_a(rPx, 4.0F, this.plotSide / 2.0F - 2.0F);
         }

         String n = String.valueOf(i + 1);
         int nw = this.field_146289_q.func_78256_a(n);
         int nx = (int)(vecPxx - nw / 2.0F);
         int ny = (int)(inRangex ? vecPyx - ringR - 10.0F : vecPyx - 12.0F);
         nx = MathHelper.func_76125_a(nx, this.plotX + 2, this.plotX + this.plotSide - 2 - nw);
         ny = MathHelper.func_76125_a(ny, this.plotY + 2, this.plotY + this.plotSide - 10);
         this.field_146289_q.func_78276_b(n, nx, ny, -15658735);
      }

      this.drawVectorInfoBlock();
      this.drawLegendText();
      this.drawOriginMarker(centerPx, centerPy);
      String rangeStr = this.distort(I18n.func_135052_a("gui.srparasites.vector_map.range", new Object[]{5000, 5000}));
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
      GlStateManager.func_179109_b(x, y, 0.0F);
      GlStateManager.func_179152_a(scale, scale, 1.0F);
      this.field_146289_q.func_78276_b(s, 0, 0, color);
      GlStateManager.func_179121_F();
   }

   private void drawVectorInfoBlock() {
      GL11.glDisable(3089);
      int x0 = this.listX;
      int y0 = this.panelY + 40;
      int vy = 0;
      String l1 = this.distort(I18n.func_135052_a("gui.srparasites.vector_map.vectors", new Object[0]));
      float s = 0.85F;
      this.drawScaledStringNoShadow(l1, x0, y0, s, -15658735);
      func_73734_a(this.listX - 1, this.listY - 1, this.listX + this.listW + 1, this.listY + this.listH + 1, 855638016);
      func_73734_a(this.listX, this.listY, this.listX + this.listW, this.listY + this.listH, 1157627903);
      GlStateManager.func_179098_w();
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      int rowH = 32;
      int y = this.listY - this.listScroll;
      GlStateManager.func_179140_f();
      GlStateManager.func_179097_i();
      GlStateManager.func_179147_l();
      GlStateManager.func_179120_a(770, 771, 1, 0);
      GlStateManager.func_179098_w();
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);

      for (int i = 0; i < this.vectors.size(); i++) {
         GuiVectorMapReport.VectorEntry ve = this.vectors.get(i);
         int ry = y + i * rowH;
         if (ry + rowH >= this.listY && ry <= this.listY + this.listH) {
            int bg = i == this.selected ? 570425344 : 167772160;
            func_73734_a(this.listX, ry, this.listX + this.listW, ry + rowH, bg);
            String aPlain = I18n.func_135052_a("gui.srparasites.vector_map.entry.xyz", new Object[]{i + 1, ve.vx, vy, ve.vz});
            String bPlain = I18n.func_135052_a("gui.srparasites.vector_map.entry.size", new Object[]{ve.r});
            String cPlain = I18n.func_135052_a("gui.srparasites.vector_map.entry.hp", new Object[]{ve.hp >= 0 ? ve.hp : "?"});
            int maxW = (int)((this.listW - 6) / s);
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
      }

      GlStateManager.func_179126_j();
   }

   private String trimToWidth(String s, int maxW) {
      if (this.field_146289_q.func_78256_a(s) <= maxW) {
         return s;
      } else {
         String ell = "...";
         int ew = this.field_146289_q.func_78256_a(ell);
         String t = s;

         while (t.length() > 0 && this.field_146289_q.func_78256_a(t) + ew > maxW) {
            t = t.substring(0, t.length() - 1);
         }

         return t + ell;
      }
   }

   private void drawLegendText() {
      float s = 0.72F;
      String dims = "5000 x 5000";
      String t1 = I18n.func_135052_a("gui.srparasites.vector_map.legend.line1", new Object[0]);
      String t2 = I18n.func_135052_a("gui.srparasites.vector_map.legend.line2", new Object[]{dims});
      String t3 = I18n.func_135052_a("gui.srparasites.vector_map.legend.line3", new Object[0]);
      int wrapW = (int)((this.legendW - 6) / s);
      List<String> lines = new ArrayList<>();

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
      int contentH = padTop + lines.size() * lineStep + fontH + padBottom;
      int dynLegendH = (int)Math.ceil(contentH * s) + 2;
      int doneTop = this.panelY + this.panelH - 24;
      int maxY = doneTop - dynLegendH - 2;
      int y = this.legendY;
      if (y > maxY) {
         y = maxY;
      }

      func_73734_a(this.legendX - 1, y - 1, this.legendX + this.legendW + 1, y + dynLegendH + 1, 855638016);
      func_73734_a(this.legendX, y, this.legendX + this.legendW, y + dynLegendH, 1157627903);
      int y0 = y + padTop;

      for (int i = 0; i < lines.size(); i++) {
         this.drawScaledStringNoShadow(lines.get(i), this.legendX + 3, y0 + i * lineStep, s, -14540254);
      }
   }

   private void drawGridAndTicks(float pxPerBlock) {
      int centerPx = this.plotX + this.plotSide / 2;
      int centerPy = this.plotY + this.plotSide / 2;
      int gridColor = 570425344;
      int axisColor = -2013265920;
      int labelColor = -15066598;
      int step = 500;
      if (this.plotSide >= 280) {
         step = 1000;
      }

      func_73734_a(centerPx, this.plotY, centerPx + 1, this.plotY + this.plotSide, axisColor);
      func_73734_a(this.plotX, centerPy, this.plotX + this.plotSide, centerPy + 1, axisColor);
      int xLabelY = this.plotY + this.plotSide + 4;
      float labelScale = 0.75F;

      for (int v = -2500; v <= 2500; v += step) {
         int gx = centerPx + Math.round(v * pxPerBlock);
         int gy = centerPy + Math.round(v * pxPerBlock);
         if (v != 0) {
            func_73734_a(gx, this.plotY, gx + 1, this.plotY + this.plotSide, gridColor);
            func_73734_a(this.plotX, gy, this.plotX + this.plotSide, gy + 1, gridColor);
         }

         boolean alt = (v / step & 1) != 0;
         String sxw = String.valueOf(this.cx + v);
         int sx = gx - (int)(this.field_146289_q.func_78256_a(sxw) * labelScale) / 2;
         int sy = xLabelY + (alt ? 9 : 0);
         this.drawScaledStringNoShadow(sxw, sx, sy, labelScale, labelColor);
         String syw = String.valueOf(this.cz + v);
         int w = (int)(this.field_146289_q.func_78256_a(syw) * labelScale);
         int gapFromPlot = 4;
         int xs = this.plotX - gapFromPlot - w;
         int ys = gy - 4 + (alt ? 4 : 0);
         this.drawScaledStringNoShadow(syw, xs, ys, labelScale, labelColor);
      }
   }

   private void drawSpriteCentered(ResourceLocation tex, float cx, float cy, int w, int h) {
      this.field_146297_k.func_110434_K().func_110577_a(tex);
      GlStateManager.func_179147_l();
      GlStateManager.func_179120_a(770, 771, 1, 0);
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      int x = Math.round(cx - w / 2.0F);
      int y = Math.round(cy - h / 2.0F);
      func_146110_a(x, y, 0.0F, 0.0F, w, h, w, h);
      GlStateManager.func_179084_k();
   }

   private void drawFilledCircle(float cx, float cy, float r, int color, int segments) {
      segments = Math.max(12, segments);
      float a = (color >>> 24 & 0xFF) / 255.0F;
      float rr = (color >>> 16 & 0xFF) / 255.0F;
      float gg = (color >>> 8 & 0xFF) / 255.0F;
      float bb = (color & 0xFF) / 255.0F;
      GlStateManager.func_179090_x();
      GlStateManager.func_179097_i();
      GlStateManager.func_179147_l();
      GlStateManager.func_179120_a(770, 771, 1, 0);
      Tessellator tess = Tessellator.func_178181_a();
      BufferBuilder buf = tess.func_178180_c();
      buf.func_181668_a(6, DefaultVertexFormats.field_181706_f);
      buf.func_181662_b(cx, cy, 0.0).func_181666_a(rr, gg, bb, a).func_181675_d();

      for (int i = 0; i <= segments; i++) {
         double ang = (Math.PI * 2) * ((double)i / segments);
         double x = cx + Math.cos(ang) * r;
         double y = cy + Math.sin(ang) * r;
         buf.func_181662_b(x, y, 0.0).func_181666_a(rr, gg, bb, a).func_181675_d();
      }

      tess.func_78381_a();
      GlStateManager.func_179084_k();
      GlStateManager.func_179098_w();
      GlStateManager.func_179126_j();
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
   }

   private void drawRing(float cx, float cy, float rOuter, float thickness, int color, int segments) {
      segments = Math.max(16, segments);
      thickness = Math.max(1.0F, thickness);
      float rInner = Math.max(0.0F, rOuter - thickness);
      float a = (color >>> 24 & 0xFF) / 255.0F;
      float rr = (color >>> 16 & 0xFF) / 255.0F;
      float gg = (color >>> 8 & 0xFF) / 255.0F;
      float bb = (color & 0xFF) / 255.0F;
      GlStateManager.func_179090_x();
      GlStateManager.func_179147_l();
      GlStateManager.func_179120_a(770, 771, 1, 0);
      GlStateManager.func_179118_c();
      GlStateManager.func_179131_c(rr, gg, bb, a);
      GL11.glBegin(5);

      for (int i = 0; i <= segments; i++) {
         double ang = (Math.PI * 2) * ((double)i / segments);
         float ca = (float)Math.cos(ang);
         float sa = (float)Math.sin(ang);
         GL11.glVertex2f(cx + ca * rOuter, cy + sa * rOuter);
         GL11.glVertex2f(cx + ca * rInner, cy + sa * rInner);
      }

      GL11.glEnd();
      GlStateManager.func_179141_d();
      GlStateManager.func_179098_w();
   }

   private void drawOriginMarker(int centerPx, int centerPy) {
      this.drawSpriteCentered(TEX_ORIGIN, centerPx + 0.5F, centerPy + 0.5F, 9, 9);
   }

   private void fillHealthFromWorldData() {
      try {
         MinecraftServer srv = this.field_146297_k.func_71401_C();
         if (srv == null) {
            return;
         }

         World world = srv.func_71218_a(this.field_146297_k.field_71439_g.field_71093_bK);
         if (world == null) {
            return;
         }

         SRPWorldData data = SRPWorldData.get(world);
         ArrayList<Integer> xs = data.getorigins("x");
         ArrayList<Integer> zs = data.getorigins("z");
         ArrayList<Integer> hs = data.getorigins("h");

         for (GuiVectorMapReport.VectorEntry ve : this.vectors) {
            for (int i = 0; i < xs.size() && i < zs.size() && i < hs.size(); i++) {
               if (xs.get(i) == ve.vx && zs.get(i) == ve.vz) {
                  ve.hp = hs.get(i);
                  break;
               }
            }
         }
      } catch (Throwable var10) {
      }
   }

   private void drawPanelBackgroundDesign() {
      this.field_146297_k.func_110434_K().func_110577_a(TEX_BG);
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.func_179141_d();
      GlStateManager.func_179147_l();
      GlStateManager.func_179120_a(770, 771, 1, 771);
      func_152125_a(this.panelX, this.panelY, 0.0F, 0.0F, 360, 300, 360, 300, 360.0F, 300.0F);
      GlStateManager.func_179084_k();
   }

   private void beginPlotScissor() {
      int fbW = this.field_146297_k.func_147110_a().field_147621_c;
      int fbH = this.field_146297_k.func_147110_a().field_147618_d;
      float xRatio = (float)fbW / this.field_146294_l;
      float yRatio = (float)fbH / this.field_146295_m;
      float guiX0 = this.screenPanelX + this.plotX * this.uiScale;
      float guiY0 = this.screenPanelY + this.plotY * this.uiScale;
      float guiW = this.plotSide * this.uiScale;
      float guiH = this.plotSide * this.uiScale;
      int scX = (int)Math.floor(guiX0 * xRatio);
      int scW = (int)Math.ceil(guiW * xRatio);
      int scYTop = (int)Math.floor(guiY0 * yRatio);
      int scH = (int)Math.ceil(guiH * yRatio);
      int scY = fbH - (scYTop + scH);
      int padX = (int)Math.ceil(1.0F * xRatio);
      int padY = (int)Math.ceil(1.0F * yRatio);
      scX -= padX;
      scY -= padY;
      scW += padX * 2;
      scH += padY * 2;
      if (scX < 0) {
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
         GL11.glEnable(3089);
         GL11.glScissor(scX, scY, scW, scH);
      } else {
         GL11.glDisable(3089);
      }
   }

   private void endPlotScissor() {
      GL11.glDisable(3089);
   }

   private static class GuiButtonNoShadow extends GuiButton {
      public GuiButtonNoShadow(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
         super(buttonId, x, y, widthIn, heightIn, buttonText);
      }

      public void func_191745_a(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
         if (this.field_146125_m) {
            mc.func_110434_K().func_110577_a(field_146122_a);
            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
            this.field_146123_n = mouseX >= this.field_146128_h
               && mouseY >= this.field_146129_i
               && mouseX < this.field_146128_h + this.field_146120_f
               && mouseY < this.field_146129_i + this.field_146121_g;
            int hoverState = this.func_146114_a(this.field_146123_n);
            GlStateManager.func_179147_l();
            GlStateManager.func_179120_a(770, 771, 1, 0);
            this.func_73729_b(this.field_146128_h, this.field_146129_i, 0, 46 + hoverState * 20, this.field_146120_f / 2, this.field_146121_g);
            this.func_73729_b(
               this.field_146128_h + this.field_146120_f / 2,
               this.field_146129_i,
               200 - this.field_146120_f / 2,
               46 + hoverState * 20,
               this.field_146120_f / 2,
               this.field_146121_g
            );
            int textColor = -15066598;
            if (!this.field_146124_l) {
               textColor = -8947849;
            }

            int sw = mc.field_71466_p.func_78256_a(this.field_146126_j);
            mc.field_71466_p
               .func_78276_b(
                  this.field_146126_j, this.field_146128_h + this.field_146120_f / 2 - sw / 2, this.field_146129_i + (this.field_146121_g - 8) / 2, textColor
               );
         }
      }
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
}
