/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.PositionedSoundRecord
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package com.dhanantry.scapeandrunparasites.bestiary.client.gui;

import com.dhanantry.scapeandrunparasites.bestiary.cap.BestiaryCapability;
import com.dhanantry.scapeandrunparasites.bestiary.cap.IBestiaryProgress;
import com.dhanantry.scapeandrunparasites.bestiary.client.gui.GuiDistortionHelper;
import com.dhanantry.scapeandrunparasites.bestiary.net.BestiaryNetwork;
import com.dhanantry.scapeandrunparasites.bestiary.net.PacketBestiaryRequest;
import com.dhanantry.scapeandrunparasites.client.celestial.CelestialObjectDefinition;
import com.dhanantry.scapeandrunparasites.client.celestial.CelestialObjectRegistry;
import com.dhanantry.scapeandrunparasites.client.celestial.CelestialPhaseClient;
import com.dhanantry.scapeandrunparasites.client.gui.StarfieldBackground;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class CelestialEventsPage
extends GuiScreen {
    private final EntityPlayer player;
    private final GuiScreen parent;
    private StarfieldBackground starfield;
    private boolean isJumbled;
    private final List<CelestialObjectDefinition> discovered = new ArrayList<CelestialObjectDefinition>();
    private int scroll = 0;
    private int selectedIndex = -1;
    private boolean draggingScrollbar = false;
    private int scrollbarDragOffset = 0;
    private int detailScrollPx = 0;
    private boolean draggingDetailScrollbar = false;
    private int detailDragGrabOffset = 0;
    private static final int TOP = 28;
    private static final int LIST_X = 16;
    private static final int LIST_Y = 38;
    private static final int LIST_W = 150;
    private static final int LIST_H = 180;
    private static final int ROW_H = 18;
    private static final int DETAIL_X = 182;
    private static final int DETAIL_Y = 38;
    private static final int DETAIL_W = 220;
    private static final int DETAIL_H = 180;
    private static final int SCROLLBAR_W = 6;
    private static final int SCROLLBAR_GAP = 6;
    private static final int SCROLLBAR_X = 4;
    private static final int SCROLLBAR_Y = 38;
    private static final int SCROLLBAR_H = 180;
    private static final int MIN_THUMB_H = 18;
    private static final int DETAIL_PAD = 12;
    private static final int DETAIL_SCROLLBAR_W = 8;
    private static final int DETAIL_SCROLLBAR_GAP = 4;
    private static final int DETAIL_LINE_H = 10;
    private static final int DETAIL_MIN_THUMB_H = 12;
    private static final int DETAIL_HEADER_H = 62;
    private static final int DETAIL_TEXT_LEFT_INSET = 4;
    private static final int DETAIL_TEXT_RIGHT_INSET = 2;
    private static final int DETAIL_TEXT_TOP_INSET = 2;
    private static final int DETAIL_TEXT_BOTTOM_INSET = 2;
    private static final int DETAIL_SPRITE_SIZE = 72;
    private static final int DETAIL_SPRITE_BOTTOM_PAD = 8;
    private static final int DETAIL_SPRITE_AREA_H = 88;
    private static final int ACTIVE_ORB_SIZE = 10;
    private static final int ACTIVE_ORB_MARGIN_LEFT = 4;
    private static final int COUNTER_Y = 226;
    private static final int COUNTER_W = 150;
    private static final ResourceLocation JUMBLED_ICON = new ResourceLocation("srparasites", "textures/gui/question_mark_small.png");
    private static final ResourceLocation EXP_ORB_TEX = new ResourceLocation("textures/entity/experience_orb.png");

    private String distort(String s) {
        return GuiDistortionHelper.jamTextIfNeeded(s, this.isJumbled);
    }

    private int getTotalCelestialCount() {
        return CelestialObjectRegistry.getObjectCount();
    }

    public CelestialEventsPage(EntityPlayer player, GuiScreen parent) {
        this.player = player;
        this.parent = parent;
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        if (this.starfield == null) {
            this.starfield = new StarfieldBackground(this.field_146297_k);
        }
        this.isJumbled = GuiDistortionHelper.isDistortionActive(this.field_146297_k);
        this.field_146292_n.clear();
        this.field_146292_n.add(new GuiButton(1, 10, 10, 60, 20, this.distort("< " + I18n.func_135052_a((String)"bestiary.celestial.home", (Object[])new Object[0]))));
        BestiaryNetwork.CH.sendToServer((IMessage)new PacketBestiaryRequest());
    }

    private void syncDiscovered() {
        this.discovered.clear();
        IBestiaryProgress prog = (IBestiaryProgress)this.player.getCapability(BestiaryCapability.CAP, null);
        if (prog == null) {
            return;
        }
        for (CelestialObjectDefinition def : CelestialObjectRegistry.getObjects()) {
            if (!prog.hasSeenCelestial(def.id)) continue;
            this.discovered.add(def);
        }
        Collections.sort(this.discovered, new Comparator<CelestialObjectDefinition>(){

            @Override
            public int compare(CelestialObjectDefinition a, CelestialObjectDefinition b) {
                return a.id.compareToIgnoreCase(b.id);
            }
        });
        if (this.selectedIndex >= this.discovered.size()) {
            int n = this.selectedIndex = this.discovered.isEmpty() ? -1 : 0;
        }
        if (this.selectedIndex < 0 && !this.discovered.isEmpty()) {
            this.selectedIndex = 0;
        }
        this.detailScrollPx = 0;
        this.draggingDetailScrollbar = false;
    }

    private int getMaxScroll() {
        int contentH = this.discovered.size() * 18;
        int max = contentH - 180;
        return Math.max(0, max);
    }

    private int visibleRows() {
        return 10;
    }

    private int getScrollbarThumbHeight() {
        int contentH = Math.max(1, this.discovered.size() * 18);
        if (contentH <= 180) {
            return 180;
        }
        int h = (int)(180.0f / (float)contentH * 180.0f);
        return Math.max(18, Math.min(180, h));
    }

    private int getScrollbarThumbY() {
        int max = this.getMaxScroll();
        if (max <= 0) {
            return 38;
        }
        int thumbH = this.getScrollbarThumbHeight();
        int trackRange = 180 - thumbH;
        if (trackRange <= 0) {
            return 38;
        }
        float t = (float)this.scroll / (float)max;
        return 38 + Math.round(t * (float)trackRange);
    }

    private boolean isMouseOverScrollbar(int mouseX, int mouseY) {
        return mouseX >= 4 && mouseX < 10 && mouseY >= 38 && mouseY < 218;
    }

    private void setScrollFromThumbTop(int thumbTop) {
        int trackRange;
        int max = this.getMaxScroll();
        if (max <= 0) {
            this.scroll = 0;
            return;
        }
        int thumbH = this.getScrollbarThumbHeight();
        int trackMin = 38;
        int trackMax = 218 - thumbH;
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
    }

    private void rebuildListButtons() {
        int idx;
        while (this.field_146292_n.size() > 1) {
            this.field_146292_n.remove(this.field_146292_n.size() - 1);
        }
        int firstRow = this.scroll / 18;
        int rows = this.visibleRows();
        for (int i = 0; i < rows && (idx = firstRow + i) >= 0 && idx < this.discovered.size(); ++i) {
            CelestialObjectDefinition def = this.discovered.get(idx);
            String nameKey = "bestiary.celestial." + def.id + ".name";
            String label = this.distort(I18n.func_188566_a((String)nameKey) ? I18n.func_135052_a((String)nameKey, (Object[])new Object[0]) : def.id);
            int y = 38 + i * 18;
            GuiButton b = new GuiButton(100 + idx, 16, y, 150, 18, label);
            this.field_146292_n.add(b);
        }
    }

    protected void func_146284_a(GuiButton button) throws IOException {
        int idx;
        if (button.field_146127_k == 1) {
            this.field_146297_k.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_184371_a((SoundEvent)SoundEvents.field_187909_gi, (float)1.0f));
            this.field_146297_k.func_147108_a(this.parent);
            return;
        }
        if (button.field_146127_k >= 100 && (idx = button.field_146127_k - 100) >= 0 && idx < this.discovered.size()) {
            this.selectedIndex = idx;
            this.detailScrollPx = 0;
            this.draggingDetailScrollbar = false;
            this.field_146297_k.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_184371_a((SoundEvent)SoundEvents.field_187909_gi, (float)1.0f));
        }
    }

    public void func_146274_d() throws IOException {
        int dir;
        super.func_146274_d();
        int dWheel = Mouse.getEventDWheel();
        if (dWheel == 0) {
            return;
        }
        int mx = Mouse.getEventX() * this.field_146294_l / this.field_146297_k.field_71443_c;
        int my = this.field_146295_m - Mouse.getEventY() * this.field_146295_m / this.field_146297_k.field_71440_d - 1;
        boolean overList = this.isMouseOver(16, 38, 166, 218, mx, my);
        boolean overDetailText = this.isMouseOver(this.getDetailTextScissorX(), this.getDetailTextScissorY(), this.getDetailTextScissorX() + this.getDetailTextScissorW(), this.getDetailTextScissorY() + this.getDetailTextScissorH(), mx, my);
        boolean overDetailScrollbar = this.isMouseOver(this.getDetailScrollbarX(), this.getDetailScrollbarY(), this.getDetailScrollbarX() + 8, this.getDetailScrollbarY() + this.getDetailScrollbarH(), mx, my);
        int n = dir = dWheel > 0 ? -1 : 1;
        if (overDetailText || overDetailScrollbar) {
            this.scrollDetail(dir);
        } else {
            this.scroll = Math.max(0, Math.min(this.getMaxScroll(), this.scroll + dir * 18));
            this.rebuildListButtons();
        }
    }

    private boolean isMouseOver(int x1, int y1, int x2, int y2, int mx, int my) {
        return mx >= x1 && mx < x2 && my >= y1 && my < y2;
    }

    private int getDetailTextX() {
        return 198;
    }

    private int getDetailTextY() {
        return 102;
    }

    private int getDetailTextW() {
        return 178;
    }

    private int getDetailTextH() {
        return 26;
    }

    private int getDetailTextScissorX() {
        return 194;
    }

    private int getDetailTextScissorY() {
        return 100;
    }

    private int getDetailTextScissorW() {
        return 184;
    }

    private int getDetailTextScissorH() {
        return 30;
    }

    private int getDetailScrollbarX() {
        return 382;
    }

    private int getDetailScrollbarY() {
        return this.getDetailTextScissorY();
    }

    private int getDetailScrollbarH() {
        return this.getDetailTextScissorH();
    }

    private List<String> getDetailWrappedLines() {
        ArrayList<String> lines = new ArrayList<String>();
        if (this.selectedIndex < 0 || this.selectedIndex >= this.discovered.size()) {
            return lines;
        }
        CelestialObjectDefinition def = this.discovered.get(this.selectedIndex);
        String descKey = "bestiary.celestial." + def.id + ".desc";
        if (I18n.func_188566_a((String)descKey)) {
            String desc = this.distort(I18n.func_135052_a((String)descKey, (Object[])new Object[0]));
            desc = TextFormatting.ITALIC + desc;
            lines.addAll(this.field_146289_q.func_78271_c(desc, this.getDetailTextW()));
        } else {
            lines.add(TextFormatting.ITALIC + this.distort(I18n.func_135052_a((String)"bestiary.celestial.missing_desc", (Object[])new Object[0])));
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

    private void scrollDetail(int dir) {
        int maxScroll = this.getDetailMaxScrollPx();
        this.detailScrollPx = Math.max(0, Math.min(maxScroll, this.detailScrollPx + dir * 10));
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

    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        int idx;
        int thumbTop;
        this.func_146276_q_();
        if (this.starfield != null) {
            this.starfield.render(mouseX, mouseY, partialTicks, this.isJumbled);
        } else {
            this.func_146276_q_();
        }
        if (this.draggingScrollbar) {
            thumbTop = mouseY - this.scrollbarDragOffset;
            this.setScrollFromThumbTop(thumbTop);
            this.rebuildListButtons();
        }
        if (this.draggingDetailScrollbar) {
            thumbTop = mouseY - this.detailDragGrabOffset;
            this.setDetailScrollFromThumbTop(thumbTop);
        }
        String title = this.distort(I18n.func_135052_a((String)"bestiary.celestial.title", (Object[])new Object[0]));
        this.func_73732_a(this.field_146289_q, title, this.field_146294_l / 2, 12, 0xFFFFFF);
        CelestialEventsPage.func_73734_a((int)14, (int)36, (int)168, (int)220, (int)-1442840576);
        CelestialEventsPage.func_73734_a((int)16, (int)38, (int)166, (int)218, (int)0x66000000);
        CelestialEventsPage.func_73734_a((int)3, (int)37, (int)11, (int)219, (int)-1442840576);
        CelestialEventsPage.func_73734_a((int)4, (int)38, (int)10, (int)218, (int)0x44000000);
        if (this.getMaxScroll() > 0) {
            int thumbY = this.getScrollbarThumbY();
            int thumbH = this.getScrollbarThumbHeight();
            CelestialEventsPage.func_73734_a((int)4, (int)thumbY, (int)10, (int)(thumbY + thumbH), (int)-1716868438);
            CelestialEventsPage.func_73734_a((int)5, (int)(thumbY + 1), (int)9, (int)(thumbY + thumbH - 1), (int)-857874979);
        }
        if (this.discovered.isEmpty()) {
            String s = this.distort(I18n.func_135052_a((String)"bestiary.celestial.none", (Object[])new Object[0]));
            this.func_73731_b(this.field_146289_q, s, 22, 44, 0xFFFFFF);
        }
        CelestialEventsPage.func_73734_a((int)180, (int)36, (int)404, (int)220, (int)-1442840576);
        CelestialEventsPage.func_73734_a((int)182, (int)38, (int)402, (int)218, (int)0x66000000);
        if (this.selectedIndex >= 0 && this.selectedIndex < this.discovered.size()) {
            CelestialObjectDefinition def = this.discovered.get(this.selectedIndex);
            int x = 194;
            int y = 50;
            String nameKey = "bestiary.celestial." + def.id + ".name";
            String name = this.distort(I18n.func_188566_a((String)nameKey) ? I18n.func_135052_a((String)nameKey, (Object[])new Object[0]) : def.id);
            this.func_73731_b(this.field_146289_q, TextFormatting.AQUA + name, x, y, 0xFFFFFF);
            this.func_73731_b(this.field_146289_q, this.distort(I18n.func_135052_a((String)"bestiary.celestial.id", (Object[])new Object[0]) + ": " + def.id), x, y += 14, 0xFFFFFF);
            this.func_73731_b(this.field_146289_q, this.distort(I18n.func_135052_a((String)"bestiary.celestial.phase", (Object[])new Object[0]) + ": " + def.minPhase + " - " + def.maxPhase), x, y += 12, 0xFFFFFF);
            y += 12;
            float pct = def.chancePerNight * 100.0f;
            String pctStr = String.format(Locale.US, "%.2f", Float.valueOf(pct));
            float chanceT = def.chancePerNight;
            if (chanceT < 0.0f) {
                chanceT = 0.0f;
            }
            if (chanceT > 1.0f) {
                chanceT = 1.0f;
            }
            int red = (int)(255.0f * (1.0f - chanceT));
            int green = (int)(255.0f * chanceT);
            int blue = 70;
            int chanceColor = red << 16 | green << 8 | blue;
            this.func_73731_b(this.field_146289_q, this.distort(I18n.func_135052_a((String)"bestiary.celestial.chance", (Object[])new Object[0]) + ": " + pctStr + "%"), x, y, chanceColor);
            CelestialEventsPage.func_73734_a((int)(this.getDetailTextScissorX() - 2), (int)(this.getDetailTextScissorY() - 2), (int)(this.getDetailTextScissorX() + this.getDetailTextScissorW() + 2), (int)(this.getDetailTextScissorY() + this.getDetailTextScissorH() + 2), (int)0x33000000);
            int spriteAreaTop = 130;
            CelestialEventsPage.func_73734_a((int)192, (int)spriteAreaTop, (int)392, (int)212, (int)0x22000000);
            List<String> lines = this.getDetailWrappedLines();
            this.enableScissor(this.getDetailTextScissorX(), this.getDetailTextScissorY(), this.getDetailTextScissorW(), this.getDetailTextScissorH());
            int yDraw = this.getDetailTextY() - this.detailScrollPx;
            for (String line : lines) {
                if (yDraw > this.getDetailTextScissorY() + this.getDetailTextScissorH()) break;
                if (yDraw + 10 >= this.getDetailTextScissorY()) {
                    this.func_73731_b(this.field_146289_q, line, this.getDetailTextX(), yDraw, 0xB8B8B8);
                }
                yDraw += 10;
            }
            this.disableScissor();
            int trackX = this.getDetailScrollbarX();
            int trackY = this.getDetailScrollbarY();
            int trackH = this.getDetailScrollbarH();
            CelestialEventsPage.func_73734_a((int)trackX, (int)trackY, (int)(trackX + 8), (int)(trackY + trackH), (int)0x22000000);
            int detailThumbY = this.getDetailScrollbarThumbY();
            int detailThumbH = this.getDetailScrollbarThumbH();
            int thumbColor = this.hasScrollableDetail() ? (this.draggingDetailScrollbar ? -861230422 : -863467384) : 0x66444444;
            CelestialEventsPage.func_73734_a((int)trackX, (int)detailThumbY, (int)(trackX + 8), (int)(detailThumbY + detailThumbH), (int)thumbColor);
            int cx = 292;
            int cy = 174;
            this.drawCelestialSprite(def, cx, cy, 72);
        } else {
            String hint = this.distort(I18n.func_135052_a((String)"bestiary.celestial.select_hint", (Object[])new Object[0]));
            this.func_73731_b(this.field_146289_q, hint, 190, 46, 0xFFFFFF);
        }
        super.func_73863_a(mouseX, mouseY, partialTicks);
        String counter = this.distort(I18n.func_135052_a((String)"bestiary.celestial.counter", (Object[])new Object[]{this.discovered.size(), this.getTotalCelestialCount()}));
        this.func_73732_a(this.field_146289_q, counter, 91, 226, 0xCCCCCC);
        int hoveredOrbIndex = -1;
        int firstRow = this.scroll / 18;
        for (int i = 0; i < this.visibleRows() && (idx = firstRow + i) >= 0 && idx < this.discovered.size(); ++i) {
            CelestialObjectDefinition def = this.discovered.get(idx);
            if (!this.isCelestialEventActiveTonight(def)) continue;
            int rowY = 38 + i * 18;
            int orbX = 20;
            int orbY = rowY + 4;
            this.drawActiveOrb(orbX, orbY, 10);
            if (!this.isMouseOverActiveOrb(mouseX, mouseY, orbX, orbY, 10)) continue;
            hoveredOrbIndex = idx;
        }
        if (hoveredOrbIndex >= 0 && hoveredOrbIndex < this.discovered.size()) {
            ArrayList<String> tooltip = new ArrayList<String>();
            tooltip.add(this.distort(I18n.func_135052_a((String)"bestiary.celestial.ongoing", (Object[])new Object[0])));
            this.func_146283_a(tooltip, mouseX, mouseY);
        }
    }

    public void refreshFromCapability() {
        this.syncDiscovered();
        this.rebuildListButtons();
    }

    private void drawCelestialSprite(CelestialObjectDefinition def, int centerX, int centerY, int sizePx) {
        ResourceLocation tex;
        if (def == null) {
            return;
        }
        ResourceLocation resourceLocation = tex = this.isJumbled ? JUMBLED_ICON : def.texture;
        if (tex == null) {
            return;
        }
        int frames = 1;
        int frame = 0;
        if (!this.isJumbled) {
            frames = Math.max(1, def.frameCount);
            int fTime = Math.max(1, def.frameTimeTicks);
            if (def.animated && frames > 1) {
                int t = Minecraft.func_71410_x().field_71439_g != null ? Minecraft.func_71410_x().field_71439_g.field_70173_aa : 0;
                frame = t / fTime % frames;
            }
        }
        float u0 = 0.0f;
        float u1 = 1.0f;
        float v0 = (float)frame / (float)frames;
        float v1 = (float)(frame + 1) / (float)frames;
        GlStateManager.func_179094_E();
        GlStateManager.func_179147_l();
        GlStateManager.func_179118_c();
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.func_179109_b((float)centerX, (float)centerY, (float)0.0f);
        GlStateManager.func_179109_b((float)((float)(-sizePx) / 2.0f), (float)((float)(-sizePx) / 2.0f), (float)0.0f);
        this.field_146297_k.func_110434_K().func_110577_a(tex);
        Tessellator tess = Tessellator.func_178181_a();
        BufferBuilder bb = tess.func_178180_c();
        bb.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        bb.func_181662_b(0.0, (double)sizePx, 0.0).func_187315_a((double)u0, (double)v1).func_181675_d();
        bb.func_181662_b((double)sizePx, (double)sizePx, 0.0).func_187315_a((double)u1, (double)v1).func_181675_d();
        bb.func_181662_b((double)sizePx, 0.0, 0.0).func_187315_a((double)u1, (double)v0).func_181675_d();
        bb.func_181662_b(0.0, 0.0, 0.0).func_187315_a((double)u0, (double)v0).func_181675_d();
        tess.func_78381_a();
        GlStateManager.func_179141_d();
        GlStateManager.func_179084_k();
        GlStateManager.func_179121_F();
    }

    private boolean isCelestialEventActiveTonight(CelestialObjectDefinition def) {
        if (def == null || this.field_146297_k == null || this.field_146297_k.field_71441_e == null) {
            return false;
        }
        if (!this.field_146297_k.field_71441_e.field_73011_w.func_76569_d()) {
            return false;
        }
        int dim = this.field_146297_k.field_71441_e.field_73011_w.getDimension();
        int phase = CelestialPhaseClient.getPhase(dim);
        boolean isForced = CelestialPhaseClient.isForcedTonight(dim, def.id);
        if (isForced) {
            return true;
        }
        if (!def.isPhaseAllowed(phase)) {
            return false;
        }
        return CelestialPhaseClient.isActiveTonight(dim, def.id);
    }

    private void drawActiveOrb(int x, int y, int size) {
        this.field_146297_k.func_110434_K().func_110577_a(EXP_ORB_TEX);
        GlStateManager.func_179094_E();
        GlStateManager.func_179147_l();
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        float u0 = 0.0f;
        float u1 = 0.25f;
        float v0 = 0.0f;
        float v1 = 0.25f;
        Tessellator tess = Tessellator.func_178181_a();
        BufferBuilder bb = tess.func_178180_c();
        bb.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        bb.func_181662_b((double)x, (double)(y + size), 0.0).func_187315_a((double)u0, (double)v1).func_181675_d();
        bb.func_181662_b((double)(x + size), (double)(y + size), 0.0).func_187315_a((double)u1, (double)v1).func_181675_d();
        bb.func_181662_b((double)(x + size), (double)y, 0.0).func_187315_a((double)u1, (double)v0).func_181675_d();
        bb.func_181662_b((double)x, (double)y, 0.0).func_187315_a((double)u0, (double)v0).func_181675_d();
        tess.func_78381_a();
        GlStateManager.func_179084_k();
        GlStateManager.func_179121_F();
    }

    private boolean isMouseOverActiveOrb(int mouseX, int mouseY, int orbX, int orbY, int orbSize) {
        return mouseX >= orbX && mouseX < orbX + orbSize && mouseY >= orbY && mouseY < orbY + orbSize;
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
                this.rebuildListButtons();
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
            this.rebuildListButtons();
        }
        if (this.draggingDetailScrollbar && this.hasScrollableDetail()) {
            thumbTop = mouseY - this.detailDragGrabOffset;
            this.setDetailScrollFromThumbTop(thumbTop);
        }
    }

    public boolean func_73868_f() {
        return false;
    }
}

