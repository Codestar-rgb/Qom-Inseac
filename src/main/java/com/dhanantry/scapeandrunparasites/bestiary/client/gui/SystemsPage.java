package com.dhanantry.scapeandrunparasites.bestiary.client.gui;

import com.dhanantry.scapeandrunparasites.bestiary.systems.SRPSystemsRegistry;
import com.dhanantry.scapeandrunparasites.bestiary.systems.SystemEntry;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class SystemsPage extends GuiScreen {
   private static final int LIST_X = 14;
   private static final int LIST_Y = 32;
   private static final int LIST_W = 130;
   private static final int LIST_H = 180;
   private static final int DETAIL_X = 156;
   private static final int DETAIL_Y = 32;
   private static final int DETAIL_W = 220;
   private static final int DETAIL_H = 180;
   private static final int ROW_H = 14;
   private static final int BTN_H = 14;
   private static final int DETAIL_PAD = 8;
   private static final int DETAIL_SCROLLBAR_W = 8;
   private static final int DETAIL_SCROLLBAR_GAP = 4;
   private static final int DETAIL_LINE_H = 10;
   private static final int DETAIL_MIN_THUMB_H = 12;
   private static final int LIST_SCROLLBAR_W = 6;
   private static final int LIST_SCROLLBAR_GAP = 6;
   private static final int LIST_SCROLLBAR_X = 2;
   private static final int LIST_SCROLLBAR_Y = 32;
   private static final int LIST_SCROLLBAR_H = 180;
   private static final int LIST_MIN_THUMB_H = 18;
   private final EntityPlayer player;
   private final GuiScreen parent;
   private final List<SystemEntry> entries = new ArrayList<>();
   private GearBackground gearBackground;
   private int selectedIndex = -1;
   private boolean isJumbled;
   private int listScrollPx = 0;
   private int detailScrollPx = 0;
   private boolean draggingListScrollbar = false;
   private int listDragGrabOffset = 0;
   private boolean draggingDetailScrollbar = false;
   private int detailDragGrabOffset = 0;
   private static final ResourceLocation JUMBLED_ICON = new ResourceLocation("srparasites", "textures/gui/question_mark_small.png");

   public SystemsPage(EntityPlayer player, GuiScreen parent) {
      this.player = player;
      this.parent = parent;
   }

   private String distort(String s) {
      return GuiDistortionHelper.jamTextIfNeeded(s, this.isJumbled);
   }

   public void func_73866_w_() {
      super.func_73866_w_();
      if (this.gearBackground == null) {
         this.gearBackground = new GearBackground(this.field_146297_k);
      }

      this.isJumbled = GuiDistortionHelper.isDistortionActive(this.field_146297_k);
      this.field_146292_n.clear();
      this.field_146292_n.add(new GuiButton(1, 10, 10, 60, 20, this.distort("< " + I18n.func_135052_a("bestiary.systems.home", new Object[0]))));
      this.rebuildEntries();
      this.rebuildListButtons();
   }

   private void rebuildEntries() {
      this.entries.clear();
      this.entries.addAll(SRPSystemsRegistry.all());
      if (this.selectedIndex >= this.entries.size()) {
         this.selectedIndex = this.entries.isEmpty() ? -1 : 0;
      }

      if (this.selectedIndex < 0 && !this.entries.isEmpty()) {
         this.selectedIndex = 0;
      }

      this.detailScrollPx = 0;
      this.draggingDetailScrollbar = false;
   }

   private int visibleRows() {
      return 12;
   }

   private void rebuildListButtons() {
      this.field_146292_n.removeIf(b -> b.field_146127_k >= 200 && b.field_146127_k < 2000);
      int firstRow = this.listScrollPx / 14;
      int maxRows = this.visibleRows();

      for (int i = 0; i < maxRows; i++) {
         int idx = firstRow + i;
         if (idx >= this.entries.size()) {
            break;
         }

         int y = 32 + i * 14;
         this.field_146292_n.add(new GuiButton(200 + idx, 16, y, 126, 14, ""));
      }
   }

   protected void func_146284_a(GuiButton button) throws IOException {
      if (button.field_146127_k == 1) {
         this.field_146297_k.func_147108_a(this.parent);
      } else {
         if (button.field_146127_k >= 200 && button.field_146127_k < 2000) {
            int idx = button.field_146127_k - 200;
            if (idx >= 0 && idx < this.entries.size()) {
               this.selectedIndex = idx;
               this.detailScrollPx = 0;
               this.draggingDetailScrollbar = false;
            }
         }
      }
   }

   private boolean isMouseOver(int x1, int y1, int x2, int y2, int mx, int my) {
      return mx >= x1 && mx < x2 && my >= y1 && my < y2;
   }

   public void func_146274_d() throws IOException {
      super.func_146274_d();
      int dwheel = Mouse.getEventDWheel();
      if (dwheel != 0) {
         int mx = Mouse.getEventX() * this.field_146294_l / this.field_146297_k.field_71443_c;
         int my = this.field_146295_m - Mouse.getEventY() * this.field_146295_m / this.field_146297_k.field_71440_d - 1;
         boolean overList = this.isMouseOver(14, 32, 144, 212, mx, my);
         boolean overDetailText = this.isMouseOver(
            this.getDetailTextX(), this.getDetailTextY(), this.getDetailTextX() + this.getDetailTextW(), this.getDetailTextY() + this.getDetailTextH(), mx, my
         );
         boolean overDetailScrollbar = this.isMouseOver(
            this.getDetailScrollbarX(),
            this.getDetailScrollbarY(),
            this.getDetailScrollbarX() + 8,
            this.getDetailScrollbarY() + this.getDetailScrollbarH(),
            mx,
            my
         );
         int dir = dwheel > 0 ? -1 : 1;
         if (overDetailText || overDetailScrollbar) {
            this.scrollDetail(dir);
         } else if (overList) {
            this.scrollList(dir);
         } else {
            this.scrollList(dir);
         }
      }
   }

   protected void func_73864_a(int mouseX, int mouseY, int mouseButton) throws IOException {
      if (mouseButton == 0 && this.hasScrollableList() && this.isMouseOverListScrollbar(mouseX, mouseY)) {
         int thumbY = this.getListScrollbarThumbY();
         int thumbH = this.getListScrollbarThumbH();
         if (mouseY >= thumbY && mouseY < thumbY + thumbH) {
            this.draggingListScrollbar = true;
            this.listDragGrabOffset = mouseY - thumbY;
         } else {
            int newThumbTop = mouseY - thumbH / 2;
            this.setListScrollFromThumbTop(newThumbTop);
            this.draggingListScrollbar = true;
            this.listDragGrabOffset = thumbH / 2;
         }
      } else if (mouseButton == 0
         && this.hasScrollableDetail()
         && this.isMouseOver(
            this.getDetailScrollbarX(),
            this.getDetailScrollbarY(),
            this.getDetailScrollbarX() + 8,
            this.getDetailScrollbarY() + this.getDetailScrollbarH(),
            mouseX,
            mouseY
         )) {
         int thumbY = this.getDetailScrollbarThumbY();
         int thumbH = this.getDetailScrollbarThumbH();
         if (mouseY >= thumbY && mouseY < thumbY + thumbH) {
            this.draggingDetailScrollbar = true;
            this.detailDragGrabOffset = mouseY - thumbY;
         } else {
            int newThumbTop = mouseY - thumbH / 2;
            this.setDetailScrollFromThumbTop(newThumbTop);
            this.draggingDetailScrollbar = true;
            this.detailDragGrabOffset = thumbH / 2;
         }
      } else {
         super.func_73864_a(mouseX, mouseY, mouseButton);
      }
   }

   protected void func_146286_b(int mouseX, int mouseY, int state) {
      this.draggingListScrollbar = false;
      this.draggingDetailScrollbar = false;
      super.func_146286_b(mouseX, mouseY, state);
   }

   protected void func_146273_a(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
      super.func_146273_a(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
      if (this.draggingDetailScrollbar && this.hasScrollableDetail()) {
         int newThumbTop = mouseY - this.detailDragGrabOffset;
         this.setDetailScrollFromThumbTop(newThumbTop);
      }

      if (this.draggingListScrollbar && this.hasScrollableList()) {
         int newThumbTop = mouseY - this.listDragGrabOffset;
         this.setListScrollFromThumbTop(newThumbTop);
      }
   }

   private void scrollList(int dir) {
      int maxScroll = Math.max(0, this.entries.size() * 14 - 180);
      this.listScrollPx = Math.max(0, Math.min(maxScroll, this.listScrollPx + dir * 14));
      this.rebuildListButtons();
   }

   private void scrollDetail(int dir) {
      int maxScroll = this.getDetailMaxScrollPx();
      this.detailScrollPx = Math.max(0, Math.min(maxScroll, this.detailScrollPx + dir * 10));
   }

   private int getDetailTextX() {
      return 164;
   }

   private int getDetailTextY() {
      return 68;
   }

   private int getDetailTextW() {
      return 192;
   }

   private int getDetailTextH() {
      return 136;
   }

   private int getDetailScrollbarX() {
      return 360;
   }

   private int getDetailScrollbarY() {
      return this.getDetailTextY();
   }

   private int getDetailScrollbarH() {
      return this.getDetailTextH();
   }

   private List<String> getDetailWrappedLines() {
      List<String> lines = new ArrayList<>();
      if (this.selectedIndex >= 0 && this.selectedIndex < this.entries.size()) {
         SystemEntry e = this.entries.get(this.selectedIndex);
         if (I18n.func_188566_a(e.descKey())) {
            String desc = this.distort(I18n.func_135052_a(e.descKey(), new Object[0]));
            lines.addAll(this.field_146289_q.func_78271_c(desc, this.getDetailTextW()));
         } else {
            lines.add(this.distort(I18n.func_135052_a("bestiary.systems.missing_desc", new Object[0])));
         }

         return lines;
      } else {
         return lines;
      }
   }

   private int getDetailContentHeightPx() {
      return this.getDetailWrappedLines().size() * 10;
   }

   private int getDetailViewportHeightPx() {
      return this.getDetailTextH();
   }

   private int getDetailMaxScrollPx() {
      return Math.max(0, this.getDetailContentHeightPx() - this.getDetailViewportHeightPx());
   }

   private boolean hasScrollableDetail() {
      return this.getDetailMaxScrollPx() > 0;
   }

   private int getDetailScrollbarThumbH() {
      int contentH = this.getDetailContentHeightPx();
      int viewH = this.getDetailViewportHeightPx();
      int trackH = this.getDetailScrollbarH();
      if (contentH > 0 && contentH > viewH) {
         int thumbH = (int)((float)viewH / contentH * trackH);
         return MathHelper.func_76125_a(thumbH, 12, trackH);
      } else {
         return trackH;
      }
   }

   private int getDetailScrollbarThumbY() {
      int trackY = this.getDetailScrollbarY();
      int trackH = this.getDetailScrollbarH();
      int thumbH = this.getDetailScrollbarThumbH();
      int maxScroll = this.getDetailMaxScrollPx();
      if (maxScroll <= 0) {
         return trackY;
      } else {
         int movable = trackH - thumbH;
         int thumbOffset = (int)((float)this.detailScrollPx / maxScroll * movable);
         return trackY + thumbOffset;
      }
   }

   private void setDetailScrollFromThumbTop(int thumbTop) {
      int trackY = this.getDetailScrollbarY();
      int trackH = this.getDetailScrollbarH();
      int thumbH = this.getDetailScrollbarThumbH();
      int movable = trackH - thumbH;
      int maxScroll = this.getDetailMaxScrollPx();
      if (movable > 0 && maxScroll > 0) {
         int clampedThumbTop = MathHelper.func_76125_a(thumbTop, trackY, trackY + movable);
         float pct = (float)(clampedThumbTop - trackY) / movable;
         this.detailScrollPx = MathHelper.func_76125_a((int)(pct * maxScroll), 0, maxScroll);
      } else {
         this.detailScrollPx = 0;
      }
   }

   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
      if (this.gearBackground != null) {
         this.gearBackground.render(partialTicks, this.isJumbled);
      } else {
         this.func_146276_q_();
      }

      String title = this.distort(I18n.func_135052_a("bestiary.systems.title", new Object[0]));
      this.func_73732_a(this.field_146289_q, title, this.field_146294_l / 2, 12, 16777215);
      func_73734_a(12, 30, 146, 214, -1442840576);
      func_73734_a(14, 32, 144, 212, 1711276032);
      func_73734_a(1, 31, 9, 213, -1442840576);
      func_73734_a(2, 32, 8, 212, 1140850688);
      if (this.hasScrollableList()) {
         int thumbY = this.getListScrollbarThumbY();
         int thumbH = this.getListScrollbarThumbH();
         func_73734_a(2, thumbY, 8, thumbY + thumbH, this.draggingListScrollbar ? -861230422 : -1716868438);
         func_73734_a(3, thumbY + 1, 7, thumbY + thumbH - 1, -857874979);
      }

      func_73734_a(154, 30, 378, 214, -1442840576);
      func_73734_a(156, 32, 376, 212, 1711276032);
      if (this.entries.isEmpty()) {
         this.func_73731_b(this.field_146289_q, this.distort(I18n.func_135052_a("bestiary.systems.none", new Object[0])), 20, 38, 16777215);
         super.func_73863_a(mouseX, mouseY, partialTicks);
      } else {
         if (this.selectedIndex >= 0 && this.selectedIndex < this.entries.size()) {
            int firstRow = this.listScrollPx / 14;
            int rel = this.selectedIndex - firstRow;
            if (rel >= 0 && rel < this.visibleRows()) {
               int y0 = 32 + rel * 14;
               func_73734_a(14, y0, 144, y0 + 14, 872415231);
            }
         }

         super.func_73863_a(mouseX, mouseY, partialTicks);
         this.enableScissor(16, 34, 126, 176);
         int firstRow = this.listScrollPx / 14;
         int maxRows = this.visibleRows();

         for (int i = 0; i < maxRows; i++) {
            int idx = firstRow + i;
            if (idx >= this.entries.size()) {
               break;
            }

            SystemEntry e = this.entries.get(idx);
            String label = this.distort(I18n.func_188566_a(e.nameKey()) ? I18n.func_135052_a(e.nameKey(), new Object[0]) : e.id);
            int centerX = 79;
            int yText = 32 + i * 14 + 3;
            int maxTextWidth = 118;
            this.drawScaledStringCentered(label, centerX, yText, maxTextWidth, 16777215);
         }

         this.disableScissor();
         if (this.selectedIndex >= 0 && this.selectedIndex < this.entries.size()) {
            SystemEntry e = this.entries.get(this.selectedIndex);
            int x = 164;
            int y = 40;
            String name = this.distort(I18n.func_188566_a(e.nameKey()) ? I18n.func_135052_a(e.nameKey(), new Object[0]) : e.id);
            this.func_73731_b(this.field_146289_q, name, x, y, 16777215);
            y += 14;
            this.func_73731_b(this.field_146289_q, this.distort(I18n.func_135052_a("bestiary.systems.id", new Object[0]) + ": " + e.id), x, y, 16777215);
            func_73734_a(
               this.getDetailTextX() - 2,
               this.getDetailTextY() - 2,
               this.getDetailTextX() + this.getDetailTextW() + 2,
               this.getDetailTextY() + this.getDetailTextH() + 2,
               855638016
            );
            List<String> lines = this.getDetailWrappedLines();
            this.enableScissor(this.getDetailTextX(), this.getDetailTextY(), this.getDetailTextW(), this.getDetailTextH());
            int yDraw = this.getDetailTextY() - this.detailScrollPx;

            for (String line : lines) {
               if (yDraw > this.getDetailTextY() + this.getDetailTextH()) {
                  break;
               }

               if (yDraw + 10 >= this.getDetailTextY()) {
                  this.func_73731_b(this.field_146289_q, line, this.getDetailTextX(), yDraw, 16777215);
               }

               yDraw += 10;
            }

            this.disableScissor();
            int trackX = this.getDetailScrollbarX();
            int trackY = this.getDetailScrollbarY();
            int trackH = this.getDetailScrollbarH();
            func_73734_a(trackX, trackY, trackX + 8, trackY + trackH, 570425344);
            int thumbY = this.getDetailScrollbarThumbY();
            int thumbH = this.getDetailScrollbarThumbH();
            int thumbColor = this.hasScrollableDetail() ? (this.draggingDetailScrollbar ? -861230422 : -863467384) : 1715749956;
            func_73734_a(trackX, thumbY, trackX + 8, thumbY + thumbH, thumbColor);
            this.drawSystemIconTopRight(e, 370, 38, 24);
         }
      }
   }

   private void drawSystemIconTopRight(SystemEntry e, int rightX, int topY, int sizePx) {
      if (e != null) {
         Minecraft mc = Minecraft.func_71410_x();
         ResourceLocation tex = this.isJumbled ? JUMBLED_ICON : e.icon;
         if (tex != null) {
            float timeSec = (float)Minecraft.func_71386_F() / 1000.0F;
            float maxTiltDeg = 6.0F;
            float speedHz = 0.2F;
            float angle = MathHelper.func_76126_a(timeSec * (float) (Math.PI * 2) * speedHz) * maxTiltDeg;
            int x = rightX - sizePx;
            mc.func_110434_K().func_110577_a(tex);
            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.func_179147_l();
            GlStateManager.func_179094_E();
            GlStateManager.func_179109_b(x + sizePx / 2.0F, topY + sizePx / 2.0F, 0.0F);
            GlStateManager.func_179114_b(angle, 0.0F, 0.0F, 1.0F);
            GlStateManager.func_179109_b(-sizePx / 2.0F, -sizePx / 2.0F, 0.0F);
            func_146110_a(0, 0, 0.0F, 0.0F, sizePx, sizePx, sizePx, sizePx);
            GlStateManager.func_179121_F();
            GlStateManager.func_179084_k();
         }
      }
   }

   private void drawScaledStringCentered(String text, int centerX, int y, int maxWidth, int color) {
      int textWidth = this.field_146289_q.func_78256_a(text);
      float scale = 1.0F;
      if (textWidth > maxWidth) {
         scale = (float)maxWidth / textWidth;
         scale = Math.max(scale, 0.65F);
      }

      GlStateManager.func_179094_E();
      GlStateManager.func_179109_b(centerX, y, 0.0F);
      GlStateManager.func_179152_a(scale, scale, 1.0F);
      this.field_146289_q.func_175065_a(text, -this.field_146289_q.func_78256_a(text) / 2, 0.0F, color, false);
      GlStateManager.func_179121_F();
   }

   private void enableScissor(int x, int y, int w, int h) {
      Minecraft mc = Minecraft.func_71410_x();
      ScaledResolution sr = new ScaledResolution(mc);
      int factor = sr.func_78325_e();
      int sx = x * factor;
      int sy = (this.field_146295_m - (y + h)) * factor;
      int sw = w * factor;
      int sh = h * factor;
      GL11.glEnable(3089);
      GL11.glScissor(sx, sy, sw, sh);
   }

   private void disableScissor() {
      GL11.glDisable(3089);
   }

   private int getListMaxScrollPx() {
      return Math.max(0, this.entries.size() * 14 - 180);
   }

   private boolean hasScrollableList() {
      return this.getListMaxScrollPx() > 0;
   }

   private int getListScrollbarThumbH() {
      int contentH = Math.max(1, this.entries.size() * 14);
      int viewH = 180;
      int trackH = 180;
      if (contentH <= viewH) {
         return trackH;
      } else {
         int thumbH = (int)((float)viewH / contentH * trackH);
         return MathHelper.func_76125_a(thumbH, 18, trackH);
      }
   }

   private int getListScrollbarThumbY() {
      int maxScroll = this.getListMaxScrollPx();
      if (maxScroll <= 0) {
         return 32;
      } else {
         int thumbH = this.getListScrollbarThumbH();
         int movable = 180 - thumbH;
         if (movable <= 0) {
            return 32;
         } else {
            int thumbOffset = (int)((float)this.listScrollPx / maxScroll * movable);
            return 32 + thumbOffset;
         }
      }
   }

   private boolean isMouseOverListScrollbar(int mouseX, int mouseY) {
      return mouseX >= 2 && mouseX < 8 && mouseY >= 32 && mouseY < 212;
   }

   private void setListScrollFromThumbTop(int thumbTop) {
      int maxScroll = this.getListMaxScrollPx();
      int thumbH = this.getListScrollbarThumbH();
      int movable = 180 - thumbH;
      if (maxScroll > 0 && movable > 0) {
         int clampedThumbTop = MathHelper.func_76125_a(thumbTop, 32, 32 + movable);
         float pct = (float)(clampedThumbTop - 32) / movable;
         this.listScrollPx = MathHelper.func_76125_a((int)(pct * maxScroll), 0, maxScroll);
         this.rebuildListButtons();
      } else {
         this.listScrollPx = 0;
      }
   }

   public boolean func_73868_f() {
      return false;
   }
}
