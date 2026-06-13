/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.text.TextFormatting
 *  org.lwjgl.input.Mouse
 */
package com.dhanantry.scapeandrunparasites.bestiary.client.gui;

import com.dhanantry.scapeandrunparasites.bestiary.blocks.BlockBestiaryEntry;
import com.dhanantry.scapeandrunparasites.bestiary.blocks.SRPBlockCompendiumRegistry;
import com.dhanantry.scapeandrunparasites.bestiary.cap.BestiaryCapability;
import com.dhanantry.scapeandrunparasites.bestiary.cap.IBestiaryProgress;
import com.dhanantry.scapeandrunparasites.bestiary.client.gui.GuiDistortionHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.input.Mouse;

public class BlocksPage
extends GuiScreen {
    private final EntityPlayer player;
    private final GuiScreen parent;
    private boolean isJumbled;
    private final List<BlockBestiaryEntry> discoveredBlocks = new ArrayList<BlockBestiaryEntry>();
    private int scroll = 0;
    private static final ResourceLocation TEX_BACKGROUND = new ResourceLocation("srparasites", "textures/gui/bestiary/blocks_background.png");
    private static final ResourceLocation TEX_SLOT = new ResourceLocation("srparasites", "textures/gui/bestiary/slot.png");
    private static final int GRID_TOP = 40;
    private static final int GRID_BOTTOM = 210;
    private static final int CELL_SIZE = 20;
    private static final int CELL_PADDING = 4;
    private static final int CELL_STRIDE = 24;
    private static final int GAP_COLS = 4;
    private static final int GRID_Y_OFFSET = 8;
    private static final int PAGE_INNER_SHIFT = 10;

    private String distort(String s) {
        return GuiDistortionHelper.jamTextIfNeeded(s, this.isJumbled);
    }

    public BlocksPage(EntityPlayer player, GuiScreen parent) {
        this.player = player;
        this.parent = parent;
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        this.field_146292_n.clear();
        this.isJumbled = GuiDistortionHelper.isDistortionActive(this.field_146297_k);
        this.field_146292_n.add(new GuiButton(1, 10, 10, 60, 20, this.distort("< " + I18n.func_135052_a((String)"bestiary.blocks.home", (Object[])new Object[0]))));
        this.syncDiscoveredBlocks();
    }

    private void syncDiscoveredBlocks() {
        this.discoveredBlocks.clear();
        IBestiaryProgress prog = (IBestiaryProgress)this.player.getCapability(BestiaryCapability.CAP, null);
        if (prog == null) {
            return;
        }
        for (BlockBestiaryEntry entry : SRPBlockCompendiumRegistry.all()) {
            if (!prog.hasSeenBlock(entry.id)) continue;
            this.discoveredBlocks.add(entry);
        }
        Collections.sort(this.discoveredBlocks, new Comparator<BlockBestiaryEntry>(){

            @Override
            public int compare(BlockBestiaryEntry a, BlockBestiaryEntry b) {
                String an = I18n.func_135052_a((String)a.nameKey, (Object[])new Object[0]);
                String bn = I18n.func_135052_a((String)b.nameKey, (Object[])new Object[0]);
                return an.compareToIgnoreCase(bn);
            }
        });
        this.scroll = 0;
    }

    protected void func_146284_a(GuiButton button) throws IOException {
        if (button.field_146127_k == 1) {
            if (this.parent != null) {
                this.field_146297_k.func_147108_a(this.parent);
            } else {
                this.field_146297_k.func_147108_a(null);
            }
            return;
        }
    }

    public void func_146274_d() throws IOException {
        super.func_146274_d();
        int dWheel = Mouse.getEventDWheel();
        if (dWheel == 0) {
            return;
        }
        int direction = (int)Math.signum(dWheel);
        int maxScroll = this.getMaxScroll();
        this.scroll -= direction * 12;
        if (this.scroll < 0) {
            this.scroll = 0;
        }
        if (this.scroll > maxScroll) {
            this.scroll = maxScroll;
        }
    }

    private int getColumns() {
        int gapWidth = 96;
        int availableWidth = this.field_146294_l - 40 - gapWidth;
        int cols = availableWidth / 24;
        if (cols < 2) {
            cols = 2;
        }
        return cols;
    }

    private int getMaxScroll() {
        if (this.discoveredBlocks.isEmpty()) {
            return 0;
        }
        int cols = this.getColumns();
        int rows = (this.discoveredBlocks.size() + cols - 1) / cols;
        int contentHeight = rows * 24;
        int viewHeight = 170;
        return Math.max(0, contentHeight - viewHeight);
    }

    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        int marginSides;
        this.func_146276_q_();
        this.field_146297_k.func_110434_K().func_110577_a(TEX_BACKGROUND);
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        int marginTop = 10;
        int marginBottom = 10;
        int bgX = marginSides = 20;
        int bgY = marginTop;
        int bgWidth = this.field_146294_l - marginSides * 2;
        int bgHeight = this.field_146295_m - marginTop - marginBottom;
        float texW = 256.0f;
        float texH = 256.0f;
        GlStateManager.func_179094_E();
        GlStateManager.func_179109_b((float)bgX, (float)bgY, (float)0.0f);
        GlStateManager.func_179152_a((float)((float)bgWidth / texW), (float)((float)bgHeight / texH), (float)1.0f);
        this.func_73729_b(0, 0, 0, 0, (int)texW, (int)texH);
        GlStateManager.func_179121_F();
        int titleY = 22;
        int subtitleY = 32;
        this.func_73732_a(this.field_146289_q, this.distort(I18n.func_135052_a((String)"bestiary.blocks.title", (Object[])new Object[0])), this.field_146294_l / 2, titleY, 0xFFFFFF);
        this.func_73732_a(this.field_146289_q, this.distort(I18n.func_135052_a((String)"bestiary.blocks.subtitle", (Object[])new Object[0])), this.field_146294_l / 2, subtitleY, 0xAAAAAA);
        this.drawBlockGrid(mouseX, mouseY, partialTicks);
        super.func_73863_a(mouseX, mouseY, partialTicks);
    }

    private void drawBlockGrid(int mouseX, int mouseY, float partialTicks) {
        if (this.discoveredBlocks.isEmpty()) {
            this.func_73732_a(this.field_146289_q, this.distort(I18n.func_135052_a((String)"bestiary.blocks.empty", (Object[])new Object[0])), this.field_146294_l / 2, 125, 0x666666);
            return;
        }
        int slotsPerRow = this.getColumns();
        int leftCols = slotsPerRow / 2;
        int rightCols = slotsPerRow - leftCols;
        int gapWidthPx = 96;
        int totalRowWidth = slotsPerRow * 24 + gapWidthPx;
        int startX = (this.field_146294_l - totalRowWidth) / 2;
        int yTop = 48 - this.scroll;
        BlockBestiaryEntry hovered = null;
        float t = ((float)this.player.field_70173_aa + partialTicks) / 10.0f;
        RenderHelper.func_74520_c();
        GlStateManager.func_179091_B();
        for (int i = 0; i < this.discoveredBlocks.size(); ++i) {
            BlockBestiaryEntry entry = this.discoveredBlocks.get(i);
            int row = i / slotsPerRow;
            int indexInRow = i % slotsPerRow;
            boolean isRightPage = indexInRow >= leftCols;
            int colLocal = isRightPage ? indexInRow - leftCols : indexInRow;
            int leftStartX = startX + 10;
            int rightStartX = startX + leftCols * 24 + gapWidthPx - 10;
            int cellX = isRightPage ? rightStartX + colLocal * 24 : leftStartX + colLocal * 24;
            int cellY = yTop + row * 24;
            if (cellY + 20 < 40 || cellY > 210) continue;
            boolean isHovered = mouseX >= cellX && mouseX <= cellX + 20 && mouseY >= cellY && mouseY <= cellY + 20;
            this.field_146297_k.func_110434_K().func_110577_a(TEX_SLOT);
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            BlocksPage.func_146110_a((int)cellX, (int)cellY, (float)0.0f, (float)0.0f, (int)20, (int)20, (float)20.0f, (float)20.0f);
            int iconSize = 16;
            int cellOffset = 2;
            int iconX = cellX + 2;
            int iconY = cellY + 2;
            float wiggleX = 0.0f;
            float wiggleY = 0.0f;
            if (isHovered) {
                hovered = entry;
                float phase = t;
                wiggleX = (float)Math.sin(phase * 2.0f) * 0.4f;
                wiggleY = (float)Math.sin(phase * 3.0f) * 0.6f;
            }
            ItemStack stack = entry.icon;
            GlStateManager.func_179094_E();
            GlStateManager.func_179109_b((float)wiggleX, (float)wiggleY, (float)0.0f);
            Minecraft.func_71410_x().func_175599_af().func_180450_b(stack, iconX, iconY);
            GlStateManager.func_179121_F();
            if (!isHovered) continue;
            GlStateManager.func_179140_f();
            GlStateManager.func_179097_i();
            BlocksPage.func_73734_a((int)(cellX - 1), (int)(cellY - 1), (int)(cellX + 20 + 1), (int)cellY, (int)-2130706433);
            BlocksPage.func_73734_a((int)(cellX - 1), (int)cellY, (int)cellX, (int)(cellY + 20 + 1), (int)-2130706433);
            BlocksPage.func_73734_a((int)(cellX + 20), (int)cellY, (int)(cellX + 20 + 1), (int)(cellY + 20 + 1), (int)-2130706433);
            BlocksPage.func_73734_a((int)(cellX - 1), (int)(cellY + 20), (int)(cellX + 20 + 1), (int)(cellY + 20 + 1), (int)-2130706433);
            GlStateManager.func_179126_j();
            GlStateManager.func_179145_e();
        }
        RenderHelper.func_74518_a();
        if (hovered != null) {
            ArrayList<String> tooltip = new ArrayList<String>();
            tooltip.add(this.distort(I18n.func_135052_a((String)hovered.nameKey, (Object[])new Object[0])));
            String lore = this.distort(I18n.func_135052_a((String)hovered.loreKey, (Object[])new Object[0]));
            if (!hovered.loreKey.equals(lore)) {
                tooltip.add(TextFormatting.GRAY + lore);
            }
            this.func_146283_a(tooltip, mouseX, mouseY);
            GlStateManager.func_179140_f();
        }
    }

    public boolean func_73868_f() {
        return false;
    }
}

