/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package com.subspaceparasite.bestiary.client.gui;

import com.subspaceparasite.bestiary.cap.BestiaryCapability;
import com.subspaceparasite.bestiary.cap.IBestiaryProgress;
import com.subspaceparasite.bestiary.client.gui.GuiDistortionHelper;
import com.subspaceparasite.bestiary.effects.SPStatusEffectRegistry;
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
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class StatusEffectsPage
extends GuiScreen {
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
    private static final int SCROLLBAR_W = 6;
    private static final int SCROLLBAR_GAP = 6;
    private static final int SCROLLBAR_X = 2;
    private static final int SCROLLBAR_Y = 32;
    private static final int SCROLLBAR_H = 180;
    private static final int MIN_THUMB_H = 18;
    private static final int DETAIL_PAD = 8;
    private static final int DETAIL_SCROLLBAR_W = 8;
    private static final int DETAIL_SCROLLBAR_GAP = 4;
    private static final int DETAIL_LINE_H = 10;
    private static final int DETAIL_MIN_THUMB_H = 12;
    private static final ResourceLocation POTION_TEX = new ResourceLocation("minecraft", "textures/gui/container/inventory.png");
    private final EntityPlayer player;
    private final GuiScreen parent;
    private final List<SPStatusEffectRegistry.Entry> discovered = new ArrayList<SPStatusEffectRegistry.Entry>();
    private int selectedIndex = -1;
    private int scroll = 0;
    private int detailScrollPx = 0;
    private boolean draggingScrollbar = false;
    private int scrollbarDragOffset = 0;
    private boolean draggingDetailScrollbar = false;
    private int detailDragGrabOffset = 0;
    private boolean isJumbled;

    private String distort(String s) {
        return GuiDistortionHelper.jamTextIfNeeded(s, this.isJumbled);
    }

    public StatusEffectsPage(EntityPlayer player, GuiScreen parent) {
        this.player = player;
        this.parent = parent;
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        this.isJumbled = GuiDistortionHelper.isDistortionActive(this.field_146297_k);
        this.field_146292_n.clear();
        this.field_146292_n.add(new GuiButton(1, 10, 10, 60, 20, this.distort("< " + I18n.func_135052_a((String)"bestiary.effects.home", (Object[])new Object[0]))));
        this.rebuildDiscovered();
        this.rebuildListButtons();
    }

    public boolean func_73868_f() {
        return false;
    }

    private void rebuildDiscovered() {
        this.discovered.clear();
        IBestiaryProgress prog = (IBestiaryProgress)this.player.getCapability(BestiaryCapability.CAP, null);
        if (prog == null) {
            return;
        }
        for (SPStatusEffectRegistry.Entry e : SPStatusEffectRegistry.all()) {
            if (!prog.hasSeenEffect(e.id)) continue;
            this.discovered.add(e);
        }
        if (this.selectedIndex >= this.discovered.size()) {
            int n = this.selectedIndex = this.discovered.isEmpty() ? -1 : 0;
        }
        if (this.selectedIndex < 0 && !this.discovered.isEmpty()) {
            this.selectedIndex = 0;
        }
        this.detailScrollPx = 0;
        this.draggingDetailScrollbar = false;
    }

    private String forceWhite(String s) {
        return s == null ? "" : TextFormatting.func_110646_a((String)s);
    }

    private void rebuildListButtons() {
        int idx;
        this.field_146292_n.removeIf(b -> b.field_146127_k >= 200 && b.field_146127_k < 2000);
        int firstRow = this.scroll / 14;
        int maxRows = this.visibleRows();
        for (int i = 0; i < maxRows && (idx = firstRow + i) < this.discovered.size(); ++i) {
            SPStatusEffectRegistry.Entry e = this.discovered.get(idx);
            String label = this.distort(this.resolveEffectNameList(e));
            int y = 32 + i * 14;
            this.field_146292_n.add(new GuiButton(200 + idx, 16, y, 126, 14, label));
        }
    }

    private String resolveEffectNameList(SPStatusEffectRegistry.Entry e) {
        String name = this.resolveEffectName(e);
        return TextFormatting.func_110646_a((String)name);
    }

    private int visibleRows() {
        return 12;
    }

    protected void func_146284_a(GuiButton button) throws IOException {
        int idx;
        if (button.field_146127_k == 1) {
            this.field_146297_k.func_147108_a(this.parent);
            return;
        }
        if (button.field_146127_k >= 200 && button.field_146127_k < 2000 && (idx = button.field_146127_k - 200) >= 0 && idx < this.discovered.size()) {
            this.selectedIndex = idx;
            this.detailScrollPx = 0;
            this.draggingDetailScrollbar = false;
        }
    }

    public void func_146274_d() throws IOException {
        int dir;
        super.func_146274_d();
        int dwheel = Mouse.getEventDWheel();
        if (dwheel == 0) {
            return;
        }
        int mx = Mouse.getEventX() * this.field_146294_l / this.field_146297_k.field_71443_c;
        int my = this.field_146295_m - Mouse.getEventY() * this.field_146295_m / this.field_146297_k.field_71440_d - 1;
        boolean overList = this.isMouseOver(14, 32, 144, 212, mx, my);
        boolean overDetailText = this.isMouseOver(this.getDetailTextX(), this.getDetailTextY(), this.getDetailTextX() + this.getDetailTextW(), this.getDetailTextY() + this.getDetailTextH(), mx, my);
        boolean overDetailScrollbar = this.isMouseOver(this.getDetailScrollbarX(), this.getDetailScrollbarY(), this.getDetailScrollbarX() + 8, this.getDetailScrollbarY() + this.getDetailScrollbarH(), mx, my);
        int n = dir = dwheel > 0 ? -1 : 1;
        if (overDetailText || overDetailScrollbar) {
            this.scrollDetail(dir);
        } else if (overList) {
            int maxScroll = this.getMaxScroll();
            this.scroll = Math.max(0, Math.min(maxScroll, this.scroll + dir * 14));
            this.rebuildListButtons();
        } else {
            int maxScroll = this.getMaxScroll();
            this.scroll = Math.max(0, Math.min(maxScroll, this.scroll + dir * 14));
            this.rebuildListButtons();
        }
    }

    private int getMaxScroll() {
        int contentH = this.discovered.size() * 14;
        return Math.max(0, contentH - 180);
    }

    private int getScrollbarThumbHeight() {
        int contentH = Math.max(1, this.discovered.size() * 14);
        if (contentH <= 180) {
            return 180;
        }
        int h = (int)(180.0f / (float)contentH * 180.0f);
        return Math.max(18, Math.min(180, h));
    }

    private int getScrollbarThumbY() {
        int max = this.getMaxScroll();
        if (max <= 0) {
            return 32;
        }
        int thumbH = this.getScrollbarThumbHeight();
        int trackRange = 180 - thumbH;
        if (trackRange <= 0) {
            return 32;
        }
        float t = (float)this.scroll / (float)max;
        return 32 + Math.round(t * (float)trackRange);
    }

    private boolean isMouseOverScrollbar(int mouseX, int mouseY) {
        return mouseX >= 2 && mouseX < 8 && mouseY >= 32 && mouseY < 212;
    }

    private void setScrollFromThumbTop(int thumbTop) {
        int trackRange;
        int max = this.getMaxScroll();
        if (max <= 0) {
            this.scroll = 0;
            return;
        }
        int thumbH = this.getScrollbarThumbHeight();
        int trackMin = 32;
        int trackMax = 212 - thumbH;
        if (thumbTop < trackMin) {
            thumbTop = trackMin;
        }
        if (thumbTop > trackMax) {
            thumbTop = trackMax;
        }
        if ((trackRange = trackMax - trackMin) <= 0) {
            this.scroll = 0;
            return;
        }
        float t = (float)(thumbTop - trackMin) / (float)trackRange;
        this.scroll = Math.round(t * (float)max);
        if (this.scroll < 0) {
            this.scroll = 0;
        }
        if (this.scroll > max) {
            this.scroll = max;
        }
        this.rebuildListButtons();
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
        ArrayList<String> lines = new ArrayList<String>();
        if (this.selectedIndex < 0 || this.selectedIndex >= this.discovered.size()) {
            return lines;
        }
        SPStatusEffectRegistry.Entry e = this.discovered.get(this.selectedIndex);
        String descKey = "bestiary.effect." + e.id + ".desc";
        if (I18n.func_188566_a((String)descKey)) {
            String desc = this.distort(I18n.func_135052_a((String)descKey, (Object[])new Object[0]));
            lines.addAll(this.field_146289_q.func_78271_c(desc, this.getDetailTextW()));
        } else {
            lines.add(this.distort(I18n.func_135052_a((String)"bestiary.effects.missing_desc", (Object[])new Object[0])));
        }
        return lines;
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
        if (contentH <= 0 || contentH <= viewH) {
            return trackH;
        }
        int thumbH = (int)((float)viewH / (float)contentH * (float)trackH);
        return MathHelper.func_76125_a((int)thumbH, (int)12, (int)trackH);
    }

    private int getDetailScrollbarThumbY() {
        int trackY = this.getDetailScrollbarY();
        int trackH = this.getDetailScrollbarH();
        int thumbH = this.getDetailScrollbarThumbH();
        int maxScroll = this.getDetailMaxScrollPx();
        if (maxScroll <= 0) {
            return trackY;
        }
        int movable = trackH - thumbH;
        int thumbOffset = (int)((float)this.detailScrollPx / (float)maxScroll * (float)movable);
        return trackY + thumbOffset;
    }

    private void setDetailScrollFromThumbTop(int thumbTop) {
        int trackY = this.getDetailScrollbarY();
        int trackH = this.getDetailScrollbarH();
        int thumbH = this.getDetailScrollbarThumbH();
        int movable = trackH - thumbH;
        int maxScroll = this.getDetailMaxScrollPx();
        if (movable <= 0 || maxScroll <= 0) {
            this.detailScrollPx = 0;
            return;
        }
        int clampedThumbTop = MathHelper.func_76125_a((int)thumbTop, (int)trackY, (int)(trackY + movable));
        float pct = (float)(clampedThumbTop - trackY) / (float)movable;
        this.detailScrollPx = MathHelper.func_76125_a((int)((int)(pct * (float)maxScroll)), (int)0, (int)maxScroll);
    }

    private boolean isMouseOver(int x1, int y1, int x2, int y2, int mx, int my) {
        return mx >= x1 && mx < x2 && my >= y1 && my < y2;
    }

    private void enableScissor(int x, int y, int w, int h) {
        Minecraft mc = Minecraft.func_71410_x();
        ScaledResolution sr = new ScaledResolution(mc);
        int factor = sr.func_78325_e();
        int sx = x * factor;
        int sy = (this.field_146295_m - (y + h)) * factor;
        int sw = w * factor;
        int sh = h * factor;
        GL11.glEnable((int)3089);
        GL11.glScissor((int)sx, (int)sy, (int)sw, (int)sh);
    }

    private void disableScissor() {
        GL11.glDisable((int)3089);
    }

    private void scrollDetail(int dir) {
        int maxScroll = this.getDetailMaxScrollPx();
        this.detailScrollPx = Math.max(0, Math.min(maxScroll, this.detailScrollPx + dir * 10));
    }

    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        int firstRow;
        int rel;
        int thumbTop;
        this.func_146276_q_();
        if (this.draggingScrollbar) {
            thumbTop = mouseY - this.scrollbarDragOffset;
            this.setScrollFromThumbTop(thumbTop);
        }
        if (this.draggingDetailScrollbar) {
            thumbTop = mouseY - this.detailDragGrabOffset;
            this.setDetailScrollFromThumbTop(thumbTop);
        }
        String title = this.distort(I18n.func_135052_a((String)"bestiary.effects.title", (Object[])new Object[0]));
        this.func_73732_a(this.field_146289_q, title, this.field_146294_l / 2, 12, 0xFFFFFF);
        StatusEffectsPage.func_73734_a((int)12, (int)30, (int)146, (int)214, (int)-1442840576);
        StatusEffectsPage.func_73734_a((int)14, (int)32, (int)144, (int)212, (int)0x66000000);
        StatusEffectsPage.func_73734_a((int)1, (int)31, (int)9, (int)213, (int)-1442840576);
        StatusEffectsPage.func_73734_a((int)2, (int)32, (int)8, (int)212, (int)0x44000000);
        if (this.getMaxScroll() > 0) {
            int thumbY = this.getScrollbarThumbY();
            int thumbH = this.getScrollbarThumbHeight();
            StatusEffectsPage.func_73734_a((int)2, (int)thumbY, (int)8, (int)(thumbY + thumbH), (int)-1716868438);
            StatusEffectsPage.func_73734_a((int)3, (int)(thumbY + 1), (int)7, (int)(thumbY + thumbH - 1), (int)-857874979);
        }
        StatusEffectsPage.func_73734_a((int)154, (int)30, (int)378, (int)214, (int)-1442840576);
        StatusEffectsPage.func_73734_a((int)156, (int)32, (int)376, (int)212, (int)0x66000000);
        if (this.discovered.isEmpty()) {
            String s = this.distort(I18n.func_135052_a((String)"bestiary.effects.none", (Object[])new Object[0]));
            this.func_73731_b(this.field_146289_q, s, 20, 38, 0xFFFFFF);
            String hint = this.distort(I18n.func_135052_a((String)"bestiary.effects.select_hint", (Object[])new Object[0]));
            this.func_73731_b(this.field_146289_q, hint, 164, 40, 0xFFFFFF);
            super.func_73863_a(mouseX, mouseY, partialTicks);
            return;
        }
        if (this.selectedIndex >= 0 && this.selectedIndex < this.discovered.size() && (rel = this.selectedIndex - (firstRow = this.scroll / 14)) >= 0 && rel < this.visibleRows()) {
            int y0 = 32 + rel * 14;
            StatusEffectsPage.func_73734_a((int)14, (int)y0, (int)144, (int)(y0 + 14), (int)0x33FFFFFF);
        }
        if (this.selectedIndex >= 0 && this.selectedIndex < this.discovered.size()) {
            SPStatusEffectRegistry.Entry e = this.discovered.get(this.selectedIndex);
            int x = 164;
            int y = 40;
            String name = this.distort(this.resolveEffectName(e));
            this.func_73731_b(this.field_146289_q, name, x, y, 0xFFFFFF);
            this.func_73731_b(this.field_146289_q, this.distort(I18n.func_135052_a((String)"bestiary.effects.id", (Object[])new Object[0]) + ": " + e.id), x, y += 14, 0xFFFFFF);
            StatusEffectsPage.func_73734_a((int)(this.getDetailTextX() - 2), (int)(this.getDetailTextY() - 2), (int)(this.getDetailTextX() + this.getDetailTextW() + 2), (int)(this.getDetailTextY() + this.getDetailTextH() + 2), (int)0x33000000);
            List<String> lines = this.getDetailWrappedLines();
            this.enableScissor(this.getDetailTextX(), this.getDetailTextY(), this.getDetailTextW(), this.getDetailTextH());
            int yDraw = this.getDetailTextY() - this.detailScrollPx;
            for (String line : lines) {
                if (yDraw > this.getDetailTextY() + this.getDetailTextH()) break;
                if (yDraw + 10 >= this.getDetailTextY()) {
                    this.func_73731_b(this.field_146289_q, line, this.getDetailTextX(), yDraw, 0xFFFFFF);
                }
                yDraw += 10;
            }
            this.disableScissor();
            int trackX = this.getDetailScrollbarX();
            int trackY = this.getDetailScrollbarY();
            int trackH = this.getDetailScrollbarH();
            StatusEffectsPage.func_73734_a((int)trackX, (int)trackY, (int)(trackX + 8), (int)(trackY + trackH), (int)0x22000000);
            int thumbY = this.getDetailScrollbarThumbY();
            int thumbH = this.getDetailScrollbarThumbH();
            int thumbColor = this.hasScrollableDetail() ? (this.draggingDetailScrollbar ? -861230422 : -863467384) : 0x66444444;
            StatusEffectsPage.func_73734_a((int)trackX, (int)thumbY, (int)(trackX + 8), (int)(thumbY + thumbH), (int)thumbColor);
            this.drawPotionIconTopRight(e, 370, 38, 24);
        }
        super.func_73863_a(mouseX, mouseY, partialTicks);
    }

    private void drawPotionIconTopRight(SPStatusEffectRegistry.Entry e, int rightX, int topY, int sizePx) {
        if (e == null || e.potion == null || e.id == null) {
            return;
        }
        ResourceLocation tex = new ResourceLocation("subspaceparasite", "textures/gui/potion_" + e.id + ".png");
        Minecraft mc = Minecraft.func_71410_x();
        mc.func_110434_K().func_110577_a(tex);
        float timeSec = (float)mc.func_71386_F() / 1000.0f;
        float maxTiltDeg = 6.0f;
        float speedHz = 0.2f;
        float angle = MathHelper.func_76126_a((float)(timeSec * ((float)Math.PI * 2) * speedHz)) * maxTiltDeg;
        int x = rightX - sizePx;
        int y = topY;
        GlStateManager.func_179094_E();
        GlStateManager.func_179147_l();
        GlStateManager.func_187428_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.func_179109_b((float)((float)x + (float)sizePx / 2.0f), (float)((float)y + (float)sizePx / 2.0f), (float)0.0f);
        GlStateManager.func_179114_b((float)angle, (float)0.0f, (float)0.0f, (float)1.0f);
        GlStateManager.func_179109_b((float)((float)(-sizePx) / 2.0f), (float)((float)(-sizePx) / 2.0f), (float)0.0f);
        StatusEffectsPage.func_146110_a((int)0, (int)0, (float)0.0f, (float)0.0f, (int)sizePx, (int)sizePx, (float)sizePx, (float)sizePx);
        GlStateManager.func_179121_F();
        GlStateManager.func_179084_k();
    }

    private String resolveEffectName(SPStatusEffectRegistry.Entry e) {
        String bestiaryKey = "bestiary.effect." + e.id + ".name";
        if (I18n.func_188566_a((String)bestiaryKey)) {
            return I18n.func_135052_a((String)bestiaryKey, (Object[])new Object[0]);
        }
        if (e.potion != null && e.potion.getRegistryName() != null) {
            String mobEffectKey = "mob_effect." + e.potion.getRegistryName().toString();
            if (I18n.func_188566_a((String)mobEffectKey)) {
                return I18n.func_135052_a((String)mobEffectKey, (Object[])new Object[0]);
            }
            String maybeKey = e.potion.func_76393_a();
            if (maybeKey != null && I18n.func_188566_a((String)maybeKey)) {
                return I18n.func_135052_a((String)maybeKey, (Object[])new Object[0]);
            }
        }
        return e.id;
    }

    protected void func_73864_a(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.func_73864_a(mouseX, mouseY, mouseButton);
        if (mouseButton == 0 && this.getMaxScroll() > 0 && this.isMouseOverScrollbar(mouseX, mouseY)) {
            int thumbY = this.getScrollbarThumbY();
            int thumbH = this.getScrollbarThumbHeight();
            if (mouseY >= thumbY && mouseY < thumbY + thumbH) {
                this.draggingScrollbar = true;
                this.scrollbarDragOffset = mouseY - thumbY;
            } else {
                int newThumbTop = mouseY - thumbH / 2;
                this.setScrollFromThumbTop(newThumbTop);
                this.draggingScrollbar = true;
                this.scrollbarDragOffset = thumbH / 2;
            }
            return;
        }
        if (mouseButton == 0 && this.hasScrollableDetail() && this.isMouseOver(this.getDetailScrollbarX(), this.getDetailScrollbarY(), this.getDetailScrollbarX() + 8, this.getDetailScrollbarY() + this.getDetailScrollbarH(), mouseX, mouseY)) {
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
        }
    }

    protected void func_146286_b(int mouseX, int mouseY, int state) {
        super.func_146286_b(mouseX, mouseY, state);
        if (state == 0) {
            this.draggingScrollbar = false;
            this.draggingDetailScrollbar = false;
        }
    }

    protected void func_146273_a(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        int thumbTop;
        super.func_146273_a(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
        if (this.draggingScrollbar && this.getMaxScroll() > 0) {
            thumbTop = mouseY - this.scrollbarDragOffset;
            this.setScrollFromThumbTop(thumbTop);
        }
        if (this.draggingDetailScrollbar && this.hasScrollableDetail()) {
            thumbTop = mouseY - this.detailDragGrabOffset;
            this.setDetailScrollFromThumbTop(thumbTop);
        }
    }
}

